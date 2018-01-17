package SpringWebAppInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringWebAppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {
		// TODO Auto-generated method stub
		 XmlWebApplicationContext appContext = new XmlWebApplicationContext();
	        appContext.setConfigLocation("/WEB-INF/spring-dispatcher-servlet.xml");

	        ServletRegistration.Dynamic dispatcher = container.addServlet(
	                "spring-dispatcher", new DispatcherServlet(appContext));
	        dispatcher.setLoadOnStartup(1);
	        dispatcher.addMapping("/");

	}    

}
