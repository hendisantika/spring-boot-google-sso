package com.hendisantika.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-google-sso
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/04/22
 * Time: 07.10
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("home")
    public void home() {
    }

    @GetMapping("auth")
    @ResponseBody
    public Authentication auth(Authentication auth) {
        return auth;
    }

    @PreAuthorize("hasAuthority('VIEW_REKENING')")
    @GetMapping("rekening")
    public void daftarRekening() {
    }
}
