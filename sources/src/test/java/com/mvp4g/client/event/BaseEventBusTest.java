package com.mvp4g.client.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mvp4g.client.Mvp4gException;
import com.mvp4g.client.history.NavigationConfirmationInterface;
import com.mvp4g.client.history.NavigationEventCommand;
import com.mvp4g.client.test_tools.EventFilterStub;
import com.mvp4g.client.test_tools.Mvp4gModuleStub;
import com.mvp4g.util.test_tools.annotation.Presenters;
import com.mvp4g.util.test_tools.annotation.Presenters.SimplePresenter;

public class BaseEventBusTest {

	private BaseEventBus bus = null;
	private Mvp4gModuleStub module;
	private EventFilterStub filter;

	@Before
	public void setUp() {
		bus = new BaseEventBus() {

			@SuppressWarnings( "unchecked" )
			@Override
			protected <T extends EventHandlerInterface<?>> T createHandler( Class<T> handlerClass ) {
				return (T)( ( Presenters.SimplePresenter.class.equals( handlerClass ) ) ? new Presenters.SimplePresenter() : null );
			}

			public void setNavigationConfirmation( NavigationConfirmationInterface navigationConfirmation ) {
								
			}

			public void confirmNavigation( NavigationEventCommand event ) {
								
			}

		};
		module = new Mvp4gModuleStub( bus );
		filter = new EventFilterStub();
	}

	@Test
	public void testSetAndGetHistoryStored() {
		assertTrue( bus.isHistoryStored() );
		bus.setHistoryStored( false );
		assertFalse( bus.isHistoryStored() );
		bus.setHistoryStored( true );
		assertTrue( bus.isHistoryStored() );
	}

	@Test
	public void testSetAndGetHistoryStoredForNextOne() {
		bus.setHistoryStored( false );
		bus.setHistoryStoredForNextOne( true );
		assertTrue( bus.isHistoryStored() );
		bus.place( module, "", "" );
		assertFalse( bus.isHistoryStored() );

		bus.setHistoryStored( true );
		bus.setHistoryStoredForNextOne( false );
		assertFalse( bus.isHistoryStored() );
		bus.place( module, "", "" );
		assertTrue( bus.isHistoryStored() );

		bus.setHistoryStored( true );
		bus.setHistoryStoredForNextOne( true );
		assertTrue( bus.isHistoryStored() );
		bus.place( module, "", "" );
		assertTrue( bus.isHistoryStored() );

		bus.setHistoryStored( false );
		bus.setHistoryStoredForNextOne( false );
		assertFalse( bus.isHistoryStored() );
		bus.place( module, "", "" );
		assertFalse( bus.isHistoryStored() );

	}

	@Test
	public void testClearHistory() {
		String test = "test";
		String form = "form";
		module.place( test, form );
		assertEquals( module.getEventType(), test );
		assertEquals( module.getForm(), form );

		bus.clearHistory( module );
		assertNull( module.getEventType() );
		assertNull( module.getForm() );

		module.place( test, form );
		assertEquals( module.getEventType(), test );
		assertEquals( module.getForm(), form );

		bus.setHistoryStored( false );
		bus.clearHistory( module );
		assertEquals( module.getEventType(), test );
		assertEquals( module.getForm(), form );

		bus.setHistoryStored( true );
		bus.clearHistory( module );
		assertNull( module.getEventType() );
		assertNull( module.getForm() );
	}

	@Test
	public void testPlace() {

		String eventType = "eventType";
		String form = "form";

		bus.setHistoryStored( false );
		bus.place( module, eventType, form );

		assertNull( module.getEventType() );
		assertNull( module.getForm() );

		bus.setHistoryStored( true );
		bus.place( module, eventType, form );

		assertEquals( module.getEventType(), eventType );
		assertEquals( module.getForm(), form );

	}

	@Test
	public void testSetterGetterFilterEnabled() {
		assertTrue( bus.isFilteringEnabled() );
		bus.setFilteringEnabled( false );
		assertFalse( bus.isFilteringEnabled() );
		bus.setFilteringEnabled( true );
		assertTrue( bus.isFilteringEnabled() );
	}

