package com.android.packageinstaller.wear;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageInstaller;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class PackageInstallerImpl {
    private static final String ACTION_INSTALL_COMMIT = "com.android.vending.INTENT_PACKAGE_INSTALL_COMMIT";
    private static final String TAG = "PackageInstallerImpl";
    private final Context mContext;
    private final Map<String, PackageInstaller.Session> mOpenSessionMap;
    private final PackageInstaller mPackageInstaller;
    private final Map<String, PackageInstaller.SessionInfo> mSessionInfoMap;

    public interface InstallListener {
        void installBeginning();

        void installFailed(int i, String str);

        void installSucceeded();
    }

    public PackageInstallerImpl(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mPackageInstaller = applicationContext.getPackageManager().getPackageInstaller();
        this.mSessionInfoMap = new HashMap();
        List<PackageInstaller.SessionInfo> mySessions = this.mPackageInstaller.getMySessions();
        for (int i = 0; i < mySessions.size(); i++) {
            PackageInstaller.SessionInfo sessionInfo = mySessions.get(i);
            String packageName = sessionInfo.getAppPackageName();
            PackageInstaller.SessionInfo oldInfo = this.mSessionInfoMap.put(packageName, sessionInfo);
            if (oldInfo != null) {
                Log.w(TAG, "Multiple sessions for " + packageName + " found. Removing " + oldInfo.getSessionId() + " & keeping " + mySessions.get(i).getSessionId());
            }
        }
        this.mOpenSessionMap = new HashMap();
    }

    public void install(String packageName, ParcelFileDescriptor parcelFileDescriptor, InstallListener callback) {
        PackageInstaller.SessionInfo sessionInfo;
        try {
            PackageInstaller.SessionInfo sessionInfo2 = this.mSessionInfoMap.get(packageName);
            PackageInstaller.Session session = sessionInfo2 != null ? getSession(packageName) : null;
            if (session != null) {
                sessionInfo = sessionInfo2;
            } else {
                try {
                    innerCreateSession(packageName);
                    PackageInstaller.SessionInfo sessionInfo3 = this.mSessionInfoMap.get(packageName);
                    try {
                        session = this.mPackageInstaller.openSession(sessionInfo3.getSessionId());
                        this.mOpenSessionMap.put(packageName, session);
                        sessionInfo = sessionInfo3;
                    } catch (SecurityException se) {
                        Log.e(TAG, "Can't open session for " + packageName + ": " + se.getMessage());
                        callback.installFailed(InstallerConstants.ERROR_INSTALL_OPEN_SESSION, "Can't open session");
                        this.mSessionInfoMap.remove(packageName);
                        return;
                    }
                } catch (IOException ioe) {
                    Log.e(TAG, "Can't create session for " + packageName + ": " + ioe.getMessage());
                    callback.installFailed(InstallerConstants.ERROR_INSTALL_CREATE_SESSION, "Could not create session");
                    this.mSessionInfoMap.remove(packageName);
                    return;
                }
            }
            InstallTask task = new InstallTask(this.mContext, packageName, parcelFileDescriptor, callback, session, getCommitCallback(packageName, sessionInfo.getSessionId(), callback));
            task.execute();
            if (task.isError()) {
                cancelSession(sessionInfo.getSessionId(), packageName);
            }
        } catch (Exception e) {
            Log.e(TAG, "Unexpected exception while installing: " + packageName + ": " + e.getMessage());
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected exception while installing ");
            sb.append(packageName);
            callback.installFailed(InstallerConstants.ERROR_INSTALL_SESSION_EXCEPTION, sb.toString());
        }
    }

    private PackageInstaller.Session getSession(String packageName) {
        PackageInstaller.Session session = this.mOpenSessionMap.get(packageName);
        if (session != null) {
            try {
                session.getNames();
                return session;
            } catch (IOException ioe) {
                Log.e(TAG, "Stale open session for " + packageName + ": " + ioe.getMessage());
                this.mOpenSessionMap.remove(packageName);
            } catch (SecurityException se) {
                Log.e(TAG, "Stale open session for " + packageName + ": " + se.getMessage());
                this.mOpenSessionMap.remove(packageName);
            }
        }
        PackageInstaller.SessionInfo sessionInfo = this.mSessionInfoMap.get(packageName);
        if (sessionInfo == null) {
            return null;
        }
        try {
            PackageInstaller.Session session2 = this.mPackageInstaller.openSession(sessionInfo.getSessionId());
            this.mOpenSessionMap.put(packageName, session2);
            return session2;
        } catch (IOException ioe2) {
            Log.w(TAG, "IOException opening old session for " + ioe2.getMessage() + " - deleting info");
            this.mSessionInfoMap.remove(packageName);
            return null;
        } catch (SecurityException e) {
            Log.w(TAG, "SessionInfo was stale for " + packageName + " - deleting info");
            this.mSessionInfoMap.remove(packageName);
            return null;
        }
    }

    private void innerCreateSession(String packageName) throws IOException {
        if (this.mSessionInfoMap.containsKey(packageName)) {
            Log.w(TAG, "Creating session for " + packageName + " when one already exists");
            return;
        }
        PackageInstaller.SessionParams params = new PackageInstaller.SessionParams(1);
        params.setAppPackageName(packageName);
        int sessionId = this.mPackageInstaller.createSession(params);
        PackageInstaller.SessionInfo sessionInfo = this.mPackageInstaller.getSessionInfo(sessionId);
        this.mSessionInfoMap.put(packageName, sessionInfo);
    }

    private void cancelSession(int sessionId, String packageName) {
        closeSession(packageName);
        this.mSessionInfoMap.remove(packageName);
        try {
            this.mPackageInstaller.abandonSession(sessionId);
        } catch (SecurityException e) {
        }
    }

    private void closeSession(String packageName) {
        PackageInstaller.Session session = this.mOpenSessionMap.remove(packageName);
        if (session != null) {
            try {
                session.close();
            } catch (Exception e) {
                Log.w(TAG, "Unexpected error closing session for " + packageName + ": " + e.getMessage());
            }
        }
    }

    private IntentSender getCommitCallback(final String packageName, final int sessionId, final InstallListener callback) {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.android.packageinstaller.wear.PackageInstallerImpl.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                PackageInstallerImpl.this.mContext.unregisterReceiver(this);
                PackageInstallerImpl.this.handleCommitCallback(intent, packageName, sessionId, callback);
            }
        };
        String action = "com.android.vending.INTENT_PACKAGE_INSTALL_COMMIT." + packageName;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(action);
        this.mContext.registerReceiver(broadcastReceiver, intentFilter);
        Intent broadcastIntent = new Intent(action);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.mContext, packageName.hashCode(), broadcastIntent, 1207959552);
        return pendingIntent.getIntentSender();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCommitCallback(Intent intent, String packageName, int sessionId, InstallListener callback) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Installation of " + packageName + " finished with extras " + intent.getExtras());
        }
        String statusMessage = intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE");
        int status = intent.getIntExtra("android.content.pm.extra.STATUS", Integer.MIN_VALUE);
        if (status == 0) {
            cancelSession(sessionId, packageName);
            callback.installSucceeded();
            return;
        }
        if (status == -1) {
            cancelSession(sessionId, packageName);
            callback.installFailed(InstallerConstants.ERROR_INSTALL_USER_ACTION_REQUIRED, "Unexpected: user action required");
            return;
        }
        cancelSession(sessionId, packageName);
        int errorCode = getPackageManagerErrorCode(status);
        Log.e(TAG, "Error " + errorCode + " while installing " + packageName + ": " + statusMessage);
        callback.installFailed(errorCode, null);
    }

    private int getPackageManagerErrorCode(int status) {
        if (status == Integer.MIN_VALUE) {
            return InstallerConstants.ERROR_INSTALL_MALFORMED_BROADCAST;
        }
        int errorCode = (-500) - status;
        return errorCode;
    }
}
