package com.amazon.avod.qos;

import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.event.EventDispatcher;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter;
import com.amazon.avod.pmet.PMETEventReporter;
import com.amazon.avod.qos.listeners.ContentManagementEventListener;
import com.amazon.avod.qos.listeners.DefaultPlaybackEventReporter;
import com.amazon.avod.qos.listeners.LicenseAcquisitionEventListener;
import com.amazon.avod.qos.metadata.DeliveryType;
import com.amazon.avod.qos.model.QosClientContext;
import com.amazon.avod.qos.reporter.CompositeSmoothStreamingEventReporter;
import com.amazon.avod.qos.reporter.PlaybackEventReporter;
import com.amazon.avod.qos.reporter.QosEventReporterFactory;
import com.amazon.avod.qos.reporter.internal.StandaloneEventReporter;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamingVideoPresentationEventReporter implements VideoPresentationEventReporter {
    private final ContentManagementEventListener mContentEventListener;
    private final LicenseAcquisitionEventListener mLicensingEventListener;
    private final PMETEventReporter mPMETEventReporter;
    private final PlaybackEventListener mPlaybackEventListener;
    private DefaultPlaybackEventReporter mPlaybackReporterView;
    private CompositeSmoothStreamingEventReporter mQosReporter;
    private final QosEventReporterFactory mReporterFactory;
    private List<ResolutionRampUpTimeReporter> mResolutionRampUpTimeReporters;
    private StandaloneEventReporter mStandaloneEventReporter;

    public SmoothStreamingVideoPresentationEventReporter(QosEventReporterFactory qosEventReporterFactory, MediaSystemSharedContext mediaSystemSharedContext) {
        this(qosEventReporterFactory, new ContentManagementEventListener(mediaSystemSharedContext.getAppContext()), new LicenseAcquisitionEventListener(), new PlaybackEventListener(mediaSystemSharedContext.getExecutorService(), mediaSystemSharedContext.getAppContext()), new PMETEventReporter());
    }

    SmoothStreamingVideoPresentationEventReporter(@Nonnull QosEventReporterFactory qosEventReporterFactory, @Nonnull ContentManagementEventListener contentManagementEventListener, @Nonnull LicenseAcquisitionEventListener licenseAcquisitionEventListener, @Nonnull PlaybackEventListener playbackEventListener, @Nonnull PMETEventReporter pMETEventReporter) {
        this.mReporterFactory = (QosEventReporterFactory) Preconditions.checkNotNull(qosEventReporterFactory, "reporterFactory");
        this.mContentEventListener = (ContentManagementEventListener) Preconditions.checkNotNull(contentManagementEventListener, "contentListener");
        this.mLicensingEventListener = (LicenseAcquisitionEventListener) Preconditions.checkNotNull(licenseAcquisitionEventListener, "licensingListener");
        this.mPlaybackEventListener = (PlaybackEventListener) Preconditions.checkNotNull(playbackEventListener, "playbackListener");
        this.mPMETEventReporter = (PMETEventReporter) Preconditions.checkNotNull(pMETEventReporter, "pmetEventReporter");
    }

    @Override // com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter
    public void initialize(VideoPresentation videoPresentation, String str) {
        QosClientContext qosClientContextForPlayback = QosClientContext.forPlayback(str, videoPresentation.getSpecification(), videoPresentation.getVideoOptions().getSessionContext());
        CompositeSmoothStreamingEventReporter compositeSmoothStreamingEventReporterNewQosEventReporter = this.mReporterFactory.newQosEventReporter(qosClientContextForPlayback);
        this.mQosReporter = compositeSmoothStreamingEventReporterNewQosEventReporter;
        PlaybackEventReporter playbackEventReporter = (PlaybackEventReporter) Preconditions.checkNotNull(compositeSmoothStreamingEventReporterNewQosEventReporter.getPlaybackReporter());
        StandaloneEventReporter standaloneEventReporterNewStandaloneEventReporter = this.mReporterFactory.newStandaloneEventReporter(str);
        this.mStandaloneEventReporter = standaloneEventReporterNewStandaloneEventReporter;
        DefaultPlaybackEventReporter defaultPlaybackEventReporter = new DefaultPlaybackEventReporter(qosClientContextForPlayback, playbackEventReporter, standaloneEventReporterNewStandaloneEventReporter);
        this.mPlaybackReporterView = defaultPlaybackEventReporter;
        String primitiveSessionId = defaultPlaybackEventReporter.getPrimitiveSessionId();
        this.mResolutionRampUpTimeReporters = Lists.newArrayList(new ResolutionRampUpTimeReporter(VideoResolution.ResolutionBand.HD, this.mStandaloneEventReporter, primitiveSessionId), new ResolutionRampUpTimeReporter(VideoResolution.ResolutionBand.HD_1080P, this.mStandaloneEventReporter, primitiveSessionId), new ResolutionRampUpTimeReporter(VideoResolution.ResolutionBand.ULTRA_HD, this.mStandaloneEventReporter, primitiveSessionId));
    }

    @Override // com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter
    public String getUserWatchSessionId() {
        return this.mPlaybackReporterView.getUserWatchSessionId();
    }

    @Override // com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter
    @Nonnull
    public com.amazon.avod.playback.PlaybackEventReporter getPlaybackReporter() {
        return this.mPlaybackReporterView;
    }

    @Nonnull
    public PMETEventReporter getPMETEventReporter() {
        return this.mPMETEventReporter;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.mPlaybackEventListener.setDeliveryType(deliveryType);
    }

    public void setAudioLanguage(String str) {
        this.mPlaybackEventListener.setAudioLanguage(str);
    }

    public void bindForPlayback(PlaybackEventTransport playbackEventTransport) {
        this.mPlaybackEventListener.bind(playbackEventTransport, this.mQosReporter.getPlaybackReporter());
        Iterator<ResolutionRampUpTimeReporter> it = this.mResolutionRampUpTimeReporters.iterator();
        while (it.hasNext()) {
            it.next().bind(playbackEventTransport);
        }
        new PeakBitrateReachedEventReporter(playbackEventTransport, this.mStandaloneEventReporter, this.mPlaybackReporterView.getPrimitiveSessionId());
    }

    public void bindForContentSession(EventDispatcher eventDispatcher) {
        this.mContentEventListener.bind(eventDispatcher, this.mQosReporter.getContentReporter(), this.mQosReporter.getSmoothStreamingReporter());
        this.mLicensingEventListener.bind(eventDispatcher, this.mQosReporter.getLicensingReporter());
    }

    public void releaseAll() {
        this.mContentEventListener.release();
        this.mLicensingEventListener.release();
        this.mPlaybackEventListener.release();
        Iterator<ResolutionRampUpTimeReporter> it = this.mResolutionRampUpTimeReporters.iterator();
        while (it.hasNext()) {
            it.next().release();
        }
    }
}
