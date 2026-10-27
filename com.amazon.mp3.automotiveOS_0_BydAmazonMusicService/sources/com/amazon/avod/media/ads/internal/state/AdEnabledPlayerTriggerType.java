package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;

/* JADX INFO: loaded from: classes2.dex */
public enum AdEnabledPlayerTriggerType implements PlayerTriggerType {
    WAIT_FOR_PREPARE,
    PRIMARY_CONTENT_PREPARED,
    AD_PREPARED,
    BEGIN_AD_BREAK,
    PREPARE_AD,
    PLAY_PRE_ROLLS,
    PLAY_AD,
    NO_MORE_ADS,
    NO_MORE_ADS_SKIP_TRANSITION,
    SKIP_CURRENT_AD_BREAK,
    SKIP_CURRENT_AD_CLIP,
    AD_PLAN_READY,
    AD_PLAN_ERROR,
    MONITOR_PRIMARY_CONTENT,
    AD_CLIP_ERROR,
    NEXT_AD_CLIP_SERVER_INSERTED
}
