package com.android.server.telecom;

import android.telecom.Log;
import com.android.internal.annotations.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
public class ProximitySensorManager extends CallsManagerListenerBase {
    private final CallsManager mCallsManager;
    private final TelecomWakeLock mTelecomWakeLock;

    public ProximitySensorManager(TelecomWakeLock telecomWakeLock, CallsManager callsManager) {
        this.mTelecomWakeLock = telecomWakeLock;
        this.mCallsManager = callsManager;
        Log.d(this, "onCreate: mProximityWakeLock: ", new Object[]{telecomWakeLock});
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallRemoved(Call call) {
        if (call.isExternalCall()) {
            return;
        }
        if (this.mCallsManager.getCalls().isEmpty()) {
            Log.i(this, "All calls removed, resetting proximity sensor to default state", new Object[0]);
            turnOff(true);
        }
        super.onCallRemoved(call);
    }

    @VisibleForTesting
    public void turnOn() {
        if (this.mCallsManager.getCalls().isEmpty()) {
            Log.w(this, "Asking to turn on prox sensor without a call? I don't think so.", new Object[0]);
        } else {
            this.mTelecomWakeLock.acquire();
        }
    }

    @VisibleForTesting
    public void turnOff(boolean z) {
        this.mTelecomWakeLock.release(!z ? 1 : 0);
    }
}
