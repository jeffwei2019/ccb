package com.beyondsoft.itsm.core.webservice;

import java.lang.reflect.ParameterizedType;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public abstract class AbstractCxfWebService<T> {
	private final String url;
	private final Class<?> clazz;

	public AbstractCxfWebService(String url) {
		this.url = url;
		this.clazz = getClazz();
	}

	private Class<?> getClazz() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		return (Class<?>) type.getActualTypeArguments()[0];
	}

	public T createObject() {
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
		jaxWsProxyFactoryBean.setAddress(url);
		jaxWsProxyFactoryBean.setServiceClass(this.clazz);
		return (T) jaxWsProxyFactoryBean.create();
	}
}
