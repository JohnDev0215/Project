package com.amazon.mp3.api.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.amazon.mp3.api.data.DataReceiver;
import com.amazon.mp3.util.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseDataProvider<T extends DataReceiver, U> implements Parcelable {
    private T mDataReceiver;
    private static final String TAG = BaseDataProvider.class.getSimpleName();
    private static AtomicInteger sJobIdGenerator = new AtomicInteger(0);
    public static final Parcelable.Creator<Object> CREATOR = new Parcelable.Creator<Object>() { // from class: com.amazon.mp3.api.data.BaseDataProvider.2
        @Override // android.os.Parcelable.Creator
        public Object createFromParcel(Parcel in) {
            throw new RuntimeException("should never be called!");
        }

        @Override // android.os.Parcelable.Creator
        public Object[] newArray(int size) {
            throw new RuntimeException("should never be called!");
        }
    };
    private final Object mLock = new Object();
    private final Map<Integer, Set<Integer>> mTypedJobsMapping = new HashMap();
    private final Map<Integer, LoaderContext> mJobContexts = new HashMap();
    private LoaderManager.LoaderCallbacks<U> mLoaderCallback = new LoaderManager.LoaderCallbacks<U>() { // from class: com.amazon.mp3.api.data.BaseDataProvider.1
        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<U> arg0) {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<U> loader, U result) {
            synchronized (BaseDataProvider.this.mLock) {
                int id = loader.getId();
                if (id == 0) {
                    Log.debug(BaseDataProvider.TAG, "Observed a finished job without record of the job!");
                    return;
                }
                LoaderContext loaderContext = (LoaderContext) BaseDataProvider.this.mJobContexts.get(Integer.valueOf(id));
                if (loaderContext == null) {
                    Log.debug(BaseDataProvider.TAG, "Observed a finished job without a context object!");
                    return;
                }
                Integer numValueOf = Integer.valueOf(loaderContext.getType());
                if (numValueOf == null) {
                    Log.error(BaseDataProvider.TAG, "Observed a finished job without record of the job!");
                    return;
                }
                if (!loaderContext.hasFlag(1)) {
                    BaseDataProvider.this.removeJob(Integer.valueOf(id));
                }
                BaseDataProvider.this.onFinished(numValueOf.intValue(), id, result);
            }
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<U> onCreateLoader(int id, Bundle params) {
            if (id == 0) {
                Log.debug(BaseDataProvider.TAG, "Observed a onCreateLoader request without record of the job!");
                return null;
            }
            return BaseDataProvider.this.onCreateLoader(Integer.valueOf(((LoaderContext) BaseDataProvider.this.mJobContexts.get(Integer.valueOf(id))).getType()).intValue(), id, params);
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected abstract Loader<U> onCreateLoader(int type, int key, Bundle params);

    protected abstract void onFinished(int type, int key, U result);

    public BaseDataProvider(T dataReceiver) {
        setReceiver(dataReceiver);
    }

    public void setReceiver(T receiver) {
        this.mDataReceiver = receiver;
    }

    protected T getReceiver() {
        return this.mDataReceiver;
    }

    private LoaderManager getLoaderManager() {
        if (getReceiver() == null || getReceiver().getLoaderManager() == null) {
            throw new RuntimeException("Unable to get a valid LoaderManager!");
        }
        return getReceiver().getLoaderManager();
    }

    protected final int addJob(int type, Bundle params, int flags, int loaderId) {
        synchronized (this.mLock) {
            if (loaderId == 0) {
                return createAndStartNewJob(type, params, flags);
            }
            if (hasJob(Integer.valueOf(loaderId))) {
                LoaderContext loaderContext = this.mJobContexts.get(Integer.valueOf(loaderId));
                if (loaderContext.hasFlag(1)) {
                    return startExistingJob(loaderContext);
                }
                throw new IllegalArgumentException("Cannot reuse non-persistent id=" + loaderId);
            }
            return createAndStartNewJob(type, params, flags);
        }
    }

    private int startExistingJob(LoaderContext existingJob) {
        startLoader(existingJob.getId(), existingJob.getParameters());
        return existingJob.getId();
    }

    private int createAndStartNewJob(int type, Bundle params, int flags) {
        int iIncrementAndGet = sJobIdGenerator.incrementAndGet();
        insertJob(new LoaderContext(iIncrementAndGet, type, params, flags));
        startLoader(iIncrementAndGet, params);
        return iIncrementAndGet;
    }

    private void insertJob(final LoaderContext context) {
        Integer numValueOf = Integer.valueOf(context.getId());
        Integer numValueOf2 = Integer.valueOf(context.getType());
        Set<Integer> hashSet = this.mTypedJobsMapping.get(numValueOf2);
        if (hashSet == null) {
            hashSet = new HashSet<>();
            this.mTypedJobsMapping.put(numValueOf2, hashSet);
        }
        hashSet.add(numValueOf);
        this.mJobContexts.put(numValueOf, context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeJob(Integer jobId) {
        LoaderContext loaderContext = this.mJobContexts.get(jobId);
        this.mJobContexts.remove(jobId);
        Set<Integer> set = this.mTypedJobsMapping.get(Integer.valueOf(loaderContext.getType()));
        if (set != null) {
            set.remove(jobId);
        }
        getLoaderManager().destroyLoader(jobId.intValue());
    }

    private boolean hasJob(Integer jobId) {
        return jobId.intValue() != 0 && this.mJobContexts.containsKey(jobId);
    }

    private void startLoader(int id, Bundle params) {
        getLoaderManager().initLoader(id, params, this.mLoaderCallback);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeMap(this.mTypedJobsMapping);
        dest.writeMap(this.mJobContexts);
    }
}
