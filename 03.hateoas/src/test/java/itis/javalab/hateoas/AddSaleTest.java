package itis.javalab.hateoas;

import itis.javalab.hateoas.models.Businessman;
import itis.javalab.hateoas.models.Sale;
import itis.javalab.hateoas.models.Sales;
import itis.javalab.hateoas.services.SalesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class AddSaleTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalesService salesService;

    @BeforeEach
    public void setUp() {
        when(salesService.addSale(1L, 1L)).thenReturn(buildSale());
    }

    private Sale buildSale() {
        return Sale.builder().id(1L).title("New Sale").length(0)
                .sales(asList(buildSales()))
                .businessmen(asList(buildBusinessman()))
                .build();
    }



    private Businessman buildBusinessman() {
        return Businessman.builder()
                .id(1L)
                .name("AaS")
                .country("sda")
                .isActive(true)
                .build();
    }

    private Sales buildSales() {
        return Sales.builder().title("New sales").description("sss").id(1L).build();
    }

    @Test
    public void addSaleTest() throws Exception {
        mockMvc.perform(get("/sales/1/add_sale/1")).andExpect(status().isOk())
                .andDo(document("add_sales", responseFields(
                        fieldWithPath("length").description("Length")
                )));
    }
}
