package com.beyondsoft.itsm.modules.user.webservice;

import org.springframework.util.Assert;

import com.beyondsoft.itsm.core.webservice.AbstractCxfWebService;
import com.beyondsoft.itsm.modules.user.domain.User;
import com.beyondsoft.itsm.modules.user.service.IUserService;

public class UserWebServiceClient extends AbstractCxfWebService<IUserService> {
	private IUserService iUserService;

	private static UserWebServiceClient userWebServiceClient;

	private UserWebServiceClient(String url) {
		super(url);
		this.iUserService = super.createObject();
	}

	public static UserWebServiceClient getInstance(String url) {
		Assert.notNull(url, "webService url地址为空");
		if (null == userWebServiceClient) {
			synchronized (UserWebServiceClient.class) {
				if (null == userWebServiceClient) {
					userWebServiceClient = new UserWebServiceClient(url);
				}
			}
		}
		return userWebServiceClient;
	}

	public User getUser(Integer userId) {
		return iUserService.findById(userId);
	}

	public static void main(String[] args) {
		String url = "http://127.0.0.1:9001/service/user?wsdl";
		User user = UserWebServiceClient.getInstance(url).getUser(1);
		System.out.println(user);
		User user1 = UserWebServiceClient.getInstance(url).getUser(1);
		System.out.println(user1);
	}

}
