package com.amazon.avod.qos.reporter.internal;

import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.qos.QoSConfig;
import com.amazon.avod.qos.internal.metrics.QoSMetric;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.qos.reporter.SmoothStreamingReporter;
import com.amazon.avod.util.DLog;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamingEventReporter extends EventReporterBase implements SmoothStreamingReporter {
    private final List<Integer> mBandwidths;
    private final int mBatchSize;
    private final List<Integer> mBitrates;
    private long mBufferFullnessMillis;
    private final List<Long> mBufferFullnesses;
    private final MediaSystemSharedContext mContext;
    private final List<Long> mDownloadTimes;
    private int mFragmentCount;
    private final List<Integer> mFragments;
    private final Joiner mJoiner;
    private long mMaxBufferFullnessMillis;
    private final List<Long> mMaxBufferSizes;
    private int mMinBitrateKbps;
    private final Random mRandom;
    private final int mReportingProbability;

    public SmoothStreamingEventReporter(@Nonnull QoSConfig qoSConfig, @Nonnull QoSMetricEventSender qoSMetricEventSender, @Nonnull MediaSystemSharedContext mediaSystemSharedContext) {
        super(qoSMetricEventSender);
        this.mBitrates = new LinkedList();
        this.mDownloadTimes = new LinkedList();
        this.mBufferFullnesses = new LinkedList();
        this.mMaxBufferSizes = new LinkedList();
        this.mFragments = new LinkedList();
        this.mBandwidths = new LinkedList();
        this.mMinBitrateKbps = Integer.MAX_VALUE;
        this.mJoiner = Joiner.on(',');
        this.mFragmentCount = 0;
        this.mBufferFullnessMillis = 0L;
        this.mBatchSize = qoSConfig.getFragmentBatchSize();
        this.mReportingProbability = qoSConfig.getFragmentBatchUploadProbability();
        this.mRandom = new Random();
        this.mContext = (MediaSystemSharedContext) Preconditions.checkNotNull(mediaSystemSharedContext, "appContext");
    }

    @Override // com.amazon.avod.qos.reporter.SmoothStreamingReporter
    public void setMaxBufferFullnessMillis(long j) {
        this.mMaxBufferFullnessMillis = j;
    }

    @Override // com.amazon.avod.qos.reporter.SmoothStreamingReporter
    public void reportSessionEnd(TimeSpan timeSpan) {
        if (this.mFragmentCount != 0) {
            uploadBatch(timeSpan, null);
        }
        if (this.mMinBitrateKbps != Integer.MAX_VALUE) {
            reportMetric(QoSMetric.Information, getMetricsBuilder().eventSubtype("LowestVideoBitrateDelivered").note(String.valueOf(this.mMinBitrateKbps)));
        }
    }

    @Override // com.amazon.avod.qos.reporter.SmoothStreamingReporter
    public void reportBufferFullnessInNanos(long j) {
        this.mBufferFullnessMillis = TimeUnit.NANOSECONDS.toMillis(j);
    }

    @Nullable
    private String buildJsonHeuristicsNote(@Nullable String str) {
        NetworkConnectionManager networkConnectionManager = NetworkConnectionManager.getInstance();
        int signalStrengthRssi = networkConnectionManager == null ? -1 : networkConnectionManager.getSignalStrengthRssi();
        int cellSignalStrength = this.mContext.getCellSignalStrength();
        try {
            JSONObject jSONObject = str == null ? new JSONObject() : new JSONObject(str);
            jSONObject.put("wifiRssi", signalStrengthRssi);
            jSONObject.put("cellSignalStrengthDb", cellSignalStrength);
            return jSONObject.toString();
        } catch (JSONException e) {
            DLog.warnf(e.getMessage());
            return null;
        }
    }

    @Override // com.amazon.avod.qos.reporter.SmoothStreamingReporter
    public void reportFragment(int i, int i2, int i3, long j, long j2, TimeSpan timeSpan, @Nullable String str) {
        getReporterContext().updateStreamingStatistics(i2, i3, this.mBufferFullnessMillis, j2, this.mMaxBufferFullnessMillis, j);
        this.mBitrates.add(Integer.valueOf(i3));
        this.mDownloadTimes.add(Long.valueOf(j2));
        this.mBufferFullnesses.add(Long.valueOf(this.mBufferFullnessMillis));
        this.mMaxBufferSizes.add(Long.valueOf(this.mMaxBufferFullnessMillis));
        this.mBandwidths.add(Integer.valueOf(i2));
        this.mFragments.add(Integer.valueOf(i));
        this.mMinBitrateKbps = Math.min(this.mMinBitrateKbps, i3);
        int i4 = this.mFragmentCount + 1;
        this.mFragmentCount = i4;
        if (i4 == this.mBatchSize) {
            uploadBatch(timeSpan, buildJsonHeuristicsNote(str));
        }
    }

    private void uploadBatch(TimeSpan timeSpan, @Nullable String str) {
        if (this.mReportingProbability > this.mRandom.nextInt(100)) {
            MetricsBuilder metricsBuilder = getMetricsBuilder();
            MetricsBuilder metricsBuilderMillisecondsStreamed = metricsBuilder.bitrateConsumption(this.mJoiner.join(this.mBitrates)).bufferFullnesses(this.mJoiner.join(this.mBufferFullnesses)).downloadTimes(this.mJoiner.join(this.mDownloadTimes)).fragments(this.mJoiner.join(this.mFragments)).fragmentBandwidths(this.mJoiner.join(this.mBandwidths)).maxBufferSizes(this.mJoiner.join(this.mMaxBufferSizes)).millisecondsStreamed(Long.valueOf(getReporterContext().getPlayedDuration(timeSpan)));
            if (Strings.isNullOrEmpty(str)) {
                str = "";
            }
            metricsBuilderMillisecondsStreamed.note(str);
            reportMetric(QoSMetric.SmoothStreaming, metricsBuilder);
        }
        this.mFragmentCount = 0;
        this.mBitrates.clear();
        this.mDownloadTimes.clear();
        this.mBufferFullnesses.clear();
        this.mMaxBufferSizes.clear();
        this.mFragments.clear();
        this.mBandwidths.clear();
    }
}
