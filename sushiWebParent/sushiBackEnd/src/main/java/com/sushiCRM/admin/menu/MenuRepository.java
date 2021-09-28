package com.sushiCRM.admin.menu;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sushiCRM.common.entity.Menu;

public interface MenuRepository extends PagingAndSortingRepository<Menu, Integer> {

	public Menu findByName(String name);
	
	public Long countById(Integer id);

}
