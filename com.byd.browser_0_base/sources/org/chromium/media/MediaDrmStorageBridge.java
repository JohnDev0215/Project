package org.chromium.media;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public final class MediaDrmStorageBridge {
    public final long a;

    public static class PersistentInfo {
        public final byte[] a;
        public final byte[] b;
        public final String c;
        public final int d;

        public PersistentInfo(byte[] bArr, byte[] bArr2, String str, int i) {
            this.a = bArr;
            this.b = bArr2;
            this.c = str;
            this.d = i;
        }

        @CalledByNative
        public static PersistentInfo create(byte[] bArr, byte[] bArr2, String str, int i) {
            return new PersistentInfo(bArr, bArr2, str, i);
        }

        @CalledByNative
        public byte[] emeId() {
            return this.a;
        }

        @CalledByNative
        public byte[] keySetId() {
            return this.b;
        }

        @CalledByNative
        public int keyType() {
            return this.d;
        }

        @CalledByNative
        public String mimeType() {
            return this.c;
        }
    }

    public MediaDrmStorageBridge(long j) {
        this.a = j;
    }
}
