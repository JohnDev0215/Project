package com.amazon.mp3.download;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.ExternalLauncherActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.service.NotificationView;
import com.amazon.mp3.intents.PendingIntentUtil;
import com.amazon.mp3.service.ServiceUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.music.downloads.DownloadManager;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.ErrorReason;
import com.amazon.music.downloads.RequestItem;
import com.amazon.music.downloads.RequestProvider;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadNotificationService extends Service implements NotificationView {
    private static final int NOTIFICATION_ID;
    public static long NOTIFICATION_INTERVAL;
    private static final String TAG;
    private boolean hasStartedAlready;
    private Notification mCompletedNotification;
    private Context mContext;
    private DownloadManager mDownloadManager;
    private Handler mHandler;
    private NotificationDownloadListener mListener;
    private Notification mNotification;
    private NotificationManager mNotificationManager;
    private boolean mShowNotification;
    private int mStartId = -1;
    private NotificationUpdateInfo mNotificationUpdateInfo = new NotificationUpdateInfo();
    private long lastNotificationTime = 0;
    private RequestProvider.RequestProviderCallback mRequestProviderCallback = new RequestProvider.RequestProviderCallback() { // from class: com.amazon.mp3.download.DownloadNotificationService.5
        @Override // com.amazon.music.downloads.RequestProvider.RequestProviderCallback
        public void requestsSubmitted(final Set<RequestItem> requests) {
            if (DownloadNotificationService.this.mStartId != -1) {
                DownloadNotificationService.this.setupNotification(requests);
            }
        }
    };
    private final Runnable mClearNotificationRunnable = new Runnable() { // from class: com.amazon.mp3.download.DownloadNotificationService.6
        @Override // java.lang.Runnable
        public void run() {
            DownloadNotificationService.this.stopForeground(false);
            DownloadNotificationService.this.mNotificationManager.cancel(DownloadNotificationService.NOTIFICATION_ID);
            DownloadNotificationService downloadNotificationService = DownloadNotificationService.this;
            downloadNotificationService.stopSelf(downloadNotificationService.mStartId);
        }
    };

    @Override // com.amazon.mp3.download.service.NotificationView
    public int getDownloadCompleteResourceId() {
        return R.drawable.notification_icn_complete;
    }

    @Override // com.amazon.mp3.download.service.NotificationView
    public int getDownloadingResourceId() {
        return R.drawable.notification_icn_downloading;
    }

    @Override // com.amazon.mp3.download.service.NotificationView
    public int getErrorResourceId() {
        return R.drawable.notification_icn_downloading;
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(final Intent intent) {
        return null;
    }

    static {
        String simpleName = DownloadNotificationService.class.getSimpleName();
        TAG = simpleName;
        NOTIFICATION_ID = simpleName.hashCode();
        NOTIFICATION_INTERVAL = 400L;
    }

    @Override // android.app.Service
    public void onCreate() {
        this.mContext = getApplicationContext();
        this.mNotificationManager = (NotificationManager) getSystemService("notification");
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.mStartId = startId;
        if ((flags & 1) == 1) {
            this.mShowNotification = loadNotificationPreference();
            handleSystemRestart(startId);
            return 3;
        }
        if (intent != null && intent.getAction() != null && intent.getAction().startsWith("com.amazon.music.downloads.notification")) {
            processNotificationRequest(intent);
            return 3;
        }
        if (intent == null || intent.getAction() == null || !intent.getAction().startsWith("com.amazon.music.downloads")) {
            return 3;
        }
        processDownloadControlRequest(intent);
        return 3;
    }

    private void handleSystemRestart(int startId) {
        ServiceUtil.startNotification(getApplicationContext(), this, NOTIFICATION_ID, getLoadingNotification(createLoadingNotificationView()));
        stopSelf(startId);
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.mHandler.removeCallbacks(this.mClearNotificationRunnable);
        this.mNotificationManager.cancel(NOTIFICATION_ID);
        NotificationDownloadListener notificationDownloadListener = this.mListener;
        if (notificationDownloadListener != null) {
            notificationDownloadListener.shutDown();
            MusicDownloader.getInstance(this).unregisterDownloadListener(this.mListener);
        }
    }

    private void processNotificationRequest(Intent intent) {
        createNotificationChannel();
        if (PlatformUtil.isVersionOrGreater(26)) {
            StatusBarNotification[] activeNotifications = this.mNotificationManager.getActiveNotifications();
            int length = activeNotifications.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                StatusBarNotification statusBarNotification = activeNotifications[i];
                if (statusBarNotification.getNotification().getChannelId().equals("android_music_download_notification")) {
                    this.mNotification = statusBarNotification.getNotification();
                    break;
                }
                i++;
            }
            if (this.mNotification == null) {
                getLoadingNotification(createLoadingNotificationView());
            }
            if (!AmazonApplication.isApplicationForeground() && PlatformUtil.isVersionOrGreater(31)) {
                NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, this.mNotification);
            } else {
                ServiceUtil.startNotification(this, this, NOTIFICATION_ID, this.mNotification);
            }
        } else if (!this.hasStartedAlready) {
            ServiceUtil.startNotification(getApplicationContext(), this, NOTIFICATION_ID, getLoadingNotification(createLoadingNotificationView()));
            this.hasStartedAlready = true;
        }
        String action = intent.getAction();
        if ("com.amazon.music.downloads.notification.SHOW".equals(action)) {
            updateNotificationPreference(true);
            this.mShowNotification = true;
            processReloadRequest();
        } else if ("com.amazon.music.downloads.notification.HIDE".equals(action)) {
            updateNotificationPreference(false);
            this.mShowNotification = false;
        }
    }

    private void processReloadRequest() {
        if (this.mDownloadManager == null) {
            this.mDownloadManager = MusicDownloader.getInstance(this);
        }
        this.mDownloadManager.getRequestProvider().getRequests(this.mRequestProviderCallback);
    }

    private void processDownloadControlRequest(Intent intent) {
        if (this.mDownloadManager == null) {
            this.mDownloadManager = MusicDownloader.getInstance(this);
        }
        String action = intent.getAction();
        String stringExtra = intent.getStringExtra(TetheredMessageKey.requestId);
        if ("com.amazon.music.downloads.PAUSE".equals(action)) {
            Log.info(TAG, "user pause from notification");
            this.mDownloadManager.pause(stringExtra);
            return;
        }
        if ("com.amazon.music.downloads.CANCEL".equals(action)) {
            Log.info(TAG, "user cancel from notification");
            this.mDownloadManager.cancel(stringExtra);
        } else if ("com.amazon.music.downloads.RESUME".equals(action)) {
            Log.info(TAG, "user resume from notification");
            this.mDownloadManager.resume(stringExtra);
        } else if ("com.amazon.music.downloads.KILL".equals(action)) {
            stopSelf(this.mStartId);
        }
    }

    private boolean loadNotificationPreference() {
        return getSharedPreferences("com.amazon.music_DownloadNotificationService", 0).getBoolean("show_notification", true);
    }

    private void updateNotificationPreference(boolean show) {
        SharedPreferences.Editor editorEdit = getSharedPreferences("com.amazon.music_DownloadNotificationService", 0).edit();
        editorEdit.putBoolean("show_notification", show);
        editorEdit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupNotification(Set<RequestItem> requestItems) {
        ArrayList arrayList = new ArrayList(requestItems.size());
        Log.info(TAG, "Current download request count: %d", Integer.valueOf(requestItems.size()));
        for (RequestItem requestItem : requestItems) {
            if (!requestItem.isBackground()) {
                arrayList.add(requestItem.getRequestId());
            }
            Log.info(TAG, "Current download request: %s", requestItem.toString());
        }
        NotificationDownloadListener notificationDownloadListener = this.mListener;
        this.mListener = new NotificationDownloadListener(arrayList, MusicDownloader.getInstance(this.mContext), this);
        if (this.mDownloadManager == null) {
            this.mDownloadManager = MusicDownloader.getInstance(this);
        }
        if (notificationDownloadListener != null) {
            this.mDownloadManager.unregisterDownloadListener(notificationDownloadListener);
        }
        this.mDownloadManager.registerDownloadListener(this.mListener);
        this.mListener.fetchDownloadingOrQueuedRequests(new Action1<List<DownloadState>>() { // from class: com.amazon.mp3.download.DownloadNotificationService.1
            @Override // rx.functions.Action1
            public void call(final List<DownloadState> downloadStates) {
                if (downloadStates.isEmpty()) {
                    DownloadNotificationService downloadNotificationService = DownloadNotificationService.this;
                    downloadNotificationService.showCompletedNotification(downloadNotificationService.createCompleteNotificationView(), DownloadNotificationService.this.getDownloadCompleteResourceId());
                    DownloadNotificationService.this.stopForeground(false);
                }
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.download.DownloadNotificationService.2
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
            }
        });
    }

    private RemoteViews createLoadingNotificationView() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.download_notif_layout);
        remoteViews.setViewVisibility(R.id.progress_bar, 8);
        remoteViews.setViewVisibility(R.id.tv_progress_percent, 8);
        remoteViews.setViewVisibility(R.id.download_notification_progressbar, 0);
        remoteViews.setTextViewText(R.id.tv_download_title, this.mContext.getString(R.string.downloading_text));
        remoteViews.setViewVisibility(R.id.resume_button, 8);
        remoteViews.setViewVisibility(R.id.pause_button, 8);
        return remoteViews;
    }

    @Override // com.amazon.mp3.download.service.NotificationView
    public RemoteViews createProgressNotificationView(final String id, final NotificationInfo notificationInfo, final float percentageDownloaded, final boolean isPaused) {
        if (notificationInfo == null || TextUtils.isEmpty(notificationInfo.getTitle())) {
            return null;
        }
        this.mNotificationUpdateInfo.setRequestId(id);
        this.mNotificationUpdateInfo.setLastPercentageDownloaded(percentageDownloaded);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.download_notif_layout);
        int i = (int) percentageDownloaded;
        remoteViews.setProgressBar(R.id.progress_bar, 100, i, false);
        remoteViews.setViewVisibility(R.id.progress_bar, 0);
        remoteViews.setViewVisibility(R.id.download_notification_progressbar, 8);
        remoteViews.setViewVisibility(R.id.tv_progress_percent, 0);
        remoteViews.setTextViewText(R.id.tv_progress_status, this.mContext.getString(R.string.downloading_text));
        remoteViews.setTextViewText(R.id.tv_progress_percent, this.mContext.getString(R.string.download_percent, Integer.valueOf(i)));
        remoteViews.setTextViewText(R.id.tv_download_title, notificationInfo.getTitle());
        int iCurrentTimeMillis = (int) System.currentTimeMillis();
        if (isPaused) {
            remoteViews.setViewVisibility(R.id.pause_button, 8);
            Intent intent = new Intent(this.mContext, (Class<?>) DownloadNotificationService.class);
            intent.setAction("com.amazon.music.downloads.RESUME");
            intent.putExtra(TetheredMessageKey.requestId, id);
            PendingIntent service = PendingIntentUtil.INSTANCE.getService(this.mContext, iCurrentTimeMillis, intent, 268435456, true);
            remoteViews.setViewVisibility(R.id.resume_button, 0);
            remoteViews.setOnClickPendingIntent(R.id.resume_button, service);
        } else {
            remoteViews.setViewVisibility(R.id.resume_button, 8);
            Intent intent2 = new Intent(this.mContext, (Class<?>) DownloadNotificationService.class);
            intent2.setAction("com.amazon.music.downloads.PAUSE");
            intent2.putExtra(TetheredMessageKey.requestId, id);
            PendingIntent service2 = PendingIntentUtil.INSTANCE.getService(this.mContext, iCurrentTimeMillis, intent2, 268435456, true);
            remoteViews.setViewVisibility(R.id.pause_button, 0);
            remoteViews.setOnClickPendingIntent(R.id.pause_button, service2);
        }
        Intent intent3 = new Intent(this.mContext, (Class<?>) DownloadNotificationService.class);
        intent3.setAction("com.amazon.music.downloads.CANCEL");
        intent3.putExtra(TetheredMessageKey.requestId, id);
        remoteViews.setOnClickPendingIntent(R.id.cancel_button, PendingIntentUtil.INSTANCE.getService(this.mContext, iCurrentTimeMillis, intent3, 268435456, true));
        remoteViews.setViewVisibility(R.id.cancel_button, 0);
        return remoteViews;
    }

    @Override // com.amazon.mp3.download.service.NotificationView
    public RemoteViews createErrorNotificationView(final String id, final ErrorReason errorReason) {
        RemoteViews remoteViews = new RemoteViews(this.mContext.getPackageName(), R.layout.error_download_notif_layout);
        int iCurrentTimeMillis = (int) System.currentTimeMillis();
        remoteViews.setTextViewText(R.id.tv_download_title, this.mContext.getString(R.string.download_failed_notif_title));
        remoteViews.setTextViewText(R.id.tv_download_error_msg, this.mContext.getString(R.string.download_failed_notif_message));
        remoteViews.setViewVisibility(R.id.resume_button, 0);
        Intent intent = new Intent(this.mContext, (Class<?>) DownloadNotificationService.class);
        intent.setAction("com.amazon.music.downloads.RESUME");
        intent.putExtra(TetheredMessageKey.requestId, id);
        PendingIntentUtil pendingIntentUtil = PendingIntentUtil.INSTANCE;
        remoteViews.setOnClickPendingIntent(R.id.resume_button, pendingIntentUtil.getService(this.mContext, iCurrentTimeMillis, intent, 268435456, true));
        Intent intent2 = new Intent(this.mContext, (Class<?>) DownloadNotificationService.class);
        intent2.setAction("com.amazon.music.downloads.CANCEL");
        intent2.putExtra(TetheredMessageKey.requestId, id);
        remoteViews.setOnClickPendingIntent(R.id.cancel_button, pendingIntentUtil.getService(this.mContext, iCurrentTimeMillis, intent2, 268435456, true));
        return remoteViews;
    }

    @Override // com.amazon.mp3.download.service.NotificationView
    public RemoteViews createCompleteNotificationView() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.download_completed_notif_layout);
        remoteViews.setTextViewText(R.id.tv_download_title, this.mContext.getString(R.string.download_completed_notif_title));
        remoteViews.setTextViewText(R.id.tv_download_message, this.mContext.getString(R.string.download_completed_notif_message));
        return remoteViews;
    }

    @Override // com.amazon.mp3.download.service.NotificationView
    public boolean isNotificationEnabled() {
        return this.mShowNotification;
    }

    @Override // com.amazon.mp3.download.service.NotificationView
    public NotificationUpdateInfo getCurrentNotification() {
        return this.mNotificationUpdateInfo;
    }

    @Override // com.amazon.mp3.download.service.NotificationView
    public void clearNotification() {
        this.mHandler.removeCallbacks(this.mClearNotificationRunnable);
        this.mHandler.postDelayed(this.mClearNotificationRunnable, 2000L);
    }

    @Override // com.amazon.mp3.download.service.NotificationView
    public void removeNotificationRunnable() {
        this.mHandler.removeCallbacks(this.mClearNotificationRunnable);
    }

    @Override // com.amazon.mp3.download.service.NotificationView
    public void showNotification(final RemoteViews notifView, final int smallIconResId, boolean force) {
        postNotification(new Runnable() { // from class: com.amazon.mp3.download.DownloadNotificationService.3
            @Override // java.lang.Runnable
            public void run() {
                if (DownloadNotificationService.this.mNotification != null) {
                    DownloadNotificationService.this.mNotification.icon = smallIconResId;
                    DownloadNotificationService.this.mNotification.contentView = notifView;
                } else {
                    Intent intent = new Intent("com.amazon.music.downloads.notification.VIEW_DOWNLOADS");
                    intent.setClass(DownloadNotificationService.this.mContext, ExternalLauncherActivity.class);
                    PendingIntentUtil pendingIntentUtil = PendingIntentUtil.INSTANCE;
                    PendingIntent activity = pendingIntentUtil.getActivity(DownloadNotificationService.this.mContext, 0, intent, 0, true);
                    Intent intent2 = new Intent("com.amazon.music.downloads.notification.HIDE");
                    intent2.setClass(DownloadNotificationService.this.mContext, DownloadNotificationService.class);
                    PendingIntent service = pendingIntentUtil.getService(DownloadNotificationService.this.mContext, 0, intent2, 0, true);
                    DownloadNotificationService.this.createNotificationChannel();
                    DownloadNotificationService downloadNotificationService = DownloadNotificationService.this;
                    downloadNotificationService.mNotification = new NotificationCompat.Builder(downloadNotificationService, "android_music_download_notification").setSmallIcon(smallIconResId).setContent(notifView).setWhen(System.currentTimeMillis()).setContentIntent(activity).setDeleteIntent(service).build();
                }
                DownloadNotificationService.this.mNotificationManager.notify(DownloadNotificationService.NOTIFICATION_ID, DownloadNotificationService.this.mNotification);
            }
        }, force);
    }

    private Notification getLoadingNotification(RemoteViews notifView) {
        Intent intent = new Intent("com.amazon.music.downloads.notification.VIEW_DOWNLOADS");
        intent.setClass(this.mContext, ExternalLauncherActivity.class);
        PendingIntentUtil pendingIntentUtil = PendingIntentUtil.INSTANCE;
        PendingIntent activity = pendingIntentUtil.getActivity(this.mContext, 0, intent, 0, true);
        Intent intent2 = new Intent("com.amazon.music.downloads.notification.HIDE");
        intent2.setClass(this.mContext, DownloadNotificationService.class);
        PendingIntent service = pendingIntentUtil.getService(this.mContext, 0, intent2, 0, true);
        createNotificationChannel();
        Notification notificationBuild = new NotificationCompat.Builder(this, "android_music_download_notification").setSmallIcon(getDownloadingResourceId()).setContent(notifView).setWhen(System.currentTimeMillis()).setContentIntent(activity).setDeleteIntent(service).build();
        this.mNotification = notificationBuild;
        return notificationBuild;
    }

    @Override // com.amazon.mp3.download.service.NotificationView
    public void showCompletedNotification(final RemoteViews notifView, final int smallIconResId) {
        postNotification(new Runnable() { // from class: com.amazon.mp3.download.DownloadNotificationService.4
            @Override // java.lang.Runnable
            public void run() {
                if (DownloadNotificationService.this.mCompletedNotification != null) {
                    DownloadNotificationService.this.mCompletedNotification.icon = smallIconResId;
                    DownloadNotificationService.this.mCompletedNotification.contentView = notifView;
                } else {
                    Intent intent = new Intent("com.amazon.music.downloads.notification.VIEW_DOWNLOADED_TAB");
                    intent.setClass(DownloadNotificationService.this.mContext, ExternalLauncherActivity.class);
                    PendingIntent activity = PendingIntentUtil.INSTANCE.getActivity(DownloadNotificationService.this.mContext, 0, intent, 0, true);
                    DownloadNotificationService.this.createNotificationChannel();
                    DownloadNotificationService downloadNotificationService = DownloadNotificationService.this;
                    downloadNotificationService.mCompletedNotification = new NotificationCompat.Builder(downloadNotificationService, "android_music_download_notification").setSmallIcon(smallIconResId).setContent(notifView).setWhen(System.currentTimeMillis()).setContentIntent(activity).setAutoCancel(true).build();
                }
                DownloadNotificationService.this.mNotificationManager.notify(DownloadNotificationService.NOTIFICATION_ID, DownloadNotificationService.this.mCompletedNotification);
            }
        }, true);
        this.mListener.fetchDownloadingOrQueuedRequests(new Action1() { // from class: com.amazon.mp3.download.DownloadNotificationService$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$showCompletedNotification$0((List) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.download.DownloadNotificationService$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DownloadNotificationService.lambda$showCompletedNotification$1((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCompletedNotification$0(List list) {
        if (list.isEmpty()) {
            stopForeground(false);
            this.hasStartedAlready = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showCompletedNotification$1(Throwable th) {
        Log.error(TAG, th.toString());
    }

    private void postNotification(Runnable runnable, boolean force) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastNotificationTime >= NOTIFICATION_INTERVAL) {
            this.lastNotificationTime = System.currentTimeMillis();
            this.mHandler.post(runnable);
        } else if (force) {
            this.mHandler.removeCallbacksAndMessages(null);
            this.mHandler.postDelayed(runnable, (this.lastNotificationTime + NOTIFICATION_INTERVAL) - jCurrentTimeMillis);
            this.lastNotificationTime += NOTIFICATION_INTERVAL;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createNotificationChannel() {
        if (PlatformUtil.isVersionOrGreater(26)) {
            NotificationChannel notificationChannel = new NotificationChannel("android_music_download_notification", "Downloads", 2);
            notificationChannel.setShowBadge(false);
            this.mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
