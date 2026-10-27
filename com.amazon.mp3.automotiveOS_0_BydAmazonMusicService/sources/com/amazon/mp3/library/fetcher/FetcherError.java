package com.amazon.mp3.library.fetcher;

/* JADX INFO: loaded from: classes3.dex */
public class FetcherError<T> {
    private final String mDetailMessage = "";
    private final Fetcher<T> mFetcher;

    public FetcherError(Fetcher<T> fetcher) {
        this.mFetcher = fetcher;
    }

    public String getDetailMessage() {
        return this.mDetailMessage;
    }
}
