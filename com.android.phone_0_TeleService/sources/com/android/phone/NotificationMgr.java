package com.android.phone;

import android.app.BroadcastOptions;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.os.UserHandle;
import android.os.UserManager;
import android.preference.PreferenceManager;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.PhoneNumberUtils;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;
import com.android.internal.telephony.TelephonyCapabilities;
import com.android.phone.settings.VoicemailSettingsActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class NotificationMgr {
    private static final boolean DBG = false;
    private static final String LOG_TAG = "NotificationMgr";
    private static NotificationMgr sInstance;
    private PhoneGlobals mApp;
    private Context mContext;
    private SubscriptionManager mSubscriptionManager;
    private TelephonyManager mTelephonyManager;
    private UserManager mUserManager;
    private SparseArray<Boolean> mSelectedUnavailableNotify = new SparseArray<>();
    private Set<Integer> mLimitedSimFunctionNotify = new HashSet();
    private ArrayMap<Integer, Boolean> mMwiVisible = new ArrayMap<>();
    private SparseArray<Integer> mPreviousServiceState = new SparseArray<>();
    private SparseArray<Long> mOOSTimestamp = new SparseArray<>();
    private SparseArray<Integer> mPendingEventCounter = new SparseArray<>();
    private SparseArray<String> mSelectedNetworkOperatorName = new SparseArray<>();
    private final Handler mHandler = new Handler() { // from class: com.android.phone.NotificationMgr.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            int iIntValue = ((Integer) message.obj).intValue();
            TelephonyManager telephonyManagerCreateForSubscriptionId = NotificationMgr.this.mTelephonyManager.createForSubscriptionId(iIntValue);
            if (telephonyManagerCreateForSubscriptionId.getServiceState() != null) {
                NotificationMgr.this.shouldShowNotification(telephonyManagerCreateForSubscriptionId.getServiceState().getState(), iIntValue);
            }
        }
    };

    private NotificationMgr(PhoneGlobals phoneGlobals) {
        this.mApp = phoneGlobals;
        this.mContext = phoneGlobals;
        this.mUserManager = (UserManager) phoneGlobals.getSystemService("user");
        this.mSubscriptionManager = SubscriptionManager.from(this.mContext);
        this.mTelephonyManager = (TelephonyManager) phoneGlobals.getSystemService("phone");
    }

    static NotificationMgr init(PhoneGlobals phoneGlobals) {
        NotificationMgr notificationMgr;
        synchronized (NotificationMgr.class) {
            if (sInstance == null) {
                sInstance = new NotificationMgr(phoneGlobals);
            } else {
                Log.wtf(LOG_TAG, "init() called multiple times!  sInstance = " + sInstance);
            }
            notificationMgr = sInstance;
        }
        return notificationMgr;
    }

    void refreshMwi(int i) {
        if (i == -1 && this.mMwiVisible.keySet().size() == 1) {
            Iterator<Integer> it = this.mMwiVisible.keySet().iterator();
            if (!it.hasNext()) {
                return;
            } else {
                i = it.next().intValue();
            }
        }
        if (this.mMwiVisible.containsKey(Integer.valueOf(i)) && this.mMwiVisible.get(Integer.valueOf(i)).booleanValue()) {
            this.mApp.notifier.updatePhoneStateListeners(true);
        }
    }

    void updateMwi(int i, boolean z) {
        updateMwi(i, z, false);
    }

    void updateMwi(int i, boolean z, boolean z2) {
        Integer num;
        String string;
        Intent intent;
        PendingIntent pendingIntent;
        String str;
        if (!PhoneGlobals.sVoiceCapable) {
            Log.w(LOG_TAG, "Called updateMwi() on non-voice-capable device! Ignoring...");
            return;
        }
        Phone phone = PhoneGlobals.getPhone(i);
        Log.i(LOG_TAG, "updateMwi(): subId " + i + " update to " + z);
        this.mMwiVisible.put(Integer.valueOf(i), Boolean.valueOf(z));
        if (!z) {
            for (UserHandle userHandle : getUsersExcludeDying()) {
                if (!hasUserRestriction("no_outgoing_calls", userHandle) && !this.mUserManager.isManagedProfile(userHandle.getIdentifier()) && !maybeSendVoicemailNotificationUsingDefaultDialer(phone, 0, null, null, false, userHandle, z2)) {
                    cancelAsUser(Integer.toString(i), 3, userHandle);
                }
            }
            return;
        }
        if (phone == null) {
            Log.w(LOG_TAG, "Found null phone for: " + i);
            return;
        }
        SubscriptionInfo activeSubscriptionInfo = this.mSubscriptionManager.getActiveSubscriptionInfo(i);
        if (activeSubscriptionInfo == null) {
            Log.w(LOG_TAG, "Found null subscription info for: " + i);
            return;
        }
        int i2 = android.R.drawable.stat_notify_voicemail;
        if (this.mTelephonyManager.getPhoneCount() > 1) {
            i2 = phone.getPhoneId() == 0 ? R.drawable.stat_notify_voicemail_sub1 : R.drawable.stat_notify_voicemail_sub2;
        }
        String string2 = this.mContext.getString(R.string.notification_voicemail_title);
        String voiceMailNumber = phone.getVoiceMailNumber();
        if (DBG) {
            log("- got vm number: '" + voiceMailNumber + "'");
        }
        if (voiceMailNumber == null && !phone.getIccRecordsLoaded()) {
            if (DBG) {
                log("- Null vm number: SIM records not loaded (yet)...");
                return;
            }
            return;
        }
        if (TelephonyCapabilities.supportsVoiceMessageCount(phone)) {
            Integer numValueOf = Integer.valueOf(phone.getVoiceMessageCount());
            num = numValueOf;
            string2 = String.format(this.mContext.getString(R.string.notification_voicemail_title_count), numValueOf);
        } else {
            num = null;
        }
        PhoneAccountHandle phoneAccountHandleMakePstnPhoneAccountHandle = PhoneUtils.makePstnPhoneAccountHandle(phone);
        boolean zIsEmpty = TextUtils.isEmpty(voiceMailNumber);
        if (zIsEmpty) {
            this.mContext.getString(R.string.notification_voicemail_no_vm_number);
            string = this.mContext.getString(R.string.notification_voicemail_no_vm_number);
            intent = new Intent("com.android.phone.CallFeaturesSetting.ADD_VOICEMAIL");
            intent.putExtra("com.android.phone.settings.SubscriptionInfoHelper.SubscriptionId", i);
            intent.setClass(this.mContext, VoicemailSettingsActivity.class);
        } else {
            if (this.mTelephonyManager.getPhoneCount() > 1) {
                string = activeSubscriptionInfo.getDisplayName().toString();
            } else {
                string = String.format(this.mContext.getString(R.string.notification_voicemail_text_format), PhoneNumberUtils.formatNumber(voiceMailNumber));
            }
            Intent intent2 = new Intent("android.intent.action.CALL", Uri.fromParts("voicemail", "", null));
            intent2.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandleMakePstnPhoneAccountHandle);
            intent = intent2;
        }
        PendingIntent activity = PendingIntent.getActivity(this.mContext, i, intent, 67108864);
        Resources resources = this.mContext.getResources();
        PersistableBundle carrierConfigForSubId = PhoneGlobals.getInstance().getCarrierConfigForSubId(i);
        Notification.Builder builder = new Notification.Builder(this.mContext);
        String str2 = "no_outgoing_calls";
        builder.setSmallIcon(i2).setWhen(System.currentTimeMillis()).setColor(activeSubscriptionInfo.getIconTint()).setContentTitle(string2).setContentText(string).setContentIntent(activity).setColor(resources.getColor(R.color.dialer_theme_color)).setOngoing(carrierConfigForSubId.getBoolean("voicemail_notification_persistent_bool")).setChannelId("voiceMail").setOnlyAlertOnce(z2);
        Notification notificationBuild = builder.build();
        for (UserHandle userHandle2 : getUsersExcludeDying()) {
            String str3 = str2;
            if (hasUserRestriction(str3, userHandle2) || this.mUserManager.isManagedProfile(userHandle2.getIdentifier())) {
                pendingIntent = activity;
                str = str3;
            } else {
                PendingIntent pendingIntent2 = activity;
                pendingIntent = activity;
                str = str3;
                if (!maybeSendVoicemailNotificationUsingDefaultDialer(phone, num, voiceMailNumber, pendingIntent2, zIsEmpty, userHandle2, z2)) {
                    notifyAsUser(Integer.toString(i), 3, notificationBuild, userHandle2);
                }
            }
            str2 = str;
            activity = pendingIntent;
        }
    }

    private List<UserHandle> getUsersExcludeDying() {
        long[] serialNumbersOfUsers = this.mUserManager.getSerialNumbersOfUsers(true);
        ArrayList arrayList = new ArrayList(serialNumbersOfUsers.length);
        for (long j : serialNumbersOfUsers) {
            arrayList.add(this.mUserManager.getUserForSerialNumber(j));
        }
        return arrayList;
    }

    private boolean hasUserRestriction(String str, UserHandle userHandle) {
        List userRestrictionSources = this.mUserManager.getUserRestrictionSources(str, userHandle);
        return (userRestrictionSources == null || userRestrictionSources.isEmpty()) ? false : true;
    }

    private boolean maybeSendVoicemailNotificationUsingDefaultDialer(Phone phone, Integer num, String str, PendingIntent pendingIntent, boolean z, UserHandle userHandle, boolean z2) {
        if (!shouldManageNotificationThroughDefaultDialer(userHandle)) {
            return false;
        }
        Intent showVoicemailIntentForDefaultDialer = getShowVoicemailIntentForDefaultDialer(userHandle);
        showVoicemailIntentForDefaultDialer.setFlags(268435456);
        showVoicemailIntentForDefaultDialer.setAction("android.telephony.action.SHOW_VOICEMAIL_NOTIFICATION");
        showVoicemailIntentForDefaultDialer.putExtra("android.telephony.extra.PHONE_ACCOUNT_HANDLE", PhoneUtils.makePstnPhoneAccountHandle(phone));
        showVoicemailIntentForDefaultDialer.putExtra("android.telephony.extra.IS_REFRESH", z2);
        if (num != null) {
            showVoicemailIntentForDefaultDialer.putExtra("android.telephony.extra.NOTIFICATION_COUNT", num);
        }
        if (num == null || num.intValue() > 0) {
            if (!TextUtils.isEmpty(str)) {
                showVoicemailIntentForDefaultDialer.putExtra("android.telephony.extra.VOICEMAIL_NUMBER", str);
            }
            if (pendingIntent != null) {
                showVoicemailIntentForDefaultDialer.putExtra(z ? "android.telephony.extra.LAUNCH_VOICEMAIL_SETTINGS_INTENT" : "android.telephony.extra.CALL_VOICEMAIL_INTENT", pendingIntent);
            }
        }
        BroadcastOptions broadcastOptionsMakeBasic = BroadcastOptions.makeBasic();
        broadcastOptionsMakeBasic.setTemporaryAppWhitelistDuration(10000L);
        this.mContext.sendBroadcastAsUser(showVoicemailIntentForDefaultDialer, userHandle, "android.permission.READ_PHONE_STATE", broadcastOptionsMakeBasic.toBundle());
        return true;
    }

    private Intent getShowVoicemailIntentForDefaultDialer(UserHandle userHandle) {
        return new Intent("android.telephony.action.SHOW_VOICEMAIL_NOTIFICATION").setPackage(((TelecomManager) this.mContext.getSystemService(TelecomManager.class)).getDefaultDialerPackage(userHandle));
    }

    private boolean shouldManageNotificationThroughDefaultDialer(UserHandle userHandle) {
        Intent showVoicemailIntentForDefaultDialer = getShowVoicemailIntentForDefaultDialer(userHandle);
        return showVoicemailIntentForDefaultDialer != null && this.mContext.getPackageManager().queryBroadcastReceivers(showVoicemailIntentForDefaultDialer, 0).size() > 0;
    }

    void updateCfi(int i, boolean z) {
        updateCfi(i, z, false);
    }

    void updateCfi(int i, boolean z, boolean z2) {
        String string;
        StringBuilder sb = new StringBuilder();
        sb.append("updateCfi: subId= ");
        sb.append(i);
        sb.append(", visible=");
        sb.append(z ? "Y" : "N");
        logi(sb.toString());
        if (z) {
            SubscriptionInfo activeSubscriptionInfo = this.mSubscriptionManager.getActiveSubscriptionInfo(i);
            if (activeSubscriptionInfo == null) {
                Log.w(LOG_TAG, "Found null subscription info for: " + i);
                return;
            }
            int i2 = R.drawable.stat_sys_phone_call_forward;
            if (this.mTelephonyManager.getPhoneCount() > 1) {
                i2 = SubscriptionManager.getSlotIndex(i) == 0 ? R.drawable.stat_sys_phone_call_forward_sub1 : R.drawable.stat_sys_phone_call_forward_sub2;
                string = activeSubscriptionInfo.getDisplayName().toString();
            } else {
                string = this.mContext.getString(R.string.labelCF);
            }
            Notification.Builder onlyAlertOnce = new Notification.Builder(this.mContext).setSmallIcon(i2).setColor(activeSubscriptionInfo.getIconTint()).setContentTitle(string).setContentText(this.mContext.getString(R.string.sum_cfu_enabled_indicator)).setShowWhen(false).setOngoing(true).setChannelId("callForwardNew").setOnlyAlertOnce(z2);
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addFlags(335544320);
            intent.setClassName("com.android.phone", "com.android.phone.CallFeaturesSetting");
            SubscriptionInfoHelper.addExtrasToIntent(intent, this.mSubscriptionManager.getActiveSubscriptionInfo(i));
            onlyAlertOnce.setContentIntent(PendingIntent.getActivity(this.mContext, i, intent, 67108864));
            notifyAsUser(Integer.toString(i), 4, onlyAlertOnce.build(), UserHandle.ALL);
            return;
        }
        for (UserHandle userHandle : getUsersExcludeDying()) {
            if (!this.mUserManager.isManagedProfile(userHandle.getIdentifier())) {
                cancelAsUser(Integer.toString(i), 4, userHandle);
            }
        }
    }

    void showDataRoamingNotification(int i, boolean z) {
        if (DBG) {
            StringBuilder sb = new StringBuilder();
            sb.append("showDataRoamingNotification() roaming ");
            sb.append(z ? "on" : "off");
            sb.append(" on subId ");
            sb.append(i);
            log(sb.toString());
        }
        Intent intent = new Intent("android.settings.DATA_ROAMING_SETTINGS");
        intent.putExtra("android.provider.extra.SUB_ID", i);
        PendingIntent activity = PendingIntent.getActivity(this.mContext, i, intent, 67108864);
        CharSequence text = this.mContext.getText(z ? R.string.roaming_on_notification_title : R.string.roaming_notification_title);
        CharSequence text2 = this.mContext.getText(z ? R.string.roaming_enabled_message : R.string.roaming_reenable_message);
        notifyAsUser(null, 5, new Notification.BigTextStyle(new Notification.Builder(this.mContext).setSmallIcon(android.R.drawable.stat_sys_warning).setContentTitle(text).setColor(this.mContext.getResources().getColor(R.color.dialer_theme_color)).setContentText(text2).setChannelId("mobileDataAlertNew").setContentIntent(activity)).bigText(text2).build(), UserHandle.ALL);
    }

    private void notifyAsUser(String str, int i, Notification notification, UserHandle userHandle) {
        try {
            ((NotificationManager) this.mContext.createPackageContextAsUser(this.mContext.getPackageName(), 0, userHandle).getSystemService("notification")).notify(str, i, notification);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(LOG_TAG, "unable to notify for user " + userHandle);
            e.printStackTrace();
        }
    }

    private void cancelAsUser(String str, int i, UserHandle userHandle) {
        try {
            ((NotificationManager) this.mContext.createPackageContextAsUser(this.mContext.getPackageName(), 0, userHandle).getSystemService("notification")).cancel(str, i);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(LOG_TAG, "unable to cancel for user " + userHandle);
            e.printStackTrace();
        }
    }

    void hideDataRoamingNotification() {
        if (DBG) {
            log("hideDataRoamingNotification()...");
        }
        cancelAsUser(null, 5, UserHandle.ALL);
    }

    public void showLimitedSimFunctionWarningNotification(int i, String str) {
        String str2;
        if (DBG) {
            log("showLimitedSimFunctionWarningNotification carrier: " + str + " subId: " + i);
        }
        if (this.mLimitedSimFunctionNotify.contains(Integer.valueOf(i))) {
            if (DBG) {
                log("showLimitedSimFunctionWarningNotification, not display again if already displayed");
                return;
            }
            return;
        }
        PendingIntent activity = PendingIntent.getActivity(this.mContext, 0, new Intent("android.settings.MOBILE_NETWORK_LIST"), 67108864);
        String line1Number = null;
        for (SubscriptionInfo subscriptionInfo : ((SubscriptionManager) this.mContext.getSystemService("telephony_subscription_service")).getActiveSubscriptionInfoList(false)) {
            if (subscriptionInfo.getSubscriptionId() != i) {
                line1Number = this.mTelephonyManager.getLine1Number(subscriptionInfo.getSubscriptionId());
            }
        }
        if (TextUtils.isEmpty(line1Number)) {
            str2 = String.format(this.mContext.getText(R.string.limited_sim_function_notification_message).toString(), str);
        } else {
            str2 = String.format(this.mContext.getText(R.string.limited_sim_function_with_phone_num_notification_message).toString(), str, line1Number);
        }
        notifyAsUser(Integer.toString(i), 7, new Notification.BigTextStyle(new Notification.Builder(this.mContext).setSmallIcon(R.drawable.ic_sim_card).setContentTitle(this.mContext.getText(R.string.limited_sim_function_notification_title)).setContentText(str2).setOnlyAlertOnce(true).setOngoing(true).setChannelId("simHighPriority").setContentIntent(activity)).bigText(str2).build(), UserHandle.ALL);
        this.mLimitedSimFunctionNotify.add(Integer.valueOf(i));
    }

    public void dismissLimitedSimFunctionWarningNotification(int i) {
        if (DBG) {
            log("dismissLimitedSimFunctionWarningNotification subId: " + i);
        }
        if (i == -1) {
            Iterator<Integer> it = this.mLimitedSimFunctionNotify.iterator();
            while (it.hasNext()) {
                cancelAsUser(Integer.toString(it.next().intValue()), 7, UserHandle.ALL);
            }
            this.mLimitedSimFunctionNotify.clear();
            return;
        }
        if (this.mLimitedSimFunctionNotify.contains(Integer.valueOf(i))) {
            cancelAsUser(Integer.toString(i), 7, UserHandle.ALL);
            this.mLimitedSimFunctionNotify.remove(Integer.valueOf(i));
        }
    }

    public void dismissLimitedSimFunctionWarningNotificationForInactiveSubs() {
        if (DBG) {
            log("dismissLimitedSimFunctionWarningNotificationForInactiveSubs");
        }
        this.mLimitedSimFunctionNotify.removeIf(new Predicate() { // from class: com.android.phone.-$$Lambda$NotificationMgr$2K2dE6Jm9AtELRpq8SOiyWHr1kk
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$dismissLimitedSimFunctionWarningNotificationForInactiveSubs$0$NotificationMgr((Integer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$dismissLimitedSimFunctionWarningNotificationForInactiveSubs$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$dismissLimitedSimFunctionWarningNotificationForInactiveSubs$0$NotificationMgr(Integer num) {
        if (this.mSubscriptionManager.isActiveSubId(num.intValue())) {
            return false;
        }
        cancelAsUser(Integer.toString(num.intValue()), 7, UserHandle.ALL);
        return true;
    }

    private void showNetworkSelection(String str, int i) {
        if (DBG) {
            log("showNetworkSelection(" + str + ")...");
        }
        if (!TextUtils.isEmpty(str)) {
            str = String.format(" (%s)", str);
        }
        Notification.Builder channelId = new Notification.Builder(this.mContext).setSmallIcon(android.R.drawable.stat_sys_warning).setContentTitle(this.mContext.getString(R.string.notification_network_selection_title)).setContentText(this.mContext.getString(R.string.notification_network_selection_text, str)).setShowWhen(false).setOngoing(true).setChannelId("alert");
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setFlags(270532608);
        intent.setComponent(new ComponentName(this.mContext.getString(R.string.mobile_network_settings_package), this.mContext.getString(R.string.mobile_network_settings_class)));
        intent.putExtra("android.provider.extra.SUB_ID", i);
        channelId.setContentIntent(PendingIntent.getActivity(this.mContext, 0, intent, 67108864));
        notifyAsUser(Integer.toString(i), 6, channelId.build(), UserHandle.ALL);
        this.mSelectedUnavailableNotify.put(i, Boolean.TRUE);
    }

    private void cancelNetworkSelection(int i) {
        if (DBG) {
            log("cancelNetworkSelection()...");
        }
        cancelAsUser(Integer.toString(i), 6, UserHandle.ALL);
    }

    void updateNetworkSelection(int i, int i2) {
        boolean isManualSelection;
        int phoneId = SubscriptionManager.getPhoneId(i2);
        Phone phone = SubscriptionManager.isValidPhoneId(phoneId) ? PhoneFactory.getPhone(phoneId) : PhoneFactory.getDefaultPhone();
        if (TelephonyCapabilities.supportsNetworkSelection(phone)) {
            if (SubscriptionManager.isValidSubscriptionId(i2)) {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
                String string = defaultSharedPreferences.getString("network_selection_name_key" + i2, "");
                if (TextUtils.isEmpty(string)) {
                    string = defaultSharedPreferences.getString("network_selection_key" + i2, "");
                }
                if (!this.mContext.getResources().getBoolean(android.R.bool.config_debugEnableAutomaticSystemServerHeapDumps)) {
                    isManualSelection = !TextUtils.isEmpty(string);
                } else {
                    isManualSelection = phone.getServiceStateTracker().mSS.getIsManualSelection();
                }
                if (DBG) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("updateNetworkSelection()...state = ");
                    sb.append(i);
                    sb.append(" new network ");
                    sb.append(isManualSelection ? string : "");
                    log(sb.toString());
                }
                if (isManualSelection) {
                    this.mSelectedNetworkOperatorName.put(i2, string);
                    shouldShowNotification(i, i2);
                    return;
                } else {
                    dismissNetworkSelectionNotification(i2);
                    clearUpNetworkSelectionNotificationParam(i2);
                    return;
                }
            }
            if (DBG) {
                log("updateNetworkSelection()...state = " + i + " not updating network due to invalid subId " + i2);
            }
            dismissNetworkSelectionNotificationForInactiveSubId();
        }
    }

    private void dismissNetworkSelectionNotification(int i) {
        if (this.mSelectedUnavailableNotify.get(i, Boolean.FALSE).booleanValue()) {
            cancelNetworkSelection(i);
            this.mSelectedUnavailableNotify.remove(i);
        }
    }

    private void dismissNetworkSelectionNotificationForInactiveSubId() {
        for (int i = 0; i < this.mSelectedUnavailableNotify.size(); i++) {
            int iKeyAt = this.mSelectedUnavailableNotify.keyAt(i);
            if (!this.mSubscriptionManager.isActiveSubId(iKeyAt)) {
                dismissNetworkSelectionNotification(iKeyAt);
                clearUpNetworkSelectionNotificationParam(iKeyAt);
            }
        }
    }

    private void log(String str) {
        Log.d(LOG_TAG, str);
    }

    private void logi(String str) {
        Log.i(LOG_TAG, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shouldShowNotification(int i, int i2) {
        if (i == 1) {
            if (this.mPreviousServiceState.get(i2, -1).intValue() != 1) {
                this.mOOSTimestamp.put(i2, Long.valueOf(getTimeStamp()));
            }
            if (getTimeStamp() - this.mOOSTimestamp.get(i2, 0L).longValue() >= 10000 || this.mPendingEventCounter.get(i2, 0).intValue() > 10) {
                showNetworkSelection(this.mSelectedNetworkOperatorName.get(i2), i2);
                clearUpNetworkSelectionNotificationParam(i2);
            } else {
                startPendingNetworkSelectionNotification(i2);
            }
        } else {
            dismissNetworkSelectionNotification(i2);
        }
        this.mPreviousServiceState.put(i2, Integer.valueOf(i));
        if (DBG) {
            log("shouldShowNotification()... subId = " + i2 + " serviceState = " + i + " mOOSTimestamp = " + this.mOOSTimestamp + " mPendingEventCounter = " + this.mPendingEventCounter);
        }
    }

    private void startPendingNetworkSelectionNotification(int i) {
        if (this.mHandler.hasMessages(1, Integer.valueOf(i))) {
            return;
        }
        if (DBG) {
            log("startPendingNetworkSelectionNotification: subId = " + i);
        }
        Handler handler = this.mHandler;
        handler.sendMessageDelayed(handler.obtainMessage(1, Integer.valueOf(i)), 10000L);
        SparseArray<Integer> sparseArray = this.mPendingEventCounter;
        sparseArray.put(i, Integer.valueOf(sparseArray.get(i, 0).intValue() + 1));
    }

    private void clearUpNetworkSelectionNotificationParam(int i) {
        if (this.mHandler.hasMessages(1, Integer.valueOf(i))) {
            this.mHandler.removeMessages(1, Integer.valueOf(i));
        }
        this.mPreviousServiceState.remove(i);
        this.mOOSTimestamp.remove(i);
        this.mPendingEventCounter.remove(i);
        this.mSelectedNetworkOperatorName.remove(i);
    }

    private static long getTimeStamp() {
        return SystemClock.elapsedRealtime();
    }
}
