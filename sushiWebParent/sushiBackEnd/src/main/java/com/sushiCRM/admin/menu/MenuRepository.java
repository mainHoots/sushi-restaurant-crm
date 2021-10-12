package com.sushiCRM.admin.menu;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sushiCRM.common.entity.Menu;

public interface MenuRepository extends PagingAndSortingRepository<Menu, Integer> {

	public Menu findByName(String name);
	
	@Query("UPDATE Menu p SET p.enabled = ?2 WHERE p.id = ?1")
	@Modifying
	public void updateEnabledStatus(Integer id, boolean enabled);
	
	public Long countById(Integer id);

}
