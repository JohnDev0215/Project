package com.amazon.podcast;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.find.utils.DeeplinksUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastDeeplinks {

    public enum DeeplinkType {
        BROWSE("browse"),
        PLAYBACK("playback"),
        LIBRARY("library");

        private final String value;

        DeeplinkType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    static Intent getIntentForPodcast(final Context context, final String deeplinkUrl) {
        Intent startIntent = MusicHomeActivity.getStartIntent(context);
        startIntent.setFlags(268435456);
        startIntent.putExtra("deeplinkUri", deeplinkUrl);
        startIntent.putExtra("podcastDeeplink", getDeeplinkType(deeplinkUrl));
        return startIntent;
    }

    private static String getDeeplinkType(final String deeplinkUrl) {
        if (deeplinkUrl.contains("/podcasts/library")) {
            Uri uri = Uri.parse(deeplinkUrl);
            if (uri.getPath().matches("/podcasts/library(/.*/.*)") && uri.getQuery().equals("do=play")) {
                return DeeplinkType.PLAYBACK.getValue();
            }
            return DeeplinkType.LIBRARY.getValue();
        }
        if (deeplinkUrl.contains("do=play") || deeplinkUrl.contains("do=featuredPlay")) {
            return DeeplinkType.PLAYBACK.getValue();
        }
        return DeeplinkType.BROWSE.getValue();
    }

    public static boolean isPodcastDeeplink(final String url) {
        return url != null && url.contains("/podcasts");
    }

    public static boolean isPodcastEpisodeDeeplink(final String url) {
        return isPodcastDeeplink(url) && (isPodcastJumpBackIn(url) || isPodcastSaves(url) || isPodcastEpisodes(url));
    }

    private static boolean isPodcastEpisodes(final String url) {
        return url.contains(DeeplinksUtils.PODCAST_EPISODES_PATH_SEGMENT);
    }

    public static boolean isPodcastJumpBackIn(final String url) {
        return url.contains("jumpBackIn");
    }

    public static boolean isPodcastCategories(final String url) {
        return url.contains("categories");
    }

    public static boolean isPodcastSaves(final String url) {
        return url.contains(Environment.PLAYLIST_PATH);
    }

    public static boolean isPodcastPlayback(final String url) {
        return getDeeplinkType(url).equals(DeeplinkType.PLAYBACK.getValue());
    }

    public static String getPodcastId(final String url) {
        return Uri.parse(url).getPath().split("/")[r1.length - 2];
    }

    public static String getEpisodeId(final String url) {
        return Uri.parse(url).getPath().split("/")[r1.length - 1];
    }
}
