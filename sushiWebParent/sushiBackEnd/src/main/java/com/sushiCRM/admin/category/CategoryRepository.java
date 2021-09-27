package com.sushiCRM.admin.category;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sushiCRM.common.entity.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {
	
	

}
