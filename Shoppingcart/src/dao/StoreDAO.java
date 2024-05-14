package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Products;

public interface StoreDAO {

	ArrayList<String> getAllCategories();

	ArrayList<Products> getAllProducts() throws SQLException;

	ArrayList<Products> getAllProductsId(String id) throws SQLException;

	ArrayList<Products> getAllProductSort(String catid) throws SQLException;

	ArrayList<Products> getCatProductsSort(String catid, String sortid) throws SQLException;

	boolean checkpincode(String pincode, String prodid) throws SQLException;

	double discountcoupon(double amount, double couponcode) throws SQLException;

	double getdiscount(double total) throws SQLException;

	double getshippingcharges(double count) throws SQLException;

	double getgst(int proid) throws SQLException;
}