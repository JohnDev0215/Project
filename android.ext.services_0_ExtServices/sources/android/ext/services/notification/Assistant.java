package android.ext.services.notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.content.pm.PackageManager;
import android.ext.services.notification.AssistantSettings;
import android.os.Bundle;
import android.os.UserHandle;
import android.service.notification.Adjustment;
import android.service.notification.NotificationAssistantService;
import android.service.notification.NotificationListenerService;
import android.service.notification.NotificationStats;
import android.service.notification.StatusBarNotification;
import android.util.ArrayMap;
import android.util.Log;
import com.android.textclassifier.notification.SmartSuggestions;
import com.android.textclassifier.notification.SmartSuggestionsHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"OverrideAbstract"})
public class Assistant extends NotificationAssistantService {
    private static final boolean DEBUG = Log.isLoggable("ExtAssistant", 3);
    private NotificationCategorizer mNotificationCategorizer;
    private PackageManager mPackageManager;
    protected AssistantSettings mSettings;
    private SmartSuggestionsHelper mSmartSuggestionsHelper;
    private SmsHelper mSmsHelper;
    protected ArrayMap<String, NotificationEntry> mLiveNotifications = new ArrayMap<>();
    private final ExecutorService mSingleThreadExecutor = Executors.newSingleThreadExecutor();
    protected AssistantSettings.Factory mSettingsFactory = AssistantSettings.FACTORY;

    public void onListenerDisconnected() {
    }

    public Adjustment onNotificationEnqueued(StatusBarNotification statusBarNotification) {
        return null;
    }

    public void onNotificationSnoozedUntilContext(StatusBarNotification statusBarNotification, String str) {
    }

    public void onNotificationsSeen(List<String> list) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onCreate() {
        super.onCreate();
        this.mPackageManager = getPackageManager();
        this.mSettings = this.mSettingsFactory.createAndRegister();
        this.mSmartSuggestionsHelper = new SmartSuggestionsHelper(this, this.mSettings);
        this.mNotificationCategorizer = new NotificationCategorizer();
        SmsHelper smsHelper = new SmsHelper(this);
        this.mSmsHelper = smsHelper;
        smsHelper.initialize();
    }

    public void onDestroy() {
        SmsHelper smsHelper = this.mSmsHelper;
        if (smsHelper != null) {
            smsHelper.destroy();
        }
        super.onDestroy();
    }

    public Adjustment onNotificationEnqueued(final StatusBarNotification statusBarNotification, final NotificationChannel notificationChannel) {
        if (DEBUG) {
            Log.i("ExtAssistant", "ENQUEUED " + statusBarNotification.getKey() + " on " + notificationChannel.getId());
        }
        if (!isForCurrentUser(statusBarNotification)) {
            return null;
        }
        this.mSingleThreadExecutor.submit(new Runnable() { // from class: android.ext.services.notification.-$$Lambda$Assistant$qgLReRMtuuL1lIroEENW9NmyU-o
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onNotificationEnqueued$0$Assistant(statusBarNotification, notificationChannel);
            }
        });
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: lambda$onNotificationEnqueued$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onNotificationEnqueued$0$Assistant(StatusBarNotification statusBarNotification, NotificationChannel notificationChannel) {
        NotificationEntry notificationEntry = new NotificationEntry(this, this.mPackageManager, statusBarNotification, notificationChannel, this.mSmsHelper);
        SmartSuggestions smartSuggestionsOnNotificationEnqueued = this.mSmartSuggestionsHelper.onNotificationEnqueued(statusBarNotification);
        if (DEBUG) {
            Log.d("ExtAssistant", String.format("Creating Adjustment for %s, with %d actions, and %d replies.", statusBarNotification.getKey(), Integer.valueOf(smartSuggestionsOnNotificationEnqueued.getActions().size()), Integer.valueOf(smartSuggestionsOnNotificationEnqueued.getReplies().size())));
        }
        adjustNotification(createEnqueuedNotificationAdjustment(notificationEntry, new ArrayList(smartSuggestionsOnNotificationEnqueued.getActions()), new ArrayList(smartSuggestionsOnNotificationEnqueued.getReplies())));
    }

