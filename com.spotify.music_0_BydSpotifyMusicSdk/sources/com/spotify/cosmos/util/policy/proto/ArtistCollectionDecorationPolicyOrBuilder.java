package com.spotify.cosmos.util.policy.proto;

import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface ArtistCollectionDecorationPolicyOrBuilder extends yj1 {
    boolean getCanBan();

    boolean getCollectionLink();

    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    boolean getIsBanned();

    boolean getIsFollowed();

    boolean getNumAlbumsInCollection();

    boolean getNumExplicitlyLikedTracks();

    boolean getNumTracksInCollection();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
