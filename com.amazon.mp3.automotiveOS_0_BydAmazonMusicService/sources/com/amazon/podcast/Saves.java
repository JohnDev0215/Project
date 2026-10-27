package com.amazon.podcast;

import android.content.Context;
import android.content.res.Resources;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.amazon.podcast.bookmark.Bookmark;
import com.amazon.podcast.completed.Completed;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.save.Save;
import com.amazon.podcast.views.Strings;
import com.amazon.soa.core.Engine;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public enum Saves {
    INSTANCE;

    private LiveData<List<Bookmark>> bookmarks;
    private Context context;
    private Engine engine;
    private String ownerId;
    private List<Save> saves;

    public void init(final Context context, final Engine engine, final String ownerId) {
        this.context = context;
        this.engine = engine;
        this.ownerId = ownerId;
        this.saves = null;
        this.bookmarks = null;
    }

    public final LiveData<List<PodcastEpisode>> getPodcastSavedEpisodes() {
        this.engine.handleMethods(this.ownerId, DeeplinkMethods.playlist());
        LiveData<List<Save>> all = Podcast.getAppSync().save().readAll(this.context);
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(all, new Observer<List<Save>>() { // from class: com.amazon.podcast.Saves.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(final List<Save> savedEpisodes) {
                Saves.this.saves = savedEpisodes;
                mediatorLiveData.postValue(Saves.this.convertPodcastEpisodes(savedEpisodes));
                Saves.this.updateBookmarks(mediatorLiveData);
            }
        });
        return mediatorLiveData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBookmarks(final MediatorLiveData<List<PodcastEpisode>> results) {
        results.removeSource(this.bookmarks);
        HashSet hashSet = new HashSet();
        Iterator<Save> it = this.saves.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getId());
        }
        LiveData all = Podcast.getAppSync().bookmark().readAll(hashSet);
        this.bookmarks = all;
        if (all == null) {
            return;
        }
        results.addSource(all, new Observer<List<Bookmark>>() { // from class: com.amazon.podcast.Saves.2
            @Override // androidx.lifecycle.Observer
            public void onChanged(final List<Bookmark> bookmarks) {
                MediatorLiveData mediatorLiveData = results;
                Saves saves = Saves.this;
                mediatorLiveData.postValue(saves.convertPodcastEpisodes(saves.saves));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<PodcastEpisode> convertPodcastEpisodes(final List<Save> saves) {
        long totalDurationMilliseconds;
        ArrayList arrayList = new ArrayList();
        if (saves == null) {
            return arrayList;
        }
        for (Save save : saves) {
            Bookmark bookmark = Podcast.getAppSync().bookmark().read(this.context, save.getId());
            if (bookmark == null) {
                totalDurationMilliseconds = save.getTotalDurationMilliseconds();
            } else {
                totalDurationMilliseconds = bookmark.getTotalDurationMilliseconds();
            }
            Integer numConvertProgress = convertProgress(bookmark, totalDurationMilliseconds);
            String strConvertRemainingDurationText = convertRemainingDurationText(bookmark, totalDurationMilliseconds);
            arrayList.add(new PodcastEpisode(save.getId(), save.getPodcastId(), save.getTitle(), save.getPodcastTitle(), save.getImage(), save.getDescription(), save.getAuthors(), Deeplinks.savedEpisodePlay(save.getId()), Integer.valueOf((int) totalDurationMilliseconds), strConvertRemainingDurationText, numConvertProgress, strConvertRemainingDurationText, save.getPodcastEpisodeVariantId()));
        }
        return arrayList;
    }

    private Integer convertProgress(final Bookmark bookmark, final long totalDurationMilliseconds) {
        if (bookmark == null) {
            return null;
        }
        Completed completed = Podcast.getAppSync().completed().read(this.context, bookmark.getId());
        long progressMilliseconds = bookmark.getProgressMilliseconds();
        long j = totalDurationMilliseconds - progressMilliseconds;
        if (completed == null || !isEpisodeCompleted(completed.getId())) {
            if (j <= TimeUnit.SECONDS.toMillis(1L)) {
                return Integer.valueOf((int) totalDurationMilliseconds);
            }
            return Integer.valueOf((int) progressMilliseconds);
        }
        Playback playback = Podcast.getPlayback();
        String id = completed.getId();
        if (playback.isEpisodePaused(id) || playback.isEpisodePlaying(id)) {
            return Integer.valueOf((int) progressMilliseconds);
        }
        return Integer.valueOf((int) totalDurationMilliseconds);
    }

    private String convertRemainingDurationText(final Bookmark bookmark, final long totalDurationMilliseconds) {
        Resources resources = this.context.getResources();
        String durationString = Strings.getDurationString(resources, totalDurationMilliseconds);
        if (bookmark == null) {
            return durationString;
        }
        long progressMilliseconds = totalDurationMilliseconds - bookmark.getProgressMilliseconds();
        String remainingDurationString = Strings.getRemainingDurationString(resources, progressMilliseconds);
        Completed completed = Podcast.getAppSync().completed().read(this.context, bookmark.getId());
        if (completed == null || !isEpisodeCompleted(completed.getId())) {
            return progressMilliseconds <= TimeUnit.SECONDS.toMillis(1L) ? durationString : remainingDurationString;
        }
        Playback playback = Podcast.getPlayback();
        String id = completed.getId();
        return (playback.isEpisodePaused(id) || playback.isEpisodePlaying(id)) ? remainingDurationString : durationString;
    }

    private boolean isEpisodeCompleted(final String id) {
        Completed completed = Podcast.getAppSync().completed().read(this.context, id);
        return completed != null && completed.getCompleted();
    }
}
