package com.sushiCRM.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "short_description")
public class MenuItemDetail {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		@Column(nullable = false, length = 260)
		private String name;
		
		@Column(nullable = false, length = 260)
		private String value;	
		
		@ManyToOne
		@JoinColumn(name = "menu_id")
		private Menu menu;
		
		public MenuItemDetail() {
			
		}
		
		public MenuItemDetail(String name, String value, Menu menu) {
			
			this.name = name;
			this.value = value;
			this.menu = menu;
			
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public Menu getMenu() {
			return menu;
		}

		public void setMenu(Menu menu) {
			this.menu = menu;
		}

}
	