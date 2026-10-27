package b.n.d;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ChildHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0039b f2020a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f2021b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List<View> f2022c = new ArrayList();

    /* JADX INFO: compiled from: ChildHelper.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f2023a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public a f2024b;

        public void a(int i2) {
            if (i2 < 64) {
                this.f2023a &= ~(1 << i2);
                return;
            }
            a aVar = this.f2024b;
            if (aVar != null) {
                aVar.a(i2 - 64);
            }
        }

        public int b(int i2) {
            a aVar = this.f2024b;
            return aVar == null ? i2 >= 64 ? Long.bitCount(this.f2023a) : Long.bitCount(this.f2023a & ((1 << i2) - 1)) : i2 < 64 ? Long.bitCount(this.f2023a & ((1 << i2) - 1)) : aVar.b(i2 - 64) + Long.bitCount(this.f2023a);
        }

        public final void c() {
            if (this.f2024b == null) {
                this.f2024b = new a();
            }
        }

        public boolean d(int i2) {
            if (i2 < 64) {
                return (this.f2023a & (1 << i2)) != 0;
            }
            c();
            return this.f2024b.d(i2 - 64);
        }

        public void e(int i2, boolean z) {
            if (i2 >= 64) {
                c();
                this.f2024b.e(i2 - 64, z);
                return;
            }
            long j = this.f2023a;
            boolean z2 = (Long.MIN_VALUE & j) != 0;
            long j2 = (1 << i2) - 1;
            this.f2023a = ((j & (~j2)) << 1) | (j & j2);
            if (z) {
                h(i2);
            } else {
                a(i2);
            }
            if (z2 || this.f2024b != null) {
                c();
                this.f2024b.e(0, z2);
            }
        }

        public boolean f(int i2) {
            if (i2 >= 64) {
                c();
                return this.f2024b.f(i2 - 64);
            }
            long j = 1 << i2;
            long j2 = this.f2023a;
            boolean z = (j2 & j) != 0;
            long j3 = j2 & (~j);
            this.f2023a = j3;
            long j4 = j - 1;
            this.f2023a = (j3 & j4) | Long.rotateRight((~j4) & j3, 1);
            a aVar = this.f2024b;
            if (aVar != null) {
                if (aVar.d(0)) {
                    h(63);
                }
                this.f2024b.f(0);
            }
            return z;
        }

        public void g() {
            this.f2023a = 0L;
            a aVar = this.f2024b;
            if (aVar != null) {
                aVar.g();
            }
        }

        public void h(int i2) {
            if (i2 < 64) {
                this.f2023a |= 1 << i2;
            } else {
                c();
                this.f2024b.h(i2 - 64);
            }
        }

        public String toString() {
            if (this.f2024b == null) {
                return Long.toBinaryString(this.f2023a);
            }
            return this.f2024b.toString() + "xx" + Long.toBinaryString(this.f2023a);
        }
    }

    /* JADX INFO: renamed from: b.n.d.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChildHelper.java */
    public interface InterfaceC0039b {
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

    public b(InterfaceC0039b interfaceC0039b) {
        this.f2020a = interfaceC0039b;
    }

    public void a(View view, int i2, boolean z) {
        int iK = i2 < 0 ? this.f2020a.k() : h(i2);
        this.f2021b.e(iK, z);
        if (z) {
            l(view);
        }
        this.f2020a.i(view, iK);
    }

    public void b(View view, boolean z) {
        a(view, -1, z);
    }

    public void c(View view, int i2, ViewGroup.LayoutParams layoutParams, boolean z) {
        int iK = i2 < 0 ? this.f2020a.k() : h(i2);
        this.f2021b.e(iK, z);
        if (z) {
            l(view);
        }
        this.f2020a.h(view, iK, layoutParams);
    }

    public void d(int i2) {
        int iH = h(i2);
        this.f2021b.f(iH);
        this.f2020a.f(iH);
    }

    public View e(int i2) {
        int size = this.f2022c.size();
        for (int i3 = 0; i3 < size; i3++) {
            View view = this.f2022c.get(i3);
            RecyclerView.c0 c0VarE = this.f2020a.e(view);
            if (c0VarE.m() == i2 && !c0VarE.t() && !c0VarE.v()) {
                return view;
            }
        }
        return null;
    }

    public View f(int i2) {
        return this.f2020a.a(h(i2));
    }

    public int g() {
        return this.f2020a.k() - this.f2022c.size();
    }

    public final int h(int i2) {
        if (i2 < 0) {
            return -1;
        }
        int iK = this.f2020a.k();
        int i3 = i2;
        while (i3 < iK) {
            int iB = i2 - (i3 - this.f2021b.b(i3));
            if (iB == 0) {
                while (this.f2021b.d(i3)) {
                    i3++;
                }
                return i3;
            }
            i3 += iB;
        }
        return -1;
    }

    public View i(int i2) {
        return this.f2020a.a(i2);
    }

    public int j() {
        return this.f2020a.k();
    }

    public void k(View view) {
        int iJ = this.f2020a.j(view);
        if (iJ >= 0) {
            this.f2021b.h(iJ);
            l(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    public final void l(View view) {
        this.f2022c.add(view);
        this.f2020a.b(view);
    }

    public int m(View view) {
        int iJ = this.f2020a.j(view);
        if (iJ == -1 || this.f2021b.d(iJ)) {
            return -1;
        }
        return iJ - this.f2021b.b(iJ);
    }

    public boolean n(View view) {
        return this.f2022c.contains(view);
    }

    public void o() {
        this.f2021b.g();
        for (int size = this.f2022c.size() - 1; size >= 0; size--) {
            this.f2020a.g(this.f2022c.get(size));
            this.f2022c.remove(size);
        }
        this.f2020a.d();
    }

    public void p(View view) {
        int iJ = this.f2020a.j(view);
        if (iJ < 0) {
            return;
        }
        if (this.f2021b.f(iJ)) {
            t(view);
        }
        this.f2020a.c(iJ);
    }

    public void q(int i2) {
        int iH = h(i2);
        View viewA = this.f2020a.a(iH);
        if (viewA == null) {
            return;
        }
        if (this.f2021b.f(iH)) {
            t(viewA);
        }
        this.f2020a.c(iH);
    }

    public boolean r(View view) {
        int iJ = this.f2020a.j(view);
        if (iJ == -1) {
            t(view);
            return true;
        }
        if (!this.f2021b.d(iJ)) {
            return false;
        }
        this.f2021b.f(iJ);
        t(view);
        this.f2020a.c(iJ);
        return true;
    }

    public void s(View view) {
        int iJ = this.f2020a.j(view);
        if (iJ < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        if (this.f2021b.d(iJ)) {
            this.f2021b.a(iJ);
            t(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public final boolean t(View view) {
        if (!this.f2022c.remove(view)) {
            return false;
        }
        this.f2020a.g(view);
        return true;
    }

    public String toString() {
        return this.f2021b.toString() + ", hidden list:" + this.f2022c.size();
    }
}
