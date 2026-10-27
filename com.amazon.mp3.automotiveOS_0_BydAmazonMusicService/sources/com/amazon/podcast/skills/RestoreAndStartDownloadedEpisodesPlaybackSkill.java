package com.amazon.podcast.skills;

import SOACoreInterface.v1_0.Method;
import android.content.Context;
import com.amazon.podcast.downloads.Download;
import com.amazon.podcast.storage.PodcastRoomDatabase;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class RestoreAndStartDownloadedEpisodesPlaybackSkill {
    public List<Method> invoke(final Context context, final PodcastRoomDatabase database, final RestoreAndStartDownloadedEpisodesPlaybackTarget target) {
        Download downloadStartAt = PlayQueue.INSTANCE.startAt(target.getEpisodeId(), database);
        if (downloadStartAt == null) {
            return Collections.emptyList();
        }
        return PlayDownloadedEpisodesMethods.restoreAndStartEpisode(downloadStartAt, context.getResources());
    }
}
