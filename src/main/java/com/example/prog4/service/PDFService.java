package com.example.prog4.service;

import com.example.prog4.model.Employee;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;


import java.io.OutputStream;
import java.io.StringWriter;

@Service
@AllArgsConstructor
public class PDFService {

 private final TemplateEngine templateEngine;


 public void generatePdfForEmployee(Employee employee, HttpServletResponse response) throws Exception {
  Context context = new Context();
  context.setVariable("employee", employee);

  StringWriter writer = new StringWriter();
  templateEngine.process("payment", context, writer);

  response.setContentType("application/pdf");
  response.setHeader("Content-Disposition", "attachment; filename=Employee_payment.pdf");

  OutputStream outputStream = response.getOutputStream();
  ITextRenderer renderer = new ITextRenderer();
  renderer.setDocumentFromString(writer.toString());
  renderer.layout();
  renderer.createPDF(outputStream);

  outputStream.close();
 }
}
