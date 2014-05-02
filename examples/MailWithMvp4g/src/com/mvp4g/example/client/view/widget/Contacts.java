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
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * A component that displays a list of contacts.
 */
public class Contacts extends Composite {

	interface Binder extends UiBinder<Widget, Contacts> {
	}

	interface Style extends CssResource {
		String item();
	}

	private static final Binder binder = GWT.create( Binder.class );

	@UiField
	ComplexPanel panel;
	@UiField
	Style style;

	public Contacts() {
		initWidget( binder.createAndBindUi( this ) );
	}

	public Anchor addContact( String contact ) {
		Anchor link = new Anchor( contact );
		link.setStyleName( style.item() );
		panel.add( link );
		return link;
	}
}
