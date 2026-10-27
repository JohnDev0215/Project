package com.amazon.client.metrics.thirdparty.codec;

import com.amazon.client.metrics.thirdparty.CodecException;
import com.amazon.client.metrics.thirdparty.MetricBatch;
import com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MetricBatchProtocolBuffersCodec implements MetricBatchCodec {
    @Override // com.amazon.client.metrics.thirdparty.codec.MetricBatchCodec
    public byte[] encode(MetricBatch metricBatch) throws CodecException {
        if (metricBatch == null) {
            throw new CodecException("MetricEntryBatch is null");
        }
        if (metricBatch.getBatchSizeInBytes() == 0) {
            throw new CodecException("MetricEntryBatch is empty");
        }
        DeviceMetricsMessage.MetricBatchMessage.Builder builderNewBuilder = DeviceMetricsMessage.MetricBatchMessage.newBuilder();
        builderNewBuilder.setDeviceSerialNumber(metricBatch.getDeviceSerialNumber());
        if (metricBatch.getDeviceType() != null) {
            builderNewBuilder.setDeviceType(metricBatch.getDeviceType());
        }
        for (Map.Entry<String, String> entry : metricBatch.getDeviceInfoMap().entrySet()) {
            if (entry.getValue() != null) {
                builderNewBuilder.addMetadata(DeviceMetricsMessage.KeyValue.newBuilder().setKey(entry.getKey()).setValue(entry.getValue()).build());
            }
        }
        for (int i = 0; i < metricBatch.getMetricEntryCount(); i++) {
            builderNewBuilder.addMetricEntry((DeviceMetricsMessage.MetricEntryMessage) metricBatch.getMetricEntry(i).getEncodedMetricEntry());
        }
        byte[] byteArray = builderNewBuilder.build().toByteArray();
        if (byteArray == null) {
            throw new CodecException("serialized batch is null");
        }
        if (byteArray.length != 0) {
            return byteArray;
        }
        throw new CodecException("serialized batch is empty");
    }
}
