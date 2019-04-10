package com.beyondsoft.itsm.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.holders.StringHolder;

import com.ca.www.UnicenterServicePlus.ServiceDesk.holders.CreateObjectResponseCreateObjectResultHolder;

public class CaObject {
	private String persid;
	private String objectType;
	private Map<String, String> attrVals;
	private Map<String, String> attributes;
	private CreateObjectResponseCreateObjectResultHolder createObjectResult = new CreateObjectResponseCreateObjectResultHolder();
	private StringHolder newHandle = new StringHolder();

	public String[] arrayOfAttributes() {
		if (null != attributes && !attributes.isEmpty()) {
			List<String> attrs = new ArrayList<String>();
			for (Map.Entry<String, String> entry : attributes.entrySet()) {
				attrs.add(entry.getKey());
				attrs.add(entry.getValue());
			}

			String[] arrAttrs = new String[attrs.size()];
			return attrs.toArray(arrAttrs);
		}
		return new String[0];
	}

	public String getPersid() {
		return persid;
	}

	public void setPersid(String persid) {
		this.persid = persid;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Map<String, String> getAttrVals() {
		return attrVals;
	}

	public void setAttrVals(Map<String, String> attrVals) {
		this.attrVals = attrVals;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public CreateObjectResponseCreateObjectResultHolder getCreateObjectResult() {
		return createObjectResult;
	}

	public void setCreateObjectResult(CreateObjectResponseCreateObjectResultHolder createObjectResult) {
		this.createObjectResult = createObjectResult;
	}

	public StringHolder getNewHandle() {
		return newHandle;
	}

	public void setNewHandle(StringHolder newHandle) {
		this.newHandle = newHandle;
	}
}
