package com.engine.tbox.data;

/* JADX INFO: loaded from: classes.dex */
public enum NetworkQuery {
    INVLIAD(0),
    RESET(1),
    RESERVE(2);

    private int mVal;

    NetworkQuery(int value) {
        this.mVal = 0;
        this.mVal = value;
    }

    public int toNumber() {
        return this.mVal;
    }

    public static NetworkQuery toNetworkQuery(int result) {
        if (result == 0) {
            return INVLIAD;
        }
        if (result == 1) {
            return RESET;
        }
        return RESERVE;
    }
}
