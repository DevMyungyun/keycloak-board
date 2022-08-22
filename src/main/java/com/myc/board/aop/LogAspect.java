package com.myc.board.aop;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LogAspect {
    
    Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(* com.myc.board..*(..))")
	private void doExecute() {}

	
	@Around("doExecute()")
	public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable{
		log.info("In dologging");
		
		

		String methodName = joinPoint.getSignature().toShortString();
		try {
			log.info(methodName+" is start");
			Object obj = joinPoint.proceed();
			return obj;
		}finally {
			log.info(methodName + " is Finish");
		}
	}
    
}
