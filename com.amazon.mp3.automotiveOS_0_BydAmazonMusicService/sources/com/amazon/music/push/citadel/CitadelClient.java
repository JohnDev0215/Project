package com.amazon.music.push.citadel;

import android.content.Context;
import android.util.Log;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.marketplace.MarketplaceResolver;
import com.amazon.music.metrics.technical.TechnicalMetricsCollection;
import com.amazon.music.push.PushMessagingClient;
import com.amazon.music.push.citadel.exception.CitadelClientException;
import com.amazon.music.push.citadel.request.UpdateCustomerActionRequest;
import com.amazon.music.push.citadel.request.UpdateCustomerStateRequest;
import com.amazon.music.push.citadel.util.CitadelEndpointProvider;
import com.amazon.music.push.citadel.util.CitadelHttpPublisherProvider;
import com.amazon.music.push.citadel.util.CitadelRetryStrategyProvider;
import com.amazon.music.push.sonarpush.NotificationUtil;
import com.amazon.music.push.sonarpush.SonarEndpointsProvider;
import com.amazon.music.push.util.MetricsUtil;
import com.amazon.music.push.util.PushSchedulers;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: loaded from: classes4.dex */
public class CitadelClient {
    private static final String TAG = "CitadelClient";
    private static CitadelClient citadelClient;
    private static TechnicalMetricsCollection technicalMetricsCollection;
    private String accessToken;
    private String appInstallId;
    private String application;
    private String applicationVersion;
    private String customerId;
    private String date;
    private String deviceId;
    private String deviceType;
    private String hardware;
    private String localeId;
    private String marketplaceId;
    private String os;
    private String osNotification;
    private String osVersion;
    private String provider;
    private String providerKey;
    private List<String> pushTopics;
    private String realm;
    private String registeredAt;
    private Integer sequenceNumber;
    private String token;

    CitadelClient() {
    }

    public static synchronized CitadelClient getInstance() {
        if (citadelClient == null) {
            Log.d(TAG, "Initializing CitadelClient");
            citadelClient = new CitadelClient();
            technicalMetricsCollection = MetricsUtil.initializeTechnicalCollectionMetrics("com.amazon.music.push.CitadelClient");
        }
        return citadelClient;
    }

