package com.amazon.digitalmusicxp.inputs;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.PlayModeEnum;
import com.amazon.digitalmusicxp.enums.QueueSeedTypeEnum;
import com.amazon.digitalmusicxp.types.BasicQueueSeed;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QueueSeedContextInput.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b4\b\u0086\b\u0018\u00002\u00020\u0001BÉ\u0001\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0018\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a\u0012\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001f\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!¢\u0006\u0004\b\\\u0010]JÍ\u0001\u0010#\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!HÆ\u0001J\t\u0010%\u001a\u00020$HÖ\u0001J\t\u0010'\u001a\u00020&HÖ\u0001J\u0013\u0010+\u001a\u00020*2\b\u0010)\u001a\u0004\u0018\u00010(HÖ\u0003R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010,\u001a\u0004\b-\u0010.R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010/\u001a\u0004\b0\u00101R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u00102\u001a\u0004\b3\u00104R\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u00105\u001a\u0004\b6\u00107R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u00108\u001a\u0004\b9\u0010:R\u0019\u0010\r\u001a\u0004\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\r\u0010;\u001a\u0004\b<\u0010=R\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010>\u001a\u0004\b?\u0010@R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010A\u001a\u0004\bB\u0010CR\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010D\u001a\u0004\bE\u0010FR\u001f\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c8\u0006¢\u0006\f\n\u0004\b\u001e\u0010G\u001a\u0004\bH\u0010IR\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010J\u001a\u0004\bK\u0010LR\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010M\u001a\u0004\bN\u0010OR\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010P\u001a\u0004\bQ\u0010RR\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0006¢\u0006\f\n\u0004\b\u001b\u0010S\u001a\u0004\bT\u0010UR\u0019\u0010 \u001a\u0004\u0018\u00010\u001f8\u0006¢\u0006\f\n\u0004\b \u0010V\u001a\u0004\bW\u0010XR\u0019\u0010\"\u001a\u0004\u0018\u00010!8\u0006¢\u0006\f\n\u0004\b\"\u0010Y\u001a\u0004\bZ\u0010[¨\u0006^"}, d2 = {"Lcom/amazon/digitalmusicxp/inputs/QueueSeedContextInput;", "Lcom/amazon/digitalmusicxp/types/BasicQueueSeed;", "Lcom/amazon/digitalmusicxp/enums/PlayModeEnum;", ContextMappingConstants.PLAY_MODE, "Lcom/amazon/digitalmusicxp/enums/QueueSeedTypeEnum;", "queueSeedType", "Lcom/amazon/digitalmusicxp/inputs/QueueIdTransferSeedContextInput;", "queueIdTransfer", "Lcom/amazon/digitalmusicxp/inputs/QueueIdHistoricalPlaybackSeedContextInput;", "queueIdHistoricalPlayback", "Lcom/amazon/digitalmusicxp/inputs/PlayableEntitySeedContextInput;", "playableEntity", "Lcom/amazon/digitalmusicxp/inputs/TrackListSeedContextInput;", "trackList", "Lcom/amazon/digitalmusicxp/inputs/LibraryAlbumSeedContextInput;", "libraryAlbum", "Lcom/amazon/digitalmusicxp/inputs/LibraryArtistSeedContextInput;", "libraryArtist", "Lcom/amazon/digitalmusicxp/inputs/StationSeedContextInput;", "station", "Lcom/amazon/digitalmusicxp/inputs/AccountSeedContextInput;", "account", "Lcom/amazon/digitalmusicxp/inputs/SearchSeedContextInput;", "search", "Lcom/amazon/digitalmusicxp/inputs/OpaqueTrackSearchSeedContextInput;", "opaqueTrackSearch", "Lcom/amazon/digitalmusicxp/inputs/SmartLibraryPlaylistSeedContextInput;", "smartLibraryPlaylist", "", "Lcom/amazon/digitalmusicxp/inputs/SeedContextInput;", "seedContext", "Lcom/amazon/digitalmusicxp/inputs/VideoChannelSeedContextInput;", "videoChannel", "Lcom/amazon/digitalmusicxp/inputs/SourceDeviceTransferSeedContextInput;", "sourceDeviceTransfer", "copy", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/digitalmusicxp/enums/PlayModeEnum;", "getPlayMode", "()Lcom/amazon/digitalmusicxp/enums/PlayModeEnum;", "Lcom/amazon/digitalmusicxp/enums/QueueSeedTypeEnum;", "getQueueSeedType", "()Lcom/amazon/digitalmusicxp/enums/QueueSeedTypeEnum;", "Lcom/amazon/digitalmusicxp/inputs/QueueIdTransferSeedContextInput;", "getQueueIdTransfer", "()Lcom/amazon/digitalmusicxp/inputs/QueueIdTransferSeedContextInput;", "Lcom/amazon/digitalmusicxp/inputs/QueueIdHistoricalPlaybackSeedContextInput;", "getQueueIdHistoricalPlayback", "()Lcom/amazon/digitalmusicxp/inputs/QueueIdHistoricalPlaybackSeedContextInput;", "Lcom/amazon/digitalmusicxp/inputs/PlayableEntitySeedContextInput;", "getPlayableEntity", "()Lcom/amazon/digitalmusicxp/inputs/PlayableEntitySeedContextInput;", "Lcom/amazon/digitalmusicxp/inputs/TrackListSeedContextInput;", "getTrackList", "()Lcom/amazon/digitalmusicxp/inputs/TrackListSeedContextInput;", "Lcom/amazon/digitalmusicxp/inputs/LibraryAlbumSeedContextInput;", "getLibraryAlbum", "()Lcom/amazon/digitalmusicxp/inputs/LibraryAlbumSeedContextInput;", "Lcom/amazon/digitalmusicxp/inputs/LibraryArtistSeedContextInput;", "getLibraryArtist", "()Lcom/amazon/digitalmusicxp/inputs/LibraryArtistSeedContextInput;", "Lcom/amazon/digitalmusicxp/inputs/StationSeedContextInput;", "getStation", "()Lcom/amazon/digitalmusicxp/inputs/StationSeedContextInput;", "Ljava/util/List;", "getSeedContext", "()Ljava/util/List;", "Lcom/amazon/digitalmusicxp/inputs/AccountSeedContextInput;", "getAccount", "()Lcom/amazon/digitalmusicxp/inputs/AccountSeedContextInput;", "Lcom/amazon/digitalmusicxp/inputs/SearchSeedContextInput;", "getSearch", "()Lcom/amazon/digitalmusicxp/inputs/SearchSeedContextInput;", "Lcom/amazon/digitalmusicxp/inputs/OpaqueTrackSearchSeedContextInput;", "getOpaqueTrackSearch", "()Lcom/amazon/digitalmusicxp/inputs/OpaqueTrackSearchSeedContextInput;", "Lcom/amazon/digitalmusicxp/inputs/SmartLibraryPlaylistSeedContextInput;", "getSmartLibraryPlaylist", "()Lcom/amazon/digitalmusicxp/inputs/SmartLibraryPlaylistSeedContextInput;", "Lcom/amazon/digitalmusicxp/inputs/VideoChannelSeedContextInput;", "getVideoChannel", "()Lcom/amazon/digitalmusicxp/inputs/VideoChannelSeedContextInput;", "Lcom/amazon/digitalmusicxp/inputs/SourceDeviceTransferSeedContextInput;", "getSourceDeviceTransfer", "()Lcom/amazon/digitalmusicxp/inputs/SourceDeviceTransferSeedContextInput;", "<init>", "(Lcom/amazon/digitalmusicxp/enums/PlayModeEnum;Lcom/amazon/digitalmusicxp/enums/QueueSeedTypeEnum;Lcom/amazon/digitalmusicxp/inputs/QueueIdTransferSeedContextInput;Lcom/amazon/digitalmusicxp/inputs/QueueIdHistoricalPlaybackSeedContextInput;Lcom/amazon/digitalmusicxp/inputs/PlayableEntitySeedContextInput;Lcom/amazon/digitalmusicxp/inputs/TrackListSeedContextInput;Lcom/amazon/digitalmusicxp/inputs/LibraryAlbumSeedContextInput;Lcom/amazon/digitalmusicxp/inputs/LibraryArtistSeedContextInput;Lcom/amazon/digitalmusicxp/inputs/StationSeedContextInput;Lcom/amazon/digitalmusicxp/inputs/AccountSeedContextInput;Lcom/amazon/digitalmusicxp/inputs/SearchSeedContextInput;Lcom/amazon/digitalmusicxp/inputs/OpaqueTrackSearchSeedContextInput;Lcom/amazon/digitalmusicxp/inputs/SmartLibraryPlaylistSeedContextInput;Ljava/util/List;Lcom/amazon/digitalmusicxp/inputs/VideoChannelSeedContextInput;Lcom/amazon/digitalmusicxp/inputs/SourceDeviceTransferSeedContextInput;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class QueueSeedContextInput extends BasicQueueSeed {
    private final LibraryAlbumSeedContextInput libraryAlbum;
    private final LibraryArtistSeedContextInput libraryArtist;
    private final PlayModeEnum playMode;
    private final PlayableEntitySeedContextInput playableEntity;
    private final QueueIdHistoricalPlaybackSeedContextInput queueIdHistoricalPlayback;
    private final QueueIdTransferSeedContextInput queueIdTransfer;
    private final QueueSeedTypeEnum queueSeedType;
    private final List<SeedContextInput> seedContext;
    private final StationSeedContextInput station;
    private final TrackListSeedContextInput trackList;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ QueueSeedContextInput copy$default(QueueSeedContextInput queueSeedContextInput, PlayModeEnum playModeEnum, QueueSeedTypeEnum queueSeedTypeEnum, QueueIdTransferSeedContextInput queueIdTransferSeedContextInput, QueueIdHistoricalPlaybackSeedContextInput queueIdHistoricalPlaybackSeedContextInput, PlayableEntitySeedContextInput playableEntitySeedContextInput, TrackListSeedContextInput trackListSeedContextInput, LibraryAlbumSeedContextInput libraryAlbumSeedContextInput, LibraryArtistSeedContextInput libraryArtistSeedContextInput, StationSeedContextInput stationSeedContextInput, AccountSeedContextInput accountSeedContextInput, SearchSeedContextInput searchSeedContextInput, OpaqueTrackSearchSeedContextInput opaqueTrackSearchSeedContextInput, SmartLibraryPlaylistSeedContextInput smartLibraryPlaylistSeedContextInput, List list, VideoChannelSeedContextInput videoChannelSeedContextInput, SourceDeviceTransferSeedContextInput sourceDeviceTransferSeedContextInput, int i, Object obj) {
        AccountSeedContextInput accountSeedContextInput2;
        SearchSeedContextInput searchSeedContextInput2;
        OpaqueTrackSearchSeedContextInput opaqueTrackSearchSeedContextInput2;
        SmartLibraryPlaylistSeedContextInput smartLibraryPlaylistSeedContextInput2;
        VideoChannelSeedContextInput videoChannelSeedContextInput2;
        SourceDeviceTransferSeedContextInput sourceDeviceTransferSeedContextInput2;
        PlayModeEnum playModeEnum2 = (i & 1) != 0 ? queueSeedContextInput.playMode : playModeEnum;
        QueueSeedTypeEnum queueSeedTypeEnum2 = (i & 2) != 0 ? queueSeedContextInput.queueSeedType : queueSeedTypeEnum;
        QueueIdTransferSeedContextInput queueIdTransferSeedContextInput2 = (i & 4) != 0 ? queueSeedContextInput.queueIdTransfer : queueIdTransferSeedContextInput;
        QueueIdHistoricalPlaybackSeedContextInput queueIdHistoricalPlaybackSeedContextInput2 = (i & 8) != 0 ? queueSeedContextInput.queueIdHistoricalPlayback : queueIdHistoricalPlaybackSeedContextInput;
        PlayableEntitySeedContextInput playableEntitySeedContextInput2 = (i & 16) != 0 ? queueSeedContextInput.playableEntity : playableEntitySeedContextInput;
        TrackListSeedContextInput trackListSeedContextInput2 = (i & 32) != 0 ? queueSeedContextInput.trackList : trackListSeedContextInput;
        LibraryAlbumSeedContextInput libraryAlbumSeedContextInput2 = (i & 64) != 0 ? queueSeedContextInput.libraryAlbum : libraryAlbumSeedContextInput;
        LibraryArtistSeedContextInput libraryArtistSeedContextInput2 = (i & 128) != 0 ? queueSeedContextInput.libraryArtist : libraryArtistSeedContextInput;
        StationSeedContextInput stationSeedContextInput2 = (i & 256) != 0 ? queueSeedContextInput.station : stationSeedContextInput;
        if ((i & 512) != 0) {
            queueSeedContextInput.getClass();
            accountSeedContextInput2 = null;
        } else {
            accountSeedContextInput2 = accountSeedContextInput;
        }
        if ((i & 1024) != 0) {
            queueSeedContextInput.getClass();
            searchSeedContextInput2 = null;
        } else {
            searchSeedContextInput2 = searchSeedContextInput;
        }
        if ((i & 2048) != 0) {
            queueSeedContextInput.getClass();
            opaqueTrackSearchSeedContextInput2 = null;
        } else {
            opaqueTrackSearchSeedContextInput2 = opaqueTrackSearchSeedContextInput;
        }
        if ((i & 4096) != 0) {
            queueSeedContextInput.getClass();
            smartLibraryPlaylistSeedContextInput2 = null;
        } else {
            smartLibraryPlaylistSeedContextInput2 = smartLibraryPlaylistSeedContextInput;
        }
        List list2 = (i & 8192) != 0 ? queueSeedContextInput.seedContext : list;
        if ((i & 16384) != 0) {
            queueSeedContextInput.getClass();
            videoChannelSeedContextInput2 = null;
        } else {
            videoChannelSeedContextInput2 = videoChannelSeedContextInput;
        }
        if ((i & 32768) != 0) {
            queueSeedContextInput.getClass();
            sourceDeviceTransferSeedContextInput2 = null;
        } else {
            sourceDeviceTransferSeedContextInput2 = sourceDeviceTransferSeedContextInput;
        }
        return queueSeedContextInput.copy(playModeEnum2, queueSeedTypeEnum2, queueIdTransferSeedContextInput2, queueIdHistoricalPlaybackSeedContextInput2, playableEntitySeedContextInput2, trackListSeedContextInput2, libraryAlbumSeedContextInput2, libraryArtistSeedContextInput2, stationSeedContextInput2, accountSeedContextInput2, searchSeedContextInput2, opaqueTrackSearchSeedContextInput2, smartLibraryPlaylistSeedContextInput2, list2, videoChannelSeedContextInput2, sourceDeviceTransferSeedContextInput2);
    }

    public final QueueSeedContextInput copy(PlayModeEnum playMode, QueueSeedTypeEnum queueSeedType, QueueIdTransferSeedContextInput queueIdTransfer, QueueIdHistoricalPlaybackSeedContextInput queueIdHistoricalPlayback, PlayableEntitySeedContextInput playableEntity, TrackListSeedContextInput trackList, LibraryAlbumSeedContextInput libraryAlbum, LibraryArtistSeedContextInput libraryArtist, StationSeedContextInput station, AccountSeedContextInput account, SearchSeedContextInput search, OpaqueTrackSearchSeedContextInput opaqueTrackSearch, SmartLibraryPlaylistSeedContextInput smartLibraryPlaylist, List<SeedContextInput> seedContext, VideoChannelSeedContextInput videoChannel, SourceDeviceTransferSeedContextInput sourceDeviceTransfer) {
        Intrinsics.checkNotNullParameter(queueSeedType, "queueSeedType");
        return new QueueSeedContextInput(playMode, queueSeedType, queueIdTransfer, queueIdHistoricalPlayback, playableEntity, trackList, libraryAlbum, libraryArtist, station, account, search, opaqueTrackSearch, smartLibraryPlaylist, seedContext, videoChannel, sourceDeviceTransfer);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueueSeedContextInput)) {
            return false;
        }
        QueueSeedContextInput queueSeedContextInput = (QueueSeedContextInput) other;
        return this.playMode == queueSeedContextInput.playMode && this.queueSeedType == queueSeedContextInput.queueSeedType && Intrinsics.areEqual(this.queueIdTransfer, queueSeedContextInput.queueIdTransfer) && Intrinsics.areEqual(this.queueIdHistoricalPlayback, queueSeedContextInput.queueIdHistoricalPlayback) && Intrinsics.areEqual(this.playableEntity, queueSeedContextInput.playableEntity) && Intrinsics.areEqual(this.trackList, queueSeedContextInput.trackList) && Intrinsics.areEqual(this.libraryAlbum, queueSeedContextInput.libraryAlbum) && Intrinsics.areEqual(this.libraryArtist, queueSeedContextInput.libraryArtist) && Intrinsics.areEqual(this.station, queueSeedContextInput.station) && Intrinsics.areEqual((Object) null, (Object) null) && Intrinsics.areEqual((Object) null, (Object) null) && Intrinsics.areEqual((Object) null, (Object) null) && Intrinsics.areEqual((Object) null, (Object) null) && Intrinsics.areEqual(this.seedContext, queueSeedContextInput.seedContext) && Intrinsics.areEqual((Object) null, (Object) null) && Intrinsics.areEqual((Object) null, (Object) null);
    }

    public final AccountSeedContextInput getAccount() {
        return null;
    }

    public final OpaqueTrackSearchSeedContextInput getOpaqueTrackSearch() {
        return null;
    }

    public final SearchSeedContextInput getSearch() {
        return null;
    }

    public final SmartLibraryPlaylistSeedContextInput getSmartLibraryPlaylist() {
        return null;
    }

    public final SourceDeviceTransferSeedContextInput getSourceDeviceTransfer() {
        return null;
    }

    public final VideoChannelSeedContextInput getVideoChannel() {
        return null;
    }

    public int hashCode() {
        PlayModeEnum playModeEnum = this.playMode;
        int iHashCode = (((playModeEnum == null ? 0 : playModeEnum.hashCode()) * 31) + this.queueSeedType.hashCode()) * 31;
        QueueIdTransferSeedContextInput queueIdTransferSeedContextInput = this.queueIdTransfer;
        int iHashCode2 = (iHashCode + (queueIdTransferSeedContextInput == null ? 0 : queueIdTransferSeedContextInput.hashCode())) * 31;
        QueueIdHistoricalPlaybackSeedContextInput queueIdHistoricalPlaybackSeedContextInput = this.queueIdHistoricalPlayback;
        int iHashCode3 = (iHashCode2 + (queueIdHistoricalPlaybackSeedContextInput == null ? 0 : queueIdHistoricalPlaybackSeedContextInput.hashCode())) * 31;
        PlayableEntitySeedContextInput playableEntitySeedContextInput = this.playableEntity;
        int iHashCode4 = (iHashCode3 + (playableEntitySeedContextInput == null ? 0 : playableEntitySeedContextInput.hashCode())) * 31;
        TrackListSeedContextInput trackListSeedContextInput = this.trackList;
        int iHashCode5 = (iHashCode4 + (trackListSeedContextInput == null ? 0 : trackListSeedContextInput.hashCode())) * 31;
        LibraryAlbumSeedContextInput libraryAlbumSeedContextInput = this.libraryAlbum;
        int iHashCode6 = (iHashCode5 + (libraryAlbumSeedContextInput == null ? 0 : libraryAlbumSeedContextInput.hashCode())) * 31;
        LibraryArtistSeedContextInput libraryArtistSeedContextInput = this.libraryArtist;
        int iHashCode7 = (iHashCode6 + (libraryArtistSeedContextInput == null ? 0 : libraryArtistSeedContextInput.hashCode())) * 31;
        StationSeedContextInput stationSeedContextInput = this.station;
        int iHashCode8 = (((((((((iHashCode7 + (stationSeedContextInput == null ? 0 : stationSeedContextInput.hashCode())) * 31) + 0) * 31) + 0) * 31) + 0) * 31) + 0) * 31;
        List<SeedContextInput> list = this.seedContext;
        return ((((iHashCode8 + (list == null ? 0 : list.hashCode())) * 31) + 0) * 31) + 0;
    }

    public String toString() {
        return "QueueSeedContextInput(playMode=" + this.playMode + ", queueSeedType=" + this.queueSeedType + ", queueIdTransfer=" + this.queueIdTransfer + ", queueIdHistoricalPlayback=" + this.queueIdHistoricalPlayback + ", playableEntity=" + this.playableEntity + ", trackList=" + this.trackList + ", libraryAlbum=" + this.libraryAlbum + ", libraryArtist=" + this.libraryArtist + ", station=" + this.station + ", account=" + ((Object) null) + ", search=" + ((Object) null) + ", opaqueTrackSearch=" + ((Object) null) + ", smartLibraryPlaylist=" + ((Object) null) + ", seedContext=" + this.seedContext + ", videoChannel=" + ((Object) null) + ", sourceDeviceTransfer=" + ((Object) null) + ')';
    }

    public /* synthetic */ QueueSeedContextInput(PlayModeEnum playModeEnum, QueueSeedTypeEnum queueSeedTypeEnum, QueueIdTransferSeedContextInput queueIdTransferSeedContextInput, QueueIdHistoricalPlaybackSeedContextInput queueIdHistoricalPlaybackSeedContextInput, PlayableEntitySeedContextInput playableEntitySeedContextInput, TrackListSeedContextInput trackListSeedContextInput, LibraryAlbumSeedContextInput libraryAlbumSeedContextInput, LibraryArtistSeedContextInput libraryArtistSeedContextInput, StationSeedContextInput stationSeedContextInput, AccountSeedContextInput accountSeedContextInput, SearchSeedContextInput searchSeedContextInput, OpaqueTrackSearchSeedContextInput opaqueTrackSearchSeedContextInput, SmartLibraryPlaylistSeedContextInput smartLibraryPlaylistSeedContextInput, List list, VideoChannelSeedContextInput videoChannelSeedContextInput, SourceDeviceTransferSeedContextInput sourceDeviceTransferSeedContextInput, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : playModeEnum, queueSeedTypeEnum, (i & 4) != 0 ? null : queueIdTransferSeedContextInput, (i & 8) != 0 ? null : queueIdHistoricalPlaybackSeedContextInput, (i & 16) != 0 ? null : playableEntitySeedContextInput, (i & 32) != 0 ? null : trackListSeedContextInput, (i & 64) != 0 ? null : libraryAlbumSeedContextInput, (i & 128) != 0 ? null : libraryArtistSeedContextInput, (i & 256) != 0 ? null : stationSeedContextInput, (i & 512) != 0 ? null : accountSeedContextInput, (i & 1024) != 0 ? null : searchSeedContextInput, (i & 2048) != 0 ? null : opaqueTrackSearchSeedContextInput, (i & 4096) != 0 ? null : smartLibraryPlaylistSeedContextInput, (i & 8192) != 0 ? null : list, (i & 16384) != 0 ? null : videoChannelSeedContextInput, (i & 32768) != 0 ? null : sourceDeviceTransferSeedContextInput);
    }

    public final PlayModeEnum getPlayMode() {
        return this.playMode;
    }

    public final QueueSeedTypeEnum getQueueSeedType() {
        return this.queueSeedType;
    }

    public final QueueIdTransferSeedContextInput getQueueIdTransfer() {
        return this.queueIdTransfer;
    }

    public final QueueIdHistoricalPlaybackSeedContextInput getQueueIdHistoricalPlayback() {
        return this.queueIdHistoricalPlayback;
    }

    public final PlayableEntitySeedContextInput getPlayableEntity() {
        return this.playableEntity;
    }

    public final TrackListSeedContextInput getTrackList() {
        return this.trackList;
    }

    public final LibraryAlbumSeedContextInput getLibraryAlbum() {
        return this.libraryAlbum;
    }

    public final LibraryArtistSeedContextInput getLibraryArtist() {
        return this.libraryArtist;
    }

    public final StationSeedContextInput getStation() {
        return this.station;
    }

    public final List<SeedContextInput> getSeedContext() {
        return this.seedContext;
    }

    public QueueSeedContextInput(PlayModeEnum playModeEnum, QueueSeedTypeEnum queueSeedType, QueueIdTransferSeedContextInput queueIdTransferSeedContextInput, QueueIdHistoricalPlaybackSeedContextInput queueIdHistoricalPlaybackSeedContextInput, PlayableEntitySeedContextInput playableEntitySeedContextInput, TrackListSeedContextInput trackListSeedContextInput, LibraryAlbumSeedContextInput libraryAlbumSeedContextInput, LibraryArtistSeedContextInput libraryArtistSeedContextInput, StationSeedContextInput stationSeedContextInput, AccountSeedContextInput accountSeedContextInput, SearchSeedContextInput searchSeedContextInput, OpaqueTrackSearchSeedContextInput opaqueTrackSearchSeedContextInput, SmartLibraryPlaylistSeedContextInput smartLibraryPlaylistSeedContextInput, List<SeedContextInput> list, VideoChannelSeedContextInput videoChannelSeedContextInput, SourceDeviceTransferSeedContextInput sourceDeviceTransferSeedContextInput) {
        Intrinsics.checkNotNullParameter(queueSeedType, "queueSeedType");
        this.playMode = playModeEnum;
        this.queueSeedType = queueSeedType;
        this.queueIdTransfer = queueIdTransferSeedContextInput;
        this.queueIdHistoricalPlayback = queueIdHistoricalPlaybackSeedContextInput;
        this.playableEntity = playableEntitySeedContextInput;
        this.trackList = trackListSeedContextInput;
        this.libraryAlbum = libraryAlbumSeedContextInput;
        this.libraryArtist = libraryArtistSeedContextInput;
        this.station = stationSeedContextInput;
        this.seedContext = list;
        ExpectedFunctionsKt.freeze(this);
    }
}
