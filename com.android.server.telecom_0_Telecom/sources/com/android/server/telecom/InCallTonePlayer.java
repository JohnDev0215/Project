package com.android.server.telecom;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.os.Handler;
import android.os.Looper;
import android.telecom.Log;
import android.telecom.Logging.Runnable;
import android.telecom.Logging.Session;
import com.android.internal.annotations.VisibleForTesting;
import com.android.server.telecom.TelecomSystem;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class InCallTonePlayer extends Thread {
    private static int sTonesPlaying;
    private final AudioManagerAdapter mAudioManagerAdapter;
    private final CallAudioManager mCallAudioManager;
    private final CallAudioRoutePeripheralAdapter mCallAudioRoutePeripheralAdapter;
    private final TelecomSystem.SyncRoot mLock;
    private final Handler mMainThreadHandler;
    private final MediaPlayerFactory mMediaPlayerFactory;
    private Session mSession;
    private final Object mSessionLock;
    private int mState;
    private final ToneGeneratorFactory mToneGenerator;
    private final int mToneId;
    private MediaPlayerAdapter mToneMediaPlayer;

    public interface AudioManagerAdapter {
        boolean isVolumeOverZero();
    }

    public interface MediaPlayerAdapter {
        int getDuration();

        void release();

        void setLooping(boolean z);

        void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener);

        void start();
    }

    public interface MediaPlayerFactory {
        MediaPlayerAdapter get(int i, AudioAttributes audioAttributes);
    }

    public interface ToneGeneratorFactory {
        ToneGenerator get(int i, int i2);
    }

    static /* synthetic */ int access$306() {
        int i = sTonesPlaying - 1;
        sTonesPlaying = i;
        return i;
    }

    public static class Factory {
        private final AudioManagerAdapter mAudioManagerAdapter;
        private CallAudioManager mCallAudioManager;
        private final CallAudioRoutePeripheralAdapter mCallAudioRoutePeripheralAdapter;
        private final TelecomSystem.SyncRoot mLock;
        private final MediaPlayerFactory mMediaPlayerFactory;
        private final ToneGeneratorFactory mToneGeneratorFactory;

        public Factory(CallAudioRoutePeripheralAdapter callAudioRoutePeripheralAdapter, TelecomSystem.SyncRoot syncRoot, ToneGeneratorFactory toneGeneratorFactory, MediaPlayerFactory mediaPlayerFactory, AudioManagerAdapter audioManagerAdapter) {
            this.mCallAudioRoutePeripheralAdapter = callAudioRoutePeripheralAdapter;
            this.mLock = syncRoot;
            this.mToneGeneratorFactory = toneGeneratorFactory;
            this.mMediaPlayerFactory = mediaPlayerFactory;
            this.mAudioManagerAdapter = audioManagerAdapter;
        }

        public void setCallAudioManager(CallAudioManager callAudioManager) {
            this.mCallAudioManager = callAudioManager;
        }

        public InCallTonePlayer createPlayer(int i) {
            return new InCallTonePlayer(i, this.mCallAudioManager, this.mCallAudioRoutePeripheralAdapter, this.mLock, this.mToneGeneratorFactory, this.mMediaPlayerFactory, this.mAudioManagerAdapter);
        }
    }

    public static class MediaPlayerAdapterImpl implements MediaPlayerAdapter {
        private MediaPlayer mMediaPlayer;

        public MediaPlayerAdapterImpl(MediaPlayer mediaPlayer) {
            this.mMediaPlayer = mediaPlayer;
        }

        @Override // com.android.server.telecom.InCallTonePlayer.MediaPlayerAdapter
        public void setLooping(boolean z) {
            MediaPlayer mediaPlayer = this.mMediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.setLooping(z);
            }
        }

        @Override // com.android.server.telecom.InCallTonePlayer.MediaPlayerAdapter
        public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
            MediaPlayer mediaPlayer = this.mMediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        }

        @Override // com.android.server.telecom.InCallTonePlayer.MediaPlayerAdapter
        public void start() {
            MediaPlayer mediaPlayer = this.mMediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.start();
            }
        }

        @Override // com.android.server.telecom.InCallTonePlayer.MediaPlayerAdapter
        public void release() {
            MediaPlayer mediaPlayer = this.mMediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
        }

        @Override // com.android.server.telecom.InCallTonePlayer.MediaPlayerAdapter
        public int getDuration() {
            MediaPlayer mediaPlayer = this.mMediaPlayer;
            if (mediaPlayer != null) {
                return mediaPlayer.getDuration();
            }
            return 0;
        }
    }

    private InCallTonePlayer(int i, CallAudioManager callAudioManager, CallAudioRoutePeripheralAdapter callAudioRoutePeripheralAdapter, TelecomSystem.SyncRoot syncRoot, ToneGeneratorFactory toneGeneratorFactory, MediaPlayerFactory mediaPlayerFactory, AudioManagerAdapter audioManagerAdapter) {
        this.mMainThreadHandler = new Handler(Looper.getMainLooper());
        this.mToneMediaPlayer = null;
        this.mSessionLock = new Object();
        this.mState = 0;
        this.mToneId = i;
        this.mCallAudioManager = callAudioManager;
        this.mCallAudioRoutePeripheralAdapter = callAudioRoutePeripheralAdapter;
        this.mLock = syncRoot;
        this.mToneGenerator = toneGeneratorFactory;
        this.mMediaPlayerFactory = mediaPlayerFactory;
        this.mAudioManagerAdapter = audioManagerAdapter;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007e A[Catch: all -> 0x00a8, TryCatch #1 {all -> 0x00a8, blocks: (B:2:0x0000, B:3:0x0002, B:8:0x0012, B:9:0x0035, B:10:0x0038, B:37:0x008e, B:38:0x00a4, B:12:0x003e, B:13:0x0045, B:21:0x0060, B:22:0x0067, B:28:0x0073, B:32:0x007e, B:34:0x0084, B:41:0x00a7, B:4:0x0003, B:6:0x0007, B:7:0x0011), top: B:47:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0082  */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.InCallTonePlayer.run():void");
    }

    private void playToneGeneratorTone(int i, int i2, int i3, int i4) {
        ToneGenerator toneGenerator = null;
        try {
            try {
                ToneGenerator toneGenerator2 = this.mToneGenerator.get(i, i2);
                Log.i(this, "playToneGeneratorTone: toneType=%d", new Object[]{Integer.valueOf(i3)});
                synchronized (this) {
                    if (this.mState != 2) {
                        this.mState = 1;
                        toneGenerator2.startTone(i3);
                        try {
                            int i5 = i4 + 20;
                            Log.v(this, "Starting tone %d...waiting for %d ms.", new Object[]{Integer.valueOf(this.mToneId), Integer.valueOf(i5)});
                            wait(i5);
                        } catch (InterruptedException e) {
                            Log.w(this, "wait interrupted", new Object[]{e});
                        }
                    }
                }
                this.mState = 0;
                if (toneGenerator2 != null) {
                    toneGenerator2.release();
                }
            } catch (RuntimeException e2) {
                Log.w(this, "Failed to create ToneGenerator.", new Object[]{e2});
            }
        } catch (Throwable th) {
            if (0 != 0) {
                toneGenerator.release();
            }
            throw th;
        }
    }

    private void playMediaTone(int i, final int i2) {
        synchronized (this) {
            if (this.mState != 2) {
                this.mState = 1;
            }
            Log.i(this, "playMediaTone: toneResourceId=%d", new Object[]{Integer.valueOf(i2)});
            MediaPlayerAdapter mediaPlayerAdapter = this.mMediaPlayerFactory.get(i2, new AudioAttributes.Builder().setUsage(2).setContentType(1).setLegacyStreamType(i).build());
            this.mToneMediaPlayer = mediaPlayerAdapter;
            mediaPlayerAdapter.setLooping(false);
            int duration = this.mToneMediaPlayer.getDuration();
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            this.mToneMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.android.server.telecom.InCallTonePlayer.1
                @Override // android.media.MediaPlayer.OnCompletionListener
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Log.i(this, "playMediaTone: toneResourceId=%d completed.", new Object[]{Integer.valueOf(i2)});
                    synchronized (this) {
                        InCallTonePlayer.this.mState = 0;
                    }
                    InCallTonePlayer.this.mToneMediaPlayer.release();
                    InCallTonePlayer.this.mToneMediaPlayer = null;
                    countDownLatch.countDown();
                }
            });
            this.mToneMediaPlayer.start();
            try {
                countDownLatch.await(duration * 2, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Log.e(this, e, "playMediaTone: tone playback interrupted.", new Object[0]);
            }
        }
    }

    @VisibleForTesting
    public boolean startTone() {
        if (this.mToneId == 2 && !this.mAudioManagerAdapter.isVolumeOverZero()) {
            Log.i(this, "startTone: skip end-call tone as device is silenced.", new Object[0]);
            return false;
        }
        int i = sTonesPlaying + 1;
        sTonesPlaying = i;
        if (i == 1) {
            this.mCallAudioManager.setIsTonePlaying(true);
        }
        synchronized (this.mSessionLock) {
            if (this.mSession != null) {
                Log.cancelSubsession(this.mSession);
            }
            this.mSession = Log.createSubsession();
        }
        super.start();
        return true;
    }

    @Override // java.lang.Thread
    public void start() {
        Log.w(this, "Do not call the start method directly; use startTone instead.", new Object[0]);
    }

    @VisibleForTesting
    public void stopTone() {
        synchronized (this) {
            if (this.mState == 1) {
                Log.d(this, "Stopping the tone %d.", new Object[]{Integer.valueOf(this.mToneId)});
                notify();
            }
            this.mState = 2;
        }
    }

    @VisibleForTesting
    public void cleanup() {
        sTonesPlaying = 0;
    }

    private void cleanUpTonePlayer() {
        this.mMainThreadHandler.post(new Runnable("ICTP.cUTP", this.mLock) { // from class: com.android.server.telecom.InCallTonePlayer.2
            public void loggedRun() {
                if (InCallTonePlayer.sTonesPlaying == 0) {
                    Log.wtf(this, "Over-releasing focus for tone player.", new Object[0]);
                } else {
                    if (InCallTonePlayer.access$306() != 0 || InCallTonePlayer.this.mCallAudioManager == null) {
                        return;
                    }
                    InCallTonePlayer.this.mCallAudioManager.setIsTonePlaying(false);
                }
            }
        }.prepare());
    }
}
