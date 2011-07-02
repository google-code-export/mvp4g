package com.mvp4g.example.client.product.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.example.client.product.bean.ProductBean;
import com.mvp4g.example.client.product.view.ProductCreationView;

@Presenter( view = ProductCreationView.class )
public class ProductCreationPresenter extends AbstractProductPresenter {

	public void onGoToCreation() {
		view.getName().setValue( "" );
		eventBus.changeBody( view );
	}

	@Override
	protected void clickOnLeftButton( ClickEvent event ) {
		product = new ProductBean();
		fillBean();
		service.createProduct( product, new AsyncCallback<Void>() {

			public void onSuccess( Void result ) {
				eventBus.productCreated( product );
				eventBus.goToDisplay( product );
				eventBus.displayMessage( "Creation Succeeded" );
			}

			public void onFailure( Throwable caught ) {
				eventBus.displayMessage( "Creation Failed" );
			}
		} );
	}

	@Override
	protected void clickOnRightButton( ClickEvent event ) {
		clear();
	}

	@Override
	protected String getPageName() {
		return "Product Create Page";
	}

}
