package com.ts.carplay.linkadapter.iap;

import ts.car.iap.protocol.IapController;

/* JADX INFO: loaded from: classes.dex */
public class IapSessionInfo {
    int mClientType;
    IapController.IapSession mIapSession;
    String mUuid;

    IapSessionInfo() {
        this.mClientType = 153;
        this.mIapSession = null;
        this.mUuid = null;
    }

    IapSessionInfo(int clientType) {
        this.mClientType = clientType;
        this.mIapSession = null;
        this.mUuid = null;
    }

    public int getClientType() {
        return this.mClientType;
    }

    public void setClientType(int clientType) {
        this.mClientType = clientType;
    }

    public IapController.IapSession getIapSession() {
        return this.mIapSession;
    }

    public void setIapSession(IapController.IapSession iapSession) {
        this.mIapSession = iapSession;
    }

    public String getUuid() {
        return this.mUuid;
    }

    public void setUuid(String uuid) {
        this.mUuid = uuid;
    }
}
