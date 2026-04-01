package com.simplilearn.estore.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import com.simplilearn.estore.db.DB;
import com.simplilearn.estore.admin.model.User;
// If your Dao<T> interface lives elsewhere, adjust this import:
import com.simplilearn.estore.enduser.dao.Dao;
//import com.simplilearn.estore.enduser.dao;

public class UserDao implements Dao<User> {

    private final DB db = DB.getDB();


    private static final String TABLE  = "users";
    private static final String COL_ID = "user_id";

    // CRUD

    @Override
    public User get(long id) {
        User user = null;
        try {
            ResultSet rs = db.executeQuery(
                "SELECT " + COL_ID + ", street, city, state, country, pincode " +
                "FROM " + TABLE + " WHERE " + COL_ID + "=" + id
            );
            if (rs.next()) {
                user = mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try {
            ResultSet rs = db.executeQuery(
                "SELECT " + COL_ID + ", street, city, state, country, pincode FROM " + TABLE
            );
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void save(User u) {

        String sql =
            "INSERT INTO " + TABLE + " (street, city, state, country, pincode) VALUES (" +
                q(u.getStreet())  + ", " +
                q(u.getCity())    + ", " +
                q(u.getState())   + ", " +
                q(u.getCountry()) + ", " +
                n(u.getPincode()) +
            ")";
        try {
            db.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User u) {
        if (u.getUserId() == null) return;

        String sql =
            "UPDATE " + TABLE + " SET " +
            "street="  + q(u.getStreet())  + ", " +
            "city="    + q(u.getCity())    + ", " +
            "state="   + q(u.getState())   + ", " +
            "country=" + q(u.getCountry()) + ", " +
            "pincode=" + n(u.getPincode()) +
            " WHERE " + COL_ID + "=" + u.getUserId();

        try {
            db.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try {
            db.executeUpdate("DELETE FROM " + TABLE + " WHERE " + COL_ID + "=" + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helpers

    private User mapRow(ResultSet rs) throws Exception {
        User u = new User();
        // Match these to actual column names:
        u.setUserId(rs.getLong(COL_ID));
        u.setStreet(rs.getString("street"));
        u.setCity(rs.getString("city"));
        u.setState(rs.getString("state"));
        u.setCountry(rs.getString("country"));
        Object pin = rs.getObject("pincode");
        u.setPincode(pin == null ? null : ((Number) pin).intValue());
        return u;
    }


    private String q(String s) {
        return (s == null) ? "NULL" : "'" + s.replace("'", "''") + "'";
    }


    private String n(Number n) {
        return (n == null) ? "NULL" : n.toString();
    }
}
