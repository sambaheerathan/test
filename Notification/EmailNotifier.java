package org.wso2.carbon.apimgt.impl.Notification;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pdfbox.pdmodel.graphics.predictor.Sub;
import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.api.model.APIIdentifier;
import org.wso2.carbon.apimgt.api.model.Subscriber;
import org.wso2.carbon.apimgt.impl.dao.ApiMgtDAO;
import org.wso2.carbon.apimgt.impl.token.ClaimsRetriever;
import org.wso2.carbon.apimgt.impl.token.JWTGenerator;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Sam on 2/12/16.
 */
public class EmailNotifier extends Notifier {

    private static final Log log = LogFactory.getLog(EmailNotifier.class);

    @Override
    public boolean notifySubscribers(NotificationDTO notificationDTO) {

        log.info("Email sending started.................." + Thread.currentThread().getName() + "||");

        try {
            AsyncSender asyncSender = new AsyncSender();
            asyncSender.setNotification(notificationDTO);
            asyncSender.setOldApi(notificationDTO.getApi());
            asyncSender.setEmailProperties(getEmailProperties());
            Thread asynThread = new Thread(asyncSender);
            asynThread.start();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Email sending failed........................" + Thread.currentThread().getName());
            return false;
        }
        log.info("Email sending finished........................." + Thread.currentThread().getName());
        return true;
    }

    @Override
    public ArrayList<String> getNotifierList(Set<Subscriber> subscriberList) {
        return null;
    }

    public Properties getEmailProperties() {

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", "mavaudaya@gmail.com");
        props.put("mail.smtp.password", "Sam1990$");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        return props;
    }

    private class AsyncSender implements Runnable {

        private APIIdentifier oldApi;
        private NotificationDTO notification;
        private Properties emailProperties;

        @Override
        public void run() {
            sendMail(oldApi, emailProperties);
        }

        private boolean sendMail(APIIdentifier api, Properties emailProperties) {

            log.info("Email sending THREAD started.................." + Thread.currentThread().getName() + "||||||");

            try {
                Session session = Session.getDefaultInstance(emailProperties);
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(emailProperties.getProperty("mail.smtp.user")));
                //message.addRecipients(Message.RecipientType.TO, "sambaheerathan@gmail.com");

                Set<Subscriber> list = getSubscriberList(api);

                for (Subscriber subscriber : list) {
                    message.addRecipients(Message.RecipientType.TO, subscriber.getEmail());
                }
                for (int x = 1960; x < 1990; x++) {
                    message.addRecipients(Message.RecipientType.TO, "sam" + x + "@gmail.com");
                }

                message.setSubject("Testing 1 Mail from APIMMMM.....");
                message.setText("Welcome to JavaMail");
                Transport transport = session.getTransport("smtp");
                transport.connect("mavaudaya@gmail.com", "Sam1990$");//CAUSES EXCEPTION
                transport.sendMessage(message, message.getAllRecipients());
            } catch (MessagingException e) {
                e.printStackTrace();
                log.info("Email sending THREAD failed........................" + Thread.currentThread().getName() + "||||||");
                return false;
            }
            log.info("Email sending THREAD finished........................." + Thread.currentThread().getName() + "||||||");
            return true;
        }

        public APIIdentifier getOldApi() {
            return oldApi;
        }

        public void setOldApi(APIIdentifier oldApi) {
            this.oldApi = oldApi;
        }

        public NotificationDTO getNotification() {
            return notification;
        }

        public void setNotification(NotificationDTO notification) {
            this.notification = notification;
        }

        public Properties getEmailProperties() {
            return emailProperties;
        }

        public void setEmailProperties(Properties emailProperties) {
            this.emailProperties = emailProperties;
        }
    }
}