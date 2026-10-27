package com.amazon.mobilepushfrontend;

import com.amazon.CoralAndroidClient.ClientBase.Helper;

/* JADX INFO: loaded from: classes3.dex */
public class SetSubscriptionsRequest extends AbstractSetSubscriptionsRequest {
    private static final int classNameHashCode = Helper.hash("com.amazon.mobilepushfrontend.SetSubscriptionsRequest");

    @Override // com.amazon.mobilepushfrontend.AbstractSetSubscriptionsRequest
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }

    @Override // com.amazon.mobilepushfrontend.AbstractSetSubscriptionsRequest
    public boolean equals(Object obj) {
        if (obj instanceof SetSubscriptionsRequest) {
            return super.equals(obj);
        }
        return false;
    }
}
