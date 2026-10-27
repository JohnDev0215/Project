package com.amazon.avod.ads.parser.vast;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastAdSystem {
    private final String mAdServer;
    private final String mVersion;

    public VastAdSystem(@Nullable String str, @Nullable String str2) {
        this.mVersion = str;
        this.mAdServer = str2;
    }

    @Nullable
    public String getAdServer() {
        return this.mAdServer;
    }
}
