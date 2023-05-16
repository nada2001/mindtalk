package com.nada.mindtalk.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class SendGridEmailService {

  private final SendGrid sendGridClient;

  public Boolean sendText(String from, String to, String subject, String body) {
    return sendEmail(from, to, subject, new Content("text/plain", body));
  }

  private Boolean sendEmail(String from, String to, String subject, Content content) {
    Mail mail = new Mail(new Email(from), subject, new Email(to), content);
    mail.setReplyTo(new Email("noreply@rateme.com"));
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      sendGridClient.api(request);
      log.info("SENDGRID EMAIL SENT ===");
      return true;
    } catch (IOException ex) {
      ex.printStackTrace();
      return false;
    }
  }
}