package com.spotify.automotive.playerapi;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public enum BitrateSetting {
    AUTO(0),
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    VERY_HIGH(4);

    public static final List c = kotlin.collections.b.w0(values());
    private final int nativeCode;

    BitrateSetting(int i) {
        this.nativeCode = i;
    }

    public final int a() {
        return this.nativeCode;
    }
}
