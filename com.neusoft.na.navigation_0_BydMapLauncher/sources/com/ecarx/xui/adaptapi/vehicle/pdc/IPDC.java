package com.ecarx.xui.adaptapi.vehicle.pdc;

/* JADX INFO: loaded from: classes2.dex */
public interface IPDC {
    IPDCState getPDCState();

    void setPDCallback(IPDCallback iPDCallback);

    void unsetPDCallback(IPDCallback iPDCallback);
}
