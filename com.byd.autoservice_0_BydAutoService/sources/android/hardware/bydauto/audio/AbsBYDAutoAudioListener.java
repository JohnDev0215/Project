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
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onAllMuteState(int i3) {
    }

    public void onAudioFocusChanged(int i3, List<AudioFocusInfo> list) {
    }

    public void onAudioMicAmpGainChanged(int i3) {
    }

    public void onBtCall_MicMuteChanged(int i3) {
    }

    public void onCarTipsVoiceStateChanged(int i3, int i4) {
    }

    public void onChannelChanged(int i3) {
    }

    public void onChimesVolGainChanged(int i3, float f3) {
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDiracLiveModeChanged(int i3) {
    }

    public void onDiracLiveStateChanged(int i3) {
    }

    public void onEepromAmpTypeChanged(int i3) {
    }

    public void onEngineSoundTypeChanged(int i3) {
    }

    public void onEqualizerChanged(int i3, int i4) {
    }

    public void onError(int i3, String str) {
    }

    public void onGearSpeedChanged(int i3, int i4) {
    }

    public void onGeneralEqualizerChanged(int i3, int i4, int i5) {
    }

    public void onGeneralSoundFieldChanged(int i3, int i4, int i5) {
    }

    public void onLoudnessStateChanged(int i3) {
    }

    public void onLoudspeakerNumChanged(int i3) {
    }

    public void onLudnessTypeChange(int i3) {
    }

    public void onMuteStateChanged(int i3) {
    }

    public void onNaviMuteStateChanged(int i3) {
    }

    public void onNaviVolumeChanged(int i3) {
    }

    public void onNavigationMediaStateChanged(int i3) {
    }

    public void onPhoneVolume(int i3) {
    }

    public void onPowerMuteStateChange(int i3) {
    }

    public void onSoundEffectChanged(int i3) {
    }

    public void onSoundFieldChanged(int i3, int i4) {
    }

    public void onSoundFieldPositionChanged(int i3) {
    }

    public void onVSPStateChanged(int i3) {
    }

    public void onVoiceAssistVolume(int i3) {
    }

    public void onVolumeChanged(int i3, int i4) {
    }

    public void onVolumeCtrlModeChanged(int i3) {
    }
}
