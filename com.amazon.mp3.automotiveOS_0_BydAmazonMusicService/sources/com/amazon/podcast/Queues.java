package com.amazon.podcast;

import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SingleThreadedQueue;
import com.amazon.music.skyfire.ui.skyfire.Contexts;

/* JADX INFO: loaded from: classes5.dex */
public final class Queues {
    public static Queue template() {
        return SingleThreadedQueue.builder().withId("TEMPLATE").build();
    }

    public static Queue playback() {
        return SingleThreadedQueue.builder().withId(Contexts.Upsell.playback).build();
    }

    public static Queue jumpBackIn() {
        return SingleThreadedQueue.builder().withId("JUMPBACKIN").build();
    }

    public static Queue appSync() {
        return SingleThreadedQueue.builder().withId("APPSYNC").build();
    }

    public static final Queue bookmarkSync() {
        return SingleThreadedQueue.builder().withId("BOOKMARK_SYNC").build();
    }

    public static final Queue followSync() {
        return SingleThreadedQueue.builder().withId("FOLLOW_SYNC").build();
    }

    public static final Queue saveSync() {
        return SingleThreadedQueue.builder().withId("SAVE_SYNC").build();
    }

    public static final Queue latestSync() {
        return SingleThreadedQueue.builder().withId("LATEST_SYNC").build();
    }

    public static final Queue downloadSync() {
        return SingleThreadedQueue.builder().withId("DOWNLOAD_SYNC").build();
    }

    public static final Queue playbackMetricsSync() {
        return SingleThreadedQueue.builder().withId("PLAYBACK_METRICS_SYNC").build();
    }

    public static final Queue uiMetricsSync() {
        return SingleThreadedQueue.builder().withId("UI_METRICS_SYNC").build();
    }

    public static final Queue completedSync() {
        return SingleThreadedQueue.builder().withId("COMPLETED_SYNC").build();
    }

    public static final Queue categoryFollowSync() {
        return SingleThreadedQueue.builder().withId("CATEGORY_FOLLOW_SYNC").build();
    }
}
