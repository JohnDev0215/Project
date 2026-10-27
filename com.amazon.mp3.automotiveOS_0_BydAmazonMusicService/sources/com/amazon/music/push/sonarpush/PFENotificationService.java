package com.amazon.music.push.sonarpush;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.ResultHandler;
import com.amazon.CoralAndroidClient.Exception.ClientException;
import com.amazon.CoralAndroidClient.Exception.NativeException;
import com.amazon.KiangService.PushInformation;
import com.amazon.KiangService.RegisterApplicationInstallResponse;
import com.amazon.mobilepushfrontend.GetNotificationSubscriptionsResponse;
import com.amazon.mobilepushfrontend.GetSubscriptionsRequest;
import com.amazon.mobilepushfrontend.PushNotificationSubscription;
import com.amazon.mobilepushfrontend.SetSubscriptionsRequest;
import com.amazon.mobilepushfrontend.appstate.external.api.MobilePushFrontendExternalAppStateService;
import com.amazon.mobilepushfrontend.appstate.external.api.MobilePushFrontendExternalAppStateServiceClientImp;
import com.amazon.mobilepushfrontend.external.api.MobilePushFrontendExternalService;
import com.amazon.mobilepushfrontend.external.api.MobilePushFrontendExternalServiceClientImp;
import com.amazon.mobilepushfrontendappstateexternal.RegisterApplicationInstallRequest;
import com.amazon.mobilepushfrontendappstateexternal.UpdateApplicationInstallRequest;
import com.amazon.music.metrics.technical.TechnicalMetricsCollection;
import com.amazon.music.push.citadel.CitadelClient;
import com.amazon.music.push.util.MetricsUtil;
import com.amazon.music.push.util.PushSchedulers;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: loaded from: classes4.dex */
public class PFENotificationService {
    private static final String TAG = "PFENotificationService";
    private static final AtomicBoolean mRegisterInProgress = new AtomicBoolean(false);
    private static TechnicalMetricsCollection technicalMetricsCollection;
    private final Context mContext;
    private MobilePushFrontendExternalServiceClientImp mMobileNotificationService;
    private final String mOperationInProgress;
    private Map<String, Boolean> mPushTopicOptInMap;
    private MarketplaceStateChangedListener marketplaceStateChangedListener;
    private final SharedPreferences sharedPreferences;
    private String mAppInstallId = null;
    private final MobilePushFrontendExternalAppStateServiceClientImp mMobileNotificationAppStateService = new MobilePushFrontendExternalAppStateServiceClientImp();

    public PFENotificationService(Context context, String str) {
        this.sharedPreferences = context.getSharedPreferences("sonarPushPreferences", 0);
        this.mContext = context.getApplicationContext();
        this.mOperationInProgress = str;
        technicalMetricsCollection = MetricsUtil.initializeTechnicalCollectionMetrics("com.amazon.music.push.SonarPFENotificationService");
    }

