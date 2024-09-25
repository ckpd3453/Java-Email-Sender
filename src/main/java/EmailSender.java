import java.io.File;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class EmailSender {
    public static void main(String[] args) {
        // Email credentials
        final String username = System.getenv("EMAIL_USERNAME");
        final String password = System.getenv("EMAIL_PASSWORD");

        // Recipient emails
        String[] toEmails = {"deeksha@strategictalentpartner.com",
                "shristi@subcodevs.com",
                "karun.kumar@inceptica.co",
                "tejaswinee.desai@silvertouch.com",
                "careers@techcurators.in",
                "ydeokar@nseit.com",
                "hr.in@selleckchem.com",
                "recruit1@fousonit.com",
                "Krithika.Shetty@winfort.net",
                "ithiring.glan@gmail.com",
                "sunitchaudhuri@pcsgpl.com",
                "hr@microproindia.com",
                "ankur@codeitsoft.com",
                "deval.misra@trackolap.com",
                "Srikanth.Gullapally@piloggroup.com",
                "priyanka@cronj.com",
                "parveen.chauhan@rahinfotech.com",
                "roshnimali92@gmail.com"};

        // SMTP server settings
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a session with an authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        for (String toEmail : toEmails) {
            try {
                // Create a new email message for each recipient
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));  // Sender's email
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));  // Send individually to each recipient
                message.setSubject("Inquiry About Software Developer Openings (Java Full Stack / MERN Stack)");

                // Create a multipart message for body and attachment
                Multipart multipart = new MimeMultipart();

                // Body part for the email body text
                BodyPart messageBodyPart = new MimeBodyPart();
                String emailBody = "Dear Sir/Mam,\n\n"
                        + "I hope this email finds you well.\n\n"
                        + "My name is Chandrakant Prasad, and I am writing to inquire about any current or upcoming job openings for "
                        + "a Software Developer position within your esteemed organization. I have 2 years of experience in full-stack development, "
                        + "specializing in both Java Full Stack and MERN Stack technologies.\n\n"
                        + "Throughout my professional journey, I have worked on projects that involve:\n\n"
                        + "• Developing and deploying scalable web applications\n"
                        + "• Creating RESTful APIs\n"
                        + "• Working with databases (SQL and NoSQL)\n"
                        + "• Utilizing both frontend and backend technologies, such as Java, Spring Boot, React, Node.js, MongoDB, and more.\n\n"
                        + "I believe my technical expertise and problem-solving skills, along with my ability to work in an agile environment, can be an asset to your team.\n\n"
                        + "I have attached my resume below for your review. I would appreciate the opportunity to discuss how I could contribute to your company's goals.\n\n"
                        + "Thank you for your time and consideration. I look forward to the possibility of hearing from you.\n\n"
                        + "Warm regards,\n"
                        + "Chandrakant Prasad\n"
                        + "Phone: 7001950699\n"
                        + "Email: chandrakantprasad68@gmail.com\n"
                        + "LinkedIn: https://www.linkedin.com/in/chandrakant-prasad-b952b01a3/";

                messageBodyPart.setText(emailBody);
                multipart.addBodyPart(messageBodyPart);

                // Body part for the attachment (resume)
                messageBodyPart = new MimeBodyPart();
                String filename = "C:/Users/Bridgelabz/Downloads/email-sender/src/main/resources/ChandraOnePagerResume (1).pdf";  // Specify the path to the PDF file
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName("Chandrakant_Prasad_Resume.pdf");  // Set the file name for the email attachment
                multipart.addBodyPart(messageBodyPart);

                // Set the multipart message to the email
                message.setContent(multipart);

                // Send the email
                Transport.send(message);

                System.out.println("Email with resume attachment sent successfully to: " + toEmail);

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
