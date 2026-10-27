package com.amazon.minerva.client.thirdparty.serializer;

import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface MetricBatchSerializer {
    List<IonMetricEvent> deserialize(byte[] bArr) throws IOException;

    byte[] serialize(List<IonMetricEvent> list) throws IOException;
}
