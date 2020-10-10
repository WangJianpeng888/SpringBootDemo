package com.wjp.demo.common;

import java.io.Serializable;

public class FileResult implements Serializable {
    //判断结果
    private boolean success;
    //返回信息
    private String message;
    //文件地址
    private String fileAddress;

    private String start;

    public FileResult(boolean success, String message,String fileAddress) {
        this.success = success;
        this.message = message;
        this.fileAddress = fileAddress;
    }

    public boolean isSuccess() {
        return success;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFileAddress() {
		return fileAddress;
	}

	public void setFileAddress(String fileAddress) {
		this.fileAddress = fileAddress;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
    
}
