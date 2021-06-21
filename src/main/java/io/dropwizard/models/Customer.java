package io.dropwizard.models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Customer {

    private int customerId;

    @NotEmpty
    @Length(max = 40)
    private String customerName;

    public Customer(int customerId, String customerName){
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public int getCustomerId(){return customerId; }

    public void setCustomerId(int customerId){this.customerId = customerId;}

    public String getCustomerName(){return customerName;}

    public void setCustomerName(String customerName){this.customerName = customerName;}


}
