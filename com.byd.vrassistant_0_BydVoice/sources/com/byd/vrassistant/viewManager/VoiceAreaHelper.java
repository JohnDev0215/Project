package com.byd.vrassistant.viewManager;

import android.text.TextUtils;
import com.byd.vrassistant.bean.WakeVrArea;
import com.byd.vrassistant.bridge.BridgeHelper;
import com.byd.vrassistant.viewInf.IVoiceAreaHelper;

/* JADX INFO: loaded from: classes2.dex */
public class VoiceAreaHelper implements IVoiceAreaHelper {
    @Override // com.byd.vrassistant.viewInf.IVoiceAreaHelper
    public WakeVrArea wakeArea(String str) {
        if (!TextUtils.isEmpty(str) && !str.contains("FRONT")) {
            return str.contains("LEFT") ? WakeVrArea.LEFT : WakeVrArea.RIGHT;
        }
        if (BridgeHelper.getVrAreaStatus().isDriverOnly()) {
            return BridgeHelper.getVrAreaStatus().isRightDriver() ? WakeVrArea.RIGHT : WakeVrArea.LEFT;
        }
        return WakeVrArea.FRONT;
    }
}
