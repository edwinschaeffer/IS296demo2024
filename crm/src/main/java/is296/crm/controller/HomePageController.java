package is296.crm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import is296.crm.dao.JDBCDao;

@Controller
public class HomePageController {
	private JdbcTemplate jdbcT;
	
	@Autowired
	private JDBCDao jDAO;
	
	public HomePageController(JdbcTemplate jdbcT) {
		this.jdbcT = jdbcT;
	}
	
	

	@RequestMapping("/")
	public String getHomePage(Model m) {
		m.addAttribute("leads", jDAO.getFullPotentialLeadsTableHardWay());
		return "home";
	}
	
	@GetMapping("/home") 
	public String getHomePageBeanPropertyRowMapper(Model m) {
		m.addAttribute("leads", jDAO.getFullPotentialLeadsTableEZWay());
		return "home";
	}
}
