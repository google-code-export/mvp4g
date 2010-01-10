package com.mvp4g.util.config.loader.annotation;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.user.client.rpc.RemoteService;
import com.mvp4g.client.annotation.Service;
import com.mvp4g.util.config.Mvp4gConfiguration;
import com.mvp4g.util.config.element.ServiceElement;
import com.mvp4g.util.exception.element.DuplicatePropertyNameException;
import com.mvp4g.util.exception.loader.Mvp4gAnnotationException;

/**
 * A class responsible for loading information contained in <code>Services</code> annotation.
 * 
 * @author plcoirier
 * 
 */
public class ServiceAnnotationsLoader extends Mvp4gAnnotationsLoader<Service> {

	/*
	 * (non-Javadoc)
	 * @see com.mvp4g.util.config.loader.annotation.Mvp4gAnnotationsLoader#loadElement(com.google.gwt.core.ext.typeinfo.JClassType, java.lang.annotation.Annotation, com.mvp4g.util.config.Mvp4gConfiguration)
	 */
	@Override
	protected void loadElement( JClassType c, Service annotation, Mvp4gConfiguration configuration ) throws Mvp4gAnnotationException {

		String className = c.getQualifiedSourceName();

		String serviceName = buildElementNameIfNeeded( annotation.name(), className, "" );

		ServiceElement service = new ServiceElement();
		try {
			service.setName( serviceName );
			service.setClassName( className );
			service.setPath( annotation.path() );
		} catch ( DuplicatePropertyNameException e ) {
			//setters are only called once, so this error can't occur.
		}

		addElement( configuration.getServices(), service, c, null );

	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mvp4g.util.config.loader.annotation.Mvp4gAnnotationsLoader#getMandatoryInterfaceName()
	 */
	@Override
	protected String getMandatoryInterfaceName() {
		return RemoteService.class.getCanonicalName();
	}

}