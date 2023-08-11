package com.demos.paymybuddy.web;

import com.demos.paymybuddy.service.impl.PdfGeneratorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PdfController {

    private final PdfGeneratorServiceImpl pdfGeneratorService;

    @GetMapping(value = "/generate-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdf() {
        try {
            String email = "owaith19@oracle.com";
            Long id = Long.valueOf("18");
            byte[] pdfBytes = pdfGeneratorService.generateTransactionPdf(email,id);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
