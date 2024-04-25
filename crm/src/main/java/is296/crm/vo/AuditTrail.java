package is296.crm.vo;

import java.sql.Timestamp;

public class AuditTrail {
	private Timestamp date;
	private String user;
	private String action;
	private String section;
	
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	
	@Override
	public String toString() {
		return "AuditTrail [date=" + date + ", user=" + user + ", action=" + action + ", section=" + section + "]";
	}
}
