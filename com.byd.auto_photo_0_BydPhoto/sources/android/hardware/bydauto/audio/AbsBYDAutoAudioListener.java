package android.hardware.bydauto.audio;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.media.AudioFocusInfo;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoAudioListener implements d {
    protected static final String TAG = "AbsBYDAutoAudioListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    public void onAllMuteState(int i) {
    }

    public void onAudioFocusChanged(int i, List<AudioFocusInfo> list) {
    }

    public void onAudioMicAmpGainChanged(int i) {
    }

    public void onBtCall_MicMuteChanged(int i) {
    }

    public void onChannelChanged(int i) {
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDiracLiveModeChanged(int i) {
    }

    public void onDiracLiveStateChanged(int i) {
    }

    public void onEepromAmpTypeChanged(int i) {
    }

    public void onEngineSoundTypeChanged(int i) {
    }

    public void onEqualizerChanged(int i, int i2) {
    }

    public void onError(int i, String str) {
    }

    public void onGearSpeedChanged(int i, int i2) {
    }

    public void onLoudnessStateChanged(int i) {
    }

    public void onLoudspeakerNumChanged(int i) {
    }

    public void onLudnessTypeChange(int i) {
    }

    public void onMuteStateChanged(int i) {
    }

    public void onNaviMuteStateChanged(int i) {
    }

    public void onNaviVolumeChanged(int i) {
    }

    public void onNavigationMediaStateChanged(int i) {
    }

    public void onPhoneVolume(int i) {
    }

    public void onPowerMuteStateChange(int i) {
    }

    public void onSoundEffectChanged(int i) {
    }

    public void onSoundFieldChanged(int i, int i2) {
    }

    public void onSoundFieldPositionChanged(int i) {
    }

    public void onVSPStateChanged(int i) {
    }

    public void onVoiceAssistVolume(int i) {
    }

    public void onVolumeChanged(int i, int i2) {
    }

    public void onVolumeCtrlModeChanged(int i) {
    }
}
