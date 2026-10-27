package com.amazon.mp3.cloudqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes3.dex */
public class RateTrackManager {
    private final CloudQueueService cloudQueueService;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public RateTrackManager(final CloudQueueService cloudQueueService) {
        this.cloudQueueService = cloudQueueService;
    }

    public void rateTrack(final String trackId, final String queueId, final Rating rating, final int millisecondsPlayedBeforeRating) {
        Validate.notNull(trackId, "trackItem can't be null", new Object[0]);
        Validate.notNull(rating, "rating can't be null", new Object[0]);
        Validate.isTrue(millisecondsPlayedBeforeRating >= 0, "millisecondsPlayedBeforeRating can't be negative", new Object[0]);
        this.executorService.submit(new RateTrackWorker(this.cloudQueueService, trackId, queueId, rating, millisecondsPlayedBeforeRating));
    }
}
