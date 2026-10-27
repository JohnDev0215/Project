package com.android.launcher3.compat;

import android.content.Context;
import android.content.pm.PackageInstaller;
import android.os.Handler;
import android.os.Process;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.SparseArray;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.Utilities;
import com.android.launcher3.compat.PackageInstallerCompat;
import com.android.launcher3.icons.IconCache;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class PackageInstallerCompatVL extends PackageInstallerCompat {
    private static final boolean DEBUG = false;
    private final Context mAppContext;
    private final IconCache mCache;
    private final PackageInstaller.SessionCallback mCallback;
    final PackageInstaller mInstaller;
    private final LauncherAppsCompat mLauncherApps;
    private final Handler mWorker;
    final SparseArray<String> mActiveSessions = new SparseArray<>();
    private final HashMap<String, Boolean> mSessionVerifiedMap = new HashMap<>();

    PackageInstallerCompatVL(Context context) {
        PackageInstaller.SessionCallback sessionCallback = new PackageInstaller.SessionCallback() { // from class: com.android.launcher3.compat.PackageInstallerCompatVL.1
            @Override // android.content.pm.PackageInstaller.SessionCallback
            public void onActiveChanged(int sessionId, boolean active) {
            }

            @Override // android.content.pm.PackageInstaller.SessionCallback
            public void onCreated(int sessionId) {
                pushSessionDisplayToLauncher(sessionId);
            }

            @Override // android.content.pm.PackageInstaller.SessionCallback
            public void onFinished(int sessionId, boolean success) {
                String str = PackageInstallerCompatVL.this.mActiveSessions.get(sessionId);
                PackageInstallerCompatVL.this.mActiveSessions.remove(sessionId);
                if (str != null) {
                    PackageInstallerCompatVL.this.sendUpdate(PackageInstallerCompat.PackageInstallInfo.fromState(success ? 0 : 2, str));
                }
            }

            @Override // android.content.pm.PackageInstaller.SessionCallback
            public void onProgressChanged(int sessionId, float progress) {
                PackageInstallerCompatVL packageInstallerCompatVL = PackageInstallerCompatVL.this;
                PackageInstaller.SessionInfo sessionInfoVerify = packageInstallerCompatVL.verify(packageInstallerCompatVL.mInstaller.getSessionInfo(sessionId));
                if (sessionInfoVerify == null || sessionInfoVerify.getAppPackageName() == null) {
                    return;
                }
                PackageInstallerCompatVL.this.sendUpdate(PackageInstallerCompat.PackageInstallInfo.fromInstallingState(sessionInfoVerify));
            }

            @Override // android.content.pm.PackageInstaller.SessionCallback
            public void onBadgingChanged(int sessionId) {
                pushSessionDisplayToLauncher(sessionId);
            }

            private PackageInstaller.SessionInfo pushSessionDisplayToLauncher(int sessionId) {
                PackageInstallerCompatVL packageInstallerCompatVL = PackageInstallerCompatVL.this;
                PackageInstaller.SessionInfo sessionInfoVerify = packageInstallerCompatVL.verify(packageInstallerCompatVL.mInstaller.getSessionInfo(sessionId));
                if (sessionInfoVerify == null || sessionInfoVerify.getAppPackageName() == null) {
                    return null;
                }
                PackageInstallerCompatVL.this.mActiveSessions.put(sessionId, sessionInfoVerify.getAppPackageName());
                PackageInstallerCompatVL.this.addSessionInfoToCache(sessionInfoVerify, Process.myUserHandle());
                LauncherAppState instanceNoCreate = LauncherAppState.getInstanceNoCreate();
                if (instanceNoCreate != null) {
                    instanceNoCreate.getModel().updateSessionDisplayInfo(sessionInfoVerify.getAppPackageName());
                }
                return sessionInfoVerify;
            }
        };
        this.mCallback = sessionCallback;
        this.mAppContext = context.getApplicationContext();
        PackageInstaller packageInstaller = context.getPackageManager().getPackageInstaller();
        this.mInstaller = packageInstaller;
        this.mCache = LauncherAppState.getInstance(context).getIconCache();
        Handler handler = new Handler(LauncherModel.getWorkerLooper());
        this.mWorker = handler;
        packageInstaller.registerSessionCallback(sessionCallback, handler);
        this.mLauncherApps = LauncherAppsCompat.getInstance(context);
    }

    @Override // com.android.launcher3.compat.PackageInstallerCompat
    public HashMap<String, PackageInstaller.SessionInfo> updateAndGetActiveSessionCache() {
        HashMap<String, PackageInstaller.SessionInfo> map = new HashMap<>();
        UserHandle userHandleMyUserHandle = Process.myUserHandle();
        for (PackageInstaller.SessionInfo sessionInfo : getAllVerifiedSessions()) {
            addSessionInfoToCache(sessionInfo, userHandleMyUserHandle);
            if (sessionInfo.getAppPackageName() != null) {
                map.put(sessionInfo.getAppPackageName(), sessionInfo);
                this.mActiveSessions.put(sessionInfo.getSessionId(), sessionInfo.getAppPackageName());
            }
        }
        return map;
    }

    void addSessionInfoToCache(PackageInstaller.SessionInfo info, UserHandle user) {
        String appPackageName = info.getAppPackageName();
        if (appPackageName != null) {
            this.mCache.cachePackageInstallInfo(appPackageName, user, info.getAppIcon(), info.getAppLabel());
        }
    }

    @Override // com.android.launcher3.compat.PackageInstallerCompat
    public void onStop() {
        this.mInstaller.unregisterSessionCallback(this.mCallback);
    }

    void sendUpdate(PackageInstallerCompat.PackageInstallInfo info) {
        LauncherAppState instanceNoCreate = LauncherAppState.getInstanceNoCreate();
        if (instanceNoCreate != null) {
            instanceNoCreate.getModel().setPackageState(info);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PackageInstaller.SessionInfo verify(PackageInstaller.SessionInfo sessionInfo) {
        if (sessionInfo == null || sessionInfo.getInstallerPackageName() == null || TextUtils.isEmpty(sessionInfo.getAppPackageName())) {
            return null;
        }
        String installerPackageName = sessionInfo.getInstallerPackageName();
        synchronized (this.mSessionVerifiedMap) {
            if (!this.mSessionVerifiedMap.containsKey(installerPackageName)) {
                boolean z = true;
                if (LauncherAppsCompat.getInstance(this.mAppContext).getApplicationInfo(installerPackageName, 1, Process.myUserHandle()) == null) {
                    z = false;
                }
                this.mSessionVerifiedMap.put(installerPackageName, Boolean.valueOf(z));
            }
        }
        if (this.mSessionVerifiedMap.get(installerPackageName).booleanValue()) {
            return sessionInfo;
        }
        return null;
    }

    @Override // com.android.launcher3.compat.PackageInstallerCompat
    public List<PackageInstaller.SessionInfo> getAllVerifiedSessions() {
        List<PackageInstaller.SessionInfo> allSessions;
        if (Utilities.ATLEAST_Q) {
            allSessions = this.mLauncherApps.getAllPackageInstallerSessions();
        } else {
            allSessions = this.mInstaller.getAllSessions();
        }
        ArrayList arrayList = new ArrayList(allSessions);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (verify((PackageInstaller.SessionInfo) it.next()) == null) {
                it.remove();
            }
        }
        return arrayList;
    }
}
