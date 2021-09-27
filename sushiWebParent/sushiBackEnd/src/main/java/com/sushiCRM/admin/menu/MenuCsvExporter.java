package com.sushiCRM.admin.menu;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.sushiCRM.common.entity.Menu;

public class MenuCsvExporter {
	
	public void export(List<Menu> listMenu, HttpServletResponse response) throws IOException {
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String timestamp = dateFormatter.format(new Date());
		String fileName = "menu_" + timestamp + ".csv";
		
		response.setContentType("text/csv");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + fileName;
		response.setHeader(headerKey, headerValue);
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"ID", "Name", "Price", "Description", "Created Time", "Updated Time", "Enabled", "In Stock"};
		
		String[] fieldMapping = {"id", "name", "price", "shortDescription", "createdTime", "updatedTime", "enabled", "inStock"};
		
		csvWriter.writeHeader(csvHeader);
		
		for (Menu menu : listMenu) {
			
			csvWriter.write(menu,  fieldMapping);
			
		}
		
		csvWriter.close();
		
	}

}
