package se.iths.springloppis.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Hjärtat av konfigurationen
@Configuration
@EnableWebSecurity // Få möjlighetatt overrida default implementatioenn.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final LoppisUserDetailService userDetailService;

    public SecurityConfig(LoppisUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    // Vill ha en authication provider
    // Man kan använda sig av många sådana i en Spring Security samt flera i samma applikation.
    @Bean // I och med att vi annoterar med Bean, görs det tillgängligt för oss så att vi kan injecta denna. I JavaEnterprice heter den @Produces
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); // Skapar DaoAuthenticationProvider
        provider.setUserDetailsService(userDetailService); // Kopplar ihop den med våran userDetailService, som är den som hämtgar ut våran userEntity och skapar upp en Principal.
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()  // Här anger vi vilken typ av authentisering och hur den ska skydda våran applikation.
                .antMatchers("/", "/users/signup") // Här skriver vi olika endpoint. Hur de ska vara tillgängliga.
                .permitAll() // De här endpoints kan alla nå
                .anyRequest().authenticated() // Alla andra requests ska vara authenticated.
                .and().formLogin(); // Slutligen får vi ange vilken typ av "login" authentisering vi ska ha.
    }

    // Det finns något som heter CSRF ( csrf().disable() ) = Cross Side Request Fogery.  Det är en slags hacking attack.
    // Är ett mer avancerat scenario man kan vilja skydda sig mot.
    // För att våran authentisering ska fungera, behöver vi i det här scenariot "disable" den,
    // annars kommer det inte att fungera. Alltså de här inbyggda skyddet csrf.
}
