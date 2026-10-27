package com.amazon.client.metrics.thirdparty.batch.queue;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public interface ByteArrayQueue {
    void add(SerializedBatch serializedBatch, boolean z) throws IOException;

    void addFirst(SerializedBatch serializedBatch, boolean z) throws IOException;

    void addListener(ByteArrayQueueListener byteArrayQueueListener);

    String getQueueName();

    void persistBatches() throws IOException;

    SerializedBatch remove() throws IOException;

    void setQueueName(String str);

    void shutdown();
}
