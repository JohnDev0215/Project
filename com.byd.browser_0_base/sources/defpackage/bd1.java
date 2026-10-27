package defpackage;

import android.media.MediaFormat;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: MediaFormatBuilder.java */
/* JADX INFO: loaded from: classes3.dex */
public final class bd1 {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0170 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0174  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(android.media.MediaFormat r20, boolean r21) {
        /*
            Method dump skipped, instruction units count: 640
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bd1.a(android.media.MediaFormat, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0057 A[Catch: all -> 0x0128, TryCatch #0 {, blocks: (B:10:0x0010, B:24:0x0031, B:25:0x0036, B:40:0x0057, B:41:0x005c, B:47:0x006c, B:48:0x0071, B:49:0x0126), top: B:56:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x006c A[Catch: all -> 0x0128, TryCatch #0 {, blocks: (B:10:0x0010, B:24:0x0031, B:25:0x0036, B:40:0x0057, B:41:0x005c, B:47:0x006c, B:48:0x0071, B:49:0x0126), top: B:56:0x0010 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.media.MediaFormat b(java.lang.String r5, int r6, int r7, byte[][] r8, org.chromium.media.HdrMetadata r9, boolean r10) {
        /*
            Method dump skipped, instruction units count: 303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bd1.b(java.lang.String, int, int, byte[][], org.chromium.media.HdrMetadata, boolean):android.media.MediaFormat");
    }

    public static void c(MediaFormat mediaFormat, byte[][] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i].length != 0) {
                mediaFormat.setByteBuffer(gq2.e("csd-", i), ByteBuffer.wrap(bArr[i]));
            }
        }
    }
}
