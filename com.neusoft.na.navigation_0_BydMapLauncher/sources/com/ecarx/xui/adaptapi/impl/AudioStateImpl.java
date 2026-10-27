package com.ecarx.xui.adaptapi.impl;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import com.ecarx.xui.adaptapi.audio.audiofx.IAudioState;

/* JADX INFO: loaded from: classes2.dex */
public class AudioStateImpl implements IAudioState {
    private final String TAG = "AudioStateImpl";
    private Context mContext;

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IAudioState
    public int getSoundStageOptimizedSeat() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IAudioState
    public boolean isBootUpMusicOn() {
        return false;
    }

    public AudioStateImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IAudioState
    public int getNaviVoiceMixMode() {
        int i = Settings.System.getInt(this.mContext.getContentResolver(), "NAVI_MIX_SWITCH", 0) == 0 ? 2 : 1;
        Log.d("AudioStateImpl", "getMixingControlState() state is " + i);
        return i;
    }
}
