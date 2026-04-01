package com.simplilearn.estore.admin.dao;

import java.sql.ResultSet; 
import java.util.ArrayList;
import java.util.List;

import com.simplilearn.estore.admin.model.Admin;
import com.simplilearn.estore.db.DB;
import com.simplilearn.estore.enduser.dao.Dao;

public class AdminDao implements Dao<Admin> {

    private final DB db = DB.getDB();


    
    private Admin mapRow(ResultSet rs) throws Exception {
        Admin a = new Admin();
        a.setAdminId(rs.getInt("adminId"));
        a.setFullName(rs.getString("fullName"));
        a.setEmail(rs.getString("email"));
        a.setPassword(rs.getString("password"));
        a.setLoginType(rs.getInt("loginType"));
        a.setAddedOn(rs.getDate("addedOn"));
        return a;
    }

    
    private String q(String s) {
        if (s == null) return "NULL";
        return "'" + s.replace("'", "''") + "'";
    }

   
    private String q(java.util.Date date) {
        if (date == null) return "NULL";
        return "'" + new java.text.SimpleDateFormat("yyyy-MM-dd").format(date) + "'";
    }

   

    @Override
    public Admin get(long id) {
        Admin admin = null;
        try {
            String sql = "SELECT * FROM admins WHERE adminId=" + id;
            ResultSet rs = db.executeQuery(sql);
            if (rs.next()) {
                admin = mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> admins = new ArrayList<>();
        try {
            String sql = "SELECT * FROM admins ORDER BY adminId";
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                admins.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public void save(Admin a) {
        try {
            // If adminId is auto-increment, omit it here
            String sql =
                "INSERT INTO admins (fullName, email, password, loginType, addedOn) VALUES (" +
                    q(a.getFullName()) + ", " +
                    q(a.getEmail()) + ", " +
                    q(a.getPassword()) + ", " +
                    (a.getLoginType() == null ? "NULL" : a.getLoginType()) + ", " +
                    q(a.getAddedOn()) +
                ")";
            db.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Admin a) {
        try {
            // Requires a non-null adminId to target the row
            String sql =
                "UPDATE admins SET " +
                    "fullName="  + q(a.getFullName()) + ", " +
                    "email="     + q(a.getEmail())    + ", " +
                    "password="  + q(a.getPassword()) + ", " +
                    "loginType=" + (a.getLoginType() == null ? "NULL" : a.getLoginType()) + ", " +
                    "addedOn="   + q(a.getAddedOn()) +
                " WHERE adminId=" + a.getAdminId();
            db.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try {
            String sql = "DELETE FROM admins WHERE adminId=" + id;
            db.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
