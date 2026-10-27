package com.amazon.podcast.skills;

import Podcast.JumpBackInInterface.v1_0.JumpBackInCacheItemElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.BadgeElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.CompactDetailTemplate;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.HeaderElement;
import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.CreateTemplateMethod;
import SOATemplateListInterface.v1_0.TemplateScreenMode;
import UpsellInterface.v1_0.ContentTrait;
import android.content.Context;
import android.content.res.Resources;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.Queues;
import com.amazon.podcast.R$string;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$DetailPageItemIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.redhoodPlus.MetricsEntityIds;
import com.amazon.podcast.redhoodPlus.Playable;
import com.amazon.podcast.redhoodPlus.PlaybackModes;
import com.amazon.podcast.redhoodPlus.RedhoodPlusClientInterfaces;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class ShowJumpBackInEpisodeSkill {
    public static List<Method> prepareInvoke(final Resources resources, final Context context, final JumpBackInCacheItemElement jumpBackIn) {
        JumpBackInCompactDetailElements jumpBackInCompactDetailElements = new JumpBackInCompactDetailElements();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.podcast(jumpBackIn, resources));
        boolean zIsRedhoodPlusMetricsEnabled = Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled();
        UiMetricAttributes$PageType uiMetricAttributes$PageType = UiMetricAttributes$PageType.PODCAST_EPISODE_DETAIL;
        UiMetricAttributes$ActionType uiMetricAttributes$ActionType = UiMetricAttributes$ActionType.GO_PODCAST_SHOW;
        UiMetricAttributes$EntityType uiMetricAttributes$EntityType = UiMetricAttributes$EntityType.PODCAST_EPISODE;
        UiMetricAttributes$EntityIdType uiMetricAttributes$EntityIdType = UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID;
        String strPodcastEpisode = MetricsEntityIds.podcastEpisode(zIsRedhoodPlusMetricsEnabled, jumpBackIn.getEpisodeId(), jumpBackIn.getPodcastEpisodeVariantId());
        UiMetricAttributes$ContentName uiMetricAttributes$ContentName = UiMetricAttributes$ContentName.PODCASTS_LIBRARY_EPISODE_DETAIL;
        arrayList.addAll(UiMetricMethods.onClicked(uiMetricAttributes$PageType, uiMetricAttributes$ActionType, uiMetricAttributes$EntityType, uiMetricAttributes$EntityIdType, strPodcastEpisode, (Integer) null, uiMetricAttributes$ContentName));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(UiMetricMethods.onPageViewed(uiMetricAttributes$PageType, UiMetricAttributes$DetailPageItemIdType.PODCAST_EPISODE_ID, MetricsEntityIds.podcastEpisode(zIsRedhoodPlusMetricsEnabled, jumpBackIn.getEpisodeId(), jumpBackIn.getPodcastEpisodeVariantId())));
        HeaderElement headerElementBuild = HeaderElement.builder().withImage(jumpBackIn.getImage()).withPrimaryText(jumpBackIn.getTitle()).withSecondaryText(jumpBackIn.getPodcastTitle()).withOnSecondaryTextSelected(arrayList).withLabel(resources.getString(R$string.podcast_header_label_episode)).build();
        Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled();
        boolean zIsPlayable = Playable.isPlayable(jumpBackIn.getContentTraits(), jumpBackIn.getPlaybackMode(), jumpBackIn.getAvailabilityDate());
        String strNormalizeWithAvailabilityDate = PlaybackModes.normalizeWithAvailabilityDate(jumpBackIn.getContentTraits(), jumpBackIn.getAvailabilityDate(), jumpBackIn.getPlaybackMode());
        Set<String> setBenefits = Podcast.getUserInfoProvider().benefits();
        List<Method> listUpsellMethods = RedhoodPlusClientInterfaces.upsellMethods(context, Deeplinks.episode(jumpBackIn.getPodcastId(), jumpBackIn.getEpisodeId(), jumpBackIn.getPodcastTitle(), jumpBackIn.getTitle()), jumpBackIn.getAvailableUpsells(), uiMetricAttributes$PageType, uiMetricAttributes$ContentName, MetricsEntityIds.podcastEpisode(zIsRedhoodPlusMetricsEnabled, jumpBackIn.getEpisodeId(), jumpBackIn.getPodcastEpisodeVariantId()), uiMetricAttributes$EntityType, uiMetricAttributes$EntityIdType);
        ArrayList arrayList3 = new ArrayList();
        if (jumpBackIn.getContentTraits() != null) {
            arrayList3.addAll(jumpBackIn.getContentTraits());
        }
        if (RedhoodPlusClientInterfaces.hasAvailabilityDatePassed(jumpBackIn.getAvailabilityDate())) {
            arrayList3.remove(ContentTrait.PRE_RELEASE);
        }
        BadgeElement badgeElementCompactDetailBadge = RedhoodPlusClientInterfaces.compactDetailBadge(context, setBenefits, arrayList3, listUpsellMethods);
        return Collections.singletonList(CreateTemplateMethod.builder().withQueue(Queues.template()).withTemplate(CompactDetailTemplate.builder().withBackgroundImage(jumpBackIn.getImage()).withHeader(headerElementBuild).withDescription(jumpBackIn.getDescription()).withBackgroundImage(jumpBackIn.getImage()).withSave(jumpBackInCompactDetailElements.saveElement(jumpBackIn)).withBookmark(jumpBackInCompactDetailElements.jumpBackInElement(jumpBackIn, resources)).withPlaybackMode(strNormalizeWithAvailabilityDate).withBadge(badgeElementCompactDetailBadge).withButton(RedhoodPlusClientInterfaces.compactDetailUpsellButton(context, arrayList3, listUpsellMethods)).withText(RedhoodPlusClientInterfaces.compactDetailTemplateUpsellText(context, setBenefits, jumpBackIn.getAvailableUpsells(), arrayList3)).withDownload(zIsPlayable ? jumpBackInCompactDetailElements.downloadElement(jumpBackIn) : null).withShare(zIsPlayable ? null : jumpBackInCompactDetailElements.shareElement(jumpBackIn, resources)).withEpisodeOptions(jumpBackInCompactDetailElements.episodeOptionsElementWithoutHeader(jumpBackIn, resources, zIsPlayable)).withOnCreated(Collections.emptyList()).withOnViewed((List<Method>) arrayList2).build()).withScreenMode(TemplateScreenMode.templateList).build());
    }
}
