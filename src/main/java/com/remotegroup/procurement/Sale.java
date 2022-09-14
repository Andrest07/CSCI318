package com.remotegroup.procurement;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sale {
    private @Id @GeneratedValue Long id;
    Long productId;
    Long InStoreSaleId;
    Long OnlineSaleId;
    String ProductName;
    Integer quantity;
    String DataTime;

    Sale () {}

    Sale(Long p, Long i,  Long os, String pn, Integer q, String dt){
        productId = p;
        InStoreSaleId = i;
        OnlineSaleId = os;
        ProductName = pn;
        quantity = q;
        DataTime = dt;
    }
    public Long getId(){
        return this.id;
    }

    public Long getProductId(){
        return this.productId;
    }

    public Long getInStoreSaleId(){
        return this.InStoreSaleId;
    }

    public Long getOnlineSaleId(){
        return this.OnlineSaleId;
    }

    public String getProductName(){
        return ProductName;
    }

    public Integer getQuantity(){
        return quantity;
    }

    public String getDataTime(){
        return DataTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(Long pId) {
        this.productId = pId;
    }

    public void setInStoreSaleId(Long sId) {
        this.InStoreSaleId = sId;
    }

    public void setOnlineSaleId(Long oId) {
        this.OnlineSaleId = oId;
    }

    public void setProductName(String newProductName){
        this.ProductName = newProductName;
    }

    public void setQuantity(Integer newQuantity){
        this.quantity = newQuantity;
    }

    public void setDataTime(String newDataTime){
        this.DataTime = newDataTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Sale))
            return false;
            Sale sale = (Sale) o;
        return Objects.equals(this.id, sale.id) 
        && Objects.equals(this.productId, sale.productId)
        && Objects.equals(this.InStoreSaleId, sale.InStoreSaleId)
        && Objects.equals(this.OnlineSaleId, sale.OnlineSaleId)
        && Objects.equals(this.ProductName, sale.ProductName)
        && Objects.equals(this.quantity, sale.quantity)
        && Objects.equals(this.DataTime, sale.DataTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.InStoreSaleId, this.productId, this.ProductName, this.quantity);
    }

    @Override
    public String toString() {
        return "Sale{" + "id=" + this.id + '\''
        + ", product id='" + this.productId + '\'' 
        + ", InStoreSale id='" + this.InStoreSaleId + '\'' 
        + ", OnlineSale id='" + this.OnlineSaleId + '\'' 
        + ", ProductName='" + this.ProductName + '\''
        + ", quantity='" + this.quantity + '\''
        + ", DataTime='" + this.DataTime + '\''
        + '}';
    }
}
