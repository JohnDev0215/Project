package a.h.h;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Comparator<byte[]> f737a = new a();

    public class a implements Comparator<byte[]> {
        @Override // java.util.Comparator
        public int compare(byte[] bArr, byte[] bArr2) {
            int length;
            int length2;
            byte[] bArr3 = bArr;
            byte[] bArr4 = bArr2;
            if (bArr3.length == bArr4.length) {
                for (int i = 0; i < bArr3.length; i++) {
                    if (bArr3[i] != bArr4[i]) {
                        length = bArr3[i];
                        length2 = bArr4[i];
                    }
                }
                return 0;
            }
            length = bArr3.length;
            length2 = bArr4.length;
            return length - length2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0092 A[LOOP:1: B:15:0x004d->B:30:0x0092, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0096 A[EDGE_INSN: B:81:0x0096->B:32:0x0096 BREAK  A[LOOP:1: B:15:0x004d->B:30:0x0092], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static a.h.h.g a(android.content.Context r20, a.h.h.e r21, android.os.CancellationSignal r22) {
        /*
            Method dump skipped, instruction units count: 447
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.h.h.d.a(android.content.Context, a.h.h.e, android.os.CancellationSignal):a.h.h.g");
    }
}
