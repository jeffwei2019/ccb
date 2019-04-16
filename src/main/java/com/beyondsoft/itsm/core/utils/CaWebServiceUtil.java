package com.beyondsoft.itsm.core.utils;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.beyondsoft.itsm.core.exception.BussinessException;
import com.beyondsoft.itsm.core.exception.ResponseStatus;
import com.ca.www.UnicenterServicePlus.ServiceDesk.USD_WebServiceLocator;
import com.ca.www.UnicenterServicePlus.ServiceDesk.USD_WebServiceSoap;

/**
 * ca webserivce工具类
 * 
 * @author jeff
 *
 */
public class CaWebServiceUtil {
	private static final Logger logger = LoggerFactory.getLogger(CaWebServiceUtil.class);

	private static ReentrantLock lock = new ReentrantLock();

	private static USD_WebServiceSoap uwss;

	private String url; // ca webservice url

	private String username; // 用户名

	private String password; // 密码

	private Integer sid; // ca返回sid

	/**
	 * 通过用户名生成CaWebServiceUtil实例
	 * 
	 * @param username 用户名
	 * @return
	 */
	public static CaWebServiceUtil fromUsername(String username) {
		CaWebServiceProperties caWebServiceProperties = SpringContextUtil.getBean("caWebServiceProperties");
		// TODO:暂时未提供数据库查询用户密码
		String password = "";
		CaWebServiceUtil caWebServiceUtil = new CaWebServiceUtil(caWebServiceProperties.getUrl(), username, password);
		return caWebServiceUtil;
	}

	/**
	 * 通过配置文件生成CaWebServiceUtil实例
	 * 
	 * @return
	 */
	public static CaWebServiceUtil fromDefault() {
		CaWebServiceProperties caWebServiceProperties = SpringContextUtil.getBean("caWebServiceProperties");

		CaWebServiceUtil caWebServiceUtil = new CaWebServiceUtil(caWebServiceProperties.getUrl(),
				caWebServiceProperties.getUsername(), caWebServiceProperties.getPassword());
		return caWebServiceUtil;
	}

	private CaWebServiceUtil(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;

		Assert.notNull(this.url, "ca webservice访问url为空");
		Assert.notNull(this.username, "ca webservice访问用户名为空");
		Assert.notNull(this.password, "ca webservice访问密码为空");
		logger.debug("ca url:{},用户名:{},密码:{}", this.url, this.username, this.password);
		init();
	}

	/**
	 * 初始化 uwss初始化，用户登陆
	 * 
	 */
	private void init() {
		try {
			if (uwss == null) {
				try {
					lock.lock();
					if (uwss == null) {
						uwss = new USD_WebServiceLocator().getUSD_WebServiceSoap(new URL(this.url));
					}
				} finally {
					if (lock.isHeldByCurrentThread()) {
						lock.unlock();
					}
				}
			}
			this.login(); // 登陆ca
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BussinessException(ResponseStatus.CA_ERROR.getCode(), e.getMessage());
		}
	}

	/**
	 * 登陆
	 * 
	 */
	public void login() {
		try {
			this.sid = uwss.login(this.username, this.password);
			logger.debug("ca返回sid:{}", this.sid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BussinessException(ResponseStatus.CA_ERROR.getCode(), e.getMessage());
		}
	}

	/**
	 * 退出ca
	 */
	public void logout() {
		try {
			uwss.logout(this.sid);
		} catch (Exception e) {
			logger.error(e.getMessage());
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
			logger.error(e.getMessage());
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
			logger.error(e.getMessage());
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
