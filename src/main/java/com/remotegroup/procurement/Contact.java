package com.remotegroup.procurement;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Contact /*extends Supplier?*/ {
    private @Id @GeneratedValue Long id;
    String name;
    String phone;
    String email;
    String position;

    Contact(String n, String p, String e, String po){
        name = n;
        phone = p;
        email = e;
        position = po;
    }
    public Long getId(){
        return this.id;
    }
    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }

    public String getEmail(){
        return email;
    }

    public String getPosition(){
        return position;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void setPhone(String newPhone){
        this.phone = newPhone;
    }

    public void setEmail(String newEmail){
        this.email = newEmail;
    }

    public void setPosition(String newPosition){
        this.position = newPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Contact))
            return false;
            Contact contact = (Contact) o;
        return Objects.equals(this.id, contact.id) 
        && Objects.equals(this.name, contact.name)
        && Objects.equals(this.phone, contact.phone) 
        && Objects.equals(this.email, contact.email)
        && Objects.equals(this.position, contact.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.phone, this.email, this.position);
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + this.id + '\''
         + ", name='" + this.name + '\''
         + ", phone='" + this.phone + '\''
         + ", email='" + this.email + '\'' 
         + ", position='" + this.position + '\'' 
         + '}';
    }
}
