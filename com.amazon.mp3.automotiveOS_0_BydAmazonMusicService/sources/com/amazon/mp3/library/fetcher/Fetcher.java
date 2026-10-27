package com.amazon.mp3.library.fetcher;

import android.os.AsyncTask;
import android.os.Handler;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.Log;
import com.amazon.musicensembleservice.AllowedParentalControls;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Fetcher<T> {
    private ErrorType mErrorType;
    protected int mPageNo = 0;
    protected boolean mHasMore = true;
    protected boolean mIsResponseEmpty = false;
    private Collection<T> mFetchedItems = new ArrayList();
    protected Collection<FetchListener<T>> mFetchListeners = new ArrayList(1);
    protected boolean mIsFetching = false;
    private boolean mHasError = false;
    private Handler mRetryHandler = new Handler();
    private long mRetryWaitTimeMs = 1000;

    protected enum ErrorType {
        PROCESSING,
        NETWORK
    }

    protected abstract Collection<T> doFetch();

    public abstract int getItemsPerPage();

    public void addFetchListener(FetchListener<T> fetchListener) {
        if (this.mFetchListeners.contains(fetchListener)) {
            return;
        }
        this.mFetchListeners.add(fetchListener);
        if (this.mIsFetching) {
            fetchListener.onFetchStarted(this.mPageNo);
        }
    }

    public void requestNextPage() {
        if (this.mIsFetching || !hasMore()) {
            return;
        }
        this.mIsFetching = true;
        Iterator<FetchListener<T>> it = this.mFetchListeners.iterator();
        while (it.hasNext()) {
            it.next().onFetchStarted(this.mPageNo);
        }
        new FetchingAsyncTask().execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Collection<T> fetch() {
        if (!hasMore()) {
            throw new IllegalStateException("Fetcher has no more data");
        }
        Collection<T> collectionDoFetch = doFetch();
        if (!this.mHasError) {
            this.mFetchedItems.addAll(collectionDoFetch);
            this.mHasMore = !collectionDoFetch.isEmpty();
            this.mIsResponseEmpty = collectionDoFetch.isEmpty();
        }
        return collectionDoFetch;
    }

    public boolean hasMore() {
        return this.mHasMore;
    }

    public int getPageNo() {
        return this.mPageNo;
    }

    public Collection<T> getFetchedItems() {
        return this.mFetchedItems;
    }

    public boolean isEmpty() {
        return this.mFetchedItems.isEmpty();
    }

    public boolean isFetching() {
        return this.mIsFetching;
    }

    private class FetchingAsyncTask extends AsyncTask<Void, Void, Collection<T>> {
        private FetchingAsyncTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Collection<T> doInBackground(final Void... params) {
            Thread.currentThread().setName("FetchingAsyncTask");
            return Fetcher.this.fetch();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final Collection<T> results) {
            Fetcher fetcher = Fetcher.this;
            fetcher.mIsFetching = false;
            if (fetcher.mHasError) {
                FetcherError<T> fetcherError = new FetcherError<>(Fetcher.this);
                for (FetchListener<T> fetchListener : Fetcher.this.mFetchListeners) {
                    if (Fetcher.this.mErrorType == ErrorType.PROCESSING) {
                        fetchListener.onProcessingError(Fetcher.this.mPageNo, fetcherError);
                    } else {
                        fetchListener.onNetworkError(Fetcher.this.mPageNo, fetcherError);
                    }
                }
                Fetcher.this.mHasError = false;
                Fetcher.this.mRetryHandler.postDelayed(new Runnable() { // from class: com.amazon.mp3.library.fetcher.Fetcher.FetchingAsyncTask.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Fetcher.this.requestNextPage();
                        Log.debug(Fetcher.this.getClass().getSimpleName(), "Retrying Hermes call");
                    }
                }, Fetcher.this.mRetryWaitTimeMs);
                Fetcher fetcher2 = Fetcher.this;
                fetcher2.mRetryWaitTimeMs = Math.min(64000L, fetcher2.mRetryWaitTimeMs * 2);
                return;
            }
            Iterator<FetchListener<T>> it = Fetcher.this.mFetchListeners.iterator();
            while (it.hasNext()) {
                it.next().onFetchCompleted(Fetcher.this.mPageNo, results);
            }
            Fetcher fetcher3 = Fetcher.this;
            fetcher3.mPageNo++;
            fetcher3.mRetryWaitTimeMs = 1000L;
        }
    }

    protected void setHasError(boolean hasError, ErrorType errorType) {
        this.mHasError = hasError;
        this.mErrorType = errorType;
    }

    protected AllowedParentalControls getParentalControls() {
        AllowedParentalControls allowedParentalControls = new AllowedParentalControls();
        allowedParentalControls.setHasExplicitLanguage(Boolean.valueOf(!AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()));
        return allowedParentalControls;
    }
}
