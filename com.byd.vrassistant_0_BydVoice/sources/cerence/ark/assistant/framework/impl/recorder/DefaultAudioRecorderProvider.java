package cerence.ark.assistant.framework.impl.recorder;

import cerence.ark.assistant.framework.audio.recorder.AudioRecorderProvider;
import cerence.ark.assistant.framework.audio.recorder.IAudioRecorder;

/* JADX INFO: loaded from: classes.dex */
public class DefaultAudioRecorderProvider extends AudioRecorderProvider {
    @Override // cerence.ark.assistant.framework.audio.recorder.AudioRecorderProvider
    public IAudioRecorder createAudioRecorder() {
        return new DefaultAudioRecorder();
    }
}
