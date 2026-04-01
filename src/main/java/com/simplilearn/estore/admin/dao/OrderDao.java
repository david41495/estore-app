package com.simplilearn.estore.admin.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.simplilearn.estore.db.DB;
import com.simplilearn.estore.enduser.dao.Dao;
import com.simplilearn.estore.admin.model.Order;

public class OrderDao implements Dao<Order> {

    private final DB db = DB.getDB();


    private static final String TABLE = "orders";
    private static final String COL_ID = "order_id";
    private static final String COL_DATE = "order_date";
    private static final String COL_STATUS = "order_status";
    private static final String COL_TOTAL_ITEMS = "total_items";
    private static final String COL_SUBTOTAL = "items_subtotal";
    private static final String COL_SHIP_CHG = "shipment_charges";
    private static final String COL_TOTAL_AMT = "total_amount";
    private static final String COL_PAY_STATUS = "payment_status";
    private static final String COL_PAY_METHOD = "payment_method";
    private static final String COL_PAY_TITLE = "payment_method_title";
    private static final String COL_USER_ID = "user_id";

    //  mapRow
    private Order mapRow(ResultSet rs) throws Exception {
        Order o = new Order();
        o.setOrderId(rs.getInt(COL_ID));
        o.setOrderDate(new Date(rs.getTimestamp(COL_DATE).getTime()));
        o.setOrderStatus(rs.getString(COL_STATUS));
        o.setTotalItems(rs.getInt(COL_TOTAL_ITEMS));
        o.setItemsSubTotal(rs.getDouble(COL_SUBTOTAL));
        o.setShipmentChargers(rs.getDouble(COL_SHIP_CHG));
        o.setTotalAmount(rs.getDouble(COL_TOTAL_AMT));
        o.setPaymentStatus(rs.getInt(COL_PAY_STATUS));
        o.setPaymentMethod(rs.getInt(COL_PAY_METHOD));     // <-- FIXED to String
        o.setPaymentMethodTitle(rs.getString(COL_PAY_TITLE));
        o.setUserId(rs.getInt(COL_USER_ID));
        return o;
    }

    // CRUD
    @Override
    public Order get(long id) {
        Order o = null;
        try {
            ResultSet rs = db.executeQuery("SELECT * FROM " + TABLE + " WHERE " + COL_ID + " = " + id);
            if (rs.next()) o = mapRow(rs);
        } catch (Exception e) { e.printStackTrace(); }
        return o;
    }

    @Override
    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();
        try {
            ResultSet rs = db.executeQuery("SELECT * FROM " + TABLE);
            while (rs.next()) list.add(mapRow(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public void save(Order o) {
        String sql = "INSERT INTO " + TABLE + " (" +
                COL_DATE + "," + COL_STATUS + "," + COL_TOTAL_ITEMS + "," +
                COL_SUBTOTAL + "," + COL_SHIP_CHG + "," + COL_TOTAL_AMT + "," +
                COL_PAY_STATUS + "," + COL_PAY_METHOD + "," + COL_PAY_TITLE + "," + COL_USER_ID +
                ") VALUES (" +
                "'" + new java.sql.Timestamp(o.getOrderDate().getTime()) + "'," +
                "'" + o.getOrderStatus() + "'," +
                o.getTotalItems() + "," +
                o.getItemsSubTotal() + "," +
                o.getShipmentChargers() + "," +
                o.getTotalAmount() + "," +
                o.getPaymentStatus() + "," +
                "'" + o.getPaymentMethod() + "'," +
                "'" + o.getPaymentMethodTitle() + "'," +
                o.getUserId() + ")";
        try {
            db.executeUpdate(sql);
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void update(Order o) {
        if (o.getOrderId() == null) return; // no update without ID
        String sql = "UPDATE " + TABLE + " SET " +
                COL_DATE + "='" + new java.sql.Timestamp(o.getOrderDate().getTime()) + "'," +
                COL_STATUS + "='" + o.getOrderStatus() + "'," +
                COL_TOTAL_ITEMS + "=" + o.getTotalItems() + "," +
                COL_SUBTOTAL + "=" + o.getItemsSubTotal() + "," +
                COL_SHIP_CHG + "=" + o.getShipmentChargers() + "," +
                COL_TOTAL_AMT + "=" + o.getTotalAmount() + "," +
                COL_PAY_STATUS + "=" + o.getPaymentStatus() + "," +
                COL_PAY_METHOD + "='" + o.getPaymentMethod() + "'," +
                COL_PAY_TITLE + "='" + o.getPaymentMethodTitle() + "'," +
                COL_USER_ID + "=" + o.getUserId() +
                " WHERE " + COL_ID + "=" + o.getOrderId();
        try {
            db.executeUpdate(sql);
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM " + TABLE + " WHERE " + COL_ID + "=" + id;
        try {
            db.executeUpdate(sql);
        } catch (Exception e) { e.printStackTrace(); }
    }
}