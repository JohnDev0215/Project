package com.amazon.mobilepushfrontend.external.transform;

import com.amazon.CoralAndroidClient.ClientBase.ClientRequest;
import com.amazon.CoralAndroidClient.ClientBase.Marshaller;
import com.amazon.mobilepushfrontend.GetSubscriptionsRequest;
import com.google.gson.Gson;

/* JADX INFO: loaded from: classes3.dex */
public class GetSubscriptionsRequestMarshaller implements Marshaller<GetSubscriptionsRequest> {
    private final Gson gson;

    private GetSubscriptionsRequestMarshaller() {
        this.gson = null;
    }

    public GetSubscriptionsRequestMarshaller(Gson gson) {
        this.gson = gson;
    }

    @Override // com.amazon.CoralAndroidClient.ClientBase.Marshaller
    public ClientRequest marshal(GetSubscriptionsRequest getSubscriptionsRequest) {
        return new ClientRequest("com.amazon.mobilepushfrontendexternal.MobilePushFrontendExternalService.GetSubscriptions", getSubscriptionsRequest != null ? this.gson.toJson(getSubscriptionsRequest) : null);
    }
}
