package cerence.ark.assistant.framework.impl.recorder;

import android.media.AudioRecord;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.framework.audio.AudioChannel;
import cerence.ark.assistant.framework.audio.AudioFormat;
import cerence.ark.assistant.framework.audio.recorder.IAudioRecorder;
import cerence.ark.assistant.library.Utils;

/* JADX INFO: loaded from: classes.dex */
public class DefaultAudioRecorder implements IAudioRecorder {
    private static final String TAG = "DefaultAudioRecorder";
    private byte[] mAudioBuffer;
    private AudioRecord mAudioRecord;

    protected int onGetAudioSource() {
        return 6;
    }

    @Override // cerence.ark.assistant.framework.audio.recorder.IAudioRecorder
    public boolean onInitialize(AudioChannel audioChannel, AudioFormat audioFormat, int i) {
        boolean z = false;
        if (!Utils.checkSelfPermission("android.permission.RECORD_AUDIO")) {
            Logger.error(TAG, "Initialize audio recorder failed, the android.permission.RECORD_AUDIO was denied.");
            return false;
        }
        int i2 = audioChannel == AudioChannel.CHANNEL_MONO ? 16 : 12;
        int i3 = audioFormat == AudioFormat.PCM_8BIT ? 3 : 2;
        int minBufferSize = AudioRecord.getMinBufferSize(i, i2, i3);
        if (minBufferSize < 0) {
            Logger.debug(TAG, String.format("getMinBufferSize error = %s", Integer.valueOf(minBufferSize)));
            minBufferSize = (i / 1000) * (audioFormat.getValue() / 8) * audioChannel.getValue() * 600;
        }
        try {
            AudioRecord audioRecord = new AudioRecord(onGetAudioSource(), i, i2, i3, minBufferSize);
            this.mAudioRecord = audioRecord;
            int state = audioRecord.getState();
            if (state == 1) {
                this.mAudioRecord.startRecording();
                Logger.debug(TAG, "init audio record success");
                z = true;
            } else {
                Logger.error(TAG, String.format("audio record init error = %s", Integer.valueOf(state)));
            }
        } catch (Exception e) {
            Logger.error(TAG, "init audio record exception", e);
        }
        return z;
    }

    @Override // cerence.ark.assistant.framework.audio.recorder.IAudioRecorder
    public byte[] onGetAudio(int i) {
        AudioRecord audioRecord = this.mAudioRecord;
        if (audioRecord == null) {
            Logger.error(TAG, "onGetAudio: audio recorder is null");
            return null;
        }
        byte[] bArr = this.mAudioBuffer;
        if (bArr == null || bArr.length != i) {
            this.mAudioBuffer = new byte[i];
        }
        int i2 = audioRecord.read(this.mAudioBuffer, 0, i);
        if (i2 == i) {
            return this.mAudioBuffer;
        }
        if (i2 > 0) {
            Logger.warn(TAG, String.format("just read partial data, bytesRead = %s", Integer.valueOf(i2)));
            byte[] bArr2 = new byte[i2];
            System.arraycopy(this.mAudioBuffer, 0, bArr2, 0, i2);
            return bArr2;
        }
        Logger.error(TAG, String.format("read audio error, bytesRead = %s", Integer.valueOf(i2)));
        return null;
    }

    @Override // cerence.ark.assistant.framework.audio.recorder.IAudioRecorder
    public void onRelease() {
        String str = TAG;
        Logger.debug(str, String.format("%s: onRelease", str));
        if (isInitAudioRecord()) {
            this.mAudioRecord.release();
            this.mAudioRecord = null;
        }
        this.mAudioBuffer = null;
    }

    private boolean isInitAudioRecord() {
        AudioRecord audioRecord = this.mAudioRecord;
        return audioRecord != null && audioRecord.getState() == 1;
    }
}
