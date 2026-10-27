package defpackage;

import defpackage.p8;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: compiled from: PriorityGoalRow.java */
/* JADX INFO: loaded from: classes.dex */
public final class m42 extends p8 {
    public hq2[] f;
    public hq2[] g;
    public int h;
    public final b i;

    /* JADX INFO: compiled from: PriorityGoalRow.java */
    public class a implements Comparator<hq2> {
        @Override // java.util.Comparator
        public final int compare(hq2 hq2Var, hq2 hq2Var2) {
            return hq2Var.f - hq2Var2.f;
        }
    }

    /* JADX INFO: compiled from: PriorityGoalRow.java */
    public class b {
        public hq2 a;

        public b() {
        }

        public final String toString() {
            String string = "[ ";
            if (this.a != null) {
                for (int i = 0; i < 9; i++) {
                    StringBuilder sbJ = C0173a.j(string);
                    sbJ.append(this.a.l[i]);
                    sbJ.append(" ");
                    string = sbJ.toString();
                }
            }
            StringBuilder sbK = C0173a.k(string, "] ");
            sbK.append(this.a);
            return sbK.toString();
        }
    }

    public m42(pk pkVar) {
        super(pkVar);
        this.f = new hq2[128];
        this.g = new hq2[128];
        this.h = 0;
        this.i = new b();
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0050, code lost:
    
        r5 = false;
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0053  */
    @Override // defpackage.p8, q71.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final defpackage.hq2 a(boolean[] r12) {
        /*
            r11 = this;
            r0 = -1
            r1 = 0
            r3 = r0
            r2 = r1
        L4:
            int r4 = r11.h
            if (r2 >= r4) goto L57
            hq2[] r4 = r11.f
            r5 = r4[r2]
            int r6 = r5.f
            boolean r6 = r12[r6]
            if (r6 == 0) goto L13
            goto L54
        L13:
            m42$b r6 = r11.i
            r6.a = r5
            r5 = 1
            r7 = 8
            if (r3 != r0) goto L36
        L1c:
            if (r7 < 0) goto L32
            hq2 r4 = r6.a
            float[] r4 = r4.l
            r4 = r4[r7]
            r8 = 0
            int r9 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r9 <= 0) goto L2a
            goto L32
        L2a:
            int r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r4 >= 0) goto L2f
            goto L33
        L2f:
            int r7 = r7 + (-1)
            goto L1c
        L32:
            r5 = r1
        L33:
            if (r5 == 0) goto L54
            goto L53
        L36:
            r4 = r4[r3]
        L38:
            if (r7 < 0) goto L50
            float[] r8 = r4.l
            r8 = r8[r7]
            hq2 r9 = r6.a
            float[] r9 = r9.l
            r9 = r9[r7]
            int r10 = (r9 > r8 ? 1 : (r9 == r8 ? 0 : -1))
            if (r10 != 0) goto L4b
            int r7 = r7 + (-1)
            goto L38
        L4b:
            int r4 = (r9 > r8 ? 1 : (r9 == r8 ? 0 : -1))
            if (r4 >= 0) goto L50
            goto L51
        L50:
            r5 = r1
        L51:
            if (r5 == 0) goto L54
        L53:
            r3 = r2
        L54:
            int r2 = r2 + 1
            goto L4
        L57:
            if (r3 != r0) goto L5b
            r11 = 0
            return r11
        L5b:
            hq2[] r11 = r11.f
            r11 = r11[r3]
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.m42.a(boolean[]):hq2");
    }

    @Override // defpackage.p8
    public final boolean e() {
        return this.h == 0;
    }

    @Override // defpackage.p8
    public final void i(q71 q71Var, p8 p8Var, boolean z) {
        boolean z2;
        hq2 hq2Var = p8Var.a;
        if (hq2Var == null) {
            return;
        }
        p8.a aVar = p8Var.d;
        int iC = aVar.c();
        for (int i = 0; i < iC; i++) {
            hq2 hq2VarD = aVar.d(i);
            float fG = aVar.g(i);
            b bVar = this.i;
            bVar.a = hq2VarD;
            boolean z3 = hq2VarD.e;
            float[] fArr = hq2Var.l;
            if (z3) {
                boolean z4 = true;
                for (int i2 = 0; i2 < 9; i2++) {
                    float[] fArr2 = bVar.a.l;
                    float f = (fArr[i2] * fG) + fArr2[i2];
                    fArr2[i2] = f;
                    if (Math.abs(f) < 1.0E-4f) {
                        bVar.a.l[i2] = 0.0f;
                    } else {
                        z4 = false;
                    }
                }
                if (z4) {
                    m42.this.k(bVar.a);
                }
                z2 = false;
            } else {
                for (int i3 = 0; i3 < 9; i3++) {
                    float f2 = fArr[i3];
                    if (f2 != 0.0f) {
                        float f3 = f2 * fG;
                        if (Math.abs(f3) < 1.0E-4f) {
                            f3 = 0.0f;
                        }
                        bVar.a.l[i3] = f3;
                    } else {
                        bVar.a.l[i3] = 0.0f;
                    }
                }
                z2 = true;
            }
            if (z2) {
                j(hq2VarD);
            }
            this.b = (p8Var.b * fG) + this.b;
        }
        k(hq2Var);
    }

    public final void j(hq2 hq2Var) {
        int i;
        int i2 = this.h + 1;
        hq2[] hq2VarArr = this.f;
        if (i2 > hq2VarArr.length) {
            hq2[] hq2VarArr2 = (hq2[]) Arrays.copyOf(hq2VarArr, hq2VarArr.length * 2);
            this.f = hq2VarArr2;
            this.g = (hq2[]) Arrays.copyOf(hq2VarArr2, hq2VarArr2.length * 2);
        }
        hq2[] hq2VarArr3 = this.f;
        int i3 = this.h;
        hq2VarArr3[i3] = hq2Var;
        int i4 = i3 + 1;
        this.h = i4;
        if (i4 > 1 && hq2VarArr3[i4 - 1].f > hq2Var.f) {
            int i5 = 0;
            while (true) {
                i = this.h;
                if (i5 >= i) {
                    break;
                }
                this.g[i5] = this.f[i5];
                i5++;
            }
            Arrays.sort(this.g, 0, i, new a());
            for (int i6 = 0; i6 < this.h; i6++) {
                this.f[i6] = this.g[i6];
            }
        }
        hq2Var.e = true;
        hq2Var.a(this);
    }

    public final void k(hq2 hq2Var) {
        int i = 0;
        while (i < this.h) {
            if (this.f[i] == hq2Var) {
                while (true) {
                    int i2 = this.h;
                    if (i >= i2 - 1) {
                        this.h = i2 - 1;
                        hq2Var.e = false;
                        return;
                    } else {
                        hq2[] hq2VarArr = this.f;
                        int i3 = i + 1;
                        hq2VarArr[i] = hq2VarArr[i3];
                        i = i3;
                    }
                }
            } else {
                i++;
            }
        }
    }

    @Override // defpackage.p8
    public final String toString() {
        String str = " goal -> (" + this.b + ") : ";
        for (int i = 0; i < this.h; i++) {
            hq2 hq2Var = this.f[i];
            b bVar = this.i;
            bVar.a = hq2Var;
            str = str + bVar + " ";
        }
        return str;
    }
}
