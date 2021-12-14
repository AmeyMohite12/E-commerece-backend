package com.ameyecommerce.springbootecommerce.dao;

import com.ameyecommerce.springbootecommerce.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// overriding the defaults of RestRepo , productCatepgry = JSON entity name and path = /product-category
// normally rest repo will just add an S to whatever the entity name i.e. /productcategorys

@RepositoryRestResource(collectionResourceRel =  "productCategory" , path = "product-category")
public interface ProductCategoryRepository  extends JpaRepository<ProductCategory , Long> {


}
