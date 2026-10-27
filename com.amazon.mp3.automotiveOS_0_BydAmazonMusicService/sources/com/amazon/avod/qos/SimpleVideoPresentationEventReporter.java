package com.amazon.avod.qos;

import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.reporting.QoSVideoPlayerEventInterpreter;
import com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter;
import com.amazon.avod.qos.listeners.DefaultPlaybackEventReporter;
import com.amazon.avod.qos.model.QosClientContext;
import com.amazon.avod.qos.reporter.PlaybackEventReporter;
import com.amazon.avod.qos.reporter.QosEventReporterFactory;
import com.amazon.avod.util.DLog;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleVideoPresentationEventReporter implements VideoPresentationEventReporter {
    private QoSVideoPlayerEventInterpreter mEventInterpreter;
    private final QosEventReporterFactory mQosEventReporterFactory;
    private DefaultPlaybackEventReporter mReporterView;

    public SimpleVideoPresentationEventReporter(QosEventReporterFactory qosEventReporterFactory) {
        this.mQosEventReporterFactory = qosEventReporterFactory;
    }

    @Override // com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter
    public void initialize(VideoPresentation videoPresentation, String str) {
        DLog.logf("Initializing reporting for session ID=%s", str);
        QosClientContext qosClientContextForPlayback = QosClientContext.forPlayback(str, videoPresentation.getSpecification(), videoPresentation.getVideoOptions().getSessionContext());
        PlaybackEventReporter playbackEventReporterNewSimpleQosEventReporter = this.mQosEventReporterFactory.newSimpleQosEventReporter(qosClientContextForPlayback);
        QoSVideoPlayerEventInterpreter qoSVideoPlayerEventInterpreter = new QoSVideoPlayerEventInterpreter(playbackEventReporterNewSimpleQosEventReporter);
        this.mEventInterpreter = qoSVideoPlayerEventInterpreter;
        qoSVideoPlayerEventInterpreter.initialize(videoPresentation);
        this.mReporterView = new DefaultPlaybackEventReporter(qosClientContextForPlayback, playbackEventReporterNewSimpleQosEventReporter, this.mQosEventReporterFactory.newStandaloneEventReporter(str));
    }

    @Override // com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter
    public String getUserWatchSessionId() {
        return this.mReporterView.getUserWatchSessionId();
    }

    @Override // com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter
    @Nonnull
    public com.amazon.avod.playback.PlaybackEventReporter getPlaybackReporter() {
        return this.mReporterView;
    }
}
