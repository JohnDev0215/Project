package com.qtxa.service;

import com.qtxa.annotation.NoConfusion;

/* JADX INFO: loaded from: classes.dex */
@com.qtxa.annotation.b({@com.qtxa.annotation.a(name = "bluetooth", type = "com.qtxa.monitor.bt.HandlerBluetoothRequest"), @com.qtxa.annotation.a(name = "wifi", type = "com.qtxa.monitor.wifi.HandlerWifiRequest"), @com.qtxa.annotation.a(name = "location", type = "com.qtxa.monitor.location.HandlerLocationRequest")})
public interface IHandlerRequest extends NoConfusion {
    void addCommCallback(d dVar);

    String onCall(String str, e eVar);
}
