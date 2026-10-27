package com.spotify.cosmos.util.policy.proto;

import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface ShowPlayedStateDecorationPolicyOrBuilder extends yj1 {
    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    boolean getIsPlayable();

    boolean getLabel();

    boolean getLatestPlayedEpisodeLink();

    boolean getPlayabilityRestriction();

    boolean getPlayedTime();

    boolean getResumeEpisodeLink();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
