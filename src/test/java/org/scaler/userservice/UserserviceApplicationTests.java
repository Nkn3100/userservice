package org.scaler.userservice;

import org.junit.jupiter.api.Test;
import org.scaler.userservice.security.repository.JpaRegisteredClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

//@SpringBootTest
class UserserviceApplicationTests {
//	@Autowired
//	private JpaRegisteredClientRepository jpaRegisteredClientRepository;
//
//	@Test
//	void contextLoads() {
//	}
//	@Test
//	void storeRegisteredClientIntoDB(){
//
//		        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("oidc-client")
//                .clientSecret("$2a$12$v5H/t6ZiArlzweUC/s/w6OUlqRhrarxSZETjDyHQ5y9UG0dYAPzV6")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .redirectUri("https://oauth.pstmn.io/v1/callback")
//                .postLogoutRedirectUri("https://oauth.pstmn.io/v1/callback")
//                .scope(OidcScopes.OPENID)
//                .scope(OidcScopes.PROFILE)
//                .scope("ADMIN")
//                .scope("STUDENT")
//                .scope("MENTOR") //Role
//				.scope("USER") //Role
//                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                .build();
//
//		jpaRegisteredClientRepository.save(oidcClient);
//
// 	}


}
