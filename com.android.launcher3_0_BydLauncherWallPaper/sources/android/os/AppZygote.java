package android.os;

import android.content.pm.ApplicationInfo;
import android.util.Log;
import dalvik.system.VMRuntime;

/* JADX INFO: loaded from: classes2.dex */
public class AppZygote {
    private static final String LOG_TAG = "AppZygote";
    private final ApplicationInfo mAppInfo;
    private final Object mLock = new Object();
    private ChildZygoteProcess mZygote;
    private final int mZygoteUid;
    private final int mZygoteUidGidMax;
    private final int mZygoteUidGidMin;

    public AppZygote(ApplicationInfo applicationInfo, int i, int i2, int i3) {
        this.mAppInfo = applicationInfo;
        this.mZygoteUid = i;
        this.mZygoteUidGidMin = i2;
        this.mZygoteUidGidMax = i3;
    }

    public ChildZygoteProcess getProcess() {
        synchronized (this.mLock) {
            ChildZygoteProcess childZygoteProcess = this.mZygote;
            if (childZygoteProcess != null) {
                return childZygoteProcess;
            }
            connectToZygoteIfNeededLocked();
            return this.mZygote;
        }
    }

    public void stopZygote() {
        synchronized (this.mLock) {
            stopZygoteLocked();
        }
    }

    public ApplicationInfo getAppInfo() {
        return this.mAppInfo;
    }

    private void stopZygoteLocked() {
        ChildZygoteProcess childZygoteProcess = this.mZygote;
        if (childZygoteProcess != null) {
            childZygoteProcess.close();
            Process.killProcessGroup(this.mZygoteUid, this.mZygote.getPid());
            this.mZygote = null;
        }
    }

    private void connectToZygoteIfNeededLocked() {
        String str = this.mAppInfo.primaryCpuAbi != null ? this.mAppInfo.primaryCpuAbi : Build.SUPPORTED_ABIS[0];
        try {
            ZygoteProcess zygoteProcess = Process.ZYGOTE_PROCESS;
            String str2 = this.mAppInfo.processName + "_zygote";
            int i = this.mZygoteUid;
            ChildZygoteProcess childZygoteProcessStartChildZygote = zygoteProcess.startChildZygote("com.android.internal.os.AppZygoteInit", str2, i, i, null, 0, "app_zygote", str, str, VMRuntime.getInstructionSet(str), this.mZygoteUidGidMin, this.mZygoteUidGidMax);
            this.mZygote = childZygoteProcessStartChildZygote;
            ZygoteProcess.waitForConnectionToZygote(childZygoteProcessStartChildZygote.getPrimarySocketAddress());
            Log.i(LOG_TAG, "Starting application preload.");
            this.mZygote.preloadApp(this.mAppInfo, str);
            Log.i(LOG_TAG, "Application preload done.");
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error connecting to app zygote", e);
            stopZygoteLocked();
        }
    }
}
