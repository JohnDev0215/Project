package com.android.server.telecom;

import android.telecom.Log;
import android.telephony.TelephonyRegistryManager;

/* JADX INFO: loaded from: classes.dex */
final class PhoneStateBroadcaster extends CallsManagerListenerBase {
    private final CallsManager mCallsManager;
    private int mCurrentState = 0;
    private final TelephonyRegistryManager mRegistry;

    public PhoneStateBroadcaster(CallsManager callsManager) {
        this.mCallsManager = callsManager;
        TelephonyRegistryManager telephonyRegistryManager = (TelephonyRegistryManager) callsManager.getContext().getSystemService(TelephonyRegistryManager.class);
        this.mRegistry = telephonyRegistryManager;
        if (telephonyRegistryManager == null) {
            Log.w(this, "TelephonyRegistry is null", new Object[0]);
        }
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallStateChanged(Call call, int i, int i2) {
        if (call.isExternalCall()) {
            return;
        }
        updateStates(call);
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallAdded(Call call) {
        if (call.isExternalCall()) {
            return;
        }
        updateStates(call);
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallRemoved(Call call) {
        if (call.isExternalCall()) {
            return;
        }
        updateStates(call);
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onExternalCallChanged(Call call, boolean z) {
        updateStates(call);
    }

    private void updateStates(Call call) {
        int i;
        if (this.mCallsManager.hasRingingOrSimulatedRingingCall()) {
            i = 1;
        } else {
            i = this.mCallsManager.getFirstCallWithState(3, 10, 5, 6) != null ? 2 : 0;
        }
        sendPhoneStateChangedBroadcast(call, i);
    }

    int getCallState() {
        return this.mCurrentState;
    }

    private void sendPhoneStateChangedBroadcast(Call call, int i) {
        if (i == this.mCurrentState) {
            return;
        }
        this.mCurrentState = i;
        String schemeSpecificPart = null;
        if (!call.isSelfManaged() && call.getHandle() != null) {
            schemeSpecificPart = call.getHandle().getSchemeSpecificPart();
        }
        TelephonyRegistryManager telephonyRegistryManager = this.mRegistry;
        if (telephonyRegistryManager != null) {
            telephonyRegistryManager.notifyCallStateChangedForAllSubscriptions(i, schemeSpecificPart);
            Log.i(this, "Broadcasted state change: %s", new Object[]{Integer.valueOf(this.mCurrentState)});
        }
    }
}
