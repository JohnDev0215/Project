package com.amazon.podcast;

import android.content.Context;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.follow.Follow;
import com.amazon.soa.core.Engine;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public enum Follows {
    INSTANCE;

    private Context context;
    private Engine engine;
    private String ownerId;

    public void init(final Context context, final Engine engine, final String ownerId) {
        this.context = context;
        this.engine = engine;
        this.ownerId = ownerId;
    }

    public final LiveData<List<PodcastShow>> getPodcastFollowedShows() {
        this.engine.handleMethods(this.ownerId, DeeplinkMethods.shows());
        return Transformations.map(Podcast.getAppSync().follow().readAll(this.context), new Function() { // from class: com.amazon.podcast.Follows$$ExternalSyntheticLambda0
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                return this.f$0.convertPodcastShows((List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<PodcastShow> convertPodcastShows(final List<Follow> follows) {
        ArrayList arrayList = new ArrayList();
        for (Follow follow : follows) {
            arrayList.add(new PodcastShow(follow.getTitle(), follow.getImage(), follow.getDescription(), follow.getPublisher(), follow.isFollowed(), Deeplinks.podcast(follow.getId(), follow.getTitle())));
        }
        return arrayList;
    }
}
