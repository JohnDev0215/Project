package com.amazon.client.metrics.thirdparty.codec;

import com.amazon.client.metrics.thirdparty.CodecException;
import com.amazon.client.metrics.thirdparty.DataPoint;
import com.amazon.client.metrics.thirdparty.DataPointType;
import com.amazon.client.metrics.thirdparty.MetricEntry;
import com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage;

/* JADX INFO: loaded from: classes2.dex */
public class MetricEntryProtocolBuffersCodec implements MetricEntryCodec {
    @Override // com.amazon.client.metrics.thirdparty.codec.MetricEntryCodec
    public EncodedMetricEntry encode(MetricEntry metricEntry) throws CodecException {
        if (metricEntry == null) {
            throw new CodecException("Metric entry is null");
        }
        if (metricEntry.getDatapoints().size() == 0) {
            throw new CodecException("Metric entry contains no data points");
        }
        DeviceMetricsMessage.MetricEntryMessage.Builder timestamp = DeviceMetricsMessage.MetricEntryMessage.newBuilder().setProgram(metricEntry.getProgram()).setSource(metricEntry.getSource()).setTimestamp(metricEntry.getTimestamp());
        for (DataPoint dataPoint : metricEntry.getDatapoints()) {
            timestamp.addDataPoint(DeviceMetricsMessage.DataPointMessage.newBuilder().setName(dataPoint.getName()).setValue(dataPoint.getValue()).setSampleSize(dataPoint.getSamples()).setType(getDataType(dataPoint.getType())).build());
        }
        return new ProtocolBuffersEncodedMetricEntry(timestamp.build());
    }

    /* JADX INFO: renamed from: com.amazon.client.metrics.thirdparty.codec.MetricEntryProtocolBuffersCodec$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$DataPointType;

        static {
            int[] iArr = new int[DataPointType.values().length];
            $SwitchMap$com$amazon$client$metrics$thirdparty$DataPointType = iArr;
            try {
                iArr[DataPointType.CT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$DataPointType[DataPointType.TI.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$DataPointType[DataPointType.DV.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$DataPointType[DataPointType.CK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static DeviceMetricsMessage.DataPointMessage.DataType getDataType(DataPointType dataPointType) throws CodecException {
        int i = AnonymousClass1.$SwitchMap$com$amazon$client$metrics$thirdparty$DataPointType[dataPointType.ordinal()];
        if (i == 1) {
            return DeviceMetricsMessage.DataPointMessage.DataType.COUNTER;
        }
        if (i == 2) {
            return DeviceMetricsMessage.DataPointMessage.DataType.TIMER;
        }
        if (i == 3) {
            return DeviceMetricsMessage.DataPointMessage.DataType.DISCRETE;
        }
        if (i == 4) {
            return DeviceMetricsMessage.DataPointMessage.DataType.CLICKSTREAM;
        }
        throw new CodecException("Invalid DataPoint type");
    }
}
