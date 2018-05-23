package com.etnetera.hr.data;


import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Predicate;
public class FrameworkSpecification {


       public static Specification<JavaScriptFramework> dynamicQuery(FrameworkRequestDTO request) {
         return (javaScripFrameworkRoot, query, builder) -> {
             Predicate p = builder.conjunction();
             if (request.getId() != null) {
                 p.getExpressions().add(builder.equal(javaScripFrameworkRoot.get("id"), request.getId()));
             }
             if (request.getName() != null) {
                 p.getExpressions().add(builder.equal(javaScripFrameworkRoot.get("name"), request.getName()));
             }
             if (request.getVersion() != null) {
                 p.getExpressions().add(builder.equal(javaScripFrameworkRoot.get("version"), request.getVersion()));
             }
             if (request.getHypeLevel() != null) {
                 p.getExpressions().add(builder.equal(javaScripFrameworkRoot.get("hypeLevel"), request.getHypeLevel()));
             }

             return p;
         };
     }

    }
