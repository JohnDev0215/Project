package com.amazon.avod.ads.parser.vast;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastResourceHTML implements VastResource {
    private final String mHTML;
    private final boolean mXmlEncoded;

    public VastResourceHTML(@Nullable boolean z, @Nullable String str) {
        this.mXmlEncoded = z;
        this.mHTML = str;
    }

    @Nullable
    public String getHTML() {
        return this.mHTML;
    }
}
