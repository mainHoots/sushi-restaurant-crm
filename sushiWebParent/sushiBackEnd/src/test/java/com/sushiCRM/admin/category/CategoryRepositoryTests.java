package com.sushiCRM.admin.category;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.sushiCRM.common.entity.Category;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTests {
	
	@Autowired
	private CategoryRepository repo;
	
	@Test
	public void testCreateRootCategory() {
		
		//Testing and adding category table to database
		//Category category = new Category("Kitchen Items");
		
		Category category = new Category("Sushi Items");
		
		Category savedCategory = repo.save(category);
		
		assertThat(savedCategory.getId()).isGreaterThan(0);
		
	}
	
	@Test
	public void testCreateSubCategory() {
		
		Category parent = new Category(1);
		
		//Category parent = new Category(2);
		
		//Created subcategory with sushi category parent
		//Category subCategory = new Category("Oshizushi", parent);
		
		//Category sasazushi = new Category("Sasazushi", parent);
		
		//Category makizushi = new Category("Makizushi", parent);
		
		Category chickenStirFry = new Category("Chicken Stir Fry", parent);
		
		Category potStickers = new Category("Pot Stickers", parent);
		
		//Category savedCategory = repo.save(subCategory);
		
		//assertThat(savedCategory.getId()).isGreaterThan(0);
		
		repo.saveAll(List.of(chickenStirFry, potStickers));
		
	}
	
	@Test
	public void testGetCategory() {
		
		//switch findById to 1 or 2 to print children of a particular category
		Category category = repo.findById(1).get();
		System.out.println(category.getName());
		
		Set<Category> children = category.getChildren();
		
		for (Category subCategory : children) {
			
			System.out.println(subCategory.getName());
			
		}
		
		assertThat(children.size()).isGreaterThan(0);
		
	}
	
	@Test
	public void testPrintHierarchicalCategories() {
		
		Iterable<Category> categories = repo.findAll();
		
		for (Category category : categories) {
			
			if (category.getParent() == null) {
				
				System.out.println(category.getName());
				
				Set<Category> children = category.getChildren();
				
				for (Category subCategory : children) {
					
					System.out.println("--" + subCategory.getName());
					
				}
				
			}
			
		}
		
	}
	
}