    public void updateCustomerAction(final Context context, final String str, final String str2) {
        Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.music.push.citadel.CitadelClient.1
            @Override // rx.functions.Action1
            public void call(Subscriber<? super Void> subscriber) {
                try {
                    CitadelClient.this.populateClientFields(context, "CitadelActionUpdate");
                    UpdateCustomerActionRequest updateCustomerActionRequest = new UpdateCustomerActionRequest(CitadelClient.this.accessToken, CitadelClient.this.customerId, CitadelClient.this.appInstallId, CitadelClient.this.date, CitadelClient.this.marketplaceId, CitadelClient.this.localeId, CitadelClient.this.deviceId, CitadelClient.this.deviceType, str, Arrays.asList(str2));
                    Log.d(CitadelClient.TAG, "Making updateCustomerAction request");
                    CitadelHttpPublisherProvider.getDefaultHttpPublisher().publish(context, CitadelRetryStrategyProvider.getDefaultActionUpdateStrategy().newStrategy(), updateCustomerActionRequest, CitadelEndpointProvider.getEndpoint(CitadelClient.this.realm, CitadelManager.isProd(context)));
                } catch (Exception e) {
                    Log.w(CitadelClient.TAG, "Error making Citadel updateCustomerAction subscription request: " + e);
                    CitadelClient.technicalMetricsCollection.incrementCounter("citadelActionRequestError", 1.0d);
                    MetricsUtil.sendEventToMTS("citadelActionRequestError", NotificationUtil.getApplicationInstallId(context), "", "citadel", "regular");
                }
            }
        }).subscribeOn(PushSchedulers.getPushScheduler()).subscribe();
    }

    public void updateCustomerAction(final Context context, final String str, final String str2, final String str3, final String str4) {
        Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.music.push.citadel.CitadelClient.2
            @Override // rx.functions.Action1
            public void call(Subscriber<? super Void> subscriber) {
                try {
                    CitadelClient.this.populateClientFields(context, "CitadelActionUpdate");
                    UpdateCustomerActionRequest updateCustomerActionRequest = new UpdateCustomerActionRequest(CitadelClient.this.accessToken, CitadelClient.this.customerId, CitadelClient.this.appInstallId, CitadelClient.this.date, CitadelClient.this.marketplaceId, CitadelClient.this.localeId, CitadelClient.this.deviceId, CitadelClient.this.deviceType, str, str2, str3, str4);
                    Log.d(CitadelClient.TAG, "Making updateCustomerAction request");
                    CitadelHttpPublisherProvider.getDefaultHttpPublisher().publish(context, CitadelRetryStrategyProvider.getDefaultActionUpdateStrategy().newStrategy(), updateCustomerActionRequest, CitadelEndpointProvider.getEndpoint(CitadelClient.this.realm, CitadelManager.isProd(context)));
                } catch (Exception e) {
                    Log.w(CitadelClient.TAG, "Error making Citadel updateCustomerAction push request: " + e);
                    CitadelClient.technicalMetricsCollection.incrementCounter("citadelActionRequestError", 1.0d);
                    MetricsUtil.sendEventToMTS("citadelActionRequestError", NotificationUtil.getApplicationInstallId(context), "", "citadel", "regular");
                }
            }
        }).subscribeOn(PushSchedulers.getPushScheduler()).subscribe();
    }

    public void updateCustomerState(final Context context) {
        Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.music.push.citadel.CitadelClient.3
            @Override // rx.functions.Action1
            public void call(Subscriber<? super Void> subscriber) {
                try {
                    CitadelClient.this.populateClientFields(context, "CitadelStateUpdate");
                    UpdateCustomerStateRequest updateCustomerStateRequest = new UpdateCustomerStateRequest(CitadelClient.this.accessToken, CitadelClient.this.customerId, CitadelClient.this.appInstallId, CitadelClient.this.date, CitadelClient.this.marketplaceId, CitadelClient.this.localeId, CitadelClient.this.deviceId, CitadelClient.this.deviceType, CitadelClient.this.application, CitadelClient.this.applicationVersion, CitadelClient.this.hardware, CitadelClient.this.os, CitadelClient.this.osVersion, CitadelClient.this.provider, CitadelClient.this.providerKey, CitadelClient.this.token, CitadelClient.this.osNotification, CitadelClient.this.sequenceNumber, CitadelClient.this.registeredAt, CitadelClient.this.pushTopics);
                    Log.d(CitadelClient.TAG, "Making updateCustomerState request");
                    CitadelHttpPublisherProvider.getDefaultHttpPublisher().publish(context, CitadelRetryStrategyProvider.getDefaultStateUpdateStrategy().newStrategy(), updateCustomerStateRequest, CitadelEndpointProvider.getEndpoint(CitadelClient.this.realm, CitadelManager.isProd(context)));
                } catch (Exception e) {
                    Log.w(CitadelClient.TAG, "Error making Citadel updateCustomerState request: " + e);
                    CitadelClient.technicalMetricsCollection.incrementCounter("citadelStateRequestError", 1.0d);
                    MetricsUtil.sendEventToMTS("citadelStateRequestError", NotificationUtil.getApplicationInstallId(context), "", "citadel", "regular");
                }
            }
        }).subscribeOn(PushSchedulers.getPushScheduler()).subscribe();
    }

    void populateClientFields(Context context, String str) throws CitadelClientException {
        try {
            this.accessToken = NotificationUtil.getAuthToken(context, str);
            this.customerId = NotificationUtil.getUserId(context);
            this.appInstallId = NotificationUtil.getApplicationInstallId(context);
            this.application = NotificationUtil.getAppInformation(context).getApplicationIdentifier();
            this.applicationVersion = NotificationUtil.getAppInformation(context).getApplicationVersion();
            this.deviceId = NotificationUtil.getDeviceId();
            this.deviceType = PushMessagingClient.getDeviceInfo().getDeviceType();
            this.hardware = NotificationUtil.getAppInformation(context).getHardwareIdentifier();
            this.os = NotificationUtil.getAppInformation(context).getOsIdentifier();
            this.osVersion = NotificationUtil.getAppInformation(context).getOsVersion();
            this.localeId = NotificationUtil.getCurrentLocale(context);
            this.marketplaceId = NotificationUtil.getMarketplaceId();
            this.date = Long.toString(System.currentTimeMillis());
            this.osNotification = NotificationUtil.checkIfNotificationsEnabled(context);
            this.registeredAt = NotificationUtil.getRegisteredAtTimestamp(context);
            this.provider = NotificationUtil.getPushInformation(context, str).getProvider();
            this.providerKey = NotificationUtil.getPushInformation(context, str).getProviderKey();
            this.token = NotificationUtil.getPushInformation(context, str).getSecret();
            this.sequenceNumber = Integer.valueOf(NotificationUtil.getSequenceNumber(context));
            this.realm = getRealm(this.marketplaceId);
            Map<String, Boolean> pushTopicsStatus = NotificationUtil.getPushTopicsStatus(context);
            if (pushTopicsStatus != null) {
                this.pushTopics = (List) pushTopicsStatus.entrySet().stream().filter(new Predicate() { // from class: com.amazon.music.push.citadel.CitadelClient$$ExternalSyntheticLambda0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return CitadelClient.lambda$populateClientFields$0((Map.Entry) obj);
                    }
                }).map(new Function() { // from class: com.amazon.music.push.citadel.CitadelClient$$ExternalSyntheticLambda1
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return CitadelClient.lambda$populateClientFields$1((Map.Entry) obj);
                    }
                }).collect(Collectors.toList());
            } else {
                this.pushTopics = null;
            }
        } catch (Exception e) {
            throw new CitadelClientException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$populateClientFields$0(Map.Entry entry) {
        return ((Boolean) entry.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$populateClientFields$1(Map.Entry entry) {
        return (String) entry.getKey();
    }

    String getRealm(String str) {
        if (SonarEndpointsProvider.get().isROWMarketplace(str)) {
            return SonarEndpointsProvider.get().getRealmForROWMarketplace(str);
        }
        Marketplace marketplaceLookupByObfuscatedId = MarketplaceResolver.lookupByObfuscatedId(str);
        if (marketplaceLookupByObfuscatedId == null || marketplaceLookupByObfuscatedId.getRegion() == null) {
            throw new CitadelClientException("MarketplaceId could not be matched to any known marketplace");
        }
        return marketplaceLookupByObfuscatedId.getRegion();
    }
}
