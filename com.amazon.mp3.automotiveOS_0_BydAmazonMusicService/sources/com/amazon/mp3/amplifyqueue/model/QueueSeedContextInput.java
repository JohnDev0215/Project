package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class QueueSeedContextInput {
    private final AccountSeedContextInput account;
    private final LibraryAlbumSeedContextInput libraryAlbum;
    private final LibraryArtistSeedContextInput libraryArtist;
    private final OpaqueTrackSearchSeedContextInput opaqueTrackSearch;
    private final PlayModeEnum playMode;
    private final String playModeEnumValue;
    private final PlayableEntitySeedContextInput playableEntity;
    private final PodcastJumpBackInSeedContextInput podcastJumpBackIn;
    private final PodcastLatestSeedContextInput podcastLatest;
    private final PodcastSavedSeedContextInput podcastSaved;
    private final PodcastShowSeedContextInput podcastShow;
    private final QueueIdHistoricalPlaybackSeedContextInput queueIdHistoricalPlayback;
    private final QueueIdTransferSeedContextInput queueIdTransfer;
    private final QueueSeedTypeEnum queueSeedType;
    private final String queueSeedTypeEnumValue;
    private final SearchSeedContextInput search;
    private final List<SeedContextInput> seedContext;
    private final SmartLibraryPlaylistSeedContextInput smartLibraryPlaylist;
    private final SourceDeviceTransferSeedContextInput sourceDeviceTransfer;
    private final StationSeedContextInput station;
    private final TrackListSeedContextInput trackList;
    private final VideoChannelSeedContextInput videoChannel;

    public interface BuildStep {
        BuildStep account(AccountSeedContextInput accountSeedContextInput);

        QueueSeedContextInput build();

        BuildStep libraryAlbum(LibraryAlbumSeedContextInput libraryAlbumSeedContextInput);

        BuildStep libraryArtist(LibraryArtistSeedContextInput libraryArtistSeedContextInput);

        BuildStep opaqueTrackSearch(OpaqueTrackSearchSeedContextInput opaqueTrackSearchSeedContextInput);

        BuildStep playModeEnumValue(String str);

        BuildStep playableEntity(PlayableEntitySeedContextInput playableEntitySeedContextInput);

        BuildStep queueIdHistoricalPlayback(QueueIdHistoricalPlaybackSeedContextInput queueIdHistoricalPlaybackSeedContextInput);

        BuildStep queueIdTransfer(QueueIdTransferSeedContextInput queueIdTransferSeedContextInput);

        BuildStep queueSeedTypeEnumValue(String str);

        BuildStep search(SearchSeedContextInput searchSeedContextInput);

        BuildStep smartLibraryPlaylist(SmartLibraryPlaylistSeedContextInput smartLibraryPlaylistSeedContextInput);

        BuildStep sourceDeviceTransfer(SourceDeviceTransferSeedContextInput sourceDeviceTransferSeedContextInput);

        BuildStep station(StationSeedContextInput stationSeedContextInput);

        BuildStep trackList(TrackListSeedContextInput trackListSeedContextInput);

        BuildStep videoChannel(VideoChannelSeedContextInput videoChannelSeedContextInput);
    }

    public interface QueueSeedTypeStep {
        SeedContextStep queueSeedType(QueueSeedTypeEnum queueSeedTypeEnum);
    }

    public interface SeedContextStep {
        BuildStep seedContext(List<SeedContextInput> list);
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

    public QueueIdTransferSeedContextInput getQueueIdTransfer() {
        return this.queueIdTransfer;
    }

    public QueueIdHistoricalPlaybackSeedContextInput getQueueIdHistoricalPlayback() {
        return this.queueIdHistoricalPlayback;
    }

    public PlayableEntitySeedContextInput getPlayableEntity() {
        return this.playableEntity;
    }

    public TrackListSeedContextInput getTrackList() {
        return this.trackList;
    }

    public LibraryAlbumSeedContextInput getLibraryAlbum() {
        return this.libraryAlbum;
    }

    public LibraryArtistSeedContextInput getLibraryArtist() {
        return this.libraryArtist;
    }

    public StationSeedContextInput getStation() {
        return this.station;
    }

    public AccountSeedContextInput getAccount() {
        return this.account;
    }

    public SearchSeedContextInput getSearch() {
        return this.search;
    }

    public OpaqueTrackSearchSeedContextInput getOpaqueTrackSearch() {
        return this.opaqueTrackSearch;
    }

    public SmartLibraryPlaylistSeedContextInput getSmartLibraryPlaylist() {
        return this.smartLibraryPlaylist;
    }

    public List<SeedContextInput> getSeedContext() {
        return this.seedContext;
    }

    public VideoChannelSeedContextInput getVideoChannel() {
        return this.videoChannel;
    }

    public SourceDeviceTransferSeedContextInput getSourceDeviceTransfer() {
        return this.sourceDeviceTransfer;
    }

    public PodcastShowSeedContextInput getPodcastShow() {
        return this.podcastShow;
    }

    public PodcastSavedSeedContextInput getPodcastSaved() {
        return this.podcastSaved;
    }

    public PodcastLatestSeedContextInput getPodcastLatest() {
        return this.podcastLatest;
    }

    public PodcastJumpBackInSeedContextInput getPodcastJumpBackIn() {
        return this.podcastJumpBackIn;
    }

    private QueueSeedContextInput(PlayModeEnum playModeEnum, String str, QueueSeedTypeEnum queueSeedTypeEnum, String str2, QueueIdTransferSeedContextInput queueIdTransferSeedContextInput, QueueIdHistoricalPlaybackSeedContextInput queueIdHistoricalPlaybackSeedContextInput, PlayableEntitySeedContextInput playableEntitySeedContextInput, TrackListSeedContextInput trackListSeedContextInput, LibraryAlbumSeedContextInput libraryAlbumSeedContextInput, LibraryArtistSeedContextInput libraryArtistSeedContextInput, StationSeedContextInput stationSeedContextInput, AccountSeedContextInput accountSeedContextInput, SearchSeedContextInput searchSeedContextInput, OpaqueTrackSearchSeedContextInput opaqueTrackSearchSeedContextInput, SmartLibraryPlaylistSeedContextInput smartLibraryPlaylistSeedContextInput, List<SeedContextInput> list, VideoChannelSeedContextInput videoChannelSeedContextInput, SourceDeviceTransferSeedContextInput sourceDeviceTransferSeedContextInput, PodcastShowSeedContextInput podcastShowSeedContextInput, PodcastSavedSeedContextInput podcastSavedSeedContextInput, PodcastLatestSeedContextInput podcastLatestSeedContextInput, PodcastJumpBackInSeedContextInput podcastJumpBackInSeedContextInput) {
        this.playMode = playModeEnum;
        this.playModeEnumValue = str;
        this.queueSeedType = queueSeedTypeEnum;
        this.queueSeedTypeEnumValue = str2;
        this.queueIdTransfer = queueIdTransferSeedContextInput;
        this.queueIdHistoricalPlayback = queueIdHistoricalPlaybackSeedContextInput;
        this.playableEntity = playableEntitySeedContextInput;
        this.trackList = trackListSeedContextInput;
        this.libraryAlbum = libraryAlbumSeedContextInput;
        this.libraryArtist = libraryArtistSeedContextInput;
        this.station = stationSeedContextInput;
        this.account = accountSeedContextInput;
        this.search = searchSeedContextInput;
        this.opaqueTrackSearch = opaqueTrackSearchSeedContextInput;
        this.smartLibraryPlaylist = smartLibraryPlaylistSeedContextInput;
        this.seedContext = list;
        this.videoChannel = videoChannelSeedContextInput;
        this.sourceDeviceTransfer = sourceDeviceTransferSeedContextInput;
        this.podcastShow = podcastShowSeedContextInput;
        this.podcastSaved = podcastSavedSeedContextInput;
        this.podcastLatest = podcastLatestSeedContextInput;
        this.podcastJumpBackIn = podcastJumpBackInSeedContextInput;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QueueSeedContextInput.class != obj.getClass()) {
            return false;
        }
        QueueSeedContextInput queueSeedContextInput = (QueueSeedContextInput) obj;
        return ObjectsCompat.equals(getPlayMode(), queueSeedContextInput.getPlayMode()) && ObjectsCompat.equals(getPlayModeEnumValue(), queueSeedContextInput.getPlayModeEnumValue()) && ObjectsCompat.equals(getQueueSeedType(), queueSeedContextInput.getQueueSeedType()) && ObjectsCompat.equals(getQueueSeedTypeEnumValue(), queueSeedContextInput.getQueueSeedTypeEnumValue()) && ObjectsCompat.equals(getQueueIdTransfer(), queueSeedContextInput.getQueueIdTransfer()) && ObjectsCompat.equals(getQueueIdHistoricalPlayback(), queueSeedContextInput.getQueueIdHistoricalPlayback()) && ObjectsCompat.equals(getPlayableEntity(), queueSeedContextInput.getPlayableEntity()) && ObjectsCompat.equals(getTrackList(), queueSeedContextInput.getTrackList()) && ObjectsCompat.equals(getLibraryAlbum(), queueSeedContextInput.getLibraryAlbum()) && ObjectsCompat.equals(getLibraryArtist(), queueSeedContextInput.getLibraryArtist()) && ObjectsCompat.equals(getStation(), queueSeedContextInput.getStation()) && ObjectsCompat.equals(getAccount(), queueSeedContextInput.getAccount()) && ObjectsCompat.equals(getSearch(), queueSeedContextInput.getSearch()) && ObjectsCompat.equals(getOpaqueTrackSearch(), queueSeedContextInput.getOpaqueTrackSearch()) && ObjectsCompat.equals(getSmartLibraryPlaylist(), queueSeedContextInput.getSmartLibraryPlaylist()) && ObjectsCompat.equals(getSeedContext(), queueSeedContextInput.getSeedContext()) && ObjectsCompat.equals(getVideoChannel(), queueSeedContextInput.getVideoChannel()) && ObjectsCompat.equals(getSourceDeviceTransfer(), queueSeedContextInput.getSourceDeviceTransfer()) && ObjectsCompat.equals(getPodcastShow(), queueSeedContextInput.getPodcastShow()) && ObjectsCompat.equals(getPodcastSaved(), queueSeedContextInput.getPodcastSaved()) && ObjectsCompat.equals(getPodcastLatest(), queueSeedContextInput.getPodcastLatest()) && ObjectsCompat.equals(getPodcastJumpBackIn(), queueSeedContextInput.getPodcastJumpBackIn());
    }

    public int hashCode() {
        return (getPlayMode() + getPlayModeEnumValue() + getQueueSeedType() + getQueueSeedTypeEnumValue() + getQueueIdTransfer() + getQueueIdHistoricalPlayback() + getPlayableEntity() + getTrackList() + getLibraryAlbum() + getLibraryArtist() + getStation() + getAccount() + getSearch() + getOpaqueTrackSearch() + getSmartLibraryPlaylist() + getSeedContext() + getVideoChannel() + getSourceDeviceTransfer() + getPodcastShow() + getPodcastSaved() + getPodcastLatest() + getPodcastJumpBackIn()).hashCode();
    }

    public static QueueSeedTypeStep builder() {
        return new Builder();
    }

    public static class Builder implements QueueSeedTypeStep, SeedContextStep, BuildStep {
        private AccountSeedContextInput account;
        private LibraryAlbumSeedContextInput libraryAlbum;
        private LibraryArtistSeedContextInput libraryArtist;
        private OpaqueTrackSearchSeedContextInput opaqueTrackSearch;
        private PlayModeEnum playMode;
        private String playModeEnumValue;
        private PlayableEntitySeedContextInput playableEntity;
        private PodcastJumpBackInSeedContextInput podcastJumpBackIn;
        private PodcastLatestSeedContextInput podcastLatest;
        private PodcastSavedSeedContextInput podcastSaved;
        private PodcastShowSeedContextInput podcastShow;
        private QueueIdHistoricalPlaybackSeedContextInput queueIdHistoricalPlayback;
        private QueueIdTransferSeedContextInput queueIdTransfer;
        private QueueSeedTypeEnum queueSeedType;
        private String queueSeedTypeEnumValue;
        private SearchSeedContextInput search;
        private List<SeedContextInput> seedContext;
        private SmartLibraryPlaylistSeedContextInput smartLibraryPlaylist;
        private SourceDeviceTransferSeedContextInput sourceDeviceTransfer;
        private StationSeedContextInput station;
        private TrackListSeedContextInput trackList;
        private VideoChannelSeedContextInput videoChannel;

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public QueueSeedContextInput build() {
            return new QueueSeedContextInput(this.playMode, this.playModeEnumValue, this.queueSeedType, this.queueSeedTypeEnumValue, this.queueIdTransfer, this.queueIdHistoricalPlayback, this.playableEntity, this.trackList, this.libraryAlbum, this.libraryArtist, this.station, this.account, this.search, this.opaqueTrackSearch, this.smartLibraryPlaylist, this.seedContext, this.videoChannel, this.sourceDeviceTransfer, this.podcastShow, this.podcastSaved, this.podcastLatest, this.podcastJumpBackIn);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.QueueSeedTypeStep
        public SeedContextStep queueSeedType(QueueSeedTypeEnum queueSeedTypeEnum) {
            Objects.requireNonNull(queueSeedTypeEnum);
            this.queueSeedType = queueSeedTypeEnum;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.SeedContextStep
        public BuildStep seedContext(List<SeedContextInput> list) {
            Objects.requireNonNull(list);
            this.seedContext = list;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public BuildStep playModeEnumValue(String str) {
            this.playModeEnumValue = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public BuildStep queueSeedTypeEnumValue(String str) {
            this.queueSeedTypeEnumValue = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public BuildStep queueIdTransfer(QueueIdTransferSeedContextInput queueIdTransferSeedContextInput) {
            this.queueIdTransfer = queueIdTransferSeedContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public BuildStep queueIdHistoricalPlayback(QueueIdHistoricalPlaybackSeedContextInput queueIdHistoricalPlaybackSeedContextInput) {
            this.queueIdHistoricalPlayback = queueIdHistoricalPlaybackSeedContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public BuildStep playableEntity(PlayableEntitySeedContextInput playableEntitySeedContextInput) {
            this.playableEntity = playableEntitySeedContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public BuildStep trackList(TrackListSeedContextInput trackListSeedContextInput) {
            this.trackList = trackListSeedContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public BuildStep libraryAlbum(LibraryAlbumSeedContextInput libraryAlbumSeedContextInput) {
            this.libraryAlbum = libraryAlbumSeedContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public BuildStep libraryArtist(LibraryArtistSeedContextInput libraryArtistSeedContextInput) {
            this.libraryArtist = libraryArtistSeedContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public BuildStep station(StationSeedContextInput stationSeedContextInput) {
            this.station = stationSeedContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public BuildStep account(AccountSeedContextInput accountSeedContextInput) {
            this.account = accountSeedContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public BuildStep search(SearchSeedContextInput searchSeedContextInput) {
            this.search = searchSeedContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public BuildStep opaqueTrackSearch(OpaqueTrackSearchSeedContextInput opaqueTrackSearchSeedContextInput) {
            this.opaqueTrackSearch = opaqueTrackSearchSeedContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public BuildStep smartLibraryPlaylist(SmartLibraryPlaylistSeedContextInput smartLibraryPlaylistSeedContextInput) {
            this.smartLibraryPlaylist = smartLibraryPlaylistSeedContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public BuildStep videoChannel(VideoChannelSeedContextInput videoChannelSeedContextInput) {
            this.videoChannel = videoChannelSeedContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public BuildStep sourceDeviceTransfer(SourceDeviceTransferSeedContextInput sourceDeviceTransferSeedContextInput) {
            this.sourceDeviceTransfer = sourceDeviceTransferSeedContextInput;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.SeedContextStep
        public /* bridge */ /* synthetic */ BuildStep seedContext(List list) {
            return seedContext((List<SeedContextInput>) list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.QueueSeedTypeStep
        public CopyOfBuilder queueSeedType(QueueSeedTypeEnum queueSeedTypeEnum) {
            return (CopyOfBuilder) super.queueSeedType(queueSeedTypeEnum);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.SeedContextStep
        public CopyOfBuilder seedContext(List<SeedContextInput> list) {
            return (CopyOfBuilder) super.seedContext(list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public CopyOfBuilder playModeEnumValue(String str) {
            return (CopyOfBuilder) super.playModeEnumValue(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public CopyOfBuilder queueSeedTypeEnumValue(String str) {
            return (CopyOfBuilder) super.queueSeedTypeEnumValue(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public CopyOfBuilder queueIdTransfer(QueueIdTransferSeedContextInput queueIdTransferSeedContextInput) {
            return (CopyOfBuilder) super.queueIdTransfer(queueIdTransferSeedContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public CopyOfBuilder queueIdHistoricalPlayback(QueueIdHistoricalPlaybackSeedContextInput queueIdHistoricalPlaybackSeedContextInput) {
            return (CopyOfBuilder) super.queueIdHistoricalPlayback(queueIdHistoricalPlaybackSeedContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public CopyOfBuilder playableEntity(PlayableEntitySeedContextInput playableEntitySeedContextInput) {
            return (CopyOfBuilder) super.playableEntity(playableEntitySeedContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public CopyOfBuilder trackList(TrackListSeedContextInput trackListSeedContextInput) {
            return (CopyOfBuilder) super.trackList(trackListSeedContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public CopyOfBuilder libraryAlbum(LibraryAlbumSeedContextInput libraryAlbumSeedContextInput) {
            return (CopyOfBuilder) super.libraryAlbum(libraryAlbumSeedContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public CopyOfBuilder libraryArtist(LibraryArtistSeedContextInput libraryArtistSeedContextInput) {
            return (CopyOfBuilder) super.libraryArtist(libraryArtistSeedContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public CopyOfBuilder station(StationSeedContextInput stationSeedContextInput) {
            return (CopyOfBuilder) super.station(stationSeedContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public CopyOfBuilder account(AccountSeedContextInput accountSeedContextInput) {
            return (CopyOfBuilder) super.account(accountSeedContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public CopyOfBuilder search(SearchSeedContextInput searchSeedContextInput) {
            return (CopyOfBuilder) super.search(searchSeedContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public CopyOfBuilder opaqueTrackSearch(OpaqueTrackSearchSeedContextInput opaqueTrackSearchSeedContextInput) {
            return (CopyOfBuilder) super.opaqueTrackSearch(opaqueTrackSearchSeedContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public CopyOfBuilder smartLibraryPlaylist(SmartLibraryPlaylistSeedContextInput smartLibraryPlaylistSeedContextInput) {
            return (CopyOfBuilder) super.smartLibraryPlaylist(smartLibraryPlaylistSeedContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public CopyOfBuilder videoChannel(VideoChannelSeedContextInput videoChannelSeedContextInput) {
            return (CopyOfBuilder) super.videoChannel(videoChannelSeedContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.BuildStep
        public CopyOfBuilder sourceDeviceTransfer(SourceDeviceTransferSeedContextInput sourceDeviceTransferSeedContextInput) {
            return (CopyOfBuilder) super.sourceDeviceTransfer(sourceDeviceTransferSeedContextInput);
        }
    }
}
