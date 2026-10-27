package org.chromium.media;

import J.N;
import java.nio.ByteBuffer;
import java.util.HashMap;
import org.chromium.media.MediaDrmBridge;
import org.chromium.media.MediaDrmStorageBridge;

/* JADX INFO: compiled from: MediaDrmSessionManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class l {
    public final HashMap<ByteBuffer, b> a = new HashMap<>();
    public final HashMap<ByteBuffer, b> b = new HashMap<>();
    public final MediaDrmStorageBridge c;

    /* JADX INFO: compiled from: MediaDrmSessionManager.java */
    public static class a {
        public static final char[] d = "0123456789ABCDEF".toCharArray();
        public final byte[] a;
        public byte[] b;
        public byte[] c;

        public a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            this.a = bArr;
            this.b = bArr2;
            this.c = bArr3;
        }

        public static String a(byte[] bArr) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bArr.length; i++) {
                int i2 = bArr[i] >>> 4;
                char[] cArr = d;
                sb.append(cArr[i2]);
                sb.append(cArr[bArr[i] & 15]);
            }
            return sb.toString();
        }

        public final void b() {
            a(this.a);
        }
    }

    /* JADX INFO: compiled from: MediaDrmSessionManager.java */
    public static class b {
        public final a a;
        public final String b;
        public int c;

        public b(a aVar, String str, int i) {
            this.a = aVar;
            this.b = str;
            this.c = i;
        }
    }

    public l(MediaDrmStorageBridge mediaDrmStorageBridge) {
        this.c = mediaDrmStorageBridge;
    }

    public final b a(a aVar) {
        return this.a.get(ByteBuffer.wrap(aVar.a));
    }

    public final void b(a aVar, byte[] bArr, MediaDrmBridge.g gVar) {
        aVar.c = bArr;
        b bVarA = a(aVar);
        a aVar2 = bVarA.a;
        MediaDrmStorageBridge.PersistentInfo persistentInfo = new MediaDrmStorageBridge.PersistentInfo(aVar2.a, aVar2.c, bVarA.b, bVarA.c);
        MediaDrmStorageBridge mediaDrmStorageBridge = this.c;
        long j = mediaDrmStorageBridge.a;
        if (j != -1) {
            N.MeALR1v2(j, mediaDrmStorageBridge, persistentInfo, gVar);
        } else {
            gVar.onResult(Boolean.FALSE);
        }
    }
}
