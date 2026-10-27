package com.amazon.avod.qos.reporter;

import com.amazon.avod.battery.BatteryInfo;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.events.EventManager;
import com.amazon.avod.http.internal.TokenKey;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.playback.reporting.EventReporterFactory;
import com.amazon.avod.playback.capability.BatteryInfoProvider;
import com.amazon.avod.playback.capability.DeviceResources;
import com.amazon.avod.qos.QoSConfig;
import com.amazon.avod.qos.model.QosClientContext;
import com.amazon.avod.qos.model.internal.QosCommonMetricsContext;
import com.amazon.avod.qos.model.internal.ReporterContext;
import com.amazon.avod.qos.reporter.internal.PlatformMetricsEventSender;
import com.amazon.avod.qos.reporter.internal.QoSMetricEventSender;
import com.amazon.avod.qos.reporter.internal.SmoothStreamingEventReporter;
import com.amazon.avod.qos.reporter.internal.SmoothStreamingNoOpReporter;
import com.amazon.avod.qos.reporter.internal.StandaloneEventReporter;
import com.amazon.avod.session.ServiceSessionManager;
import com.amazon.avod.util.DLog;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class QosEventReporterFactory implements EventReporterFactory {
    private final Provider<BatteryInfo> mBatteryInfoProvider;
    private final QosCommonMetricsContext mCommonMetricsContext;
    private final MediaSystemSharedContext mContext;
    private final ExecutorService mExecutor;
    private final QoSConfig mConfig = QoSConfig.INSTANCE;
    private final EventManager mEventManager = EventManager.getInstance();
    private final DeviceResources mDeviceResources = DeviceResources.getInstance();

    public QosEventReporterFactory(MediaSystemSharedContext mediaSystemSharedContext, BatteryInfoProvider batteryInfoProvider) {
        this.mContext = mediaSystemSharedContext;
        this.mExecutor = mediaSystemSharedContext.getExecutorService();
        this.mBatteryInfoProvider = batteryInfoProvider;
        this.mCommonMetricsContext = new QosCommonMetricsContext(mediaSystemSharedContext.getAppContext(), mediaSystemSharedContext.getDeviceConfiguration(), mediaSystemSharedContext.getDeviceIdentity(), NetworkConnectionManager.getInstance(), ServiceSessionManager.getInstance());
    }

    @Override // com.amazon.avod.media.playback.reporting.EventReporterFactory
    public StandaloneEventReporter newStandaloneEventReporter(String str) {
        return new StandaloneEventReporter(this.mCommonMetricsContext, str, new QoSMetricEventSender(this.mEventManager, this.mConfig));
    }

    @Nonnull
    public PlaybackEventReporter newSimpleQosEventReporter(QosClientContext qosClientContext) {
        return newSimpleQosEventReporter(new ReporterContext(this.mConfig, this.mExecutor, this.mCommonMetricsContext, qosClientContext, getTokenKey(qosClientContext.getSessionContext())), new QoSMetricEventSender(this.mEventManager, this.mConfig));
    }

    private PlaybackEventReporter newSimpleQosEventReporter(ReporterContext reporterContext, QoSMetricEventSender qoSMetricEventSender) {
        PlaybackEventReporter playbackEventReporter = new PlaybackEventReporter(this.mConfig, qoSMetricEventSender, new PlatformMetricsEventSender(this.mContext.getAppContext()), this.mBatteryInfoProvider, this.mDeviceResources, this.mContext);
        playbackEventReporter.initialize(reporterContext);
        return playbackEventReporter;
    }

    @Nonnull
    public CompositeSmoothStreamingEventReporter newQosEventReporter(QosClientContext qosClientContext) {
        SmoothStreamingReporter smoothStreamingNoOpReporter;
        ReporterContext reporterContext = new ReporterContext(this.mConfig, this.mExecutor, this.mCommonMetricsContext, qosClientContext, getTokenKey(qosClientContext.getSessionContext()));
        QoSMetricEventSender qoSMetricEventSender = new QoSMetricEventSender(this.mEventManager, this.mConfig);
        ContentManagementEventReporter contentManagementEventReporter = new ContentManagementEventReporter(this.mConfig, qoSMetricEventSender);
        contentManagementEventReporter.initialize(reporterContext);
        LicenseAcquisitionEventReporter licenseAcquisitionEventReporter = new LicenseAcquisitionEventReporter(qoSMetricEventSender);
        licenseAcquisitionEventReporter.initialize(reporterContext);
        PlaybackEventReporter playbackEventReporterNewSimpleQosEventReporter = qosClientContext.isPlayback() ? newSimpleQosEventReporter(reporterContext, qoSMetricEventSender) : null;
        if (qosClientContext.isPlayback() || this.mConfig.reportPerFragmentMetricsOnDownload()) {
            SmoothStreamingEventReporter smoothStreamingEventReporter = new SmoothStreamingEventReporter(this.mConfig, qoSMetricEventSender, this.mContext);
            smoothStreamingEventReporter.initialize(reporterContext);
            smoothStreamingNoOpReporter = smoothStreamingEventReporter;
        } else {
            smoothStreamingNoOpReporter = new SmoothStreamingNoOpReporter();
        }
        DLog.logf("Primitive Session ID: %s, User Watch Session ID: %s", reporterContext.getPrimitiveSessionId(), qosClientContext.getUserWatchSessionId());
        return new CompositeSmoothStreamingEventReporter(playbackEventReporterNewSimpleQosEventReporter, contentManagementEventReporter, licenseAcquisitionEventReporter, smoothStreamingNoOpReporter);
    }

    @Nullable
    private TokenKey getTokenKey(Map<String, String> map) {
        String str = map.get(MAPAccountManager.KEY_SSO_ACCOUNT_DIRECTED_ID);
        String str2 = map.get("profileDirectedId");
        if (str == null) {
            return null;
        }
        return new TokenKey(str, str2);
    }
}
