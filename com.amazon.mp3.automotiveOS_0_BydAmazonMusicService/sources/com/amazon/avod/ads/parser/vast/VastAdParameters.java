package com.amazon.avod.ads.parser.vast;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastAdParameters {
    private final String mAdParameters;
    private final boolean mXmlEncoded;

    public VastAdParameters(boolean z, @Nullable String str) {
        this.mXmlEncoded = z;
        this.mAdParameters = str;
    }
}
