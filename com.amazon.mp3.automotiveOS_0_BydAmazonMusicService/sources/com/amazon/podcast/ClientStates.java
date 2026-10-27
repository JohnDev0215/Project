package com.amazon.podcast;

import Podcast.BookmarkInterface.v1_0.BookmarkOperationsClientState;
import Podcast.CategoryFollowInterface.v1_0.CategoryFollowOperationsClientState;
import Podcast.CompletedInterface.v1_0.CompletedOperationsClientState;
import Podcast.DeeplinkInterface.v1_0.DeeplinkClientState;
import Podcast.FollowInterface.v1_0.FollowOperationsClientState;
import Podcast.PlaybackMetricsInterface.v1_0.PlaybackMetricsOperationsClientState;
import Podcast.SaveInterface.v1_0.SaveOperationsClientState;
import Podcast.UIMetricsInterface.v1_0.UIMetricsOperationsClientState;
import SOACoreInterface.v1_0.ClientState;
import android.content.Context;
import com.amazon.podcast.appsync.AppSync;
import com.amazon.podcast.deeplinks.PodcastDeeplink;
import com.amazon.podcast.followPrompt.FollowPromptPreferences;
import com.amazon.podcast.ptc.PTCPreferences;
import com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateV2Methods;
import com.google.android.gms.common.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class ClientStates {
    private final AppSync appSync;
    private final Context context;
    private PodcastDeeplink deeplink;

    public static final class Entry {
        private final ClientState clientState;
        private final String key;

        public static Entry create(final String key, final ClientState clientState) {
            return new Entry(key, clientState);
        }

        private Entry(final String key, final ClientState clientState) {
            this.key = key;
            this.clientState = clientState;
        }

        public String getKey() {
            return this.key;
        }

        public ClientState getClientState() {
            return this.clientState;
        }
    }

    public ClientStates(final AppSync appSync, final Context context) {
        this.appSync = appSync;
        this.context = context;
    }

    public void setDeeplinkInfo(final PodcastDeeplink deeplink) {
        this.deeplink = deeplink;
    }

    public PodcastDeeplink getDeeplinkInfo() {
        return this.deeplink;
    }

    public Entry get(final String name) {
        if (name.startsWith("Podcast.FollowInterface.v1_0")) {
            return this.appSync.follow().clientState(name);
        }
        if (name.startsWith("Podcast.BookmarkInterface.v1_0")) {
            return this.appSync.bookmark().clientState(name);
        }
        if (name.startsWith("Podcast.BiteBookmarkInterface.v1_0")) {
            return this.appSync.biteBookmark().clientState(name);
        }
        if (name.startsWith("Podcast.SaveInterface.v1_0")) {
            return this.appSync.save().clientState(name);
        }
        if (name.startsWith("Podcast.PlaybackMetricsInterface.v1_0")) {
            return this.appSync.playbackMetrics().clientState(name);
        }
        if (name.startsWith("Podcast.DeeplinkInterface.v1_0")) {
            PodcastDeeplink podcastDeeplink = this.deeplink;
            if (podcastDeeplink == null) {
                return null;
            }
            return deeplinkClientState(podcastDeeplink.getUrl(), this.deeplink.isBackStackRequired());
        }
        if (name.startsWith("Podcast.UIMetricsInterface.v1_0")) {
            return this.appSync.uiMetrics().clientState(name);
        }
        if (name.startsWith("Podcast.CompletedInterface.v1_0")) {
            return this.appSync.completed().clientState(name);
        }
        if (name.startsWith("Podcast.PlaybackInterface.v1_0")) {
            return new Entry("localEpisodeAlreadyPlaying", Podcast.getPlayback().getPlayLocalEpisodesClientState(name));
        }
        if (name.startsWith("Podcast.Touch.PodcastDetailTemplateInterface.v2_0.RefinementOptionsClientState")) {
            return new Entry("refinementOptions", PodcastDetailTemplateV2Methods.clientStates(this.context, name));
        }
        if (name.startsWith("Podcast.CategoryFollowInterface.v1_0")) {
            return this.appSync.categoryFollow().clientState(name);
        }
        if (name.startsWith("Podcast.Touch.PTCTemplateInterface.v1_0.PTCPreferencesClientState")) {
            return new Entry("ptcPreferences", PTCPreferences.INSTANCE.readPTCPreferencesClientState(this.context));
        }
        if (name.startsWith("Podcast.Touch.PTCTemplateInterface.v1_0.PTCSelectionsClientState")) {
            return new Entry("ptcSelections", PTCPreferences.INSTANCE.readPTCSelectionsClientState());
        }
        if (!name.startsWith("Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceClientState")) {
            return null;
        }
        return new Entry("hidePromptPreference", FollowPromptPreferences.INSTANCE.readHidePromptPreferenceClientState(this.context));
    }

    public boolean shouldExclude(final ClientState clientState) {
        if (clientState instanceof FollowOperationsClientState) {
            return CollectionUtils.isEmpty(((FollowOperationsClientState) clientState).getOperations());
        }
        if (clientState instanceof SaveOperationsClientState) {
            return CollectionUtils.isEmpty(((SaveOperationsClientState) clientState).getOperations());
        }
        if (clientState instanceof BookmarkOperationsClientState) {
            return CollectionUtils.isEmpty(((BookmarkOperationsClientState) clientState).getOperations());
        }
        if (clientState instanceof PlaybackMetricsOperationsClientState) {
            return CollectionUtils.isEmpty(((PlaybackMetricsOperationsClientState) clientState).getOperations());
        }
        if (clientState instanceof UIMetricsOperationsClientState) {
            return CollectionUtils.isEmpty(((UIMetricsOperationsClientState) clientState).getOperations());
        }
        if (clientState instanceof CompletedOperationsClientState) {
            return CollectionUtils.isEmpty(((CompletedOperationsClientState) clientState).getOperations());
        }
        if (clientState instanceof CategoryFollowOperationsClientState) {
            return CollectionUtils.isEmpty(((CategoryFollowOperationsClientState) clientState).getOperations());
        }
        return false;
    }

    public Entry deeplinkClientState(final String url, final boolean isBackStackRequired) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        return new Entry("deeplink", DeeplinkClientState.builder().withUrl(url).withBackStackRequired(isBackStackRequired).build());
    }
}
