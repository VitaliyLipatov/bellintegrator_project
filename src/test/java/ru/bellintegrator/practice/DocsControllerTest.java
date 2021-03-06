package ru.bellintegrator.practice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
@Sql(statements = {
        "DELETE FROM User;",
        "DELETE FROM Doc;",
        "DELETE FROM Doc_Type;",
        "INSERT INTO Doc_Type (id, name, code,) VALUES " +
                "(1, 'Паспорт гражданина РФ', 21)," +
                "(2, 'Удостоверение беженца', 13);",
})
@AutoConfigureMockMvc
public class DocsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testList() throws Exception {
        mockMvc.perform(post("/api/docs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].code", is("21")))
                .andExpect(jsonPath("$.data[0].name", is("Паспорт гражданина РФ")))
                .andExpect(jsonPath("$.data[1].code", is("13")))
                .andExpect(jsonPath("$.data[1].name", is("Удостоверение беженца")));
    }
}
