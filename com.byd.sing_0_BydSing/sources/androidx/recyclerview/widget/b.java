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
    final InterfaceC0049b f1758a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final a f1759b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final List<View> f1760c = new ArrayList();

    /* JADX INFO: compiled from: ChildHelper.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        long f1761a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        a f1762b;

        a() {
        }

        private void c() {
            if (this.f1762b == null) {
                this.f1762b = new a();
            }
        }

        void a(int i) {
            if (i < 64) {
                this.f1761a &= ~(1 << i);
                return;
            }
            a aVar = this.f1762b;
            if (aVar != null) {
                aVar.a(i - 64);
            }
        }

        int b(int i) {
            a aVar = this.f1762b;
            return aVar == null ? i >= 64 ? Long.bitCount(this.f1761a) : Long.bitCount(this.f1761a & ((1 << i) - 1)) : i < 64 ? Long.bitCount(this.f1761a & ((1 << i) - 1)) : aVar.b(i - 64) + Long.bitCount(this.f1761a);
        }

        boolean d(int i) {
            if (i < 64) {
                return (this.f1761a & (1 << i)) != 0;
            }
            c();
            return this.f1762b.d(i - 64);
        }

        void e(int i, boolean z) {
            if (i >= 64) {
                c();
                this.f1762b.e(i - 64, z);
                return;
            }
            long j = this.f1761a;
            boolean z2 = (Long.MIN_VALUE & j) != 0;
            long j2 = (1 << i) - 1;
            this.f1761a = ((j & (~j2)) << 1) | (j & j2);
            if (z) {
                h(i);
            } else {
                a(i);
            }
            if (z2 || this.f1762b != null) {
                c();
                this.f1762b.e(0, z2);
            }
        }

        boolean f(int i) {
            if (i >= 64) {
                c();
                return this.f1762b.f(i - 64);
            }
            long j = 1 << i;
            long j2 = this.f1761a;
            boolean z = (j2 & j) != 0;
            long j3 = j2 & (~j);
            this.f1761a = j3;
            long j4 = j - 1;
            this.f1761a = (j3 & j4) | Long.rotateRight((~j4) & j3, 1);
            a aVar = this.f1762b;
            if (aVar != null) {
                if (aVar.d(0)) {
                    h(63);
                }
                this.f1762b.f(0);
            }
            return z;
        }

        void g() {
            this.f1761a = 0L;
            a aVar = this.f1762b;
            if (aVar != null) {
                aVar.g();
            }
        }

        void h(int i) {
            if (i < 64) {
                this.f1761a |= 1 << i;
            } else {
                c();
                this.f1762b.h(i - 64);
            }
        }

        public String toString() {
            if (this.f1762b == null) {
                return Long.toBinaryString(this.f1761a);
            }
            return this.f1762b.toString() + "xx" + Long.toBinaryString(this.f1761a);
        }
    }

    /* JADX INFO: renamed from: androidx.recyclerview.widget.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChildHelper.java */
    interface InterfaceC0049b {
        View a(int i);

        void b(View view);

        RecyclerView.c0 c(View view);

        void d(int i);

        void e(View view);

        void f(View view, int i);

        int g();

        void h(int i);

        void i();

        void j(View view, int i, ViewGroup.LayoutParams layoutParams);

        int k(View view);
    }

    b(InterfaceC0049b interfaceC0049b) {
        this.f1758a = interfaceC0049b;
    }

    private int h(int i) {
        if (i < 0) {
            return -1;
        }
        int iG = this.f1758a.g();
        int i2 = i;
        while (i2 < iG) {
            int iB = i - (i2 - this.f1759b.b(i2));
            if (iB == 0) {
                while (this.f1759b.d(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += iB;
        }
        return -1;
    }

    private void l(View view) {
        this.f1760c.add(view);
        this.f1758a.b(view);
    }

    private boolean t(View view) {
        if (!this.f1760c.remove(view)) {
            return false;
        }
        this.f1758a.e(view);
        return true;
    }

    void a(View view, int i, boolean z) {
        int iG = i < 0 ? this.f1758a.g() : h(i);
        this.f1759b.e(iG, z);
        if (z) {
            l(view);
        }
        this.f1758a.f(view, iG);
    }

    void b(View view, boolean z) {
        a(view, -1, z);
    }

    void c(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        int iG = i < 0 ? this.f1758a.g() : h(i);
        this.f1759b.e(iG, z);
        if (z) {
            l(view);
        }
        this.f1758a.j(view, iG, layoutParams);
    }

    void d(int i) {
        int iH = h(i);
        this.f1759b.f(iH);
        this.f1758a.d(iH);
    }

    View e(int i) {
        int size = this.f1760c.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = this.f1760c.get(i2);
            RecyclerView.c0 c0VarC = this.f1758a.c(view);
            if (c0VarC.m() == i && !c0VarC.t() && !c0VarC.v()) {
                return view;
            }
        }
        return null;
    }

    View f(int i) {
        return this.f1758a.a(h(i));
    }

    int g() {
        return this.f1758a.g() - this.f1760c.size();
    }

    View i(int i) {
        return this.f1758a.a(i);
    }

    int j() {
        return this.f1758a.g();
    }

    void k(View view) {
        int iK = this.f1758a.k(view);
        if (iK >= 0) {
            this.f1759b.h(iK);
            l(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    int m(View view) {
        int iK = this.f1758a.k(view);
        if (iK == -1 || this.f1759b.d(iK)) {
            return -1;
        }
        return iK - this.f1759b.b(iK);
    }

    boolean n(View view) {
        return this.f1760c.contains(view);
    }

    void o() {
        this.f1759b.g();
        for (int size = this.f1760c.size() - 1; size >= 0; size--) {
            this.f1758a.e(this.f1760c.get(size));
            this.f1760c.remove(size);
        }
        this.f1758a.i();
    }

    void p(View view) {
        int iK = this.f1758a.k(view);
        if (iK < 0) {
            return;
        }
        if (this.f1759b.f(iK)) {
            t(view);
        }
        this.f1758a.h(iK);
    }

    void q(int i) {
        int iH = h(i);
        View viewA = this.f1758a.a(iH);
        if (viewA == null) {
            return;
        }
        if (this.f1759b.f(iH)) {
            t(viewA);
        }
        this.f1758a.h(iH);
    }

    boolean r(View view) {
        int iK = this.f1758a.k(view);
        if (iK == -1) {
            t(view);
            return true;
        }
        if (!this.f1759b.d(iK)) {
            return false;
        }
        this.f1759b.f(iK);
        t(view);
        this.f1758a.h(iK);
        return true;
    }

    void s(View view) {
        int iK = this.f1758a.k(view);
        if (iK < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        if (this.f1759b.d(iK)) {
            this.f1759b.a(iK);
            t(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public String toString() {
        return this.f1759b.toString() + ", hidden list:" + this.f1760c.size();
    }
}
