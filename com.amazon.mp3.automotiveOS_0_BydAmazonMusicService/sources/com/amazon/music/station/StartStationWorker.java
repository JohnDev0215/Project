package com.amazon.music.station;

import com.amazon.musicplayqueueservice.client.common.AllowedParentalControls;
import com.amazon.musicplayqueueservice.client.common.CreateQueueRequest;
import com.amazon.musicplayqueueservice.client.common.CreateQueueResponse;
import com.android.volley.VolleyError;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
final class StartStationWorker implements Runnable {
    private final boolean isExplicitFilterEnabled;
    private final String remoteTargetId;
    private final StationCache stationCache;
    private final StationService stationService;

    StartStationWorker(StationCache stationCache, StationService stationService, boolean z, String str) {
        this.stationCache = stationCache;
        this.stationService = stationService;
        this.isExplicitFilterEnabled = z;
        this.remoteTargetId = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.remoteTargetId != null) {
                startStation();
                return;
            }
            this.stationCache.onCacheWorkerActive();
            CreateQueueResponse createQueueResponseStartStation = startStation();
            this.stationCache.onCacheUpdated(createQueueResponseStartStation.getQueue().getQueueId(), createQueueResponseStartStation.getQueue().getPageToken(), createQueueResponseStartStation.getQueue().getQueueMetadata(), SynchronizedTrackItem.createAll(createQueueResponseStartStation.getTrackMetadataList(), this.stationCache.getMarketplace(), createQueueResponseStartStation.getQueue().getQueueId()));
        } catch (Exception e) {
            this.stationCache.interrupt(new NoNextTrackException(e));
        }
    }

    private CreateQueueResponse startStation() throws VolleyError {
        return this.stationService.startStation(getCreateQueueRequest());
    }

    public CreateQueueRequest getCreateQueueRequest() {
        CreateQueueRequest createQueueRequest = new CreateQueueRequest();
        if (this.stationCache.getStationItem().getIdentifier() != null && this.stationCache.getStationItem().getIdentifierType() != null) {
            createQueueRequest.setIdentifier(this.stationCache.getStationItem().getIdentifier());
            createQueueRequest.setIdentifierType(this.stationCache.getStationItem().getIdentifierType());
        } else if (this.stationCache.getStationItem().getIdentifiers() != null && this.stationCache.getStationItem().getIdentifierType() != null) {
            createQueueRequest.setIdentifiers(this.stationCache.getStationItem().getIdentifiers());
            createQueueRequest.setIdentifierType(this.stationCache.getStationItem().getIdentifierType());
        } else {
            createQueueRequest.setIdentifier(this.stationCache.getStationItem().getKey());
            createQueueRequest.setIdentifierType("STATION_KEY");
        }
        createQueueRequest.setTargetId(this.remoteTargetId);
        boolean z = !this.isExplicitFilterEnabled;
        AllowedParentalControls allowedParentalControls = new AllowedParentalControls();
        allowedParentalControls.setHasExplicitLanguage(Boolean.valueOf(z));
        createQueueRequest.setAllowedParentalControls(allowedParentalControls);
        createQueueRequest.setSupportedFeatures(Arrays.asList("INTERLUDE_CONTENT"));
        return createQueueRequest;
    }
}
