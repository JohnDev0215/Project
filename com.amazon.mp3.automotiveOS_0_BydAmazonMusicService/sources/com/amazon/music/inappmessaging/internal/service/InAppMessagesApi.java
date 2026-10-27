package com.amazon.music.inappmessaging.internal.service;

import com.amazon.music.inappmessaging.external.DeviceInformation;
import com.amazon.music.inappmessaging.external.IAMCredentials;
import com.amazon.music.inappmessaging.external.IAMRequest;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageRequest;
import com.amazon.music.inappmessaging.external.model.HandleActionRequest;
import com.amazon.music.inappmessaging.external.model.WeblabTriggerRequest;
import com.amazon.music.inappmessaging.internal.model.DynamicMessageResponse;
import com.amazon.music.inappmessaging.internal.model.HandleActionResponse;
import rx.Observable;

/* JADX INFO: loaded from: classes4.dex */
public interface InAppMessagesApi {
    Observable<DynamicMessageResponse> getDynamicMessages(IAMCredentials iamCredentials, DeviceInformation deviceInformation, DynamicMessageRequest request);

    String getInAppBatchJson(IAMCredentials iamCredentials, DeviceInformation deviceInformation, String url);

    Observable<InAppMessageListJson> getInAppMessages(IAMCredentials iamCredentials, DeviceInformation deviceInformation, IAMRequest request);

    Observable<HandleActionResponse> handleAction(IAMCredentials iamCredentials, DeviceInformation deviceInformation, HandleActionRequest request);

    void recordWeblabTriggers(IAMCredentials iamCredentials, DeviceInformation deviceInformation, WeblabTriggerRequest request, DynamicMessageEvent event);
}
