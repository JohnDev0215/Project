package com.android.packageinstaller.wear;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import com.android.packageinstaller.DeviceUtils;
import com.android.packageinstaller.R;
import com.android.packageinstaller.wear.PackageInstallerImpl;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class WearPackageInstallerService extends Service {
    private static final String TAG = "WearPkgInstallerService";
    private static final String WEAR_APPS_CHANNEL = "wear_app_install_uninstall";
    private static volatile PowerManager.WakeLock lockStatic = null;
    private final int START_INSTALL = 1;
    private final int START_UNINSTALL = 2;
    private int mInstallNotificationId = 1;
    private final Map<String, Integer> mNotifIdMap = new ArrayMap();
    private NotificationChannel mNotificationChannel;
    private ServiceHandler mServiceHandler;

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) throws Throwable {
            int i = msg.what;
            if (i == 1) {
                WearPackageInstallerService.this.installPackage(msg.getData());
            } else if (i == 2) {
                WearPackageInstallerService.this.uninstallPackage(msg.getData());
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        HandlerThread thread = new HandlerThread("PackageInstallerThread", 10);
        thread.start();
        this.mServiceHandler = new ServiceHandler(thread.getLooper());
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        Message msg;
        String notifTitle;
        if (!DeviceUtils.isWear(this)) {
            Log.w(TAG, "Not running on wearable.");
            finishServiceEarly(startId);
            return 2;
        }
        if (intent == null) {
            Log.w(TAG, "Got null intent.");
            finishServiceEarly(startId);
            return 2;
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Got install/uninstall request " + intent);
        }
        Uri packageUri = intent.getData();
        if (packageUri == null) {
            Log.e(TAG, "No package URI in intent");
            finishServiceEarly(startId);
            return 2;
        }
        String packageName = WearPackageUtil.getSanitizedPackageName(packageUri);
        if (packageName == null) {
            Log.e(TAG, "Invalid package name in URI (expected package:<pkgName>): " + packageUri);
            finishServiceEarly(startId);
            return 2;
        }
        PowerManager.WakeLock lock = getLock(getApplicationContext());
        if (!lock.isHeld()) {
            lock.acquire();
        }
        Bundle intentBundle = intent.getExtras();
        if (intentBundle == null) {
            intentBundle = new Bundle();
        }
        WearPackageArgs.setStartId(intentBundle, startId);
        WearPackageArgs.setPackageName(intentBundle, packageName);
        if ("android.intent.action.INSTALL_PACKAGE".equals(intent.getAction())) {
            msg = this.mServiceHandler.obtainMessage(1);
            notifTitle = getString(R.string.installing);
        } else {
            String notifTitle2 = intent.getAction();
            if ("android.intent.action.UNINSTALL_PACKAGE".equals(notifTitle2)) {
                msg = this.mServiceHandler.obtainMessage(2);
                notifTitle = getString(R.string.uninstalling);
            } else {
                Log.e(TAG, "Unknown action : " + intent.getAction());
                finishServiceEarly(startId);
                return 2;
            }
        }
        Pair<Integer, Notification> notifPair = buildNotification(packageName, notifTitle);
        startForeground(((Integer) notifPair.first).intValue(), (Notification) notifPair.second);
        msg.setData(intentBundle);
        this.mServiceHandler.sendMessage(msg);
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Not initialized variable reg: 31, insn: 0x03bd: MOVE (r14 I:??[OBJECT, ARRAY]) = (r31 I:??[OBJECT, ARRAY] A[D('pm' android.content.pm.PackageManager)]), block:B:130:0x03b6 */
    /* JADX WARN: Not initialized variable reg: 31, insn: 0x03cc: MOVE (r14 I:??[OBJECT, ARRAY]) = (r31 I:??[OBJECT, ARRAY] A[D('pm' android.content.pm.PackageManager)]), block:B:132:0x03c4 */
    /* JADX WARN: Not initialized variable reg: 32, insn: 0x03bf: MOVE (r18 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r32 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('skipIfLowerVersion' boolean)]), block:B:130:0x03b6 */
    /* JADX WARN: Not initialized variable reg: 32, insn: 0x03ce: MOVE (r18 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r32 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('skipIfLowerVersion' boolean)]), block:B:132:0x03c4 */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0379  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void installPackage(android.os.Bundle r38) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1606
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.packageinstaller.wear.WearPackageInstallerService.installPackage(android.os.Bundle):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uninstallPackage(Bundle argsBundle) {
        int startId = WearPackageArgs.getStartId(argsBundle);
        String packageName = WearPackageArgs.getPackageName(argsBundle);
        PowerManager.WakeLock lock = getLock(getApplicationContext());
        PackageManager pm = getPackageManager();
        try {
            PackageInfo pkgInfo = pm.getPackageInfo(packageName, 0);
            getLabelAndUpdateNotification(packageName, getString(R.string.uninstalling_app, new Object[]{pkgInfo.applicationInfo.loadLabel(pm)}));
            pm.deletePackage(packageName, new PackageDeleteObserver(lock, startId), 2);
            Log.i(TAG, "Sent delete request for " + packageName);
        } catch (PackageManager.NameNotFoundException | IllegalArgumentException e) {
            Log.w(TAG, "Could not find package, not deleting " + packageName, e);
            finishService(lock, startId);
        }
    }

    private boolean checkPermissions(PackageInfo pkgInfo, int companionSdkVersion, int companionDeviceVersion, Uri permUri, List<String> wearablePermissions, File apkFile) {
        if (pkgInfo.applicationInfo.targetSdkVersion >= 23 || !doesWearHaveUngrantedPerms(pkgInfo.packageName, permUri, wearablePermissions)) {
            return true;
        }
        if (companionSdkVersion == 0 || companionSdkVersion >= 23) {
            Log.e(TAG, "MNC: Wear app's targetSdkVersion should be at least 23, if phone app is targeting at least 23, will continue.");
            return false;
        }
        return false;
    }

    private boolean doesWearHaveUngrantedPerms(String packageName, Uri permUri, List<String> wearablePermissions) {
        if (permUri == null) {
            Log.e(TAG, "Permission URI is null");
            return true;
        }
        Cursor permCursor = getContentResolver().query(permUri, null, null, null, null);
        if (permCursor == null) {
            Log.e(TAG, "Could not get the cursor for the permissions");
            return true;
        }
        Set<String> grantedPerms = new HashSet<>();
        Set<String> ungrantedPerms = new HashSet<>();
        while (permCursor.moveToNext()) {
            if (permCursor.getColumnCount() == 2 && 3 == permCursor.getType(0) && 1 == permCursor.getType(1)) {
                String perm = permCursor.getString(0);
                Integer granted = Integer.valueOf(permCursor.getInt(1));
                if (granted.intValue() == 1) {
                    grantedPerms.add(perm);
                } else {
                    ungrantedPerms.add(perm);
                }
            }
        }
        permCursor.close();
        boolean hasUngrantedPerm = false;
        for (String wearablePerm : wearablePermissions) {
            if (!grantedPerms.contains(wearablePerm)) {
                hasUngrantedPerm = true;
                if (!ungrantedPerms.contains(wearablePerm)) {
                    Log.e(TAG, "Wearable " + packageName + " has a permission \"" + wearablePerm + "\" that is not defined in the host application's manifest.");
                } else {
                    Log.w(TAG, "Wearable " + packageName + " has a permission \"" + wearablePerm + "\" that is not granted in the host application.");
                }
            }
        }
        return hasUngrantedPerm;
    }

    private void finishServiceEarly(int startId) {
        Pair<Integer, Notification> notifPair = buildNotification(getApplicationContext().getPackageName(), "");
        startForeground(((Integer) notifPair.first).intValue(), (Notification) notifPair.second);
        finishService(null, startId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishService(PowerManager.WakeLock lock, int startId) {
        if (lock != null && lock.isHeld()) {
            lock.release();
        }
        stopSelf(startId);
    }

    private synchronized PowerManager.WakeLock getLock(Context context) {
        if (lockStatic == null) {
            PowerManager mgr = (PowerManager) context.getSystemService("power");
            lockStatic = mgr.newWakeLock(1, context.getClass().getSimpleName());
            lockStatic.setReferenceCounted(true);
        }
        return lockStatic;
    }

    private class PackageInstallListener implements PackageInstallerImpl.InstallListener {
        private String mApplicationPackageName;
        private Context mContext;
        private int mStartId;
        private PowerManager.WakeLock mWakeLock;

        private PackageInstallListener(Context context, PowerManager.WakeLock wakeLock, int startId, String applicationPackageName) {
            this.mContext = context;
            this.mWakeLock = wakeLock;
            this.mStartId = startId;
            this.mApplicationPackageName = applicationPackageName;
        }

        @Override // com.android.packageinstaller.wear.PackageInstallerImpl.InstallListener
        public void installBeginning() {
            Log.i(WearPackageInstallerService.TAG, "Package " + this.mApplicationPackageName + " is being installed.");
        }

        @Override // com.android.packageinstaller.wear.PackageInstallerImpl.InstallListener
        public void installSucceeded() {
            try {
                Log.i(WearPackageInstallerService.TAG, "Package " + this.mApplicationPackageName + " was installed.");
                File tempFile = WearPackageUtil.getTemporaryFile(this.mContext, this.mApplicationPackageName);
                if (tempFile != null) {
                    tempFile.delete();
                }
            } finally {
                WearPackageInstallerService.this.finishService(this.mWakeLock, this.mStartId);
            }
        }

        @Override // com.android.packageinstaller.wear.PackageInstallerImpl.InstallListener
        public void installFailed(int errorCode, String errorDesc) {
            Log.e(WearPackageInstallerService.TAG, "Package install failed " + this.mApplicationPackageName + ", errorCode " + errorCode);
            WearPackageInstallerService.this.finishService(this.mWakeLock, this.mStartId);
        }
    }

    private class PackageDeleteObserver extends IPackageDeleteObserver.Stub {
        private int mStartId;
        private PowerManager.WakeLock mWakeLock;

        private PackageDeleteObserver(PowerManager.WakeLock wakeLock, int startId) {
            this.mWakeLock = wakeLock;
            this.mStartId = startId;
        }

        public void packageDeleted(String packageName, int returnCode) {
            try {
                if (returnCode >= 0) {
                    Log.i(WearPackageInstallerService.TAG, "Package " + packageName + " was uninstalled.");
                } else {
                    Log.e(WearPackageInstallerService.TAG, "Package uninstall failed " + packageName + ", returnCode " + returnCode);
                }
            } finally {
                WearPackageInstallerService.this.finishService(this.mWakeLock, this.mStartId);
            }
        }
    }

    private synchronized Pair<Integer, Notification> buildNotification(String packageName, String title) {
        int notifId;
        if (this.mNotifIdMap.containsKey(packageName)) {
            notifId = this.mNotifIdMap.get(packageName).intValue();
        } else {
            notifId = this.mInstallNotificationId;
            this.mInstallNotificationId = notifId + 1;
            this.mNotifIdMap.put(packageName, Integer.valueOf(notifId));
        }
        if (this.mNotificationChannel == null) {
            this.mNotificationChannel = new NotificationChannel(WEAR_APPS_CHANNEL, getString(R.string.wear_app_channel), 1);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(this.mNotificationChannel);
        }
        return new Pair<>(Integer.valueOf(notifId), new Notification.Builder(this, WEAR_APPS_CHANNEL).setSmallIcon(R.drawable.ic_file_download).setContentTitle(title).build());
    }

    private void getLabelAndUpdateNotification(String packageName, String title) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        Pair<Integer, Notification> notifPair = buildNotification(packageName, title);
        notificationManager.notify(((Integer) notifPair.first).intValue(), (Notification) notifPair.second);
    }
}
