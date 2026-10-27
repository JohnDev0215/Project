package com.ecarx.xui.adaptapi.vehicle.pdc;

/* JADX INFO: loaded from: classes2.dex */
public class PDCImpl implements IPDC {
    private IPDCallback listener;

    @Override // com.ecarx.xui.adaptapi.vehicle.pdc.IPDC
    public IPDCState getPDCState() {
        return new PDCStateImpl();
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.pdc.IPDC
    public void setPDCallback(IPDCallback iPDCallback) {
        this.listener = iPDCallback;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.pdc.IPDC
    public void unsetPDCallback(IPDCallback iPDCallback) {
        this.listener = null;
    }
}
