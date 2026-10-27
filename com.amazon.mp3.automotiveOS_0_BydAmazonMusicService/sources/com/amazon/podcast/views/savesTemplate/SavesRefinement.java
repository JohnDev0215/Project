package com.amazon.podcast.views.savesTemplate;

import Podcast.SaveInterface.v1_0.RefinementBarElement;
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
import com.amazon.podcast.save.Save;
import com.amazon.podcast.save.SaveQueryParameters;
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
final class SavesRefinement {
    private static final Logger logger = LoggerFactory.getLogger(SavesRefinement.class.getSimpleName());
    private LiveData<List<Completed>> completedLiveData;
    private final Context context;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final LifecycleOwner lifecycleOwner;
    private MutableLiveData<List<Save>> mutableLiveData;
    private RefinementBarElement refinementBarElement;
    private LiveData<List<Save>> saveLiveData;
    private SaveRefinementPreferences userPreferences;

    public SavesRefinement(final Context context, final LifecycleOwner lifecycleOwner, final RefinementBarElement refinementBarElement, final SaveRefinementPreferences userPreferences) {
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        this.refinementBarElement = refinementBarElement;
        this.userPreferences = userPreferences;
    }

    LiveData<List<Save>> getSavedEpisodes() {
        MutableLiveData<List<Save>> mutableLiveData = this.mutableLiveData;
        if (mutableLiveData != null) {
            mutableLiveData.removeObservers(this.lifecycleOwner);
            this.mutableLiveData = null;
        }
        LiveData<List<Save>> liveData = this.saveLiveData;
        if (liveData != null) {
            liveData.removeObservers(this.lifecycleOwner);
            this.saveLiveData = null;
        }
        LiveData<List<Completed>> liveData2 = this.completedLiveData;
        if (liveData2 != null) {
            liveData2.removeObservers(this.lifecycleOwner);
            this.completedLiveData = null;
        }
        this.executorService.submit(new Runnable() { // from class: com.amazon.podcast.views.savesTemplate.SavesRefinement.1
            @Override // java.lang.Runnable
            public void run() {
                SavesRefinement.this.executeSaveQuery(true);
            }
        });
        MutableLiveData<List<Save>> mutableLiveData2 = new MutableLiveData<>();
        this.mutableLiveData = mutableLiveData2;
        return mutableLiveData2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeSaveQuery(final boolean observeLiveData) {
        String sortValue = this.userPreferences.readSortValue(this.context);
        String defaultState = this.refinementBarElement.getSortingOptions().getDefaultState();
        if (sortValue == null) {
            sortValue = defaultState;
        }
        final AppSync appSync = Podcast.getAppSync();
        final SaveQueryParameters saveQueryParameters = new SaveQueryParameters();
        List<Save> all = appSync.save().readAll(saveQueryParameters);
        if ("NEWEST_TO_OLDEST".equals(sortValue)) {
            all = getNewestToOldestSavedEpisodes(all);
        } else if ("OLDEST_TO_NEWEST".equals(sortValue)) {
            all = getOldestToNewestSavedEpisodes(all);
        } else if ("SHOW_NAME".equals(sortValue)) {
            all = getSavedEpisodesShowNameAsc(all);
        }
        final boolean unplayedFilterValue = this.userPreferences.readUnplayedFilterValue(this.context);
        final CompletedQueryParameters completedQueryParameters = new CompletedQueryParameters();
        if (unplayedFilterValue) {
            all = getUnPlayedSavedEpisodes(all, appSync.completed().readAll(completedQueryParameters));
        }
        this.mutableLiveData.postValue(all);
        if (observeLiveData) {
            Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.views.savesTemplate.SavesRefinement.2
                @Override // java.lang.Runnable
                public void run() {
                    SavesRefinement.this.setupLiveDataObservers(appSync, unplayedFilterValue, saveQueryParameters, completedQueryParameters);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupLiveDataObservers(final AppSync appSync, final boolean isUnplayed, final SaveQueryParameters saveQueryParameters, final CompletedQueryParameters completedQueryParameters) {
        LiveData<List<Save>> allLiveData = appSync.save().readAllLiveData(saveQueryParameters);
        this.saveLiveData = allLiveData;
        allLiveData.observe(this.lifecycleOwner, new Observer<List<Save>>() { // from class: com.amazon.podcast.views.savesTemplate.SavesRefinement.3
            @Override // androidx.lifecycle.Observer
            public void onChanged(final List<Save> saves) {
                SavesRefinement.this.postLiveDataUpdates();
            }
        });
        if (isUnplayed) {
            LiveData<List<Completed>> allLiveData2 = appSync.completed().readAllLiveData(completedQueryParameters);
            this.completedLiveData = allLiveData2;
            allLiveData2.observe(this.lifecycleOwner, new Observer<List<Completed>>() { // from class: com.amazon.podcast.views.savesTemplate.SavesRefinement.4
                @Override // androidx.lifecycle.Observer
                public void onChanged(final List<Completed> completes) {
                    SavesRefinement.this.postLiveDataUpdates();
                }
            });
        }
    }

    private List<Save> getUnPlayedSavedEpisodes(final List<Save> saves, final List<Completed> completedList) {
        HashSet hashSet = new HashSet();
        if (completedList != null) {
            for (Completed completed : completedList) {
                if (completed.getCompleted()) {
                    hashSet.add(completed.getId());
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Save save : saves) {
            if (!hashSet.contains(save.getId())) {
                arrayList.add(save);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postLiveDataUpdates() {
        this.executorService.submit(new Runnable() { // from class: com.amazon.podcast.views.savesTemplate.SavesRefinement.5
            @Override // java.lang.Runnable
            public void run() {
                SavesRefinement.this.executeSaveQuery(false);
            }
        });
    }

    private List<Save> getSavedEpisodesShowNameAsc(final List<Save> saves) {
        Collections.sort(saves, new Comparator<Save>() { // from class: com.amazon.podcast.views.savesTemplate.SavesRefinement.6
            @Override // java.util.Comparator
            public int compare(final Save save1, final Save save2) {
                int iCompareToIgnoreCase = save1.getPodcastTitle().compareToIgnoreCase(save2.getPodcastTitle());
                return iCompareToIgnoreCase == 0 ? SavesRefinement.this.sortByPublisDate(save1, save2) : iCompareToIgnoreCase;
            }
        });
        return saves;
    }

    private List<Save> getOldestToNewestSavedEpisodes(final List<Save> saves) {
        List<Save> newestToOldestSavedEpisodes = getNewestToOldestSavedEpisodes(saves);
        Collections.reverse(newestToOldestSavedEpisodes);
        return newestToOldestSavedEpisodes;
    }

    private List<Save> getNewestToOldestSavedEpisodes(final List<Save> saves) {
        Collections.sort(saves, new Comparator<Save>() { // from class: com.amazon.podcast.views.savesTemplate.SavesRefinement.7
            @Override // java.util.Comparator
            public int compare(final Save save1, final Save save2) {
                return SavesRefinement.this.sortByPublisDate(save1, save2);
            }
        });
        return saves;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int sortByPublisDate(final Save save1, final Save save2) {
        try {
            Date dateConvertStringToDate = Strings.convertStringToDate(save1.getPublishDate());
            Date dateConvertStringToDate2 = Strings.convertStringToDate(save2.getPublishDate());
            if (dateConvertStringToDate != null && dateConvertStringToDate2 != null) {
                return dateConvertStringToDate2.compareTo(dateConvertStringToDate);
            }
            return 0;
        } catch (Exception e) {
            logger.debug("Exception while parsing publish date for " + save1.getTitle(), (Throwable) e);
            e.printStackTrace();
            return 0;
        }
    }
}
