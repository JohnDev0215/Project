package com.amazon.music.skyfire.platform.core;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MultiThreadedQueueImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0002\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/skyfire/platform/core/MultiThreadedQueueImpl;", "Lcom/amazon/music/skyfire/platform/core/MultiThreadedQueue;", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "DMSFCoreKMM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MultiThreadedQueueImpl extends MultiThreadedQueue {
    private final String id;

    public MultiThreadedQueueImpl(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
    }

    public final String getId() {
        return this.id;
    }

    @Override // com.amazon.music.skyfire.platform.core.Queue
    public String id() {
        return this.id;
    }
}