    Adjustment createEnqueuedNotificationAdjustment(NotificationEntry notificationEntry, ArrayList<Notification.Action> arrayList, ArrayList<CharSequence> arrayList2) {
        Bundle bundle = new Bundle();
        if (!arrayList.isEmpty()) {
            bundle.putParcelableArrayList("key_contextual_actions", arrayList);
        }
        if (!arrayList2.isEmpty()) {
            bundle.putCharSequenceArrayList("key_text_replies", arrayList2);
        }
        if (this.mNotificationCategorizer.shouldSilence(notificationEntry)) {
            bundle.putInt("key_importance", notificationEntry.getImportance() < 2 ? notificationEntry.getImportance() : 2);
        } else {
            bundle.putInt("key_importance", notificationEntry.getImportance());
        }
        return new Adjustment(notificationEntry.getSbn().getPackageName(), notificationEntry.getSbn().getKey(), bundle, "", notificationEntry.getSbn().getUserId());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onNotificationPosted(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap) {
        if (DEBUG) {
            Log.i("ExtAssistant", "POSTED " + statusBarNotification.getKey());
        }
        try {
            if (isForCurrentUser(statusBarNotification)) {
                NotificationListenerService.Ranking ranking = new NotificationListenerService.Ranking();
                rankingMap.getRanking(statusBarNotification.getKey(), ranking);
                if (ranking.getChannel() != null) {
                    this.mLiveNotifications.put(statusBarNotification.getKey(), new NotificationEntry(this, this.mPackageManager, statusBarNotification, ranking.getChannel(), this.mSmsHelper));
                }
            }
        } catch (Throwable th) {
            Log.e("ExtAssistant", "Error occurred processing post", th);
        }
    }

    public void onNotificationRemoved(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap, NotificationStats notificationStats, int i) {
        try {
            if (isForCurrentUser(statusBarNotification)) {
                this.mLiveNotifications.remove(statusBarNotification.getKey());
            }
        } catch (Throwable th) {
            Log.e("ExtAssistant", "Error occurred processing removal of " + statusBarNotification.getKey(), th);
        }
    }

    public void onNotificationExpansionChanged(String str, boolean z, final boolean z2) {
        if (DEBUG) {
            Log.d("ExtAssistant", "onNotificationExpansionChanged() called with: key = [" + str + "], isUserAction = [" + z + "], isExpanded = [" + z2 + "]");
        }
        final NotificationEntry notificationEntry = this.mLiveNotifications.get(str);
        if (notificationEntry != null) {
            this.mSingleThreadExecutor.submit(new Runnable() { // from class: android.ext.services.notification.-$$Lambda$Assistant$8OcbgaBkWzZD9QqsQxJ8tATyoDg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onNotificationExpansionChanged$1$Assistant(notificationEntry, z2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onNotificationExpansionChanged$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onNotificationExpansionChanged$1$Assistant(NotificationEntry notificationEntry, boolean z) {
        this.mSmartSuggestionsHelper.onNotificationExpansionChanged(notificationEntry.getSbn(), z);
    }

    public void onNotificationDirectReplied(final String str) {
        if (DEBUG) {
            Log.i("ExtAssistant", "onNotificationDirectReplied " + str);
        }
        this.mSingleThreadExecutor.submit(new Runnable() { // from class: android.ext.services.notification.-$$Lambda$Assistant$enxCDYGVDNz5w3C940JBwNCQS_0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onNotificationDirectReplied$2$Assistant(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onNotificationDirectReplied$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onNotificationDirectReplied$2$Assistant(String str) {
        this.mSmartSuggestionsHelper.onNotificationDirectReplied(str);
    }

    public void onSuggestedReplySent(final String str, final CharSequence charSequence, final int i) {
        if (DEBUG) {
            Log.d("ExtAssistant", "onSuggestedReplySent() called with: key = [" + str + "], reply = [" + ((Object) charSequence) + "], source = [" + i + "]");
        }
        this.mSingleThreadExecutor.submit(new Runnable() { // from class: android.ext.services.notification.-$$Lambda$Assistant$KNS2YvWxIn_DhQn56_TYhEouU5g
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onSuggestedReplySent$3$Assistant(str, charSequence, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onSuggestedReplySent$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onSuggestedReplySent$3$Assistant(String str, CharSequence charSequence, int i) {
        this.mSmartSuggestionsHelper.onSuggestedReplySent(str, charSequence, i);
    }

    public void onActionInvoked(final String str, final Notification.Action action, final int i) {
        if (DEBUG) {
            Log.d("ExtAssistant", "onActionInvoked() called with: key = [" + str + "], action = [" + ((Object) action.title) + "], source = [" + i + "]");
        }
        this.mSingleThreadExecutor.submit(new Runnable() { // from class: android.ext.services.notification.-$$Lambda$Assistant$zkD5hZILRH-2pAmtLN6pAn6Qgx8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onActionInvoked$4$Assistant(str, action, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onActionInvoked$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onActionInvoked$4$Assistant(String str, Notification.Action action, int i) {
        this.mSmartSuggestionsHelper.onActionClicked(str, action, i);
    }

    public void onListenerConnected() {
        if (DEBUG) {
            Log.i("ExtAssistant", "Connected");
        }
    }

    private boolean isForCurrentUser(StatusBarNotification statusBarNotification) {
        return statusBarNotification != null && statusBarNotification.getUserId() == UserHandle.myUserId();
    }
}
