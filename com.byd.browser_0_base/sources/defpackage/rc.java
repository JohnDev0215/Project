package defpackage;

import com.google.android.gms.vision.barcode.BarcodeDetector;

/* JADX INFO: compiled from: BarcodeDetectionImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class rc implements qc {
    public final BarcodeDetector e;

    public rc(xc xcVar) {
        int[] iArr = xcVar.b;
        int i = 0;
        if (iArr != null && iArr.length > 0) {
            int i2 = 0;
            while (true) {
                int[] iArr2 = xcVar.b;
                if (i >= iArr2.length) {
                    break;
                }
                int i3 = iArr2[i];
                if (i3 == 0) {
                    i2 |= 4096;
                } else if (i3 == 1) {
                    i2 |= 1;
                } else if (i3 == 2) {
                    i2 |= 2;
                } else if (i3 == 3) {
                    i2 |= 4;
                } else if (i3 == 4) {
                    i2 |= 8;
                } else if (i3 == 5) {
                    i2 |= 16;
                } else if (i3 == 6) {
                    i2 |= 32;
                } else if (i3 == 7) {
                    i2 |= 64;
                } else if (i3 == 8) {
                    i2 |= 128;
                } else if (i3 == 9) {
                    i2 |= 2048;
                } else if (i3 == 10) {
                    i2 |= 256;
                } else if (i3 == 12) {
                    i2 |= 512;
                } else if (i3 == 13) {
                    i2 |= 1024;
                } else {
                    fa1.a("BarcodeDetectionImpl", "Unsupported barcode format hint: " + i3);
                }
                i++;
            }
            i = i2;
        }
        this.e = new BarcodeDetector.Builder(xz.c()).setBarcodeFormats(i).build();
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00cd A[PHI: r4
  0x00cd: PHI (r4v14 int) = (r4v13 int), (r4v15 int), (r4v16 int) binds: [B:18:0x00a8, B:20:0x00ab, B:33:0x00cc] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // defpackage.qc
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a0(defpackage.xe r9, wc.e r10) {
        /*
            Method dump skipped, instruction units count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rc.a0(xe, wc$e):void");
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.e.release();
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
        close();
    }
}
