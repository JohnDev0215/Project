package com.amazon.device.crashmanager.processor;

import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil;
import com.amazon.device.utils.det.DetUtil;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultArtifactProcessorFactory implements ArtifactProcessorFactory {
    private final CrashDescriptorDedupeUtil mCrashDedupeUtil;
    private final DetUtil mDetUtil;
    private final String mDeviceType;
    private final Map<String, String> mExtraInfo;
    private final MetricEvent mMetricEvent;
    private final MetricsHeaderProcessorFactory mMetricsHeaderProcessorFactory;

    public DefaultArtifactProcessorFactory(DetUtil detUtil, String str, Map<String, String> map, MetricsHeaderProcessorFactory metricsHeaderProcessorFactory, CrashDescriptorDedupeUtil crashDescriptorDedupeUtil, MetricEvent metricEvent) {
        if (detUtil == null) {
            throw new IllegalArgumentException("DetUtil must not be null.");
        }
        if (str == null) {
            throw new IllegalArgumentException("DeviceType must not be null.");
        }
        if (metricsHeaderProcessorFactory == null) {
            throw new IllegalArgumentException("MetricsHeaderProcessorFactory must not be null.");
        }
        if (crashDescriptorDedupeUtil == null) {
            throw new IllegalArgumentException("DuplicateCrashCounts must not be null.");
        }
        this.mDetUtil = detUtil;
        this.mDeviceType = str;
        this.mExtraInfo = map;
        this.mMetricsHeaderProcessorFactory = metricsHeaderProcessorFactory;
        this.mCrashDedupeUtil = crashDescriptorDedupeUtil;
        this.mMetricEvent = metricEvent;
    }

    @Override // com.amazon.device.crashmanager.processor.ArtifactProcessorFactory
    public List<ArtifactProcessor> constructArtifactProcessors() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(new JavaCrashArtifactProcessor(this.mDetUtil, this.mDeviceType, this.mExtraInfo, this.mMetricsHeaderProcessorFactory, this.mCrashDedupeUtil));
        linkedList.add(new AnrArtifactProcessor(this.mDetUtil, this.mDeviceType, this.mExtraInfo, this.mMetricsHeaderProcessorFactory, this.mCrashDedupeUtil));
        linkedList.add(new SmvArtifactProcessor(this.mDetUtil, this.mDeviceType, this.mExtraInfo, this.mMetricsHeaderProcessorFactory, this.mCrashDedupeUtil));
        linkedList.add(new NativeCrashArtifactProcessor(this.mDetUtil, this.mDeviceType, this.mExtraInfo, this.mMetricsHeaderProcessorFactory, this.mCrashDedupeUtil));
        linkedList.add(new DiagnosticArtifactProcessor(this.mDetUtil, this.mDeviceType, this.mExtraInfo));
        linkedList.add(new RamDumpArtifactProcessor(this.mDetUtil, this.mDeviceType, this.mExtraInfo));
        linkedList.add(new PinFailuresArtifactProcessor(this.mDetUtil, this.mDeviceType, this.mExtraInfo, this.mMetricEvent, this.mCrashDedupeUtil));
        linkedList.add(new DefaultArtifactProcessor(this.mDetUtil, this.mDeviceType, this.mExtraInfo));
        return linkedList;
    }
}
