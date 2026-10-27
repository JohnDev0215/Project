package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface ArtistCollectionStateOrBuilder extends yj1 {
    boolean getCanBan();

    String getCollectionLink();

    ByteString getCollectionLinkBytes();

    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    boolean getFollowed();

    boolean getIsBanned();

    int getNumAlbumsInCollection();

    int getNumExplicitlyLikedTracks();

    int getNumTracksInCollection();

    boolean hasCanBan();

    boolean hasCollectionLink();

    boolean hasFollowed();

    boolean hasIsBanned();

    boolean hasNumAlbumsInCollection();

    boolean hasNumExplicitlyLikedTracks();

    boolean hasNumTracksInCollection();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
