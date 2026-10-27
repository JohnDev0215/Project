package com.amazon.device.utils.thirdparty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkBroadcastReceiver extends BroadcastReceiver {
    private Context mContext;
    private List<NetworkListener> mNetworkListenerList = new ArrayList();
    private boolean isRegistered = false;

    public NetworkBroadcastReceiver(Context context) {
        this.mContext = context;
    }

    @Override // android.content.BroadcastReceiver
    public synchronized void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            if (intent.getBooleanExtra("noConnectivity", true)) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            if (NetworkManager.instance(this.mContext).isWifiConnected()) {
                for (NetworkListener networkListener : this.mNetworkListenerList) {
                    if (networkListener.onWifiAvailable()) {
                        arrayList.add(networkListener);
                    }
                }
            } else if (NetworkManager.instance(this.mContext).isEthernetConnected()) {
                for (NetworkListener networkListener2 : this.mNetworkListenerList) {
                    if (networkListener2.onEthernetAvailable()) {
                        arrayList.add(networkListener2);
                    }
                }
            } else if (NetworkManager.instance(this.mContext).isWanConnected()) {
                for (NetworkListener networkListener3 : this.mNetworkListenerList) {
                    if (networkListener3.onWanAvailable()) {
                        arrayList.add(networkListener3);
                    }
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.mNetworkListenerList.remove((NetworkListener) it.next());
            }
            tryUnregister();
        }
    }

    private void tryUnregister() {
        if (this.isRegistered && this.mNetworkListenerList.size() == 0) {
            this.mContext.unregisterReceiver(this);
            this.isRegistered = false;
        }
    }
}
