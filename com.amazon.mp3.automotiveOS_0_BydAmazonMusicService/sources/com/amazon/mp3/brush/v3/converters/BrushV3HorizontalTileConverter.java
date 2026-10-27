package com.amazon.mp3.brush.v3.converters;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.brush.converters.BrushConverterUtils;
import com.amazon.mp3.brush.v3.extensions.ImageKt;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.music.ContentAccessType;
import com.amazon.music.page.api.model.Album;
import com.amazon.music.page.api.model.Artist;
import com.amazon.music.page.api.model.AssetType;
import com.amazon.music.page.api.model.Barker;
import com.amazon.music.page.api.model.Button;
import com.amazon.music.page.api.model.Entity;
import com.amazon.music.page.api.model.Genre;
import com.amazon.music.page.api.model.Image;
import com.amazon.music.page.api.model.Playlist;
import com.amazon.music.page.api.model.PodcastAvailableUpsells;
import com.amazon.music.page.api.model.PodcastContentTraits;
import com.amazon.music.page.api.model.PodcastEpisode;
import com.amazon.music.page.api.model.PodcastPlaybackMode;
import com.amazon.music.page.api.model.Station;
import com.amazon.music.page.api.model.Track;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.GenreMetadata;
import com.amazon.music.views.library.metadata.NavigationMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemModel;
import com.visualon.OSMPUtils.voOSType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushV3HorizontalTileConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\"\u0010#J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0007H\u0002J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\tH\u0002J\"\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u000b2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0002J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0010H\u0002J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0012H\u0002J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0014H\u0002J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0016H\u0002J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0004\u001a\u00020\u0018H\u0002J \u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\f2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001bH\u0002J\u0012\u0010!\u001a\u0004\u0018\u00010 2\u0006\u0010\u001f\u001a\u00020\u0002H\u0016J\"\u0010!\u001a\u0004\u0018\u00010 2\u0006\u0010\u001f\u001a\u00020\u00022\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0016¨\u0006$"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3HorizontalTileConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/page/api/model/Entity;", "Lcom/amazon/music/page/api/model/Album;", "item", "Lcom/amazon/music/views/library/models/HorizontalTileModel;", "convertAlbum", "Lcom/amazon/music/page/api/model/Playlist;", "convertPlaylist", "Lcom/amazon/music/page/api/model/Station;", "convertStation", "Lcom/amazon/music/page/api/model/Track;", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadataList", "convertTrack", "Lcom/amazon/music/page/api/model/Genre;", "convertGenre", "Lcom/amazon/music/page/api/model/Artist;", "convertArtist", "Lcom/amazon/music/page/api/model/Barker;", "convertBarker", "Lcom/amazon/music/page/api/model/Button;", "convertButton", "Lcom/amazon/music/page/api/model/PodcastEpisode;", "Lcom/amazon/podcast/views/episodeRowItemsListView/EpisodeRowItemModel;", "convertPodcastEpisode", "", "", "contentEncodings", "removeImmersiveEncoding", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class BrushV3HorizontalTileConverter implements SingleBaseModelConverter<Entity> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return convert(data, null);
    }

    public BaseViewModel convert(Entity data, List<? extends ContentMetadata> metadataList) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data.getAlbum() != null) {
            Album album = data.getAlbum();
            Intrinsics.checkNotNullExpressionValue(album, "data.album");
            return convertAlbum(album);
        }
        if (data.getPlaylist() != null) {
            Playlist playlist = data.getPlaylist();
            Intrinsics.checkNotNullExpressionValue(playlist, "data.playlist");
            return convertPlaylist(playlist);
        }
        if (data.getStation() != null) {
            Station station = data.getStation();
            Intrinsics.checkNotNullExpressionValue(station, "data.station");
            return convertStation(station);
        }
        if (data.getTrack() != null) {
            Track track = data.getTrack();
            Intrinsics.checkNotNullExpressionValue(track, "data.track");
            return convertTrack(track, metadataList);
        }
        if (data.getGenre() != null) {
            Genre genre = data.getGenre();
            Intrinsics.checkNotNullExpressionValue(genre, "data.genre");
            return convertGenre(genre);
        }
        if (data.getArtist() != null) {
            Artist artist = data.getArtist();
            Intrinsics.checkNotNullExpressionValue(artist, "data.artist");
            return convertArtist(artist);
        }
        if (data.getBarker() != null) {
            Barker barker = data.getBarker();
            Intrinsics.checkNotNullExpressionValue(barker, "data.barker");
            return convertBarker(barker);
        }
        if (data.getButton() != null) {
            Button button = data.getButton();
            Intrinsics.checkNotNullExpressionValue(button, "data.button");
            return convertButton(button);
        }
        if (data.getPodcastEpisode() == null) {
            return null;
        }
        PodcastEpisode podcastEpisode = data.getPodcastEpisode();
        Intrinsics.checkNotNullExpressionValue(podcastEpisode, "data.podcastEpisode");
        return convertPodcastEpisode(podcastEpisode);
    }

    private final HorizontalTileModel convertAlbum(Album item) {
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility;
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        List<Artist> artists = item.getArtists();
        Artist artist = artists == null ? null : (Artist) CollectionsKt___CollectionsKt.first((List) artists);
        List<Image> images = item.getImages();
        Intrinsics.checkNotNullExpressionValue(images, "item.images");
        Image originalArtOrDefault = ImageKt.getOriginalArtOrDefault(images);
        List<String> listRemoveImmersiveEncoding = removeImmersiveEncoding(item.getContentEncoding());
        String blockRef = item.getBlockRef();
        String primaryTitle = item.getPrimaryTitle();
        String secondaryTitle = item.getSecondaryTitle();
        String tertiaryTitle = item.getTertiaryTitle();
        ArtworkFrameModel artworkFrameModel = new ArtworkFrameModel(item.getBlockRef(), originalArtOrDefault == null ? null : originalArtOrDefault.getSrc(), 0, null, null, null, null, null, null, null, null, null, 4088, null);
        String blockRef2 = item.getBlockRef();
        String title = item.getTitle();
        String src = originalArtOrDefault == null ? null : originalArtOrDefault.getSrc();
        String asin2 = artist == null ? null : artist.getAsin();
        String name = artist == null ? null : artist.getName();
        Boolean isInLibrary = item.getIsInLibrary();
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        List<String> contentTiers2 = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers2, "item.contentTiers");
        Set<SubscriptionType> setConvertOnDemandTiers = brushConverterUtils.convertOnDemandTiers(contentTiers2);
        if (item.getPlaymodeEligibility() != null) {
            com.amazon.music.page.api.model.PlaymodeEligibility playmodeEligibility = item.getPlaymodeEligibility();
            Intrinsics.checkNotNullExpressionValue(playmodeEligibility, "item.playmodeEligibility");
            playmodeEligibilityConvertPlaymodeEligibility = brushConverterUtils.convertPlaymodeEligibility(playmodeEligibility);
        } else {
            playmodeEligibilityConvertPlaymodeEligibility = null;
        }
        return new HorizontalTileModel(blockRef, null, primaryTitle, secondaryTitle, tertiaryTitle, artworkFrameModel, null, false, null, null, false, false, listRemoveImmersiveEncoding, false, false, null, 0, new AlbumMetadata(blockRef2, ContentPlaybackUtils.INSTANCE.getAlbumUri(artist != null ? artist.getName() : null, item.getTitle(), asin), null, asin, title, src, null, asin2, name, isInLibrary, Boolean.FALSE, setConvertTiers, false, null, null, null, playmodeEligibilityConvertPlaymodeEligibility, setConvertOnDemandTiers, null, null, 847940, null), null, false, false, null, false, null, null, null, false, false, false, null, 1073541058, null);
    }

    private final HorizontalTileModel convertPlaylist(Playlist item) {
        String asin = item.getAsin();
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = null;
        if (asin == null) {
            return null;
        }
        List<Image> images = item.getImages();
        Intrinsics.checkNotNullExpressionValue(images, "item.images");
        Image originalArtOrDefault = ImageKt.getOriginalArtOrDefault(images);
        int i = (item.getAssetTypes() == null || !AssetType.VIDEO.equals(item.getAssetTypes().get(0))) ? 1 : 8;
        String blockRef = item.getBlockRef();
        String primaryTitle = item.getPrimaryTitle();
        String secondaryTitle = item.getSecondaryTitle();
        String tertiaryTitle = item.getTertiaryTitle();
        ArtworkFrameModel artworkFrameModel = new ArtworkFrameModel(item.getBlockRef(), originalArtOrDefault == null ? null : originalArtOrDefault.getSrc(), Integer.valueOf(i), null, null, null, null, null, null, null, null, null, 4088, null);
        String blockRef2 = item.getBlockRef();
        String title = item.getTitle();
        String src = originalArtOrDefault == null ? null : originalArtOrDefault.getSrc();
        Boolean isInLibrary = item.getIsInLibrary();
        Boolean isOwned = item.getIsOwned();
        boolean zBooleanValue = isOwned == null ? false : isOwned.booleanValue();
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        List<String> contentTiers2 = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers2, "item.contentTiers");
        Set<SubscriptionType> setConvertOnDemandTiers = brushConverterUtils.convertOnDemandTiers(contentTiers2);
        if (item.getPlaymodeEligibility() != null) {
            com.amazon.music.page.api.model.PlaymodeEligibility playmodeEligibility = item.getPlaymodeEligibility();
            Intrinsics.checkNotNullExpressionValue(playmodeEligibility, "item.playmodeEligibility");
            playmodeEligibilityConvertPlaymodeEligibility = brushConverterUtils.convertPlaymodeEligibility(playmodeEligibility);
        }
        PlaymodeEligibility playmodeEligibility2 = playmodeEligibilityConvertPlaymodeEligibility;
        Uri playlistUri = ContentPlaybackUtils.INSTANCE.getPlaylistUri(asin);
        List<AssetType> assetTypes = item.getAssetTypes();
        Intrinsics.checkNotNullExpressionValue(assetTypes, "item.assetTypes");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = assetTypes.iterator();
        while (it.hasNext()) {
            String string = ((AssetType) it.next()).toString();
            if (string != null) {
                arrayList.add(string);
            }
        }
        int i2 = i;
        return new HorizontalTileModel(blockRef, null, primaryTitle, secondaryTitle, tertiaryTitle, artworkFrameModel, null, false, null, null, false, false, null, false, false, null, i2, new PlaylistMetadata(blockRef2, playlistUri, null, asin, title, src, null, null, null, null, isInLibrary, setConvertTiers, arrayList, false, null, null, null, null, zBooleanValue, null, false, playmodeEligibility2, setConvertOnDemandTiers, false, null, 26993604, null), null, i2 == 8, false, null, false, null, null, null, false, false, false, null, 1073020866, null);
    }

    private final HorizontalTileModel convertStation(Station item) {
        String stationKey = item.getStationKey();
        if (stationKey == null) {
            return null;
        }
        List<Image> images = item.getImages();
        Intrinsics.checkNotNullExpressionValue(images, "item.images");
        Image originalArtOrDefault = ImageKt.getOriginalArtOrDefault(images);
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        return new HorizontalTileModel(item.getBlockRef(), null, item.getTitle(), item.getSecondaryTitle(), item.getTertiaryTitle(), new ArtworkFrameModel(item.getBlockRef(), originalArtOrDefault == null ? null : originalArtOrDefault.getSrc(), 3, null, null, 1, null, null, null, null, null, null, 4056, null), null, false, null, null, false, false, null, false, false, null, 3, new StationMetadata(item.getBlockRef(), ContentPlaybackUtils.INSTANCE.getStationUri(stationKey), null, stationKey, item.getTitle(), null, null, null, originalArtOrDefault != null ? originalArtOrDefault.getSrc() : null, MapsKt__MapsKt.emptyMap(), brushConverterUtils.convertTiers(contentTiers), null, null, null, 14564, null), null, false, false, null, false, null, null, null, false, false, false, null, 1073545154, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x00f6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.music.views.library.models.HorizontalTileModel convertTrack(com.amazon.music.page.api.model.Track r77, java.util.List<? extends com.amazon.music.views.library.metadata.ContentMetadata> r78) {
        /*
            Method dump skipped, instruction units count: 615
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.brush.v3.converters.BrushV3HorizontalTileConverter.convertTrack(com.amazon.music.page.api.model.Track, java.util.List):com.amazon.music.views.library.models.HorizontalTileModel");
    }

    private final HorizontalTileModel convertGenre(Genre item) {
        String id = item.getId();
        if (id == null) {
            return null;
        }
        List<Image> images = item.getImages();
        Intrinsics.checkNotNullExpressionValue(images, "item.images");
        Image originalArtOrDefault = ImageKt.getOriginalArtOrDefault(images);
        return new HorizontalTileModel(item.getBlockRef(), null, item.getPrimaryTitle(), item.getSecondaryTitle(), item.getTertiaryTitle(), new ArtworkFrameModel(item.getBlockRef(), originalArtOrDefault == null ? null : originalArtOrDefault.getSrc(), 5, null, null, null, null, null, null, null, null, null, 4088, null), null, false, null, null, false, false, null, false, false, null, 5, new GenreMetadata(item.getBlockRef(), ContentPlaybackUtils.INSTANCE.getGenreUri(), null, item.getStationKey(), id, item.getTitle(), originalArtOrDefault != null ? originalArtOrDefault.getSrc() : null, null, voOSType.VOOSMP_PID_ANALYTICS_FOUNDATION_LOCATION, null), null, false, false, null, false, null, null, null, false, false, false, null, 1073545154, null);
    }

    private final HorizontalTileModel convertArtist(Artist item) {
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        List<Image> images = item.getImages();
        Intrinsics.checkNotNullExpressionValue(images, "item.images");
        Image originalArtOrDefault = ImageKt.getOriginalArtOrDefault(images);
        return new HorizontalTileModel(item.getBlockRef(), null, item.getPrimaryTitle(), item.getSecondaryTitle(), item.getTertiaryTitle(), new ArtworkFrameModel(item.getBlockRef(), originalArtOrDefault == null ? null : originalArtOrDefault.getSrc(), 4, null, null, null, null, null, null, null, null, null, 4088, null), null, false, null, null, false, false, null, false, false, null, 4, new ArtistMetadata(item.getBlockRef(), ContentPlaybackUtils.INSTANCE.getArtistUri(), null, null, asin, item.getName(), null, originalArtOrDefault != null ? originalArtOrDefault.getSrc() : null, item.getContributorAsin(), null, null, false, null, null, false, null, null, false, false, null, 1048140, null), null, false, false, null, false, null, null, null, false, false, false, null, 1073545154, null);
    }

    private final HorizontalTileModel convertBarker(Barker item) {
        if (item.getTarget() == null) {
            return null;
        }
        List<Image> images = item.getImages();
        Intrinsics.checkNotNullExpressionValue(images, "item.images");
        Image originalArtOrDefault = ImageKt.getOriginalArtOrDefault(images);
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        String subTitle = item.getSubTitle();
        ArtworkFrameModel artworkFrameModel = new ArtworkFrameModel(item.getBlockRef(), originalArtOrDefault != null ? originalArtOrDefault.getSrc() : null, null, null, null, null, null, null, null, null, null, null, 4088, null);
        String blockRef2 = item.getBlockRef();
        String target = item.getTarget();
        Uri barkerUri = ContentPlaybackUtils.INSTANCE.getBarkerUri();
        Intrinsics.checkNotNullExpressionValue(target, "target");
        return new HorizontalTileModel(blockRef, null, title, subTitle, null, artworkFrameModel, null, false, null, null, false, false, null, false, false, null, 2, new NavigationMetadata(blockRef2, barkerUri, null, target, null, null, null, 116, null), null, false, false, null, false, null, null, null, false, false, false, null, 1073545170, null);
    }

    private final HorizontalTileModel convertButton(Button item) {
        if (item.getTarget() == null) {
            return null;
        }
        List<Image> images = item.getImages();
        Intrinsics.checkNotNullExpressionValue(images, "item.images");
        Image originalArtOrDefault = ImageKt.getOriginalArtOrDefault(images);
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        ArtworkFrameModel artworkFrameModel = new ArtworkFrameModel(item.getBlockRef(), originalArtOrDefault != null ? originalArtOrDefault.getSrc() : null, null, null, null, null, null, null, null, null, null, null, 4088, null);
        String blockRef2 = item.getBlockRef();
        String target = item.getTarget();
        Uri buttonUri = ContentPlaybackUtils.INSTANCE.getButtonUri();
        Intrinsics.checkNotNullExpressionValue(target, "target");
        return new HorizontalTileModel(blockRef, null, title, null, null, artworkFrameModel, null, false, null, null, false, false, null, false, false, null, 2, new NavigationMetadata(blockRef2, buttonUri, null, target, null, null, null, 116, null), null, false, false, null, false, null, null, null, false, false, false, null, 1073545178, null);
    }

    private final EpisodeRowItemModel convertPodcastEpisode(PodcastEpisode item) {
        if (item.getCatalogId() == null) {
            return null;
        }
        String catalogId = item.getCatalogId();
        String podcastShowCatalogId = item.getPodcastShowCatalogId();
        Image image = item.getImage();
        String src = image == null ? null : image.getSrc();
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        String subtitle = item.getSubtitle();
        BigDecimal durationSeconds = item.getDurationSeconds();
        String publishDate = item.getPublishDate();
        PodcastContentTraits contentTraits = item.getContentTraits();
        PodcastAvailableUpsells availableUpsells = item.getAvailableUpsells();
        PodcastPlaybackMode playbackMode = item.getPlaybackMode();
        String string = playbackMode == null ? null : playbackMode.toString();
        String podcastEpisodeVariantId = item.getPodcastEpisodeVariantId();
        String podcastShowVariantId = item.getPodcastShowVariantId();
        String availabilityDate = item.getAvailabilityDate();
        String str = availabilityDate == null ? null : availabilityDate;
        String blockRef2 = item.getBlockRef();
        String catalogId2 = item.getCatalogId();
        String podcastShowCatalogId2 = item.getPodcastShowCatalogId();
        Image podcastShowImage = item.getPodcastShowImage();
        String src2 = podcastShowImage != null ? podcastShowImage.getSrc() : null;
        String title2 = item.getTitle();
        String subtitle2 = item.getSubtitle();
        List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        Uri podcastEpisodeUri = ContentPlaybackUtils.INSTANCE.getPodcastEpisodeUri();
        Intrinsics.checkNotNullExpressionValue(catalogId2, "catalogId");
        return new EpisodeRowItemModel(blockRef, new PodcastMetadata(blockRef2, podcastEpisodeUri, listEmptyList, catalogId2, "", null, title2, subtitle2, src2, podcastShowCatalogId2, null, 1024, null), 11, catalogId, podcastShowCatalogId, title, subtitle, publishDate, durationSeconds.intValue(), src, contentTraits, availableUpsells, string, podcastEpisodeVariantId, podcastShowVariantId, str);
    }

    private final List<String> removeImmersiveEncoding(List<String> contentEncodings) {
        if (AmazonApplication.getCapabilities().isSpatialAudioEnabled(AmazonApplication.getContext()) && contentEncodings != null) {
            if (contentEncodings.contains("immersive")) {
                contentEncodings.remove("immersive");
            }
        } else {
            boolean z = false;
            if ((contentEncodings != null && contentEncodings.contains("atmosAvailable")) && contentEncodings != null) {
                contentEncodings.remove("atmosAvailable");
            }
            if (contentEncodings != null && contentEncodings.contains("ra360Available")) {
                z = true;
            }
            if (z && contentEncodings != null) {
                contentEncodings.remove("ra360Available");
            }
        }
        return contentEncodings;
    }
}
