package com.ecarx.xui.adaptapi.bt.hid;

/* JADX INFO: loaded from: classes2.dex */
public interface IHidCallback {
    void onHidServiceReady();

    void onHidStateChanged(String str, int i, int i2, int i3);
}
