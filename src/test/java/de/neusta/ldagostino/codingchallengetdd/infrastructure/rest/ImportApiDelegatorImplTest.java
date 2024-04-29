package de.neusta.ldagostino.codingchallengetdd.infrastructure.rest;

import de.neusta.ldagostino.codingchallengetdd.application.ImportService;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.rest.ImportApiDelegatorImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImportApiDelegatorImplTest {

    @InjectMocks
    private ImportApiDelegatorImpl importApiDelegator;

    @Mock
    ImportService importService;

    @Test
    void importDataTest() {

        MultipartFile multipartFile = new MockMultipartFile("file", "sitzplan.csv", MediaType.TEXT_PLAIN_VALUE, "".getBytes());
        ResponseEntity<Void> responseEntity = importApiDelegator.importPost(multipartFile);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void importDataWithExceptionThrown() throws IOException {

        MultipartFile multipartFile = mock(MultipartFile.class);

        when(multipartFile.getBytes()).thenThrow(IOException.class);

        assertThrows(RuntimeException.class, () -> importApiDelegator.importPost(multipartFile));
    }
}