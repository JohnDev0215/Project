package android.media;

import android.annotation.SystemApi;
import android.media.AudioAttributes;
import android.media.VolumeShaper;
import com.android.internal.util.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class HwAudioSource extends PlayerBase {
    private final AudioAttributes mAudioAttributes;
    private final AudioDeviceInfo mAudioDeviceInfo;
    private int mNativeHandle;

    @Override // android.media.PlayerBase
    int playerApplyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation) {
        return 0;
    }

    @Override // android.media.PlayerBase
    int playerSetAuxEffectSendLevel(boolean z, float f) {
        return 0;
    }

    @Override // android.media.PlayerBase
    void playerSetVolume(boolean z, float f, float f2) {
    }

    private HwAudioSource(AudioDeviceInfo audioDeviceInfo, AudioAttributes audioAttributes) {
        super(audioAttributes, 14);
        Preconditions.checkNotNull(audioDeviceInfo);
        Preconditions.checkNotNull(audioAttributes);
        Preconditions.checkArgument(audioDeviceInfo.isSource(), "Requires a source device");
        this.mAudioDeviceInfo = audioDeviceInfo;
        this.mAudioAttributes = audioAttributes;
        baseRegisterPlayer();
    }

    @Override // android.media.PlayerBase
    VolumeShaper.State playerGetVolumeShaperState(int i) {
        return new VolumeShaper.State(1.0f, 1.0f);
    }

    @Override // android.media.PlayerBase
    void playerStart() {
        start();
    }

    @Override // android.media.PlayerBase
    void playerPause() {
        stop();
    }

    @Override // android.media.PlayerBase
    void playerStop() {
        stop();
    }

    public void start() {
        Preconditions.checkState(!isPlaying(), "HwAudioSource is currently playing");
        baseStart();
        this.mNativeHandle = AudioSystem.startAudioSource(this.mAudioDeviceInfo.getPort().activeConfig(), this.mAudioAttributes);
    }

    public boolean isPlaying() {
        return this.mNativeHandle != 0;
    }

    public void stop() {
        baseStop();
        int i = this.mNativeHandle;
        if (i > 0) {
            AudioSystem.stopAudioSource(i);
            this.mNativeHandle = 0;
        }
    }

    public static final class Builder {
        private AudioAttributes mAudioAttributes;
        private AudioDeviceInfo mAudioDeviceInfo;

        public Builder setAudioAttributes(AudioAttributes audioAttributes) {
            Preconditions.checkNotNull(audioAttributes);
            this.mAudioAttributes = audioAttributes;
            return this;
        }

        public Builder setAudioDeviceInfo(AudioDeviceInfo audioDeviceInfo) {
            Preconditions.checkNotNull(audioDeviceInfo);
            Preconditions.checkArgument(audioDeviceInfo.isSource());
            this.mAudioDeviceInfo = audioDeviceInfo;
            return this;
        }

        public HwAudioSource build() {
            Preconditions.checkNotNull(this.mAudioDeviceInfo);
            if (this.mAudioAttributes == null) {
                this.mAudioAttributes = new AudioAttributes.Builder().setUsage(1).build();
            }
            return new HwAudioSource(this.mAudioDeviceInfo, this.mAudioAttributes);
        }
    }
}
