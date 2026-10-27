package com.amazon.podcast.views.episodeRowItemsListView;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.PlaybackInterface.v1_0.PauseMediaMethod;
import Podcast.PlaybackInterface.v1_0.ResumeMediaMethod;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.BookmarkElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.CompletedElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.DownloadElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import SOACoreInterface.v1_0.Method;
import UpsellInterface.v1_0.ContentTrait;
import android.content.Context;
import android.content.res.Resources;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.Queues;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.redhoodPlus.MetricsEntityIds;
import com.amazon.podcast.redhoodPlus.Playable;
import com.amazon.podcast.redhoodPlus.PlaybackModes;
import com.amazon.podcast.redhoodPlus.RedhoodPlusClientInterfaces;
import com.amazon.podcast.skills.EpisodeOptionsElements;
import com.amazon.podcast.views.Strings;
import com.amazon.podcast.views.compactDetailTemplate.CompactDetailTemplateMethods;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class EpisodeRowItemsMethods {

    /* JADX INFO: renamed from: com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemsMethods$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$podcast$metrics$UiMetricAttributes$PageType;

        static {
            int[] iArr = new int[UiMetricAttributes$PageType.values().length];
            $SwitchMap$com$amazon$podcast$metrics$UiMetricAttributes$PageType = iArr;
            try {
                iArr[UiMetricAttributes$PageType.PODCASTS_LIBRARY_DOWNLOADS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$podcast$metrics$UiMetricAttributes$PageType[UiMetricAttributes$PageType.PODCASTS_LIBRARY_PLAYLIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$podcast$metrics$UiMetricAttributes$PageType[UiMetricAttributes$PageType.PODCASTS_JUMP_BACK_IN_EPISODES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$podcast$metrics$UiMetricAttributes$PageType[UiMetricAttributes$PageType.PODCASTS_SAVED_EPISODES.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$podcast$metrics$UiMetricAttributes$PageType[UiMetricAttributes$PageType.PODCASTS_LIBRARY_LATEST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$podcast$metrics$UiMetricAttributes$PageType[UiMetricAttributes$PageType.PODCAST_SHOW_DETAIL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$podcast$metrics$UiMetricAttributes$PageType[UiMetricAttributes$PageType.PODCASTS_HOME.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static UiMetricAttributes$ContentName getContentName(final UiMetricAttributes$PageType pageType) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$podcast$metrics$UiMetricAttributes$PageType[pageType.ordinal()]) {
            case 1:
                return UiMetricAttributes$ContentName.PODCASTS_LIBRARY_DOWNLOADS;
            case 2:
                return UiMetricAttributes$ContentName.PODCASTS_LIBRARY_PLAYLIST;
            case 3:
                return UiMetricAttributes$ContentName.PODCASTS_JUMP_BACK_IN_EPISODES;
            case 4:
                return UiMetricAttributes$ContentName.PODCASTS_SAVED_EPISODES;
            case 5:
                return UiMetricAttributes$ContentName.PODCASTS_LIBRARY_LATEST;
            case 6:
                return UiMetricAttributes$ContentName.PODCAST_SHOW_DETAIL;
            case 7:
                return UiMetricAttributes$ContentName.PODCASTS_HOME;
            default:
                return null;
        }
    }

    public static EpisodeRowItemElement convertEpisodeRowItemElement(final EpisodeRowItemModel model, final UiMetricAttributes$PageType pageType, final Resources resources, final Context context) {
        CompletedElement completedElement;
        DownloadElement downloadElement;
        BookmarkElement bookmarkElement;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        ArrayList arrayList;
        ArrayList arrayList2;
        String metricsPreset = UiMetricMethods.getMetricsPreset(model.getViewReferenceId());
        String catalogId = model.getCatalogId();
        String podcastShowCatalogId = model.getPodcastShowCatalogId();
        String title = model.getTitle();
        String subTitle = model.getSubTitle();
        String imageUrl = model.getImageUrl();
        long millis = TimeUnit.SECONDS.toMillis(model.getDurationSeconds());
        String readablePublishDate = Strings.getReadablePublishDate(model.getPublishDate(), resources);
        String strMarketplaceId = Podcast.getUserInfoProvider().marketplaceId();
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(CompactDetailTemplateMethods.createAndBindCompactDetailTemplate(strMarketplaceId, catalogId, podcastShowCatalogId, title, subTitle, imageUrl, resources));
        EpisodeOptionsElement episodeOptionsElementEpisodeOptionsElementsWithHeader = null;
        arrayList3.addAll(UiMetricMethods.onClicked(metricsPreset, UiMetricAttributes$ActionType.SELECT_PODCAST_EPISODE, pageType, (UiMetricAttributes$ContentName) null));
        Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled();
        String strNormalizeWithAvailabilityDate = PlaybackModes.normalizeWithAvailabilityDate(model.getContentTraits(), model.getAvailabilityDate(), model.getPlaybackMode());
        boolean zIsPlayable = Playable.isPlayable(model.getContentTraits(), model.getPlaybackMode(), model.getAvailabilityDate());
        ArrayList arrayList4 = new ArrayList();
        if (model.getContentTraits() != null) {
            arrayList4.addAll(model.getContentTraits());
        }
        if (RedhoodPlusClientInterfaces.hasAvailabilityDatePassed(model.getAvailabilityDate())) {
            arrayList4.remove(ContentTrait.PRE_RELEASE);
        }
        BookmarkElement bookmarkElementBuild = zIsPlayable ? BookmarkElement.builder().withId(catalogId).withTotalDurationMilliseconds(Long.valueOf(millis)).build() : null;
        ArrayList arrayList5 = new ArrayList();
        arrayList5.addAll(DeeplinkMethods.download(catalogId));
        arrayList5.addAll(UiMetricMethods.onClicked(metricsPreset, UiMetricAttributes$ActionType.DOWNLOAD_PODCAST_EPISODE, pageType, (UiMetricAttributes$ContentName) null));
        DownloadElement downloadElementBuild = zIsPlayable ? DownloadElement.builder().withId(catalogId).withOnDownload(arrayList5).build() : null;
        CompletedElement completedElementBuild = CompletedElement.builder().withId(catalogId).build();
        if (zIsPlayable) {
            completedElement = completedElementBuild;
            arrayList = arrayList3;
            downloadElement = downloadElementBuild;
            bookmarkElement = bookmarkElementBuild;
            arrayList2 = arrayList4;
            str = strNormalizeWithAvailabilityDate;
            str2 = imageUrl;
            str3 = subTitle;
            str4 = podcastShowCatalogId;
            str5 = title;
            str6 = catalogId;
            str7 = metricsPreset;
            episodeOptionsElementEpisodeOptionsElementsWithHeader = new EpisodeOptionsElements().episodeOptionsElementsWithHeader(metricsPreset, catalogId, podcastShowCatalogId, MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), catalogId, model.getPodcastEpisodeVariantId()), MetricsEntityIds.podcastShow(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), podcastShowCatalogId, model.getPodcastShowVariantId()), imageUrl, title, subTitle, pageType, millis, readablePublishDate, resources);
        } else {
            completedElement = completedElementBuild;
            downloadElement = downloadElementBuild;
            bookmarkElement = bookmarkElementBuild;
            str = strNormalizeWithAvailabilityDate;
            str2 = imageUrl;
            str3 = subTitle;
            str4 = podcastShowCatalogId;
            str5 = title;
            str6 = catalogId;
            str7 = metricsPreset;
            arrayList = arrayList3;
            arrayList2 = arrayList4;
        }
        EpisodeOptionsElement episodeOptionsElement = episodeOptionsElementEpisodeOptionsElementsWithHeader;
        List<Method> listPlayEpisode = playEpisode(str6, str4, str5, str3, str7, resources, pageType);
        String str8 = str6;
        String str9 = str3;
        String str10 = str5;
        String str11 = str7;
        List<Method> listUpsellMethods = RedhoodPlusClientInterfaces.upsellMethods(context, Deeplinks.episode(str4, str8, str9, str10), model.getAvailableUpsells(), str11, pageType);
        return EpisodeRowItemElement.builder().withMediaId(str8).withImage(str2).withPrimaryText(str10).withSecondaryText(str9).withTertiaryText(readablePublishDate).withOnItemSelected(arrayList).withBookmark(bookmarkElement).withOnPlaySelected(listPlayEpisode).withOnPauseSelected(pauseEpisode(str11, pageType)).withOnResumeSelected(resumeEpisode(str11, pageType)).withCompleted(completedElement).withDownload(downloadElement).withEpisodeOptions(episodeOptionsElement).withButton(RedhoodPlusClientInterfaces.episodeRowItemUpsellButton(context, arrayList2, listUpsellMethods)).withImageBadge(RedhoodPlusClientInterfaces.episodeRowItemImageBadge(context, arrayList2, listUpsellMethods)).withDescriptionBadge(RedhoodPlusClientInterfaces.episodeRowItemDescriptionBadge(context, arrayList2, listUpsellMethods)).withPlaybackMode(str).build();
    }

    private static List<Method> playEpisode(final String episodeId, final String podcastId, final String title, final String podcastTitle, final String blockRef, final Resources resources, final UiMetricAttributes$PageType pageType) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.episodePlay(episodeId, podcastId, title, podcastTitle, resources));
        arrayList.addAll(UiMetricMethods.onClicked(blockRef, UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE, pageType, (UiMetricAttributes$ContentName) null));
        return arrayList;
    }

    private static List<Method> pauseEpisode(final String blockRef, final UiMetricAttributes$PageType pageType) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(PauseMediaMethod.builder().withQueue(Queues.playback()).withForced(Boolean.FALSE).build());
        arrayList.addAll(UiMetricMethods.onClicked(blockRef, UiMetricAttributes$ActionType.PLAY_PAUSE_PODCAST_EPISODE, pageType, (UiMetricAttributes$ContentName) null));
        return arrayList;
    }

    private static List<Method> resumeEpisode(final String blockRef, final UiMetricAttributes$PageType pageType) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ResumeMediaMethod.builder().withQueue(Queues.playback()).withForced(Boolean.FALSE).build());
        arrayList.addAll(UiMetricMethods.onClicked(blockRef, UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE, pageType, (UiMetricAttributes$ContentName) null));
        return arrayList;
    }
}
