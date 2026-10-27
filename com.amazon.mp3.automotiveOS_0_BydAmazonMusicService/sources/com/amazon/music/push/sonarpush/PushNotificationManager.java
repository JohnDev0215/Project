package com.amazon.music.push.sonarpush;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.MetricsManager;
import com.amazon.music.metrics.mts.event.definition.push.PushCampaignEvent;
import com.amazon.music.push.citadel.CitadelClient;
import com.google.android.gms.stats.CodePackage;
import com.google.firebase.FirebaseApp;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class PushNotificationManager {
    private static final String TAG = "PushNotificationManager";
    private static PushNotificationManager pushManager;

    public String getNotificationProvider() {
        return CodePackage.GCM;
    }

    private PushNotificationManager() {
    }

    public static synchronized PushNotificationManager getInstance() {
        if (pushManager == null) {
            pushManager = new PushNotificationManager();
        }
        return pushManager;
    }

    void registerApplication(Context context, String str) {
        Log.d(TAG, "Register Application Attempt");
        new PFENotificationService(context, str).registerApplication(context);
    }

    void updateApplication(Context context, String str) {
        new PFENotificationService(context, str).updateAppInfo(context);
    }

    public void updateApplicationSignIn(Context context) {
        String applicationInstallId = NotificationUtil.getApplicationInstallId(context);
        if (!TextUtils.isEmpty(applicationInstallId)) {
            Log.d(TAG, "Signing in customer with App Id " + applicationInstallId);
            updateApplication(context, "SignIn");
            return;
        }
        registerApplication(context, "RegisterWithFirstSignIn");
    }

    public void updateApplicationFCMToken(Context context, String str) {
        String applicationInstallId = NotificationUtil.getApplicationInstallId(context);
        if (str.equals(SonarPushCacheManager.getString(context, "fcmToken"))) {
            Log.w(TAG, "Attempt to update duplicate FCM token detected for App Id " + applicationInstallId);
            return;
        }
        Log.d(TAG, "Updating FCM token for App Id " + applicationInstallId);
        SonarPushCacheManager.putString(context, "fcmToken", str);
        if (!TextUtils.isEmpty(applicationInstallId)) {
            updateApplication(context, "FCMTokenUpdate");
        } else {
            registerApplication(context, "RegisterWithFCMTokenUpdate");
        }
    }

    public void updateApplicationSignOut(Context context) {
        if (SonarPushCacheManager.getString(context, "isCustomerSignedIn").equals("YES")) {
            String applicationInstallId = NotificationUtil.getApplicationInstallId(context);
            if (TextUtils.isEmpty(applicationInstallId)) {
                return;
            }
            Log.d(TAG, "Signing out customer with App Id " + applicationInstallId);
            updateApplication(context, "SignOut");
        }
    }

    public void updateApplicationMarketPlaceChanged(Context context) {
        boolean zEquals = SonarPushCacheManager.getString(context, "isCustomerSignedIn").equals("YES");
        SonarPushCacheManager.putString(context, "marketPlaceId", NotificationUtil.getMarketplaceId());
        if (!zEquals) {
            Log.d(TAG, "Customer NOT signed in, not proceeding with Kiang marketPlace update, App Id " + NotificationUtil.getApplicationInstallId(context));
            return;
        }
        String applicationInstallId = NotificationUtil.getApplicationInstallId(context);
        if (TextUtils.isEmpty(applicationInstallId)) {
            return;
        }
        Log.d(TAG, "Changing marketplace for customer with App Id " + applicationInstallId);
        updateApplication(context, "MarketPlaceUpdate");
    }

    public void updateApplicationLocaleChanged(Context context) {
        boolean zEquals = SonarPushCacheManager.getString(context, "isCustomerSignedIn").equals("YES");
        String applicationInstallId = NotificationUtil.getApplicationInstallId(context);
        if (!zEquals) {
            Log.d(TAG, "Customer NOT signed in, not proceeding with locale update, App Id " + applicationInstallId);
            return;
        }
        if (TextUtils.isEmpty(applicationInstallId)) {
            return;
        }
        Log.d(TAG, "Updating locale for customer with App Id " + applicationInstallId);
        updateApplication(context, "LocaleUpdate");
    }

    public void updateApplicationAppVersion(Context context, String str) {
        boolean zEquals = SonarPushCacheManager.getString(context, "isCustomerSignedIn").equals("YES");
        String applicationInstallId = NotificationUtil.getApplicationInstallId(context);
        if (!zEquals) {
            Log.d(TAG, "Customer NOT signed in, not proceeding with app version update, App Id " + applicationInstallId);
            return;
        }
        if (!TextUtils.isEmpty(applicationInstallId)) {
            SonarPushCacheManager.putString(context, Splash.PARAMS_APP_VERSION, str);
            Log.d(TAG, "Updating app version for customer with App Id " + applicationInstallId);
            updateApplication(context, "AppVersionUpdate");
            return;
        }
        registerApplication(context, "AppVersionUpdate");
    }

    public void updatePushNotificationSubscription(Context context, Map<String, Boolean> map) {
        String applicationInstallId = NotificationUtil.getApplicationInstallId(context);
        if (TextUtils.isEmpty(applicationInstallId)) {
            return;
        }
        sendSubscriptionEventToMTS(context, map);
        Log.d(TAG, "Updating notification subscription for customer with App Id " + applicationInstallId);
        PFENotificationService pFENotificationService = new PFENotificationService(context, "SubscriptionUpdate");
        try {
            sendSubscriptionUpdateToCitadel(context, map);
            NotificationUtil.updatePushNotificationSubscriptionCache(context, map);
            pFENotificationService.getAndSetSubscriptions(context);
        } catch (NotificationException e) {
            Log.e(TAG, "NotificationException encountered while updating subscription for App Id " + applicationInstallId, e);
        }
    }

    public void retrieveAndCachePushNotificationSubscription(Context context) {
        String applicationInstallId = NotificationUtil.getApplicationInstallId(context);
        if (TextUtils.isEmpty(applicationInstallId)) {
            return;
        }
        Log.d(TAG, "Retrieving notification subscription for customer with App Id " + applicationInstallId);
        try {
            new PFENotificationService(context, "SubscriptionRetrieve").getAndSetSubscriptions(context);
        } catch (NotificationException e) {
            Log.e(TAG, "NotificationException while retrieving subscription for App Id " + applicationInstallId, e);
        }
    }

    public NotificationTarget constructNotificationTarget(String str, int i, String str2) {
        NotificationTarget notificationTarget = new NotificationTarget();
        notificationTarget.setApplicationId(str);
        notificationTarget.setDestination(str2);
        notificationTarget.setType(i);
        return notificationTarget;
    }

    public NotificationTarget getNewNotificationTarget(Context context, String str) {
        if (FirebaseApp.getApps(context).isEmpty()) {
            Log.e(TAG, "Firebase should have been initialised by now.");
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return constructNotificationTarget("com.amazon.mp3", 1, str);
    }

    private void sendSubscriptionEventToMTS(Context context, Map<String, Boolean> map) {
        MetricsManager manager = MetricsHolder.getManager();
        Map<String, Boolean> map2 = SonarPushCacheManager.getMap(context, "arePushTopicsOptedIn");
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            if (map2.containsKey(entry.getKey()) && map2.get(entry.getKey()).booleanValue() && entry.getValue() == Boolean.FALSE) {
                String str = "opted_out." + entry.getKey();
                String applicationInstallId = NotificationUtil.getApplicationInstallId(context);
                PushCampaignEvent pushCampaignEventBuild = PushCampaignEvent.builder(str, applicationInstallId, "", "sonar", "regular").withOptOutType("application").build();
                Log.i(TAG, "Sending optout metrics to MTS with actionType " + str + " for App Id " + applicationInstallId);
                manager.handleEvent(pushCampaignEventBuild);
            }
        }
    }

    public void sendSubscriptionUpdateToCitadel(Context context, Map<String, Boolean> map) {
        Map<String, Boolean> map2 = SonarPushCacheManager.getMap(context, "arePushTopicsOptedIn");
        if (map2.isEmpty() || map.isEmpty()) {
            Log.w(TAG, "Could not update Citadel engagement data because of empty subscriptions cache.");
            return;
        }
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            if (!StringUtils.isBlank(entry.getKey())) {
                String key = entry.getKey();
                Boolean value = entry.getValue();
                if (map2.get(key) != value) {
                    String str = value.booleanValue() ? "Subscription OptIn" : "Subscription OptOut";
                    Log.i(TAG, "Sending event " + str + " to citadel engagement table");
                    if (CitadelClient.getInstance() != null) {
                        CitadelClient.getInstance().updateCustomerAction(context, str, key);
                    }
                }
            }
        }
    }
}
