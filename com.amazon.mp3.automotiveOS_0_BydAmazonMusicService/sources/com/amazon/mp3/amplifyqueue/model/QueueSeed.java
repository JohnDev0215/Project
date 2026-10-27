package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class QueueSeed {
    private final AccountSeed account;
    private final LibraryAlbumSeed libraryAlbum;
    private final LibraryArtistSeed libraryArtist;
    private final OpaqueTrackSearchSeed opaqueTrackSearch;
    private final PlayModeEnum playMode;
    private final String playModeEnumValue;
    private final PlayableEntitySeed playableEntity;
    private final PodcastJumpBackInSeed podcastJumpBackIn;
    private final PodcastLatestSeed podcastLatest;
    private final PodcastSavedSeed podcastSaved;
    private final PodcastShowSeed podcastShow;
    private final QueueIdHistoricalPlaybackSeed queueIdHistoricalPlayback;
    private final QueueIdTransferSeed queueIdTransfer;
    private final QueueSeedTypeEnum queueSeedType;
    private final String queueSeedTypeEnumValue;
    private final SearchSeed search;
    private final SmartLibraryPlaylistSeed smartLibraryPlaylist;
    private final SourceDeviceTransferSeed sourceDeviceTransfer;
    private final StationSeed station;
    private final TrackListSeed trackList;
    private final VideoChannelSeed videoChannel;

    public interface BuildStep {
        BuildStep account(AccountSeed accountSeed);

        QueueSeed build();

        BuildStep libraryAlbum(LibraryAlbumSeed libraryAlbumSeed);

        BuildStep libraryArtist(LibraryArtistSeed libraryArtistSeed);

        BuildStep opaqueTrackSearch(OpaqueTrackSearchSeed opaqueTrackSearchSeed);

        BuildStep playMode(PlayModeEnum playModeEnum);

        BuildStep playModeEnumValue(String str);

        BuildStep playableEntity(PlayableEntitySeed playableEntitySeed);

        BuildStep queueIdHistoricalPlayback(QueueIdHistoricalPlaybackSeed queueIdHistoricalPlaybackSeed);

        BuildStep queueIdTransfer(QueueIdTransferSeed queueIdTransferSeed);

        BuildStep queueSeedTypeEnumValue(String str);

        BuildStep search(SearchSeed searchSeed);

        BuildStep smartLibraryPlaylist(SmartLibraryPlaylistSeed smartLibraryPlaylistSeed);

        BuildStep station(StationSeed stationSeed);

        BuildStep trackList(TrackListSeed trackListSeed);
    }

    public interface QueueSeedTypeStep {
        BuildStep queueSeedType(QueueSeedTypeEnum queueSeedTypeEnum);
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

    public QueueIdTransferSeed getQueueIdTransfer() {
        return this.queueIdTransfer;
    }

    public QueueIdHistoricalPlaybackSeed getQueueIdHistoricalPlayback() {
        return this.queueIdHistoricalPlayback;
    }

    public PlayableEntitySeed getPlayableEntity() {
        return this.playableEntity;
    }

    public TrackListSeed getTrackList() {
        return this.trackList;
    }

    public LibraryAlbumSeed getLibraryAlbum() {
        return this.libraryAlbum;
    }

    public LibraryArtistSeed getLibraryArtist() {
        return this.libraryArtist;
    }

    public OpaqueTrackSearchSeed getOpaqueTrackSearch() {
        return this.opaqueTrackSearch;
    }

    public StationSeed getStation() {
        return this.station;
    }

    public SearchSeed getSearch() {
        return this.search;
    }

    public AccountSeed getAccount() {
        return this.account;
    }

    public SmartLibraryPlaylistSeed getSmartLibraryPlaylist() {
        return this.smartLibraryPlaylist;
    }

    public VideoChannelSeed getVideoChannel() {
        return this.videoChannel;
    }

    public SourceDeviceTransferSeed getSourceDeviceTransfer() {
        return this.sourceDeviceTransfer;
    }

    public PodcastShowSeed getPodcastShow() {
        return this.podcastShow;
    }

    public PodcastLatestSeed getPodcastLatest() {
        return this.podcastLatest;
    }

    public PodcastSavedSeed getPodcastSaved() {
        return this.podcastSaved;
    }

    public PodcastJumpBackInSeed getPodcastJumpBackIn() {
        return this.podcastJumpBackIn;
    }

    private QueueSeed(PlayModeEnum playModeEnum, String str, QueueSeedTypeEnum queueSeedTypeEnum, String str2, QueueIdTransferSeed queueIdTransferSeed, QueueIdHistoricalPlaybackSeed queueIdHistoricalPlaybackSeed, PlayableEntitySeed playableEntitySeed, TrackListSeed trackListSeed, LibraryAlbumSeed libraryAlbumSeed, LibraryArtistSeed libraryArtistSeed, OpaqueTrackSearchSeed opaqueTrackSearchSeed, StationSeed stationSeed, SearchSeed searchSeed, AccountSeed accountSeed, SmartLibraryPlaylistSeed smartLibraryPlaylistSeed, VideoChannelSeed videoChannelSeed, SourceDeviceTransferSeed sourceDeviceTransferSeed, PodcastShowSeed podcastShowSeed, PodcastLatestSeed podcastLatestSeed, PodcastSavedSeed podcastSavedSeed, PodcastJumpBackInSeed podcastJumpBackInSeed) {
        this.playMode = playModeEnum;
        this.playModeEnumValue = str;
        this.queueSeedType = queueSeedTypeEnum;
        this.queueSeedTypeEnumValue = str2;
        this.queueIdTransfer = queueIdTransferSeed;
        this.queueIdHistoricalPlayback = queueIdHistoricalPlaybackSeed;
        this.playableEntity = playableEntitySeed;
        this.trackList = trackListSeed;
        this.libraryAlbum = libraryAlbumSeed;
        this.libraryArtist = libraryArtistSeed;
        this.opaqueTrackSearch = opaqueTrackSearchSeed;
        this.station = stationSeed;
        this.search = searchSeed;
        this.account = accountSeed;
        this.smartLibraryPlaylist = smartLibraryPlaylistSeed;
        this.videoChannel = videoChannelSeed;
        this.sourceDeviceTransfer = sourceDeviceTransferSeed;
        this.podcastShow = podcastShowSeed;
        this.podcastLatest = podcastLatestSeed;
        this.podcastSaved = podcastSavedSeed;
        this.podcastJumpBackIn = podcastJumpBackInSeed;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QueueSeed.class != obj.getClass()) {
            return false;
        }
        QueueSeed queueSeed = (QueueSeed) obj;
        return ObjectsCompat.equals(getPlayMode(), queueSeed.getPlayMode()) && ObjectsCompat.equals(getPlayModeEnumValue(), queueSeed.getPlayModeEnumValue()) && ObjectsCompat.equals(getQueueSeedType(), queueSeed.getQueueSeedType()) && ObjectsCompat.equals(getQueueSeedTypeEnumValue(), queueSeed.getQueueSeedTypeEnumValue()) && ObjectsCompat.equals(getQueueIdTransfer(), queueSeed.getQueueIdTransfer()) && ObjectsCompat.equals(getQueueIdHistoricalPlayback(), queueSeed.getQueueIdHistoricalPlayback()) && ObjectsCompat.equals(getPlayableEntity(), queueSeed.getPlayableEntity()) && ObjectsCompat.equals(getTrackList(), queueSeed.getTrackList()) && ObjectsCompat.equals(getLibraryAlbum(), queueSeed.getLibraryAlbum()) && ObjectsCompat.equals(getLibraryArtist(), queueSeed.getLibraryArtist()) && ObjectsCompat.equals(getOpaqueTrackSearch(), queueSeed.getOpaqueTrackSearch()) && ObjectsCompat.equals(getStation(), queueSeed.getStation()) && ObjectsCompat.equals(getSearch(), queueSeed.getSearch()) && ObjectsCompat.equals(getAccount(), queueSeed.getAccount()) && ObjectsCompat.equals(getSmartLibraryPlaylist(), queueSeed.getSmartLibraryPlaylist()) && ObjectsCompat.equals(getVideoChannel(), queueSeed.getVideoChannel()) && ObjectsCompat.equals(getSourceDeviceTransfer(), queueSeed.getSourceDeviceTransfer()) && ObjectsCompat.equals(getPodcastShow(), queueSeed.getPodcastShow()) && ObjectsCompat.equals(getPodcastLatest(), queueSeed.getPodcastLatest()) && ObjectsCompat.equals(getPodcastSaved(), queueSeed.getPodcastSaved()) && ObjectsCompat.equals(getPodcastJumpBackIn(), queueSeed.getPodcastJumpBackIn());
    }

    public int hashCode() {
        return (getPlayMode() + getPlayModeEnumValue() + getQueueSeedType() + getQueueSeedTypeEnumValue() + getQueueIdTransfer() + getQueueIdHistoricalPlayback() + getPlayableEntity() + getTrackList() + getLibraryAlbum() + getLibraryArtist() + getOpaqueTrackSearch() + getStation() + getSearch() + getAccount() + getSmartLibraryPlaylist() + getVideoChannel() + getSourceDeviceTransfer() + getPodcastShow() + getPodcastLatest() + getPodcastSaved() + getPodcastJumpBackIn()).hashCode();
    }

    public static QueueSeedTypeStep builder() {
        return new Builder();
    }

    public static class Builder implements QueueSeedTypeStep, BuildStep {
        private AccountSeed account;
        private LibraryAlbumSeed libraryAlbum;
        private LibraryArtistSeed libraryArtist;
        private OpaqueTrackSearchSeed opaqueTrackSearch;
        private PlayModeEnum playMode;
        private String playModeEnumValue;
        private PlayableEntitySeed playableEntity;
        private PodcastJumpBackInSeed podcastJumpBackIn;
        private PodcastLatestSeed podcastLatest;
        private PodcastSavedSeed podcastSaved;
        private PodcastShowSeed podcastShow;
        private QueueIdHistoricalPlaybackSeed queueIdHistoricalPlayback;
        private QueueIdTransferSeed queueIdTransfer;
        private QueueSeedTypeEnum queueSeedType;
        private String queueSeedTypeEnumValue;
        private SearchSeed search;
        private SmartLibraryPlaylistSeed smartLibraryPlaylist;
        private SourceDeviceTransferSeed sourceDeviceTransfer;
        private StationSeed station;
        private TrackListSeed trackList;
        private VideoChannelSeed videoChannel;

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public QueueSeed build() {
            return new QueueSeed(this.playMode, this.playModeEnumValue, this.queueSeedType, this.queueSeedTypeEnumValue, this.queueIdTransfer, this.queueIdHistoricalPlayback, this.playableEntity, this.trackList, this.libraryAlbum, this.libraryArtist, this.opaqueTrackSearch, this.station, this.search, this.account, this.smartLibraryPlaylist, this.videoChannel, this.sourceDeviceTransfer, this.podcastShow, this.podcastLatest, this.podcastSaved, this.podcastJumpBackIn);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.QueueSeedTypeStep
        public BuildStep queueSeedType(QueueSeedTypeEnum queueSeedTypeEnum) {
            Objects.requireNonNull(queueSeedTypeEnum);
            this.queueSeedType = queueSeedTypeEnum;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public BuildStep playMode(PlayModeEnum playModeEnum) {
            this.playMode = playModeEnum;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public BuildStep playModeEnumValue(String str) {
            this.playModeEnumValue = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public BuildStep queueSeedTypeEnumValue(String str) {
            this.queueSeedTypeEnumValue = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public BuildStep queueIdTransfer(QueueIdTransferSeed queueIdTransferSeed) {
            this.queueIdTransfer = queueIdTransferSeed;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public BuildStep queueIdHistoricalPlayback(QueueIdHistoricalPlaybackSeed queueIdHistoricalPlaybackSeed) {
            this.queueIdHistoricalPlayback = queueIdHistoricalPlaybackSeed;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public BuildStep playableEntity(PlayableEntitySeed playableEntitySeed) {
            this.playableEntity = playableEntitySeed;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public BuildStep trackList(TrackListSeed trackListSeed) {
            this.trackList = trackListSeed;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public BuildStep libraryAlbum(LibraryAlbumSeed libraryAlbumSeed) {
            this.libraryAlbum = libraryAlbumSeed;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public BuildStep libraryArtist(LibraryArtistSeed libraryArtistSeed) {
            this.libraryArtist = libraryArtistSeed;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public BuildStep opaqueTrackSearch(OpaqueTrackSearchSeed opaqueTrackSearchSeed) {
            this.opaqueTrackSearch = opaqueTrackSearchSeed;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public BuildStep station(StationSeed stationSeed) {
            this.station = stationSeed;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public BuildStep search(SearchSeed searchSeed) {
            this.search = searchSeed;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public BuildStep account(AccountSeed accountSeed) {
            this.account = accountSeed;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public BuildStep smartLibraryPlaylist(SmartLibraryPlaylistSeed smartLibraryPlaylistSeed) {
            this.smartLibraryPlaylist = smartLibraryPlaylistSeed;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeed.QueueSeedTypeStep
        public CopyOfBuilder queueSeedType(QueueSeedTypeEnum queueSeedTypeEnum) {
            return (CopyOfBuilder) super.queueSeedType(queueSeedTypeEnum);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public CopyOfBuilder playMode(PlayModeEnum playModeEnum) {
            return (CopyOfBuilder) super.playMode(playModeEnum);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public CopyOfBuilder playModeEnumValue(String str) {
            return (CopyOfBuilder) super.playModeEnumValue(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public CopyOfBuilder queueSeedTypeEnumValue(String str) {
            return (CopyOfBuilder) super.queueSeedTypeEnumValue(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public CopyOfBuilder queueIdTransfer(QueueIdTransferSeed queueIdTransferSeed) {
            return (CopyOfBuilder) super.queueIdTransfer(queueIdTransferSeed);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public CopyOfBuilder queueIdHistoricalPlayback(QueueIdHistoricalPlaybackSeed queueIdHistoricalPlaybackSeed) {
            return (CopyOfBuilder) super.queueIdHistoricalPlayback(queueIdHistoricalPlaybackSeed);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public CopyOfBuilder playableEntity(PlayableEntitySeed playableEntitySeed) {
            return (CopyOfBuilder) super.playableEntity(playableEntitySeed);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public CopyOfBuilder trackList(TrackListSeed trackListSeed) {
            return (CopyOfBuilder) super.trackList(trackListSeed);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public CopyOfBuilder libraryAlbum(LibraryAlbumSeed libraryAlbumSeed) {
            return (CopyOfBuilder) super.libraryAlbum(libraryAlbumSeed);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public CopyOfBuilder libraryArtist(LibraryArtistSeed libraryArtistSeed) {
            return (CopyOfBuilder) super.libraryArtist(libraryArtistSeed);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public CopyOfBuilder opaqueTrackSearch(OpaqueTrackSearchSeed opaqueTrackSearchSeed) {
            return (CopyOfBuilder) super.opaqueTrackSearch(opaqueTrackSearchSeed);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public CopyOfBuilder station(StationSeed stationSeed) {
            return (CopyOfBuilder) super.station(stationSeed);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public CopyOfBuilder search(SearchSeed searchSeed) {
            return (CopyOfBuilder) super.search(searchSeed);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public CopyOfBuilder account(AccountSeed accountSeed) {
            return (CopyOfBuilder) super.account(accountSeed);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeed.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeed.BuildStep
        public CopyOfBuilder smartLibraryPlaylist(SmartLibraryPlaylistSeed smartLibraryPlaylistSeed) {
            return (CopyOfBuilder) super.smartLibraryPlaylist(smartLibraryPlaylistSeed);
        }
    }
}
