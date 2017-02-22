/**
 * 
 */
package com.cox.bis.customer.preference.route;

/**
 * @author mkrishna
 *
 */
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.cox.bis.customer.preference.api.model.PreferencesSearchRequest;
import com.cox.bis.customer.preference.api.model.PreferencesUpdateRequest;
import com.cox.bis.customer.preference.util.LocalConstants;


@SpringBootApplication
@ComponentScan("com.cox.bis")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class CustomerPreferenceRB extends SpringBootServletInitializer implements LocalConstants {

	public static void main(String[] args) {
		SpringApplication.run(CustomerPreferenceRB.class, args);
	}

	@Bean
	ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean servlet = new ServletRegistrationBean(
				new CamelHttpTransportServlet(), "/customer/account/v1/*");
		servlet.setName("CamelServlet");
		return servlet;
	}

	@Component
	class CustomerCommentsRoute extends RouteBuilder {

		@Override
		public void configure() throws Exception {


			restConfiguration()
			.contextPath("/customer/account/v1").apiContextPath("/api-doc")
			.apiProperty("api.title", "Camel REST API")
			.apiProperty("api.version", "1.0")
			.apiProperty("cors", "true")
			.apiContextRouteId("doc-api")
			.bindingMode(RestBindingMode.json);

			rest("/preferences/search").description("preferences  REST service")
			.post("/").type(PreferencesSearchRequest.class).description("preferences search REST service")
			.route().routeId("preferences-search-api")
			.to("direct:preferencesSearch-route");

			rest("/preferences/update").description("Preference update REST service")
			.post("/").type(PreferencesUpdateRequest.class).description("preference update")
			.route().routeId("preference-update-api")
			.to("direct:preferencesUpdate-route");

			from("direct:preferencesSearch-route")
			.routeId("preferencesSearch-route").description("preferencesSearch-route")
			.to("bean:preferencesSearchProcessor?method=search");

			// Operation: preferencesUpdate
			from("direct:preferencesUpdate-route")
			.routeId("preferencesUpdate-route").description("preferencesUpdate-route")
			.to("bean:preferencesUpdateProcessor?method=validate")
			.to("bean:preferencesUpdateProcessor?method=init")					
			.to("bean:preferencesUpdateProcessor?method=update");
		}
	}
}