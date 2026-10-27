package com.amazon.music.connect.feed;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

/* JADX INFO: loaded from: classes4.dex */
public class FeedDataSourceFactory extends DataSource.Factory {
    private FeedDataSource feedDataSource;
    private FeedService feedService;
    public MutableLiveData<FeedDataSource> liveData;

    public FeedDataSourceFactory(FeedService feedService) {
        this.feedService = feedService;
    }

    @Override // androidx.paging.DataSource.Factory
    public DataSource create() {
        this.feedDataSource = new FeedDataSource(this.feedService);
        MutableLiveData<FeedDataSource> mutableLiveData = new MutableLiveData<>();
        this.liveData = mutableLiveData;
        mutableLiveData.postValue(this.feedDataSource);
        return this.feedDataSource;
    }
}
