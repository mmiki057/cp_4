package pl.iashchenko.productcatalog;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SqlProductStorageTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    void setupDatabase(){
        jdbcTemplate.execute("DROP TABLE 'product_catalog__products' IF EXISTS");

        var sql = """
                CREATE TABLE 'product_catalog__products' (
                    id VARCHAR(100) NOT NULL,
                    name VARCHAR(100) NOT NULL,
                    description VARCHAR(100) NOT NULL,
                    price DECIMAL (12, 2),
                    cover VARCHAR(100),
                    PRIMARY KEY(id)                    
                """;

        jdbcTemplate.execute(sql);
    }
    @Test
    void itAllowsToInsertIntoTableWithArguments() {

        var insertSql = """
                INSERT INTO 'product_catalog__products' (id, name, description)
                VALUES
                    (?, ?, ?)
                """;

        jdbcTemplate.update(insertSql, "e9c1fd22-8286-4f7c-b506-79cc2d5310fd", "product1", "nice one");

        var result = jdbcTemplate.queryFotObject("select count(*) from 'product_catalog_products'", Integer.class);

        assert result == 1;

    }

    @Test
    void itAllowsToInsertIntoTableWithArgumentsNamedParameters() {

        var insertSql = """
                INSERT INTO 'product_catalog__products' (id, name, description)
                VALUES
                    (:id, :name, :desc)
                """;

        Map<String, Object> params = new HashMap<>();
        params.put("id", "e9c1fd22-8286-4f7c-b506-79cc2d5310fd");
        params.put("desc", "products");
        params.put("name", "nice product");

        var namedJdbc = new NamedParameterJdbcTemplate(jdbcTemplate);
        namedJdbc.update(insertSql, params);

        var result = jdbcTemplate.queryForObject("select count(*) from 'product_catalog__catalogs'", Integer.class)

        assert result == 1;

    }
    @Test
    void itSaveAndLoadProduct() {
        var product = thereIsProduct();
        var storage = thereIsStorage();

        storage.save(product);

        var loaded = storage.loadProductById(product.getId());

        assertEquals(product.getId(), loaded.getId());
        assertEquals(product.getDescription(), loaded.getDescription());
    }

    private ProductStorage thereIsStorage() {
        return HashMapProductStorage();
    }

    private Product thereIsProduct() {
        return null;
    }


    @Test
    void itLoadsAllProducts() {
        Product product = thereIsProduct();
        ProductStorage storage = thereIsStorage();

        storage.save(product);

        List<Product> all = storage.allProducts();

        assertEquals(1, all.size());
    }

}
