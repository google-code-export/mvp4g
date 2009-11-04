/*
 * Copyright 2009 Pierre-Laurent Coirier
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.mvp4g.client.event;

import com.mvp4g.client.Mvp4gException;

/**
 * This class is in charge of dispatching an event to the right presenters thanks to commands
 * 
 * @author plcoirier
 * 
 */
public abstract class XmlEventBus extends BaseEventBus implements EventBusWithLookup {

	/**
	 * Execute the command associated with the event type in order to trigger the presenters that
	 * can handle the event. Forward to the handler the object associated with the event.<br/>
	 * <br/>
	 * Store the event in GWT History stack if needed and possible. If an event can't be stored in
	 * GWT History stack (ie when no History Converter has been associated to the event), the method
	 * will not try to store in GWT History stack even if storeInHistory is true.
	 * 
	 * @param eventType
	 *            type of the event to dispatch
	 * @param form
	 *            form to forward to the handlers
	 * @param storeInHistory
	 *            indicates if the event must be stored in the GWT History stack. This parameter
	 *            matters only if the event can be stored in the GWT History stack (ie if a History
	 *            converter is associated with the event).
	 * @throws Mvp4gException
	 *             exception thrown in case an error occurs while the event is dispatched.<br/>
	 *             The most common cases that this error could be thrown is in case:
	 *             <ul>
	 *             <li>the type of the event can't be handled by the event bus because no command is
	 *             associated to it.</li>
	 *             <li>the class type of the object to be used by the handler methods is different
	 *             from the class type of the handlers method.</li>
	 *             </ul>
	 *             This exception shouldn't be caught. If this exception is raised, it means that
	 *             there is an error in the application that needs to be fixed (most of the time, an
	 *             error in the configuration file).
	 */
	abstract public void dispatch( String eventType, Object form ); 
	
	
	
	/*{
		try {
			commands.get( eventType ).execute( form, storeInHistory );
		} catch ( NullPointerException e ) {
			//if it's a configuration error, it means the error happened in this class 
			if ( e.getStackTrace()[0].getClassName().equals( XmlEventBus.class.getName() ) ) {
				throw new Mvp4gException( "Event " + eventType + " doesn't exist. Have you forgotten to add it to your Mvp4g configuration file?" );
			}
			throw e;
		} catch ( ClassCastException e ) {
			//if it's a configuration error, it means the error happened in the command called by this class
			if ( e.getStackTrace()[1].getClassName().equals( XmlEventBus.class.getName() ) ) {
				throw new Mvp4gException( "Class of the object sent with event " + eventType
						+ " is incorrect. It should be the same as the one configured in the Mvp4g configuration file." );
			}
			throw e;
		}
	}*/





	/**
	 * Execute the command associated with the event type in order to trigger the presenters that
	 * can handle the event. In this case no object is forwarded to the handler(s).<br/>
	 * <br/>
	 * 
	 * The event is automatically stored in GWT History stack if possible (ie if an History
	 * Converter is associated with the event)
	 * 
	 * Calling dispatch(eventType) is equivalent to calling dispatch(eventType, null, true).
	 * 
	 * @param eventType
	 *            type of the event to dispatch
	 * @throws Mvp4gException
	 *             exception thrown in case an error occurs while the event is dispatched.<br/>
	 *             The most common cases that this error could be thrown is in case:
	 *             <ul>
	 *             <li>the type of the event can't be handled by the event bus because no command is
	 *             associated to it.</li>
	 *             <li>the class type of the object to be used by the handler methods is different
	 *             from the class type of the handlers method.</li>
	 *             </ul>
	 *             This exception shouldn't be caught. If this exception is raised, it means that
	 *             there is an error in the application that needs to be fixed (most of the time, an
	 *             error in the configuration file).
	 */
	public void dispatch( String eventType ) {
		dispatch( eventType, null );
	}

	/**
	 * Execute the command associated with the event type in order to trigger the presenters that
	 * can handle the event. Forward to the handler the object associated with the event. <br/>
	 * In this function, the type is given as a enumeration. The toString method of this enumeration
	 * must return the type of the event defined the configuration file.<br/>
	 * <br/>
	 * The event is automatically stored in GWT History stack if possible (ie if an History
	 * Converter is associated with the event)<br/>
	 * <br/>
	 * Calling dispatch(enumEventType, form) is equivalent to calling
	 * dispatch(enumEventType.toString(), form, true).
	 * 
	 * 
	 * 
	 * @param enumEventType
	 *            type of the event to dispatch
	 * @param form
	 *            form to forward to the handlers
	 * @throws Mvp4gException
	 *             exception thrown in case an error occurs while the event is dispatched.<br/>
	 *             The most common cases that this error could be thrown is in case:
	 *             <ul>
	 *             <li>the type of the event can't be handled by the event bus because no command is
	 *             associated to it.</li>
	 *             <li>the class type of the object to be used by the handler methods is different
	 *             from the class type of the handlers method.</li>
	 *             </ul>
	 *             This exception shouldn't be caught. If this exception is raised, it means that
	 *             there is an error in the application that needs to be fixed (most of the time, an
	 *             error in the configuration file).
	 */
	public <E extends Enum<E>> void dispatch( Enum<E> enumEventType, Object form ) {
		this.dispatch( enumEventType.toString(), form );
	}

	/**
	 * Execute the command associated with the event type in order to trigger the presenters that
	 * can handle the event. In this case no object is forwarded to the handler(s).<br/>
	 * <br/>
	 * In this function, the type is given as a enumeration. The toString method of this enumeration
	 * must return the type of the event defined the configuration file.<br/>
	 * <br/>
	 * The event is automatically stored in GWT History stack if possible (ie if an History
	 * Converter is associated with the event)<br/>
	 * <br/>
	 * Calling dispatch(enumEventType) is equivalent to calling dispatch(enumEventType.toString(),
	 * null, true).
	 * 
	 * @param enumEventType
	 *            type of the event to dispatch
	 * @throws Mvp4gException
	 *             exception thrown in case an error occurs while the event is dispatched.<br/>
	 *             The most common cases that this error could be thrown is in case:
	 *             <ul>
	 *             <li>the type of the event can't be handled by the event bus because no command is
	 *             associated to it.</li>
	 *             <li>the class type of the object to be used by the handler methods is different
	 *             from the class type of the handlers method.</li>
	 *             </ul>
	 *             This exception shouldn't be caught. If this exception is raised, it means that
	 *             there is an error in the application that needs to be fixed (most of the time, an
	 *             error in the configuration file).
	 */
	public <E extends Enum<E>> void dispatch( Enum<E> enumEventType ) {
		this.dispatch( enumEventType.toString(), null );
	}
	

	protected void handleClassCastException( ClassCastException e, String eventType ) {
		if ( e.getStackTrace()[0].getClassName().equals( this.getClass().getName() ) ) {
			throw new Mvp4gException( "Class of the object sent with event " + eventType + " is incorrect." );
		}
	}

}