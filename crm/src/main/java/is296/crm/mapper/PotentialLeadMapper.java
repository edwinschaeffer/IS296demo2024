package is296.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import is296.crm.vo.PotentialLead;

@Mapper
public interface PotentialLeadMapper {

	@Select("SELECT * FROM POTENTIAL_LEADS")
	@Results(id = "plResultMap", value = {
		@Result(property = "employeeCount", column = "EMPLOYEE_COUNT"),
		@Result(property = "ageOfBusiness", column = "AGE_OF_BUSINESS"),
		@Result(property = "zipCode", column = "ZIP_CODE")
	})
	public List<PotentialLead> getListofAllPLs();
	
	@Select("SELECT * FROM POTENTIAL_LEADS WHERE ID = #{id}")
	@ResultMap("plResultMap")
	public PotentialLead getLeadById(String id);
	
	// Add as many fields as you want...
	@Update("UPDATE POTENTIAL_LEADS "
			+ "SET CITY = #{pl.city}, "
			+ "COMPANY = #{pl.company}, "
			+ "STREET = #{pl.street} "
			+ "WHERE ID = #{pl.id}")
	public void updatePotentialLead(@Param("pl") PotentialLead pl);
	
}
