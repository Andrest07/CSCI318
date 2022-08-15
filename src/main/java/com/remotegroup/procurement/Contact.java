package com.remotegroup.procurement;


public class Contact /*extends Supplier?*/ {
    String id;
    String name;
    String phone;
    String email;
    String position;

    Contact(String i, String n, String p, String e, String po){
        id = i;
        name = n;
        phone = p;
        email = e;
        position = po;
    }

    public String getId(){
        return id;
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

    /*
    public void setId(String newId){
        this.id = newId;
    }*/

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
}
