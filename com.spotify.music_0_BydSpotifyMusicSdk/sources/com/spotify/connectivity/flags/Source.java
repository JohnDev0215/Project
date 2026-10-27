package com.spotify.connectivity.flags;

import p.v03;

/* JADX INFO: loaded from: classes.dex */
public class Source {
    private final String key;
    private final String sourceType;

    public Source(String str, String str2) {
        v03.j(str, "sourceType");
        v03.j(str2, "key");
        this.sourceType = str;
        this.key = str2;
    }

    public final String getKey() {
        return this.key;
    }

    public final String getSourceType() {
        return this.sourceType;
    }
}
