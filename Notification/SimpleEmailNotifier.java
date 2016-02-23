package org.wso2.carbon.apimgt.impl.Notification;

import org.wso2.carbon.apimgt.api.model.Subscriber;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Sam on 2/19/16.
 */
public class SimpleEmailNotifier extends Notifier {
    @Override
    public boolean notifySubscribers(NotificationDTO notification) {
        return true;
    }

    @Override
    public ArrayList<String> getNotifierList(Set<Subscriber> subscriberList) {
        return null;
    }
}
