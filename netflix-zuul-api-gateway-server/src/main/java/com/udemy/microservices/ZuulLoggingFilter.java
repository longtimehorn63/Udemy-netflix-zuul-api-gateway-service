package com.udemy.microservices;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public Object run() {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		logger.info("request -> {} request URI -> {}", request, request.getRequestURI());
		return null;
	}

	@Override
	/*
	 * can have business logic to look at the request to determine 
	 * if the filter should be executed
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	/*
	 * pre before request is executed
	 * post after the request is executed
	 * error only error requests where exceptions have occurred
	 */
	public String filterType() {
		return "pre";
	}

}
