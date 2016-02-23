package org.wso2.carbon.apimgt.impl.Notification;

import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.api.model.APIIdentifier;
import org.wso2.carbon.apimgt.api.model.Subscriber;
import org.wso2.carbon.apimgt.impl.dao.ApiMgtDAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sam on 2/12/16.
 */
public abstract class Notifier {

    public abstract boolean notifySubscribers(NotificationDTO notification);

    public abstract ArrayList<String> getNotifierList(Set<Subscriber> subscriberList);

    public Set<Subscriber> getSubscriberList(APIIdentifier api) {

        Set<Subscriber> subscriberList = new HashSet<Subscriber>();

        ApiMgtDAO dao = new ApiMgtDAO();
        try {
            Set<Subscriber> subscribers = dao.getSubscribersOfAPI(api);

        } catch (APIManagementException e) {
            return null;
        }
        return subscriberList;
    }
}


