package com.hpi.tpc.app.security;

import com.hpi.tpc.ui.views.main.*;
import com.vaadin.flow.spring.security.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;

/**
 * Configures spring security, doing the following:
 * <li>Bypass security checks for static resources,</li>
 * <li>Restrict access to the application, allowing only logged in users,</li>
 * <li>Set up the login form</li>
 * <p>
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity
{

//    @Autowired private CustomUserDetailsService userDetailsService;

//    @Autowired private PasswordEncoder passwordEncoder;

    /**
     * Require login to access internal pages and configure login form.
     *
     * @param http
     *
     * @throws java.lang.Exception
     */
    @Override
    protected void configure(HttpSecurity http)
        throws Exception
    {
        /*// Not using Spring CSRF here to be able to use plain HTML 
        //  for the login page
        http.csrf().disable()
            // Register our CustomRequestCache, that saves unauthorized 
            //  access attempts, so the user is redirected after login.
            .requestCache().requestCache(new CustomRequestCache())
            // Restrict access to our application.
            .and().authorizeRequests()
            // Allow all flow internal requests.
            .requestMatchers(SecurityUtils::isFrameworkInternalRequest).
            permitAll()
            // Allow all requests by logged in users.
            .anyRequest().authenticated()
            // Configure the login page.
            .and().formLogin().loginPage(LOGIN_URL).permitAll().
            loginProcessingUrl(LOGIN_PROCESSING_URL)
            .failureUrl(LOGIN_FAILURE_URL)
            // Register the success handler that redirects users to 
            //  the page they last tried
            // to access
            .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
            // Configure logout
            .and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);

//                    http.authorizeRequests().anyRequest().hasAnyAuthority(Role.getAllRoles());
        */
         // Delegating the responsibility of general configurations
        // of http security to the super class. It's configuring
        // the followings: Vaadin's CSRF protection by ignoring
        // framework's internal requests, default request cache,
        // ignoring public views annotated with @AnonymousAllowed,
        // restricting access to other views/endpoints, and enabling
        // ViewAccessChecker authorization.
        // You can add any possible extra configurations of your own
        // here (the following is just an example):

        // http.rememberMe().alwaysRemember(false);

        // Configure your static resources with public access before calling
        // super.configure(HttpSecurity) as it adds final anyRequest matcher
        http.authorizeRequests()
            .antMatchers("/public/**")
            .permitAll();

        super.configure(http); 

        // This is important to register your login view to the
        // view access checker mechanism:
        setLoginView(http, LoginView.class); 
    }

    /**
     * The password encoder to use when encrypting passwords.
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    

    /**
     * Registers our UserDetailsService and the password encoder to be used on
     * login attempts.
     *
     * @param auth
     *
     * @throws java.lang.Exception
     */
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth)
        throws Exception
    {
        super.configure(auth);
        auth.userDetailsService(userDetailsService).
            passwordEncoder(passwordEncoder);
    }
    */

    /**
     * Allows access to static resources, bypassing Spring security.
     *
     * @param web
     * @throws java.lang.Exception
     */
    @Override
    public void configure(WebSecurity web)
        throws Exception
    {
        super.configure(web);
        /*web.ignoring().antMatchers(
            // Vaadin Flow static resources
            "/VAADIN/**",
            // the standard favicon URI
            "/favicon.ico",
            // the robots exclusion standard
            "/robots.txt",
            // web application manifest
            "/manifest.webmanifest",
            "/sw.js",
            "/offline-page.html",
            // icons and images
            "/icons/**",
            "/images/**",
            // (development mode) static resources
            "/frontend/**",
            // (development mode) webjars
            "/webjars/**",
            // (development mode) H2 debugging console
            "/h2-console/**",
            // (production mode) static resources
            "/frontend-es5/**", "/frontend-es6/**",
            "/sw-runtime-resources-precache.js");
*/
    }
}
