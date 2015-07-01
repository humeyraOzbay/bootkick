package com.cebi.bootkick;

import com.cebi.bootkick.ws.HelloWsImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:META-INF/cxf/cxf.xml"})
public class WsConfiguration {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private HelloWsImpl helloWsImpl;

    // Replaces the need for web.xml
//     <servlet>
//        <servlet-name>CXFServlet</servlet-name>
//        <servlet-class>org.apache.cxf.transport.servlet.CXFServlet</servlet-class>
//        <load-on-startup>1</load-on-startup>
//    </servlet>
//    <servlet-mapping>
//        <servlet-name>CXFServlet</servlet-name>
//        <url-pattern>/services/*</url-pattern>
//    </servlet-mapping>
    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext context) {
        return new ServletRegistrationBean(new CXFServlet(), "/api/*");
    }

    // Replaces 
    // <jaxws:endpoint id="helloWorld" implementor="demo.spring.service.HelloWorldImpl" address="/HelloWorld"/>
    @Bean
    public EndpointImpl helloService() {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        EndpointImpl endpoint = new EndpointImpl(bus, helloWsImpl);
        endpoint.publish("/hello");
        endpoint.getServer().getEndpoint().getInInterceptors().add(new LoggingInInterceptor());
        endpoint.getServer().getEndpoint().getOutInterceptors().add(new LoggingOutInterceptor());
        return endpoint;
    }
}
