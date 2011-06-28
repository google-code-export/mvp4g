/*
 * Copyright 2010 Pierre-Laurent Coirier
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

package com.mvp4g.client.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.google.gwt.inject.client.GinModule;
import com.mvp4g.client.DefaultMvp4gGinModule;
import com.mvp4g.client.Mvp4gModule;

/**
 * This annotation indicates that the annotated interface should be used to define the event bus of
 * the specified module. This annotation can be used only on interfaces that extends
 * <code>EventBus</code>.<br/>
 * <br/>
 * The annotation has the following attributes:
 * <ul>
 * <li>startView: class of the view that should be loaded when the module starts. This view must be
 * one of the view injected to a presenter.</li>
 * <li>startViewName: you can also specify the name of the start view (in case you have several
 * views with the same class, in this case you would need to name your views). In any case, you
 * still need to specify the class of the view.</li>
 * <li>module: class of the module for which the annotated interface should be used to generate the
 * event bus. If no module is specified, it means that the interface should be used to generate the
 * Root Module (first module to be loaded and only module without parent).</li>
 * <li>historyOnStart: if true, the current history state will be fired when the application starts.
 * </li>
 * <li>ginModules: classes of the GIN modules the framework should use when generating presenters,
 * event handlers, history converters & views. You can specify zero to severals GIN modules.</li>
 * </ul>
 * 
 * 
 * @author plcoirier
 * 
 */

@Retention( RetentionPolicy.RUNTIME )
public @interface Events {

	String startViewName() default "";

	Class<?> startView();

	Class<? extends Mvp4gModule> module() default Mvp4gModule.class;

	boolean historyOnStart() default false;

	Class<? extends GinModule>[] ginModules() default DefaultMvp4gGinModule.class;
	
	String[] ginModuleProperties() default {};

}
