package com.amazon.music.station;

import androidx.annotation.AnyThread;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.musicplayqueueservice.client.common.QueueMetadata;

/* JADX INFO: loaded from: classes4.dex */
@AnyThread
public final class Station {
    private final StationCache stationCache;

    public static final Station create(Marketplace marketplace, StationItem stationItem, StationService stationService, boolean z, String str) {
        return new Station(StationCache.create(marketplace, stationItem, stationService, z, str));
    }

    @GuardedBy("this")
    public synchronized SynchronizedTrackItem nextTrack() throws NoNextTrackException, InterruptedException {
        return this.stationCache.nextTrack();
    }

    @GuardedBy("this")
    public synchronized SynchronizedTrackItem peekTrack() throws NoNextTrackException, InterruptedException {
        return this.stationCache.peekTrack();
    }

    private Station(StationCache stationCache) {
        this.stationCache = stationCache;
    }

    public String getStationFromAnythingSeedID() {
        return this.stationCache.getStationFromAnythingSeedID();
    }

    @Nullable
    public QueueMetadata getQueueMetadata() {
        return this.stationCache.getQueueMetadata();
    }
}
