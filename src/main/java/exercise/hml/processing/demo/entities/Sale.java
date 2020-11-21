package exercise.hml.processing.demo.entities;


import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    private double discount;
    private Car car;
    private Customer customer;

    public Sale() {
    }


    @Column
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }


    @ManyToOne
    @JoinColumn(name = "car_id")
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
