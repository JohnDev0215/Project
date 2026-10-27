package com.google.firebase.messaging;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.CommonNotificationBuilder;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes6.dex */
class DisplayNotification {
    private final Context context;
    private final ExecutorService networkIoExecutor;
    private final NotificationParams params;

    public DisplayNotification(Context context, NotificationParams notificationParams, ExecutorService executorService) {
        this.networkIoExecutor = executorService;
        this.context = context;
        this.params = notificationParams;
    }

    private boolean isAppForeground() {
        if (((KeyguardManager) this.context.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            return false;
        }
        if (!PlatformVersion.isAtLeastLollipop()) {
            SystemClock.sleep(10L);
        }
        int iMyPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == iMyPid) {
                return runningAppProcessInfo.importance == 100;
            }
        }
        return false;
    }

    boolean handleNotification() {
        if (this.params.getBoolean("gcm.n.noui")) {
            return true;
        }
        if (isAppForeground()) {
            return false;
        }
        ImageDownload imageDownloadStartImageDownloadInBackground = startImageDownloadInBackground();
        CommonNotificationBuilder.DisplayNotificationInfo displayNotificationInfoCreateNotificationInfo = CommonNotificationBuilder.createNotificationInfo(this.context, this.params);
        waitForAndApplyImageDownload(displayNotificationInfoCreateNotificationInfo.notificationBuilder, imageDownloadStartImageDownloadInBackground);
        showNotification(displayNotificationInfoCreateNotificationInfo);
        return true;
    }

    @Nullable
    private ImageDownload startImageDownloadInBackground() {
        ImageDownload imageDownloadCreate = ImageDownload.create(this.params.getString("gcm.n.image"));
        if (imageDownloadCreate != null) {
            imageDownloadCreate.start(this.networkIoExecutor);
        }
        return imageDownloadCreate;
    }

    private void waitForAndApplyImageDownload(NotificationCompat.Builder builder, @Nullable ImageDownload imageDownload) {
        if (imageDownload == null) {
            return;
        }
        try {
            Bitmap bitmap = (Bitmap) Tasks.await(imageDownload.getTask(), 5L, TimeUnit.SECONDS);
            builder.setLargeIcon(bitmap);
            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null));
        } catch (InterruptedException unused) {
            Log.w("FirebaseMessaging", "Interrupted while downloading image, showing notification without it");
            imageDownload.close();
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            Log.w("FirebaseMessaging", "Failed to download image: " + e.getCause());
        } catch (TimeoutException unused2) {
            Log.w("FirebaseMessaging", "Failed to download image in time, showing notification without it");
            imageDownload.close();
        }
    }

    private void showNotification(CommonNotificationBuilder.DisplayNotificationInfo displayNotificationInfo) {
        if (Log.isLoggable("FirebaseMessaging", 3)) {
            Log.d("FirebaseMessaging", "Showing notification");
        }
        ((NotificationManager) this.context.getSystemService("notification")).notify(displayNotificationInfo.tag, displayNotificationInfo.id, displayNotificationInfo.notificationBuilder.build());
    }
}
