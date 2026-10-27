package com.amazon.mp3.brush.converters;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.brush.AllAccessPlaylistsUtils;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.ContentAccessType;
import com.amazon.music.platform.playback.data.ShuffleType;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.RecentsMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.VerticalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.PlaymodeEligibility;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Artist;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Image;
import com.amazon.musicensembleservice.brush.Playlist;
import com.amazon.musicensembleservice.brush.Station;
import com.amazon.musicensembleservice.brush.UserPlaylist;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushRecentsVerticalTileConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u001b\u0010\u001cJA\u0010\f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\b\f\u0010\rJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0012\u001a\u00020\u0011J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0012\u001a\u00020\u0015J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0012\u001a\u00020\u0017J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0012\u001a\u00020\u0019¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushRecentsVerticalTileConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/musicensembleservice/brush/Entity;", "", "contentType", "", "isFree", "isFreeOnDemand", "", "contentIdentifier", "Lcom/amazon/musicensembleservice/PlaymodeEligibility;", "playmodeEligibility", "getPlayIconType", "(IZZLjava/lang/String;Lcom/amazon/musicensembleservice/PlaymodeEligibility;)Ljava/lang/Integer;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "Lcom/amazon/musicensembleservice/brush/Station;", "item", "Lcom/amazon/music/views/library/models/VerticalTileModel;", "convertStation", "Lcom/amazon/musicensembleservice/brush/Album;", "convertAlbum", "Lcom/amazon/musicensembleservice/brush/Playlist;", "convertPlaylist", "Lcom/amazon/musicensembleservice/brush/UserPlaylist;", "convertUserPlaylist", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushRecentsVerticalTileConverter implements SingleBaseModelConverter<Entity> {
    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof Station) {
            return convertStation((Station) data);
        }
        if (data instanceof Album) {
            return convertAlbum((Album) data);
        }
        if (data instanceof Playlist) {
            return convertPlaylist((Playlist) data);
        }
        if (data instanceof UserPlaylist) {
            return convertUserPlaylist((UserPlaylist) data);
        }
        return null;
    }

    public final VerticalTileModel convertStation(Station item) {
        Intrinsics.checkNotNullParameter(item, "item");
        String key = item.getStationKey();
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        Image image = item.getImage();
        String url = image == null ? null : image.getUrl();
        ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(key, "key");
        RecentsMetadata recentsMetadata = new RecentsMetadata(item.getBlockRef(), contentPlaybackUtils.getStationUri(key), null, item.getTitle(), url, null, key, key, null, String.valueOf(3), false, setConvertTiers, 1316, null);
        return new VerticalTileModel(item.getBlockRef(), item.getPrimaryTitle(), item.getSecondaryTitle(), null, new ArtworkFrameModel(null, url, 3, null, null, getPlayIconType(3, setConvertTiers.contains(ContentAccessType.NIGHTWING), setConvertTiers.contains(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE), key, null), recentsMetadata, null, null, null, null, null, 3993, null), null, 3, recentsMetadata, false, null, null, null, null, null, null, null, null, null, null, null, null, 2096936, null);
    }

    public final VerticalTileModel convertAlbum(Album item) {
        Intrinsics.checkNotNullParameter(item, "item");
        String asin = item.getAsin();
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        Image image = item.getImage();
        String url = image == null ? null : image.getUrl();
        List<Artist> artists = item.getArtists();
        if (artists != null) {
        }
        ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
        String title = item.getTitle();
        Intrinsics.checkNotNullExpressionValue(asin, "asin");
        RecentsMetadata recentsMetadata = new RecentsMetadata(item.getBlockRef(), contentPlaybackUtils.getAlbumUriWithoutTracks(title, asin), null, item.getTitle(), url, null, asin, asin, null, String.valueOf(0), false, setConvertTiers, 1316, null);
        return new VerticalTileModel(item.getBlockRef(), item.getPrimaryTitle(), item.getSecondaryTitle(), null, new ArtworkFrameModel(null, url, 0, null, null, getPlayIconType(0, setConvertTiers.contains(ContentAccessType.NIGHTWING), setConvertTiers.contains(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE), asin, item.getPlaymodeEligibility()), recentsMetadata, null, null, null, null, null, 3993, null), null, 0, recentsMetadata, false, null, null, null, null, null, null, null, null, null, null, null, null, 2096936, null);
    }

    public final VerticalTileModel convertPlaylist(Playlist item) {
        Intrinsics.checkNotNullParameter(item, "item");
        String asin = item.getAsin();
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        Image image = item.getImage();
        String url = image == null ? null : image.getUrl();
        ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(asin, "asin");
        RecentsMetadata recentsMetadata = new RecentsMetadata(item.getBlockRef(), contentPlaybackUtils.getPlaylistUri(asin), null, item.getTitle(), url, null, asin, asin, null, String.valueOf(1), false, setConvertTiers, 1316, null);
        return new VerticalTileModel(item.getBlockRef(), item.getPrimaryTitle(), item.getSecondaryTitle(), null, new ArtworkFrameModel(null, url, 1, null, null, getPlayIconType(1, setConvertTiers.contains(ContentAccessType.NIGHTWING), setConvertTiers.contains(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE), asin, item.getPlaymodeEligibility()), recentsMetadata, null, null, null, null, null, 3993, null), null, 1, recentsMetadata, false, null, null, null, null, null, null, null, null, null, null, null, AllAccessPlaylistsUtils.INSTANCE.getAllAccessLabelRecents(item), 1048360, null);
    }

    public final VerticalTileModel convertUserPlaylist(UserPlaylist item) {
        Object next;
        Image image;
        Intrinsics.checkNotNullParameter(item, "item");
        String playlistId = item.getPlaylistId();
        Set<ContentAccessType> setConvertTiers = BrushConverterUtils.INSTANCE.convertTiers(CollectionsKt__CollectionsKt.emptyList());
        List<Image> images = item.getImages();
        if (images == null) {
            image = null;
        } else {
            Iterator<T> it = images.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if ("ORIGINAL_ART".equals(((Image) next).getType())) {
                    break;
                }
            }
            image = (Image) next;
        }
        String url = image != null ? image.getUrl() : null;
        ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(playlistId, "playlistId");
        RecentsMetadata recentsMetadata = new RecentsMetadata(item.getBlockRef(), contentPlaybackUtils.getUserPlaylistLocalUriWithoutTrack(applicationContext, playlistId), null, item.getTitle(), url, null, playlistId, playlistId, null, String.valueOf(1), false, setConvertTiers, 1316, null);
        return new VerticalTileModel(item.getBlockRef(), item.getPrimaryTitle(), item.getSecondaryTitle(), null, new ArtworkFrameModel(null, url, 1, null, null, getPlayIconType(1, setConvertTiers.contains(ContentAccessType.NIGHTWING), setConvertTiers.contains(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE), playlistId, item.getPlaymodeEligibility()), recentsMetadata, null, null, null, null, null, 3993, null), null, 1, recentsMetadata, false, null, null, null, null, null, null, null, null, null, null, null, AllAccessPlaylistsUtils.INSTANCE.getAllAccessLabelRecents(item), 1048360, null);
    }

    private final Integer getPlayIconType(int contentType, boolean isFree, boolean isFreeOnDemand, String contentIdentifier, PlaymodeEligibility playmodeEligibility) {
        com.amazon.music.platform.data.eligibility.PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility;
        boolean zIsEnabled = Feature.use_eligibility_recently_played_library_v1.isEnabled();
        if (contentType == 3) {
            return 1;
        }
        if (contentType == 1 && UserSubscriptionUtil.isNightwingOnly()) {
            if (isFree && isFreeOnDemand) {
                return AmazonApplication.getCapabilities().isOnDemandPlaylistsEnabled() && GetOnDemandPlaylistsService.INSTANCE.get().isOnDemandPlaylist(contentIdentifier) ? 0 : 2;
            }
            if (isFree) {
                return 1;
            }
        }
        if (zIsEnabled && (playmodeEligibilityConvertPlaymodeEligibility = BrushConverterUtils.convertPlaymodeEligibility(playmodeEligibility)) != null) {
            if (playmodeEligibilityConvertPlaymodeEligibility.isStationFromAnything()) {
                return 1;
            }
            if (playmodeEligibilityConvertPlaymodeEligibility.shuffleType() == ShuffleType.MIXED) {
                return 3;
            }
            if (playmodeEligibilityConvertPlaymodeEligibility.isOnDemand() || playmodeEligibilityConvertPlaymodeEligibility.shuffleType() == ShuffleType.INLINE) {
                return 0;
            }
        }
        return 0;
    }
}
