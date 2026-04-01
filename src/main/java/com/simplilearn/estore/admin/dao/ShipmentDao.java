package com.simplilearn.estore.admin.dao;

import com.simplilearn.estore.db.DB;
import com.simplilearn.estore.admin.model.Shipment;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShipmentDao {

    // SQL
    private static final String INSERT_SQL =
        "INSERT INTO shipments (shipmentStatus, shipmentTitle, shipmentDate, shipmentMethod, shipmentCompany) " +
        "VALUES (?,?,?,?,?)";

    private static final String UPDATE_SQL =
        "UPDATE shipments SET shipmentStatus=?, shipmentTitle=?, shipmentDate=?, " +
        "shipmentMethod=?, shipmentCompany=? WHERE shipmentId=?";

    private static final String DELETE_SQL =
        "DELETE FROM shipments WHERE shipmentId=?";

    private static final String SELECT_ONE_SQL =
        "SELECT shipmentId, shipmentStatus, shipmentTitle, shipmentDate, shipmentMethod, shipmentCompany " +
        "FROM shipments WHERE shipmentId=?";

    private static final String SELECT_ALL_SQL =
        "SELECT shipmentId, shipmentStatus, shipmentTitle, shipmentDate, shipmentMethod, shipmentCompany " +
        "FROM shipments ORDER BY shipmentId DESC";

    private static final String SELECT_BY_STATUS_SQL =
        "SELECT shipmentId, shipmentStatus, shipmentTitle, shipmentDate, shipmentMethod, shipmentCompany " +
        "FROM shipments WHERE shipmentStatus=? ORDER BY shipmentId DESC";

    // CREATE
    /** @return generated shipmentId, or -1 if not created */
    public int create(Shipment s) throws SQLException {
        try (Connection conn = DB.getDB().getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, safeInt(s.getShipmentStatus()));
            ps.setString(2, s.getShipmentTitle());
            // if your column is DATETIME/TIMESTAMP use setTimestamp; if DATE use setDate
            setDateOrTimestamp(ps, 3, s.getShipmentDate());
            ps.setString(4, s.getShipmentMethod());
            ps.setString(5, s.getShipmentCompany());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    // READ
    public Shipment getById(int shipmentId) throws SQLException {
        try (Connection conn = DB.getDB().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ONE_SQL)) {

            ps.setInt(1, shipmentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    public List<Shipment> listAll() throws SQLException {
        List<Shipment> out = new ArrayList<>();
        try (Connection conn = DB.getDB().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) out.add(mapRow(rs));
        }
        return out;
    }

    public List<Shipment> listByStatus(int status) throws SQLException {
        List<Shipment> out = new ArrayList<>();
        try (Connection conn = DB.getDB().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_STATUS_SQL)) {
            ps.setInt(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) out.add(mapRow(rs));
            }
        }
        return out;
    }

    // UPDATE
    public boolean update(Shipment s) throws SQLException {
        if (s.getShipmentId() == null) return false;
        try (Connection conn = DB.getDB().getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {

            ps.setInt(1, safeInt(s.getShipmentStatus()));
            ps.setString(2, s.getShipmentTitle());
            setDateOrTimestamp(ps, 3, s.getShipmentDate());
            ps.setString(4, s.getShipmentMethod());
            ps.setString(5, s.getShipmentCompany());
            ps.setInt(6, s.getShipmentId());

            return ps.executeUpdate() == 1;
        }
    }

    // DELETE
    public boolean delete(int shipmentId) throws SQLException {
        try (Connection conn = DB.getDB().getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {

            ps.setInt(1, shipmentId);
            return ps.executeUpdate() == 1;
        }
    }

    // Helpers
    private static int safeInt(Integer v) { return v == null ? 0 : v; }

    private static void setDateOrTimestamp(PreparedStatement ps, int idx, Date utilDate) throws SQLException {
        if (utilDate == null) {
            ps.setTimestamp(idx, null);
            return;
        }

        ps.setTimestamp(idx, new Timestamp(utilDate.getTime()));

    }

    private static Shipment mapRow(ResultSet rs) throws SQLException {
        Shipment s = new Shipment();
        s.setShipmentId(rs.getInt("shipmentId"));
        s.setShipmentStatus(rs.getInt("shipmentStatus"));
        s.setShipmentTitle(rs.getString("shipmentTitle"));

        Timestamp ts = rs.getTimestamp("shipmentDate");
        s.setShipmentDate(ts == null ? null : new Date(ts.getTime()));


        s.setShipmentMethod(rs.getString("shipmentMethod"));
        s.setShipmentCompany(rs.getString("shipmentCompany"));
        return s;
    }
}

