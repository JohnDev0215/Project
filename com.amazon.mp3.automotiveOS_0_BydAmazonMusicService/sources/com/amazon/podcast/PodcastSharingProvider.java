package com.amazon.podcast;

import android.content.Context;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.music.share.PodcastBiteShareProvider;
import com.amazon.music.share.PodcastEpisodeShareProvider;
import com.amazon.music.share.PodcastShowShareProvider;
import com.amazon.podcast.bootstrap.SharingProvider;
import com.amazon.podcast.sharing.SharePodcastBiteElement;
import com.amazon.podcast.sharing.SharePodcastEpisodeElement;
import com.amazon.podcast.sharing.SharePodcastShowElement;

/* JADX INFO: loaded from: classes5.dex */
public class PodcastSharingProvider extends SharingProvider {
    @Override // com.amazon.podcast.bootstrap.SharingProvider
    public void sharePodcastShow(final Context context, final SharePodcastShowElement showShareElement) {
        new PodcastShowShareProvider(context, EndpointsProvider.get().getMusicDomain(), showShareElement.getId(), showShareElement.getTitle(), showShareElement.getDescription(), showShareElement.getLabel(), showShareElement.getPrompt(), showShareElement.getDeeplinkUrl(), showShareElement.getImageUrl(), showShareElement.getMetricsEntityType()).startShare();
    }

    @Override // com.amazon.podcast.bootstrap.SharingProvider
    public void sharePodcastEpisode(final Context context, final SharePodcastEpisodeElement episodeShareElement) {
        new PodcastEpisodeShareProvider(context, EndpointsProvider.get().getMusicDomain(), episodeShareElement.getId(), episodeShareElement.getTitle(), episodeShareElement.getSubtitle(), episodeShareElement.getDescription(), episodeShareElement.getLabel(), episodeShareElement.getPrompt(), episodeShareElement.getDeeplinkUrl(), episodeShareElement.getImageUrl(), episodeShareElement.getMetricsEntityType()).startShare();
    }

    @Override // com.amazon.podcast.bootstrap.SharingProvider
    public void sharePodcastBite(final Context context, final SharePodcastBiteElement biteShareElement) {
        new PodcastBiteShareProvider(context, EndpointsProvider.get().getMusicDomain(), biteShareElement.getId(), biteShareElement.getTitle(), biteShareElement.getDescription(), biteShareElement.getLabel(), biteShareElement.getPrompt(), biteShareElement.getDeeplinkUrl(), biteShareElement.getImageUrl(), biteShareElement.getMetricsEntityType()).startShare();
    }
}
