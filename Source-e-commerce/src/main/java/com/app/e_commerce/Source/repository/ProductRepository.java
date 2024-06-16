package com.app.e_commerce.Source.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.e_commerce.Source.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    
    @Query(value = """
            select p from ProductEntity p where 1=1 
            and ((:productName is null or :productName = '') or lower(p.productName) like lower(concat('%', :productName, '%'))) 
            order by 
            case 
                when p.changedDt is not null then p.changedDt 
                else p.createdDt 
            end desc, 
            p.createdDt desc """,
    countQuery = 
        """
            select count(p) from ProductEntity p where 1=1 
            and ((:productName is null or :productName = '') or lower(p.productName) like lower(concat('%', :productName, '%'))) 
            """)
    Page<ProductEntity> search(@Param("productName") String productName, Pageable pageable);

    @Query(value = "select p from ProductEntity p where lower(p.productName) = :productName ")
    ProductEntity getProduct(@Param("productName") String productName);

}
