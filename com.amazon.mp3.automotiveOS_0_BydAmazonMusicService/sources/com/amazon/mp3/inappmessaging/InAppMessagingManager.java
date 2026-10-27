package com.amazon.mp3.inappmessaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.ftu.FTUSettingsUtil;
import com.amazon.mp3.navigation.DeeplinkHandlerActivity;
import com.amazon.mp3.store.html5.map.MAPCookiesManager;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.featureflag.FeatureFlagUnavailableException;
import com.amazon.music.inappmessaging.external.IAMRequest;
import com.amazon.music.inappmessaging.external.IAMRequestBuilder;
import com.amazon.music.inappmessaging.external.InAppMessageBroadcasts;
import com.amazon.music.inappmessaging.external.InAppMessageClientApi;
import com.amazon.music.inappmessaging.external.model.Stage;
import com.amazon.music.voice.VoiceManagerSingleton;
import rx.Observable;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

/* JADX INFO: loaded from: classes3.dex */
public class InAppMessagingManager {
    private static final String TAG = "InAppMessagingManager";
    private static InAppMessageClientApi iamMessageApi;
    private static BehaviorSubject<Boolean> sIamSubject = BehaviorSubject.create();
    private static boolean sIsInitialized;
    private final BroadcastReceiver mAlexaBroadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.inappmessaging.InAppMessagingManager.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                Log.warning(InAppMessagingManager.TAG, "The intent action is null for InAppMessagingManager receiver. Doing nothing.");
                return;
            }
            if (!action.equals(InAppMessageBroadcasts.SPLASH_DISPLAYED)) {
                if (!action.equals("com.amazon.mp3.wakewordcommand.alexa.ui.opened") || InAppMessagingManager.iamMessageApi == null || InAppMessagingManager.this.mFragmentActivity == null) {
                    return;
                }
                Log.info(InAppMessagingManager.TAG, "Dismissing visible InAppMessaging splashes");
                InAppMessagingManager.iamMessageApi.dismissVisibleSplash(InAppMessagingManager.this.mFragmentActivity.getSupportFragmentManager());
                return;
            }
            synchronized (VoiceManagerSingleton.class) {
                if (VoiceManagerSingleton.isInitialized() && VoiceManagerSingleton.getInstance().isAlexaUiVisible()) {
                    Log.info(InAppMessagingManager.TAG, "Splash displayed, dismissing visible AlexaUI");
                    VoiceManagerSingleton.getInstance().closeAlexaUi();
                }
            }
        }
    };
    private final Context mContext;
    private FragmentActivity mFragmentActivity;

    public Observable<Boolean> getIamShownObservable() {
        return sIamSubject;
    }

    public InAppMessagingManager(final Context context) {
        this.mContext = context.getApplicationContext();
        registerReceiver();
    }

    public void loadInAppMessage() {
        String str = TAG;
        Log.debug(str, "loadInAppMessage()");
        if (sIsInitialized) {
            Log.debug(str, "IAM is already initialized");
            return;
        }
        Log.debug(str, "In-App Messaging feature is on");
        try {
            sIsInitialized = true;
            IAMRequest iAMRequestAppStartIAMRequest = appStartIAMRequest();
            if (initializeInAppMessaging(iAMRequestAppStartIAMRequest)) {
                iamMessageApi.loadInAppMessage(iAMRequestAppStartIAMRequest);
            }
        } catch (Exception e) {
            Log.error(TAG, "Failed to initialize in app messaging", e);
        }
    }

    public void displayInAppMessage(final FragmentActivity fragmentActivity, final IAMRequest request, final Boolean isFTU) {
        this.mFragmentActivity = fragmentActivity;
        if (request == null) {
            request = appStartIAMRequest();
        }
        BootstrapSingletonTask.get(this.mContext).registerFeatureFlagObserver().take(1).subscribe(new Action1<Void>() { // from class: com.amazon.mp3.inappmessaging.InAppMessagingManager.2
            @Override // rx.functions.Action1
            public void call(final Void aVoid) {
                if (InAppMessagingManager.supportsInAppMessaging()) {
                    Log.debug(InAppMessagingManager.TAG, "In-App Messaging feature is on");
                    try {
                        if (InAppMessagingManager.this.mFragmentActivity != null) {
                            boolean unused = InAppMessagingManager.sIsInitialized = true;
                            if (InAppMessagingManager.this.initializeInAppMessaging(request)) {
                                InAppMessagingManager.iamMessageApi.dismissVisibleSplash(InAppMessagingManager.this.mFragmentActivity.getSupportFragmentManager());
                                if (request.shouldThrottle()) {
                                    InAppMessagingManager.iamMessageApi.displayInAppMessage(InAppMessagingManager.this.mFragmentActivity.getSupportFragmentManager(), InAppMessagingManager.this.mContext, request, Boolean.valueOf(UserSubscriptionUtil.getUserSubscription().isNightwingOnly()), isFTU).subscribe(new Action1<Boolean>() { // from class: com.amazon.mp3.inappmessaging.InAppMessagingManager.2.1
                                        @Override // rx.functions.Action1
                                        public void call(final Boolean aBoolean) {
                                            InAppMessagingManager.sIamSubject.onNext(aBoolean);
                                        }
                                    });
                                } else {
                                    InAppMessagingManager.iamMessageApi.displayInAppMessage(InAppMessagingManager.this.mFragmentActivity.getSupportFragmentManager(), InAppMessagingManager.this.mContext, request, Boolean.valueOf(UserSubscriptionUtil.getUserSubscription().isNightwingOnly()), isFTU);
                                }
                            } else {
                                InAppMessagingManager.sIamSubject.onNext(Boolean.FALSE);
                            }
                        }
                        return;
                    } catch (Exception e) {
                        Log.error(InAppMessagingManager.TAG, "Failed to initialize in app messaging", e);
                        InAppMessagingManager.sIamSubject.onNext(Boolean.FALSE);
                        return;
                    }
                }
                Log.debug(InAppMessagingManager.TAG, "In-App Messaging feature is off");
            }
        });
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.amazon.mp3.wakewordcommand.alexa.ui.opened");
        intentFilter.addAction(InAppMessageBroadcasts.SPLASH_DISPLAYED);
        this.mContext.registerReceiver(this.mAlexaBroadcastReceiver, intentFilter);
    }

    public void onDestroy() {
        if (supportsInAppMessaging()) {
            this.mContext.unregisterReceiver(this.mAlexaBroadcastReceiver);
            this.mFragmentActivity = null;
        }
    }

    public static boolean supportsInAppMessaging() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().supportsInAppMessaging();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable", e);
            return FeatureFlagsProvider.getNoOpProvider().supportsInAppMessaging();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean initializeInAppMessaging(final IAMRequest request) throws Exception {
        boolean zShouldThrottle = request.shouldThrottle();
        if (DeeplinkHandlerActivity.isAppOpenedFromDeeplink() && !FTUSettingsUtil.shouldShowIAMForFTUDeeplink() && zShouldThrottle) {
            Log.debug(TAG, "Not initializing IAM due to non-IAM deeplink");
            return false;
        }
        FTUSettingsUtil.setShowIAMForFTUDeeplink(false);
        Log.debug(TAG, "Initializing In-App Messaging");
        return initialize();
    }

    private String getIamEndpointUrl() throws Exception {
        if (!SettingsUtil.isCustomEndpointEnabled(this.mContext)) {
            return null;
        }
        return SettingsUtil.getPrefs(this.mContext).getString(this.mContext.getString(R.string.setting_key_dev_iam_endpoint), null);
    }

    private boolean shouldUseCustomDynMsgEndpoint() {
        return SettingsUtil.isCustomEndpointEnabled(AmazonApplication.getContext()) && Stage.GAMMA.toString().equals(Environment.DYNMSG_URL.getCurrentOverrideId());
    }

    public static void forceRefresh() {
        if (supportsInAppMessaging()) {
            sIsInitialized = false;
            InAppMessageClientApi.forceRefresh();
        }
    }

    public IAMRequest appStartIAMRequest() {
        return new IAMRequestBuilder().shouldThrottle(true).withTrigger("appStart").build();
    }

    private boolean initialize() throws Exception {
        IAMCredentialsVendorImpl iAMCredentialsVendorImpl = new IAMCredentialsVendorImpl(AccountCredentialStorage.get(this.mContext), AmazonApplication.getAuthInfoMAPR5(), MAPCookiesManager.getCookiesManager(), AccountDetailUtil.get(this.mContext), ApplicationAttributes.getInstance(this.mContext));
        IAMDeviceInformationVendorImpl iAMDeviceInformationVendorImpl = new IAMDeviceInformationVendorImpl(this.mContext);
        String iamEndpointUrl = getIamEndpointUrl();
        iamMessageApi = InAppMessageClientApi.newInstance(iAMCredentialsVendorImpl, iAMDeviceInformationVendorImpl, iamEndpointUrl, shouldUseCustomDynMsgEndpoint(), new IAMWebViewDeeplinkHandlerImpl(this.mContext), this.mContext, AccountDetailUtil.get(this.mContext).getHomeMarketPlace().getObfuscatedId(), AccountDetailUtil.getMusicTerritoryOfResidence());
        Log.debug(TAG, "IAM endpoint URL is set to " + iamEndpointUrl);
        return iamMessageApi != null;
    }
}
