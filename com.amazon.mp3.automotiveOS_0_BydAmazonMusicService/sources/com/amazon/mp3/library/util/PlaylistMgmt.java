package com.amazon.mp3.library.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.activity.SavePlaylistActivity;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.DialogUtil;
import com.amazon.mp3.util.Log;
import java.util.Hashtable;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistMgmt {
    private static final String[] DEFAULT_PROJECTION = {"luid", "prime_status", "ownership_status"};
    private static final String TAG = "PlaylistMgmt";
    private Activity mActivity;
    private AddToPlaylistQueue mAddQueue;
    private Uri mContentUri;
    private Context mContext;
    private OnPlaylistMgmt mOnPlaylistMgmt;
    private long mPlaylistId;
    private Uri mPlaylistUri;
    private ProgressDialog mProgressDialog;
    private String mProgressMessage;
    private String mSourceId;
    private boolean mMutated = false;
    private boolean mPaused = false;
    protected Handler mHandler = new Handler();
    private boolean mIsPlaylistChangedObserverRegister = false;
    private boolean mAddQueueConnected = false;
    private Hashtable<String, PlaylistUtil.PlaylistTrack> mCachePlaylist = new Hashtable<>();
    private long mAddAllTracksJobId = -1;
    private long mCachePlaylistJobId = -1;
    private ContentObserver mPlaylistChangedObserver = new ContentObserver(AmazonApplication.getBackgroundHandler()) { // from class: com.amazon.mp3.library.util.PlaylistMgmt.1
        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            PlaylistMgmt.this.asyncCachePlaylist();
            if (PlaylistMgmt.this.mOnPlaylistMgmt != null) {
                PlaylistMgmt.this.mOnPlaylistMgmt.onPlaylistChangedObserver();
            }
        }
    };
    private Runnable mShowProgressRunnable = new Runnable() { // from class: com.amazon.mp3.library.util.PlaylistMgmt.2
        @Override // java.lang.Runnable
        public void run() {
            if (PlaylistMgmt.this.isPaused() || PlaylistMgmt.this.mProgressDialog == null) {
                return;
            }
            PlaylistMgmt.this.mProgressDialog.show();
            PlaylistMgmt playlistMgmt = PlaylistMgmt.this;
            playlistMgmt.mHandler.postDelayed(playlistMgmt.mAllowProgressCancelRunnable, 10000L);
        }
    };
    private Runnable mAllowProgressCancelRunnable = new Runnable() { // from class: com.amazon.mp3.library.util.PlaylistMgmt.3
        @Override // java.lang.Runnable
        public void run() {
            if (PlaylistMgmt.this.mProgressDialog != null) {
                PlaylistMgmt.this.mProgressDialog.setCancelable(true);
            }
        }
    };

    public interface OnPlaylistMgmt {
        Cursor onAddAllTracksCursor();

        void onAddAllTracksCursorFinish();

        void onAddAllTracksJobFinish();

        long onAddJob(Job job);

        void onCachePlaylistJobFinish();

        void onPlaylistChangedObserver();

        void onSetDuration(String duration);

        boolean onShuffleExist();

        void onUpdateCachePlaylist(Hashtable<String, PlaylistUtil.PlaylistTrack> cachePlaylist);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSortOrder() {
        return null;
    }

    public Uri getPlaylistUri() {
        return this.mPlaylistUri;
    }

    public long getPlaylistId() {
        return this.mPlaylistId;
    }

    public PlaylistMgmt(Activity activity, Context context, Uri contentUri, OnPlaylistMgmt onPlaylistMgmt, Intent intent) {
        this.mActivity = activity;
        this.mContext = context;
        this.mContentUri = contentUri;
        this.mOnPlaylistMgmt = onPlaylistMgmt;
        if (intent != null) {
            Uri uri = (Uri) intent.getExtras().getParcelable("com.amazon.mp3.library.EXTRA_PLAYLIST_URI");
            this.mPlaylistUri = uri;
            if (uri != null) {
                this.mPlaylistId = PlaylistUtil.getPlaylistId(uri);
            }
        } else {
            this.mPlaylistUri = null;
        }
        Uri uri2 = this.mPlaylistUri;
        if (uri2 != null) {
            this.mSourceId = MediaProvider.getSource(uri2);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        restoreInstanceState(savedInstanceState);
    }

    public void onActivityCreated(Bundle instanceState) {
        connectToAddQueue();
        registerPlaylistChangedObserver();
        if (this.mAddAllTracksJobId != -1) {
            showProgressDialog(this.mProgressMessage, 0, false);
        }
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    public void onResume() {
        this.mPaused = false;
        if (this.mPlaylistUri == null) {
            return;
        }
        this.mMutated = PlaylistUtil.getMutatedFlag(getActivity());
        asyncCachePlaylist();
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    public void onPause() {
        this.mPaused = true;
        if (this.mPlaylistUri == null) {
            return;
        }
        if (this.mMutated) {
            PlaylistUtil.setMutatedFlag(getActivity());
        }
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public void onDestroy() {
        disconnectToAddQueue();
        unregisterPlaylistChangedObserver();
    }

    public void onSaveInstanceState(Bundle outState) {
        if (outState == null || this.mPlaylistUri == null) {
            return;
        }
        outState.putBoolean("com.amazon.mp3.library.EXTRA_PLAYLIST_MUTATED", this.mMutated);
        outState.putBoolean("com.amazon.mp3.AddPlaylistDetailToPlaylistFragment.EXTRA_SHOW_PROGRESS_DIALOG", this.mProgressDialog != null);
        outState.putString("com.amazon.mp3.AddPlaylistDetailToPlaylistFragment.EXTRA_PROGRESS_DIALOG_MESSAGE", this.mProgressMessage);
        outState.putLong("com.amazon.mp3.AddPlaylistDetailToPlaylistFragment.EXTRA_ADD_ALL_TRACKS_JOB_ID", this.mAddAllTracksJobId);
        outState.putLong("com.amazon.mp3.AddPlaylistDetailToPlaylistFragment.EXTRA_CACHE_PLAYLIST_JOB_ID", this.mCachePlaylistJobId);
    }

    public void restoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null || this.mPlaylistUri == null) {
            return;
        }
        this.mMutated = savedInstanceState.getBoolean("com.amazon.mp3.library.EXTRA_PLAYLIST_MUTATED", false);
        this.mProgressMessage = savedInstanceState.getString("com.amazon.mp3.AddPlaylistDetailToPlaylistFragment.EXTRA_PROGRESS_DIALOG_MESSAGE");
        this.mAddAllTracksJobId = savedInstanceState.getLong("com.amazon.mp3.AddPlaylistDetailToPlaylistFragment.EXTRA_ADD_ALL_TRACKS_JOB_ID");
        this.mCachePlaylistJobId = savedInstanceState.getLong("com.amazon.mp3.AddPlaylistDetailToPlaylistFragment.EXTRA_CACHE_PLAYLIST_JOB_ID");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.mPlaylistUri != null && requestCode == SavePlaylistActivity.REQUEST_CODE && resultCode == -1) {
            PlaylistUtil.clearMutatedFlag(getContext());
            finish();
        }
    }

    public void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        OnPlaylistMgmt onPlaylistMgmt;
        if (this.mPlaylistUri == null || (onPlaylistMgmt = this.mOnPlaylistMgmt) == null) {
            return;
        }
        if (this.mCachePlaylistJobId == jobId) {
            this.mCachePlaylistJobId = -1L;
            onPlaylistMgmt.onCachePlaylistJobFinish();
        } else if (this.mAddAllTracksJobId == jobId) {
            this.mAddAllTracksJobId = -1L;
            hideProgressDialog();
            retrievePlaylist();
            this.mOnPlaylistMgmt.onAddAllTracksJobFinish();
        }
    }

    public void processAddButtonClick(Uri trackUri, boolean allowDuplicates) {
        if (this.mPlaylistUri == null) {
            return;
        }
        addTrack(trackUri, allowDuplicates);
    }

    public void processDeleteButtonClick(long playlistTrackId, long udo) {
        if (this.mPlaylistUri == null) {
            return;
        }
        deleteTrack(playlistTrackId, udo);
    }

    public void processAddAllButton() {
        processAddAllButton(null);
    }

    public void processAddAllButton(Uri contentUri) {
        if (this.mPlaylistUri == null) {
            return;
        }
        addAllTracks(contentUri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri getTrackContentUri(String luid) {
        return MediaProvider.Tracks.getContentUri(this.mSourceId, luid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean addTrack(Uri trackUri, boolean allowDuplicates) {
        Uri uri = this.mPlaylistUri;
        if (uri != null) {
            if (!PlaylistUtil.hasPlaylistReachLimit(this.mContext, uri)) {
                if (allowDuplicates || !PlaylistUtil.playlistContainsTrack(this.mContext, this.mPlaylistUri, trackUri)) {
                    this.mAddQueue.add(trackUri);
                    this.mMutated = true;
                    PlaylistUtil.setMutatedFlag(getContext());
                    return true;
                }
            } else {
                this.mActivity.startActivity(DialogActivity.getStartIntent(this.mContext, R.string.dmusic_playlist_edit_max_size_title, R.string.dmusic_playlist_edit_max_size_desc));
            }
        }
        return false;
    }

    private boolean deleteTrack(long playlistTrackId, long udo) {
        Context context = this.mContext;
        Uri uri = this.mPlaylistUri;
        if (!PlaylistUtil.deletePlaylistTrack(context, uri, uri, this.mPlaylistId, playlistTrackId, udo)) {
            return false;
        }
        this.mContext.getContentResolver().notifyChange(MediaProvider.UdoPlaylists.getContentUri(this.mSourceId, this.mPlaylistId), null);
        this.mMutated = true;
        PlaylistUtil.setMutatedFlag(getContext());
        return true;
    }

    private long addJob(Job job) {
        OnPlaylistMgmt onPlaylistMgmt = this.mOnPlaylistMgmt;
        if (onPlaylistMgmt == null) {
            return -1L;
        }
        return onPlaylistMgmt.onAddJob(job);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void asyncCachePlaylist() {
        if (this.mPlaylistUri == null || this.mCachePlaylistJobId != -1) {
            return;
        }
        this.mCachePlaylistJobId = addJob(new CachePlaylistJob());
    }

    private void addAllTracks(Uri contentUri) {
        if (contentUri == null) {
            contentUri = this.mContentUri;
        }
        if (this.mPlaylistUri == null || this.mAddAllTracksJobId != -1) {
            return;
        }
        showProgressDialog(R.string.dmusic_playlist_add_to_working_message, false);
        this.mAddAllTracksJobId = addJob(new AddAllTracksJob(contentUri));
    }

    public class AddAllTracksJob extends Job {
        private final Uri mJobContentUri;

        private AddAllTracksJob(Uri contentUri) {
            this.mJobContentUri = contentUri;
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            boolean z;
            Uri trackContentUri;
            Cursor cursorOnAddAllTracksCursor = PlaylistMgmt.this.mOnPlaylistMgmt != null ? PlaylistMgmt.this.mOnPlaylistMgmt.onAddAllTracksCursor() : null;
            if (cursorOnAddAllTracksCursor == null) {
                cursorOnAddAllTracksCursor = PlaylistMgmt.createCursor(getContext(), this.mJobContentUri, PlaylistMgmt.this.getProjection(), PlaylistMgmt.this.getSortOrder());
                z = true;
            } else {
                z = false;
            }
            if (cursorOnAddAllTracksCursor != null && cursorOnAddAllTracksCursor.moveToFirst()) {
                PlaylistMgmt.this.unregisterPlaylistChangedObserver();
                try {
                    int columnIndexOrThrow = cursorOnAddAllTracksCursor.getColumnIndexOrThrow("luid");
                    int columnIndexOrThrow2 = cursorOnAddAllTracksCursor.getColumnIndexOrThrow("asin");
                    if (PlaylistMgmt.this.mOnPlaylistMgmt != null && PlaylistMgmt.this.mOnPlaylistMgmt.onShuffleExist()) {
                        cursorOnAddAllTracksCursor.moveToNext();
                    }
                    if (!cursorOnAddAllTracksCursor.isAfterLast()) {
                        do {
                            String string = cursorOnAddAllTracksCursor.getString(columnIndexOrThrow);
                            String strFindObjectIdByTrackLocalLuid = TrackUtil.findObjectIdByTrackLocalLuid(getContext(), string);
                            if (!PlaylistMgmt.this.mCachePlaylist.containsKey(string) && ((!StringUtils.isNotBlank(strFindObjectIdByTrackLocalLuid) || !PlaylistMgmt.this.mCachePlaylist.containsKey(strFindObjectIdByTrackLocalLuid)) && !StringUtils.isBlank(cursorOnAddAllTracksCursor.getString(columnIndexOrThrow2)) && (trackContentUri = PlaylistMgmt.this.getTrackContentUri(string)) != null && PlaylistMgmt.this.addTrack(trackContentUri, false) && PlaylistUtil.hasPlaylistReachLimit(PlaylistMgmt.this.mContext, PlaylistMgmt.this.mPlaylistUri))) {
                                break;
                            }
                        } while (cursorOnAddAllTracksCursor.moveToNext());
                    }
                    if (z) {
                        DbUtil.closeCursor(cursorOnAddAllTracksCursor);
                    } else if (PlaylistMgmt.this.mOnPlaylistMgmt != null) {
                        PlaylistMgmt.this.mOnPlaylistMgmt.onAddAllTracksCursorFinish();
                    }
                    synchronized (PlaylistMgmt.this.mAddQueue) {
                        while (PlaylistMgmt.this.mAddQueue.getPendingInserts() > 0) {
                            try {
                                PlaylistMgmt.this.mAddQueue.wait();
                            } catch (InterruptedException unused) {
                                Log.warning(PlaylistMgmt.TAG, "InterruptedException");
                            }
                        }
                    }
                    PlaylistMgmt.this.registerPlaylistChangedObserver();
                } catch (Throwable th) {
                    if (z) {
                        DbUtil.closeCursor(cursorOnAddAllTracksCursor);
                    } else if (PlaylistMgmt.this.mOnPlaylistMgmt != null) {
                        PlaylistMgmt.this.mOnPlaylistMgmt.onAddAllTracksCursorFinish();
                    }
                    synchronized (PlaylistMgmt.this.mAddQueue) {
                        while (PlaylistMgmt.this.mAddQueue.getPendingInserts() > 0) {
                            try {
                                PlaylistMgmt.this.mAddQueue.wait();
                            } catch (InterruptedException unused2) {
                                Log.warning(PlaylistMgmt.TAG, "InterruptedException");
                            }
                        }
                        PlaylistMgmt.this.registerPlaylistChangedObserver();
                        throw th;
                    }
                }
            }
            return 0;
        }
    }

    public class CachePlaylistJob extends Job {
        public CachePlaylistJob() {
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            PlaylistMgmt.this.retrievePlaylist();
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void retrievePlaylist() {
        Activity activity = getActivity();
        Uri uri = this.mPlaylistUri;
        PlaylistUtil.PlaylistTrackInfo playlistTrackInfoCreatePlaylistInfo = PlaylistUtil.createPlaylistInfo(activity, uri, CirrusMediaSource.match(uri));
        this.mCachePlaylist = playlistTrackInfoCreatePlaylistInfo.mCachePlaylist;
        OnPlaylistMgmt onPlaylistMgmt = this.mOnPlaylistMgmt;
        if (onPlaylistMgmt != null) {
            onPlaylistMgmt.onSetDuration(PlaylistUtil.calculateSongsDuration(getActivity(), playlistTrackInfoCreatePlaylistInfo.mTrackCount, playlistTrackInfoCreatePlaylistInfo.mCalcDuration));
            this.mOnPlaylistMgmt.onUpdateCachePlaylist(this.mCachePlaylist);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerPlaylistChangedObserver() {
        if (this.mPlaylistUri == null || this.mIsPlaylistChangedObserverRegister) {
            return;
        }
        getContext().getContentResolver().registerContentObserver(MediaProvider.UdoPlaylists.getContentUri(this.mSourceId, PlaylistUtil.getPlaylistId(this.mPlaylistUri)), true, this.mPlaylistChangedObserver);
        this.mIsPlaylistChangedObserverRegister = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterPlaylistChangedObserver() {
        if (this.mPlaylistUri != null && this.mIsPlaylistChangedObserverRegister) {
            getContext().getContentResolver().unregisterContentObserver(this.mPlaylistChangedObserver);
            this.mIsPlaylistChangedObserverRegister = false;
        }
    }

    private void finish() {
        PlaylistUtil.clearMutatedFlag(this.mActivity);
        this.mActivity.finish();
    }

    protected void showProgressDialog(int messageId, boolean delayCancelable) {
        showProgressDialog(messageId, 0, delayCancelable);
    }

    protected void showProgressDialog(int messageId, int delay, boolean delayCancelable) {
        showProgressDialog(messageId != 0 ? this.mContext.getString(messageId) : "", delay, delayCancelable);
    }

    protected void showProgressDialog(String message, int delay, boolean delayCancelable) {
        if (this.mActivity == null) {
            return;
        }
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        if (message != null) {
            this.mProgressMessage = message;
        } else {
            this.mProgressMessage = "";
        }
        ProgressDialog progressDialog2 = new ProgressDialog(this.mActivity);
        this.mProgressDialog = progressDialog2;
        progressDialog2.setMessage(this.mProgressMessage);
        this.mProgressDialog.setCancelable(false);
        DialogUtil.installDefaultKeyListener(this.mProgressDialog);
        if (delay > 0) {
            this.mHandler.removeCallbacks(this.mShowProgressRunnable);
            return;
        }
        this.mProgressDialog.show();
        if (delayCancelable) {
            this.mHandler.postDelayed(this.mAllowProgressCancelRunnable, 10000L);
        }
    }

    protected void hideProgressDialog() {
        this.mHandler.removeCallbacks(this.mShowProgressRunnable);
        this.mHandler.removeCallbacks(this.mAllowProgressCancelRunnable);
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
            this.mProgressDialog = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPaused() {
        return this.mPaused;
    }

    private Activity getActivity() {
        return this.mActivity;
    }

    private Context getContext() {
        return this.mContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String[] getProjection() {
        return TrackListAdapter.getDefaultProjection();
    }

    private void connectToAddQueue() {
        Uri uri = this.mPlaylistUri;
        if (uri == null || this.mAddQueueConnected) {
            return;
        }
        this.mAddQueue = AddToPlaylistQueue.getInstance(this.mContext, uri);
        this.mAddQueueConnected = true;
    }

    private void disconnectToAddQueue() {
        if (this.mPlaylistUri == null || !this.mAddQueueConnected) {
            return;
        }
        this.mAddQueue.disconnect();
        this.mAddQueueConnected = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Cursor createCursor(Context context, Uri contentUri, String[] projection, String sortOrder) {
        return context.getContentResolver().query(contentUri, projection, "luid is not null AND (prime_status < 400 OR ownership_status < 200)", null, sortOrder);
    }
}
