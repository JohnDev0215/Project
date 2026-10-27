package com.byd.vrassistant.bridge;

import android.util.Log;
import com.byd.vrassistant.ContextHolder;
import com.byd.vrassistant.manager.DataManager;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.utils.RLog;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class VrStatusImpl implements VrStatus {
    private static final String TAG = "NEU_VrStatusImpl";

    @Override // com.byd.vrassistant.bridge.VrStatus
    public boolean isVoiceOnly() {
        boolean z = SystemSettingManager.get().getHUForbidStatus() != 0;
        RLog.d(TAG, "noUI: getHUForbidStatus=" + z);
        return z;
    }

    @Override // com.byd.vrassistant.bridge.VrStatus
    public boolean isAAForeground() {
        boolean zIsAndroidAutoForeground = SystemSettingManager.get().isAndroidAutoForeground();
        Log.i(TAG, "isAAForeground: " + zIsAndroidAutoForeground);
        return zIsAndroidAutoForeground;
    }

    @Override // com.byd.vrassistant.bridge.VrStatus
    public boolean isMultiDialogEnabled() {
        boolean isEnableDLDH = DataManager.get().getIsEnableDLDH();
        Log.i(TAG, "isMultiDialogEnabled: " + isEnableDLDH);
        return isEnableDLDH;
    }

    @Override // com.byd.vrassistant.bridge.VrStatus
    public boolean getEnableWordStreaming() {
        boolean enableWordStreaming = DataManager.get().getEnableWordStreaming();
        Log.i(TAG, "getEnableWordStreaming: " + enableWordStreaming);
        return enableWordStreaming;
    }

    @Override // com.byd.vrassistant.bridge.VrStatus
    public int getSkinType() {
        return SystemSettingManager.get().getSkinType();
    }

    @Override // com.byd.vrassistant.bridge.VrStatus
    public Locale getLocale(String str) {
        return ContextHolder.getCurrentLocale();
    }
}
