package com.amazon.mobilepushfrontend;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GetNotificationSubscriptionsResponse implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.mobilepushfrontend.GetNotificationSubscriptionsResponse");
    private HyperText helpLink;
    private HyperText message;
    private List<Object> subscriptionGroups;
    private List<PushNotificationSubscription> subscriptions;

    public List<PushNotificationSubscription> getSubscriptions() {
        return this.subscriptions;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.subscriptionGroups, this.subscriptions, this.helpLink, this.message);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GetNotificationSubscriptionsResponse)) {
            return false;
        }
        GetNotificationSubscriptionsResponse getNotificationSubscriptionsResponse = (GetNotificationSubscriptionsResponse) obj;
        return Helper.equals(this.subscriptionGroups, getNotificationSubscriptionsResponse.subscriptionGroups) && Helper.equals(this.subscriptions, getNotificationSubscriptionsResponse.subscriptions) && Helper.equals(this.helpLink, getNotificationSubscriptionsResponse.helpLink) && Helper.equals(this.message, getNotificationSubscriptionsResponse.message);
    }
}
