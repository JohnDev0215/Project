package com.ts.carplay.linkadapter.iap.data;

/* JADX INFO: loaded from: classes.dex */
public enum IapSessionStatus {
    IapSessionUnknownStatus(-1),
    IapSessionIapVersion1(0),
    IapSessionIapVersion2(1),
    IapSessionAuthenticated(2),
    IapSessionAuthenticationFailed(3),
    IapSessionConnected(4),
    IapSessionConnectionFailed(5),
    IapSessionDisconnecting(6),
    IapSessionDisconnected(7),
    IapSessionDown(8),
    IapSessionReset(9);

    private int mStatus;

    IapSessionStatus(int status) {
        this.mStatus = status;
    }

    public int getStatusValue() {
        return this.mStatus;
    }

    public static IapSessionStatus fromStatusValue(int status) {
        for (IapSessionStatus value : values()) {
            if (value.getStatusValue() == status) {
                return value;
            }
        }
        return IapSessionUnknownStatus;
    }
}
