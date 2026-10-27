package cerence.ark.assistant.framework.impl.player;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.SystemClock;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.framework.audio.AudioChannel;
import cerence.ark.assistant.framework.audio.AudioFormat;
import cerence.ark.assistant.framework.audio.player.IAudioPlayer;
import cerence.ark.assistant.library.Utils;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultAudioPlayer implements IAudioPlayer {
    private static final String TAG = "DefaultAudioPlayer";
    private AudioTrack mAudioTrack;

    @Override // cerence.ark.assistant.framework.audio.player.IAudioPlayer
    public boolean onInitialize(AudioChannel audioChannel, AudioFormat audioFormat, int i, AudioAttributes audioAttributes) {
        int i2 = audioChannel == AudioChannel.CHANNEL_MONO ? 4 : 12;
        int i3 = audioFormat == AudioFormat.PCM_8BIT ? 3 : 2;
        int minBufferSize = AudioTrack.getMinBufferSize(i, i2, i3);
        if (minBufferSize < 0) {
            Logger.error(TAG, Utils.format("getMinBufferSize error = %s", Integer.valueOf(minBufferSize)));
            minBufferSize = (i / 1000) * (audioFormat.getValue() / 8) * audioChannel.getValue() * 400;
        }
        int i4 = minBufferSize;
        if (audioAttributes == null) {
            try {
                audioAttributes = new AudioAttributes.Builder().setUsage(16).setContentType(1).build();
            } catch (IllegalArgumentException e) {
                Logger.error(TAG, "init audio player exception", e);
                return false;
            }
        }
        this.mAudioTrack = new AudioTrack(audioAttributes, new AudioFormat.Builder().setChannelMask(i2).setEncoding(i3).setSampleRate(i).build(), i4, 1, 0);
        Logger.info(TAG, "init audio player success");
        return true;
    }

    @Override // cerence.ark.assistant.framework.audio.player.IAudioPlayer
    public void onPlayAudio(byte[] bArr) {
        if (this.mAudioTrack.getPlayState() != 3) {
            this.mAudioTrack.play();
        }
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        this.mAudioTrack.write(bArr, 0, bArr.length);
        long jElapsedRealtime2 = SystemClock.elapsedRealtime() - jElapsedRealtime;
        if (jElapsedRealtime2 >= 5000) {
            Logger.error(TAG, Utils.format("onPlayAudio: It takes %s ms to write audio data (%d) to device", Long.valueOf(jElapsedRealtime2), Integer.valueOf(bArr.length)));
        }
    }

    @Override // cerence.ark.assistant.framework.audio.player.IAudioPlayer
    public void onEndOfStream() {
        this.mAudioTrack.stop();
    }

    @Override // cerence.ark.assistant.framework.audio.player.IAudioPlayer
    public void onStop(boolean z) {
        if (isAudioPlayerStopped()) {
            Logger.info(TAG, "onStop: audio track already stopped");
            return;
        }
        if (z) {
            this.mAudioTrack.pause();
            this.mAudioTrack.flush();
        }
        this.mAudioTrack.stop();
    }

    @Override // cerence.ark.assistant.framework.audio.player.IAudioPlayer
    public void onRelease() {
        Logger.info(TAG, "onRelease");
        onStop(true);
        this.mAudioTrack.release();
        this.mAudioTrack = null;
    }

    private boolean isAudioPlayerStopped() {
        AudioTrack audioTrack = this.mAudioTrack;
        return audioTrack != null && audioTrack.getPlayState() == 1;
    }
}
