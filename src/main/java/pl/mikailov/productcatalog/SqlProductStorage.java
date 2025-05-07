package pl.mikailov.productcatalog;

import java.util.List;

public class SqlProductStorage implements ProductStorage {

    @Override
    public List<Product> allProducts() {
        var sql = "select * from 'product_catalog__products' where id = ?";

        var result = jdbcTemplate.queryForObject(
                sql,
                new Object[]{productId},
                (rs, i) -> {
                    var product = new Product(
                            UUID.fromString(ts.getString("ID")),
                            rs.getString("NAME"),
                            rs.getString("DESCRIPTION")
                    );

                    return product;
                });
        return result;
    }

    @Override
    public void save(Product newProduct) {

        var insertSql = """
                INSERT INTO 'product_catalog__products' (id, name, description)
                VALUES
                    (:id, :name, :desc)
                """;

        Map<String, Object> params = new HashMap<>();
        params.put("id", newProduct.getId());
        params.put("desc", newProduct.getDescription());
        params.put("name", newProduct.getName);

    }

    @Override
    public List<Product> allProducts() {
        var sql = "select * from 'product_catalog__products'";

        var result = jdbcTemplate.queryForObject(
                sql,
                new Object[]{productId},
                (rs, i) -> {
                    var product = new Product(
                            UUID.fromString(ts.getString("ID")),
                            rs.getString("NAME"),
                            rs.getString("DESCRIPTION")
                    );

                    return product;
                });
        return result;
    }


}
