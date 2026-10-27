package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ChildHelper.java */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final b f3024a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final a f3025b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final List<View> f3026c = new ArrayList();

    /* JADX INFO: compiled from: ChildHelper.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        long f3027a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        a f3028b;

        a() {
        }

        private void c() {
            if (this.f3028b == null) {
                this.f3028b = new a();
            }
        }

        void a(int i7) {
            if (i7 < 64) {
                this.f3027a &= ~(1 << i7);
                return;
            }
            a aVar = this.f3028b;
            if (aVar != null) {
                aVar.a(i7 - 64);
            }
        }

        int b(int i7) {
            a aVar = this.f3028b;
            return aVar == null ? i7 >= 64 ? Long.bitCount(this.f3027a) : Long.bitCount(this.f3027a & ((1 << i7) - 1)) : i7 < 64 ? Long.bitCount(this.f3027a & ((1 << i7) - 1)) : aVar.b(i7 - 64) + Long.bitCount(this.f3027a);
        }

        boolean d(int i7) {
            if (i7 < 64) {
                return (this.f3027a & (1 << i7)) != 0;
            }
            c();
            return this.f3028b.d(i7 - 64);
        }

        void e(int i7, boolean z6) {
            if (i7 >= 64) {
                c();
                this.f3028b.e(i7 - 64, z6);
                return;
            }
            long j7 = this.f3027a;
            boolean z7 = (Long.MIN_VALUE & j7) != 0;
            long j8 = (1 << i7) - 1;
            this.f3027a = ((j7 & (~j8)) << 1) | (j7 & j8);
            if (z6) {
                h(i7);
            } else {
                a(i7);
            }
            if (z7 || this.f3028b != null) {
                c();
                this.f3028b.e(0, z7);
            }
        }

        boolean f(int i7) {
            if (i7 >= 64) {
                c();
                return this.f3028b.f(i7 - 64);
            }
            long j7 = 1 << i7;
            long j8 = this.f3027a;
            boolean z6 = (j8 & j7) != 0;
            long j9 = j8 & (~j7);
            this.f3027a = j9;
            long j10 = j7 - 1;
            this.f3027a = (j9 & j10) | Long.rotateRight((~j10) & j9, 1);
            a aVar = this.f3028b;
            if (aVar != null) {
                if (aVar.d(0)) {
                    h(63);
                }
                this.f3028b.f(0);
            }
            return z6;
        }

        void g() {
            this.f3027a = 0L;
            a aVar = this.f3028b;
            if (aVar != null) {
                aVar.g();
            }
        }

        void h(int i7) {
            if (i7 < 64) {
                this.f3027a |= 1 << i7;
            } else {
                c();
                this.f3028b.h(i7 - 64);
            }
        }

        public String toString() {
            if (this.f3028b == null) {
                return Long.toBinaryString(this.f3027a);
            }
            return this.f3028b.toString() + "xx" + Long.toBinaryString(this.f3027a);
        }
    }

    /* JADX INFO: compiled from: ChildHelper.java */
    interface b {
        View a(int i7);

        void b(View view);

        void c(int i7);

        void d();

        RecyclerView.c0 e(View view);

        void f(int i7);

        void g(View view);

        void h(View view, int i7, ViewGroup.LayoutParams layoutParams);

        void i(View view, int i7);

        int j(View view);

        int k();
    }

    d(b bVar) {
        this.f3024a = bVar;
    }

    private int h(int i7) {
        if (i7 < 0) {
            return -1;
        }
        int iK = this.f3024a.k();
        int i8 = i7;
        while (i8 < iK) {
            int iB = i7 - (i8 - this.f3025b.b(i8));
            if (iB == 0) {
                while (this.f3025b.d(i8)) {
                    i8++;
                }
                return i8;
            }
            i8 += iB;
        }
        return -1;
    }

    private void l(View view) {
        this.f3026c.add(view);
        this.f3024a.b(view);
    }

    private boolean t(View view) {
        if (!this.f3026c.remove(view)) {
            return false;
        }
        this.f3024a.g(view);
        return true;
    }

    void a(View view, int i7, boolean z6) {
        int iK = i7 < 0 ? this.f3024a.k() : h(i7);
        this.f3025b.e(iK, z6);
        if (z6) {
            l(view);
        }
        this.f3024a.i(view, iK);
    }

    void b(View view, boolean z6) {
        a(view, -1, z6);
    }

    void c(View view, int i7, ViewGroup.LayoutParams layoutParams, boolean z6) {
        int iK = i7 < 0 ? this.f3024a.k() : h(i7);
        this.f3025b.e(iK, z6);
        if (z6) {
            l(view);
        }
        this.f3024a.h(view, iK, layoutParams);
    }

    void d(int i7) {
        int iH = h(i7);
        this.f3025b.f(iH);
        this.f3024a.f(iH);
    }

    View e(int i7) {
        int size = this.f3026c.size();
        for (int i8 = 0; i8 < size; i8++) {
            View view = this.f3026c.get(i8);
            RecyclerView.c0 c0VarE = this.f3024a.e(view);
            if (c0VarE.m() == i7 && !c0VarE.t() && !c0VarE.v()) {
                return view;
            }
        }
        return null;
    }

    View f(int i7) {
        return this.f3024a.a(h(i7));
    }

    int g() {
        return this.f3024a.k() - this.f3026c.size();
    }

    View i(int i7) {
        return this.f3024a.a(i7);
    }

    int j() {
        return this.f3024a.k();
    }

    void k(View view) {
        int iJ = this.f3024a.j(view);
        if (iJ >= 0) {
            this.f3025b.h(iJ);
            l(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    int m(View view) {
        int iJ = this.f3024a.j(view);
        if (iJ == -1 || this.f3025b.d(iJ)) {
            return -1;
        }
        return iJ - this.f3025b.b(iJ);
    }

    boolean n(View view) {
        return this.f3026c.contains(view);
    }

    void o() {
        this.f3025b.g();
        for (int size = this.f3026c.size() - 1; size >= 0; size--) {
            this.f3024a.g(this.f3026c.get(size));
            this.f3026c.remove(size);
        }
        this.f3024a.d();
    }

    void p(View view) {
        int iJ = this.f3024a.j(view);
        if (iJ < 0) {
            return;
        }
        if (this.f3025b.f(iJ)) {
            t(view);
        }
        this.f3024a.c(iJ);
    }

    void q(int i7) {
        int iH = h(i7);
        View viewA = this.f3024a.a(iH);
        if (viewA == null) {
            return;
        }
        if (this.f3025b.f(iH)) {
            t(viewA);
        }
        this.f3024a.c(iH);
    }

    boolean r(View view) {
        int iJ = this.f3024a.j(view);
        if (iJ == -1) {
            t(view);
            return true;
        }
        if (!this.f3025b.d(iJ)) {
            return false;
        }
        this.f3025b.f(iJ);
        t(view);
        this.f3024a.c(iJ);
        return true;
    }

    void s(View view) {
        int iJ = this.f3024a.j(view);
        if (iJ < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        if (this.f3025b.d(iJ)) {
            this.f3025b.a(iJ);
            t(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public String toString() {
        return this.f3025b.toString() + ", hidden list:" + this.f3026c.size();
    }
}
