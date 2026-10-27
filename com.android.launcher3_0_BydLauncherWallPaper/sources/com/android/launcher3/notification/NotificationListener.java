package com.android.launcher3.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.util.IntSet;
import com.android.launcher3.util.PackageUserKey;
import com.android.launcher3.util.SecureSettingsObserver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationListener extends NotificationListenerService {
    private static final int MSG_NOTIFICATION_FULL_REFRESH = 3;
    private static final int MSG_NOTIFICATION_POSTED = 1;
    private static final int MSG_NOTIFICATION_REMOVED = 2;
    public static final String TAG = "NotificationListener";
    private static boolean sIsConnected;
    private static boolean sIsCreated;
    private static NotificationListener sNotificationListenerInstance;
    private static NotificationsChangedListener sNotificationsChangedListener;
    private static StatusBarNotificationsChangedListener sStatusBarNotificationsChangedListener;
    private String mLastKeyDismissedByLauncher;
    private SecureSettingsObserver mNotificationDotsObserver;
    private final Handler.Callback mUiCallback;
    private final Handler mUiHandler;
    private final Handler.Callback mWorkerCallback;
    private final Handler mWorkerHandler;
    private final NotificationListenerService.Ranking mTempRanking = new NotificationListenerService.Ranking();
    private final Map<String, NotificationGroup> mNotificationGroupMap = new HashMap();
    private final Map<String, String> mNotificationGroupKeyMap = new HashMap();

    public interface NotificationsChangedListener {
        void onNotificationFullRefresh(List<StatusBarNotification> activeNotifications);

        void onNotificationPosted(PackageUserKey postedPackageUserKey, NotificationKeyData notificationKey, boolean shouldBeFilteredOut);

        void onNotificationRemoved(PackageUserKey removedPackageUserKey, NotificationKeyData notificationKey);
    }

    public interface StatusBarNotificationsChangedListener {
        void onNotificationPosted(StatusBarNotification sbn);

        void onNotificationRemoved(StatusBarNotification sbn);
    }

    public NotificationListener() {
        Handler.Callback callback = new Handler.Callback() { // from class: com.android.launcher3.notification.NotificationListener.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                Object arrayList;
                int i = message.what;
                if (i == 1 || i == 2) {
                    NotificationListener.this.mUiHandler.obtainMessage(message.what, message.obj).sendToTarget();
                } else if (i == 3) {
                    if (NotificationListener.sIsConnected) {
                        try {
                            NotificationListener notificationListener = NotificationListener.this;
                            arrayList = notificationListener.filterNotifications(notificationListener.getActiveNotifications());
                        } catch (SecurityException unused) {
                            Log.e(NotificationListener.TAG, "SecurityException: failed to fetch notifications");
                            arrayList = new ArrayList();
                        }
                    } else {
                        arrayList = new ArrayList();
                    }
                    NotificationListener.this.mUiHandler.obtainMessage(message.what, arrayList).sendToTarget();
                }
                return true;
            }
        };
        this.mWorkerCallback = callback;
        Handler.Callback callback2 = new Handler.Callback() { // from class: com.android.launcher3.notification.NotificationListener.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                int i = message.what;
                if (i != 1) {
                    if (i != 2) {
                        if (i == 3 && NotificationListener.sNotificationsChangedListener != null) {
                            NotificationListener.sNotificationsChangedListener.onNotificationFullRefresh((List) message.obj);
                        }
                    } else if (NotificationListener.sNotificationsChangedListener != null) {
                        Pair pair = (Pair) message.obj;
                        NotificationListener.sNotificationsChangedListener.onNotificationRemoved((PackageUserKey) pair.first, (NotificationKeyData) pair.second);
                    }
                } else if (NotificationListener.sNotificationsChangedListener != null) {
                    NotificationPostedMsg notificationPostedMsg = (NotificationPostedMsg) message.obj;
                    NotificationListener.sNotificationsChangedListener.onNotificationPosted(notificationPostedMsg.packageUserKey, notificationPostedMsg.notificationKey, notificationPostedMsg.shouldBeFilteredOut);
                }
                return true;
            }
        };
        this.mUiCallback = callback2;
        this.mWorkerHandler = new Handler(LauncherModel.getWorkerLooper(), callback);
        this.mUiHandler = new Handler(Looper.getMainLooper(), callback2);
        sNotificationListenerInstance = this;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        sIsCreated = true;
    }

    @Override // android.service.notification.NotificationListenerService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        sIsCreated = false;
    }

    public static NotificationListener getInstanceIfConnected() {
        if (sIsConnected) {
            return sNotificationListenerInstance;
        }
        return null;
    }

    public static void setNotificationsChangedListener(NotificationsChangedListener listener) {
        NotificationsChangedListener notificationsChangedListener;
        sNotificationsChangedListener = listener;
        NotificationListener instanceIfConnected = getInstanceIfConnected();
        if (instanceIfConnected != null) {
            instanceIfConnected.onNotificationFullRefresh();
        } else {
            if (sIsCreated || (notificationsChangedListener = sNotificationsChangedListener) == null) {
                return;
            }
            notificationsChangedListener.onNotificationFullRefresh(Collections.emptyList());
        }
    }

    public static void setStatusBarNotificationsChangedListener(StatusBarNotificationsChangedListener listener) {
        sStatusBarNotificationsChangedListener = listener;
    }

    public static void removeNotificationsChangedListener() {
        sNotificationsChangedListener = null;
    }

    public static void removeStatusBarNotificationsChangedListener() {
        sStatusBarNotificationsChangedListener = null;
    }

    @Override // android.service.notification.NotificationListenerService
    public void onListenerConnected() {
        super.onListenerConnected();
        sIsConnected = true;
        SecureSettingsObserver secureSettingsObserverNewNotificationSettingsObserver = SecureSettingsObserver.newNotificationSettingsObserver(this, new SecureSettingsObserver.OnChangeListener() { // from class: com.android.launcher3.notification.-$$Lambda$NotificationListener$n8PUkqW8fc_Wfqc2Cu_PKtkwx0k
            @Override // com.android.launcher3.util.SecureSettingsObserver.OnChangeListener
            public final void onSettingsChanged(boolean z) {
                this.f$0.onNotificationSettingsChanged(z);
            }
        });
        this.mNotificationDotsObserver = secureSettingsObserverNewNotificationSettingsObserver;
        secureSettingsObserverNewNotificationSettingsObserver.register();
        this.mNotificationDotsObserver.dispatchOnChange();
        onNotificationFullRefresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNotificationSettingsChanged(boolean areNotificationDotsEnabled) {
        if (areNotificationDotsEnabled || !sIsConnected) {
            return;
        }
        requestUnbind();
    }

    private void onNotificationFullRefresh() {
        this.mWorkerHandler.obtainMessage(3).sendToTarget();
    }

    @Override // android.service.notification.NotificationListenerService
    public void onListenerDisconnected() {
        super.onListenerDisconnected();
        sIsConnected = false;
        this.mNotificationDotsObserver.unregister();
    }

    @Override // android.service.notification.NotificationListenerService
    public void onNotificationPosted(final StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        if (sbn == null) {
            return;
        }
        this.mWorkerHandler.obtainMessage(1, new NotificationPostedMsg(sbn)).sendToTarget();
        StatusBarNotificationsChangedListener statusBarNotificationsChangedListener = sStatusBarNotificationsChangedListener;
        if (statusBarNotificationsChangedListener != null) {
            statusBarNotificationsChangedListener.onNotificationPosted(sbn);
        }
    }

    private class NotificationPostedMsg {
        final NotificationKeyData notificationKey;
        final PackageUserKey packageUserKey;
        final boolean shouldBeFilteredOut;

        NotificationPostedMsg(StatusBarNotification sbn) {
            this.packageUserKey = PackageUserKey.fromNotification(sbn);
            this.notificationKey = NotificationKeyData.fromNotification(sbn);
            this.shouldBeFilteredOut = NotificationListener.this.shouldBeFilteredOut(sbn);
        }
    }

    @Override // android.service.notification.NotificationListenerService
    public void onNotificationRemoved(final StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        if (sbn == null) {
            return;
        }
        this.mWorkerHandler.obtainMessage(2, new Pair(PackageUserKey.fromNotification(sbn), NotificationKeyData.fromNotification(sbn))).sendToTarget();
        StatusBarNotificationsChangedListener statusBarNotificationsChangedListener = sStatusBarNotificationsChangedListener;
        if (statusBarNotificationsChangedListener != null) {
            statusBarNotificationsChangedListener.onNotificationRemoved(sbn);
        }
        NotificationGroup notificationGroup = this.mNotificationGroupMap.get(sbn.getGroupKey());
        String key = sbn.getKey();
        if (notificationGroup != null) {
            notificationGroup.removeChildKey(key);
            if (notificationGroup.isEmpty()) {
                if (key.equals(this.mLastKeyDismissedByLauncher)) {
                    cancelNotification(notificationGroup.getGroupSummaryKey());
                }
                this.mNotificationGroupMap.remove(sbn.getGroupKey());
            }
        }
        if (key.equals(this.mLastKeyDismissedByLauncher)) {
            this.mLastKeyDismissedByLauncher = null;
        }
    }

    public void cancelNotificationFromLauncher(String key) {
        this.mLastKeyDismissedByLauncher = key;
        cancelNotification(key);
    }

    @Override // android.service.notification.NotificationListenerService
    public void onNotificationRankingUpdate(NotificationListenerService.RankingMap rankingMap) {
        super.onNotificationRankingUpdate(rankingMap);
        for (StatusBarNotification statusBarNotification : getActiveNotifications(rankingMap.getOrderedKeys())) {
            updateGroupKeyIfNecessary(statusBarNotification);
        }
    }

    private void updateGroupKeyIfNecessary(StatusBarNotification sbn) {
        String key = sbn.getKey();
        String str = this.mNotificationGroupKeyMap.get(key);
        String groupKey = sbn.getGroupKey();
        if (str == null || !str.equals(groupKey)) {
            this.mNotificationGroupKeyMap.put(key, groupKey);
            if (str != null && this.mNotificationGroupMap.containsKey(str)) {
                NotificationGroup notificationGroup = this.mNotificationGroupMap.get(str);
                notificationGroup.removeChildKey(key);
                if (notificationGroup.isEmpty()) {
                    this.mNotificationGroupMap.remove(str);
                }
            }
        }
        if (!sbn.isGroup() || groupKey == null) {
            return;
        }
        NotificationGroup notificationGroup2 = this.mNotificationGroupMap.get(groupKey);
        if (notificationGroup2 == null) {
            notificationGroup2 = new NotificationGroup();
            this.mNotificationGroupMap.put(groupKey, notificationGroup2);
        }
        if ((sbn.getNotification().flags & 512) != 0) {
            notificationGroup2.setGroupSummaryKey(key);
        } else {
            notificationGroup2.addChildKey(key);
        }
    }

    public List<StatusBarNotification> getNotificationsForKeys(List<NotificationKeyData> keys) {
        StatusBarNotification[] activeNotifications = getActiveNotifications((String[]) NotificationKeyData.extractKeysOnly(keys).toArray(new String[keys.size()]));
        return activeNotifications == null ? Collections.emptyList() : Arrays.asList(activeNotifications);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<StatusBarNotification> filterNotifications(StatusBarNotification[] notifications) {
        if (notifications == null) {
            return null;
        }
        IntSet intSet = new IntSet();
        for (int i = 0; i < notifications.length; i++) {
            if (shouldBeFilteredOut(notifications[i])) {
                intSet.add(i);
            }
        }
        ArrayList arrayList = new ArrayList(notifications.length - intSet.size());
        for (int i2 = 0; i2 < notifications.length; i2++) {
            if (!intSet.contains(i2)) {
                arrayList.add(notifications[i2]);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldBeFilteredOut(StatusBarNotification sbn) {
        Notification notification = sbn.getNotification();
        updateGroupKeyIfNecessary(sbn);
        getCurrentRanking().getRanking(sbn.getKey(), this.mTempRanking);
        if (!this.mTempRanking.canShowBadge()) {
            return true;
        }
        if (this.mTempRanking.getChannel().getId().equals(NotificationChannel.DEFAULT_CHANNEL_ID) && (notification.flags & 2) != 0) {
            return true;
        }
        return ((notification.flags & 512) != 0) || (TextUtils.isEmpty(notification.extras.getCharSequence("android.title")) && TextUtils.isEmpty(notification.extras.getCharSequence("android.text")));
    }
}
