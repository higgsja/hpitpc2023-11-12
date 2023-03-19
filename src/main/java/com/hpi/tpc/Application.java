package com.hpi.tpc;

import com.vaadin.flow.component.page.*;
import com.vaadin.flow.server.*;
import com.vaadin.flow.shared.communication.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.*;
import org.springframework.boot.web.servlet.support.*;
import org.vaadin.artur.helpers.*;

/**
 * The entry point of the Spring Boot application.
 */
//@SpringBootApplication(scanBasePackages = "com.hpi", exclude = ErrorMvcAutoConfiguration.class)
@SpringBootApplication
@Viewport("width=device-width, initial-scale=1")
//@PageTitle("A cool vaadin app")
//@BodySize(height = "100vh", width = "100vw")
//@Meta(name = "author", content = "bunny")
//@Inline(wrapping = Wrapping.AUTOMATIC,
//        position = Position.APPEND,
//        target = TargetElement.BODY,
//        value = "custom.html")
@PWA(name = "Trading Performance Coach", shortName = "TPC")
@Push(value = PushMode.AUTOMATIC)
//@Theme(value = Lumo.class)
//public class Application
//    extends SpringBootServletInitializer
//    implements AppShellConfigurator
//{
//
//    public static void main(String[] args)
//    {
//        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
//    }
//}

public class Application 
    extends SpringBootServletInitializer
    implements AppShellConfigurator
{

    public static void main(String[] args)
    {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(Application.class);
    }
}
