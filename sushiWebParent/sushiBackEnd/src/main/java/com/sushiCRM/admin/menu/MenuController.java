package com.sushiCRM.admin.menu;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sushiCRM.common.entity.Menu;

@Controller
public class MenuController {
	
	@Autowired private MenuService menuService;
	
	@GetMapping("/menu")
	public String listAll(Model model) {
		
		List<Menu> listMenu = menuService.listAll();
		model.addAttribute("listMenu", listMenu);
		
		return "menu/menu";		
	}
	
	@GetMapping("/menu/new")
	public String newMenu(Model model) {
		
		Menu menu = new Menu();
		menu.setEnabled(true);
		menu.setInStock(true);
		model.addAttribute("menu", menu);
		model.addAttribute("menuTitle", "Create New Menu Item");
		
		return "menu/menu_form";
		
	}
	
	@PostMapping("/menu/save")
	public String saveMenu(Menu menu, RedirectAttributes ra) {
		
		menuService.save(menu);
		ra.addFlashAttribute("message", "The Menu Item has been saved");
		
		/* System.out.println("Menu Item Name: " + menu.getName()); */
		
		return "redirect:/menu";
		
	}
	
	 @GetMapping("/menu/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		
		List<Menu> listMenu = menuService.listAll();
		MenuCsvExporter exporter = new MenuCsvExporter();
		exporter.export(listMenu, response);
		
	} 

}
