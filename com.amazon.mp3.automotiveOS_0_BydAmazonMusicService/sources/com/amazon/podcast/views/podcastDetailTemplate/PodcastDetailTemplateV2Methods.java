package com.amazon.podcast.views.podcastDetailTemplate;

import Podcast.Touch.NotificationElementInterface.v1_0.CloseNotificationMethod;
import Podcast.Touch.NotificationElementInterface.v1_0.ShowNotificationMethod;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.AddEpisodeRowItemsMethod;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.HeaderElement;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.PodcastDetailTemplate;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.RefinementOptionsClientState;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ReplaceEpisodeRowItemsMethod;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ShowDownloadedEpisodesMethod;
import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.CreateTemplateMethod;
import android.content.Context;
import android.content.res.Resources;
import com.amazon.podcast.Queues;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.endpoints.Endpoints;
import com.amazon.podcast.metrics.latency.LatencyMetricAttributes$PageType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastDetailTemplateV2Methods {
    public static List<String> methodsForRegistry() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(AddEpisodeRowItemsMethod.class.getCanonicalName());
        arrayList.add(ReplaceEpisodeRowItemsMethod.class.getCanonicalName());
        arrayList.add(ShowDownloadedEpisodesMethod.class.getCanonicalName());
        arrayList.add(ShowNotificationMethod.class.getCanonicalName());
        arrayList.add(CloseNotificationMethod.class.getCanonicalName());
        return arrayList;
    }

    public static RefinementOptionsClientState clientStates(final Context context, final String name) {
        return PodcastDetailClientState.INSTANCE.readClientState(context, name);
    }

    public static List<Method> createAndBindPodcastDetailTemplate(final String marketplaceId, final String podcastId, final String title, final String subtitle, final String imageUrl, final Resources resources) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(CreateTemplateMethod.builder().withTemplate(PodcastDetailTemplate.builder().withHeader(HeaderElement.builder().withImage(imageUrl).withPrimaryText(title).withSecondaryText(subtitle).build()).withOnCreated(Arrays.asList(DeeplinkMethods.invokeDeeplinkMethod(Queues.template(), Endpoints.browse(marketplaceId), Deeplinks.podcast(podcastId, title), DeeplinkMethods.onErrorAlertTemplate(resources), LatencyMetricAttributes$PageType.PODCAST_SHOW_DETAIL, new String[0]))).build()).withQueue(Queues.template()).withForced(Boolean.FALSE).build());
        return arrayList;
    }
}
