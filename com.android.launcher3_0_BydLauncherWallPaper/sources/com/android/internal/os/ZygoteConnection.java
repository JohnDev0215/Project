package com.android.internal.os;

import android.content.pm.ApplicationInfo;
import android.net.Credentials;
import android.net.LocalSocket;
import android.os.Parcel;
import android.os.Process;
import android.os.Trace;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import dalvik.system.VMRuntime;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import libcore.io.IoUtils;

/* JADX INFO: loaded from: classes4.dex */
class ZygoteConnection {
    private static final String TAG = "Zygote";
    private final String abiList;
    private boolean isEof;
    private final LocalSocket mSocket;
    private final DataOutputStream mSocketOutStream;
    private final BufferedReader mSocketReader;
    private final Credentials peer;

    protected boolean canPreloadApp() {
        return false;
    }

    ZygoteConnection(LocalSocket localSocket, String str) throws IOException {
        this.mSocket = localSocket;
        this.abiList = str;
        this.mSocketOutStream = new DataOutputStream(localSocket.getOutputStream());
        this.mSocketReader = new BufferedReader(new InputStreamReader(localSocket.getInputStream()), 256);
        localSocket.setSoTimeout(1000);
        try {
            this.peer = localSocket.getPeerCredentials();
            this.isEof = false;
        } catch (IOException e) {
            Log.e(TAG, "Cannot read peer credentials", e);
            throw e;
        }
    }

    FileDescriptor getFileDescriptor() {
        return this.mSocket.getFileDescriptor();
    }

