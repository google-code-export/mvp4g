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
 * An Mvp4g History converter configuration element.</p>
 * 
 * @author plcoirier
 */
public class HistoryConverterElement extends Mvp4gWithServicesElement {

	public HistoryConverterElement() {
		super( "historyConverter" );
	}
	
	public void setConvertParams( String convertParams ) throws DuplicatePropertyNameException {
		setProperty( "convertParams", convertParams );
	}

	public String getConvertParams() {
		String convertParams = getProperty( "convertParams" );
		// By default it's true
		return ( convertParams == null ) ? "true" : getProperty( "convertParams" );
	}

	public boolean isConvertParams() {
		return Boolean.TRUE.toString().equals( getConvertParams() );
	}

}
