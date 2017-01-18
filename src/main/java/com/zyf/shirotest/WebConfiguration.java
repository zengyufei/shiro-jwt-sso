package com.zyf.shirotest;

import com.baomidou.kisso.web.KissoConfigListener;
import com.baomidou.kisso.web.filter.SSOFilter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebConfiguration.class);
	}

}