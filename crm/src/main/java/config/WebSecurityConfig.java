package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(/* jsr250Enabled = true */)
public class WebSecurityConfig {

	@Value("${ldap.urls}")
	private String ldapUrls;
	
	@Value("${ldap.base.dn}")
	private String ldapBaseDn;
	
	@Value("${ldap.username}")
	private String ldapSecurityPrincipal;
	
	@Value("${ldap.password}")
	private String ldapUrlsPrincipalPassword;
	
	@Value("${ldap.user.dn.pattern}")
	private String ldapUserDnPattern;
	
	@Value("${ldap.enabled}")
	private String ldapEnabled;
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// the ldapEnabled option is just for use with the sample external 
		// ldap server
		// The else clause uses internal Spring LDAP 
		
		if(Boolean.parseBoolean(ldapEnabled)) {
			auth
			.ldapAuthentication()
			.contextSource()
			.url(ldapUrls + ldapBaseDn)
			.managerDn(ldapSecurityPrincipal)
			.managerPassword(ldapUrlsPrincipalPassword)
			.and()
			.userDnPatterns(ldapUserDnPattern);
		} else {
			auth
			.inMemoryAuthentication()
			.withUser("user")
			.password("{noop}password")
			.roles("USER")
			.and()
			.withUser("admin")
			.password("{noop}admin")
			.roles("ADMIN");
		}
	}
}
