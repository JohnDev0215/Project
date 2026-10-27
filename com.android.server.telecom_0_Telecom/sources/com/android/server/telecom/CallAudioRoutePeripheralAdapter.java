package com.android.server.telecom;

import com.android.server.telecom.DockManager;
import com.android.server.telecom.WiredHeadsetManager;
import com.android.server.telecom.bluetooth.BluetoothRouteManager;

/* JADX INFO: loaded from: classes.dex */
public class CallAudioRoutePeripheralAdapter implements WiredHeadsetManager.Listener, DockManager.Listener, BluetoothRouteManager.BluetoothStateListener {
    private final BluetoothRouteManager mBluetoothRouteManager;
    private final CallAudioRouteStateMachine mCallAudioRouteStateMachine;

    public CallAudioRoutePeripheralAdapter(CallAudioRouteStateMachine callAudioRouteStateMachine, BluetoothRouteManager bluetoothRouteManager, WiredHeadsetManager wiredHeadsetManager, DockManager dockManager) {
        this.mCallAudioRouteStateMachine = callAudioRouteStateMachine;
        this.mBluetoothRouteManager = bluetoothRouteManager;
        bluetoothRouteManager.setListener(this);
        wiredHeadsetManager.addListener(this);
        dockManager.addListener(this);
    }

    public boolean isBluetoothAudioOn() {
        return this.mBluetoothRouteManager.isBluetoothAudioConnectedOrPending();
    }

    @Override // com.android.server.telecom.bluetooth.BluetoothRouteManager.BluetoothStateListener
    public void onBluetoothDeviceListChanged() {
        this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(7);
    }

    @Override // com.android.server.telecom.bluetooth.BluetoothRouteManager.BluetoothStateListener
    public void onBluetoothActiveDevicePresent() {
        this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(8);
    }

    @Override // com.android.server.telecom.bluetooth.BluetoothRouteManager.BluetoothStateListener
    public void onBluetoothActiveDeviceGone() {
        this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(9);
    }

    @Override // com.android.server.telecom.bluetooth.BluetoothRouteManager.BluetoothStateListener
    public void onBluetoothAudioConnected() {
        this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(1302);
    }

    @Override // com.android.server.telecom.bluetooth.BluetoothRouteManager.BluetoothStateListener
    public void onBluetoothAudioDisconnected() {
        this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(1301);
    }

    @Override // com.android.server.telecom.bluetooth.BluetoothRouteManager.BluetoothStateListener
    public void onUnexpectedBluetoothStateChange() {
        this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(1201);
    }

    @Override // com.android.server.telecom.WiredHeadsetManager.Listener
    public void onWiredHeadsetPluggedInChanged(boolean z, boolean z2) {
        if (!z && z2) {
            this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(1);
        } else {
            if (!z || z2) {
                return;
            }
            this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(2);
        }
    }

    @Override // com.android.server.telecom.DockManager.Listener
    public void onDockChanged(boolean z) {
        this.mCallAudioRouteStateMachine.sendMessageWithSessionInfo(z ? 5 : 6);
    }
}
