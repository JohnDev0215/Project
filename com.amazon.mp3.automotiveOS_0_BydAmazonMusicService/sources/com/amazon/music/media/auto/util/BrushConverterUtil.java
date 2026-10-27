package com.amazon.music.media.auto.util;

import com.amazon.music.ContentAccessType;
import com.amazon.music.models.AssetQuality;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.musicensembleservice.brush.ParentalControls;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: compiled from: BrushConverterUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H&J\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H&J\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H&J(\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00100\r2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e0\rH&J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00022\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0002H&J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00140\u00022\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0002H&J\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u0019H&J\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u001dH&J\u0018\u0010 \u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0003H&J\u0018\u0010!\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0003H&J(\u0010%\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u0003H&J(\u0010&\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u0003H&¨\u0006'"}, d2 = {"Lcom/amazon/music/media/auto/util/BrushConverterUtil;", "", "", "", "tiers", "", "Lcom/amazon/music/ContentAccessType;", "convertTiers", "eligibilityFlags", "Lcom/amazon/music/platform/data/subscription/SubscriptionType;", "convertContentTiers", "onDemandFlags", "convertOnDemandTiers", "", "Lcom/amazon/musicensembleservice/brush/ParentalControls;", "parentalControlsMap", "", "convertExplicitMap", "Lcom/amazon/musicensembleservice/brush/AssetQuality;", "museAssetQualityList", "Lcom/amazon/music/models/AssetQuality;", "convertAssetQuality", "Lcom/amazon/music/page/api/model/AssetQuality;", "pageAssetQualityList", "convertAssetQualityPage", "Lcom/amazon/music/page/api/model/PlaymodeEligibility;", "playmodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "convertPlaymodeEligibility", "Lcom/amazon/music/search/PlaymodeEligibility;", "showId", "showTitle", "getPodcastShowTarget", "getPodcastShowBackgroundTarget", "showCatalogId", "catalogId", "title", "getPodcastEpisodeTarget", "getPodcastEpisodeBackgroundTarget", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public interface BrushConverterUtil {
    List<AssetQuality> convertAssetQuality(List<? extends com.amazon.musicensembleservice.brush.AssetQuality> museAssetQualityList);

    List<AssetQuality> convertAssetQualityPage(List<? extends com.amazon.music.page.api.model.AssetQuality> pageAssetQualityList);

    Set<SubscriptionType> convertContentTiers(List<String> eligibilityFlags);

    Map<ContentAccessType, Boolean> convertExplicitMap(Map<String, ? extends ParentalControls> parentalControlsMap);

    Set<SubscriptionType> convertOnDemandTiers(List<String> onDemandFlags);

    PlaymodeEligibility convertPlaymodeEligibility(com.amazon.music.page.api.model.PlaymodeEligibility playmodeEligibility);

    PlaymodeEligibility convertPlaymodeEligibility(com.amazon.music.search.PlaymodeEligibility playmodeEligibility);

    Set<ContentAccessType> convertTiers(List<String> tiers);

    String getPodcastEpisodeBackgroundTarget(String showCatalogId, String catalogId, String showTitle, String title);

    String getPodcastEpisodeTarget(String showCatalogId, String catalogId, String showTitle, String title);

    String getPodcastShowBackgroundTarget(String showId, String showTitle);

    String getPodcastShowTarget(String showId, String showTitle);
}
