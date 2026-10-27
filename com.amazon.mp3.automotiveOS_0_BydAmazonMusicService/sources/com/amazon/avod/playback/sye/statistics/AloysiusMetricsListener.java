package com.amazon.avod.playback.sye.statistics;

import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.framework.event.EventDispatcher;
import com.amazon.avod.playback.sye.SyeDomainVendingMachine;
import com.amazon.avod.playback.sye.events.BitstreamEvent;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.netinsight.sye.syeClient.ISyeMetrics;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusMetricsListener implements MetricsListener {
    private final BitstreamEvent.Factory mBitstreamFactory = new BitstreamEvent.Factory();
    private final EventDispatcher mEventBus;
    private final SyeDomainVendingMachine mSyeDomainVendingMachine;

    public AloysiusMetricsListener(@Nonnull EventDispatcher eventDispatcher, @Nonnull SyeDomainVendingMachine syeDomainVendingMachine) {
        this.mEventBus = (EventDispatcher) Preconditions.checkNotNull(eventDispatcher, "eventBus");
        this.mSyeDomainVendingMachine = (SyeDomainVendingMachine) Preconditions.checkNotNull(syeDomainVendingMachine, "syeDomainVendingMachine");
    }

    @Override // com.amazon.avod.playback.sye.statistics.MetricsListener
    public void onMetricsAvailable(ISyeMetrics iSyeMetrics, int i, int i2, @Nullable VideoResolution videoResolution) {
        try {
            this.mEventBus.postEvent(this.mBitstreamFactory.fromMetrics(iSyeMetrics, this.mSyeDomainVendingMachine.currentSyeDomain(), i, i2, videoResolution));
        } catch (Exception e) {
            DLog.warnf("Skip reporting and swallow error %s", e.getMessage());
        }
    }
}
