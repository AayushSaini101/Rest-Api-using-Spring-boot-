package Aayush.Aayush;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(AuthenticationManagerBuilder ob){
        //Providing the access to the particular roles of the client
        try {
            ob.inMemoryAuthentication()
                     .withUser("Aayush").password(("Aayush")).roles(("USER"))
                     .and()
                     .withUser("Zack")
                     .password("Zack").roles("ADMIN");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   @Bean
    public PasswordEncoder getPasswordEncoder(){

        return NoOpPasswordEncoder.getInstance();
   }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/GetPerson").hasRole("USER")
                .antMatchers("/createTable").hasRole("ADMIN").and().formLogin();
    }
}

