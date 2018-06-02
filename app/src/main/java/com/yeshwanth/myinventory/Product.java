package com.yeshwanth.myinventory;

/*
 * Created by Yeshwanth on 24-04-2018. # _Yeshwanth_Reddy
 */
public class Product {
    private String name, vendor, price, stock, item_id;

    public Product(String name, String price, String stock){
        this.name = name;
        this.price = price;
        this.stock = stock;
//        this.item_id = item_id;
    }
    public String getname(){ return name;}
    public String getprice(){ return  price;}
    public String getstock(){ return stock;}
//    public String getItem_id(){ return item_id;}
}
