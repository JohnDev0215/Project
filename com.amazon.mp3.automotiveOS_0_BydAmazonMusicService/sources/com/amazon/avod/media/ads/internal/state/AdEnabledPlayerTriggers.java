package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.SimpleTrigger;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;

/* JADX INFO: loaded from: classes2.dex */
public class AdEnabledPlayerTriggers {
    public static final SimpleTrigger<PlayerTriggerType> AD_PLAN_READY = new SimpleTrigger<>(AdEnabledPlayerTriggerType.AD_PLAN_READY);
    public static final SimpleTrigger<PlayerTriggerType> AD_PLAN_ERROR = new SimpleTrigger<>(AdEnabledPlayerTriggerType.AD_PLAN_ERROR);
    public static final SimpleTrigger<PlayerTriggerType> NO_MORE_ADS = new SimpleTrigger<>(AdEnabledPlayerTriggerType.NO_MORE_ADS);
    public static final SimpleTrigger<PlayerTriggerType> PLAY_AD = new SimpleTrigger<>(AdEnabledPlayerTriggerType.PLAY_AD);
    public static final SimpleTrigger<PlayerTriggerType> NO_MORE_ADS_SKIP_BLACK_FRAME = new SimpleTrigger<>(AdEnabledPlayerTriggerType.NO_MORE_ADS_SKIP_TRANSITION);
}
