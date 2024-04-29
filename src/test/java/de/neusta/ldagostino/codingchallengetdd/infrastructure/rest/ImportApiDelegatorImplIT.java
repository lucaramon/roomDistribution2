package de.neusta.ldagostino.codingchallengetdd.infrastructure.rest;

import de.neusta.ldagostino.codingchallengetdd.application.ImportService;
import de.neusta.ldagostino.codingchallengetdd.generated.api.ImportApi;
import de.neusta.ldagostino.codingchallengetdd.generated.api.ImportApiController;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.ErrorHandler;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.rest.ImportApiDelegatorImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ImportApi.class)
@Import({ErrorHandler.class, ImportApiController.class, ImportApiDelegatorImpl.class})
public class ImportApiDelegatorImplIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ImportService importService;

    @Test
    void importDataWithFileTest() throws Exception {

        MockMultipartFile multipartFile = new MockMultipartFile("file", "sitzplan.csv", MediaType.TEXT_PLAIN_VALUE, "".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/import")
                        .file(multipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
