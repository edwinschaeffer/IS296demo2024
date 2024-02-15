package config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@Configuration
public class DemoConfig {
	
	@Bean
	public DataSource dataSrc(String getString) throws SQLException {
		System.out.println("dataSrc bean instantiated");
		DataSource ds = new EmbeddedDatabaseBuilder().addScript("classpath:potentialLeads.sql").build();
		ds.getConnection().setAutoCommit(true);
		return ds;
	}
	
	@Bean
	public JdbcTemplate getJdbcTemplate(DataSource ds) throws SQLException {
		System.out.println("getJdbcTemplate bean instantiated");
		return new JdbcTemplate(ds);
	}
	
	@Bean
	public String getString() {
		System.out.println("getString bean instantiated");		
		return "Hi there";
	}
	@Bean
	public String getStrineee2() {
		System.out.println("getString2 bean instantiated");		
		return "Hi there2";		
	}

}
