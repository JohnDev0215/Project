package com.byd.vrassistant.manager;

import cerence.ark.assistant.framework.audio.recorder.AudioRecorderProvider;
import cerence.ark.assistant.framework.audio.recorder.IAudioRecorder;

/* JADX INFO: loaded from: classes2.dex */
public class CustomAudioRecorderProvider extends AudioRecorderProvider {
    @Override // cerence.ark.assistant.framework.audio.recorder.AudioRecorderProvider
    public IAudioRecorder createAudioRecorder() {
        return new CustomAudioRecorder();
    }
}
