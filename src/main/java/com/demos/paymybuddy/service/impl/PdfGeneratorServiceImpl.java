package com.demos.paymybuddy.service.impl;

import com.demos.paymybuddy.domain.CustomUser;
import com.demos.paymybuddy.dto.TransactionDto;
import com.demos.paymybuddy.service.CustomUserService;
import com.demos.paymybuddy.service.PdfGeneratorService;
import com.demos.paymybuddy.service.TransactionService;
import com.demos.paymybuddy.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class PdfGeneratorServiceImpl implements PdfGeneratorService {

    private final TemplateEngine templateEngine;
    private final TransactionService transactionService;
    private final CustomUserService userService;

    @Override
    public byte[] generateTransactionPdf(String email, Long transactionID) {

        CustomUser user = userService.findByEmail(email);
        TransactionDto transaction = transactionService.findById(transactionID);
        String fullName = user.getFirstName() +" "+ user.getLastName();

        try {
            Context context = new Context();
            context.setVariable(Constant.PDF_TEMPLATE_NAME_VARIABLE, fullName);
            context.setVariable(Constant.PDF_TEMPLATE_TRANSACTION_VARIABLE,transaction);

            String processedHtml = templateEngine.process(Constant.PDF_TEMPLATE_SINGLE_TRANSACTION, context);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(outputStream);
            renderer.finishPDF();

            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }    }

    @Override
    public byte[] generateAllTransactionsPdf(String email) {
        return new byte[0];
    }
}
