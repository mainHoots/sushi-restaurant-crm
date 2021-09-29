package com.sushiCRM.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.sushiCRM.common.entity.Role;
import com.sushiCRM.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUserWithOneRole() {
		
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userBrianHoots = new User("def@sushi.com", "pizzaplz", "Brian", "Hoots");
		userBrianHoots.addRole(roleAdmin);
		
		User savedUser = repo.save(userBrianHoots);
		assertThat(savedUser.getId()).isGreaterThan(0);
		
	}
	
	@Test
	public void testCreateUserWithTwoRoles() {
		
		User userLotharBuho = new User("lobu@gmail.com", "lotharpassword", "Lothar", "Buho");
		Role roleSalesperson = new Role(2);
		Role roleAdmin = new Role(1);
		userLotharBuho.addRole(roleSalesperson);
		userLotharBuho.addRole(roleAdmin);
		
		User savedUser = repo.save(userLotharBuho);
		assertThat(savedUser.getId()).isGreaterThan(0);
		
	}
	
	@Test
	public void testListAllUsers() {
		
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
		
	}
	
	@Test
	public void testGetUserById() {
		
		User userName = repo.findById(1).get();
		System.out.println(userName);
		assertThat(userName).isNotNull();
		
	}
	
	@Test
	public void testUpdateUserDetails() {
		
		User userName = repo.findById(1).get();
		userName.setEnabled(true);
		userName.setEmail("SushiMeProgrammer@gmail.com");
		
		repo.save(userName);
		
	}
	
	@Test
	public void testUpdateUserRoles() {
		
		User userLotharBuho = repo.findById(2).get();
		Role roleAssistant = new Role(3);
		Role roleAdmin = new Role(1);
		
		userLotharBuho.getRoles().remove(roleAdmin);
		userLotharBuho.addRole(roleAssistant);
		
		repo.save(userLotharBuho);
		
	}
	
	@Test
	public void testDeleteUser() {
	
		Integer userId = 2;
		repo.deleteById(userId);
		
		repo.findById(userId);
		
	}
	
	@Test
	public void testGetUserByEmail() {
		
		/* String email = "fake@email.com"; */
		
		String email = "einstein@gmail.com";
		User user = repo.getUserByEmail(email);
		
		assertThat(user).isNotNull();
		
	}
	
	@Test
	public void testCountById() {
		
		/* Integer id = 100; */
		
		Integer id = 6;
		Long countById = repo.countById(id);
		
		assertThat(countById).isNotNull().isGreaterThan(0);
		
	}

}
