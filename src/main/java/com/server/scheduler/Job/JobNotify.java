package com.server.scheduler.Job;

import com.server.scheduler.model.Employee;
import com.server.scheduler.repository.EmployeeRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class JobNotify {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobNotify.class);

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Value("${spring.mail.username}")
    private String sender;

    @Scheduled(cron = "${scheduling.job.cron}")
    public void jobNotification() {
        List<Employee> employeeList = employeeRepository.findAll();
        for (Employee employee : employeeList) {
            sendEmail(employee);
        }
    }

    private void sendEmail(Employee employee) {

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            LOGGER.info("Sending Email to {}", employee);

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setSubject("Notification");
            mimeMessageHelper.setTo(employee.getEmail());
            String link = "Bonjour";
            mimeMessageHelper.setText(link);
            javaMailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            e.printStackTrace();
            LOGGER.error("Failed to send email to {}", employee);
        }
    }
}
