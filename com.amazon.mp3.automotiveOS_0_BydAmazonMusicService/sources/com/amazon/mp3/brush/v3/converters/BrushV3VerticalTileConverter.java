package com.amazon.mp3.brush.v3.converters;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.brush.AllAccessPlaylistsUtils;
import com.amazon.mp3.brush.converters.BrushConverterUtils;
import com.amazon.mp3.brush.v3.extensions.ImageKt;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.music.ContentAccessType;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.page.api.model.Album;
import com.amazon.music.page.api.model.Artist;
import com.amazon.music.page.api.model.AssetQuality;
import com.amazon.music.page.api.model.AssetType;
import com.amazon.music.page.api.model.Barker;
import com.amazon.music.page.api.model.Button;
import com.amazon.music.page.api.model.Entity;
import com.amazon.music.page.api.model.Genre;
import com.amazon.music.page.api.model.Image;
import com.amazon.music.page.api.model.ParentalControls;
import com.amazon.music.page.api.model.Playlist;
import com.amazon.music.page.api.model.PodcastAvailableUpsells;
import com.amazon.music.page.api.model.PodcastContentTraits;
import com.amazon.music.page.api.model.PodcastEpisode;
import com.amazon.music.page.api.model.PodcastPlaybackMode;
import com.amazon.music.page.api.model.PodcastShow;
import com.amazon.music.page.api.model.Station;
import com.amazon.music.page.api.model.Track;
import com.amazon.music.page.api.model.VideoStory;
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
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.amazon.music.views.library.metadata.VideoStoryMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.VerticalTileModel;
import com.amazon.music.views.library.models.VideoStoryTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemModel;
import com.amazon.podcast.views.horizontalRowItemsListView.PodcastHorizontalRowItemModel;
import com.amazon.podcast.views.verticalItemView.VerticalItemModel;
import com.visualon.OSMPUtils.voOSType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: BrushV3VerticalTileConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 12\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00011B\u0007¢\u0006\u0004\b/\u00100J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0007H\u0002J\"\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u000fH\u0002J\"\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u00112\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0012H\u0002J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0015H\u0002J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0017H\u0002J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0019H\u0002J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u001bH\u0002J\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0004\u001a\u00020\u001dH\u0002J\u0012\u0010!\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0004\u001a\u00020 H\u0002J \u0010$\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00122\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\"H\u0002J\"\u0010'\u001a\u0004\u0018\u00010&2\u0006\u0010\u0004\u001a\u00020%2\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0012H\u0002J\u0010\u0010)\u001a\u00020(2\u0006\u0010\n\u001a\u00020\tH\u0002J\u0012\u0010,\u001a\u0004\u0018\u00010+2\u0006\u0010*\u001a\u00020\u0002H\u0016J\"\u0010,\u001a\u0004\u0018\u00010+2\u0006\u0010*\u001a\u00020\u00022\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0012H\u0016J\u0010\u0010-\u001a\u0004\u0018\u00010+2\u0006\u0010\u0004\u001a\u00020\u001dJ\u0010\u0010.\u001a\u0004\u0018\u00010+2\u0006\u0010\u0004\u001a\u00020 ¨\u00062"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3VerticalTileConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/page/api/model/Entity;", "Lcom/amazon/music/page/api/model/Album;", "item", "Lcom/amazon/music/views/library/models/VerticalTileModel;", "convertAlbum", "Lcom/amazon/music/page/api/model/Playlist;", "convertPlaylist", "", "asin", "Lcom/amazon/music/page/api/model/Image;", ContextMappingConstants.IMAGE, "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getPlaylistMetaData", "Lcom/amazon/music/page/api/model/Station;", "convertStation", "Lcom/amazon/music/page/api/model/Track;", "", "metadataList", "convertTrack", "Lcom/amazon/music/page/api/model/Genre;", "convertGenre", "Lcom/amazon/music/page/api/model/Artist;", "convertArtist", "Lcom/amazon/music/page/api/model/Barker;", "convertBarker", "Lcom/amazon/music/page/api/model/Button;", "convertButton", "Lcom/amazon/music/page/api/model/PodcastShow;", "Lcom/amazon/podcast/views/verticalItemView/VerticalItemModel;", "convertPodcastShow", "Lcom/amazon/music/page/api/model/PodcastEpisode;", "convertPodcastEpisode", "", "contentEncodings", "removeImmersiveEncoding", "Lcom/amazon/music/page/api/model/VideoStory;", "Lcom/amazon/music/views/library/models/VideoStoryTileModel;", "convertVideoStory", "Landroid/net/Uri;", "getVideoStoryURI", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "convertPodcastShowForSeeMore", "convertPodcastEpisodeForSeeMore", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class BrushV3VerticalTileConverter implements SingleBaseModelConverter<Entity> {
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
        if (data.getPodcastEpisode() != null) {
            PodcastEpisode podcastEpisode = data.getPodcastEpisode();
            Intrinsics.checkNotNullExpressionValue(podcastEpisode, "data.podcastEpisode");
            return convertPodcastEpisode(podcastEpisode);
        }
        if (data.getPodcastShow() != null) {
            PodcastShow podcastShow = data.getPodcastShow();
            Intrinsics.checkNotNullExpressionValue(podcastShow, "data.podcastShow");
            return convertPodcastShow(podcastShow);
        }
        if (data.getVideoStory() == null) {
            return null;
        }
        VideoStory videoStory = data.getVideoStory();
        Intrinsics.checkNotNullExpressionValue(videoStory, "data.videoStory");
        return convertVideoStory(videoStory, metadataList);
    }

    private final VerticalTileModel convertAlbum(Album item) {
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
        AlbumMetadata albumMetadata = new AlbumMetadata(blockRef, ContentPlaybackUtils.INSTANCE.getAlbumUri(artist == null ? null : artist.getName(), item.getTitle(), asin), null, asin, title, src, null, asin2, name, isInLibrary, Boolean.FALSE, setConvertTiers, false, null, null, null, playmodeEligibilityConvertPlaymodeEligibility, setConvertOnDemandTiers, null, null, 847940, null);
        return new VerticalTileModel(item.getBlockRef(), item.getPrimaryTitle(), item.getSecondaryTitle(), item.getTertiaryTitle(), new ArtworkFrameModel(item.getBlockRef(), originalArtOrDefault != null ? originalArtOrDefault.getSrc() : null, 0, null, null, null, null, null, null, null, null, null, 4088, null), listRemoveImmersiveEncoding, 0, albumMetadata, false, null, null, null, null, null, null, null, null, null, null, null, AllAccessPlaylistsUtils.INSTANCE.getOnDemandBadging(albumMetadata), 1048320, null);
    }

    private final VerticalTileModel convertPlaylist(Playlist item) {
        BrushV3VerticalTileConverter brushV3VerticalTileConverter;
        Playlist playlist;
        int i;
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        List<Image> images = item.getImages();
        Intrinsics.checkNotNullExpressionValue(images, "item.images");
        Image originalArtOrDefault = ImageKt.getOriginalArtOrDefault(images);
        if (item.getAssetTypes() == null || !AssetType.VIDEO.equals(item.getAssetTypes().get(0))) {
            brushV3VerticalTileConverter = this;
            playlist = item;
            i = 1;
        } else {
            brushV3VerticalTileConverter = this;
            playlist = item;
            i = 8;
        }
        ContentMetadata playlistMetaData = brushV3VerticalTileConverter.getPlaylistMetaData(playlist, asin, originalArtOrDefault);
        String blockRef = item.getBlockRef();
        String primaryTitle = item.getPrimaryTitle();
        String secondaryTitle = item.getSecondaryTitle();
        String tertiaryTitle = item.getTertiaryTitle();
        String blockRef2 = item.getBlockRef();
        String src = originalArtOrDefault == null ? null : originalArtOrDefault.getSrc();
        IconStyleKey iconStyleKey = IconStyleKey.SONIC_RUSH_OVERLAY;
        AllAccessPlaylistsUtils allAccessPlaylistsUtils = AllAccessPlaylistsUtils.INSTANCE;
        return new VerticalTileModel(blockRef, primaryTitle, secondaryTitle, tertiaryTitle, new ArtworkFrameModel(blockRef2, src, Integer.valueOf(i), null, null, Intrinsics.areEqual(allAccessPlaylistsUtils.getAllAccessCTAPlayIcon(playlistMetaData), Boolean.TRUE) ? 0 : null, null, null, null, null, null, iconStyleKey, 2008, null), null, i, playlistMetaData, false, Boolean.valueOf(i == 8), Boolean.valueOf(i == 8), null, null, null, null, null, null, null, null, null, allAccessPlaylistsUtils.getOnDemandBadging(playlistMetaData), 1046816, null);
    }

    private final ContentMetadata getPlaylistMetaData(Playlist item, String asin, Image image) {
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility;
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility2;
        if (GetOnDemandPlaylistsService.INSTANCE.get().isOnDemandUserPlaylist(item.getAsin())) {
            String blockRef = item.getBlockRef();
            String title = item.getTitle();
            String src = image == null ? null : image.getSrc();
            Uri userPlaylistLocalUriWithoutTrack = ContentPlaybackUtils.INSTANCE.getUserPlaylistLocalUriWithoutTrack(AmazonApplication.getApplication().getApplicationContext(), asin);
            Boolean isOwned = item.getIsOwned();
            boolean zBooleanValue = isOwned == null ? false : isOwned.booleanValue();
            if (item.getPlaymodeEligibility() != null) {
                BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
                com.amazon.music.page.api.model.PlaymodeEligibility playmodeEligibility = item.getPlaymodeEligibility();
                Intrinsics.checkNotNullExpressionValue(playmodeEligibility, "item.playmodeEligibility");
                playmodeEligibilityConvertPlaymodeEligibility2 = brushConverterUtils.convertPlaymodeEligibility(playmodeEligibility);
            } else {
                playmodeEligibilityConvertPlaymodeEligibility2 = null;
            }
            long jLongValue = item.getTracksCount().longValue();
            BrushConverterUtils brushConverterUtils2 = BrushConverterUtils.INSTANCE;
            List<String> contentTiers = item.getContentTiers();
            Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
            return new UserPlaylistMetadata(blockRef, userPlaylistLocalUriWithoutTrack, null, title, src, null, Long.valueOf(jLongValue), null, zBooleanValue, asin, playmodeEligibilityConvertPlaymodeEligibility2, null, brushConverterUtils2.convertOnDemandTiers(contentTiers), false, 10404, null);
        }
        String blockRef2 = item.getBlockRef();
        String title2 = item.getTitle();
        String src2 = image == null ? null : image.getSrc();
        Boolean isInLibrary = item.getIsInLibrary();
        Boolean isOwned2 = item.getIsOwned();
        boolean zBooleanValue2 = isOwned2 == null ? false : isOwned2.booleanValue();
        BrushConverterUtils brushConverterUtils3 = BrushConverterUtils.INSTANCE;
        List<String> contentTiers2 = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers2, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils3.convertTiers(contentTiers2);
        List<String> contentTiers3 = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers3, "item.contentTiers");
        Set<SubscriptionType> setConvertOnDemandTiers = brushConverterUtils3.convertOnDemandTiers(contentTiers3);
        if (item.getPlaymodeEligibility() != null) {
            com.amazon.music.page.api.model.PlaymodeEligibility playmodeEligibility2 = item.getPlaymodeEligibility();
            Intrinsics.checkNotNullExpressionValue(playmodeEligibility2, "item.playmodeEligibility");
            playmodeEligibilityConvertPlaymodeEligibility = brushConverterUtils3.convertPlaymodeEligibility(playmodeEligibility2);
        } else {
            playmodeEligibilityConvertPlaymodeEligibility = null;
        }
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
        return new PlaylistMetadata(blockRef2, playlistUri, null, asin, title2, src2, null, null, null, null, isInLibrary, setConvertTiers, arrayList, false, null, null, null, null, zBooleanValue2, null, false, playmodeEligibilityConvertPlaymodeEligibility, setConvertOnDemandTiers, false, null, 26993604, null);
    }

    private final VerticalTileModel convertStation(Station item) {
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
        return new VerticalTileModel(item.getBlockRef(), item.getTitle(), item.getSecondaryTitle(), item.getTertiaryTitle(), new ArtworkFrameModel(item.getBlockRef(), originalArtOrDefault == null ? null : originalArtOrDefault.getSrc(), 3, null, null, 1, null, null, null, null, null, null, 4056, null), null, 3, new StationMetadata(item.getBlockRef(), ContentPlaybackUtils.INSTANCE.getStationUri(stationKey), null, stationKey, item.getTitle(), null, null, null, originalArtOrDefault != null ? originalArtOrDefault.getSrc() : null, MapsKt__MapsKt.emptyMap(), brushConverterUtils.convertTiers(contentTiers), null, null, null, 14564, null), false, null, null, null, null, null, null, null, null, null, null, null, null, 2096928, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.music.views.library.models.VerticalTileModel convertTrack(com.amazon.music.page.api.model.Track r66, java.util.List<? extends com.amazon.music.views.library.metadata.ContentMetadata> r67) {
        /*
            Method dump skipped, instruction units count: 462
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.brush.v3.converters.BrushV3VerticalTileConverter.convertTrack(com.amazon.music.page.api.model.Track, java.util.List):com.amazon.music.views.library.models.VerticalTileModel");
    }

    private final VerticalTileModel convertGenre(Genre item) {
        String id = item.getId();
        if (id == null) {
            return null;
        }
        List<Image> images = item.getImages();
        Intrinsics.checkNotNullExpressionValue(images, "item.images");
        Image originalArtOrDefault = ImageKt.getOriginalArtOrDefault(images);
        return new VerticalTileModel(item.getBlockRef(), item.getPrimaryTitle(), item.getSecondaryTitle(), item.getTertiaryTitle(), new ArtworkFrameModel(item.getBlockRef(), originalArtOrDefault == null ? null : originalArtOrDefault.getSrc(), 5, null, null, null, null, null, null, null, null, null, 4088, null), null, 5, new GenreMetadata(item.getBlockRef(), ContentPlaybackUtils.INSTANCE.getGenreUri(), null, item.getStationKey(), id, item.getTitle(), originalArtOrDefault != null ? originalArtOrDefault.getSrc() : null, null, voOSType.VOOSMP_PID_ANALYTICS_FOUNDATION_LOCATION, null), false, null, null, null, null, null, null, null, null, null, null, null, null, 2096928, null);
    }

    private final VerticalTileModel convertArtist(Artist item) {
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        List<Image> images = item.getImages();
        Intrinsics.checkNotNullExpressionValue(images, "item.images");
        Image originalArtOrDefault = ImageKt.getOriginalArtOrDefault(images);
        return new VerticalTileModel(item.getBlockRef(), item.getPrimaryTitle(), item.getSecondaryTitle(), item.getTertiaryTitle(), new ArtworkFrameModel(item.getBlockRef(), originalArtOrDefault == null ? null : originalArtOrDefault.getSrc(), 4, null, null, null, null, null, null, null, null, null, 4088, null), null, 4, new ArtistMetadata(item.getBlockRef(), ContentPlaybackUtils.INSTANCE.getArtistUri(), null, null, asin, item.getName(), null, originalArtOrDefault != null ? originalArtOrDefault.getSrc() : null, item.getContributorAsin(), null, null, false, null, null, false, null, null, false, false, null, 1048140, null), false, null, null, null, null, null, null, null, null, null, null, null, null, 2096928, null);
    }

    private final VerticalTileModel convertBarker(Barker item) {
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
        return new VerticalTileModel(blockRef, title, subTitle, null, artworkFrameModel, null, 2, new NavigationMetadata(blockRef2, barkerUri, null, target, null, null, null, 116, null), false, null, null, null, null, null, null, null, null, null, null, null, null, 2096936, null);
    }

    private final VerticalTileModel convertButton(Button item) {
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
        return new VerticalTileModel(blockRef, title, null, null, artworkFrameModel, null, 2, new NavigationMetadata(blockRef2, buttonUri, null, target, null, null, null, 116, null), false, null, null, null, null, null, null, null, null, null, null, null, null, 2096940, null);
    }

    private final VerticalItemModel convertPodcastShow(PodcastShow item) {
        String strPodcast = Deeplinks.podcast(item.getCatalogId(), item.getTitle());
        String blockRef = item.getBlockRef();
        Uri EMPTY = Uri.EMPTY;
        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
        List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        String catalogId = item.getCatalogId();
        Intrinsics.checkNotNullExpressionValue(catalogId, "item.catalogId");
        String title = item.getTitle();
        String subtitle = item.getSubtitle();
        Image image = item.getImage();
        PodcastMetadata podcastMetadata = new PodcastMetadata(blockRef, EMPTY, listEmptyList, catalogId, "", strPodcast, title, subtitle, image == null ? null : image.getSrc(), item.getCatalogId(), null, 1024, null);
        String blockRef2 = item.getBlockRef();
        String catalogId2 = item.getCatalogId();
        String title2 = item.getTitle();
        String subtitle2 = item.getSubtitle();
        Image image2 = item.getImage();
        String src = image2 == null ? null : image2.getSrc();
        String podcastShowVariantId = item.getPodcastShowVariantId();
        PodcastAvailableUpsells availableUpsells = item.getAvailableUpsells();
        PodcastPlaybackMode playbackMode = item.getPlaybackMode();
        return new VerticalItemModel(blockRef2, podcastMetadata, 12, catalogId2, "", title2, subtitle2, 0L, src, false, "", podcastShowVariantId, availableUpsells, playbackMode == null ? null : playbackMode.toString(), item.getContentTraits(), "", false);
    }

    private final VerticalItemModel convertPodcastEpisode(PodcastEpisode item) {
        String strEpisode = Deeplinks.episode(item.getPodcastShowCatalogId(), item.getCatalogId(), item.getPodcastShowTitle(), item.getTitle());
        String blockRef = item.getBlockRef();
        Uri EMPTY = Uri.EMPTY;
        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
        List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        String catalogId = item.getCatalogId();
        Intrinsics.checkNotNullExpressionValue(catalogId, "item.catalogId");
        String title = item.getTitle();
        String subtitle = item.getSubtitle();
        Image image = item.getImage();
        PodcastMetadata podcastMetadata = new PodcastMetadata(blockRef, EMPTY, listEmptyList, catalogId, "", strEpisode, title, subtitle, image == null ? null : image.getSrc(), item.getCatalogId(), null, 1024, null);
        String blockRef2 = item.getBlockRef();
        String catalogId2 = item.getCatalogId();
        String podcastShowCatalogId = item.getPodcastShowCatalogId();
        String title2 = item.getTitle();
        String subtitle2 = item.getSubtitle();
        long jLongValue = item.getDurationSeconds().longValue();
        Image image2 = item.getImage();
        String src = image2 == null ? null : image2.getSrc();
        String podcastEpisodeVariantId = item.getPodcastEpisodeVariantId();
        String podcastShowVariantId = item.getPodcastShowVariantId();
        PodcastAvailableUpsells availableUpsells = item.getAvailableUpsells();
        PodcastPlaybackMode playbackMode = item.getPlaybackMode();
        return new VerticalItemModel(blockRef2, podcastMetadata, 11, catalogId2, podcastShowCatalogId, title2, subtitle2, jLongValue, src, false, podcastEpisodeVariantId, podcastShowVariantId, availableUpsells, playbackMode != null ? playbackMode.toString() : null, item.getContentTraits(), item.getAvailabilityDate(), false);
    }

    public final BaseViewModel convertPodcastShowForSeeMore(PodcastShow item) {
        Intrinsics.checkNotNullParameter(item, "item");
        String blockRef = item.getBlockRef();
        String blockRef2 = item.getBlockRef();
        String catalogId = item.getCatalogId();
        Image image = item.getImage();
        String src = image == null ? null : image.getSrc();
        String title = item.getTitle();
        String subtitle = item.getSubtitle();
        List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        Uri EMPTY = Uri.EMPTY;
        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
        Intrinsics.checkNotNullExpressionValue(catalogId, "catalogId");
        PodcastMetadata podcastMetadata = new PodcastMetadata(blockRef2, EMPTY, listEmptyList, catalogId, "", null, title, subtitle, src, null, null, 1024, null);
        String catalogId2 = item.getCatalogId();
        String title2 = item.getTitle();
        String subtitle2 = item.getSubtitle();
        Image image2 = item.getImage();
        String src2 = image2 == null ? null : image2.getSrc();
        PodcastContentTraits contentTraits = item.getContentTraits();
        PodcastAvailableUpsells availableUpsells = item.getAvailableUpsells();
        PodcastPlaybackMode playbackMode = item.getPlaybackMode();
        return new PodcastHorizontalRowItemModel(blockRef, podcastMetadata, 12, catalogId2, title2, subtitle2, src2, contentTraits, availableUpsells, playbackMode == null ? null : playbackMode.toString(), item.getPodcastShowVariantId(), null, null, false, false, false);
    }

    public final BaseViewModel convertPodcastEpisodeForSeeMore(PodcastEpisode item) {
        Intrinsics.checkNotNullParameter(item, "item");
        String deeplink = Deeplinks.episode(item.getPodcastShowCatalogId(), item.getCatalogId(), item.getPodcastShowTitle(), item.getTitle());
        String blockRef = item.getBlockRef();
        String blockRef2 = item.getBlockRef();
        String catalogId = item.getCatalogId();
        String podcastShowCatalogId = item.getPodcastShowCatalogId();
        Image image = item.getImage();
        String src = image == null ? null : image.getSrc();
        String title = item.getTitle();
        String subtitle = item.getSubtitle();
        List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        Uri EMPTY = Uri.EMPTY;
        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
        Intrinsics.checkNotNullExpressionValue(catalogId, "catalogId");
        Intrinsics.checkNotNullExpressionValue(deeplink, "deeplink");
        PodcastMetadata podcastMetadata = new PodcastMetadata(blockRef2, EMPTY, listEmptyList, catalogId, deeplink, null, title, subtitle, src, podcastShowCatalogId, null, 1024, null);
        String catalogId2 = item.getCatalogId();
        String podcastShowCatalogId2 = item.getPodcastShowCatalogId();
        String title2 = item.getTitle();
        String subtitle2 = item.getSubtitle();
        String publishDate = item.getPublishDate();
        int iIntValue = item.getDurationSeconds().intValue();
        Image image2 = item.getImage();
        String src2 = image2 == null ? null : image2.getSrc();
        PodcastContentTraits contentTraits = item.getContentTraits();
        PodcastAvailableUpsells availableUpsells = item.getAvailableUpsells();
        PodcastPlaybackMode playbackMode = item.getPlaybackMode();
        return new EpisodeRowItemModel(blockRef, podcastMetadata, 11, catalogId2, podcastShowCatalogId2, title2, subtitle2, publishDate, iIntValue, src2, contentTraits, availableUpsells, playbackMode != null ? playbackMode.toString() : null, item.getPodcastEpisodeVariantId(), item.getPodcastShowVariantId(), item.getAvailabilityDate());
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

    private final VideoStoryTileModel convertVideoStory(VideoStory item, List<? extends ContentMetadata> metadataList) {
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        Image images = item.getImages();
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        int millis = (int) (item.getDuration() != null ? TimeUnit.SECONDS.toMillis(item.getDuration().longValueExact()) : 0L);
        String blockRef = item.getBlockRef();
        String primaryTitle = item.getPrimaryTitle();
        String secondaryTitle = item.getSecondaryTitle();
        ArtworkFrameModel artworkFrameModel = new ArtworkFrameModel(item.getBlockRef(), images == null ? null : images.getSrc(), 14, null, null, null, null, null, null, null, null, null, 4088, null);
        String blockRef2 = item.getBlockRef();
        String title = item.getTitle();
        String blockRef3 = item.getBlockRef();
        String src = images != null ? images.getSrc() : null;
        List<AssetQuality> assetQualities = item.getAssetQualities();
        Intrinsics.checkNotNullExpressionValue(assetQualities, "item.assetQualities");
        List<com.amazon.music.models.AssetQuality> listConvertAssetQualityPage = brushConverterUtils.convertAssetQualityPage(assetQualities);
        ParentalControls parentalControls = item.getParentalControls();
        boolean zAreEqual = parentalControls == null ? false : Intrinsics.areEqual(parentalControls.getHasExplicitLanguage(), Boolean.TRUE);
        String asin2 = item.getAsin();
        Intrinsics.checkNotNullExpressionValue(asin2, "item.asin");
        return new VideoStoryTileModel(blockRef, primaryTitle, secondaryTitle, null, artworkFrameModel, null, 14, new VideoStoryMetadata(blockRef2, getVideoStoryURI(asin2), null, asin, title, blockRef3, src, setConvertTiers, zAreEqual, metadataList, listConvertAssetQualityPage, millis, null, null, 12292, null), null, asin, null, 0, 3368, null);
    }

    private final Uri getVideoStoryURI(String asin) {
        Uri uri = Uri.parse(HttpUrl.INSTANCE.get("https://music.amazon.com").newBuilder().addEncodedPathSegment("videos").addPathSegment(ParserUtil.STORY_ID_QUERY_PARAM_NAME).addPathSegment(asin).build().uri().toString());
        Intrinsics.checkNotNullExpressionValue(uri, "parse(BASEURL.toHttpUrl(…ild().toUri().toString())");
        return uri;
    }
}
