package tau.mmo.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tau.mmo.domain.Player;
import tau.mmo.service.PlayerManager;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/*
This example uses MockMvc to mock server and http requests. This way we don't need to run real server.
We can also use Mockito to mock access to database.
This is bullshit
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerManager service;

    @Test
    public void contextLoads() throws Exception {
        assertNotNull(mockMvc);
    }

    @Test
    public void greetingShouldReturnHelloMessage() throws Exception {
        this.mockMvc.perform(get("/"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello")));
    }


    @Test
    public void getAllShouldReturnEmptyResults() throws Exception {
        when(service.findAllPlayers()).thenReturn(new LinkedList<Player>());
        this.mockMvc.perform(get("/players"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void getAllShouldReturnSomeResults() throws Exception {
        List<Player> expectedResult = new LinkedList<Player>();
        Player np = new Player();
        np.setId(123l);
        np.setName("Waclawa");
        expectedResult.add(np);
        when(service.findAllPlayers()).thenReturn(expectedResult);
        this.mockMvc.perform(get("/players"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":123,\"Name\":\"Waclawa\"}]"));
    }
    @Test
    public void postNewPlayerShouldReallyAddItToDatabase() throws Exception {
        Player p = new Player();
        p.setName("Nowislaw");
        when(service.addPlayer(p)).thenReturn(2l);
        this.mockMvc.perform(post("/player")
                    .content("{\"Name\":\"Nowislaw\"}")
                    .contentType("application/json"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Nowislaw")))
                .andExpect(content().string(containsString("\"id\":2")));
        p.setId(2l);
        verify(service).addPlayer(p);
    }
}