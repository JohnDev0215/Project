package com.amazon.mobilepushfrontend;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;

/* JADX INFO: loaded from: classes3.dex */
public class PushNotificationSubscription implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.mobilepushfrontend.PushNotificationSubscription");
    private HyperText details;
    private String groupId;
    private boolean requiresUserRecognized;
    private boolean subscribed;
    private String subscriptionId;
    private String title;

    public String getTitle() {
        return this.title;
    }

    public boolean isSubscribed() {
        return this.subscribed;
    }

    public void setSubscribed(boolean z) {
        this.subscribed = z;
    }

    public String getSubscriptionId() {
        return this.subscriptionId;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.title, this.groupId, Boolean.valueOf(this.subscribed), this.details, this.subscriptionId, Boolean.valueOf(this.requiresUserRecognized));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PushNotificationSubscription)) {
            return false;
        }
        PushNotificationSubscription pushNotificationSubscription = (PushNotificationSubscription) obj;
        return Helper.equals(this.title, pushNotificationSubscription.title) && Helper.equals(this.groupId, pushNotificationSubscription.groupId) && Helper.equals(Boolean.valueOf(this.subscribed), Boolean.valueOf(pushNotificationSubscription.subscribed)) && Helper.equals(this.details, pushNotificationSubscription.details) && Helper.equals(this.subscriptionId, pushNotificationSubscription.subscriptionId) && Helper.equals(Boolean.valueOf(this.requiresUserRecognized), Boolean.valueOf(pushNotificationSubscription.requiresUserRecognized));
    }
}
