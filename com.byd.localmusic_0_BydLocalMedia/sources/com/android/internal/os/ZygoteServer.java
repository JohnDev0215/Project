package com.android.internal.os;

import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.os.SystemClock;
import android.os.Trace;
import android.system.ErrnoException;
import android.system.Os;
import android.util.Log;
import dalvik.system.ZygoteHooks;
import java.io.FileDescriptor;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
class ZygoteServer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int INVALID_TIMESTAMP = -1;
    public static final String TAG = "ZygoteServer";
    private static final String USAP_POOL_REFILL_DELAY_MS_DEFAULT = "3000";
    private static final String USAP_POOL_SIZE_MAX_DEFAULT = "10";
    private static final int USAP_POOL_SIZE_MAX_LIMIT = 100;
    private static final String USAP_POOL_SIZE_MIN_DEFAULT = "1";
    private static final int USAP_POOL_SIZE_MIN_LIMIT = 1;
    private boolean mCloseSocketFd;
    private boolean mIsFirstPropertyCheck;
    private boolean mIsForkChild;
    private long mLastPropCheckTimestamp;
    private boolean mUsapPoolEnabled;
    private final FileDescriptor mUsapPoolEventFD;
    private UsapPoolRefillAction mUsapPoolRefillAction;
    private int mUsapPoolRefillDelayMs;
    private int mUsapPoolRefillThreshold;
    private long mUsapPoolRefillTriggerTimestamp;
    private int mUsapPoolSizeMax;
    private int mUsapPoolSizeMin;
    private final LocalServerSocket mUsapPoolSocket;
    private final boolean mUsapPoolSupported;
    private LocalServerSocket mZygoteSocket;

    private enum UsapPoolRefillAction {
        DELAYED,
        IMMEDIATE,
        NONE
    }

    ZygoteServer() {
        this.mUsapPoolEnabled = false;
        this.mUsapPoolSizeMax = 0;
        this.mUsapPoolSizeMin = 0;
        this.mUsapPoolRefillThreshold = 0;
        this.mUsapPoolRefillDelayMs = -1;
        this.mIsFirstPropertyCheck = true;
        this.mLastPropCheckTimestamp = 0L;
        this.mUsapPoolEventFD = null;
        this.mZygoteSocket = null;
        this.mUsapPoolSocket = null;
        this.mUsapPoolSupported = false;
    }

    ZygoteServer(boolean z) {
        this.mUsapPoolEnabled = false;
        this.mUsapPoolSizeMax = 0;
        this.mUsapPoolSizeMin = 0;
        this.mUsapPoolRefillThreshold = 0;
        this.mUsapPoolRefillDelayMs = -1;
        this.mIsFirstPropertyCheck = true;
        this.mLastPropCheckTimestamp = 0L;
        this.mUsapPoolEventFD = Zygote.getUsapPoolEventFD();
        if (z) {
            this.mZygoteSocket = Zygote.createManagedSocketFromInitSocket(Zygote.PRIMARY_SOCKET_NAME);
            this.mUsapPoolSocket = Zygote.createManagedSocketFromInitSocket(Zygote.USAP_POOL_PRIMARY_SOCKET_NAME);
        } else {
            this.mZygoteSocket = Zygote.createManagedSocketFromInitSocket(Zygote.SECONDARY_SOCKET_NAME);
            this.mUsapPoolSocket = Zygote.createManagedSocketFromInitSocket(Zygote.USAP_POOL_SECONDARY_SOCKET_NAME);
        }
        this.mUsapPoolSupported = true;
        fetchUsapPoolPolicyProps();
    }

    void setForkChild() {
        this.mIsForkChild = true;
    }

    public boolean isUsapPoolEnabled() {
        return this.mUsapPoolEnabled;
    }

    void registerServerSocketAtAbstractName(String str) {
        if (this.mZygoteSocket == null) {
            try {
                this.mZygoteSocket = new LocalServerSocket(str);
                this.mCloseSocketFd = false;
            } catch (IOException e) {
                throw new RuntimeException("Error binding to abstract socket '" + str + "'", e);
            }
        }
    }

    private ZygoteConnection acceptCommandPeer(String str) {
        try {
            return createNewConnection(this.mZygoteSocket.accept(), str);
        } catch (IOException e) {
            throw new RuntimeException("IOException during accept()", e);
        }
    }

    protected ZygoteConnection createNewConnection(LocalSocket localSocket, String str) throws IOException {
        return new ZygoteConnection(localSocket, str);
    }

    void closeServerSocket() {
        try {
            if (this.mZygoteSocket != null) {
                FileDescriptor fileDescriptor = this.mZygoteSocket.getFileDescriptor();
                this.mZygoteSocket.close();
                if (fileDescriptor != null && this.mCloseSocketFd) {
                    Os.close(fileDescriptor);
                }
            }
        } catch (ErrnoException e) {
            Log.e(TAG, "Zygote:  error closing descriptor", e);
        } catch (IOException e2) {
            Log.e(TAG, "Zygote:  error closing sockets", e2);
        }
        this.mZygoteSocket = null;
    }

    FileDescriptor getZygoteSocketFileDescriptor() {
        return this.mZygoteSocket.getFileDescriptor();
    }

    private void fetchUsapPoolPolicyProps() {
        if (this.mUsapPoolSupported) {
            String configurationProperty = Zygote.getConfigurationProperty(ZygoteConfig.USAP_POOL_SIZE_MAX, USAP_POOL_SIZE_MAX_DEFAULT);
            if (!configurationProperty.isEmpty()) {
                this.mUsapPoolSizeMax = Integer.min(Integer.parseInt(configurationProperty), 100);
            }
            String configurationProperty2 = Zygote.getConfigurationProperty(ZygoteConfig.USAP_POOL_SIZE_MIN, USAP_POOL_SIZE_MIN_DEFAULT);
            if (!configurationProperty2.isEmpty()) {
                this.mUsapPoolSizeMin = Integer.max(Integer.parseInt(configurationProperty2), 1);
            }
            String configurationProperty3 = Zygote.getConfigurationProperty(ZygoteConfig.USAP_POOL_REFILL_THRESHOLD, Integer.toString(this.mUsapPoolSizeMax / 2));
            if (!configurationProperty3.isEmpty()) {
                this.mUsapPoolRefillThreshold = Integer.min(Integer.parseInt(configurationProperty3), this.mUsapPoolSizeMax);
            }
            String configurationProperty4 = Zygote.getConfigurationProperty(ZygoteConfig.USAP_POOL_REFILL_DELAY_MS, USAP_POOL_REFILL_DELAY_MS_DEFAULT);
            if (!configurationProperty4.isEmpty()) {
                this.mUsapPoolRefillDelayMs = Integer.parseInt(configurationProperty4);
            }
            if (this.mUsapPoolSizeMin >= this.mUsapPoolSizeMax) {
                Log.w(TAG, "The max size of the USAP pool must be greater than the minimum size.  Restoring default values.");
                this.mUsapPoolSizeMax = Integer.parseInt(USAP_POOL_SIZE_MAX_DEFAULT);
                this.mUsapPoolSizeMin = Integer.parseInt(USAP_POOL_SIZE_MIN_DEFAULT);
                this.mUsapPoolRefillThreshold = this.mUsapPoolSizeMax / 2;
            }
        }
    }

    private void fetchUsapPoolPolicyPropsWithMinInterval() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (this.mIsFirstPropertyCheck || jElapsedRealtime - this.mLastPropCheckTimestamp >= 60000) {
            this.mIsFirstPropertyCheck = false;
            this.mLastPropCheckTimestamp = jElapsedRealtime;
            fetchUsapPoolPolicyProps();
        }
    }

    private void fetchUsapPoolPolicyPropsIfUnfetched() {
        if (this.mIsFirstPropertyCheck) {
            this.mIsFirstPropertyCheck = false;
            fetchUsapPoolPolicyProps();
        }
    }

    Runnable fillUsapPool(int[] iArr, boolean z) {
        int i;
        Runnable runnableForkUsap;
        Trace.traceBegin(64L, "Zygote:FillUsapPool");
        fetchUsapPoolPolicyPropsIfUnfetched();
        int usapPoolCount = Zygote.getUsapPoolCount();
        if (z) {
            i = this.mUsapPoolSizeMin - usapPoolCount;
            Log.i(Zygote.PRIMARY_SOCKET_NAME, "Priority USAP Pool refill. New USAPs: " + i);
        } else {
            i = this.mUsapPoolSizeMax - usapPoolCount;
            Log.i(Zygote.PRIMARY_SOCKET_NAME, "Delayed USAP Pool refill. New USAPs: " + i);
        }
        ZygoteHooks.preFork();
        do {
            i--;
            if (i >= 0) {
                runnableForkUsap = Zygote.forkUsap(this.mUsapPoolSocket, iArr, z);
            } else {
                ZygoteHooks.postForkCommon();
                resetUsapRefillState();
                Trace.traceEnd(64L);
                return null;
            }
        } while (runnableForkUsap == null);
        return runnableForkUsap;
    }

    Runnable setUsapPoolStatus(boolean z, LocalSocket localSocket) {
        if (!this.mUsapPoolSupported) {
            Log.w(TAG, "Attempting to enable a USAP pool for a Zygote that doesn't support it.");
            return null;
        }
        if (this.mUsapPoolEnabled == z) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("USAP Pool status change: ");
        sb.append(z ? "ENABLED" : "DISABLED");
        Log.i(TAG, sb.toString());
        this.mUsapPoolEnabled = z;
        if (z) {
            return fillUsapPool(new int[]{localSocket.getFileDescriptor().getInt$()}, false);
        }
        Zygote.emptyUsapPool();
        return null;
    }

    void resetUsapRefillState() {
        this.mUsapPoolRefillAction = UsapPoolRefillAction.NONE;
        this.mUsapPoolRefillTriggerTimestamp = -1L;
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x0221 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01f4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    java.lang.Runnable runSelectLoop(java.lang.String r18) {
        /*
            Method dump skipped, instruction units count: 559
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.os.ZygoteServer.runSelectLoop(java.lang.String):java.lang.Runnable");
    }
}
