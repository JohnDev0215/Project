package com.amazon.podcast.views.horizontalRowItemsListView;

import Podcast.FollowInterface.v1_0.FollowWriteElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.BadgeElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.FollowElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElement;
import SOAAppSyncInterface.v1_0.WriteMethod;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.content.res.Resources;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.Queues;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.redhoodPlus.RedhoodPlusClientInterfaces;
import com.amazon.podcast.redhoodPlus.SearchRedhoodPlusClientInterfaces;
import com.amazon.podcast.views.Strings;
import com.amazon.podcast.views.compactDetailTemplate.CompactDetailTemplateMethods;
import com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateV2Methods;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
final class HorizontalRowItemsMethods {
    private static final Logger logger = LoggerFactory.getLogger("HorizontalRowItemsMethods");

    HorizontalRowItemsMethods() {
    }

    public static HorizontalItemElement convertHorizontalItemElement(final PodcastHorizontalRowItemModel model, final Context context, final Resources resources) {
        String metricsPreset = UiMetricMethods.getMetricsPreset(model.getViewReferenceId());
        String catalogId = model.getCatalogId();
        String podcastShowVariantId = model.getPodcastShowVariantId();
        String title = model.getTitle();
        String subTitle = model.getSubTitle();
        String imageUrl = model.getImageUrl();
        String strMarketplaceId = Podcast.getUserInfoProvider().marketplaceId();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(PodcastDetailTemplateV2Methods.createAndBindPodcastDetailTemplate(strMarketplaceId, catalogId, title, subTitle, imageUrl, resources));
        arrayList.addAll(UiMetricMethods.onClicked(metricsPreset));
        FollowElement followElement = followElement(catalogId, podcastShowVariantId, title, metricsPreset);
        return HorizontalItemElement.builder().withPrimaryText(title).withSecondaryText(subTitle).withTertiaryText(RedhoodPlusClientInterfaces.horizontalRowItemUpsellText(context, resources, model.getContentTraits(), model.getAvailableUpsells())).withImage(imageUrl).withFollow(followElement).withBadge(RedhoodPlusClientInterfaces.horizontalRowItemBadge(context, resources, model.getContentTraits(), model.getAvailableUpsells())).withOnItemSelected(arrayList).build();
    }

    public static HorizontalItemElement convertHorizontalItemElementForSearchResults(final PodcastHorizontalRowItemModel model, final Context context, final Resources resources) {
        String metricsPreset = UiMetricMethods.getMetricsPreset(model.getViewReferenceId());
        String catalogId = model.getCatalogId();
        model.getPodcastShowVariantId();
        String title = model.getTitle();
        String subTitle = model.getSubTitle();
        String imageUrl = model.getImageUrl();
        String strMarketplaceId = Podcast.getUserInfoProvider().marketplaceId();
        String musicTerritory = getMusicTerritory();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(PodcastDetailTemplateV2Methods.createAndBindPodcastDetailTemplate(strMarketplaceId, catalogId, title, subTitle, imageUrl, resources));
        arrayList.addAll(UiMetricMethods.onClicked(metricsPreset, UiMetricAttributes$ActionType.SELECT_PODCAST_SHOW, (UiMetricAttributes$PageType) null, (UiMetricAttributes$ContentName) null));
        String playbackMode = SearchRedhoodPlusClientInterfaces.getPlaybackMode(model.getContentTiers());
        return HorizontalItemElement.builder().withPrimaryText(title).withSecondaryText(subTitle).withTertiaryText(SearchRedhoodPlusClientInterfaces.horizontalRowItemUpsellText(resources, playbackMode)).withImage(imageUrl).withFollow(null).withBadge(SearchRedhoodPlusClientInterfaces.horizontalRowItemPodcastShowBadge(resources, playbackMode, SearchRedhoodPlusClientInterfaces.getTierBenefits(musicTerritory, model.getTierBenefits()))).withOnItemSelected(arrayList).build();
    }

