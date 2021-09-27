package com.sushiCRM.admin.menu;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushiCRM.common.entity.Menu;

@Service
public class MenuService {
	
	@Autowired private MenuRepository repo;
	
	public List<Menu> listAll(){
		
		return (List<Menu>) repo.findAll();
		
	}
	
	public Menu save(Menu menu) {
		
		if (menu.getId() == null) {
			
			menu.setCreatedTime(new Date());
			
		}
		
		menu.setUpdatedTime(new Date());
		
		return repo.save(menu);
		
	}

}
