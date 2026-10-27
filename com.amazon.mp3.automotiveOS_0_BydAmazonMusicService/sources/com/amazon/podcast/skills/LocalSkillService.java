package com.amazon.podcast.skills;

import SOACoreInterface.v1_0.Method;
import android.content.Context;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.storage.PodcastRoomDatabase;
import com.amazon.soa.core.Engine;
import com.amazon.soa.core.MethodCallback;
import com.amazon.soa.core.OnMethodExecutedListener;
import java.util.Collections;
import java.util.HashSet;

/* JADX INFO: loaded from: classes5.dex */
public final class LocalSkillService implements MethodCallback {
    private final Context context;
    private final PodcastRoomDatabase database;
    private final Engine engine;

    public LocalSkillService(final Context context, final Engine engine, final PodcastRoomDatabase database) {
        this.context = context;
        this.engine = engine;
        this.database = database;
        HashSet hashSet = new HashSet();
        hashSet.add(InvokeLocalSkillMethod.class.getCanonicalName());
        engine.registerOwner("LocalSkillService");
        engine.registerMethods(hashSet, Collections.emptySet(), this);
    }

    @Override // com.amazon.soa.core.MethodCallback
    public void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        Handlers.INSTANCE.runInBackground(new Runnable() { // from class: com.amazon.podcast.skills.LocalSkillService.1
            @Override // java.lang.Runnable
            public void run() {
                LocalSkillService.this.executeInternal(method, ownerId, listener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeInternal(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        if (method instanceof InvokeLocalSkillMethod) {
            invokeLocalSkillMethod((InvokeLocalSkillMethod) method, ownerId);
        }
        listener.onMethodExecuted(method);
    }

    private void invokeLocalSkillMethod(final InvokeLocalSkillMethod method, final String ownerId) {
        LocalSkillTarget target = method.getTarget();
        if (target instanceof PlayDownloadedEpisodesTarget) {
            this.engine.handleMethods(ownerId, new PlayDownloadedEpisodesSkill().invoke(this.context, this.database, (PlayDownloadedEpisodesTarget) target));
            return;
        }
        if (target instanceof PlayNextDownloadedEpisodeTarget) {
            this.engine.handleMethods(ownerId, new PlayNextDownloadedEpisodeSkill().invoke(this.context, this.database, (PlayNextDownloadedEpisodeTarget) target));
            return;
        }
        if (target instanceof PlayPreviousDownloadedEpisodeTarget) {
            this.engine.handleMethods(ownerId, new PlayPreviousDownloadedEpisodeSkill().invoke(this.context, this.database, (PlayPreviousDownloadedEpisodeTarget) target));
        } else if (target instanceof RestoreDownloadedEpisodesPlaybackTarget) {
            this.engine.handleMethods(ownerId, new RestoreDownloadedEpisodesPlaybackSkill().invoke(this.context, this.database, (RestoreDownloadedEpisodesPlaybackTarget) target));
        } else if (target instanceof RestoreAndStartDownloadedEpisodesPlaybackTarget) {
            this.engine.handleMethods(ownerId, new RestoreAndStartDownloadedEpisodesPlaybackSkill().invoke(this.context, this.database, (RestoreAndStartDownloadedEpisodesPlaybackTarget) target));
        }
    }
}
