package com.amazon.music.inappmessaging.internal.cache;

import android.util.Pair;
import com.amazon.music.inappmessaging.external.DeviceInformation;
import com.amazon.music.inappmessaging.external.IAMCredentials;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.inappmessaging.internal.cache.database.DynamicMessagingCache;
import com.amazon.music.inappmessaging.internal.cache.entity.DynamicMessagesPerTrigger;
import com.amazon.music.inappmessaging.internal.content.DynMsgWeblabHandler;
import com.amazon.music.inappmessaging.internal.model.CachePolicyType;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import com.amazon.music.inappmessaging.internal.model.DynamicMessageResponse;
import com.amazon.music.inappmessaging.internal.service.InAppMessagesApi;
import com.amazon.music.inappmessaging.internal.util.DynamicMessageRequestHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Completable;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
public class DynMsgCacheHandlerImpl implements DynMsgCacheHandler {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) DynMsgCacheHandlerImpl.class);
    private final Observable<IAMCredentials> credentials;
    private final Observable<DeviceInformation> deviceInformation;
    private final DynMsgWeblabHandler dynMsgWeblabHandler;
    private final DynamicMessagingCache dynamicMessagingCache;
    private final InAppMessagesApi inAppMessagesApiClient;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Observable lambda$callDynMsgAPI$4(Observable observable) {
        return observable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Observable lambda$callDynMsgAPIWithDeeplinkRequest$5(Observable observable) {
        return observable;
    }

    public DynMsgCacheHandlerImpl(final DynamicMessagingCache dynamicMessagingCache, final InAppMessagesApi inAppMessagesApi, final Observable<IAMCredentials> iamCredentials, final Observable<DeviceInformation> deviceInformation, final DynMsgWeblabHandler dynMsgWeblabHandler) {
        this.dynamicMessagingCache = dynamicMessagingCache;
        this.inAppMessagesApiClient = inAppMessagesApi;
        this.credentials = iamCredentials;
        this.deviceInformation = deviceInformation;
        this.dynMsgWeblabHandler = dynMsgWeblabHandler;
    }

    @Override // com.amazon.music.inappmessaging.internal.cache.DynMsgCacheHandler
    public Observable<DynamicMessage> getDynamicMessage(final DynamicMessageEvent event) {
        Pair<DynamicMessage, Long> pairRetrieveValidMessageFromCacheAndDeleteIfRequired = retrieveValidMessageFromCacheAndDeleteIfRequired(event);
        DynamicMessage dynamicMessage = (DynamicMessage) pairRetrieveValidMessageFromCacheAndDeleteIfRequired.first;
        Long l = (Long) pairRetrieveValidMessageFromCacheAndDeleteIfRequired.second;
        if (dynamicMessage == null && !isTriggerResponseWithinTimeToLive(l)) {
            loadDynamicMessagesInCache().subscribe();
        }
        return Observable.just(dynamicMessage);
    }

    @Override // com.amazon.music.inappmessaging.internal.cache.DynMsgCacheHandler
    public Observable<Boolean> loadDynamicMessagesInCache() {
        return callDynMsgAPI().flatMap(new Func1<DynamicMessageResponse, Observable<Boolean>>() { // from class: com.amazon.music.inappmessaging.internal.cache.DynMsgCacheHandlerImpl.1
            @Override // rx.functions.Func1
            public Observable<Boolean> call(final DynamicMessageResponse response) {
                DynMsgCacheHandlerImpl.this.storeDynamicMessageResponseInCache(response, false);
                return Observable.just(Boolean.TRUE);
            }
        }).onErrorReturn(new Func1() { // from class: com.amazon.music.inappmessaging.internal.cache.DynMsgCacheHandlerImpl$$ExternalSyntheticLambda1
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return DynMsgCacheHandlerImpl.lambda$loadDynamicMessagesInCache$0((Throwable) obj);
            }
        }).subscribeOn(Schedulers.io());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$loadDynamicMessagesInCache$0(Throwable th) {
        LOG.error("Error in getting dynamic messages:" + th.getMessage());
        return Boolean.FALSE;
    }

    @Override // com.amazon.music.inappmessaging.internal.cache.DynMsgCacheHandler
    public Observable<Boolean> loadDeeplinkDynamicMessage(final DynamicMessageEvent event, final boolean isFTU) {
        return callDynMsgAPIWithDeeplinkRequest(event, isFTU).flatMap(new Func1<DynamicMessageResponse, Observable<Boolean>>() { // from class: com.amazon.music.inappmessaging.internal.cache.DynMsgCacheHandlerImpl.2
            @Override // rx.functions.Func1
            public Observable<Boolean> call(final DynamicMessageResponse response) {
                DynMsgCacheHandlerImpl.this.storeDynamicMessageResponseInCache(response, true);
                return Observable.just(Boolean.TRUE);
            }
        }).onErrorReturn(new Func1() { // from class: com.amazon.music.inappmessaging.internal.cache.DynMsgCacheHandlerImpl$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return DynMsgCacheHandlerImpl.lambda$loadDeeplinkDynamicMessage$1((Throwable) obj);
            }
        }).subscribeOn(Schedulers.io());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$loadDeeplinkDynamicMessage$1(Throwable th) {
        LOG.error("Error in getting deeplink dynamic message:" + th.getMessage());
        return Boolean.FALSE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$clearCache$2() {
        this.dynamicMessagingCache.dynamicMessagingDao().deleteAllEntries();
    }

    @Override // com.amazon.music.inappmessaging.internal.cache.DynMsgCacheHandler
    public void clearCache() {
        Completable.fromAction(new Action0() { // from class: com.amazon.music.inappmessaging.internal.cache.DynMsgCacheHandlerImpl$$ExternalSyntheticLambda4
            @Override // rx.functions.Action0
            public final void call() {
                this.f$0.lambda$clearCache$2();
            }
        }).doOnError(new Action1() { // from class: com.amazon.music.inappmessaging.internal.cache.DynMsgCacheHandlerImpl$$ExternalSyntheticLambda5
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DynMsgCacheHandlerImpl.lambda$clearCache$3((Throwable) obj);
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$clearCache$3(Throwable th) {
        LOG.error("Error deleting dynamic message cache entries: " + th.getMessage());
    }

    private Observable<DynamicMessageResponse> callDynMsgAPI() {
        return Observable.zip(this.credentials, this.deviceInformation, new Func2<IAMCredentials, DeviceInformation, Observable<DynamicMessageResponse>>() { // from class: com.amazon.music.inappmessaging.internal.cache.DynMsgCacheHandlerImpl.3
            @Override // rx.functions.Func2
            public Observable<DynamicMessageResponse> call(final IAMCredentials iamCredentials, final DeviceInformation deviceInformation) {
                return DynMsgCacheHandlerImpl.this.inAppMessagesApiClient.getDynamicMessages(iamCredentials, deviceInformation, DynamicMessageRequestHelper.createDynamicMessageRequestForBatchCall(iamCredentials.getMusicTerritory()));
            }
        }).subscribeOn(Schedulers.io()).flatMap(new Func1() { // from class: com.amazon.music.inappmessaging.internal.cache.DynMsgCacheHandlerImpl$$ExternalSyntheticLambda2
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return DynMsgCacheHandlerImpl.lambda$callDynMsgAPI$4((Observable) obj);
            }
        });
    }

    private Observable<DynamicMessageResponse> callDynMsgAPIWithDeeplinkRequest(final DynamicMessageEvent event, final boolean isFTU) {
        return Observable.zip(this.credentials, this.deviceInformation, new Func2<IAMCredentials, DeviceInformation, Observable<DynamicMessageResponse>>() { // from class: com.amazon.music.inappmessaging.internal.cache.DynMsgCacheHandlerImpl.4
            @Override // rx.functions.Func2
            public Observable<DynamicMessageResponse> call(final IAMCredentials iamCredentials, final DeviceInformation deviceInformation) {
                return DynMsgCacheHandlerImpl.this.inAppMessagesApiClient.getDynamicMessages(iamCredentials, deviceInformation, DynamicMessageRequestHelper.createDynamicMessageDeeplinkRequest(event, iamCredentials.getMusicTerritory(), isFTU));
            }
        }).subscribeOn(Schedulers.io()).flatMap(new Func1() { // from class: com.amazon.music.inappmessaging.internal.cache.DynMsgCacheHandlerImpl$$ExternalSyntheticLambda3
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return DynMsgCacheHandlerImpl.lambda$callDynMsgAPIWithDeeplinkRequest$5((Observable) obj);
            }
        });
    }

    void storeDynamicMessageResponseInCache(final DynamicMessageResponse dynamicMessageResponse, final boolean isDeeplinkEvent) {
        Map<Trigger, List<DynamicMessage>> map;
        if (dynamicMessageResponse == null || (map = dynamicMessageResponse.dynamicMessagesByTrigger) == null || map.size() == 0) {
            return;
        }
        if (!isDeeplinkEvent) {
            this.dynamicMessagingCache.dynamicMessagingDao().deleteAllEntries();
        }
        ArrayList arrayList = new ArrayList();
        long j = dynamicMessageResponse.epochTimeToLive;
        for (Map.Entry<Trigger, List<DynamicMessage>> entry : dynamicMessageResponse.dynamicMessagesByTrigger.entrySet()) {
            arrayList.add(new DynamicMessagesPerTrigger(entry.getKey(), entry.getValue(), j));
        }
        this.dynamicMessagingCache.dynamicMessagingDao().addMessages(arrayList);
    }

    boolean isTriggerResponseWithinTimeToLive(final Long epochTimeToLive) {
        return epochTimeToLive != null && System.currentTimeMillis() < epochTimeToLive.longValue();
    }

    Pair<DynamicMessage, Long> retrieveValidMessageFromCacheAndDeleteIfRequired(final DynamicMessageEvent event) {
        DynamicMessagesPerTrigger dynamicMessages = this.dynamicMessagingCache.dynamicMessagingDao().getDynamicMessages(Trigger.fromName(event.getEvent()));
        DynamicMessage dynamicMessage = null;
        if (dynamicMessages == null) {
            return new Pair<>(null, null);
        }
        long epochTimeToLive = dynamicMessages.getEpochTimeToLive();
        List<DynamicMessage> dynamicMessages2 = dynamicMessages.getDynamicMessages();
        long jCurrentTimeMillis = System.currentTimeMillis();
        int i = 0;
        while (true) {
            if (i >= dynamicMessages2.size()) {
                break;
            }
            if (DynamicMessageRequestHelper.isDynamicMessageValid(event.isCritical(), jCurrentTimeMillis, epochTimeToLive, dynamicMessages2.get(i))) {
                dynamicMessage = dynamicMessages2.get(i);
                if (dynamicMessage.weblabsToTrigger.size() > 0) {
                    this.dynMsgWeblabHandler.recordWeblabTriggers(dynamicMessage.weblabsToTrigger, event).subscribe();
                }
                if (dynamicMessage.cachePolicy == CachePolicyType.SHOW_ONCE) {
                    dynamicMessages2.remove(i);
                }
                this.dynamicMessagingCache.dynamicMessagingDao().addMessages(Arrays.asList(new DynamicMessagesPerTrigger(dynamicMessages.getTrigger(), dynamicMessages2, epochTimeToLive)));
            } else {
                i++;
            }
        }
        return new Pair<>(dynamicMessage, Long.valueOf(epochTimeToLive));
    }
}
