package com.amazon.podcast.bootstrap;

import android.content.Context;
import com.amazon.podcast.sharing.SharePodcastBiteElement;
import com.amazon.podcast.sharing.SharePodcastEpisodeElement;
import com.amazon.podcast.sharing.SharePodcastShowElement;

/* JADX INFO: loaded from: classes5.dex */
public abstract class SharingProvider {
    public abstract void sharePodcastBite(final Context context, final SharePodcastBiteElement shareElement);

    public abstract void sharePodcastEpisode(final Context context, final SharePodcastEpisodeElement shareElement);

    public abstract void sharePodcastShow(final Context context, final SharePodcastShowElement shareElement);
}
