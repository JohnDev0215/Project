package com.amazon.avod.ads.parser.vast;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastInlineCreative implements VastCreativeHolder {
    private final String mAdId;
    private final Object mCreative;
    private final String mId;
    private final int mSequence;

    public VastInlineCreative(@Nullable String str, int i, @Nullable String str2, @Nullable Object obj) {
        this.mId = str;
        this.mSequence = i;
        this.mAdId = str2;
        this.mCreative = obj;
    }

    @Nullable
    public String getId() {
        return this.mId;
    }

    public int getSequence() {
        return this.mSequence;
    }

    @Override // com.amazon.avod.ads.parser.vast.VastCreativeHolder
    @Nullable
    public Object getCreative() {
        return this.mCreative;
    }
}
