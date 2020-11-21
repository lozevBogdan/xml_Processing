package exercise.hml.processing.demo.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    private String make;
    private String model;
    private Long travelledDistance;
    private Set<Part> parts;
    private Set<Sale> sales;



    public Car() {

    }


    @Column
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Column
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "travelled_distance")
    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cars_parts",
    joinColumns = @JoinColumn(name = "part_id"),
    inverseJoinColumns = @JoinColumn(name = "car_id"))
    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    @OneToMany(mappedBy = "car")
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }


}
