package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final b f1160a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final a f1161b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final List<View> f1162c = new ArrayList();

    /* JADX INFO: compiled from: caradapter-bug */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        long f1163a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        a f1164b;

        a() {
        }

        private void c() {
            if (this.f1164b == null) {
                this.f1164b = new a();
            }
        }

        void a(int i) {
            if (i < 64) {
                this.f1163a &= ~(1 << i);
                return;
            }
            a aVar = this.f1164b;
            if (aVar != null) {
                aVar.a(i - 64);
            }
        }

        int b(int i) {
            a aVar = this.f1164b;
            if (aVar == null) {
                if (i >= 64) {
                    return Long.bitCount(this.f1163a);
                }
                return Long.bitCount(((1 << i) - 1) & this.f1163a);
            }
            if (i >= 64) {
                return aVar.b(i - 64) + Long.bitCount(this.f1163a);
            }
            return Long.bitCount(((1 << i) - 1) & this.f1163a);
        }

        boolean d(int i) {
            if (i < 64) {
                return ((1 << i) & this.f1163a) != 0;
            }
            c();
            return this.f1164b.d(i - 64);
        }

        void e(int i, boolean z) {
            if (i >= 64) {
                c();
                this.f1164b.e(i - 64, z);
                return;
            }
            boolean z2 = (this.f1163a & Long.MIN_VALUE) != 0;
            long j = (1 << i) - 1;
            long j2 = this.f1163a;
            this.f1163a = ((j2 & (~j)) << 1) | (j2 & j);
            if (z) {
                h(i);
            } else {
                a(i);
            }
            if (z2 || this.f1164b != null) {
                c();
                this.f1164b.e(0, z2);
            }
        }

        boolean f(int i) {
            if (i >= 64) {
                c();
                return this.f1164b.f(i - 64);
            }
            long j = 1 << i;
            boolean z = (this.f1163a & j) != 0;
            long j2 = this.f1163a & (~j);
            this.f1163a = j2;
            long j3 = j - 1;
            this.f1163a = (j2 & j3) | Long.rotateRight((~j3) & j2, 1);
            a aVar = this.f1164b;
            if (aVar != null) {
                if (aVar.d(0)) {
                    h(63);
                }
                this.f1164b.f(0);
            }
            return z;
        }

        void g() {
            this.f1163a = 0L;
            a aVar = this.f1164b;
            if (aVar != null) {
                aVar.g();
            }
        }

        void h(int i) {
            if (i < 64) {
                this.f1163a |= 1 << i;
            } else {
                c();
                this.f1164b.h(i - 64);
            }
        }

        public String toString() {
            if (this.f1164b == null) {
                return Long.toBinaryString(this.f1163a);
            }
            return this.f1164b.toString() + "xx" + Long.toBinaryString(this.f1163a);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    interface b {
        View a(int i);

        void b(View view);

        void c(int i);

        void d();

        RecyclerView.A e(View view);

        void f(int i);

        void g(View view);

        void h(View view, int i, ViewGroup.LayoutParams layoutParams);

        void i(View view, int i);

        int j(View view);

        int k();
    }

    d(b bVar) {
        this.f1160a = bVar;
    }

    private int h(int i) {
        if (i < 0) {
            return -1;
        }
        int iK = this.f1160a.k();
        int i2 = i;
        while (i2 < iK) {
            int iB = i - (i2 - this.f1161b.b(i2));
            if (iB == 0) {
                while (this.f1161b.d(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += iB;
        }
        return -1;
    }

    private void l(View view) {
        this.f1162c.add(view);
        this.f1160a.b(view);
    }

    private boolean t(View view) {
        if (!this.f1162c.remove(view)) {
            return false;
        }
        this.f1160a.g(view);
        return true;
    }

    void a(View view, int i, boolean z) {
        int iK = i < 0 ? this.f1160a.k() : h(i);
        this.f1161b.e(iK, z);
        if (z) {
            l(view);
        }
        this.f1160a.i(view, iK);
    }

    void b(View view, boolean z) {
        a(view, -1, z);
    }

    void c(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        int iK = i < 0 ? this.f1160a.k() : h(i);
        this.f1161b.e(iK, z);
        if (z) {
            l(view);
        }
        this.f1160a.h(view, iK, layoutParams);
    }

    void d(int i) {
        int iH = h(i);
        this.f1161b.f(iH);
        this.f1160a.f(iH);
    }

    View e(int i) {
        int size = this.f1162c.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = this.f1162c.get(i2);
            RecyclerView.A aE = this.f1160a.e(view);
            if (aE.m() == i && !aE.t() && !aE.v()) {
                return view;
            }
        }
        return null;
    }

    View f(int i) {
        return this.f1160a.a(h(i));
    }

    int g() {
        return this.f1160a.k() - this.f1162c.size();
    }

    View i(int i) {
        return this.f1160a.a(i);
    }

    int j() {
        return this.f1160a.k();
    }

    void k(View view) {
        int iJ = this.f1160a.j(view);
        if (iJ >= 0) {
            this.f1161b.h(iJ);
            l(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    int m(View view) {
        int iJ = this.f1160a.j(view);
        if (iJ == -1 || this.f1161b.d(iJ)) {
            return -1;
        }
        return iJ - this.f1161b.b(iJ);
    }

    boolean n(View view) {
        return this.f1162c.contains(view);
    }

    void o() {
        this.f1161b.g();
        for (int size = this.f1162c.size() - 1; size >= 0; size--) {
            this.f1160a.g(this.f1162c.get(size));
            this.f1162c.remove(size);
        }
        this.f1160a.d();
    }

    void p(View view) {
        int iJ = this.f1160a.j(view);
        if (iJ < 0) {
            return;
        }
        if (this.f1161b.f(iJ)) {
            t(view);
        }
        this.f1160a.c(iJ);
    }

    void q(int i) {
        int iH = h(i);
        View viewA = this.f1160a.a(iH);
        if (viewA == null) {
            return;
        }
        if (this.f1161b.f(iH)) {
            t(viewA);
        }
        this.f1160a.c(iH);
    }

    boolean r(View view) {
        int iJ = this.f1160a.j(view);
        if (iJ == -1) {
            t(view);
            return true;
        }
        if (!this.f1161b.d(iJ)) {
            return false;
        }
        this.f1161b.f(iJ);
        t(view);
        this.f1160a.c(iJ);
        return true;
    }

    void s(View view) {
        int iJ = this.f1160a.j(view);
        if (iJ < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        if (this.f1161b.d(iJ)) {
            this.f1161b.a(iJ);
            t(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public String toString() {
        return this.f1161b.toString() + ", hidden list:" + this.f1162c.size();
    }
}
