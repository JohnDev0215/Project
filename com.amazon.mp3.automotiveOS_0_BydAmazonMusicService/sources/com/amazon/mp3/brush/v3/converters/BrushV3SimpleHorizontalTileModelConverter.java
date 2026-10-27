package com.amazon.mp3.brush.v3.converters;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.auto.carmode.CarModePresetsUtility;
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
import com.amazon.music.page.api.model.RenderingType;
import com.amazon.music.page.api.model.Station;
import com.amazon.music.page.api.model.Track;
import com.amazon.music.page.api.model.Widget;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.SimpleHorizontalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.HorizontalTileType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: BrushV3SimpleHorizontalTileModelConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 :2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001:B%\u0012\b\b\u0002\u00105\u001a\u000204\u0012\b\b\u0002\u00107\u001a\u000206\u0012\b\u00102\u001a\u0004\u0018\u000101¢\u0006\u0004\b8\u00109J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0002H\u0002J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0002H\u0002J*\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00022\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0002J(\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u00022\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0002J\u0018\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u0002H\u0002J\u0018\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u0002H\u0002J\u0018\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u0002H\u0002J\u0018\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0004\u001a\u00020\u0002H\u0002J\u0018\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0004\u001a\u00020\u0002H\u0002J\u0018\u0010\u001f\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0004\u001a\u00020\u0002H\u0002J\u0018\u0010\"\u001a\u00020\r2\u0006\u0010!\u001a\u00020 2\u0006\u0010\u0004\u001a\u00020\u0002H\u0002J\u0018\u0010%\u001a\u00020\r2\u0006\u0010$\u001a\u00020#2\u0006\u0010\u0004\u001a\u00020\u0002H\u0002J7\u0010-\u001a\u00020,2\b\u0010'\u001a\u0004\u0018\u00010&2\b\u0010(\u001a\u0004\u0018\u00010&2\u0006\u0010*\u001a\u00020)2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010)H\u0002¢\u0006\u0004\b-\u0010.J\u0016\u0010/\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0004\u001a\u00020\u0002H\u0002J\u0012\u00100\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0002H\u0016R\u0016\u00102\u001a\u0004\u0018\u0001018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103¨\u0006;"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3SimpleHorizontalTileModelConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/page/api/model/Widget;", "Lcom/amazon/music/media/auto/brush/ContentMetadataConverter;", "widget", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convertSingleEntityWidget", "convertMultiEntityWidget", "Lcom/amazon/music/page/api/model/Entity;", "entity", "", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "metadataList", "Lcom/amazon/music/views/library/models/SimpleHorizontalTileModel;", "convertEntity", "Lcom/amazon/music/page/api/model/Track;", "track", "convertTrack", "Lcom/amazon/music/page/api/model/Playlist;", Environment.PLAYLIST_PATH, "convertPlaylist", "convertUserPlaylist", "convertCatalogPlaylist", "Lcom/amazon/music/page/api/model/Album;", "album", "convertAlbum", "Lcom/amazon/music/page/api/model/Station;", "station", "convertStation", "Lcom/amazon/music/page/api/model/Artist;", "artist", "convertArtist", "Lcom/amazon/music/page/api/model/PodcastEpisode;", "podcastEpisode", "convertPodcastEpisode", "Lcom/amazon/music/page/api/model/PodcastShow;", "podcastShow", "convertPodcastShow", "", "blockRef", "imageUrl", "", "contentType", "playIconType", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "createArtworkFrameModel", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;)Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "createMetadataList", "convert", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "ContentPlaybackUtil", "Lcom/amazon/music/media/auto/util/BrushConverterUtil;", "BrushConverterUtil", "<init>", "(Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;Lcom/amazon/music/media/auto/util/BrushConverterUtil;Landroid/content/Context;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushV3SimpleHorizontalTileModelConverter extends ContentMetadataConverter implements SingleBaseModelConverter<Widget> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = BrushV3SimpleHorizontalTileModelConverter.class.getSimpleName();
    private final Context context;

    /* JADX INFO: compiled from: BrushV3SimpleHorizontalTileModelConverter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RenderingType.values().length];
            iArr[RenderingType.PRESET_SINGLE_ENTITY.ordinal()] = 1;
            iArr[RenderingType.PRESET_MULTI_ENTITY.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ BrushV3SimpleHorizontalTileModelConverter(ContentPlaybackUtil contentPlaybackUtil, BrushConverterUtil brushConverterUtil, Context context, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ContentPlaybackUtils.INSTANCE : contentPlaybackUtil, (i & 2) != 0 ? BrushConverterUtils.INSTANCE : brushConverterUtil, context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BrushV3SimpleHorizontalTileModelConverter(ContentPlaybackUtil ContentPlaybackUtil, BrushConverterUtil BrushConverterUtil, Context context) {
        super(ContentPlaybackUtil, BrushConverterUtil);
        Intrinsics.checkNotNullParameter(ContentPlaybackUtil, "ContentPlaybackUtil");
        Intrinsics.checkNotNullParameter(BrushConverterUtil, "BrushConverterUtil");
        this.context = context;
    }

    /* JADX INFO: compiled from: BrushV3SimpleHorizontalTileModelConverter.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\u0004\u0018\u00010\u0004*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3SimpleHorizontalTileModelConverter$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "widgetId", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getWidgetId", "(Lcom/amazon/music/views/library/metadata/ContentMetadata;)Ljava/lang/String;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getWidgetId(ContentMetadata contentMetadata) {
            Object next;
            Intrinsics.checkNotNullParameter(contentMetadata, "<this>");
            List<String> tags = contentMetadata.getTags();
            if (tags == null) {
                return null;
            }
            Iterator<T> it = tags.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (StringsKt__StringsKt.contains$default((CharSequence) next, (CharSequence) "WIDGET_ID_", false, 2, (Object) null)) {
                    break;
                }
            }
            String str = (String) next;
            if (str == null) {
                return null;
            }
            return StringsKt__StringsKt.removePrefix(str, (CharSequence) "WIDGET_ID_");
        }
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Widget widget) {
        Intrinsics.checkNotNullParameter(widget, "widget");
        RenderingType renderingType = widget.getRenderingType();
        int i = renderingType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[renderingType.ordinal()];
        if (i == 1) {
            return convertSingleEntityWidget(widget);
        }
        if (i != 2) {
            return null;
        }
        return convertMultiEntityWidget(widget);
    }

    private final BaseViewModel convertSingleEntityWidget(Widget widget) {
        List<Entity> entities = widget.getContent().getEntities();
        if (entities == null || entities.isEmpty()) {
            return null;
        }
        List<Entity> entities2 = widget.getContent().getEntities();
        Intrinsics.checkNotNullExpressionValue(entities2, "widget.content.entities");
        Object objFirst = CollectionsKt___CollectionsKt.first((List<? extends Object>) entities2);
        Intrinsics.checkNotNullExpressionValue(objFirst, "widget.content.entities.first()");
        return convertEntity((Entity) objFirst, widget, null);
    }

    private final BaseViewModel convertMultiEntityWidget(Widget widget) {
        List<Entity> entities = widget.getContent().getEntities();
        if (entities == null || entities.isEmpty()) {
            return null;
        }
        List<TrackMetadata> listCreateMetadataList = createMetadataList(widget);
        List<Entity> entities2 = widget.getContent().getEntities();
        Intrinsics.checkNotNullExpressionValue(entities2, "widget.content.entities");
        Object objFirst = CollectionsKt___CollectionsKt.first((List<? extends Object>) entities2);
        Intrinsics.checkNotNullExpressionValue(objFirst, "widget.content.entities.first()");
        return convertEntity((Entity) objFirst, widget, listCreateMetadataList);
    }

    private final SimpleHorizontalTileModel convertEntity(Entity entity, Widget widget, List<TrackMetadata> metadataList) {
        if (entity.getTrack() != null) {
            Track track = entity.getTrack();
            Intrinsics.checkNotNullExpressionValue(track, "entity.track");
            return convertTrack(track, widget, metadataList);
        }
        if (entity.getAlbum() != null) {
            Album album = entity.getAlbum();
            Intrinsics.checkNotNullExpressionValue(album, "entity.album");
            return convertAlbum(album, widget);
        }
        if (entity.getPlaylist() != null) {
            Playlist playlist = entity.getPlaylist();
            Intrinsics.checkNotNullExpressionValue(playlist, "entity.playlist");
            return convertPlaylist(playlist, widget);
        }
        if (entity.getStation() != null) {
            Station station = entity.getStation();
            Intrinsics.checkNotNullExpressionValue(station, "entity.station");
            return convertStation(station, widget);
        }
        if (entity.getArtist() != null) {
            Artist artist = entity.getArtist();
            Intrinsics.checkNotNullExpressionValue(artist, "entity.artist");
            return convertArtist(artist, widget);
        }
        if (entity.getPodcastEpisode() != null) {
            PodcastEpisode podcastEpisode = entity.getPodcastEpisode();
            Intrinsics.checkNotNullExpressionValue(podcastEpisode, "entity.podcastEpisode");
            return convertPodcastEpisode(podcastEpisode, widget);
        }
        if (entity.getPodcastShow() == null) {
            return null;
        }
        PodcastShow podcastShow = entity.getPodcastShow();
        Intrinsics.checkNotNullExpressionValue(podcastShow, "entity.podcastShow");
        return convertPodcastShow(podcastShow, widget);
    }

    private final SimpleHorizontalTileModel convertTrack(Track track, Widget widget, List<TrackMetadata> metadataList) {
        TrackMetadata trackMetadataConvertTrackMetadata;
        if (metadataList == null || metadataList.isEmpty()) {
            String id = widget.getId();
            trackMetadataConvertTrackMetadata = convertTrackMetadata(track, id == null ? null : CollectionsKt__CollectionsJVMKt.listOf(Intrinsics.stringPlus("WIDGET_ID_", id)));
        } else {
            trackMetadataConvertTrackMetadata = (TrackMetadata) CollectionsKt___CollectionsKt.first((List) metadataList);
        }
        TrackMetadata trackMetadata = trackMetadataConvertTrackMetadata;
        HorizontalTileType horizontalTileType = HorizontalTileType.REGULAR;
        String blockRef = track.getBlockRef();
        String title = widget.getTitle();
        if (title == null) {
            title = track.getTitle();
        }
        String str = title;
        String strConvertContentTypeToSubtitle = CarModePresetsUtility.INSTANCE.convertContentTypeToSubtitle(2, trackMetadata, this.context);
        Boolean isInLibrary = track.getIsInLibrary();
        return new SimpleHorizontalTileModel(blockRef, str, strConvertContentTypeToSubtitle, null, createArtworkFrameModel$default(this, track.getBlockRef(), trackMetadata.getImageUrl(), 2, null, 8, null), true, isInLibrary != null ? isInLibrary.booleanValue() : false, horizontalTileType, 2, trackMetadata, metadataList, false, 2056, null);
    }

    private final SimpleHorizontalTileModel convertPlaylist(Playlist playlist, Widget widget) {
        if (PrimePlaylistUtil.isAsin(playlist.getAsin())) {
            return convertCatalogPlaylist(playlist, widget);
        }
        return convertUserPlaylist(playlist, widget);
    }

    private final SimpleHorizontalTileModel convertUserPlaylist(Playlist playlist, Widget widget) {
        String id = widget.getId();
        UserPlaylistMetadata userPlaylistMetadataConvertUserPlaylistMetadata = convertUserPlaylistMetadata(playlist, id == null ? null : CollectionsKt__CollectionsJVMKt.listOf(Intrinsics.stringPlus("WIDGET_ID_", id)));
        try {
            Uri uriGenerateUserPlaylistContentUri = PlaybackHandlerUtil.INSTANCE.generateUserPlaylistContentUri(this.context, userPlaylistMetadataConvertUserPlaylistMetadata);
            if (uriGenerateUserPlaylistContentUri != null) {
                userPlaylistMetadataConvertUserPlaylistMetadata.setUri(uriGenerateUserPlaylistContentUri);
            }
        } catch (Exception e) {
            Log.error(TAG, "Uri is empty for User Playlist.", e);
        }
        return new SimpleHorizontalTileModel(playlist.getBlockRef(), widget.getTitle(), CarModePresetsUtility.INSTANCE.convertContentTypeToSubtitle(1, userPlaylistMetadataConvertUserPlaylistMetadata, this.context), null, createArtworkFrameModel$default(this, playlist.getBlockRef(), userPlaylistMetadataConvertUserPlaylistMetadata.getImageUrl(), 1, null, 8, null), false, false, HorizontalTileType.REGULAR, 1, userPlaylistMetadataConvertUserPlaylistMetadata, null, false, 3176, null);
    }

    private final SimpleHorizontalTileModel convertCatalogPlaylist(Playlist playlist, Widget widget) {
        String id = widget.getId();
        PlaylistMetadata playlistMetadataConvertPlaylistMetadata = convertPlaylistMetadata(playlist, id == null ? null : CollectionsKt__CollectionsJVMKt.listOf(Intrinsics.stringPlus("WIDGET_ID_", id)));
        return new SimpleHorizontalTileModel(playlist.getBlockRef(), widget.getTitle(), CarModePresetsUtility.INSTANCE.convertContentTypeToSubtitle(1, playlistMetadataConvertPlaylistMetadata, this.context), null, createArtworkFrameModel$default(this, playlist.getBlockRef(), playlistMetadataConvertPlaylistMetadata.getImageUrl(), 1, null, 8, null), false, false, HorizontalTileType.REGULAR, 1, playlistMetadataConvertPlaylistMetadata, null, false, 3176, null);
    }

    private final SimpleHorizontalTileModel convertAlbum(Album album, Widget widget) {
        String id = widget.getId();
        AlbumMetadata albumMetadataConvertAlbumMetadata = convertAlbumMetadata(album, id == null ? null : CollectionsKt__CollectionsJVMKt.listOf(Intrinsics.stringPlus("WIDGET_ID_", id)));
        return new SimpleHorizontalTileModel(album.getBlockRef(), widget.getTitle(), CarModePresetsUtility.INSTANCE.convertContentTypeToSubtitle(0, albumMetadataConvertAlbumMetadata, this.context), null, createArtworkFrameModel$default(this, album.getBlockRef(), albumMetadataConvertAlbumMetadata.getImageUrl(), 0, null, 8, null), false, false, HorizontalTileType.REGULAR, 0, albumMetadataConvertAlbumMetadata, null, false, 3176, null);
    }

    private final SimpleHorizontalTileModel convertStation(Station station, Widget widget) {
        String id = widget.getId();
        StationMetadata stationMetadataConvertStationMetadata = convertStationMetadata(station, id == null ? null : CollectionsKt__CollectionsJVMKt.listOf(Intrinsics.stringPlus("WIDGET_ID_", id)));
        return new SimpleHorizontalTileModel(station.getBlockRef(), widget.getTitle(), CarModePresetsUtility.INSTANCE.convertContentTypeToSubtitle(3, stationMetadataConvertStationMetadata, this.context), null, createArtworkFrameModel(station.getBlockRef(), stationMetadataConvertStationMetadata.getImageUrl(), 3, 1), false, false, HorizontalTileType.REGULAR, 3, stationMetadataConvertStationMetadata, null, false, 3176, null);
    }

    private final SimpleHorizontalTileModel convertArtist(Artist artist, Widget widget) {
        String id = widget.getId();
        ArtistMetadata artistMetadataConvertArtistMetadata = convertArtistMetadata(artist, id == null ? null : CollectionsKt__CollectionsJVMKt.listOf(Intrinsics.stringPlus("WIDGET_ID_", id)));
        return new SimpleHorizontalTileModel(artist.getBlockRef(), artist.getPrimaryTitle(), CarModePresetsUtility.INSTANCE.convertContentTypeToSubtitle(4, artistMetadataConvertArtistMetadata, this.context), null, createArtworkFrameModel$default(this, artist.getBlockRef(), artistMetadataConvertArtistMetadata.getImageUrl(), 4, null, 8, null), false, false, HorizontalTileType.REGULAR, 4, artistMetadataConvertArtistMetadata, null, false, 3176, null);
    }

    private final SimpleHorizontalTileModel convertPodcastEpisode(PodcastEpisode podcastEpisode, Widget widget) {
        String id = widget.getId();
        PodcastMetadata podcastMetadataConvertPodcastEpisodeMetadata = convertPodcastEpisodeMetadata(podcastEpisode, id == null ? null : CollectionsKt__CollectionsJVMKt.listOf(Intrinsics.stringPlus("WIDGET_ID_", id)));
        return new SimpleHorizontalTileModel(podcastEpisode.getBlockRef(), podcastEpisode.getTitle(), CarModePresetsUtility.INSTANCE.convertContentTypeToSubtitle(11, podcastMetadataConvertPodcastEpisodeMetadata, this.context), null, createArtworkFrameModel$default(this, podcastEpisode.getBlockRef(), podcastMetadataConvertPodcastEpisodeMetadata.getImageUrl(), 11, null, 8, null), false, false, HorizontalTileType.REGULAR, 11, podcastMetadataConvertPodcastEpisodeMetadata, null, false, 3176, null);
    }

    private final SimpleHorizontalTileModel convertPodcastShow(PodcastShow podcastShow, Widget widget) {
        String id = widget.getId();
        PodcastMetadata podcastMetadataConvertPodcastShowMetadata = convertPodcastShowMetadata(podcastShow, id == null ? null : CollectionsKt__CollectionsJVMKt.listOf(Intrinsics.stringPlus("WIDGET_ID_", id)));
        return new SimpleHorizontalTileModel(podcastShow.getBlockRef(), podcastShow.getTitle(), CarModePresetsUtility.INSTANCE.convertContentTypeToSubtitle(12, podcastMetadataConvertPodcastShowMetadata, this.context), null, createArtworkFrameModel$default(this, podcastShow.getBlockRef(), podcastMetadataConvertPodcastShowMetadata.getImageUrl(), 12, null, 8, null), false, false, HorizontalTileType.REGULAR, 12, podcastMetadataConvertPodcastShowMetadata, null, false, 3176, null);
    }

    static /* synthetic */ ArtworkFrameModel createArtworkFrameModel$default(BrushV3SimpleHorizontalTileModelConverter brushV3SimpleHorizontalTileModelConverter, String str, String str2, int i, Integer num, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            num = null;
        }
        return brushV3SimpleHorizontalTileModelConverter.createArtworkFrameModel(str, str2, i, num);
    }

    private final ArtworkFrameModel createArtworkFrameModel(String blockRef, String imageUrl, int contentType, Integer playIconType) {
        return new ArtworkFrameModel(blockRef, imageUrl, Integer.valueOf(contentType), null, null, playIconType, null, null, null, null, null, null, 4056, null);
    }

    private final List<TrackMetadata> createMetadataList(Widget widget) {
        ArrayList arrayList = new ArrayList();
        String id = widget.getId();
        List<String> listListOf = id == null ? null : CollectionsKt__CollectionsJVMKt.listOf(Intrinsics.stringPlus("WIDGET_ID_", id));
        for (Entity entity : widget.getContent().getEntities()) {
            if (entity.getTrack() != null) {
                Track track = entity.getTrack();
                Intrinsics.checkNotNullExpressionValue(track, "entity.track");
                arrayList.add(convertTrackMetadata(track, listListOf));
            }
        }
        return arrayList;
    }
}
