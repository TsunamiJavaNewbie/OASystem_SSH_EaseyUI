package com.tsunami.oa.framework.util;

import org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor;

public class ExceptionLogimplements extends ThrowsAdviceInterceptor {

	public ExceptionLogimplements(Object throwsAdvice) {
		super(throwsAdvice);
	}
	
	

}
