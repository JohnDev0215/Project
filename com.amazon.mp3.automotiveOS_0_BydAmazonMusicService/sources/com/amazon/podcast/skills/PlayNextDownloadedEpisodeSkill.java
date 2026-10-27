package com.amazon.podcast.skills;

import SOACoreInterface.v1_0.Method;
import android.content.Context;
import com.amazon.podcast.Queues;
import com.amazon.podcast.downloads.Download;
import com.amazon.podcast.storage.PodcastRoomDatabase;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class PlayNextDownloadedEpisodeSkill {
    public static List<Method> prepareInvoke() {
        return Collections.singletonList(new InvokeLocalSkillMethod(new PlayNextDownloadedEpisodeTarget(), Queues.playback()));
    }

    public List<Method> invoke(final Context context, final PodcastRoomDatabase database, final PlayNextDownloadedEpisodeTarget target) {
        Download next = PlayQueue.INSTANCE.next(database);
        if (next == null) {
            return Collections.emptyList();
        }
        return PlayDownloadedEpisodesMethods.playNextEpisode(next, context.getResources());
    }
}
