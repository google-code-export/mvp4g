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
package com.mvp4g.util.config.element;

import com.mvp4g.util.exception.element.DuplicatePropertyNameException;

/**
 * An Mvp4g Presenter configuration element.</p>
 * 
 * @author javier
 */
public class PresenterElement extends EventHandlerElement {

	public PresenterElement() {
		super( "presenter" );
	}

	public void setView( String view ) throws DuplicatePropertyNameException {
		setProperty( "view", view );
	}

	public String getView() {
		return getProperty( "view" );
	}
	
	public String getInverseView() {
		return getProperty( "inverseView" );
	}

	public boolean hasInverseView() {
		return Boolean.TRUE.toString().equalsIgnoreCase( getInverseView() );
	}

	public void setInverseView( String inverseView ) throws DuplicatePropertyNameException {
		setProperty( "inverseView", inverseView );
	}

}
