package com.amazon.mobilepushfrontend;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractGetSubscriptionsRequest implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.mobilepushfrontend.AbstractGetSubscriptionsRequest");
    private String applicationInstallId;
    private String locale;
    private String marketplaceId;

    public void setApplicationInstallId(String str) {
        this.applicationInstallId = str;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.applicationInstallId, this.marketplaceId, this.locale);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractGetSubscriptionsRequest)) {
            return false;
        }
        AbstractGetSubscriptionsRequest abstractGetSubscriptionsRequest = (AbstractGetSubscriptionsRequest) obj;
        return Helper.equals(this.applicationInstallId, abstractGetSubscriptionsRequest.applicationInstallId) && Helper.equals(this.marketplaceId, abstractGetSubscriptionsRequest.marketplaceId) && Helper.equals(this.locale, abstractGetSubscriptionsRequest.locale);
    }
}
