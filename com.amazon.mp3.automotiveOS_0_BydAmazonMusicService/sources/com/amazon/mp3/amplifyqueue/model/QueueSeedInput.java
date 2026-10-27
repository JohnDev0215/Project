package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class QueueSeedInput {
    private final AccountSeedInput account;
    private final LibraryAlbumSeedInput libraryAlbum;
    private final LibraryArtistSeedInput libraryArtist;
    private final OpaqueTrackSearchSeedInput opaqueTrackSearch;
    private final PlayModeEnum playMode;
    private final String playModeEnumValue;
    private final PlayableEntitySeedInput playableEntity;
    private final PodcastJumpBackInSeedInput podcastJumpBackIn;
    private final PodcastLatestSeedInput podcastLatest;
    private final PodcastSavedSeedInput podcastSaved;
    private final PodcastShowSeedInput podcastShow;
    private final QueueIdHistoricalPlaybackSeedInput queueIdHistoricalPlayback;
    private final QueueIdTransferSeedInput queueIdTransfer;
    private final QueueSeedTypeEnum queueSeedType;
    private final String queueSeedTypeEnumValue;
    private final SearchSeedInput search;
    private final SmartLibraryPlaylistSeedInput smartLibraryPlaylist;
    private final SourceDeviceTransferSeedInput sourceDeviceTransfer;
    private final StationSeedInput station;
    private final TrackListSeedInput trackList;
    private final VideoChannelSeedInput videoChannel;

    public interface BuildStep {
    }

    public static class Builder implements QueueSeedTypeStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface QueueSeedTypeStep {
    }

    public PlayModeEnum getPlayMode() {
        return this.playMode;
    }

    public String getPlayModeEnumValue() {
        return this.playModeEnumValue;
    }

    public QueueSeedTypeEnum getQueueSeedType() {
        return this.queueSeedType;
    }

    public String getQueueSeedTypeEnumValue() {
        return this.queueSeedTypeEnumValue;
    }

    public QueueIdTransferSeedInput getQueueIdTransfer() {
        return this.queueIdTransfer;
    }

    public QueueIdHistoricalPlaybackSeedInput getQueueIdHistoricalPlayback() {
        return this.queueIdHistoricalPlayback;
    }

    public PlayableEntitySeedInput getPlayableEntity() {
        return this.playableEntity;
    }

    public TrackListSeedInput getTrackList() {
        return this.trackList;
    }

    public LibraryAlbumSeedInput getLibraryAlbum() {
        return this.libraryAlbum;
    }

    public LibraryArtistSeedInput getLibraryArtist() {
        return this.libraryArtist;
    }

    public StationSeedInput getStation() {
        return this.station;
    }

    public AccountSeedInput getAccount() {
        return this.account;
    }

    public SearchSeedInput getSearch() {
        return this.search;
    }

    public OpaqueTrackSearchSeedInput getOpaqueTrackSearch() {
        return this.opaqueTrackSearch;
    }

    public SmartLibraryPlaylistSeedInput getSmartLibraryPlaylist() {
        return this.smartLibraryPlaylist;
    }

    public VideoChannelSeedInput getVideoChannel() {
        return this.videoChannel;
    }

    public SourceDeviceTransferSeedInput getSourceDeviceTransfer() {
        return this.sourceDeviceTransfer;
    }

    public PodcastShowSeedInput getPodcastShow() {
        return this.podcastShow;
    }

    public PodcastSavedSeedInput getPodcastSaved() {
        return this.podcastSaved;
    }

    public PodcastLatestSeedInput getPodcastLatest() {
        return this.podcastLatest;
    }

    public PodcastJumpBackInSeedInput getPodcastJumpBackIn() {
        return this.podcastJumpBackIn;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QueueSeedInput.class != obj.getClass()) {
            return false;
        }
        QueueSeedInput queueSeedInput = (QueueSeedInput) obj;
        return ObjectsCompat.equals(getPlayMode(), queueSeedInput.getPlayMode()) && ObjectsCompat.equals(getPlayModeEnumValue(), queueSeedInput.getPlayModeEnumValue()) && ObjectsCompat.equals(getQueueSeedType(), queueSeedInput.getQueueSeedType()) && ObjectsCompat.equals(getQueueSeedTypeEnumValue(), queueSeedInput.getQueueSeedTypeEnumValue()) && ObjectsCompat.equals(getQueueIdTransfer(), queueSeedInput.getQueueIdTransfer()) && ObjectsCompat.equals(getQueueIdHistoricalPlayback(), queueSeedInput.getQueueIdHistoricalPlayback()) && ObjectsCompat.equals(getPlayableEntity(), queueSeedInput.getPlayableEntity()) && ObjectsCompat.equals(getTrackList(), queueSeedInput.getTrackList()) && ObjectsCompat.equals(getLibraryAlbum(), queueSeedInput.getLibraryAlbum()) && ObjectsCompat.equals(getLibraryArtist(), queueSeedInput.getLibraryArtist()) && ObjectsCompat.equals(getStation(), queueSeedInput.getStation()) && ObjectsCompat.equals(getAccount(), queueSeedInput.getAccount()) && ObjectsCompat.equals(getSearch(), queueSeedInput.getSearch()) && ObjectsCompat.equals(getOpaqueTrackSearch(), queueSeedInput.getOpaqueTrackSearch()) && ObjectsCompat.equals(getSmartLibraryPlaylist(), queueSeedInput.getSmartLibraryPlaylist()) && ObjectsCompat.equals(getVideoChannel(), queueSeedInput.getVideoChannel()) && ObjectsCompat.equals(getSourceDeviceTransfer(), queueSeedInput.getSourceDeviceTransfer()) && ObjectsCompat.equals(getPodcastShow(), queueSeedInput.getPodcastShow()) && ObjectsCompat.equals(getPodcastSaved(), queueSeedInput.getPodcastSaved()) && ObjectsCompat.equals(getPodcastLatest(), queueSeedInput.getPodcastLatest()) && ObjectsCompat.equals(getPodcastJumpBackIn(), queueSeedInput.getPodcastJumpBackIn());
    }

    public int hashCode() {
        return (getPlayMode() + getPlayModeEnumValue() + getQueueSeedType() + getQueueSeedTypeEnumValue() + getQueueIdTransfer() + getQueueIdHistoricalPlayback() + getPlayableEntity() + getTrackList() + getLibraryAlbum() + getLibraryArtist() + getStation() + getAccount() + getSearch() + getOpaqueTrackSearch() + getSmartLibraryPlaylist() + getVideoChannel() + getSourceDeviceTransfer() + getPodcastShow() + getPodcastSaved() + getPodcastLatest() + getPodcastJumpBackIn()).hashCode();
    }
}
