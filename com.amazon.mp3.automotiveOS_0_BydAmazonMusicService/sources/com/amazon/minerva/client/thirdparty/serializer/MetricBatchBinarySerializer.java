package com.amazon.minerva.client.thirdparty.serializer;

import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MetricBatchBinarySerializer implements MetricBatchSerializer {
    @Override // com.amazon.minerva.client.thirdparty.serializer.MetricBatchSerializer
    public byte[] serialize(List<IonMetricEvent> list) throws IOException {
        return IonMetricEventConverter.convertIonMetricEventsToIonBinary(list);
    }

    @Override // com.amazon.minerva.client.thirdparty.serializer.MetricBatchSerializer
    public List<IonMetricEvent> deserialize(byte[] bArr) throws IOException {
        return IonMetricEventConverter.convertIonBinaryToIonMetricEvents(bArr);
    }
}
