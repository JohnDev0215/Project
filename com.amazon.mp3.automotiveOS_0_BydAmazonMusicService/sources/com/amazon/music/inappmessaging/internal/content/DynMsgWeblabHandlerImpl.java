package com.amazon.music.inappmessaging.internal.content;

import android.util.Log;
import com.amazon.music.inappmessaging.external.DeviceInformation;
import com.amazon.music.inappmessaging.external.IAMCredentials;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.WeblabTriggerRequest;
import com.amazon.music.inappmessaging.internal.model.Weblab;
import com.amazon.music.inappmessaging.internal.service.InAppMessagesApi;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
public class DynMsgWeblabHandlerImpl implements DynMsgWeblabHandler {
    private static final String TAG = "DynMsgWeblabHandlerImpl";
    private final Observable<IAMCredentials> credentials;
    private final Observable<DeviceInformation> deviceInformation;
    private final InAppMessagesApi inAppMessagesApiClient;

    public DynMsgWeblabHandlerImpl(final InAppMessagesApi inAppMessagesApi, final Observable<IAMCredentials> iamCredentials, final Observable<DeviceInformation> deviceInformation) {
        this.inAppMessagesApiClient = inAppMessagesApi;
        this.credentials = iamCredentials;
        this.deviceInformation = deviceInformation;
    }

    @Override // com.amazon.music.inappmessaging.internal.content.DynMsgWeblabHandler
    public Observable<Boolean> recordWeblabTriggers(final List<Weblab> weblabs, final DynamicMessageEvent event) {
        return Observable.zip(this.credentials, this.deviceInformation, new Func2<IAMCredentials, DeviceInformation, Boolean>() { // from class: com.amazon.music.inappmessaging.internal.content.DynMsgWeblabHandlerImpl.1
            @Override // rx.functions.Func2
            public Boolean call(final IAMCredentials iamCredentials, final DeviceInformation deviceInformation) {
                try {
                    DynMsgWeblabHandlerImpl.this.inAppMessagesApiClient.recordWeblabTriggers(iamCredentials, deviceInformation, new WeblabTriggerRequest(iamCredentials.getMusicTerritory(), weblabs), event);
                    return Boolean.TRUE;
                } catch (Exception e) {
                    Log.e(DynMsgWeblabHandlerImpl.TAG, "Error recording trigger for weblab. " + e.getMessage());
                    return Boolean.FALSE;
                }
            }
        }).onErrorReturn(new Func1() { // from class: com.amazon.music.inappmessaging.internal.content.DynMsgWeblabHandlerImpl$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return DynMsgWeblabHandlerImpl.lambda$recordWeblabTriggers$0((Throwable) obj);
            }
        }).subscribeOn(Schedulers.io());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$recordWeblabTriggers$0(Throwable th) {
        Log.e(TAG, "Error found with parameters passed to record weblab triggers. " + th.getMessage());
        return Boolean.FALSE;
    }
}
