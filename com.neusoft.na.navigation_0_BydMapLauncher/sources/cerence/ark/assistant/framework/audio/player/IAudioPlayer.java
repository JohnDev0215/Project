package cerence.ark.assistant.framework.audio.player;

import android.media.AudioAttributes;
import cerence.ark.assistant.framework.audio.AudioChannel;
import cerence.ark.assistant.framework.audio.AudioFormat;

/* JADX INFO: loaded from: classes2.dex */
public interface IAudioPlayer {
    void onEndOfStream();

    boolean onInitialize(AudioChannel audioChannel, AudioFormat audioFormat, int i, AudioAttributes audioAttributes);

    void onPlayAudio(byte[] bArr);

    void onRelease();

    void onStop(boolean z);
}
