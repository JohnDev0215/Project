package com.amazon.mobilepushfrontend.external.api;

import com.amazon.CoralAndroidClient.ClientBase.ClientBase;
import com.amazon.CoralAndroidClient.ClientBase.ResultHandler;
import com.amazon.mobilepushfrontend.GetSubscriptionsRequest;
import com.amazon.mobilepushfrontend.SetSubscriptionsRequest;
import com.amazon.mobilepushfrontend.external.transform.GetSubscriptionsRequestMarshaller;
import com.amazon.mobilepushfrontend.external.transform.GetSubscriptionsUnmarshaller;
import com.amazon.mobilepushfrontend.external.transform.SetSubscriptionsRequestMarshaller;
import com.amazon.mobilepushfrontend.external.transform.SetSubscriptionsUnmarshaller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/* JADX INFO: loaded from: classes3.dex */
public class MobilePushFrontendExternalServiceClientImp extends ClientBase implements MobilePushFrontendExternalService {
    private final Gson gson;

    public MobilePushFrontendExternalServiceClientImp() {
        this(new GsonBuilder().create());
    }

    public MobilePushFrontendExternalServiceClientImp(Gson gson) {
        this.gson = gson;
    }

    @Override // com.amazon.mobilepushfrontend.external.api.MobilePushFrontendExternalService
    public void getSubscriptionsAsync(GetSubscriptionsRequest getSubscriptionsRequest, ResultHandler resultHandler) {
        invokeAsync(getSubscriptionsRequest, new GetSubscriptionsRequestMarshaller(this.gson), new GetSubscriptionsUnmarshaller(this.gson), resultHandler);
    }

    @Override // com.amazon.mobilepushfrontend.external.api.MobilePushFrontendExternalService
    public void setSubscriptionsAsync(SetSubscriptionsRequest setSubscriptionsRequest, ResultHandler resultHandler) {
        invokeAsync(setSubscriptionsRequest, new SetSubscriptionsRequestMarshaller(this.gson), new SetSubscriptionsUnmarshaller(this.gson), resultHandler);
    }
}
