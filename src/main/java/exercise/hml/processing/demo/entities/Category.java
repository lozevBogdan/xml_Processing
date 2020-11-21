package exercise.hml.processing.demo.entities;


import com.fasterxml.jackson.databind.ser.Serializers;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private String name;
    private Set<Product> products;

    public Category() {
    }


    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "categories",fetch = FetchType.EAGER)
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
