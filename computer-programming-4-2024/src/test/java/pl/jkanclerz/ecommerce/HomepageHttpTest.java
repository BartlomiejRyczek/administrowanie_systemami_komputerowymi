package pl.jkanclerz.ecommerce;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomepageHttpTest {
    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate http;

    @Test
    void itLoadsHomepage() {
        var url = String.format("http://localhost:%s", port);

        ResponseEntity<String> response = http.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody()).contains("My ecommerce");
    }
}
