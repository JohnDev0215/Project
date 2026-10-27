package com.ecarx.xui.adaptapi.impl;

import android.util.Log;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.audio.audiofx.IHarmanEqualizer;

/* JADX INFO: loaded from: classes2.dex */
public class HarmanEqualizerImpl implements IHarmanEqualizer {
    private final String TAG = "HarmanEqualizerImpl";

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IHarmanEqualizer
    public short getBandLevel(int i) {
        return (short) 0;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IHarmanEqualizer
    public short[] getBandLevelRange() {
        return new short[0];
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IHarmanEqualizer
    public boolean isHarmanClariFiOnOff() {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IHarmanEqualizer
    public boolean isSurroundSoundOnOff() {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IHarmanEqualizer
    public boolean registerHarmanSettingListener(IHarmanEqualizer.IHarmanSettingListener iHarmanSettingListener) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IHarmanEqualizer
    public void setBandLevel(int i, short s) {
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IHarmanEqualizer
    public boolean setHarmanClariFiOnOff(boolean z) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IHarmanEqualizer
    public boolean setSurroundSoundOnOff(boolean z) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IHarmanEqualizer
    public boolean unregisterHarmanSettingListener(IHarmanEqualizer.IHarmanSettingListener iHarmanSettingListener) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IHarmanEqualizer
    public FunctionStatus isHarmanSettingSupported(int i) {
        FunctionStatus functionStatus = FunctionStatus.notactive;
        Log.d("HarmanEqualizerImpl", "isHarmanSettingSupported() result " + functionStatus);
        return functionStatus;
    }
}
