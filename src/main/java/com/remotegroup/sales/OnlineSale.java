package com.remotegroup.sales;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OnlineSale {
    private @Id @GeneratedValue Long id;
    String CustomerName;
    String address;

    OnlineSale () {}

    OnlineSale(String c, String n){
        CustomerName = c;
        address = n;
    }
    public Long getId(){
        return this.id;
    }

    public String getCustomerName(){
        return this.CustomerName;
    }

    public String getSaleId(){
        return this.CustomerName;
    }

    public String getAddress(){
        return address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerName(String newCustomerName) {
        this.CustomerName = newCustomerName;
    }

    public void setAddress(String newAddress){
        this.address = newAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof OnlineSale))
            return false;
            OnlineSale OnlineSale = (OnlineSale) o;
        return Objects.equals(this.id, OnlineSale.id) 
        && Objects.equals(this.CustomerName, OnlineSale.CustomerName)
        && Objects.equals(this.address, OnlineSale.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.CustomerName, this.address);
    }

    @Override
    public String toString() {
        return "OnlineSale{" + "id=" + this.id + '\''
        + ", CustomerName id='" + this.CustomerName + '\'' 
        + ", address='" + this.address + '\''
         + '}';
    }
}