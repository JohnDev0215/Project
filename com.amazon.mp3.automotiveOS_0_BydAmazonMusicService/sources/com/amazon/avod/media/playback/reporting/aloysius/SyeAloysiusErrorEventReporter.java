package com.amazon.avod.media.playback.reporting.aloysius;

import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusErrorEventReporter;
import com.amazon.avod.playback.sye.events.SyeErrorEvent;
import com.amazon.avod.playback.sye.events.SyeFallbackMidStreamEvent;
import com.amazon.avod.playback.sye.events.SyeUDPBlockEvent;
import com.amazon.avod.util.DLog;
import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.Subscribe;
import com.netinsight.sye.syeClient.generated.enums.SyePlayerError;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SyeAloysiusErrorEventReporter extends AloysiusErrorEventReporter {
    private List<SyePlayerError> nonFatalErrors;

    public SyeAloysiusErrorEventReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nonnull PlaybackEventTransport playbackEventTransport) {
        super(mediaEventQueue, playbackEventTransport, null, null, null);
        this.nonFatalErrors = ImmutableList.of(SyePlayerError.TimeshiftOutOfRange);
    }

    @Subscribe
    public void handleSyeErrorEvent(SyeErrorEvent syeErrorEvent) {
        DLog.devf("SyeAloysiusErrorEventReporter::handleSyeErrorEvent %s, %s, %s", syeErrorEvent.getSyePlayerError(), syeErrorEvent.getErrorMessage(), Boolean.valueOf(syeErrorEvent.isFatal()));
        AloysiusErrorEventReporter.ErrorMediaEvent.Builder builder = new AloysiusErrorEventReporter.ErrorMediaEvent.Builder();
        builder.genericMessage(syeErrorEvent.getSyePlayerError().name());
        builder.specificMessage(syeErrorEvent.getErrorMessage());
        builder.type(AloysiusErrorEventReporter.ErrorMediaEvent.Type.Unreachable);
        builder.fatal(!this.nonFatalErrors.contains(syeErrorEvent.getSyePlayerError()) && syeErrorEvent.isFatal());
        builder.stackTrace(new StackTraceElement[0]);
        this.mMediaEventQueue.add(builder.build());
    }

    @Subscribe
    public void handleUDPBlockEvent(SyeUDPBlockEvent syeUDPBlockEvent) {
        DLog.devf("SyeAloysiusErrorEventReporter::handleUDPBlockEvent Sye player UDP blocked error");
        AloysiusErrorEventReporter.ErrorMediaEvent.Builder builder = new AloysiusErrorEventReporter.ErrorMediaEvent.Builder();
        builder.genericMessage("UDP blocked");
        builder.specificMessage("UDP blocked detected. Fallback to normal live player");
        builder.type(AloysiusErrorEventReporter.ErrorMediaEvent.Type.Unreachable);
        builder.fatal(false);
        builder.stackTrace(new StackTraceElement[0]);
        this.mMediaEventQueue.add(builder.build());
    }

    @Subscribe
    public void handleSyeFallbackMidStreamEvent(SyeFallbackMidStreamEvent syeFallbackMidStreamEvent) {
        DLog.devf("SyeAloysiusErrorEventReporter::handleSyeFallbackMidStreamEvent, Sye Player fallback on mid of stream.");
        AloysiusErrorEventReporter.ErrorMediaEvent.Builder builder = new AloysiusErrorEventReporter.ErrorMediaEvent.Builder();
        builder.genericMessage("SyeFallbackMidStream");
        builder.specificMessage(syeFallbackMidStreamEvent.getOriginalSyePlayerError().name() + " Error isn't handled in timeout. Fallback to normal live player");
        builder.type(AloysiusErrorEventReporter.ErrorMediaEvent.Type.Unreachable);
        builder.fatal(false);
        builder.stackTrace(new StackTraceElement[0]);
        this.mMediaEventQueue.add(builder.build());
    }
}
