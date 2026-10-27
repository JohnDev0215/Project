package com.spotify.cosmos.util.proto;

import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface TrackCollectionStateOrBuilder extends yj1 {
    boolean getCanAddToCollection();

    boolean getCanBan();

    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    boolean getIsBanned();

    boolean getIsInCollection();

    boolean hasCanAddToCollection();

    boolean hasCanBan();

    boolean hasIsBanned();

    boolean hasIsInCollection();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
