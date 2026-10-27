package com.amazon.mp3.library.provider.source.nowplaying;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.catalog.fragment.PlayQueueFragment;
import com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.nowplaying.PlayQueueException;
import com.amazon.mp3.library.provider.source.nowplaying.rx.RxPlayQueue;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.util.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes3.dex */
public class NowPlayingQueueUtil {
    private static final String TAG = "NowPlayingQueueUtil";
    private Context mContext;
    public ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
    private RxPlayQueue mRxPlayQueue;

    private static class NowPlayingQueueUtilHolder {
        static NowPlayingQueueUtil sInstance = new NowPlayingQueueUtil();

        private NowPlayingQueueUtilHolder() {
        }
    }

    public static NowPlayingQueueUtil getInstance() {
        return NowPlayingQueueUtilHolder.sInstance;
    }

    public void initRxPlayQueue(Context context, PlayQueue playQueue) {
        this.mContext = context.getApplicationContext();
        this.mRxPlayQueue = new RxPlayQueue(playQueue);
    }

    public RxPlayQueue getRxPlayQueue() {
        return this.mRxPlayQueue;
    }

    private void addToPlayQueueNext(final Uri uri, final String selection, final String[] selectionArgs, final boolean showToast, final boolean refreshCollection) {
        PlayQueueSequencer.getInstance().addToPlayQueue(uri, selection, selectionArgs, true, showToast);
    }

    private void addToPlayQueueTail(final Uri uri, final String selection, final String[] selectionArgs, final boolean showToast, final boolean refreshCollection) {
        PlayQueueSequencer.getInstance().addToPlayQueue(uri, selection, selectionArgs, false, showToast);
    }

    public void addToPlayQueue(final Uri uri, boolean playNext) {
        addToPlayQueue(uri, null, null, playNext, true, true);
    }

    public void addToPlayQueue(final Uri uri, final String selection, final String[] selectionArgs, final boolean playNext) {
        addToPlayQueue(uri, selection, selectionArgs, playNext, true, true);
    }

    public void addToPlayQueue(final Uri uri, final String selection, final String[] selectionArgs, final boolean playNext, final boolean showToast, final boolean refreshCollection) {
        if (CloudQueueSequencer.isCloudQueuePlaying()) {
            CloudQueuePlaybackUtil.INSTANCE.addTrackToCurrentQueue(uri, playNext, showToast);
        } else {
            if (!isInitialized()) {
                throw new IllegalStateException("Cannot add to play queue, forgot to call init?");
            }
            if (playNext) {
                addToPlayQueueNext(uri, selection, selectionArgs, showToast, refreshCollection);
            } else {
                addToPlayQueueTail(uri, selection, selectionArgs, showToast, refreshCollection);
            }
        }
    }

    private boolean isInitialized() {
        return (this.mRxPlayQueue == null || this.mContext == null) ? false : true;
    }

    public void handleException(Throwable throwable) {
        String message = throwable.getMessage();
        if (throwable instanceof PlayQueueException) {
            PlayQueueException playQueueException = (PlayQueueException) throwable;
            if (playQueueException.getReason() == PlayQueueException.Reason.PLAYQUEUE_FULL) {
                message = this.mContext.getResources().getString(R.string.dmusic_now_playing_queue_toast_error_set_full);
            } else if (playQueueException.getReason() == PlayQueueException.Reason.DB_NULL || playQueueException.getReason() == PlayQueueException.Reason.TRACKS_NOT_AVAILABLE) {
                message = this.mContext.getResources().getString(R.string.dmusic_now_playing_queue_toast_error_set);
            }
        }
        Log.error(TAG, message);
    }

    public static Intent getNowPlayingQueueIntent(Context context) {
        if (!CastingUtil.isPlayQueueSupported()) {
            Log.error(TAG, "Play Queue functionalities are disabled while casting.");
            return null;
        }
        return PlayQueueFragment.getStartIntent(context);
    }

    public Cursor getCursorFromUri(Uri uri, String selection, String[] selectionArgs) {
        return getCursorFromUri(uri, selection, selectionArgs, null);
    }

    public Cursor getCursorFromUri(Uri uri, String selection, String[] selectionArgs, String sortOrder) {
        String[] defaultProjection = getDefaultProjection();
        ContentResolver contentResolver = this.mContext.getContentResolver();
        if (sortOrder == null) {
            sortOrder = getDefaultSortOrder(uri);
        }
        return contentResolver.query(uri, defaultProjection, selection, selectionArgs, sortOrder);
    }

    public String getDefaultSortOrder(final Uri uri) {
        int iMatch = DefaultUriMatcher.match(uri);
        if (iMatch == 1) {
            return CirrusMediaSource.getTrackSortOrder();
        }
        if (iMatch == 5 || iMatch == 9 || iMatch == 26) {
            return CirrusMediaSource.getAlbumTrackSortOrder();
        }
        if (iMatch == 28 || iMatch == 33) {
            return "order_num";
        }
        return null;
    }

    private String[] getDefaultProjection() {
        return BaseTrackProvider.DEFAULT_TRACK_PROJECTION;
    }

    public boolean isUriSupported(Uri uri) {
        return (DefaultUriMatcher.match(uri) == 32 || DefaultUriMatcher.match(uri) == 35 || DefaultUriMatcher.match(uri) == 37) ? false : true;
    }
}
