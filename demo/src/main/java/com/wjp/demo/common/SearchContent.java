package com.wjp.demo.common;

public class SearchContent {
private Integer pageNow;//当前为第几页
private Integer pageSize;//每页多少条
private String nameLike;//搜索名称
private Integer stateCheck;//当前选择的状态
public Integer getPageNow() {
	return pageNow;
}
public void setPageNow(Integer pageNow) {
	this.pageNow = pageNow;
}
public Integer getPageSize() {
	return pageSize;
}
public void setPageSize(Integer pageSize) {
	this.pageSize = pageSize;
}
public String getNameLike() {
	return nameLike;
}
public void setNameLike(String nameLike) {
	this.nameLike = nameLike;
}
public Integer getStateCheck() {
	return stateCheck;
}
public void setStateCheck(Integer stateCheck) {
	this.stateCheck = stateCheck;
}

}
