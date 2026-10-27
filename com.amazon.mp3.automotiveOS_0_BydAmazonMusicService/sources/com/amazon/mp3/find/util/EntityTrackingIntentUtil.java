package com.amazon.mp3.find.util;

import android.content.Context;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.find.view.SearchBrushFragment;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.search.model.PodcastEpisode;
import com.amazon.mp3.search.model.PodcastShow;
import com.amazon.music.ContentAccessType;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.intents.EntityRefreshIntentInfo;
import com.amazon.music.find.intents.EntityTrackingIntentService;
import com.amazon.music.find.intents.EntityVisitIntentInfo;
import com.amazon.music.find.utils.ContentTierUtils;
import com.amazon.music.find.utils.DeeplinksUtils;
import com.amazon.music.find.utils.ParentalControlsPerContentTierUtils;
import com.amazon.music.find.viewmodels.SearchExperienceMode;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.find.common.AssetQuality;
import com.amazon.music.platform.find.common.ContentTier;
import com.amazon.music.platform.find.common.EntityIdType;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.platform.find.common.ParentalControls;
import com.amazon.music.platform.find.common.ParentalControlsPerContentTier;
import com.amazon.music.platform.find.service.common.EntityProperties;
import com.amazon.music.station.StationItem;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.CommunityPlaylistMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: EntityTrackingIntentUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b*\u0010+J\"\u0010\t\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J\"\u0010\t\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J\"\u0010\t\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J\"\u0010\t\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J\"\u0010\t\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J \u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J\"\u0010\t\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J>\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0017\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0013H\u0007J\u0018\u0010\t\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u001b\u001a\u00020\u001aJ\u0016\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c2\u0006\u0010\u0005\u001a\u00020\rJ \u0010\t\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\u0007\u001a\u00020\u0006J2\u0010\t\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0013J2\u0010\t\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010%\u001a\u00020$2\u0006\u0010#\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0013J$\u0010)\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010&\u001a\u0004\u0018\u00010\u00132\b\u0010(\u001a\u0004\u0018\u00010'¨\u0006,"}, d2 = {"Lcom/amazon/mp3/find/util/EntityTrackingIntentUtil;", "", "Landroid/content/Context;", "context", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "contentMetadata", "", "didNavigateFromSearch", "", "sendEntityVisitIntentEvent", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "Lcom/amazon/music/views/library/metadata/CommunityPlaylistMetadata;", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "Lcom/amazon/music/views/library/metadata/PodcastMetadata;", "Lcom/amazon/music/station/StationItem;", "stationItem", "Landroidx/fragment/app/Fragment;", "fragment", "", "contentUrl", "title", MediaTrack.ROLE_SUBTITLE, "imageUrl", "variantId", "sendEntityVisitEvent", "Lcom/amazon/music/find/intents/EntityVisitIntentInfo;", "entityVisitInfo", "", "Lcom/amazon/music/platform/find/common/ContentTier;", "getSupportedTiers", "Lcom/amazon/mp3/playlist/CatalogPlaylist;", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/search/model/PodcastShow;", "podcastShow", ImagesContract.URL, "Lcom/amazon/mp3/search/model/PodcastEpisode;", "podcastEpisode", ContextMappingConstants.ENTITY_ID, "Lcom/amazon/music/platform/find/common/EntityIdType;", ContextMappingConstants.ENTITY_ID_TYPE, "sendEntityRefreshIntentEvent", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class EntityTrackingIntentUtil {
    public static final EntityTrackingIntentUtil INSTANCE = new EntityTrackingIntentUtil();

    private EntityTrackingIntentUtil() {
    }

    public final void sendEntityVisitIntentEvent(Context context, EntityVisitIntentInfo entityVisitInfo) {
        Intrinsics.checkNotNullParameter(entityVisitInfo, "entityVisitInfo");
        if (context != null) {
            EntityTrackingIntentService.INSTANCE.processEntityVisit(context, entityVisitInfo);
        }
    }

    @JvmStatic
    public static final void sendEntityVisitIntentEvent(Context context, ArtistMetadata contentMetadata, boolean didNavigateFromSearch) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        String title = contentMetadata.getTitle();
        String imageUrl = contentMetadata.getImageUrl();
        String asin = contentMetadata.getAsin();
        if (context == null || title == null || imageUrl == null || asin == null) {
            return;
        }
        EntityTrackingIntentService.INSTANCE.processEntityVisit(context, new EntityVisitIntentInfo(title, asin, EntityIdType.ASIN, EntityType.ARTIST, imageUrl, didNavigateFromSearch, null, null, 192, null));
    }

    @JvmStatic
    public static final void sendEntityVisitIntentEvent(Context context, AlbumMetadata contentMetadata, boolean didNavigateFromSearch) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        String title = contentMetadata.getTitle();
        String imageUrl = contentMetadata.getImageUrl();
        String asin = contentMetadata.getAsin();
        if (context == null || title == null || imageUrl == null || asin == null) {
            return;
        }
        EntityTrackingIntentService.INSTANCE.processEntityVisit(context, new EntityVisitIntentInfo(title, asin, EntityIdType.ASIN, EntityType.ALBUM, imageUrl, didNavigateFromSearch, null, new EntityProperties(contentMetadata.getArtistAsin(), contentMetadata.getArtistTitle(), (String) null, (String) null, (String) null, ContentTierUtils.INSTANCE.convertFromContentAccessTypeSet(contentMetadata.getSupportedTiers()), (ParentalControlsPerContentTier) null, new ParentalControls(contentMetadata.getIsExplicit()), (String) null, (Boolean) null, (Boolean) null, (String) null, (Boolean) null, (Integer) null, (List) null, contentMetadata.getLanguageOfPerformances(), (String) null, (Boolean) null, (Boolean) null, 491356, (DefaultConstructorMarker) null)));
    }

    @JvmStatic
    public static final void sendEntityVisitIntentEvent(Context context, CommunityPlaylistMetadata contentMetadata, boolean didNavigateFromSearch) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        String title = contentMetadata.getTitle();
        String imageUrl = contentMetadata.getImageUrl();
        String playlistId = contentMetadata.getPlaylistId();
        if (context == null || title == null || imageUrl == null || playlistId == null) {
            return;
        }
        EntityTrackingIntentService.Companion companion = EntityTrackingIntentService.INSTANCE;
        EntityType entityType = EntityType.COMMUNITY_PLAYLIST;
        EntityIdType entityIdType = EntityIdType.PLAYLIST_ID;
        ContentTierUtils contentTierUtils = ContentTierUtils.INSTANCE;
        Set<ContentAccessType> contentTiers = contentMetadata.getContentTiers();
        if (contentTiers == null) {
            contentTiers = SetsKt__SetsKt.emptySet();
        }
        companion.processEntityVisit(context, new EntityVisitIntentInfo(title, playlistId, entityIdType, entityType, imageUrl, didNavigateFromSearch, null, new EntityProperties((String) null, (String) null, (String) null, (String) null, (String) null, contentTierUtils.convertFromContentAccessTypeSet(contentTiers), (ParentalControlsPerContentTier) null, new ParentalControls(false), (String) null, (Boolean) null, (Boolean) null, contentMetadata.getCurator(), (Boolean) null, (Integer) null, (List) null, (List) null, (String) null, (Boolean) null, (Boolean) null, 522015, (DefaultConstructorMarker) null)));
    }

    @JvmStatic
    public static final void sendEntityVisitIntentEvent(Context context, PlaylistMetadata contentMetadata, boolean didNavigateFromSearch) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        String title = contentMetadata.getTitle();
        String imageUrl = contentMetadata.getImageUrl();
        String asin = contentMetadata.getAsin();
        if (context == null || title == null || imageUrl == null || asin == null) {
            return;
        }
        EntityTrackingIntentService.INSTANCE.processEntityVisit(context, new EntityVisitIntentInfo(title, asin, EntityIdType.ASIN, contentMetadata.getContentTypes().contains("VIDEO") ? EntityType.VIDEO_PLAYLIST : EntityType.PLAYLIST, imageUrl, didNavigateFromSearch, null, new EntityProperties((String) null, (String) null, (String) null, (String) null, (String) null, ContentTierUtils.INSTANCE.convertFromContentAccessTypeSet(contentMetadata.getSupportedTiers()), (ParentalControlsPerContentTier) null, new ParentalControls(contentMetadata.getIsExplicit()), (String) null, (Boolean) null, (Boolean) null, (String) null, (Boolean) null, (Integer) null, (List) null, contentMetadata.getLanguageOfPerformances(), (String) null, (Boolean) null, (Boolean) null, 491295, (DefaultConstructorMarker) null)));
    }

    @JvmStatic
    public static final void sendEntityVisitIntentEvent(Context context, TrackMetadata contentMetadata, boolean didNavigateFromSearch) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        String title = contentMetadata.getTitle();
        String imageUrl = contentMetadata.getImageUrl();
        if (context == null || title == null || imageUrl == null) {
            return;
        }
        EntityTrackingIntentService.Companion companion = EntityTrackingIntentService.INSTANCE;
        String asin = contentMetadata.getAsin();
        if (asin == null) {
            asin = "";
        }
        String str = asin;
        EntityType entityType = Intrinsics.areEqual(contentMetadata.getAssetType(), "VIDEO") ? EntityType.VIDEO : EntityType.TRACK;
        EntityIdType entityIdType = EntityIdType.ASIN;
        List<ContentTier> supportedTiers = INSTANCE.getSupportedTiers(contentMetadata);
        Boolean isExplicit = contentMetadata.getIsExplicit();
        ParentalControls parentalControls = new ParentalControls(isExplicit == null ? true : isExplicit.booleanValue());
        String artistAsin = contentMetadata.getArtistAsin();
        String artistTitle = contentMetadata.getArtistTitle();
        String albumAsin = contentMetadata.getAlbumAsin();
        String albumTitle = contentMetadata.getAlbumTitle();
        int duration = contentMetadata.getDuration();
        List<AssetQuality> listConvertAssetQualities = BrushV2Converter.INSTANCE.convertAssetQualities(contentMetadata.getAssetQuality());
        PlaymodeEligibility playmodeEligibility = contentMetadata.getPlaymodeEligibility();
        boolean zAreEqual = playmodeEligibility == null ? false : Intrinsics.areEqual(playmodeEligibility.getIsOnDemandPlayable(), Boolean.TRUE);
        PlaymodeEligibility playmodeEligibility2 = contentMetadata.getPlaymodeEligibility();
        companion.processEntityVisit(context, new EntityVisitIntentInfo(title, str, entityIdType, entityType, imageUrl, didNavigateFromSearch, null, new EntityProperties(artistAsin, artistTitle, albumAsin, albumTitle, (String) null, supportedTiers, (ParentalControlsPerContentTier) null, parentalControls, (String) null, Boolean.valueOf(zAreEqual), Boolean.valueOf(playmodeEligibility2 == null ? false : Intrinsics.areEqual(playmodeEligibility2.getIsStationFromAnythingPlayable(), Boolean.TRUE)), (String) null, contentMetadata.getIsOwned(), Integer.valueOf(duration), listConvertAssetQualities, contentMetadata.getLanguageOfPerformances(), (String) null, (Boolean) null, (Boolean) null, 461072, (DefaultConstructorMarker) null)));
    }

    @JvmStatic
    public static final void sendEntityVisitIntentEvent(Context context, PodcastMetadata contentMetadata, boolean didNavigateFromSearch) {
        EntityType entityType;
        EntityIdType entityIdType;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        String title = contentMetadata.getTitle();
        String imageUrl = contentMetadata.getImageUrl();
        String target = contentMetadata.getTarget();
        if (title == null || imageUrl == null || target == null) {
            return;
        }
        String episodeIdFromDeeplink = Deeplinks.getEpisodeIdFromDeeplink(target);
        Intrinsics.checkNotNullExpressionValue(episodeIdFromDeeplink, "getEpisodeIdFromDeeplink(contentUrl)");
        if (episodeIdFromDeeplink.length() > 0) {
            entityType = EntityType.PODCAST_EPISODE;
            entityIdType = EntityIdType.PODCAST_EPISODE_ID;
        } else {
            entityType = EntityType.PODCAST_SHOW;
            entityIdType = EntityIdType.PODCAST_SHOW_ID;
        }
        String subTitle = contentMetadata.getSubTitle();
        String variantId = contentMetadata.getVariantId();
        Boolean bool = Boolean.FALSE;
        EntityTrackingIntentService.INSTANCE.processEntityVisit(context, new EntityVisitIntentInfo(title, contentMetadata.getCatalogId(), entityIdType, entityType, imageUrl, didNavigateFromSearch, target, new EntityProperties((String) null, (String) null, (String) null, (String) null, subTitle, (List) null, (ParentalControlsPerContentTier) null, (ParentalControls) null, variantId, bool, bool, (String) null, (Boolean) null, (Integer) null, (List) null, (List) null, (String) null, (Boolean) null, (Boolean) null, 522255, (DefaultConstructorMarker) null)));
    }

    public final List<ContentTier> getSupportedTiers(TrackMetadata contentMetadata) {
        List<ContentTier> listConvertFromContentAccessTypeSet;
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        Set<ContentAccessType> supportedTiers = contentMetadata.getSupportedTiers();
        if (supportedTiers == null || supportedTiers.isEmpty()) {
            MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(contentMetadata.getUri());
            if (track == null) {
                listConvertFromContentAccessTypeSet = null;
            } else {
                ContentTierUtils contentTierUtils = ContentTierUtils.INSTANCE;
                Set<ContentAccessType> supportedTiers2 = track.getSupportedTiers();
                if (supportedTiers2 == null) {
                    supportedTiers2 = SetsKt__SetsKt.emptySet();
                }
                listConvertFromContentAccessTypeSet = contentTierUtils.convertFromContentAccessTypeSet(supportedTiers2);
            }
            return listConvertFromContentAccessTypeSet == null ? CollectionsKt__CollectionsKt.emptyList() : listConvertFromContentAccessTypeSet;
        }
        return ContentTierUtils.INSTANCE.convertFromContentAccessTypeSet(supportedTiers);
    }

    @JvmStatic
    public static final void sendEntityVisitIntentEvent(Context context, StationItem stationItem, boolean didNavigateFromSearch) {
        Intrinsics.checkNotNullParameter(stationItem, "stationItem");
        if (context == null || stationItem.getKey() == null || stationItem.getExplicitMap() == null || stationItem.getTitle() == null || stationItem.getImageUrl() == null || stationItem.getSupportedTiers() == null) {
            return;
        }
        EntityTrackingIntentService.Companion companion = EntityTrackingIntentService.INSTANCE;
        String title = stationItem.getTitle();
        String key = stationItem.getKey();
        EntityType entityType = EntityType.STATION;
        EntityIdType entityIdType = EntityIdType.STATION_KEY;
        String imageUrl = stationItem.getImageUrl();
        ContentTierUtils contentTierUtils = ContentTierUtils.INSTANCE;
        Set<ContentAccessType> supportedTiers = stationItem.getSupportedTiers();
        Intrinsics.checkNotNullExpressionValue(supportedTiers, "stationItem.supportedTiers");
        List<ContentTier> listConvertFromContentAccessTypeSet = contentTierUtils.convertFromContentAccessTypeSet(supportedTiers);
        ParentalControlsPerContentTierUtils parentalControlsPerContentTierUtils = ParentalControlsPerContentTierUtils.INSTANCE;
        Map<ContentAccessType, Boolean> explicitMap = stationItem.getExplicitMap();
        Intrinsics.checkNotNullExpressionValue(explicitMap, "stationItem.explicitMap");
        EntityProperties entityProperties = new EntityProperties((String) null, (String) null, (String) null, (String) null, (String) null, listConvertFromContentAccessTypeSet, parentalControlsPerContentTierUtils.convertContentAccessTypeExplicitMap(explicitMap), (ParentalControls) null, (String) null, (Boolean) null, (Boolean) null, (String) null, (Boolean) null, (Integer) null, (List) null, (List) null, (String) null, (Boolean) null, (Boolean) null, 524063, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullExpressionValue(title, "title");
        Intrinsics.checkNotNullExpressionValue(key, "key");
        Intrinsics.checkNotNullExpressionValue(imageUrl, "imageUrl");
        companion.processEntityVisit(context, new EntityVisitIntentInfo(title, key, entityIdType, entityType, imageUrl, didNavigateFromSearch, null, entityProperties));
    }

    public final void sendEntityVisitIntentEvent(Context context, CatalogPlaylist playlist, boolean didNavigateFromSearch) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        if (context == null || playlist.getAsin() == null || playlist.getTitle() == null || playlist.getThumbnailArtUrl() == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (playlist.isFree()) {
            arrayList.add(ContentTier.FREE);
        }
        if (playlist.isFreeOnDemand()) {
            arrayList.add(ContentTier.FREE_ON_DEMAND_PLAYABLE);
        }
        arrayList.add(playlist.isPrime() ? ContentTier.PRIME : ContentTier.MUSIC_SUBSCRIPTION);
        EntityTrackingIntentService.Companion companion = EntityTrackingIntentService.INSTANCE;
        String title = playlist.getTitle();
        String asin = playlist.getAsin();
        EntityType entityType = EntityType.PLAYLIST;
        EntityIdType entityIdType = EntityIdType.ASIN;
        String thumbnailArtUrl = playlist.getThumbnailArtUrl();
        EntityProperties entityProperties = new EntityProperties((String) null, (String) null, (String) null, (String) null, (String) null, arrayList, (ParentalControlsPerContentTier) null, new ParentalControls(playlist.isExplicit()), (String) null, (Boolean) null, (Boolean) null, (String) null, (Boolean) null, (Integer) null, (List) null, (List) null, (String) null, (Boolean) null, (Boolean) null, 524063, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullExpressionValue(title, "title");
        Intrinsics.checkNotNullExpressionValue(asin, "asin");
        Intrinsics.checkNotNullExpressionValue(thumbnailArtUrl, "thumbnailArtUrl");
        companion.processEntityVisit(context, new EntityVisitIntentInfo(title, asin, entityIdType, entityType, thumbnailArtUrl, didNavigateFromSearch, null, entityProperties));
    }

    @JvmStatic
    public static final void sendEntityVisitEvent(Fragment fragment, String contentUrl, String title, String subtitle, String imageUrl, String variantId) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(contentUrl, "contentUrl");
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        if (StringsKt__StringsKt.contains$default((CharSequence) contentUrl, (CharSequence) DeeplinksUtils.PODCASTS_PATH_SEGMENT, false, 2, (Object) null) && (fragment instanceof SearchBrushFragment)) {
            SearchBrushFragment searchBrushFragment = (SearchBrushFragment) fragment;
            if (searchBrushFragment.getCurrentMode() == SearchExperienceMode.SEARCH_HISTORY || searchBrushFragment.getCurrentMode() == SearchExperienceMode.SEARCH_SUGGESTION || searchBrushFragment.getCurrentMode() == SearchExperienceMode.SEARCH_RESULT || searchBrushFragment.getCurrentMode() == SearchExperienceMode.SEE_ALL) {
                Uri uri = Uri.parse(contentUrl);
                String str = uri.getPathSegments().get(1);
                if (StringsKt__StringsKt.contains$default((CharSequence) contentUrl, (CharSequence) DeeplinksUtils.PODCAST_EPISODES_PATH_SEGMENT, false, 2, (Object) null)) {
                    EntityTrackingIntentUtil entityTrackingIntentUtil = INSTANCE;
                    Context context = searchBrushFragment.getContext();
                    PodcastEpisode podcastEpisode = new PodcastEpisode();
                    podcastEpisode.setPodcastShowId(str);
                    podcastEpisode.setPodcastEpisodeId(uri.getPathSegments().get(3));
                    podcastEpisode.setTitle(title);
                    podcastEpisode.setSubtitle(subtitle);
                    podcastEpisode.setArtworkUri(imageUrl);
                    podcastEpisode.setExplicit(uri.getBooleanQueryParameter(BrushV2Converter.KEY_IS_EXPLICIT, true));
                    Unit unit = Unit.INSTANCE;
                    entityTrackingIntentUtil.sendEntityVisitIntentEvent(context, podcastEpisode, contentUrl, true, variantId);
                    return;
                }
                EntityTrackingIntentUtil entityTrackingIntentUtil2 = INSTANCE;
                Context context2 = searchBrushFragment.getContext();
                PodcastShow podcastShow = new PodcastShow();
                podcastShow.setPodcastShowId(str);
                podcastShow.setTitle(title);
                podcastShow.setSubtitle(subtitle);
                podcastShow.setArtworkUri(imageUrl);
                podcastShow.setExplicit(uri.getBooleanQueryParameter(BrushV2Converter.KEY_IS_EXPLICIT, true));
                Unit unit2 = Unit.INSTANCE;
                entityTrackingIntentUtil2.sendEntityVisitIntentEvent(context2, podcastShow, contentUrl, true, variantId);
            }
        }
    }

    public final void sendEntityVisitIntentEvent(Context context, PodcastShow podcastShow, String url, boolean didNavigateFromSearch, String variantId) {
        Intrinsics.checkNotNullParameter(podcastShow, "podcastShow");
        Intrinsics.checkNotNullParameter(url, "url");
        if (context == null || podcastShow.getPodcastShowId() == null || podcastShow.getTitle() == null || podcastShow.getArtworkUri() == null) {
            return;
        }
        EntityTrackingIntentService.Companion companion = EntityTrackingIntentService.INSTANCE;
        String title = podcastShow.getTitle();
        Intrinsics.checkNotNull(title);
        String podcastShowId = podcastShow.getPodcastShowId();
        Intrinsics.checkNotNull(podcastShowId);
        EntityType entityType = EntityType.PODCAST_SHOW;
        EntityIdType entityIdType = EntityIdType.PODCAST_SHOW_ID;
        String artworkUri = podcastShow.getArtworkUri();
        Intrinsics.checkNotNull(artworkUri);
        companion.processEntityVisit(context, new EntityVisitIntentInfo(title, podcastShowId, entityIdType, entityType, artworkUri, didNavigateFromSearch, url, new EntityProperties((String) null, (String) null, (String) null, (String) null, podcastShow.getSubtitle(), CollectionsKt__CollectionsKt.emptyList(), (ParentalControlsPerContentTier) null, new ParentalControls(podcastShow.getIsExplicit()), variantId, (Boolean) null, (Boolean) null, (String) null, (Boolean) null, (Integer) null, (List) null, (List) null, (String) null, (Boolean) null, (Boolean) null, 523791, (DefaultConstructorMarker) null)));
    }

    public final void sendEntityVisitIntentEvent(Context context, PodcastEpisode podcastEpisode, String url, boolean didNavigateFromSearch, String variantId) {
        Intrinsics.checkNotNullParameter(podcastEpisode, "podcastEpisode");
        Intrinsics.checkNotNullParameter(url, "url");
        if (context == null || podcastEpisode.getPodcastEpisodeId() == null || podcastEpisode.getTitle() == null || podcastEpisode.getArtworkUri() == null) {
            return;
        }
        EntityTrackingIntentService.Companion companion = EntityTrackingIntentService.INSTANCE;
        String title = podcastEpisode.getTitle();
        Intrinsics.checkNotNull(title);
        String podcastEpisodeId = podcastEpisode.getPodcastEpisodeId();
        Intrinsics.checkNotNull(podcastEpisodeId);
        EntityType entityType = EntityType.PODCAST_EPISODE;
        EntityIdType entityIdType = EntityIdType.PODCAST_EPISODE_ID;
        String artworkUri = podcastEpisode.getArtworkUri();
        Intrinsics.checkNotNull(artworkUri);
        companion.processEntityVisit(context, new EntityVisitIntentInfo(title, podcastEpisodeId, entityIdType, entityType, artworkUri, didNavigateFromSearch, url, new EntityProperties((String) null, (String) null, (String) null, (String) null, podcastEpisode.getSubtitle(), CollectionsKt__CollectionsKt.emptyList(), (ParentalControlsPerContentTier) null, new ParentalControls(podcastEpisode.getIsExplicit()), variantId, (Boolean) null, (Boolean) null, (String) null, (Boolean) null, (Integer) null, (List) null, (List) null, (String) null, (Boolean) null, (Boolean) null, 523791, (DefaultConstructorMarker) null)));
    }

    public final void sendEntityRefreshIntentEvent(Context context, String entityId, EntityIdType entityIdType) {
        if (context == null || entityId == null || entityIdType == null) {
            return;
        }
        EntityTrackingIntentService.INSTANCE.processEntityRefresh(context, new EntityRefreshIntentInfo.Builder(null, null, null, null, 15, null).withEntity(entityId, entityIdType).build());
    }
}
