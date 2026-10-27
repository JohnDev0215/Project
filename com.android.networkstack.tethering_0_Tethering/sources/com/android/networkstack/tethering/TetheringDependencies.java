package com.android.networkstack.tethering;

import android.app.usage.NetworkStatsManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.INetd;
import android.net.NetworkRequest;
import android.net.ip.IpServer;
import android.net.util.SharedLog;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.android.networkstack.tethering.BpfCoordinator;
import com.android.networkstack.tethering.OffloadController;
import com.android.networkstack.tethering.util.StateMachine;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class TetheringDependencies {
    public abstract BluetoothAdapter getBluetoothAdapter();

    public abstract Context getContext();

    public abstract NetworkRequest getDefaultNetworkRequest();

    public abstract IpServer.Dependencies getIpServerDependencies();

    public abstract Looper getTetheringLooper();

    public BpfCoordinator getBpfCoordinator(BpfCoordinator.Dependencies dependencies) {
        return new BpfCoordinator(dependencies);
    }

    public OffloadHardwareInterface getOffloadHardwareInterface(Handler handler, SharedLog sharedLog) {
        return new OffloadHardwareInterface(handler, sharedLog);
    }

    public OffloadController getOffloadController(Handler handler, SharedLog sharedLog, OffloadController.Dependencies dependencies) {
        return new OffloadController(handler, getOffloadHardwareInterface(handler, sharedLog), getContext().getContentResolver(), (NetworkStatsManager) getContext().getSystemService("netstats"), sharedLog, dependencies);
    }

    public UpstreamNetworkMonitor getUpstreamNetworkMonitor(Context context, StateMachine stateMachine, SharedLog sharedLog, int i) {
        return new UpstreamNetworkMonitor(context, stateMachine, sharedLog, i);
    }

    public IPv6TetheringCoordinator getIPv6TetheringCoordinator(ArrayList<IpServer> arrayList, SharedLog sharedLog) {
        return new IPv6TetheringCoordinator(arrayList, sharedLog);
    }

    public EntitlementManager getEntitlementManager(Context context, Handler handler, SharedLog sharedLog, Runnable runnable) {
        return new EntitlementManager(context, handler, sharedLog, runnable);
    }

    public TetheringConfiguration generateTetheringConfiguration(Context context, SharedLog sharedLog, int i) {
        return new TetheringConfiguration(context, sharedLog, i);
    }

    public INetd getINetd(Context context) {
        return INetd.Stub.asInterface((IBinder) context.getSystemService("netd"));
    }

    public TetheringNotificationUpdater getNotificationUpdater(Context context, Looper looper) {
        return new TetheringNotificationUpdater(context, looper);
    }

    public boolean isTetheringDenied() {
        return TextUtils.equals(SystemProperties.get("ro.tether.denied"), "true");
    }
}
