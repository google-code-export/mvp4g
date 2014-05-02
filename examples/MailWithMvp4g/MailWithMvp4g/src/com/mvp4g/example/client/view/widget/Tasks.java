/*
 * Copyright 2007 Google Inc.
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
package com.mvp4g.example.client.view.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Composite that represents a collection of <code>Task</code> items.
 */
public class Tasks extends Composite {

	interface Binder extends UiBinder<FlowPanel, Tasks> {
	}

	interface Style extends CssResource {
		String item();
	}

	@UiField
	Style style;

	private static final Binder binder = GWT.create( Binder.class );

	private FlowPanel panel;

	public Tasks() {
		panel = binder.createAndBindUi( this );
		initWidget( panel );
	}

	public void addTask( String task ) {
		CheckBox cb = new CheckBox( task );
		cb.setStyleName( style.item() );
		panel.add( cb );
	}
}
