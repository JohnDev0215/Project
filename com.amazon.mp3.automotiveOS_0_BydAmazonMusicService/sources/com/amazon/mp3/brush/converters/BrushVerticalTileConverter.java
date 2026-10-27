package com.amazon.mp3.brush.converters;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.brush.AllAccessPlaylistsUtils;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.merch.MerchMshopCampaign;
import com.amazon.mp3.merch.MerchUriUtils;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.ContentAccessType;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.GenreMetadata;
import com.amazon.music.views.library.metadata.LivestreamMetadata;
import com.amazon.music.views.library.metadata.MerchMetadata;
import com.amazon.music.views.library.metadata.NavigationMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.FollowTileModel;
import com.amazon.music.views.library.models.VerticalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Artist;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.FeaturedBarker;
import com.amazon.musicensembleservice.brush.Genre;
import com.amazon.musicensembleservice.brush.Image;
import com.amazon.musicensembleservice.brush.LiveStream;
import com.amazon.musicensembleservice.brush.Merch;
import com.amazon.musicensembleservice.brush.ParentalControls;
import com.amazon.musicensembleservice.brush.Playlist;
import com.amazon.musicensembleservice.brush.PodcastEpisode;
import com.amazon.musicensembleservice.brush.PodcastShow;
import com.amazon.musicensembleservice.brush.Station;
import com.amazon.musicensembleservice.brush.StreamProvider;
import com.amazon.musicensembleservice.brush.Track;
import com.amazon.musicensembleservice.brush.UserPlaylist;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemModel;
import com.amazon.podcast.views.horizontalRowItemsListView.PodcastHorizontalRowItemModel;
import com.visualon.OSMPUtils.voOSType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: BrushVerticalTileConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\b\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b6\u00107J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0007H\u0002J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\tH\u0002J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u000bH\u0002J\"\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\r2\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eH\u0002J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0012H\u0002J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0014H\u0002J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0016H\u0002JC\u0010!\u001a\u00020 2\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u00182\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001eH\u0002¢\u0006\u0004\b!\u0010\"J\u0012\u0010$\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020#H\u0002J\u0012\u0010&\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020%H\u0002J\u0012\u0010(\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020'H\u0002J\u0012\u0010+\u001a\u0004\u0018\u00010*2\u0006\u0010\u0004\u001a\u00020)H\u0002J\u001b\u0010,\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0004\b,\u0010-J\n\u0010/\u001a\u0004\u0018\u00010.H\u0002J \u00102\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u000e2\u000e\u00101\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u000100H\u0002J\u0010\u00104\u001a\u0004\u0018\u00010*2\u0006\u00103\u001a\u00020\u0002J\"\u00104\u001a\u0004\u0018\u00010*2\u0006\u00103\u001a\u00020\u00022\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eH\u0016J\u0010\u00105\u001a\u0004\u0018\u00010*2\u0006\u0010\u0004\u001a\u00020'¨\u00068"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushVerticalTileConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/musicensembleservice/brush/Entity;", "Lcom/amazon/musicensembleservice/brush/Album;", "item", "Lcom/amazon/music/views/library/models/VerticalTileModel;", "convertAlbum", "Lcom/amazon/musicensembleservice/brush/Merch;", "convertMerch", "Lcom/amazon/musicensembleservice/brush/Playlist;", "convertPlaylist", "Lcom/amazon/musicensembleservice/brush/Station;", "convertStation", "Lcom/amazon/musicensembleservice/brush/Track;", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadataList", "convertTrack", "Lcom/amazon/musicensembleservice/brush/Genre;", "convertGenre", "Lcom/amazon/musicensembleservice/brush/Artist;", "convertArtist", "Lcom/amazon/musicensembleservice/brush/UserPlaylist;", "convertUserPlaylist", "", "blockRef", "imageUrl", "", "contentType", "contentMetadata", "", "allAccessCTAIcon", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "createArtworkFrameModel", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/lang/Boolean;)Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "Lcom/amazon/musicensembleservice/brush/FeaturedBarker;", "convertFeaturedBarker", "Lcom/amazon/musicensembleservice/brush/LiveStream;", "convertLivestream", "Lcom/amazon/musicensembleservice/brush/PodcastShow;", "convertPodcastShow", "Lcom/amazon/musicensembleservice/brush/PodcastEpisode;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convertPodcastEpisode", "getPlayIconType", "(Ljava/lang/Integer;)Ljava/lang/Integer;", "Lcom/amazon/music/views/library/styles/keys/IconStyleKey;", "getPlayIconStyleKey", "", "contentEncodings", "removeImmersiveEncoding", "data", "convert", "convertPodcastShowForSeeMore", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class BrushVerticalTileConverter implements SingleBaseModelConverter<Entity> {
    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public final BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return convert(data, null);
    }

    public BaseViewModel convert(Entity data, List<? extends ContentMetadata> metadataList) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof Album) {
            return convertAlbum((Album) data);
        }
        if (data instanceof Playlist) {
            return convertPlaylist((Playlist) data);
        }
        if (data instanceof Station) {
            return convertStation((Station) data);
        }
        if (data instanceof Track) {
            return convertTrack((Track) data, metadataList);
        }
        if (data instanceof Genre) {
            return convertGenre((Genre) data);
        }
        if (data instanceof Artist) {
            return convertArtist((Artist) data);
        }
        if (data instanceof UserPlaylist) {
            return convertUserPlaylist((UserPlaylist) data);
        }
        if (data instanceof FeaturedBarker) {
            return convertFeaturedBarker((FeaturedBarker) data);
        }
        if (data instanceof LiveStream) {
            return convertLivestream((LiveStream) data);
        }
        if (data instanceof PodcastShow) {
            return convertPodcastShow((PodcastShow) data);
        }
        if (data instanceof PodcastEpisode) {
            return convertPodcastEpisode((PodcastEpisode) data);
        }
        if (data instanceof Merch) {
            return convertMerch((Merch) data);
        }
        return null;
    }

    private final VerticalTileModel convertAlbum(Album item) {
        String primaryTitle;
        String asin = item.getAsin();
        Image image = item.getImage();
        String url = image == null ? null : image.getUrl();
        List<Artist> artists = item.getArtists();
        Artist artist = artists == null ? null : (Artist) CollectionsKt___CollectionsKt.first((List) artists);
        List<String> listRemoveImmersiveEncoding = removeImmersiveEncoding(item.getContentEncoding());
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        String asin2 = artist == null ? null : artist.getAsin();
        String name = artist == null ? null : artist.getName();
        Boolean boolIsIsInLibrary = item.isIsInLibrary();
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        List<String> contentTiers2 = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers2, "item.contentTiers");
        Set<SubscriptionType> setConvertOnDemandTiers = brushConverterUtils.convertOnDemandTiers(contentTiers2);
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = BrushConverterUtils.convertPlaymodeEligibility(item.getPlaymodeEligibility());
        ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
        String title2 = item.getTitle();
        String name2 = artist != null ? artist.getName() : null;
        Intrinsics.checkNotNullExpressionValue(asin, "asin");
        AlbumMetadata albumMetadata = new AlbumMetadata(blockRef, contentPlaybackUtils.getAlbumUri(name2, title2, asin), null, asin, title, url, null, asin2, name, boolIsIsInLibrary, Boolean.FALSE, setConvertTiers, false, null, null, null, playmodeEligibilityConvertPlaymodeEligibility, setConvertOnDemandTiers, null, null, 847940, null);
        ChildUserUtil childUserUtil = ChildUserUtil.INSTANCE;
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
        if (childUserUtil.isChildUser(applicationContext)) {
            primaryTitle = StringUtil.removeExplicitLabelFromAlbumOrTrackTitle(item.getPrimaryTitle());
        } else {
            primaryTitle = item.getPrimaryTitle();
        }
        return new VerticalTileModel(item.getBlockRef(), primaryTitle, item.getSecondaryTitle(), item.getTertiaryTitle(), createArtworkFrameModel$default(this, item.getBlockRef(), url, 0, albumMetadata, null, 16, null), listRemoveImmersiveEncoding, 0, albumMetadata, false, null, null, null, null, null, null, null, null, null, null, item.getLabel(), AllAccessPlaylistsUtils.INSTANCE.getOnDemandBadging(albumMetadata), 524032, null);
    }

    private final VerticalTileModel convertMerch(Merch item) {
        String asin = item.getAsin();
        Image image = item.getImage();
        String url = image == null ? null : image.getUrl();
        String blockRef = item.getBlockRef();
        MerchUriUtils merchUriUtils = MerchUriUtils.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(asin, "asin");
        String artistAsin = item.getArtistAsin();
        Intrinsics.checkNotNullExpressionValue(artistAsin, "item.artistAsin");
        Uri merchUri$default = MerchUriUtils.getMerchUri$default(merchUriUtils, asin, artistAsin, null, 4, null);
        String artistAsin2 = item.getArtistAsin();
        MerchMshopCampaign merchMshopCampaign = MerchMshopCampaign.LISTING;
        String artistAsin3 = item.getArtistAsin();
        Intrinsics.checkNotNullExpressionValue(artistAsin3, "item.artistAsin");
        MerchMetadata merchMetadata = new MerchMetadata(blockRef, merchUri$default, null, asin, artistAsin2, merchUriUtils.getRefMarker(merchMshopCampaign, artistAsin3), null, 68, null);
        return new VerticalTileModel(item.getBlockRef(), item.getPrimaryTitle(), item.getSecondaryTitle(), null, createArtworkFrameModel$default(this, item.getBlockRef(), url, 13, merchMetadata, null, 16, null), null, 13, merchMetadata, false, null, null, null, null, null, null, null, null, null, null, item.getTertiaryTitle(), null, 1572648, null);
    }

    private final VerticalTileModel convertPlaylist(Playlist item) {
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        Image image = item.getImage();
        String url = image == null ? null : image.getUrl();
        List<String> contentTypes = item.getContentTypes();
        int i = Intrinsics.areEqual(contentTypes != null ? (String) CollectionsKt___CollectionsKt.first((List) contentTypes) : null, "VIDEO") ? 8 : 1;
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        Boolean boolIsIsInLibrary = item.isIsInLibrary();
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        List<String> contentTiers2 = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers2, "item.contentTiers");
        Set<SubscriptionType> setConvertOnDemandTiers = brushConverterUtils.convertOnDemandTiers(contentTiers2);
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = BrushConverterUtils.convertPlaymodeEligibility(item.getPlaymodeEligibility());
        Boolean boolIsIsOwned = item.isIsOwned();
        boolean zBooleanValue = boolIsIsOwned == null ? false : boolIsIsOwned.booleanValue();
        Uri playlistUri = ContentPlaybackUtils.INSTANCE.getPlaylistUri(asin);
        List<String> contentTypes2 = item.getContentTypes();
        if (contentTypes2 == null) {
            contentTypes2 = CollectionsKt__CollectionsJVMKt.listOf("AUDIO");
        }
        int i2 = i;
        PlaylistMetadata playlistMetadata = new PlaylistMetadata(blockRef, playlistUri, null, asin, title, url, null, null, null, null, boolIsIsInLibrary, setConvertTiers, contentTypes2, false, null, null, null, null, zBooleanValue, null, false, playmodeEligibilityConvertPlaymodeEligibility, setConvertOnDemandTiers, false, null, 26993604, null);
        String blockRef2 = item.getBlockRef();
        String primaryTitle = item.getPrimaryTitle();
        String secondaryTitle = item.getSecondaryTitle();
        String tertiaryTitle = item.getTertiaryTitle();
        String blockRef3 = item.getBlockRef();
        Integer numValueOf = Integer.valueOf(i2);
        AllAccessPlaylistsUtils allAccessPlaylistsUtils = AllAccessPlaylistsUtils.INSTANCE;
        return new VerticalTileModel(blockRef2, primaryTitle, secondaryTitle, tertiaryTitle, createArtworkFrameModel(blockRef3, url, numValueOf, playlistMetadata, allAccessPlaylistsUtils.getAllAccessCTAPlayIcon(playlistMetadata)), null, i2, playlistMetadata, false, Boolean.valueOf(i2 == 8), Boolean.TRUE, null, null, null, null, null, null, null, null, item.getLabel(), allAccessPlaylistsUtils.getOnDemandBadging(playlistMetadata), 522528, null);
    }

    private final VerticalTileModel convertStation(Station item) {
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
        return new VerticalTileModel(item.getBlockRef(), item.getPrimaryTitle(), item.getSecondaryTitle(), item.getTertiaryTitle(), createArtworkFrameModel$default(this, item.getBlockRef(), url, 3, stationMetadata, null, 16, null), null, 3, stationMetadata, false, null, null, null, null, null, null, null, null, null, null, item.getLabel(), null, 1572640, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ef  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.music.views.library.models.VerticalTileModel convertTrack(com.amazon.musicensembleservice.brush.Track r67, java.util.List<? extends com.amazon.music.views.library.metadata.ContentMetadata> r68) {
        /*
            Method dump skipped, instruction units count: 429
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.brush.converters.BrushVerticalTileConverter.convertTrack(com.amazon.musicensembleservice.brush.Track, java.util.List):com.amazon.music.views.library.models.VerticalTileModel");
    }

    private final VerticalTileModel convertGenre(Genre item) {
        if (item.getBrowseId() == null) {
            return null;
        }
        Image image = item.getImage();
        String url = image != null ? image.getUrl() : null;
        String blockRef = item.getBlockRef();
        String browseId = item.getBrowseId();
        String title = item.getTitle();
        Uri genreUri = ContentPlaybackUtils.INSTANCE.getGenreUri();
        Intrinsics.checkNotNullExpressionValue(browseId, "browseId");
        GenreMetadata genreMetadata = new GenreMetadata(blockRef, genreUri, null, null, browseId, title, url, null, voOSType.VOOSMP_PID_ANALYTICS_EXPORT_GET_PACKET, null);
        return new VerticalTileModel(item.getBlockRef(), item.getPrimaryTitle(), item.getSecondaryTitle(), item.getTertiaryTitle(), createArtworkFrameModel$default(this, item.getBlockRef(), url, 5, genreMetadata, null, 16, null), null, 5, genreMetadata, false, null, null, null, null, null, null, null, null, null, null, item.getLabel(), null, 1572640, null);
    }

    private final VerticalTileModel convertArtist(Artist item) {
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        Image image = item.getImage();
        String url = image != null ? image.getUrl() : null;
        String blockRef = item.getBlockRef();
        String name = item.getName();
        String contributorAsin = item.getContributorAsin();
        Uri artistUri = ContentPlaybackUtils.INSTANCE.getArtistUri();
        Boolean boolIsIsFollowing = item.isIsFollowing();
        ArtistMetadata artistMetadata = new ArtistMetadata(blockRef, artistUri, null, null, asin, name, null, url, contributorAsin, null, null, boolIsIsFollowing == null ? false : boolIsIsFollowing.booleanValue(), null, null, false, null, null, false, false, null, 1046092, null);
        if (item.getRelationMetadata() != null) {
            Boolean boolIsIsSuggested = item.getRelationMetadata().isIsSuggested();
            Intrinsics.checkNotNullExpressionValue(boolIsIsSuggested, "item.relationMetadata.isIsSuggested");
            if (boolIsIsSuggested.booleanValue()) {
                return new FollowTileModel(item.getBlockRef(), item.getPrimaryTitle(), item.getSecondaryTitle(), item.getTertiaryTitle(), createArtworkFrameModel$default(this, item.getBlockRef(), url, 4, artistMetadata, null, 16, null), null, 4, artistMetadata, false, 288, null);
            }
        }
        return new VerticalTileModel(item.getBlockRef(), item.getPrimaryTitle(), item.getSecondaryTitle(), item.getTertiaryTitle(), createArtworkFrameModel$default(this, item.getBlockRef(), url, 4, artistMetadata, null, 16, null), null, 4, artistMetadata, false, null, null, null, null, null, null, null, null, null, null, item.getLabel(), null, 1572640, null);
    }

    private final VerticalTileModel convertUserPlaylist(UserPlaylist item) {
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
        String title = item.getTitle();
        String description = item.getDescription();
        Long tracksCount = item.getTracksCount();
        Boolean isIsOwned = item.isIsOwned();
        Uri userPlaylistUri = ContentPlaybackUtils.INSTANCE.getUserPlaylistUri();
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = BrushConverterUtils.convertPlaymodeEligibility(item.getPlaymodeEligibility());
        Intrinsics.checkNotNullExpressionValue(isIsOwned, "isIsOwned");
        UserPlaylistMetadata userPlaylistMetadata = new UserPlaylistMetadata(blockRef, userPlaylistUri, null, title, url, description, tracksCount, null, isIsOwned.booleanValue(), playlistId, playmodeEligibilityConvertPlaymodeEligibility, null, null, false, 14468, null);
        String blockRef2 = item.getBlockRef();
        String primaryTitle = item.getPrimaryTitle();
        String secondaryTitle = item.getSecondaryTitle();
        String tertiaryTitle = item.getTertiaryTitle();
        String blockRef3 = item.getBlockRef();
        AllAccessPlaylistsUtils allAccessPlaylistsUtils = AllAccessPlaylistsUtils.INSTANCE;
        return new VerticalTileModel(blockRef2, primaryTitle, secondaryTitle, tertiaryTitle, createArtworkFrameModel(blockRef3, url, 1, userPlaylistMetadata, allAccessPlaylistsUtils.getAllAccessCTAPlayIcon(userPlaylistMetadata)), null, 1, userPlaylistMetadata, false, null, null, null, null, null, null, null, null, null, null, item.getLabel(), allAccessPlaylistsUtils.getOnDemandBadging(userPlaylistMetadata), 524064, null);
    }

    static /* synthetic */ ArtworkFrameModel createArtworkFrameModel$default(BrushVerticalTileConverter brushVerticalTileConverter, String str, String str2, Integer num, ContentMetadata contentMetadata, Boolean bool, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createArtworkFrameModel");
        }
        if ((i & 16) != 0) {
            bool = Boolean.FALSE;
        }
        return brushVerticalTileConverter.createArtworkFrameModel(str, str2, num, contentMetadata, bool);
    }

    private final ArtworkFrameModel createArtworkFrameModel(String blockRef, String imageUrl, Integer contentType, ContentMetadata contentMetadata, Boolean allAccessCTAIcon) {
        return new ArtworkFrameModel(blockRef, imageUrl, contentType, null, null, Intrinsics.areEqual(allAccessCTAIcon, Boolean.TRUE) ? 0 : getPlayIconType(contentType), contentMetadata, null, null, null, null, getPlayIconStyleKey(), 1944, null);
    }

    private final VerticalTileModel convertFeaturedBarker(FeaturedBarker item) {
        String target = item.getTarget();
        String url = null;
        if (target == null) {
            return null;
        }
        List<Image> images = item.getImages();
        if (images != null) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : images) {
                Image image = (Image) obj;
                if (StringsKt__StringsJVMKt.equals$default(image == null ? null : image.getType(), "ORIGINAL_ART", false, 2, null)) {
                    arrayList.add(obj);
                }
            }
            Image image2 = (Image) CollectionsKt___CollectionsKt.first((List) arrayList);
            if (image2 != null) {
                url = image2.getUrl();
            }
        }
        String str = url;
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        String subtitle = item.getSubtitle();
        ArtworkFrameModel artworkFrameModelCreateArtworkFrameModel$default = createArtworkFrameModel$default(this, item.getBlockRef(), str, null, null, null, 16, null);
        String blockRef2 = item.getBlockRef();
        Uri barkerUri = ContentPlaybackUtils.INSTANCE.getBarkerUri();
        Intrinsics.checkNotNullExpressionValue(target, "target");
        return new VerticalTileModel(blockRef, title, subtitle, null, artworkFrameModelCreateArtworkFrameModel$default, null, 2, new NavigationMetadata(blockRef2, barkerUri, null, target, null, null, null, 116, null), false, null, null, null, null, null, null, null, null, null, null, null, null, 2096936, null);
    }

    private final VerticalTileModel convertLivestream(LiveStream item) {
        ArtistMetadata artistMetadata;
        String url;
        List<Artist> artists = item.getArtists();
        if (artists == null || artists.size() <= 0) {
            artistMetadata = null;
        } else {
            String blockRef = item.getBlockRef();
            String asin = ((Artist) CollectionsKt___CollectionsKt.first((List) artists)).getAsin();
            String primaryTitle = ((Artist) CollectionsKt___CollectionsKt.first((List) artists)).getPrimaryTitle();
            Image image = ((Artist) CollectionsKt___CollectionsKt.first((List) artists)).getImage();
            artistMetadata = new ArtistMetadata(blockRef, ContentPlaybackUtils.INSTANCE.getArtistUri(), null, null, asin, primaryTitle, null, image == null ? null : image.getUrl(), null, null, null, false, null, null, false, null, null, false, false, null, 1048396, null);
        }
        StreamProvider streamProvider = item.getStreamProvider();
        if (streamProvider == null || streamProvider.getWordmarkImages() == null || streamProvider.getWordmarkImages().size() <= 0) {
            url = null;
        } else {
            List<Image> wordmarkImages = streamProvider.getWordmarkImages();
            Intrinsics.checkNotNullExpressionValue(wordmarkImages, "streamProvider.wordmarkImages");
            url = ((Image) CollectionsKt___CollectionsKt.first((List) wordmarkImages)).getUrl();
        }
        if (item.getPrimaryTitle() == null) {
            return null;
        }
        List<Image> images = item.getImages();
        Intrinsics.checkNotNullExpressionValue(images, "item.images");
        String url2 = ((Image) CollectionsKt___CollectionsKt.first((List) images)).getUrl();
        String blockRef2 = item.getBlockRef();
        String primaryTitle2 = item.getPrimaryTitle();
        String secondaryTitle = item.getSecondaryTitle();
        String tertiaryTitle = item.getTertiaryTitle();
        ArtworkFrameModel artworkFrameModelCreateArtworkFrameModel$default = createArtworkFrameModel$default(this, item.getBlockRef(), url2, null, null, null, 16, null);
        String viewers = item.getViewers();
        String blockRef3 = item.getBlockRef();
        String id = item.getId();
        Uri livestreamUri = ContentPlaybackUtils.INSTANCE.getLivestreamUri();
        Intrinsics.checkNotNullExpressionValue(id, "id");
        LivestreamMetadata livestreamMetadata = new LivestreamMetadata(blockRef3, livestreamUri, null, id, 4, null);
        String label = item.getLabel();
        Boolean bool = Boolean.TRUE;
        return new VerticalTileModel(blockRef2, primaryTitle2, secondaryTitle, tertiaryTitle, artworkFrameModelCreateArtworkFrameModel$default, null, 9, livestreamMetadata, false, bool, null, viewers, artistMetadata, url, bool, null, null, null, null, label, null, 1541408, null);
    }

    private final VerticalTileModel convertPodcastShow(PodcastShow item) {
        String deeplink = Deeplinks.podcast(item.getCatalogId(), item.getTitle());
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        String subtitle = item.getSubtitle();
        ArtworkFrameModel artworkFrameModel = new ArtworkFrameModel(null, item.getImage().getUrl(), null, null, null, null, null, null, null, null, null, null, 3993, null);
        String blockRef2 = item.getBlockRef();
        Uri barkerUri = ContentPlaybackUtils.INSTANCE.getBarkerUri();
        Intrinsics.checkNotNullExpressionValue(deeplink, "deeplink");
        return new VerticalTileModel(blockRef, title, subtitle, null, artworkFrameModel, null, 2, new NavigationMetadata(blockRef2, barkerUri, null, deeplink, null, null, null, 116, null), false, null, null, null, null, null, null, Boolean.TRUE, null, null, null, item.getLabel(), null, 1539880, null);
    }

    private final BaseViewModel convertPodcastEpisode(PodcastEpisode item) {
        String blockRef = item.getBlockRef();
        String blockRef2 = item.getBlockRef();
        String catalogId = item.getCatalogId();
        String podcastShowCatalogId = item.getPodcastShowCatalogId();
        Image image = item.getImage();
        String url = image == null ? null : image.getUrl();
        String title = item.getTitle();
        String subtitle = item.getSubtitle();
        List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        Uri EMPTY = Uri.EMPTY;
        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
        Intrinsics.checkNotNullExpressionValue(catalogId, "catalogId");
        PodcastMetadata podcastMetadata = new PodcastMetadata(blockRef2, EMPTY, listEmptyList, catalogId, "", null, title, subtitle, url, podcastShowCatalogId, null, 1024, null);
        String catalogId2 = item.getCatalogId();
        String podcastShowCatalogId2 = item.getPodcastShowCatalogId();
        String title2 = item.getTitle();
        String subtitle2 = item.getSubtitle();
        String publishDate = item.getPublishDate();
        int iLongValue = (int) item.getDurationSeconds().longValue();
        Image image2 = item.getImage();
        return new EpisodeRowItemModel(blockRef, podcastMetadata, 11, catalogId2, podcastShowCatalogId2, title2, subtitle2, publishDate, iLongValue, image2 == null ? null : image2.getUrl(), item.getContentTraits(), item.getAvailableUpsells(), item.getPlaybackMode(), item.getPodcastEpisodeVariantId(), item.getPodcastShowVariantId(), item.getAvailabilityDate());
    }

    public final BaseViewModel convertPodcastShowForSeeMore(PodcastShow item) {
        Intrinsics.checkNotNullParameter(item, "item");
        String blockRef = item.getBlockRef();
        String blockRef2 = item.getBlockRef();
        String catalogId = item.getCatalogId();
        Image image = item.getImage();
        String url = image == null ? null : image.getUrl();
        String title = item.getTitle();
        String subtitle = item.getSubtitle();
        List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        Uri EMPTY = Uri.EMPTY;
        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
        Intrinsics.checkNotNullExpressionValue(catalogId, "catalogId");
        PodcastMetadata podcastMetadata = new PodcastMetadata(blockRef2, EMPTY, listEmptyList, catalogId, "", null, title, subtitle, url, null, null, 1024, null);
        String catalogId2 = item.getCatalogId();
        String title2 = item.getTitle();
        String subtitle2 = item.getSubtitle();
        Image image2 = item.getImage();
        return new PodcastHorizontalRowItemModel(blockRef, podcastMetadata, 12, catalogId2, title2, subtitle2, image2 == null ? null : image2.getUrl(), item.getContentTraits(), item.getAvailableUpsells(), item.getPlaybackMode(), item.getPodcastShowVariantId(), null, null, false, false, false);
    }

    private final Integer getPlayIconType(Integer contentType) {
        if (contentType != null && contentType.intValue() == 3) {
            return 1;
        }
        return (contentType != null && contentType.intValue() == 2) ? 0 : null;
    }

    private final IconStyleKey getPlayIconStyleKey() {
        if (Feature.sonic_rush.isEnabled(Feature.album_detail_performance_improvements_phase1B.isEnabled(true))) {
            return IconStyleKey.SONIC_RUSH_OVERLAY;
        }
        return null;
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
