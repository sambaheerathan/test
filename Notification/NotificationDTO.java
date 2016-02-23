package org.wso2.carbon.apimgt.impl.Notification;

import org.wso2.carbon.apimgt.api.model.APIIdentifier;
import java.util.ArrayList;

/**
 * Created by Sam on 2/19/16.
 */
public class NotificationDTO {

    private String subject;
    private String message;
    private APIIdentifier api;
    private ArrayList<String> notifierList;


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public APIIdentifier getApi() {
        return api;
    }

    public void setApi(APIIdentifier api) {
        this.api = api;
    }

    public ArrayList<String> getNotifierList() {
        return notifierList;
    }

    public void setNotifierList(ArrayList<String> notifierList) {
        this.notifierList = notifierList;
    }
}
