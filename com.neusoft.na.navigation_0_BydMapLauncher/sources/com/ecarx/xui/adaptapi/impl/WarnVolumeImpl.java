package com.ecarx.xui.adaptapi.impl;

import android.util.Log;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.audio.audiofx.IWarnVolume;

/* JADX INFO: loaded from: classes2.dex */
public class WarnVolumeImpl implements IWarnVolume {
    private final String TAG = "WarnVolumeImpl";

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IWarnVolume
    public int getMaxWarnVolume(int i) {
        return 3;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IWarnVolume
    public int getMinWarnVolume(int i) {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IWarnVolume
    public int[] getSupportWarnVolumeLevel(int i) {
        return new int[0];
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IWarnVolume
    public int getWarnVolume(int i) {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IWarnVolume
    public int getWarnVolumeLevel(int i) {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IWarnVolume
    public int getWarnVolumeSetMode(int i) {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IWarnVolume
    public boolean setWarnVolume(int i, int i2) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IWarnVolume
    public boolean setWarnVolumeLevel(int i, int i2) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IWarnVolume
    public FunctionStatus isWarnVolumeTypeSupported(int i) {
        FunctionStatus functionStatus = FunctionStatus.active;
        Log.d("WarnVolumeImpl", "isWarnVolumeTypeSupported() result " + functionStatus);
        return functionStatus;
    }
}
