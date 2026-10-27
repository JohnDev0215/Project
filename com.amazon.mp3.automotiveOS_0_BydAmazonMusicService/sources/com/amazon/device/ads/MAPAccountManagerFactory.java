package com.amazon.device.ads;

/* JADX INFO: loaded from: classes2.dex */
class MAPAccountManagerFactory {
    MAPAccountManagerFactory() {
    }

    public AccountManagerWrapper getMapAccountManager() {
        return new MAPAccountManagerWrapper();
    }
}
