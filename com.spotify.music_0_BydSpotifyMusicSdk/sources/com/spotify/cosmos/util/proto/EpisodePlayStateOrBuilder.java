package com.spotify.cosmos.util.proto;

import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface EpisodePlayStateOrBuilder extends yj1 {
    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    boolean getIsPlayable();

    boolean getIsPlayed();

    int getLastPlayedAt();

    PlayabilityRestriction getPlayabilityRestriction();

    int getTimeLeft();

    boolean hasIsPlayable();

    boolean hasIsPlayed();

    boolean hasLastPlayedAt();

    boolean hasPlayabilityRestriction();

    boolean hasTimeLeft();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
