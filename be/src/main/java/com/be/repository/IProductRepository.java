package com.be.repository;

import com.be.dto.product.IProductDto;
import com.be.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductRepository extends JpaRepository<Product,Integer> {
    @Query(value = "select\n" +
            "p.id,p.description,p.name,p.price,p.quantity,p.img,pt.name as nameProductType,s.name as nameStyles,s2.name as nameSubject  from product p\n" +
            "join product_type pt on pt.id = p.product_type_id\n" +
            "join styles s on s.id = p.styles_id\n" +
            "join subject s2 on p.subject_id = s2.id\n" +
            "where p.name like concat('%',:nameProduct,'%')\n" +
            "and pt.name like concat('%',:nameProductType,'%')\n" +
            "and s.name like concat('%',:nameStyles,'%')\n" +
            "and s2.name like concat('%',:nameSubject,'%')\n" +
            "order by p.id desc",
    countQuery = "select\n" +
            "p.id,p.description,p.name,p.price,p.quantity,p.img,pt.name as nameProductType,s.name as nameStyles,s2.name as nameSubject  from product p\n" +
            "join product_type pt on pt.id = p.product_type_id\n" +
            "join styles s on s.id = p.styles_id\n" +
            "join subject s2 on p.subject_id = s2.id\n" +
            "where p.name like concat('%',:nameProduct,'%')\n" +
            "and pt.name like concat('%',:nameProductType,'%')\n" +
            "and s.name like concat('%',:nameStyles,'%')\n" +
            "and s2.name like concat('%',:nameSubject,'%')\n" +
            "order by p.id desc",
    nativeQuery = true)
    Page<IProductDto> listSearchProduct(Pageable pageable,
                                        @Param("nameProduct") String nameProduct,
                                        @Param("nameProductType")String nameProductType,
                                        @Param("nameStyles") String nameStyles,
                                        @Param("nameSubject")String nameSubject);

    @Query(value = "select\n" +
            "p.id,p.description,p.name,p.price,p.quantity,p.img,pt.name as nameProductType,s.name as nameStyles,s2.name as nameSubject  from product p\n" +
            "join product_type pt on pt.id = p.product_type_id\n" +
            "join styles s on s.id = p.styles_id\n" +
            "join subject s2 on p.subject_id = s2.id\n" +
            "where p.id = :idProduct",
    countQuery = "select\n" +
            "p.id,p.description,p.name,p.price,p.quantity,p.img,pt.name as nameProductType,s.name as nameStyles,s2.name as nameSubject  from product p\n" +
            "join product_type pt on pt.id = p.product_type_id\n" +
            "join styles s on s.id = p.styles_id\n" +
            "join subject s2 on p.subject_id = s2.id\n" +
            "where p.id = :idProduct",
    nativeQuery = true)
    IProductDto getProduct (@Param("idProduct") Integer idProduct);
}
