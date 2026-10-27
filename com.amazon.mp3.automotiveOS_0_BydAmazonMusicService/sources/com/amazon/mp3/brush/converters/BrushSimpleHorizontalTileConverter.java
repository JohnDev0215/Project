package com.amazon.mp3.brush.converters;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.auto.carmode.CarModePresetsUtility;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlaybackHandlerUtil;
import com.amazon.music.ContentAccessType;
import com.amazon.music.find.converter.model.MusicSearchAlbum;
import com.amazon.music.find.converter.model.MusicSearchArtist;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.SimpleHorizontalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.HorizontalTileType;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Artist;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Content;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Image;
import com.amazon.musicensembleservice.brush.ParentalControls;
import com.amazon.musicensembleservice.brush.Playlist;
import com.amazon.musicensembleservice.brush.PodcastEpisode;
import com.amazon.musicensembleservice.brush.PodcastShow;
import com.amazon.musicensembleservice.brush.Station;
import com.amazon.musicensembleservice.brush.Track;
import com.amazon.musicensembleservice.brush.UserPlaylist;
import com.amazon.podcast.deeplinks.Deeplinks;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushSimpleHorizontalTileConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 02\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00010B\u0011\u0012\b\u0010,\u001a\u0004\u0018\u00010+¢\u0006\u0004\b.\u0010/J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\"\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0016J\u001a\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0004J\u001a\u0010\u0010\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000f2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0004J$\u0010\u0013\u001a\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0004J$\u0010\u0015\u001a\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0004J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\n\u001a\u00020\u0014H\u0014J$\u0010\u0019\u001a\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0004J$\u0010\u001b\u001a\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0004J4\u0010\u001d\u001a\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\u00022\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0004J\u0010\u0010\u001e\u001a\u00020\u00162\u0006\u0010\n\u001a\u00020\u001cH\u0014J\u0018\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u0010\n\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\u0002J\u001c\u0010\"\u001a\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020!2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0004J$\u0010(\u001a\u00020\u00162\b\u0010$\u001a\u0004\u0018\u00010#2\b\u0010%\u001a\u0004\u0018\u00010#2\u0006\u0010'\u001a\u00020&H\u0004J\u0019\u0010)\u001a\u0004\u0018\u00010&2\u0006\u0010'\u001a\u00020&H\u0004¢\u0006\u0004\b)\u0010*R\u0016\u0010,\u001a\u0004\u0018\u00010+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-¨\u00061"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushSimpleHorizontalTileConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/musicensembleservice/brush/Block;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadataList", "Lcom/amazon/musicensembleservice/brush/PodcastShow;", "item", "Lcom/amazon/music/views/library/models/enums/HorizontalTileType;", "tileType", "Lcom/amazon/music/views/library/models/SimpleHorizontalTileModel;", "convertPodcastShow", "Lcom/amazon/musicensembleservice/brush/PodcastEpisode;", "convertPodcastEpisode", "Lcom/amazon/musicensembleservice/brush/UserPlaylist;", "block", "convertUserPlaylist", "Lcom/amazon/musicensembleservice/brush/Album;", "convertAlbum", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "getAlbumArtworkFrameModel", "Lcom/amazon/musicensembleservice/brush/Playlist;", "convertPlaylist", "Lcom/amazon/musicensembleservice/brush/Station;", "convertStation", "Lcom/amazon/musicensembleservice/brush/Track;", "convertTrack", "getTrackArtworkFrameModel", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "getTrackMetadata", "Lcom/amazon/musicensembleservice/brush/Artist;", "convertArtist", "", "blockRef", "imageUrl", "", "contentType", "createArtworkFrameModel", "getPlayIconType", "(I)Ljava/lang/Integer;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class BrushSimpleHorizontalTileConverter implements SingleBaseModelConverter<Block> {
    private static final String TAG = BrushSimpleHorizontalTileConverter.class.getSimpleName();
    private final Context context;

    public BrushSimpleHorizontalTileConverter(Context context) {
        this.context = context;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public final BaseViewModel convert(Block data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return convert(data, null);
    }

    public BaseViewModel convert(Block data, List<? extends ContentMetadata> metadataList) {
        Intrinsics.checkNotNullParameter(data, "data");
        Content content = data.getContent();
        List<Entity> entities = content == null ? null : content.getEntities();
        if (entities == null || entities.isEmpty()) {
            Log.error(TAG, "This converter needs at least 1 entity to convert to a SimpleHorizontalTileModel");
            return null;
        }
        Entity entity = (Entity) CollectionsKt___CollectionsKt.first((List) entities);
        if (entity instanceof Album) {
            return convertAlbum$default(this, (Album) entity, data, null, 4, null);
        }
        if (entity instanceof Playlist) {
            return convertPlaylist$default(this, (Playlist) entity, data, null, 4, null);
        }
        if (entity instanceof Station) {
            return convertStation$default(this, (Station) entity, data, null, 4, null);
        }
        if (entity instanceof Track) {
            return convertTrack$default(this, (Track) entity, data, metadataList, null, 8, null);
        }
        if (entity instanceof UserPlaylist) {
            return convertUserPlaylist$default(this, (UserPlaylist) entity, data, null, 4, null);
        }
        if (entity instanceof Artist) {
            return convertArtist$default(this, (Artist) entity, null, 2, null);
        }
        if (entity instanceof PodcastShow) {
            return convertPodcastShow$default(this, (PodcastShow) entity, null, 2, null);
        }
        if (entity instanceof PodcastEpisode) {
            return convertPodcastEpisode$default(this, (PodcastEpisode) entity, null, 2, null);
        }
        return null;
    }

    public static /* synthetic */ SimpleHorizontalTileModel convertPodcastShow$default(BrushSimpleHorizontalTileConverter brushSimpleHorizontalTileConverter, PodcastShow podcastShow, HorizontalTileType horizontalTileType, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: convertPodcastShow");
        }
        if ((i & 2) != 0) {
            horizontalTileType = HorizontalTileType.REGULAR;
        }
        return brushSimpleHorizontalTileConverter.convertPodcastShow(podcastShow, horizontalTileType);
    }

    protected final SimpleHorizontalTileModel convertPodcastShow(PodcastShow item, HorizontalTileType tileType) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(tileType, "tileType");
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        String subtitle = item.getSubtitle();
        Uri barkerUri = ContentPlaybackUtils.INSTANCE.getBarkerUri();
        String catalogId = item.getCatalogId();
        String catalogId2 = item.getCatalogId();
        String str = Deeplinks.top1RecommenderShowPlay(item.getCatalogId(), item.getTitle());
        String strPodcast = Deeplinks.podcast(item.getCatalogId(), item.getTitle());
        String url = item.getImage().getUrl();
        Intrinsics.checkNotNullExpressionValue(catalogId, "catalogId");
        Intrinsics.checkNotNullExpressionValue(str, "top1RecommenderShowPlay(…em.catalogId, item.title)");
        return new SimpleHorizontalTileModel(item.getBlockRef(), item.getTitle(), item.getSubtitle(), null, createArtworkFrameModel(item.getBlockRef(), item.getImage().getUrl(), 12), false, false, tileType, 12, new PodcastMetadata(blockRef, barkerUri, null, catalogId, str, strPodcast, title, subtitle, url, catalogId2, null, 1028, null), null, false, 3176, null);
    }

    public static /* synthetic */ SimpleHorizontalTileModel convertPodcastEpisode$default(BrushSimpleHorizontalTileConverter brushSimpleHorizontalTileConverter, PodcastEpisode podcastEpisode, HorizontalTileType horizontalTileType, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: convertPodcastEpisode");
        }
        if ((i & 2) != 0) {
            horizontalTileType = HorizontalTileType.REGULAR;
        }
        return brushSimpleHorizontalTileConverter.convertPodcastEpisode(podcastEpisode, horizontalTileType);
    }

    protected final SimpleHorizontalTileModel convertPodcastEpisode(PodcastEpisode item, HorizontalTileType tileType) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(tileType, "tileType");
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        String subtitle = item.getSubtitle();
        Uri barkerUri = ContentPlaybackUtils.INSTANCE.getBarkerUri();
        String catalogId = item.getCatalogId();
        String str = Deeplinks.top1RecommenderEpisodePlay(item.getPodcastShowCatalogId(), item.getCatalogId(), item.getPodcastShowTitle(), item.getTitle());
        String strEpisode = Deeplinks.episode(item.getPodcastShowCatalogId(), item.getCatalogId(), item.getPodcastShowTitle(), item.getTitle());
        String url = item.getImage().getUrl();
        String podcastShowCatalogId = item.getPodcastShowCatalogId();
        Intrinsics.checkNotNullExpressionValue(catalogId, "catalogId");
        Intrinsics.checkNotNullExpressionValue(str, "top1RecommenderEpisodePl…              item.title)");
        return new SimpleHorizontalTileModel(item.getBlockRef(), item.getTitle(), item.getSubtitle(), null, createArtworkFrameModel(item.getBlockRef(), item.getImage().getUrl(), 11), false, false, tileType, 11, new PodcastMetadata(blockRef, barkerUri, null, catalogId, str, strEpisode, title, subtitle, url, podcastShowCatalogId, null, 1028, null), null, false, 3176, null);
    }

    public static /* synthetic */ SimpleHorizontalTileModel convertUserPlaylist$default(BrushSimpleHorizontalTileConverter brushSimpleHorizontalTileConverter, UserPlaylist userPlaylist, Block block, HorizontalTileType horizontalTileType, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: convertUserPlaylist");
        }
        if ((i & 4) != 0) {
            horizontalTileType = HorizontalTileType.REGULAR;
        }
        return brushSimpleHorizontalTileConverter.convertUserPlaylist(userPlaylist, block, horizontalTileType);
    }

    protected final SimpleHorizontalTileModel convertUserPlaylist(UserPlaylist item, Block block, HorizontalTileType tileType) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(tileType, "tileType");
        String title = item.getTitle();
        if (title == null) {
            title = block.getTitle();
        }
        String str = title;
        String playlistId = item.getPlaylistId();
        Image image = null;
        Object obj = null;
        if (playlistId == null) {
            return null;
        }
        List<Image> images = item.getImages();
        if (images != null) {
            Iterator<T> it = images.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if ("ORIGINAL_ART".equals(((Image) next).getType())) {
                    obj = next;
                    break;
                }
            }
            image = (Image) obj;
        }
        String url = image == null ? "" : image.getUrl();
        String blockRef = item.getBlockRef();
        String description = item.getDescription();
        Long tracksCount = item.getTracksCount();
        Boolean isIsOwned = item.isIsOwned();
        Uri uri = Uri.parse("");
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = BrushConverterUtils.convertPlaymodeEligibility(item.getPlaymodeEligibility());
        Intrinsics.checkNotNullExpressionValue(uri, "parse(\"\")");
        Intrinsics.checkNotNullExpressionValue(isIsOwned, "isIsOwned");
        UserPlaylistMetadata userPlaylistMetadata = new UserPlaylistMetadata(blockRef, uri, null, str, url, description, tracksCount, null, isIsOwned.booleanValue(), playlistId, playmodeEligibilityConvertPlaymodeEligibility, null, null, false, 14468, null);
        try {
            Uri uriGenerateUserPlaylistContentUri = PlaybackHandlerUtil.INSTANCE.generateUserPlaylistContentUri(this.context, userPlaylistMetadata);
            if (uriGenerateUserPlaylistContentUri != null) {
                userPlaylistMetadata.setUri(uriGenerateUserPlaylistContentUri);
            }
        } catch (Exception e) {
            Log.error(TAG, "Uri is empty for User Playlist.", e);
        }
        return new SimpleHorizontalTileModel(item.getBlockRef(), str, CarModePresetsUtility.INSTANCE.convertContentTypeToSubtitle(1, userPlaylistMetadata, this.context), null, createArtworkFrameModel(item.getBlockRef(), url, 1), false, false, tileType, 1, userPlaylistMetadata, null, false, 3176, null);
    }

    public static /* synthetic */ SimpleHorizontalTileModel convertAlbum$default(BrushSimpleHorizontalTileConverter brushSimpleHorizontalTileConverter, Album album, Block block, HorizontalTileType horizontalTileType, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: convertAlbum");
        }
        if ((i & 4) != 0) {
            horizontalTileType = HorizontalTileType.REGULAR;
        }
        return brushSimpleHorizontalTileConverter.convertAlbum(album, block, horizontalTileType);
    }

    protected final SimpleHorizontalTileModel convertAlbum(Album item, Block block, HorizontalTileType tileType) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(tileType, "tileType");
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        Image image = item.getImage();
        String url = image == null ? null : image.getUrl();
        List<Artist> artists = item.getArtists();
        Artist artist = artists == null ? null : (Artist) CollectionsKt___CollectionsKt.first((List) artists);
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        String asin2 = artist == null ? null : artist.getAsin();
        String name = artist == null ? null : artist.getName();
        Boolean boolIsIsOwned = item instanceof MusicSearchAlbum ? ((MusicSearchAlbum) item).isIsOwned() : Boolean.FALSE;
        Boolean boolIsIsInLibrary = item.isIsInLibrary();
        boolean zBooleanValue = boolIsIsInLibrary == null ? false : boolIsIsInLibrary.booleanValue();
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        Uri albumUri = ContentPlaybackUtils.INSTANCE.getAlbumUri(artist != null ? artist.getName() : null, item.getTitle(), asin);
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = BrushConverterUtils.convertPlaymodeEligibility(item.getPlaymodeEligibility());
        List<String> contentTiers2 = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers2, "item.contentTiers");
        AlbumMetadata albumMetadata = new AlbumMetadata(blockRef, albumUri, null, asin, title, url, null, asin2, name, Boolean.valueOf(zBooleanValue), boolIsIsOwned, setConvertTiers, false, null, null, null, playmodeEligibilityConvertPlaymodeEligibility, brushConverterUtils.convertOnDemandTiers(contentTiers2), null, null, 847940, null);
        return new SimpleHorizontalTileModel(item.getBlockRef(), block.getTitle(), CarModePresetsUtility.INSTANCE.convertContentTypeToSubtitle(0, albumMetadata, this.context), null, getAlbumArtworkFrameModel(item), false, false, tileType, 0, albumMetadata, null, false, 3176, null);
    }

    protected ArtworkFrameModel getAlbumArtworkFrameModel(Album item) {
        Intrinsics.checkNotNullParameter(item, "item");
        Image image = item.getImage();
        return createArtworkFrameModel(item.getBlockRef(), image == null ? null : image.getUrl(), 0);
    }

    public static /* synthetic */ SimpleHorizontalTileModel convertPlaylist$default(BrushSimpleHorizontalTileConverter brushSimpleHorizontalTileConverter, Playlist playlist, Block block, HorizontalTileType horizontalTileType, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: convertPlaylist");
        }
        if ((i & 4) != 0) {
            horizontalTileType = HorizontalTileType.REGULAR;
        }
        return brushSimpleHorizontalTileConverter.convertPlaylist(playlist, block, horizontalTileType);
    }

    protected final SimpleHorizontalTileModel convertPlaylist(Playlist item, Block block, HorizontalTileType tileType) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(tileType, "tileType");
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        Image image = item.getImage();
        String url = image == null ? null : image.getUrl();
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        Boolean boolIsIsInLibrary = item.isIsInLibrary();
        boolean zBooleanValue = boolIsIsInLibrary == null ? false : boolIsIsInLibrary.booleanValue();
        Boolean boolIsIsOwned = item.isIsOwned();
        boolean zBooleanValue2 = boolIsIsOwned != null ? boolIsIsOwned.booleanValue() : false;
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        List<String> contentTiers2 = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers2, "item.contentTiers");
        Set<SubscriptionType> setConvertOnDemandTiers = brushConverterUtils.convertOnDemandTiers(contentTiers2);
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = BrushConverterUtils.convertPlaymodeEligibility(item.getPlaymodeEligibility());
        Uri playlistUri = ContentPlaybackUtils.INSTANCE.getPlaylistUri(asin);
        List<String> contentTypes = item.getContentTypes();
        if (contentTypes == null) {
            contentTypes = CollectionsKt__CollectionsJVMKt.listOf("AUDIO");
        }
        PlaylistMetadata playlistMetadata = new PlaylistMetadata(blockRef, playlistUri, null, asin, title, url, null, null, null, null, Boolean.valueOf(zBooleanValue), setConvertTiers, contentTypes, false, null, null, null, null, zBooleanValue2, null, false, playmodeEligibilityConvertPlaymodeEligibility, setConvertOnDemandTiers, false, null, 26993604, null);
        List<String> contentTypes2 = item.getContentTypes();
        int i = Intrinsics.areEqual(contentTypes2 != null ? (String) CollectionsKt___CollectionsKt.first((List) contentTypes2) : null, "VIDEO") ? 8 : 1;
        return new SimpleHorizontalTileModel(item.getBlockRef(), block.getTitle(), CarModePresetsUtility.INSTANCE.convertContentTypeToSubtitle(i, playlistMetadata, this.context), null, createArtworkFrameModel(item.getBlockRef(), url, i), false, false, tileType, i, playlistMetadata, null, false, 3176, null);
    }

    public static /* synthetic */ SimpleHorizontalTileModel convertStation$default(BrushSimpleHorizontalTileConverter brushSimpleHorizontalTileConverter, Station station, Block block, HorizontalTileType horizontalTileType, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: convertStation");
        }
        if ((i & 4) != 0) {
            horizontalTileType = HorizontalTileType.REGULAR;
        }
        return brushSimpleHorizontalTileConverter.convertStation(station, block, horizontalTileType);
    }

    protected final SimpleHorizontalTileModel convertStation(Station item, Block block, HorizontalTileType tileType) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(tileType, "tileType");
        String stationKey = item.getStationKey();
        if (stationKey == null) {
            return null;
        }
        Image image = item.getImage();
        String url = image != null ? image.getUrl() : null;
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        Map<String, ParentalControls> parentalControlsPerContentTier = item.getParentalControlsPerContentTier();
        Intrinsics.checkNotNullExpressionValue(parentalControlsPerContentTier, "item.parentalControlsPerContentTier");
        StationMetadata stationMetadata = new StationMetadata(blockRef, ContentPlaybackUtils.INSTANCE.getStationUri(stationKey), null, stationKey, title, null, null, null, url, brushConverterUtils.convertExplicitMap(parentalControlsPerContentTier), setConvertTiers, null, null, null, 14564, null);
        return new SimpleHorizontalTileModel(item.getBlockRef(), block.getTitle(), CarModePresetsUtility.INSTANCE.convertContentTypeToSubtitle(3, stationMetadata, this.context), null, createArtworkFrameModel(item.getBlockRef(), url, 3), false, false, tileType, 3, stationMetadata, null, false, 3176, null);
    }

    public static /* synthetic */ SimpleHorizontalTileModel convertTrack$default(BrushSimpleHorizontalTileConverter brushSimpleHorizontalTileConverter, Track track, Block block, List list, HorizontalTileType horizontalTileType, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: convertTrack");
        }
        if ((i & 8) != 0) {
            horizontalTileType = HorizontalTileType.REGULAR;
        }
        return brushSimpleHorizontalTileConverter.convertTrack(track, block, list, horizontalTileType);
    }

    protected final SimpleHorizontalTileModel convertTrack(Track item, Block block, List<? extends ContentMetadata> metadataList, HorizontalTileType tileType) {
        ContentMetadata trackMetadata;
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(tileType, "tileType");
        if (item.getAsin() != null) {
            int i = Intrinsics.areEqual(item.getAssetType(), "VIDEO") ? 7 : 2;
            if (metadataList != null && (!metadataList.isEmpty()) && tileType != HorizontalTileType.DETAILED) {
                trackMetadata = (ContentMetadata) CollectionsKt___CollectionsKt.first((List) metadataList);
            } else {
                trackMetadata = getTrackMetadata(item, block);
            }
            ContentMetadata contentMetadata = trackMetadata;
            if (contentMetadata != null) {
                String blockRef = item.getBlockRef();
                String title = block.getTitle();
                String strConvertContentTypeToSubtitle = CarModePresetsUtility.INSTANCE.convertContentTypeToSubtitle(i, contentMetadata, this.context);
                Boolean boolIsIsInLibrary = item.isIsInLibrary();
                return new SimpleHorizontalTileModel(blockRef, title, strConvertContentTypeToSubtitle, null, getTrackArtworkFrameModel(item), !Intrinsics.areEqual(item.getAssetType(), "VIDEO"), boolIsIsInLibrary == null ? false : boolIsIsInLibrary.booleanValue(), tileType, i, contentMetadata, metadataList, false, 2056, null);
            }
        }
        return null;
    }

    protected ArtworkFrameModel getTrackArtworkFrameModel(Track item) {
        Image image;
        Intrinsics.checkNotNullParameter(item, "item");
        Album album = item.getAlbum();
        Image image2 = item.getImage();
        String url = null;
        if (image2 != null) {
            url = image2.getUrl();
        } else if (album != null && (image = album.getImage()) != null) {
            url = image.getUrl();
        }
        return createArtworkFrameModel(item.getBlockRef(), url, 2);
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00b6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.amazon.music.views.library.metadata.TrackMetadata getTrackMetadata(com.amazon.musicensembleservice.brush.Track r43, com.amazon.musicensembleservice.brush.Block r44) {
        /*
            Method dump skipped, instruction units count: 358
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.brush.converters.BrushSimpleHorizontalTileConverter.getTrackMetadata(com.amazon.musicensembleservice.brush.Track, com.amazon.musicensembleservice.brush.Block):com.amazon.music.views.library.metadata.TrackMetadata");
    }

    public static /* synthetic */ SimpleHorizontalTileModel convertArtist$default(BrushSimpleHorizontalTileConverter brushSimpleHorizontalTileConverter, Artist artist, HorizontalTileType horizontalTileType, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: convertArtist");
        }
        if ((i & 2) != 0) {
            horizontalTileType = HorizontalTileType.REGULAR;
        }
        return brushSimpleHorizontalTileConverter.convertArtist(artist, horizontalTileType);
    }

    protected final SimpleHorizontalTileModel convertArtist(Artist item, HorizontalTileType tileType) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(tileType, "tileType");
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        Image image = item.getImage();
        String url = image == null ? null : image.getUrl();
        ArtistMetadata artistMetadata = new ArtistMetadata(item.getBlockRef(), ContentPlaybackUtils.INSTANCE.getArtistUri(asin), null, null, asin, item.getName(), null, url, item.getContributorAsin(), null, item instanceof MusicSearchArtist ? BrushConverterUtils.INSTANCE.convertTiers(((MusicSearchArtist) item).getContentTiers()) : null, false, null, null, false, null, null, false, false, null, 1047116, null);
        return new SimpleHorizontalTileModel(item.getBlockRef(), item.getPrimaryTitle(), CarModePresetsUtility.INSTANCE.convertContentTypeToSubtitle(4, artistMetadata, this.context), null, createArtworkFrameModel(item.getBlockRef(), url, 4), false, false, tileType, 4, artistMetadata, null, false, 3176, null);
    }

    protected final ArtworkFrameModel createArtworkFrameModel(String blockRef, String imageUrl, int contentType) {
        return new ArtworkFrameModel(blockRef, imageUrl, Integer.valueOf(contentType), null, null, getPlayIconType(contentType), null, null, null, null, null, null, 4056, null);
    }

    protected final Integer getPlayIconType(int contentType) {
        return contentType == 3 ? 1 : null;
    }
}
