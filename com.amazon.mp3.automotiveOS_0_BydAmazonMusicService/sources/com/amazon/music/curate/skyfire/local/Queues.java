package com.amazon.music.curate.skyfire.local;

import CoreInterface.v1_0.Queue;
import QueuesInterface.v1_0.ImmutableSingleThreadedQueue;

/* JADX INFO: loaded from: classes4.dex */
class Queues {
    Queues() {
    }

    public static Queue template() {
        return ImmutableSingleThreadedQueue.builder().id("TEMPLATE").build();
    }
}
