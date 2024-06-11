package tobyspring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class HellobootApplication {
	@Bean
	public HelloController helloController(HelloService helloService){
		return new HelloController(helloService);
	}
	@Bean
	public HelloService helloService(){
		return new SimpleHelloService();
	}

	public static void main(String[] args) {
		// 자바 코드를 이용한 구성 정보를 사용하려 AnnotationConfigWebApplicationContext 클래스로 컨테이너를 만들어야 한다.
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
			@Override
			protected void onRefresh() {
				super.onRefresh();

				ServletWebServerFactory ServerFactory = new TomcatServletWebServerFactory();
				WebServer webServer = ServerFactory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet",
							new DispatcherServlet(this)
					).addMapping("/*");
				});
				webServer.start();
			}
		};
		applicationContext.register(HellobootApplication.class);
		applicationContext.refresh();
	}
}
