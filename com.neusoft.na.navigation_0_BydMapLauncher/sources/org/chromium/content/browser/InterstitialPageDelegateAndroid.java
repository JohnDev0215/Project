package org.chromium.content.browser;

import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class InterstitialPageDelegateAndroid {
    private long mNativePtr;

    private native void nativeDontProceed(long j);

    private native long nativeInit(String str);

    private native void nativeProceed(long j);

    protected void commandReceived(String str) {
    }

    protected void onDontProceed() {
    }

    protected void onProceed() {
    }

    public InterstitialPageDelegateAndroid(String str) {
        this.mNativePtr = nativeInit(str);
    }

    public long getNative() {
        return this.mNativePtr;
    }

    private void onNativeDestroyed() {
        this.mNativePtr = 0L;
    }

    protected void proceed() {
        long j = this.mNativePtr;
        if (j != 0) {
            nativeProceed(j);
        }
    }

    protected void dontProceed() {
        long j = this.mNativePtr;
        if (j != 0) {
            nativeDontProceed(j);
        }
    }
}
