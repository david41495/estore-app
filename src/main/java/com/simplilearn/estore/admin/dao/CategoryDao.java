package com.simplilearn.estore.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.Date;

import com.simplilearn.estore.db.DB;
import com.simplilearn.estore.admin.model.Category;
import com.simplilearn.estore.enduser.dao.Dao; 

public class CategoryDao implements Dao<Category> {

    private final DB db = DB.getDB();

    
    private static final String TABLE  = "categories";
    private static final String COL_ID = "category_id";
    private static final String COL_NAME = "category_name";
    private static final String COL_DESC = "category_description";
    private static final String COL_IMG  = "category_image_url";
    private static final String COL_ACTIVE = "active";
    private static final String COL_ADDED  = "added_on";

    

    private Category mapRow(ResultSet rs) throws Exception {
        Category c = new Category();
        c.setCategoryId(rs.getInt(COL_ID));
        c.setCategoryName(rs.getString(COL_NAME));
        c.setCategoryDescription(rs.getString(COL_DESC));
        c.setCategoryImageUrl(rs.getString(COL_IMG));
        c.setActive(rs.getInt(COL_ACTIVE));
        // java.sql.Date -> java.util.Date (if your model uses java.util.Date)
        Date added = rs.getDate(COL_ADDED);
        c.setAddedOn(added);
        return c;
    }

    private String q(String s) { return s == null ? "NULL" : "'" + s.replace("'", "''") + "'"; }
    private String q(Date d)   { return d == null ? "NULL" : "'" + new java.sql.Date(d.getTime()) + "'"; }
    private String q(Integer i){ return i == null ? "NULL" : String.valueOf(i); }

    // ---------- CRUD ----------

    @Override
    public Category get(long id) {
        Category cat = null;
        try {
            ResultSet rs = db.executeQuery(
                "SELECT * FROM " + TABLE + " WHERE " + COL_ID + "=" + id);
            if (rs.next()) cat = mapRow(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cat;
    }

    @Override
    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        try {
            ResultSet rs = db.executeQuery("SELECT * FROM " + TABLE);
            while (rs.next()) list.add(mapRow(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void save(Category c) {
        String sql =
            "INSERT INTO " + TABLE + " (" +
                COL_NAME + "," + COL_DESC + "," + COL_IMG + "," + COL_ACTIVE + "," + COL_ADDED +
            ") VALUES (" +
                q(c.getCategoryName()) + "," +
                q(c.getCategoryDescription()) + "," +
                q(c.getCategoryImageUrl()) + "," +
                q(c.getActive()) + "," +
                q(c.getAddedOn()) +
            ")";
        try {
            db.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category c) {
        if (c.getCategoryId() == null) return; // nothing to update without ID

        String sql =
            "UPDATE " + TABLE + " SET " +
                COL_NAME  + "=" + q(c.getCategoryName()) + "," +
                COL_DESC  + "=" + q(c.getCategoryDescription()) + "," +
                COL_IMG   + "=" + q(c.getCategoryImageUrl()) + "," +
                COL_ACTIVE+ "=" + q(c.getActive()) + "," +
                COL_ADDED + "=" + q(c.getAddedOn()) +
            " WHERE " + COL_ID + "=" + c.getCategoryId();

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
}

