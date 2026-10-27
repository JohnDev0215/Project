package com.google.android.projection.protocol;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothEndpoint implements CarServiceProvider, BluetoothCallbacks {
    private final String mCarBluetoothAddress;
    private final BluetoothEventListener mListener;
    private long mNativeBluetoothEndpoint;
    private final int[] mSupportedBluetoothPairingMethods;

    public interface BluetoothEventListener {
        void onAuthenticationResult(int i);

        void onPairingRequest(String str, int i);
    }

    private native int nativeInit(int i, long j) throws IllegalStateException;

    private native void nativeOnReadyForPairing(boolean z);

    private native void nativeRequestDelayedPairing();

    private native void nativeSendAuthData(String str, int i);

    private native void nativeSendBluetoothUnavailable();

    private native void nativeSetDeviceInfo(String str, int[] iArr);

    private native void nativeShutdown();

    public BluetoothEndpoint(BluetoothEventListener listener, String carBluetoothAddress, int[] supportedBluetoothPairingMethods) {
        this.mListener = listener;
        this.mCarBluetoothAddress = carBluetoothAddress;
        this.mSupportedBluetoothPairingMethods = supportedBluetoothPairingMethods;
    }

    @Override // com.google.android.projection.protocol.CarServiceProvider
    public boolean create(int serviceId, long nativeGalReceiver) {
        if (nativeInit(serviceId, nativeGalReceiver) == 0) {
            nativeSetDeviceInfo(this.mCarBluetoothAddress, this.mSupportedBluetoothPairingMethods);
            return true;
        }
        return false;
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public void destroy() {
        nativeShutdown();
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public long getNativeInstance() {
        return this.mNativeBluetoothEndpoint;
    }

    @Override // com.google.android.projection.protocol.BluetoothCallbacks
    public void onPairingRequest(String phoneAddress, int pairingMethod) {
        this.mListener.onPairingRequest(phoneAddress, pairingMethod);
    }

    @Override // com.google.android.projection.protocol.BluetoothCallbacks
    public void onAuthenticationResult(int status) {
        this.mListener.onAuthenticationResult(status);
    }

    public void requestDelayedPairing() {
        nativeRequestDelayedPairing();
    }

    public void sendBluetoothUnavailable() {
        nativeSendBluetoothUnavailable();
    }

    public void onReadyForPairing(boolean alreadyPaired) {
        nativeOnReadyForPairing(alreadyPaired);
    }

    public void sendAuthData(String authData, int pairingMethod) {
        nativeSendAuthData(authData, pairingMethod);
    }
}
