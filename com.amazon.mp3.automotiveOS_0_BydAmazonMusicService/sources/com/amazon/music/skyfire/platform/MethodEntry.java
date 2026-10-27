package com.amazon.music.skyfire.platform;

import com.amazon.music.skyfire.platform.core.Queue;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MethodEntry.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0002\u0010\u0007R\u0013\u0010\u0006\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/skyfire/platform/MethodEntry;", "T", "", "ownerId", "", "methodId", "method", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "getMethod", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getMethodId", "()Ljava/lang/String;", "getOwnerId", "queue", "Lcom/amazon/music/skyfire/platform/core/Queue;", "getQueue", "()Lcom/amazon/music/skyfire/platform/core/Queue;", "setQueue", "(Lcom/amazon/music/skyfire/platform/core/Queue;)V", "DMSFCoreKMM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MethodEntry<T> {
    private final T method;
    private final String methodId;
    private final String ownerId;
    public Queue queue;

    public MethodEntry(String ownerId, String methodId, T method) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(methodId, "methodId");
        Intrinsics.checkNotNullParameter(method, "method");
        this.ownerId = ownerId;
        this.methodId = methodId;
        this.method = method;
    }

    public final T getMethod() {
        return this.method;
    }

    public final String getMethodId() {
        return this.methodId;
    }

    public final String getOwnerId() {
        return this.ownerId;
    }

    public final Queue getQueue() {
        Queue queue = this.queue;
        if (queue != null) {
            return queue;
        }
        Intrinsics.throwUninitializedPropertyAccessException("queue");
        return null;
    }

    public final void setQueue(Queue queue) {
        Intrinsics.checkNotNullParameter(queue, "<set-?>");
        this.queue = queue;
    }
}
