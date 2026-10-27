package com.amazon.music.push;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.music.account.AccountManager;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.push.citadel.CitadelManager;
import com.amazon.music.push.sonarpush.NotificationUtil;
import com.amazon.music.push.sonarpush.PushNotificationManager;
import com.amazon.music.push.sonarpush.SonarPushCacheManager;
import com.amazon.music.push.util.PushSchedulers;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;
import java.util.HashMap;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;

/* JADX INFO: loaded from: classes4.dex */
public class PushMessagingClient {
    public static final String TAG = "PushMessagingClient";
    private static DeviceInfo sDeviceInfo;
    private static BehaviorSubject<Void> sPushMessagingClientPublishSubject = BehaviorSubject.create();
    private static Builder sBuilder = null;
    private static Action1<Throwable> onErrorHandler = new Action1<Throwable>() { // from class: com.amazon.music.push.PushMessagingClient.2
        @Override // rx.functions.Action1
        public void call(Throwable th) {
            Log.w(PushMessagingClient.TAG, "Error in observable", th);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static Observable<Void> get() {
        return sPushMessagingClientPublishSubject.filter(new Func1<Void, Boolean>() { // from class: com.amazon.music.push.PushMessagingClient.1
            @Override // rx.functions.Func1
            public Boolean call(Void r1) {
                return Boolean.TRUE;
            }
        }).take(1).observeOn(AndroidSchedulers.mainThread());
    }

    public static final class Builder {
        private final AccountManager accountManager;
        private final BuildType buildType;
        private final InstallType installType;
        private final PlatformType platformType;

        private Builder(BuildType buildType, PlatformType platformType, InstallType installType, AccountManager accountManager) {
            this.buildType = buildType;
            this.platformType = platformType;
            this.installType = installType;
            this.accountManager = accountManager;
        }

        public void configureForNewSession(Context context) {
            if (PushMessagingClient.sDeviceInfo == null || !PushMessagingClient.sDeviceInfo.isFireOS().booleanValue()) {
                final Context applicationContext = context.getApplicationContext();
                Builder unused = PushMessagingClient.sBuilder = this;
                PushMessagingClient.sPushMessagingClientPublishSubject.onNext(null);
                try {
                    PushMessagingClient.updateLocaleInSonarIfChanged(context);
                } catch (Exception e) {
                    Log.w(PushMessagingClient.TAG, "Error while verifying locale change ", e);
                }
                PushMessagingClient.get().subscribeOn(PushSchedulers.getPushScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Void>() { // from class: com.amazon.music.push.PushMessagingClient.Builder.1
                    @Override // rx.functions.Action1
                    public void call(Void r2) {
                        PushMessagingClient.updateSonarPushCache(applicationContext, Builder.this.buildType);
                        PushMessagingClient.sPushMessagingClientPublishSubject.onNext(null);
                    }
                }, PushMessagingClient.onErrorHandler);
            }
        }
    }

    public static Builder builder(BuildType buildType, PlatformType platformType, InstallType installType, AccountManager accountManager) {
        return new Builder(buildType, platformType, installType, accountManager);
    }

    @SuppressWarnings({"ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD"})
    public void setDeviceInfo(DeviceInfo deviceInfo, final Context context) {
        sDeviceInfo = deviceInfo;
        Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.music.push.PushMessagingClient.3
            @Override // rx.functions.Action1
            public void call(Subscriber<? super Void> subscriber) {
                try {
                    PushMessagingClient.this.updateAppVersionInSonarIfChanged(context);
                    CitadelManager.getInstance();
                    CitadelManager.performHealthCheck(context);
                } catch (Exception e) {
                    Log.w("Exception encountered while setting device info in Sonar/Citadel ", e);
                }
            }
        }).subscribeOn(PushSchedulers.getPushScheduler()).subscribe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAppVersionInSonarIfChanged(Context context) {
        if (TextUtils.equals(NotificationUtil.isCustomerSignedIn(context), "YES")) {
            DeviceInfo deviceInfo = sDeviceInfo;
            String appVersion = deviceInfo == null ? null : deviceInfo.getAppVersion();
            String appVersion2 = NotificationUtil.getAppVersion(context);
            if (TextUtils.isEmpty(appVersion) || TextUtils.isEmpty(appVersion2) || TextUtils.equals(appVersion, appVersion2)) {
                return;
            }
            PushNotificationManager.getInstance().updateApplicationAppVersion(context, appVersion);
            updatePreferencesIfNeeded(context);
        }
    }

    private static void updatePreferencesIfNeeded(Context context) {
        if (SonarPushCacheManager.getBoolean(context, "newSubscriptionsSynced")) {
            return;
        }
        SonarPushCacheManager.getMap(context, "arePushTopicsOptedIn");
        setDefaultValueForNewTopics(context);
        PushNotificationManager.getInstance().updatePushNotificationSubscription(context, SonarPushCacheManager.getMap(context, "arePushTopicsOptedIn"));
    }

    private static void setDefaultValueForNewTopics(Context context) {
        Map map = SonarPushCacheManager.getMap(context, "arePushTopicsOptedIn");
        if (map == null) {
            map = new HashMap();
        }
        Boolean bool = Boolean.TRUE;
        map.put("MUSIC_live_stream", bool);
        map.put("MUSIC_merch", bool);
        map.put("MUSIC_podcast", bool);
        map.put("MUSIC_video", bool);
        SonarPushCacheManager.putMap(context, "arePushTopicsOptedIn", map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateLocaleInSonarIfChanged(final Context context) {
        Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.music.push.PushMessagingClient.4
            @Override // rx.functions.Action1
            public void call(Subscriber<? super Void> subscriber) {
                if (TextUtils.equals(NotificationUtil.isCustomerSignedIn(context), "YES") && NotificationUtil.shouldUpdateLocale(context)) {
                    PushNotificationManager.getInstance().updateApplicationLocaleChanged(context);
                }
            }
        }).subscribeOn(PushSchedulers.getPushScheduler()).subscribe();
    }

    public void setUserId(String str, Context context, String str2) {
        Context applicationContext = context.getApplicationContext();
        boolean zEquals = SonarPushCacheManager.getString(applicationContext, "isCustomerSignedIn").equals("YES");
        SonarPushCacheManager.putString(applicationContext, "currentUserId", str);
        if (isOkToSignIn(str2, str, zEquals)) {
            SonarPushCacheManager.putString(applicationContext, "isCustomerSignedIn", "YES");
            SonarPushCacheManager.putString(applicationContext, "currentLocale", NotificationUtil.getCurrentLocale(applicationContext));
            PushNotificationManager.getInstance().updateApplicationSignIn(applicationContext);
        }
    }

    private boolean isOkToSignIn(String str, String str2, boolean z) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || z) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateSonarPushCache(Context context, BuildType buildType) {
        SonarPushCacheManager.putString(context, Splash.PARAMS_APP_VERSION, sDeviceInfo.getAppVersion());
        SonarPushCacheManager.putString(context, "marketPlaceId", sDeviceInfo.getMarketplace().getObfuscatedId());
        SonarPushCacheManager.putString(context, "territory", sDeviceInfo.getTerritory());
        SonarPushCacheManager.putString(context, "buildType", buildType.toString());
    }

