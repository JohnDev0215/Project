package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface ImageGroupOrBuilder extends yj1 {
    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    String getLargeLink();

    ByteString getLargeLinkBytes();

    String getSmallLink();

    ByteString getSmallLinkBytes();

    String getStandardLink();

    ByteString getStandardLinkBytes();

    String getXlargeLink();

    ByteString getXlargeLinkBytes();

    boolean hasLargeLink();

    boolean hasSmallLink();

    boolean hasStandardLink();

    boolean hasXlargeLink();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
