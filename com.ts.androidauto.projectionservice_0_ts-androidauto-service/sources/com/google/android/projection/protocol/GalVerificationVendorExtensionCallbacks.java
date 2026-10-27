package com.google.android.projection.protocol;

/* JADX INFO: loaded from: classes.dex */
interface GalVerificationVendorExtensionCallbacks extends NativeCallback {
    int injectInputCallback(byte[] bArr);

    int injectSensorDataCallback(byte[] bArr);

    int requestAudioFocusChangeCallback(byte[] bArr);

    int requestDisplayInformationCallback();

    int requestScreenCaptureCallback();

    int setVideoFocusCallback(byte[] bArr);
}
