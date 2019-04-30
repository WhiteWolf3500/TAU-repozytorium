package pl.edu.pjatk.tau.mmo.dao;

import pl.edu.pjatk.tau.mmo.dao.PlayerDao;
import pl.edu.pjatk.tau.mmo.domain.Player;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PlayerDaoJdbcImpl implements PlayerDao {

    public PreparedStatement addPlayerStmt;
    public PreparedStatement getAllPlayersStmt;
    public PreparedStatement deletePlayerStmt;
    public PreparedStatement getPlayerStmt;
    public PreparedStatement updatePlayerStmt;

    Connection connection;

    @Override
    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        getAllPlayersStmt = connection.prepareStatement(
                "SELECT id, name, level, heroclass FROM Player ORDER BY id");
        addPlayerStmt= connection.prepareStatement(
                "INSERT INTO Player (name, level, heroclass) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        updatePlayerStmt= connection.prepareStatement(
                "UPDATE Player SET name = ?, level = ?, heroclass = ? WHERE id = ?",
                Statement.RETURN_GENERATED_KEYS);
        deletePlayerStmt= connection.prepareStatement(
                "DELETE FROM Player WHERE id = ?",
                Statement.RETURN_GENERATED_KEYS);
    }

    @Override
    public int addPlayer(Player player) {
        int count = 0;
        try {
            addPlayerStmt.setString(1, player.getName());
            addPlayerStmt.setInt(2, player.getLevel());
            addPlayerStmt.setString(3, player.getHeroClass());
            count = addPlayerStmt.executeUpdate();
            ResultSet generatedKeys = addPlayerStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                player.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage() + "\n" + e.getStackTrace().toString());
        }
        return count;
    }

    public List<Player> getAllPlayers() {
        List<Player> players = new LinkedList<>();
        try {
            ResultSet rs = getAllPlayersStmt.executeQuery();

            while (rs.next()) {
                Player p = new Player();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setLevel(rs.getInt("level"));
                p.setHeroClass(rs.getString("heroclass"));
                players.add(p);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage() + "\n" + e.getStackTrace().toString());
        }
        return players;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public int deletePlayer(Player player) throws SQLException {
        try {
            deletePlayerStmt.setLong(1, player.getId());
            if (deletePlayerStmt.executeUpdate() == 0) throw new SQLException("can't delete");
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage() + "\n" + e.getStackTrace().toString());
        }
        return 1;
    }

    @Override
    public int updatePlayer(Player player) throws SQLException {
        int count = 0;
        try {
            updatePlayerStmt.setString(1, player.getName());
            updatePlayerStmt.setInt(2, player.getLevel());
            updatePlayerStmt.setString(3, player.getHeroClass());
            if (player.getId() != null) {
                updatePlayerStmt.setLong(4, player.getId());
            } else {
                updatePlayerStmt.setLong(4, -1);
            }
            count = updatePlayerStmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage() + "\n" + e.getStackTrace().toString());
        }
        if (count <= 0)
            throw new SQLException("Player not found for update");
        return count;
    }

    @Override
    public Player getPlayer(long id) throws SQLException {
        try {
            getPlayerStmt.setLong(1, id);
            ResultSet rs = getPlayerStmt.executeQuery();

            if (rs.next()) {
                Player p = new Player();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setLevel(rs.getInt("level"));
                p.setHeroClass(rs.getString("heroclass"));
                return p;
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage() + "\n" + e.getStackTrace().toString());
        }
        throw new SQLException("Player with id " + id + " does not exist");
    }
}