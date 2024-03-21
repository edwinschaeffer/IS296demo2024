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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import is296.crm.dao.JDBCDao;
import is296.crm.vo.PotentialLead;

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
		PotentialLead pl = new PotentialLead();
		pl.setArea("myhouse");
		int x = 0;
		jDAO.setAValue(pl, x);
		System.out.println(pl.getArea());
		System.out.println(x);
		m.addAttribute("leads", jDAO.getFullPotentialLeadsTableEZWay());
		return "home";
	}
	
	@RequestMapping("/homeMyBatisAll")
	public String getHomePageMyBatis(Model m) {
		m.addAttribute("leads", jDAO.getListofAllPLsMyBatis());
		return "home";
	}
	@GetMapping("/transformLocationJPA")
	public String getHomePageTransformLocation(Model m) {
		m.addAttribute("leads", jDAO.getAllPLsJPATransform());
		return "home";
	}
	
	@GetMapping("/getMaxEmployees") 
	public String getHomePageMaxEmployees(Model m) {
		m.addAttribute("leads", jDAO.getMaxEmployeesJPA());
		return "home";
	}
	
	@RequestMapping("/homeJPAAll")
	public String getHomePageJPA(Model m) {
		m.addAttribute("leads", jDAO.getAllPLsJPA());
		return "home";
	}
	@RequestMapping("/homeMyB/{plId}")
	public String getLeadByIdMyBatis(@PathVariable(value="plId") String id, Model model) {
		model.addAttribute("lead", jDAO.getLeadByIdMyBatis(id));
		return "submitLeadJquery";
	}
	
	@GetMapping("/homeJPAById")
	public String getPLByIdJPA(@RequestParam String plId, Model model) {
		PotentialLead pl = jDAO.getPLByIdJPA(plId);
		List<PotentialLead> plList = new ArrayList<PotentialLead>();
		plList.add(pl);
		model.addAttribute("leads", plList);
		return "home";
	}
	
	@RequestMapping("/submitLead")
	public String getLeadByIdMyBatis() {
		return "submitLeadJquery";
	}
	// POST HTTP methods send data as a RequestBody in JSON like: { "pl" : "PL-12345" }
	// notice the annotation @RequestBody
	@PostMapping("/findLead") 
	public @ResponseBody PotentialLead getLeadByIdMyBatisAjax(@RequestBody String pl, Model model) {
		return jDAO.getLeadByIdMyBatis(pl);
	}
	
	// GET HTTP methods send data as query string like: http://localhost:8080/findJPAById?plId=PL-12345&city=SaintLouis
	// notice the different annotation @RequestParam - notice the variable name matches the URL Request Parameter
	@GetMapping("/findJPAById")
	public @ResponseBody PotentialLead getPLByIdJPASubmitLeadPage(@RequestParam String plId, Model model) {
		return jDAO.getPLByIdJPA(plId);
	}	
	
	@GetMapping("/homeJPAByCity/{city}")
	public String getPLFindByCityJPA(@PathVariable String city, Model model) {
		model.addAttribute("leads", jDAO.getPLByCityJPA(city));
		return "home";
	}
	
	@PostMapping("/updateLead") 
	public @ResponseBody String updateLead(@RequestBody PotentialLead pl) {
		jDAO.updatePotentialLead(pl);
		return "success";
	}
}
