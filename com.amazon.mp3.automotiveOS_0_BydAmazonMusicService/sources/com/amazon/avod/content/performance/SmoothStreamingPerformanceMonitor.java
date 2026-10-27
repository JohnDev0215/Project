package com.amazon.avod.content.performance;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionState;
import com.amazon.avod.content.config.SmoothStreamingDeviceCapabilityConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackPerformanceConfig;
import com.amazon.avod.content.downloading.ContentAccessor;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.quality.capability.RuntimeDeviceCapabilityBasedBitrateResolver;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.PlaybackPerformanceReport;
import com.amazon.avod.playback.capability.DeviceCapability;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class SmoothStreamingPerformanceMonitor implements PerformanceMonitor {
    private final TimeSpan mBufferToKeepOnTrimTime;
    private ContentAccessor mContentAccessor;
    private final int mCriticalDownshiftCount;
    private final DeviceCapability mDeviceCapability;
    private final SmoothStreamingDeviceCapabilityConfig mDeviceCapsConfig;
    private ScheduledExecutorService mExecutor;
    private final int mLowEndPerfPenalty;
    private final int mMajorDownshiftCount;
    private PlaybackPerformanceReport mMostRecentReport;
    private int mPerformanceLevel;
    private PlaybackPerformanceReport mProcessingReport;
    private ContentSessionState mSessionState;
    private int mStaticBitrateCap;
    private StreamIndex mStreamIndex;
    private final TimeSpan mTimeToSustainPerformanceBeforeUpshift;
    private final int mUpshiftCount;
    private ScheduledFuture<?> mUpshiftFuture;
    private final Object mPerformanceReportLock = new Object();
    private boolean mIsMonitoring = false;

    public SmoothStreamingPerformanceMonitor(@Nonnull SmoothStreamingPlaybackPerformanceConfig smoothStreamingPlaybackPerformanceConfig, @Nonnull SmoothStreamingDeviceCapabilityConfig smoothStreamingDeviceCapabilityConfig, @Nonnull DeviceCapability deviceCapability) {
        Preconditions.checkNotNull(smoothStreamingPlaybackPerformanceConfig);
        Preconditions.checkNotNull(deviceCapability);
        Preconditions.checkNotNull(smoothStreamingDeviceCapabilityConfig);
        this.mDeviceCapsConfig = smoothStreamingDeviceCapabilityConfig;
        this.mDeviceCapability = deviceCapability;
        this.mBufferToKeepOnTrimTime = smoothStreamingPlaybackPerformanceConfig.getTrimBufferOnPerf();
        this.mTimeToSustainPerformanceBeforeUpshift = smoothStreamingPlaybackPerformanceConfig.getSustainablePerfUpshift();
        this.mLowEndPerfPenalty = smoothStreamingPlaybackPerformanceConfig.getLowEndPerfPenalty();
        this.mCriticalDownshiftCount = smoothStreamingPlaybackPerformanceConfig.getCriticalPerfDownshiftCount();
        this.mMajorDownshiftCount = smoothStreamingPlaybackPerformanceConfig.getMajorPerfDownshiftCount();
        this.mUpshiftCount = smoothStreamingPlaybackPerformanceConfig.getPerfUpshiftCount();
    }

    @Override // com.amazon.avod.content.performance.PerformanceMonitor
    public void begin(@Nonnull ContentAccessor contentAccessor, @Nonnull ContentSessionState contentSessionState, @Nonnull StreamIndex streamIndex) throws ContentException {
        int highestSustainableBitrate;
        boolean zIsLowEndDevice;
        synchronized (this.mPerformanceReportLock) {
            if (this.mIsMonitoring) {
                return;
            }
            Preconditions.checkNotNull(contentAccessor);
            Preconditions.checkNotNull(streamIndex);
            Preconditions.checkNotNull(contentSessionState);
            RuntimeDeviceCapabilityBasedBitrateResolver runtimeDeviceCapabilityBasedBitrateResolver = new RuntimeDeviceCapabilityBasedBitrateResolver(this.mDeviceCapsConfig);
            try {
                highestSustainableBitrate = runtimeDeviceCapabilityBasedBitrateResolver.getHighestSustainableBitrate(this.mDeviceCapability);
                zIsLowEndDevice = runtimeDeviceCapabilityBasedBitrateResolver.isLowEndDevice(this.mDeviceCapability);
            } catch (ContentException unused) {
                highestSustainableBitrate = Integer.MAX_VALUE;
                DLog.errorf("Failed to calculate the bitrate cap. No cap will be applied.");
                zIsLowEndDevice = false;
            }
            begin(ScheduledExecutorBuilder.newBuilderFor(this, new String[0]).withFixedThreadPoolSize(1).build(), highestSustainableBitrate, zIsLowEndDevice, contentAccessor, contentSessionState, streamIndex);
        }
    }

    void begin(@Nonnull ScheduledExecutorService scheduledExecutorService, int i, boolean z, @Nonnull ContentAccessor contentAccessor, @Nonnull ContentSessionState contentSessionState, @Nonnull StreamIndex streamIndex) {
        Preconditions.checkNotNull(scheduledExecutorService);
        Preconditions.checkArgument(i > 0);
        this.mStreamIndex = streamIndex;
        this.mContentAccessor = contentAccessor;
        this.mSessionState = contentSessionState;
        this.mStaticBitrateCap = i;
        this.mExecutor = scheduledExecutorService;
        if (z) {
            this.mPerformanceLevel = this.mLowEndPerfPenalty;
            scheduleUpshift();
        } else {
            this.mPerformanceLevel = 0;
        }
        this.mSessionState.setSustainableQualityLevel(getQualityLevelForDegradation());
        this.mIsMonitoring = true;
    }

    @Override // com.amazon.avod.content.performance.PerformanceMonitor
    public void end() {
        synchronized (this.mPerformanceReportLock) {
            if (this.mIsMonitoring) {
                this.mExecutor.shutdownNow();
                this.mExecutor = null;
                this.mIsMonitoring = false;
            }
        }
    }

    @Override // com.amazon.avod.content.performance.PerformanceMonitor
    public void setPlaybackPerformanceReport(@Nullable PlaybackPerformanceReport playbackPerformanceReport) {
        synchronized (this.mPerformanceReportLock) {
            if (this.mIsMonitoring) {
                setPlaybackPerformanceReport(playbackPerformanceReport, TimeSpan.now());
            }
        }
    }

    void setPlaybackPerformanceReport(@Nullable PlaybackPerformanceReport playbackPerformanceReport, @Nonnull TimeSpan timeSpan) {
        evaluatePerformanceDegradation(timeSpan, playbackPerformanceReport);
    }

    int getPerformanceLevel() {
        return this.mPerformanceLevel;
    }

    @Nullable
    private QualityLevel getQualityLevelForDegradation() {
        StreamIndex streamIndex = this.mStreamIndex;
        QualityLevel closestQualityLevel = streamIndex.getClosestQualityLevel(this.mSessionState.getConsumptionHead(streamIndex.getIndex()), this.mStaticBitrateCap);
        int i = this.mPerformanceLevel;
        QualityLevel qualityLevel = closestQualityLevel;
        while (i > 0 && closestQualityLevel != null) {
            int bitrate = closestQualityLevel.getBitrate();
            StreamIndex streamIndex2 = this.mStreamIndex;
            QualityLevel qualityLevelLessThanEqual = streamIndex2.getQualityLevelLessThanEqual(this.mSessionState.getConsumptionHead(streamIndex2.getIndex()), bitrate - 1);
            i--;
            qualityLevel = closestQualityLevel;
            closestQualityLevel = qualityLevelLessThanEqual;
        }
        return closestQualityLevel == null ? qualityLevel : closestQualityLevel;
    }

    int evaluatePerformanceDegradation(@Nonnull TimeSpan timeSpan, @Nullable PlaybackPerformanceReport playbackPerformanceReport) {
        if (!isSustainablePerformanceReport(playbackPerformanceReport) && isNewReportMoreRecent(playbackPerformanceReport, this.mMostRecentReport)) {
            this.mMostRecentReport = playbackPerformanceReport;
            scheduleUpshift();
        }
        if (isReportStale(this.mProcessingReport, timeSpan, this.mBufferToKeepOnTrimTime)) {
            this.mProcessingReport = null;
            if (isDegradedPerformanceReport(this.mMostRecentReport) && !isReportStale(this.mMostRecentReport, timeSpan, this.mBufferToKeepOnTrimTime)) {
                PlaybackPerformanceReport playbackPerformanceReport2 = this.mMostRecentReport;
                this.mProcessingReport = playbackPerformanceReport2;
                int i = this.mPerformanceLevel;
                if (i < 20) {
                    int i2 = i + (playbackPerformanceReport2.getSeverity() == PlaybackPerformanceReport.PlaybackPerformanceSeverity.CRITICAL ? this.mCriticalDownshiftCount : this.mMajorDownshiftCount);
                    this.mPerformanceLevel = i2;
                    DLog.logf("Degrading performance level to %d due to %s performance report", Integer.valueOf(i2), this.mProcessingReport.getSeverity());
                    QualityLevel qualityLevelForDegradation = getQualityLevelForDegradation();
                    this.mSessionState.setSustainableQualityLevel(qualityLevelForDegradation);
                    this.mContentAccessor.restrictToQuality(qualityLevelForDegradation, this.mSessionState.getPlayPositionInNanos() + this.mBufferToKeepOnTrimTime.getTotalNanoSeconds());
                }
            }
        }
        return this.mPerformanceLevel;
    }

    private void scheduleUpshift() {
        ScheduledFuture<?> scheduledFuture = this.mUpshiftFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.mUpshiftFuture = null;
        }
        this.mUpshiftFuture = this.mExecutor.schedule(new Runnable() { // from class: com.amazon.avod.content.performance.SmoothStreamingPerformanceMonitor.1
            @Override // java.lang.Runnable
            public void run() {
                SmoothStreamingPerformanceMonitor.this.upshift();
            }
        }, this.mTimeToSustainPerformanceBeforeUpshift.getTotalMilliseconds(), TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upshift() {
        synchronized (this.mPerformanceReportLock) {
            int i = this.mPerformanceLevel;
            if (i == 0) {
                this.mMostRecentReport = null;
            } else {
                int i2 = i - this.mUpshiftCount;
                this.mPerformanceLevel = i2;
                DLog.logf("Upgrading performance level to %s due to sustained playback performance.", Integer.valueOf(i2));
                this.mSessionState.setSustainableQualityLevel(getQualityLevelForDegradation());
                scheduleUpshift();
            }
        }
    }

    private boolean isDegradedPerformanceReport(PlaybackPerformanceReport playbackPerformanceReport) {
        return playbackPerformanceReport != null && (playbackPerformanceReport.getSeverity() == PlaybackPerformanceReport.PlaybackPerformanceSeverity.CRITICAL || playbackPerformanceReport.getSeverity() == PlaybackPerformanceReport.PlaybackPerformanceSeverity.MAJOR);
    }

    private boolean isSustainablePerformanceReport(@Nullable PlaybackPerformanceReport playbackPerformanceReport) {
        return playbackPerformanceReport == null || playbackPerformanceReport.getSeverity() == PlaybackPerformanceReport.PlaybackPerformanceSeverity.NONE;
    }

    private boolean isReportStale(@Nullable PlaybackPerformanceReport playbackPerformanceReport, @Nonnull TimeSpan timeSpan, @Nonnull TimeSpan timeSpan2) {
        return playbackPerformanceReport == null || TimeSpan.add(playbackPerformanceReport.getTimeStamp(), timeSpan2).lessThan(timeSpan);
    }

    private boolean isNewReportMoreRecent(@Nullable PlaybackPerformanceReport playbackPerformanceReport, @Nullable PlaybackPerformanceReport playbackPerformanceReport2) {
        return playbackPerformanceReport != null && (playbackPerformanceReport2 == null || playbackPerformanceReport.getTimeStamp().greaterThan(playbackPerformanceReport2.getTimeStamp()));
    }
}
