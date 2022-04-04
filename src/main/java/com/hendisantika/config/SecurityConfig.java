package com.hendisantika.config;

import com.hendisantika.entity.Permission;
import com.hendisantika.entity.User;
import com.hendisantika.repository.UserRepository;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-google-sso
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/04/22
 * Time: 07.06
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().logout().permitAll()
                .and().oauth2Login()
                .userInfoEndpoint()
                .userAuthoritiesMapper(authoritiesMapper())
                .and().defaultSuccessUrl("/home", true);
    }

    private GrantedAuthoritiesMapper authoritiesMapper() {
        return (authorities) -> {
            String emailAttrName = "email";
            String email = authorities.stream()
                    .filter(OAuth2UserAuthority.class::isInstance)
                    .map(OAuth2UserAuthority.class::cast)
                    .filter(userAuthority -> userAuthority.getAttributes().containsKey(emailAttrName))
                    .map(userAuthority -> userAuthority.getAttributes().get(emailAttrName).toString())
                    .findFirst()
                    .orElse(null);

            if (email == null) {
                return authorities;        // data email tidak ada di userInfo dari Google
            }

            User user = userRepository.findByUsername(email);
            if (user == null) {
                return authorities;     // email user ini belum terdaftar di database
            }

            Set<Permission> userAuthorities = user.getRole().getPermissions();
            if (userAuthorities.isEmpty()) {
                return authorities;        // Return the 'unmapped' authorities
            }

            return Stream.concat(
                    authorities.stream(),
                    userAuthorities.stream()
                            .map(Permission::getValue)
                            .map(SimpleGrantedAuthority::new)
            ).collect(Collectors.toCollection(ArrayList::new));
        };
    }
}
