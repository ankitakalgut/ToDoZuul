package com.bridgelabz.zuul.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bridgelabz.zuul.filter.SimpleFilter;

/***************************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 **************************************************************************************/
@Configuration
public class Config 
{
	@Bean
	public SimpleFilter preFilter()
	{
		return new  SimpleFilter();
	}
}
