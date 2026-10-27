package com.amazon.music.connect.feed;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.amazon.music.connect.IdentityProvider;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public class FeedViewModel extends ViewModel {
    private static final long BACKGROUND_REFRESH_DELAY_IN_MILLISECONDS = TimeUnit.HOURS.toMillis(2);
    private static final long MIMIMUM_REFRESH_DELAY_IN_MILLISECONDS = TimeUnit.MINUTES.toMillis(15);
    private static final String TAG = "FeedViewModel";
    public boolean artistFollowSupported;
    private final FeedDataSourceFactory feedDataSourceFactory;
    private final MutableLiveData<FeedEvent> feedEventLiveData;
    private final LiveData<PagedList<FeedItem>> feedItemLiveData;
    private final HandlerThread handlerThread;
    public final IdentityProvider identityProvider;
    private long lastRefreshTime;
    private final Handler periodicRefreshHandler;

    public FeedViewModel(IdentityProvider identityProvider, String str, String str2, boolean z) {
        HandlerThread handlerThread = new HandlerThread(TAG, 10);
        this.handlerThread = handlerThread;
        this.lastRefreshTime = 0L;
        FeedDataSourceFactory feedDataSourceFactory = new FeedDataSourceFactory(new FeedService(identityProvider, str, str2));
        this.feedDataSourceFactory = feedDataSourceFactory;
        this.feedItemLiveData = new LivePagedListBuilder(feedDataSourceFactory, new PagedList.Config.Builder().setEnablePlaceholders(false).setInitialLoadSizeHint(10).setPageSize(10).build()).setFetchExecutor(Executors.newFixedThreadPool(5)).build();
        this.feedEventLiveData = new MutableLiveData<>();
        this.artistFollowSupported = z;
        handlerThread.start();
        this.periodicRefreshHandler = new Handler(handlerThread.getLooper());
        this.identityProvider = identityProvider;
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        stopPeriodicRefresh();
    }

    public LiveData<PagedList<FeedItem>> getFeedItemsLiveData() {
        return this.feedItemLiveData;
    }

    public MutableLiveData<FeedEvent> getFeedEventLiveData() {
        return this.feedEventLiveData;
    }

    public void startPeriodicRefresh() {
        stopPeriodicRefresh();
        this.periodicRefreshHandler.post(new Runnable() { // from class: com.amazon.music.connect.feed.FeedViewModel.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (FeedViewModel.this.lastRefreshTime + FeedViewModel.MIMIMUM_REFRESH_DELAY_IN_MILLISECONDS < System.currentTimeMillis()) {
                        FeedViewModel.this.refresh();
                    }
                    FeedViewModel.this.periodicRefreshHandler.postDelayed(this, FeedViewModel.BACKGROUND_REFRESH_DELAY_IN_MILLISECONDS);
                } catch (Exception e) {
                    Log.e(FeedViewModel.TAG, "error", e);
                }
            }
        });
    }

    public void stopPeriodicRefresh() {
        this.periodicRefreshHandler.removeCallbacksAndMessages(null);
    }

    public void refresh() {
        this.lastRefreshTime = System.currentTimeMillis();
        MutableLiveData<FeedDataSource> mutableLiveData = this.feedDataSourceFactory.liveData;
        if (mutableLiveData == null || mutableLiveData.getValue() == null) {
            return;
        }
        this.feedDataSourceFactory.liveData.getValue().invalidate();
    }
}
