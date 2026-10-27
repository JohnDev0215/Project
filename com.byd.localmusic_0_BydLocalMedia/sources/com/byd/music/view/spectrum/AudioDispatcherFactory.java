package com.byd.music.view.spectrum;

import android.media.AudioRecord;

/* JADX INFO: loaded from: classes5.dex */
public class AudioDispatcherFactory {
    public static AudioDispatcher fromDefaultMicrophone(int i, int i2, int i3) {
        int minBufferSize = AudioRecord.getMinBufferSize(i, 16, 2);
        if (minBufferSize / 2 <= i2) {
            AudioRecord audioRecord = new AudioRecord(1, i, 16, 2, i2 * 2);
            AndroidAudioInputStream androidAudioInputStream = new AndroidAudioInputStream(audioRecord, new TarsosDSPAudioFormat(i, 16, 1, true, false));
            audioRecord.startRecording();
            return new AudioDispatcher(androidAudioInputStream, i2, i3);
        }
        throw new IllegalArgumentException("Buffer size too small should be at least " + (minBufferSize * 2));
    }

    public static AudioDispatcher fromPipe(String str, int i, int i2, int i3) {
        return new AudioDispatcher(new PipedAudioStream(str).getMonoStream(i, 0.0d), i2, i3);
    }
}
