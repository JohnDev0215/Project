package com.byd.music.netObserver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import com.byd.music.netObserver.NetworkUtils;
import com.byd.music.utils.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class NetStateChangeReceiver extends BroadcastReceiver {
    private static final String TAG = "NetStateChangeReceiver";
    private NetworkUtils.NetworkType mType = NetworkUtils.getNetworkType();
    private List<NetStateChangeObserver> mObservers = new ArrayList();
    private List<Context> contextList = new ArrayList();

    private static class InstanceHolder {
        private static final NetStateChangeReceiver INSTANCE = new NetStateChangeReceiver();

        private InstanceHolder() {
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LogUtil.e(TAG, "onReceive: ");
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            notifyObservers(NetworkUtils.getNetworkType());
        }
    }

    public static void registerReceiver(Context context) {
        LogUtil.e(TAG, "registerReceiver: ");
        if (InstanceHolder.INSTANCE.contextList == null || context == null) {
            return;
        }
        InstanceHolder.INSTANCE.contextList.add(context);
        context.registerReceiver(InstanceHolder.INSTANCE, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public static void unRegisterReceiver(Context context) {
        if (InstanceHolder.INSTANCE.contextList == null || context == null || !InstanceHolder.INSTANCE.contextList.contains(context)) {
            return;
        }
        try {
            context.unregisterReceiver(InstanceHolder.INSTANCE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        InstanceHolder.INSTANCE.contextList.remove(context);
    }

    public static void registerObserver(NetStateChangeObserver netStateChangeObserver) {
        LogUtil.e(TAG, "registerObserver: ");
        if (netStateChangeObserver == null || InstanceHolder.INSTANCE.mObservers.contains(netStateChangeObserver)) {
            return;
        }
        InstanceHolder.INSTANCE.mObservers.add(netStateChangeObserver);
    }

    public static void unRegisterObserver(NetStateChangeObserver netStateChangeObserver) {
        LogUtil.e(TAG, "unRegisterObserver: ");
        if (netStateChangeObserver == null || InstanceHolder.INSTANCE.mObservers == null) {
            return;
        }
        InstanceHolder.INSTANCE.mObservers.remove(netStateChangeObserver);
    }

    private void notifyObservers(NetworkUtils.NetworkType networkType) {
        if (this.mType == networkType) {
            return;
        }
        this.mType = networkType;
        if (networkType == NetworkUtils.NetworkType.NETWORK_NO) {
            Iterator<NetStateChangeObserver> it = this.mObservers.iterator();
            while (it.hasNext()) {
                dealObserver(it.next(), false);
            }
            return;
        }
        for (NetStateChangeObserver netStateChangeObserver : this.mObservers) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (NetworkUtils.isNetSystemUsable()) {
                    dealObserver(netStateChangeObserver, true);
                } else {
                    dealObserver(netStateChangeObserver, false);
                }
            } else {
                dealObserver(netStateChangeObserver, true);
            }
        }
    }

    private void dealObserver(NetStateChangeObserver netStateChangeObserver, boolean z) {
        LogUtil.e(TAG, "dealObserver: isConnected=" + z);
        if (z) {
            netStateChangeObserver.onNetConnected();
        } else {
            netStateChangeObserver.onNetDisconnected();
        }
    }
}
