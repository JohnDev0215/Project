package defpackage;

import defpackage.qh0;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: Values.java */
/* JADX INFO: loaded from: classes3.dex */
public final class vk3 extends ui3 {
    public boolean[] b;
    public long[] c;
    public String[] d;
    public uh0[] e;

    public static final vk3 b(e70 e70Var) {
        t50 t50VarL = e70Var.l(16);
        boolean[] zArr = null;
        long[] jArr = null;
        if (t50VarL.a == 0) {
            return null;
        }
        vk3 vk3Var = new vk3();
        int i = t50VarL.b;
        if (i == 0) {
            e70 e70VarT = e70Var.t(24, n3.F0(0));
            if (e70VarT != null) {
                t50 t50VarG = e70VarT.g();
                int i2 = t50VarG.b;
                int i3 = (i2 + 7) / 8;
                if (t50VarG.a < i3 + 8) {
                    throw new q80("Array header is incorrect.");
                }
                byte[] bArr = new byte[i3];
                gq0 gq0Var = e70VarT.a;
                ((ByteBuffer) gq0Var.a).position(e70VarT.b + 8);
                ((ByteBuffer) gq0Var.a).get(bArr);
                boolean[] zArr2 = new boolean[i2];
                for (int i4 = 0; i4 < i3; i4++) {
                    for (int i5 = 0; i5 < 8; i5++) {
                        int i6 = (i4 * 8) + i5;
                        if (i6 < i2) {
                            zArr2[i6] = (bArr[i4] & (1 << i5)) != 0;
                        }
                    }
                }
                zArr = zArr2;
            }
            vk3Var.b = zArr;
            vk3Var.a = 0;
        } else if (i == 1) {
            e70 e70VarT2 = e70Var.t(24, n3.F0(0));
            if (e70VarT2 != null) {
                jArr = new long[e70VarT2.i(-1, 8L).b];
                gq0 gq0Var2 = e70VarT2.a;
                ((ByteBuffer) gq0Var2.a).position(e70VarT2.b + 8);
                ((ByteBuffer) gq0Var2.a).asLongBuffer().get(jArr);
            }
            vk3Var.c = jArr;
            vk3Var.a = 1;
        } else if (i == 2) {
            e70 e70VarT3 = e70Var.t(24, false);
            int i7 = e70VarT3.k(-1).b;
            vk3Var.d = new String[i7];
            for (int i8 = 0; i8 < i7; i8++) {
                vk3Var.d[i8] = C0175b.g(i8, 8, 8, e70VarT3, false);
            }
            vk3Var.a = 2;
        } else if (i == 3) {
            e70 e70VarT4 = e70Var.t(24, false);
            int i9 = e70VarT4.k(-1).b;
            vk3Var.e = new uh0[i9];
            for (int i10 = 0; i10 < i9; i10++) {
                vk3Var.e[i10] = uh0.d(C0173a.g(i10, 8, 8, e70VarT4, false));
            }
            vk3Var.a = 3;
        }
        return vk3Var;
    }

    @Override // defpackage.ui3
    public final void a(qh0 qh0Var, int i) {
        qh0Var.b(16, i);
        qh0Var.b(this.a, i + 4);
        int i2 = this.a;
        boolean zF0 = n3.F0(0);
        if (i2 == 0) {
            boolean[] zArr = this.b;
            int i3 = i + 8;
            if (zArr == null) {
                qh0Var.q(i3, zF0);
                return;
            }
            int length = (zArr.length + 7) / 8;
            byte[] bArr = new byte[length];
            for (int i4 = 0; i4 < length; i4++) {
                for (int i5 = 0; i5 < 8; i5++) {
                    int i6 = (i4 * 8) + i5;
                    if (i6 < zArr.length && zArr[i6]) {
                        bArr[i4] = (byte) (bArr[i4] | ((byte) (1 << i5)));
                    }
                }
            }
            int length2 = zArr.length;
            qh0Var.f(i3, ((long) qh0Var.b.d) - ((long) (qh0Var.a + i3)));
            qh0 qh0VarT = qh0Var.t(new t50(length + 8, length2));
            qh0.a aVar = qh0VarT.b;
            aVar.b.position(qh0VarT.a + 8);
            aVar.b.put(bArr);
            return;
        }
        if (i2 == 1) {
            long[] jArr = this.c;
            int i7 = i + 8;
            if (jArr == null) {
                qh0Var.q(i7, zF0);
                return;
            }
            qh0 qh0VarS = qh0Var.s(8, jArr.length, i7, -1);
            qh0.a aVar2 = qh0VarS.b;
            aVar2.b.position(qh0VarS.a + 8);
            aVar2.b.asLongBuffer().put(jArr);
            return;
        }
        if (i2 == 2) {
            String[] strArr = this.d;
            if (strArr == null) {
                qh0Var.q(i + 8, false);
                return;
            }
            qh0 qh0VarR = qh0Var.r(strArr.length, i + 8);
            int iF = 0;
            while (true) {
                String[] strArr2 = this.d;
                if (iF >= strArr2.length) {
                    return;
                }
                iF = C0175b.f(iF, 8, 8, qh0VarR, strArr2[iF], false, iF, 1);
            }
        } else {
            if (i2 != 3) {
                return;
            }
            uh0[] uh0VarArr = this.e;
            if (uh0VarArr == null) {
                qh0Var.q(i + 8, false);
                return;
            }
            qh0 qh0VarR2 = qh0Var.r(uh0VarArr.length, i + 8);
            int i8 = 0;
            while (true) {
                uh0[] uh0VarArr2 = this.e;
                if (i8 >= uh0VarArr2.length) {
                    return;
                }
                qh0VarR2.k(uh0VarArr2[i8], (i8 * 8) + 8, false);
                i8++;
            }
        }
    }
}
