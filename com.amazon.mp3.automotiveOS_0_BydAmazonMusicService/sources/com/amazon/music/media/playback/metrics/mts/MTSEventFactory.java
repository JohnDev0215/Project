package com.amazon.music.media.playback.metrics.mts;

import androidx.annotation.Nullable;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.MediaLink;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.metrics.MetricsInfo;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.types.TerminationReason;

/* JADX INFO: loaded from: classes4.dex */
public interface MTSEventFactory {
    MTSEvent createGhostListeningInterruptedEvent(MetricsInfo metricsInfo, long j, long j2);

    MTSEvent createPlaybackStateEvent(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, boolean z);

    MTSEvent createStreamingInitiatedEvent(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, long j2, @Nullable ChangeReason changeReason);

    MTSEvent createStreamingInitiationFailedEvent(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, ChangeReason changeReason, PlaybackException playbackException);

    MTSEvent createStreamingRequiredRebufferingEvent(MetricsInfo metricsInfo, MediaLink mediaLink, long j, long j2, long j3, float f);

    MTSEvent createStreamingResumedEvent(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, long j2, long j3, @Nullable ChangeReason changeReason);

    MTSEvent createStreamingTerminatedEvent(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, long j2, long j3, int i, long j4, float f, ChangeReason changeReason, @Nullable PlaybackException playbackException, long j5);

    MTSEvent createStreamingTerminatedEvent(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, long j2, long j3, int i, long j4, float f, TerminationReason terminationReason, @Nullable PlaybackException playbackException, long j5);

    MTSEvent createSubDeviceConnectedEvent(String str, boolean z);

    MTSEvent createSubDeviceDisconnectedEvent(String str, boolean z);

    MTSEvent createTrackStreamedEvent(MetricsInfo metricsInfo, long j, ChangeReason changeReason);

    MTSEvent createTrackStreamedEvent(MetricsInfo metricsInfo, long j, TerminationReason terminationReason);
}
