package com.amazon.music.push;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.technical.TechnicalMetricsCollection;
import com.amazon.music.push.activity.NotificationOpenHandlerActivity;
import com.amazon.music.push.citadel.CitadelClient;
import com.amazon.music.push.sonarpush.NotificationUtil;
import com.amazon.music.push.util.MetricsUtil;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationHandler {
    private static final String TAG = "NotificationHandler";
    private static int notification_id;
    private static int request_code;
    private final TechnicalMetricsCollection technicalMetricsCollection = MetricsUtil.initializeTechnicalCollectionMetrics("com.amazon.music.push.PushMessagingService");

    public void handlePushDelivered(Context context, Map<String, String> map) {
        String str;
        String str2;
        String str3 = map.get("pinpoint.campaign.campaign_id");
        String str4 = map.get("pinpoint.notification.imageIconUrl");
        String str5 = map.get("pinpoint.deeplink");
        String applicationInstallId = NotificationUtil.getApplicationInstallId(context);
        String str6 = map.get("pinpoint.notification.title");
        String str7 = map.get("pinpoint.notification.body");
        if (str5.contains(Splash.PARAMS_LOCALE)) {
            try {
                Map<String, String> mapPushLocaleHandler = new PushLocaleInPayloadManager(context).pushLocaleHandler(map);
                if (!StringUtils.isBlank(mapPushLocaleHandler.get("pinpoint.notification.body")) && !StringUtils.isBlank(mapPushLocaleHandler.get("pinpoint.notification.title"))) {
                    String str8 = mapPushLocaleHandler.get("pinpoint.notification.title");
                    String str9 = mapPushLocaleHandler.get("pinpoint.notification.body");
                    if (!TextUtils.equals(str8, map.get("pinpoint.notification.title")) || !TextUtils.equals(str9, map.get("pinpoint.notification.body"))) {
                        emitPushPayloadModifiedServiceMetrics(true, applicationInstallId);
                    }
                    str2 = str9;
                    str = str8;
                }
                Log.w(TAG, "Modified payload returned has blank body/title, suppressing notification");
                emitPushPayloadModifiedServiceMetrics(false, applicationInstallId);
                return;
            } catch (Exception e) {
                Log.e(TAG, "Error while modifying push payload " + e);
                emitPushPayloadModifiedServiceMetrics(false, applicationInstallId);
                return;
            }
        }
        str = str6;
        str2 = str7;
        CitadelClient.getInstance().updateCustomerAction(context, "Push Delivered", "", str3, str5);
        if (str3 != null && !str3.isEmpty()) {
            NotificationUtil.publishPushDeliveredMetrics(context, str3);
        }
        createNotificationChannel(context);
        Intent intent = new Intent(context, (Class<?>) NotificationOpenHandlerActivity.class);
        intent.setFlags(268468224);
        intent.putExtra(Splash.PARAMS_CAMPAIGN_ID, str3);
        intent.putExtra("deeplink", str5);
        intent.setAction("com.amazon.mp3.push.intent");
        int i = request_code;
        request_code = i + 1;
        NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(context, "AmazonMusicPush").setSmallIcon(R$drawable.notification_small_icon).setContentTitle(str).setContentText(str2).setPriority(1).setContentIntent(PendingIntent.getActivity(context, i, intent, 1140850688)).setAutoCancel(true);
        if (!createNotificationIcons(str4, autoCancel, str2)) {
            autoCancel.setStyle(new NotificationCompat.BigTextStyle().bigText(str2));
        }
        NotificationManagerCompat notificationManagerCompatFrom = NotificationManagerCompat.from(context);
        Log.d(TAG, "Publishing push notification to the app");
        int i2 = notification_id;
        notification_id = i2 + 1;
        notificationManagerCompatFrom.notify(i2, autoCancel.build());
    }

    public void emitPushDeliveredServiceMetrics(String str, boolean z, String str2) {
        if (z) {
            Log.d(TAG, "app_in_foreground:" + str);
            MetricsUtil.sendEventToMTS("app_in_foreground:" + str, str2, str, "sonar", "regular");
            MetricsUtil.sendEventToMTS("app_in_foreground", str2, str, "sonar", "regular");
            this.technicalMetricsCollection.incrementCounter("sonarPushMessaging:campaignReceivedInForeground:" + str, 1.0d);
            return;
        }
        Log.d(TAG, "posted_notification:" + str);
        MetricsUtil.sendEventToMTS("posted_notification:" + str, str2, str, "sonar", "regular");
        MetricsUtil.sendEventToMTS("posted_notification", str2, str, "sonar", "regular");
        this.technicalMetricsCollection.incrementCounter("sonarPushMessaging:campaignReceivedInBackground:" + str, 1.0d);
    }

    public void emitPushPayloadModifiedServiceMetrics(boolean z, String str) {
        if (z) {
            Log.d(TAG, "pushPayloadModifiedSuccess");
            MetricsUtil.sendEventToMTS("pushPayloadModifiedSuccess", str, "", "sonar", "regular");
            this.technicalMetricsCollection.incrementCounter("pushPayloadModifiedSuccess", 1.0d);
        } else {
            Log.d(TAG, "pushPayloadModifiedFailure");
            MetricsUtil.sendEventToMTS("pushPayloadModifiedFailure", str, "", "sonar", "regular");
            this.technicalMetricsCollection.incrementCounter("pushPayloadModifiedFailure", 1.0d);
        }
    }

    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("AmazonMusicPush", "Amazon Music", 4);
            notificationChannel.setShowBadge(true);
            notificationChannel.setDescription("This is Amazon Music Push channel");
            notificationChannel.setLockscreenVisibility(0);
            ((NotificationManager) context.getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
    }

    private boolean createNotificationIcons(String str, NotificationCompat.Builder builder, String str2) {
        Bitmap bitmap;
        if (str != null) {
            try {
                bitmap = new DownloadImageTask().execute(str).get();
                try {
                    Log.d(TAG, "Download Push notification image url successful");
                } catch (InterruptedException e) {
                    e = e;
                    Log.e(TAG, "Interrupted when downloading image : " + e.getMessage(), e);
                } catch (ExecutionException e2) {
                    e = e2;
                    Log.e(TAG, "Failed to execute download image thread : " + e.getMessage(), e);
                }
            } catch (InterruptedException e3) {
                e = e3;
                bitmap = null;
            } catch (ExecutionException e4) {
                e = e4;
                bitmap = null;
            }
        } else {
            bitmap = null;
        }
        if (bitmap != null) {
            builder.setLargeIcon(bitmap);
            if (!str.contains("Stacked_Amazon_Music_WhiteOnIndigo") && str.contains("rm=y")) {
                builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null));
                return true;
            }
        }
        return false;
    }

    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private DownloadImageTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Bitmap doInBackground(String... strArr) {
            try {
                return BitmapFactory.decodeStream(((URLConnection) FirebasePerfUrlConnection.instrument(new URL(strArr[0]).openConnection())).getInputStream());
            } catch (IOException e) {
                Log.e(NotificationHandler.TAG, "Cannot download or find image for notification " + e.getMessage());
                return null;
            }
        }
    }
}
