package de.neusta.ldagostino.codingchallengetdd.infrastructure.rest;

import de.neusta.ldagostino.codingchallengetdd.application.ImportService;
import de.neusta.ldagostino.codingchallengetdd.generated.api.ImportApiDelegate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ImportApiDelegatorImpl implements ImportApiDelegate {

    private final ImportService importService;

    @Override
    public ResponseEntity<Void> importPost(MultipartFile file) {

        try {
            importService.importData(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }
}
