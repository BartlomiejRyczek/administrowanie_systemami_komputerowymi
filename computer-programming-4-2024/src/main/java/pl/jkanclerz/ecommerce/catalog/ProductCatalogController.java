package pl.jkanclerz.ecommerce.catalog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductCatalogController {

    private final ProductCatalog productCatalog;

    public ProductCatalogController(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    @GetMapping("/api/products")
    List<Product> getAllProducts() {
        return productCatalog.allProducts();
    }
}
