package android.hardware.bydauto.audio;

import android.hardware.bydauto.BYDAutoEventValue;
import android.media.AudioFocusInfo;
import b.c;
import b.d;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoAudioListener implements d {
    public static final String TAG = "AbsBYDAutoAudioListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void onAllMuteState(int i8) {
    }

    public void onAudioFocusChanged(int i8, List<AudioFocusInfo> list) {
    }

    public void onAudioMicAmpGainChanged(int i8) {
    }

    public void onBtCall_MicMuteChanged(int i8) {
    }

    public void onCarTipsVoiceStateChanged(int i8, int i9) {
    }

    public void onChannelChanged(int i8) {
    }

    public void onChimesVolGainChanged(int i8, float f8) {
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDiracLiveModeChanged(int i8) {
    }

    public void onDiracLiveStateChanged(int i8) {
    }

    public void onEepromAmpTypeChanged(int i8) {
    }

    public void onEngineSoundTypeChanged(int i8) {
    }

    public void onEqualizerChanged(int i8, int i9) {
    }

    public void onError(int i8, String str) {
    }

    public void onGearSpeedChanged(int i8, int i9) {
    }

    public void onGeneralEqualizerChanged(int i8, int i9, int i10) {
    }

    public void onGeneralSoundFieldChanged(int i8, int i9, int i10) {
    }

    public void onLoudnessStateChanged(int i8) {
    }

    public void onLoudspeakerNumChanged(int i8) {
    }

    public void onLudnessTypeChange(int i8) {
    }

    public void onMuteStateChanged(int i8) {
    }

    public void onNaviMuteStateChanged(int i8) {
    }

    public void onNaviVolumeChanged(int i8) {
    }

    public void onNavigationMediaStateChanged(int i8) {
    }

    public void onPhoneVolume(int i8) {
    }

    public void onPowerMuteStateChange(int i8) {
    }

    public void onSoundEffectChanged(int i8) {
    }

    public void onSoundFieldChanged(int i8, int i9) {
    }

    public void onSoundFieldPositionChanged(int i8) {
    }

    public void onVSPStateChanged(int i8) {
    }

    public void onVoiceAssistVolume(int i8) {
    }

    public void onVolumeChanged(int i8, int i9) {
    }

    public void onVolumeCtrlModeChanged(int i8) {
    }
}
