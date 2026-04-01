package com.simplilearn.estore.admin.dao;

import com.simplilearn.estore.db.DB;
import com.simplilearn.estore.admin.model.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD for the orderitems table.
 * Adjust table/column names if your schema differs.
 */
public class OrderItemDao {

    // SQL
    private static final String INSERT_SQL =
        "INSERT INTO orderitems " +
        "(orderId, productId, productTitle, productDescription, productCode, " +
        " productImg, productCategory, price, quantity, totalPrice) " +
        "VALUES (?,?,?,?,?,?,?,?,?,?)";

    private static final String UPDATE_SQL =
        "UPDATE orderitems SET " +
        "orderId=?, productId=?, productTitle=?, productDescription=?, productCode=?, " +
        "productImg=?, productCategory=?, price=?, quantity=?, totalPrice=? " +
        "WHERE orderItemId=?";

    private static final String DELETE_SQL =
        "DELETE FROM orderitems WHERE orderItemId=?";

    private static final String SELECT_ONE_SQL =
        "SELECT orderItemId, orderId, productId, productTitle, productDescription, productCode, " +
        "productImg, productCategory, price, quantity, totalPrice " +
        "FROM orderitems WHERE orderItemId=?";

    private static final String SELECT_ALL_SQL =
        "SELECT orderItemId, orderId, productId, productTitle, productDescription, productCode, " +
        "productImg, productCategory, price, quantity, totalPrice " +
        "FROM orderitems ORDER BY orderItemId DESC";

    private static final String SELECT_BY_ORDER_SQL =
        "SELECT orderItemId, orderId, productId, productTitle, productDescription, productCode, " +
        "productImg, productCategory, price, quantity, totalPrice " +
        "FROM orderitems WHERE orderId=? ORDER BY orderItemId ASC";

    // CREATE
    public int create(OrderItem oi) throws SQLException {
        try (Connection conn = DB.getDB().getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, safeInt(oi.getOrderId()));
            ps.setInt(2, safeInt(oi.getProductId()));
            ps.setString(3, oi.getProductTitle());
            ps.setString(4, oi.getProductDescription());
            ps.setString(5, oi.getProductCode());
            ps.setString(6, oi.getProductImg());
            ps.setString(7, oi.getProductCategory());
            ps.setInt(8, safeInt(oi.getPrice()));
            ps.setInt(9, safeInt(oi.getQuantity()));
            ps.setInt(10, safeInt(oi.getTotalPrice()));

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // new orderItemId
                }
            }
        }
        return -1; // not created
    }

    // READ
    public OrderItem getById(int orderItemId) throws SQLException {
        try (Connection conn = DB.getDB().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ONE_SQL)) {
            ps.setInt(1, orderItemId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    public List<OrderItem> listAll() throws SQLException {
        List<OrderItem> out = new ArrayList<>();
        try (Connection conn = DB.getDB().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) out.add(mapRow(rs));
        }
        return out;
    }

    public List<OrderItem> listByOrderId(int orderId) throws SQLException {
        List<OrderItem> out = new ArrayList<>();
        try (Connection conn = DB.getDB().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ORDER_SQL)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) out.add(mapRow(rs));
            }
        }
        return out;
    }

    // UPDATE
    public boolean update(OrderItem oi) throws SQLException {
        if (oi.getOrderItemId() == null) return false;
        try (Connection conn = DB.getDB().getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {

            ps.setInt(1, safeInt(oi.getOrderId()));
            ps.setInt(2, safeInt(oi.getProductId()));
            ps.setString(3, oi.getProductTitle());
            ps.setString(4, oi.getProductDescription());
            ps.setString(5, oi.getProductCode());
            ps.setString(6, oi.getProductImg());
            ps.setString(7, oi.getProductCategory());
            ps.setInt(8, safeInt(oi.getPrice()));
            ps.setInt(9, safeInt(oi.getQuantity()));
            ps.setInt(10, safeInt(oi.getTotalPrice()));
            ps.setInt(11, safeInt(oi.getOrderItemId()));

            return ps.executeUpdate() == 1;
        }
    }

    // DELETE
    public boolean delete(int orderItemId) throws SQLException {
        try (Connection conn = DB.getDB().getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, orderItemId);
            return ps.executeUpdate() == 1;
        }
    }


    private static int safeInt(Integer v) { return v == null ? 0 : v; }

    private static OrderItem mapRow(ResultSet rs) throws SQLException {
        OrderItem oi = new OrderItem();
        oi.setOrderItemId(rs.getInt("orderItemId"));
        oi.setOrderId(rs.getInt("orderId"));
        oi.setProductId(rs.getInt("productId"));
        oi.setProductTitle(rs.getString("productTitle"));
        oi.setProductDescription(rs.getString("productDescription"));
        oi.setProductCode(rs.getString("productCode"));
        oi.setProductImg(rs.getString("productImg"));
        oi.setProductCategory(rs.getString("productCategory"));
        oi.setPrice(rs.getInt("price"));
        oi.setQuantity(rs.getInt("quantity"));
        oi.setTotalPrice(rs.getInt("totalPrice"));
        return oi;
    }
}
