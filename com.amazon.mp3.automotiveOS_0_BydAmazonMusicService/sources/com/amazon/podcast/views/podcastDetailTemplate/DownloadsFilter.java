package com.amazon.podcast.views.podcastDetailTemplate;

import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.RefinementOptionsClientState;
import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.appsync.AppSync;
import com.amazon.podcast.completed.Completed;
import com.amazon.podcast.completed.CompletedQueryParameters;
import com.amazon.podcast.downloads.Download;
import com.amazon.podcast.downloads.DownloadQueryParameters;
import com.amazon.podcast.views.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
final class DownloadsFilter {
    private static final Logger logger = LoggerFactory.getLogger(DownloadsFilter.class.getSimpleName());
    private LiveData<List<Completed>> completedLiveData;
    private final Context context;
    private LiveData<List<Download>> downloadLiveData;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final LifecycleOwner lifecycleOwner;
    private MutableLiveData<List<Download>> mutableLiveData;

    public DownloadsFilter(final Context context, final LifecycleOwner lifecycleOwner) {
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
    }

    public LiveData<List<Download>> getDownloadedEpisodesLiveData(final String podcastId, final String clientStateName, final String defaultSortState) {
        MutableLiveData<List<Download>> mutableLiveData = this.mutableLiveData;
        if (mutableLiveData != null) {
            mutableLiveData.removeObservers(this.lifecycleOwner);
            this.mutableLiveData = null;
        }
        LiveData<List<Download>> liveData = this.downloadLiveData;
        if (liveData != null) {
            liveData.removeObservers(this.lifecycleOwner);
            this.downloadLiveData = null;
        }
        LiveData<List<Completed>> liveData2 = this.completedLiveData;
        if (liveData2 != null) {
            liveData2.removeObservers(this.lifecycleOwner);
            this.completedLiveData = null;
        }
        this.executorService.submit(new Runnable() { // from class: com.amazon.podcast.views.podcastDetailTemplate.DownloadsFilter.1
            @Override // java.lang.Runnable
            public void run() {
                DownloadsFilter.this.executeDownloadQuery(podcastId, clientStateName, true, defaultSortState);
            }
        });
        MutableLiveData<List<Download>> mutableLiveData2 = new MutableLiveData<>();
        this.mutableLiveData = mutableLiveData2;
        return mutableLiveData2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeDownloadQuery(final String podcastId, final String clientStateName, final boolean observeLiveData, final String defaultSortState) {
        RefinementOptionsClientState clientState = PodcastDetailClientState.INSTANCE.readClientState(this.context, clientStateName);
        final DownloadQueryParameters downloadQueryParameters = new DownloadQueryParameters(podcastId, clientState.getSeasonNumber());
        final boolean zBooleanValue = clientState.isUnplayed() == null ? false : clientState.isUnplayed().booleanValue();
        final AppSync appSync = Podcast.getAppSync();
        List<Download> all = appSync.download().readAll(downloadQueryParameters);
        if (zBooleanValue) {
            all = getUnPlayedDownloadedEpisodes(all, appSync.completed().readAll(getCompletedQueryParameters(downloadQueryParameters)));
        }
        this.mutableLiveData.postValue(getSortedDownloadedEpisodes(defaultSortState, all, clientState));
        if (observeLiveData) {
            Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.views.podcastDetailTemplate.DownloadsFilter.2
                @Override // java.lang.Runnable
                public void run() {
                    DownloadsFilter.this.setupLiveDataObservers(podcastId, clientStateName, appSync, zBooleanValue, defaultSortState, downloadQueryParameters);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupLiveDataObservers(final String podcastId, final String clientStateName, final AppSync appSync, final boolean isUnPlayed, final String defaultSortState, final DownloadQueryParameters downloadQueryParameters) {
        LiveData<List<Download>> allLiveData = appSync.download().readAllLiveData(downloadQueryParameters);
        this.downloadLiveData = allLiveData;
        allLiveData.observe(this.lifecycleOwner, new Observer<List<Download>>() { // from class: com.amazon.podcast.views.podcastDetailTemplate.DownloadsFilter.3
            @Override // androidx.lifecycle.Observer
            public void onChanged(final List<Download> downloads) {
                DownloadsFilter.this.postLiveDataUpdates(podcastId, clientStateName, defaultSortState);
            }
        });
        if (isUnPlayed) {
            LiveData<List<Completed>> allLiveData2 = appSync.completed().readAllLiveData(getCompletedQueryParameters(downloadQueryParameters));
            this.completedLiveData = allLiveData2;
            allLiveData2.observe(this.lifecycleOwner, new Observer<List<Completed>>() { // from class: com.amazon.podcast.views.podcastDetailTemplate.DownloadsFilter.4
                @Override // androidx.lifecycle.Observer
                public void onChanged(final List<Completed> completeds) {
                    DownloadsFilter.this.postLiveDataUpdates(podcastId, clientStateName, defaultSortState);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postLiveDataUpdates(final String podcastId, final String clientStateName, final String defaultSortState) {
        this.executorService.submit(new Runnable() { // from class: com.amazon.podcast.views.podcastDetailTemplate.DownloadsFilter.5
            @Override // java.lang.Runnable
            public void run() {
                DownloadsFilter.this.executeDownloadQuery(podcastId, clientStateName, false, defaultSortState);
            }
        });
    }

    private List<Download> getSortedDownloadedEpisodes(final String defaultState, final List<Download> downloads, final RefinementOptionsClientState clientState) {
        String sort = clientState.getSort();
        if (sort != null) {
            defaultState = sort;
        }
        if ("NEWEST_TO_OLDEST".equals(defaultState)) {
            return getNewestToOldestDownloadedEpisodes(downloads);
        }
        return "OLDEST_TO_NEWEST".equals(defaultState) ? getOldestToNewestDownloadedEpisodes(downloads) : downloads;
    }

    private List<Download> getNewestToOldestDownloadedEpisodes(final List<Download> downloads) {
        List<Download> oldestToNewestDownloadedEpisodes = getOldestToNewestDownloadedEpisodes(downloads);
        Collections.reverse(oldestToNewestDownloadedEpisodes);
        return oldestToNewestDownloadedEpisodes;
    }

    private List<Download> getOldestToNewestDownloadedEpisodes(final List<Download> downloads) {
        Collections.sort(downloads, new Comparator<Download>() { // from class: com.amazon.podcast.views.podcastDetailTemplate.DownloadsFilter.6
            @Override // java.util.Comparator
            public int compare(final Download download1, final Download download2) {
                try {
                    return Strings.convertStringToDate(download1.getPublishDate()).compareTo(Strings.convertStringToDate(download2.getPublishDate()));
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
        return downloads;
    }

    private List<Download> getUnPlayedDownloadedEpisodes(final List<Download> downloads, final List<Completed> completedList) {
        HashSet hashSet = new HashSet();
        if (completedList != null) {
            for (Completed completed : completedList) {
                if (completed.getCompleted()) {
                    hashSet.add(completed.getId());
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Download download : downloads) {
            if (!hashSet.contains(download.getId())) {
                arrayList.add(download);
            }
        }
        return arrayList;
    }

    private CompletedQueryParameters getCompletedQueryParameters(final DownloadQueryParameters downloadQueryParameters) {
        return new CompletedQueryParameters(downloadQueryParameters.getPodcastId(), downloadQueryParameters.getSeasonNumber());
    }
}
