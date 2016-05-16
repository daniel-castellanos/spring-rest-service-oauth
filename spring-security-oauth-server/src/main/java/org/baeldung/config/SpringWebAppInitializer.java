package org.baeldung.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		appContext.register(ApplicationContextConfig.class);
				
		/*CustomRequestLoggingFilter filter = new CustomRequestLoggingFilter();
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(100000000);
		servletContext.addFilter("requestFilter", filter)
			.addMappingForUrlPatterns(null, false, "/*");*/
		
		//servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
		
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("SpringDispatcher",
				new DispatcherServlet(appContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

	}

}