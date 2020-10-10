package com.wjp.demo.service;

import java.util.List;

import com.wjp.demo.common.SearchContent;
import com.wjp.demo.entity.Brand;

public interface BrandService {

	void addBrand(Brand brand);

	List<Brand> findByBCode(String code);

	List<Brand> findAll();

	void setStateUseByBId(int id);
	
	void setStateNotUseByBId(int id);

	List<Brand> findBySearchCon(SearchContent con);

	int countBySearchCon(SearchContent con);

	void deleteByBId(int id);

	void updateBrand(Brand brand);

	List<Brand> findByBState(int i);
}
