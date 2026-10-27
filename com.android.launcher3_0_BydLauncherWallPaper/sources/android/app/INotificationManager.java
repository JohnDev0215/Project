package android.app;

import android.app.ITransientNotification;
import android.app.ITransientNotificationCallback;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.pm.ParceledListSlice;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import android.service.notification.Adjustment;
import android.service.notification.Condition;
import android.service.notification.IConditionProvider;
import android.service.notification.INotificationListener;
import android.service.notification.StatusBarNotification;
import android.service.notification.ZenModeConfig;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface INotificationManager extends IInterface {

    public static class Default implements INotificationManager {
        @Override // android.app.INotificationManager
        public String addAutomaticZenRule(AutomaticZenRule automaticZenRule) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public void allowAssistantAdjustment(String str) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void applyAdjustmentFromAssistant(INotificationListener iNotificationListener, Adjustment adjustment) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void applyAdjustmentsFromAssistant(INotificationListener iNotificationListener, List<Adjustment> list) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void applyEnqueuedAdjustmentFromAssistant(INotificationListener iNotificationListener, Adjustment adjustment) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void applyRestore(byte[] bArr, int i) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public boolean areBubblesAllowed(String str) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean areChannelsBypassingDnd() throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean areNotificationsEnabled(String str) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean areNotificationsEnabledForPackage(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.INotificationManager
        public boolean canNotifyAsPackage(String str, String str2, int i) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean canShowBadge(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public void cancelAllNotifications(String str, int i) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void cancelNotificationFromListener(INotificationListener iNotificationListener, String str, String str2, int i) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void cancelNotificationWithTag(String str, String str2, String str3, int i, int i2) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void cancelNotificationsFromListener(INotificationListener iNotificationListener, String[] strArr) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void cancelToast(String str, IBinder iBinder) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void clearData(String str, int i, boolean z) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void clearRequestedListenerHints(INotificationListener iNotificationListener) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void createConversationNotificationChannelForPackage(String str, int i, String str2, NotificationChannel notificationChannel, String str3) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void createNotificationChannelGroups(String str, ParceledListSlice parceledListSlice) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void createNotificationChannels(String str, ParceledListSlice parceledListSlice) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void createNotificationChannelsForPackage(String str, int i, ParceledListSlice parceledListSlice) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void deleteConversationNotificationChannels(String str, int i, String str2) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void deleteNotificationChannel(String str, String str2) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void deleteNotificationChannelGroup(String str, String str2) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void deleteNotificationHistoryItem(String str, int i, long j) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void disallowAssistantAdjustment(String str) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void enqueueNotificationWithTag(String str, String str2, String str3, int i, Notification notification, int i2) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void enqueueTextToast(String str, IBinder iBinder, CharSequence charSequence, int i, int i2, ITransientNotificationCallback iTransientNotificationCallback) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void enqueueToast(String str, IBinder iBinder, ITransientNotification iTransientNotification, int i, int i2) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void finishToken(String str, IBinder iBinder) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public StatusBarNotification[] getActiveNotifications(String str) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getActiveNotificationsFromListener(INotificationListener iNotificationListener, String[] strArr, int i) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public StatusBarNotification[] getActiveNotificationsWithAttribution(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public List<String> getAllowedAssistantAdjustments(String str) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ComponentName getAllowedNotificationAssistant() throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ComponentName getAllowedNotificationAssistantForUser(int i) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getAppActiveNotifications(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public int getAppsBypassingDndCount(int i) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public AutomaticZenRule getAutomaticZenRule(String str) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public byte[] getBackupPayload(int i) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public int getBlockedAppCount(int i) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public int getBlockedChannelCount(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public int getBubblePreferenceForPackage(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public NotificationManager.Policy getConsolidatedNotificationPolicy() throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public NotificationChannel getConversationNotificationChannel(String str, int i, String str2, String str3, boolean z, String str4) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getConversations(boolean z) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getConversationsForPackage(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public int getDeletedChannelCount(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public ComponentName getEffectsSuppressor() throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public List<String> getEnabledNotificationListenerPackages() throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public List<ComponentName> getEnabledNotificationListeners(int i) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public int getHintsFromListener(INotificationListener iNotificationListener) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public StatusBarNotification[] getHistoricalNotifications(String str, int i, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public StatusBarNotification[] getHistoricalNotificationsWithAttribution(String str, String str2, int i, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public int getInterruptionFilterFromListener(INotificationListener iNotificationListener) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public NotificationChannel getNotificationChannel(String str, int i, String str2, String str3) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public NotificationChannel getNotificationChannelForPackage(String str, int i, String str2, String str3, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public NotificationChannelGroup getNotificationChannelGroup(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public NotificationChannelGroup getNotificationChannelGroupForPackage(String str, String str2, int i) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getNotificationChannelGroups(String str) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getNotificationChannelGroupsForPackage(String str, int i, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getNotificationChannelGroupsFromPrivilegedListener(INotificationListener iNotificationListener, String str, UserHandle userHandle) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getNotificationChannels(String str, String str2, int i) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getNotificationChannelsBypassingDnd(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getNotificationChannelsForPackage(String str, int i, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getNotificationChannelsFromPrivilegedListener(INotificationListener iNotificationListener, String str, UserHandle userHandle) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public String getNotificationDelegate(String str) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public NotificationHistory getNotificationHistory(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public NotificationManager.Policy getNotificationPolicy(String str) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public int getNumNotificationChannelsForPackage(String str, int i, boolean z) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public int getPackageImportance(String str) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public NotificationChannelGroup getPopulatedNotificationChannelGroupForPackage(String str, int i, String str2, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public boolean getPrivateNotificationsAllowed() throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public int getRuleInstanceCount(ComponentName componentName) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getSnoozedNotificationsFromListener(INotificationListener iNotificationListener, int i) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public int getZenMode() throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public ZenModeConfig getZenModeConfig() throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public List<ZenModeConfig.ZenRule> getZenRules() throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public boolean hasSentValidMsg(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean hasUserDemotedInvalidMsgApp(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean isInInvalidMsgState(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean isNotificationAssistantAccessGranted(ComponentName componentName) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean isNotificationListenerAccessGranted(ComponentName componentName) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean isNotificationListenerAccessGrantedForUser(ComponentName componentName, int i) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean isNotificationPolicyAccessGranted(String str) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean isNotificationPolicyAccessGrantedForPackage(String str) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean isPackagePaused(String str) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean isSystemConditionProviderEnabled(String str) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean matchesCallFilter(Bundle bundle) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public void notifyConditions(String str, IConditionProvider iConditionProvider, Condition[] conditionArr) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public boolean onlyHasDefaultChannel(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public long pullStats(long j, int i, boolean z, List<ParcelFileDescriptor> list) throws RemoteException {
            return 0L;
        }

        @Override // android.app.INotificationManager
        public void registerListener(INotificationListener iNotificationListener, ComponentName componentName, int i) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public boolean removeAutomaticZenRule(String str) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean removeAutomaticZenRules(String str) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public void requestBindListener(ComponentName componentName) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void requestBindProvider(ComponentName componentName) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void requestHintsFromListener(INotificationListener iNotificationListener, int i) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void requestInterruptionFilterFromListener(INotificationListener iNotificationListener, int i) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void requestUnbindListener(INotificationListener iNotificationListener) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void requestUnbindProvider(IConditionProvider iConditionProvider) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setAutomaticZenRuleState(String str, Condition condition) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setBubblesAllowed(String str, int i, int i2) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setHideSilentStatusIcons(boolean z) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setInterruptionFilter(String str, int i) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setInvalidMsgAppDemoted(String str, int i, boolean z) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setNotificationAssistantAccessGranted(ComponentName componentName, boolean z) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setNotificationAssistantAccessGrantedForUser(ComponentName componentName, int i, boolean z) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setNotificationDelegate(String str, String str2) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setNotificationListenerAccessGranted(ComponentName componentName, boolean z) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setNotificationListenerAccessGrantedForUser(ComponentName componentName, int i, boolean z) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setNotificationPolicy(String str, NotificationManager.Policy policy) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setNotificationPolicyAccessGranted(String str, boolean z) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setNotificationPolicyAccessGrantedForUser(String str, int i, boolean z) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setNotificationsEnabledForPackage(String str, int i, boolean z) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setNotificationsEnabledWithImportanceLockForPackage(String str, int i, boolean z) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setNotificationsShownFromListener(INotificationListener iNotificationListener, String[] strArr) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setOnNotificationPostedTrimFromListener(INotificationListener iNotificationListener, int i) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setPrivateNotificationsAllowed(boolean z) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setShowBadge(String str, int i, boolean z) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setZenMode(int i, Uri uri, String str) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public boolean shouldHideSilentStatusIcons(String str) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public void silenceNotificationSound() throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void snoozeNotificationUntilContextFromListener(INotificationListener iNotificationListener, String str, String str2) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void snoozeNotificationUntilFromListener(INotificationListener iNotificationListener, String str, long j) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void unregisterListener(INotificationListener iNotificationListener, int i) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void unsnoozeNotificationFromAssistant(INotificationListener iNotificationListener, String str) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void unsnoozeNotificationFromSystemListener(INotificationListener iNotificationListener, String str) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public boolean updateAutomaticZenRule(String str, AutomaticZenRule automaticZenRule) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public void updateNotificationChannelForPackage(String str, int i, NotificationChannel notificationChannel) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void updateNotificationChannelFromPrivilegedListener(INotificationListener iNotificationListener, String str, UserHandle userHandle, NotificationChannel notificationChannel) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void updateNotificationChannelGroupForPackage(String str, int i, NotificationChannelGroup notificationChannelGroup) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void updateNotificationChannelGroupFromPrivilegedListener(INotificationListener iNotificationListener, String str, UserHandle userHandle, NotificationChannelGroup notificationChannelGroup) throws RemoteException {
        }
    }

    String addAutomaticZenRule(AutomaticZenRule automaticZenRule) throws RemoteException;

    void allowAssistantAdjustment(String str) throws RemoteException;

    void applyAdjustmentFromAssistant(INotificationListener iNotificationListener, Adjustment adjustment) throws RemoteException;

    void applyAdjustmentsFromAssistant(INotificationListener iNotificationListener, List<Adjustment> list) throws RemoteException;

    void applyEnqueuedAdjustmentFromAssistant(INotificationListener iNotificationListener, Adjustment adjustment) throws RemoteException;

    void applyRestore(byte[] bArr, int i) throws RemoteException;

    boolean areBubblesAllowed(String str) throws RemoteException;

    boolean areChannelsBypassingDnd() throws RemoteException;

    boolean areNotificationsEnabled(String str) throws RemoteException;

    boolean areNotificationsEnabledForPackage(String str, int i) throws RemoteException;

    boolean canNotifyAsPackage(String str, String str2, int i) throws RemoteException;

    boolean canShowBadge(String str, int i) throws RemoteException;

    void cancelAllNotifications(String str, int i) throws RemoteException;

    void cancelNotificationFromListener(INotificationListener iNotificationListener, String str, String str2, int i) throws RemoteException;

    void cancelNotificationWithTag(String str, String str2, String str3, int i, int i2) throws RemoteException;

    void cancelNotificationsFromListener(INotificationListener iNotificationListener, String[] strArr) throws RemoteException;

    void cancelToast(String str, IBinder iBinder) throws RemoteException;

    void clearData(String str, int i, boolean z) throws RemoteException;

    void clearRequestedListenerHints(INotificationListener iNotificationListener) throws RemoteException;

    void createConversationNotificationChannelForPackage(String str, int i, String str2, NotificationChannel notificationChannel, String str3) throws RemoteException;

    void createNotificationChannelGroups(String str, ParceledListSlice parceledListSlice) throws RemoteException;

    void createNotificationChannels(String str, ParceledListSlice parceledListSlice) throws RemoteException;

    void createNotificationChannelsForPackage(String str, int i, ParceledListSlice parceledListSlice) throws RemoteException;

    void deleteConversationNotificationChannels(String str, int i, String str2) throws RemoteException;

    void deleteNotificationChannel(String str, String str2) throws RemoteException;

    void deleteNotificationChannelGroup(String str, String str2) throws RemoteException;

    void deleteNotificationHistoryItem(String str, int i, long j) throws RemoteException;

    void disallowAssistantAdjustment(String str) throws RemoteException;

    void enqueueNotificationWithTag(String str, String str2, String str3, int i, Notification notification, int i2) throws RemoteException;

    void enqueueTextToast(String str, IBinder iBinder, CharSequence charSequence, int i, int i2, ITransientNotificationCallback iTransientNotificationCallback) throws RemoteException;

    void enqueueToast(String str, IBinder iBinder, ITransientNotification iTransientNotification, int i, int i2) throws RemoteException;

    void finishToken(String str, IBinder iBinder) throws RemoteException;

    StatusBarNotification[] getActiveNotifications(String str) throws RemoteException;

    ParceledListSlice getActiveNotificationsFromListener(INotificationListener iNotificationListener, String[] strArr, int i) throws RemoteException;

    StatusBarNotification[] getActiveNotificationsWithAttribution(String str, String str2) throws RemoteException;

    List<String> getAllowedAssistantAdjustments(String str) throws RemoteException;

    ComponentName getAllowedNotificationAssistant() throws RemoteException;

    ComponentName getAllowedNotificationAssistantForUser(int i) throws RemoteException;

    ParceledListSlice getAppActiveNotifications(String str, int i) throws RemoteException;

    int getAppsBypassingDndCount(int i) throws RemoteException;

    AutomaticZenRule getAutomaticZenRule(String str) throws RemoteException;

    byte[] getBackupPayload(int i) throws RemoteException;

    int getBlockedAppCount(int i) throws RemoteException;

    int getBlockedChannelCount(String str, int i) throws RemoteException;

    int getBubblePreferenceForPackage(String str, int i) throws RemoteException;

    NotificationManager.Policy getConsolidatedNotificationPolicy() throws RemoteException;

    NotificationChannel getConversationNotificationChannel(String str, int i, String str2, String str3, boolean z, String str4) throws RemoteException;

    ParceledListSlice getConversations(boolean z) throws RemoteException;

    ParceledListSlice getConversationsForPackage(String str, int i) throws RemoteException;

    int getDeletedChannelCount(String str, int i) throws RemoteException;

    ComponentName getEffectsSuppressor() throws RemoteException;

    List<String> getEnabledNotificationListenerPackages() throws RemoteException;

    List<ComponentName> getEnabledNotificationListeners(int i) throws RemoteException;

    int getHintsFromListener(INotificationListener iNotificationListener) throws RemoteException;

    StatusBarNotification[] getHistoricalNotifications(String str, int i, boolean z) throws RemoteException;

    StatusBarNotification[] getHistoricalNotificationsWithAttribution(String str, String str2, int i, boolean z) throws RemoteException;

    int getInterruptionFilterFromListener(INotificationListener iNotificationListener) throws RemoteException;

    NotificationChannel getNotificationChannel(String str, int i, String str2, String str3) throws RemoteException;

    NotificationChannel getNotificationChannelForPackage(String str, int i, String str2, String str3, boolean z) throws RemoteException;

    NotificationChannelGroup getNotificationChannelGroup(String str, String str2) throws RemoteException;

    NotificationChannelGroup getNotificationChannelGroupForPackage(String str, String str2, int i) throws RemoteException;

    ParceledListSlice getNotificationChannelGroups(String str) throws RemoteException;

    ParceledListSlice getNotificationChannelGroupsForPackage(String str, int i, boolean z) throws RemoteException;

    ParceledListSlice getNotificationChannelGroupsFromPrivilegedListener(INotificationListener iNotificationListener, String str, UserHandle userHandle) throws RemoteException;

    ParceledListSlice getNotificationChannels(String str, String str2, int i) throws RemoteException;

    ParceledListSlice getNotificationChannelsBypassingDnd(String str, int i) throws RemoteException;

    ParceledListSlice getNotificationChannelsForPackage(String str, int i, boolean z) throws RemoteException;

    ParceledListSlice getNotificationChannelsFromPrivilegedListener(INotificationListener iNotificationListener, String str, UserHandle userHandle) throws RemoteException;

    String getNotificationDelegate(String str) throws RemoteException;

    NotificationHistory getNotificationHistory(String str, String str2) throws RemoteException;

    NotificationManager.Policy getNotificationPolicy(String str) throws RemoteException;

    int getNumNotificationChannelsForPackage(String str, int i, boolean z) throws RemoteException;

    int getPackageImportance(String str) throws RemoteException;

    NotificationChannelGroup getPopulatedNotificationChannelGroupForPackage(String str, int i, String str2, boolean z) throws RemoteException;

    boolean getPrivateNotificationsAllowed() throws RemoteException;

    int getRuleInstanceCount(ComponentName componentName) throws RemoteException;

    ParceledListSlice getSnoozedNotificationsFromListener(INotificationListener iNotificationListener, int i) throws RemoteException;

    int getZenMode() throws RemoteException;

    ZenModeConfig getZenModeConfig() throws RemoteException;

    List<ZenModeConfig.ZenRule> getZenRules() throws RemoteException;

    boolean hasSentValidMsg(String str, int i) throws RemoteException;

    boolean hasUserDemotedInvalidMsgApp(String str, int i) throws RemoteException;

    boolean isInInvalidMsgState(String str, int i) throws RemoteException;

    boolean isNotificationAssistantAccessGranted(ComponentName componentName) throws RemoteException;

    boolean isNotificationListenerAccessGranted(ComponentName componentName) throws RemoteException;

    boolean isNotificationListenerAccessGrantedForUser(ComponentName componentName, int i) throws RemoteException;

    boolean isNotificationPolicyAccessGranted(String str) throws RemoteException;

    boolean isNotificationPolicyAccessGrantedForPackage(String str) throws RemoteException;

    boolean isPackagePaused(String str) throws RemoteException;

    boolean isSystemConditionProviderEnabled(String str) throws RemoteException;

    boolean matchesCallFilter(Bundle bundle) throws RemoteException;

    void notifyConditions(String str, IConditionProvider iConditionProvider, Condition[] conditionArr) throws RemoteException;

    boolean onlyHasDefaultChannel(String str, int i) throws RemoteException;

    long pullStats(long j, int i, boolean z, List<ParcelFileDescriptor> list) throws RemoteException;

    void registerListener(INotificationListener iNotificationListener, ComponentName componentName, int i) throws RemoteException;

    boolean removeAutomaticZenRule(String str) throws RemoteException;

    boolean removeAutomaticZenRules(String str) throws RemoteException;

    void requestBindListener(ComponentName componentName) throws RemoteException;

    void requestBindProvider(ComponentName componentName) throws RemoteException;

    void requestHintsFromListener(INotificationListener iNotificationListener, int i) throws RemoteException;

    void requestInterruptionFilterFromListener(INotificationListener iNotificationListener, int i) throws RemoteException;

    void requestUnbindListener(INotificationListener iNotificationListener) throws RemoteException;

    void requestUnbindProvider(IConditionProvider iConditionProvider) throws RemoteException;

    void setAutomaticZenRuleState(String str, Condition condition) throws RemoteException;

    void setBubblesAllowed(String str, int i, int i2) throws RemoteException;

    void setHideSilentStatusIcons(boolean z) throws RemoteException;

    void setInterruptionFilter(String str, int i) throws RemoteException;

    void setInvalidMsgAppDemoted(String str, int i, boolean z) throws RemoteException;

    void setNotificationAssistantAccessGranted(ComponentName componentName, boolean z) throws RemoteException;

    void setNotificationAssistantAccessGrantedForUser(ComponentName componentName, int i, boolean z) throws RemoteException;

    void setNotificationDelegate(String str, String str2) throws RemoteException;

    void setNotificationListenerAccessGranted(ComponentName componentName, boolean z) throws RemoteException;

    void setNotificationListenerAccessGrantedForUser(ComponentName componentName, int i, boolean z) throws RemoteException;

    void setNotificationPolicy(String str, NotificationManager.Policy policy) throws RemoteException;

    void setNotificationPolicyAccessGranted(String str, boolean z) throws RemoteException;

    void setNotificationPolicyAccessGrantedForUser(String str, int i, boolean z) throws RemoteException;

    void setNotificationsEnabledForPackage(String str, int i, boolean z) throws RemoteException;

    void setNotificationsEnabledWithImportanceLockForPackage(String str, int i, boolean z) throws RemoteException;

    void setNotificationsShownFromListener(INotificationListener iNotificationListener, String[] strArr) throws RemoteException;

    void setOnNotificationPostedTrimFromListener(INotificationListener iNotificationListener, int i) throws RemoteException;

    void setPrivateNotificationsAllowed(boolean z) throws RemoteException;

    void setShowBadge(String str, int i, boolean z) throws RemoteException;

    void setZenMode(int i, Uri uri, String str) throws RemoteException;

    boolean shouldHideSilentStatusIcons(String str) throws RemoteException;

    void silenceNotificationSound() throws RemoteException;

    void snoozeNotificationUntilContextFromListener(INotificationListener iNotificationListener, String str, String str2) throws RemoteException;

    void snoozeNotificationUntilFromListener(INotificationListener iNotificationListener, String str, long j) throws RemoteException;

    void unregisterListener(INotificationListener iNotificationListener, int i) throws RemoteException;

    void unsnoozeNotificationFromAssistant(INotificationListener iNotificationListener, String str) throws RemoteException;

    void unsnoozeNotificationFromSystemListener(INotificationListener iNotificationListener, String str) throws RemoteException;

    boolean updateAutomaticZenRule(String str, AutomaticZenRule automaticZenRule) throws RemoteException;

    void updateNotificationChannelForPackage(String str, int i, NotificationChannel notificationChannel) throws RemoteException;

    void updateNotificationChannelFromPrivilegedListener(INotificationListener iNotificationListener, String str, UserHandle userHandle, NotificationChannel notificationChannel) throws RemoteException;

    void updateNotificationChannelGroupForPackage(String str, int i, NotificationChannelGroup notificationChannelGroup) throws RemoteException;

    void updateNotificationChannelGroupFromPrivilegedListener(INotificationListener iNotificationListener, String str, UserHandle userHandle, NotificationChannelGroup notificationChannelGroup) throws RemoteException;

    public static abstract class Stub extends Binder implements INotificationManager {
        private static final String DESCRIPTOR = "android.app.INotificationManager";
        static final int TRANSACTION_addAutomaticZenRule = 121;
        static final int TRANSACTION_allowAssistantAdjustment = 21;
        static final int TRANSACTION_applyAdjustmentFromAssistant = 90;
        static final int TRANSACTION_applyAdjustmentsFromAssistant = 91;
        static final int TRANSACTION_applyEnqueuedAdjustmentFromAssistant = 89;
        static final int TRANSACTION_applyRestore = 128;
        static final int TRANSACTION_areBubblesAllowed = 26;
        static final int TRANSACTION_areChannelsBypassingDnd = 54;
        static final int TRANSACTION_areNotificationsEnabled = 18;
        static final int TRANSACTION_areNotificationsEnabledForPackage = 17;
        static final int TRANSACTION_canNotifyAsPackage = 132;
        static final int TRANSACTION_canShowBadge = 10;
        static final int TRANSACTION_cancelAllNotifications = 1;
        static final int TRANSACTION_cancelNotificationFromListener = 67;
        static final int TRANSACTION_cancelNotificationWithTag = 8;
        static final int TRANSACTION_cancelNotificationsFromListener = 68;
        static final int TRANSACTION_cancelToast = 5;
        static final int TRANSACTION_clearData = 2;
        static final int TRANSACTION_clearRequestedListenerHints = 78;
        static final int TRANSACTION_createConversationNotificationChannelForPackage = 40;
        static final int TRANSACTION_createNotificationChannelGroups = 28;
        static final int TRANSACTION_createNotificationChannels = 29;
        static final int TRANSACTION_createNotificationChannelsForPackage = 30;
        static final int TRANSACTION_deleteConversationNotificationChannels = 43;
        static final int TRANSACTION_deleteNotificationChannel = 42;
        static final int TRANSACTION_deleteNotificationChannelGroup = 49;
        static final int TRANSACTION_deleteNotificationHistoryItem = 58;
        static final int TRANSACTION_disallowAssistantAdjustment = 22;
        static final int TRANSACTION_enqueueNotificationWithTag = 7;
        static final int TRANSACTION_enqueueTextToast = 3;
        static final int TRANSACTION_enqueueToast = 4;
        static final int TRANSACTION_finishToken = 6;
        static final int TRANSACTION_getActiveNotifications = 60;
        static final int TRANSACTION_getActiveNotificationsFromListener = 76;
        static final int TRANSACTION_getActiveNotificationsWithAttribution = 61;
        static final int TRANSACTION_getAllowedAssistantAdjustments = 20;
        static final int TRANSACTION_getAllowedNotificationAssistant = 107;
        static final int TRANSACTION_getAllowedNotificationAssistantForUser = 106;
        static final int TRANSACTION_getAppActiveNotifications = 129;
        static final int TRANSACTION_getAppsBypassingDndCount = 55;
        static final int TRANSACTION_getAutomaticZenRule = 119;
        static final int TRANSACTION_getBackupPayload = 127;
        static final int TRANSACTION_getBlockedAppCount = 53;
        static final int TRANSACTION_getBlockedChannelCount = 48;
        static final int TRANSACTION_getBubblePreferenceForPackage = 27;
        static final int TRANSACTION_getConsolidatedNotificationPolicy = 110;
        static final int TRANSACTION_getConversationNotificationChannel = 39;
        static final int TRANSACTION_getConversations = 31;
        static final int TRANSACTION_getConversationsForPackage = 32;
        static final int TRANSACTION_getDeletedChannelCount = 47;
        static final int TRANSACTION_getEffectsSuppressor = 94;
        static final int TRANSACTION_getEnabledNotificationListenerPackages = 104;
        static final int TRANSACTION_getEnabledNotificationListeners = 105;
        static final int TRANSACTION_getHintsFromListener = 80;
        static final int TRANSACTION_getHistoricalNotifications = 62;
        static final int TRANSACTION_getHistoricalNotificationsWithAttribution = 63;
        static final int TRANSACTION_getInterruptionFilterFromListener = 82;
        static final int TRANSACTION_getNotificationChannel = 38;
        static final int TRANSACTION_getNotificationChannelForPackage = 41;
        static final int TRANSACTION_getNotificationChannelGroup = 50;
        static final int TRANSACTION_getNotificationChannelGroupForPackage = 34;
        static final int TRANSACTION_getNotificationChannelGroups = 51;
        static final int TRANSACTION_getNotificationChannelGroupsForPackage = 33;
        static final int TRANSACTION_getNotificationChannelGroupsFromPrivilegedListener = 88;
        static final int TRANSACTION_getNotificationChannels = 44;
        static final int TRANSACTION_getNotificationChannelsBypassingDnd = 56;
        static final int TRANSACTION_getNotificationChannelsForPackage = 45;
        static final int TRANSACTION_getNotificationChannelsFromPrivilegedListener = 87;
        static final int TRANSACTION_getNotificationDelegate = 131;
        static final int TRANSACTION_getNotificationHistory = 64;
        static final int TRANSACTION_getNotificationPolicy = 114;
        static final int TRANSACTION_getNumNotificationChannelsForPackage = 46;
        static final int TRANSACTION_getPackageImportance = 19;
        static final int TRANSACTION_getPopulatedNotificationChannelGroupForPackage = 35;
        static final int TRANSACTION_getPrivateNotificationsAllowed = 134;
        static final int TRANSACTION_getRuleInstanceCount = 125;
        static final int TRANSACTION_getSnoozedNotificationsFromListener = 77;
        static final int TRANSACTION_getZenMode = 108;
        static final int TRANSACTION_getZenModeConfig = 109;
        static final int TRANSACTION_getZenRules = 120;
        static final int TRANSACTION_hasSentValidMsg = 11;
        static final int TRANSACTION_hasUserDemotedInvalidMsgApp = 13;
        static final int TRANSACTION_isInInvalidMsgState = 12;
        static final int TRANSACTION_isNotificationAssistantAccessGranted = 99;
        static final int TRANSACTION_isNotificationListenerAccessGranted = 97;
        static final int TRANSACTION_isNotificationListenerAccessGrantedForUser = 98;
        static final int TRANSACTION_isNotificationPolicyAccessGranted = 113;
        static final int TRANSACTION_isNotificationPolicyAccessGrantedForPackage = 116;
        static final int TRANSACTION_isPackagePaused = 57;
        static final int TRANSACTION_isSystemConditionProviderEnabled = 96;
        static final int TRANSACTION_matchesCallFilter = 95;
        static final int TRANSACTION_notifyConditions = 112;
        static final int TRANSACTION_onlyHasDefaultChannel = 52;
        static final int TRANSACTION_pullStats = 135;
        static final int TRANSACTION_registerListener = 65;
        static final int TRANSACTION_removeAutomaticZenRule = 123;
        static final int TRANSACTION_removeAutomaticZenRules = 124;
        static final int TRANSACTION_requestBindListener = 71;
        static final int TRANSACTION_requestBindProvider = 73;
        static final int TRANSACTION_requestHintsFromListener = 79;
        static final int TRANSACTION_requestInterruptionFilterFromListener = 81;
        static final int TRANSACTION_requestUnbindListener = 72;
        static final int TRANSACTION_requestUnbindProvider = 74;
        static final int TRANSACTION_setAutomaticZenRuleState = 126;
        static final int TRANSACTION_setBubblesAllowed = 25;
        static final int TRANSACTION_setHideSilentStatusIcons = 24;
        static final int TRANSACTION_setInterruptionFilter = 84;
        static final int TRANSACTION_setInvalidMsgAppDemoted = 14;
        static final int TRANSACTION_setNotificationAssistantAccessGranted = 101;
        static final int TRANSACTION_setNotificationAssistantAccessGrantedForUser = 103;
        static final int TRANSACTION_setNotificationDelegate = 130;
        static final int TRANSACTION_setNotificationListenerAccessGranted = 100;
        static final int TRANSACTION_setNotificationListenerAccessGrantedForUser = 102;
        static final int TRANSACTION_setNotificationPolicy = 115;
        static final int TRANSACTION_setNotificationPolicyAccessGranted = 117;
        static final int TRANSACTION_setNotificationPolicyAccessGrantedForUser = 118;
        static final int TRANSACTION_setNotificationsEnabledForPackage = 15;
        static final int TRANSACTION_setNotificationsEnabledWithImportanceLockForPackage = 16;
        static final int TRANSACTION_setNotificationsShownFromListener = 75;
        static final int TRANSACTION_setOnNotificationPostedTrimFromListener = 83;
        static final int TRANSACTION_setPrivateNotificationsAllowed = 133;
        static final int TRANSACTION_setShowBadge = 9;
        static final int TRANSACTION_setZenMode = 111;
        static final int TRANSACTION_shouldHideSilentStatusIcons = 23;
        static final int TRANSACTION_silenceNotificationSound = 59;
        static final int TRANSACTION_snoozeNotificationUntilContextFromListener = 69;
        static final int TRANSACTION_snoozeNotificationUntilFromListener = 70;
        static final int TRANSACTION_unregisterListener = 66;
        static final int TRANSACTION_unsnoozeNotificationFromAssistant = 92;
        static final int TRANSACTION_unsnoozeNotificationFromSystemListener = 93;
        static final int TRANSACTION_updateAutomaticZenRule = 122;
        static final int TRANSACTION_updateNotificationChannelForPackage = 37;
        static final int TRANSACTION_updateNotificationChannelFromPrivilegedListener = 86;
        static final int TRANSACTION_updateNotificationChannelGroupForPackage = 36;
        static final int TRANSACTION_updateNotificationChannelGroupFromPrivilegedListener = 85;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "cancelAllNotifications";
                case 2:
                    return "clearData";
                case 3:
                    return "enqueueTextToast";
                case 4:
                    return "enqueueToast";
                case 5:
                    return "cancelToast";
                case 6:
                    return "finishToken";
                case 7:
                    return "enqueueNotificationWithTag";
                case 8:
                    return "cancelNotificationWithTag";
                case 9:
                    return "setShowBadge";
                case 10:
                    return "canShowBadge";
                case 11:
                    return "hasSentValidMsg";
                case 12:
                    return "isInInvalidMsgState";
                case 13:
                    return "hasUserDemotedInvalidMsgApp";
                case 14:
                    return "setInvalidMsgAppDemoted";
                case 15:
                    return "setNotificationsEnabledForPackage";
                case 16:
                    return "setNotificationsEnabledWithImportanceLockForPackage";
                case 17:
                    return "areNotificationsEnabledForPackage";
                case 18:
                    return "areNotificationsEnabled";
                case 19:
                    return "getPackageImportance";
                case 20:
                    return "getAllowedAssistantAdjustments";
                case 21:
                    return "allowAssistantAdjustment";
                case 22:
                    return "disallowAssistantAdjustment";
                case 23:
                    return "shouldHideSilentStatusIcons";
                case 24:
                    return "setHideSilentStatusIcons";
                case 25:
                    return "setBubblesAllowed";
                case 26:
                    return "areBubblesAllowed";
                case 27:
                    return "getBubblePreferenceForPackage";
                case 28:
                    return "createNotificationChannelGroups";
                case 29:
                    return "createNotificationChannels";
                case 30:
                    return "createNotificationChannelsForPackage";
                case 31:
                    return "getConversations";
                case 32:
                    return "getConversationsForPackage";
                case 33:
                    return "getNotificationChannelGroupsForPackage";
                case 34:
                    return "getNotificationChannelGroupForPackage";
                case 35:
                    return "getPopulatedNotificationChannelGroupForPackage";
                case 36:
                    return "updateNotificationChannelGroupForPackage";
                case 37:
                    return "updateNotificationChannelForPackage";
                case 38:
                    return "getNotificationChannel";
                case 39:
                    return "getConversationNotificationChannel";
                case 40:
                    return "createConversationNotificationChannelForPackage";
                case 41:
                    return "getNotificationChannelForPackage";
                case 42:
                    return "deleteNotificationChannel";
                case 43:
                    return "deleteConversationNotificationChannels";
                case 44:
                    return "getNotificationChannels";
                case 45:
                    return "getNotificationChannelsForPackage";
                case 46:
                    return "getNumNotificationChannelsForPackage";
                case 47:
                    return "getDeletedChannelCount";
                case 48:
                    return "getBlockedChannelCount";
                case 49:
                    return "deleteNotificationChannelGroup";
                case 50:
                    return "getNotificationChannelGroup";
                case 51:
                    return "getNotificationChannelGroups";
                case 52:
                    return "onlyHasDefaultChannel";
                case 53:
                    return "getBlockedAppCount";
                case 54:
                    return "areChannelsBypassingDnd";
                case 55:
                    return "getAppsBypassingDndCount";
                case 56:
                    return "getNotificationChannelsBypassingDnd";
                case 57:
                    return "isPackagePaused";
                case 58:
                    return "deleteNotificationHistoryItem";
                case 59:
                    return "silenceNotificationSound";
                case 60:
                    return "getActiveNotifications";
                case 61:
                    return "getActiveNotificationsWithAttribution";
                case 62:
                    return "getHistoricalNotifications";
                case 63:
                    return "getHistoricalNotificationsWithAttribution";
                case 64:
                    return "getNotificationHistory";
                case 65:
                    return "registerListener";
                case 66:
                    return "unregisterListener";
                case 67:
                    return "cancelNotificationFromListener";
                case 68:
                    return "cancelNotificationsFromListener";
                case 69:
                    return "snoozeNotificationUntilContextFromListener";
                case 70:
                    return "snoozeNotificationUntilFromListener";
                case 71:
                    return "requestBindListener";
                case 72:
                    return "requestUnbindListener";
                case 73:
                    return "requestBindProvider";
                case 74:
                    return "requestUnbindProvider";
                case 75:
                    return "setNotificationsShownFromListener";
                case 76:
                    return "getActiveNotificationsFromListener";
                case 77:
                    return "getSnoozedNotificationsFromListener";
                case 78:
                    return "clearRequestedListenerHints";
                case 79:
                    return "requestHintsFromListener";
                case 80:
                    return "getHintsFromListener";
                case 81:
                    return "requestInterruptionFilterFromListener";
                case 82:
                    return "getInterruptionFilterFromListener";
                case 83:
                    return "setOnNotificationPostedTrimFromListener";
                case 84:
                    return "setInterruptionFilter";
                case 85:
                    return "updateNotificationChannelGroupFromPrivilegedListener";
                case 86:
                    return "updateNotificationChannelFromPrivilegedListener";
                case 87:
                    return "getNotificationChannelsFromPrivilegedListener";
                case 88:
                    return "getNotificationChannelGroupsFromPrivilegedListener";
                case 89:
                    return "applyEnqueuedAdjustmentFromAssistant";
                case 90:
                    return "applyAdjustmentFromAssistant";
                case 91:
                    return "applyAdjustmentsFromAssistant";
                case 92:
                    return "unsnoozeNotificationFromAssistant";
                case 93:
                    return "unsnoozeNotificationFromSystemListener";
                case 94:
                    return "getEffectsSuppressor";
                case 95:
                    return "matchesCallFilter";
                case 96:
                    return "isSystemConditionProviderEnabled";
                case 97:
                    return "isNotificationListenerAccessGranted";
                case 98:
                    return "isNotificationListenerAccessGrantedForUser";
                case 99:
                    return "isNotificationAssistantAccessGranted";
                case 100:
                    return "setNotificationListenerAccessGranted";
                case 101:
                    return "setNotificationAssistantAccessGranted";
                case 102:
                    return "setNotificationListenerAccessGrantedForUser";
                case 103:
                    return "setNotificationAssistantAccessGrantedForUser";
                case 104:
                    return "getEnabledNotificationListenerPackages";
                case 105:
                    return "getEnabledNotificationListeners";
                case 106:
                    return "getAllowedNotificationAssistantForUser";
                case 107:
                    return "getAllowedNotificationAssistant";
                case 108:
                    return "getZenMode";
                case 109:
                    return "getZenModeConfig";
                case 110:
                    return "getConsolidatedNotificationPolicy";
                case 111:
                    return "setZenMode";
                case 112:
                    return "notifyConditions";
                case 113:
                    return "isNotificationPolicyAccessGranted";
                case 114:
                    return "getNotificationPolicy";
                case 115:
                    return "setNotificationPolicy";
                case 116:
                    return "isNotificationPolicyAccessGrantedForPackage";
                case 117:
                    return "setNotificationPolicyAccessGranted";
                case 118:
                    return "setNotificationPolicyAccessGrantedForUser";
                case 119:
                    return "getAutomaticZenRule";
                case 120:
                    return "getZenRules";
                case 121:
                    return "addAutomaticZenRule";
                case 122:
                    return "updateAutomaticZenRule";
                case 123:
                    return "removeAutomaticZenRule";
                case 124:
                    return "removeAutomaticZenRules";
                case 125:
                    return "getRuleInstanceCount";
                case 126:
                    return "setAutomaticZenRuleState";
                case 127:
                    return "getBackupPayload";
                case 128:
                    return "applyRestore";
                case 129:
                    return "getAppActiveNotifications";
                case 130:
                    return "setNotificationDelegate";
                case 131:
                    return "getNotificationDelegate";
                case 132:
                    return "canNotifyAsPackage";
                case 133:
                    return "setPrivateNotificationsAllowed";
                case 134:
                    return "getPrivateNotificationsAllowed";
                case 135:
                    return "pullStats";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INotificationManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INotificationManager)) {
                return (INotificationManager) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelAllNotifications(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearData(parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    enqueueTextToast(parcel.readString(), parcel.readStrongBinder(), parcel.readInt() != 0 ? TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), ITransientNotificationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    enqueueToast(parcel.readString(), parcel.readStrongBinder(), ITransientNotification.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelToast(parcel.readString(), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    finishToken(parcel.readString(), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    enqueueNotificationWithTag(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? Notification.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelNotificationWithTag(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    setShowBadge(parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCanShowBadge = canShowBadge(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCanShowBadge ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasSentValidMsg = hasSentValidMsg(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasSentValidMsg ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsInInvalidMsgState = isInInvalidMsgState(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsInInvalidMsgState ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasUserDemotedInvalidMsgApp = hasUserDemotedInvalidMsgApp(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasUserDemotedInvalidMsgApp ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInvalidMsgAppDemoted(parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNotificationsEnabledForPackage(parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNotificationsEnabledWithImportanceLockForPackage(parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAreNotificationsEnabledForPackage = areNotificationsEnabledForPackage(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAreNotificationsEnabledForPackage ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAreNotificationsEnabled = areNotificationsEnabled(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAreNotificationsEnabled ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    int packageImportance = getPackageImportance(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(packageImportance);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> allowedAssistantAdjustments = getAllowedAssistantAdjustments(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStringList(allowedAssistantAdjustments);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    allowAssistantAdjustment(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    disallowAssistantAdjustment(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zShouldHideSilentStatusIcons = shouldHideSilentStatusIcons(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zShouldHideSilentStatusIcons ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    setHideSilentStatusIcons(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBubblesAllowed(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAreBubblesAllowed = areBubblesAllowed(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAreBubblesAllowed ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    int bubblePreferenceForPackage = getBubblePreferenceForPackage(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(bubblePreferenceForPackage);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    createNotificationChannelGroups(parcel.readString(), parcel.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    createNotificationChannels(parcel.readString(), parcel.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    createNotificationChannelsForPackage(parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice conversations = getConversations(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (conversations != null) {
                        parcel2.writeInt(1);
                        conversations.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice conversationsForPackage = getConversationsForPackage(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (conversationsForPackage != null) {
                        parcel2.writeInt(1);
                        conversationsForPackage.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice notificationChannelGroupsForPackage = getNotificationChannelGroupsForPackage(parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (notificationChannelGroupsForPackage != null) {
                        parcel2.writeInt(1);
                        notificationChannelGroupsForPackage.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    NotificationChannelGroup notificationChannelGroupForPackage = getNotificationChannelGroupForPackage(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (notificationChannelGroupForPackage != null) {
                        parcel2.writeInt(1);
                        notificationChannelGroupForPackage.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    NotificationChannelGroup populatedNotificationChannelGroupForPackage = getPopulatedNotificationChannelGroupForPackage(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (populatedNotificationChannelGroupForPackage != null) {
                        parcel2.writeInt(1);
                        populatedNotificationChannelGroupForPackage.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateNotificationChannelGroupForPackage(parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? NotificationChannelGroup.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateNotificationChannelForPackage(parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? NotificationChannel.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    NotificationChannel notificationChannel = getNotificationChannel(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (notificationChannel != null) {
                        parcel2.writeInt(1);
                        notificationChannel.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    NotificationChannel conversationNotificationChannel = getConversationNotificationChannel(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readString());
                    parcel2.writeNoException();
                    if (conversationNotificationChannel != null) {
                        parcel2.writeInt(1);
                        conversationNotificationChannel.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    createConversationNotificationChannelForPackage(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? NotificationChannel.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    NotificationChannel notificationChannelForPackage = getNotificationChannelForPackage(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (notificationChannelForPackage != null) {
                        parcel2.writeInt(1);
                        notificationChannelForPackage.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteNotificationChannel(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteConversationNotificationChannels(parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice notificationChannels = getNotificationChannels(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (notificationChannels != null) {
                        parcel2.writeInt(1);
                        notificationChannels.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice notificationChannelsForPackage = getNotificationChannelsForPackage(parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (notificationChannelsForPackage != null) {
                        parcel2.writeInt(1);
                        notificationChannelsForPackage.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    int numNotificationChannelsForPackage = getNumNotificationChannelsForPackage(parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(numNotificationChannelsForPackage);
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    int deletedChannelCount = getDeletedChannelCount(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(deletedChannelCount);
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    int blockedChannelCount = getBlockedChannelCount(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(blockedChannelCount);
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteNotificationChannelGroup(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    NotificationChannelGroup notificationChannelGroup = getNotificationChannelGroup(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (notificationChannelGroup != null) {
                        parcel2.writeInt(1);
                        notificationChannelGroup.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice notificationChannelGroups = getNotificationChannelGroups(parcel.readString());
                    parcel2.writeNoException();
                    if (notificationChannelGroups != null) {
                        parcel2.writeInt(1);
                        notificationChannelGroups.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zOnlyHasDefaultChannel = onlyHasDefaultChannel(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zOnlyHasDefaultChannel ? 1 : 0);
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    int blockedAppCount = getBlockedAppCount(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(blockedAppCount);
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAreChannelsBypassingDnd = areChannelsBypassingDnd();
                    parcel2.writeNoException();
                    parcel2.writeInt(zAreChannelsBypassingDnd ? 1 : 0);
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    int appsBypassingDndCount = getAppsBypassingDndCount(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(appsBypassingDndCount);
                    return true;
                case 56:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice notificationChannelsBypassingDnd = getNotificationChannelsBypassingDnd(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (notificationChannelsBypassingDnd != null) {
                        parcel2.writeInt(1);
                        notificationChannelsBypassingDnd.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 57:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPackagePaused = isPackagePaused(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPackagePaused ? 1 : 0);
                    return true;
                case 58:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteNotificationHistoryItem(parcel.readString(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 59:
                    parcel.enforceInterface(DESCRIPTOR);
                    silenceNotificationSound();
                    parcel2.writeNoException();
                    return true;
                case 60:
                    parcel.enforceInterface(DESCRIPTOR);
                    StatusBarNotification[] activeNotifications = getActiveNotifications(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(activeNotifications, 1);
                    return true;
                case 61:
                    parcel.enforceInterface(DESCRIPTOR);
                    StatusBarNotification[] activeNotificationsWithAttribution = getActiveNotificationsWithAttribution(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(activeNotificationsWithAttribution, 1);
                    return true;
                case 62:
                    parcel.enforceInterface(DESCRIPTOR);
                    StatusBarNotification[] historicalNotifications = getHistoricalNotifications(parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(historicalNotifications, 1);
                    return true;
                case 63:
                    parcel.enforceInterface(DESCRIPTOR);
                    StatusBarNotification[] historicalNotificationsWithAttribution = getHistoricalNotificationsWithAttribution(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(historicalNotificationsWithAttribution, 1);
                    return true;
                case 64:
                    parcel.enforceInterface(DESCRIPTOR);
                    NotificationHistory notificationHistory = getNotificationHistory(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (notificationHistory != null) {
                        parcel2.writeInt(1);
                        notificationHistory.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 65:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 66:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 67:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelNotificationFromListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 68:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelNotificationsFromListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                case 69:
                    parcel.enforceInterface(DESCRIPTOR);
                    snoozeNotificationUntilContextFromListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 70:
                    parcel.enforceInterface(DESCRIPTOR);
                    snoozeNotificationUntilFromListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 71:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestBindListener(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 72:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestUnbindListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 73:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestBindProvider(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 74:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestUnbindProvider(IConditionProvider.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 75:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNotificationsShownFromListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                case 76:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice activeNotificationsFromListener = getActiveNotificationsFromListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.createStringArray(), parcel.readInt());
                    parcel2.writeNoException();
                    if (activeNotificationsFromListener != null) {
                        parcel2.writeInt(1);
                        activeNotificationsFromListener.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 77:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice snoozedNotificationsFromListener = getSnoozedNotificationsFromListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    if (snoozedNotificationsFromListener != null) {
                        parcel2.writeInt(1);
                        snoozedNotificationsFromListener.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 78:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearRequestedListenerHints(INotificationListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 79:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestHintsFromListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 80:
                    parcel.enforceInterface(DESCRIPTOR);
                    int hintsFromListener = getHintsFromListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(hintsFromListener);
                    return true;
                case 81:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestInterruptionFilterFromListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 82:
                    parcel.enforceInterface(DESCRIPTOR);
                    int interruptionFilterFromListener = getInterruptionFilterFromListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(interruptionFilterFromListener);
                    return true;
                case 83:
                    parcel.enforceInterface(DESCRIPTOR);
                    setOnNotificationPostedTrimFromListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 84:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInterruptionFilter(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 85:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateNotificationChannelGroupFromPrivilegedListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? NotificationChannelGroup.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 86:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateNotificationChannelFromPrivilegedListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? NotificationChannel.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 87:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice notificationChannelsFromPrivilegedListener = getNotificationChannelsFromPrivilegedListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (notificationChannelsFromPrivilegedListener != null) {
                        parcel2.writeInt(1);
                        notificationChannelsFromPrivilegedListener.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 88:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice notificationChannelGroupsFromPrivilegedListener = getNotificationChannelGroupsFromPrivilegedListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (notificationChannelGroupsFromPrivilegedListener != null) {
                        parcel2.writeInt(1);
                        notificationChannelGroupsFromPrivilegedListener.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 89:
                    parcel.enforceInterface(DESCRIPTOR);
                    applyEnqueuedAdjustmentFromAssistant(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? Adjustment.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 90:
                    parcel.enforceInterface(DESCRIPTOR);
                    applyAdjustmentFromAssistant(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? Adjustment.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 91:
                    parcel.enforceInterface(DESCRIPTOR);
                    applyAdjustmentsFromAssistant(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.createTypedArrayList(Adjustment.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 92:
                    parcel.enforceInterface(DESCRIPTOR);
                    unsnoozeNotificationFromAssistant(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 93:
                    parcel.enforceInterface(DESCRIPTOR);
                    unsnoozeNotificationFromSystemListener(INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 94:
                    parcel.enforceInterface(DESCRIPTOR);
                    ComponentName effectsSuppressor = getEffectsSuppressor();
                    parcel2.writeNoException();
                    if (effectsSuppressor != null) {
                        parcel2.writeInt(1);
                        effectsSuppressor.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 95:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zMatchesCallFilter = matchesCallFilter(parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zMatchesCallFilter ? 1 : 0);
                    return true;
                case 96:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSystemConditionProviderEnabled = isSystemConditionProviderEnabled(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSystemConditionProviderEnabled ? 1 : 0);
                    return true;
                case 97:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsNotificationListenerAccessGranted = isNotificationListenerAccessGranted(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsNotificationListenerAccessGranted ? 1 : 0);
                    return true;
                case 98:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsNotificationListenerAccessGrantedForUser = isNotificationListenerAccessGrantedForUser(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsNotificationListenerAccessGrantedForUser ? 1 : 0);
                    return true;
                case 99:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsNotificationAssistantAccessGranted = isNotificationAssistantAccessGranted(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsNotificationAssistantAccessGranted ? 1 : 0);
                    return true;
                case 100:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNotificationListenerAccessGranted(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 101:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNotificationAssistantAccessGranted(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 102:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNotificationListenerAccessGrantedForUser(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 103:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNotificationAssistantAccessGrantedForUser(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 104:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> enabledNotificationListenerPackages = getEnabledNotificationListenerPackages();
                    parcel2.writeNoException();
                    parcel2.writeStringList(enabledNotificationListenerPackages);
                    return true;
                case 105:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<ComponentName> enabledNotificationListeners = getEnabledNotificationListeners(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(enabledNotificationListeners);
                    return true;
                case 106:
                    parcel.enforceInterface(DESCRIPTOR);
                    ComponentName allowedNotificationAssistantForUser = getAllowedNotificationAssistantForUser(parcel.readInt());
                    parcel2.writeNoException();
                    if (allowedNotificationAssistantForUser != null) {
                        parcel2.writeInt(1);
                        allowedNotificationAssistantForUser.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 107:
                    parcel.enforceInterface(DESCRIPTOR);
                    ComponentName allowedNotificationAssistant = getAllowedNotificationAssistant();
                    parcel2.writeNoException();
                    if (allowedNotificationAssistant != null) {
                        parcel2.writeInt(1);
                        allowedNotificationAssistant.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 108:
                    parcel.enforceInterface(DESCRIPTOR);
                    int zenMode = getZenMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(zenMode);
                    return true;
                case 109:
                    parcel.enforceInterface(DESCRIPTOR);
                    ZenModeConfig zenModeConfig = getZenModeConfig();
                    parcel2.writeNoException();
                    if (zenModeConfig != null) {
                        parcel2.writeInt(1);
                        zenModeConfig.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 110:
                    parcel.enforceInterface(DESCRIPTOR);
                    NotificationManager.Policy consolidatedNotificationPolicy = getConsolidatedNotificationPolicy();
                    parcel2.writeNoException();
                    if (consolidatedNotificationPolicy != null) {
                        parcel2.writeInt(1);
                        consolidatedNotificationPolicy.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 111:
                    parcel.enforceInterface(DESCRIPTOR);
                    setZenMode(parcel.readInt(), parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    return true;
                case 112:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyConditions(parcel.readString(), IConditionProvider.Stub.asInterface(parcel.readStrongBinder()), (Condition[]) parcel.createTypedArray(Condition.CREATOR));
                    return true;
                case 113:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsNotificationPolicyAccessGranted = isNotificationPolicyAccessGranted(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsNotificationPolicyAccessGranted ? 1 : 0);
                    return true;
                case 114:
                    parcel.enforceInterface(DESCRIPTOR);
                    NotificationManager.Policy notificationPolicy = getNotificationPolicy(parcel.readString());
                    parcel2.writeNoException();
                    if (notificationPolicy != null) {
                        parcel2.writeInt(1);
                        notificationPolicy.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 115:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNotificationPolicy(parcel.readString(), parcel.readInt() != 0 ? NotificationManager.Policy.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 116:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsNotificationPolicyAccessGrantedForPackage = isNotificationPolicyAccessGrantedForPackage(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsNotificationPolicyAccessGrantedForPackage ? 1 : 0);
                    return true;
                case 117:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNotificationPolicyAccessGranted(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 118:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNotificationPolicyAccessGrantedForUser(parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 119:
                    parcel.enforceInterface(DESCRIPTOR);
                    AutomaticZenRule automaticZenRule = getAutomaticZenRule(parcel.readString());
                    parcel2.writeNoException();
                    if (automaticZenRule != null) {
                        parcel2.writeInt(1);
                        automaticZenRule.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 120:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<ZenModeConfig.ZenRule> zenRules = getZenRules();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(zenRules);
                    return true;
                case 121:
                    parcel.enforceInterface(DESCRIPTOR);
                    String strAddAutomaticZenRule = addAutomaticZenRule(parcel.readInt() != 0 ? AutomaticZenRule.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeString(strAddAutomaticZenRule);
                    return true;
                case 122:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUpdateAutomaticZenRule = updateAutomaticZenRule(parcel.readString(), parcel.readInt() != 0 ? AutomaticZenRule.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zUpdateAutomaticZenRule ? 1 : 0);
                    return true;
                case 123:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRemoveAutomaticZenRule = removeAutomaticZenRule(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveAutomaticZenRule ? 1 : 0);
                    return true;
                case 124:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRemoveAutomaticZenRules = removeAutomaticZenRules(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveAutomaticZenRules ? 1 : 0);
                    return true;
                case 125:
                    parcel.enforceInterface(DESCRIPTOR);
                    int ruleInstanceCount = getRuleInstanceCount(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(ruleInstanceCount);
                    return true;
                case 126:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAutomaticZenRuleState(parcel.readString(), parcel.readInt() != 0 ? Condition.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 127:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] backupPayload = getBackupPayload(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(backupPayload);
                    return true;
                case 128:
                    parcel.enforceInterface(DESCRIPTOR);
                    applyRestore(parcel.createByteArray(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 129:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice appActiveNotifications = getAppActiveNotifications(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (appActiveNotifications != null) {
                        parcel2.writeInt(1);
                        appActiveNotifications.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 130:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNotificationDelegate(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 131:
                    parcel.enforceInterface(DESCRIPTOR);
                    String notificationDelegate = getNotificationDelegate(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(notificationDelegate);
                    return true;
                case 132:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCanNotifyAsPackage = canNotifyAsPackage(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCanNotifyAsPackage ? 1 : 0);
                    return true;
                case 133:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPrivateNotificationsAllowed(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 134:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean privateNotificationsAllowed = getPrivateNotificationsAllowed();
                    parcel2.writeNoException();
                    parcel2.writeInt(privateNotificationsAllowed ? 1 : 0);
                    return true;
                case 135:
                    parcel.enforceInterface(DESCRIPTOR);
                    long jPullStats = pullStats(parcel.readLong(), parcel.readInt(), parcel.readInt() != 0, new ArrayList());
                    parcel2.writeNoException();
                    parcel2.writeLong(jPullStats);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements INotificationManager {
            public static INotificationManager sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.app.INotificationManager
            public void cancelAllNotifications(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelAllNotifications(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void clearData(String str, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearData(str, i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void enqueueTextToast(String str, IBinder iBinder, CharSequence charSequence, int i, int i2, ITransientNotificationCallback iTransientNotificationCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (charSequence != null) {
                        parcelObtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iTransientNotificationCallback != null ? iTransientNotificationCallback.asBinder() : null);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enqueueTextToast(str, iBinder, charSequence, i, i2, iTransientNotificationCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void enqueueToast(String str, IBinder iBinder, ITransientNotification iTransientNotification, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeStrongBinder(iTransientNotification != null ? iTransientNotification.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enqueueToast(str, iBinder, iTransientNotification, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void cancelToast(String str, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelToast(str, iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void finishToken(String str, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finishToken(str, iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void enqueueNotificationWithTag(String str, String str2, String str3, int i, Notification notification, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i);
                    if (notification != null) {
                        parcelObtain.writeInt(1);
                        notification.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enqueueNotificationWithTag(str, str2, str3, i, notification, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void cancelNotificationWithTag(String str, String str2, String str3, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelNotificationWithTag(str, str2, str3, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setShowBadge(String str, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setShowBadge(str, i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean canShowBadge(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canShowBadge(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean hasSentValidMsg(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasSentValidMsg(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean isInInvalidMsgState(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInInvalidMsgState(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean hasUserDemotedInvalidMsgApp(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasUserDemotedInvalidMsgApp(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setInvalidMsgAppDemoted(String str, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInvalidMsgAppDemoted(str, i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationsEnabledForPackage(String str, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNotificationsEnabledForPackage(str, i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationsEnabledWithImportanceLockForPackage(String str, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNotificationsEnabledWithImportanceLockForPackage(str, i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean areNotificationsEnabledForPackage(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().areNotificationsEnabledForPackage(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean areNotificationsEnabled(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().areNotificationsEnabled(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getPackageImportance(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPackageImportance(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public List<String> getAllowedAssistantAdjustments(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllowedAssistantAdjustments(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void allowAssistantAdjustment(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().allowAssistantAdjustment(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void disallowAssistantAdjustment(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disallowAssistantAdjustment(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean shouldHideSilentStatusIcons(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldHideSilentStatusIcons(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setHideSilentStatusIcons(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setHideSilentStatusIcons(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setBubblesAllowed(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBubblesAllowed(str, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean areBubblesAllowed(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().areBubblesAllowed(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getBubblePreferenceForPackage(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBubblePreferenceForPackage(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void createNotificationChannelGroups(String str, ParceledListSlice parceledListSlice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (parceledListSlice != null) {
                        parcelObtain.writeInt(1);
                        parceledListSlice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().createNotificationChannelGroups(str, parceledListSlice);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void createNotificationChannels(String str, ParceledListSlice parceledListSlice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (parceledListSlice != null) {
                        parcelObtain.writeInt(1);
                        parceledListSlice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().createNotificationChannels(str, parceledListSlice);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void createNotificationChannelsForPackage(String str, int i, ParceledListSlice parceledListSlice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (parceledListSlice != null) {
                        parcelObtain.writeInt(1);
                        parceledListSlice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().createNotificationChannelsForPackage(str, i, parceledListSlice);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getConversations(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConversations(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getConversationsForPackage(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConversationsForPackage(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getNotificationChannelGroupsForPackage(String str, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannelGroupsForPackage(str, i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationChannelGroup getNotificationChannelGroupForPackage(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannelGroupForPackage(str, str2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NotificationChannelGroup.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationChannelGroup getPopulatedNotificationChannelGroupForPackage(String str, int i, String str2, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPopulatedNotificationChannelGroupForPackage(str, i, str2, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NotificationChannelGroup.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void updateNotificationChannelGroupForPackage(String str, int i, NotificationChannelGroup notificationChannelGroup) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (notificationChannelGroup != null) {
                        parcelObtain.writeInt(1);
                        notificationChannelGroup.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateNotificationChannelGroupForPackage(str, i, notificationChannelGroup);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void updateNotificationChannelForPackage(String str, int i, NotificationChannel notificationChannel) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (notificationChannel != null) {
                        parcelObtain.writeInt(1);
                        notificationChannel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateNotificationChannelForPackage(str, i, notificationChannel);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationChannel getNotificationChannel(String str, int i, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannel(str, i, str2, str3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NotificationChannel.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationChannel getConversationNotificationChannel(String str, int i, String str2, String str3, boolean z, String str4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str4);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConversationNotificationChannel(str, i, str2, str3, z, str4);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NotificationChannel.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void createConversationNotificationChannelForPackage(String str, int i, String str2, NotificationChannel notificationChannel, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    if (notificationChannel != null) {
                        parcelObtain.writeInt(1);
                        notificationChannel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().createConversationNotificationChannelForPackage(str, i, str2, notificationChannel, str3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationChannel getNotificationChannelForPackage(String str, int i, String str2, String str3, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannelForPackage(str, i, str2, str3, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NotificationChannel.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void deleteNotificationChannel(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteNotificationChannel(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void deleteConversationNotificationChannels(String str, int i, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteConversationNotificationChannels(str, i, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getNotificationChannels(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannels(str, str2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getNotificationChannelsForPackage(String str, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannelsForPackage(str, i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getNumNotificationChannelsForPackage(String str, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNumNotificationChannelsForPackage(str, i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getDeletedChannelCount(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeletedChannelCount(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getBlockedChannelCount(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(48, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBlockedChannelCount(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void deleteNotificationChannelGroup(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(49, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteNotificationChannelGroup(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationChannelGroup getNotificationChannelGroup(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(50, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannelGroup(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NotificationChannelGroup.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getNotificationChannelGroups(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(51, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannelGroups(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean onlyHasDefaultChannel(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(52, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onlyHasDefaultChannel(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getBlockedAppCount(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(53, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBlockedAppCount(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean areChannelsBypassingDnd() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(54, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().areChannelsBypassingDnd();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getAppsBypassingDndCount(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(55, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppsBypassingDndCount(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getNotificationChannelsBypassingDnd(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(56, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannelsBypassingDnd(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean isPackagePaused(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(57, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPackagePaused(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void deleteNotificationHistoryItem(String str, int i, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(58, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteNotificationHistoryItem(str, i, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void silenceNotificationSound() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(59, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().silenceNotificationSound();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public StatusBarNotification[] getActiveNotifications(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(60, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveNotifications(str);
                    }
                    parcelObtain2.readException();
                    return (StatusBarNotification[]) parcelObtain2.createTypedArray(StatusBarNotification.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public StatusBarNotification[] getActiveNotificationsWithAttribution(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(61, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveNotificationsWithAttribution(str, str2);
                    }
                    parcelObtain2.readException();
                    return (StatusBarNotification[]) parcelObtain2.createTypedArray(StatusBarNotification.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public StatusBarNotification[] getHistoricalNotifications(String str, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(62, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHistoricalNotifications(str, i, z);
                    }
                    parcelObtain2.readException();
                    return (StatusBarNotification[]) parcelObtain2.createTypedArray(StatusBarNotification.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public StatusBarNotification[] getHistoricalNotificationsWithAttribution(String str, String str2, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(63, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHistoricalNotificationsWithAttribution(str, str2, i, z);
                    }
                    parcelObtain2.readException();
                    return (StatusBarNotification[]) parcelObtain2.createTypedArray(StatusBarNotification.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationHistory getNotificationHistory(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(64, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationHistory(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NotificationHistory.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void registerListener(INotificationListener iNotificationListener, ComponentName componentName, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(65, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(iNotificationListener, componentName, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void unregisterListener(INotificationListener iNotificationListener, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(66, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(iNotificationListener, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void cancelNotificationFromListener(INotificationListener iNotificationListener, String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(67, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelNotificationFromListener(iNotificationListener, str, str2, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void cancelNotificationsFromListener(INotificationListener iNotificationListener, String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(68, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelNotificationsFromListener(iNotificationListener, strArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void snoozeNotificationUntilContextFromListener(INotificationListener iNotificationListener, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(69, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().snoozeNotificationUntilContextFromListener(iNotificationListener, str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void snoozeNotificationUntilFromListener(INotificationListener iNotificationListener, String str, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(70, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().snoozeNotificationUntilFromListener(iNotificationListener, str, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void requestBindListener(ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(71, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestBindListener(componentName);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void requestUnbindListener(INotificationListener iNotificationListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    if (!this.mRemote.transact(72, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestUnbindListener(iNotificationListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void requestBindProvider(ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(73, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestBindProvider(componentName);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void requestUnbindProvider(IConditionProvider iConditionProvider) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iConditionProvider != null ? iConditionProvider.asBinder() : null);
                    if (!this.mRemote.transact(74, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestUnbindProvider(iConditionProvider);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationsShownFromListener(INotificationListener iNotificationListener, String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(75, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNotificationsShownFromListener(iNotificationListener, strArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getActiveNotificationsFromListener(INotificationListener iNotificationListener, String[] strArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(76, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveNotificationsFromListener(iNotificationListener, strArr, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getSnoozedNotificationsFromListener(INotificationListener iNotificationListener, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(77, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSnoozedNotificationsFromListener(iNotificationListener, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void clearRequestedListenerHints(INotificationListener iNotificationListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    if (!this.mRemote.transact(78, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearRequestedListenerHints(iNotificationListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void requestHintsFromListener(INotificationListener iNotificationListener, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(79, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestHintsFromListener(iNotificationListener, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getHintsFromListener(INotificationListener iNotificationListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    if (!this.mRemote.transact(80, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHintsFromListener(iNotificationListener);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void requestInterruptionFilterFromListener(INotificationListener iNotificationListener, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(81, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestInterruptionFilterFromListener(iNotificationListener, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getInterruptionFilterFromListener(INotificationListener iNotificationListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    if (!this.mRemote.transact(82, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInterruptionFilterFromListener(iNotificationListener);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setOnNotificationPostedTrimFromListener(INotificationListener iNotificationListener, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(83, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setOnNotificationPostedTrimFromListener(iNotificationListener, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setInterruptionFilter(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(84, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInterruptionFilter(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void updateNotificationChannelGroupFromPrivilegedListener(INotificationListener iNotificationListener, String str, UserHandle userHandle, NotificationChannelGroup notificationChannelGroup) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (notificationChannelGroup != null) {
                        parcelObtain.writeInt(1);
                        notificationChannelGroup.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(85, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateNotificationChannelGroupFromPrivilegedListener(iNotificationListener, str, userHandle, notificationChannelGroup);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void updateNotificationChannelFromPrivilegedListener(INotificationListener iNotificationListener, String str, UserHandle userHandle, NotificationChannel notificationChannel) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (notificationChannel != null) {
                        parcelObtain.writeInt(1);
                        notificationChannel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(86, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateNotificationChannelFromPrivilegedListener(iNotificationListener, str, userHandle, notificationChannel);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getNotificationChannelsFromPrivilegedListener(INotificationListener iNotificationListener, String str, UserHandle userHandle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(87, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannelsFromPrivilegedListener(iNotificationListener, str, userHandle);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getNotificationChannelGroupsFromPrivilegedListener(INotificationListener iNotificationListener, String str, UserHandle userHandle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(88, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannelGroupsFromPrivilegedListener(iNotificationListener, str, userHandle);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void applyEnqueuedAdjustmentFromAssistant(INotificationListener iNotificationListener, Adjustment adjustment) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    if (adjustment != null) {
                        parcelObtain.writeInt(1);
                        adjustment.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(89, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyEnqueuedAdjustmentFromAssistant(iNotificationListener, adjustment);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void applyAdjustmentFromAssistant(INotificationListener iNotificationListener, Adjustment adjustment) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    if (adjustment != null) {
                        parcelObtain.writeInt(1);
                        adjustment.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(90, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyAdjustmentFromAssistant(iNotificationListener, adjustment);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void applyAdjustmentsFromAssistant(INotificationListener iNotificationListener, List<Adjustment> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(91, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyAdjustmentsFromAssistant(iNotificationListener, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void unsnoozeNotificationFromAssistant(INotificationListener iNotificationListener, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(92, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unsnoozeNotificationFromAssistant(iNotificationListener, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void unsnoozeNotificationFromSystemListener(INotificationListener iNotificationListener, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotificationListener != null ? iNotificationListener.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(93, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unsnoozeNotificationFromSystemListener(iNotificationListener, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ComponentName getEffectsSuppressor() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(94, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEffectsSuppressor();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean matchesCallFilter(Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(95, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().matchesCallFilter(bundle);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean isSystemConditionProviderEnabled(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(96, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSystemConditionProviderEnabled(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean isNotificationListenerAccessGranted(ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(97, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNotificationListenerAccessGranted(componentName);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean isNotificationListenerAccessGrantedForUser(ComponentName componentName, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(98, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNotificationListenerAccessGrantedForUser(componentName, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean isNotificationAssistantAccessGranted(ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(99, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNotificationAssistantAccessGranted(componentName);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationListenerAccessGranted(ComponentName componentName, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(100, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNotificationListenerAccessGranted(componentName, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationAssistantAccessGranted(ComponentName componentName, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(101, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNotificationAssistantAccessGranted(componentName, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationListenerAccessGrantedForUser(ComponentName componentName, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i2 = 1;
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!z) {
                        i2 = 0;
                    }
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(102, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNotificationListenerAccessGrantedForUser(componentName, i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationAssistantAccessGrantedForUser(ComponentName componentName, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i2 = 1;
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!z) {
                        i2 = 0;
                    }
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(103, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNotificationAssistantAccessGrantedForUser(componentName, i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public List<String> getEnabledNotificationListenerPackages() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(104, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEnabledNotificationListenerPackages();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public List<ComponentName> getEnabledNotificationListeners(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(105, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEnabledNotificationListeners(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(ComponentName.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ComponentName getAllowedNotificationAssistantForUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(106, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllowedNotificationAssistantForUser(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ComponentName getAllowedNotificationAssistant() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(107, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllowedNotificationAssistant();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getZenMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(108, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getZenMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ZenModeConfig getZenModeConfig() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(109, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getZenModeConfig();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ZenModeConfig.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationManager.Policy getConsolidatedNotificationPolicy() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(110, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConsolidatedNotificationPolicy();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NotificationManager.Policy.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setZenMode(int i, Uri uri, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(111, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setZenMode(i, uri, str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void notifyConditions(String str, IConditionProvider iConditionProvider, Condition[] conditionArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iConditionProvider != null ? iConditionProvider.asBinder() : null);
                    parcelObtain.writeTypedArray(conditionArr, 0);
                    if (this.mRemote.transact(112, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyConditions(str, iConditionProvider, conditionArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean isNotificationPolicyAccessGranted(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(113, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNotificationPolicyAccessGranted(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationManager.Policy getNotificationPolicy(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(114, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationPolicy(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NotificationManager.Policy.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationPolicy(String str, NotificationManager.Policy policy) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (policy != null) {
                        parcelObtain.writeInt(1);
                        policy.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(115, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNotificationPolicy(str, policy);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean isNotificationPolicyAccessGrantedForPackage(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(116, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNotificationPolicyAccessGrantedForPackage(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationPolicyAccessGranted(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(117, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNotificationPolicyAccessGranted(str, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationPolicyAccessGrantedForUser(String str, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(118, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNotificationPolicyAccessGrantedForUser(str, i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public AutomaticZenRule getAutomaticZenRule(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(119, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAutomaticZenRule(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AutomaticZenRule.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public List<ZenModeConfig.ZenRule> getZenRules() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(120, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getZenRules();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(ZenModeConfig.ZenRule.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public String addAutomaticZenRule(AutomaticZenRule automaticZenRule) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (automaticZenRule != null) {
                        parcelObtain.writeInt(1);
                        automaticZenRule.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(121, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addAutomaticZenRule(automaticZenRule);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean updateAutomaticZenRule(String str, AutomaticZenRule automaticZenRule) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (automaticZenRule != null) {
                        parcelObtain.writeInt(1);
                        automaticZenRule.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(122, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateAutomaticZenRule(str, automaticZenRule);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean removeAutomaticZenRule(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(123, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeAutomaticZenRule(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean removeAutomaticZenRules(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(124, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeAutomaticZenRules(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getRuleInstanceCount(ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(125, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRuleInstanceCount(componentName);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setAutomaticZenRuleState(String str, Condition condition) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (condition != null) {
                        parcelObtain.writeInt(1);
                        condition.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(126, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAutomaticZenRuleState(str, condition);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public byte[] getBackupPayload(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(127, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBackupPayload(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void applyRestore(byte[] bArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(128, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyRestore(bArr, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getAppActiveNotifications(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(129, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppActiveNotifications(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationDelegate(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(130, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNotificationDelegate(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public String getNotificationDelegate(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(131, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationDelegate(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean canNotifyAsPackage(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(132, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canNotifyAsPackage(str, str2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setPrivateNotificationsAllowed(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(133, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPrivateNotificationsAllowed(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean getPrivateNotificationsAllowed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(134, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPrivateNotificationsAllowed();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public long pullStats(long j, int i, boolean z, List<ParcelFileDescriptor> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(135, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().pullStats(j, i, z, list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INotificationManager iNotificationManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iNotificationManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iNotificationManager;
            return true;
        }

        public static INotificationManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
