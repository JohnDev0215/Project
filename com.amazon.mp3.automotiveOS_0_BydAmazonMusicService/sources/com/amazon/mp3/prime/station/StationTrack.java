package com.amazon.mp3.prime.station;

import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.TrackType;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.music.station.SynchronizedTrackItem;

/* JADX INFO: loaded from: classes4.dex */
public final class StationTrack extends MusicTrack {
    private final SynchronizedTrackItem mTrackItem;

    public StationTrack(final SynchronizedTrackItem trackItem) {
        this.mTrackItem = trackItem;
        setTitle(trackItem.getTitle());
        setAsin(trackItem.getIdentifier());
        setAlbumArtUrl(trackItem.getAlbumArtImageUrl());
        setArtistName(trackItem.getArtistName());
        setArtistAsin(trackItem.getTrackArtistAsin());
        setAlbumName(trackItem.getAlbumTitle());
        setAlbumAsin(trackItem.getAlbumAsin());
        setDuration(trackItem.getTrackDuration().longValue());
        setTrackType(TrackType.STATION);
        setAlbumArtistName(trackItem.getArtistName());
        setContentUri(MediaProvider.Tracks.getPrimeAdditionalTrackContentUri("cirrus", "stations", getAsin()));
        setMatchHash((getAsin().hashCode() * 31) + trackItem.getMarketplace().hashCode());
        setOwnershipStatus(ContentOwnershipStatus.NOT_IN_LIBRARY);
        Boolean boolIsPrime = trackItem.isPrime();
        Boolean boolIsMusicSubscription = trackItem.isMusicSubscription();
        if (boolIsPrime != null && boolIsPrime.booleanValue()) {
            setContentCatalogStatus(ContentCatalogStatus.PRIME);
        } else if (boolIsMusicSubscription != null && boolIsMusicSubscription.booleanValue()) {
            setContentCatalogStatus(ContentCatalogStatus.HAWKFIRE);
        } else {
            setContentCatalogStatus(ContentCatalogStatus.PREVIOUSLY_CATALOG);
        }
        setIsExplicit(trackItem.isExplicit());
        if (trackItem.getMarketplace() != null) {
            setMarketplace(trackItem.getMarketplace().getObfuscatedId());
        }
    }

    @Override // com.amazon.mp3.library.item.MusicTrack
    public void setContentCatalogStatus(ContentCatalogStatus catalogStatus) {
        this.mCatalogStatus = catalogStatus;
    }

    public SynchronizedTrackItem getTrackItem() {
        return this.mTrackItem;
    }
}
