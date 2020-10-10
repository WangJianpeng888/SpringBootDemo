package com.wjp.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author 王建鹏
 * 	品牌类 Brand
 *  2020/9/17
 */
@Entity
@Table(name="Brand")
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bId;
	
	//品牌编码
	@Column
	private String bCode;
	
	//品牌名称
	@Column
	private String bName;
	
	//排列序号
	@Column
	private int bRange;
	
	//品牌状态
	@Column
	private int bState;

	//品牌图片（活跃）
	@Column
	private String bPicActive;
	
	//品牌图片（休眠）
	@Column
	private String bPicDormancy;
	
	//品牌故事
	@Column
	private String bStory;
	
	//操作时间
	@Column
	private String bFinalTime;

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getbCode() {
		return bCode;
	}

	public void setbCode(String bCode) {
		this.bCode = bCode;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public int getbRange() {
		return bRange;
	}

	public void setbRange(int bRange) {
		this.bRange = bRange;
	}

	public int getbState() {
		return bState;
	}

	public void setbState(int bState) {
		this.bState = bState;
	}

	public String getbPicActive() {
		return bPicActive;
	}

	public void setbPicActive(String bPicActive) {
		this.bPicActive = bPicActive;
	}

	public String getbPicDormancy() {
		return bPicDormancy;
	}

	public void setbPicDormancy(String bPicDormancy) {
		this.bPicDormancy = bPicDormancy;
	}

	public String getbStory() {
		return bStory;
	}

	public void setbStory(String bStory) {
		this.bStory = bStory;
	}

	public String getbFinalTime() {
		return bFinalTime;
	}

	public void setbFinalTime(String bFinalTime) {
		this.bFinalTime = bFinalTime;
	}

	
	
}
