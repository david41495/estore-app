package com.simplilearn.estore.admin.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.simplilearn.estore.db.DB;
import com.simplilearn.estore.admin.model.Product;
import com.simplilearn.estore.enduser.dao.Dao;   // keep your project’s Dao<T> path

public class ProductDao implements Dao<Product> {

  private final DB db = DB.getDB();

  // db names
  private static final String TABLE              = "products";
  private static final String COL_ID             = "product_id";
  private static final String COL_TITLE          = "product_title";
  private static final String COL_DESC           = "product_description";
  private static final String COL_CODE           = "product_code";
  private static final String COL_IMAGES         = "images";               // stored as comma-separated string
  private static final String COL_THUMBNAIL      = "thumbnail_image";      // map to Product.getThemnailImage()
  private static final String COL_PRICE          = "price";
  private static final String COL_ADDED_ON       = "added_on";
  private static final String COL_RATING         = "rating";


  private static String q(Object v) {
    if (v == null) return "NULL";
    if (v instanceof Number) return v.toString();
    if (v instanceof java.util.Date) {
      // Let MySQL convert string to DATE/TIMESTAMP; or use proper DATE_FORMAT if needed
      return "'" + new java.sql.Timestamp(((Date)v).getTime()).toString() + "'";
    }
    String s = v.toString().replace("'", "''");
    return "'" + s + "'";
  }

  private static String joinImages(List<String> images) {
    if (images == null || images.isEmpty()) return null;
    return String.join(",", images);
  }

  private static List<String> splitImages(String csv) {
    if (csv == null || csv.isEmpty()) return new ArrayList<>();
    return new ArrayList<>(Arrays.asList(csv.split(",")));
  }

  private Product mapRow(ResultSet rs) throws Exception {
    Product p = new Product();
    p.setProductId(rs.getInt(COL_ID));
    p.setProductTitle(rs.getString(COL_TITLE));
    p.setProductDescription(rs.getString(COL_DESC));
    p.setProductCode(rs.getString(COL_CODE));
    p.setImages(splitImages(rs.getString(COL_IMAGES)));
    p.setThumbnailImage((Integer) rs.getObject(COL_THUMBNAIL));
    p.setPrice((Integer) rs.getObject(COL_PRICE));
    p.setAddedOn(rs.getTimestamp(COL_ADDED_ON));
    p.setRating((Integer) rs.getObject(COL_RATING));
    return p;
  }

  // CRUD

  @Override
  public Product get(long id) {
    Product p = null;
    try {
      ResultSet rs = db.executeQuery(
        "SELECT * FROM " + TABLE + " WHERE " + COL_ID + " = " + id);
      if (rs.next()) p = mapRow(rs);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return p;
  }

  @Override
  public List<Product> getAll() {
    List<Product> list = new ArrayList<>();
    try {
      ResultSet rs = db.executeQuery("SELECT * FROM " + TABLE);
      while (rs.next()) list.add(mapRow(rs));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  @Override
  public void save(Product p) {
    String imagesCsv = joinImages(p.getImages());
    String sql =
      "INSERT INTO " + TABLE + " (" +
        COL_TITLE + "," +
        COL_DESC  + "," +
        COL_CODE  + "," +
        COL_IMAGES + "," +
        COL_THUMBNAIL + "," +
        COL_PRICE + "," +
        COL_ADDED_ON + "," +
        COL_RATING +
      ") VALUES (" +
        q(p.getProductTitle()) + "," +
        q(p.getProductDescription()) + "," +
        q(p.getProductCode()) + "," +
        q(imagesCsv) + "," +
        q(p.getThumbnailImage()) + "," +
        q(p.getPrice()) + "," +
        q(p.getAddedOn()) + "," +
        q(p.getRating()) +
      ")";

    try { db.executeUpdate(sql); }
    catch (Exception e) { e.printStackTrace(); }
  }

  @Override
  public void update(Product p) {
    if (p.getProductId() == null) return; // can't update without ID

    String imagesCsv = joinImages(p.getImages());
    String sql =
      "UPDATE " + TABLE + " SET " +
        COL_TITLE     + " = " + q(p.getProductTitle()) + "," +
        COL_DESC      + " = " + q(p.getProductDescription()) + "," +
        COL_CODE      + " = " + q(p.getProductCode()) + "," +
        COL_IMAGES    + " = " + q(imagesCsv) + "," +
        COL_THUMBNAIL + " = " + q(p.getThumbnailImage()) + "," +
        COL_PRICE     + " = " + q(p.getPrice()) + "," +
        COL_ADDED_ON  + " = " + q(p.getAddedOn()) + "," +
        COL_RATING    + " = " + q(p.getRating()) +
      " WHERE " + COL_ID + " = " + p.getProductId();

    try { db.executeUpdate(sql); }
    catch (Exception e) { e.printStackTrace(); }
  }

  @Override
  public void delete(long id) {
    String sql = "DELETE FROM " + TABLE + " WHERE " + COL_ID + " = " + id;
    try { db.executeUpdate(sql); }
    catch (Exception e) { e.printStackTrace(); }
  }
}

