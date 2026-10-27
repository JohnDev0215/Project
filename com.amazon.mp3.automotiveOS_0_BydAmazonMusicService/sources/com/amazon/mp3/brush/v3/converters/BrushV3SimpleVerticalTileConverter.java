package com.amazon.mp3.brush.v3.converters;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.brush.converters.BrushConverterUtils;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlaybackHandlerUtil;
import com.amazon.music.media.auto.brush.ContentMetadataConverter;
import com.amazon.music.media.auto.util.BrushConverterUtil;
import com.amazon.music.media.auto.util.ContentPlaybackUtil;
import com.amazon.music.page.api.model.Album;
import com.amazon.music.page.api.model.Artist;
import com.amazon.music.page.api.model.Entity;
import com.amazon.music.page.api.model.Playlist;
import com.amazon.music.page.api.model.PodcastEpisode;
import com.amazon.music.page.api.model.PodcastShow;
import com.amazon.music.page.api.model.Station;
import com.amazon.music.page.api.model.Track;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.SimpleVerticalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushV3SimpleVerticalTileConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 02\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u00010B\u001b\u0012\b\b\u0002\u0010+\u001a\u00020*\u0012\b\b\u0002\u0010-\u001a\u00020,¢\u0006\u0004\b.\u0010/J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0002H\u0002J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0015H\u0002J\u0010\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\u0010\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001bH\u0002J7\u0010%\u001a\u00020$2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010 \u001a\u0004\u0018\u00010\u001e2\u0006\u0010\"\u001a\u00020!2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010!H\u0002¢\u0006\u0004\b%\u0010&J\u0012\u0010)\u001a\u0004\u0018\u00010(2\u0006\u0010'\u001a\u00020\u0002H\u0016¨\u00061"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3SimpleVerticalTileConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/page/api/model/Entity;", "Lcom/amazon/music/media/auto/brush/ContentMetadataConverter;", "entity", "Lcom/amazon/music/views/library/models/SimpleVerticalTileModel;", "convertEntity", "Lcom/amazon/music/page/api/model/Track;", "track", "convertTrack", "Lcom/amazon/music/page/api/model/Playlist;", Environment.PLAYLIST_PATH, "convertPlaylist", "convertUserPlaylist", "convertCatalogPlaylist", "Lcom/amazon/music/page/api/model/Album;", "album", "convertAlbum", "Lcom/amazon/music/page/api/model/Station;", "station", "convertStation", "Lcom/amazon/music/page/api/model/Artist;", "artist", "convertArtist", "Lcom/amazon/music/page/api/model/PodcastEpisode;", "podcastEpisode", "convertPodcastEpisode", "Lcom/amazon/music/page/api/model/PodcastShow;", "podcastShow", "convertPodcastShow", "", "blockRef", "imageUrl", "", "contentType", "playIconType", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "createArtworkFrameModel", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;)Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "ContentPlaybackUtil", "Lcom/amazon/music/media/auto/util/BrushConverterUtil;", "BrushConverterUtil", "<init>", "(Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;Lcom/amazon/music/media/auto/util/BrushConverterUtil;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushV3SimpleVerticalTileConverter extends ContentMetadataConverter implements SingleBaseModelConverter<Entity> {
    private static final String TAG = BrushV3SimpleVerticalTileConverter.class.getSimpleName();

    public BrushV3SimpleVerticalTileConverter() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public /* synthetic */ BrushV3SimpleVerticalTileConverter(ContentPlaybackUtil contentPlaybackUtil, BrushConverterUtil brushConverterUtil, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ContentPlaybackUtils.INSTANCE : contentPlaybackUtil, (i & 2) != 0 ? BrushConverterUtils.INSTANCE : brushConverterUtil);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BrushV3SimpleVerticalTileConverter(ContentPlaybackUtil ContentPlaybackUtil, BrushConverterUtil BrushConverterUtil) {
        super(ContentPlaybackUtil, BrushConverterUtil);
        Intrinsics.checkNotNullParameter(ContentPlaybackUtil, "ContentPlaybackUtil");
        Intrinsics.checkNotNullParameter(BrushConverterUtil, "BrushConverterUtil");
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return convertEntity(data);
    }

    private final SimpleVerticalTileModel convertEntity(Entity entity) {
        if (entity.getTrack() != null) {
            Track track = entity.getTrack();
            Intrinsics.checkNotNullExpressionValue(track, "entity.track");
            return convertTrack(track);
        }
        if (entity.getAlbum() != null) {
            Album album = entity.getAlbum();
            Intrinsics.checkNotNullExpressionValue(album, "entity.album");
            return convertAlbum(album);
        }
        if (entity.getPlaylist() != null) {
            Playlist playlist = entity.getPlaylist();
            Intrinsics.checkNotNullExpressionValue(playlist, "entity.playlist");
            return convertPlaylist(playlist);
        }
        if (entity.getStation() != null) {
            Station station = entity.getStation();
            Intrinsics.checkNotNullExpressionValue(station, "entity.station");
            return convertStation(station);
        }
        if (entity.getArtist() != null) {
            Artist artist = entity.getArtist();
            Intrinsics.checkNotNullExpressionValue(artist, "entity.artist");
            return convertArtist(artist);
        }
        if (entity.getPodcastEpisode() != null) {
            PodcastEpisode podcastEpisode = entity.getPodcastEpisode();
            Intrinsics.checkNotNullExpressionValue(podcastEpisode, "entity.podcastEpisode");
            return convertPodcastEpisode(podcastEpisode);
        }
        if (entity.getPodcastShow() == null) {
            return null;
        }
        PodcastShow podcastShow = entity.getPodcastShow();
        Intrinsics.checkNotNullExpressionValue(podcastShow, "entity.podcastShow");
        return convertPodcastShow(podcastShow);
    }

    private final SimpleVerticalTileModel convertTrack(Track track) {
        String blockRef = track.getBlockRef();
        TrackMetadata trackMetadataConvertTrackMetadata = convertTrackMetadata(track, blockRef == null ? null : CollectionsKt__CollectionsJVMKt.listOf(Intrinsics.stringPlus("WIDGET_ID_", blockRef)));
        return new SimpleVerticalTileModel(track.getBlockRef(), track.getTitle(), createArtworkFrameModel$default(this, track.getBlockRef(), trackMetadataConvertTrackMetadata.getImageUrl(), 2, null, 8, null), 2, trackMetadataConvertTrackMetadata, null, 32, null);
    }

    private final SimpleVerticalTileModel convertPlaylist(Playlist playlist) {
        if (PrimePlaylistUtil.isAsin(playlist.getAsin())) {
            return convertCatalogPlaylist(playlist);
        }
        return convertUserPlaylist(playlist);
    }

    private final SimpleVerticalTileModel convertUserPlaylist(Playlist playlist) {
        String blockRef = playlist.getBlockRef();
        UserPlaylistMetadata userPlaylistMetadataConvertUserPlaylistMetadata = convertUserPlaylistMetadata(playlist, blockRef == null ? null : CollectionsKt__CollectionsJVMKt.listOf(Intrinsics.stringPlus("WIDGET_ID_", blockRef)));
        try {
            Uri uriGenerateUserPlaylistContentUri = PlaybackHandlerUtil.INSTANCE.generateUserPlaylistContentUri(AmazonApplication.getApplication().getApplicationContext(), userPlaylistMetadataConvertUserPlaylistMetadata);
            if (uriGenerateUserPlaylistContentUri != null) {
                userPlaylistMetadataConvertUserPlaylistMetadata.setUri(uriGenerateUserPlaylistContentUri);
            }
        } catch (Exception e) {
            Log.error(TAG, "Uri is empty for User Playlist.", e);
        }
        return new SimpleVerticalTileModel(playlist.getBlockRef(), playlist.getTitle(), createArtworkFrameModel$default(this, playlist.getBlockRef(), userPlaylistMetadataConvertUserPlaylistMetadata.getImageUrl(), 1, null, 8, null), 1, userPlaylistMetadataConvertUserPlaylistMetadata, null, 32, null);
    }

    private final SimpleVerticalTileModel convertCatalogPlaylist(Playlist playlist) {
        String blockRef = playlist.getBlockRef();
        PlaylistMetadata playlistMetadataConvertPlaylistMetadata = convertPlaylistMetadata(playlist, blockRef == null ? null : CollectionsKt__CollectionsJVMKt.listOf(Intrinsics.stringPlus("WIDGET_ID_", blockRef)));
        return new SimpleVerticalTileModel(playlist.getBlockRef(), playlist.getTitle(), createArtworkFrameModel$default(this, playlist.getBlockRef(), playlistMetadataConvertPlaylistMetadata.getImageUrl(), 1, null, 8, null), 1, playlistMetadataConvertPlaylistMetadata, null, 32, null);
    }

    private final SimpleVerticalTileModel convertAlbum(Album album) {
        String blockRef = album.getBlockRef();
        AlbumMetadata albumMetadataConvertAlbumMetadata = convertAlbumMetadata(album, blockRef == null ? null : CollectionsKt__CollectionsJVMKt.listOf(Intrinsics.stringPlus("WIDGET_ID_", blockRef)));
        return new SimpleVerticalTileModel(album.getBlockRef(), album.getTitle(), createArtworkFrameModel$default(this, album.getBlockRef(), albumMetadataConvertAlbumMetadata.getImageUrl(), 0, null, 8, null), 0, albumMetadataConvertAlbumMetadata, null, 32, null);
    }

    private final SimpleVerticalTileModel convertStation(Station station) {
        String blockRef = station.getBlockRef();
        StationMetadata stationMetadataConvertStationMetadata = convertStationMetadata(station, blockRef == null ? null : CollectionsKt__CollectionsJVMKt.listOf(Intrinsics.stringPlus("WIDGET_ID_", blockRef)));
        return new SimpleVerticalTileModel(station.getBlockRef(), station.getTitle(), createArtworkFrameModel(station.getBlockRef(), stationMetadataConvertStationMetadata.getImageUrl(), 3, 1), 3, stationMetadataConvertStationMetadata, null, 32, null);
    }

    private final SimpleVerticalTileModel convertArtist(Artist artist) {
        String blockRef = artist.getBlockRef();
        ArtistMetadata artistMetadataConvertArtistMetadata = convertArtistMetadata(artist, blockRef == null ? null : CollectionsKt__CollectionsJVMKt.listOf(Intrinsics.stringPlus("WIDGET_ID_", blockRef)));
        return new SimpleVerticalTileModel(artist.getBlockRef(), artist.getPrimaryTitle(), createArtworkFrameModel$default(this, artist.getBlockRef(), artistMetadataConvertArtistMetadata.getImageUrl(), 4, null, 8, null), 4, artistMetadataConvertArtistMetadata, null, 32, null);
    }

    private final SimpleVerticalTileModel convertPodcastEpisode(PodcastEpisode podcastEpisode) {
        String blockRef = podcastEpisode.getBlockRef();
        PodcastMetadata podcastMetadataConvertPodcastEpisodeMetadata = convertPodcastEpisodeMetadata(podcastEpisode, blockRef == null ? null : CollectionsKt__CollectionsJVMKt.listOf(Intrinsics.stringPlus("WIDGET_ID_", blockRef)));
        return new SimpleVerticalTileModel(podcastEpisode.getBlockRef(), podcastEpisode.getTitle(), createArtworkFrameModel$default(this, podcastEpisode.getBlockRef(), podcastMetadataConvertPodcastEpisodeMetadata.getImageUrl(), 11, null, 8, null), 11, podcastMetadataConvertPodcastEpisodeMetadata, null, 32, null);
    }

    private final SimpleVerticalTileModel convertPodcastShow(PodcastShow podcastShow) {
        String blockRef = podcastShow.getBlockRef();
        PodcastMetadata podcastMetadataConvertPodcastShowMetadata = convertPodcastShowMetadata(podcastShow, blockRef == null ? null : CollectionsKt__CollectionsJVMKt.listOf(Intrinsics.stringPlus("WIDGET_ID_", blockRef)));
        return new SimpleVerticalTileModel(podcastShow.getBlockRef(), podcastShow.getTitle(), createArtworkFrameModel$default(this, podcastShow.getBlockRef(), podcastMetadataConvertPodcastShowMetadata.getImageUrl(), 12, null, 8, null), 12, podcastMetadataConvertPodcastShowMetadata, null, 32, null);
    }

    static /* synthetic */ ArtworkFrameModel createArtworkFrameModel$default(BrushV3SimpleVerticalTileConverter brushV3SimpleVerticalTileConverter, String str, String str2, int i, Integer num, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            num = null;
        }
        return brushV3SimpleVerticalTileConverter.createArtworkFrameModel(str, str2, i, num);
    }

    private final ArtworkFrameModel createArtworkFrameModel(String blockRef, String imageUrl, int contentType, Integer playIconType) {
        return new ArtworkFrameModel(blockRef, imageUrl, Integer.valueOf(contentType), null, null, playIconType, null, null, null, null, null, null, 4056, null);
    }
}
