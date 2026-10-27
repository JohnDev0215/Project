package com.amazon.mp3.library.fragment;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ListView;
import com.amazon.mp3.actionbar.view.ActionBarSourceToggleView;
import com.amazon.mp3.library.adapter.EmptyFilterAdapter;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.test.QAAutomation;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public abstract class CursorListFragment extends AbstractListFragment {
    private static final String TAG = CursorListFragment.class.getSimpleName();
    private ThrottledChangeObserver mChangeObserver;
    private HandlerThread mChangeObserverHandlerThread;
    private Parcelable mListState;
    private Runnable mOnCursorLoadCompleted;
    private int mReactivateFlags = 0;
    private boolean mDeactivated = true;
    private MyDataSetObserver mDataSetObserver = new MyDataSetObserver();
    private long mAsyncCursorLoadJobId = -1;
    private boolean mReloadCursorOnCompletion = false;
    private int mNumColumns = -1;
    private boolean mOneColumn = false;

    protected abstract CursorAdapter getAdapter();

    protected void onCursorChanged(Cursor newCursor, Cursor oldCursor) {
    }

    protected void onCursorRequeried(Cursor c) {
    }

    protected abstract Cursor query();

    static /* synthetic */ int access$476(CursorListFragment cursorListFragment, int i) {
        int i2 = i | cursorListFragment.mReactivateFlags;
        cursorListFragment.mReactivateFlags = i2;
        return i2;
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mChangeObserverHandlerThread = getChangeObserverHandlerThread();
        this.mChangeObserver = new ThrottledChangeObserver(new Handler(this.mChangeObserverHandlerThread.getLooper()));
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (this.mUseGridView) {
            this.mNumColumns = ((GridView) getListView()).getNumColumns();
        }
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        changeCursor(null);
        HandlerThread handlerThread = this.mChangeObserverHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        super.onDestroy();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment
    public void onActivated() {
        super.onActivated();
        checkReactivateCursor();
        this.mDeactivated = false;
    }

    @Override // com.amazon.mp3.fragment.BaseFragment
    public void onDeactivated() {
        this.mReactivateFlags = 0;
        this.mDeactivated = true;
    }

    public void notifyDataSetChanged() {
        CursorAdapter adapter = getAdapter();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        saveListPosition();
    }

    private void saveListPosition() {
        this.mListState = getListView().onSaveInstanceState();
    }

    HandlerThread getChangeObserverHandlerThread() {
        HandlerThread handlerThread = new HandlerThread("ChangeObserverHandlerThread");
        handlerThread.start();
        return handlerThread;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void onContentUriChanged() {
        super.onContentUriChanged();
        changeCursor(null);
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initQuery();
    }

    protected void initQuery() {
        requeryCursorInBackground();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.actionbar.view.ActionBarSourceToggleView.SourceChangedListener
    public void onSourceToggled(ActionBarSourceToggleView.Source source) {
        super.onSourceToggled(source);
        hideEmptyUI();
    }

    protected final void requeryCursorInBackground(final Runnable onCursorLoadCompleted) {
        if (this.mAsyncCursorLoadJobId == -1) {
            this.mAsyncCursorLoadJobId = 1L;
            setDeferredCursorLoadOptions(false, onCursorLoadCompleted);
            if (getContentUri() == null) {
                String str = TAG;
                Log.error(str, "Not running CursorLoadTask because it would NPE due to a null content uri");
                Log.error(str, TextUtils.join("\n", Thread.currentThread().getStackTrace()));
                return;
            }
            new CursorLoadTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        setDeferredCursorLoadOptions(true, onCursorLoadCompleted);
    }

    protected final void requeryCursorInBackground() {
        requeryCursorInBackground(null);
    }

    protected CursorAdapter getEmptyFilterListAdapter() {
        return new EmptyFilterAdapter((Context) getActivity(), getCurrentFilter(), true);
    }

    private void setDeferredCursorLoadOptions(final boolean reloadCursorOnCompletion, final Runnable onCursorLoadCompleted) {
        this.mReloadCursorOnCompletion = reloadCursorOnCompletion;
        Runnable runnable = this.mOnCursorLoadCompleted;
        if (runnable != null) {
            Log.warning(TAG, String.format("Replacing CursorLoadTask completed callback, callback %s will be ignored.", runnable));
        }
        this.mOnCursorLoadCompleted = onCursorLoadCompleted;
    }

    private boolean wasRequeryRequestedSinceLastCursorLoadCompleted() {
        return this.mReloadCursorOnCompletion;
    }

    private void executeRequery() {
        Runnable runnable = this.mOnCursorLoadCompleted;
        setDeferredCursorLoadOptions(false, null);
        requeryCursorInBackground(runnable);
    }

    private void executeRequeryCallbackIfExists() {
        Runnable runnable = this.mOnCursorLoadCompleted;
        if (runnable != null) {
            runnable.run();
        }
        setDeferredCursorLoadOptions(false, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCursorLoadCompleted() {
        if (wasRequeryRequestedSinceLastCursorLoadCompleted()) {
            executeRequery();
        } else {
            executeRequeryCallbackIfExists();
        }
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        if (jobId == this.mAsyncCursorLoadJobId) {
            this.mAsyncCursorLoadJobId = -1L;
            checkShowEmptyFilterAdapter();
        }
        super.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobStarted(long jobId, Job job) {
        checkShowLoadingText();
        super.onJobStarted(jobId, job);
    }

    protected static Cursor createCursor(Context context, Uri contentUri, String[] projection) {
        return createCursor(context, contentUri, projection, null, null, null);
    }

    protected static Cursor createCursor(Context context, Uri contentUri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (context != null) {
            return context.getContentResolver().query(contentUri, projection, selection, selectionArgs, sortOrder);
        }
        return null;
    }

    protected final void changeCursor(Cursor cursor) {
        String str = TAG;
        Object[] objArr = new Object[2];
        objArr[0] = getClass();
        objArr[1] = cursor == null ? "null" : "not null";
        Log.verbose(str, "%s, changeCursor, %s", objArr);
        CursorAdapter adapter = getAdapter();
        if (adapter == null) {
            return;
        }
        Cursor cursor2 = adapter.getCursor();
        if (cursor != null && getListView().getAdapter() != null) {
            saveListPosition();
        }
        if (cursor == cursor2) {
            return;
        }
        if (cursor2 != null) {
            cursor2.unregisterContentObserver(this.mChangeObserver);
            cursor2.unregisterDataSetObserver(this.mDataSetObserver);
            DbUtil.closeCursor(cursor2);
        }
        adapter.changeCursor(cursor);
        Cursor cursor3 = adapter.getCursor();
        if (cursor3 != null) {
            cursor3.registerContentObserver(this.mChangeObserver);
            cursor3.registerDataSetObserver(this.mDataSetObserver);
        }
        if (getActivity() == null) {
            return;
        }
        if (!checkShowEmptyFilterAdapter()) {
            if (getCursor() != null && isEmpty()) {
                if (getSourceId() == null) {
                    return;
                } else {
                    showEmptyUI();
                }
            } else {
                hideEmptyUI();
            }
            if (adapter != getListView().getAdapter()) {
                setListViewAdapter(adapter);
            }
            if (this.mListState != null) {
                getListView().onRestoreInstanceState(this.mListState);
            }
        } else {
            hideEmptyUI();
        }
        onCursorChanged(cursor3, cursor2);
        QAAutomation.sendCursorChangedLocalBroadcast(getActivity());
    }

    protected final Cursor getCursor() {
        CursorAdapter adapter = getAdapter();
        if (adapter != null) {
            return adapter.getCursor();
        }
        return null;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected boolean isEmpty() {
        Cursor cursor = getCursor();
        return cursor == null || cursor.getCount() <= 0;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected boolean isLoading() {
        return this.mAsyncCursorLoadJobId != -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkShowEmptyFilterAdapter() {
        checkShowLoadingText();
        Cursor cursor = getCursor();
        if ((cursor == null || cursor.getCount() == 0) && !isLoading() && getCurrentFilter().length() > 0) {
            setListViewAdapter(getEmptyFilterListAdapter());
            if (this.mUseGridView) {
                GridView gridView = (GridView) getListView();
                this.mNumColumns = gridView.getNumColumns();
                gridView.setNumColumns(1);
                this.mOneColumn = true;
            } else {
                ((ListView) getListView()).removeHeaderView(this.mEmptyView);
            }
            return true;
        }
        if (this.mUseGridView && this.mOneColumn) {
            ((GridView) getListView()).setNumColumns(this.mNumColumns);
            this.mOneColumn = false;
        }
        return false;
    }

    private void checkReactivateCursor() {
        int i = this.mReactivateFlags;
        if (i == 0) {
            return;
        }
        if ((i & 1) != 0) {
            requeryCursorInBackground();
            return;
        }
        CursorAdapter adapter = getAdapter();
        if (adapter != null) {
            if ((this.mReactivateFlags & 4) != 0) {
                adapter.notifyDataSetInvalidated();
            } else {
                adapter.notifyDataSetChanged();
            }
        }
        this.mReactivateFlags = 0;
    }

    private class ThrottledChangeObserver extends ContentObserver {
        private final Handler handler;
        final AtomicBoolean processedPreviousChange;

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        private ThrottledChangeObserver(final Handler handler) {
            super(handler);
            this.processedPreviousChange = new AtomicBoolean(true);
            this.handler = handler;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            if (this.processedPreviousChange.get()) {
                this.processedPreviousChange.set(false);
                this.handler.postDelayed(new Runnable() { // from class: com.amazon.mp3.library.fragment.CursorListFragment.ThrottledChangeObserver.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ThrottledChangeObserver.this.processedPreviousChange.set(true);
                        if (CursorListFragment.this.mDeactivated) {
                            CursorListFragment.access$476(CursorListFragment.this, 1);
                        } else {
                            CursorListFragment.this.requeryCursorInBackground();
                        }
                    }
                }, 1000L);
            }
        }
    }

    private class MyDataSetObserver extends DataSetObserver {
        private MyDataSetObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (CursorListFragment.this.mDeactivated) {
                CursorListFragment.access$476(CursorListFragment.this, 2);
                return;
            }
            CursorAdapter adapter = CursorListFragment.this.getAdapter();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            if (CursorListFragment.this.mDeactivated) {
                CursorListFragment.access$476(CursorListFragment.this, 4);
                return;
            }
            CursorAdapter adapter = CursorListFragment.this.getAdapter();
            if (adapter != null) {
                adapter.notifyDataSetInvalidated();
            }
        }
    }

    private class CursorLoadTask extends AsyncTask<Void, Void, Cursor> {
        public String toString() {
            return "CursorListFragment.CursorLoadTask";
        }

        private CursorLoadTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Cursor doInBackground(final Void... params) {
            Cursor cursorQuery = null;
            try {
                cursorQuery = CursorListFragment.this.query();
                if (cursorQuery != null) {
                    cursorQuery.getCount();
                }
            } catch (Exception e) {
                Log.error(CursorListFragment.TAG, "Exception caught in CursorLoadTask: " + e.getMessage());
            }
            return cursorQuery;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final Cursor cursor) {
            if (!CursorListFragment.this.isDestroyed()) {
                CursorListFragment.this.mAsyncCursorLoadJobId = -1L;
                CursorListFragment.this.onCursorRequeried(cursor);
                CursorListFragment.this.checkShowEmptyFilterAdapter();
                CursorListFragment.this.onCursorLoadCompleted();
                return;
            }
            DbUtil.closeCursor(cursor);
        }
    }
}
