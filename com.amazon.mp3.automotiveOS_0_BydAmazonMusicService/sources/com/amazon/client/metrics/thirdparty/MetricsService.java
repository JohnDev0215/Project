package com.amazon.client.metrics.thirdparty;

import com.amazon.client.metrics.thirdparty.batch.creator.BatchCreator;
import com.amazon.client.metrics.thirdparty.batch.creator.PriorityChannelPair;
import com.amazon.client.metrics.thirdparty.batch.transmitter.BatchTransmitter;
import com.amazon.device.utils.thirdparty.DeviceUtil;
import com.amazon.dp.logger.DPLogger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MetricsService implements UploadIntentListener {
    private static final DPLogger log = new DPLogger();
    private final Map<PriorityChannelPair, BatchCreator> mBatchCreatorMap;
    private final List<BatchTransmitter> mBatchTransmitterList;
    private final DeviceUtil mDeviceUtil;

    public MetricsService(DeviceUtil deviceUtil, Map<PriorityChannelPair, BatchCreator> map, List<BatchTransmitter> list) {
        if (deviceUtil == null) {
            throw new NullPointerException("DeviceUtil can not be null");
        }
        if (map == null) {
            throw new NullPointerException("BatchCreatorMap can not be null");
        }
        if (list == null) {
            throw new NullPointerException("BatchTransmitter list can not be null");
        }
        this.mDeviceUtil = deviceUtil;
        this.mBatchCreatorMap = map;
        this.mBatchTransmitterList = list;
        MetricsBroadcastReceiver.addUploadIntentListener(this);
    }

    public void record(MetricEntry metricEntry, Priority priority, Channel channel) {
        BatchCreator batchCreator = this.mBatchCreatorMap.get(new PriorityChannelPair(priority, channel));
        if (batchCreator != null) {
            batchCreator.addMetricEntry(metricEntry);
        } else {
            log.error("record", String.format("Metric dropped. No batch pipeline exists for priority %s and channel %s", priority, channel), new Object[0]);
        }
    }

    public void shutdown() {
        log.info("shutdown", "Enqueuing all batches and shutting down batch creators and transmitters.", new Object[0]);
        for (BatchCreator batchCreator : this.mBatchCreatorMap.values()) {
            try {
                batchCreator.enqueueBatchForTransmission();
            } catch (Exception e) {
                log.error("shutdown", "Exception enqueuing batches:", e.getMessage());
            }
            batchCreator.shutdown();
        }
        Iterator<BatchTransmitter> it = this.mBatchTransmitterList.iterator();
        while (it.hasNext()) {
            it.next().shutdown();
        }
        MetricsBroadcastReceiver.shutdown();
    }

    @Override // com.amazon.client.metrics.thirdparty.UploadIntentListener
    public void onUploadIntentReceived() {
        log.info("onUploadIntentReceived", "Enqueuing all batches and triggering transmission.", new Object[0]);
        Iterator<BatchCreator> it = this.mBatchCreatorMap.values().iterator();
        while (it.hasNext()) {
            it.next().enqueueBatchForTransmission();
        }
        Iterator<BatchTransmitter> it2 = this.mBatchTransmitterList.iterator();
        while (it2.hasNext()) {
            it2.next().transmitBatches(true);
        }
    }
}
