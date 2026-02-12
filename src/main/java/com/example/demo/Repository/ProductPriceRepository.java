package com.example.demo.Repository;

import com.example.demo.DTO.ShopProductPriceDTO;
import com.example.demo.Entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {
    @Query("""
    Select new com.example.demo.DTO.ShopProductPriceDTO(p.name, pp.price)
    FROM ProductPrice pp
    Join pp.product p
    Join pp.shop s
    Where s.id = :shopId
    And p.groceryList.id = :listId""")

    List<ShopProductPriceDTO> getPricesForListInShop(@Param("shopId") Long shopId, @Param("listId") Long listId);

}
