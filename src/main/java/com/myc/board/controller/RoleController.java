package com.myc.board.controller;

import java.util.HashMap;
import java.util.Set;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello KeyCloak!";
    }
    @RequestMapping("/health-check")
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.ok()
                            .body(new HashMap<>(){{
                                put("STATUS", "health check success");
                            }});
    }

    @RequestMapping("/tester/test")
    public String tester() {
        return "This is tester permit";
    }

    @RequestMapping("/user/test")
    public String user() {
        return "This is user permit";
    }

    @RequestMapping("/manager/test")
    public String manager() {
        System.out.println("manager router connect");
        return "This is manager permit";
    }

    @GetMapping("/userinfo")
    public String userInfoController(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        KeycloakPrincipal principal = (KeycloakPrincipal)auth.getPrincipal();


        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();
        String username = accessToken.getPreferredUsername();
        String emailID = accessToken.getEmail();
        String lastname = accessToken.getFamilyName();
        String firstname = accessToken.getGivenName();
        String realmName = accessToken.getIssuer();
        AccessToken.Access realmAccess = accessToken.getRealmAccess();
        Set<String> roles = realmAccess.getRoles();
        System.out.println("emailID = " + emailID);
        System.out.println("firstname = " + firstname);
        System.out.println("lastname = " + lastname);
        System.out.println("username = " + username);
        System.out.println("realmName = " + realmName);
        System.out.println("roles = "+ roles);

        //KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) principal;
        //AccessToken accessToken = keycloakAuthenticationToken.getAccount().getKeycloakSecurityContext().getToken();
        //
        //model.addAttribute("username", accessToken.getGivenName());
        //System.out.println("accessToken.getGivenName() = " + accessToken.getGivenName());
        return "page";
    }
}
