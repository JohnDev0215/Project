package com.google.android.projection.protocol;

/* JADX INFO: loaded from: classes.dex */
public abstract class VendorExtensionBase implements CarServiceProvider {
    private static final boolean DBG = false;
    private static final String TAG = "AHU.VENDOR_EXTENSION";
    protected final int MESSAGE_SUCCESS = 0;
    private long mNativeVendorExtensionBase;

    private native void nativeInit(int i, long j) throws IllegalStateException;

    private native void nativeSend(byte[] bArr);

    private native void nativeShutdown();

    protected abstract String[] getPackageWhitelist();

    protected abstract String getServiceName();

    protected abstract int onMessage(byte[] bArr);

    public VendorExtensionBase() {
        dbgLog(VendorExtensionBase.class.getSimpleName());
    }

    @Override // com.google.android.projection.protocol.CarServiceProvider
    public boolean create(int serviceId, long nativeGalReceiver) {
        dbgLog("init");
        nativeInit(serviceId, nativeGalReceiver);
        return true;
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public void destroy() {
        dbgLog("shutdown");
        nativeShutdown();
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public long getNativeInstance() {
        return this.mNativeVendorExtensionBase;
    }

    protected void sendData(byte[] data) {
        dbgLog("sendData");
        nativeSend(data);
    }

    private static void dbgLog(String message) {
    }
}
