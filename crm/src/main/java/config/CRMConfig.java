package config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import is296.crm.controller.HomePageController;

@SpringBootApplication
@ComponentScan("is296.crm.dao")
public class CRMConfig {
	@Bean
	public DataSource dataSrc() throws SQLException {
		DataSource ds = new EmbeddedDatabaseBuilder().addScript("classpath:potentialLeads.sql").build();
		ds.getConnection().setAutoCommit(true);
		return ds;
	}
	
	@Bean
	public JdbcTemplate getJdbcTemplate() throws SQLException {
		return new JdbcTemplate(dataSrc());
	}
	@Bean
	public HomePageController getHPController() throws SQLException  {
		return new HomePageController(getJdbcTemplate());
	}
}
