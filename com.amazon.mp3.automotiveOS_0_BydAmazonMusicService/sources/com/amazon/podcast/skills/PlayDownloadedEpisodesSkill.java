package com.amazon.podcast.skills;

import SOACoreInterface.v1_0.Method;
import android.content.Context;
import com.amazon.podcast.Queues;
import com.amazon.podcast.downloads.Download;
import com.amazon.podcast.storage.PodcastRoomDatabase;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class PlayDownloadedEpisodesSkill {
    public static List<Method> prepareInvoke(final String episodeId) {
        return Collections.singletonList(new InvokeLocalSkillMethod(new PlayDownloadedEpisodesTarget(episodeId, true), Queues.playback()));
    }

    public static List<Method> prepareInvoke(final String episodeId, final boolean launchNowPlaying) {
        return Collections.singletonList(new InvokeLocalSkillMethod(new PlayDownloadedEpisodesTarget(episodeId, launchNowPlaying), Queues.playback()));
    }

    public List<Method> invoke(final Context context, final PodcastRoomDatabase database, final PlayDownloadedEpisodesTarget target) {
        Download downloadStartAt = PlayQueue.INSTANCE.startAt(target.getEpisodeId(), database);
        if (downloadStartAt == null) {
            return Collections.emptyList();
        }
        return PlayDownloadedEpisodesMethods.playEpisode(downloadStartAt, target.getLaunchNowPlaying(), context.getResources());
    }
}
