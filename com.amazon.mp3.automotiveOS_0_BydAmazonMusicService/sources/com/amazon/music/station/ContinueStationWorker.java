package com.amazon.music.station;

import com.amazon.music.station.NoNextTrackException;
import com.amazon.musicplayqueueservice.client.common.GetNextTracksRequest;
import com.amazon.musicplayqueueservice.client.common.GetNextTracksResponse;

/* JADX INFO: loaded from: classes4.dex */
final class ContinueStationWorker implements Runnable {
    private final StationCache stationCache;
    private final StationService stationService;

    ContinueStationWorker(StationCache stationCache, StationService stationService) {
        this.stationCache = stationCache;
        this.stationService = stationService;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.stationCache.onCacheWorkerActive();
        if (this.stationCache.getPageToken() == null) {
            this.stationCache.interrupt(new NoNextTrackException(NoNextTrackException.Reason.END_OF_STATION));
            return;
        }
        try {
            GetNextTracksRequest getNextTracksRequest = new GetNextTracksRequest();
            getNextTracksRequest.setNumberOfTracks(5);
            getNextTracksRequest.setPageToken(this.stationCache.getPageToken());
            GetNextTracksResponse tracksForStation = this.stationService.getTracksForStation(getNextTracksRequest);
            this.stationCache.onCacheUpdated(tracksForStation.getNextPageToken(), SynchronizedTrackItem.createAll(tracksForStation.getTrackMetadataList(), this.stationCache.getMarketplace(), this.stationCache.getStationId()));
        } catch (Exception e) {
            this.stationCache.interrupt(new NoNextTrackException(e));
        }
    }
}
