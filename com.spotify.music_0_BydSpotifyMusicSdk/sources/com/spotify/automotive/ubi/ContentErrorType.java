package com.spotify.automotive.ubi;

import com.spotify.connectivity.productstate.RxProductState;

/* JADX INFO: loaded from: classes.dex */
public enum ContentErrorType {
    OFFLINE(RxProductState.Keys.KEY_OFFLINE),
    UNKNOWN("unknown");

    private final String id;

    ContentErrorType(String str) {
        this.id = str;
    }

    public final String a() {
        return this.id;
    }
}
