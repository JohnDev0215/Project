package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface TrackArtistMetadataOrBuilder extends yj1 {
    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    String getLink();

    ByteString getLinkBytes();

    String getName();

    ByteString getNameBytes();

    ImageGroup getPortraits();

    boolean hasLink();

    boolean hasName();

    boolean hasPortraits();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
