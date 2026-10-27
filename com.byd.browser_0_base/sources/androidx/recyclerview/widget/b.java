package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.C0175b;
import defpackage.in3;
import defpackage.om3;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ChildHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public final InterfaceC0025b a;
    public final a b = new a();
    public final ArrayList c = new ArrayList();

    /* JADX INFO: compiled from: ChildHelper.java */
    public static class a {
        public long a = 0;
        public a b;

        public final void a(int i) {
            if (i < 64) {
                this.a &= ~(1 << i);
                return;
            }
            a aVar = this.b;
            if (aVar != null) {
                aVar.a(i - 64);
            }
        }

        public final int b(int i) {
            a aVar = this.b;
            if (aVar == null) {
                if (i >= 64) {
                    return Long.bitCount(this.a);
                }
                return Long.bitCount(((1 << i) - 1) & this.a);
            }
            if (i < 64) {
                return Long.bitCount(((1 << i) - 1) & this.a);
            }
            return Long.bitCount(this.a) + aVar.b(i - 64);
        }

        public final void c() {
            if (this.b == null) {
                this.b = new a();
            }
        }

        public final boolean d(int i) {
            if (i < 64) {
                return ((1 << i) & this.a) != 0;
            }
            c();
            return this.b.d(i - 64);
        }

        public final void e(int i, boolean z) {
            if (i >= 64) {
                c();
                this.b.e(i - 64, z);
                return;
            }
            long j = this.a;
            boolean z2 = (Long.MIN_VALUE & j) != 0;
            long j2 = (1 << i) - 1;
            this.a = ((j & (~j2)) << 1) | (j & j2);
            if (z) {
                h(i);
            } else {
                a(i);
            }
            if (z2 || this.b != null) {
                c();
                this.b.e(0, z2);
            }
        }

        public final boolean f(int i) {
            if (i >= 64) {
                c();
                return this.b.f(i - 64);
            }
            long j = 1 << i;
            long j2 = this.a;
            boolean z = (j2 & j) != 0;
            long j3 = j2 & (~j);
            this.a = j3;
            long j4 = j - 1;
            this.a = (j3 & j4) | Long.rotateRight((~j4) & j3, 1);
            a aVar = this.b;
            if (aVar != null) {
                if (aVar.d(0)) {
                    h(63);
                }
                this.b.f(0);
            }
            return z;
        }

        public final void g() {
            this.a = 0L;
            a aVar = this.b;
            if (aVar != null) {
                aVar.g();
            }
        }

        public final void h(int i) {
            if (i < 64) {
                this.a |= 1 << i;
            } else {
                c();
                this.b.h(i - 64);
            }
        }

        public final String toString() {
            if (this.b == null) {
                return Long.toBinaryString(this.a);
            }
            return this.b.toString() + "xx" + Long.toBinaryString(this.a);
        }
    }

    /* JADX INFO: renamed from: androidx.recyclerview.widget.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChildHelper.java */
    public interface InterfaceC0025b {
    }

    public b(v vVar) {
        this.a = vVar;
    }

    public final void a(View view, int i, boolean z) {
        InterfaceC0025b interfaceC0025b = this.a;
        int iA = i < 0 ? ((v) interfaceC0025b).a() : f(i);
        this.b.e(iA, z);
        if (z) {
            i(view);
        }
        RecyclerView recyclerView = ((v) interfaceC0025b).a;
        recyclerView.addView(view, iA);
        RecyclerView.a0 a0VarK = RecyclerView.K(view);
        RecyclerView.e eVar = recyclerView.q;
        if (eVar != null && a0VarK != null) {
            eVar.y(a0VarK);
        }
        ArrayList arrayList = recyclerView.H;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            } else {
                ((RecyclerView.n) recyclerView.H.get(size)).d(view);
            }
        }
    }

    public final void b(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        InterfaceC0025b interfaceC0025b = this.a;
        int iA = i < 0 ? ((v) interfaceC0025b).a() : f(i);
        this.b.e(iA, z);
        if (z) {
            i(view);
        }
        v vVar = (v) interfaceC0025b;
        vVar.getClass();
        RecyclerView.a0 a0VarK = RecyclerView.K(view);
        RecyclerView recyclerView = vVar.a;
        if (a0VarK != null) {
            if (!a0VarK.o() && !a0VarK.s()) {
                StringBuilder sb = new StringBuilder("Called attach on a child which is not detached: ");
                sb.append(a0VarK);
                throw new IllegalArgumentException(C0175b.h(recyclerView, sb));
            }
            if (RecyclerView.G0) {
                a0VarK.toString();
            }
            a0VarK.n &= -257;
        }
        recyclerView.attachViewToParent(view, iA, layoutParams);
    }

    public final void c(int i) {
        RecyclerView.a0 a0VarK;
        int iF = f(i);
        this.b.f(iF);
        v vVar = (v) this.a;
        View childAt = vVar.a.getChildAt(iF);
        RecyclerView recyclerView = vVar.a;
        if (childAt != null && (a0VarK = RecyclerView.K(childAt)) != null) {
            if (a0VarK.o() && !a0VarK.s()) {
                StringBuilder sb = new StringBuilder("called detach on an already detached child ");
                sb.append(a0VarK);
                throw new IllegalArgumentException(C0175b.h(recyclerView, sb));
            }
            if (RecyclerView.G0) {
                a0VarK.toString();
            }
            a0VarK.d(256);
        }
        recyclerView.detachViewFromParent(iF);
    }

    public final View d(int i) {
        return ((v) this.a).a.getChildAt(f(i));
    }

    public final int e() {
        return ((v) this.a).a() - this.c.size();
    }

    public final int f(int i) {
        if (i < 0) {
            return -1;
        }
        int iA = ((v) this.a).a();
        int i2 = i;
        while (i2 < iA) {
            a aVar = this.b;
            int iB = i - (i2 - aVar.b(i2));
            if (iB == 0) {
                while (aVar.d(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += iB;
        }
        return -1;
    }

    public final View g(int i) {
        return ((v) this.a).a.getChildAt(i);
    }

    public final int h() {
        return ((v) this.a).a();
    }

    public final void i(View view) {
        this.c.add(view);
        v vVar = (v) this.a;
        vVar.getClass();
        RecyclerView.a0 a0VarK = RecyclerView.K(view);
        if (a0VarK != null) {
            int i = a0VarK.u;
            View view2 = a0VarK.e;
            if (i != -1) {
                a0VarK.t = i;
            } else {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                a0VarK.t = om3.d.b(view2);
            }
            RecyclerView recyclerView = vVar.a;
            if (recyclerView.O()) {
                a0VarK.u = 4;
                recyclerView.z0.add(a0VarK);
            } else {
                WeakHashMap<View, in3> weakHashMap2 = om3.a;
                om3.d.p(view2, 4);
            }
        }
    }

    public final boolean j(View view) {
        return this.c.contains(view);
    }

    public final void k(View view) {
        if (this.c.remove(view)) {
            v vVar = (v) this.a;
            vVar.getClass();
            RecyclerView.a0 a0VarK = RecyclerView.K(view);
            if (a0VarK != null) {
                int i = a0VarK.t;
                RecyclerView recyclerView = vVar.a;
                if (recyclerView.O()) {
                    a0VarK.u = i;
                    recyclerView.z0.add(a0VarK);
                } else {
                    WeakHashMap<View, in3> weakHashMap = om3.a;
                    om3.d.p(a0VarK.e, i);
                }
                a0VarK.t = 0;
            }
        }
    }

    public final String toString() {
        return this.b.toString() + ", hidden list:" + this.c.size();
    }
}
