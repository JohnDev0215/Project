package com.byd.systemsettings.wifi2.present;

import com.byd.systemsettings.wifi2.impl.BydWifiAutoConnection;
import com.byd.systemsettings.wifi2.view.pager.WifiAddNetworkPager;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class WifiAddNetPresenter extends f<WifiAddNetworkPager> {
    public void setAutoConnectEnable(boolean z7) {
        BydWifiAutoConnection.Singleton.get().setAutoConnectEnable(z7);
    }
}
