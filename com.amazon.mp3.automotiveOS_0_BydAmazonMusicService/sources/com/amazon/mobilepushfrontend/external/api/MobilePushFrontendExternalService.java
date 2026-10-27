package com.amazon.mobilepushfrontend.external.api;

import com.amazon.CoralAndroidClient.ClientBase.ResultHandler;
import com.amazon.mobilepushfrontend.GetSubscriptionsRequest;
import com.amazon.mobilepushfrontend.SetSubscriptionsRequest;

/* JADX INFO: loaded from: classes3.dex */
public interface MobilePushFrontendExternalService {
    void getSubscriptionsAsync(GetSubscriptionsRequest getSubscriptionsRequest, ResultHandler resultHandler);

    void setSubscriptionsAsync(SetSubscriptionsRequest setSubscriptionsRequest, ResultHandler resultHandler);
}
