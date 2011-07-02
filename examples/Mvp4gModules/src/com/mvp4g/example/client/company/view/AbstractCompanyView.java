package com.mvp4g.example.client.company.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.view.BaseCycleView;
import com.mvp4g.example.client.company.presenter.AbstractCompanyPresenter.CompanyViewInterface;

public abstract class AbstractCompanyView extends BaseCycleView implements CompanyViewInterface {

	private Button leftButton = null;
	private Button rightButton = null;
	private Button selectButton = null;

	public HasClickHandlers getLeftButton() {
		return leftButton;
	}

	public HasClickHandlers getRightButton() {
		return rightButton;
	}
	
	public HasClickHandlers getSelectNameButton() {
		return selectButton;
	}

	public void alert(String message){
		Window.alert( message );
	}
	
	public boolean confirm(String message){
		return Window.confirm( message );
	}

	public void createView() {
		selectButton = new Button( "Select Name");
		leftButton = new Button( getLeftButtonName() );
		rightButton = new Button( getRightButtonName() );

		Grid grid = new Grid( 2, 2 );
		grid.setText( 0, 0, "Name :" );
		grid.setWidget( 0, 1, createAndGetNameWidget() );

		HorizontalPanel buttons = new HorizontalPanel();
		buttons.add( selectButton );
		buttons.add( leftButton );
		buttons.add( rightButton );

		grid.setWidget( 1, 1, buttons );

		initWidget( grid );
	}

	abstract protected String getLeftButtonName();

	abstract protected String getRightButtonName();

	abstract protected Widget createAndGetNameWidget();

}
