package com.android.permissioncontroller.permission.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.ArraySet;
import com.android.car.ui.utils.RotaryConstants;
import com.android.permissioncontroller.Constants;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.utils.LocationUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes8.dex */
public class AutoGrantPermissionsNotifier {
    private static final ArraySet<String> PERMISSIONS_TO_NOTIFY_FOR;
    private final Context mContext;
    private final ArrayList<String> mGrantedPermissions = new ArrayList<>();
    private final PackageInfo mPackageInfo;

    static {
        ArraySet<String> arraySet = new ArraySet<>();
        PERMISSIONS_TO_NOTIFY_FOR = arraySet;
        arraySet.add("android.permission.ACCESS_FINE_LOCATION");
        PERMISSIONS_TO_NOTIFY_FOR.add("android.permission.ACCESS_BACKGROUND_LOCATION");
        PERMISSIONS_TO_NOTIFY_FOR.add("android.permission.ACCESS_COARSE_LOCATION");
    }

    public AutoGrantPermissionsNotifier(Context context, PackageInfo packageInfo) {
        this.mPackageInfo = packageInfo;
        UserHandle callingUser = UserHandle.getUserHandleForUid(packageInfo.applicationInfo.uid);
        this.mContext = context.createContextAsUser(callingUser, 0);
    }

    private void createAutoGrantNotifierChannel(boolean shouldNotifySilently) {
        NotificationManager notificationManager = (NotificationManager) Utils.getSystemServiceSafe(this.mContext, NotificationManager.class);
        NotificationChannel autoGrantedPermissionsChannel = new NotificationChannel(getNotificationChannelId(shouldNotifySilently), this.mContext.getString(R.string.auto_granted_permissions), 4);
        if (shouldNotifySilently) {
            autoGrantedPermissionsChannel.enableVibration(false);
            autoGrantedPermissionsChannel.setSound(Uri.EMPTY, null);
        }
        notificationManager.createNotificationChannel(autoGrantedPermissionsChannel);
    }

    public void notifyOfAutoGrantPermissions(boolean shouldNotifySilently) {
        if (this.mGrantedPermissions.isEmpty()) {
            return;
        }
        createAutoGrantNotifierChannel(shouldNotifySilently);
        PackageManager pm = this.mContext.getPackageManager();
        CharSequence pkgLabel = pm.getApplicationLabel(this.mPackageInfo.applicationInfo);
        long sessionId = Utils.getValidSessionId();
        Intent manageAppPermission = getSettingsPermissionIntent(sessionId);
        Bitmap pkgIconBmp = getPackageIcon(pm.getApplicationIcon(this.mPackageInfo.applicationInfo));
        int packageBasedRequestCode = this.mPackageInfo.packageName.hashCode();
        String title = this.mContext.getString(R.string.auto_granted_location_permission_notification_title);
        String messageText = this.mContext.getString(R.string.auto_granted_permission_notification_body, pkgLabel);
        Notification.Builder b = new Notification.Builder(this.mContext, getNotificationChannelId(shouldNotifySilently)).setContentTitle(title).setContentText(messageText).setStyle(new Notification.BigTextStyle().bigText(messageText).setBigContentTitle(title)).setSmallIcon(R.drawable.ic_pin_drop).setLargeIcon(pkgIconBmp).setColor(this.mContext.getColor(android.R.color.system_notification_accent_color)).setContentIntent(PendingIntent.getActivity(this.mContext, packageBasedRequestCode, manageAppPermission, RotaryConstants.ACTION_HIDE_IME));
        CharSequence appName = getSettingsAppName();
        if (appName != null) {
            Bundle extras = new Bundle();
            extras.putString("android.substName", appName.toString());
            b.addExtras(extras);
        }
        NotificationManager notificationManager = (NotificationManager) Utils.getSystemServiceSafe(this.mContext, NotificationManager.class);
        notificationManager.cancel(this.mPackageInfo.packageName, 1);
        notificationManager.notify(this.mPackageInfo.packageName, 1, b.build());
    }

    public void onPermissionAutoGranted(String permissionName) {
        if (PERMISSIONS_TO_NOTIFY_FOR.contains(permissionName)) {
            this.mGrantedPermissions.add(permissionName);
        }
    }

    private CharSequence getSettingsAppName() {
        PackageManager pm = this.mContext.getPackageManager();
        Intent openSettingsIntent = new Intent("android.settings.SETTINGS");
        ResolveInfo resolveInfo = pm.resolveActivity(openSettingsIntent, 0);
        if (resolveInfo == null) {
            return null;
        }
        return pm.getApplicationLabel(resolveInfo.activityInfo.applicationInfo);
    }

    private Intent getSettingsPermissionIntent(long sessionId) {
        UserHandle callingUser = UserHandle.getUserHandleForUid(this.mPackageInfo.applicationInfo.uid);
        return new Intent("android.intent.action.MANAGE_APP_PERMISSION").addFlags(402653184).putExtra("android.intent.extra.PERMISSION_GROUP_NAME", LocationUtils.LOCATION_PERMISSION).putExtra("android.intent.extra.PACKAGE_NAME", this.mPackageInfo.packageName).putExtra("android.intent.extra.USER", callingUser).putExtra(Constants.EXTRA_SESSION_ID, sessionId).putExtra("com.android.permissioncontroller.extra.CALLER_NAME", AutoGrantPermissionsNotifier.class.getName());
    }

    private Bitmap getPackageIcon(Drawable pkgIcon) {
        Bitmap pkgIconBmp = Bitmap.createBitmap(pkgIcon.getIntrinsicWidth(), pkgIcon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(pkgIconBmp);
        pkgIcon.setBounds(0, 0, pkgIcon.getIntrinsicWidth(), pkgIcon.getIntrinsicHeight());
        pkgIcon.draw(canvas);
        return pkgIconBmp;
    }

    private String getNotificationChannelId(boolean shouldNotifySilently) {
        if (shouldNotifySilently) {
            return Constants.ADMIN_AUTO_GRANTED_PERMISSIONS_NOTIFICATION_CHANNEL_ID;
        }
        return Constants.ADMIN_AUTO_GRANTED_PERMISSIONS_ALERTING_NOTIFICATION_CHANNEL_ID;
    }
}
