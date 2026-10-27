package com.audio.usbmic;

import android.media.AudioTrack;
import android.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class UsbAudioTrackLoopDriver {
    public static final int CHANNEL_OUT_STEREO = 12;
    public static final int ENCODING_PCM_16_BIT = 2;
    public static final int SAMPLES_RATE = 48000;
    private static final String TAG = "MicphoneService";
    private static LoopbackThread audioLoopback;
    public static UsbAudioTrackLoopDriver mServcie;
    private AudioTrack audioTrack;
    private AtomicBoolean work = new AtomicBoolean(false);

    private class LoopbackThread extends Thread {
        private int BufferSize;

        private LoopbackThread() {
            this.BufferSize = 0;
        }

        private void mSleep(int i) {
            try {
                Thread.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            int minBufferSize = AudioTrack.getMinBufferSize(48000, 12, 2);
            this.BufferSize = minBufferSize;
            UsbAudioTrackLoopDriver.this.audioTrack = new AudioTrack(3, 48000, 12, 2, minBufferSize, 1);
            UsbAudioTrackLoopDriver.this.audioTrack.play();
            byte[] bArr = new byte[this.BufferSize];
            while (UsbAudioTrackLoopDriver.this.work.get()) {
                UsbAudioTrackLoopDriver.this.audioTrack.write(bArr, 0, this.BufferSize);
            }
            UsbAudioTrackLoopDriver.this.work.set(false);
            mSleep(1000);
            UsbAudioTrackLoopDriver.this.audioTrack.flush();
            UsbAudioTrackLoopDriver.this.audioTrack.stop();
            UsbAudioTrackLoopDriver.this.audioTrack.release();
            UsbAudioTrackLoopDriver.this.audioTrack = null;
        }
    }

    public static UsbAudioTrackLoopDriver getInstance() {
        if (mServcie == null) {
            mServcie = new UsbAudioTrackLoopDriver();
        }
        return mServcie;
    }

    private int startLoopback() {
        Log.e(TAG, "capturePlayStart");
        if (this.work.get()) {
            return 0;
        }
        this.work.set(true);
        LoopbackThread loopbackThread = new LoopbackThread();
        audioLoopback = loopbackThread;
        loopbackThread.start();
        return 0;
    }

    private int stopLoopback() {
        if (this.work.get() && audioLoopback != null) {
            Log.e(TAG, "capturePlayStop");
            this.work.set(false);
            try {
                try {
                    audioLoopback.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "capturePlayStop ok");
            } finally {
                audioLoopback = null;
            }
        }
        return 0;
    }

    public int micAuthCheck(String str, int i, int i2) {
        return UsbMicDevice.usbMicAuthcheck(str, i, i2);
    }

    public int micServiceStart() {
        startLoopback();
        return 0;
    }

    public int micServiceStop() {
        stopLoopback();
        return 0;
    }

    public void release() {
        this.work = null;
    }
}
