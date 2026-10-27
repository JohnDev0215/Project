package com.amazon.music.skyfire.ui.skyfire;

import CoreInterface.v1_0.Queue;
import QueuesInterface.v1_0.ImmutableMultiThreadedQueue;
import QueuesInterface.v1_0.ImmutableSingleThreadedQueue;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Queues.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Queues;", "", "()V", "multiThreaded", "LCoreInterface/v1_0/Queue;", "getMultiThreaded", "()LCoreInterface/v1_0/Queue;", "singleTheaded", "getSingleTheaded", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class Queues {
    public static final Queues INSTANCE = new Queues();
    private static final Queue multiThreaded;
    private static final Queue singleTheaded;

    private Queues() {
    }

    public final Queue getSingleTheaded() {
        return singleTheaded;
    }

    static {
        ImmutableSingleThreadedQueue immutableSingleThreadedQueueBuild = ImmutableSingleThreadedQueue.builder().id("ST_HTTP").build();
        Intrinsics.checkNotNullExpressionValue(immutableSingleThreadedQueueBuild, "builder()\n            .i…TP\")\n            .build()");
        singleTheaded = immutableSingleThreadedQueueBuild;
        ImmutableMultiThreadedQueue immutableMultiThreadedQueueBuild = ImmutableMultiThreadedQueue.builder().id("MT_HTTP").build();
        Intrinsics.checkNotNullExpressionValue(immutableMultiThreadedQueueBuild, "builder()\n            .i…TP\")\n            .build()");
        multiThreaded = immutableMultiThreadedQueueBuild;
    }

    public final Queue getMultiThreaded() {
        return multiThreaded;
    }
}
