package com.amazon.mp3.brush.converters;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.music.ContentAccessType;
import com.amazon.music.alps.config.URLConfig;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.LiveEventMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.models.FeaturePlayModel;
import com.amazon.music.views.library.models.PodcastFeaturePlayModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Artist;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.FeaturedPlay;
import com.amazon.musicensembleservice.brush.Image;
import com.amazon.musicensembleservice.brush.LiveEvent;
import com.amazon.musicensembleservice.brush.ParentalControls;
import com.amazon.musicensembleservice.brush.Playlist;
import com.amazon.musicensembleservice.brush.PodcastEpisode;
import com.amazon.musicensembleservice.brush.PodcastShow;
import com.amazon.musicensembleservice.brush.Station;
import com.amazon.podcast.deeplinks.Deeplinks;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: BrushFeaturedPlayConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u001d\u0010\u001eJ\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u001a\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u001a\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u000bH\u0002J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\rH\u0002J\u001a\u0010\u0010\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u000fH\u0002J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0011H\u0002J\u001a\u0010\u0015\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0014H\u0002J\u0018\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00162\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0002H\u0016R\u0016\u0010\u001b\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushFeaturedPlayConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/musicensembleservice/brush/Entity;", "Lcom/amazon/musicensembleservice/brush/FeaturedPlay;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convertFeaturedPlay", "Lcom/amazon/musicensembleservice/brush/Station;", "entity", "Lcom/amazon/music/views/library/models/FeaturePlayModel;", "convertStation", "Lcom/amazon/musicensembleservice/brush/Playlist;", "convertPlaylist", "Lcom/amazon/musicensembleservice/brush/Album;", "convertAlbum", "Lcom/amazon/musicensembleservice/brush/LiveEvent;", "convertLiveEvent", "Lcom/amazon/musicensembleservice/brush/PodcastEpisode;", "Lcom/amazon/music/views/library/models/PodcastFeaturePlayModel;", "convertPodcastEpisode", "Lcom/amazon/musicensembleservice/brush/PodcastShow;", "convertPodcastShow", "", "", "filterImages", "convert", "", "isFacetedNavUX", "Z", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushFeaturedPlayConverter implements SingleBaseModelConverter<Entity> {
    private boolean isFacetedNavUX;

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.isFacetedNavUX = AmazonApplication.getCapabilities().isFacetedNavigationEnabled();
        if (data instanceof FeaturedPlay) {
            return convertFeaturedPlay((FeaturedPlay) data);
        }
        return null;
    }

    private final BaseViewModel convertFeaturedPlay(FeaturedPlay data) {
        Entity entity = data.getEntity();
        if (entity instanceof Station) {
            return convertStation(data, (Station) entity);
        }
        if (entity instanceof Playlist) {
            return convertPlaylist(data, (Playlist) entity);
        }
        if (entity instanceof Album) {
            return convertAlbum(data, (Album) entity);
        }
        if (entity instanceof PodcastEpisode) {
            return convertPodcastEpisode(data, (PodcastEpisode) entity);
        }
        if (entity instanceof PodcastShow) {
            return convertPodcastShow(data, (PodcastShow) entity);
        }
        if (entity instanceof LiveEvent) {
            return convertLiveEvent(data, (LiveEvent) entity);
        }
        return null;
    }

    private final FeaturePlayModel convertStation(FeaturedPlay data, Station entity) {
        String stationKey;
        String stationKey2 = entity.getStationKey();
        Intrinsics.checkNotNullExpressionValue(stationKey2, "entity.stationKey");
        List listSplit$default = StringsKt__StringsKt.split$default((CharSequence) stationKey2, new char[]{'_'}, false, 0, 6, (Object) null);
        if (listSplit$default.size() >= 2) {
            stationKey = (String) listSplit$default.get(1);
        } else {
            stationKey = entity.getStationKey();
        }
        String key = stationKey;
        List<String> listFilterImages = filterImages(data);
        if (data.getHeader() == null) {
            return null;
        }
        String blockRef = data.getBlockRef();
        String header = data.getHeader();
        String primaryTitle = data.getPrimaryTitle();
        String secondaryTitle = data.getSecondaryTitle();
        String tertiaryTitle = data.getTertiaryTitle();
        String altDescription = data.getAltDescription();
        String str = listFilterImages == null ? null : (String) CollectionsKt___CollectionsKt.first((List) listFilterImages);
        String label = data.getLabel();
        boolean z = this.isFacetedNavUX;
        String blockRef2 = data.getBlockRef();
        String primaryTitle2 = data.getPrimaryTitle();
        String str2 = listFilterImages != null ? (String) CollectionsKt___CollectionsKt.first((List) listFilterImages) : null;
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = entity.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "entity.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        Map<String, ParentalControls> parentalControlsPerContentTier = entity.getParentalControlsPerContentTier();
        Intrinsics.checkNotNullExpressionValue(parentalControlsPerContentTier, "entity.parentalControlsPerContentTier");
        Map<ContentAccessType, Boolean> mapConvertExplicitMap = brushConverterUtils.convertExplicitMap(parentalControlsPerContentTier);
        ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(key, "key");
        StationMetadata stationMetadata = new StationMetadata(blockRef2, contentPlaybackUtils.getStationUri(key), null, key, primaryTitle2, null, null, null, str2, mapConvertExplicitMap, setConvertTiers, data.getPageToken(), null, data.isStartNewQueue(), 4324, null);
        Intrinsics.checkNotNullExpressionValue(header, "header");
        return new FeaturePlayModel(blockRef, null, header, primaryTitle, secondaryTitle, tertiaryTitle, str, altDescription, 3, label, stationMetadata, null, false, null, z, listFilterImages, 14338, null);
    }

    private final FeaturePlayModel convertPlaylist(FeaturedPlay data, Playlist entity) {
        List<String> listFilterImages = filterImages(data);
        if (data.getHeader() == null) {
            return null;
        }
        String blockRef = data.getBlockRef();
        String header = data.getHeader();
        String primaryTitle = data.getPrimaryTitle();
        String secondaryTitle = data.getSecondaryTitle();
        String tertiaryTitle = data.getTertiaryTitle();
        String altDescription = data.getAltDescription();
        String str = listFilterImages == null ? null : (String) CollectionsKt___CollectionsKt.first((List) listFilterImages);
        String label = data.getLabel();
        boolean z = this.isFacetedNavUX;
        String blockRef2 = data.getBlockRef();
        String asin = entity.getAsin();
        String primaryTitle2 = data.getPrimaryTitle();
        String str2 = listFilterImages != null ? (String) CollectionsKt___CollectionsKt.first((List) listFilterImages) : null;
        Boolean boolIsIsInLibrary = entity.isIsInLibrary();
        Boolean boolIsIsOwned = entity.isIsOwned();
        boolean zBooleanValue = boolIsIsOwned == null ? false : boolIsIsOwned.booleanValue();
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = entity.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "entity.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        List<String> contentTiers2 = entity.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers2, "entity.contentTiers");
        Set<SubscriptionType> setConvertOnDemandTiers = brushConverterUtils.convertOnDemandTiers(contentTiers2);
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = BrushConverterUtils.convertPlaymodeEligibility(entity.getPlaymodeEligibility());
        ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
        String asin2 = entity.getAsin();
        Intrinsics.checkNotNullExpressionValue(asin2, "entity.asin");
        Uri playlistUri = contentPlaybackUtils.getPlaylistUri(asin2);
        List<String> contentTypes = entity.getContentTypes();
        if (contentTypes == null) {
            contentTypes = CollectionsKt__CollectionsJVMKt.listOf("AUDIO");
        }
        PlaylistMetadata playlistMetadata = new PlaylistMetadata(blockRef2, playlistUri, null, asin, primaryTitle2, str2, null, null, null, null, boolIsIsInLibrary, setConvertTiers, contentTypes, false, null, null, null, null, zBooleanValue, null, false, playmodeEligibilityConvertPlaymodeEligibility, setConvertOnDemandTiers, false, null, 26993604, null);
        Intrinsics.checkNotNullExpressionValue(header, "header");
        return new FeaturePlayModel(blockRef, null, header, primaryTitle, secondaryTitle, tertiaryTitle, str, altDescription, 1, label, playlistMetadata, null, false, null, z, listFilterImages, 14338, null);
    }

    private final FeaturePlayModel convertAlbum(FeaturedPlay data, Album entity) {
        List<String> listFilterImages = filterImages(data);
        List<Artist> artists = entity.getArtists();
        Artist artist = artists == null ? null : (Artist) CollectionsKt___CollectionsKt.first((List) artists);
        if (data.getHeader() == null) {
            return null;
        }
        String blockRef = data.getBlockRef();
        String header = data.getHeader();
        String primaryTitle = data.getPrimaryTitle();
        String secondaryTitle = data.getSecondaryTitle();
        String tertiaryTitle = data.getTertiaryTitle();
        String altDescription = data.getAltDescription();
        String str = listFilterImages == null ? null : (String) CollectionsKt___CollectionsKt.first((List) listFilterImages);
        String label = data.getLabel();
        boolean z = this.isFacetedNavUX;
        String blockRef2 = data.getBlockRef();
        String asin = entity.getAsin();
        String title = entity.getTitle();
        String str2 = listFilterImages == null ? null : (String) CollectionsKt___CollectionsKt.first((List) listFilterImages);
        String asin2 = artist == null ? null : artist.getAsin();
        String name = artist == null ? null : artist.getName();
        boolean zAreEqual = Intrinsics.areEqual(entity.isIsInLibrary(), Boolean.TRUE);
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = entity.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "entity.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        List<String> contentTiers2 = entity.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers2, "entity.contentTiers");
        Set<SubscriptionType> setConvertOnDemandTiers = brushConverterUtils.convertOnDemandTiers(contentTiers2);
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = BrushConverterUtils.convertPlaymodeEligibility(entity.getPlaymodeEligibility());
        ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
        String asin3 = entity.getAsin();
        String title2 = entity.getTitle();
        String name2 = artist != null ? artist.getName() : null;
        Intrinsics.checkNotNullExpressionValue(asin3, "asin");
        AlbumMetadata albumMetadata = new AlbumMetadata(blockRef2, contentPlaybackUtils.getAlbumUri(name2, title2, asin3), null, asin, title, str2, null, asin2, name, Boolean.valueOf(zAreEqual), null, setConvertTiers, false, null, null, null, playmodeEligibilityConvertPlaymodeEligibility, setConvertOnDemandTiers, null, null, 848964, null);
        Intrinsics.checkNotNullExpressionValue(header, "header");
        return new FeaturePlayModel(blockRef, null, header, primaryTitle, secondaryTitle, tertiaryTitle, str, altDescription, 0, label, albumMetadata, null, false, null, z, listFilterImages, 14338, null);
    }

    private final FeaturePlayModel convertLiveEvent(FeaturedPlay data, LiveEvent entity) {
        List<String> listFilterImages = filterImages(data);
        Uri uri = Uri.parse(URLConfig.SHARE_BASE_URL.getValue() + "live/events/" + ((Object) entity.getId()));
        if (data.getHeader() == null) {
            return null;
        }
        String blockRef = data.getBlockRef();
        String header = data.getHeader();
        String primaryTitle = data.getPrimaryTitle();
        String secondaryTitle = data.getSecondaryTitle();
        String tertiaryTitle = data.getTertiaryTitle();
        String altDescription = data.getAltDescription();
        String str = listFilterImages == null ? null : (String) CollectionsKt___CollectionsKt.first((List) listFilterImages);
        String label = data.getLabel();
        boolean z = this.isFacetedNavUX;
        String blockRef2 = data.getBlockRef();
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        String id = entity.getId();
        Intrinsics.checkNotNullExpressionValue(id, "entity.id");
        LiveEventMetadata liveEventMetadata = new LiveEventMetadata(blockRef2, uri, null, id, 4, null);
        Intrinsics.checkNotNullExpressionValue(header, "header");
        return new FeaturePlayModel(blockRef, null, header, primaryTitle, secondaryTitle, tertiaryTitle, str, altDescription, 15, label, liveEventMetadata, null, false, null, z, listFilterImages, 14338, null);
    }

    private final PodcastFeaturePlayModel convertPodcastEpisode(FeaturedPlay data, PodcastEpisode entity) {
        List<String> listFilterImages = filterImages(data);
        if (data.getHeader() == null) {
            return null;
        }
        String blockRef = data.getBlockRef();
        String header = data.getHeader();
        String primaryTitle = data.getPrimaryTitle();
        String secondaryTitle = data.getSecondaryTitle();
        String tertiaryTitle = data.getTertiaryTitle();
        String str = listFilterImages == null ? null : (String) CollectionsKt___CollectionsKt.first((List) listFilterImages);
        String label = data.getLabel();
        String altDescription = data.getAltDescription();
        String blockRef2 = data.getBlockRef();
        String catalogId = entity.getCatalogId();
        String str2 = Deeplinks.top1RecommenderEpisodePlay(entity.getPodcastShowCatalogId(), entity.getCatalogId(), entity.getPodcastShowTitle(), entity.getTitle());
        String strEpisode = Deeplinks.episode(entity.getPodcastShowCatalogId(), entity.getCatalogId(), entity.getPodcastShowTitle(), entity.getTitle());
        Uri podcastEpisodeUri = ContentPlaybackUtils.INSTANCE.getPodcastEpisodeUri();
        Intrinsics.checkNotNullExpressionValue(catalogId, "catalogId");
        Intrinsics.checkNotNullExpressionValue(str2, "top1RecommenderEpisodePl…            entity.title)");
        PodcastMetadata podcastMetadata = new PodcastMetadata(blockRef2, podcastEpisodeUri, null, catalogId, str2, strEpisode, null, null, null, null, null, 1988, null);
        Intrinsics.checkNotNullExpressionValue(header, "header");
        return new PodcastFeaturePlayModel(blockRef, null, header, primaryTitle, secondaryTitle, tertiaryTitle, str, altDescription, 11, label, podcastMetadata, null, false, listFilterImages, 6146, null);
    }

    private final PodcastFeaturePlayModel convertPodcastShow(FeaturedPlay data, PodcastShow entity) {
        List<String> listFilterImages = filterImages(data);
        if (data.getHeader() == null) {
            return null;
        }
        String blockRef = data.getBlockRef();
        String header = data.getHeader();
        String primaryTitle = data.getPrimaryTitle();
        String secondaryTitle = data.getSecondaryTitle();
        String tertiaryTitle = data.getTertiaryTitle();
        String label = data.getLabel();
        String altDescription = data.getAltDescription();
        String str = listFilterImages == null ? null : (String) CollectionsKt___CollectionsKt.first((List) listFilterImages);
        String blockRef2 = data.getBlockRef();
        String catalogId = entity.getCatalogId();
        String str2 = Deeplinks.top1RecommenderShowPlay(entity.getCatalogId(), entity.getTitle());
        String strPodcast = Deeplinks.podcast(entity.getCatalogId(), entity.getTitle());
        Uri podcastShowUri = ContentPlaybackUtils.INSTANCE.getPodcastShowUri();
        Intrinsics.checkNotNullExpressionValue(catalogId, "catalogId");
        Intrinsics.checkNotNullExpressionValue(str2, "top1RecommenderShowPlay(…            entity.title)");
        PodcastMetadata podcastMetadata = new PodcastMetadata(blockRef2, podcastShowUri, null, catalogId, str2, strPodcast, null, null, null, null, null, 1988, null);
        Intrinsics.checkNotNullExpressionValue(header, "header");
        return new PodcastFeaturePlayModel(blockRef, null, header, primaryTitle, secondaryTitle, tertiaryTitle, str, altDescription, 12, label, podcastMetadata, null, false, listFilterImages, 6146, null);
    }

    private final List<String> filterImages(FeaturedPlay data) {
        ArrayList arrayList;
        List<Image> images = data.getImages();
        ArrayList arrayList2 = null;
        if (images == null) {
            arrayList = null;
        } else {
            ArrayList<Image> arrayList3 = new ArrayList();
            for (Object obj : images) {
                Image image = (Image) obj;
                if (StringsKt__StringsJVMKt.equals$default(image == null ? null : image.getType(), "ORIGINAL_ART", false, 2, null)) {
                    arrayList3.add(obj);
                }
            }
            arrayList = new ArrayList();
            for (Image image2 : arrayList3) {
                String url = image2 == null ? null : image2.getUrl();
                if (url != null) {
                    arrayList.add(url);
                }
            }
        }
        if (!(arrayList == null || arrayList.isEmpty())) {
            return arrayList;
        }
        List<Image> images2 = data.getImages();
        if (images2 != null) {
            ArrayList<Image> arrayList4 = new ArrayList();
            for (Object obj2 : images2) {
                Image image3 = (Image) obj2;
                if (StringsKt__StringsJVMKt.equals$default(image3 == null ? null : image3.getType(), "16x9", false, 2, null)) {
                    arrayList4.add(obj2);
                }
            }
            ArrayList arrayList5 = new ArrayList();
            for (Image image4 : arrayList4) {
                String url2 = image4 == null ? null : image4.getUrl();
                if (url2 != null) {
                    arrayList5.add(url2);
                }
            }
            arrayList2 = arrayList5;
        }
        return arrayList2;
    }
}
