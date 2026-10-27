package com.amazon.mp3.inappmessaging;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.config.AssociateTag;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.store.html5.map.MAPCookiesManager;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.featureflag.FeatureFlagUnavailableException;
import com.amazon.music.inappmessaging.external.DynamicMessageOrchestrator;
import com.amazon.music.inappmessaging.external.WeblabHelper;
import com.amazon.music.inappmessaging.external.events.DynamicMessageEventSender;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.Stage;
import com.amazon.music.inappmessaging.internal.util.SplashUpsellV2SupportedFeature;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingErrorCode;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import rx.Observable;

/* JADX INFO: loaded from: classes3.dex */
public class DynamicMessagingManager {
    private static final String TAG = "DynamicMessagingManager";
    private static DynamicMessageOrchestrator mDynamicMessageOrchestrator;
    private static boolean mIsDynMsgInitialized;
    private final Context mContext;

    public DynamicMessagingManager(final Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static boolean isDynamicMessagingSupported() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isDynamicMessagingSupported();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable", e);
            return FeatureFlagsProvider.getNoOpProvider().isDynamicMessagingSupported();
        }
    }

    public Observable<Boolean> displayDynamicMessage(final FragmentActivity fragmentActivity, final DynamicMessageEvent event, final DynamicMessageMetricsAttributes metricsAttributes) {
        DynamicMessageOrchestrator dynamicMessageOrchestrator = mDynamicMessageOrchestrator;
        if (dynamicMessageOrchestrator != null) {
            return dynamicMessageOrchestrator.displayDynamicMessage(fragmentActivity, event, metricsAttributes);
        }
        String str = "Dynamic Message Orchestrator is null. Cannot display dynamic message for trigger " + event.getEvent();
        Log.error(TAG, str);
        DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR).withErrorCode(DynamicMessagingErrorCode.OTHER).withErrorMessage(str).withTrigger(event.getEvent()).send();
        return Observable.just(Boolean.FALSE);
    }

    public Observable<Fragment> displayNonBlockingDynamicMessage(final FragmentActivity fragmentActivity, final DynamicMessageEvent event, final DynamicMessageMetricsAttributes metricsAttributes) {
        DynamicMessageOrchestrator dynamicMessageOrchestrator = mDynamicMessageOrchestrator;
        if (dynamicMessageOrchestrator != null) {
            return dynamicMessageOrchestrator.displayNonBlockingDynamicMessage(fragmentActivity, event, metricsAttributes);
        }
        String str = "Dynamic Message Orchestrator is null. Cannot display nonBlocking dynamic message for trigger " + event.getEvent();
        Log.error(TAG, str);
        DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR).withErrorCode(DynamicMessagingErrorCode.OTHER).withErrorMessage(str).withTrigger(event.getEvent()).send();
        return Observable.just(null);
    }

    public synchronized void initializeDynMsg() {
        String str = TAG;
        Log.debug(str, "Load dynamic messages");
        if (mIsDynMsgInitialized) {
            Log.debug(str, "Dynamic Messaging is already initialized");
            return;
        }
        mIsDynMsgInitialized = true;
        try {
            if (!createDynMsgOrchestrator()) {
                throw new IllegalStateException("Dynamic Messaging Orchestrator was not created.");
            }
            WeblabHelper.getInstance().enableSplashV2(shouldUseSplashV2());
            WeblabHelper.getInstance().setIsFireOS(PlatformUtil.isFireOS());
            WeblabHelper.getInstance().setIsFireOS(PlatformUtil.isFireOS());
            WeblabHelper.getInstance().enableGPBLaunchedInTerritory(isGPBLaunchedInTerritory());
            WeblabHelper.getInstance().setIsCustomerAmazonWallet(isCustomerAmazonWallet());
            mDynamicMessageOrchestrator.loadDynamicMessage();
        } catch (Exception e) {
            mIsDynMsgInitialized = false;
            Log.error(TAG, "Failed to initialize dynamic messaging", e);
        }
    }

    public void loadDeeplinkDynamicMessage(final DynamicMessageEvent event, final boolean isFTU) {
        mDynamicMessageOrchestrator.loadDeeplinkDynamicMessage(event, isFTU);
    }

    public synchronized boolean isDynMsgInitialized() {
        return mIsDynMsgInitialized;
    }

    public static void clearCache() {
        if (mIsDynMsgInitialized) {
            mDynamicMessageOrchestrator.clearCache();
        }
    }

    private boolean createDynMsgOrchestrator() {
        IAMCredentialsVendorImpl iAMCredentialsVendorImpl = new IAMCredentialsVendorImpl(AccountCredentialStorage.get(this.mContext), AmazonApplication.getAuthInfoMAPR5(), MAPCookiesManager.getCookiesManager(), AccountDetailUtil.get(this.mContext), ApplicationAttributes.getInstance(this.mContext));
        IAMDeviceInformationVendorImpl iAMDeviceInformationVendorImpl = new IAMDeviceInformationVendorImpl(this.mContext);
        String homeMarketPlaceId = AccountDetailUtil.get(this.mContext).getHomeMarketPlaceId();
        boolean zShouldUseCustomEndpoint = shouldUseCustomEndpoint(this.mContext);
        Context context = this.mContext;
        DynamicMessageOrchestrator dynamicMessageOrchestratorNewInstance = DynamicMessageOrchestrator.newInstance(context, homeMarketPlaceId, iAMCredentialsVendorImpl, iAMDeviceInformationVendorImpl, AssociateTag.getInstance(context), zShouldUseCustomEndpoint);
        mDynamicMessageOrchestrator = dynamicMessageOrchestratorNewInstance;
        return dynamicMessageOrchestratorNewInstance != null;
    }

    private boolean shouldUseSplashV2() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        return (featureGateProvider != null && featureGateProvider.isFeatureAvailable(SplashUpsellV2SupportedFeature.splash_support_on_dynamic_messaging_v2)) && isDynamicMessagingSupported();
    }

    private boolean isGPBLaunchedInTerritory() {
        return AmazonApplication.getCapabilities().isGPBLaunchedInTerritory();
    }

    private boolean isCustomerAmazonWallet() {
        return UserSubscriptionUtil.getUserSubscription().isPrime();
    }

    public boolean shouldUseCustomEndpoint(final Context context) {
        return SettingsUtil.isCustomEndpointEnabled(context) && Stage.GAMMA.toString().equals(Environment.DYNMSG_URL.getCurrentOverrideId());
    }
}
