package com.amazon.mp3.ondemandplaylists;

import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.ondemandplaylists.metrics.GetOnDemandPlaylistsMetricsUtil;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.music.platform.playback.PlaybackExperience;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import kotlin.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: OnDemandPlaylistSynchronizer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0010\u0010\n\u001a\u00020\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\tR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/ondemandplaylists/OnDemandPlaylistSynchronizer;", "", "()V", "logger", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "shouldSyncOnDemandPlaylists", "", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "syncOnDemandPlaylists", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OnDemandPlaylistSynchronizer {
    public static final OnDemandPlaylistSynchronizer INSTANCE = new OnDemandPlaylistSynchronizer();
    private static final Logger logger = LoggerFactory.getLogger(OnDemandPlaylistSynchronizer.class.getName());

    private OnDemandPlaylistSynchronizer() {
    }

    public final void syncOnDemandPlaylists(ContentMetadata contentMetadata) {
        if (shouldSyncOnDemandPlaylists(contentMetadata)) {
            logger.debug("Synchronizing on-demand playlists");
            GetOnDemandPlaylistsMetricsUtil.INSTANCE.trackEvent(GetOnDemandPlaylistsMetricsUtil.EventType.EVENT_SEARCH_RESULT_AAP_REFRESH);
            GetOnDemandPlaylistsService.INSTANCE.get().fetchAndCacheOnDemandPlaylists();
        }
    }

    private final boolean shouldSyncOnDemandPlaylists(ContentMetadata contentMetadata) {
        int i = 1;
        if (!Feature.search_aap_instant_unlock.isEnabled(true)) {
            return false;
        }
        PlaybackExperience playbackExperience = null;
        byte b = 0;
        byte b2 = 0;
        byte b3 = 0;
        byte b4 = 0;
        if (contentMetadata instanceof PlaylistMetadata) {
            playbackExperience = new EligibilityUtil(b4 == true ? 1 : 0, i, b3 == true ? 1 : 0).getPlaybackExperience((EntityEligibilitiesProvider) contentMetadata);
        } else if (contentMetadata instanceof TrackMetadata) {
            playbackExperience = new EligibilityUtil(b2 == true ? 1 : 0, i, b == true ? 1 : 0).getPlaybackExperience((EntityEligibilitiesProvider) contentMetadata);
        }
        return PlaybackExperience.ON_DEMAND == playbackExperience;
    }
}
