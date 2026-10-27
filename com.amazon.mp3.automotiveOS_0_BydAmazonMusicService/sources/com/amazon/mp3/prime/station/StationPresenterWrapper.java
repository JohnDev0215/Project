package com.amazon.mp3.prime.station;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.auth.AuthExceptionMetricsLogger;
import com.amazon.mp3.library.fetcher.FetchListener;
import com.amazon.mp3.library.fetcher.Fetcher;
import com.amazon.mp3.library.fetcher.FetcherError;
import com.amazon.mp3.prime.RecommendedStationsFetcher;
import com.amazon.mp3.util.Log;
import com.amazon.music.station.StationException;
import com.amazon.music.station.StationItem;
import com.amazon.music.station.StationManager;
import com.amazon.music.station.model.RefinementItem;
import com.amazon.music.station.model.SectionItem;
import com.amazon.music.station.presenter.StationListPresenter;
import com.amazon.music.station.view.StationListView;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes4.dex */
public class StationPresenterWrapper {
    public static final String TAG = "StationPresenterWrapper";
    private boolean mIsRecs;
    private RefinementItem mQaRefinement;
    private final Fetcher<StationItem> mRecommendedStationsFetcher;
    private final StationListPresenter mStationListPresenter;
    private StationListView mStationListView;
    private StationListViewWrapper mStationListViewWrapper;
    private RefinementItem mStationRecsRefinement;

    private class StationListViewWrapper implements StationListView {
        private final Context mContext;
        private final AuthExceptionMetricsLogger mExceptionMetricsLogger;

        public StationListViewWrapper(final Context context) {
            this.mContext = context.getApplicationContext();
            this.mExceptionMetricsLogger = new AuthExceptionMetricsLogger(context);
        }

        @Override // com.amazon.music.station.view.StationListView
        public void onRefinementItemLoaded(final Map<SectionItem, List<RefinementItem>> map) {
            this.mExceptionMetricsLogger.successfulCall();
            if (AmazonApplication.getCapabilities().shouldShowStationsRecommendations(this.mContext)) {
                List<RefinementItem> list = map.get(StationPresenterWrapper.this.getSectionItemById(map.keySet(), "allStations"));
                if (list != null) {
                    if (list.isEmpty() || !"Recommended Stations".equals(list.get(list.size() - 1).getId())) {
                        list.add(StationPresenterWrapper.this.mStationRecsRefinement);
                    }
                } else {
                    map.put(new SectionItem("Recommended Stations", "Recommended Stations"), Collections.singletonList(StationPresenterWrapper.this.mStationRecsRefinement));
                }
            }
            if (StationPresenterWrapper.this.mStationListView != null) {
                StationPresenterWrapper.this.mStationListView.onRefinementItemLoaded(map);
            }
        }

        @Override // com.amazon.music.station.view.StationListView
        public void onStationItemsLoaded(final RefinementItem refinementItem, final List<StationItem> list) {
            this.mExceptionMetricsLogger.successfulCall();
            if (StationPresenterWrapper.this.mIsRecs || StationPresenterWrapper.this.mStationListView == null) {
                return;
            }
            Observable.from(list).filter(new Func1<StationItem, Boolean>() { // from class: com.amazon.mp3.prime.station.StationPresenterWrapper.StationListViewWrapper.3
                @Override // rx.functions.Func1
                public Boolean call(final StationItem stationItem) {
                    if (stationItem != null && TextUtils.isEmpty(stationItem.getKey())) {
                        Log.warning(StationPresenterWrapper.TAG, "got one station which has null key title: %s seedId: %s seedType: %s ", stationItem.getTitle(), stationItem.getSeedId(), stationItem.getSeedType());
                        return Boolean.FALSE;
                    }
                    if (stationItem == null) {
                        return Boolean.FALSE;
                    }
                    return Boolean.TRUE;
                }
            }).toList().toSingle().subscribe(new Action1<List<StationItem>>() { // from class: com.amazon.mp3.prime.station.StationPresenterWrapper.StationListViewWrapper.1
                @Override // rx.functions.Action1
                public void call(final List<StationItem> stationItems) {
                    StationPresenterWrapper.this.mStationListView.onStationItemsLoaded(refinementItem, stationItems);
                }
            }, new Action1<Throwable>() { // from class: com.amazon.mp3.prime.station.StationPresenterWrapper.StationListViewWrapper.2
                @Override // rx.functions.Action1
                public void call(final Throwable throwable) {
                }
            });
        }

        @Override // com.amazon.music.station.view.StationListView
        public void onNetworkError(final StationException e) {
            if (StationPresenterWrapper.this.mStationListView != null) {
                StationPresenterWrapper.this.mStationListView.onNetworkError(e);
            }
        }

        @Override // com.amazon.music.station.view.StationListView
        public void onProcessingError(final StationException e) {
            this.mExceptionMetricsLogger.exceptionThrown(e);
            if (StationPresenterWrapper.this.mStationListView != null) {
                StationPresenterWrapper.this.mStationListView.onProcessingError(e);
            }
        }
    }

    private class StationFetchListener implements FetchListener<StationItem> {
        @Override // com.amazon.mp3.library.fetcher.FetchListener
        public void onFetchStarted(final int pageNo) {
        }