    public void setNotificationPreferenceInPFE(Context context, Map<String, Boolean> map) {
        PushNotificationManager.getInstance().updatePushNotificationSubscription(context, map);
    }

    public Map<String, Boolean> getNotificationPreference(Context context, Boolean bool) {
        boolean z = SonarPushCacheManager.getBoolean(context, "subscriptionsynced");
        if (SonarPushCacheManager.getString(context, "isCustomerSignedIn").equals("YES") && (bool.booleanValue() || !z)) {
            Log.i(TAG, "Refreshing subscription in device cache");
            SonarPushCacheManager.putBoolean(context, "subscriptionsynced", true);
            PushNotificationManager.getInstance().retrieveAndCachePushNotificationSubscription(context);
        }
        return getNotificationPreferencefromPFE(context);
    }

    public Map<String, Boolean> getNotificationPreferencefromPFE(Context context) {
        Map<String, Boolean> map = SonarPushCacheManager.getMap(context, "arePushTopicsOptedIn");
        return (map == null || map.isEmpty()) ? NotificationUtil.cacheAndReturnDefaultSubscriptionValues(context) : map;
    }

    public static DeviceInfo getDeviceInfo() {
        return sDeviceInfo;
    }

    public void setSignedIn(final Context context, final boolean z) {
        get().subscribe(new Action1<Void>() { // from class: com.amazon.music.push.PushMessagingClient.5
            @Override // rx.functions.Action1
            public void call(Void r2) {
                if (z) {
                    return;
                }
                PushNotificationManager.getInstance().updateApplicationSignOut(context.getApplicationContext());
            }
        }, onErrorHandler);
    }
}
