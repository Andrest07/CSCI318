package com.remotegroup.inventory;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Part {
    private @Id @GeneratedValue Long id;
    long supplierId;
    Long productId;
    String name;
    String description;

    Part () {}

    Part(Long i, Long p, String n, String d){
        supplierId = i;
        productId = p;
        name = n;
        description = d;
    }
    public Long getId(){
        return this.id;
    }

    public Long getSupplierId(){
        return this.supplierId;
    }

    public Long getProductId(){
        return this.productId;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSupplierId(Long sId) {
        this.supplierId = sId;
    }

    public void setProductId(Long pId) {
        this.productId = pId;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void setDescription(String newDescription){
        this.description = newDescription;
    }

    public boolean equals(Part o) {
        if (this == o)
            return true;
        if (!(o instanceof Part))
            return false;
            Part part = (Part) o;
        return Objects.equals(this.id, part.id) 
        && Objects.equals(this.supplierId, part.supplierId)
        && Objects.equals(this.productId, part.productId)
        && Objects.equals(this.name, part.name)
        && Objects.equals(this.description, part.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.supplierId, this.productId, this.name, this.description);
    }

    @Override
    public String toString() {
        return "Part{" + "id=" + this.id + '\''
        + ", supplier id='" + this.supplierId + '\'' 
        + ", product id='" + this.productId + '\'' 
        + ", name='" + this.name + '\''
         + ", description='" + this.description + '\''
         + '}';
    }
}