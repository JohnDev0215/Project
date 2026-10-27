package com.amazon.mobilepushfrontend;

import com.amazon.CoralAndroidClient.ClientBase.Helper;

/* JADX INFO: loaded from: classes3.dex */
public class GetSubscriptionsRequest extends AbstractGetSubscriptionsRequest {
    private static final int classNameHashCode = Helper.hash("com.amazon.mobilepushfrontend.GetSubscriptionsRequest");

    @Override // com.amazon.mobilepushfrontend.AbstractGetSubscriptionsRequest
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }

    @Override // com.amazon.mobilepushfrontend.AbstractGetSubscriptionsRequest
    public boolean equals(Object obj) {
        if (obj instanceof GetSubscriptionsRequest) {
            return super.equals(obj);
        }
        return false;
    }
}
