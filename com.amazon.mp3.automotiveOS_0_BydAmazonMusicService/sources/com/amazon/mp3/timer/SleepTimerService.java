package com.amazon.mp3.timer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.Parcelable;
import android.text.format.DateUtils;
import android.widget.RemoteViews;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.amazon.mp3.activity.settings.SettingsActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.intents.PendingIntentUtil;
import com.amazon.mp3.service.ServiceUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.UniqueCodeUtil;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public class SleepTimerService extends Service {
    private CountDownTimer mTimer;
    private final Object mTimerLock = new Object();
    private static final String TAG = SleepTimerCallback.class.getSimpleName();
    private static final int NOTIFICATION_ID = UniqueCodeUtil.nextUniqueCode();

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void setAndStartTimer(Context context, int secondsUntilSleep, SleepTimerCallback callback) {
        ServiceUtil.startService(context, new Intent(context, (Class<?>) SleepTimerService.class).setAction("action.sleep").putExtra("extra.sleep_time_secs", secondsUntilSleep).putExtra("extra.callback", callback));
    }

    public static void stopTimer(Context context) {
        ServiceUtil.startService(context, new Intent(context, (Class<?>) SleepTimerService.class).setAction("action.sleep").putExtra("extra.sleep_time_secs", 0));
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        SleepTimerCallback sleepTimerCallback;
        if (!"action.sleep".equals(intent.getAction())) {
            return 2;
        }
        synchronized (this.mTimerLock) {
            CountDownTimer countDownTimer = this.mTimer;
            sleepTimerCallback = null;
            if (countDownTimer != null) {
                countDownTimer.cancel();
                this.mTimer.onTick(-1L);
                this.mTimer = null;
            }
        }
        int intExtra = intent.getIntExtra("extra.sleep_time_secs", 0);
        if (intExtra <= 0) {
            return 2;
        }
        Parcelable parcelableExtra = intent.getParcelableExtra("extra.callback");
        if (parcelableExtra instanceof SleepTimerCallback) {
            sleepTimerCallback = (SleepTimerCallback) parcelableExtra;
        } else {
            Log.error(TAG, "Invalid type passed in");
        }
        setSleepTimer(intExtra, sleepTimerCallback);
        return 2;
    }

    private void setSleepTimer(int secondsUntilSleep, SleepTimerCallback callback) {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.sleep_timer_notification);
        Notification notificationBuild = createNotification(remoteViews).build();
        synchronized (this.mTimerLock) {
            CountDownTimer countDownTimerCreateTimer = createTimer(secondsUntilSleep, notificationBuild, remoteViews, callback);
            this.mTimer = countDownTimerCreateTimer;
            countDownTimerCreateTimer.onTick(TimeUnit.SECONDS.toMillis(secondsUntilSleep));
            this.mTimer.start();
        }
    }

    private CountDownTimer createTimer(int secondsUntilSleep, final Notification notification, final RemoteViews remoteViews, final SleepTimerCallback callback) {
        return new CountDownTimer(TimeUnit.SECONDS.toMillis(secondsUntilSleep), 1000L) { // from class: com.amazon.mp3.timer.SleepTimerService.1
            private final StringBuilder mRecycledBuilder = new StringBuilder(8);
            private final Bundle mTimeRemaining = new Bundle(1);

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished < 0) {
                    callback.send(2, null);
                    SleepTimerService.this.stopForeground(true);
                    return;
                }
                long j = (long) ((millisUntilFinished / 1000) + 0.5d);
                remoteViews.setTextViewText(R.id.text, SleepTimerService.this.getString(R.string.dmusic_sleep_timer_notification, SleepTimerService.this.getResources().getQuantityString(R.plurals.dmusic_sleep_timer_minutes, (int) (j / 60), DateUtils.formatElapsedTime(this.mRecycledBuilder, j))));
                SleepTimerService.this.startForeground(SleepTimerService.NOTIFICATION_ID, notification);
                this.mTimeRemaining.putLong("time_reamining", j);
                callback.send(3, this.mTimeRemaining);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                synchronized (SleepTimerService.this.mTimerLock) {
                    SleepTimerService.this.mTimer = null;
                }
                SleepTimerCallback sleepTimerCallback = callback;
                if (sleepTimerCallback != null) {
                    sleepTimerCallback.send(1, null);
                }
                SleepTimerService.this.stopForeground(true);
            }
        };
    }

    private NotificationCompat.Builder createNotification(RemoteViews views) {
        views.setOnClickPendingIntent(R.id.root, PendingIntentUtil.INSTANCE.getActivity(this, 0, SettingsActivity.getStartIntent(this), 134217728, true));
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "android_music_sleep_notification");
        builder.setOngoing(true).setSmallIcon(R.drawable.notification_status_icon_alarm).setContent(views).setPriority(1).setShowWhen(false);
        return builder;
    }

    private void createNotificationChannel() {
        if (PlatformUtil.isVersionOrGreater(26)) {
            NotificationChannel notificationChannel = new NotificationChannel("android_music_sleep_notification", "Sleep Timer", 2);
            notificationChannel.setShowBadge(false);
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }
}
