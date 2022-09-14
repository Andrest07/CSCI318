package com.remotegroup.sales;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InStoreSale extends Sale {
    private @Id @GeneratedValue Long id;
    Long storeId;
    String receipt;

    InStoreSale () {}

    InStoreSale(Long i, String n){
        storeId = i;
        receipt = n;
    }
    public Long getId(){
        return this.id;
    }

    public Long getStoreId(){
        return this.storeId;
    }

    public Long getSaleId(){
        return this.storeId;
    }

    public String getReceipt(){
        return receipt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStoreId(Long id) {
        this.storeId = id;
    }

    public void setReceipt(String newReceipt){
        this.receipt = newReceipt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof InStoreSale))
            return false;
            InStoreSale InStoreSale = (InStoreSale) o;
        return Objects.equals(this.id, InStoreSale.id) 
        && Objects.equals(this.storeId, InStoreSale.storeId)
        && Objects.equals(this.receipt, InStoreSale.receipt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.storeId, this.receipt);
    }

    @Override
    public String toString() {
        return "InStoreSale{" + "id=" + this.id + '\''
        + ", store id='" + this.storeId + '\'' 
        + ", receipt='" + this.receipt + '\''
         + '}';
    }
}