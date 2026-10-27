package com.amazon.mp3.brush.converters;

import android.content.res.Resources;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.music.ContentAccessType;
import com.amazon.music.find.api.util.RelatedEntityUtil;
import com.amazon.music.find.converter.model.MusicSearchTrack;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.search.RelatedPlaylist;
import com.amazon.music.views.library.converter.BaseModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Artist;
import com.amazon.musicensembleservice.brush.AssetQuality;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Content;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Image;
import com.amazon.musicensembleservice.brush.ParentalControls;
import com.amazon.musicensembleservice.brush.PlaybackContainer;
import com.amazon.musicensembleservice.brush.Playlist;
import com.amazon.musicensembleservice.brush.Track;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushPlaybackContainerConverterUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u001a\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0002JC\u0010\u0010\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0015H\u0002¢\u0006\u0002\u0010\u0018J\"\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u00122\b\b\u0002\u0010\u001c\u001a\u00020\u001dJ\u0016\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!J\u0012\u0010\"\u001a\u0004\u0018\u00010\u00122\u0006\u0010#\u001a\u00020$H\u0002J\u0016\u0010%\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u0015¨\u0006'"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushPlaybackContainerConverterUtils;", "", "()V", "buildArtworkFromRelatedPlaylist", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "relatedPlaylist", "Lcom/amazon/music/search/RelatedPlaylist;", "convertEntity", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "entity", "Lcom/amazon/musicensembleservice/brush/Entity;", "playbackContainer", "Lcom/amazon/musicensembleservice/brush/PlaybackContainer;", "convertTrack", "item", "Lcom/amazon/musicensembleservice/brush/Track;", "createArtworkFrameModel", "blockRef", "", "imageUrl", "contentType", "", "resourceId", "imageSize", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "createSectionGridViewModel", "Lcom/amazon/music/views/library/models/SectionGridViewModel;", "renderingType", "isNestedSectionGrid", "", "extractedPlaybackContainers", "", "data", "Lcom/amazon/musicensembleservice/brush/Block;", "getContainerSubtitle", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "isPlaybackContainerEligible", "position", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BrushPlaybackContainerConverterUtils {
    public static final BrushPlaybackContainerConverterUtils INSTANCE = new BrushPlaybackContainerConverterUtils();

    private BrushPlaybackContainerConverterUtils() {
    }

    public final SectionGridViewModel createSectionGridViewModel(Entity entity, String renderingType, boolean isNestedSectionGrid) {
        BaseViewModel baseViewModelConvert;
        Intrinsics.checkNotNullParameter(entity, "entity");
        Intrinsics.checkNotNullParameter(renderingType, "renderingType");
        if (!(entity instanceof MusicSearchTrack)) {
            return null;
        }
        MusicSearchTrack musicSearchTrack = (MusicSearchTrack) entity;
        List<RelatedPlaylist> relatedPlaylists = musicSearchTrack.getRelatedPlaylists();
        RelatedPlaylist relatedPlaylist = relatedPlaylists == null ? null : RelatedEntityUtil.INSTANCE.getRelatedPlaylist(relatedPlaylists);
        ArrayList arrayList = new ArrayList();
        BaseModelConverter converter = BrushConverterFactory.INSTANCE.getConverter(renderingType);
        SingleBaseModelConverter singleBaseModelConverter = converter instanceof SingleBaseModelConverter ? (SingleBaseModelConverter) converter : null;
        if (singleBaseModelConverter != null && (baseViewModelConvert = singleBaseModelConverter.convert(entity)) != null) {
            arrayList.add(baseViewModelConvert);
        }
        ContentMetadata containerMetadata = BrushConverterUtils.INSTANCE.getContainerMetadata(musicSearchTrack);
        String blockRef = musicSearchTrack.getBlockRef();
        String title = relatedPlaylist == null ? null : relatedPlaylist.getTitle();
        return new SectionGridViewModel(blockRef, arrayList, isNestedSectionGrid ? 19 : BrushLayoutTypeUtils.INSTANCE.getTileLayoutType(renderingType), null, title, containerMetadata != null ? INSTANCE.getContainerSubtitle(containerMetadata) : null, null, null, null, null, false, null, false, buildArtworkFromRelatedPlaylist(relatedPlaylist), containerMetadata, null, null, 106440, null);
    }

    private final ArtworkFrameModel buildArtworkFromRelatedPlaylist(RelatedPlaylist relatedPlaylist) {
        return new ArtworkFrameModel(relatedPlaylist == null ? null : relatedPlaylist.getBlockRef(), relatedPlaylist != null ? relatedPlaylist.getImageUrl() : null, 1, null, null, null, null, null, null, null, null, null, 4088, null);
    }

    public final boolean isPlaybackContainerEligible(Entity entity, int position) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        if (position != 0 || !(entity instanceof MusicSearchTrack)) {
            return false;
        }
        MusicSearchTrack musicSearchTrack = (MusicSearchTrack) entity;
        List<RelatedPlaylist> relatedPlaylists = musicSearchTrack.getRelatedPlaylists();
        return (relatedPlaylists != null && (relatedPlaylists.isEmpty() ^ true)) && RelatedEntityUtil.INSTANCE.isRelatedPlaylistFeaturedAndOnDemand(musicSearchTrack.getRelatedPlaylists()) && FMPMFeatureGating.SEARCH_ON_DEMAND_RELATED_PLAYLIST_TRACKS_WIDGET.isEnabled();
    }

    private final String getContainerSubtitle(ContentMetadata contentMetadata) {
        Resources resources = AmazonApplication.getApplication().getApplicationContext().getResources();
        if (contentMetadata instanceof PlaylistMetadata) {
            return resources.getString(R.string.dmusic_all_access_playlist);
        }
        return null;
    }

    public final List<BaseViewModel> extractedPlaybackContainers(Block data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        Content content = data.getContent();
        List<Entity> entities = content == null ? null : content.getEntities();
        if (entities == null || entities.isEmpty()) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        for (Entity entity : entities) {
            if (entity != null) {
                PlaybackContainer playbackContainer = (PlaybackContainer) entity;
                List<Entity> entities2 = playbackContainer.getEntities();
                Intrinsics.checkNotNullExpressionValue(entities2, "entities");
                for (Entity entity2 : entities2) {
                    BrushPlaybackContainerConverterUtils brushPlaybackContainerConverterUtils = INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(entity2, "entity");
                    BaseViewModel baseViewModelConvertEntity = brushPlaybackContainerConverterUtils.convertEntity(entity2, playbackContainer);
                    if (baseViewModelConvertEntity != null) {
                        arrayList.add(baseViewModelConvertEntity);
                    }
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.musicensembleservice.brush.PlaybackContainer");
            }
        }
        return arrayList;
    }

    private final BaseViewModel convertEntity(Entity entity, PlaybackContainer playbackContainer) {
        if (entity instanceof Track) {
            return convertTrack((Track) entity, playbackContainer);
        }
        return null;
    }

    private final BaseViewModel convertTrack(Track item, PlaybackContainer playbackContainer) {
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        Entity container = playbackContainer.getContainer();
        Playlist playlist = container instanceof Playlist ? (Playlist) container : null;
        Album album = item.getAlbum();
        Image image = item.getImage();
        String url = (image == null && (album == null || (image = album.getImage()) == null)) ? null : image.getUrl();
        List<Artist> artists = item.getArtists();
        Artist artist = artists == null ? null : (Artist) CollectionsKt___CollectionsKt.first((List) artists);
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        String asin2 = album == null ? null : album.getAsin();
        String title2 = album == null ? null : album.getTitle();
        String asin3 = artist == null ? null : artist.getAsin();
        String name = artist == null ? null : artist.getName();
        ParentalControls parentalControls = item.getParentalControls();
        boolean zAreEqual = parentalControls == null ? false : Intrinsics.areEqual(parentalControls.isHasExplicitLanguage(), Boolean.TRUE);
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        List<String> contentTiers2 = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers2, "item.contentTiers");
        Set<SubscriptionType> setConvertOnDemandTiers = brushConverterUtils.convertOnDemandTiers(contentTiers2);
        Uri trackUri = ContentPlaybackUtils.INSTANCE.getTrackUri(asin);
        String assetType = item.getAssetType();
        if (assetType == null) {
            assetType = "AUDIO";
        }
        String str = assetType;
        List<AssetQuality> assetQualities = item.getAssetQualities();
        if (assetQualities == null) {
            assetQualities = CollectionsKt__CollectionsKt.emptyList();
        }
        List<com.amazon.music.models.AssetQuality> listConvertAssetQuality = brushConverterUtils.convertAssetQuality(assetQualities);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        Long duration = item.getDuration();
        return new HorizontalTileModel(item.getBlockRef(), null, item.getPrimaryTitle(), playlist != null ? playlist.getTitle() : null, item.getTertiaryTitle(), createArtworkFrameModel$default(INSTANCE, item.getBlockRef(), url, 2, null, null, 24, null), null, false, null, null, false, false, null, false, true, null, 2, new TrackMetadata(blockRef, trackUri, null, asin, title, url, null, asin2, title2, null, asin3, name, null, null, null, null, null, Boolean.valueOf(zAreEqual), null, setConvertTiers, str, listConvertAssetQuality, (int) timeUnit.toMillis(duration == null ? 0L : duration.longValue()), null, null, null, null, null, null, false, null, false, BrushConverterUtils.convertPlaymodeEligibility(item.getPlaymodeEligibility()), setConvertOnDemandTiers, brushConverterUtils.getContainerMetadata(playbackContainer, playbackContainer.getBlockRef()), null, -7998908, 8, null), null, false, false, null, true, null, null, null, false, false, false, null, 1069334466, null);
    }

    static /* synthetic */ ArtworkFrameModel createArtworkFrameModel$default(BrushPlaybackContainerConverterUtils brushPlaybackContainerConverterUtils, String str, String str2, Integer num, Integer num2, Integer num3, int i, Object obj) {
        return brushPlaybackContainerConverterUtils.createArtworkFrameModel(str, str2, num, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? null : num3);
    }

    private final ArtworkFrameModel createArtworkFrameModel(String blockRef, String imageUrl, Integer contentType, Integer resourceId, Integer imageSize) {
        return new ArtworkFrameModel(blockRef, imageUrl, contentType, null, null, null, null, resourceId, imageSize, null, null, null, 3704, null);
    }
}
