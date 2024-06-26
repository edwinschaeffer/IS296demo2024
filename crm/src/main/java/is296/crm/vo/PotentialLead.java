package is296.crm.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "POTENTIAL_LEADS")
public class PotentialLead {
	private String company;
	@Id
	@Column(nullable = false, name = "ID")
	private String id;
	private String city;
	@Column(nullable = true, name = "EMPLOYEE_COUNT")
	private String employeeCount;
	private String area;
	@Column(nullable = true, name = "AGE_OF_BUSINESS")
	private String ageOfBusiness;
	private String industry;
	// -- JsonProperty -- Returns with the key as "STREET"
	// @JsonProperty("STREET")
	private String street;
	private String state;
	@Column(nullable = true, name = "ZIP_CODE")
	private String zipCode;
	private String website;

	public String getAgeOfBusiness() {
		return ageOfBusiness;
	}
	public void setAgeOfBusiness(String ageOfBusiness) {
		this.ageOfBusiness = ageOfBusiness;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmployeeCount() {
		return employeeCount;
	}
	public void setEmployeeCount(String employeeCount) {
		this.employeeCount = employeeCount;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Override
	public String toString() {
		return "PotentialLead [company=" + company + ", id=" + id + ", city=" + city + ", employeeCount="
				+ employeeCount + ", area=" + area + "]";
	}
	
}
