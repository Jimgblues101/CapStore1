package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Presigned;
import za.ac.cput.service.S3Service;

/**
 * S3Controller.java
 *
 * @author Zachariah Matsimella
 * Student Num: 220097429
 * @date 09-Sep-24
 */
@RestController
@RequestMapping("/s3")
@CrossOrigin("*")
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @PostMapping("/presigned-url")
    public ResponseEntity<Presigned> generatePresignedUrl(@RequestParam String fileName, @RequestParam String contentType) {
        try {
            String url = s3Service.generatePresignedUrl(fileName, contentType);
            Presigned response = Presigned.builder()
                    .url(url) // Set the URL in the Presigned object
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Return a 500 status code if something goes wrong
            return ResponseEntity.status(500).body(Presigned.builder()
                    .url("Failed to generate presigned URL in the controller") // Set error message
                    .build());
        }
    }
}