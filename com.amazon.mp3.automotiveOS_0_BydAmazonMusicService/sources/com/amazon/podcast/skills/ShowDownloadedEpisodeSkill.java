package com.amazon.podcast.skills;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.BadgeElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.ButtonElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.CompactDetailTemplate;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.HeaderElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.ShareElement;
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
import com.amazon.podcast.downloads.Download;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class ShowDownloadedEpisodeSkill {
    public static List<Method> prepareInvoke(final Resources resources, final Context context, final Download download) {
        Resources resources2;
        DownloadCompactDetailElements downloadCompactDetailElements = new DownloadCompactDetailElements();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.podcast(download, resources));
        boolean zIsRedhoodPlusUXEnabled = Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled();
        UiMetricAttributes$PageType uiMetricAttributes$PageType = UiMetricAttributes$PageType.PODCAST_EPISODE_DETAIL;
        UiMetricAttributes$ActionType uiMetricAttributes$ActionType = UiMetricAttributes$ActionType.GO_PODCAST_SHOW;
        UiMetricAttributes$EntityType uiMetricAttributes$EntityType = UiMetricAttributes$EntityType.PODCAST_EPISODE;
        UiMetricAttributes$EntityIdType uiMetricAttributes$EntityIdType = UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID;
        String strPodcastEpisode = MetricsEntityIds.podcastEpisode(zIsRedhoodPlusUXEnabled, download.getId(), download.getPodcastEpisodeVariantId());
        UiMetricAttributes$ContentName uiMetricAttributes$ContentName = UiMetricAttributes$ContentName.PODCASTS_LIBRARY_EPISODE_DETAIL;
        arrayList.addAll(UiMetricMethods.onClicked(uiMetricAttributes$PageType, uiMetricAttributes$ActionType, uiMetricAttributes$EntityType, uiMetricAttributes$EntityIdType, strPodcastEpisode, (Integer) null, uiMetricAttributes$ContentName));
        boolean zIsPlayable = Playable.isPlayable(download.getContentTraits(), download.getPlaybackMode(), download.getAvailabilityDate());
        String strNormalizeWithAvailabilityDate = PlaybackModes.normalizeWithAvailabilityDate(download.getContentTraits(), download.getAvailabilityDate(), download.getPlaybackMode());
        ArrayList arrayList2 = new ArrayList();
        if (download.getContentTraits() != null) {
            arrayList2.addAll(download.getContentTraits());
        }
        if (RedhoodPlusClientInterfaces.hasAvailabilityDatePassed(download.getAvailabilityDate())) {
            arrayList2.remove(ContentTrait.PRE_RELEASE);
        }
        HeaderElement headerElementBuild = HeaderElement.builder().withImage(download.getImage()).withPrimaryText(download.getTitle()).withSecondaryText(download.getPodcastTitle()).withOnSecondaryTextSelected(arrayList).withTertiaryText(RedhoodPlusClientInterfaces.getDisplayDate(resources, download.getAvailableUpsells(), arrayList2, download.getPublishDate(), download.getAvailabilityDate())).withLabel(resources.getString(R$string.podcast_header_label_episode)).build();
        Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled();
        Set<String> setBenefits = Podcast.getUserInfoProvider().benefits();
        List<Method> listUpsellMethods = RedhoodPlusClientInterfaces.upsellMethods(context, Deeplinks.episode(download.getPodcastId(), download.getId(), download.getPodcastTitle(), download.getTitle()), download.getAvailableUpsells(), uiMetricAttributes$PageType, uiMetricAttributes$ContentName, MetricsEntityIds.podcastEpisode(zIsRedhoodPlusUXEnabled, download.getId(), download.getPodcastEpisodeVariantId()), uiMetricAttributes$EntityType, uiMetricAttributes$EntityIdType);
        BadgeElement badgeElementCompactDetailBadge = RedhoodPlusClientInterfaces.compactDetailBadge(context, setBenefits, arrayList2, listUpsellMethods);
        ButtonElement buttonElementCompactDetailUpsellButton = RedhoodPlusClientInterfaces.compactDetailUpsellButton(context, arrayList2, listUpsellMethods);
        String strCompactDetailTemplateUpsellText = RedhoodPlusClientInterfaces.compactDetailTemplateUpsellText(context, setBenefits, download.getAvailableUpsells(), arrayList2);
        LatencyMetricElement latencyMetricElementBuild = LatencyMetricElement.builder().withPageType(LatencyMetricAttributes$PageType.PODCAST_EPISODE_DETAIL.toString()).build();
        CompactDetailTemplate.Builder builderWithText = CompactDetailTemplate.builder().withBackgroundImage(download.getImage()).withHeader(headerElementBuild).withDescription(download.getDescription()).withBackgroundImage(download.getImage()).withSave(downloadCompactDetailElements.saveElement(download)).withBookmark(downloadCompactDetailElements.bookmarkElement(download)).withPlaybackMode(strNormalizeWithAvailabilityDate).withBadge(badgeElementCompactDetailBadge).withButton(buttonElementCompactDetailUpsellButton).withText(strCompactDetailTemplateUpsellText);
        ShareElement shareElement = null;
        CompactDetailTemplate.Builder builderWithDownload = builderWithText.withDownload(zIsPlayable ? downloadCompactDetailElements.downloadElement(download) : null);
        if (zIsPlayable) {
            resources2 = resources;
        } else {
            resources2 = resources;
            shareElement = downloadCompactDetailElements.shareElement(download, resources2);
        }
        return Collections.singletonList(CreateTemplateMethod.builder().withQueue(Queues.template()).withTemplate(builderWithDownload.withShare(shareElement).withEpisodeOptions(downloadCompactDetailElements.episodeOptionsElementWithoutHeader(download, resources2, zIsPlayable)).withOnCreated(Collections.emptyList()).withOnViewed(UiMetricMethods.onPageViewed(uiMetricAttributes$PageType, UiMetricAttributes$DetailPageItemIdType.PODCAST_EPISODE_ID, MetricsEntityIds.podcastEpisode(zIsRedhoodPlusUXEnabled, download.getId(), download.getPodcastEpisodeVariantId()))).withLatencyMetricElement(latencyMetricElementBuild).build()).withScreenMode(TemplateScreenMode.templateList).build());
    }
}
