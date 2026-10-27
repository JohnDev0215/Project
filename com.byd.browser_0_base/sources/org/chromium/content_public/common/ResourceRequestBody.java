package org.chromium.content_public.common;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public final class ResourceRequestBody {
    public final byte[] a;

    public ResourceRequestBody(byte[] bArr) {
        this.a = bArr;
    }

    @CalledByNative
    public static ResourceRequestBody createFromEncodedNativeForm(byte[] bArr) {
        return new ResourceRequestBody(bArr);
    }

    @CalledByNative
    public final byte[] getEncodedNativeForm() {
        return this.a;
    }
}
