package com.micphone;

/* JADX INFO: loaded from: classes.dex */
public interface TsBydListener {
    void onEndpointEvent(int i, int i2, int i3);

    void onHotPlugInPidVid(int i, int i2);

    void onHotPlugState(int i, boolean z, boolean z2);

    void onInsideMic();

    void onOtaEvent(int i, int i2, int i3);

    void onOtaKTEventMode();
}
