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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        "DELETE FROM Office;",
        "DELETE FROM Organization;",
        "INSERT INTO Organization (id, name, full_name, inn, kpp, address, phone, is_active, version) VALUES " +
                "(1, 'OOO Вектор', 'Общество с ограниченной ответственностью \"Вектор\"', '1171174545', '775782780', 'Россия, г.Уфа, ул. Кислотная, д.45', '+7 347 228 69 36', 'true', 0)," +
                "(2, 'ИП Сакаев И.Н.', 'Индивидуальный предприниматель Сакаев Ильдар Наилевич', '0278030578', '145102789', 'Россия, г.Уфа, бульвар Ибрагимова, 99', '+7 347 66 66 66', 'true', 0)," +
                "(3, 'ООО Скорбим', 'Общество с ограниченной ответственностью \"Скорбим\"', '640789451', '789741753', 'Россия, г.Уфа, ул. Фронтовых бригад, д.1', '+7 347 88 89 88', 'true', 0);",
        "INSERT INTO Office (id, org_id, name, address, phone, is_active, version) VALUES " +
                "(1, 1, 'Головной офис', 'Россия, г.Уфа, ул. Айская, д.59', '+7 347 224 56 62', true, 0)," +
                "(2, 1, 'Производственная база', 'Россия, г.Уфа, Соединительное шоссе, д.1', '+7 347 218 77 77', true, 0)," +
                "(3, 2, 'Розничный магазин \"Свадьба102\"', 'Россия, г.Уфа, проспект Октября, д.70', '+7 347 234 51 10', true, 0)," +
                "(5, 3, 'Дополнительный офис \"Небеса\"', 'Россия, г.Санкт-Петербург, ул.Площадь Восстания, д.12', '+7 812 322 19 10', true, 0)," +
                "(6, 3, 'Оптовый центр продаж', 'Россия, г.Уфа, ул. Рижская, д.5', '+7 347 218 34 32', false, 0);"
})
@AutoConfigureMockMvc
public class OfficeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testList() throws Exception {
        String jsonBodyFilter = "{\"orgId\": 1}";
        mockMvc.perform(post("/api/office/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonBodyFilter))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].name", is("Головной офис")))
                .andExpect(jsonPath("$.data[0].isActive", is(true)))
                .andExpect(jsonPath("$.data[1].id", is(2)))
                .andExpect(jsonPath("$.data[1].name", is("Производственная база")))
                .andExpect(jsonPath("$.data[1].isActive", is(true)));
    }

    @Test
    public void testListError() throws Exception {
        String jsonBodyFilter = "{\"orgId\": 300}";
        mockMvc.perform(post("/api/office/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonBodyFilter))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(0)));
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get("/api/office/3"))
                .andExpect(status().isOk())
                .andExpect((MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8)))
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.id", is(3)))
                .andExpect(jsonPath("$.data.name", is("Розничный магазин \"Свадьба102\"")))
                .andExpect(jsonPath("$.data.address", is("Россия, г.Уфа, проспект Октября, д.70")))
                .andExpect(jsonPath("$.data.phone", is("+7 347 234 51 10")))
                .andExpect(jsonPath("$.data.isActive", is(true)));
    }

    @Test
    public void testGetByIdError() throws Exception {
        mockMvc.perform(get("/api/office/20"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error", notNullValue()))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testUpdate() throws Exception {
        String jsonBodyFilter = "{\"id\": 6, \"name\": \"ООО Оптовый центр продаж\", \"address\": \"Россия, г.Уфа, ул. Рижская, д.5\"}";
        mockMvc.perform(post("/api/office/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonBodyFilter))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("success")));
    }

    @Test
    public void testUpdateError() throws Exception {
        String jsonBodyFilter = "{\"id\": 666, \"name\": \"ООО Оптовый центр продаж\", \"address\": \"Россия, г.Уфа, ул. Рижская, д.5\"}";
        mockMvc.perform(post("/api/office/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonBodyFilter))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    @Test
    public void testSave() throws Exception {
        String jsonBodyFilter = "{\"orgId\": 3, \"name\": \"ООО Олимп\", \"address\": \"Россия, г.Москва, ул. Победы, д.666\"}";
        mockMvc.perform(post("/api/office/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonBodyFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.result", is("success")));
    }

    @Test
    public void testSaveError() throws Exception {
        String jsonBodyFilter = "{\"name\": \"ООО Олимп\", \"address\": \"Россия, г.Москва, ул. Победы, д.666\"}";
        mockMvc.perform(post("/api/office/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonBodyFilter))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error", notNullValue()));
    }
}
