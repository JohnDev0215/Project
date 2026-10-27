package com.amazon.mp3.library.util;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playlist.AddToPlaylistEventBuilder;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.MetricsHolder;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes3.dex */
public final class AddToPlaylistQueue {
    private static final String TAG = "AddToPlaylistQueue";
    private static HashMap<Uri, AddToPlaylistQueue> sUriToInstanceMap = new HashMap<>();
    private Context mContext;
    private int mPendingInserts;
    private long mPlaylistId;
    private Uri mPlaylistUri;
    private int mRefCount;
    private String mSourceId;
    private Handler mUiThreadHandler = new Handler();
    private int mTotalCount = 0;
    private Runnable mCheckedQuitRunnable = new Runnable() { // from class: com.amazon.mp3.library.util.AddToPlaylistQueue.1
        @Override // java.lang.Runnable
        public void run() {
            synchronized (AddToPlaylistQueue.this) {
                if (AddToPlaylistQueue.this.mRefCount <= 0 && AddToPlaylistQueue.this.mThread != null) {
                    AddToPlaylistQueue.this.mThread.getLooper().quit();
                    AddToPlaylistQueue.sUriToInstanceMap.remove(AddToPlaylistQueue.this.mPlaylistUri);
                }
            }
        }
    };
    private InsertionThread mThread = new InsertionThread();

    static /* synthetic */ int access$104(AddToPlaylistQueue addToPlaylistQueue) {
        int i = addToPlaylistQueue.mPendingInserts + 1;
        addToPlaylistQueue.mPendingInserts = i;
        return i;
    }

    static /* synthetic */ int access$106(AddToPlaylistQueue addToPlaylistQueue) {
        int i = addToPlaylistQueue.mPendingInserts - 1;
        addToPlaylistQueue.mPendingInserts = i;
        return i;
    }

    static /* synthetic */ int access$208(AddToPlaylistQueue addToPlaylistQueue) {
        int i = addToPlaylistQueue.mTotalCount;
        addToPlaylistQueue.mTotalCount = i + 1;
        return i;
    }

    private AddToPlaylistQueue(Context context, Uri playlistUri) {
        this.mContext = context;
        this.mPlaylistUri = playlistUri;
        this.mSourceId = MediaProvider.getSource(playlistUri);
        this.mPlaylistId = PlaylistUtil.getPlaylistId(playlistUri);
    }

    public static AddToPlaylistQueue getInstance(Context context, Uri playlistUri) {
        AddToPlaylistQueue addToPlaylistQueue = sUriToInstanceMap.get(playlistUri);
        if (addToPlaylistQueue == null) {
            addToPlaylistQueue = new AddToPlaylistQueue(context, playlistUri);
            sUriToInstanceMap.put(playlistUri, addToPlaylistQueue);
            addToPlaylistQueue.mThread.start();
        } else if (addToPlaylistQueue.mThread.mHandler != null) {
            addToPlaylistQueue.mThread.mHandler.removeMessages(1);
        }
        synchronized (addToPlaylistQueue) {
            addToPlaylistQueue.mRefCount++;
        }
        return addToPlaylistQueue;
    }

    public synchronized void disconnect() {
        int i = this.mRefCount - 1;
        this.mRefCount = i;
        if (i == 0) {
            this.mThread.postQuit();
        }
    }

    public void add(Uri contentUri) {
        add(contentUri, null);
    }

    public void add(Uri contentUri, String sortOrder) {
        String source = MediaProvider.getSource(contentUri);
        String type = DefaultUriMatcher.getType(contentUri);
        if (!source.equals(this.mSourceId)) {
            String str = TAG;
            Log.error(str, "add failed - source mismatch sourceId = " + source + " mSourceId = " + this.mSourceId);
            Log.warning(str, TextUtils.join("\n", Thread.currentThread().getStackTrace()));
            return;
        }
        if (!type.equals("vnd.android.cursor.dir/vnd.amazonmp3.track") && !type.equals("vnd.android.cursor.item/vnd.amazonmp3.track") && !type.equals("vnd.android.cursor.dir/vnd.amazonmp3.udoplaylisttrack") && !type.equals("vnd.android.cursor.item/vnd.amazonmp3.udoplaylisttrack")) {
            String str2 = TAG;
            Log.error(str2, "add failed - specified URI is not a Tracks type = " + type);
            Log.warning(str2, TextUtils.join("\n", Thread.currentThread().getStackTrace()));
            return;
        }
        this.mThread.postInsert(contentUri, sortOrder);
    }

