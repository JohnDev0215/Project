package android.hardware.bydauto.audio;

import android.hardware.bydauto.BYDAutoEventValue;
import android.media.AudioFocusInfo;
import b.c;
import b.d;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoAudioListener implements d {
    protected static final String TAG = "AbsBYDAutoAudioListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onAllMuteState(int i7) {
    }

    public void onAudioFocusChanged(int i7, List<AudioFocusInfo> list) {
    }

    public void onAudioMicAmpGainChanged(int i7) {
    }

    public void onBtCall_MicMuteChanged(int i7) {
    }

    public void onChannelChanged(int i7) {
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDiracLiveModeChanged(int i7) {
    }

    public void onDiracLiveStateChanged(int i7) {
    }

    public void onEepromAmpTypeChanged(int i7) {
    }

    public void onEngineSoundTypeChanged(int i7) {
    }

    public void onEqualizerChanged(int i7, int i10) {
    }

    public void onError(int i7, String str) {
    }

    public void onGearSpeedChanged(int i7, int i10) {
    }

    public void onLoudnessStateChanged(int i7) {
    }

    public void onLoudspeakerNumChanged(int i7) {
    }

    public void onLudnessTypeChange(int i7) {
    }

    public void onMuteStateChanged(int i7) {
    }

    public void onNaviMuteStateChanged(int i7) {
    }

    public void onNaviVolumeChanged(int i7) {
    }

    public void onNavigationMediaStateChanged(int i7) {
    }

    public void onPhoneVolume(int i7) {
    }

    public void onPowerMuteStateChange(int i7) {
    }

    public void onSoundEffectChanged(int i7) {
    }

    public void onSoundFieldChanged(int i7, int i10) {
    }

    public void onSoundFieldPositionChanged(int i7) {
    }

    public void onVSPStateChanged(int i7) {
    }

    public void onVoiceAssistVolume(int i7) {
    }

    public void onVolumeChanged(int i7, int i10) {
    }

    public void onVolumeCtrlModeChanged(int i7) {
    }
}
