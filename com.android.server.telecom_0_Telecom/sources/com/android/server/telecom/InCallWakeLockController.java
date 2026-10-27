package com.android.server.telecom;

import android.telecom.Log;
import com.android.internal.annotations.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public class InCallWakeLockController extends CallsManagerListenerBase {
    private final CallsManager mCallsManager;
    private final TelecomWakeLock mTelecomWakeLock;

    @VisibleForTesting
    public InCallWakeLockController(TelecomWakeLock telecomWakeLock, CallsManager callsManager) {
        this.mCallsManager = callsManager;
        this.mTelecomWakeLock = telecomWakeLock;
        telecomWakeLock.setReferenceCounted(false);
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallAdded(Call call) {
        if (call.isExternalCall()) {
            return;
        }
        handleWakeLock();
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallRemoved(Call call) {
        if (call.isExternalCall()) {
            return;
        }
        handleWakeLock();
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallStateChanged(Call call, int i, int i2) {
        if (call.isExternalCall()) {
            return;
        }
        handleWakeLock();
    }

    private void handleWakeLock() {
        if (this.mCallsManager.getRingingOrSimulatedRingingCall() != null) {
            this.mTelecomWakeLock.acquire();
            Log.i(this, "Acquiring full wake lock", new Object[0]);
        } else {
            this.mTelecomWakeLock.release(0);
            Log.i(this, "Releasing full wake lock", new Object[0]);
        }
    }
}