        private StationFetchListener() {
        }

        @Override // com.amazon.mp3.library.fetcher.FetchListener
        public void onFetchCompleted(final int pageNo, final Collection<StationItem> results) {
            if (StationPresenterWrapper.this.mStationListView == null || StationPresenterWrapper.this.mRecommendedStationsFetcher == null) {
                return;
            }
            StationPresenterWrapper.this.mStationListView.onStationItemsLoaded(StationPresenterWrapper.this.mStationRecsRefinement, (List) StationPresenterWrapper.this.mRecommendedStationsFetcher.getFetchedItems());
        }

        @Override // com.amazon.mp3.library.fetcher.FetchListener
        public void onNetworkError(final int pageNo, final FetcherError<StationItem> fetcherError) {
            if (StationPresenterWrapper.this.mStationListView != null) {
                StationPresenterWrapper.this.mStationListView.onNetworkError(new StationException(new Exception(fetcherError.getDetailMessage())));
            }
        }

        @Override // com.amazon.mp3.library.fetcher.FetchListener
        public void onProcessingError(final int pageNo, final FetcherError<StationItem> fetcherError) {
            if (StationPresenterWrapper.this.mStationListView != null) {
                StationPresenterWrapper.this.mStationListView.onProcessingError(new StationException(new Exception(fetcherError.getDetailMessage())));
            }
        }
    }

    public StationPresenterWrapper(StationListPresenter stationListPresenter, Fetcher<StationItem> recommendedStationsFetcher) {
        this.mQaRefinement = new RefinementItem("QA Stations", "QA Stations", null);
        this.mStationListPresenter = stationListPresenter;
        this.mRecommendedStationsFetcher = recommendedStationsFetcher;
        this.mStationRecsRefinement = new RefinementItem("Recommended Stations", "Recommended Stations", null);
        if (recommendedStationsFetcher != null) {
            recommendedStationsFetcher.addFetchListener(new StationFetchListener());
        }
    }

    public StationPresenterWrapper(final StationManager stationManager) {
        this(new StationListPresenter(stationManager), getRecommendedStationsFetcher(stationManager));
    }

    private static RecommendedStationsFetcher getRecommendedStationsFetcher(final StationManager stationManager) {
        if (AmazonApplication.getCapabilities().shouldShowStationsRecommendations()) {
            return new RecommendedStationsFetcher(stationManager);
        }
        return null;
    }

    public void start(StationListView stationListView, Context context) {
        this.mStationListView = stationListView;
        StationListViewWrapper stationListViewWrapper = new StationListViewWrapper(context);
        this.mStationListViewWrapper = stationListViewWrapper;
        this.mStationListPresenter.start(stationListViewWrapper);
    }

    public void resume(StationListView stationListView, Context context) {
        this.mStationListView = stationListView;
        StationListViewWrapper stationListViewWrapper = new StationListViewWrapper(context);
        this.mStationListViewWrapper = stationListViewWrapper;
        this.mStationListPresenter.resume(stationListViewWrapper);
    }

    public void pause() {
        this.mStationListPresenter.pause();
        this.mStationListView = null;
    }

    public void refreshData() {
        StationListPresenter stationListPresenter = this.mStationListPresenter;
        if (stationListPresenter != null) {
            stationListPresenter.refreshData();
        }
    }

    public void setRefinement(RefinementItem refinementItem) {
        this.mIsRecs = false;
        if (this.mStationRecsRefinement.equals(refinementItem)) {
            Fetcher<StationItem> fetcher = this.mRecommendedStationsFetcher;
            if (fetcher != null && !fetcher.isFetching()) {
                if (this.mRecommendedStationsFetcher.isEmpty()) {
                    this.mRecommendedStationsFetcher.requestNextPage();
                } else {
                    this.mStationListViewWrapper.onStationItemsLoaded(this.mStationRecsRefinement, (List) this.mRecommendedStationsFetcher.getFetchedItems());
                }
            }
            this.mIsRecs = AmazonApplication.getCapabilities().shouldShowStationsRecommendations();
            return;
        }
        if (this.mQaRefinement.getId().equals(refinementItem.getId())) {
            this.mStationListViewWrapper.onStationItemsLoaded(this.mQaRefinement, Collections.singletonList(new StationItem(true, true, false, false, "QA Station " + new Random().nextInt(10), "https://images-na.ssl-images-amazon.com/images/G/01/Gotham/gotham_ArtTriangleTemplate_Country_TodaysCountry.jpg", "b3961c75-3dbc-487d-9285-48af4fc621b9", "", "A2OT1MTTF0HDOK")));
            return;
        }
        this.mStationListPresenter.setRefinement(refinementItem);
    }

    public boolean requestNextPage() {
        Fetcher<StationItem> fetcher;
        if (!this.mIsRecs || (fetcher = this.mRecommendedStationsFetcher) == null) {
            return false;
        }
        fetcher.requestNextPage();
        return this.mRecommendedStationsFetcher.isFetching();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SectionItem getSectionItemById(Set<SectionItem> sectionItemSet, final String sectionId) {
        for (SectionItem sectionItem : sectionItemSet) {
            if (sectionId.equals(sectionItem.getId())) {
                return sectionItem;
            }
        }
        return null;
    }
}
