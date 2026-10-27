package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.google.common.base.Preconditions;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class MonitorTrigger implements Trigger<PlayerTriggerType> {
    private final TimeSpan mStartTime;

    public MonitorTrigger(TimeSpan timeSpan) {
        this.mStartTime = (TimeSpan) Preconditions.checkNotNull(timeSpan);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.avod.fsm.Trigger
    public PlayerTriggerType getType() {
        return AdEnabledPlayerTriggerType.MONITOR_PRIMARY_CONTENT;
    }

    public String toString() {
        return String.format(Locale.US, "MonitorTrigger:%s", this.mStartTime);
    }
}
