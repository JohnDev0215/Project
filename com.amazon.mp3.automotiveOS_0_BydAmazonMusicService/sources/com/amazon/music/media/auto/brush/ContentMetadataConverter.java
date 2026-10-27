package com.amazon.music.media.auto.brush;

import android.net.Uri;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.ContentAccessType;
import com.amazon.music.media.auto.util.BrushConverterUtil;
import com.amazon.music.media.auto.util.ContentPlaybackUtil;
import com.amazon.music.page.api.model.Album;
import com.amazon.music.page.api.model.Artist;
import com.amazon.music.page.api.model.AssetQuality;
import com.amazon.music.page.api.model.AssetType;
import com.amazon.music.page.api.model.Image;
import com.amazon.music.page.api.model.ImageType;
import com.amazon.music.page.api.model.Playlist;
import com.amazon.music.page.api.model.PlaymodeEligibility;
import com.amazon.music.page.api.model.PodcastEpisode;
import com.amazon.music.page.api.model.PodcastShow;
import com.amazon.music.page.api.model.Station;
import com.amazon.music.page.api.model.Track;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContentMetadataConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010&\u001a\u00020%\u0012\u0006\u0010+\u001a\u00020*¢\u0006\u0004\b/\u00100J \u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004J \u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\t2\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004J \u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\r2\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004J \u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004J \u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u00132\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004J \u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u00172\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004J \u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001b2\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004J \u0010!\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001f2\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004J\u0012\u0010$\u001a\u0004\u0018\u00010\u00052\u0006\u0010#\u001a\u00020\"H\u0016R\u001a\u0010&\u001a\u00020%8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u001a\u0010+\u001a\u00020*8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.¨\u00061"}, d2 = {"Lcom/amazon/music/media/auto/brush/ContentMetadataConverter;", "", "Lcom/amazon/music/page/api/model/Track;", "track", "", "", "tags", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "convertTrackMetadata", "Lcom/amazon/music/page/api/model/Station;", "station", "Lcom/amazon/music/views/library/metadata/StationMetadata;", "convertStationMetadata", "Lcom/amazon/music/page/api/model/Playlist;", Environment.PLAYLIST_PATH, "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "convertPlaylistMetadata", "Lcom/amazon/music/views/library/metadata/UserPlaylistMetadata;", "convertUserPlaylistMetadata", "Lcom/amazon/music/page/api/model/Album;", "album", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "convertAlbumMetadata", "Lcom/amazon/music/page/api/model/Artist;", "artist", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "convertArtistMetadata", "Lcom/amazon/music/page/api/model/PodcastEpisode;", "podcastEpisode", "Lcom/amazon/music/views/library/metadata/PodcastMetadata;", "convertPodcastEpisodeMetadata", "Lcom/amazon/music/page/api/model/PodcastShow;", "podcastShow", "convertPodcastShowMetadata", "Ljava/io/Serializable;", "model", "getImageUrl", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "ContentPlaybackUtils", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "getContentPlaybackUtils", "()Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "Lcom/amazon/music/media/auto/util/BrushConverterUtil;", "BrushConverterUtils", "Lcom/amazon/music/media/auto/util/BrushConverterUtil;", "getBrushConverterUtils", "()Lcom/amazon/music/media/auto/util/BrushConverterUtil;", "<init>", "(Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;Lcom/amazon/music/media/auto/util/BrushConverterUtil;)V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public class ContentMetadataConverter {
    private final BrushConverterUtil BrushConverterUtils;
    private final ContentPlaybackUtil ContentPlaybackUtils;

    public ContentMetadataConverter(ContentPlaybackUtil ContentPlaybackUtils, BrushConverterUtil BrushConverterUtils) {
        Intrinsics.checkNotNullParameter(ContentPlaybackUtils, "ContentPlaybackUtils");
        Intrinsics.checkNotNullParameter(BrushConverterUtils, "BrushConverterUtils");
        this.ContentPlaybackUtils = ContentPlaybackUtils;
        this.BrushConverterUtils = BrushConverterUtils;
    }

    protected final BrushConverterUtil getBrushConverterUtils() {
        return this.BrushConverterUtils;
    }

    public final TrackMetadata convertTrackMetadata(Track track, List<String> tags) {
        Intrinsics.checkNotNullParameter(track, "track");
        Album album = track.getAlbum();
        List<Artist> artists = track.getArtists();
        Artist artist = artists == null ? null : (Artist) CollectionsKt___CollectionsKt.firstOrNull((List) artists);
        String blockRef = track.getBlockRef();
        ContentPlaybackUtil contentPlaybackUtil = this.ContentPlaybackUtils;
        String asin = track.getAsin();
        Intrinsics.checkNotNullExpressionValue(asin, "track.asin");
        Uri trackUri = contentPlaybackUtil.getTrackUri(asin);
        String asin2 = track.getAsin();
        String title = track.getTitle();
        String imageUrl = getImageUrl(track);
        String asin3 = album == null ? null : album.getAsin();
        String title2 = album == null ? null : album.getTitle();
        String asin4 = artist == null ? null : artist.getAsin();
        String name = artist == null ? null : artist.getName();
        Boolean isInLibrary = track.getIsInLibrary();
        boolean zBooleanValue = isInLibrary == null ? false : isInLibrary.booleanValue();
        Boolean isOwned = track.getIsOwned();
        boolean zBooleanValue2 = isOwned != null ? isOwned.booleanValue() : false;
        Boolean hasExplicitLanguage = track.getParentalControls().getHasExplicitLanguage();
        BrushConverterUtil brushConverterUtil = this.BrushConverterUtils;
        List<String> contentTiers = track.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "track.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtil.convertTiers(contentTiers);
        String string = track.getAssetType().toString();
        BrushConverterUtil brushConverterUtil2 = this.BrushConverterUtils;
        List<AssetQuality> assetQualities = track.getAssetQualities();
        Intrinsics.checkNotNullExpressionValue(assetQualities, "track.assetQualities");
        List<com.amazon.music.models.AssetQuality> listConvertAssetQualityPage = brushConverterUtil2.convertAssetQualityPage(assetQualities);
        String str = title2;
        int millis = (int) TimeUnit.SECONDS.toMillis(track.getDuration().longValueExact());
        BrushConverterUtil brushConverterUtil3 = this.BrushConverterUtils;
        List<String> contentTiers2 = track.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers2, "track.contentTiers");
        Set<SubscriptionType> setConvertOnDemandTiers = brushConverterUtil3.convertOnDemandTiers(contentTiers2);
        PlaymodeEligibility playmodeEligibility = track.getPlaymodeEligibility();
        com.amazon.music.platform.data.eligibility.PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = playmodeEligibility == null ? null : getBrushConverterUtils().convertPlaymodeEligibility(playmodeEligibility);
        Boolean boolValueOf = Boolean.valueOf(zBooleanValue);
        Boolean boolValueOf2 = Boolean.valueOf(zBooleanValue2);
        Intrinsics.checkNotNullExpressionValue(string, "toString()");
        return new TrackMetadata(blockRef, trackUri, tags, asin2, title, imageUrl, null, asin3, str, null, asin4, name, null, null, boolValueOf, boolValueOf2, null, hasExplicitLanguage, null, setConvertTiers, string, listConvertAssetQualityPage, millis, null, null, null, null, null, null, false, null, false, playmodeEligibilityConvertPlaymodeEligibility, setConvertOnDemandTiers, null, null, -8048064, 12, null);
    }

    public final StationMetadata convertStationMetadata(Station station, List<String> tags) {
        Intrinsics.checkNotNullParameter(station, "station");
        String blockRef = station.getBlockRef();
        ContentPlaybackUtil contentPlaybackUtil = this.ContentPlaybackUtils;
        String stationKey = station.getStationKey();
        Intrinsics.checkNotNullExpressionValue(stationKey, "station.stationKey");
        Uri stationUri = contentPlaybackUtil.getStationUri(stationKey);
        String stationKey2 = station.getStationKey();
        String title = station.getTitle();
        String imageUrl = getImageUrl(station);
        BrushConverterUtil brushConverterUtil = this.BrushConverterUtils;
        List<String> contentTiers = station.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "station.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtil.convertTiers(contentTiers);
        Map mapEmptyMap = MapsKt__MapsKt.emptyMap();
        Intrinsics.checkNotNullExpressionValue(stationKey2, "stationKey");
        return new StationMetadata(blockRef, stationUri, tags, stationKey2, title, null, null, null, imageUrl, mapEmptyMap, setConvertTiers, null, null, null, 14560, null);
    }

    public final PlaylistMetadata convertPlaylistMetadata(Playlist playlist, List<String> tags) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        String blockRef = playlist.getBlockRef();
        ContentPlaybackUtil contentPlaybackUtil = this.ContentPlaybackUtils;
        String asin = playlist.getAsin();
        Intrinsics.checkNotNullExpressionValue(asin, "playlist.asin");
        Uri playlistUri = contentPlaybackUtil.getPlaylistUri(asin);
        String asin2 = playlist.getAsin();
        String title = playlist.getTitle();
        String imageUrl = getImageUrl(playlist);
        Boolean isInLibrary = playlist.getIsInLibrary();
        boolean zBooleanValue = isInLibrary == null ? false : isInLibrary.booleanValue();
        BrushConverterUtil brushConverterUtil = this.BrushConverterUtils;
        List<String> contentTiers = playlist.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "playlist.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtil.convertTiers(contentTiers);
        List<AssetType> assetTypes = playlist.getAssetTypes();
        Intrinsics.checkNotNullExpressionValue(assetTypes, "playlist.assetTypes");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = assetTypes.iterator();
        while (it.hasNext()) {
            String string = ((AssetType) it.next()).toString();
            if (string != null) {
                arrayList.add(string);
            }
        }
        BrushConverterUtil brushConverterUtil2 = this.BrushConverterUtils;
        List<String> contentTiers2 = playlist.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers2, "playlist.contentTiers");
        Set<SubscriptionType> setConvertOnDemandTiers = brushConverterUtil2.convertOnDemandTiers(contentTiers2);
        PlaymodeEligibility playmodeEligibility = playlist.getPlaymodeEligibility();
        return new PlaylistMetadata(blockRef, playlistUri, tags, asin2, title, imageUrl, null, null, null, null, Boolean.valueOf(zBooleanValue), setConvertTiers, arrayList, false, null, null, null, null, false, null, false, playmodeEligibility == null ? null : getBrushConverterUtils().convertPlaymodeEligibility(playmodeEligibility), setConvertOnDemandTiers, false, null, 27255744, null);
    }

    public final UserPlaylistMetadata convertUserPlaylistMetadata(Playlist playlist, List<String> tags) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        String blockRef = playlist.getBlockRef();
        Uri userPlaylistUri = this.ContentPlaybackUtils.getUserPlaylistUri();
        String asin = playlist.getAsin();
        String title = playlist.getTitle();
        String imageUrl = getImageUrl(playlist);
        Boolean isOwned = playlist.getIsOwned();
        boolean zBooleanValue = isOwned == null ? true : isOwned.booleanValue();
        BrushConverterUtil brushConverterUtil = this.BrushConverterUtils;
        List<String> contentTiers = playlist.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "playlist.contentTiers");
        Set<SubscriptionType> setConvertOnDemandTiers = brushConverterUtil.convertOnDemandTiers(contentTiers);
        BrushConverterUtil brushConverterUtil2 = this.BrushConverterUtils;
        List<String> contentTiers2 = playlist.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers2, "playlist.contentTiers");
        Set<SubscriptionType> setConvertContentTiers = brushConverterUtil2.convertContentTiers(contentTiers2);
        PlaymodeEligibility playmodeEligibility = playlist.getPlaymodeEligibility();
        com.amazon.music.platform.data.eligibility.PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = playmodeEligibility == null ? null : getBrushConverterUtils().convertPlaymodeEligibility(playmodeEligibility);
        Intrinsics.checkNotNullExpressionValue(asin, "asin");
        return new UserPlaylistMetadata(blockRef, userPlaylistUri, tags, title, imageUrl, null, null, null, zBooleanValue, asin, playmodeEligibilityConvertPlaymodeEligibility, setConvertContentTiers, setConvertOnDemandTiers, false, 8416, null);
    }

    public final AlbumMetadata convertAlbumMetadata(Album album, List<String> tags) {
        Intrinsics.checkNotNullParameter(album, "album");
        List<Artist> artists = album.getArtists();
        Artist artist = artists == null ? null : (Artist) CollectionsKt___CollectionsKt.firstOrNull((List) artists);
        String blockRef = album.getBlockRef();
        ContentPlaybackUtil contentPlaybackUtil = this.ContentPlaybackUtils;
        String asin = album.getAsin();
        String title = album.getTitle();
        String name = artist == null ? null : artist.getName();
        Intrinsics.checkNotNullExpressionValue(asin, "asin");
        Uri albumUri = contentPlaybackUtil.getAlbumUri(name, title, asin);
        String asin2 = album.getAsin();
        String title2 = album.getTitle();
        String imageUrl = getImageUrl(album);
        String asin3 = artist == null ? null : artist.getAsin();
        String name2 = artist == null ? null : artist.getName();
        Boolean isInLibrary = album.getIsInLibrary();
        boolean zBooleanValue = isInLibrary == null ? false : isInLibrary.booleanValue();
        Boolean isOwned = album.getIsOwned();
        boolean zBooleanValue2 = isOwned != null ? isOwned.booleanValue() : false;
        List<String> contentTiers = album.getContentTiers();
        Set<ContentAccessType> setConvertTiers = contentTiers == null ? null : getBrushConverterUtils().convertTiers(contentTiers);
        if (setConvertTiers == null) {
            setConvertTiers = SetsKt__SetsKt.emptySet();
        }
        Set<ContentAccessType> set = setConvertTiers;
        List<String> contentTiers2 = album.getContentTiers();
        Set<SubscriptionType> setConvertOnDemandTiers = contentTiers2 == null ? null : getBrushConverterUtils().convertOnDemandTiers(contentTiers2);
        PlaymodeEligibility playmodeEligibility = album.getPlaymodeEligibility();
        return new AlbumMetadata(blockRef, albumUri, tags, asin2, title2, imageUrl, null, asin3, name2, Boolean.valueOf(zBooleanValue), Boolean.valueOf(zBooleanValue2), set, false, null, null, null, playmodeEligibility != null ? getBrushConverterUtils().convertPlaymodeEligibility(playmodeEligibility) : null, setConvertOnDemandTiers, null, null, 847936, null);
    }

    public final ArtistMetadata convertArtistMetadata(Artist artist, List<String> tags) {
        Intrinsics.checkNotNullParameter(artist, "artist");
        String blockRef = artist.getBlockRef();
        ContentPlaybackUtil contentPlaybackUtil = this.ContentPlaybackUtils;
        String asin = artist.getAsin();
        Intrinsics.checkNotNullExpressionValue(asin, "artist.asin");
        return new ArtistMetadata(blockRef, contentPlaybackUtil.getArtistUri(asin), tags, null, artist.getAsin(), artist.getName(), null, getImageUrl(artist), artist.getContributorAsin(), null, null, false, null, null, false, null, null, false, false, null, 1048136, null);
    }

    public final PodcastMetadata convertPodcastEpisodeMetadata(PodcastEpisode podcastEpisode, List<String> tags) {
        Intrinsics.checkNotNullParameter(podcastEpisode, "podcastEpisode");
        BrushConverterUtil brushConverterUtil = this.BrushConverterUtils;
        String podcastShowCatalogId = podcastEpisode.getPodcastShowCatalogId();
        Intrinsics.checkNotNullExpressionValue(podcastShowCatalogId, "podcastEpisode.podcastShowCatalogId");
        String catalogId = podcastEpisode.getCatalogId();
        Intrinsics.checkNotNullExpressionValue(catalogId, "podcastEpisode.catalogId");
        String podcastShowTitle = podcastEpisode.getPodcastShowTitle();
        Intrinsics.checkNotNullExpressionValue(podcastShowTitle, "podcastEpisode.podcastShowTitle");
        String title = podcastEpisode.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "podcastEpisode.title");
        String podcastEpisodeTarget = brushConverterUtil.getPodcastEpisodeTarget(podcastShowCatalogId, catalogId, podcastShowTitle, title);
        BrushConverterUtil brushConverterUtil2 = this.BrushConverterUtils;
        String podcastShowCatalogId2 = podcastEpisode.getPodcastShowCatalogId();
        Intrinsics.checkNotNullExpressionValue(podcastShowCatalogId2, "podcastEpisode.podcastShowCatalogId");
        String catalogId2 = podcastEpisode.getCatalogId();
        Intrinsics.checkNotNullExpressionValue(catalogId2, "podcastEpisode.catalogId");
        String podcastShowTitle2 = podcastEpisode.getPodcastShowTitle();
        Intrinsics.checkNotNullExpressionValue(podcastShowTitle2, "podcastEpisode.podcastShowTitle");
        String title2 = podcastEpisode.getTitle();
        Intrinsics.checkNotNullExpressionValue(title2, "podcastEpisode.title");
        String podcastEpisodeBackgroundTarget = brushConverterUtil2.getPodcastEpisodeBackgroundTarget(podcastShowCatalogId2, catalogId2, podcastShowTitle2, title2);
        String blockRef = podcastEpisode.getBlockRef();
        String title3 = podcastEpisode.getTitle();
        String subtitle = podcastEpisode.getSubtitle();
        Uri podcastEpisodeUri = this.ContentPlaybackUtils.getPodcastEpisodeUri();
        String catalogId3 = podcastEpisode.getCatalogId();
        String imageUrl = getImageUrl(podcastEpisode);
        String podcastShowCatalogId3 = podcastEpisode.getPodcastShowCatalogId();
        Intrinsics.checkNotNullExpressionValue(catalogId3, "catalogId");
        return new PodcastMetadata(blockRef, podcastEpisodeUri, tags, catalogId3, podcastEpisodeTarget, podcastEpisodeBackgroundTarget, title3, subtitle, imageUrl, podcastShowCatalogId3, null, 1024, null);
    }

    public final PodcastMetadata convertPodcastShowMetadata(PodcastShow podcastShow, List<String> tags) {
        Intrinsics.checkNotNullParameter(podcastShow, "podcastShow");
        BrushConverterUtil brushConverterUtil = this.BrushConverterUtils;
        String catalogId = podcastShow.getCatalogId();
        Intrinsics.checkNotNullExpressionValue(catalogId, "podcastShow.catalogId");
        String title = podcastShow.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "podcastShow.title");
        String podcastShowTarget = brushConverterUtil.getPodcastShowTarget(catalogId, title);
        BrushConverterUtil brushConverterUtil2 = this.BrushConverterUtils;
        String catalogId2 = podcastShow.getCatalogId();
        Intrinsics.checkNotNullExpressionValue(catalogId2, "podcastShow.catalogId");
        String title2 = podcastShow.getTitle();
        Intrinsics.checkNotNullExpressionValue(title2, "podcastShow.title");
        String podcastShowBackgroundTarget = brushConverterUtil2.getPodcastShowBackgroundTarget(catalogId2, title2);
        String blockRef = podcastShow.getBlockRef();
        String title3 = podcastShow.getTitle();
        String subtitle = podcastShow.getSubtitle();
        Uri podcastShowUri = this.ContentPlaybackUtils.getPodcastShowUri();
        String catalogId3 = podcastShow.getCatalogId();
        String catalogId4 = podcastShow.getCatalogId();
        String imageUrl = getImageUrl(podcastShow);
        Intrinsics.checkNotNullExpressionValue(catalogId3, "catalogId");
        return new PodcastMetadata(blockRef, podcastShowUri, tags, catalogId3, podcastShowTarget, podcastShowBackgroundTarget, title3, subtitle, imageUrl, catalogId4, null, 1024, null);
    }

    public String getImageUrl(Serializable model) {
        Image image;
        Object next;
        Object next2;
        Object next3;
        List<Image> images;
        Image image2;
        Object next4;
        Image image3;
        Object next5;
        Image image4;
        Intrinsics.checkNotNullParameter(model, "model");
        if (model instanceof Artist) {
            List<Image> images2 = ((Artist) model).getImages();
            if (images2 == null) {
                image4 = null;
            } else {
                Iterator<T> it = images2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next5 = null;
                        break;
                    }
                    next5 = it.next();
                    if (((Image) next5).getType() == ImageType.ORIGINAL_ART) {
                        break;
                    }
                }
                image4 = (Image) next5;
            }
            if (image4 == null) {
                return null;
            }
            return image4.getSrc();
        }
        if (model instanceof Album) {
            List<Image> images3 = ((Album) model).getImages();
            if (images3 == null) {
                image3 = null;
            } else {
                Iterator<T> it2 = images3.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        next4 = null;
                        break;
                    }
                    next4 = it2.next();
                    if (((Image) next4).getType() == ImageType.ORIGINAL_ART) {
                        break;
                    }
                }
                image3 = (Image) next4;
            }
            if (image3 == null) {
                return null;
            }
            return image3.getSrc();
        }
        if (model instanceof Track) {
            Track track = (Track) model;
            List<Image> images4 = track.getImages();
            Intrinsics.checkNotNullExpressionValue(images4, "model.images");
            Iterator<T> it3 = images4.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    next3 = null;
                    break;
                }
                next3 = it3.next();
                if (((Image) next3).getType() == ImageType.ORIGINAL_ART) {
                    break;
                }
            }
            Image image5 = (Image) next3;
            if (image5 != null) {
                return image5.getSrc();
            }
            Album album = track.getAlbum();
            if (album == null || (images = album.getImages()) == null || (image2 = (Image) CollectionsKt___CollectionsKt.firstOrNull((List) images)) == null) {
                return null;
            }
            return image2.getSrc();
        }
        if (model instanceof Station) {
            List<Image> images5 = ((Station) model).getImages();
            Intrinsics.checkNotNullExpressionValue(images5, "model.images");
            Iterator<T> it4 = images5.iterator();
            while (true) {
                if (!it4.hasNext()) {
                    next2 = null;
                    break;
                }
                next2 = it4.next();
                if (((Image) next2).getType() == ImageType.ORIGINAL_ART) {
                    break;
                }
            }
            Image image6 = (Image) next2;
            if (image6 == null) {
                return null;
            }
            return image6.getSrc();
        }
        if (!(model instanceof Playlist)) {
            if (model instanceof PodcastEpisode) {
                Image image7 = ((PodcastEpisode) model).getImage();
                if (image7 == null) {
                    return null;
                }
                return image7.getSrc();
            }
            if (!(model instanceof PodcastShow) || (image = ((PodcastShow) model).getImage()) == null) {
                return null;
            }
            return image.getSrc();
        }
        List<Image> images6 = ((Playlist) model).getImages();
        Intrinsics.checkNotNullExpressionValue(images6, "model.images");
        Iterator<T> it5 = images6.iterator();
        while (true) {
            if (!it5.hasNext()) {
                next = null;
                break;
            }
            next = it5.next();
            if (((Image) next).getType() == ImageType.ORIGINAL_ART) {
                break;
            }
        }
        Image image8 = (Image) next;
        if (image8 == null) {
            return null;
        }
        return image8.getSrc();
    }
}
