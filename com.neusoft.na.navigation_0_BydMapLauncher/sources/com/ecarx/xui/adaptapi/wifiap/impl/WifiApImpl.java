package com.ecarx.xui.adaptapi.wifiap.impl;

import android.content.Context;
import com.ecarx.xui.adaptapi.wifiap.IWifiAPHost;
import com.ecarx.xui.adaptapi.wifiap.IWifiApClient;
import com.ecarx.xui.adaptapi.wifiap.IWifiApClientCallback;
import com.ecarx.xui.adaptapi.wifiap.WifiAp;
import com.ecarx.xui.adaptapi.wifiap.specific.WifiApBean;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class WifiApImpl extends WifiAp {
    private Context mContext;
    WifiApBean mWifiApBean;

    public WifiApImpl(Context context) {
        this.mWifiApBean = new WifiApBean(context);
        this.mContext = context;
    }

    @Override // com.ecarx.xui.adaptapi.wifiap.WifiAp
    public IWifiAPHost getWifiAPHost() {
        return new WifiAPHostImpl(this.mContext);
    }

    @Override // com.ecarx.xui.adaptapi.wifiap.WifiAp
    public int getMaxConnections() {
        return this.mWifiApBean.getMaxConnections();
    }

    @Override // com.ecarx.xui.adaptapi.wifiap.WifiAp
    public boolean setMaxConnections(int i) {
        return this.mWifiApBean.setMaxConnections(i);
    }

    @Override // com.ecarx.xui.adaptapi.wifiap.WifiAp
    public List<IWifiApClient> getWifiApClients() {
        return this.mWifiApBean.getWifiApClients();
    }

    @Override // com.ecarx.xui.adaptapi.wifiap.WifiAp
    public boolean setWifiApClientCallback(IWifiApClientCallback iWifiApClientCallback) {
        return this.mWifiApBean.setWifiApClientCallback(iWifiApClientCallback);
    }

    @Override // com.ecarx.xui.adaptapi.wifiap.WifiAp
    public boolean unsetWifiApClientCallback(IWifiApClientCallback iWifiApClientCallback) {
        return this.mWifiApBean.unsetWifiApClientCallback(iWifiApClientCallback);
    }
}
