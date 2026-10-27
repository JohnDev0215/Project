package com.android.networkstack.tethering;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.NetworkCapabilities;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.SparseArray;

/* JADX INFO: loaded from: classes.dex */
public class TetheringNotificationUpdater {
    static final String ACTION_DISABLE_TETHERING = "com.android.server.connectivity.tethering.DISABLE_TETHERING";
    static final int DOWNSTREAM_NONE = 0;
    static final int EVENT_SHOW_NO_UPSTREAM = 1;
    static final int NO_ICON_ID = 0;
    static final int NO_UPSTREAM_NOTIFICATION_ID = 1002;
    static final int RESTRICTED_NOTIFICATION_ID = 1001;
    static final int ROAMING_NOTIFICATION_ID = 1003;
    static final int VERIZON_CARRIER_ID = 1839;
    private static final SparseArray<MccMncOverrideInfo> sCarrierIdToMccMnc;
    private final NotificationChannel mChannel;
    private final Context mContext;
    private final Handler mHandler;
    private final NotificationManager mNotificationManager;
    private int mDownstreamTypesMask = 0;
    private boolean mNoUpstream = false;
    private boolean mRoaming = false;
    private volatile int mActiveDataSubId = -1;

    private static final class MccMncOverrideInfo {
        public final int homeMcc;
        public final int homeMnc;
        public final String visitedMccMnc;

        MccMncOverrideInfo(String str, int i, int i2) {
            this.visitedMccMnc = str;
            this.homeMcc = i;
            this.homeMnc = i2;
        }
    }

    static {
        SparseArray<MccMncOverrideInfo> sparseArray = new SparseArray<>();
        sCarrierIdToMccMnc = sparseArray;
        sparseArray.put(VERIZON_CARRIER_ID, new MccMncOverrideInfo("20404", 311, 480));
    }

