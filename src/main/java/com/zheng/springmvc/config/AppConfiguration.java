package com.zheng.springmvc.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.zheng.springmvc.security.CustomAuthenticationProvider;
import com.zheng.springmvc.security.CustomUserDetailsService;

/*
 * IMPORTANT: http://stackoverflow.com/questions/3652090/difference-between-applicationcontext-xml-and-spring-servlet-xml-in-spring-frame
 */

@EnableWebMvc // this is the same as <mvc:annotation-driven/>
@ComponentScan(basePackages = {"com.zheng.springmvc"}) //this is the same as <context:component-scan base-package="com.zheng.springmvc" />
@Configuration
public class AppConfiguration extends WebMvcConfigurerAdapter {
	
	//<mvc:resources mapping="/webjars/**" location="/webjars/"/>
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
    }
	
	/*
	 * <mvc:interceptors>
	        <bean id="localeChangeInterceptor"
	            class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor">
	            <property name="paramName" value="language" />
	        </bean>
    	</mvc:interceptors>
    */
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLocaleChangeInterceptor());
    }
	
	//<mvc:view-controller path="/login" view-name="login" />
	@Override
	  public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/login").setViewName("login");
	  }
	
	
	/*
	 * <bean
        class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix">
            <value>/WEB-INF/views/</value>
        </property>
        <property name="suffix">
            <value>.jsp</value>
        </property>
    	</bean>
	 */
	@Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
	
	/*
	 * <bean id="messageSource"
    		class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
    		<property name="basename" value="classpath:messages" />
    		<property name="defaultEncoding" value="UTF-8" />
		</bean>

	 */
	@Bean(name="messageSource")
	public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:messages");
		source.setDefaultEncoding("UTF-8");
		return source;
	}
	
	/*
	 * <bean id="localeResolver"
	        class="org.springframework.web.servlet.i18n.SessionLocaleResolver">
	        <property name="defaultLocale" value="en" />
	    </bean>
	 */
	@Bean(name="localeResolver")
	public SessionLocaleResolver getSessionLocaleResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		Locale locale = new Locale("en");
		resolver.setDefaultLocale(locale);
		return resolver;
	}
	
	/*
	 * <bean id="localeChangeInterceptor"
            class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor">
            <property name="paramName" value="language" />
        </bean>
	 */
	@Bean(name="localeChangeInterceptor")
	public LocaleChangeInterceptor getLocaleChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		return localeChangeInterceptor;
	}
	
	
	// http://www.baeldung.com/spring-security-registration-password-encoding-bcrypt
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	
}
