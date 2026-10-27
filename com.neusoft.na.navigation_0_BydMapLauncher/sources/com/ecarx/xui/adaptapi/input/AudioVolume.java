package com.ecarx.xui.adaptapi.input;

import android.content.Context;
import com.neusoft.aam.AlfusAudioManager;
import com.neusoft.optimus.utility.log.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class AudioVolume extends AudioApi {
    private String TAG = "AudioApi";

    @Override // com.ecarx.xui.adaptapi.input.AudioApi
    public int getActiveAudioFocusType(Context context) {
        AlfusAudioManager alfusAudioManager = new AlfusAudioManager();
        LogTool.d(this.TAG, "getActiveStreamType()=" + alfusAudioManager.getActiveStreamType());
        return alfusAudioManager.getActiveStreamType();
    }
}
