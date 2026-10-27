package n2;

import android.util.Pair;
import e2.e0;
import e2.f0;
import h2.AbstractC1343A;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import u2.c0;

/* JADX INFO: loaded from: classes.dex */
public final class Z extends f0 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ int f19611n = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f19612e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final c0 f19613f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f19614g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f19615h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int[] f19616i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int[] f19617j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final f0[] f19618k;
    public final Object[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final HashMap f19619m;

    /* JADX WARN: Illegal instructions before constructor call */
    public Z(ArrayList arrayList, c0 c0Var) {
        f0[] f0VarArr = new f0[arrayList.size()];
        Iterator it = arrayList.iterator();
        int i7 = 0;
        int i10 = 0;
        while (it.hasNext()) {
            f0VarArr[i10] = ((L) it.next()).b();
            i10++;
        }
        Object[] objArr = new Object[arrayList.size()];
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            objArr[i7] = ((L) it2.next()).a();
            i7++;
        }
        this(f0VarArr, objArr, c0Var);
    }

    @Override // e2.f0
    public final int a(boolean z6) {
        if (this.f19612e == 0) {
            return -1;
        }
        int iQ = 0;
        if (z6) {
            int[] iArr = this.f19613f.f22804b;
            iQ = iArr.length > 0 ? iArr[0] : -1;
        }
        do {
            f0[] f0VarArr = this.f19618k;
            if (!f0VarArr[iQ].p()) {
                return f0VarArr[iQ].a(z6) + this.f19617j[iQ];
            }
            iQ = q(iQ, z6);
        } while (iQ != -1);
        return -1;
    }

    @Override // e2.f0
    public final int b(Object obj) {
        int iB;
        if (!(obj instanceof Pair)) {
            return -1;
        }
        Pair pair = (Pair) obj;
        Object obj2 = pair.first;
        Object obj3 = pair.second;
        Integer num = (Integer) this.f19619m.get(obj2);
        int iIntValue = num == null ? -1 : num.intValue();
        if (iIntValue == -1 || (iB = this.f19618k[iIntValue].b(obj3)) == -1) {
            return -1;
        }
        return this.f19616i[iIntValue] + iB;
    }

    @Override // e2.f0
    public final int c(boolean z6) {
        int iR;
        int i7 = this.f19612e;
        if (i7 == 0) {
            return -1;
        }
        if (z6) {
            int[] iArr = this.f19613f.f22804b;
            iR = iArr.length > 0 ? iArr[iArr.length - 1] : -1;
        } else {
            iR = i7 - 1;
        }
        do {
            f0[] f0VarArr = this.f19618k;
            if (!f0VarArr[iR].p()) {
                return f0VarArr[iR].c(z6) + this.f19617j[iR];
            }
            iR = r(iR, z6);
        } while (iR != -1);
        return -1;
    }

    @Override // e2.f0
    public final int e(int i7, int i10, boolean z6) {
        int[] iArr = this.f19617j;
        int iD = AbstractC1343A.d(iArr, i7 + 1, false, false);
        int i11 = iArr[iD];
        f0[] f0VarArr = this.f19618k;
        int iE = f0VarArr[iD].e(i7 - i11, i10 != 2 ? i10 : 0, z6);
        if (iE != -1) {
            return i11 + iE;
        }
        int iQ = q(iD, z6);
        while (iQ != -1 && f0VarArr[iQ].p()) {
            iQ = q(iQ, z6);
        }
        if (iQ != -1) {
            return f0VarArr[iQ].a(z6) + iArr[iQ];
        }
        if (i10 == 2) {
            return a(z6);
        }
        return -1;
    }

    @Override // e2.f0
    public final e2.c0 f(int i7, e2.c0 c0Var, boolean z6) {
        int[] iArr = this.f19616i;
        int iD = AbstractC1343A.d(iArr, i7 + 1, false, false);
        int i10 = this.f19617j[iD];
        this.f19618k[iD].f(i7 - iArr[iD], c0Var, z6);
        c0Var.c += i10;
        if (z6) {
            Object obj = this.l[iD];
            Object obj2 = c0Var.f14060b;
            obj2.getClass();
            c0Var.f14060b = Pair.create(obj, obj2);
        }
        return c0Var;
    }

    @Override // e2.f0
    public final e2.c0 g(Object obj, e2.c0 c0Var) {
        Pair pair = (Pair) obj;
        Object obj2 = pair.first;
        Object obj3 = pair.second;
        Integer num = (Integer) this.f19619m.get(obj2);
        int iIntValue = num == null ? -1 : num.intValue();
        int i7 = this.f19617j[iIntValue];
        this.f19618k[iIntValue].g(obj3, c0Var);
        c0Var.c += i7;
        c0Var.f14060b = obj;
        return c0Var;
    }

    @Override // e2.f0
    public final int h() {
        return this.f19615h;
    }

    @Override // e2.f0
    public final int k(int i7, int i10, boolean z6) {
        int[] iArr = this.f19617j;
        int iD = AbstractC1343A.d(iArr, i7 + 1, false, false);
        int i11 = iArr[iD];
        f0[] f0VarArr = this.f19618k;
        int iK = f0VarArr[iD].k(i7 - i11, i10 != 2 ? i10 : 0, z6);
        if (iK != -1) {
            return i11 + iK;
        }
        int iR = r(iD, z6);
        while (iR != -1 && f0VarArr[iR].p()) {
            iR = r(iR, z6);
        }
        if (iR != -1) {
            return f0VarArr[iR].c(z6) + iArr[iR];
        }
        if (i10 == 2) {
            return c(z6);
        }
        return -1;
    }

    @Override // e2.f0
    public final Object l(int i7) {
        int[] iArr = this.f19616i;
        int iD = AbstractC1343A.d(iArr, i7 + 1, false, false);
        return Pair.create(this.l[iD], this.f19618k[iD].l(i7 - iArr[iD]));
    }

    @Override // e2.f0
    public final e0 m(int i7, e0 e0Var, long j3) {
        int[] iArr = this.f19617j;
        int iD = AbstractC1343A.d(iArr, i7 + 1, false, false);
        int i10 = iArr[iD];
        int i11 = this.f19616i[iD];
        this.f19618k[iD].m(i7 - i10, e0Var, j3);
        Object objCreate = this.l[iD];
        if (!e0.f14075q.equals(e0Var.f14085a)) {
            objCreate = Pair.create(objCreate, e0Var.f14085a);
        }
        e0Var.f14085a = objCreate;
        e0Var.f14095n += i11;
        e0Var.o += i11;
        return e0Var;
    }

    @Override // e2.f0
    public final int o() {
        return this.f19614g;
    }

    public final int q(int i7, boolean z6) {
        if (!z6) {
            if (i7 < this.f19612e - 1) {
                return i7 + 1;
            }
            return -1;
        }
        c0 c0Var = this.f19613f;
        int i10 = c0Var.c[i7] + 1;
        int[] iArr = c0Var.f22804b;
        if (i10 < iArr.length) {
            return iArr[i10];
        }
        return -1;
    }

    public final int r(int i7, boolean z6) {
        if (!z6) {
            if (i7 > 0) {
                return i7 - 1;
            }
            return -1;
        }
        c0 c0Var = this.f19613f;
        int i10 = c0Var.c[i7] - 1;
        if (i10 >= 0) {
            return c0Var.f22804b[i10];
        }
        return -1;
    }

    public Z(f0[] f0VarArr, Object[] objArr, c0 c0Var) {
        this.f19613f = c0Var;
        this.f19612e = c0Var.f22804b.length;
        int length = f0VarArr.length;
        this.f19618k = f0VarArr;
        this.f19616i = new int[length];
        this.f19617j = new int[length];
        this.l = objArr;
        this.f19619m = new HashMap();
        int length2 = f0VarArr.length;
        int i7 = 0;
        int iO = 0;
        int iH = 0;
        int i10 = 0;
        while (i7 < length2) {
            f0 f0Var = f0VarArr[i7];
            this.f19618k[i10] = f0Var;
            this.f19617j[i10] = iO;
            this.f19616i[i10] = iH;
            iO += f0Var.o();
            iH += this.f19618k[i10].h();
            this.f19619m.put(objArr[i10], Integer.valueOf(i10));
            i7++;
            i10++;
        }
        this.f19614g = iO;
        this.f19615h = iH;
    }
}
