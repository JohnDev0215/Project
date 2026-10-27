package android.net.util;

import android.net.INetd;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class NetdService {
    private static final long BASE_TIMEOUT_MS = 100;
    private static final long MAX_TIMEOUT_MS = 1000;
    private static final String TAG = NetdService.class.getSimpleName();

    public interface NetdCommand {
        void run(INetd iNetd) throws RemoteException;
    }

    public static INetd getInstance() {
        INetd netdInstance = INetd.Stub.asInterface(ServiceManager.getService("netd"));
        if (netdInstance == null) {
            Log.w(TAG, "WARNING: returning null INetd instance.");
        }
        return netdInstance;
    }

    public static INetd get(long maxTimeoutMs) {
        long stop;
        if (maxTimeoutMs == 0) {
            return getInstance();
        }
        if (maxTimeoutMs > 0) {
            stop = SystemClock.elapsedRealtime() + maxTimeoutMs;
        } else {
            stop = Long.MAX_VALUE;
        }
        long timeoutMs = 0;
        while (true) {
            INetd netdInstance = getInstance();
            if (netdInstance != null) {
                return netdInstance;
            }
            long remaining = stop - SystemClock.elapsedRealtime();
            if (remaining > 0) {
                timeoutMs = Math.min(Math.min(BASE_TIMEOUT_MS + timeoutMs, MAX_TIMEOUT_MS), remaining);
                try {
                    Thread.sleep(timeoutMs);
                } catch (InterruptedException e) {
                }
            } else {
                return null;
            }
        }
    }

    public static INetd get() {
        return get(-1L);
    }

    public static void run(NetdCommand cmd) {
        while (true) {
            try {
                cmd.run(get());
                return;
            } catch (RemoteException re) {
                Log.e(TAG, "error communicating with netd: " + re);
            }
        }
    }
}
