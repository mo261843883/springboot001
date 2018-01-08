package controllerTets;

import com.example.springboot001.Springboot001Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Springboot001Application.class)
@AutoConfigureMockMvc
public class TestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testString() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/test/getParam").param("str","11111","asdasd")).andExpect((MockMvcResultMatchers.status().isOk()));
    }
}