    public static HorizontalItemElement convertEpisodeToHorizontalItemElementForSearchResults(final PodcastEpisodeHorizontalRowItemModel model, final Context context, final Resources resources) {
        String metricsPreset = UiMetricMethods.getMetricsPreset(model.getViewReferenceId());
        String catalogId = model.getCatalogId();
        String podcastShowCatalogId = model.getPodcastShowCatalogId();
        String title = model.getTitle();
        String imageUrl = model.getImageUrl();
        String strMarketplaceId = Podcast.getUserInfoProvider().marketplaceId();
        String musicTerritory = getMusicTerritory();
        ArrayList arrayList = new ArrayList();
        String playbackMode = SearchRedhoodPlusClientInterfaces.getPlaybackMode(model.getContentTiers());
        List<String> tierBenefits = SearchRedhoodPlusClientInterfaces.getTierBenefits(musicTerritory, model.getPodcastEpisodeTierBenefits());
        String availabilityDate = SearchRedhoodPlusClientInterfaces.getAvailabilityDate(model.getAvailabilityDateMap());
        String strHorizontalRowItemEpisodeSubtitle = SearchRedhoodPlusClientInterfaces.horizontalRowItemEpisodeSubtitle(resources, model.getSubtitle(), availabilityDate, playbackMode, Strings.getReadablePublishDate(Strings.getISOFormatString(model.getPublishDate()), resources));
        arrayList.addAll(CompactDetailTemplateMethods.createAndBindCompactDetailTemplate(strMarketplaceId, catalogId, podcastShowCatalogId, title, strHorizontalRowItemEpisodeSubtitle, imageUrl, resources));
        arrayList.addAll(UiMetricMethods.onClicked(metricsPreset, UiMetricAttributes$ActionType.SELECT_PODCAST_EPISODE, (UiMetricAttributes$PageType) null, (UiMetricAttributes$ContentName) null));
        BadgeElement badgeElementHorizontalRowItemEpisodeBadge = SearchRedhoodPlusClientInterfaces.horizontalRowItemEpisodeBadge(resources, playbackMode, availabilityDate, tierBenefits);
        return HorizontalItemElement.builder().withPrimaryText(title).withSecondaryText(strHorizontalRowItemEpisodeSubtitle).withBadge(badgeElementHorizontalRowItemEpisodeBadge).withTertiaryText(SearchRedhoodPlusClientInterfaces.horizontalRowItemUpsellText(resources, playbackMode)).withFollow(null).withImage(imageUrl).withOnItemSelected(arrayList).build();
    }

    private static FollowElement followElement(final String podcastId, final String podcastShowVariantId, final String podcastTitle, final String blockRef) {
        List<Method> listUpdateFollowMethods = updateFollowMethods(podcastId, podcastShowVariantId, podcastTitle, true);
        List<Method> listUpdateFollowMethods2 = updateFollowMethods(podcastId, podcastShowVariantId, podcastTitle, false);
        listUpdateFollowMethods.addAll(UiMetricMethods.onClicked(blockRef, UiMetricAttributes$ActionType.FOLLOW_PODCAST_SHOW, (UiMetricAttributes$PageType) null, (UiMetricAttributes$ContentName) null));
        listUpdateFollowMethods.addAll(DeeplinkMethods.syncPodcastMetadata(podcastId));
        listUpdateFollowMethods2.addAll(UiMetricMethods.onClicked(blockRef, UiMetricAttributes$ActionType.UNFOLLOW_PODCAST_SHOW, (UiMetricAttributes$PageType) null, (UiMetricAttributes$ContentName) null));
        listUpdateFollowMethods2.addAll(DeeplinkMethods.syncPodcastMetadata(podcastId));
        return FollowElement.builder().withId(podcastId).withOnFollow(listUpdateFollowMethods).withOnUnFollow(listUpdateFollowMethods2).build();
    }

    private static List<Method> updateFollowMethods(final String podcastId, final String podcastShowVariantId, final String podcasTitle, final boolean isGoingToFollow) {
        WriteMethod writeMethodBuild = WriteMethod.builder().withElement(Collections.singletonList(FollowWriteElement.builder().withId(podcastId).withPodcastShowVariantId(podcastShowVariantId).withTitle(podcasTitle).withPublisher("").withImage("").withDescription("").withFollowed(isGoingToFollow).build())).withCondition(null).withForced(Boolean.FALSE).withQueue(Queues.followSync()).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(writeMethodBuild);
        return arrayList;
    }

    private static String getMusicTerritory() {
        if (Podcast.getUserInfoProvider().musicTerritory() == null) {
            try {
                return AccountManagerSingleton.get().getUser().getMusicTerritoryOfResidence();
            } catch (Exception e) {
                logger.error("Failed to retrieve MusicTerritory. ", (Throwable) e);
                return "";
            }
        }
        return Podcast.getUserInfoProvider().musicTerritory();
    }
}
