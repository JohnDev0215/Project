package com.amazon.music.station;

import com.amazon.musicplayqueueservice.client.common.RateEntityRequest;
import com.android.volley.VolleyError;

/* JADX INFO: loaded from: classes4.dex */
final class RateTrackWorker implements Runnable {
    private final int millisecondsPlayedBeforeRating;
    private final Rating rating;
    private final StationService stationService;
    private final SynchronizedTrackItem trackItem;

    RateTrackWorker(StationService stationService, SynchronizedTrackItem synchronizedTrackItem, Rating rating, int i) {
        this.stationService = stationService;
        this.trackItem = synchronizedTrackItem;
        this.rating = rating;
        this.millisecondsPlayedBeforeRating = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            RateEntityRequest rateEntityRequest = new RateEntityRequest();
            rateEntityRequest.setIdentifier(this.trackItem.getTrackId());
            rateEntityRequest.setRating(this.rating.name());
            rateEntityRequest.setDurationPlayedInMilliseconds(this.millisecondsPlayedBeforeRating);
            rateEntityRequest.setUtcTimeStamp(System.currentTimeMillis());
            this.stationService.rateEntity(rateEntityRequest);
        } catch (VolleyError unused) {
        }
    }
}
