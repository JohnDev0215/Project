package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface AlbumCollectionStateOrBuilder extends yj1 {
    String getCollectionLink();

    ByteString getCollectionLinkBytes();

    boolean getComplete();

    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    int getNumTracksInCollection();

    boolean hasCollectionLink();

    boolean hasComplete();

    boolean hasNumTracksInCollection();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
