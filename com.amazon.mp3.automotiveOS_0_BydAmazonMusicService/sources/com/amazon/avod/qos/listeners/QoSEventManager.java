package com.amazon.avod.qos.listeners;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.qos.ErrorReportingConfiguration;
import com.amazon.avod.qos.QoSConfig;
import com.amazon.avod.qos.model.QosClientContext;
import com.amazon.avod.qos.reporter.CompositeSmoothStreamingEventReporter;
import com.amazon.avod.qos.reporter.QosEventReporterFactory;
import com.amazon.avod.util.DLog;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class QoSEventManager {
    private final CompositeSmoothStreamingEventReporter mCompositeReporter;
    private final QoSConfig mConfig;
    final ContentManagementEventListener mContentListener;
    private final ErrorReportingConfiguration mErrorConfig;
    final LicenseAcquisitionEventListener mLicensingListener;
    private QosEventListenerBase[] mQosEventListeners;
    private final PlaybackEventReporter mReporter;
    private final VideoSpecification mVideoSpec;

    public QoSEventManager(QosEventReporterFactory qosEventReporterFactory, VideoSpecification videoSpecification, ErrorReportingConfiguration errorReportingConfiguration, QoSConfig qoSConfig, ContentManagementEventListener contentManagementEventListener, LicenseAcquisitionEventListener licenseAcquisitionEventListener, Map<String, String> map) {
        this.mVideoSpec = videoSpecification;
        QosClientContext qosClientContextForContentDownload = QosClientContext.forContentDownload(videoSpecification, map);
        this.mCompositeReporter = qosEventReporterFactory.newQosEventReporter(qosClientContextForContentDownload);
        this.mReporter = qosEventReporterFactory.newStandaloneEventReporter(qosClientContextForContentDownload.getUserWatchSessionId());
        this.mErrorConfig = errorReportingConfiguration;
        this.mConfig = qoSConfig;
        this.mContentListener = contentManagementEventListener;
        this.mLicensingListener = licenseAcquisitionEventListener;
    }

    @Nonnull
    public PlaybackEventReporter getPlaybackEventReporter() {
        return this.mReporter;
    }

    public void reportErrorFetchingContentUrls(ContentException contentException, boolean z) {
        String string = contentException.toString();
        ImmutableMap<ContentException.ContentError, String> immutableMap = ContentManagementEventListener.ERROR_SUBTYPE_MAP;
        String str = immutableMap.get(contentException.getErrorCode());
        if (str == null) {
            str = immutableMap.get(ContentException.ContentError.UNKNOWN_ERROR);
        }
        this.mCompositeReporter.getContentReporter().onError(str, string, z ? 3 : this.mErrorConfig.getSeverityFor(contentException, false, contentException.getStatusCode()), contentException.getErrorCode().name(), contentException.getUrl() != null ? contentException.getUrl().toString() : null);
    }

    public void bindListeners(ContentSession contentSession, ContentSessionType contentSessionType) {
        if ((contentSessionType == ContentSessionType.CONTENT_CACHE || contentSessionType == ContentSessionType.LIVE_CACHE) && !this.mConfig.shouldReportMetricsForCaching()) {
            DLog.logf("DWNLD Not reporting qos for content cache events because server config is off");
            return;
        }
        this.mContentListener.bind(contentSession.getEventDispatcher(), this.mCompositeReporter.getContentReporter(), this.mCompositeReporter.getSmoothStreamingReporter());
        this.mLicensingListener.bind(contentSession.getEventDispatcher(), this.mCompositeReporter.getLicensingReporter());
        this.mQosEventListeners = new QosEventListenerBase[]{this.mContentListener, this.mLicensingListener};
    }

    public void releaseListeners() {
        QosEventListenerBase[] qosEventListenerBaseArr = this.mQosEventListeners;
        if (qosEventListenerBaseArr != null) {
            for (QosEventListenerBase qosEventListenerBase : qosEventListenerBaseArr) {
                qosEventListenerBase.release();
            }
        }
    }

    public void setContentSessionType(ContentSessionType contentSessionType) {
        this.mContentListener.setContentSessionType(contentSessionType);
    }

    public void setQosNote(String str) {
        this.mContentListener.setQosNote(str);
    }

    public void setAudioLanguage(@Nullable String str) {
        this.mContentListener.setAudioLanguage(str);
    }
}
