package com.audio.usbmic;

import android.media.AudioRecord;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class UsbAudioRecordLoopDriver {
    private static final int AUDIO_SOURCE_MIC_LOOPBACK = 11;
    public static final int CHANNEL_OUT_STEREO = 12;
    public static final int ENCODING_PCM_16_BIT = 2;
    public static final int SAMPLES_RATE = 48000;
    private static final String TAG = "MicphoneService";
    public static UsbAudioRecordLoopDriver mServcie;
    private AudioRecord audioRecord;

    public static UsbAudioRecordLoopDriver getInstance() {
        if (mServcie == null) {
            mServcie = new UsbAudioRecordLoopDriver();
        }
        return mServcie;
    }

    public int micServiceStart() {
        AudioRecord audioRecord = new AudioRecord(11, 48000, 12, 2, AudioRecord.getMinBufferSize(48000, 12, 2));
        this.audioRecord = audioRecord;
        audioRecord.startRecording();
        Log.e(TAG, "start AudioRecord source 11");
        return 0;
    }

    public int micServiceStop() {
        this.audioRecord.stop();
        this.audioRecord.release();
        this.audioRecord = null;
        Log.e(TAG, "start AudioRecord");
        return 0;
    }
}
