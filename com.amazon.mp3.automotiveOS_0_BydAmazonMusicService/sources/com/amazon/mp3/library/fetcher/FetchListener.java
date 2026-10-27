package com.amazon.mp3.library.fetcher;

import java.util.Collection;

/* JADX INFO: loaded from: classes3.dex */
public interface FetchListener<T> {
    void onFetchCompleted(int pageNo, Collection<T> results);

    void onFetchStarted(int pageNo);

    void onNetworkError(int pageNo, FetcherError<T> fetcherError);

    void onProcessingError(int pageNo, FetcherError<T> fetcherError);
}
