package com.amazon.mp3.brush.converters;

import UpsellInterface.v1_0.UpsellType;
import android.content.ContentResolver;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.baseviews.model.configuration.ModelConfiguration;
import com.amazon.mp3.cache.AlbumCache;
import com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.search.SearchItemUtils;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.extensions.ModelConfigurationKt;
import com.amazon.music.ContentAccessType;
import com.amazon.music.find.api.util.RelatedEntityUtil;
import com.amazon.music.find.converter.model.MusicSearchArtist;
import com.amazon.music.find.converter.model.MusicSearchTrack;
import com.amazon.music.media.auto.util.BrushConverterUtil;
import com.amazon.music.models.AssetQuality;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.search.RelatedPlaylist;
import com.amazon.music.storeservice.model.AssetQualityItem;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Artist;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Image;
import com.amazon.musicensembleservice.brush.ParentalControls;
import com.amazon.musicensembleservice.brush.PlaybackContainer;
import com.amazon.musicensembleservice.brush.Playlist;
import com.amazon.podcast.deeplinks.Deeplinks;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringNumberConversionsKt;

/* JADX INFO: compiled from: BrushConverterUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000Ò\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\bV\u0010WJ\u0018\u0010\u0006\u001a\u00020\u00052\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0007J\u0014\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0007J\u001a\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u0003H\u0002J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0014H\u0002J\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00152\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u00152\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J(\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020 0\u001d2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001e0\u001dH\u0016J$\u0010#\u001a\u00020 2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\"\u001a\u00020\u0003H\u0016J(\u0010(\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\u0003H\u0016J(\u0010)\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\u0003H\u0016J\u0018\u0010+\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0003H\u0016J\u0018\u0010,\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0003H\u0016J\u001c\u00100\u001a\b\u0012\u0004\u0012\u00020/0\u00022\f\u0010.\u001a\b\u0012\u0004\u0012\u00020-0\u0002H\u0016J\u001c\u00103\u001a\b\u0012\u0004\u0012\u00020/0\u00022\f\u00102\u001a\b\u0012\u0004\u0012\u0002010\u0002H\u0016J\u001e\u00106\u001a\b\u0012\u0004\u0012\u00020/0\u00022\u000e\u00105\u001a\n\u0012\u0004\u0012\u000204\u0018\u00010\u0002H\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u000207H\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u000208H\u0016J\u001a\u0010;\u001a\u0004\u0018\u00010\u000e2\u0006\u0010:\u001a\u0002092\b\u0010\r\u001a\u0004\u0018\u00010\u0003J\u0010\u0010;\u001a\u0004\u0018\u00010\u000e2\u0006\u0010=\u001a\u00020<J&\u0010C\u001a\u00020B2\u0006\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010?\u001a\u0004\u0018\u00010>2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010@J\u000e\u0010C\u001a\u00020B2\u0006\u0010D\u001a\u00020\u0014J\u0018\u0010C\u001a\u00020B2\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010E\u001a\u0004\u0018\u00010@J\u001a\u0010C\u001a\u00020B2\u0006\u0010D\u001a\u00020\u00142\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u0014J\u000e\u0010I\u001a\u00020 2\u0006\u0010H\u001a\u00020GJ\u0010\u0010L\u001a\u0004\u0018\u00010\u00142\u0006\u0010K\u001a\u00020JJ&\u0010S\u001a\u00020 2\u0006\u0010N\u001a\u00020M2\u0006\u0010P\u001a\u00020O2\u0006\u0010H\u001a\u00020G2\u0006\u0010R\u001a\u00020QJ\u000e\u0010U\u001a\u00020 2\u0006\u0010T\u001a\u00020\u000e¨\u0006X"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushConverterUtils;", "Lcom/amazon/music/media/auto/util/BrushConverterUtil;", "", "", "tiers", "Lcom/amazon/mp3/prime/browse/metadata/CatalogStatusTiers;", "convertToCatalogStatusTiers", "Lcom/amazon/musicensembleservice/PlaymodeEligibility;", "playmodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "convertPlaymodeEligibility", "Lcom/amazon/musicensembleservice/brush/Playlist;", Environment.PLAYLIST_PATH, "blockRef", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getPlaylistMetadata", "Lcom/amazon/musicensembleservice/brush/Album;", "album", "Landroid/net/Uri;", "getAlbumUri", "Lcom/amazon/mp3/library/item/Album;", "", "Lcom/amazon/music/ContentAccessType;", "convertTiers", "eligibilityFlags", "Lcom/amazon/music/platform/data/subscription/SubscriptionType;", "convertContentTiers", "onDemandFlags", "convertOnDemandTiers", "", "Lcom/amazon/musicensembleservice/brush/ParentalControls;", "parentalControlsMap", "", "convertExplicitMap", "tier", "getExplicitValueForTier", "showCatalogId", "catalogId", "showTitle", "title", "getPodcastEpisodeBackgroundTarget", "getPodcastEpisodeTarget", "showId", "getPodcastShowBackgroundTarget", "getPodcastShowTarget", "Lcom/amazon/musicensembleservice/brush/AssetQuality;", "museAssetQualityList", "Lcom/amazon/music/models/AssetQuality;", "convertAssetQuality", "Lcom/amazon/music/page/api/model/AssetQuality;", "pageAssetQualityList", "convertAssetQualityPage", "Lcom/amazon/music/storeservice/model/AssetQualityItem;", "museAssetQualityItemList", "convertAssetQualityItem", "Lcom/amazon/music/search/PlaymodeEligibility;", "Lcom/amazon/music/page/api/model/PlaymodeEligibility;", "Lcom/amazon/musicensembleservice/brush/PlaybackContainer;", "playbackContainer", "getContainerMetadata", "Lcom/amazon/music/find/converter/model/MusicSearchTrack;", "searchTrack", "Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;", "modelConfiguration", "Lcom/amazon/mp3/cache/AlbumCache;", "albumCache", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "getAlbumMetadata", "downloadedAlbum", "cache", "libraryAlbum", "Lcom/amazon/musicensembleservice/brush/Entity;", "entity", "isContentAvailable", "", "albumId", "retrieveLibraryAlbumFromDB", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "model", "Lcom/amazon/musicensembleservice/brush/Block;", "sectionData", "", "index", "shouldShowHotSpot", "contentMetadata", "shouldHidePlayTypeIconForExperiment", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushConverterUtils implements BrushConverterUtil {
    public static final BrushConverterUtils INSTANCE = new BrushConverterUtils();

    private BrushConverterUtils() {
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.amazon.music.media.auto.util.BrushConverterUtil
    public Set<ContentAccessType> convertTiers(List<String> tiers) {
        Intrinsics.checkNotNullParameter(tiers, "tiers");
        HashSet hashSet = new HashSet();
        for (String str : tiers) {
            switch (str.hashCode()) {
                case -835635115:
                    if (str.equals("FREE_ON_DEMAND_PLAYABLE")) {
                        hashSet.add(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE);
                    }
                    break;
                case -398170857:
                    if (str.equals("MUSIC_SUBSCRIPTION")) {
                        hashSet.add(ContentAccessType.HAWKFIRE);
                    }
                    break;
                case 2166380:
                    if (str.equals(UpsellType.FREE)) {
                        hashSet.add(ContentAccessType.NIGHTWING);
                    }
                    break;
                case 76397151:
                    if (str.equals(UpsellType.PRIME)) {
                        hashSet.add(ContentAccessType.PRIME);
                    }
                    break;
                case 329571894:
                    if (str.equals("SONIC_RUSH_ON_DEMAND_PLAYABLE")) {
                        hashSet.add(ContentAccessType.SONIC_RUSH_ONDEMAND_PLAYABLE);
                    }
                    break;
                case 686193195:
                    if (str.equals("SONIC_RUSH")) {
                        hashSet.add(ContentAccessType.SONIC_RUSH);
                    }
                    break;
            }
        }
        return hashSet;
    }

    @JvmStatic
    public static final CatalogStatusTiers convertToCatalogStatusTiers(List<String> tiers) {
        return new CatalogStatusTiers(tiers != null && tiers.contains(UpsellType.PRIME), tiers != null && tiers.contains("MUSIC_SUBSCRIPTION"), tiers != null && tiers.contains(UpsellType.FREE), tiers != null && tiers.contains("FREE_ON_DEMAND_PLAYABLE"), tiers != null && tiers.contains("SONIC_RUSH"), tiers != null && tiers.contains("SONIC_RUSH_ON_DEMAND_PLAYABLE"), false, 64, null);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.amazon.music.media.auto.util.BrushConverterUtil
    public Set<SubscriptionType> convertContentTiers(List<String> eligibilityFlags) {
        Intrinsics.checkNotNullParameter(eligibilityFlags, "eligibilityFlags");
        HashSet hashSet = new HashSet();
        for (String str : eligibilityFlags) {
            switch (str.hashCode()) {
                case -2085184714:
                    if (str.equals("KATANA")) {
                        hashSet.add(SubscriptionType.MUSIC_HD);
                    }
                    break;
                case -398170857:
                    if (str.equals("MUSIC_SUBSCRIPTION")) {
                        hashSet.add(SubscriptionType.MUSIC_UNLIMITED);
                    }
                    break;
                case 2166380:
                    if (str.equals(UpsellType.FREE)) {
                        hashSet.add(SubscriptionType.FREE);
                    }
                    break;
                case 76397151:
                    if (str.equals(UpsellType.PRIME)) {
                        hashSet.add(SubscriptionType.PRIME);
                    }
                    break;
                case 686193195:
                    if (str.equals("SONIC_RUSH")) {
                        hashSet.add(SubscriptionType.SONIC_RUSH);
                    }
                    break;
            }
        }
        return hashSet;
    }

    @Override // com.amazon.music.media.auto.util.BrushConverterUtil
    public Set<SubscriptionType> convertOnDemandTiers(List<String> onDemandFlags) {
        Intrinsics.checkNotNullParameter(onDemandFlags, "onDemandFlags");
        HashSet hashSet = new HashSet();
        for (String str : onDemandFlags) {
            if (Intrinsics.areEqual(str, "SONIC_RUSH_ON_DEMAND_PLAYABLE")) {
                hashSet.add(SubscriptionType.SONIC_RUSH);
            } else if (Intrinsics.areEqual(str, "FREE_ON_DEMAND_PLAYABLE")) {
                hashSet.add(SubscriptionType.FREE);
            }
        }
        return hashSet;
    }

    @Override // com.amazon.music.media.auto.util.BrushConverterUtil
    public Map<ContentAccessType, Boolean> convertExplicitMap(Map<String, ? extends ParentalControls> parentalControlsMap) {
        Intrinsics.checkNotNullParameter(parentalControlsMap, "parentalControlsMap");
        HashMap map = new HashMap(2);
        map.put(ContentAccessType.HAWKFIRE, Boolean.valueOf(getExplicitValueForTier(parentalControlsMap, "MUSIC_SUBSCRIPTION")));
        map.put(ContentAccessType.PRIME, Boolean.valueOf(getExplicitValueForTier(parentalControlsMap, UpsellType.PRIME)));
        return map;
    }

    public boolean getExplicitValueForTier(Map<String, ? extends ParentalControls> parentalControlsMap, String tier) {
        Intrinsics.checkNotNullParameter(parentalControlsMap, "parentalControlsMap");
        Intrinsics.checkNotNullParameter(tier, "tier");
        if (parentalControlsMap.containsKey(tier)) {
            Object value = MapsKt__MapsKt.getValue(parentalControlsMap, tier);
            Intrinsics.checkNotNull(value);
            Boolean boolIsHasExplicitLanguage = ((ParentalControls) value).isHasExplicitLanguage();
            Intrinsics.checkNotNullExpressionValue(boolIsHasExplicitLanguage, "parentalControlsMap.getV…)!!.isHasExplicitLanguage");
            if (boolIsHasExplicitLanguage.booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.music.media.auto.util.BrushConverterUtil
    public String getPodcastEpisodeBackgroundTarget(String showCatalogId, String catalogId, String showTitle, String title) {
        Intrinsics.checkNotNullParameter(showCatalogId, "showCatalogId");
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        Intrinsics.checkNotNullParameter(showTitle, "showTitle");
        Intrinsics.checkNotNullParameter(title, "title");
        String strEpisode = Deeplinks.episode(showCatalogId, catalogId, showTitle, title);
        Intrinsics.checkNotNullExpressionValue(strEpisode, "episode(showCatalogId, c…alogId, showTitle, title)");
        return strEpisode;
    }

    @Override // com.amazon.music.media.auto.util.BrushConverterUtil
    public String getPodcastEpisodeTarget(String showCatalogId, String catalogId, String showTitle, String title) {
        Intrinsics.checkNotNullParameter(showCatalogId, "showCatalogId");
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        Intrinsics.checkNotNullParameter(showTitle, "showTitle");
        Intrinsics.checkNotNullParameter(title, "title");
        String str = Deeplinks.top1RecommenderEpisodePlay(showCatalogId, catalogId, showTitle, title);
        Intrinsics.checkNotNullExpressionValue(str, "top1RecommenderEpisodePl…alogId, showTitle, title)");
        return str;
    }

    @Override // com.amazon.music.media.auto.util.BrushConverterUtil
    public String getPodcastShowBackgroundTarget(String showId, String showTitle) {
        Intrinsics.checkNotNullParameter(showId, "showId");
        Intrinsics.checkNotNullParameter(showTitle, "showTitle");
        String strPodcast = Deeplinks.podcast(showId, showTitle);
        Intrinsics.checkNotNullExpressionValue(strPodcast, "podcast(showId, showTitle)");
        return strPodcast;
    }

    @Override // com.amazon.music.media.auto.util.BrushConverterUtil
    public String getPodcastShowTarget(String showId, String showTitle) {
        Intrinsics.checkNotNullParameter(showId, "showId");
        Intrinsics.checkNotNullParameter(showTitle, "showTitle");
        String str = Deeplinks.top1RecommenderShowPlay(showId, showTitle);
        Intrinsics.checkNotNullExpressionValue(str, "top1RecommenderShowPlay(showId, showTitle)");
        return str;
    }

    @Override // com.amazon.music.media.auto.util.BrushConverterUtil
    public PlaymodeEligibility convertPlaymodeEligibility(com.amazon.music.search.PlaymodeEligibility playmodeEligibility) {
        Intrinsics.checkNotNullParameter(playmodeEligibility, "playmodeEligibility");
        return new PlaymodeEligibility(playmodeEligibility.isOnDemandPlayable(), playmodeEligibility.isStationFromAnythingPlayable(), playmodeEligibility.getShuffleType());
    }

    @JvmStatic
    public static final PlaymodeEligibility convertPlaymodeEligibility(com.amazon.musicensembleservice.PlaymodeEligibility playmodeEligibility) {
        if (playmodeEligibility == null) {
            return null;
        }
        return new PlaymodeEligibility(playmodeEligibility.isOnDemand(), playmodeEligibility.isSfa(), playmodeEligibility.getShuffleType());
    }

    @Override // com.amazon.music.media.auto.util.BrushConverterUtil
    public PlaymodeEligibility convertPlaymodeEligibility(com.amazon.music.page.api.model.PlaymodeEligibility playmodeEligibility) {
        Intrinsics.checkNotNullParameter(playmodeEligibility, "playmodeEligibility");
        return new PlaymodeEligibility(playmodeEligibility.getOnDemand(), playmodeEligibility.getSfa(), playmodeEligibility.getShuffleType().toString());
    }

    public final ContentMetadata getContainerMetadata(PlaybackContainer playbackContainer, String blockRef) {
        Intrinsics.checkNotNullParameter(playbackContainer, "playbackContainer");
        if (!(playbackContainer.getContainer() instanceof Playlist)) {
            return null;
        }
        Entity container = playbackContainer.getContainer();
        if (container != null) {
            return getPlaylistMetadata((Playlist) container, blockRef);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.musicensembleservice.brush.Playlist");
    }

    private final ContentMetadata getPlaylistMetadata(Playlist playlist, String blockRef) {
        String asin = playlist.getAsin();
        ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
        String asin2 = playlist.getAsin();
        Intrinsics.checkNotNullExpressionValue(asin2, "playlist.asin");
        Uri playlistUri = contentPlaybackUtils.getPlaylistUri(asin2);
        List<String> contentTiers = playlist.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "playlist.contentTiers");
        Set<ContentAccessType> setConvertTiers = convertTiers(contentTiers);
        Boolean boolIsIsOwned = playlist.isIsOwned();
        boolean zBooleanValue = boolIsIsOwned == null ? false : boolIsIsOwned.booleanValue();
        List<String> contentTiers2 = playlist.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers2, "playlist.contentTiers");
        Set<SubscriptionType> setConvertOnDemandTiers = convertOnDemandTiers(contentTiers2);
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = convertPlaymodeEligibility(playlist.getPlaymodeEligibility());
        List<String> contentTypes = playlist.getContentTypes();
        Intrinsics.checkNotNullExpressionValue(contentTypes, "contentTypes");
        return new PlaylistMetadata(blockRef, playlistUri, null, asin, null, null, null, null, null, null, null, setConvertTiers, contentTypes, false, null, null, null, null, zBooleanValue, null, false, playmodeEligibilityConvertPlaymodeEligibility, setConvertOnDemandTiers, false, null, 26994676, null);
    }

    public final ContentMetadata getContainerMetadata(MusicSearchTrack searchTrack) {
        String str;
        Intrinsics.checkNotNullParameter(searchTrack, "searchTrack");
        RelatedPlaylist relatedPlaylist = RelatedEntityUtil.INSTANCE.getRelatedPlaylist(searchTrack);
        if (relatedPlaylist != null) {
            String asin = relatedPlaylist.getAsin();
            String playlistId = relatedPlaylist.getPlaylistId();
            if (asin == null) {
                str = PrimePlaylistUtil.isAsin(playlistId) ? playlistId : null;
            } else {
                str = asin;
            }
            if (str != null) {
                com.amazon.music.search.PlaymodeEligibility playmodeEligibility = relatedPlaylist.getPlaymodeEligibility();
                PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = playmodeEligibility != null ? INSTANCE.convertPlaymodeEligibility(playmodeEligibility) : null;
                String blockRef = relatedPlaylist.getBlockRef();
                String title = relatedPlaylist.getTitle();
                String imageUrl = relatedPlaylist.getImageUrl();
                Uri playlistUri = ContentPlaybackUtils.INSTANCE.getPlaylistUri(str);
                BrushConverterUtils brushConverterUtils = INSTANCE;
                List<String> contentTiers = searchTrack.getContentTiers();
                Intrinsics.checkNotNullExpressionValue(contentTiers, "searchTrack.contentTiers");
                return new PlaylistMetadata(blockRef, playlistUri, null, str, title, imageUrl, null, null, null, null, null, brushConverterUtils.convertTiers(contentTiers), CollectionsKt__CollectionsKt.emptyList(), false, null, null, null, null, false, null, false, playmodeEligibilityConvertPlaymodeEligibility, null, false, null, 31451076, null);
            }
        }
        return null;
    }

    public static /* synthetic */ AlbumMetadata getAlbumMetadata$default(BrushConverterUtils brushConverterUtils, Album album, ModelConfiguration modelConfiguration, AlbumCache albumCache, int i, Object obj) {
        if ((i & 2) != 0) {
            modelConfiguration = null;
        }
        if ((i & 4) != 0) {
            albumCache = null;
        }
        return brushConverterUtils.getAlbumMetadata(album, modelConfiguration, albumCache);
    }

    public final AlbumMetadata getAlbumMetadata(Album album, ModelConfiguration modelConfiguration, AlbumCache albumCache) {
        Intrinsics.checkNotNullParameter(album, "album");
        if (ModelConfigurationKt.getContentMetadata(modelConfiguration) instanceof AlbumMetadata) {
            ContentMetadata contentMetadata = ModelConfigurationKt.getContentMetadata(modelConfiguration);
            if (contentMetadata != null) {
                return (AlbumMetadata) contentMetadata;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.AlbumMetadata");
        }
        List<Artist> artists = album.getArtists();
        Artist artist = artists == null ? null : (Artist) CollectionsKt___CollectionsKt.first((List) artists);
        long jGenerateAlbumId = IdGenerator.generateAlbumId(album.getAsin(), album.getTitle());
        String blockRef = album.getBlockRef();
        Uri albumUri = albumCache == null ? null : albumCache.getAlbumUri();
        Uri contentUri = albumUri == null ? MediaProvider.Albums.getContentUri("cirrus", jGenerateAlbumId) : albumUri;
        Intrinsics.checkNotNullExpressionValue(contentUri, "albumCache?.getCollectio…ource.ID_CIRRUS, albumId)");
        String asin = album.getAsin();
        String title = album.getTitle();
        Image image = album.getImage();
        String url = image == null ? null : image.getUrl();
        String asin2 = artist == null ? null : artist.getAsin();
        String name = artist != null ? artist.getName() : null;
        Boolean boolValueOf = Boolean.valueOf(Intrinsics.areEqual(album.isIsInLibrary(), Boolean.TRUE));
        List<String> contentTiers = album.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "album.contentTiers");
        Set<ContentAccessType> setConvertTiers = convertTiers(contentTiers);
        Boolean boolIsHasExplicitLanguage = album.getParentalControls().isHasExplicitLanguage();
        Intrinsics.checkNotNullExpressionValue(boolIsHasExplicitLanguage, "album.parentalControls.isHasExplicitLanguage");
        boolean zBooleanValue = boolIsHasExplicitLanguage.booleanValue();
        Integer numValueOf = Integer.valueOf((int) album.getTracksCount().longValue());
        List<String> contentEncoding = album.getContentEncoding();
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = convertPlaymodeEligibility(album.getPlaymodeEligibility());
        List<String> contentTiers2 = album.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers2, "album.contentTiers");
        return new AlbumMetadata(blockRef, contentUri, null, asin, title, url, null, asin2, name, boolValueOf, null, setConvertTiers, zBooleanValue, numValueOf, null, contentEncoding, playmodeEligibilityConvertPlaymodeEligibility, convertOnDemandTiers(contentTiers2), null, null, 803908, null);
    }

    public final AlbumMetadata getAlbumMetadata(com.amazon.mp3.library.item.Album downloadedAlbum) {
        Intrinsics.checkNotNullParameter(downloadedAlbum, "downloadedAlbum");
        String id = downloadedAlbum.getId();
        Long longOrNull = id == null ? null : StringsKt__StringNumberConversionsKt.toLongOrNull(id);
        return getAlbumMetadata(downloadedAlbum, retrieveLibraryAlbumFromDB(longOrNull == null ? IdGenerator.generateAlbumId(downloadedAlbum.getAsin(), downloadedAlbum.getTitle()) : longOrNull.longValue()));
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.amazon.music.views.library.metadata.AlbumMetadata getAlbumMetadata(com.amazon.musicensembleservice.brush.Album r32, com.amazon.mp3.cache.AlbumCache r33) {
        /*
            Method dump skipped, instruction units count: 312
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.brush.converters.BrushConverterUtils.getAlbumMetadata(com.amazon.musicensembleservice.brush.Album, com.amazon.mp3.cache.AlbumCache):com.amazon.music.views.library.metadata.AlbumMetadata");
    }

    public final AlbumMetadata getAlbumMetadata(com.amazon.mp3.library.item.Album downloadedAlbum, com.amazon.mp3.library.item.Album libraryAlbum) {
        Intrinsics.checkNotNullParameter(downloadedAlbum, "downloadedAlbum");
        Uri contentUri = libraryAlbum == null ? null : libraryAlbum.getContentUri();
        if (contentUri == null) {
            contentUri = getAlbumUri(downloadedAlbum);
        }
        Uri uri = contentUri;
        String asin = libraryAlbum == null ? null : libraryAlbum.getAsin();
        if (asin == null) {
            asin = downloadedAlbum.getAsin();
        }
        String str = asin;
        String title = libraryAlbum == null ? null : libraryAlbum.getTitle();
        if (title == null) {
            title = downloadedAlbum.getTitle();
        }
        String str2 = title;
        String artistAsin = libraryAlbum == null ? null : libraryAlbum.getArtistAsin();
        if (artistAsin == null) {
            artistAsin = downloadedAlbum.getArtistAsin();
        }
        String str3 = artistAsin;
        String artistName = libraryAlbum != null ? libraryAlbum.getArtistName() : null;
        if (artistName == null) {
            artistName = downloadedAlbum.getArtistName();
        }
        String str4 = artistName;
        boolean z = false;
        boolean z2 = libraryAlbum != null && libraryAlbum.isInLibrary();
        boolean z3 = (libraryAlbum != null && libraryAlbum.isAllOwned()) && libraryAlbum.getTrackCount() >= ((long) downloadedAlbum.getTracks().size());
        if (libraryAlbum != null && libraryAlbum.isDownloaded()) {
            z = true;
        }
        return new AlbumMetadata(null, uri, null, str, str2, null, null, str3, str4, Boolean.valueOf(z2), Boolean.valueOf(z3), SetsKt__SetsKt.emptySet(), false, Integer.valueOf(downloadedAlbum.getTracks().size()), null, null, null, null, Boolean.valueOf(z), null, 774245, null);
    }

    private final Uri getAlbumUri(Album album) {
        Uri contentUri = MediaProvider.Albums.getContentUri("cirrus", IdGenerator.generateAlbumId(album.getAsin(), album.getTitle()));
        Intrinsics.checkNotNullExpressionValue(contentUri, "getContentUri(CirrusMedi…ource.ID_CIRRUS, albumId)");
        return contentUri;
    }

    private final Uri getAlbumUri(com.amazon.mp3.library.item.Album album) {
        Uri contentUri = MediaProvider.Albums.getContentUri("cirrus", IdGenerator.generateAlbumId(album.getAsin(), album.getTitle()));
        Intrinsics.checkNotNullExpressionValue(contentUri, "getContentUri(CirrusMedi…ource.ID_CIRRUS, albumId)");
        return contentUri;
    }

    public final boolean isContentAvailable(Entity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        if (entity instanceof MusicSearchArtist) {
            return SearchItemUtils.isSourceLibrary(((MusicSearchArtist) entity).getSource());
        }
        return false;
    }

    public final com.amazon.mp3.library.item.Album retrieveLibraryAlbumFromDB(long albumId) {
        Uri libraryAlbumUri = MediaProvider.Albums.getContentUri("cirrus", albumId);
        DBQueryUtil dBQueryUtil = DBQueryUtil.INSTANCE;
        ContentResolver contentResolver = AmazonApplication.getContext().getContentResolver();
        Intrinsics.checkNotNullExpressionValue(libraryAlbumUri, "libraryAlbumUri");
        return dBQueryUtil.convertAlbumDetailCursorToAlbum(dBQueryUtil.runAlbumDetailQuery(contentResolver, libraryAlbumUri, true), libraryAlbumUri);
    }

    public final boolean shouldShowHotSpot(BaseViewModel model, Block sectionData, Entity entity, int index) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(sectionData, "sectionData");
        Intrinsics.checkNotNullParameter(entity, "entity");
        if (model instanceof HorizontalTileModel) {
            List<String> tags = sectionData.getTags();
            if ((tags != null && tags.contains("hotspot_search_top_results_section")) && index == 0 && (entity instanceof MusicSearchTrack)) {
                MusicSearchTrack musicSearchTrack = (MusicSearchTrack) entity;
                if (Intrinsics.areEqual(musicSearchTrack.getAssetType(), "AUDIO")) {
                    com.amazon.musicensembleservice.PlaymodeEligibility playmodeEligibility = musicSearchTrack.getPlaymodeEligibility();
                    if (!(playmodeEligibility == null ? false : Intrinsics.areEqual(playmodeEligibility.isOnDemand(), Boolean.TRUE)) && !musicSearchTrack.isIsOwned().booleanValue()) {
                        return true;
                    }
                }
            }
        }
        if (model instanceof SectionGridViewModel) {
            List<String> tags2 = sectionData.getTags();
            if (tags2 != null && tags2.contains("hotspot_search_top_results_section")) {
                return true;
            }
        }
        return false;
    }

    public final boolean shouldHidePlayTypeIconForExperiment(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        List<String> tags = contentMetadata.getTags();
        if (tags == null) {
            return false;
        }
        return tags.contains("upsell-tag-no-playicon-widget");
    }

    @Override // com.amazon.music.media.auto.util.BrushConverterUtil
    public List<AssetQuality> convertAssetQuality(List<? extends com.amazon.musicensembleservice.brush.AssetQuality> museAssetQualityList) {
        Intrinsics.checkNotNullParameter(museAssetQualityList, "museAssetQualityList");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(museAssetQualityList, 10));
        for (com.amazon.musicensembleservice.brush.AssetQuality assetQuality : museAssetQualityList) {
            String quality = assetQuality.getQuality();
            String str = quality == null ? "" : quality;
            String pid = assetQuality.getPid();
            arrayList.add(new AssetQuality(pid == null ? "" : pid, str, 0, 4, null));
        }
        return CollectionsKt___CollectionsKt.toList(arrayList);
    }

    public List<AssetQuality> convertAssetQualityItem(List<? extends AssetQualityItem> museAssetQualityItemList) {
        List<AssetQuality> list;
        if (museAssetQualityItemList == null) {
            list = null;
        } else {
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(museAssetQualityItemList, 10));
            for (AssetQualityItem assetQualityItem : museAssetQualityItemList) {
                String quality = assetQualityItem.getQuality();
                String str = quality == null ? "" : quality;
                String pid = assetQualityItem.getPid();
                arrayList.add(new AssetQuality(pid == null ? "" : pid, str, 0, 4, null));
            }
            list = CollectionsKt___CollectionsKt.toList(arrayList);
        }
        return list == null ? CollectionsKt__CollectionsKt.emptyList() : list;
    }

    @Override // com.amazon.music.media.auto.util.BrushConverterUtil
    public List<AssetQuality> convertAssetQualityPage(List<? extends com.amazon.music.page.api.model.AssetQuality> pageAssetQualityList) {
        Intrinsics.checkNotNullParameter(pageAssetQualityList, "pageAssetQualityList");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(pageAssetQualityList, 10));
        for (com.amazon.music.page.api.model.AssetQuality assetQuality : pageAssetQualityList) {
            String quality = assetQuality.getQuality();
            String string = "";
            String str = quality == null ? "" : quality;
            if (assetQuality.getPlaybackId() != null) {
                string = assetQuality.getPlaybackId().toString();
            }
            arrayList.add(new AssetQuality(string, str, 0, 4, null));
        }
        return CollectionsKt___CollectionsKt.toList(arrayList);
    }
}
