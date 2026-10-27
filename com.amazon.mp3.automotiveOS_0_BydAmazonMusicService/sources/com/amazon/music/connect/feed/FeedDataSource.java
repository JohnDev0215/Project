package com.amazon.music.connect.feed;

import android.util.Log;
import androidx.paging.PageKeyedDataSource;
import com.amazon.music.connect.feed.FeedItem;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class FeedDataSource extends PageKeyedDataSource<Integer, FeedItem> {
    private static final String TAG = "FeedDataSource";
    private FeedService feedService;

    @Override // androidx.paging.PageKeyedDataSource
    public void loadBefore(PageKeyedDataSource.LoadParams<Integer> loadParams, PageKeyedDataSource.LoadCallback<Integer, FeedItem> loadCallback) {
    }

    public FeedDataSource(FeedService feedService) {
        this.feedService = feedService;
    }

    @Override // androidx.paging.PageKeyedDataSource
    public void loadInitial(PageKeyedDataSource.LoadInitialParams<Integer> loadInitialParams, PageKeyedDataSource.LoadInitialCallback<Integer, FeedItem> loadInitialCallback) {
        try {
            ArrayList<FeedItem> feedItems = this.feedService.getFeedItems(loadInitialParams.requestedLoadSize, 0);
            loadInitialCallback.onResult(filterUnsupportedFeedItems(feedItems), null, Integer.valueOf(feedItems.size()));
        } catch (Exception e) {
            Log.e(TAG, "Encountered exception while fetching initial items", e);
            loadInitialCallback.onResult(new ArrayList(), null, 0);
        }
    }

    @Override // androidx.paging.PageKeyedDataSource
    public void loadAfter(PageKeyedDataSource.LoadParams<Integer> loadParams, PageKeyedDataSource.LoadCallback<Integer, FeedItem> loadCallback) {
        try {
            ArrayList<FeedItem> feedItems = this.feedService.getFeedItems(loadParams.requestedLoadSize, loadParams.key.intValue());
            loadCallback.onResult(filterUnsupportedFeedItems(feedItems), Integer.valueOf(loadParams.key.intValue() + feedItems.size()));
        } catch (Exception e) {
            Log.e(TAG, "Encountered exception while fetching items", e);
            loadCallback.onResult(new ArrayList(), loadParams.key);
        }
    }

    private ArrayList<FeedItem> filterUnsupportedFeedItems(ArrayList<FeedItem> arrayList) {
        ArrayList<FeedItem> arrayList2 = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            FeedItem feedItem = arrayList.get(i);
            FeedItem.Type type = feedItem.type;
            if (type == FeedItem.Type.ARTIST_NEW_RELEASE || type == FeedItem.Type.FRIEND_FOLLOW || (feedItem.title != null && feedItem.linkUrl != null)) {
                arrayList2.add(feedItem);
            }
        }
        return arrayList2;
    }
}
