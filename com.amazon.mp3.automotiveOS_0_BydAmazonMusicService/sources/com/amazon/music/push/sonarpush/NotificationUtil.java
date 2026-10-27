package com.amazon.music.push.sonarpush;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationManagerCompat;
import com.amazon.KiangService.ApplicationInformation;
import com.amazon.KiangService.DeviceUniqueID;
import com.amazon.KiangService.PushInformation;
import com.amazon.music.account.AccountManager;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.authentication.mapr5.OAuthProviderMAPR5;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.MetricsManager;
import com.amazon.music.metrics.mts.event.definition.push.PushCampaignEvent;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.push.util.PushSchedulers;
import com.google.android.gms.stats.CodePackage;
import java.util.HashMap;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationUtil {
    private static final String TAG = "NotificationUtil";
    private static String authToken = "";

    public static PushInformation getPushInformation(Context context, String str) {
        if (PushNotificationManager.getInstance() == null || PushNotificationManager.getInstance().getNotificationProvider() == null || str.equals("SignOut")) {
            return null;
        }
        return pushInformationFromNotificationTarget(PushNotificationManager.getInstance().getNewNotificationTarget(context, SonarPushCacheManager.getString(context, "fcmToken")));
    }

    public static PushInformation pushInformationFromNotificationTarget(NotificationTarget notificationTarget) {
        if (notificationTarget == null) {
            return null;
        }
        PushInformation pushInformation = new PushInformation();
        pushInformation.setSecret(notificationTarget.getDestination());
        pushInformation.setProvider(getProviderNameFromType(notificationTarget.getType()));
        pushInformation.setProviderKey(notificationTarget.getApplicationId());
        return pushInformation;
    }

    public static String getProviderNameFromType(int i) {
        if (i == 1) {
            return CodePackage.GCM;
        }
        Log.e(TAG, "Invalid Push Provider Type " + i);
        return null;
    }

    public static ApplicationInformation getAppInformation(Context context) {
        ApplicationInformation applicationInformation = new ApplicationInformation();
        String str = Build.MANUFACTURER + " " + Build.MODEL;
        String appVersion = getAppVersion(context);
        applicationInformation.setUbid("");
        applicationInformation.setHardwareIdentifier(str);
        applicationInformation.setOsIdentifier("Android");
        applicationInformation.setOsVersion(Build.VERSION.RELEASE);
        applicationInformation.setApplicationIdentifier("Music");
        applicationInformation.setApplicationVersion(appVersion);
        applicationInformation.setAssociateTag("");
        applicationInformation.setDeviceUniqueId(getDeviceUniqueId());
        return applicationInformation;
    }

    public static String getAppVersion(Context context) {
        return SonarPushCacheManager.getString(context, Splash.PARAMS_APP_VERSION);
    }

    public static DeviceUniqueID getDeviceUniqueId() {
        DeviceUniqueID deviceUniqueID = new DeviceUniqueID();
        String deviceId = getDeviceId();
        deviceUniqueID.setType("UDID");
        deviceUniqueID.setValue(deviceId);
        return deviceUniqueID;
    }

    public static String getDeviceId() {
        try {
            return ((DeviceInfoProvider) Providers.INSTANCE.get(DeviceInfoProvider.class)).getDeviceIdentity().getDeviceId();
        } catch (Exception e) {
            Log.w(TAG, "Unable to get device id from Platform Providers: " + e.getMessage());
            return null;
        }
    }

    public static String getApplicationInstallId(Context context) {
        return SonarPushCacheManager.getString(context, "appInstallId");
    }

    public static String getMarketplaceId() {
        AccountManager accountManager = AccountManagerSingleton.get();
        SonarEndpointsProvider sonarEndpointsProvider = SonarEndpointsProvider.get();
        String musicTerritory = accountManager.getMusicTerritory();
        if (sonarEndpointsProvider.isROWTerritory(musicTerritory)) {
            return sonarEndpointsProvider.getROWMarketPlaceId(musicTerritory);
        }
        return accountManager.getMarketplace().getObfuscatedId();
    }

    public static synchronized int getAndIncreaseSequenceNumber(Context context) {
        int i;
        i = SonarPushCacheManager.getInt(context, "updateSequenceNumber") + 1;
        SonarPushCacheManager.putInt(context, "updateSequenceNumber", i);
        return i;
    }

    public static int getSequenceNumber(Context context) {
        return SonarPushCacheManager.getInt(context, "updateSequenceNumber");
    }

    public static String getAuthToken(Context context, String str) {
        if (str.equals("SignOut") || str.equals("CitadelStateUpdate")) {
            return authToken;
        }
        refreshAuthToken(context);
        return authToken;
    }

    private static void refreshAuthToken(Context context) {
        try {
            AccountManager accountManager = AccountManagerSingleton.get();
            if (context == null || accountManager == null || accountManager.getUser() == null) {
                Log.w(TAG, "Could not retrieve auth token");
                authToken = "";
            } else {
                authToken = new OAuthProviderMAPR5(context).getOAuthInfo().accessToken;
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception while retrieving accesToken:", e);
        }
    }

    public static String getUserId(Context context) {
        return SonarPushCacheManager.getString(context, "currentUserId");
    }

    public static String checkIfNotificationsEnabled(Context context) {
        return NotificationManagerCompat.from(context).areNotificationsEnabled() ? "ACTIVE" : "INACTIVE";
    }

    public static String getCurrentLocale(Context context) {
        String string = context.getResources().getConfiguration().locale.toString();
        return TextUtils.isEmpty(string) ? AccountManagerSingleton.get().getMarketplace().getLocale().iterator().next() : string;
    }

    public static boolean shouldUpdateLocale(Context context) {
        String currentLocale = getCurrentLocale(context);
        String string = SonarPushCacheManager.getString(context, "currentLocale");
        return (TextUtils.isEmpty(currentLocale) || TextUtils.isEmpty(string) || TextUtils.equals(currentLocale, string)) ? false : true;
    }

    public static String isCustomerSignedIn(Context context) {
        String string = SonarPushCacheManager.getString(context, "isCustomerSignedIn");
        if (!string.isEmpty()) {
            refreshAuthToken(context);
        }
        return string;
    }

    public static String getBuildType(Context context) {
        String string = SonarPushCacheManager.getString(context, "buildType");
        return string.equals("") ? "DEBUG" : string;
    }

    public static Map<String, Boolean> getPushTopicsStatus(Context context) {
        return SonarPushCacheManager.getMap(context, "arePushTopicsOptedIn");
    }

    public static Map<String, Boolean> cacheAndReturnDefaultSubscriptionValues(Context context) {
        HashMap map = new HashMap();
        Boolean bool = Boolean.TRUE;
        map.put("MUSIC_promo", bool);
        map.put("MUSIC_social", bool);
        map.put("MUSIC_profile", bool);
        map.put("MUSIC_live_stream", bool);
        map.put("MUSIC_merch", bool);
        map.put("MUSIC_podcast", bool);
        map.put("MUSIC_video", bool);
        SonarPushCacheManager.putMap(context, "arePushTopicsOptedIn", map);
        return map;
    }

    public static void updatePushNotificationSubscriptionCache(Context context, Map<String, Boolean> map) {
        map.put("MUSIC_profile", Boolean.TRUE);
        SonarPushCacheManager.putMap(context, "arePushTopicsOptedIn", map);
    }

    public static void setRegisteredAtTimestamp(Context context) {
        SonarPushCacheManager.putString(context, "sonarRegisteredAt", Long.toString(System.currentTimeMillis()));
    }

    public static String getRegisteredAtTimestamp(Context context) {
        return SonarPushCacheManager.getString(context, "sonarRegisteredAt");
    }

    public static void publishPushDeliveredMetrics(Context context, String str) {
        MetricsHolder.getManager();
        publishEventToMTS(PushCampaignEvent.builder("posted_notification", getApplicationInstallId(context), str, "sonar", "regular").build(), "posted_notification", str);
    }

    public static void publishPushClickedMetrics(Context context, String str) {
        publishEventToMTS(PushCampaignEvent.builder("notification_opened", getApplicationInstallId(context), str, "sonar", "regular").build(), "notification_opened", str);
    }

    private static void publishEventToMTS(final PushCampaignEvent pushCampaignEvent, final String str, final String str2) {
        final MetricsManager manager = MetricsHolder.getManager();
        Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.music.push.sonarpush.NotificationUtil.1
            @Override // rx.functions.Action1
            public void call(Subscriber<? super Void> subscriber) {
                Log.i(NotificationUtil.TAG, "Publishing push notification metrics to MTS with actionType " + str + " and campaignId " + str2);
                manager.handleEvent(pushCampaignEvent);
            }
        }).subscribeOn(PushSchedulers.getPushScheduler()).subscribe();
    }
}
