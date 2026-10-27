package org.chromium.media;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MediaDrmStorageBridge$PersistentInfo {
    public final byte[] a;
    public final byte[] b;
    public final String c;
    public final int d;

    public MediaDrmStorageBridge$PersistentInfo(byte[] bArr, byte[] bArr2, String str, int i) {
        this.a = bArr;
        this.b = bArr2;
        this.c = str;
        this.d = i;
    }

    public static MediaDrmStorageBridge$PersistentInfo create(byte[] bArr, byte[] bArr2, String str, int i) {
        return new MediaDrmStorageBridge$PersistentInfo(bArr, bArr2, str, i);
    }

    public byte[] emeId() {
        return this.a;
    }

    public byte[] keySetId() {
        return this.b;
    }

    public int keyType() {
        return this.d;
    }

    public String mimeType() {
        return this.c;
    }
}
