package com.beyondsoft.itsm.core.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.springframework.util.Assert;

import com.beyondsoft.itsm.core.exception.BussinessException;
import com.beyondsoft.itsm.core.exception.ResponseStatus;
import com.ca.www.UnicenterServicePlus.ServiceDesk.USD_WebServiceLocator;
import com.ca.www.UnicenterServicePlus.ServiceDesk.USD_WebServiceSoap;

/**
 * 
 * @author jeff
 *
 */
public class CaWebServiceUtil {
	private static CaWebServiceUtil caWebServiceUtil;

	private USD_WebServiceSoap uwss;

	private CaWebServiceProperties caWebServiceProperties;

	private String url; // ca webservice url

	private String username; // 用户名

	private String password; // 密码

	private Integer sid; // ca返回sid

	private CaWebServiceUtil() {
		caWebServiceProperties = SpringContextUtil.getBean("caWebServiceProperties");

		Assert.notNull(caWebServiceProperties.getUrl(), "ca webservice访问url为空");
		Assert.notNull(caWebServiceProperties.getUsername(), "ca webservice访问用户名为空");
		Assert.notNull(caWebServiceProperties.getPassword(), "ca webservice访问密码为空");

		this.url = caWebServiceProperties.getUrl();
		this.username = caWebServiceProperties.getUsername();
		this.password = caWebServiceProperties.getPassword();

		init();

	};

	public static CaWebServiceUtil getInstance() {
		if (null == caWebServiceUtil) {
			synchronized (CaWebServiceUtil.class) {
				if (null == caWebServiceUtil) {
					caWebServiceUtil = new CaWebServiceUtil();
				}
			}
		}
		return caWebServiceUtil;
	}

	/**
	 * 初始化
	 * 
	 * @throws ServiceException
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	private void init() {
		try {
			uwss = new USD_WebServiceLocator().getUSD_WebServiceSoap(new URL(this.url));
			this.login(); // 登陆ca
		} catch (Exception e) {
			throw new BussinessException(ResponseStatus.CA_ERROR.getCode(), e.getMessage());
		}
	}

	/**
	 * 登陆
	 * 
	 * @throws RemoteException
	 */
	public void login() {
		try {
			this.sid = uwss.login(this.username, this.password);
		} catch (Exception e) {
			throw new BussinessException(ResponseStatus.CA_ERROR.getCode(), e.getMessage());
		}
	}

	/**
	 * 退出ca
	 */
	public void logout() {
		try {
			uwss.logout(this.sid);
			caWebServiceUtil = null;
		} catch (Exception e) {
			throw new BussinessException(ResponseStatus.CA_ERROR.getCode(), e.getMessage());
		}
	}

	/**
	 * 更新ca对象
	 * 
	 * @param obj
	 */
	public void updateObject(CaObject obj) {
		try {
			uwss.updateObject(this.sid, obj.getPersid(), obj.arrayOfAttributes(), new String[0]);
		} catch (Exception e) {
			throw new BussinessException(ResponseStatus.CA_ERROR.getCode(), e.getMessage());
		}
	}

	/**
	 * 创建ca对象
	 * 
	 * @param obj
	 */
	public void createObject(CaObject obj) {
		try {
			uwss.createObject(this.sid, obj.getObjectType(), obj.arrayOfAttributes(), new String[0],
					obj.getCreateObjectResult(), obj.getNewHandle());
		} catch (Exception e) {
			throw new BussinessException(ResponseStatus.CA_ERROR.getCode(), e.getMessage());
		}
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Integer getSid() {
		return sid;
	}

}
