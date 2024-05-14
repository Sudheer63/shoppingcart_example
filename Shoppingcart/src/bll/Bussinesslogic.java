package bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DAObridge;
import dao.StoreDAO;
import model.BillingDetails;
import model.Items;

public class Bussinesslogic {
	private double total;
	private StoreDAO obj = DAObridge.get();;

	private double gettotal(List<Items> list) {
		for (Items i : list) {
			total += i.getPrice() * i.getQuantity();

		}
		return total;
	}

	public ArrayList<BillingDetails> proposedbill(List<Items> list) throws SQLException {
		ArrayList<BillingDetails> bill = new ArrayList<BillingDetails>();
		double priceratio = 0;
		double pricetoquantity = 0;
		double gst = 0;
		double sc_individual_gst = 0;
		double prodwithoutgst = 0;
		double ingst = 0;
		double count = gettotal(list);
		double sc_individual = 0;
		double total_individual = 0;
		double discount_amount = obj.getdiscount(count);
		double total_shipping_charges = obj.getshippingcharges(count - discount_amount);
		double discount_individual = 0;
		for (Items i : list) {

			gst = obj.getgst(i.getProid());

			// prod inclusive gst calculation
			prodwithoutgst = (i.getPrice() / (1 + (gst / 100)));
			ingst = i.getPrice() - prodwithoutgst;

			// individual shipping charges
			pricetoquantity = i.getPrice() * i.getQuantity();
			priceratio = pricetoquantity / count;

			sc_individual = total_shipping_charges * priceratio;

			// individual shipping charges gst
			sc_individual_gst = sc_individual * (gst / 100);

			// discount percentage
			discount_individual = priceratio * discount_amount;

			total_individual = pricetoquantity + sc_individual + sc_individual_gst - discount_individual;
			bill.add(new BillingDetails(i.getProid(), i.getName(), i.getPrice(), i.getQuantity(),
					Math.round(prodwithoutgst), Math.round(ingst), Math.round(pricetoquantity),
					Math.round(discount_individual), Math.round(sc_individual), Math.round(sc_individual_gst),
					Math.round(total_individual)));
		}

		return bill;
	}

}