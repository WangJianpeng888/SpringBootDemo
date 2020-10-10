package com.wjp.demo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjp.demo.common.SearchContent;
import com.wjp.demo.dao.BrandRepository;
import com.wjp.demo.entity.Brand;
import com.wjp.demo.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{
	@Autowired
    private BrandRepository brandRepository;

	@Override
	public void addBrand(Brand brand) {
		brandRepository.save(brand);
	}

	@Override
	public List<Brand> findByBCode(String code) {
		return brandRepository.findByBCode(code);
	}

	@Override
	public List<Brand> findAll() {
		return brandRepository.findAll();
	}

	@Override
	public void setStateUseByBId(int id) {
		brandRepository.setStateUseByBId(id);
	}

	@Override
	public void setStateNotUseByBId(int id) {
		brandRepository.setStateNotUseByBId(id);
	}

	@Override
	public List<Brand> findBySearchCon(SearchContent con) {
		String nameLike;
		if(con.getNameLike()!=null) {
			nameLike=con.getNameLike();//名称查询 like
			nameLike="%"+nameLike+"%";
		}else {
			nameLike="%%";
		}
		int pageSize=con.getPageSize();//每页多少条数据
		int pageNow=con.getPageNow();//当前页数
		List<Brand>  list ;
		if(con.getStateCheck()==null) {
			System.out.println(nameLike+"|"+pageSize*(pageNow-1)+"|"+pageSize);
			list=brandRepository.findBySearchCon(nameLike,pageSize*(pageNow-1),pageSize);
//			if(list.size()==0 && pageNow>1) {
//				pageNow--;
//				list=brandRepository.findBySearchCon(nameLike,pageSize*(pageNow-1),pageSize);
//			}
			return list;
		}else {
			int stateCheck=con.getStateCheck();//状态查询
			System.out.println(nameLike+"|"+"|"+stateCheck+"|"+pageSize*(pageNow-1)+"|"+pageSize);
			list=brandRepository.findBySearchCon(nameLike,stateCheck,pageSize*(pageNow-1),pageSize);
//			if(list.size()==0 && pageNow>1) {
//				pageNow--;
//				list=brandRepository.findBySearchCon(nameLike,stateCheck,pageSize*(pageNow-1),pageSize);
//			}
			return list;
		}
		
		
	}

	@Override
	public int countBySearchCon(SearchContent con) {
		String nameLike;
		if(con.getNameLike()!=null) {
			nameLike=con.getNameLike();//名称查询 like
			nameLike="%"+nameLike+"%";
		}else {
			nameLike="%%";
		}
		int count=0;
		if(con.getStateCheck()==null) {
			count=brandRepository.getCountBySearchCon(nameLike);
		}else {
			int stateCheck=con.getStateCheck();//状态查询
			System.out.println("禁用:"+nameLike+"|"+stateCheck);
			count=brandRepository.getCountBySearchCon(nameLike,stateCheck);
		}
		return count;
		
	}

	@Override
	public void deleteByBId(int id) {
		brandRepository.delete(id);
	}

	@Override
	public void updateBrand(Brand brand) {
		brandRepository.save(brand);
	}

	@Override
	public List<Brand> findByBState(int i) {
		return brandRepository.findByBStateOrderByBRange(i);
	}
	

}
