package com.amazon.device.ads;

import android.content.Context;
import android.util.Log;
import com.amazon.aps.shared.APSAnalytics;
import com.amazon.aps.shared.analytics.APSEventSeverity;
import com.amazon.aps.shared.analytics.APSEventType;
import com.amazon.device.ads.AdvertisingIdentifier;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AmazonOOAdRegistration {
    private static final String LOGTAG = "AmazonOOAdRegistration";
    private static final AmazonOOAdRegistrationExecutor executor = new AmazonOOAdRegistrationExecutor();

    public static final void setAppKey(String str) throws IllegalArgumentException {
        try {
            AmazonOOAdRegistrationExecutor amazonOOAdRegistrationExecutor = executor;
            if (amazonOOAdRegistrationExecutor != null) {
                amazonOOAdRegistrationExecutor.setAppKey(str);
            }
        } catch (RuntimeException e) {
            Log.e(LOGTAG, "Failed to execute setAppKey method. Detail: ", e);
            APSAnalytics.logEvent(APSEventSeverity.ERROR, APSEventType.EXCEPTION, "Fail to execute setAppKey method during runtime in AmazonOOAdRegistration class", e);
        }
    }

    public static final void registerApp(Context context) {
        try {
            int integerFromJSON = JSONUtils.getIntegerFromJSON(Settings.getInstance().getJSONObject("config-analyticsParams", new JSONObject()), "samplingRate", 1);
            APSAnalytics.init(context);
            APSAnalytics.setSamplingRate(integerFromJSON);
            AmazonOOAdRegistrationExecutor amazonOOAdRegistrationExecutor = executor;
            if (amazonOOAdRegistrationExecutor != null) {
                amazonOOAdRegistrationExecutor.registerApp(context);
            }
        } catch (RuntimeException e) {
            Log.e(LOGTAG, "Failed to execute registerApp method. Detail: ", e);
            APSAnalytics.logEvent(APSEventSeverity.FATAL, APSEventType.EXCEPTION, "Fail to execute registerApp method during runtime in AmazonOOAdRegistration class", e);
        }
    }

    public static void setAppName(String str) {
        try {
            AmazonOOAdRegistrationExecutor amazonOOAdRegistrationExecutor = executor;
            if (amazonOOAdRegistrationExecutor != null) {
                amazonOOAdRegistrationExecutor.setAppName(str);
            }
        } catch (RuntimeException e) {
            Log.e(LOGTAG, "Failed to execute setAppName method. Detail: ", e);
            APSAnalytics.logEvent(APSEventSeverity.ERROR, APSEventType.EXCEPTION, "Fail to execute setAppName method during runtime in AmazonOOAdRegistration class", e);
        }
    }

    public static void setAppDefinedMarketplace(String str) {
        try {
            AmazonOOAdRegistrationExecutor amazonOOAdRegistrationExecutor = executor;
            if (amazonOOAdRegistrationExecutor != null) {
                amazonOOAdRegistrationExecutor.setAppDefinedMarketplace(str);
            }
        } catch (RuntimeException e) {
            Log.e(LOGTAG, "Failed to execute setAppDefinedMarketplace method. Detail: ", e);
            APSAnalytics.logEvent(APSEventSeverity.ERROR, APSEventType.EXCEPTION, "Fail to execute setAppDefinedMarketplace method during runtime in AmazonOOAdRegistration class", e);
        }
    }

    @Deprecated
    public static void identifyAmazonUserUsingMAP() {
        AmazonOOAdRegistrationExecutor amazonOOAdRegistrationExecutor = executor;
        if (amazonOOAdRegistrationExecutor != null) {
            amazonOOAdRegistrationExecutor.identifyAmazonUserUsingMAP();
        }
    }

    public static void setReferrer(Context context, String str) {
        try {
            AmazonOOAdRegistrationExecutor amazonOOAdRegistrationExecutor = executor;
            if (amazonOOAdRegistrationExecutor != null) {
                amazonOOAdRegistrationExecutor.setReferrer(context, str);
            }
        } catch (RuntimeException e) {
            Log.e(LOGTAG, "Failed to execute setReferrer method. Detail: ", e);
            APSAnalytics.logEvent(APSEventSeverity.ERROR, APSEventType.EXCEPTION, "Fail to execute setReferrer method during runtime in AmazonOOAdRegistration class", e);
        }
    }

    public static class AmazonOOAdRegistrationInfo {
        private final AdvertisingIdentifier.Info advertisingIdentifierInfo;
        private final AmazonOOAdRegistrationExecutor amazonOOAdRegistrationExecutor;
        private final Configuration configuration;
        private final MobileAdsInfoStore infoStore;
        private final Settings settings;
        private final SystemTime systemTime;

        AmazonOOAdRegistrationInfo(AdvertisingIdentifier advertisingIdentifier, MobileAdsInfoStore mobileAdsInfoStore, Settings settings, Configuration configuration, SystemTime systemTime, AmazonOOAdRegistrationExecutor amazonOOAdRegistrationExecutor) {
            this.advertisingIdentifierInfo = advertisingIdentifier.getAdvertisingIdentifierInfo();
            this.infoStore = mobileAdsInfoStore;
            this.settings = settings;
            this.configuration = configuration;
            this.systemTime = systemTime;
            this.amazonOOAdRegistrationExecutor = amazonOOAdRegistrationExecutor;
        }
    }
}
