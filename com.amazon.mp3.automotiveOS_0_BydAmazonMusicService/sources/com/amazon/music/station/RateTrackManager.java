package com.amazon.music.station;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public final class RateTrackManager {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final StationService stationService;

    public RateTrackManager(StationService stationService) {
        this.stationService = stationService;
    }

    public void rateTrack(SynchronizedTrackItem synchronizedTrackItem, Rating rating, int i) {
        Validate.notNull(synchronizedTrackItem, "trackItem can't be null", new Object[0]);
        Validate.notNull(rating, "rating can't be null", new Object[0]);
        Validate.isTrue(i >= 0, "millisecondsPlayedBeforeRating can't be negative", new Object[0]);
        this.executorService.submit(new RateTrackWorker(this.stationService, synchronizedTrackItem, synchronizedTrackItem.changeTrackRating(rating), i));
    }
}
