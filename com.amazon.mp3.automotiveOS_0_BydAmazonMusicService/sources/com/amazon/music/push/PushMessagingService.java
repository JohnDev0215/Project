package com.amazon.music.push;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.amazon.music.push.sonarpush.NotificationUtil;
import com.amazon.music.push.sonarpush.PushNotificationManager;
import com.amazon.music.push.sonarpush.SonarPushCacheManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class PushMessagingService extends FirebaseMessagingService implements LifecycleObserver {
    private static final String TAG = PushMessagingService.class.getSimpleName();
    private boolean isAppInForeground;
    private NotificationHandler notificationHandler = new NotificationHandler();

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onAppBackgrounded() {
        this.isAppInForeground = false;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onAppForegrounded() {
        this.isAppInForeground = true;
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        String str = data.get("pinpoint.campaign.campaign_id");
        String applicationInstallId = NotificationUtil.getApplicationInstallId(getApplicationContext());
        try {
            this.notificationHandler.handlePushDelivered(getApplicationContext(), data);
            this.notificationHandler.emitPushDeliveredServiceMetrics(str, this.isAppInForeground, applicationInstallId);
        } catch (Exception e) {
            Log.e(TAG, "Error while invoking notificationHandler for push delivered event" + e.toString());
        }
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String str) {
        String str2 = TAG;
        Log.i(str2, "New Push token acquired");
        Context applicationContext = getApplicationContext();
        if (TextUtils.isEmpty(SonarPushCacheManager.getString(applicationContext, "fcmToken"))) {
            Log.i(str2, "Saving push token in cache");
            SonarPushCacheManager.putString(applicationContext, "fcmToken", str);
        } else {
            PushNotificationManager.getInstance().updateApplicationFCMToken(applicationContext, str);
        }
    }
}
