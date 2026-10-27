package com.amazon.avod.qos.listeners;

import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.qos.ErrorReportingConfiguration;
import com.amazon.avod.qos.QoSConfig;
import com.amazon.avod.qos.reporter.QosEventReporterFactory;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class QoSEventManagerFactory {
    private final QosEventReporterFactory mQosEventReporterFactory;
    private final MediaSystemSharedContext mSharedContext;

    public QoSEventManagerFactory(@Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull QosEventReporterFactory qosEventReporterFactory) {
        this.mSharedContext = mediaSystemSharedContext;
        this.mQosEventReporterFactory = qosEventReporterFactory;
    }

    public QoSEventManager newQoSEventManager(VideoSpecification videoSpecification, Map<String, String> map) {
        return new QoSEventManager(this.mQosEventReporterFactory, videoSpecification, new ErrorReportingConfiguration(), QoSConfig.INSTANCE, new ContentManagementEventListener(this.mSharedContext.getAppContext()), new LicenseAcquisitionEventListener(), map);
    }
}
