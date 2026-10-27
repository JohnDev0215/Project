package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.PlayModeEnum;
import com.amazon.digitalmusicxp.enums.QueueSeedTypeEnum;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QueueSeed.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u009f\u0001\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#\u0012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010(\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u000102\u0012\n\b\u0002\u00108\u001a\u0004\u0018\u000107\u0012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010<\u0012\n\b\u0002\u0010B\u001a\u0004\u0018\u00010A\u0012\n\b\u0002\u0010G\u001a\u0004\u0018\u00010F¢\u0006\u0004\bK\u0010LJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u0019\u0010$\u001a\u0004\u0018\u00010#8\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\u0019\u0010)\u001a\u0004\u0018\u00010(8\u0006¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R\u0019\u0010.\u001a\u0004\u0018\u00010-8\u0006¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u00101R\u0019\u00103\u001a\u0004\u0018\u0001028\u0006¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b5\u00106R\u0019\u00108\u001a\u0004\u0018\u0001078\u0006¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b:\u0010;R\u0019\u0010=\u001a\u0004\u0018\u00010<8\u0006¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@R\u0019\u0010B\u001a\u0004\u0018\u00010A8\u0006¢\u0006\f\n\u0004\bB\u0010C\u001a\u0004\bD\u0010ER\u0019\u0010G\u001a\u0004\u0018\u00010F8\u0006¢\u0006\f\n\u0004\bG\u0010H\u001a\u0004\bI\u0010J¨\u0006M"}, d2 = {"Lcom/amazon/digitalmusicxp/types/QueueSeed;", "Lcom/amazon/digitalmusicxp/types/BasicQueueSeed;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/digitalmusicxp/enums/PlayModeEnum;", ContextMappingConstants.PLAY_MODE, "Lcom/amazon/digitalmusicxp/enums/PlayModeEnum;", "getPlayMode", "()Lcom/amazon/digitalmusicxp/enums/PlayModeEnum;", "Lcom/amazon/digitalmusicxp/enums/QueueSeedTypeEnum;", "queueSeedType", "Lcom/amazon/digitalmusicxp/enums/QueueSeedTypeEnum;", "getQueueSeedType", "()Lcom/amazon/digitalmusicxp/enums/QueueSeedTypeEnum;", "Lcom/amazon/digitalmusicxp/types/QueueIdTransferSeed;", "queueIdTransfer", "Lcom/amazon/digitalmusicxp/types/QueueIdTransferSeed;", "getQueueIdTransfer", "()Lcom/amazon/digitalmusicxp/types/QueueIdTransferSeed;", "Lcom/amazon/digitalmusicxp/types/QueueIdHistoricalPlaybackSeed;", "queueIdHistoricalPlayback", "Lcom/amazon/digitalmusicxp/types/QueueIdHistoricalPlaybackSeed;", "getQueueIdHistoricalPlayback", "()Lcom/amazon/digitalmusicxp/types/QueueIdHistoricalPlaybackSeed;", "Lcom/amazon/digitalmusicxp/types/PlayableEntitySeed;", "playableEntity", "Lcom/amazon/digitalmusicxp/types/PlayableEntitySeed;", "getPlayableEntity", "()Lcom/amazon/digitalmusicxp/types/PlayableEntitySeed;", "Lcom/amazon/digitalmusicxp/types/TrackListSeed;", "trackList", "Lcom/amazon/digitalmusicxp/types/TrackListSeed;", "getTrackList", "()Lcom/amazon/digitalmusicxp/types/TrackListSeed;", "Lcom/amazon/digitalmusicxp/types/LibraryAlbumSeed;", "libraryAlbum", "Lcom/amazon/digitalmusicxp/types/LibraryAlbumSeed;", "getLibraryAlbum", "()Lcom/amazon/digitalmusicxp/types/LibraryAlbumSeed;", "Lcom/amazon/digitalmusicxp/types/LibraryArtistSeed;", "libraryArtist", "Lcom/amazon/digitalmusicxp/types/LibraryArtistSeed;", "getLibraryArtist", "()Lcom/amazon/digitalmusicxp/types/LibraryArtistSeed;", "Lcom/amazon/digitalmusicxp/types/OpaqueTrackSearchSeed;", "opaqueTrackSearch", "Lcom/amazon/digitalmusicxp/types/OpaqueTrackSearchSeed;", "getOpaqueTrackSearch", "()Lcom/amazon/digitalmusicxp/types/OpaqueTrackSearchSeed;", "Lcom/amazon/digitalmusicxp/types/StationSeed;", "station", "Lcom/amazon/digitalmusicxp/types/StationSeed;", "getStation", "()Lcom/amazon/digitalmusicxp/types/StationSeed;", "Lcom/amazon/digitalmusicxp/types/SearchSeed;", "search", "Lcom/amazon/digitalmusicxp/types/SearchSeed;", "getSearch", "()Lcom/amazon/digitalmusicxp/types/SearchSeed;", "Lcom/amazon/digitalmusicxp/types/AccountSeed;", "account", "Lcom/amazon/digitalmusicxp/types/AccountSeed;", "getAccount", "()Lcom/amazon/digitalmusicxp/types/AccountSeed;", "Lcom/amazon/digitalmusicxp/types/SmartLibraryPlaylistSeed;", "smartLibraryPlaylist", "Lcom/amazon/digitalmusicxp/types/SmartLibraryPlaylistSeed;", "getSmartLibraryPlaylist", "()Lcom/amazon/digitalmusicxp/types/SmartLibraryPlaylistSeed;", "<init>", "(Lcom/amazon/digitalmusicxp/enums/PlayModeEnum;Lcom/amazon/digitalmusicxp/enums/QueueSeedTypeEnum;Lcom/amazon/digitalmusicxp/types/QueueIdTransferSeed;Lcom/amazon/digitalmusicxp/types/QueueIdHistoricalPlaybackSeed;Lcom/amazon/digitalmusicxp/types/PlayableEntitySeed;Lcom/amazon/digitalmusicxp/types/TrackListSeed;Lcom/amazon/digitalmusicxp/types/LibraryAlbumSeed;Lcom/amazon/digitalmusicxp/types/LibraryArtistSeed;Lcom/amazon/digitalmusicxp/types/OpaqueTrackSearchSeed;Lcom/amazon/digitalmusicxp/types/StationSeed;Lcom/amazon/digitalmusicxp/types/SearchSeed;Lcom/amazon/digitalmusicxp/types/AccountSeed;Lcom/amazon/digitalmusicxp/types/SmartLibraryPlaylistSeed;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class QueueSeed extends BasicQueueSeed {
    private final AccountSeed account;
    private final LibraryAlbumSeed libraryAlbum;
    private final LibraryArtistSeed libraryArtist;
    private final OpaqueTrackSearchSeed opaqueTrackSearch;
    private final PlayModeEnum playMode;
    private final PlayableEntitySeed playableEntity;
    private final QueueIdHistoricalPlaybackSeed queueIdHistoricalPlayback;
    private final QueueIdTransferSeed queueIdTransfer;
    private final QueueSeedTypeEnum queueSeedType;
    private final SearchSeed search;
    private final SmartLibraryPlaylistSeed smartLibraryPlaylist;
    private final StationSeed station;
    private final TrackListSeed trackList;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueueSeed)) {
            return false;
        }
        QueueSeed queueSeed = (QueueSeed) other;
        return this.playMode == queueSeed.playMode && this.queueSeedType == queueSeed.queueSeedType && Intrinsics.areEqual(this.queueIdTransfer, queueSeed.queueIdTransfer) && Intrinsics.areEqual(this.queueIdHistoricalPlayback, queueSeed.queueIdHistoricalPlayback) && Intrinsics.areEqual(this.playableEntity, queueSeed.playableEntity) && Intrinsics.areEqual(this.trackList, queueSeed.trackList) && Intrinsics.areEqual(this.libraryAlbum, queueSeed.libraryAlbum) && Intrinsics.areEqual(this.libraryArtist, queueSeed.libraryArtist) && Intrinsics.areEqual(this.opaqueTrackSearch, queueSeed.opaqueTrackSearch) && Intrinsics.areEqual(this.station, queueSeed.station) && Intrinsics.areEqual(this.search, queueSeed.search) && Intrinsics.areEqual(this.account, queueSeed.account) && Intrinsics.areEqual(this.smartLibraryPlaylist, queueSeed.smartLibraryPlaylist);
    }

    public int hashCode() {
        PlayModeEnum playModeEnum = this.playMode;
        int iHashCode = (((playModeEnum == null ? 0 : playModeEnum.hashCode()) * 31) + this.queueSeedType.hashCode()) * 31;
        QueueIdTransferSeed queueIdTransferSeed = this.queueIdTransfer;
        int iHashCode2 = (iHashCode + (queueIdTransferSeed == null ? 0 : queueIdTransferSeed.hashCode())) * 31;
        QueueIdHistoricalPlaybackSeed queueIdHistoricalPlaybackSeed = this.queueIdHistoricalPlayback;
        int iHashCode3 = (iHashCode2 + (queueIdHistoricalPlaybackSeed == null ? 0 : queueIdHistoricalPlaybackSeed.hashCode())) * 31;
        PlayableEntitySeed playableEntitySeed = this.playableEntity;
        int iHashCode4 = (iHashCode3 + (playableEntitySeed == null ? 0 : playableEntitySeed.hashCode())) * 31;
        TrackListSeed trackListSeed = this.trackList;
        int iHashCode5 = (iHashCode4 + (trackListSeed == null ? 0 : trackListSeed.hashCode())) * 31;
        LibraryAlbumSeed libraryAlbumSeed = this.libraryAlbum;
        int iHashCode6 = (iHashCode5 + (libraryAlbumSeed == null ? 0 : libraryAlbumSeed.hashCode())) * 31;
        LibraryArtistSeed libraryArtistSeed = this.libraryArtist;
        int iHashCode7 = (iHashCode6 + (libraryArtistSeed == null ? 0 : libraryArtistSeed.hashCode())) * 31;
        OpaqueTrackSearchSeed opaqueTrackSearchSeed = this.opaqueTrackSearch;
        int iHashCode8 = (iHashCode7 + (opaqueTrackSearchSeed == null ? 0 : opaqueTrackSearchSeed.hashCode())) * 31;
        StationSeed stationSeed = this.station;
        int iHashCode9 = (iHashCode8 + (stationSeed == null ? 0 : stationSeed.hashCode())) * 31;
        SearchSeed searchSeed = this.search;
        int iHashCode10 = (iHashCode9 + (searchSeed == null ? 0 : searchSeed.hashCode())) * 31;
        AccountSeed accountSeed = this.account;
        int iHashCode11 = (iHashCode10 + (accountSeed == null ? 0 : accountSeed.hashCode())) * 31;
        SmartLibraryPlaylistSeed smartLibraryPlaylistSeed = this.smartLibraryPlaylist;
        return iHashCode11 + (smartLibraryPlaylistSeed != null ? smartLibraryPlaylistSeed.hashCode() : 0);
    }

    public String toString() {
        return "QueueSeed(playMode=" + this.playMode + ", queueSeedType=" + this.queueSeedType + ", queueIdTransfer=" + this.queueIdTransfer + ", queueIdHistoricalPlayback=" + this.queueIdHistoricalPlayback + ", playableEntity=" + this.playableEntity + ", trackList=" + this.trackList + ", libraryAlbum=" + this.libraryAlbum + ", libraryArtist=" + this.libraryArtist + ", opaqueTrackSearch=" + this.opaqueTrackSearch + ", station=" + this.station + ", search=" + this.search + ", account=" + this.account + ", smartLibraryPlaylist=" + this.smartLibraryPlaylist + ')';
    }

    public /* synthetic */ QueueSeed(PlayModeEnum playModeEnum, QueueSeedTypeEnum queueSeedTypeEnum, QueueIdTransferSeed queueIdTransferSeed, QueueIdHistoricalPlaybackSeed queueIdHistoricalPlaybackSeed, PlayableEntitySeed playableEntitySeed, TrackListSeed trackListSeed, LibraryAlbumSeed libraryAlbumSeed, LibraryArtistSeed libraryArtistSeed, OpaqueTrackSearchSeed opaqueTrackSearchSeed, StationSeed stationSeed, SearchSeed searchSeed, AccountSeed accountSeed, SmartLibraryPlaylistSeed smartLibraryPlaylistSeed, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : playModeEnum, queueSeedTypeEnum, (i & 4) != 0 ? null : queueIdTransferSeed, (i & 8) != 0 ? null : queueIdHistoricalPlaybackSeed, (i & 16) != 0 ? null : playableEntitySeed, (i & 32) != 0 ? null : trackListSeed, (i & 64) != 0 ? null : libraryAlbumSeed, (i & 128) != 0 ? null : libraryArtistSeed, (i & 256) != 0 ? null : opaqueTrackSearchSeed, (i & 512) != 0 ? null : stationSeed, (i & 1024) != 0 ? null : searchSeed, (i & 2048) != 0 ? null : accountSeed, (i & 4096) != 0 ? null : smartLibraryPlaylistSeed);
    }

    public final PlayModeEnum getPlayMode() {
        return this.playMode;
    }

    public final QueueSeedTypeEnum getQueueSeedType() {
        return this.queueSeedType;
    }

    public final QueueIdTransferSeed getQueueIdTransfer() {
        return this.queueIdTransfer;
    }

    public final QueueIdHistoricalPlaybackSeed getQueueIdHistoricalPlayback() {
        return this.queueIdHistoricalPlayback;
    }

    public final PlayableEntitySeed getPlayableEntity() {
        return this.playableEntity;
    }

    public final TrackListSeed getTrackList() {
        return this.trackList;
    }

    public final LibraryAlbumSeed getLibraryAlbum() {
        return this.libraryAlbum;
    }

    public final LibraryArtistSeed getLibraryArtist() {
        return this.libraryArtist;
    }

    public final OpaqueTrackSearchSeed getOpaqueTrackSearch() {
        return this.opaqueTrackSearch;
    }

    public final StationSeed getStation() {
        return this.station;
    }

    public final SearchSeed getSearch() {
        return this.search;
    }

    public final AccountSeed getAccount() {
        return this.account;
    }

    public QueueSeed(PlayModeEnum playModeEnum, QueueSeedTypeEnum queueSeedType, QueueIdTransferSeed queueIdTransferSeed, QueueIdHistoricalPlaybackSeed queueIdHistoricalPlaybackSeed, PlayableEntitySeed playableEntitySeed, TrackListSeed trackListSeed, LibraryAlbumSeed libraryAlbumSeed, LibraryArtistSeed libraryArtistSeed, OpaqueTrackSearchSeed opaqueTrackSearchSeed, StationSeed stationSeed, SearchSeed searchSeed, AccountSeed accountSeed, SmartLibraryPlaylistSeed smartLibraryPlaylistSeed) {
        Intrinsics.checkNotNullParameter(queueSeedType, "queueSeedType");
        this.playMode = playModeEnum;
        this.queueSeedType = queueSeedType;
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
        ExpectedFunctionsKt.freeze(this);
    }

    public final SmartLibraryPlaylistSeed getSmartLibraryPlaylist() {
        return this.smartLibraryPlaylist;
    }
}
