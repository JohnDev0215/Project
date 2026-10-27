package com.amazon.avod.qahooks;

import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.BufferingAnalysis;
import com.amazon.avod.playback.PlaybackBufferEventType;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackSessionBufferEventListener;
import com.amazon.avod.qahooks.QALog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VerificationLogger implements PlaybackSessionBufferEventListener {
    VideoSpecification mVideoSpec;

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferProgress(float f) {
    }

    public void initialize(@Nonnull VideoPresentation videoPresentation) {
        Preconditions.checkNotNull(videoPresentation, "presentation parameter cannot be null.");
        this.mVideoSpec = (VideoSpecification) Preconditions.checkNotNull(videoPresentation.getSpecification(), "video specification in presentation cannot be null.");
        videoPresentation.getPlayer().addListener(this);
    }

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferStart(PlaybackBufferEventType playbackBufferEventType, PlaybackEventContext playbackEventContext, @Nullable BufferingAnalysis bufferingAnalysis) {
        QALog.newQALog(PlaybackQAEvent.BUFFERING_STARTED).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.ASIN, this.mVideoSpec.getTitleId()).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.TITLE_ID, this.mVideoSpec.getTitleId()).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.BUFFER_EVENT_TYPE, playbackBufferEventType.name()).send();
    }

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferEnd(PlaybackBufferEventType playbackBufferEventType, PlaybackEventContext playbackEventContext) {
        QALog.newQALog(PlaybackQAEvent.BUFFERING_ENDED).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.ASIN, this.mVideoSpec.getTitleId()).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.TITLE_ID, this.mVideoSpec.getTitleId()).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.BUFFER_EVENT_TYPE, playbackBufferEventType.name()).send();
    }
}
