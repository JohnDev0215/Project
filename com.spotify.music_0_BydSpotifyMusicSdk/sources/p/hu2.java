package p;

import android.view.View;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class hu2 {
    public final /* synthetic */ int a;
    public Object b;
    public Object c;

    public hu2(int i) {
        this.a = i;
        if (i != 1) {
            this.b = new kd2();
            this.c = new wd1();
        }
    }

    public final void a(n22 n22Var) {
        gu2 gu2VarA = (gu2) ((kd2) this.b).get(n22Var);
        if (gu2VarA == null) {
            gu2VarA = gu2.a();
            ((kd2) this.b).put(n22Var, gu2VarA);
        }
        gu2VarA.a |= 1;
    }

    public final void b(n22 n22Var, u12 u12Var) {
        gu2 gu2VarA = (gu2) ((kd2) this.b).get(n22Var);
        if (gu2VarA == null) {
            gu2VarA = gu2.a();
            ((kd2) this.b).put(n22Var, gu2VarA);
        }
        gu2VarA.c = u12Var;
        gu2VarA.a |= 8;
    }

    public final void c(n22 n22Var, u12 u12Var) {
        gu2 gu2VarA = (gu2) ((kd2) this.b).get(n22Var);
        if (gu2VarA == null) {
            gu2VarA = gu2.a();
            ((kd2) this.b).put(n22Var, gu2VarA);
        }
        gu2VarA.b = u12Var;
        gu2VarA.a |= 4;
    }

    public final void d() {
        switch (this.a) {
            case 0:
                ((kd2) this.b).clear();
                ((wd1) this.c).a();
                break;
            default:
                int[] iArr = (int[]) this.b;
                if (iArr != null) {
                    Arrays.fill(iArr, -1);
                }
                this.c = null;
                break;
        }
    }

    public final void e(int i) {
        Object obj = this.b;
        if (((int[]) obj) == null) {
            int[] iArr = new int[Math.max(i, 10) + 1];
            this.b = iArr;
            Arrays.fill(iArr, -1);
        } else if (i >= ((int[]) obj).length) {
            int[] iArr2 = (int[]) obj;
            int length = ((int[]) obj).length;
            while (length <= i) {
                length *= 2;
            }
            int[] iArr3 = new int[length];
            this.b = iArr3;
            System.arraycopy(iArr2, 0, iArr3, 0, iArr2.length);
            Object obj2 = this.b;
            Arrays.fill((int[]) obj2, iArr2.length, ((int[]) obj2).length, -1);
        }
    }

    public final View f(int i, int i2, int i3, int i4) {
        int iA;
        int i5;
        int iB;
        View viewU;
        du duVar = (du) ((ct2) this.b);
        int i6 = duVar.c;
        Object obj = duVar.e;
        switch (i6) {
            case 0:
                iA = ((y12) obj).A();
                break;
            default:
                iA = ((y12) obj).C();
                break;
        }
        du duVar2 = (du) ((ct2) this.b);
        int i7 = duVar2.c;
        Object obj2 = duVar2.e;
        switch (i7) {
            case 0:
                y12 y12Var = (y12) obj2;
                i5 = y12Var.n;
                iB = y12Var.B();
                break;
            default:
                y12 y12Var2 = (y12) obj2;
                i5 = y12Var2.o;
                iB = y12Var2.z();
                break;
        }
        int i8 = i5 - iB;
        int i9 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            du duVar3 = (du) ((ct2) this.b);
            int i10 = duVar3.c;
            Object obj3 = duVar3.e;
            switch (i10) {
                case 0:
                    viewU = ((y12) obj3).u(i);
                    break;
                default:
                    viewU = ((y12) obj3).u(i);
                    break;
            }
            int iC = ((du) ((ct2) this.b)).c(viewU);
            int iB2 = ((du) ((ct2) this.b)).b(viewU);
            Object obj4 = this.c;
            bt2 bt2Var = (bt2) obj4;
            bt2Var.b = iA;
            bt2Var.c = i8;
            bt2Var.d = iC;
            bt2Var.e = iB2;
            if (i3 != 0) {
                ((bt2) obj4).a = i3;
                if (((bt2) obj4).a()) {
                    return viewU;
                }
            }
            if (i4 != 0) {
                Object obj5 = this.c;
                ((bt2) obj5).a = i4;
                if (((bt2) obj5).a()) {
                    view = viewU;
                }
            }
            i += i9;
        }
        return view;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int g(int r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.b
            int[] r0 = (int[]) r0
            r1 = -1
            if (r0 != 0) goto L8
            return r1
        L8:
            int r0 = r0.length
            if (r6 < r0) goto Lc
            return r1
        Lc:
            java.lang.Object r0 = r5.c
            r2 = r0
            java.util.List r2 = (java.util.List) r2
            if (r2 != 0) goto L15
        L13:
            r0 = r1
            goto L72
        L15:
            java.util.List r0 = (java.util.List) r0
            r2 = 0
            if (r0 != 0) goto L1b
            goto L36
        L1b:
            int r0 = r0.size()
            int r0 = r0 + (-1)
        L21:
            if (r0 < 0) goto L36
            java.lang.Object r3 = r5.c
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r3 = r3.get(r0)
            p.qf2 r3 = (p.qf2) r3
            int r4 = r3.c
            if (r4 != r6) goto L33
            r2 = r3
            goto L36
        L33:
            int r0 = r0 + (-1)
            goto L21
        L36:
            if (r2 == 0) goto L3f
            java.lang.Object r0 = r5.c
            java.util.List r0 = (java.util.List) r0
            r0.remove(r2)
        L3f:
            java.lang.Object r0 = r5.c
            java.util.List r0 = (java.util.List) r0
            int r0 = r0.size()
            r2 = 0
        L48:
            if (r2 >= r0) goto L5c
            java.lang.Object r3 = r5.c
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r3 = r3.get(r2)
            p.qf2 r3 = (p.qf2) r3
            int r3 = r3.c
            if (r3 < r6) goto L59
            goto L5d
        L59:
            int r2 = r2 + 1
            goto L48
        L5c:
            r2 = r1
        L5d:
            if (r2 == r1) goto L13
            java.lang.Object r0 = r5.c
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r0.get(r2)
            p.qf2 r0 = (p.qf2) r0
            java.lang.Object r3 = r5.c
            java.util.List r3 = (java.util.List) r3
            r3.remove(r2)
            int r0 = r0.c
        L72:
            if (r0 != r1) goto L82
            java.lang.Object r0 = r5.b
            int[] r0 = (int[]) r0
            int r2 = r0.length
            java.util.Arrays.fill(r0, r6, r2, r1)
            java.lang.Object r6 = r5.b
            int[] r6 = (int[]) r6
            int r6 = r6.length
            return r6
        L82:
            java.lang.Object r2 = r5.b
            int[] r2 = (int[]) r2
            int r0 = r0 + 1
            java.util.Arrays.fill(r2, r6, r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p.hu2.g(int):int");
    }

    public final boolean h(View view) {
        int iA;
        int i;
        int iB;
        bt2 bt2Var = (bt2) this.c;
        du duVar = (du) ((ct2) this.b);
        int i2 = duVar.c;
        Object obj = duVar.e;
        switch (i2) {
            case 0:
                iA = ((y12) obj).A();
                break;
            default:
                iA = ((y12) obj).C();
                break;
        }
        du duVar2 = (du) ((ct2) this.b);
        int i3 = duVar2.c;
        Object obj2 = duVar2.e;
        switch (i3) {
            case 0:
                y12 y12Var = (y12) obj2;
                i = y12Var.n;
                iB = y12Var.B();
                break;
            default:
                y12 y12Var2 = (y12) obj2;
                i = y12Var2.o;
                iB = y12Var2.z();
                break;
        }
        int i4 = i - iB;
        int iC = ((du) ((ct2) this.b)).c(view);
        int iB2 = ((du) ((ct2) this.b)).b(view);
        bt2Var.b = iA;
        bt2Var.c = i4;
        bt2Var.d = iC;
        bt2Var.e = iB2;
        Object obj3 = this.c;
        ((bt2) obj3).a = 24579;
        return ((bt2) obj3).a();
    }

    public final void i(int i, int i2) {
        int[] iArr = (int[]) this.b;
        if (iArr == null || i >= iArr.length) {
            return;
        }
        int i3 = i + i2;
        e(i3);
        int[] iArr2 = (int[]) this.b;
        System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
        Arrays.fill((int[]) this.b, i, i3, -1);
        List list = (List) this.c;
        if (list == null) {
            return;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            qf2 qf2Var = (qf2) ((List) this.c).get(size);
            int i4 = qf2Var.c;
            if (i4 >= i) {
                qf2Var.c = i4 + i2;
            }
        }
    }

    public final void j(int i, int i2) {
        int[] iArr = (int[]) this.b;
        if (iArr == null || i >= iArr.length) {
            return;
        }
        int i3 = i + i2;
        e(i3);
        int[] iArr2 = (int[]) this.b;
        System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
        int[] iArr3 = (int[]) this.b;
        Arrays.fill(iArr3, iArr3.length - i2, iArr3.length, -1);
        List list = (List) this.c;
        if (list == null) {
            return;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            qf2 qf2Var = (qf2) ((List) this.c).get(size);
            int i4 = qf2Var.c;
            if (i4 >= i) {
                if (i4 < i3) {
                    ((List) this.c).remove(size);
                } else {
                    qf2Var.c = i4 - i2;
                }
            }
        }
    }

    public final u12 k(n22 n22Var, int i) {
        gu2 gu2Var;
        u12 u12Var;
        int iD = ((kd2) this.b).d(n22Var);
        if (iD >= 0 && (gu2Var = (gu2) ((kd2) this.b).i(iD)) != null) {
            int i2 = gu2Var.a;
            if ((i2 & i) != 0) {
                int i3 = i2 & (~i);
                gu2Var.a = i3;
                if (i == 4) {
                    u12Var = gu2Var.b;
                } else {
                    if (i != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    u12Var = gu2Var.c;
                }
                if ((i3 & 12) == 0) {
                    ((kd2) this.b).g(iD);
                    gu2Var.a = 0;
                    gu2Var.b = null;
                    gu2Var.c = null;
                    gu2.d.b(gu2Var);
                }
                return u12Var;
            }
        }
        return null;
    }

    public final void l(n22 n22Var) {
        gu2 gu2Var = (gu2) ((kd2) this.b).get(n22Var);
        if (gu2Var == null) {
            return;
        }
        gu2Var.a &= -2;
    }

    public final void m(n22 n22Var) {
        int iH = ((wd1) this.c).h() - 1;
        while (true) {
            if (iH < 0) {
                break;
            }
            if (n22Var == ((wd1) this.c).j(iH)) {
                wd1 wd1Var = (wd1) this.c;
                Object[] objArr = wd1Var.k;
                Object obj = objArr[iH];
                Object obj2 = xd1.a;
                if (obj != obj2) {
                    objArr[iH] = obj2;
                    wd1Var.c = true;
                }
            } else {
                iH--;
            }
        }
        gu2 gu2Var = (gu2) ((kd2) this.b).remove(n22Var);
        if (gu2Var != null) {
            gu2Var.a = 0;
            gu2Var.b = null;
            gu2Var.c = null;
            gu2.d.b(gu2Var);
        }
    }

    public hu2(du duVar) {
        this.a = 2;
        this.b = duVar;
        bt2 bt2Var = new bt2();
        bt2Var.a = 0;
        this.c = bt2Var;
    }
}