	@Test
	public void testFilter() {
		assertTrue( bus.filterEvent( "test" ) );
		filter.setFilter( false );
		bus.addEventFilter( filter );
		assertFalse( bus.filterEvent( "test" ) );
		bus.removeEventFilter( filter );
		assertTrue( bus.filterEvent( "test" ) );
	}

	@Test
	public void testMultipleFilters() {
		assertTrue( bus.filterEvent( "test" ) );
		EventFilterStub filter1 = new EventFilterStub();
		EventFilterStub filter2 = new EventFilterStub();
		bus.addEventFilter( filter1 );
		bus.addEventFilter( filter2 );

		filter1.setFilter( true );
		filter2.setFilter( true );
		assertTrue( bus.filterEvent( "test" ) );

		filter1.setFilter( true );
		filter2.setFilter( false );
		assertFalse( bus.filterEvent( "test" ) );

		filter1.setFilter( false );
		filter2.setFilter( true );
		assertFalse( bus.filterEvent( "test" ) );

		filter1.setFilter( false );
		filter2.setFilter( false );
		assertFalse( bus.filterEvent( "test" ) );
	}

	@Test
	public void testSetFilterEnabledForNextOne() {
		assertTrue( bus.filterEvent( "test" ) );
		filter.setFilter( false );
		bus.addEventFilter( filter );
		assertFalse( bus.filterEvent( "test" ) );

		bus.setFilteringEnabledForNextOne( false );
		assertTrue( bus.filterEvent( "test" ) );
		assertFalse( bus.filterEvent( "test" ) );

		bus.setFilteringEnabled( false );
		bus.setFilteringEnabledForNextOne( false );
		assertTrue( bus.filterEvent( "test" ) );
		assertTrue( bus.filterEvent( "test" ) );

		bus.setFilteringEnabled( false );
		bus.setFilteringEnabledForNextOne( true );
		assertFalse( bus.filterEvent( "test" ) );
		assertTrue( bus.filterEvent( "test" ) );
	}

	@Test
	public void testAddRemoveHandler() {
		List<SimplePresenter> list = bus.getHandlers( SimplePresenter.class );
		assertNull( list );

		SimplePresenter p = bus.addHandler( SimplePresenter.class );
		list = bus.getHandlers( SimplePresenter.class );
		assertTrue( list.size() == 1 );
		assertEquals( list.get( 0 ), p );

		bus.removeHandler( p );
		list = bus.getHandlers( SimplePresenter.class );
		assertTrue( list.size() == 0 );
	}
	
	@Test
	public void testDefaultAddHandler() {
		List<SimplePresenter> list = bus.getHandlers( SimplePresenter.class );	

		SimplePresenter p = bus.addHandler( SimplePresenter.class );
		list = bus.getHandlers( SimplePresenter.class );
		assertTrue( list.size() == 1 );
		assertEquals( list.get( 0 ), p );
		assertTrue( p.isBindCalled() );
		
		p = bus.addHandler( SimplePresenter.class, true );
		assertTrue( list.size() == 2 );
		assertEquals( list.get( 1 ), p );
		assertTrue( p.isBindCalled() );
		
		p = bus.addHandler( SimplePresenter.class, false );
		assertTrue( list.size() == 3 );
		assertEquals( list.get( 2 ), p );
		assertFalse( p.isBindCalled() );

	}

	@Test
	public void testAddUnknownHanlder() {
		try {
			bus.addHandler( Presenters.PresenterNoParameter.class );
			fail();
		} catch ( Mvp4gException e ) {
			assertEquals(
					"Handler with type "
							+ Presenters.PresenterNoParameter.class.getName()
							+ " couldn't be created by the Mvp4g. Have you forgotten to set multiple attribute to true for this handler or are you trying to create an handler that belongs to another module (another type of event bus injected in this handler)?",
					e.getMessage() );
		}
	}
}