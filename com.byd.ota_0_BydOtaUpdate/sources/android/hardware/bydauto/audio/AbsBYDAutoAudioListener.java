package android.hardware.bydauto.audio;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.media.AudioFocusInfo;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoAudioListener implements d {
    public static final String TAG = "AbsBYDAutoAudioListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onAllMuteState(int i2) {
    }

    public void onAudioFocusChanged(int i2, List<AudioFocusInfo> list) {
    }

    public void onAudioMicAmpGainChanged(int i2) {
    }

    public void onBtCall_MicMuteChanged(int i2) {
    }

    public void onCarTipsVoiceStateChanged(int i2, int i3) {
    }

    public void onChannelChanged(int i2) {
    }

    public void onChimesVolGainChanged(int i2, float f2) {
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDiracLiveModeChanged(int i2) {
    }

    public void onDiracLiveStateChanged(int i2) {
    }

    public void onEepromAmpTypeChanged(int i2) {
    }

    public void onEngineSoundTypeChanged(int i2) {
    }

    public void onEqualizerChanged(int i2, int i3) {
    }

    public void onError(int i2, String str) {
    }

    public void onGearSpeedChanged(int i2, int i3) {
    }

    public void onGeneralEqualizerChanged(int i2, int i3, int i4) {
    }

    public void onGeneralSoundFieldChanged(int i2, int i3, int i4) {
    }

    public void onLoudnessStateChanged(int i2) {
    }

    public void onLoudspeakerNumChanged(int i2) {
    }

    public void onLudnessTypeChange(int i2) {
    }

    public void onMuteStateChanged(int i2) {
    }

    public void onNaviMuteStateChanged(int i2) {
    }

    public void onNaviVolumeChanged(int i2) {
    }

    public void onNavigationMediaStateChanged(int i2) {
    }

    public void onPhoneVolume(int i2) {
    }

    public void onPowerMuteStateChange(int i2) {
    }

    public void onSoundEffectChanged(int i2) {
    }

    public void onSoundFieldChanged(int i2, int i3) {
    }

    public void onSoundFieldPositionChanged(int i2) {
    }

    public void onVSPStateChanged(int i2) {
    }

    public void onVoiceAssistVolume(int i2) {
    }

    public void onVolumeChanged(int i2, int i3) {
    }

    public void onVolumeCtrlModeChanged(int i2) {
    }
}
