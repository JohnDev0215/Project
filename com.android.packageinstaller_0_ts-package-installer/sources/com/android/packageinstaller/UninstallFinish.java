package com.android.packageinstaller;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.admin.IDevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.UserInfo;
import android.graphics.drawable.Icon;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import android.widget.Toast;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class UninstallFinish extends BroadcastReceiver {
    static final String EXTRA_APP_LABEL = "com.android.packageinstaller.extra.APP_LABEL";
    static final String EXTRA_UNINSTALL_ID = "com.android.packageinstaller.extra.UNINSTALL_ID";
    private static final String LOG_TAG = UninstallFinish.class.getSimpleName();
    private static final String UNINSTALL_FAILURE_CHANNEL = "uninstall failure";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int uninstallId;
        NotificationManager notificationManager;
        int blockingUserId;
        UserInfo otherBlockingUser;
        int legacyStatus;
        int returnCode = intent.getIntExtra("android.content.pm.extra.STATUS", 0);
        Log.i(LOG_TAG, "Uninstall finished extras=" + intent.getExtras());
        if (returnCode == -1) {
            context.startActivity((Intent) intent.getParcelableExtra("android.intent.extra.INTENT"));
            return;
        }
        int uninstallId2 = intent.getIntExtra(EXTRA_UNINSTALL_ID, 0);
        ApplicationInfo appInfo = (ApplicationInfo) intent.getParcelableExtra(PackageUtil.INTENT_ATTR_APPLICATION_INFO);
        String appLabel = intent.getStringExtra(EXTRA_APP_LABEL);
        boolean allUsers = intent.getBooleanExtra("android.intent.extra.UNINSTALL_ALL_USERS", false);
        NotificationManager notificationManager2 = (NotificationManager) context.getSystemService(NotificationManager.class);
        UserManager userManager = (UserManager) context.getSystemService(UserManager.class);
        NotificationChannel uninstallFailureChannel = new NotificationChannel(UNINSTALL_FAILURE_CHANNEL, context.getString(R.string.uninstall_failure_notification_channel), 3);
        notificationManager2.createNotificationChannel(uninstallFailureChannel);
        Notification.Builder uninstallFailedNotification = new Notification.Builder(context, UNINSTALL_FAILURE_CHANNEL);
        if (returnCode == 0) {
            notificationManager2.cancel(uninstallId2);
            Toast.makeText(context, context.getString(R.string.uninstall_done_app, appLabel), 1).show();
            return;
        }
        if (returnCode == 2) {
            int legacyStatus2 = intent.getIntExtra("android.content.pm.extra.LEGACY_STATUS", 0);
            if (legacyStatus2 == -4) {
                IPackageManager packageManager = IPackageManager.Stub.asInterface(ServiceManager.getService("package"));
                List<UserInfo> users = userManager.getUsers();
                uninstallId = uninstallId2;
                int uninstallId3 = 0;
                while (true) {
                    if (uninstallId3 >= users.size()) {
                        notificationManager = notificationManager2;
                        blockingUserId = -10000;
                        break;
                    }
                    List<UserInfo> users2 = users;
                    UserInfo user = users.get(uninstallId3);
                    try {
                        notificationManager = notificationManager2;
                        try {
                            if (packageManager.getBlockUninstallForUser(appInfo.packageName, user.id)) {
                                blockingUserId = user.id;
                                break;
                            }
                            continue;
                        } catch (RemoteException e) {
                            e = e;
                            Log.e(LOG_TAG, "Failed to talk to package manager", e);
                        }
                    } catch (RemoteException e2) {
                        e = e2;
                        notificationManager = notificationManager2;
                    }
                    uninstallId3++;
                    users = users2;
                    notificationManager2 = notificationManager;
                }
                int myUserId = UserHandle.myUserId();
                if (isProfileOfOrSame(userManager, myUserId, blockingUserId)) {
                    addDeviceManagerButton(context, uninstallFailedNotification);
                } else {
                    addManageUsersButton(context, uninstallFailedNotification);
                }
                if (blockingUserId == -10000) {
                    Log.d(LOG_TAG, "Uninstall failed for " + appInfo.packageName + " with code " + returnCode + " no blocking user");
                } else if (blockingUserId == 0) {
                    setBigText(uninstallFailedNotification, context.getString(R.string.uninstall_blocked_device_owner));
                } else if (!allUsers) {
                    setBigText(uninstallFailedNotification, context.getString(R.string.uninstall_blocked_profile_owner));
                } else {
                    setBigText(uninstallFailedNotification, context.getString(R.string.uninstall_all_blocked_profile_owner));
                }
            } else if (legacyStatus2 == -2) {
                IDevicePolicyManager dpm = IDevicePolicyManager.Stub.asInterface(ServiceManager.getService("device_policy"));
                int myUserId2 = UserHandle.myUserId();
                Iterator it = userManager.getUsers().iterator();
                while (true) {
                    if (it.hasNext()) {
                        UserInfo user2 = (UserInfo) it.next();
                        if (!isProfileOfOrSame(userManager, myUserId2, user2.id)) {
                            try {
                                legacyStatus = legacyStatus2;
                            } catch (RemoteException e3) {
                                e = e3;
                                legacyStatus = legacyStatus2;
                            }
                            try {
                                if (dpm.packageHasActiveAdmins(appInfo.packageName, user2.id)) {
                                    otherBlockingUser = user2;
                                    break;
                                }
                            } catch (RemoteException e4) {
                                e = e4;
                                Log.e(LOG_TAG, "Failed to talk to package manager", e);
                            }
                            legacyStatus2 = legacyStatus;
                        }
                    } else {
                        otherBlockingUser = null;
                        break;
                    }
                }
                if (otherBlockingUser == null) {
                    Log.d(LOG_TAG, "Uninstall failed because " + appInfo.packageName + " is a device admin");
                    addDeviceManagerButton(context, uninstallFailedNotification);
                    setBigText(uninstallFailedNotification, context.getString(R.string.uninstall_failed_device_policy_manager));
                    uninstallId = uninstallId2;
                    notificationManager = notificationManager2;
                } else {
                    Log.d(LOG_TAG, "Uninstall failed because " + appInfo.packageName + " is a device admin of user " + otherBlockingUser);
                    setBigText(uninstallFailedNotification, String.format(context.getString(R.string.uninstall_failed_device_policy_manager_of_user), otherBlockingUser.name));
                    uninstallId = uninstallId2;
                    notificationManager = notificationManager2;
                }
            } else {
                Log.d(LOG_TAG, "Uninstall blocked for " + appInfo.packageName + " with legacy code " + legacyStatus2);
                uninstallId = uninstallId2;
                notificationManager = notificationManager2;
            }
        } else {
            Log.d(LOG_TAG, "Uninstall failed for " + appInfo.packageName + " with code " + returnCode);
            uninstallId = uninstallId2;
            notificationManager = notificationManager2;
        }
        uninstallFailedNotification.setContentTitle(context.getString(R.string.uninstall_failed_app, appLabel));
        uninstallFailedNotification.setOngoing(false);
        uninstallFailedNotification.setSmallIcon(R.drawable.ic_error);
        notificationManager.notify(uninstallId, uninstallFailedNotification.build());
    }

    private boolean isProfileOfOrSame(UserManager userManager, int userId, int profileId) {
        if (userId == profileId) {
            return true;
        }
        UserInfo parentUser = userManager.getProfileParent(profileId);
        return parentUser != null && parentUser.id == userId;
    }

    private void setBigText(Notification.Builder builder, CharSequence text) {
        builder.setStyle(new Notification.BigTextStyle().bigText(text));
    }

    private void addManageUsersButton(Context context, Notification.Builder builder) {
        Intent intent = new Intent("android.settings.USER_SETTINGS");
        intent.setFlags(1342177280);
        builder.addAction(new Notification.Action.Builder(Icon.createWithResource(context, R.drawable.ic_settings_multiuser), context.getString(R.string.manage_users), PendingIntent.getActivity(context, 0, intent, 134217728)).build());
    }

    private void addDeviceManagerButton(Context context, Notification.Builder builder) {
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.Settings$DeviceAdminSettingsActivity");
        intent.setFlags(1342177280);
        builder.addAction(new Notification.Action.Builder(Icon.createWithResource(context, R.drawable.ic_lock), context.getString(R.string.manage_device_administrators), PendingIntent.getActivity(context, 0, intent, 134217728)).build());
    }
}
