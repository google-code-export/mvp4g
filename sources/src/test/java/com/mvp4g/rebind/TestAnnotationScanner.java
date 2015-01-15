package com.mvp4g.rebind;

import static org.junit.Assert.assertEquals;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.dev.javac.typemodel.TypeOracleStub;
import com.google.gwt.dev.util.UnitTestTreeLogger;
import com.mvp4g.client.annotation.EventHandler;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.History;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.annotation.Service;
import com.mvp4g.rebind.test_tools.annotation.OneEventHandler;
import com.mvp4g.rebind.test_tools.annotation.OneEvents;
import com.mvp4g.rebind.test_tools.annotation.OneHistory;
import com.mvp4g.rebind.test_tools.annotation.OnePresenter;
import com.mvp4g.rebind.test_tools.annotation.OneService;

public class TestAnnotationScanner {

	private TreeLogger logger = null;
	private TypeOracleStub oracle = null;

	@SuppressWarnings( "rawtypes" )
	private static final Class[] annotations = new Class[] { Presenter.class, History.class, Events.class, Service.class, EventHandler.class };

	@Before
	public void setUp() {
		logger = new UnitTestTreeLogger.Builder().createLogger();
		oracle = new TypeOracleStub();
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void testEmpty() {
		Map<Class<? extends Annotation>, List<JClassType>> scanResult = AnnotationScanner.scan( logger, oracle, annotations );

		for ( Class<? extends Annotation> annotation : annotations ) {
			assertEquals( 0, scanResult.get( annotation ).size() );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void testWithClass() {

		JClassType[] types = new JClassType[5];
		types[0] = oracle.findType( OnePresenter.class.getName() );
		types[1] = oracle.findType( OneHistory.class.getName() );
		types[2] = oracle.findType( OneEvents.class.getName() );
		types[3] = oracle.findType( OneService.class.getName() );
		types[4] = oracle.findType( OneEventHandler.class.getName() );

		Map<Class<? extends Annotation>, List<JClassType>> scanResult = AnnotationScanner.scan( logger, oracle, annotations );
		for ( int i = 0; i < types.length; i++ ) {
			assertEquals( types[i], scanResult.get( annotations[i] ).get( 0 ) );
		}
	}

}
