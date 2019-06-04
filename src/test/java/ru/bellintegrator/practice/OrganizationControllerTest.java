package ru.bellintegrator.practice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
@Sql(statements = {
        "DELETE FROM User;",
        "DELETE FROM Office;",
        "DELETE FROM Organization;",
        "INSERT INTO Organization (id, name, full_name, inn, kpp, address, phone, is_active, version) VALUES " +
                "(1, 'ООО Вектор', 'Общество с ограниченной ответственностью \"Вектор\"', '1171174545', '775782780', 'Россия, г.Уфа, ул. Кислотная, д.45', '+7 347 228 69 36', 'true', 0)," +
                "(2, 'ИП Сакаев И.Н.', 'Индивидуальный предприниматель Сакаев Ильдар Наилевич', '0278030578', '145102789', 'Россия, г.Уфа, бульвар Ибрагимова, 99', '+7 347 66 66 66', 'true', 0)," +
                "(3, 'ООО Скорбим', 'Общество с ограниченной ответственностью Скорбим', '0123456789', '123456789', 'Россия, г.Уфа, ул. Фронтовых бригад, д.1', '+7 347 88 89 88', 'true', 0);"
})
@AutoConfigureMockMvc
public class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testList() throws Exception {
        String jsonBodyFilter = "{\"name\": \"ИП Сакаев И.Н.\"}";
        mockMvc.perform(post("/api/organization/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonBodyFilter))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is(2)))
                .andExpect(jsonPath("$.data[0].name", is("ИП Сакаев И.Н.")))
                .andExpect(jsonPath("$.data[0].isActive", is(true)));
    }

    @Test
    public void testListError() throws Exception {
        String jsonBodyFilter = "{\"name\": \"Абракадабра\"}";
        mockMvc.perform(post("/api/organization/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonBodyFilter))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(0)));
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get("/api/organization/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.name", is("ООО Вектор")))
                .andExpect(jsonPath("$.data.fullName", is("Общество с ограниченной ответственностью \"Вектор\"")))
                .andExpect(jsonPath("$.data.inn", is("1171174545")))
                .andExpect(jsonPath("$.data.kpp", is("775782780")))
                .andExpect(jsonPath("$.data.address", is("Россия, г.Уфа, ул. Кислотная, д.45")))
                .andExpect(jsonPath("$.data.phone", is("+7 347 228 69 36")))
                .andExpect(jsonPath("$.data.isActive", is(true)));
    }

    @Test
    public void testGetByIdError() throws Exception {
        mockMvc.perform(get("/api/organization/5"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error", notNullValue()))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testUpdate() throws Exception {
        String jsonBodyFilter = "{\"id\": 3, \"name\": \"ООО Скорбим\",\"fullName\": \"Общество с ограниченной ответственностью Скорбим\", \"inn\": \"0123456789\", " +
                "\"kpp\": \"123456789\", \"address\": \"Россия, г.Уфа, ул. Рижская, д.5\"}";
        mockMvc.perform(post("/api/organization/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonBodyFilter))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("success")));
    }

    @Test
    public void testUpdateError() throws Exception {
        String jsonBodyFilter = "{\"id\": 3, \"name\": \"ООО Скорбим\",\"fullName\": \"Общество с ограниченной ответственностью Скорбим\", \"inn\": \"0123456789\", " +
                "\"kpp\": \"123456789\"}";
        mockMvc.perform(post("/api/organization/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonBodyFilter))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    @Test
    public void testSave() throws Exception {
        String jsonBodyFilter = "{\"name\": \"ОАО Уралсиб\",\"fullName\": \"Открытое акционерно общество Уралсиб\", \"inn\": \"0123455559\", " +
                "\"kpp\": \"012345678\", \"address\": \"Россия, г.Москва, ул. Ефремова, д.8\", \"isActive\": true}";
        mockMvc.perform(post("/api/organization/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonBodyFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.result", is("success")));
    }

    @Test
    public void testSaveError() throws Exception {
        String jsonBodyFilter = "{\"name\": \"ОАО Уралсиб\",\"fullName\": \"Открытое акционерно общество Уралсиб\", \"inn\": \"0123455559\"}";
        mockMvc.perform(post("/api/organization/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonBodyFilter))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error", notNullValue()));
    }
}
