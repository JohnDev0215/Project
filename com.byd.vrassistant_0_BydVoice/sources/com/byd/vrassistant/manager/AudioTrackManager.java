package com.byd.vrassistant.manager;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.Process;
import com.byd.vrassistant.utils.RLog;
import java.lang.Thread;

/* JADX INFO: loaded from: classes2.dex */
public class AudioTrackManager {
    private static final String TAG = "NEU_AudioTrackManager";
    private static final int mAudioFormat = 2;
    private static final int mChannelConfig = 4;
    private static volatile AudioTrackManager mInstance = null;
    private static final int mMode = 1;
    private static final int mSampleRateInHz = 22050;
    private static final int mStreamType = 16;
    private AudioTrack mAudioTrack;
    private int mMinBufferSize;
    private Thread mRecordThread;
    private boolean isStart = false;
    Runnable recordRunnable = new Runnable() { // from class: com.byd.vrassistant.manager.AudioTrackManager.1
        @Override // java.lang.Runnable
        public void run() {
            try {
                Process.setThreadPriority(-19);
                int i = AudioTrackManager.this.mMinBufferSize;
                byte[] bArr = new byte[i];
                RLog.d(AudioTrackManager.TAG, "mAudioTrack==start=write");
                while (AudioTrackManager.this.isStart) {
                    if (AudioTrackManager.this.mAudioTrack.getPlayState() != 3) {
                        AudioTrackManager.this.mAudioTrack.play();
                    }
                    AudioTrackManager.this.mAudioTrack.write(bArr, 0, i);
                }
                RLog.d(AudioTrackManager.TAG, "mAudioTrack==end=write");
                AudioTrackManager.this.stopPlay();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public AudioTrackManager() {
        initData();
    }

    private void initData() {
        this.mMinBufferSize = AudioTrack.getMinBufferSize(22050, 4, 2);
        this.mAudioTrack = new AudioTrack(new AudioAttributes.Builder().setUsage(16).build(), new AudioFormat.Builder().setChannelMask(4).setEncoding(2).setSampleRate(22050).build(), this.mMinBufferSize, 1, 0);
    }

    public static AudioTrackManager getInstance() {
        if (mInstance == null) {
            synchronized (AudioTrackManager.class) {
                if (mInstance == null) {
                    mInstance = new AudioTrackManager();
                }
            }
        }
        return mInstance;
    }

    private void destroyThread() {
        RLog.d(TAG, "destroyThread");
        try {
            try {
                this.isStart = false;
                if (this.mRecordThread != null && Thread.State.RUNNABLE == this.mRecordThread.getState()) {
                    try {
                        Thread.sleep(500L);
                        this.mRecordThread.interrupt();
                    } catch (Exception unused) {
                        this.mRecordThread = null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            this.mRecordThread = null;
        }
    }

    private void startThread() {
        this.isStart = true;
        if (this.mRecordThread == null) {
            Thread thread = new Thread(this.recordRunnable);
            this.mRecordThread = thread;
            thread.start();
            RLog.d(TAG, "  mRecordThread.start()");
        }
    }

    public void startPlay() {
        if (this.isStart) {
            return;
        }
        initData();
        startThread();
    }

    public void stopPlay() {
        try {
            RLog.d(TAG, "stopPlay");
            destroyThread();
            AudioTrack audioTrack = this.mAudioTrack;
            if (audioTrack != null) {
                if (audioTrack.getState() == 1) {
                    this.mAudioTrack.stop();
                }
                this.mAudioTrack.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack == null || audioTrack.getState() != 1) {
            return;
        }
        this.mAudioTrack.pause();
    }
}
