package com.sushiCRM.admin.menu;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.sushiCRM.common.entity.Menu;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class MenuRepositoryTests {

	@Autowired
	private MenuRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateMenu() {
		
		Menu menu = new Menu();
		menu.setName("California Roll");
		menu.setShortDescription("Cannot go wrong with cucumbers, avocado and kanikama");
		menu.setPrice(8);
		menu.setCreatedTime(new Date());
		menu.setUpdatedTime(new Date());
		menu.setEnabled(true);
		menu.setInStock(true);
		
		Menu savedMenu = repo.save(menu);
		
		assertThat(savedMenu).isNotNull();
		assertThat(savedMenu.getId()).isGreaterThan(0);
		
	}
	
	@Test
	public void testListAllMenu() {
		
		Iterable<Menu> iterableMenu = repo.findAll();
		
		iterableMenu.forEach(System.out::println);
		
	}
	
	@Test
	public void testGetMenu() {
		
		Integer id = 4;
		Menu menu = repo.findById(id).get();
		System.out.println(menu);
		
		assertThat(menu).isNotNull();
		
	}
	
	@Test
	public void testUpdateMenu() {
		
		Integer id = 4;
		Menu menu = repo.findById(id).get();
		menu.setPrice(10);
		
		repo.save(menu);
		
		Menu updatedMenu = entityManager.find(Menu.class, id);
		
		assertThat(updatedMenu.getPrice()).isEqualTo(10);
		
	}
	
	@Test
	public void testDeleteMenu() {
		
		Integer id = 1;
		repo.deleteById(id);
		
		Optional<Menu> result = repo.findById(id);
		
		assertThat(!result.isPresent());
		
	}
	
	@Test
	public void testSaveMenuItemDetail() {
		
		Integer menuId = 3;
		
		Menu menu = repo.findById(menuId).get();
		
		menu.addDetail("Red Dragon Roll", "Asparagus, Avocado, Kanikama, Spicy Tuna Topping with Tempura Crunch");
		menu.addDetail("Spicy", "Yes");
		menu.addDetail("Raw Fish Present", "No");
		
		Menu savedMenu = repo.save(menu);
		assertThat(savedMenu.getDetails()).isNotEmpty();
		
	}
	
}
