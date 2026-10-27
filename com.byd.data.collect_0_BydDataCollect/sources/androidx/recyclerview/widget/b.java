package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ChildHelper.java */
/* JADX INFO: loaded from: classes.dex */
class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final InterfaceC0027b f1915a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final a f1916b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final List<View> f1917c = new ArrayList();

    /* JADX INFO: compiled from: ChildHelper.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        long f1918a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        a f1919b;

        a() {
        }

        private void c() {
            if (this.f1919b == null) {
                this.f1919b = new a();
            }
        }

        void a(int i2) {
            if (i2 < 64) {
                this.f1918a &= ~(1 << i2);
                return;
            }
            a aVar = this.f1919b;
            if (aVar != null) {
                aVar.a(i2 - 64);
            }
        }

        int b(int i2) {
            a aVar = this.f1919b;
            return aVar == null ? i2 >= 64 ? Long.bitCount(this.f1918a) : Long.bitCount(this.f1918a & ((1 << i2) - 1)) : i2 < 64 ? Long.bitCount(this.f1918a & ((1 << i2) - 1)) : aVar.b(i2 - 64) + Long.bitCount(this.f1918a);
        }

        boolean d(int i2) {
            if (i2 < 64) {
                return (this.f1918a & (1 << i2)) != 0;
            }
            c();
            return this.f1919b.d(i2 - 64);
        }

        void e(int i2, boolean z) {
            if (i2 >= 64) {
                c();
                this.f1919b.e(i2 - 64, z);
                return;
            }
            long j = this.f1918a;
            boolean z2 = (Long.MIN_VALUE & j) != 0;
            long j2 = (1 << i2) - 1;
            this.f1918a = ((j & (~j2)) << 1) | (j & j2);
            if (z) {
                h(i2);
            } else {
                a(i2);
            }
            if (z2 || this.f1919b != null) {
                c();
                this.f1919b.e(0, z2);
            }
        }

        boolean f(int i2) {
            if (i2 >= 64) {
                c();
                return this.f1919b.f(i2 - 64);
            }
            long j = 1 << i2;
            long j2 = this.f1918a;
            boolean z = (j2 & j) != 0;
            long j3 = j2 & (~j);
            this.f1918a = j3;
            long j4 = j - 1;
            this.f1918a = (j3 & j4) | Long.rotateRight((~j4) & j3, 1);
            a aVar = this.f1919b;
            if (aVar != null) {
                if (aVar.d(0)) {
                    h(63);
                }
                this.f1919b.f(0);
            }
            return z;
        }

        void g() {
            this.f1918a = 0L;
            a aVar = this.f1919b;
            if (aVar != null) {
                aVar.g();
            }
        }

        void h(int i2) {
            if (i2 < 64) {
                this.f1918a |= 1 << i2;
            } else {
                c();
                this.f1919b.h(i2 - 64);
            }
        }

        public String toString() {
            if (this.f1919b == null) {
                return Long.toBinaryString(this.f1918a);
            }
            return this.f1919b.toString() + "xx" + Long.toBinaryString(this.f1918a);
        }
    }

    /* JADX INFO: renamed from: androidx.recyclerview.widget.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChildHelper.java */
    interface InterfaceC0027b {
        View a(int i2);

        void b(View view);

        void c(int i2);

        void d();

        RecyclerView.c0 e(View view);

        void f(int i2);

        void g(View view);

        void h(View view, int i2, ViewGroup.LayoutParams layoutParams);

        void i(View view, int i2);

        int j(View view);

        int k();
    }

    b(InterfaceC0027b interfaceC0027b) {
        this.f1915a = interfaceC0027b;
    }

    private int h(int i2) {
        if (i2 < 0) {
            return -1;
        }
        int iK = this.f1915a.k();
        int i3 = i2;
        while (i3 < iK) {
            int iB = i2 - (i3 - this.f1916b.b(i3));
            if (iB == 0) {
                while (this.f1916b.d(i3)) {
                    i3++;
                }
                return i3;
            }
            i3 += iB;
        }
        return -1;
    }

    private void l(View view) {
        this.f1917c.add(view);
        this.f1915a.b(view);
    }

    private boolean t(View view) {
        if (!this.f1917c.remove(view)) {
            return false;
        }
        this.f1915a.g(view);
        return true;
    }

    void a(View view, int i2, boolean z) {
        int iK = i2 < 0 ? this.f1915a.k() : h(i2);
        this.f1916b.e(iK, z);
        if (z) {
            l(view);
        }
        this.f1915a.i(view, iK);
    }

    void b(View view, boolean z) {
        a(view, -1, z);
    }

    void c(View view, int i2, ViewGroup.LayoutParams layoutParams, boolean z) {
        int iK = i2 < 0 ? this.f1915a.k() : h(i2);
        this.f1916b.e(iK, z);
        if (z) {
            l(view);
        }
        this.f1915a.h(view, iK, layoutParams);
    }

    void d(int i2) {
        int iH = h(i2);
        this.f1916b.f(iH);
        this.f1915a.f(iH);
    }

    View e(int i2) {
        int size = this.f1917c.size();
        for (int i3 = 0; i3 < size; i3++) {
            View view = this.f1917c.get(i3);
            RecyclerView.c0 c0VarE = this.f1915a.e(view);
            if (c0VarE.m() == i2 && !c0VarE.t() && !c0VarE.v()) {
                return view;
            }
        }
        return null;
    }

    View f(int i2) {
        return this.f1915a.a(h(i2));
    }

    int g() {
        return this.f1915a.k() - this.f1917c.size();
    }

    View i(int i2) {
        return this.f1915a.a(i2);
    }

    int j() {
        return this.f1915a.k();
    }

    void k(View view) {
        int iJ = this.f1915a.j(view);
        if (iJ >= 0) {
            this.f1916b.h(iJ);
            l(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    int m(View view) {
        int iJ = this.f1915a.j(view);
        if (iJ == -1 || this.f1916b.d(iJ)) {
            return -1;
        }
        return iJ - this.f1916b.b(iJ);
    }

    boolean n(View view) {
        return this.f1917c.contains(view);
    }

    void o() {
        this.f1916b.g();
        for (int size = this.f1917c.size() - 1; size >= 0; size--) {
            this.f1915a.g(this.f1917c.get(size));
            this.f1917c.remove(size);
        }
        this.f1915a.d();
    }

    void p(View view) {
        int iJ = this.f1915a.j(view);
        if (iJ < 0) {
            return;
        }
        if (this.f1916b.f(iJ)) {
            t(view);
        }
        this.f1915a.c(iJ);
    }

    void q(int i2) {
        int iH = h(i2);
        View viewA = this.f1915a.a(iH);
        if (viewA == null) {
            return;
        }
        if (this.f1916b.f(iH)) {
            t(viewA);
        }
        this.f1915a.c(iH);
    }

    boolean r(View view) {
        int iJ = this.f1915a.j(view);
        if (iJ == -1) {
            t(view);
            return true;
        }
        if (!this.f1916b.d(iJ)) {
            return false;
        }
        this.f1916b.f(iJ);
        t(view);
        this.f1915a.c(iJ);
        return true;
    }

    void s(View view) {
        int iJ = this.f1915a.j(view);
        if (iJ < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        if (this.f1916b.d(iJ)) {
            this.f1916b.a(iJ);
            t(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public String toString() {
        return this.f1916b.toString() + ", hidden list:" + this.f1917c.size();
    }
}
