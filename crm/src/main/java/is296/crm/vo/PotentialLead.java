package is296.crm.vo;

public class PotentialLead {
	private String company;
	private String id;
	private String city;
	private String employeeCount;
	private String area;
	
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
