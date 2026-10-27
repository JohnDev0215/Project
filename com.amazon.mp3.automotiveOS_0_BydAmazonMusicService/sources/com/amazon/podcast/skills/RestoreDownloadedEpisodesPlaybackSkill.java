package com.amazon.podcast.skills;

import SOACoreInterface.v1_0.Method;
import android.content.Context;
import com.amazon.podcast.Queues;
import com.amazon.podcast.downloads.Download;
import com.amazon.podcast.storage.PodcastRoomDatabase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class RestoreDownloadedEpisodesPlaybackSkill {
    public static List<Method> prepareInvoke(final String episodeId) {
        InvokeLocalSkillMethod invokeLocalSkillMethod = new InvokeLocalSkillMethod(new RestoreDownloadedEpisodesPlaybackTarget(episodeId), Queues.playback());
        ArrayList arrayList = new ArrayList();
        arrayList.add(invokeLocalSkillMethod);
        return arrayList;
    }

    public List<Method> invoke(final Context context, final PodcastRoomDatabase database, final RestoreDownloadedEpisodesPlaybackTarget target) {
        Download downloadStartAt = PlayQueue.INSTANCE.startAt(target.getEpisodeId(), database);
        if (downloadStartAt == null) {
            return Collections.emptyList();
        }
        return PlayDownloadedEpisodesMethods.restoreEpisode(downloadStartAt, context.getResources());
    }
}
