package org.freelac.book.springboot.web;

import org.freelac.book.springboot.web.ProfileController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProfileControllerTest {
    
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("profile은 인증없이 호출될 수 있다.")
    void can_response_profile_not_Authentication() {
        //given
        String expected = "default";
        //when
        ResponseEntity<String> response = restTemplate.getForEntity("/profile", String.class);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expected);
    }

    @Test
    @DisplayName("real profile이 조회된다")
    void profile() {
        //given
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");
        ProfileController profileController = new ProfileController(env);
        //when
        String profile = profileController.profile();
        //then
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    @DisplayName("real profile이 없으면 첫번째를 조회한다.")
    void if_not_real_find_first_profile() {
        //given
        String expectedProfile = "oauth";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");
        ProfileController profileController = new ProfileController(env);
        //when
        String profile = profileController.profile();
        //then
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    @DisplayName("active profile이 없으면 default를 조회한다.")
    void if_not_active_find_default_profile() {
        //given
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment();

        ProfileController profileController = new ProfileController(env);
        //when
        String profile = profileController.profile();
        //then
        assertThat(profile).isEqualTo(expectedProfile);
    }
}