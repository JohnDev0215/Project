package com.amazon.avod.ads.parser.vast;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastWrapperCreative implements VastCreativeHolder {
    private final String mAdId;
    private final Object mCreative;
    private final String mId;
    private final String mSequence;

    public VastWrapperCreative(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Object obj) {
        this.mId = str;
        this.mSequence = str2;
        this.mAdId = str3;
        this.mCreative = obj;
    }

    @Nullable
    public String getId() {
        return this.mId;
    }

    @Override // com.amazon.avod.ads.parser.vast.VastCreativeHolder
    @Nullable
    public Object getCreative() {
        return this.mCreative;
    }
}
