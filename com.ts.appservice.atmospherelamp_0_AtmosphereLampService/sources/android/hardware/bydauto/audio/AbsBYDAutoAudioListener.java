package android.hardware.bydauto.audio;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.media.AudioFocusInfo;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoAudioListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoAudioListener";

    public void onChannelChanged(int channelId) {
    }

    public void onVolumeChanged(int value, int flag) {
    }

    public void onNaviVolumeChanged(int value) {
    }

    public void onMuteStateChanged(int state) {
    }

    public void onNaviMuteStateChanged(int state) {
    }

    public void onSoundFieldChanged(int x, int y) {
    }

    public void onSoundEffectChanged(int effect) {
    }

    public void onDiracLiveStateChanged(int state) {
    }

    public void onLoudnessStateChanged(int state) {
    }

    public void onEqualizerChanged(int frequencyNum, int value) {
    }

    public void onLoudspeakerNumChanged(int value) {
    }

    public void onVolumeCtrlModeChanged(int mode) {
    }

    public void onAudioMicAmpGainChanged(int value) {
    }

    public void onDiracLiveModeChanged(int mode) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
    }

    public void onVoiceAssistVolume(int value) {
    }

    public void onPhoneVolume(int value) {
    }

    public void onSoundFieldPositionChanged(int type) {
    }

    public void onAudioFocusChanged(int zoneId, List<AudioFocusInfo> focusInfoList) {
    }

    public void onNavigationMediaStateChanged(int value) {
    }

    public void onEepromAmpTypeChanged(int value) {
    }

    public void onGearSpeedChanged(int manufacturer, int gear) {
    }

    public void onAllMuteState(int state) {
    }

    public void onLudnessTypeChange(int type) {
    }

    public void onEngineSoundTypeChanged(int value) {
    }

    public void onBtCall_MicMuteChanged(int state) {
    }

    public void onPowerMuteStateChange(int state) {
    }

    public void onVSPStateChanged(int state) {
    }

    public void onCarTipsVoiceStateChanged(int type, int state) {
    }

    public void onChimesVolGainChanged(int chimeType, float value) {
    }

    public void onGeneralSoundFieldChanged(int manufactor, int balance, int fade) {
    }

    public void onGeneralEqualizerChanged(int manufactor, int frequencyNum, int value) {
    }
}
