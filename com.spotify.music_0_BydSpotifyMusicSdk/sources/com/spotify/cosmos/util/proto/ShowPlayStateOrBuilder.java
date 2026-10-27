package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import com.spotify.cosmos.util.proto.ShowPlayState;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface ShowPlayStateOrBuilder extends yj1 {
    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    boolean getIsPlayable();

    ShowPlayState.Label getLabel();

    String getLatestPlayedEpisodeLink();

    ByteString getLatestPlayedEpisodeLinkBytes();

    PlayabilityRestriction getPlayabilityRestriction();

    int getPlayedPercentage();

    long getPlayedTime();

    String getResumeEpisodeLink();

    ByteString getResumeEpisodeLinkBytes();

    boolean hasIsPlayable();

    boolean hasLabel();

    boolean hasLatestPlayedEpisodeLink();

    boolean hasPlayabilityRestriction();

    boolean hasPlayedPercentage();

    boolean hasPlayedTime();

    boolean hasResumeEpisodeLink();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
