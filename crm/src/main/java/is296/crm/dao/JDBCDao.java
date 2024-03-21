package is296.crm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import is296.crm.mapper.PotentialLeadMapper;
import is296.crm.repository.PotentialLeadRepository;
import is296.crm.vo.PotentialLead;

@Service
public class JDBCDao {
	
	@Autowired
	private JdbcTemplate j;
	
	@Autowired
	private PotentialLeadMapper plm;
	
	@Autowired
	private PotentialLeadRepository plRepo;
	
	
	public List<PotentialLead> getAllPLsJPA() {
		return plRepo.findAll();
	}
	
	public List<PotentialLead> getAllPLsJPATransform() {
		List<PotentialLead> plListAll = plRepo.findAll();
		for(PotentialLead pl : plListAll) {
			switch(pl.getArea()) {
				case "MID-AMERICA": 
					pl.setArea("MA");
				break;
				case "CENTRAL ILLINOIS": 
					pl.setArea("CI");
				break;
			}
		}
		PotentialLead plTestAddRecord = new PotentialLead();
		plTestAddRecord.setId("ED-TEST");
		plTestAddRecord.setArea("ILLINOIS");
		plTestAddRecord.setCity("EDWARDSVILLE");
		plListAll.add(plTestAddRecord);
		plRepo.saveAll(plListAll);
		return plRepo.findAll();
	}

	public List<PotentialLead> getMaxEmployeesJPA() {
		return plRepo.getMaxEmployees();
	}
	public PotentialLead getPLByIdJPA(String plId) {
		return plRepo.findByid(plId);
	}
	
	public List<PotentialLead> getPLByCityJPA(String city) {
		return plRepo.findByCity(city);
	}
	
	public PotentialLead getLeadByIdMyBatis(String id) {
		return plm.getLeadById(id);
	}
	
	public List<PotentialLead> getListofAllPLsMyBatis() {
		List<PotentialLead> plList = plm.getListofAllPLs();
		// Simple ETL (Extract, Transform, or Load) but it's an example
		for(PotentialLead pl : plList) {
			pl.setCity(pl.getCity().toUpperCase());
		}
		return plList;
	}
	
	// Pass by reference/value example
	public void setAValue(PotentialLead pl, int x) {
		pl.setArea("stlcc");
		x = 5;
	}
	
	public List<PotentialLead> getFullPotentialLeadsTableHardWay() {
		List<Map<String, Object>> rows = j.queryForList("SELECT * FROM POTENTIAL_LEADS");
		List<PotentialLead> plList = new ArrayList<PotentialLead>();
		
		for(Map<String, Object> row : rows) {
			PotentialLead pl = new PotentialLead();
			for(String key : row.keySet()) {
				switch(key) {
					case "COMPANY" : 
						pl.setCompany((String)row.get(key));
					break;
					case "ID" :
						pl.setId((String)row.get(key));
					break;
					case "CITY" :
						pl.setCity((String)row.get(key));
					break;
					case "EMPLOYEE_COUNT" :
						pl.setEmployeeCount((String)row.get(key));
					break;
					case "AREA" :
						pl.setArea((String)row.get(key));
					break;
				}
			}
			plList.add(pl);
		}
		return plList;
	}
	public List<PotentialLead> getFullPotentialLeadsTableEZWay() {
		String sql = "SELECT COMPANY as company, ID as id, CITY as city, AREA as area, "
				+ "EMPLOYEE_COUNT as employeeCount FROM POTENTIAL_LEADS";
		List<PotentialLead> plList = j.query(sql, new BeanPropertyRowMapper(PotentialLead.class));
		return plList;
	}
	
	public void updatePotentialLead(PotentialLead pl) {
		plm.updatePotentialLead(pl);
	}
}
