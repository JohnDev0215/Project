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
    final InterfaceC0023b f1841a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final a f1842b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final List<View> f1843c = new ArrayList();

    /* JADX INFO: compiled from: ChildHelper.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        long f1844a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        a f1845b;

        a() {
        }

        private void c() {
            if (this.f1845b == null) {
                this.f1845b = new a();
            }
        }

        void a(int i) {
            if (i < 64) {
                this.f1844a &= ~(1 << i);
                return;
            }
            a aVar = this.f1845b;
            if (aVar != null) {
                aVar.a(i - 64);
            }
        }

        int b(int i) {
            a aVar = this.f1845b;
            return aVar == null ? i >= 64 ? Long.bitCount(this.f1844a) : Long.bitCount(this.f1844a & ((1 << i) - 1)) : i < 64 ? Long.bitCount(this.f1844a & ((1 << i) - 1)) : aVar.b(i - 64) + Long.bitCount(this.f1844a);
        }

        boolean d(int i) {
            if (i < 64) {
                return (this.f1844a & (1 << i)) != 0;
            }
            c();
            return this.f1845b.d(i - 64);
        }

        void e(int i, boolean z) {
            if (i >= 64) {
                c();
                this.f1845b.e(i - 64, z);
                return;
            }
            long j = this.f1844a;
            boolean z2 = (Long.MIN_VALUE & j) != 0;
            long j2 = (1 << i) - 1;
            this.f1844a = ((j & (~j2)) << 1) | (j & j2);
            if (z) {
                h(i);
            } else {
                a(i);
            }
            if (z2 || this.f1845b != null) {
                c();
                this.f1845b.e(0, z2);
            }
        }

        boolean f(int i) {
            if (i >= 64) {
                c();
                return this.f1845b.f(i - 64);
            }
            long j = 1 << i;
            long j2 = this.f1844a;
            boolean z = (j2 & j) != 0;
            long j3 = j2 & (~j);
            this.f1844a = j3;
            long j4 = j - 1;
            this.f1844a = (j3 & j4) | Long.rotateRight((~j4) & j3, 1);
            a aVar = this.f1845b;
            if (aVar != null) {
                if (aVar.d(0)) {
                    h(63);
                }
                this.f1845b.f(0);
            }
            return z;
        }

        void g() {
            this.f1844a = 0L;
            a aVar = this.f1845b;
            if (aVar != null) {
                aVar.g();
            }
        }

        void h(int i) {
            if (i < 64) {
                this.f1844a |= 1 << i;
            } else {
                c();
                this.f1845b.h(i - 64);
            }
        }

        public String toString() {
            if (this.f1845b == null) {
                return Long.toBinaryString(this.f1844a);
            }
            return this.f1845b.toString() + "xx" + Long.toBinaryString(this.f1844a);
        }
    }

    /* JADX INFO: renamed from: androidx.recyclerview.widget.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChildHelper.java */
    interface InterfaceC0023b {
        View a(int i);

        void b(View view);

        void c(int i);

        void d();

        RecyclerView.c0 e(View view);

        void f(int i);

        void g(View view);

        void h(View view, int i, ViewGroup.LayoutParams layoutParams);

        void i(View view, int i);

        int j(View view);

        int k();
    }

    b(InterfaceC0023b interfaceC0023b) {
        this.f1841a = interfaceC0023b;
    }

    private int h(int i) {
        if (i < 0) {
            return -1;
        }
        int iK = this.f1841a.k();
        int i2 = i;
        while (i2 < iK) {
            int iB = i - (i2 - this.f1842b.b(i2));
            if (iB == 0) {
                while (this.f1842b.d(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += iB;
        }
        return -1;
    }

    private void l(View view) {
        this.f1843c.add(view);
        this.f1841a.b(view);
    }

    private boolean t(View view) {
        if (!this.f1843c.remove(view)) {
            return false;
        }
        this.f1841a.g(view);
        return true;
    }

    void a(View view, int i, boolean z) {
        int iK = i < 0 ? this.f1841a.k() : h(i);
        this.f1842b.e(iK, z);
        if (z) {
            l(view);
        }
        this.f1841a.i(view, iK);
    }

    void b(View view, boolean z) {
        a(view, -1, z);
    }

    void c(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        int iK = i < 0 ? this.f1841a.k() : h(i);
        this.f1842b.e(iK, z);
        if (z) {
            l(view);
        }
        this.f1841a.h(view, iK, layoutParams);
    }

    void d(int i) {
        int iH = h(i);
        this.f1842b.f(iH);
        this.f1841a.f(iH);
    }

    View e(int i) {
        int size = this.f1843c.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = this.f1843c.get(i2);
            RecyclerView.c0 c0VarE = this.f1841a.e(view);
            if (c0VarE.m() == i && !c0VarE.t() && !c0VarE.v()) {
                return view;
            }
        }
        return null;
    }

    View f(int i) {
        return this.f1841a.a(h(i));
    }

    int g() {
        return this.f1841a.k() - this.f1843c.size();
    }

    View i(int i) {
        return this.f1841a.a(i);
    }

    int j() {
        return this.f1841a.k();
    }

    void k(View view) {
        int iJ = this.f1841a.j(view);
        if (iJ >= 0) {
            this.f1842b.h(iJ);
            l(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    int m(View view) {
        int iJ = this.f1841a.j(view);
        if (iJ == -1 || this.f1842b.d(iJ)) {
            return -1;
        }
        return iJ - this.f1842b.b(iJ);
    }

    boolean n(View view) {
        return this.f1843c.contains(view);
    }

    void o() {
        this.f1842b.g();
        for (int size = this.f1843c.size() - 1; size >= 0; size--) {
            this.f1841a.g(this.f1843c.get(size));
            this.f1843c.remove(size);
        }
        this.f1841a.d();
    }

    void p(View view) {
        int iJ = this.f1841a.j(view);
        if (iJ < 0) {
            return;
        }
        if (this.f1842b.f(iJ)) {
            t(view);
        }
        this.f1841a.c(iJ);
    }

    void q(int i) {
        int iH = h(i);
        View viewA = this.f1841a.a(iH);
        if (viewA == null) {
            return;
        }
        if (this.f1842b.f(iH)) {
            t(viewA);
        }
        this.f1841a.c(iH);
    }

    boolean r(View view) {
        int iJ = this.f1841a.j(view);
        if (iJ == -1) {
            t(view);
            return true;
        }
        if (!this.f1842b.d(iJ)) {
            return false;
        }
        this.f1842b.f(iJ);
        t(view);
        this.f1841a.c(iJ);
        return true;
    }

    void s(View view) {
        int iJ = this.f1841a.j(view);
        if (iJ < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        if (this.f1842b.d(iJ)) {
            this.f1842b.a(iJ);
            t(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public String toString() {
        return this.f1842b.toString() + ", hidden list:" + this.f1843c.size();
    }
}