    public TetheringNotificationUpdater(Context context, Looper looper) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.createContextAsUser(UserHandle.ALL, 0).getSystemService("notification");
        NotificationChannel notificationChannel = new NotificationChannel("TETHERING_STATUS", context.getResources().getString(R.string.notification_channel_tethering_status), 2);
        this.mChannel = notificationChannel;
        this.mNotificationManager.createNotificationChannel(notificationChannel);
        this.mHandler = new NotificationHandler(looper);
    }

    private class NotificationHandler extends Handler {
        NotificationHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != TetheringNotificationUpdater.EVENT_SHOW_NO_UPSTREAM) {
                return;
            }
            TetheringNotificationUpdater.this.notifyTetheringNoUpstream();
        }
    }

    public void onDownstreamChanged(int i) {
        updateActiveNotifications(this.mActiveDataSubId, i, this.mNoUpstream, this.mRoaming);
    }

    public void onActiveDataSubscriptionIdChanged(int i) {
        updateActiveNotifications(i, this.mDownstreamTypesMask, this.mNoUpstream, this.mRoaming);
    }

    public void onUpstreamCapabilitiesChanged(NetworkCapabilities networkCapabilities) {
        updateActiveNotifications(this.mActiveDataSubId, this.mDownstreamTypesMask, networkCapabilities == null ? EVENT_SHOW_NO_UPSTREAM : false, (networkCapabilities == null || networkCapabilities.hasCapability(18)) ? false : true);
    }

    final Handler getHandler() {
        return this.mHandler;
    }

    Resources getResourcesForSubId(Context context, int i) {
        Resources resourcesForSubId = SubscriptionManager.getResourcesForSubId(context, i);
        TelephonyManager telephonyManagerCreateForSubscriptionId = ((TelephonyManager) context.getSystemService("phone")).createForSubscriptionId(this.mActiveDataSubId);
        int simCarrierId = telephonyManagerCreateForSubscriptionId.getSimCarrierId();
        String simOperator = telephonyManagerCreateForSubscriptionId.getSimOperator();
        MccMncOverrideInfo mccMncOverrideInfo = sCarrierIdToMccMnc.get(simCarrierId);
        if (mccMncOverrideInfo == null || !mccMncOverrideInfo.visitedMccMnc.equals(simOperator)) {
            return resourcesForSubId;
        }
        Configuration configuration = resourcesForSubId.getConfiguration();
        configuration.mcc = mccMncOverrideInfo.homeMcc;
        configuration.mnc = mccMncOverrideInfo.homeMnc;
        return context.createConfigurationContext(configuration).getResources();
    }

    private void updateActiveNotifications(int i, int i2, boolean z, boolean z2) {
        boolean z3 = true;
        boolean z4 = (i2 == 0 ? EVENT_SHOW_NO_UPSTREAM : false) != (this.mDownstreamTypesMask == 0 ? EVENT_SHOW_NO_UPSTREAM : false) ? EVENT_SHOW_NO_UPSTREAM : false;
        boolean z5 = i != this.mActiveDataSubId ? EVENT_SHOW_NO_UPSTREAM : false;
        boolean z6 = z != this.mNoUpstream ? EVENT_SHOW_NO_UPSTREAM : false;
        boolean z7 = z2 != this.mRoaming ? EVENT_SHOW_NO_UPSTREAM : false;
        if (!z4 && !z5) {
            z3 = false;
        }
        this.mActiveDataSubId = i;
        this.mDownstreamTypesMask = i2;
        this.mNoUpstream = z;
        this.mRoaming = z2;
        if (z3 || z6) {
            updateNoUpstreamNotification();
        }
        if (z3 || z7) {
            updateRoamingNotification();
        }
    }

    private void updateNoUpstreamNotification() {
        if (!(this.mDownstreamTypesMask == 0 ? EVENT_SHOW_NO_UPSTREAM : false) && this.mNoUpstream && setupNoUpstreamNotification()) {
            return;
        }
        clearNotification(NO_UPSTREAM_NOTIFICATION_ID);
        this.mHandler.removeMessages(EVENT_SHOW_NO_UPSTREAM);
    }

    private void updateRoamingNotification() {
        if (!(this.mDownstreamTypesMask == 0) && this.mRoaming && setupRoamingNotification()) {
            return;
        }
        clearNotification(ROAMING_NOTIFICATION_ID);
    }

    void tetheringRestrictionLifted() {
        clearNotification(RESTRICTED_NOTIFICATION_ID);
    }

    private void clearNotification(int i) {
        this.mNotificationManager.cancel(null, i);
    }

    static String getSettingsPackageName(PackageManager packageManager) {
        ComponentName componentNameResolveActivity = new Intent("android.settings.SETTINGS").resolveActivity(packageManager);
        return componentNameResolveActivity != null ? componentNameResolveActivity.getPackageName() : "com.android.settings";
    }

    void notifyTetheringDisabledByRestriction() {
        Resources resourcesForSubId = getResourcesForSubId(this.mContext, this.mActiveDataSubId);
        String string = resourcesForSubId.getString(R.string.disable_tether_notification_title);
        String string2 = resourcesForSubId.getString(R.string.disable_tether_notification_message);
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            return;
        }
        showNotification(R.drawable.stat_sys_tether_general, string, string2, RESTRICTED_NOTIFICATION_ID, false, PendingIntent.getActivity(this.mContext.createContextAsUser(UserHandle.CURRENT, 0), 0, new Intent("android.settings.TETHER_SETTINGS").setPackage(getSettingsPackageName(this.mContext.getPackageManager())), 335544320, null), new Notification.Action[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyTetheringNoUpstream() {
        Resources resourcesForSubId = getResourcesForSubId(this.mContext, this.mActiveDataSubId);
        String string = resourcesForSubId.getString(R.string.no_upstream_notification_title);
        String string2 = resourcesForSubId.getString(R.string.no_upstream_notification_message);
        String string3 = resourcesForSubId.getString(R.string.no_upstream_notification_disable_button);
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
            return;
        }
        Intent intent = new Intent(ACTION_DISABLE_TETHERING);
        intent.setPackage(this.mContext.getPackageName());
        showNotification(R.drawable.stat_sys_tether_general, string, string2, NO_UPSTREAM_NOTIFICATION_ID, true, null, new Notification.Action.Builder(0, string3, PendingIntent.getBroadcast(this.mContext.createContextAsUser(UserHandle.CURRENT, 0), 0, intent, 67108864)).build());
    }

    private boolean setupRoamingNotification() {
        Resources resourcesForSubId = getResourcesForSubId(this.mContext, this.mActiveDataSubId);
        if (!resourcesForSubId.getBoolean(R.bool.config_upstream_roaming_notification)) {
            return false;
        }
        String string = resourcesForSubId.getString(R.string.upstream_roaming_notification_title);
        String string2 = resourcesForSubId.getString(R.string.upstream_roaming_notification_message);
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            return false;
        }
        showNotification(R.drawable.stat_sys_tether_general, string, string2, ROAMING_NOTIFICATION_ID, true, PendingIntent.getActivity(this.mContext.createContextAsUser(UserHandle.CURRENT, 0), 0, new Intent("android.settings.TETHER_SETTINGS").setPackage(getSettingsPackageName(this.mContext.getPackageManager())), 335544320, null), new Notification.Action[0]);
        return true;
    }

    private boolean setupNoUpstreamNotification() {
        int integer = getResourcesForSubId(this.mContext, this.mActiveDataSubId).getInteger(R.integer.delay_to_show_no_upstream_after_no_backhaul);
        if (integer < 0) {
            return false;
        }
        Handler handler = this.mHandler;
        handler.sendMessageDelayed(handler.obtainMessage(EVENT_SHOW_NO_UPSTREAM), integer);
        return true;
    }

    private void showNotification(int i, String str, String str2, int i2, boolean z, PendingIntent pendingIntent, Notification.Action... actionArr) {
        this.mNotificationManager.notify(null, i2, new Notification.Builder(this.mContext, this.mChannel.getId()).setSmallIcon(i).setContentTitle(str).setContentText(str2).setOngoing(z).setColor(this.mContext.getColor(android.R.color.system_notification_accent_color)).setVisibility(EVENT_SHOW_NO_UPSTREAM).setCategory("status").setContentIntent(pendingIntent).setActions(actionArr).build());
    }
}
