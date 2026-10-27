package cerence.ark.assistant.framework.impl.prompter;

import cerence.ark.assistant.framework.audio.player.IAudioPlayer;
import cerence.ark.assistant.framework.impl.player.DefaultAudioPlayer;
import cerence.ark.assistant.framework.prompter.PromptPlayerProvider;

/* JADX INFO: loaded from: classes.dex */
public class DefaultPromptPlayerProvider extends PromptPlayerProvider {
    @Override // cerence.ark.assistant.framework.prompter.PromptPlayerProvider
    public IAudioPlayer createPromptPlayer() {
        return new DefaultAudioPlayer();
    }
}
