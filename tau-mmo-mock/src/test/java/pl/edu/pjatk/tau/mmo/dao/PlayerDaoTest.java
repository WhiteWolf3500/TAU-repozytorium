package pl.edu.pjatk.tau.mmo.dao;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import pl.edu.pjatk.tau.mmo.domain.Player;
import java.sql.*;
import static org.hamcrest.CoreMatchers.*;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@RunWith(JUnit4.class)
public class PlayerDaoTest {
    private static final Logger LOGGER = Logger.getLogger(PlayerDaoTest.class.getCanonicalName());
    Random random;
    PlayerDao playerManager;
    static List<Player> initialDatabaseState;

    abstract class AbstractResultSet implements ResultSet {
        int i;

        @Override
        public long getLong(String s) throws SQLException {
            return initialDatabaseState.get(i-1).getId();
        }
        @Override
        public String getString(String s) throws SQLException {
            if(s=="name") {
                return initialDatabaseState.get(i-1).getName();
            } else if (s=="heroclass") {
                return initialDatabaseState.get(i-1).getHeroClass();
            } else {
                return "0";
            }
        }
        @Override
        public int getInt(String s) throws SQLException {
            if (s=="level") {
                return initialDatabaseState.get(i-1).getLevel();
            } else {
                return 0;
            }
        }
 
        @Override
        public boolean next() throws SQLException {
            i++;
            if (i > initialDatabaseState.size())
                return false;
            else
                return true;
        }
    }

    @Mock
    Connection connection;
    @Mock
    PreparedStatement selectStatementMock;
    @Mock
    PreparedStatement insertStatementMock;
    @Mock
    PreparedStatement updateStatementMock;
    @Mock
    PreparedStatement deleteStatementMock;

    @Before
    public void setup() throws SQLException {
        
        random = new Random();
        initialDatabaseState = new LinkedList<>();
        for (int i = 0; i < 10;i++) {
            Player player = new Player();
            player.setId(i);
            player.setName("Name"+random.nextInt(1000));
            player.setLevel(random.nextInt(1000));
            player.setHeroClass("Archer class "+random.nextInt(1000));
            initialDatabaseState.add(player);
        }
        Mockito.when(connection.prepareStatement("SELECT id, name, level, heroclass FROM Player ORDER BY id")).thenReturn(selectStatementMock);
        Mockito.when(connection.prepareStatement("INSERT INTO Player (name, level, heroclass) VALUES (?, ?, ?)",Statement.RETURN_GENERATED_KEYS)).thenReturn(insertStatementMock);
        Mockito.when(connection.prepareStatement("UPDATE Player SET name = ?, level = ?, heroclass = ? WHERE id = ?",Statement.RETURN_GENERATED_KEYS)).thenReturn(updateStatementMock);
        Mockito.when(connection.prepareStatement("DELETE FROM Player WHERE id = ?",Statement.RETURN_GENERATED_KEYS)).thenReturn(deleteStatementMock);
    }

    @Test
    public void setConnectionCheck() throws SQLException {
        PlayerDaoJdbcImpl dao = new PlayerDaoJdbcImpl();
        dao.setConnection(connection);
        assertNotNull(dao.getConnection());
        assertEquals(dao.getConnection(), connection);
    }

    @Test
    public void setConnectionCreatesQueriesCheck() throws SQLException {
        PlayerDaoJdbcImpl dao = new PlayerDaoJdbcImpl();
        dao.setConnection(connection);
        assertNotNull(dao.getAllPlayersStmt);
        Mockito.verify(connection).prepareStatement("SELECT id, name, level, heroclass FROM Player ORDER BY id");
    }

    // @Test
    // public void checkAdding() throws Exception {
    //     Player player = new Player();
    //     player.setName("Adam");
    //     player.setLevel(39);
    //     player.setHeroClass("Archer tier 1");

    //     assertEquals(1, playerManager.addPlayer(player));

    //     expectedDbState.add(player);
    //     assertThat(playerManager.getAllPlayers(), equalTo(expectedDbState));
    // }

    // @Test
    // public void checkGetting() throws Exception {
    //     Player player = expectedDbState.get(5);
    //     assertEquals(player, playerManager.getPlayer(player.getId()));
    // }

    // @Test
    // public void checkDeletingSuccess() {
    //     Player player = expectedDbState.get(3);
    //     expectedDbState.remove(player);
    //     assertEquals(1, playerManager.deletePlayer(player));
    //     assertThat(playerManager.getAllPlayers(), equalTo(expectedDbState));
    // }

    // @Test(expected = SQLException.class)
    // public void checkDeletingFailure() throws SQLException {
    //     Player player = expectedDbState.get(3);
    //     expectedDbState.remove(player);
    //     playerManager.deletePlayer(player);
    //     playerManager.deletePlayer(player);
    // }

    // @Test
    // public void checkUpdatingSuccess() throws SQLException {
    //     Player player = expectedDbState.get(3);
    //     player.setName("Tyrael");
    //     player.setLevel(99);
    //     player.setHeroClass("Paladin tier 9");
    //     expectedDbState.set(3, player);
    //     assertEquals(1, playerManager.updatePlayer(player));
    //     assertThat(playerManager.getAllPlayers(), equalTo(expectedDbState));
    // }

    // @Test(expected = SQLException.class)
    // public void checkUpdatingFailure() throws SQLException {
    //     Player player = new Player("test", 10, "test");
    //     assertEquals(1, playerManager.updatePlayer(player));
    // }

}