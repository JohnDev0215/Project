package com.amazon.podcast.skills;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.BadgeElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.ButtonElement;
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
import com.amazon.podcast.metrics.latency.LatencyMetricAttributes$PageType;
import com.amazon.podcast.redhoodPlus.MetricsEntityIds;
import com.amazon.podcast.redhoodPlus.Playable;
import com.amazon.podcast.redhoodPlus.PlaybackModes;
import com.amazon.podcast.redhoodPlus.RedhoodPlusClientInterfaces;
import com.amazon.podcast.save.Save;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class ShowSavedEpisodeSkill {
    public static List<Method> prepareInvoke(final Resources resources, final Context context, final Save save) {
        SaveCompactDetailElements saveCompactDetailElements = new SaveCompactDetailElements();
        boolean zIsRedhoodPlusMetricsEnabled = Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.podcast(save, resources));
        UiMetricAttributes$PageType uiMetricAttributes$PageType = UiMetricAttributes$PageType.PODCAST_EPISODE_DETAIL;
        UiMetricAttributes$ActionType uiMetricAttributes$ActionType = UiMetricAttributes$ActionType.GO_PODCAST_SHOW;
        UiMetricAttributes$EntityType uiMetricAttributes$EntityType = UiMetricAttributes$EntityType.PODCAST_EPISODE;
        UiMetricAttributes$EntityIdType uiMetricAttributes$EntityIdType = UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID;
        String strPodcastEpisode = MetricsEntityIds.podcastEpisode(zIsRedhoodPlusMetricsEnabled, save.getId(), save.getPodcastEpisodeVariantId());
        UiMetricAttributes$ContentName uiMetricAttributes$ContentName = UiMetricAttributes$ContentName.PODCASTS_LIBRARY_EPISODE_DETAIL;
        arrayList.addAll(UiMetricMethods.onClicked(uiMetricAttributes$PageType, uiMetricAttributes$ActionType, uiMetricAttributes$EntityType, uiMetricAttributes$EntityIdType, strPodcastEpisode, (Integer) null, uiMetricAttributes$ContentName));
        boolean zIsPlayable = Playable.isPlayable(save.getContentTraits(), save.getPlaybackMode(), save.getAvailabilityDate());
        String strNormalizeWithAvailabilityDate = PlaybackModes.normalizeWithAvailabilityDate(save.getContentTraits(), save.getAvailabilityDate(), save.getPlaybackMode());
        ArrayList arrayList2 = new ArrayList();
        if (save.getContentTraits() != null) {
            arrayList2.addAll(save.getContentTraits());
        }
        if (RedhoodPlusClientInterfaces.hasAvailabilityDatePassed(save.getAvailabilityDate())) {
            arrayList2.remove(ContentTrait.PRE_RELEASE);
        }
        HeaderElement headerElementBuild = HeaderElement.builder().withImage(save.getImage()).withPrimaryText(save.getTitle()).withSecondaryText(save.getPodcastTitle()).withTertiaryText(RedhoodPlusClientInterfaces.getDisplayDate(resources, save.getAvailableUpsells(), arrayList2, save.getPublishDate(), save.getAvailabilityDate())).withOnSecondaryTextSelected(arrayList).withLabel(resources.getString(R$string.podcast_header_label_episode)).build();
        Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled();
        Set<String> setBenefits = Podcast.getUserInfoProvider().benefits();
        List<Method> listUpsellMethods = RedhoodPlusClientInterfaces.upsellMethods(context, Deeplinks.episode(save.getPodcastId(), save.getId(), save.getPodcastTitle(), save.getTitle()), save.getAvailableUpsells(), uiMetricAttributes$PageType, uiMetricAttributes$ContentName, MetricsEntityIds.podcastEpisode(zIsRedhoodPlusMetricsEnabled, save.getId(), save.getPodcastEpisodeVariantId()), uiMetricAttributes$EntityType, uiMetricAttributes$EntityIdType);
        BadgeElement badgeElementCompactDetailBadge = RedhoodPlusClientInterfaces.compactDetailBadge(context, setBenefits, arrayList2, listUpsellMethods);
        ButtonElement buttonElementCompactDetailUpsellButton = RedhoodPlusClientInterfaces.compactDetailUpsellButton(context, arrayList2, listUpsellMethods);
        return Collections.singletonList(CreateTemplateMethod.builder().withQueue(Queues.template()).withTemplate(CompactDetailTemplate.builder().withBackgroundImage(save.getImage()).withHeader(headerElementBuild).withDescription(save.getDescription()).withBackgroundImage(save.getImage()).withSave(saveCompactDetailElements.saveElement(save)).withBookmark(saveCompactDetailElements.bookmarkElement(save, resources)).withPlaybackMode(strNormalizeWithAvailabilityDate).withBadge(badgeElementCompactDetailBadge).withButton(buttonElementCompactDetailUpsellButton).withText(RedhoodPlusClientInterfaces.compactDetailTemplateUpsellText(context, setBenefits, save.getAvailableUpsells(), arrayList2)).withDownload(zIsPlayable ? saveCompactDetailElements.downloadElement(save) : null).withShare(zIsPlayable ? null : saveCompactDetailElements.shareElement(save, resources)).withEpisodeOptions(saveCompactDetailElements.episodeOptionsElementWithoutHeader(save, resources, zIsPlayable)).withOnCreated(Collections.emptyList()).withOnViewed(UiMetricMethods.onPageViewed(uiMetricAttributes$PageType, UiMetricAttributes$DetailPageItemIdType.PODCAST_EPISODE_ID, MetricsEntityIds.podcastEpisode(zIsRedhoodPlusMetricsEnabled, save.getId(), save.getPodcastEpisodeVariantId()))).withPlaybackMode(strNormalizeWithAvailabilityDate).withLatencyMetricElement(LatencyMetricElement.builder().withPageType(LatencyMetricAttributes$PageType.PODCAST_EPISODE_DETAIL.toString()).build()).build()).withScreenMode(TemplateScreenMode.templateList).build());
    }
}
