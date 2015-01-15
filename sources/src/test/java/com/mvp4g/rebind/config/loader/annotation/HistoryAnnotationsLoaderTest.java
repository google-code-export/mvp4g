package com.mvp4g.rebind.config.loader.annotation;

import java.util.Set;

import com.mvp4g.client.annotation.History;
import com.mvp4g.rebind.config.element.HistoryConverterElement;
import com.mvp4g.rebind.test_tools.annotation.HistoryConverters;
import com.mvp4g.rebind.test_tools.annotation.history_converters.SimpleHistoryConverter;

public class HistoryAnnotationsLoaderTest extends AbstractMvp4gAnnotationsWithServiceLoaderTest<History, HistoryAnnotationsLoader> {

	@Override
	protected HistoryAnnotationsLoader createLoader() {
		return new HistoryAnnotationsLoader();
	}

	@SuppressWarnings( "unchecked" )
	@Override
	protected Set<HistoryConverterElement> getSet() {
		return configuration.getHistoryConverters();
	}

	@Override
	protected Class<?> getSimpleClass() {
		return SimpleHistoryConverter.class;
	}

	@Override
	protected Class<?> getWithNameClass() {
		return HistoryConverters.HistoryConverterWithName.class;
	}

	@Override
	protected Class<?> getClassNotAsync() {
		return HistoryConverters.HistoryConverterNotAsync.class;
	}

	@Override
	protected Class<?> getClassNotPublic() {
		return HistoryConverters.HistoryConverterNotPublic.class;
	}

	@Override
	protected Class<?> getClassWithMoreThanOne() {
		return HistoryConverters.HistoryConverterWithMoreThanOneParameter.class;
	}

	@Override
	protected Class<?> getClassWithNoParameter() {
		return HistoryConverters.HistoryConverterWithNoParameter.class;
	}

	@Override
	protected Class<?> getService() {
		return HistoryConverters.HistoryConverterWithService.class;
	}

	@Override
	protected Class<?> getServiceWithName() {
		return HistoryConverters.HistoryConverterWithServiceAndName.class;
	}

	@Override
	protected Class<?> getSameService() {
		return HistoryConverters.HistoryConverterWithSameService.class;
	}

	@Override
	protected Class<?> getWrongInterface() {
		return Object.class;
	}

}
