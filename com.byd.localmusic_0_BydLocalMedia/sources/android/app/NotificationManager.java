package android.app;

import android.annotation.SystemApi;
import android.app.INotificationManager;
import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ParceledListSlice;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.StrictMode;
import android.os.UserHandle;
import android.service.notification.Condition;
import android.service.notification.StatusBarNotification;
import android.service.notification.ZenModeConfig;
import android.util.Log;
import android.util.proto.ProtoOutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class NotificationManager {
    public static final String ACTION_APP_BLOCK_STATE_CHANGED = "android.app.action.APP_BLOCK_STATE_CHANGED";
    public static final String ACTION_AUTOMATIC_ZEN_RULE = "android.app.action.AUTOMATIC_ZEN_RULE";
    public static final String ACTION_AUTOMATIC_ZEN_RULE_STATUS_CHANGED = "android.app.action.AUTOMATIC_ZEN_RULE_STATUS_CHANGED";
    public static final String ACTION_EFFECTS_SUPPRESSOR_CHANGED = "android.os.action.ACTION_EFFECTS_SUPPRESSOR_CHANGED";
    public static final String ACTION_INTERRUPTION_FILTER_CHANGED = "android.app.action.INTERRUPTION_FILTER_CHANGED";
    public static final String ACTION_INTERRUPTION_FILTER_CHANGED_INTERNAL = "android.app.action.INTERRUPTION_FILTER_CHANGED_INTERNAL";
    public static final String ACTION_NOTIFICATION_CHANNEL_BLOCK_STATE_CHANGED = "android.app.action.NOTIFICATION_CHANNEL_BLOCK_STATE_CHANGED";
    public static final String ACTION_NOTIFICATION_CHANNEL_GROUP_BLOCK_STATE_CHANGED = "android.app.action.NOTIFICATION_CHANNEL_GROUP_BLOCK_STATE_CHANGED";
    public static final String ACTION_NOTIFICATION_POLICY_ACCESS_GRANTED_CHANGED = "android.app.action.NOTIFICATION_POLICY_ACCESS_GRANTED_CHANGED";
    public static final String ACTION_NOTIFICATION_POLICY_CHANGED = "android.app.action.NOTIFICATION_POLICY_CHANGED";
    public static final int AUTOMATIC_RULE_STATUS_DISABLED = 2;
    public static final int AUTOMATIC_RULE_STATUS_ENABLED = 1;
    public static final int AUTOMATIC_RULE_STATUS_REMOVED = 3;
    public static final int AUTOMATIC_RULE_STATUS_UNKNOWN = -1;
    public static final int BUBBLE_PREFERENCE_ALL = 1;
    public static final int BUBBLE_PREFERENCE_NONE = 0;
    public static final int BUBBLE_PREFERENCE_SELECTED = 2;
    public static final String EXTRA_AUTOMATIC_RULE_ID = "android.app.extra.AUTOMATIC_RULE_ID";
    public static final String EXTRA_AUTOMATIC_ZEN_RULE_ID = "android.app.extra.AUTOMATIC_ZEN_RULE_ID";
    public static final String EXTRA_AUTOMATIC_ZEN_RULE_STATUS = "android.app.extra.AUTOMATIC_ZEN_RULE_STATUS";
    public static final String EXTRA_BLOCKED_STATE = "android.app.extra.BLOCKED_STATE";
    public static final String EXTRA_NOTIFICATION_CHANNEL_GROUP_ID = "android.app.extra.NOTIFICATION_CHANNEL_GROUP_ID";
    public static final String EXTRA_NOTIFICATION_CHANNEL_ID = "android.app.extra.NOTIFICATION_CHANNEL_ID";
    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_MAX = 5;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;
    public static final int INTERRUPTION_FILTER_ALARMS = 4;
    public static final int INTERRUPTION_FILTER_ALL = 1;
    public static final int INTERRUPTION_FILTER_NONE = 3;
    public static final int INTERRUPTION_FILTER_PRIORITY = 2;
    public static final int INTERRUPTION_FILTER_UNKNOWN = 0;
    public static final String META_DATA_AUTOMATIC_RULE_TYPE = "android.service.zen.automatic.ruleType";
    public static final String META_DATA_RULE_INSTANCE_LIMIT = "android.service.zen.automatic.ruleInstanceLimit";
    private static String TAG = "NotificationManager";
    public static final int VISIBILITY_NO_OVERRIDE = -1000;
    private static boolean localLOGV = false;
    private static INotificationManager sService;
    private Context mContext;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AutomaticZenRuleStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Importance {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface InterruptionFilter {
    }

    public static int zenModeFromInterruptionFilter(int i, int i2) {
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        if (i == 3) {
            return 2;
        }
        if (i != 4) {
            return i2;
        }
        return 3;
    }

    public static int zenModeToInterruptionFilter(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            return i != 3 ? 0 : 4;
        }
        return 3;
    }

    public static INotificationManager getService() {
        INotificationManager iNotificationManager = sService;
        if (iNotificationManager != null) {
            return iNotificationManager;
        }
        INotificationManager iNotificationManagerAsInterface = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
        sService = iNotificationManagerAsInterface;
        return iNotificationManagerAsInterface;
    }

    NotificationManager(Context context, Handler handler) {
        this.mContext = context;
    }

    public static NotificationManager from(Context context) {
        return (NotificationManager) context.getSystemService("notification");
    }

    public void notify(int i, Notification notification) {
        notify(null, i, notification);
    }

    public void notify(String str, int i, Notification notification) {
        notifyAsUser(str, i, notification, this.mContext.getUser());
    }

    public void notifyAsPackage(String str, String str2, int i, Notification notification) {
        INotificationManager service = getService();
        String packageName = this.mContext.getPackageName();
        try {
            if (localLOGV) {
                Log.v(TAG, packageName + ": notify(" + i + ", " + notification + ")");
            }
            service.enqueueNotificationWithTag(str, packageName, str2, i, fixNotification(notification), this.mContext.getUser().getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void notifyAsUser(String str, int i, Notification notification, UserHandle userHandle) {
        INotificationManager service = getService();
        String packageName = this.mContext.getPackageName();
        try {
            if (localLOGV) {
                Log.v(TAG, packageName + ": notify(" + i + ", " + notification + ")");
            }
            service.enqueueNotificationWithTag(packageName, this.mContext.getOpPackageName(), str, i, fixNotification(notification), userHandle.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private Notification fixNotification(Notification notification) {
        String packageName = this.mContext.getPackageName();
        Notification.addFieldsFromContext(this.mContext, notification);
        if (notification.sound != null) {
            notification.sound = notification.sound.getCanonicalUri();
            if (StrictMode.vmFileUriExposureEnabled()) {
                notification.sound.checkFileUriExposed("Notification.sound");
            }
        }
        fixLegacySmallIcon(notification, packageName);
        if (this.mContext.getApplicationInfo().targetSdkVersion > 22 && notification.getSmallIcon() == null) {
            throw new IllegalArgumentException("Invalid notification (no valid small icon): " + notification);
        }
        notification.reduceImageSizes(this.mContext);
        return Notification.Builder.maybeCloneStrippedForDelivery(notification);
    }

    private void fixLegacySmallIcon(Notification notification, String str) {
        if (notification.getSmallIcon() != null || notification.icon == 0) {
            return;
        }
        notification.setSmallIcon(Icon.createWithResource(str, notification.icon));
    }

    public void cancel(int i) {
        cancel(null, i);
    }

    public void cancel(String str, int i) {
        cancelAsUser(str, i, this.mContext.getUser());
    }

    public void cancelAsPackage(String str, String str2, int i) {
        try {
            getService().cancelNotificationWithTag(str, this.mContext.getOpPackageName(), str2, i, this.mContext.getUser().getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void cancelAsUser(String str, int i, UserHandle userHandle) {
        INotificationManager service = getService();
        String packageName = this.mContext.getPackageName();
        if (localLOGV) {
            Log.v(TAG, packageName + ": cancel(" + i + ")");
        }
        try {
            service.cancelNotificationWithTag(packageName, this.mContext.getOpPackageName(), str, i, userHandle.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void cancelAll() {
        INotificationManager service = getService();
        String packageName = this.mContext.getPackageName();
        if (localLOGV) {
            Log.v(TAG, packageName + ": cancelAll()");
        }
        try {
            service.cancelAllNotifications(packageName, this.mContext.getUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setNotificationDelegate(String str) {
        INotificationManager service = getService();
        String packageName = this.mContext.getPackageName();
        if (localLOGV) {
            Log.v(TAG, packageName + ": cancelAll()");
        }
        try {
            service.setNotificationDelegate(packageName, str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public String getNotificationDelegate() {
        try {
            return getService().getNotificationDelegate(this.mContext.getPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean canNotifyAsPackage(String str) {
        try {
            return getService().canNotifyAsPackage(this.mContext.getPackageName(), str, this.mContext.getUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void createNotificationChannelGroup(NotificationChannelGroup notificationChannelGroup) {
        createNotificationChannelGroups(Arrays.asList(notificationChannelGroup));
    }

    public void createNotificationChannelGroups(List<NotificationChannelGroup> list) {
        try {
            getService().createNotificationChannelGroups(this.mContext.getPackageName(), new ParceledListSlice(list));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void createNotificationChannel(NotificationChannel notificationChannel) {
        createNotificationChannels(Arrays.asList(notificationChannel));
    }

    public void createNotificationChannels(List<NotificationChannel> list) {
        try {
            getService().createNotificationChannels(this.mContext.getPackageName(), new ParceledListSlice(list));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public NotificationChannel getNotificationChannel(String str) {
        try {
            return getService().getNotificationChannel(this.mContext.getOpPackageName(), this.mContext.getUserId(), this.mContext.getPackageName(), str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public NotificationChannel getNotificationChannel(String str, String str2) {
        try {
            return getService().getConversationNotificationChannel(this.mContext.getOpPackageName(), this.mContext.getUserId(), this.mContext.getPackageName(), str, true, str2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<NotificationChannel> getNotificationChannels() {
        try {
            return getService().getNotificationChannels(this.mContext.getOpPackageName(), this.mContext.getPackageName(), this.mContext.getUserId()).getList();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void deleteNotificationChannel(String str) {
        try {
            getService().deleteNotificationChannel(this.mContext.getPackageName(), str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public NotificationChannelGroup getNotificationChannelGroup(String str) {
        try {
            return getService().getNotificationChannelGroup(this.mContext.getPackageName(), str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<NotificationChannelGroup> getNotificationChannelGroups() {
        try {
            ParceledListSlice notificationChannelGroups = getService().getNotificationChannelGroups(this.mContext.getPackageName());
            if (notificationChannelGroups != null) {
                return notificationChannelGroups.getList();
            }
            return new ArrayList();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void deleteNotificationChannelGroup(String str) {
        try {
            getService().deleteNotificationChannelGroup(this.mContext.getPackageName(), str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public ComponentName getEffectsSuppressor() {
        try {
            return getService().getEffectsSuppressor();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean matchesCallFilter(Bundle bundle) {
        try {
            return getService().matchesCallFilter(bundle);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isSystemConditionProviderEnabled(String str) {
        try {
            return getService().isSystemConditionProviderEnabled(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setZenMode(int i, Uri uri, String str) {
        try {
            getService().setZenMode(i, uri, str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getZenMode() {
        try {
            return getService().getZenMode();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public ZenModeConfig getZenModeConfig() {
        try {
            return getService().getZenModeConfig();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Policy getConsolidatedNotificationPolicy() {
        try {
            return getService().getConsolidatedNotificationPolicy();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getRuleInstanceCount(ComponentName componentName) {
        try {
            return getService().getRuleInstanceCount(componentName);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Map<String, AutomaticZenRule> getAutomaticZenRules() {
        try {
            List<ZenModeConfig.ZenRule> zenRules = getService().getZenRules();
            HashMap map = new HashMap();
            for (ZenModeConfig.ZenRule zenRule : zenRules) {
                map.put(zenRule.id, new AutomaticZenRule(zenRule.name, zenRule.component, zenRule.configurationActivity, zenRule.conditionId, zenRule.zenPolicy, zenModeToInterruptionFilter(zenRule.zenMode), zenRule.enabled, zenRule.creationTime));
            }
            return map;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public AutomaticZenRule getAutomaticZenRule(String str) {
        try {
            return getService().getAutomaticZenRule(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public String addAutomaticZenRule(AutomaticZenRule automaticZenRule) {
        try {
            return getService().addAutomaticZenRule(automaticZenRule);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean updateAutomaticZenRule(String str, AutomaticZenRule automaticZenRule) {
        try {
            return getService().updateAutomaticZenRule(str, automaticZenRule);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setAutomaticZenRuleState(String str, Condition condition) {
        try {
            getService().setAutomaticZenRuleState(str, condition);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean removeAutomaticZenRule(String str) {
        try {
            return getService().removeAutomaticZenRule(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean removeAutomaticZenRules(String str) {
        try {
            return getService().removeAutomaticZenRules(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getImportance() {
        try {
            return getService().getPackageImportance(this.mContext.getPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean areNotificationsEnabled() {
        try {
            return getService().areNotificationsEnabled(this.mContext.getPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean areBubblesAllowed() {
        try {
            return getService().areBubblesAllowed(this.mContext.getPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void silenceNotificationSound() {
        try {
            getService().silenceNotificationSound();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean areNotificationsPaused() {
        try {
            return getService().isPackagePaused(this.mContext.getPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isNotificationPolicyAccessGranted() {
        try {
            return getService().isNotificationPolicyAccessGranted(this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isNotificationListenerAccessGranted(ComponentName componentName) {
        try {
            return getService().isNotificationListenerAccessGranted(componentName);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isNotificationAssistantAccessGranted(ComponentName componentName) {
        try {
            return getService().isNotificationAssistantAccessGranted(componentName);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean shouldHideSilentStatusBarIcons() {
        try {
            return getService().shouldHideSilentStatusIcons(this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public List<String> getAllowedAssistantAdjustments() {
        try {
            return getService().getAllowedAssistantAdjustments(this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void allowAssistantAdjustment(String str) {
        try {
            getService().allowAssistantAdjustment(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void disallowAssistantAdjustment(String str) {
        try {
            getService().disallowAssistantAdjustment(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isNotificationPolicyAccessGrantedForPackage(String str) {
        try {
            return getService().isNotificationPolicyAccessGrantedForPackage(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<String> getEnabledNotificationListenerPackages() {
        try {
            return getService().getEnabledNotificationListenerPackages();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Policy getNotificationPolicy() {
        try {
            return getService().getNotificationPolicy(this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setNotificationPolicy(Policy policy) {
        checkRequired("policy", policy);
        try {
            getService().setNotificationPolicy(this.mContext.getOpPackageName(), policy);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setNotificationPolicyAccessGranted(String str, boolean z) {
        try {
            getService().setNotificationPolicyAccessGranted(str, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setNotificationListenerAccessGranted(ComponentName componentName, boolean z) {
        try {
            getService().setNotificationListenerAccessGranted(componentName, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setNotificationListenerAccessGrantedForUser(ComponentName componentName, int i, boolean z) {
        try {
            getService().setNotificationListenerAccessGrantedForUser(componentName, i, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setNotificationAssistantAccessGranted(ComponentName componentName, boolean z) {
        try {
            getService().setNotificationAssistantAccessGranted(componentName, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<ComponentName> getEnabledNotificationListeners(int i) {
        try {
            return getService().getEnabledNotificationListeners(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public ComponentName getAllowedNotificationAssistant() {
        try {
            return getService().getAllowedNotificationAssistant();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private static void checkRequired(String str, Object obj) {
        if (obj != null) {
            return;
        }
        throw new IllegalArgumentException(str + " is required");
    }

    public static class Policy implements Parcelable {
        public static final int CONVERSATION_SENDERS_ANYONE = 1;
        public static final int CONVERSATION_SENDERS_IMPORTANT = 2;
        public static final int CONVERSATION_SENDERS_NONE = 3;
        public static final int CONVERSATION_SENDERS_UNSET = -1;
        public static final int PRIORITY_CATEGORY_ALARMS = 32;
        public static final int PRIORITY_CATEGORY_CALLS = 8;
        public static final int PRIORITY_CATEGORY_CONVERSATIONS = 256;
        public static final int PRIORITY_CATEGORY_EVENTS = 2;
        public static final int PRIORITY_CATEGORY_MEDIA = 64;
        public static final int PRIORITY_CATEGORY_MESSAGES = 4;
        public static final int PRIORITY_CATEGORY_REMINDERS = 1;
        public static final int PRIORITY_CATEGORY_REPEAT_CALLERS = 16;
        public static final int PRIORITY_CATEGORY_SYSTEM = 128;
        public static final int PRIORITY_SENDERS_ANY = 0;
        public static final int PRIORITY_SENDERS_CONTACTS = 1;
        public static final int PRIORITY_SENDERS_STARRED = 2;
        public static final int STATE_CHANNELS_BYPASSING_DND = 1;
        public static final int STATE_UNSET = -1;
        public static final int SUPPRESSED_EFFECTS_UNSET = -1;
        public static final int SUPPRESSED_EFFECT_AMBIENT = 128;
        public static final int SUPPRESSED_EFFECT_BADGE = 64;
        public static final int SUPPRESSED_EFFECT_FULL_SCREEN_INTENT = 4;
        public static final int SUPPRESSED_EFFECT_LIGHTS = 8;
        public static final int SUPPRESSED_EFFECT_NOTIFICATION_LIST = 256;
        public static final int SUPPRESSED_EFFECT_PEEK = 16;

        @Deprecated
        public static final int SUPPRESSED_EFFECT_SCREEN_OFF = 1;

        @Deprecated
        public static final int SUPPRESSED_EFFECT_SCREEN_ON = 2;
        public static final int SUPPRESSED_EFFECT_STATUS_BAR = 32;
        public final int priorityCallSenders;
        public final int priorityCategories;
        public final int priorityConversationSenders;
        public final int priorityMessageSenders;
        public final int state;
        public final int suppressedVisualEffects;
        public static final int[] ALL_PRIORITY_CATEGORIES = {32, 64, 128, 1, 2, 4, 8, 16, 256};
        private static final int[] ALL_SUPPRESSED_EFFECTS = {1, 2, 4, 8, 16, 32, 64, 128, 256};
        public static final Parcelable.Creator<Policy> CREATOR = new Parcelable.Creator<Policy>() { // from class: android.app.NotificationManager.Policy.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Policy createFromParcel(Parcel parcel) {
                return new Policy(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Policy[] newArray(int i) {
                return new Policy[i];
            }
        };

        @Retention(RetentionPolicy.SOURCE)
        public @interface ConversationSenders {
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface PrioritySenders {
        }

        private boolean suppressedVisualEffectsEqual(int i, int i2) {
            if (i == i2) {
                return true;
            }
            if ((i & 2) != 0) {
                i |= 16;
            }
            if ((i & 1) != 0) {
                i = i | 4 | 8 | 128;
            }
            if ((i2 & 2) != 0) {
                i2 |= 16;
            }
            if ((i2 & 1) != 0) {
                i2 = i2 | 4 | 8 | 128;
            }
            int i3 = i & 2;
            if (i3 != (i2 & 2)) {
                if (((i3 != 0 ? i2 : i) & 16) == 0) {
                    return false;
                }
            }
            int i4 = i & 1;
            if (i4 != (i2 & 1)) {
                int i5 = i4 != 0 ? i2 : i;
                if ((i5 & 4) == 0 || (i5 & 8) == 0 || (i5 & 128) == 0) {
                    return false;
                }
            }
            return ((i & (-3)) & (-2)) == ((i2 & (-3)) & (-2));
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Policy(int i, int i2, int i3) {
            this(i, i2, i3, -1, -1, -1);
        }

        public Policy(int i, int i2, int i3, int i4) {
            this(i, i2, i3, i4, -1, -1);
        }

        public Policy(int i, int i2, int i3, int i4, int i5) {
            this(i, i2, i3, i4, -1, i5);
        }

        public Policy(int i, int i2, int i3, int i4, int i5, int i6) {
            this.priorityCategories = i;
            this.priorityCallSenders = i2;
            this.priorityMessageSenders = i3;
            this.suppressedVisualEffects = i4;
            this.state = i5;
            this.priorityConversationSenders = i6;
        }

        public Policy(Parcel parcel) {
            this(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.priorityCategories);
            parcel.writeInt(this.priorityCallSenders);
            parcel.writeInt(this.priorityMessageSenders);
            parcel.writeInt(this.suppressedVisualEffects);
            parcel.writeInt(this.state);
            parcel.writeInt(this.priorityConversationSenders);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.priorityCategories), Integer.valueOf(this.priorityCallSenders), Integer.valueOf(this.priorityMessageSenders), Integer.valueOf(this.suppressedVisualEffects), Integer.valueOf(this.state), Integer.valueOf(this.priorityConversationSenders));
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Policy)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            Policy policy = (Policy) obj;
            return policy.priorityCategories == this.priorityCategories && policy.priorityCallSenders == this.priorityCallSenders && policy.priorityMessageSenders == this.priorityMessageSenders && suppressedVisualEffectsEqual(this.suppressedVisualEffects, policy.suppressedVisualEffects) && policy.state == this.state && policy.priorityConversationSenders == this.priorityConversationSenders;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("NotificationManager.Policy[priorityCategories=");
            sb.append(priorityCategoriesToString(this.priorityCategories));
            sb.append(",priorityCallSenders=");
            sb.append(prioritySendersToString(this.priorityCallSenders));
            sb.append(",priorityMessageSenders=");
            sb.append(prioritySendersToString(this.priorityMessageSenders));
            sb.append(",priorityConvSenders=");
            sb.append(conversationSendersToString(this.priorityConversationSenders));
            sb.append(",suppressedVisualEffects=");
            sb.append(suppressedEffectsToString(this.suppressedVisualEffects));
            sb.append(",areChannelsBypassingDnd=");
            sb.append((this.state & 1) != 0 ? "true" : "false");
            sb.append("]");
            return sb.toString();
        }

        public void dumpDebug(ProtoOutputStream protoOutputStream, long j) {
            long jStart = protoOutputStream.start(j);
            bitwiseToProtoEnum(protoOutputStream, 2259152797697L, this.priorityCategories);
            protoOutputStream.write(1159641169922L, this.priorityCallSenders);
            protoOutputStream.write(1159641169923L, this.priorityMessageSenders);
            bitwiseToProtoEnum(protoOutputStream, 2259152797700L, this.suppressedVisualEffects);
            protoOutputStream.end(jStart);
        }

        private static void bitwiseToProtoEnum(ProtoOutputStream protoOutputStream, long j, int i) {
            int i2 = 1;
            while (i > 0) {
                if ((i & 1) == 1) {
                    protoOutputStream.write(j, i2);
                }
                i2++;
                i >>>= 1;
            }
        }

        public static int getAllSuppressedVisualEffects() {
            int i = 0;
            int i2 = 0;
            while (true) {
                int[] iArr = ALL_SUPPRESSED_EFFECTS;
                if (i >= iArr.length) {
                    return i2;
                }
                i2 |= iArr[i];
                i++;
            }
        }

        public static boolean areAllVisualEffectsSuppressed(int i) {
            int i2 = 0;
            while (true) {
                int[] iArr = ALL_SUPPRESSED_EFFECTS;
                if (i2 >= iArr.length) {
                    return true;
                }
                if ((iArr[i2] & i) == 0) {
                    return false;
                }
                i2++;
            }
        }

        private static int toggleEffects(int i, int[] iArr, boolean z) {
            for (int i2 : iArr) {
                i = z ? i | i2 : i & (~i2);
            }
            return i;
        }

        public static String suppressedEffectsToString(int i) {
            if (i <= 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            int i2 = 0;
            while (true) {
                int[] iArr = ALL_SUPPRESSED_EFFECTS;
                if (i2 >= iArr.length) {
                    break;
                }
                int i3 = iArr[i2];
                if ((i & i3) != 0) {
                    if (sb.length() > 0) {
                        sb.append(',');
                    }
                    sb.append(effectToString(i3));
                }
                i &= ~i3;
                i2++;
            }
            if (i != 0) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append("UNKNOWN_");
                sb.append(i);
            }
            return sb.toString();
        }

        public static String priorityCategoriesToString(int i) {
            if (i == 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            int i2 = 0;
            while (true) {
                int[] iArr = ALL_PRIORITY_CATEGORIES;
                if (i2 >= iArr.length) {
                    break;
                }
                int i3 = iArr[i2];
                if ((i & i3) != 0) {
                    if (sb.length() > 0) {
                        sb.append(',');
                    }
                    sb.append(priorityCategoryToString(i3));
                }
                i &= ~i3;
                i2++;
            }
            if (i != 0) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append("PRIORITY_CATEGORY_UNKNOWN_");
                sb.append(i);
            }
            return sb.toString();
        }

        private static String effectToString(int i) {
            if (i == -1) {
                return "SUPPRESSED_EFFECTS_UNSET";
            }
            if (i == 4) {
                return "SUPPRESSED_EFFECT_FULL_SCREEN_INTENT";
            }
            if (i == 8) {
                return "SUPPRESSED_EFFECT_LIGHTS";
            }
            if (i == 16) {
                return "SUPPRESSED_EFFECT_PEEK";
            }
            if (i == 32) {
                return "SUPPRESSED_EFFECT_STATUS_BAR";
            }
            if (i == 64) {
                return "SUPPRESSED_EFFECT_BADGE";
            }
            if (i == 128) {
                return "SUPPRESSED_EFFECT_AMBIENT";
            }
            if (i == 256) {
                return "SUPPRESSED_EFFECT_NOTIFICATION_LIST";
            }
            if (i == 1) {
                return "SUPPRESSED_EFFECT_SCREEN_OFF";
            }
            if (i == 2) {
                return "SUPPRESSED_EFFECT_SCREEN_ON";
            }
            return "UNKNOWN_" + i;
        }

        private static String priorityCategoryToString(int i) {
            if (i == 1) {
                return "PRIORITY_CATEGORY_REMINDERS";
            }
            if (i == 2) {
                return "PRIORITY_CATEGORY_EVENTS";
            }
            if (i == 4) {
                return "PRIORITY_CATEGORY_MESSAGES";
            }
            if (i == 8) {
                return "PRIORITY_CATEGORY_CALLS";
            }
            if (i == 16) {
                return "PRIORITY_CATEGORY_REPEAT_CALLERS";
            }
            if (i == 32) {
                return "PRIORITY_CATEGORY_ALARMS";
            }
            if (i == 64) {
                return "PRIORITY_CATEGORY_MEDIA";
            }
            if (i == 128) {
                return "PRIORITY_CATEGORY_SYSTEM";
            }
            if (i == 256) {
                return "PRIORITY_CATEGORY_CONVERSATIONS";
            }
            return "PRIORITY_CATEGORY_UNKNOWN_" + i;
        }

        public static String prioritySendersToString(int i) {
            if (i == 0) {
                return "PRIORITY_SENDERS_ANY";
            }
            if (i == 1) {
                return "PRIORITY_SENDERS_CONTACTS";
            }
            if (i == 2) {
                return "PRIORITY_SENDERS_STARRED";
            }
            return "PRIORITY_SENDERS_UNKNOWN_" + i;
        }

        public static String conversationSendersToString(int i) {
            if (i == -1) {
                return "unset";
            }
            if (i == 1) {
                return "anyone";
            }
            if (i == 2) {
                return "important";
            }
            if (i == 3) {
                return "none";
            }
            return "invalidConversationType{" + i + "}";
        }

        public boolean allowAlarms() {
            return (this.priorityCategories & 32) != 0;
        }

        public boolean allowMedia() {
            return (this.priorityCategories & 64) != 0;
        }

        public boolean allowSystem() {
            return (this.priorityCategories & 128) != 0;
        }

        public boolean allowRepeatCallers() {
            return (this.priorityCategories & 16) != 0;
        }

        public boolean allowCalls() {
            return (this.priorityCategories & 8) != 0;
        }

        public boolean allowConversations() {
            return (this.priorityCategories & 256) != 0;
        }

        public boolean allowMessages() {
            return (this.priorityCategories & 4) != 0;
        }

        public boolean allowEvents() {
            return (this.priorityCategories & 2) != 0;
        }

        public boolean allowReminders() {
            return (this.priorityCategories & 1) != 0;
        }

        public int allowCallsFrom() {
            return this.priorityCallSenders;
        }

        public int allowMessagesFrom() {
            return this.priorityMessageSenders;
        }

        public int allowConversationsFrom() {
            return this.priorityConversationSenders;
        }

        public boolean showFullScreenIntents() {
            return (this.suppressedVisualEffects & 4) == 0;
        }

        public boolean showLights() {
            return (this.suppressedVisualEffects & 8) == 0;
        }

        public boolean showPeeking() {
            return (this.suppressedVisualEffects & 16) == 0;
        }

        public boolean showStatusBarIcons() {
            return (this.suppressedVisualEffects & 32) == 0;
        }

        public boolean showAmbient() {
            return (this.suppressedVisualEffects & 128) == 0;
        }

        public boolean showBadges() {
            return (this.suppressedVisualEffects & 64) == 0;
        }

        public boolean showInNotificationList() {
            return (this.suppressedVisualEffects & 256) == 0;
        }

        public Policy copy() {
            Parcel parcelObtain = Parcel.obtain();
            try {
                writeToParcel(parcelObtain, 0);
                parcelObtain.setDataPosition(0);
                return new Policy(parcelObtain);
            } finally {
                parcelObtain.recycle();
            }
        }
    }

    public StatusBarNotification[] getActiveNotifications() {
        try {
            ParceledListSlice appActiveNotifications = getService().getAppActiveNotifications(this.mContext.getPackageName(), this.mContext.getUserId());
            if (appActiveNotifications == null) {
                return new StatusBarNotification[0];
            }
            List list = appActiveNotifications.getList();
            return (StatusBarNotification[]) list.toArray(new StatusBarNotification[list.size()]);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public final int getCurrentInterruptionFilter() {
        try {
            return zenModeToInterruptionFilter(getService().getZenMode());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public final void setInterruptionFilter(int i) {
        try {
            getService().setInterruptionFilter(this.mContext.getOpPackageName(), i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
