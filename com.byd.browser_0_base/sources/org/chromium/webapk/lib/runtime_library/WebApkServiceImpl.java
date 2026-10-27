package org.chromium.webapk.lib.runtime_library;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import defpackage.InterfaceC0223z0;
import defpackage.f1;
import defpackage.g1;
import org.chromium.webapk.lib.runtime_library.IWebApkApi;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
public class WebApkServiceImpl extends IWebApkApi.Stub {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String KEY_HOST_BROWSER_UID = "host_browser_uid";
    public static final String KEY_SMALL_ICON_ID = "small_icon_id";
    private static final String TAG = "WebApkServiceImpl";
    private final Context mContext;
    private final int mHostUid;
    private final int mSmallIconId;

    public WebApkServiceImpl(Context context, Bundle bundle) {
        this.mContext = context;
        this.mSmallIconId = bundle.getInt(KEY_SMALL_ICON_ID);
        this.mHostUid = bundle.getInt(KEY_HOST_BROWSER_UID);
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) this.mContext.getSystemService("notification");
    }

    private static String getTaskBaseActivityPackageName(ActivityManager.AppTask appTask) {
        try {
            ActivityManager.RecentTaskInfo taskInfo = appTask.getTaskInfo();
            if (taskInfo == null || f1.a(taskInfo) == null) {
                return null;
            }
            return f1.a(taskInfo).getPackageName();
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
    public void cancelNotification(String str, int i) {
        getNotificationManager().cancel(str, i);
    }

    @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
    public int checkNotificationPermission() {
        Log.w(TAG, "Should NOT reach WebApkServiceImpl#checkNotificationPermission().");
        return -1;
    }

    @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
    public boolean finishAndRemoveTaskSdk23() {
        ActivityManager activityManager = (ActivityManager) this.mContext.getSystemService("activity");
        String packageName = this.mContext.getPackageName();
        for (ActivityManager.AppTask appTask : activityManager.getAppTasks()) {
            if (TextUtils.equals(getTaskBaseActivityPackageName(appTask), packageName)) {
                appTask.finishAndRemoveTask();
                return true;
            }
        }
        return false;
    }

    @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
    public int getSmallIconId() {
        return this.mSmallIconId;
    }

    @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
    public boolean notificationPermissionEnabled() {
        Log.w(TAG, "Should NOT reach WebApkServiceImpl#notificationPermissionEnabled() because it is deprecated.");
        return InterfaceC0223z0.b(this.mContext).a();
    }

    @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
    public void notifyNotification(String str, int i, Notification notification) {
        Log.w(TAG, "Should NOT reach WebApkServiceImpl#notifyNotification(String, int, Notification).");
    }

    @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
    public void notifyNotificationWithChannel(String str, int i, Notification notification, String str2) {
        NotificationManager notificationManager = getNotificationManager();
        if (Build.VERSION.SDK_INT >= 26 && g1.b(notification) != null) {
            g1.c();
            g1.d(notificationManager, g1.a(g1.b(notification), str2));
        }
        notificationManager.notify(str, i, notification);
    }

    @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi.Stub, android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        int callingUid = Binder.getCallingUid();
        if (this.mHostUid == callingUid) {
            return super.onTransact(i, parcel, parcel2, i2);
        }
        throw new RemoteException("Unauthorized caller " + callingUid + " does not match expected host=" + this.mHostUid);
    }

    @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
    public PendingIntent requestNotificationPermission(String str, String str2) {
        Log.w(TAG, "Should NOT reach WebApkServiceImpl#requestNotificationPermission(String, String).");
        return null;
    }
}
