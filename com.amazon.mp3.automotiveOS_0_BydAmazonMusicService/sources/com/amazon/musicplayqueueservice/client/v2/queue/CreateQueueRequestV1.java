package com.amazon.musicplayqueueservice.client.v2.queue;

import com.amazon.musicplayqueueservice.client.common.AbstractClientRequest;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class CreateQueueRequestV1 extends AbstractCreateQueueRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.queue.CreateQueueRequestV1");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.client.v2.queue.AbstractCreateQueueRequest, com.amazon.musicplayqueueservice.client.common.AbstractClientRequest, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractClientRequest abstractClientRequest) {
        if (abstractClientRequest == null) {
            return -1;
        }
        if (abstractClientRequest == this) {
            return 0;
        }
        if (abstractClientRequest instanceof CreateQueueRequestV1) {
            return super.compareTo(abstractClientRequest);
        }
        return 1;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.queue.AbstractCreateQueueRequest, com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public boolean equals(Object obj) {
        if (obj instanceof CreateQueueRequestV1) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.queue.AbstractCreateQueueRequest, com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
