package de.seuhd.campuscoffee.api.dtos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserSystemTest {

    @Autowired
    private TestRestTemplate rest;

    @Test
    void testCreateAndRetrieveUser() {

        UserDtoAPI request = new UserDtoAPI(
                null, null, null,
                "Reiner",
                "Reiner@example.com",
                "Reiner",
                "Wahnsinn"
        );

        // POST /api/users
        ResponseEntity<UserDtoAPI> createResponse =
                rest.postForEntity("/api/users", request, UserDtoAPI.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResponse.getBody()).isNotNull();
        assertThat(createResponse.getBody().id()).isNotNull();

        Long id = createResponse.getBody().id();

        // GET /api/users/{id}
        ResponseEntity<UserDtoAPI> getResponse =
                rest.getForEntity("/api/users/" + id, UserDtoAPI.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).isNotNull();
        assertThat(getResponse.getBody().loginName()).isEqualTo("Reiner");
        assertThat(getResponse.getBody().emailAddress()).isEqualTo("Reiner@example.com");
        assertThat(getResponse.getBody().firstName()).isEqualTo("Reiner");
        assertThat(getResponse.getBody().lastName()).isEqualTo("Wahnsinn");
    }
    @Test
    void testUpdateUser() {

        // Zuerst Benutzer anlegen
        UserDtoAPI request = new UserDtoAPI(
                null, null, null,
                "Tim",
                "Tim@example.com",
                "Tim",
                "Tommy"
        );

        ResponseEntity<UserDtoAPI> createResponse =
                rest.postForEntity("/api/users", request, UserDtoAPI.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Long id = createResponse.getBody().id();

        // Update Payload
        UserDtoAPI updateDto = new UserDtoAPI(
                id,
                createResponse.getBody().createdAt(),
                null,
                "TimUpdated",
                "Tim.new@example.com",
                "Timmy",
                "Tom"
        );

        // PUT /api/users/{id}
        HttpEntity<UserDtoAPI> entity = new HttpEntity<>(updateDto, new HttpHeaders());
        ResponseEntity<UserDtoAPI> updateResponse =
                rest.exchange("/api/users/" + id, HttpMethod.PUT, entity, UserDtoAPI.class);

        assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // GET prüfen
        ResponseEntity<UserDtoAPI> getResponse =
                rest.getForEntity("/api/users/" + id, UserDtoAPI.class);

        assertThat(getResponse.getBody().loginName()).isEqualTo("TimUpdated");
        assertThat(getResponse.getBody().emailAddress()).isEqualTo("Tim.new@example.com");
        assertThat(getResponse.getBody().firstName()).isEqualTo("Timmy");
        assertThat(getResponse.getBody().lastName()).isEqualTo("Tom");
    }
}