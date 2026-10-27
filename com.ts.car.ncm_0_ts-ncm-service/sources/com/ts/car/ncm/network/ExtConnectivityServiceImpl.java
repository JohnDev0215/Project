package com.ts.car.ncm.network;

import android.content.Context;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.PrintWriterPrinter;
import com.android.internal.util.IndentingPrintWriter;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import ts.car.ncm.network.IExtConnectivityManager;
import ts.car.ncm.network.IExtConnectivityServiceListener;
import ts.car.ncm.network.IfaceInfo;

/* JADX INFO: loaded from: classes.dex */
public class ExtConnectivityServiceImpl extends IExtConnectivityManager.Stub {
    private static final String TAG = ExtConnectivityServiceImpl.class.getSimpleName();
    private final Context mContext;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private AtomicBoolean mStarted = new AtomicBoolean(false);
    private ExtNetworkTracker mTracker;

    protected ExtConnectivityServiceImpl(Context context) {
        Log.i(TAG, TAG + " create");
        this.mContext = context;
    }

    protected void enforceAccessPermission() {
        this.mContext.enforceCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE", "ACCESS_NETWORK_STATE require");
    }

    protected void enforceChangePermission() {
        this.mContext.enforceCallingOrSelfPermission("android.permission.CHANGE_NETWORK_STATE", "CHANGE_NETWORK_STATE require");
    }

    protected void start() {
        Log.i(TAG, "Start ExtConnectivityService thread");
        HandlerThread handlerThread = new HandlerThread("ExtConnectivityServiceThread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        ExtNetworkTracker extNetworkTracker = new ExtNetworkTracker(this.mContext, this.mHandler);
        this.mTracker = extNetworkTracker;
        extNetworkTracker.start();
        this.mStarted.set(true);
    }

    protected void stop() {
        Log.i(TAG, "Stop ExtNetwork service");
        this.mTracker.stop();
        this.mHandler.getLooper().quit();
        this.mStarted.set(false);
    }

    public IfaceInfo getIfaceInfo(String ifname) {
        enforceAccessPermission();
        return this.mTracker.getIfaceInfo(ifname);
    }

    public List<IfaceInfo> getIfaceInfos() {
        enforceAccessPermission();
        return this.mTracker.getIfaceInfos();
    }

    public boolean enableMobileNetwork(boolean enable) {
        Log.d(TAG, "set network enable: " + enable);
        enforceChangePermission();
        return this.mTracker.enableMobileNetwork(enable);
    }

    public boolean enableInterface(String ifaceName, boolean enable) {
        Log.d(TAG, "set network interface: " + ifaceName + " enable: " + enable);
        enforceChangePermission();
        if (ifaceName == null || ifaceName.trim().length() == 0) {
            Log.e(TAG, "Invaild interface name:" + ifaceName);
            return false;
        }
        return this.mTracker.enableInterface(ifaceName, enable);
    }

    public boolean getMobileNetworkEnable() {
        enforceAccessPermission();
        return this.mTracker.getMobileNetworkEnable();
    }

    public void registerListener(IExtConnectivityServiceListener listener) {
        Log.d(TAG, "add Listener");
        if (listener == null) {
            throw new IllegalArgumentException("listener must not be null");
        }
        this.mTracker.registerListener(listener);
    }

    public void unregisterListener(IExtConnectivityServiceListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("listener must not be null");
        }
        this.mTracker.unregisterListener(listener);
    }

    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        IndentingPrintWriter pw = new IndentingPrintWriter(writer, "  ");
        if (this.mContext.checkCallingOrSelfPermission("android.permission.DUMP") != 0) {
            pw.println("Permission Denial: can't dump ExtConnectivityService from pid=" + Binder.getCallingPid() + ", uid=" + Binder.getCallingUid());
            return;
        }
        pw.println("Current Ethernet state: ");
        pw.increaseIndent();
        pw.decreaseIndent();
        pw.println("Handler:");
        pw.increaseIndent();
        this.mHandler.dump(new PrintWriterPrinter(pw), "ExtConnectivityServiceImpl");
        pw.decreaseIndent();
    }
}
