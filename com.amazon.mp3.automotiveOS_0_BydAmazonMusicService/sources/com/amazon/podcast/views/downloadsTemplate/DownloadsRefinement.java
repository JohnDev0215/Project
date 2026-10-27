package com.amazon.podcast.views.downloadsTemplate;

import Podcast.DownloadInterface.v1_0.RefinementBarElement;
import android.content.Context;
import androidx.annotation.WorkerThread;
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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class DownloadsRefinement {
    private static final Logger logger = LoggerFactory.getLogger(DownloadsRefinement.class.getSimpleName());
    private LiveData<List<Completed>> completedLiveData;
    private final Context context;
    private LiveData<List<Download>> downloadLiveData;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final LifecycleOwner lifecycleOwner;
    private MutableLiveData<List<Download>> mutableLiveData;
    private RefinementBarElement refinementBarElement;
    private DownloadRefinementPreferences userPreferences;

    public DownloadsRefinement(final Context context, final LifecycleOwner lifecycleOwner, final RefinementBarElement refinementBarElement, final DownloadRefinementPreferences userPreferences) {
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        this.refinementBarElement = refinementBarElement;
        this.userPreferences = userPreferences;
    }

    public LiveData<List<Download>> getDownloadedEpisodes() {
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
        this.executorService.submit(new Runnable() { // from class: com.amazon.podcast.views.downloadsTemplate.DownloadsRefinement.1
            @Override // java.lang.Runnable
            public void run() {
                DownloadsRefinement.this.executeDownloadQuery(true);
            }
        });
        MutableLiveData<List<Download>> mutableLiveData2 = new MutableLiveData<>();
        this.mutableLiveData = mutableLiveData2;
        return mutableLiveData2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeDownloadQuery(final boolean observeLiveData) {
        String sortValue = this.userPreferences.readSortValue(this.context);
        String defaultState = this.refinementBarElement.getSortingOptions().getDefaultState();
        if (sortValue == null) {
            sortValue = defaultState;
        }
        final AppSync appSync = Podcast.getAppSync();
        final DownloadQueryParameters downloadQueryParameters = new DownloadQueryParameters();
        List<Download> all = appSync.download().readAll(downloadQueryParameters);
        final boolean unplayedFilterValue = this.userPreferences.readUnplayedFilterValue(this.context);
        final CompletedQueryParameters completedQueryParameters = new CompletedQueryParameters();
        this.mutableLiveData.postValue(sortAndFilterEpisodes(all, sortValue, unplayedFilterValue));
        if (observeLiveData) {
            Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.views.downloadsTemplate.DownloadsRefinement.2
                @Override // java.lang.Runnable
                public void run() {
                    DownloadsRefinement.this.setupLiveDataObservers(appSync, unplayedFilterValue, downloadQueryParameters, completedQueryParameters);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupLiveDataObservers(final AppSync appSync, final boolean isUnplayed, final DownloadQueryParameters downloadQueryParameters, final CompletedQueryParameters completedQueryParameters) {
        LiveData<List<Download>> allLiveData = appSync.download().readAllLiveData(downloadQueryParameters);
        this.downloadLiveData = allLiveData;
        allLiveData.observe(this.lifecycleOwner, new Observer<List<Download>>() { // from class: com.amazon.podcast.views.downloadsTemplate.DownloadsRefinement.3
            @Override // androidx.lifecycle.Observer
            public void onChanged(final List<Download> downloads) {
                DownloadsRefinement.this.postLiveDataUpdates();
            }
        });
        if (isUnplayed) {
            LiveData<List<Completed>> allLiveData2 = appSync.completed().readAllLiveData(completedQueryParameters);
            this.completedLiveData = allLiveData2;
            allLiveData2.observe(this.lifecycleOwner, new Observer<List<Completed>>() { // from class: com.amazon.podcast.views.downloadsTemplate.DownloadsRefinement.4
                @Override // androidx.lifecycle.Observer
                public void onChanged(final List<Completed> completeds) {
                    DownloadsRefinement.this.postLiveDataUpdates();
                }
            });
        }
    }

    @WorkerThread
    public List<Download> sortAndFilterEpisodes(final List<Download> downloadList, final String sortState, final boolean isUnplayed) {
        if ("NEWEST_TO_OLDEST".equals(sortState)) {
            downloadList = getNewestToOldestDownloadedEpisodes(downloadList);
        } else if ("OLDEST_TO_NEWEST".equals(sortState)) {
            downloadList = getOldestToNewestDownloadedEpisodes(downloadList);
        } else if ("SHOW_NAME".equals(sortState)) {
            downloadList = getDownloadedEpisodesShowNameAsc(downloadList);
        }
        return isUnplayed ? getUnPlayedDownloadedEpisodes(downloadList, Podcast.getAppSync().completed().readAll(new CompletedQueryParameters())) : downloadList;
    }

    public List<Download> getUnPlayedDownloadedEpisodes(final List<Download> downloads, final List<Completed> completedList) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public void postLiveDataUpdates() {
        this.executorService.submit(new Runnable() { // from class: com.amazon.podcast.views.downloadsTemplate.DownloadsRefinement.5
            @Override // java.lang.Runnable
            public void run() {
                DownloadsRefinement.this.executeDownloadQuery(false);
            }
        });
    }

    public List<Download> getDownloadedEpisodesShowNameAsc(final List<Download> downloads) {
        Collections.sort(downloads, new Comparator<Download>() { // from class: com.amazon.podcast.views.downloadsTemplate.DownloadsRefinement.6
            @Override // java.util.Comparator
            public int compare(final Download download1, final Download download2) {
                int iCompareToIgnoreCase = download1.getPodcastTitle().compareToIgnoreCase(download2.getPodcastTitle());
                return iCompareToIgnoreCase == 0 ? DownloadsRefinement.this.sortByPublisDate(download1, download2) : iCompareToIgnoreCase;
            }
        });
        return downloads;
    }

    public List<Download> getNewestToOldestDownloadedEpisodes(final List<Download> downloads) {
        Collections.sort(downloads, new Comparator<Download>() { // from class: com.amazon.podcast.views.downloadsTemplate.DownloadsRefinement.7
            @Override // java.util.Comparator
            public int compare(final Download download1, final Download download2) {
                return DownloadsRefinement.this.sortByPublisDate(download1, download2);
            }
        });
        return downloads;
    }

    public List<Download> getOldestToNewestDownloadedEpisodes(final List<Download> downloads) {
        List<Download> newestToOldestDownloadedEpisodes = getNewestToOldestDownloadedEpisodes(downloads);
        Collections.reverse(newestToOldestDownloadedEpisodes);
        return newestToOldestDownloadedEpisodes;
    }

    public int sortByPublisDate(final Download download1, final Download download2) {
        try {
            Date dateConvertStringToDate = Strings.convertStringToDate(download1.getPublishDate());
            Date dateConvertStringToDate2 = Strings.convertStringToDate(download2.getPublishDate());
            if (dateConvertStringToDate != null && dateConvertStringToDate2 != null) {
                return dateConvertStringToDate2.compareTo(dateConvertStringToDate);
            }
            return 0;
        } catch (Exception e) {
            logger.debug("Exception while parsing publish date for " + download1.getTitle(), (Throwable) e);
            e.printStackTrace();
            return 0;
        }
    }
}
