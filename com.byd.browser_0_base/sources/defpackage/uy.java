package defpackage;

import defpackage.qh0;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: ContentSecurityPolicy.java */
/* JADX INFO: loaded from: classes3.dex */
public final class uy extends hv2 {
    public static final t50[] o;
    public static final t50 p;
    public d20 b;
    public HashMap c;
    public HashMap d;
    public boolean e;
    public boolean f;
    public boolean g;
    public int h;
    public vy i;
    public boolean j;
    public String[] k;
    public int l;
    public f20 m;
    public String[] n;

    static {
        t50 t50Var = new t50(80, 0);
        o = new t50[]{t50Var};
        p = t50Var;
    }

    public uy(int i) {
        super(80);
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = 0;
        this.j = false;
        this.l = 0;
    }

    public static uy d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            uy uyVar = new uy(e70Var.c(o).b);
            uyVar.b = d20.d(e70Var.t(8, false));
            e70 e70VarT = e70Var.t(16, false);
            e70VarT.j();
            int[] iArrR = e70VarT.r(8, 0);
            int i = 0;
            while (true) {
                boolean z = true;
                if (i >= iArrR.length) {
                    e70 e70VarT2 = e70VarT.t(16, false);
                    int i2 = e70VarT2.k(iArrR.length).b;
                    String[] strArr = new String[i2];
                    for (int i3 = 0; i3 < i2; i3++) {
                        strArr[i3] = C0175b.g(i3, 8, 8, e70VarT2, false);
                    }
                    uyVar.c = new HashMap();
                    for (int i4 = 0; i4 < iArrR.length; i4++) {
                        uyVar.c.put(Integer.valueOf(iArrR[i4]), strArr[i4]);
                    }
                    e70 e70VarT3 = e70Var.t(24, false);
                    e70VarT3.j();
                    int[] iArrR2 = e70VarT3.r(8, 0);
                    for (int i5 = 0; i5 < iArrR2.length; i5++) {
                        int i6 = iArrR2[i5];
                        if (!(i6 >= 0 && i6 <= 29)) {
                            throw new q80("Invalid enum value.");
                        }
                    }
                    e70 e70VarT4 = e70VarT3.t(16, false);
                    int i7 = e70VarT4.k(iArrR2.length).b;
                    e20[] e20VarArr = new e20[i7];
                    for (int i8 = 0; i8 < i7; i8++) {
                        e20VarArr[i8] = e20.d(C0173a.g(i8, 8, 8, e70VarT4, false));
                    }
                    uyVar.d = new HashMap();
                    for (int i9 = 0; i9 < iArrR2.length; i9++) {
                        uyVar.d.put(Integer.valueOf(iArrR2[i9]), e20VarArr[i9]);
                    }
                    uyVar.e = e70Var.d(32, 0);
                    uyVar.f = e70Var.d(32, 1);
                    uyVar.g = e70Var.d(32, 2);
                    uyVar.j = e70Var.d(32, 3);
                    uyVar.h = e70Var.p(36);
                    uyVar.i = vy.d(e70Var.t(40, false));
                    e70 e70VarT5 = e70Var.t(48, false);
                    int i10 = e70VarT5.k(-1).b;
                    uyVar.k = new String[i10];
                    for (int i11 = 0; i11 < i10; i11++) {
                        uyVar.k[i11] = C0175b.g(i11, 8, 8, e70VarT5, false);
                    }
                    int iP = e70Var.p(56);
                    uyVar.l = iP;
                    if (!(iP >= 0 && iP <= 1)) {
                        throw new q80("Invalid enum value.");
                    }
                    uyVar.l = iP;
                    uyVar.m = f20.d(e70Var.t(64, true));
                    e70 e70VarT6 = e70Var.t(72, false);
                    int i12 = e70VarT6.k(-1).b;
                    uyVar.n = new String[i12];
                    for (int i13 = 0; i13 < i12; i13++) {
                        uyVar.n[i13] = C0175b.g(i13, 8, 8, e70VarT6, false);
                    }
                    return uyVar;
                }
                int i14 = iArrR[i];
                if (i14 < 0 || i14 > 29) {
                    z = false;
                }
                if (!z) {
                    throw new q80("Invalid enum value.");
                }
                i++;
            }
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(p);
        int i = 8;
        qh0VarT.k(this.b, 8, false);
        HashMap map = this.c;
        t50 t50Var = n3.f;
        int i2 = qh0VarT.a;
        qh0.a aVar = qh0VarT.b;
        boolean z = true;
        if (map == null) {
            qh0VarT.q(16, false);
        } else {
            qh0VarT.f(16, ((long) aVar.d) - ((long) (i2 + 16)));
            qh0 qh0VarT2 = qh0VarT.t(t50Var);
            int size = this.c.size();
            int[] iArr = new int[size];
            String[] strArr = new String[size];
            int i3 = 0;
            for (Map.Entry entry : this.c.entrySet()) {
                iArr[i3] = ((Integer) entry.getKey()).intValue();
                strArr[i3] = (String) entry.getValue();
                i3++;
            }
            qh0VarT2.o(iArr, 8, 0);
            qh0 qh0VarR = qh0VarT2.r(size, 16);
            int iF = 0;
            while (iF < size) {
                iF = C0175b.f(iF, 8, i, qh0VarR, strArr[iF], false, iF, 1);
                z = z;
                size = size;
                strArr = strArr;
                i = 8;
            }
        }
        boolean z2 = z;
        if (this.d == null) {
            qh0VarT.q(24, false);
        } else {
            qh0VarT.f(24, ((long) aVar.d) - ((long) (i2 + 24)));
            qh0 qh0VarT3 = qh0VarT.t(t50Var);
            int size2 = this.d.size();
            int[] iArr2 = new int[size2];
            e20[] e20VarArr = new e20[size2];
            int i4 = 0;
            for (Map.Entry entry2 : this.d.entrySet()) {
                iArr2[i4] = ((Integer) entry2.getKey()).intValue();
                e20VarArr[i4] = (e20) entry2.getValue();
                i4 += z2 ? 1 : 0;
            }
            int i5 = 8;
            qh0VarT3.o(iArr2, 8, 0);
            qh0 qh0VarR2 = qh0VarT3.r(size2, 16);
            int i6 = 0;
            while (i6 < size2) {
                qh0VarR2.k(e20VarArr[i6], (i6 * 8) + i5, false);
                i6++;
                i5 = 8;
            }
        }
        qh0VarT.d(32, 0, this.e);
        qh0VarT.d(32, z2 ? 1 : 0, this.f);
        qh0VarT.d(32, 2, this.g);
        qh0VarT.d(32, 3, this.j);
        qh0VarT.b(this.h, 36);
        qh0VarT.k(this.i, 40, false);
        String[] strArr2 = this.k;
        if (strArr2 != null) {
            qh0 qh0VarR3 = qh0VarT.r(strArr2.length, 48);
            int iF2 = 0;
            while (true) {
                String[] strArr3 = this.k;
                if (iF2 >= strArr3.length) {
                    break;
                }
                iF2 = C0175b.f(iF2, 8, 8, qh0VarR3, strArr3[iF2], false, iF2, 1);
            }
        } else {
            qh0VarT.q(48, false);
        }
        qh0VarT.b(this.l, 56);
        qh0VarT.k(this.m, 64, z2);
        String[] strArr4 = this.n;
        if (strArr4 == null) {
            qh0VarT.q(72, false);
            return;
        }
        qh0 qh0VarR4 = qh0VarT.r(strArr4.length, 72);
        int iF3 = 0;
        while (true) {
            String[] strArr5 = this.n;
            if (iF3 >= strArr5.length) {
                return;
            }
            iF3 = C0175b.f(iF3, 8, 8, qh0VarR4, strArr5[iF3], false, iF3, 1);
        }
    }
}
