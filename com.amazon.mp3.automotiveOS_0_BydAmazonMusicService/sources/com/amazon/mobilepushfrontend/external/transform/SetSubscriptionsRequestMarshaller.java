package com.amazon.mobilepushfrontend.external.transform;

import com.amazon.CoralAndroidClient.ClientBase.ClientRequest;
import com.amazon.CoralAndroidClient.ClientBase.Marshaller;
import com.amazon.mobilepushfrontend.SetSubscriptionsRequest;
import com.google.gson.Gson;

/* JADX INFO: loaded from: classes3.dex */
public class SetSubscriptionsRequestMarshaller implements Marshaller<SetSubscriptionsRequest> {
    private final Gson gson;

    private SetSubscriptionsRequestMarshaller() {
        this.gson = null;
    }

    public SetSubscriptionsRequestMarshaller(Gson gson) {
        this.gson = gson;
    }

    @Override // com.amazon.CoralAndroidClient.ClientBase.Marshaller
    public ClientRequest marshal(SetSubscriptionsRequest setSubscriptionsRequest) {
        return new ClientRequest("com.amazon.mobilepushfrontendexternal.MobilePushFrontendExternalService.SetSubscriptions", setSubscriptionsRequest != null ? this.gson.toJson(setSubscriptionsRequest) : null);
    }
}
