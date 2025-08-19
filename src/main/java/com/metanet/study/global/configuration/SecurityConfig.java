package com.metanet.study.global.configuration;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.saml2.core.Saml2X509Credential;
import org.springframework.security.saml2.core.Saml2X509Credential.Saml2X509CredentialType;
import org.springframework.security.saml2.provider.service.registration.InMemoryRelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistration;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrations;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  @Bean
  SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(exchanges -> exchanges.requestMatchers("/api/**").authenticated()
            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/api-docs/**").permitAll()
            .requestMatchers("/public/**", "/login**", "/oauth2/**", "/saml2/**").permitAll()
            .anyRequest().authenticated())
        .saml2Login(Customizer.withDefaults()) // 명시적 호출 없이, Saml2LoginConfigurer가 자동 등록됨
        // .logout(logout -> ...) // 로그아웃도 세션과 IDP 모두 하려면 세팅
        .csrf(csrf -> csrf.disable()) // CSRF 비활성화
        .build();
  }

  @Bean
  public RelyingPartyRegistrationRepository relyingPartyRegistrationRepository() throws Exception {
    String key =
        new String(new ClassPathResource("saml-signing.key").getInputStream().readAllBytes())
            .replaceAll("-----BEGIN PRIVATE KEY-----", "")
            .replaceAll("-----END PRIVATE KEY-----", "").replaceAll("\\s+", "");
    byte[] keyBytes = Base64.getDecoder().decode(key);
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
    PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);

    InputStream certInputStream = new ClassPathResource("saml-signing.crt").getInputStream();
    X509Certificate certificate = (X509Certificate) CertificateFactory.getInstance("X.509")
        .generateCertificate(certInputStream);

    Saml2X509Credential signingCredential =
        new Saml2X509Credential(privateKey, certificate, Saml2X509CredentialType.SIGNING);

    RelyingPartyRegistration registration =
        RelyingPartyRegistrations.fromMetadataLocation("classpath:keycloak-metadata.xml") // Keycloak에서
                                                                                          // export한
                                                                                          // IdP
                                                                                          // 메타데이터
                                                                                          // xml
            .registrationId("keycloak").entityId("client-saml-sp") // SP(EntityID), 반드시 Keycloak
                                                                   // Client ID와 일치
            .signingX509Credentials(c -> c.add(signingCredential))
            .assertionConsumerServiceLocation("http://localhost:5001/login/saml2/sso/keycloak") // rediect
                                                                                                // uri
            // .verificationX509Credentials(c -> c.add(verificationCredential)) //
            .build();


    //
    // RelyingPartyRegistration registration =
    // RelyingPartyRegistration.withRegistrationId("keycloak")
    // .entityId("client-saml-sp") // Spring의 EntityID, Keycloak Client ID와 맞춰야 함!
    // .assertionConsumerServiceLocation("http://localhost:8080/login/saml2/sso/keycloak")
    // .assertingPartyDetails(party -> party.entityId("http://localhost:7000/realms/md-auth-store")
    // .singleSignOnServiceLocation("http://localhost:7000/realms/md-auth-store/protocol/saml")
    // .verificationX509Credentials(c -> c.add(verificationCredential)))
    // .signingX509Credentials(c -> c.add(signingCredential)).build();

    return new InMemoryRelyingPartyRegistrationRepository(registration);
  }
}
