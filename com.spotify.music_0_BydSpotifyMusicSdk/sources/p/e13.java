package p;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class e13 extends OutputStream {
    public final w13 c = new w13();
    public final File e;
    public final com.google.android.play.core.assetpacks.p k;
    public long l;
    public long m;
    public FileOutputStream n;
    public q03 o;

    public e13(File file, com.google.android.play.core.assetpacks.p pVar) {
        this.e = file;
        this.k = pVar;
    }

    @Override // java.io.OutputStream
    public final void write(int i) {
        write(new byte[]{(byte) i}, 0, 1);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        int iMin;
        String str;
        while (i2 > 0) {
            if (this.l == 0 && this.m == 0) {
                w13 w13Var = this.c;
                int iA = w13Var.a(bArr, i, i2);
                if (iA == -1) {
                    return;
                }
                i += iA;
                i2 -= iA;
                q03 q03VarB = w13Var.b();
                this.o = q03VarB;
                boolean z = q03VarB.e;
                com.google.android.play.core.assetpacks.p pVar = this.k;
                if (z) {
                    this.l = 0L;
                    byte[] bArr2 = q03VarB.f;
                    pVar.k(bArr2, bArr2.length);
                    this.m = this.o.f.length;
                } else if (q03VarB.c == 0 && ((str = q03VarB.a) == null || !str.endsWith("/"))) {
                    pVar.i(this.o.f);
                    File file = new File(this.e, this.o.a);
                    file.getParentFile().mkdirs();
                    this.l = this.o.b;
                    this.n = new FileOutputStream(file);
                } else {
                    byte[] bArr3 = this.o.f;
                    pVar.k(bArr3, bArr3.length);
                    this.l = this.o.b;
                }
            }
            String str2 = this.o.a;
            if (str2 == null || !str2.endsWith("/")) {
                q03 q03Var = this.o;
                if (q03Var.e) {
                    this.k.d(this.m, bArr, i, i2);
                    this.m += (long) i2;
                    iMin = i2;
                } else if (q03Var.c == 0) {
                    iMin = (int) Math.min(i2, this.l);
                    this.n.write(bArr, i, iMin);
                    long j = this.l - ((long) iMin);
                    this.l = j;
                    if (j == 0) {
                        this.n.close();
                    }
                } else {
                    iMin = (int) Math.min(i2, this.l);
                    q03 q03Var2 = this.o;
                    this.k.d((((long) q03Var2.f.length) + q03Var2.b) - this.l, bArr, i, iMin);
                    this.l -= (long) iMin;
                }
                i += iMin;
                i2 -= iMin;
            }
        }
    }
}
