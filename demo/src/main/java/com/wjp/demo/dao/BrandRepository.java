package com.wjp.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.wjp.demo.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand,Integer>{

	List<Brand> findByBCode(String code);
	
	@Transactional
	@Modifying
	@Query(value="update brand set b_state='1' where b_id = ?1", nativeQuery=true)
	void setStateUseByBId(int id);
	
	@Transactional
	@Modifying
	@Query(value="update brand set b_state='0' where b_id = ?1", nativeQuery=true)
	void setStateNotUseByBId(int id);

	@Query(value="select * from brand where b_name like ?1 and b_state =?2  limit ?3,?4", nativeQuery=true)
	List<Brand> findBySearchCon(String nameLike,Integer stateCheck,Integer countSize, Integer pageNow);
	
	@Query(value="select * from brand where b_name like ?1 limit ?2,?3", nativeQuery=true)
	List<Brand> findBySearchCon(String nameLike, int i, int pageSize);

	@Query(value="select count(*) from brand where b_name like ?1", nativeQuery=true)
	int getCountBySearchCon(String nameLike);

	@Query(value="select count(*) from brand where b_name like ?1 and b_state =?2 ", nativeQuery=true)
	int getCountBySearchCon(String nameLike, int stateCheck);

	List<Brand> findByBStateOrderByBRange(int i);

}
