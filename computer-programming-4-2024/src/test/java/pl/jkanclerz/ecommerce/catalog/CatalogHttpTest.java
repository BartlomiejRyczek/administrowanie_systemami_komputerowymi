package pl.jkanclerz.ecommerce.catalog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CatalogHttpTest {
    public static final String EXPECTED_PRODUCT_NAME = "Example product";

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate http;

    @Autowired
    ProductCatalog catalog;

    @Test
    void itLoadsProducts() {
        var url = String.format("http://localhost:%s/api/products", port);
        catalog.addProduct(EXPECTED_PRODUCT_NAME, "nice one");

        ResponseEntity<Product[]> response = http.getForEntity(url, Product[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody())
                .hasSizeGreaterThan(1)
                .extracting("name")
                .contains(EXPECTED_PRODUCT_NAME);
    }
}
