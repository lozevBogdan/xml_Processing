package exercise.hml.processing.demo;

import exercise.hml.processing.demo.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Engine implements CommandLineRunner {

    public final SupplierService supplierService;
    public final UserService userService;
    public final ProductService productService;
    public final CategoryService categoryService;
    public final PartService partService;
    public final CarService carService;
    public final CustomerService customerService;
    private final SaleService saleService;

    public Engine(SupplierService supplierService, UserService userService, ProductService productService,
                  CategoryService categoryService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.partService = partService;
        this.carService = carService;

        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {



        // Seed data
        this.userService.seedUsers();
        this.categoryService.seedCategory();
        this.productService.seedProducts();

        //Query 1 – Products in Range
       this.productService.getProductInRange500And1000();

        //Query 2 – Successfully Sold Products
       this.userService.getAllSuccessfullySoldProducts();

        //Query 3 – Categories by Products Count
        this.categoryService.getAllCategoriesByProductsCount();

        //Query 4 – Users and Products
        this.userService.getAllBySoldProductsSizeAndLastName();

 //seedData from suppliers.xml, parts.xml, cars.xml, customers.xml
        this.supplierService.seedSuppliers();
        this.partService.seedParts();
        this.carService.seedCars();
        this.customerService.seedCustomers();
        this.saleService.seedSales();

        //Query 1 – Ordered Customers
        this.customerService.getAllCustomersOrderByBirthDateAndOldestDriver();

        //Query 2 – Cars from Make Toyota
       this.carService.getCarsByToyota();

        //Query 3 – Local Suppliers
      this.supplierService.getLocalSuppliers();

        //Query 4 – Cars with Their List of Parts
       this.carService.getCarsWithTheirParts();

        //Query 5 – Total Sales by Customer
     this.customerService.getTotalSalesByCustomers();

        //Query 6 – Sales with Applied Discount
        this.saleService.getSalesWithAppliedDiscount();

        System.out.println("All Processes are finished !");


    }
}
