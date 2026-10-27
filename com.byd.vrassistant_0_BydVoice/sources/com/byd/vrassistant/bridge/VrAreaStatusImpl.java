package com.byd.vrassistant.bridge;

import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.manager.VoiceAssistantManager;

/* JADX INFO: loaded from: classes.dex */
public class VrAreaStatusImpl implements VrAreaStatus {
    @Override // com.byd.vrassistant.bridge.VrAreaStatus
    public boolean isDriverOnly() {
        return ((isRightDriver() ? 1 : 2) & VoiceAssistantManager.get().getVoiceLockMask()) == 0;
    }

    @Override // com.byd.vrassistant.bridge.VrAreaStatus
    public boolean isRightDriver() {
        return SystemSettingManager.get().isRightDriver();
    }
}
