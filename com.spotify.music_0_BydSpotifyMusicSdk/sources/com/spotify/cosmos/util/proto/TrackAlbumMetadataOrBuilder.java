package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface TrackAlbumMetadataOrBuilder extends yj1 {
    TrackAlbumArtistMetadata getArtist();

    ImageGroup getCovers();

    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    String getLink();

    ByteString getLinkBytes();

    String getName();

    ByteString getNameBytes();

    boolean hasArtist();

    boolean hasCovers();

    boolean hasLink();

    boolean hasName();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
