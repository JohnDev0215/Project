package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.media.playback.state.PlayerStateType;

/* JADX INFO: loaded from: classes2.dex */
public enum AdEnabledPlayerStateType implements PlayerStateType {
    ACTIVE,
    CHECK_PREROLL,
    PREPARING_AD,
    PREPARED_SEEKING,
    PLAYING_AD,
    PAUSE,
    AD_BREAK,
    AD_CLIP,
    WAITING_FOR_AD_PLAN,
    AD_PLAN_READY,
    MONITORING_FOR_AD_BREAK,
    POST_PRIMARY_STATE,
    CLIP_ERROR_STATE,
    TRANSITION_STATE,
    WAITING_FOR_PRIMARY_PLAYER
}
