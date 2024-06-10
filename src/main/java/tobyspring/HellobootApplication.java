package tobyspring;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		ServletWebServerFactory ServerFactory = new TomcatServletWebServerFactory();
		WebServer webServer = ServerFactory.getWebServer(servletContext -> {
			HelloController helloController = new HelloController();

			// 서블릿을 추가하고
			servletContext.addServlet("frontController", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// 인증, 보안, 다국어, 공통 기능들을  처리한다.
					if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())){
						String name = req.getParameter("name");

						// 복잡한 로직은 컨트롤러로 분리되었다.
						String ret = helloController.hello(name);

						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println(ret);
					}
					else if(req.getRequestURI().equals("/user")){
						//
					}
					else {
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}
				}
				// 서블릿 컨테이너가 들어온 요청을 서블릿에게 연결하는 매핑 작업 해야 한다.
			}).addMapping("/*");
		});
		webServer.start();
	}

}