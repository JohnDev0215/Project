package com.byd.vrassistant.bridge;

import com.byd.vrassistant.manager.SystemSettingManager;

/* JADX INFO: loaded from: classes.dex */
public class SystemSettingImpl implements SystemSetting {
    @Override // com.byd.vrassistant.bridge.SystemSetting
    public void setSendingHomeKey(boolean z) {
        SystemSettingManager.get().setSendingHomeKey(z);
    }

    @Override // com.byd.vrassistant.bridge.SystemSetting
    public void openWifiPage() {
        SystemSettingManager.get().openWifiSetting();
    }
}
