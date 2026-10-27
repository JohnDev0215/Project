package com.byd.vrassistant.manager;

import cerence.ark.assistant.framework.audio.player.AudioPlayerProvider;
import cerence.ark.assistant.framework.audio.player.IAudioPlayer;

/* JADX INFO: loaded from: classes2.dex */
public class CustomAudioPlayerProvider extends AudioPlayerProvider {
    @Override // cerence.ark.assistant.framework.audio.player.AudioPlayerProvider
    public IAudioPlayer createAudioPlayer() {
        return new CustomAudioPlayer();
    }
}
