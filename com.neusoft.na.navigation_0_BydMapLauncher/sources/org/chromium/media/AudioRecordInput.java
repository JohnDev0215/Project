package org.chromium.media;

import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import android.os.Process;
import java.nio.ByteBuffer;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media")
class AudioRecordInput {
    private static final boolean DEBUG = false;
    private static final int HARDWARE_DELAY_MS = 100;
    private static final String TAG = "cr.media";
    private AcousticEchoCanceler mAEC;
    private AudioRecord mAudioRecord;
    private AudioRecordThread mAudioRecordThread;
    private final int mBitsPerSample;
    private ByteBuffer mBuffer;
    private final int mChannels;
    private final int mHardwareDelayBytes;
    private final long mNativeAudioRecordInputStream;
    private final int mSampleRate;
    private final boolean mUsePlatformAEC;

    private native void nativeCacheDirectBufferAddress(long j, ByteBuffer byteBuffer);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeOnData(long j, int i, int i2);

    private class AudioRecordThread extends Thread {
        private volatile boolean mKeepAlive;

        private AudioRecordThread() {
            this.mKeepAlive = true;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(-19);
            try {
                AudioRecordInput.this.mAudioRecord.startRecording();
                while (this.mKeepAlive) {
                    int i = AudioRecordInput.this.mAudioRecord.read(AudioRecordInput.this.mBuffer, AudioRecordInput.this.mBuffer.capacity());
                    if (i > 0) {
                        AudioRecordInput audioRecordInput = AudioRecordInput.this;
                        audioRecordInput.nativeOnData(audioRecordInput.mNativeAudioRecordInputStream, i, AudioRecordInput.this.mHardwareDelayBytes);
                    } else {
                        Log.e(AudioRecordInput.TAG, "read failed: %d", Integer.valueOf(i));
                        if (i == -3) {
                            this.mKeepAlive = false;
                        }
                    }
                }
                try {
                    AudioRecordInput.this.mAudioRecord.stop();
                } catch (IllegalStateException e) {
                    Log.e(AudioRecordInput.TAG, "stop failed", e);
                }
            } catch (IllegalStateException e2) {
                Log.e(AudioRecordInput.TAG, "startRecording failed", e2);
            }
        }

        public void joinRecordThread() {
            this.mKeepAlive = false;
            while (isAlive()) {
                try {
                    join();
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    private static AudioRecordInput createAudioRecordInput(long j, int i, int i2, int i3, int i4, boolean z) {
        return new AudioRecordInput(j, i, i2, i3, i4, z);
    }

    private AudioRecordInput(long j, int i, int i2, int i3, int i4, boolean z) {
        this.mNativeAudioRecordInputStream = j;
        this.mSampleRate = i;
        this.mChannels = i2;
        this.mBitsPerSample = i3;
        this.mHardwareDelayBytes = (((i * 100) / 1000) * i3) / 8;
        this.mUsePlatformAEC = z;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i4);
        this.mBuffer = byteBufferAllocateDirect;
        nativeCacheDirectBufferAddress(j, byteBufferAllocateDirect);
    }

    private boolean open() {
        int i;
        if (this.mAudioRecord != null) {
            Log.e(TAG, "open() called twice without a close()", new Object[0]);
            return false;
        }
        int i2 = this.mChannels;
        int i3 = 2;
        if (i2 == 1) {
            i = 16;
        } else {
            if (i2 != 2) {
                Log.e(TAG, "Unsupported number of channels: %d", Integer.valueOf(i2));
                return false;
            }
            i = 12;
        }
        int i4 = this.mBitsPerSample;
        if (i4 == 8) {
            i3 = 3;
        } else if (i4 != 16) {
            Log.e(TAG, "Unsupported bits per sample: %d", Integer.valueOf(i4));
            return false;
        }
        int i5 = i3;
        int minBufferSize = AudioRecord.getMinBufferSize(this.mSampleRate, i, i5);
        if (minBufferSize < 0) {
            Log.e(TAG, "getMinBufferSize error: %d", Integer.valueOf(minBufferSize));
            return false;
        }
        try {
            this.mAudioRecord = new AudioRecord(7, this.mSampleRate, i, i5, Math.max(this.mBuffer.capacity(), minBufferSize));
            if (AcousticEchoCanceler.isAvailable()) {
                AcousticEchoCanceler acousticEchoCancelerCreate = AcousticEchoCanceler.create(this.mAudioRecord.getAudioSessionId());
                this.mAEC = acousticEchoCancelerCreate;
                if (acousticEchoCancelerCreate == null) {
                    Log.e(TAG, "AcousticEchoCanceler.create failed", new Object[0]);
                    return false;
                }
                int enabled = acousticEchoCancelerCreate.setEnabled(this.mUsePlatformAEC);
                if (enabled != 0) {
                    Log.e(TAG, "setEnabled error: %d", Integer.valueOf(enabled));
                    return false;
                }
            }
            return true;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "AudioRecord failed", e);
            return false;
        }
    }

    private void start() {
        if (this.mAudioRecord == null) {
            Log.e(TAG, "start() called before open().", new Object[0]);
        } else {
            if (this.mAudioRecordThread != null) {
                return;
            }
            AudioRecordThread audioRecordThread = new AudioRecordThread();
            this.mAudioRecordThread = audioRecordThread;
            audioRecordThread.start();
        }
    }

    private void stop() {
        AudioRecordThread audioRecordThread = this.mAudioRecordThread;
        if (audioRecordThread == null) {
            return;
        }
        audioRecordThread.joinRecordThread();
        this.mAudioRecordThread = null;
    }

    private void close() {
        if (this.mAudioRecordThread != null) {
            Log.e(TAG, "close() called before stop().", new Object[0]);
            return;
        }
        if (this.mAudioRecord == null) {
            return;
        }
        AcousticEchoCanceler acousticEchoCanceler = this.mAEC;
        if (acousticEchoCanceler != null) {
            acousticEchoCanceler.release();
            this.mAEC = null;
        }
        this.mAudioRecord.release();
        this.mAudioRecord = null;
    }
}
