package com.rotomaker.oemindia;

public class Shops {
    private String vendorid,image,companyname,location,price,mobilenumber,productname;
    public Shops(String vendorid, String image, String productname,String companyname,String location,String price,String mobilenumber) {
        this.vendorid = vendorid;
        this.image = image;
        this.productname=productname;
        this.companyname = companyname;
        this.location = location;
        this.price = price;
        this.mobilenumber = mobilenumber;


    }
    public String getVendorid() {
        return vendorid;
    }
    public void setVendorid(String vendorid) {
        this.vendorid = vendorid;
    }


    public String getImage() {
        return image;
    }
    public void setImage(String image) {this.image=image;}

    public String getProductname() {
        return productname;
    }
    public void setProductname(String productname) {this.productname=productname;}

    public String getCompanyname() {
        return companyname;
    }
    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }
    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }



}
