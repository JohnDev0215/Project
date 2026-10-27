package com.byd.vrassistant.manager;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import cerence.ark.assistant.framework.audio.AudioChannel;
import cerence.ark.assistant.framework.audio.AudioFormat;
import cerence.ark.assistant.framework.audio.player.IAudioPlayer;
import com.byd.vrassistant.utils.RLog;

/* JADX INFO: loaded from: classes2.dex */
public class CustomAudioPlayer implements IAudioPlayer {
    private static final String TAG = "NEU_CustomAudioPlayer";
    private static final int sampleRate = 22050;
    private AudioTrack mAudioTrack;

    @Override // cerence.ark.assistant.framework.audio.player.IAudioPlayer
    public boolean onInitialize(AudioChannel audioChannel, AudioFormat audioFormat, int i, AudioAttributes audioAttributes) {
        RLog.i(TAG, "onInitialize param,sampleRateInHz=" + i);
        int minBufferSize = AudioTrack.getMinBufferSize(22050, 4, 2);
        if (minBufferSize < 0) {
            RLog.i(TAG, String.format("getMinBufferSize error = %s", Integer.valueOf(minBufferSize)));
            minBufferSize = (audioFormat.getValue() / 8) * 22 * audioChannel.getValue() * 400;
        }
        try {
            this.mAudioTrack = new AudioTrack(new AudioAttributes.Builder().setUsage(16).build(), new AudioFormat.Builder().setChannelMask(4).setEncoding(2).setSampleRate(22050).build(), minBufferSize, 1, 0);
            RLog.i(TAG, "init audio player success");
            return true;
        } catch (IllegalArgumentException e) {
            RLog.i(TAG, "init audio player exception", e);
            return false;
        }
    }

    @Override // cerence.ark.assistant.framework.audio.player.IAudioPlayer
    public void onPlayAudio(byte[] bArr) {
        if (this.mAudioTrack.getPlayState() != 3) {
            this.mAudioTrack.play();
        }
        if (bArr != null && bArr.length > 0) {
            this.mAudioTrack.write(bArr, 0, bArr.length);
        }
        for (byte b : bArr) {
        }
    }

    @Override // cerence.ark.assistant.framework.audio.player.IAudioPlayer
    public void onEndOfStream() {
        this.mAudioTrack.stop();
    }

    @Override // cerence.ark.assistant.framework.audio.player.IAudioPlayer
    public void onStop(boolean z) {
        if (isAudioPlayerStopped()) {
            RLog.i(TAG, "onStop: audio track already stopped");
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
        RLog.i(TAG, "onRelease");
        onStop(true);
        this.mAudioTrack.release();
        this.mAudioTrack = null;
    }

    private boolean isAudioPlayerStopped() {
        AudioTrack audioTrack = this.mAudioTrack;
        return audioTrack != null && audioTrack.getPlayState() == 1;
    }
}