    Runnable processOneCommand(ZygoteServer zygoteServer) throws Throwable {
        FileDescriptor fileDescriptor;
        FileDescriptor fileDescriptor2;
        int[] iArr;
        try {
            String[] argumentList = Zygote.readArgumentList(this.mSocketReader);
            FileDescriptor fileDescriptor3 = null;
            if (argumentList == null) {
                this.isEof = true;
                return null;
            }
            ZygoteArguments zygoteArguments = new ZygoteArguments(argumentList);
            if (zygoteArguments.mBootCompleted) {
                handleBootCompleted();
                return null;
            }
            if (zygoteArguments.mAbiListQuery) {
                handleAbiListQuery();
                return null;
            }
            if (zygoteArguments.mPidQuery) {
                handlePidQuery();
                return null;
            }
            if (zygoteArguments.mUsapPoolStatusSpecified) {
                return handleUsapPoolStatusChange(zygoteServer, zygoteArguments.mUsapPoolEnabled);
            }
            if (zygoteArguments.mPreloadDefault) {
                handlePreload();
                return null;
            }
            if (zygoteArguments.mPreloadPackage != null) {
                handlePreloadPackage(zygoteArguments.mPreloadPackage, zygoteArguments.mPreloadPackageLibs, zygoteArguments.mPreloadPackageLibFileName, zygoteArguments.mPreloadPackageCacheKey);
                return null;
            }
            if (canPreloadApp() && zygoteArguments.mPreloadApp != null) {
                byte[] bArrDecode = Base64.getDecoder().decode(zygoteArguments.mPreloadApp);
                Parcel parcelObtain = Parcel.obtain();
                parcelObtain.unmarshall(bArrDecode, 0, bArrDecode.length);
                parcelObtain.setDataPosition(0);
                ApplicationInfo applicationInfoCreateFromParcel = ApplicationInfo.CREATOR.createFromParcel(parcelObtain);
                parcelObtain.recycle();
                if (applicationInfoCreateFromParcel != null) {
                    handlePreloadApp(applicationInfoCreateFromParcel);
                    return null;
                }
                throw new IllegalArgumentException("Failed to deserialize --preload-app");
            }
            if (zygoteArguments.mApiBlacklistExemptions != null) {
                return handleApiBlacklistExemptions(zygoteServer, zygoteArguments.mApiBlacklistExemptions);
            }
            if (zygoteArguments.mHiddenApiAccessLogSampleRate != -1 || zygoteArguments.mHiddenApiAccessStatslogSampleRate != -1) {
                return handleHiddenApiAccessLogSampleRate(zygoteServer, zygoteArguments.mHiddenApiAccessLogSampleRate, zygoteArguments.mHiddenApiAccessStatslogSampleRate);
            }
            if (zygoteArguments.mPermittedCapabilities != 0 || zygoteArguments.mEffectiveCapabilities != 0) {
                throw new ZygoteSecurityException("Client may not specify capabilities: permitted=0x" + Long.toHexString(zygoteArguments.mPermittedCapabilities) + ", effective=0x" + Long.toHexString(zygoteArguments.mEffectiveCapabilities));
            }
            Zygote.applyUidSecurityPolicy(zygoteArguments, this.peer);
            Zygote.applyInvokeWithSecurityPolicy(zygoteArguments, this.peer);
            Zygote.applyDebuggerSystemProperty(zygoteArguments);
            Zygote.applyInvokeWithSystemProperty(zygoteArguments);
            int[][] iArr2 = zygoteArguments.mRLimits != null ? (int[][]) zygoteArguments.mRLimits.toArray(Zygote.INT_ARRAY_2D) : null;
            if (zygoteArguments.mInvokeWith != null) {
                try {
                    FileDescriptor[] fileDescriptorArrPipe2 = Os.pipe2(OsConstants.O_CLOEXEC);
                    FileDescriptor fileDescriptor4 = fileDescriptorArrPipe2[1];
                    fileDescriptor = fileDescriptorArrPipe2[0];
                    Os.fcntlInt(fileDescriptor4, OsConstants.F_SETFD, 0);
                    fileDescriptor2 = fileDescriptor4;
                    iArr = new int[]{fileDescriptor4.getInt$(), fileDescriptor.getInt$()};
                } catch (ErrnoException e) {
                    throw new IllegalStateException("Unable to set up pipe for invoke-with", e);
                }
            } else {
                fileDescriptor = null;
                fileDescriptor2 = null;
                iArr = null;
            }
            int[] iArr3 = {-1, -1};
            FileDescriptor fileDescriptor5 = this.mSocket.getFileDescriptor();
            if (fileDescriptor5 != null) {
                iArr3[0] = fileDescriptor5.getInt$();
            }
            FileDescriptor zygoteSocketFileDescriptor = zygoteServer.getZygoteSocketFileDescriptor();
            if (zygoteSocketFileDescriptor != null) {
                iArr3[1] = zygoteSocketFileDescriptor.getInt$();
            }
            FileDescriptor fileDescriptor6 = fileDescriptor2;
            int iForkAndSpecialize = Zygote.forkAndSpecialize(zygoteArguments.mUid, zygoteArguments.mGid, zygoteArguments.mGids, zygoteArguments.mRuntimeFlags, iArr2, zygoteArguments.mMountExternal, zygoteArguments.mSeInfo, zygoteArguments.mNiceName, iArr3, iArr, zygoteArguments.mStartChildZygote, zygoteArguments.mInstructionSet, zygoteArguments.mAppDataDir, zygoteArguments.mIsTopApp, zygoteArguments.mPkgDataInfoList, zygoteArguments.mWhitelistedDataInfoList, zygoteArguments.mBindMountAppDataDirs, zygoteArguments.mBindMountAppStorageDirs);
            try {
                if (iForkAndSpecialize == 0) {
                    zygoteServer.setForkChild();
                    zygoteServer.closeServerSocket();
                    IoUtils.closeQuietly(fileDescriptor);
                    try {
                        Runnable runnableHandleChildProc = handleChildProc(zygoteArguments, fileDescriptor6, zygoteArguments.mStartChildZygote);
                        IoUtils.closeQuietly(fileDescriptor6);
                        IoUtils.closeQuietly((FileDescriptor) null);
                        return runnableHandleChildProc;
                    } catch (Throwable th) {
                        th = th;
                        fileDescriptor = null;
                        fileDescriptor3 = fileDescriptor6;
                        IoUtils.closeQuietly(fileDescriptor3);
                        IoUtils.closeQuietly(fileDescriptor);
                        throw th;
                    }
                }
                IoUtils.closeQuietly(fileDescriptor6);
                try {
                    handleParentProc(iForkAndSpecialize, fileDescriptor);
                    IoUtils.closeQuietly((FileDescriptor) null);
                    IoUtils.closeQuietly(fileDescriptor);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    IoUtils.closeQuietly(fileDescriptor3);
                    IoUtils.closeQuietly(fileDescriptor);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e2) {
            throw new IllegalStateException("IOException on command socket", e2);
        }
    }

    private void handleAbiListQuery() {
        try {
            byte[] bytes = this.abiList.getBytes(StandardCharsets.US_ASCII);
            this.mSocketOutStream.writeInt(bytes.length);
            this.mSocketOutStream.write(bytes);
        } catch (IOException e) {
            throw new IllegalStateException("Error writing to command socket", e);
        }
    }

    private void handlePidQuery() {
        try {
            byte[] bytes = String.valueOf(Process.myPid()).getBytes(StandardCharsets.US_ASCII);
            this.mSocketOutStream.writeInt(bytes.length);
            this.mSocketOutStream.write(bytes);
        } catch (IOException e) {
            throw new IllegalStateException("Error writing to command socket", e);
        }
    }

    private void handleBootCompleted() {
        try {
            this.mSocketOutStream.writeInt(0);
            VMRuntime.bootCompleted();
        } catch (IOException e) {
            throw new IllegalStateException("Error writing to command socket", e);
        }
    }

    private void handlePreload() {
        try {
            if (isPreloadComplete()) {
                this.mSocketOutStream.writeInt(1);
            } else {
                preload();
                this.mSocketOutStream.writeInt(0);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Error writing to command socket", e);
        }
    }

    private Runnable stateChangeWithUsapPoolReset(ZygoteServer zygoteServer, Runnable runnable) {
        try {
            if (zygoteServer.isUsapPoolEnabled()) {
                Log.i(TAG, "Emptying USAP Pool due to state change.");
                Zygote.emptyUsapPool();
            }
            runnable.run();
            if (zygoteServer.isUsapPoolEnabled()) {
                Runnable runnableFillUsapPool = zygoteServer.fillUsapPool(new int[]{this.mSocket.getFileDescriptor().getInt$()}, false);
                if (runnableFillUsapPool != null) {
                    zygoteServer.setForkChild();
                    return runnableFillUsapPool;
                }
                Log.i(TAG, "Finished refilling USAP Pool after state change.");
            }
            this.mSocketOutStream.writeInt(0);
            return null;
        } catch (IOException e) {
            throw new IllegalStateException("Error writing to command socket", e);
        }
    }

    private Runnable handleApiBlacklistExemptions(ZygoteServer zygoteServer, final String[] strArr) {
        return stateChangeWithUsapPoolReset(zygoteServer, new Runnable() { // from class: com.android.internal.os.-$$Lambda$ZygoteConnection$ceMLgCFeJphPjBdmOi8kdozOFFw
            @Override // java.lang.Runnable
            public final void run() {
                ZygoteInit.setApiBlacklistExemptions(strArr);
            }
        });
    }

    private Runnable handleUsapPoolStatusChange(ZygoteServer zygoteServer, boolean z) {
        try {
            Runnable usapPoolStatus = zygoteServer.setUsapPoolStatus(z, this.mSocket);
            if (usapPoolStatus == null) {
                this.mSocketOutStream.writeInt(0);
            } else {
                zygoteServer.setForkChild();
            }
            return usapPoolStatus;
        } catch (IOException e) {
            throw new IllegalStateException("Error writing to command socket", e);
        }
    }

    private Runnable handleHiddenApiAccessLogSampleRate(ZygoteServer zygoteServer, final int i, final int i2) {
        return stateChangeWithUsapPoolReset(zygoteServer, new Runnable() { // from class: com.android.internal.os.-$$Lambda$ZygoteConnection$2i8jK35XOSWWkLpo-i-oMUccQ58
            @Override // java.lang.Runnable
            public final void run() {
                ZygoteConnection.lambda$handleHiddenApiAccessLogSampleRate$1(i, i2);
            }
        });
    }

    static /* synthetic */ void lambda$handleHiddenApiAccessLogSampleRate$1(int i, int i2) {
        ZygoteInit.setHiddenApiAccessLogSampleRate(Math.max(i, i2));
        StatsdHiddenApiUsageLogger.setHiddenApiAccessLogSampleRates(i, i2);
        ZygoteInit.setHiddenApiUsageLogger(StatsdHiddenApiUsageLogger.getInstance());
    }

    protected void preload() {
        ZygoteInit.lazyPreload();
    }

    protected boolean isPreloadComplete() {
        return ZygoteInit.isPreloadComplete();
    }

    protected DataOutputStream getSocketOutputStream() {
        return this.mSocketOutStream;
    }

    protected void handlePreloadPackage(String str, String str2, String str3, String str4) {
        throw new RuntimeException("Zygote does not support package preloading");
    }

    protected void handlePreloadApp(ApplicationInfo applicationInfo) {
        throw new RuntimeException("Zygote does not support app preloading");
    }

    void closeSocket() {
        try {
            this.mSocket.close();
        } catch (IOException e) {
            Log.e(TAG, "Exception while closing command socket in parent", e);
        }
    }

    boolean isClosedByPeer() {
        return this.isEof;
    }

    private Runnable handleChildProc(ZygoteArguments zygoteArguments, FileDescriptor fileDescriptor, boolean z) {
        closeSocket();
        Zygote.setAppProcessName(zygoteArguments, TAG);
        Trace.traceEnd(64L);
        if (zygoteArguments.mInvokeWith != null) {
            WrapperInit.execApplication(zygoteArguments.mInvokeWith, zygoteArguments.mNiceName, zygoteArguments.mTargetSdkVersion, VMRuntime.getCurrentInstructionSet(), fileDescriptor, zygoteArguments.mRemainingArgs);
            throw new IllegalStateException("WrapperInit.execApplication unexpectedly returned");
        }
        if (!z) {
            return ZygoteInit.zygoteInit(zygoteArguments.mTargetSdkVersion, zygoteArguments.mDisabledCompatChanges, zygoteArguments.mRemainingArgs, null);
        }
        return ZygoteInit.childZygoteInit(zygoteArguments.mTargetSdkVersion, zygoteArguments.mRemainingArgs, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00c4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void handleParentProc(int r20, java.io.FileDescriptor r21) {
        /*
            Method dump skipped, instruction units count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.os.ZygoteConnection.handleParentProc(int, java.io.FileDescriptor):void");
    }

    private void setChildPgid(int i) {
        try {
            Os.setpgid(i, Os.getpgid(this.peer.getPid()));
        } catch (ErrnoException unused) {
            Log.i(TAG, "Zygote: setpgid failed. This is normal if peer is not in our session");
        }
    }
}
