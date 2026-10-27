package com.ecarx.xui.adaptapi.wifiap.specific;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.util.Log;
import com.ecarx.xui.adaptapi.wifiap.IWifiApClient;
import com.ecarx.xui.adaptapi.wifiap.IWifiApClientCallback;
import com.ecarx.xui.adaptapi.wifiap.impl.WifiApClientImpl;
import com.neusoft.optimus.utility.log.LogTool;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class WifiApBean {
    private static final String TAG = "WifiApBean";
    public static final String WIFI_HOTSPOT_CLIENTS_CHANGED_ACTION = "android.net.wifi.WIFI_HOTSPOT_CLIENTS_CHANGED";
    public final String WIFI_HOTSPOT_MAX_CLIENT_NUM = "wifi_hotspot_max_client_num";
    private Context mContext;
    private List<IWifiApClientCallback> mIWifiApClientCallbacks;
    WifiManager mWifiManager;

    public WifiApBean(Context context) {
        LogTool.d(TAG, "WifiApBean()");
        this.mContext = context;
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        this.mIWifiApClientCallbacks = new ArrayList();
        registerBroadcastReceiver();
    }

    private void registerBroadcastReceiver() {
        LogTool.d(TAG, "registerBroadcastReceiver() registering...");
        TetherChangeReceiver tetherChangeReceiver = new TetherChangeReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WIFI_HOTSPOT_CLIENTS_CHANGED_ACTION);
        this.mContext.registerReceiver(tetherChangeReceiver, intentFilter);
    }

    public int getMaxConnections() {
        int connectiveNum = getConnectiveNum();
        LogTool.d(TAG, "getMaxConnections() = " + connectiveNum);
        return connectiveNum;
    }

    public boolean setMaxConnections(int i) {
        LogTool.d(TAG, "setConnectiveNum() " + Boolean.valueOf(Settings.System.putInt(this.mContext.getApplicationContext().getContentResolver(), "wifi_hotspot_max_client_num", i)));
        LogTool.d(TAG, "E01 platform does not support this feature !!!");
        return false;
    }

    public List<IWifiApClient> getWifiApClients() {
        ArrayList arrayList = new ArrayList();
        try {
            List list = (List) WifiManager.class.getDeclaredMethod("getHotspotClients", null).invoke(this.mWifiManager, null);
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(new WifiApClientImpl(it.next()));
                }
            } else {
                Log.d(TAG, "result is null");
            }
            LogTool.d(TAG, "mIWifiApClients = " + arrayList.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public synchronized boolean setWifiApClientCallback(IWifiApClientCallback iWifiApClientCallback) {
        if (iWifiApClientCallback == null) {
            LogTool.d(TAG, "setWifiApClientCallback() callback is null.");
            return false;
        }
        LogTool.d(TAG, "setWifiApClientCallback() adding...");
        this.mIWifiApClientCallbacks.add(iWifiApClientCallback);
        iWifiApClientCallback.onWifiApClientChanged(getWifiApClients());
        return true;
    }

    private class TetherChangeReceiver extends BroadcastReceiver {
        private TetherChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogTool.d(WifiApBean.TAG, "onReceive() " + intent.toString());
            if (intent.getAction().equals(WifiApBean.WIFI_HOTSPOT_CLIENTS_CHANGED_ACTION)) {
                WifiApBean wifiApBean = WifiApBean.this;
                wifiApBean.notifyAllListener(wifiApBean.getWifiApClients());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAllListener(List<IWifiApClient> list) {
        LogTool.d(TAG, "notifyAllListener() " + list.size());
        List<IWifiApClientCallback> list2 = this.mIWifiApClientCallbacks;
        if (list2 == null || list2.size() < 1) {
            return;
        }
        synchronized (this.mIWifiApClientCallbacks) {
            for (IWifiApClientCallback iWifiApClientCallback : this.mIWifiApClientCallbacks) {
                if (iWifiApClientCallback != null) {
                    iWifiApClientCallback.onWifiApClientChanged(list);
                }
            }
        }
    }

    public synchronized boolean unsetWifiApClientCallback(IWifiApClientCallback iWifiApClientCallback) {
        if (iWifiApClientCallback == null) {
            LogTool.d(TAG, "unsetWifiApClientCallback() callback is null.");
            return false;
        }
        List<IWifiApClientCallback> list = this.mIWifiApClientCallbacks;
        if (list != null && list.size() >= 1) {
            this.mIWifiApClientCallbacks.remove(iWifiApClientCallback);
        }
        return true;
    }

    private int getConnectiveNum() {
        int i = Settings.System.getInt(this.mContext.getApplicationContext().getContentResolver(), "wifi_hotspot_max_client_num", 5);
        LogTool.d(TAG, "getConnectiveNum() " + i);
        return i;
    }
}
