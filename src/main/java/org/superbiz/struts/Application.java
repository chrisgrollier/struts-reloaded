package org.superbiz.struts;

import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;

@SpringBootApplication
@RefreshScope
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	@Order(1)
	FilterRegistrationBean<?> siteMeshFilter() {
		FilterRegistrationBean<SiteMeshFilter> bean = new FilterRegistrationBean<>();
		bean.addUrlPatterns("/*");
		bean.setFilter(new SiteMeshFilter());
		return bean;
	}

	@Bean
	@Order(2)
	FilterRegistrationBean<?> strutsPrepareAndExecuteFilter() {
		FilterRegistrationBean<StrutsPrepareAndExecuteFilter> bean = new FilterRegistrationBean<>();
		bean.addUrlPatterns("/", "/addUserForm.action", "/addUser.action", "/findUserForm.action", "/findUser.action",
				"/listAllUsers.action");
		bean.setFilter(new StrutsPrepareAndExecuteFilter());
		bean.addInitParameter("actionPackages", "com.lq");
		return bean;
	}
}
