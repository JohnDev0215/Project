package com.amazon.podcast.views.verticalItemView;

import Podcast.Touch.BookmarkElementInterface.v1_0.BookmarkElement;
import Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElement;
import SOACoreInterface.v1_0.Method;
import UpsellInterface.v1_0.ContentTrait;
import android.content.Context;
import android.content.res.Resources;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.redhoodPlus.Playable;
import com.amazon.podcast.redhoodPlus.PlaybackModes;
import com.amazon.podcast.views.compactDetailTemplate.CompactDetailTemplateMethods;
import com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateV2Methods;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class VerticalItemMethods {
    public static VerticalItemElement convertEpisodeToVerticalItemElement(final VerticalItemModel model, final UiMetricAttributes$PageType pageType, final Resources resources, final Context context) {
        String podcastEpisodeCatalogId = model.getPodcastEpisodeCatalogId();
        String podcastShowCatalogId = model.getPodcastShowCatalogId();
        String title = model.getTitle();
        String subTitle = model.getSubTitle();
        String imageUrl = model.getImageUrl();
        String strMarketplaceId = Podcast.getUserInfoProvider().marketplaceId();
        PlaybackModes.normalizeWithAvailabilityDate(model.getContentTraits(), model.getAvailabilityDate(), model.getPlaybackMode());
        String metricsPreset = UiMetricMethods.getMetricsPreset(model.getViewReferenceId());
        boolean zIsPlayable = Playable.isPlayable(model.getContentTraits(), model.getPlaybackMode(), model.getAvailabilityDate());
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (model.getContentTraits() != null) {
            arrayList.addAll(model.getContentTraits());
        }
        if (zIsPlayable) {
            arrayList.remove(ContentTrait.PRE_RELEASE);
            arrayList2.addAll(playEpisode(podcastEpisodeCatalogId, podcastShowCatalogId, title, subTitle, metricsPreset, resources, pageType));
        } else {
            arrayList2.addAll(CompactDetailTemplateMethods.createAndBindCompactDetailTemplate(strMarketplaceId, podcastEpisodeCatalogId, podcastShowCatalogId, title, subTitle, imageUrl, resources));
            arrayList2.addAll(UiMetricMethods.onClicked(metricsPreset, UiMetricAttributes$ActionType.SELECT_PODCAST_EPISODE, pageType, (UiMetricAttributes$ContentName) null));
        }
        VerticalItemElement.Builder builderWithOnItemSelected = VerticalItemElement.builder().withImage(imageUrl).withPrimaryText(title).withOnItemSelected(arrayList2);
        if (model.isProgressEnabled() && zIsPlayable) {
            builderWithOnItemSelected.withBookmark(BookmarkElement.builder().withId(podcastEpisodeCatalogId).build());
        }
        return builderWithOnItemSelected.build();
    }

    public static VerticalItemElement convertShowToVerticalItemElement(final VerticalItemModel model, final UiMetricAttributes$PageType pageType, final Resources resources, final Context context) {
        String podcastShowCatalogId = model.getPodcastShowCatalogId();
        String metricsPreset = UiMetricMethods.getMetricsPreset(model.getViewReferenceId());
        String title = model.getTitle();
        String subTitle = model.getSubTitle();
        String imageUrl = model.getImageUrl();
        String strMarketplaceId = Podcast.getUserInfoProvider().marketplaceId();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(PodcastDetailTemplateV2Methods.createAndBindPodcastDetailTemplate(strMarketplaceId, podcastShowCatalogId, title, subTitle, imageUrl, resources));
        arrayList.addAll(UiMetricMethods.onClicked(metricsPreset, UiMetricAttributes$ActionType.SELECT_PODCAST_SHOW, pageType, (UiMetricAttributes$ContentName) null));
        return VerticalItemElement.builder().withImage(imageUrl).withPrimaryText(title).withSecondaryText(subTitle).withOnItemSelected(arrayList).build();
    }

    private static List<Method> playEpisode(final String episodeId, final String podcastId, final String title, final String podcastTitle, final String blockRef, final Resources resources, final UiMetricAttributes$PageType pageType) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.episodePlay(episodeId, podcastId, title, podcastTitle, resources));
        arrayList.addAll(UiMetricMethods.onClicked(blockRef, UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE, pageType, (UiMetricAttributes$ContentName) null));
        return arrayList;
    }

    /* JADX INFO: renamed from: com.amazon.podcast.views.verticalItemView.VerticalItemMethods$1, reason: invalid class name */
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
}
