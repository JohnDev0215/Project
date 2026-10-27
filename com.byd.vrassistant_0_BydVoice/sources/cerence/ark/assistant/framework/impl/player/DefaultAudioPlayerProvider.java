package cerence.ark.assistant.framework.impl.player;

import cerence.ark.assistant.framework.audio.player.AudioPlayerProvider;
import cerence.ark.assistant.framework.audio.player.IAudioPlayer;

/* JADX INFO: loaded from: classes.dex */
public class DefaultAudioPlayerProvider extends AudioPlayerProvider {
    @Override // cerence.ark.assistant.framework.audio.player.AudioPlayerProvider
    public IAudioPlayer createAudioPlayer() {
        return new DefaultAudioPlayer();
    }
}
