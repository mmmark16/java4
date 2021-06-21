package com.company;

import java.sql.*;

public class DatabaseUtils {
    public static void insertEntity(Entity entity) throws SQLException {
        try (Connection c = GameServer.getConnection()) {
            String sql = "INSERT INTO entities VALUES(DEFAULT, ?, ?, NULL)";

            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getTitle());
            ps.setTimestamp(2, new Timestamp(entity.getDate_created().getTime()));

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                entity.setId(keys.getInt(1));
                return;
            }
        }
    }

    public static void insertEntityPlayer(EntityPlayer entity) throws SQLException {
        try (Connection c = GameServer.getConnection()) {
            String sql = "INSERT INTO players VALUES(DEFAULT ,?,?)";

            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getTitle());
            ps.setDouble(2, entity.getExp());

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                entity.setId(keys.getInt(1));
                return;
            }
        }
    }
    public static void Battle_logs(Entity entity, Entity entity_death) throws SQLException {
        try (Connection c = GameServer.getConnection()){
            String sql = "INSERT INTO battle_logs VALUES(DEFAUL, ?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, entity.getId());
            ps.setLong(2, entity_death.getId());
            ps.setTimestamp(3, new Timestamp(entity.getDate_death().getTime()));

            ps.executeUpdate();
        }
    }

    public static void update(Entity entity) throws SQLException {
        try (Connection c = GameServer.getConnection()) {
            String sql = "UPDATE entities SET date_death = ? WHERE id_entity = ?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setTimestamp(1, new Timestamp(entity.getDate_death().getTime()));
            ps.setLong(2, entity.getId());

            ps.executeUpdate();
        }
    }
}
