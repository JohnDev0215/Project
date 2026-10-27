package com.amazon.podcast.views.compactDetailTemplate;

import Podcast.Touch.CompactDetailTemplateInterface.v1_0.CompactDetailTemplate;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.HeaderElement;
import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.CreateTemplateMethod;
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
public final class CompactDetailTemplateMethods {
    public static List<Method> createAndBindCompactDetailTemplate(final String marketplaceId, final String episodeId, final String podcastId, final String episodeTitle, final String showTitle, final String imageUrl, final Resources resources) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(CreateTemplateMethod.builder().withTemplate(CompactDetailTemplate.builder().withHeader(HeaderElement.builder().withImage(imageUrl).withPrimaryText(episodeTitle).withSecondaryText(showTitle).build()).withOnCreated(Arrays.asList(DeeplinkMethods.invokeDeeplinkMethod(Queues.template(), Endpoints.browse(marketplaceId), Deeplinks.episode(podcastId, episodeId, showTitle, episodeTitle), DeeplinkMethods.onErrorAlertTemplate(resources), LatencyMetricAttributes$PageType.PODCAST_EPISODE_DETAIL, new String[0]))).build()).withQueue(Queues.template()).withForced(Boolean.FALSE).build());
        return arrayList;
    }
}
