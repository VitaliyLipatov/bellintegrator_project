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
        "DELETE FROM Doc;",
        "DELETE FROM Doc_Type;",
        "DELETE FROM Office;",
        "DELETE FROM Organization;",
        "DELETE FROM Country;",
        "INSERT INTO Organization (id, name, full_name, inn, kpp, address, phone, is_active, version) VALUES " +
                "(1, 'OOO Вектор', 'Общество с ограниченной ответственностью \"Вектор\"', '1171174545', '775782780', 'Россия, г.Уфа, ул. Кислотная, д.45', '+7 347 228 69 36', 'true', 0)," +
                "(2, 'ИП Сакаев И.Н.', 'Индивидуальный предприниматель Сакаев Ильдар Наилевич', '0278030578', '145102789', 'Россия, г.Уфа, бульвар Ибрагимова, 99', '+7 347 66 66 66', 'true', 0);",
        "INSERT INTO Office (id, org_id, name, address, phone, is_active, version) VALUES " +
                "(1, 1, 'Головной офис', 'Россия, г.Уфа, ул. Айская, д.59', '+7 347 224 56 62', true, 0)," +
                "(2, 1, 'Производственная база', 'Россия, г.Уфа, Соединительное шоссе, д.1', '+7 347 218 77 77', true, 0)," +
                "(3, 2, 'Розничный магазин \"Свадьба102\"', 'Россия, г.Уфа, проспект Октября, д.70', '+7 347 234 51 10', true, 0);",
        "INSERT INTO Doc_Type (id, name, code) VALUES " +
                "(1, 'Паспорт гражданина РФ', '21')," +
                "(2, 'Удостоверение беженца', '13')," +
                "(3, 'Вид на жительство в РФ', '12')," +
                "(4, 'Паспорт иностранного гражданина', '10');",
        "INSERT INTO Doc (id, type_id, number, date) VALUES " +
                "(7, 1, '8000 000001', '2017-04-11')," +
                "(8, 4, '8123 010001', '2015-11-30')," +
                "(9, 1, '8010 110001', '2010-01-31')," +
                "(10, 1, '8010 110077', '2012-04-30')," +
                "(11, 1, '8011 110077', '2013-04-30')," +
                "(12, 1, '8000 119997', '2009-04-22');",
        "INSERT INTO Country (id, name, code) VALUES " +
                "(1, 'Российская Федерация', '643')," +
                "(2, 'Мадагаскар', '123')," +
                "(3, 'Кипр', '399');",
        "INSERT INTO User (id, office_id, doc_id, country_id, first_name, second_name, middle_name, position, phone, is_identified, version) VALUES " +
                "(1, 1, 12, 1, 'Артем', 'Андреев', 'Петрович', 'Генеральный директор', '89173451211', 'true', 0)," +
                "(2, 1, 11, 1, 'Снежана', 'Семенова', 'Денисовна', 'Заведующая хозяйством', '89871000111', 'true', 0)," +
                "(3, 2, 10, 1, 'Владислав', 'Ильмурзин', 'Владимирович', 'Водитель-экспедитор', '89093508878', 'true', 0)," +
                "(4, 2, 9, 2, 'Сергей', 'Семенов', 'Браниславович', 'Продавец', '89111234567', 'true', 0)," +
                "(5, 3,  8, 1, 'Остап', 'Ибрагимович', 'Наилевич', 'Начальник отдела продаж', '89059054334', 'true', 0)," +
                "(6, 3, 7, 3, 'Андрей', 'Ильин', 'Сергеевич', 'Кладовщик', '89131391313', 'true', 0);"
})
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testList() throws Exception {
        String jsonBodyFilter = "{\"officeId\":2}";
        mockMvc.perform(post("/api/user/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonBodyFilter))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id", is(3)))
                .andExpect(jsonPath("$.data[0].firstName", is("Владислав")))
                .andExpect(jsonPath("$.data[0].secondName", is("Ильмурзин")))
                .andExpect(jsonPath("$.data[0].middleName", is("Владимирович")))
                .andExpect(jsonPath("$.data[0].position", is("Водитель-экспедитор")))
                .andExpect(jsonPath("$.data[1].id", is(4)))
                .andExpect(jsonPath("$.data[1].firstName", is("Сергей")))
                .andExpect(jsonPath("$.data[1].secondName", is("Семенов")))
                .andExpect(jsonPath("$.data[1].middleName", is("Браниславович")))
                .andExpect(jsonPath("$.data[1].position", is("Продавец")));
    }

    @Test
    public void testListError() throws Exception {
        String jsonBodyFilter = "{\"officeId\": 15}";
        mockMvc.perform(post("/api/user/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonBodyFilter))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(0)));
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect((MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8)))
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.firstName", is("Артем")))
                .andExpect(jsonPath("$.data.secondName", is("Андреев")))
                .andExpect(jsonPath("$.data.middleName", is("Петрович")))
                .andExpect(jsonPath("$.data.position", is("Генеральный директор")))
                .andExpect(jsonPath("$.data.docName", is("Паспорт гражданина РФ")))
                .andExpect(jsonPath("$.data.docNumber", is("8000 119997")))
                .andExpect(jsonPath("$.data.docDate", is("2009-04-22")))
                .andExpect(jsonPath("$.data.citizenshipName", is("Российская Федерация")))
                .andExpect(jsonPath("$.data.citizenshipCode", is("643")))
                .andExpect(jsonPath("$.data.isIdentified", is(true)));
    }

    @Test
    public void testGetByIdError() throws Exception {
        mockMvc.perform(get("/api/user/100"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error", notNullValue()))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testUpdate() throws Exception {
        String jsonBodyFilter = "{\"id\": 1, \"firstName\": \"Артем\", \"position\": \"Генеральный директор\", \"citizenshipCode\": \"399\", \"isIdentified\": true}";
        mockMvc.perform(post("/api/user/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonBodyFilter))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("success")));
    }

    @Test
    public void testUpdateError() throws Exception {
        String jsonBodyFilter = "{\"id\": 0, \"firstName\": \"Артем\", \"position\": \"Генеральный директор\", \"citizenshipCode\": \"399\", \"isIdentified\": true}";
        mockMvc.perform(post("/api/user/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonBodyFilter))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    @Test
    public void testSave() throws Exception {
        String jsonBodyFilter = "{\"officeId\": 3, \"firstName\": \"Василий\", \"position\": \"Грузчик\", \"citizenshipCode\": \"643\", \"isIdentified\": true}";
        mockMvc.perform(post("/api/user/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonBodyFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.result", is("success")));
    }

    @Test
    public void testSaveError() throws Exception {
        String jsonBodyFilter = "{\"officeId\": 3, \"firstName\": \"Вас**илий\"}";
        mockMvc.perform(post("/api/office/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonBodyFilter))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error", notNullValue()));
    }
}
