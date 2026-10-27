package org.chromium.content.browser;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.metrics.RecordHistogram;
import org.chromium.content.R;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
class MediaThrottler implements MediaPlayer.OnErrorListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int RELEASE_WATCH_DOG_PLAYER_DELAY_IN_MILLIS = 5000;
    private static final int SERVER_CRASH_COUNT_THRESHOLD_FOR_THROTTLING = 4;
    private static final long SERVER_CRASH_INTERVAL_THRESHOLD_IN_MILLIS = 60000;
    private static final String TAG = "cr_MediaThrottler";
    private static final long UNKNOWN_LAST_SERVER_CRASH_TIME = -1;
    private final Context mContext;
    private MediaPlayer mPlayer;
    private int mRequestCount;
    private int mServerCrashCount;
    private long mLastCrashTime = -1;
    private final Object mLock = new Object();
    private final Runnable mDelayedReleaseRunnable = new Runnable() { // from class: org.chromium.content.browser.MediaThrottler.1
        @Override // java.lang.Runnable
        public void run() {
            new ReleaseWatchDogTask().execute(new Void[0]);
        }
    };
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private class ReleaseWatchDogTask extends AsyncTask<Void, Void, Void> {
        private ReleaseWatchDogTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            synchronized (MediaThrottler.this.mLock) {
                if (MediaThrottler.this.mRequestCount == 0 && MediaThrottler.this.mPlayer != null) {
                    MediaThrottler.this.mPlayer.release();
                    MediaThrottler.this.mPlayer = null;
                }
            }
            return null;
        }
    }

    private static MediaThrottler create(Context context) {
        return new MediaThrottler(context);
    }

    private MediaThrottler(Context context) {
        this.mContext = context;
    }

    private class StartWatchDogTask extends AsyncTask<Void, Void, Void> {
        private StartWatchDogTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            synchronized (MediaThrottler.this.mLock) {
                if (MediaThrottler.this.mPlayer != null || MediaThrottler.this.mRequestCount == 0) {
                    return null;
                }
                try {
                    MediaThrottler mediaThrottler = MediaThrottler.this;
                    mediaThrottler.mPlayer = MediaPlayer.create(mediaThrottler.mContext, R.raw.empty);
                } catch (IllegalStateException e) {
                    Log.e(MediaThrottler.TAG, "Exception happens while creating the watch dog player.", e);
                } catch (RuntimeException e2) {
                    Log.e(MediaThrottler.TAG, "Exception happens while creating the watch dog player.", e2);
                }
                if (MediaThrottler.this.mPlayer != null) {
                    MediaThrottler.this.mPlayer.setOnErrorListener(MediaThrottler.this);
                } else {
                    Log.e(MediaThrottler.TAG, "Unable to create watch dog player, treat it as server crash.", new Object[0]);
                    MediaThrottler.this.onMediaServerCrash();
                }
                return null;
            }
        }
    }

    private boolean requestDecoderResources() {
        synchronized (this.mLock) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long j = this.mLastCrashTime;
            if (j != -1 && jElapsedRealtime - j < 60000 && this.mServerCrashCount >= 4) {
                Log.e(TAG, "Request to decode media data denied due to throttling.", new Object[0]);
                return false;
            }
            int i = this.mRequestCount + 1;
            this.mRequestCount = i;
            if (i == 1 || this.mPlayer == null) {
                this.mHandler.removeCallbacks(this.mDelayedReleaseRunnable);
                this.mHandler.post(new Runnable() { // from class: org.chromium.content.browser.MediaThrottler.2
                    @Override // java.lang.Runnable
                    public void run() {
                        new StartWatchDogTask().execute(new Void[0]);
                    }
                });
            }
            return true;
        }
    }

    private void onDecodeRequestFinished() {
        synchronized (this.mLock) {
            int i = this.mRequestCount - 1;
            this.mRequestCount = i;
            if (i == 0) {
                prepareToStopWatchDog();
            }
        }
    }

    private void prepareToStopWatchDog() {
        this.mHandler.postDelayed(this.mDelayedReleaseRunnable, 5000L);
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (i != 100) {
            return true;
        }
        synchronized (this.mLock) {
            onMediaServerCrash();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMediaServerCrash() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.mLastCrashTime;
        if (j != -1 && jElapsedRealtime - j < 60000) {
            this.mServerCrashCount++;
        } else {
            recordNumMediaServerCrashes();
            this.mServerCrashCount = 1;
        }
        this.mLastCrashTime = jElapsedRealtime;
    }

    private void reset() {
        synchronized (this.mLock) {
            recordNumMediaServerCrashes();
            this.mServerCrashCount = 0;
            this.mLastCrashTime = -1L;
        }
    }

    private void recordNumMediaServerCrashes() {
        int i = this.mServerCrashCount;
        if (i > 0) {
            RecordHistogram.recordCountHistogram("Media.Android.NumMediaServerCrashes", i);
        }
    }
}
