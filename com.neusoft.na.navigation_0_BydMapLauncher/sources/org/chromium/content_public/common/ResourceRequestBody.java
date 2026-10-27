package org.chromium.content_public.common;

import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public final class ResourceRequestBody {
    private byte[] mEncodedNativeForm;

    private static native byte[] nativeCreateResourceRequestBodyFromBytes(byte[] bArr);

    private ResourceRequestBody(byte[] bArr) {
        this.mEncodedNativeForm = bArr;
    }

    private static ResourceRequestBody createFromEncodedNativeForm(byte[] bArr) {
        return new ResourceRequestBody(bArr);
    }

    private byte[] getEncodedNativeForm() {
        return this.mEncodedNativeForm;
    }

    public static ResourceRequestBody createFromBytes(byte[] bArr) {
        return createFromEncodedNativeForm(nativeCreateResourceRequestBodyFromBytes(bArr));
    }
}
