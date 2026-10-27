package com.amazon.mp3.recentlyplayed;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import com.amazon.mp3.util.CountDownTimer;
import com.amazon.mp3.util.Log;
import com.amazon.music.curate.skyfire.Curate;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public class RecentsCountDownTimer extends CountDownTimer {
    private static long COUNTDOWN_INTERVAL = 0;
    private static long MILLIS_IN_FUTURE = 0;
    private static final String TAG = "RecentsCountDownTimer";
    private static RecentsCountDownTimer sInstance;
    private final Context mContext;
    private Uri previousContentUri;

    @Override // com.amazon.mp3.util.CountDownTimer
    public void onTick(final long millisUntilFinished) {
    }

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        MILLIS_IN_FUTURE = timeUnit.toMillis(30L);
        COUNTDOWN_INTERVAL = timeUnit.toMillis(30L);
    }

    private RecentsCountDownTimer(final Context context) {
        super(MILLIS_IN_FUTURE, COUNTDOWN_INTERVAL);
        this.mContext = context;
    }

    public static synchronized RecentsCountDownTimer getInstance(final Context context) {
        if (sInstance == null) {
            sInstance = new RecentsCountDownTimer(context);
        }
        return sInstance;
    }

    @Override // com.amazon.mp3.util.CountDownTimer
    public void onFinish() {
        Log.debug(TAG, "RecentsTimer has finished waiting for 30 seconds of playback");
        AsyncTask.execute(new Runnable() { // from class: com.amazon.mp3.recentlyplayed.RecentsCountDownTimer.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    RecentlyPlayedManager.getInstance(RecentsCountDownTimer.this.mContext).saveCurrentlyPlayingCollection(RecentsCountDownTimer.this.previousContentUri);
                    Curate.requestLibrarySync();
                } catch (IllegalArgumentException e) {
                    Log.error(RecentsCountDownTimer.TAG, "PreviousContentUri is invalid URI: " + RecentsCountDownTimer.this.previousContentUri + "\n " + e);
                }
            }
        });
    }
}
