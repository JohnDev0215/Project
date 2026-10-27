package cerence.ark.assistant.framework.impl.player;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.framework.audio.AudioChannel;
import cerence.ark.assistant.framework.audio.AudioFormat;
import cerence.ark.assistant.framework.audio.player.IAudioPlayer;

/* JADX INFO: loaded from: classes.dex */
public class DefaultAudioPlayer implements IAudioPlayer {
    private static final String TAG = "DefaultAudioPlayer";
    private AudioTrack mAudioTrack;

    @Override // cerence.ark.assistant.framework.audio.player.IAudioPlayer
    public boolean onInitialize(AudioChannel audioChannel, AudioFormat audioFormat, int i, AudioAttributes audioAttributes) {
        AudioAttributes audioAttributesBuild;
        int i2 = audioChannel == AudioChannel.CHANNEL_MONO ? 4 : 12;
        int i3 = audioFormat == AudioFormat.PCM_8BIT ? 3 : 2;
        int minBufferSize = AudioTrack.getMinBufferSize(i, i2, i3);
        if (minBufferSize < 0) {
            Logger.error(TAG, String.format("getMinBufferSize error = %s", Integer.valueOf(minBufferSize)));
            minBufferSize = (i / 1000) * (audioFormat.getValue() / 8) * audioChannel.getValue() * 400;
        }
        int i4 = minBufferSize;
        if (audioAttributes != null) {
            audioAttributesBuild = audioAttributes;
        } else {
            try {
                audioAttributesBuild = new AudioAttributes.Builder().setLegacyStreamType(3).build();
            } catch (IllegalArgumentException e) {
                e = e;
                Logger.error(TAG, "init audio player exception", e);
                return false;
            }
        }
        try {
            this.mAudioTrack = new AudioTrack(audioAttributesBuild, new AudioFormat.Builder().setChannelMask(i2).setEncoding(i3).setSampleRate(i).build(), i4, 1, 0);
            Logger.debug(TAG, "init audio player success");
            return true;
        } catch (IllegalArgumentException e2) {
            e = e2;
            Logger.error(TAG, "init audio player exception", e);
            return false;
        }
    }

    @Override // cerence.ark.assistant.framework.audio.player.IAudioPlayer
    public void onPlayAudio(byte[] bArr) {
        if (this.mAudioTrack.getPlayState() != 3) {
            this.mAudioTrack.play();
        }
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        this.mAudioTrack.write(bArr, 0, bArr.length);
    }

    @Override // cerence.ark.assistant.framework.audio.player.IAudioPlayer
    public void onEndOfStream() {
        this.mAudioTrack.stop();
    }

    @Override // cerence.ark.assistant.framework.audio.player.IAudioPlayer
    public void onStop(boolean z) {
        if (isAudioPlayerStopped()) {
            Logger.debug(TAG, "onStop: audio track already stopped");
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
        Logger.debug(TAG, "onRelease");
        onStop(true);
        this.mAudioTrack.release();
        this.mAudioTrack = null;
    }

    private boolean isAudioPlayerStopped() {
        AudioTrack audioTrack = this.mAudioTrack;
        return audioTrack != null && audioTrack.getPlayState() == 1;
    }
}
