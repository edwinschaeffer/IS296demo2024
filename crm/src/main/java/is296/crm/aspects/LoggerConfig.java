package is296.crm.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerConfig {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Before("execution(* getHomePage*(..))")
	public void logEveryGetHomePage(JoinPoint jp) {
		log.info("User is accessing a getHomePage method on " 
				+ jp.getTarget().getClass() + ". This advice is Executing Before the " 
				+ jp.getSignature().getName() + "() method");
	}
	@Before("execution(@org.springframework.web.bind.annotation.GetMapping * *.*(..) )")
	public void logAllGetMapping(JoinPoint jp) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(principal.toString());
		String userName;
		if(principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		log.info(userName + " accessing - " + jp.getTarget().getClass() + " ; Executing "
				+ jp.getSignature().getName() + "() method");
	}
	
}
