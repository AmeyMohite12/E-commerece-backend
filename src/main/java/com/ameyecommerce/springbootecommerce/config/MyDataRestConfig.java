package com.ameyecommerce.springbootecommerce.config;

import com.ameyecommerce.springbootecommerce.entity.Country;
import com.ameyecommerce.springbootecommerce.entity.Product;
import com.ameyecommerce.springbootecommerce.entity.ProductCategory;
import com.ameyecommerce.springbootecommerce.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/// Added CorsRegistry as an additional adrgument ; It changed overtime after tutorial was made.

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {



        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};

        // disable HTTP methods for Product: PUT, POST, DELETE and PATCH
        disableHTTPMethods(Product.class ,config, theUnsupportedActions);
        disableHTTPMethods(ProductCategory.class ,config, theUnsupportedActions);
        disableHTTPMethods(State.class ,config, theUnsupportedActions);
        disableHTTPMethods(Country.class ,config, theUnsupportedActions);


        // need to expose Ids of product category in ProductCategory repo so need this helper method with entity manager object.
        exposeIds(config);

    }

    private void disableHTTPMethods(Class theClass ,RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config){
        Set<EntityType<?> > entities = entityManager.getMetamodel().getEntities();

        List<Class> entityClasses = entities.stream().map(e->e.getJavaType()).collect(Collectors.toList());
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
