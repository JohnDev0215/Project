package com.android.bluetooth.btservice;

/* JADX INFO: loaded from: classes.dex */
final class JniCallbacks {
    private AdapterProperties mAdapterProperties;
    private AdapterService mAdapterService;
    private BondStateMachine mBondStateMachine;
    private RemoteDevices mRemoteDevices;

    JniCallbacks(AdapterService adapterService, AdapterProperties adapterProperties) {
        this.mAdapterService = adapterService;
        this.mAdapterProperties = adapterProperties;
    }

    void init(BondStateMachine bondStateMachine, RemoteDevices remoteDevices) {
        this.mRemoteDevices = remoteDevices;
        this.mBondStateMachine = bondStateMachine;
    }

    void cleanup() {
        this.mRemoteDevices = null;
        this.mAdapterProperties = null;
        this.mAdapterService = null;
        this.mBondStateMachine = null;
    }

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    void sspRequestCallback(byte[] address, byte[] name, int cod, int pairingVariant, int passkey) {
        this.mBondStateMachine.sspRequestCallback(address, name, cod, pairingVariant, passkey);
    }

    void devicePropertyChangedCallback(byte[] address, int[] types, byte[][] val) {
        this.mRemoteDevices.devicePropertyChangedCallback(address, types, val);
    }

    void deviceFoundCallback(byte[] address) {
        this.mRemoteDevices.deviceFoundCallback(address);
    }

    void pinRequestCallback(byte[] address, byte[] name, int cod, boolean min16Digits) {
        this.mBondStateMachine.pinRequestCallback(address, name, cod, min16Digits);
    }

    void bondStateChangeCallback(int status, byte[] address, int newState) {
        this.mBondStateMachine.bondStateChangeCallback(status, address, newState);
    }

    void aclStateChangeCallback(int status, byte[] address, int newState, int state_change_reason) {
        this.mRemoteDevices.aclStateChangeCallback(status, address, newState, state_change_reason);
    }

    void stateChangeCallback(int status) {
        this.mAdapterService.stateChangeCallback(status);
    }

    void discoveryStateChangeCallback(int state) {
        this.mAdapterProperties.discoveryStateChangeCallback(state);
    }

    void adapterPropertyChangedCallback(int[] types, byte[][] val) {
        this.mAdapterProperties.adapterPropertyChangedCallback(types, val);
    }
}
