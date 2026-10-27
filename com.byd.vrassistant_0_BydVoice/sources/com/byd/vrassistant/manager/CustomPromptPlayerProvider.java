package com.byd.vrassistant.manager;

import cerence.ark.assistant.framework.audio.player.IAudioPlayer;
import cerence.ark.assistant.framework.prompter.PromptPlayerProvider;

/* JADX INFO: loaded from: classes2.dex */
public class CustomPromptPlayerProvider extends PromptPlayerProvider {
    @Override // cerence.ark.assistant.framework.prompter.PromptPlayerProvider
    public IAudioPlayer createPromptPlayer() {
        return new CustomPromptPlayerListener();
    }
}
