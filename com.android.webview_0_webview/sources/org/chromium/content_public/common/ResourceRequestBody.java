package org.chromium.content_public.common;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ResourceRequestBody {
    public byte[] a;

    public ResourceRequestBody(byte[] bArr) {
        this.a = bArr;
    }

    public static ResourceRequestBody createFromEncodedNativeForm(byte[] bArr) {
        return new ResourceRequestBody(bArr);
    }

    private byte[] getEncodedNativeForm() {
        return this.a;
    }
}