    public int getPendingInserts() {
        return this.mPendingInserts;
    }

    private class InsertionThread extends HandlerThread {
        private Handler mHandler;
        private CountDownLatch mWaitForPrepared;

        public InsertionThread() {
            super("AddToPlaylistQueue.InsertionThread");
            this.mWaitForPrepared = new CountDownLatch(1);
            this.mHandler = null;
        }

        public void postQuit() {
            waitForLooper();
            Handler handler = this.mHandler;
            handler.sendMessage(handler.obtainMessage(1));
        }

        public void postInsert(Uri uri, String sortOrder) {
            waitForLooper();
            AddToPlaylistQueue.access$104(AddToPlaylistQueue.this);
            Message messageObtainMessage = this.mHandler.obtainMessage(0);
            messageObtainMessage.obj = new Object[]{uri, sortOrder};
            this.mHandler.sendMessage(messageObtainMessage);
        }

        protected void waitForLooper() {
            try {
                this.mWaitForPrepared.await();
            } catch (InterruptedException unused) {
                Log.verbose("AddToPlaylistQueue.InsertionThread", "interrupted");
            }
        }

        @Override // android.os.HandlerThread
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            this.mHandler = new Handler(getLooper()) { // from class: com.amazon.mp3.library.util.AddToPlaylistQueue.InsertionThread.1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    int i = msg.what;
                    if (i == 0) {
                        AddToPlaylistQueue.access$208(AddToPlaylistQueue.this);
                        try {
                            Object[] objArr = (Object[]) msg.obj;
                            PlaylistUtil.insertUdoPlaylistTracks(AddToPlaylistQueue.this.mContext, AddToPlaylistQueue.this.mSourceId, AddToPlaylistQueue.this.mPlaylistId, (Uri) objArr[0], (String) objArr[1]);
                        } finally {
                            AddToPlaylistQueue.this.mUiThreadHandler.post(new InsertionFinishedRunnable());
                        }
                    } else if (i == 1) {
                        if (AddToPlaylistQueue.this.mTotalCount > 0) {
                            AddToPlaylistQueue addToPlaylistQueue = AddToPlaylistQueue.this;
                            addToPlaylistQueue.submitPlaylistAddFlexEvent(addToPlaylistQueue.mTotalCount);
                            AddToPlaylistQueue.this.mTotalCount = 0;
                        }
                        AddToPlaylistQueue.this.mUiThreadHandler.post(AddToPlaylistQueue.this.mCheckedQuitRunnable);
                        PlaylistUtil.refreshPlaylistArt(AddToPlaylistQueue.this.mPlaylistUri);
                    }
                    super.handleMessage(msg);
                }
            };
            this.mWaitForPrepared.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void submitPlaylistAddFlexEvent(int count) {
        String playlistLuid = MediaProvider.Playlists.getPlaylistLuid(this.mPlaylistUri);
        if (playlistLuid == null) {
            Log.warning(TAG, "Playlist ID is null. Not reporting add flex event");
        } else {
            MetricsHolder.getManager().handleEvent(new AddToPlaylistEventBuilder().withPlaylistId(playlistLuid).withNumTracks(count).build());
        }
    }

    private class InsertionFinishedRunnable implements Runnable {
        private InsertionFinishedRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (AddToPlaylistQueue.this) {
                AddToPlaylistQueue.access$106(AddToPlaylistQueue.this);
                if (AddToPlaylistQueue.this.getPendingInserts() == 0) {
                    AddToPlaylistQueue.this.notifyAll();
                }
            }
        }
    }
}