    public void registerApplication(final Context context) {
        technicalMetricsCollection.startTimer("RegisterApplicationLatency");
        Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.music.push.sonarpush.PFENotificationService.1
            @Override // rx.functions.Action1
            public void call(Subscriber<? super Void> subscriber) {
                PFENotificationService pFENotificationService = PFENotificationService.this;
                Context context2 = context;
                pFENotificationService.registerAppImpl(context2, new AppRegisterHandler(context2));
            }
        }).subscribeOn(PushSchedulers.getPushScheduler()).subscribe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerAppImpl(Context context, ResultHandler resultHandler) {
        if (TextUtils.isEmpty(NotificationUtil.getApplicationInstallId(context)) && !mRegisterInProgress.getAndSet(true)) {
            RegisterApplicationInstallRequest registerApplicationInstallRequest = new RegisterApplicationInstallRequest();
            registerApplicationInstallRequest.setApplicationInformation(NotificationUtil.getAppInformation(context));
            registerApplicationInstallRequest.setPushInformation(NotificationUtil.getPushInformation(context, this.mOperationInProgress));
            mobilePushAppStateService(context, 0).registerApplicationInstallAsync(registerApplicationInstallRequest, resultHandler);
            Log.d(TAG, "Disabling retry registration if already registered");
        }
    }

    public void updateAppInfo(final Context context) {
        technicalMetricsCollection.startTimer("UpdateApplicationLatency");
        Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.music.push.sonarpush.PFENotificationService.2
            @Override // rx.functions.Action1
            public void call(Subscriber<? super Void> subscriber) {
                PushInformation pushInformation = NotificationUtil.getPushInformation(context, PFENotificationService.this.mOperationInProgress);
                PFENotificationService pFENotificationService = PFENotificationService.this;
                Context context2 = context;
                pFENotificationService.updateAppInfoImpl(context2, pushInformation, NotificationUtil.getCurrentLocale(context2), new UpdateAppInfo());
            }
        }).subscribeOn(PushSchedulers.getPushScheduler()).subscribe();
    }

    public void getAndSetSubscriptions(final Context context) throws NotificationException {
        technicalMetricsCollection.startTimer("SetSubscriptionLatency");
        if (this.mOperationInProgress.equals("SubscriptionUpdate")) {
            recordCustomerPushToggleServiceMetrics();
        }
        final String applicationInstallId = NotificationUtil.getApplicationInstallId(context);
        if (TextUtils.isEmpty(applicationInstallId)) {
            throw new NotificationException("Update subscription failed as no App Id is found");
        }
        Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.music.push.sonarpush.PFENotificationService.3
            @Override // rx.functions.Action1
            public void call(Subscriber<? super Void> subscriber) {
                Log.d(PFENotificationService.TAG, "Calling getSubscription for App Id " + applicationInstallId);
                GetSubscriptionsRequest getSubscriptionsRequest = new GetSubscriptionsRequest();
                getSubscriptionsRequest.setApplicationInstallId(applicationInstallId);
                getSubscriptionsRequest.setMarketplaceId(NotificationUtil.getMarketplaceId());
                getSubscriptionsRequest.setLocale(NotificationUtil.getCurrentLocale(context));
                PFENotificationService.this.mobileNotificationService(context).getSubscriptionsAsync(getSubscriptionsRequest, new GetSubscriptionsHandler());
            }
        }).subscribeOn(PushSchedulers.getPushScheduler()).subscribe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSubscriptions(final Context context, final List<PushNotificationSubscription> list) {
        final String applicationInstallId = NotificationUtil.getApplicationInstallId(context);
        Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.music.push.sonarpush.PFENotificationService.4
            @Override // rx.functions.Action1
            public void call(Subscriber<? super Void> subscriber) {
                Log.d(PFENotificationService.TAG, "Calling setSubscription for App Id " + applicationInstallId);
                SetSubscriptionsRequest setSubscriptionsRequest = new SetSubscriptionsRequest();
                setSubscriptionsRequest.setApplicationInstallId(applicationInstallId);
                setSubscriptionsRequest.setMarketplaceId(NotificationUtil.getMarketplaceId());
                setSubscriptionsRequest.setSubscriptions(list);
                PFENotificationService.this.mobileNotificationService(context).setSubscriptionsAsync(setSubscriptionsRequest, new SetSubscriptionsHandler());
            }
        }).subscribeOn(PushSchedulers.getPushScheduler()).subscribe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAppInfoImpl(Context context, PushInformation pushInformation, String str, ResultHandler resultHandler) {
        String applicationInstallId = NotificationUtil.getApplicationInstallId(context);
        if (TextUtils.isEmpty(applicationInstallId)) {
            registerAppImpl(context, resultHandler);
            return;
        }
        String userId = NotificationUtil.getUserId(context);
        String authToken = NotificationUtil.getAuthToken(context, this.mOperationInProgress);
        if (userId != null && TextUtils.isEmpty(authToken)) {
            if (resultHandler != null) {
                resultHandler.onException(new NotificationServiceAuthenticationException("Authentication token is empty"));
                return;
            }
            return;
        }
        UpdateApplicationInstallRequest updateApplicationInstallRequest = new UpdateApplicationInstallRequest();
        updateApplicationInstallRequest.setApplicationInformation(NotificationUtil.getAppInformation(context));
        updateApplicationInstallRequest.setApplicationInstallId(applicationInstallId);
        updateApplicationInstallRequest.setLocaleId(str);
        updateApplicationInstallRequest.setMarketplaceId(NotificationUtil.getMarketplaceId());
        updateApplicationInstallRequest.setPushInformation(pushInformation);
        updateApplicationInstallRequest.setOsNotificationState(NotificationUtil.checkIfNotificationsEnabled(context));
        updateApplicationInstallRequest.setSequenceNumber(Integer.valueOf(NotificationUtil.getAndIncreaseSequenceNumber(context)));
        mobilePushAppStateService(context, 3).updateApplicationInstallAsync(updateApplicationInstallRequest, resultHandler);
        Log.d(TAG, "UpdateApplicationInstallRequest fired successfully");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized MobilePushFrontendExternalService mobileNotificationService(Context context) {
        if (this.mMobileNotificationService == null) {
            this.mMobileNotificationService = new MobilePushFrontendExternalServiceClientImp();
        }
        try {
        } catch (NativeException e) {
            Log.e(TAG, "Can't resolve the server", e);
        }
        if (this.mMobileNotificationService.getEndpoint().isEmpty()) {
            this.mMobileNotificationService.setEndpoint(getServiceBaseUrl(context) + "pfe/");
            this.mMobileNotificationService.setHttpURLConnectionFactory(new AuthenticatedHttpURLConnectionFactory(NotificationUtil.getAuthToken(context, this.mOperationInProgress)));
        } else {
            this.mMobileNotificationService.setHttpURLConnectionFactory(new AuthenticatedHttpURLConnectionFactory(NotificationUtil.getAuthToken(context, this.mOperationInProgress)));
        }
        return this.mMobileNotificationService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRegisterSuccess(RegisterApplicationInstallResponse registerApplicationInstallResponse) {
        technicalMetricsCollection.stopTimer("RegisterApplicationLatency");
        technicalMetricsCollection.incrementCounter("sonarPushMessaging:registerApplicationSuccess", 1.0d);
        String applicationInstallId = registerApplicationInstallResponse.getApplicationInstallId();
        this.mAppInstallId = applicationInstallId;
        MetricsUtil.sendEventToMTS("registration.successful", applicationInstallId, "", "sonar", "regular");
        String str = TAG;
        Log.d(str, "App registration successful - App Id " + this.mAppInstallId);
        NotificationUtil.setRegisteredAtTimestamp(this.mContext);
        mRegisterInProgress.set(false);
        MarketplaceStateChangedListener marketplaceStateChangedListener = new MarketplaceStateChangedListener(this.mContext);
        this.marketplaceStateChangedListener = marketplaceStateChangedListener;
        marketplaceStateChangedListener.registerListener();
        if (TextUtils.isEmpty(this.mAppInstallId)) {
            Log.e(str, "App Install Id received from Sonar is empty");
        } else {
            SonarPushCacheManager.putString(this.mContext, "appInstallId", this.mAppInstallId);
            updateAppInfo(this.mContext);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRegisterFailure() {
        technicalMetricsCollection.stopTimer("RegisterApplicationLatency");
        technicalMetricsCollection.incrementCounter("sonarPushMessaging:registerApplicationFailure", 1.0d);
        MetricsUtil.sendEventToMTS("registration.failed", NotificationUtil.getApplicationInstallId(this.mContext), "", "sonar", "regular");
        Log.d(TAG, "RegisterFailure -- Application install ID not fetched from Kiang");
        mRegisterInProgress.set(false);
    }

    private String getServiceBaseUrl(Context context) {
        String domain = SonarEndpointsProvider.get().getDomain();
        return (NotificationUtil.getBuildType(context).equals("PROD") ? "https://msh." : "https://shm.") + domain + "/";
    }

    private void recordCustomerPushToggleServiceMetrics() {
        Map<String, Boolean> map = SonarPushCacheManager.getMap(this.mContext, "arePushTopicsOptedIn");
        if (map == null || map.isEmpty()) {
            Log.d(TAG, "PushTopicOptInMap contents are null or empty ");
            return;
        }
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            Log.d(TAG, "After customer toggle Push topic " + entry.getKey() + " has subscription status " + entry.getValue() + " for App Id " + NotificationUtil.getApplicationInstallId(this.mContext));
            TechnicalMetricsCollection technicalMetricsCollection2 = technicalMetricsCollection;
            StringBuilder sb = new StringBuilder();
            sb.append("customerPushToggle:");
            sb.append(entry.getKey());
            sb.append(":");
            sb.append(entry.getValue());
            technicalMetricsCollection2.incrementCounter(sb.toString(), 1.0d);
            MetricsUtil.sendEventToMTS("pushToggle:" + entry.getKey() + ":" + entry.getValue(), NotificationUtil.getApplicationInstallId(this.mContext), "", "sonar", "regular");
        }
    }

    private MobilePushFrontendExternalAppStateService mobilePushAppStateService(Context context, int i) {
        try {
            if (this.mMobileNotificationAppStateService.getEndpoint().isEmpty()) {
                this.mMobileNotificationAppStateService.setEndpoint(getServiceBaseUrl(context) + "kfe/");
                Log.d(TAG, "mobilePushAppStateService - PushEndPoint Set to : " + this.mMobileNotificationAppStateService.getEndpoint());
            }
        } catch (NativeException e) {
            Log.e(TAG, "Can't resolve the server", e);
        }
        this.mMobileNotificationAppStateService.setHttpURLConnectionFactory(new AuthenticatedHttpURLConnectionFactory(NotificationUtil.getAuthToken(context, "SignOut")));
        this.mMobileNotificationAppStateService.setMaxRetry(i);
        return this.mMobileNotificationAppStateService;
    }

    private static class AppRegisterHandler implements ResultHandler {
        private final Context mContext;
        private final PFENotificationService mService;

        private AppRegisterHandler(PFENotificationService pFENotificationService, Context context) {
            this.mService = pFENotificationService;
            this.mContext = context;
        }

        @Override // com.amazon.CoralAndroidClient.ClientBase.ResultHandler
        public void onSuccess(ClientOutput clientOutput) {
            if (clientOutput instanceof RegisterApplicationInstallResponse) {
                this.mService.onRegisterSuccess((RegisterApplicationInstallResponse) clientOutput);
            }
        }

        @Override // com.amazon.CoralAndroidClient.ClientBase.ResultHandler
        public void onException(ClientException clientException) {
            this.mService.onRegisterFailure();
        }
    }

    private class UpdateAppInfo implements ResultHandler {
        private UpdateAppInfo() {
        }

        @Override // com.amazon.CoralAndroidClient.ClientBase.ResultHandler
        public void onSuccess(ClientOutput clientOutput) {
            PFENotificationService.technicalMetricsCollection.stopTimer("UpdateApplicationLatency");
            Log.d(PFENotificationService.TAG, "Successful operation " + PFENotificationService.this.mOperationInProgress + " for App Id " + NotificationUtil.getApplicationInstallId(PFENotificationService.this.mContext));
            updateLocaleCacheIfNeeded();
            if (!PFENotificationService.this.mOperationInProgress.equals("RegisterWithFirstSignIn") && !PFENotificationService.this.mOperationInProgress.equals("AppVersionUpdate")) {
                CitadelClient.getInstance().updateCustomerState(PFENotificationService.this.mContext);
            }
            PFENotificationService.technicalMetricsCollection.incrementCounter("sonarPushMessaging:updateApplicationSuccess:".concat(PFENotificationService.this.mOperationInProgress), 1.0d);
            String applicationInstallId = NotificationUtil.getApplicationInstallId(PFENotificationService.this.mContext);
            MetricsUtil.sendEventToMTS("update.successful:".concat(PFENotificationService.this.mOperationInProgress), applicationInstallId, "", "sonar", "regular");
            if (PFENotificationService.this.mOperationInProgress.equals("SignIn") || PFENotificationService.this.mOperationInProgress.equals("RegisterWithFirstSignIn") || PFENotificationService.this.mOperationInProgress.equals("MarketPlaceUpdate") || PFENotificationService.this.mOperationInProgress.equals("AppVersionUpdate")) {
                SonarPushCacheManager.putString(PFENotificationService.this.mContext, "isCustomerSignedIn", "YES");
                try {
                    PFENotificationService pFENotificationService = PFENotificationService.this;
                    pFENotificationService.getAndSetSubscriptions(pFENotificationService.mContext);
                    return;
                } catch (NotificationException e) {
                    Log.e(PFENotificationService.TAG, "NotificationException encountered while calling updateSubscriptions ", e);
                    return;
                }
            }
            if (PFENotificationService.this.mOperationInProgress.equals("SignOut")) {
                SonarPushCacheManager.putString(PFENotificationService.this.mContext, "isCustomerSignedIn", "NO");
            } else if (PFENotificationService.this.mOperationInProgress.equals("LocaleUpdate")) {
                PFENotificationService.technicalMetricsCollection.incrementCounter("customerLocaleUpdate:", 1.0d);
                MetricsUtil.sendEventToMTS("customerLocaleUpdate:", applicationInstallId, "", "sonar", "regular");
            }
        }

        private void updateLocaleCacheIfNeeded() {
            if (NotificationUtil.shouldUpdateLocale(PFENotificationService.this.mContext)) {
                SonarPushCacheManager.putString(PFENotificationService.this.mContext, "currentLocale", NotificationUtil.getCurrentLocale(PFENotificationService.this.mContext));
            }
        }

        @Override // com.amazon.CoralAndroidClient.ClientBase.ResultHandler
        public void onException(ClientException clientException) {
            PFENotificationService.technicalMetricsCollection.stopTimer("UpdateApplicationLatency");
            PFENotificationService.technicalMetricsCollection.incrementCounter("sonarPushMessaging:updateApplicationFailure:".concat(PFENotificationService.this.mOperationInProgress), 1.0d);
            Log.e(PFENotificationService.TAG, "UpdateAppInfo exception: " + clientException.getMessage() + " for App Id " + NotificationUtil.getApplicationInstallId(PFENotificationService.this.mContext));
            MetricsUtil.sendEventToMTS("update.failed:".concat(PFENotificationService.this.mOperationInProgress), NotificationUtil.getApplicationInstallId(PFENotificationService.this.mContext), "", "sonar", "regular");
        }
    }

    private class GetSubscriptionsHandler implements ResultHandler {
        private GetSubscriptionsHandler() {
        }

        @Override // com.amazon.CoralAndroidClient.ClientBase.ResultHandler
        public void onSuccess(ClientOutput clientOutput) {
            if (clientOutput instanceof GetNotificationSubscriptionsResponse) {
                PFENotificationService.technicalMetricsCollection.incrementCounter("sonarPushgetSubscriptions.failedMessaging:getSubscriptionSuccess", 1.0d);
                MetricsUtil.sendEventToMTS("getSubscriptions.successful", NotificationUtil.getApplicationInstallId(PFENotificationService.this.mContext), "", "sonar", "regular");
                List<PushNotificationSubscription> subscriptions = ((GetNotificationSubscriptionsResponse) clientOutput).getSubscriptions();
                if (PFENotificationService.this.mOperationInProgress.equals("RegisterWithFirstSignIn")) {
                    PFENotificationService pFENotificationService = PFENotificationService.this;
                    pFENotificationService.mPushTopicOptInMap = NotificationUtil.cacheAndReturnDefaultSubscriptionValues(pFENotificationService.mContext);
                } else {
                    PFENotificationService pFENotificationService2 = PFENotificationService.this;
                    pFENotificationService2.mPushTopicOptInMap = NotificationUtil.getPushTopicsStatus(pFENotificationService2.mContext);
                    if (PFENotificationService.this.mPushTopicOptInMap == null || PFENotificationService.this.mPushTopicOptInMap.isEmpty()) {
                        Log.i(PFENotificationService.TAG, "Subscription app cache was null/empty for App Id " + NotificationUtil.getApplicationInstallId(PFENotificationService.this.mContext));
                        updateSubscriptionCache(subscriptions);
                        return;
                    }
                }
                boolean z = false;
                for (int i = 0; i < subscriptions.size(); i++) {
                    String title = subscriptions.get(i).getTitle();
                    String subscriptionId = subscriptions.get(i).getSubscriptionId();
                    if (title == null || title.isEmpty()) {
                        Log.i(PFENotificationService.TAG, "Removing push topic with subscription id " + subscriptionId);
                        subscriptions.remove(i);
                    } else {
                        PFENotificationService.this.mPushTopicOptInMap.putIfAbsent(subscriptionId, Boolean.FALSE);
                        z = subscriptionId.equals("MUSIC_profile") && !subscriptions.get(i).isSubscribed();
                        Log.i(PFENotificationService.TAG, "Received topic " + title + " with subscription status as " + subscriptions.get(i).isSubscribed() + ": cached status is " + PFENotificationService.this.mPushTopicOptInMap.get(subscriptionId));
                        subscriptions.get(i).setSubscribed(((Boolean) PFENotificationService.this.mPushTopicOptInMap.get(subscriptionId)).booleanValue());
                    }
                }
                Log.d(PFENotificationService.TAG, "GetSubscriptionsHandler - Successfully fetched subscription for App Id " + NotificationUtil.getApplicationInstallId(PFENotificationService.this.mContext));
                if (PFENotificationService.this.mOperationInProgress.equals("SubscriptionRetrieve") && !z) {
                    updateSubscriptionCache(subscriptions);
                } else {
                    PFENotificationService pFENotificationService3 = PFENotificationService.this;
                    pFENotificationService3.setSubscriptions(pFENotificationService3.mContext, subscriptions);
                }
            }
        }

        private void updateSubscriptionCache(List<PushNotificationSubscription> list) {
            for (int i = 0; i < list.size(); i++) {
                PushNotificationSubscription pushNotificationSubscription = list.get(i);
                PFENotificationService.this.mPushTopicOptInMap.put(pushNotificationSubscription.getSubscriptionId(), Boolean.valueOf(pushNotificationSubscription.isSubscribed()));
            }
            SonarPushCacheManager.putMap(PFENotificationService.this.mContext, "arePushTopicsOptedIn", PFENotificationService.this.mPushTopicOptInMap);
        }

        @Override // com.amazon.CoralAndroidClient.ClientBase.ResultHandler
        public void onException(ClientException clientException) {
            Log.e(PFENotificationService.TAG, "GetSubscriptionsHandler Exception " + clientException.getMessage() + " for App Id " + NotificationUtil.getApplicationInstallId(PFENotificationService.this.mContext));
            PFENotificationService.technicalMetricsCollection.incrementCounter("sonarPushMessaging:getSubscriptionFailure", 1.0d);
            MetricsUtil.sendEventToMTS("getSubscriptions.failed", NotificationUtil.getApplicationInstallId(PFENotificationService.this.mContext), "", "sonar", "regular");
        }
    }

    private class SetSubscriptionsHandler implements ResultHandler {
        private SetSubscriptionsHandler() {
        }

        @Override // com.amazon.CoralAndroidClient.ClientBase.ResultHandler
        public void onSuccess(ClientOutput clientOutput) {
            PFENotificationService.technicalMetricsCollection.stopTimer("SetSubscriptionLatency");
            PFENotificationService.technicalMetricsCollection.incrementCounter("sonarPushMessaging:setSubscriptionSuccess", 1.0d);
            MetricsUtil.sendEventToMTS("setSubscriptions.successful", NotificationUtil.getApplicationInstallId(PFENotificationService.this.mContext), "", "sonar", "regular");
            SonarPushCacheManager.putMap(PFENotificationService.this.mContext, "arePushTopicsOptedIn", PFENotificationService.this.mPushTopicOptInMap);
            CitadelClient.getInstance().updateCustomerState(PFENotificationService.this.mContext);
            SonarPushCacheManager.putBoolean(PFENotificationService.this.mContext, "newSubscriptionsSynced", true);
            Log.d(PFENotificationService.TAG, "SetSubscriptionsHandler - Subscription successful for App Id " + NotificationUtil.getApplicationInstallId(PFENotificationService.this.mContext));
        }

        @Override // com.amazon.CoralAndroidClient.ClientBase.ResultHandler
        public void onException(ClientException clientException) {
            PFENotificationService.technicalMetricsCollection.stopTimer("SetSubscriptionLatency");
            Log.e(PFENotificationService.TAG, "SetSubscriptionsHandler - Exception " + clientException.getMessage() + " for App Id " + NotificationUtil.getApplicationInstallId(PFENotificationService.this.mContext));
            PFENotificationService.technicalMetricsCollection.incrementCounter("sonarPushMessaging:setSubscriptionFailure", 1.0d);
            MetricsUtil.sendEventToMTS("setSubscriptions.failed", NotificationUtil.getApplicationInstallId(PFENotificationService.this.mContext), "", "sonar", "regular");
        }
    }
}
