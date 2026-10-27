package com.byd.systemsettings.wifi2.present;

import com.byd.systemsettings.wifi2.impl.BydWifiAutoConnection;
import com.byd.systemsettings.wifi2.view.pager.WifiInputConnectPager;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class WifiInputConnectPresenter extends f<WifiInputConnectPager> {
    public void setAutoConnectEnable(boolean z7) {
        BydWifiAutoConnection.Singleton.get().setAutoConnectEnable(z7);
    }
}
