/**
 * 
 */
package com.mvp4g.util.config.loader.xml;

import org.apache.commons.configuration.XMLConfiguration;

import com.mvp4g.util.config.element.EventElement;

/**
 * A class responsible for loading all Events defined in the mvp4g-config.xml file.
 * 
 * @author javier
 * 
 */
public class EventsLoader extends Mvp4gElementLoader<EventElement> {

	static final String[] REQUIRED_ATTRIBUTES = { "type" };
	static final String[] OPTIONAL_ATTRIBUTES = { "calledMethod", "eventObjectClass", "history" };
	static final String[] MULTI_VALUE_ATTRIBUTES = { "handlers" };

	@SuppressWarnings( "unchecked" )
	public EventsLoader( XMLConfiguration xmlConfig ) {
		super( xmlConfig.configurationsAt( "events.event" ) );
	}

	@Override
	protected String getElementLabel() {
		return "Event";
	}

	@Override
	protected String[] getRequiredAttributeNames() {
		return REQUIRED_ATTRIBUTES;
	}

	@Override
	protected String[] getOptionalAttributeNames() {
		return OPTIONAL_ATTRIBUTES;
	}

	@Override
	protected String[] getMultiValueAttributeNames() {
		return MULTI_VALUE_ATTRIBUTES;
	}

	@Override
	protected EventElement newElement() {
		return new EventElement();
	}

}