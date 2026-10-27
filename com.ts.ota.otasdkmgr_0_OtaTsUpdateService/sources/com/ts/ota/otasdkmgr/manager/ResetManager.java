package com.ts.ota.otasdkmgr.manager;

import android.os.Bundle;
import android.util.Log;
import com.ts.qnxmanager.QnxManager;

/* JADX INFO: loaded from: classes.dex */
public class ResetManager {
    private static final String POWER_CYCLE_REASON = "powerCycleReason";
    private static final String POWER_CYCLE_TYPE = "powerCycleType";
    private static final String REQUESTER = "requester";
    private static final String SHUT_DOWN_TYPE = "shutDownType";
    private static final String SOC_BOOT_TARGET = "socBootTarget";
    private static final int SUB_RESET = 82;
    private static final String TAG = "ResetManager";
    private static final int TYPE_POWER = 4;
    private static final String VIP_BOOT_TARGET = "vipBootTarget";
    private static ResetManager sResetManager;
    private QnxManager mQnxManager = QnxManager.getInstance();

    private ResetManager() {
    }

    public static synchronized ResetManager getInstance() {
        if (sResetManager == null) {
            sResetManager = new ResetManager();
        }
        return sResetManager;
    }

    public boolean reset() {
        Log.w(TAG, "reset.in");
        Bundle bundle = new Bundle();
        bundle.putString(REQUESTER, "OTA_reset");
        bundle.putInt(POWER_CYCLE_TYPE, 1);
        bundle.putInt(SHUT_DOWN_TYPE, 1);
        bundle.putInt(SOC_BOOT_TARGET, 0);
        bundle.putInt(VIP_BOOT_TARGET, 0);
        bundle.putInt(POWER_CYCLE_REASON, 0);
        boolean result = sendMessage(SUB_RESET, bundle);
        Log.w(TAG, "reset.out " + result);
        return result;
    }

    private boolean sendMessage(int type, Bundle bundle) {
        try {
            this.mQnxManager.sendMessage(4, type, null, bundle);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
