package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface TrackAlbumArtistMetadataOrBuilder extends yj1 {
    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    String getLink();

    ByteString getLinkBytes();

    String getName();

    ByteString getNameBytes();

    boolean hasLink();

    boolean hasName();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
