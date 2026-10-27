package com.amazon.mobilepushfrontend;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractSetSubscriptionsRequest implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.mobilepushfrontend.AbstractSetSubscriptionsRequest");
    private String applicationInstallId;
    private String marketplaceId;
    private List<PushNotificationSubscription> subscriptions;

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setApplicationInstallId(String str) {
        this.applicationInstallId = str;
    }

    public void setSubscriptions(List<PushNotificationSubscription> list) {
        this.subscriptions = list;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.marketplaceId, this.applicationInstallId, this.subscriptions);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractSetSubscriptionsRequest)) {
            return false;
        }
        AbstractSetSubscriptionsRequest abstractSetSubscriptionsRequest = (AbstractSetSubscriptionsRequest) obj;
        return Helper.equals(this.marketplaceId, abstractSetSubscriptionsRequest.marketplaceId) && Helper.equals(this.applicationInstallId, abstractSetSubscriptionsRequest.applicationInstallId) && Helper.equals(this.subscriptions, abstractSetSubscriptionsRequest.subscriptions);
    }
}
