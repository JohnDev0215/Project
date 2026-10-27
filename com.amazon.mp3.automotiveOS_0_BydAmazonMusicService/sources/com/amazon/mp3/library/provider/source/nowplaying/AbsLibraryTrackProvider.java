package com.amazon.mp3.library.provider.source.nowplaying;

import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.util.Log;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbsLibraryTrackProvider extends BaseTrackProvider {
    private static final String TAG = "AbsLibraryTrackProvider";
    private Cursor mCursor;
    private MusicTrack mLastAccessedTrack;
    private LoadCollectionTask mLoadCollectionTask;
    private AtomicInteger mLoadRequestId = new AtomicInteger();
    private LibraryTrackProviderContentObserver mObserver = new LibraryTrackProviderContentObserver(AmazonApplication.getBackgroundHandler());
    private RefreshTracksTask mRefreshTracksTask;
    private boolean mShuffle;

    protected abstract void loadCollectionInternal(LoadCookie cookie);

    protected abstract void refreshInternal(LoadCookie cookie);

    protected void updateUri(MusicTrack track) {
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void loadCollection(LoadCookie cookie) {
        cancel(1);
        this.mLoadRequestId.set(cookie.getRequestId());
        this.mShuffle = cookie.getShuffle();
        setLoading(true);
        loadCollectionInternal(cookie);
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void cancel() {
        cancel(1);
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void refresh(LoadCookie cookie) {
        cancel();
        this.mLoadRequestId.set(cookie.getRequestId());
        setLoading(true);
        refreshInternal(cookie);
    }

    protected synchronized void cancel(int id) {
        RefreshTracksTask refreshTracksTask;
        if (id == 1) {
            LoadCollectionTask loadCollectionTask = this.mLoadCollectionTask;
            if (loadCollectionTask != null) {
                loadCollectionTask.cancel(true);
            }
            RefreshTracksTask refreshTracksTask2 = this.mRefreshTracksTask;
            if (refreshTracksTask2 != null) {
                refreshTracksTask2.cancel(true);
            }
        } else if (id == 2 && (refreshTracksTask = this.mRefreshTracksTask) != null) {
            refreshTracksTask.cancel(true);
        }
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public MusicTrack getTrack(int position) {
        return setLastAccessedTrack(position);
    }

    private synchronized MusicTrack setLastAccessedTrack(int position) {
        Cursor cursor = this.mCursor;
        if (cursor != null && !cursor.isClosed()) {
            if (!this.mCursor.moveToPosition(position)) {
                return null;
            }
            return setLastAccessedTrack();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized MusicTrack setLastAccessedTrack() {
        Cursor cursor = this.mCursor;
        if (cursor != null && !cursor.isBeforeFirst() && !this.mCursor.isAfterLast() && !this.mCursor.isClosed()) {
            MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(this.mCursor);
            this.mLastAccessedTrack = track;
            updateUri(track);
            return track;
        }
        return null;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.BaseTrackProvider, com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public int getCount() {
        Cursor cursor = this.mCursor;
        if (cursor == null || cursor.isClosed()) {
            return 0;
        }
        return this.mCursor.getCount();
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void close() {
        closeCursor();
        this.mCursor = null;
    }

    private void closeCursor() {
        Cursor cursor = this.mCursor;
        if (cursor == null || cursor.isClosed()) {
            return;
        }
        this.mObserver.reset();
        this.mCursor.unregisterContentObserver(this.mObserver);
        this.mCursor.close();
    }

    private final class LibraryTrackProviderContentObserver extends ContentObserver {
        public void reset() {
        }

        public LibraryTrackProviderContentObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            Log.debug(AbsLibraryTrackProvider.TAG, "NowPlayingContentObserver: onChange - receive content observer change notification");
            Iterator<TrackProviderListener> it = AbsLibraryTrackProvider.this.getListeners().iterator();
            while (it.hasNext()) {
                it.next().onCollectionChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void swapCursor(Cursor newCursor) {
        if (newCursor == this.mCursor) {
            return;
        }
        closeCursor();
        this.mCursor = newCursor;
        newCursor.registerContentObserver(this.mObserver);
    }

    private final class LoadCollectionTask extends AsyncTask<Void, Void, Void> {
        private final LoadCookie mCookie;
        private final Cursor mCursor;

        LoadCollectionTask(LoadCookie cookie, Cursor cursor) {
            this.mCursor = cursor;
            this.mCookie = cookie;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... params) {
            AbsLibraryTrackProvider.this.swapCursor(this.mCursor);
            AbsLibraryTrackProvider.this.setLoading(false);
            Log.debug(AbsLibraryTrackProvider.TAG, "LoadTracksTask: moveTo[%d]", Integer.valueOf(this.mCookie.getCollectionPosition()));
            this.mCursor.moveToFirst();
            Set<TrackProviderListener> listeners = AbsLibraryTrackProvider.this.getListeners();
            if (listeners == null) {
                return null;
            }
            Iterator<TrackProviderListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onCollectionLoaded(this.mCookie);
            }
            return null;
        }
    }

    private final class RefreshTracksTask extends AsyncTask<Void, Void, Integer> {
        private final LoadCookie mCookie;
        private final Cursor mTaskCursor;

        RefreshTracksTask(@NonNull LoadCookie cookie, @NonNull Cursor cursor) {
            this.mCookie = cookie;
            this.mTaskCursor = cursor;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Removed duplicated region for block: B:31:0x011f  */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Integer doInBackground(java.lang.Void... r15) {
            /*
                Method dump skipped, instruction units count: 436
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.provider.source.nowplaying.AbsLibraryTrackProvider.RefreshTracksTask.doInBackground(java.lang.Void[]):java.lang.Integer");
        }

        private boolean isOwnershipChangedToNotInLibrary(final MusicTrack currentTrack, final MusicTrack newTrack) {
            return currentTrack.getOwnershipStatus() != newTrack.getOwnershipStatus() && newTrack.getOwnershipStatus() == ContentOwnershipStatus.NOT_IN_LIBRARY;
        }

        private boolean isSameTrack(final MusicTrack currentTrack, final MusicTrack newTrack) {
            String luid = currentTrack != null ? currentTrack.getLuid() : null;
            String luid2 = newTrack != null ? newTrack.getLuid() : null;
            if (currentTrack != null || newTrack != null) {
                if (currentTrack == null && newTrack != null) {
                    return false;
                }
                if (currentTrack != null && newTrack == null) {
                    return false;
                }
                if (luid2 != null && luid != null) {
                    return luid2.equals(luid);
                }
                if (newTrack.getMatchHash() != currentTrack.getMatchHash()) {
                    return false;
                }
            }
            return true;
        }

        @Override // android.os.AsyncTask
        protected void onCancelled() {
            Log.debug(AbsLibraryTrackProvider.TAG, "RefreshTracksTask: cancelled");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Integer result) {
            Log.debug(AbsLibraryTrackProvider.TAG, "RefreshTracksTask %d: onPostExecute()", Long.valueOf(Thread.currentThread().getId()));
            AbsLibraryTrackProvider.this.setLastAccessedTrack();
            if (result.intValue() == 2) {
                Log.debug(AbsLibraryTrackProvider.TAG, "RefreshTracksTask %d: onPostExecute() result=%s", Long.valueOf(Thread.currentThread().getId()), "NO CHANGE");
                Iterator<TrackProviderListener> it = AbsLibraryTrackProvider.this.getListeners().iterator();
                while (it.hasNext()) {
                    it.next().onCollectionRefreshed(false);
                }
                return;
            }
            if (result.intValue() == 0 || result.intValue() == 1) {
                Log.debug(AbsLibraryTrackProvider.TAG, "RefreshTracksTask %d: onPostExecute() result=%s", Long.valueOf(Thread.currentThread().getId()), "RELOAD or REFRESH");
                Iterator<TrackProviderListener> it2 = AbsLibraryTrackProvider.this.getListeners().iterator();
                while (it2.hasNext()) {
                    it2.next().onCollectionRefreshed(true);
                }
                return;
            }
            Log.debug(AbsLibraryTrackProvider.TAG, "RefreshTracksTask %d: onPostExecute() result=%s", Long.valueOf(Thread.currentThread().getId()), "UNKNOWN");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int updateTrackPosition(final Cursor cursor) {
        long id = Thread.currentThread().getId();
        String str = TAG;
        Log.debug(str, "updateTrackPosition: %d", Long.valueOf(id));
        if (this.mLastAccessedTrack == null) {
            return -1;
        }
        cursor.moveToFirst();
        String asin = this.mLastAccessedTrack.getAsin();
        String luid = this.mLastAccessedTrack.getLuid();
        Log.info(str, "updateTrackPosition: %d", Long.valueOf(id));
        Log.debug(str, "updateTrackPosition: %d ASIN=%s LUID=%s", Long.valueOf(id), asin, luid);
        int columnIndex = cursor.getColumnIndex("asin");
        if (TextUtils.isEmpty(asin)) {
            columnIndex = cursor.getColumnIndex("luid");
            asin = luid;
        }
        if (TextUtils.isEmpty(asin)) {
            Log.debug(str, "updateTrackPosition %d: both asin and luid are invalid, can't find track", Long.valueOf(id));
            return -1;
        }
        while (!asin.equals(cursor.getString(columnIndex))) {
            if (!cursor.moveToNext()) {
                Log.debug(TAG, "updateTrackPosition %d: didn't find track", Long.valueOf(id));
                return -1;
            }
        }
        Log.debug(TAG, "updateTrackPosition %d: found track", Long.valueOf(id));
        return cursor.getPosition();
    }

    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
        String str = TAG;
        Log.debug(str, "onQueryComplete");
        if (cursor == null || cursor.isClosed() || cursor.getCount() == 0) {
            Log.warning(str, "onQueryComplete: null cursor or cursor closed");
            AmazonApplication.getContext().sendBroadcast(new Intent("com.amazon.mp3.library.activity.nowplaying.clear_and_finish"));
            return;
        }
        if (token == 1) {
            LoadCookie loadCookie = (LoadCookie) cookie;
            if (loadCookie.getRequestId() != this.mLoadRequestId.get()) {
                Log.warning(str, "onQueryComplete: Load Tracks -- stale request");
                return;
            }
            LoadCollectionTask loadCollectionTask = new LoadCollectionTask(loadCookie, cursor);
            this.mLoadCollectionTask = loadCollectionTask;
            loadCollectionTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        if (token == 2) {
            Log.debug(str, "onQueryComplete: Refresh Tracks");
            LoadCookie loadCookie2 = (LoadCookie) cookie;
            if (loadCookie2.getRequestId() != this.mLoadRequestId.get()) {
                Log.warning(str, "onQueryComplete: Refresh Tracks -- stale request");
                return;
            }
            RefreshTracksTask refreshTracksTask = new RefreshTracksTask(loadCookie2, cursor);
            this.mRefreshTracksTask = refreshTracksTask;
            refreshTracksTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }
}
