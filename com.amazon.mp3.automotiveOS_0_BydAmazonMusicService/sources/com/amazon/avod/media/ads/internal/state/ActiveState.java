package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;

/* JADX INFO: loaded from: classes2.dex */
public class ActiveState extends AdEnabledPlaybackState {
    public ActiveState(AdPlaybackStateMachineContext adPlaybackStateMachineContext) {
        super(AdEnabledPlayerStateType.ACTIVE, adPlaybackStateMachineContext);
    }
}
