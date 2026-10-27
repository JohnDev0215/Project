package com.amazon.podcast;

import Podcast.JumpBackInInterface.v1_0.JumpBackInCacheItemElement;
import Podcast.JumpBackInInterface.v1_0.JumpBackInWriteCacheElement;
import android.content.Context;
import android.content.res.Resources;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import com.amazon.podcast.bookmark.Bookmark;
import com.amazon.podcast.bookmark.BookmarkQueryParameters;
import com.amazon.podcast.caches.Caches;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.views.Strings;
import com.amazon.soa.core.Engine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public enum JumpBackIns {
    INSTANCE;

    private Context context;
    private Engine engine;
    private final Object lock = new Object();
    private String ownerId;
    private List<PodcastEpisode> podcastEpisodes;
    private Resources resources;

    JumpBackIns() {
    }

    public void init(final Context context, final Engine engine, final String ownerId) {
        this.context = context;
        this.resources = context.getResources();
        this.engine = engine;
        this.ownerId = ownerId;
        this.podcastEpisodes = new ArrayList();
    }

    public final LiveData<List<PodcastEpisode>> getPodcastJumpBackInEpisodes() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.bookmarks());
        arrayList.addAll(DeeplinkMethods.jumpBackIn(this.resources));
        this.engine.handleMethods(this.ownerId, arrayList);
        LiveData liveData = Caches.INSTANCE.jumpBackInCache().read();
        LiveData<List<Bookmark>> all = Podcast.getAppSync().bookmark().readAll(this.context);
        LiveData map = Transformations.map(liveData, new Function() { // from class: com.amazon.podcast.JumpBackIns$$ExternalSyntheticLambda0
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                return this.f$0.convertJumpBackInItems((JumpBackInWriteCacheElement) obj);
            }
        });
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(map, new Observer<List<JumpBackInCacheItemElement>>() { // from class: com.amazon.podcast.JumpBackIns.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(final List<JumpBackInCacheItemElement> jumpBackInCacheItemElements) {
                if (jumpBackInCacheItemElements == null) {
                    return;
                }
                Handlers.INSTANCE.getBackground().post(new Runnable() { // from class: com.amazon.podcast.JumpBackIns.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (JumpBackIns.this.shouldRefresh(jumpBackInCacheItemElements)) {
                            AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                            mediatorLiveData.postValue(JumpBackIns.this.convertJumpBackInElementsToPodcastEpisodes(jumpBackInCacheItemElements));
                        }
                    }
                });
            }
        });
        mediatorLiveData.addSource(all, new Observer<List<Bookmark>>() { // from class: com.amazon.podcast.JumpBackIns.2
            @Override // androidx.lifecycle.Observer
            public void onChanged(final List<Bookmark> bookmarks) {
                Handlers.INSTANCE.getBackground().post(new Runnable() { // from class: com.amazon.podcast.JumpBackIns.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                        mediatorLiveData.postValue(JumpBackIns.this.convertBookmarksToPodcastEpisodes(bookmarks));
                    }
                });
            }
        });
        return mediatorLiveData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<PodcastEpisode> convertJumpBackInElementsToPodcastEpisodes(final List<JumpBackInCacheItemElement> elements) {
        ArrayList arrayList;
        long totalDurationMilliseconds;
        synchronized (this.lock) {
            HashSet hashSet = new HashSet();
            Iterator<JumpBackInCacheItemElement> it = elements.iterator();
            while (it.hasNext()) {
                hashSet.add(it.next().getEpisodeId());
            }
            Map<String, Bookmark> mapMapIdToBookmark = mapIdToBookmark(Podcast.getAppSync().bookmark().readAll(new BookmarkQueryParameters(hashSet)));
            arrayList = new ArrayList();
            for (JumpBackInCacheItemElement jumpBackInCacheItemElement : elements) {
                Bookmark bookmark = mapMapIdToBookmark.get(jumpBackInCacheItemElement.getEpisodeId());
                if (bookmark == null) {
                    totalDurationMilliseconds = jumpBackInCacheItemElement.getTotalDurationMilliseconds().longValue();
                } else {
                    totalDurationMilliseconds = bookmark.getTotalDurationMilliseconds();
                }
                Long lValueOf = Long.valueOf(totalDurationMilliseconds);
                Integer numConvertProgress = convertProgress(bookmark, lValueOf);
                arrayList.add(new PodcastEpisode(jumpBackInCacheItemElement.getEpisodeId(), jumpBackInCacheItemElement.getPodcastId(), jumpBackInCacheItemElement.getTitle(), jumpBackInCacheItemElement.getPodcastTitle(), jumpBackInCacheItemElement.getPodcastImage(), jumpBackInCacheItemElement.getDescription(), jumpBackInCacheItemElement.getAuthors().toString(), jumpBackInCacheItemElement.getPlayDeeplink(), lValueOf == null ? null : Integer.valueOf(lValueOf.intValue()), lValueOf == null ? null : Strings.getDurationString(this.resources, lValueOf.longValue()), numConvertProgress, convertRemainingDurationText(bookmark, lValueOf), jumpBackInCacheItemElement.getPodcastEpisodeVariantId()));
            }
            this.podcastEpisodes = arrayList;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<PodcastEpisode> convertBookmarksToPodcastEpisodes(final List<Bookmark> bookmarkElements) {
        Map<String, Bookmark> map;
        Bookmark bookmark;
        synchronized (this.lock) {
            if (this.podcastEpisodes.isEmpty()) {
                return this.podcastEpisodes;
            }
            Map<String, Bookmark> mapMapIdToBookmark = mapIdToBookmark(bookmarkElements);
            int i = 0;
            while (i < this.podcastEpisodes.size()) {
                PodcastEpisode podcastEpisode = this.podcastEpisodes.get(i);
                String episodeId = podcastEpisode.getEpisodeId();
                if (!mapMapIdToBookmark.containsKey(episodeId) || (bookmark = mapMapIdToBookmark.get(episodeId)) == null) {
                    map = mapMapIdToBookmark;
                } else {
                    String id = bookmark.getId();
                    Long lValueOf = Long.valueOf(bookmark.getTotalDurationMilliseconds());
                    Integer numConvertProgress = convertProgress(bookmark, lValueOf);
                    String durationString = lValueOf == null ? null : Strings.getDurationString(this.resources, lValueOf.longValue());
                    String strConvertRemainingDurationText = convertRemainingDurationText(bookmark, lValueOf);
                    if (id.equals(episodeId)) {
                        map = mapMapIdToBookmark;
                        this.podcastEpisodes.set(i, new PodcastEpisode(episodeId, podcastEpisode.getPodcastId(), podcastEpisode.getTitle(), podcastEpisode.getPodcastTitle(), podcastEpisode.getImage(), podcastEpisode.getDescription(), podcastEpisode.getAuthors(), podcastEpisode.getDeeplink(), lValueOf == null ? null : Integer.valueOf(lValueOf.intValue()), durationString, numConvertProgress, strConvertRemainingDurationText, podcastEpisode.getPodcastEpisodeVariantId()));
                    } else {
                        map = mapMapIdToBookmark;
                    }
                }
                i++;
                mapMapIdToBookmark = map;
            }
            return this.podcastEpisodes;
        }
    }

    private Map<String, Bookmark> mapIdToBookmark(final List<Bookmark> bookmarks) {
        HashMap map = new HashMap();
        for (Bookmark bookmark : bookmarks) {
            map.put(bookmark.getId(), bookmark);
        }
        return map;
    }

    private String convertRemainingDurationText(final Bookmark bookmark, final Long totalDurationMilliseconds) {
        Long lValueOf;
        if (totalDurationMilliseconds == null) {
            return null;
        }
        String durationString = Strings.getDurationString(this.resources, totalDurationMilliseconds.longValue());
        if (bookmark == null || (lValueOf = Long.valueOf(bookmark.getProgressMilliseconds())) == null) {
            return durationString;
        }
        long jLongValue = totalDurationMilliseconds.longValue() - lValueOf.longValue();
        return jLongValue <= TimeUnit.SECONDS.toMillis(1L) ? durationString : Strings.getRemainingDurationString(this.resources, jLongValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<JumpBackInCacheItemElement> convertJumpBackInItems(final JumpBackInWriteCacheElement writeCacheElement) {
        if (writeCacheElement == null) {
            return Collections.emptyList();
        }
        return writeCacheElement.getElements();
    }

    private Integer convertProgress(final Bookmark bookmark, final Long totalDurationMilliseconds) {
        Long lValueOf;
        if (bookmark == null || (lValueOf = Long.valueOf(bookmark.getProgressMilliseconds())) == null || totalDurationMilliseconds == null) {
            return null;
        }
        if (totalDurationMilliseconds.longValue() - lValueOf.longValue() <= TimeUnit.SECONDS.toMillis(1L)) {
            return Integer.valueOf(totalDurationMilliseconds.intValue());
        }
        return Integer.valueOf(lValueOf.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldRefresh(final List<JumpBackInCacheItemElement> jumpBackInCacheItemElements) {
        synchronized (this.lock) {
            if (!(this.podcastEpisodes.size() == jumpBackInCacheItemElements.size())) {
                return true;
            }
            for (int i = 0; i < this.podcastEpisodes.size(); i++) {
                PodcastEpisode podcastEpisode = this.podcastEpisodes.get(i);
                JumpBackInCacheItemElement jumpBackInCacheItemElement = jumpBackInCacheItemElements.get(i);
                if (podcastEpisode != null && jumpBackInCacheItemElement != null && !podcastEpisode.getEpisodeId().equals(jumpBackInCacheItemElement.getEpisodeId())) {
                    return true;
                }
            }
            return false;
        }
    }
}
