package com.wjp.demo.common;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class ResJsonMap {
	public String  resCode;
	public String resMsg;
	
	public ResJsonMap(String  resCode,String resMsg) {
		this.resCode=resCode;
		this.resMsg=resMsg;
	}
	
	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public String resJsonMap(){
		Map<String, String> map = new HashMap();
		map.put("resCode", this.resCode);
		map.put("resMsg", this.resMsg);
		return JSON.toJSONString(map);
	}

}
