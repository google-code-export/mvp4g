package com.mvp4g.util.config.element;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mvp4g.util.exception.element.DuplicatePropertyNameException;

public class EventElementTest extends AbstractMvp4gElementTest<EventElement> {

	private static final String[] properties = { "eventObjectClass", "calledMethod", "type", "history" };
	private static final String[] values = { "handlers" };
	
	@Test
	public void testGetCalledMethod() throws DuplicatePropertyNameException{
		element.setType( "display" );
		assertEquals( "onDisplay", element.getCalledMethod() );
		element.setCalledMethod( "onDisplayCalled" );
		assertEquals( "onDisplayCalled", element.getCalledMethod() );
	}
	
	@Test
	public void testGetCalledMethodOneCharacter() throws DuplicatePropertyNameException{
		element.setType( "o" );
		assertEquals( "onO", element.getCalledMethod() );
	}


	@Test
	public void testHasHistory() throws DuplicatePropertyNameException{
		assertFalse( element.hasHistory() );
		element.setHistory( "converter" );
		assertTrue( element.hasHistory() );
	}

	@Test
	public void testToString() throws DuplicatePropertyNameException{
		element.setType( "type" );
		assertEquals( "[type]", element.toString() );
	}

	@Override
	protected String[] getProperties() {
		return properties;
	}

	@Override
	protected String getTag() {
		return "event";
	}

	@Override
	protected String[] getValues() {
		return values;
	}

	@Override
	protected EventElement newElement() {
		return new EventElement();
	}

	@Override
	protected String getUniqueIdentifierName() {
		return "type";
	}

}