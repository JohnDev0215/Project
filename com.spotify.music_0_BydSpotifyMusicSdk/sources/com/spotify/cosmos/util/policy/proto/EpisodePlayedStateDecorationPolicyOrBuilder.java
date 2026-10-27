package com.spotify.cosmos.util.policy.proto;

import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface EpisodePlayedStateDecorationPolicyOrBuilder extends yj1 {
    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    boolean getIsPlayed();

    boolean getLastPlayedAt();

    boolean getPlayabilityRestriction();

    boolean getPlayable();

    boolean getTimeLeft();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
