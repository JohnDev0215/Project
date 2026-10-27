package com.spotify.cosmos.util.proto;

import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface TrackPlayStateOrBuilder extends yj1 {
    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    boolean getIsPlayable();

    PlayabilityRestriction getPlayabilityRestriction();

    boolean hasIsPlayable();

    boolean hasPlayabilityRestriction();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
