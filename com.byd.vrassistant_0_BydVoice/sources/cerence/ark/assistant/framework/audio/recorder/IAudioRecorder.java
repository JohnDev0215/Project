package cerence.ark.assistant.framework.audio.recorder;

import cerence.ark.assistant.framework.audio.AudioChannel;
import cerence.ark.assistant.framework.audio.AudioFormat;

/* JADX INFO: loaded from: classes.dex */
public interface IAudioRecorder {
    byte[] onGetAudio(int i);

    boolean onInitialize(AudioChannel audioChannel, AudioFormat audioFormat, int i);

    void onRelease();
}
