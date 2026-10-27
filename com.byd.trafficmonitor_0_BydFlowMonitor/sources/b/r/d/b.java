package b.r.d;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ChildHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0057b f3027a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f3028b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List<View> f3029c = new ArrayList();

    /* JADX INFO: compiled from: ChildHelper.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f3030a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public a f3031b;

        public void a(int i2) {
            if (i2 < 64) {
                this.f3030a &= ~(1 << i2);
                return;
            }
            a aVar = this.f3031b;
            if (aVar != null) {
                aVar.a(i2 - 64);
            }
        }

        public int b(int i2) {
            a aVar = this.f3031b;
            return aVar == null ? i2 >= 64 ? Long.bitCount(this.f3030a) : Long.bitCount(this.f3030a & ((1 << i2) - 1)) : i2 < 64 ? Long.bitCount(this.f3030a & ((1 << i2) - 1)) : aVar.b(i2 - 64) + Long.bitCount(this.f3030a);
        }

        public final void c() {
            if (this.f3031b == null) {
                this.f3031b = new a();
            }
        }

        public boolean d(int i2) {
            if (i2 < 64) {
                return (this.f3030a & (1 << i2)) != 0;
            }
            c();
            return this.f3031b.d(i2 - 64);
        }

        public void e(int i2, boolean z) {
            if (i2 >= 64) {
                c();
                this.f3031b.e(i2 - 64, z);
                return;
            }
            long j = this.f3030a;
            boolean z2 = (Long.MIN_VALUE & j) != 0;
            long j2 = (1 << i2) - 1;
            this.f3030a = ((j & (~j2)) << 1) | (j & j2);
            if (z) {
                h(i2);
            } else {
                a(i2);
            }
            if (z2 || this.f3031b != null) {
                c();
                this.f3031b.e(0, z2);
            }
        }

        public boolean f(int i2) {
            if (i2 >= 64) {
                c();
                return this.f3031b.f(i2 - 64);
            }
            long j = 1 << i2;
            long j2 = this.f3030a;
            boolean z = (j2 & j) != 0;
            long j3 = j2 & (~j);
            this.f3030a = j3;
            long j4 = j - 1;
            this.f3030a = (j3 & j4) | Long.rotateRight((~j4) & j3, 1);
            a aVar = this.f3031b;
            if (aVar != null) {
                if (aVar.d(0)) {
                    h(63);
                }
                this.f3031b.f(0);
            }
            return z;
        }

        public void g() {
            this.f3030a = 0L;
            a aVar = this.f3031b;
            if (aVar != null) {
                aVar.g();
            }
        }

        public void h(int i2) {
            if (i2 < 64) {
                this.f3030a |= 1 << i2;
            } else {
                c();
                this.f3031b.h(i2 - 64);
            }
        }

        public String toString() {
            if (this.f3031b == null) {
                return Long.toBinaryString(this.f3030a);
            }
            return this.f3031b.toString() + "xx" + Long.toBinaryString(this.f3030a);
        }
    }

    /* JADX INFO: renamed from: b.r.d.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChildHelper.java */
    public interface InterfaceC0057b {
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

    public b(InterfaceC0057b interfaceC0057b) {
        this.f3027a = interfaceC0057b;
    }

    public void a(View view, int i2, boolean z) {
        int iK = i2 < 0 ? this.f3027a.k() : h(i2);
        this.f3028b.e(iK, z);
        if (z) {
            l(view);
        }
        this.f3027a.i(view, iK);
    }

    public void b(View view, boolean z) {
        a(view, -1, z);
    }

    public void c(View view, int i2, ViewGroup.LayoutParams layoutParams, boolean z) {
        int iK = i2 < 0 ? this.f3027a.k() : h(i2);
        this.f3028b.e(iK, z);
        if (z) {
            l(view);
        }
        this.f3027a.h(view, iK, layoutParams);
    }

    public void d(int i2) {
        int iH = h(i2);
        this.f3028b.f(iH);
        this.f3027a.f(iH);
    }

    public View e(int i2) {
        int size = this.f3029c.size();
        for (int i3 = 0; i3 < size; i3++) {
            View view = this.f3029c.get(i3);
            RecyclerView.c0 c0VarE = this.f3027a.e(view);
            if (c0VarE.m() == i2 && !c0VarE.t() && !c0VarE.v()) {
                return view;
            }
        }
        return null;
    }

    public View f(int i2) {
        return this.f3027a.a(h(i2));
    }

    public int g() {
        return this.f3027a.k() - this.f3029c.size();
    }

    public final int h(int i2) {
        if (i2 < 0) {
            return -1;
        }
        int iK = this.f3027a.k();
        int i3 = i2;
        while (i3 < iK) {
            int iB = i2 - (i3 - this.f3028b.b(i3));
            if (iB == 0) {
                while (this.f3028b.d(i3)) {
                    i3++;
                }
                return i3;
            }
            i3 += iB;
        }
        return -1;
    }

    public View i(int i2) {
        return this.f3027a.a(i2);
    }

    public int j() {
        return this.f3027a.k();
    }

    public void k(View view) {
        int iJ = this.f3027a.j(view);
        if (iJ >= 0) {
            this.f3028b.h(iJ);
            l(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    public final void l(View view) {
        this.f3029c.add(view);
        this.f3027a.b(view);
    }

    public int m(View view) {
        int iJ = this.f3027a.j(view);
        if (iJ == -1 || this.f3028b.d(iJ)) {
            return -1;
        }
        return iJ - this.f3028b.b(iJ);
    }

    public boolean n(View view) {
        return this.f3029c.contains(view);
    }

    public void o() {
        this.f3028b.g();
        for (int size = this.f3029c.size() - 1; size >= 0; size--) {
            this.f3027a.g(this.f3029c.get(size));
            this.f3029c.remove(size);
        }
        this.f3027a.d();
    }

    public void p(View view) {
        int iJ = this.f3027a.j(view);
        if (iJ < 0) {
            return;
        }
        if (this.f3028b.f(iJ)) {
            t(view);
        }
        this.f3027a.c(iJ);
    }

    public void q(int i2) {
        int iH = h(i2);
        View viewA = this.f3027a.a(iH);
        if (viewA == null) {
            return;
        }
        if (this.f3028b.f(iH)) {
            t(viewA);
        }
        this.f3027a.c(iH);
    }

    public boolean r(View view) {
        int iJ = this.f3027a.j(view);
        if (iJ == -1) {
            t(view);
            return true;
        }
        if (!this.f3028b.d(iJ)) {
            return false;
        }
        this.f3028b.f(iJ);
        t(view);
        this.f3027a.c(iJ);
        return true;
    }

    public void s(View view) {
        int iJ = this.f3027a.j(view);
        if (iJ < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        if (this.f3028b.d(iJ)) {
            this.f3028b.a(iJ);
            t(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public final boolean t(View view) {
        if (!this.f3029c.remove(view)) {
            return false;
        }
        this.f3027a.g(view);
        return true;
    }

    public String toString() {
        return this.f3028b.toString() + ", hidden list:" + this.f3029c.size();
    }
}
