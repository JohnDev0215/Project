package com.ecarx.xui.adaptapi.impl;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.audio.audiofx.Audio;
import com.ecarx.xui.adaptapi.audio.audiofx.IAudioState;
import com.ecarx.xui.adaptapi.audio.audiofx.ICompensation;
import com.ecarx.xui.adaptapi.audio.audiofx.IEqualizer;
import com.ecarx.xui.adaptapi.audio.audiofx.IFaderBalance;
import com.ecarx.xui.adaptapi.audio.audiofx.IHarmanEqualizer;
import com.ecarx.xui.adaptapi.audio.audiofx.IWarnVolume;

/* JADX INFO: loaded from: classes2.dex */
public class AudioImpl extends Audio {
    private final String TAG = "AudioImpl";
    private Context mContext;

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.Audio
    public int getAudioProvider() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.Audio
    public boolean registerAudioSettingListener(Audio.IAudioSettingListener iAudioSettingListener) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.Audio
    public void setBootUpMusicOnOff(boolean z) {
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.Audio
    public void setSeatSoundStageOptimize(int i) {
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.Audio
    public boolean unregisterAudioSettingListener(Audio.IAudioSettingListener iAudioSettingListener) {
        return false;
    }

    public AudioImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.Audio
    public IFaderBalance getFaderBalance() {
        Log.d("AudioImpl", "getFaderBalance()");
        return new FaderBalanceImpl();
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.Audio
    public IEqualizer getEqualizer() {
        Log.d("AudioImpl", "getEqualizer()");
        return new EqualizerImpl(this.mContext);
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.Audio
    public ICompensation getCompensation() {
        Log.d("AudioImpl", "getCompensation()");
        return new CompensationImpl(this.mContext);
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.Audio
    public IAudioState getAudioState() {
        Log.d("AudioImpl", "getAudioState()");
        return new AudioStateImpl(this.mContext);
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.Audio
    public IHarmanEqualizer getHarmanEqualizer() {
        Log.d("AudioImpl", "getHarmanEqualizer()");
        return new HarmanEqualizerImpl();
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.Audio
    public void setNaviVoiceMixMode(int i) {
        Log.d("AudioImpl", "setNaviVoiceMixMode() " + i);
        if (i == 1) {
            i = 1;
        } else if (i == 2) {
            i = 0;
        }
        Settings.System.putInt(this.mContext.getContentResolver(), "NAVI_MIX_SWITCH", i);
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.Audio
    public IWarnVolume getWarnVolumeSetting() {
        Log.d("AudioImpl", "getWarnVolumeSetting()");
        return new WarnVolumeImpl();
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.Audio
    public FunctionStatus isAudioSettingSupported(int i) {
        FunctionStatus functionStatus = FunctionStatus.notactive;
        Log.d("AudioImpl", "isAudioSettingSupported() result " + functionStatus);
        return functionStatus;
    }
}
