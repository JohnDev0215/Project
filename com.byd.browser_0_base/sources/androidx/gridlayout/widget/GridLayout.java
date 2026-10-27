package androidx.gridlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.util.LogPrinter;
import android.util.Pair;
import android.util.Printer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import androidx.gridlayout.R$dimen;
import androidx.gridlayout.R$styleable;
import defpackage.gq2;
import defpackage.in3;
import defpackage.om3;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class GridLayout extends ViewGroup {
    public static final androidx.gridlayout.widget.a A;
    public static final androidx.gridlayout.widget.a B;
    public static final e C;
    public static final f D;
    public static final g E;
    public static final LogPrinter m = new LogPrinter(3, GridLayout.class.getName());
    public static final a n = new a();
    public static final int o = R$styleable.GridLayout_orientation;
    public static final int p = R$styleable.GridLayout_rowCount;
    public static final int q = R$styleable.GridLayout_columnCount;
    public static final int r = R$styleable.GridLayout_useDefaultMargins;
    public static final int s = R$styleable.GridLayout_alignmentMode;
    public static final int t = R$styleable.GridLayout_rowOrderPreserved;
    public static final int u = R$styleable.GridLayout_columnOrderPreserved;
    public static final b v = new b();
    public static final c w;
    public static final d x;
    public static final c y;
    public static final d z;
    public final k e;
    public final k f;
    public int g;
    public boolean h;
    public int i;
    public final int j;
    public int k;
    public Printer l;

    public class b extends h {
        @Override // androidx.gridlayout.widget.GridLayout.h
        public final int a(View view, int i, int i2) {
            return Integer.MIN_VALUE;
        }

        @Override // androidx.gridlayout.widget.GridLayout.h
        public final String c() {
            return "UNDEFINED";
        }

        @Override // androidx.gridlayout.widget.GridLayout.h
        public final int d(View view, int i) {
            return Integer.MIN_VALUE;
        }
    }

    public class c extends h {
        @Override // androidx.gridlayout.widget.GridLayout.h
        public final int a(View view, int i, int i2) {
            return 0;
        }

        @Override // androidx.gridlayout.widget.GridLayout.h
        public final String c() {
            return "LEADING";
        }

        @Override // androidx.gridlayout.widget.GridLayout.h
        public final int d(View view, int i) {
            return 0;
        }
    }

    public class e extends h {
        @Override // androidx.gridlayout.widget.GridLayout.h
        public final int a(View view, int i, int i2) {
            return i >> 1;
        }

        @Override // androidx.gridlayout.widget.GridLayout.h
        public final String c() {
            return "CENTER";
        }

        @Override // androidx.gridlayout.widget.GridLayout.h
        public final int d(View view, int i) {
            return i >> 1;
        }
    }

    public class f extends h {

        public class a extends l {
            public int d;

            @Override // androidx.gridlayout.widget.GridLayout.l
            public final int a(GridLayout gridLayout, View view, h hVar, int i, boolean z) {
                return Math.max(0, super.a(gridLayout, view, hVar, i, z));
            }

            @Override // androidx.gridlayout.widget.GridLayout.l
            public final void b(int i, int i2) {
                super.b(i, i2);
                this.d = Math.max(this.d, i + i2);
            }

            @Override // androidx.gridlayout.widget.GridLayout.l
            public final void c() {
                super.c();
                this.d = Integer.MIN_VALUE;
            }

            @Override // androidx.gridlayout.widget.GridLayout.l
            public final int d(boolean z) {
                return Math.max(super.d(z), this.d);
            }
        }

        @Override // androidx.gridlayout.widget.GridLayout.h
        public final int a(View view, int i, int i2) {
            if (view.getVisibility() == 8) {
                return 0;
            }
            int baseline = view.getBaseline();
            if (baseline == -1) {
                return Integer.MIN_VALUE;
            }
            return baseline;
        }

        @Override // androidx.gridlayout.widget.GridLayout.h
        public final l b() {
            return new a();
        }

        @Override // androidx.gridlayout.widget.GridLayout.h
        public final String c() {
            return "BASELINE";
        }

        @Override // androidx.gridlayout.widget.GridLayout.h
        public final int d(View view, int i) {
            return 0;
        }
    }

    public static final class i {
        public final m a;
        public final n b;
        public boolean c = true;

        public i(m mVar, n nVar) {
            this.a = mVar;
            this.b = nVar;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.a);
            sb.append(" ");
            sb.append(!this.c ? "+>" : "->");
            sb.append(" ");
            sb.append(this.b);
            return sb.toString();
        }
    }

    public static final class j<K, V> extends ArrayList<Pair<K, V>> {
        public final Class<K> e;
        public final Class<V> f;

        public j(Class<K> cls, Class<V> cls2) {
            this.e = cls;
            this.f = cls2;
        }

        public final o<K, V> b() {
            int size = size();
            Object[] objArr = (Object[]) Array.newInstance((Class<?>) this.e, size);
            Object[] objArr2 = (Object[]) Array.newInstance((Class<?>) this.f, size);
            for (int i = 0; i < size; i++) {
                objArr[i] = get(i).first;
                objArr2[i] = get(i).second;
            }
            return new o<>(objArr, objArr2);
        }
    }

    public final class k {
        public final boolean a;
        public o<p, l> d;
        public o<m, n> f;
        public o<m, n> h;
        public int[] j;
        public int[] l;
        public i[] n;
        public int[] p;
        public boolean r;
        public int[] t;
        public int b = Integer.MIN_VALUE;
        public int c = Integer.MIN_VALUE;
        public boolean e = false;
        public boolean g = false;
        public boolean i = false;
        public boolean k = false;
        public boolean m = false;
        public boolean o = false;
        public boolean q = false;
        public boolean s = false;
        public boolean u = true;
        public final n v = new n(0);
        public final n w = new n(-100000);

        public k(boolean z) {
            this.a = z;
        }

        public static void k(ArrayList arrayList, m mVar, n nVar, boolean z) {
            if (mVar.b - mVar.a == 0) {
                return;
            }
            if (z) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    if (((i) it.next()).a.equals(mVar)) {
                        return;
                    }
                }
            }
            arrayList.add(new i(mVar, nVar));
        }

        public final String a(ArrayList arrayList) {
            String str;
            String str2 = this.a ? "x" : "y";
            StringBuilder sb = new StringBuilder();
            Iterator it = arrayList.iterator();
            boolean z = true;
            while (it.hasNext()) {
                i iVar = (i) it.next();
                if (z) {
                    z = false;
                } else {
                    sb.append(", ");
                }
                m mVar = iVar.a;
                int i = mVar.a;
                int i2 = iVar.b.a;
                int i3 = mVar.b;
                if (i < i3) {
                    str = str2 + i3 + "-" + str2 + i + ">=" + i2;
                } else {
                    str = str2 + i + "-" + str2 + i3 + "<=" + (-i2);
                }
                sb.append(str);
            }
            return sb.toString();
        }

        public final void b(o<m, n> oVar, boolean z) {
            for (n nVar : oVar.c) {
                nVar.a = Integer.MIN_VALUE;
            }
            l[] lVarArr = g().c;
            for (int i = 0; i < lVarArr.length; i++) {
                int iD = lVarArr[i].d(z);
                n nVar2 = oVar.c[oVar.a[i]];
                int i2 = nVar2.a;
                if (!z) {
                    iD = -iD;
                }
                nVar2.a = Math.max(i2, iD);
            }
        }

        public final void c(boolean z) {
            int[] iArr = z ? this.j : this.l;
            GridLayout gridLayout = GridLayout.this;
            int childCount = gridLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = gridLayout.getChildAt(i);
                if (childAt.getVisibility() != 8) {
                    gridLayout.getClass();
                    LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                    boolean z2 = this.a;
                    m mVar = (z2 ? layoutParams.b : layoutParams.a).b;
                    int i2 = z ? mVar.a : mVar.b;
                    iArr[i2] = Math.max(iArr[i2], gridLayout.f(childAt, z2, z));
                }
            }
        }

        public final o<m, n> d(boolean z) {
            m mVar;
            j jVar = new j(m.class, n.class);
            p[] pVarArr = g().b;
            int length = pVarArr.length;
            for (int i = 0; i < length; i++) {
                if (z) {
                    mVar = pVarArr[i].b;
                } else {
                    m mVar2 = pVarArr[i].b;
                    mVar = new m(mVar2.b, mVar2.a);
                }
                jVar.add(Pair.create(mVar, new n()));
            }
            return jVar.b();
        }

        public final i[] e() {
            if (this.n == null) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                if (this.f == null) {
                    this.f = d(true);
                }
                if (!this.g) {
                    b(this.f, true);
                    this.g = true;
                }
                o<m, n> oVar = this.f;
                int i = 0;
                while (true) {
                    m[] mVarArr = oVar.b;
                    if (i >= mVarArr.length) {
                        break;
                    }
                    k(arrayList, mVarArr[i], oVar.c[i], false);
                    i++;
                }
                if (this.h == null) {
                    this.h = d(false);
                }
                if (!this.i) {
                    b(this.h, false);
                    this.i = true;
                }
                o<m, n> oVar2 = this.h;
                int i2 = 0;
                while (true) {
                    m[] mVarArr2 = oVar2.b;
                    if (i2 >= mVarArr2.length) {
                        break;
                    }
                    k(arrayList2, mVarArr2[i2], oVar2.c[i2], false);
                    i2++;
                }
                if (this.u) {
                    int i3 = 0;
                    while (i3 < f()) {
                        int i4 = i3 + 1;
                        k(arrayList, new m(i3, i4), new n(0), true);
                        i3 = i4;
                    }
                }
                int iF = f();
                k(arrayList, new m(0, iF), this.v, false);
                k(arrayList2, new m(iF, 0), this.w, false);
                i[] iVarArrQ = q(arrayList);
                i[] iVarArrQ2 = q(arrayList2);
                LogPrinter logPrinter = GridLayout.m;
                Object[] objArr = (Object[]) Array.newInstance(iVarArrQ.getClass().getComponentType(), iVarArrQ.length + iVarArrQ2.length);
                System.arraycopy(iVarArrQ, 0, objArr, 0, iVarArrQ.length);
                System.arraycopy(iVarArrQ2, 0, objArr, iVarArrQ.length, iVarArrQ2.length);
                this.n = (i[]) objArr;
            }
            if (!this.o) {
                if (this.f == null) {
                    this.f = d(true);
                }
                if (!this.g) {
                    b(this.f, true);
                    this.g = true;
                }
                if (this.h == null) {
                    this.h = d(false);
                }
                if (!this.i) {
                    b(this.h, false);
                    this.i = true;
                }
                this.o = true;
            }
            return this.n;
        }

        public final int f() {
            return Math.max(this.b, i());
        }

        public final o<p, l> g() {
            int iE;
            int i;
            o<p, l> oVar = this.d;
            boolean z = this.a;
            GridLayout gridLayout = GridLayout.this;
            if (oVar == null) {
                j jVar = new j(p.class, l.class);
                int childCount = gridLayout.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = gridLayout.getChildAt(i2);
                    LogPrinter logPrinter = GridLayout.m;
                    LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                    p pVar = z ? layoutParams.b : layoutParams.a;
                    jVar.add(Pair.create(pVar, pVar.a(z).b()));
                }
                this.d = jVar.b();
            }
            if (!this.e) {
                for (l lVar : this.d.c) {
                    lVar.c();
                }
                int childCount2 = gridLayout.getChildCount();
                for (int i3 = 0; i3 < childCount2; i3++) {
                    View childAt2 = gridLayout.getChildAt(i3);
                    LogPrinter logPrinter2 = GridLayout.m;
                    LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                    p pVar2 = z ? layoutParams2.b : layoutParams2.a;
                    if (childAt2.getVisibility() == 8) {
                        iE = 0;
                    } else {
                        iE = gridLayout.e(childAt2, z, false) + gridLayout.e(childAt2, z, true) + (z ? childAt2.getMeasuredWidth() : childAt2.getMeasuredHeight());
                    }
                    if (pVar2.d == 0.0f) {
                        i = 0;
                    } else {
                        if (this.t == null) {
                            this.t = new int[gridLayout.getChildCount()];
                        }
                        i = this.t[i3];
                    }
                    int i4 = iE + i;
                    o<p, l> oVar2 = this.d;
                    l lVar2 = oVar2.c[oVar2.a[i3]];
                    lVar2.c = ((pVar2.c == GridLayout.v && pVar2.d == 0.0f) ? 0 : 2) & lVar2.c;
                    int iA = pVar2.a(z).a(childAt2, i4, gridLayout.getLayoutMode());
                    lVar2.b(iA, i4 - iA);
                }
                this.e = true;
            }
            return this.d;
        }

        public final int[] h() {
            boolean z;
            if (this.p == null) {
                this.p = new int[f() + 1];
            }
            if (!this.q) {
                int[] iArr = this.p;
                boolean z2 = this.s;
                GridLayout gridLayout = GridLayout.this;
                float f = 0.0f;
                boolean z3 = this.a;
                if (!z2) {
                    int childCount = gridLayout.getChildCount();
                    int i = 0;
                    while (true) {
                        if (i >= childCount) {
                            z = false;
                            break;
                        }
                        View childAt = gridLayout.getChildAt(i);
                        if (childAt.getVisibility() != 8) {
                            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                            if ((z3 ? layoutParams.b : layoutParams.a).d != 0.0f) {
                                z = true;
                                break;
                            }
                        }
                        i++;
                    }
                    this.r = z;
                    this.s = true;
                }
                if (this.r) {
                    if (this.t == null) {
                        this.t = new int[gridLayout.getChildCount()];
                    }
                    Arrays.fill(this.t, 0);
                    p(e(), iArr, true);
                    int childCount2 = (gridLayout.getChildCount() * this.v.a) + 1;
                    if (childCount2 >= 2) {
                        int childCount3 = gridLayout.getChildCount();
                        for (int i2 = 0; i2 < childCount3; i2++) {
                            View childAt2 = gridLayout.getChildAt(i2);
                            if (childAt2.getVisibility() != 8) {
                                LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                                f += (z3 ? layoutParams2.b : layoutParams2.a).d;
                            }
                        }
                        int i3 = -1;
                        boolean z4 = true;
                        int i4 = 0;
                        while (i4 < childCount2) {
                            int i5 = (int) ((((long) i4) + ((long) childCount2)) / 2);
                            m();
                            o(f, i5);
                            boolean zP = p(e(), iArr, false);
                            if (zP) {
                                i4 = i5 + 1;
                                i3 = i5;
                            } else {
                                childCount2 = i5;
                            }
                            z4 = zP;
                        }
                        if (i3 > 0 && !z4) {
                            m();
                            o(f, i3);
                            p(e(), iArr, true);
                        }
                    }
                } else {
                    p(e(), iArr, true);
                }
                if (!this.u) {
                    int i6 = iArr[0];
                    int length = iArr.length;
                    for (int i7 = 0; i7 < length; i7++) {
                        iArr[i7] = iArr[i7] - i6;
                    }
                }
                this.q = true;
            }
            return this.p;
        }

        public final int i() {
            if (this.c == Integer.MIN_VALUE) {
                GridLayout gridLayout = GridLayout.this;
                int childCount = gridLayout.getChildCount();
                int iMax = -1;
                for (int i = 0; i < childCount; i++) {
                    View childAt = gridLayout.getChildAt(i);
                    LogPrinter logPrinter = GridLayout.m;
                    LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                    m mVar = (this.a ? layoutParams.b : layoutParams.a).b;
                    int iMax2 = Math.max(iMax, mVar.a);
                    int i2 = mVar.b;
                    iMax = Math.max(Math.max(iMax2, i2), i2 - mVar.a);
                }
                this.c = Math.max(0, iMax != -1 ? iMax : Integer.MIN_VALUE);
            }
            return this.c;
        }

        public final int j(int i) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            n nVar = this.w;
            n nVar2 = this.v;
            if (mode == Integer.MIN_VALUE) {
                nVar2.a = 0;
                nVar.a = -size;
                this.q = false;
                return h()[f()];
            }
            if (mode == 0) {
                nVar2.a = 0;
                nVar.a = -100000;
                this.q = false;
                return h()[f()];
            }
            if (mode != 1073741824) {
                return 0;
            }
            nVar2.a = size;
            nVar.a = -size;
            this.q = false;
            return h()[f()];
        }

        public final void l() {
            this.c = Integer.MIN_VALUE;
            this.d = null;
            this.f = null;
            this.h = null;
            this.j = null;
            this.l = null;
            this.n = null;
            this.p = null;
            this.t = null;
            this.s = false;
            m();
        }

        public final void m() {
            this.e = false;
            this.g = false;
            this.i = false;
            this.k = false;
            this.m = false;
            this.o = false;
            this.q = false;
        }

        public final void n(int i) {
            if (i == Integer.MIN_VALUE || i >= i()) {
                this.b = i;
            } else {
                GridLayout.g((this.a ? "column" : "row").concat("Count must be greater than or equal to the maximum of all grid indices (and spans) defined in the LayoutParams of each child"));
                throw null;
            }
        }

        public final void o(float f, int i) {
            Arrays.fill(this.t, 0);
            GridLayout gridLayout = GridLayout.this;
            int childCount = gridLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = gridLayout.getChildAt(i2);
                if (childAt.getVisibility() != 8) {
                    gridLayout.getClass();
                    LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                    float f2 = (this.a ? layoutParams.b : layoutParams.a).d;
                    if (f2 != 0.0f) {
                        int iRound = Math.round((i * f2) / f);
                        this.t[i2] = iRound;
                        i -= iRound;
                        f -= f2;
                    }
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0043  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x00c4  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean p(androidx.gridlayout.widget.GridLayout.i[] r17, int[] r18, boolean r19) {
            /*
                Method dump skipped, instruction units count: 240
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.gridlayout.widget.GridLayout.k.p(androidx.gridlayout.widget.GridLayout$i[], int[], boolean):boolean");
        }

        public final i[] q(ArrayList arrayList) {
            androidx.gridlayout.widget.b bVar = new androidx.gridlayout.widget.b(this, (i[]) arrayList.toArray(new i[arrayList.size()]));
            int length = bVar.c.length;
            for (int i = 0; i < length; i++) {
                bVar.a(i);
            }
            return bVar.a;
        }
    }

    public static class l {
        public int a;
        public int b;
        public int c;

        public l() {
            c();
        }

        public int a(GridLayout gridLayout, View view, h hVar, int i, boolean z) {
            return this.a - hVar.a(view, i, gridLayout.getLayoutMode());
        }

        public void b(int i, int i2) {
            this.a = Math.max(this.a, i);
            this.b = Math.max(this.b, i2);
        }

        public void c() {
            this.a = Integer.MIN_VALUE;
            this.b = Integer.MIN_VALUE;
            this.c = 2;
        }

        public int d(boolean z) {
            if (!z) {
                int i = this.c;
                LogPrinter logPrinter = GridLayout.m;
                if ((i & 2) != 0) {
                    return 100000;
                }
            }
            return this.a + this.b;
        }

        public final String toString() {
            return "Bounds{before=" + this.a + ", after=" + this.b + '}';
        }
    }

    public static final class m {
        public final int a;
        public final int b;

        public m(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || m.class != obj.getClass()) {
                return false;
            }
            m mVar = (m) obj;
            return this.b == mVar.b && this.a == mVar.a;
        }

        public final int hashCode() {
            return (this.a * 31) + this.b;
        }

        public final String toString() {
            return "[" + this.a + ", " + this.b + "]";
        }
    }

    public static final class o<K, V> {
        public final int[] a;
        public final K[] b;
        public final V[] c;

        public o(K[] kArr, V[] vArr) {
            int length = kArr.length;
            int[] iArr = new int[length];
            HashMap map = new HashMap();
            for (int i = 0; i < length; i++) {
                K k = kArr[i];
                Integer numValueOf = (Integer) map.get(k);
                if (numValueOf == null) {
                    numValueOf = Integer.valueOf(map.size());
                    map.put(k, numValueOf);
                }
                iArr[i] = numValueOf.intValue();
            }
            this.a = iArr;
            this.b = (K[]) a(kArr, iArr);
            this.c = (V[]) a(vArr, iArr);
        }

        public static <K> K[] a(K[] kArr, int[] iArr) {
            int length = kArr.length;
            Class<?> componentType = kArr.getClass().getComponentType();
            LogPrinter logPrinter = GridLayout.m;
            int iMax = -1;
            for (int i : iArr) {
                iMax = Math.max(iMax, i);
            }
            K[] kArr2 = (K[]) ((Object[]) Array.newInstance(componentType, iMax + 1));
            for (int i2 = 0; i2 < length; i2++) {
                kArr2[iArr[i2]] = kArr[i2];
            }
            return kArr2;
        }
    }

    public static class p {
        public static final p e = GridLayout.l(Integer.MIN_VALUE, 1, GridLayout.v, 0.0f);
        public final boolean a;
        public final m b;
        public final h c;
        public final float d;

        public p(boolean z, m mVar, h hVar, float f) {
            this.a = z;
            this.b = mVar;
            this.c = hVar;
            this.d = f;
        }

        public final h a(boolean z) {
            b bVar = GridLayout.v;
            h hVar = this.c;
            return hVar != bVar ? hVar : this.d == 0.0f ? z ? GridLayout.y : GridLayout.D : GridLayout.E;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof p)) {
                return false;
            }
            p pVar = (p) obj;
            return this.c.equals(pVar.c) && this.b.equals(pVar.b);
        }

        public final int hashCode() {
            return this.c.hashCode() + (this.b.hashCode() * 31);
        }
    }

    static {
        c cVar = new c();
        d dVar = new d();
        w = cVar;
        x = dVar;
        y = cVar;
        z = dVar;
        A = new androidx.gridlayout.widget.a(cVar, dVar);
        B = new androidx.gridlayout.widget.a(dVar, cVar);
        C = new e();
        D = new f();
        E = new g();
    }

    public GridLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.e = new k(true);
        this.f = new k(false);
        this.g = 0;
        this.h = false;
        this.i = 1;
        this.k = 0;
        this.l = m;
        this.j = context.getResources().getDimensionPixelOffset(R$dimen.default_gap);
        int[] iArr = R$styleable.GridLayout;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.n.b(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i2, 0);
        try {
            setRowCount(typedArrayObtainStyledAttributes.getInt(p, Integer.MIN_VALUE));
            setColumnCount(typedArrayObtainStyledAttributes.getInt(q, Integer.MIN_VALUE));
            setOrientation(typedArrayObtainStyledAttributes.getInt(o, 0));
            setUseDefaultMargins(typedArrayObtainStyledAttributes.getBoolean(r, false));
            setAlignmentMode(typedArrayObtainStyledAttributes.getInt(s, 1));
            setRowOrderPreserved(typedArrayObtainStyledAttributes.getBoolean(t, true));
            setColumnOrderPreserved(typedArrayObtainStyledAttributes.getBoolean(u, true));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static h d(int i2, boolean z2) {
        int i3 = (i2 & (z2 ? 7 : 112)) >> (z2 ? 0 : 4);
        return i3 != 1 ? i3 != 3 ? i3 != 5 ? i3 != 7 ? i3 != 8388611 ? i3 != 8388613 ? v : z : y : E : z2 ? B : x : z2 ? A : w : C;
    }

    public static void g(String str) {
        throw new IllegalArgumentException(gq2.f(str, ". "));
    }

    public static void k(LayoutParams layoutParams, int i2, int i3, int i4, int i5) {
        m mVar = new m(i2, i3 + i2);
        p pVar = layoutParams.a;
        layoutParams.a = new p(pVar.a, mVar, pVar.c, pVar.d);
        m mVar2 = new m(i4, i5 + i4);
        p pVar2 = layoutParams.b;
        layoutParams.b = new p(pVar2.a, mVar2, pVar2.c, pVar2.d);
    }

    public static p l(int i2, int i3, h hVar, float f2) {
        return new p(i2 != Integer.MIN_VALUE, new m(i2, i3 + i2), hVar, f2);
    }

    public final void a(LayoutParams layoutParams, boolean z2) {
        String str = z2 ? "column" : "row";
        m mVar = (z2 ? layoutParams.b : layoutParams.a).b;
        int i2 = mVar.a;
        if (i2 != Integer.MIN_VALUE && i2 < 0) {
            g(str.concat(" indices must be positive"));
            throw null;
        }
        int i3 = (z2 ? this.e : this.f).b;
        if (i3 != Integer.MIN_VALUE) {
            int i4 = mVar.b;
            if (i4 > i3) {
                g(str + " indices (start + span) mustn't exceed the " + str + " count");
                throw null;
            }
            if (i4 - i2 <= i3) {
                return;
            }
            g(str + " span mustn't exceed the " + str + " count");
            throw null;
        }
    }

    public final int b() {
        int childCount = getChildCount();
        int iHashCode = 1;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                iHashCode = ((LayoutParams) childAt.getLayoutParams()).hashCode() + (iHashCode * 31);
            }
        }
        return iHashCode;
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0092 A[EDGE_INSN: B:68:0x0092->B:55:0x0092 BREAK  A[LOOP:1: B:39:0x0070->B:73:0x0070], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c() {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.gridlayout.widget.GridLayout.c():void");
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (!(layoutParams instanceof LayoutParams)) {
            return false;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        a(layoutParams2, true);
        a(layoutParams2, false);
        return true;
    }

    public final int e(View view, boolean z2, boolean z3) {
        int[] iArr;
        if (this.i == 1) {
            return f(view, z2, z3);
        }
        k kVar = z2 ? this.e : this.f;
        if (z3) {
            if (kVar.j == null) {
                kVar.j = new int[kVar.f() + 1];
            }
            if (!kVar.k) {
                kVar.c(true);
                kVar.k = true;
            }
            iArr = kVar.j;
        } else {
            if (kVar.l == null) {
                kVar.l = new int[kVar.f() + 1];
            }
            if (!kVar.m) {
                kVar.c(false);
                kVar.m = true;
            }
            iArr = kVar.l;
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        m mVar = (z2 ? layoutParams.b : layoutParams.a).b;
        return iArr[z3 ? mVar.a : mVar.b];
    }

    public final int f(View view, boolean z2, boolean z3) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i2 = z2 ? z3 ? ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin : ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin : z3 ? ((ViewGroup.MarginLayoutParams) layoutParams).topMargin : ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        if (i2 != Integer.MIN_VALUE) {
            return i2;
        }
        if (this.h && view.getClass() != Space.class) {
            return this.j / 2;
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public int getAlignmentMode() {
        return this.i;
    }

    public int getColumnCount() {
        return this.e.f();
    }

    public int getOrientation() {
        return this.g;
    }

    public Printer getPrinter() {
        return this.l;
    }

    public int getRowCount() {
        return this.f.f();
    }

    public boolean getUseDefaultMargins() {
        return this.h;
    }

    public final void h() {
        this.k = 0;
        k kVar = this.e;
        if (kVar != null) {
            kVar.l();
        }
        k kVar2 = this.f;
        if (kVar2 != null) {
            kVar2.l();
        }
        if (kVar == null || kVar2 == null) {
            return;
        }
        kVar.m();
        kVar2.m();
    }

    public final void i(View view, int i2, int i3, int i4, int i5) {
        view.measure(ViewGroup.getChildMeasureSpec(i2, e(view, true, false) + e(view, true, true), i4), ViewGroup.getChildMeasureSpec(i3, e(view, false, false) + e(view, false, true), i5));
    }

    public final void j(int i2, int i3, boolean z2) {
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (z2) {
                    i(childAt, i2, i3, ((ViewGroup.MarginLayoutParams) layoutParams).width, ((ViewGroup.MarginLayoutParams) layoutParams).height);
                } else {
                    boolean z3 = this.g == 0;
                    p pVar = z3 ? layoutParams.b : layoutParams.a;
                    if (pVar.a(z3) == E) {
                        int[] iArrH = (z3 ? this.e : this.f).h();
                        m mVar = pVar.b;
                        int iE = (iArrH[mVar.b] - iArrH[mVar.a]) - (e(childAt, z3, false) + e(childAt, z3, true));
                        if (z3) {
                            i(childAt, i2, i3, iE, ((ViewGroup.MarginLayoutParams) layoutParams).height);
                        } else {
                            i(childAt, i2, i3, ((ViewGroup.MarginLayoutParams) layoutParams).width, iE);
                        }
                    }
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int[] iArr;
        k kVar;
        int i6;
        boolean z3;
        int i7;
        View view;
        GridLayout gridLayout = this;
        c();
        int i8 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int i9 = (i8 - paddingLeft) - paddingRight;
        k kVar2 = gridLayout.e;
        kVar2.v.a = i9;
        kVar2.w.a = -i9;
        boolean z4 = false;
        kVar2.q = false;
        kVar2.h();
        int i10 = ((i5 - i3) - paddingTop) - paddingBottom;
        k kVar3 = gridLayout.f;
        kVar3.v.a = i10;
        kVar3.w.a = -i10;
        kVar3.q = false;
        kVar3.h();
        int[] iArrH = kVar2.h();
        int[] iArrH2 = kVar3.h();
        int childCount = getChildCount();
        int i11 = 0;
        while (i11 < childCount) {
            View childAt = gridLayout.getChildAt(i11);
            if (childAt.getVisibility() == 8) {
                i6 = i11;
                i7 = childCount;
                kVar = kVar2;
                z3 = z4;
                iArr = iArrH;
            } else {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                p pVar = layoutParams.b;
                p pVar2 = layoutParams.a;
                m mVar = pVar.b;
                m mVar2 = pVar2.b;
                int i12 = childCount;
                int i13 = iArrH[mVar.a];
                int i14 = iArrH2[mVar2.a];
                int i15 = iArrH[mVar.b];
                int i16 = iArrH2[mVar2.b];
                int i17 = i15 - i13;
                int i18 = i16 - i14;
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                iArr = iArrH;
                h hVarA = pVar.a(true);
                h hVarA2 = pVar2.a(false);
                o<p, l> oVarG = kVar2.g();
                l lVar = oVarG.c[oVarG.a[i11]];
                o<p, l> oVarG2 = kVar3.g();
                kVar = kVar2;
                l lVar2 = oVarG2.c[oVarG2.a[i11]];
                i6 = i11;
                int iD = hVarA.d(childAt, i17 - lVar.d(true));
                int iD2 = hVarA2.d(childAt, i18 - lVar2.d(true));
                int iE = gridLayout.e(childAt, true, true);
                int iE2 = gridLayout.e(childAt, false, true);
                int iE3 = gridLayout.e(childAt, true, false);
                int i19 = iE + iE3;
                int iE4 = iE2 + gridLayout.e(childAt, false, false);
                z3 = false;
                i7 = i12;
                int iA = lVar.a(this, childAt, hVarA, measuredWidth + i19, true);
                int iA2 = lVar2.a(this, childAt, hVarA2, measuredHeight + iE4, false);
                int iE5 = hVarA.e(measuredWidth, i17 - i19);
                int iE6 = hVarA2.e(measuredHeight, i18 - iE4);
                int i20 = i13 + iD + iA;
                WeakHashMap<View, in3> weakHashMap = om3.a;
                int i21 = !(om3.e.c(this) == 1) ? paddingLeft + iE + i20 : (((i8 - iE5) - paddingRight) - iE3) - i20;
                int i22 = paddingTop + i14 + iD2 + iA2 + iE2;
                if (iE5 == childAt.getMeasuredWidth() && iE6 == childAt.getMeasuredHeight()) {
                    view = childAt;
                } else {
                    view = childAt;
                    view.measure(View.MeasureSpec.makeMeasureSpec(iE5, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(iE6, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
                }
                view.layout(i21, i22, iE5 + i21, iE6 + i22);
            }
            i11 = i6 + 1;
            gridLayout = this;
            iArrH = iArr;
            kVar2 = kVar;
            childCount = i7;
            z4 = z3;
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i2, int i3) {
        int iJ;
        int iJ2;
        c();
        k kVar = this.f;
        k kVar2 = this.e;
        if (kVar2 != null && kVar != null) {
            kVar2.m();
            kVar.m();
        }
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize((-paddingRight) + i2), View.MeasureSpec.getMode(i2));
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize((-paddingBottom) + i3), View.MeasureSpec.getMode(i3));
        j(iMakeMeasureSpec, iMakeMeasureSpec2, true);
        if (this.g == 0) {
            iJ2 = kVar2.j(iMakeMeasureSpec);
            j(iMakeMeasureSpec, iMakeMeasureSpec2, false);
            iJ = kVar.j(iMakeMeasureSpec2);
        } else {
            iJ = kVar.j(iMakeMeasureSpec2);
            j(iMakeMeasureSpec, iMakeMeasureSpec2, false);
            iJ2 = kVar2.j(iMakeMeasureSpec);
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iJ2 + paddingRight, getSuggestedMinimumWidth()), i2, 0), View.resolveSizeAndState(Math.max(iJ + paddingBottom, getSuggestedMinimumHeight()), i3, 0));
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        super.requestLayout();
        h();
    }

    public void setAlignmentMode(int i2) {
        this.i = i2;
        requestLayout();
    }

    public void setColumnCount(int i2) {
        this.e.n(i2);
        h();
        requestLayout();
    }

    public void setColumnOrderPreserved(boolean z2) {
        k kVar = this.e;
        kVar.u = z2;
        kVar.l();
        h();
        requestLayout();
    }

    public void setOrientation(int i2) {
        if (this.g != i2) {
            this.g = i2;
            h();
            requestLayout();
        }
    }

    public void setPrinter(Printer printer) {
        if (printer == null) {
            printer = n;
        }
        this.l = printer;
    }

    public void setRowCount(int i2) {
        this.f.n(i2);
        h();
        requestLayout();
    }

    public void setRowOrderPreserved(boolean z2) {
        k kVar = this.f;
        kVar.u = z2;
        kVar.l();
        h();
        requestLayout();
    }

    public void setUseDefaultMargins(boolean z2) {
        this.h = z2;
        requestLayout();
    }

    public static final class n {
        public int a;

        public n() {
            this.a = Integer.MIN_VALUE;
        }

        public final String toString() {
            return Integer.toString(this.a);
        }

        public n(int i) {
            this.a = i;
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public static final int c = 1;
        public static final int d = R$styleable.GridLayout_Layout_android_layout_margin;
        public static final int e = R$styleable.GridLayout_Layout_android_layout_marginLeft;
        public static final int f = R$styleable.GridLayout_Layout_android_layout_marginTop;
        public static final int g = R$styleable.GridLayout_Layout_android_layout_marginRight;
        public static final int h = R$styleable.GridLayout_Layout_android_layout_marginBottom;
        public static final int i = R$styleable.GridLayout_Layout_layout_column;
        public static final int j = R$styleable.GridLayout_Layout_layout_columnSpan;
        public static final int k = R$styleable.GridLayout_Layout_layout_columnWeight;
        public static final int l = R$styleable.GridLayout_Layout_layout_row;
        public static final int m = R$styleable.GridLayout_Layout_layout_rowSpan;
        public static final int n = R$styleable.GridLayout_Layout_layout_rowWeight;
        public static final int o = R$styleable.GridLayout_Layout_layout_gravity;
        public p a;
        public p b;

        public LayoutParams(p pVar, p pVar2) {
            super(-2, -2);
            p pVar3 = p.e;
            this.a = pVar3;
            this.b = pVar3;
            setMargins(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
            this.a = pVar;
            this.b = pVar2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LayoutParams)) {
                return false;
            }
            LayoutParams layoutParams = (LayoutParams) obj;
            return this.b.equals(layoutParams.b) && this.a.equals(layoutParams.a);
        }

        public final int hashCode() {
            return this.b.hashCode() + (this.a.hashCode() * 31);
        }

        @Override // android.view.ViewGroup.LayoutParams
        public final void setBaseAttributes(TypedArray typedArray, int i2, int i3) {
            ((ViewGroup.MarginLayoutParams) this).width = typedArray.getLayoutDimension(i2, -2);
            ((ViewGroup.MarginLayoutParams) this).height = typedArray.getLayoutDimension(i3, -2);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public LayoutParams() {
            p pVar = p.e;
            this(pVar, pVar);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            p pVar = p.e;
            this.a = pVar;
            this.b = pVar;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            p pVar = p.e;
            this.a = pVar;
            this.b = pVar;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            p pVar = p.e;
            this.a = pVar;
            this.b = pVar;
            this.a = layoutParams.a;
            this.b = layoutParams.b;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            p pVar = p.e;
            this.a = pVar;
            this.b = pVar;
            int[] iArr = R$styleable.GridLayout_Layout;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
            try {
                int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(d, Integer.MIN_VALUE);
                ((ViewGroup.MarginLayoutParams) this).leftMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(e, dimensionPixelSize);
                ((ViewGroup.MarginLayoutParams) this).topMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(f, dimensionPixelSize);
                ((ViewGroup.MarginLayoutParams) this).rightMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(g, dimensionPixelSize);
                ((ViewGroup.MarginLayoutParams) this).bottomMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(h, dimensionPixelSize);
                typedArrayObtainStyledAttributes.recycle();
                typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
                try {
                    int i2 = typedArrayObtainStyledAttributes.getInt(o, 0);
                    int i3 = typedArrayObtainStyledAttributes.getInt(i, Integer.MIN_VALUE);
                    int i4 = j;
                    int i5 = c;
                    this.b = GridLayout.l(i3, typedArrayObtainStyledAttributes.getInt(i4, i5), GridLayout.d(i2, true), typedArrayObtainStyledAttributes.getFloat(k, 0.0f));
                    this.a = GridLayout.l(typedArrayObtainStyledAttributes.getInt(l, Integer.MIN_VALUE), typedArrayObtainStyledAttributes.getInt(m, i5), GridLayout.d(i2, false), typedArrayObtainStyledAttributes.getFloat(n, 0.0f));
                } finally {
                }
            } finally {
            }
        }
    }

    public GridLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GridLayout(Context context) {
        this(context, null);
    }

    public class a implements Printer {
        @Override // android.util.Printer
        public final void println(String str) {
        }
    }

    public class d extends h {
        @Override // androidx.gridlayout.widget.GridLayout.h
        public final String c() {
            return "TRAILING";
        }

        @Override // androidx.gridlayout.widget.GridLayout.h
        public final int d(View view, int i) {
            return i;
        }

        @Override // androidx.gridlayout.widget.GridLayout.h
        public final int a(View view, int i, int i2) {
            return i;
        }
    }

    public class g extends h {
        @Override // androidx.gridlayout.widget.GridLayout.h
        public final int a(View view, int i, int i2) {
            return Integer.MIN_VALUE;
        }

        @Override // androidx.gridlayout.widget.GridLayout.h
        public final String c() {
            return "FILL";
        }

        @Override // androidx.gridlayout.widget.GridLayout.h
        public final int d(View view, int i) {
            return 0;
        }

        @Override // androidx.gridlayout.widget.GridLayout.h
        public final int e(int i, int i2) {
            return i2;
        }
    }

    public static abstract class h {
        public abstract int a(View view, int i, int i2);

        public l b() {
            return new l();
        }

        public abstract String c();

        public abstract int d(View view, int i);

        public final String toString() {
            return "Alignment:" + c();
        }

        public int e(int i, int i2) {
            return i;
        }
    }
}
