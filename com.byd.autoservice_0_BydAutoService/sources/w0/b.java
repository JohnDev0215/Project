package w0;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ChildHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0069b f5529a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f5530b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List<View> f5531c = new ArrayList();

    /* JADX INFO: compiled from: ChildHelper.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f5532a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public a f5533b;

        public void a(int i3) {
            if (i3 < 64) {
                this.f5532a &= ~(1 << i3);
                return;
            }
            a aVar = this.f5533b;
            if (aVar != null) {
                aVar.a(i3 - 64);
            }
        }

        public int b(int i3) {
            a aVar = this.f5533b;
            return aVar == null ? i3 >= 64 ? Long.bitCount(this.f5532a) : Long.bitCount(this.f5532a & ((1 << i3) - 1)) : i3 < 64 ? Long.bitCount(this.f5532a & ((1 << i3) - 1)) : aVar.b(i3 - 64) + Long.bitCount(this.f5532a);
        }

        public final void c() {
            if (this.f5533b == null) {
                this.f5533b = new a();
            }
        }

        public boolean d(int i3) {
            if (i3 < 64) {
                return (this.f5532a & (1 << i3)) != 0;
            }
            c();
            return this.f5533b.d(i3 - 64);
        }

        public void e(int i3, boolean z3) {
            if (i3 >= 64) {
                c();
                this.f5533b.e(i3 - 64, z3);
                return;
            }
            long j3 = this.f5532a;
            boolean z4 = (Long.MIN_VALUE & j3) != 0;
            long j4 = (1 << i3) - 1;
            this.f5532a = ((j3 & (~j4)) << 1) | (j3 & j4);
            if (z3) {
                h(i3);
            } else {
                a(i3);
            }
            if (z4 || this.f5533b != null) {
                c();
                this.f5533b.e(0, z4);
            }
        }

        public boolean f(int i3) {
            if (i3 >= 64) {
                c();
                return this.f5533b.f(i3 - 64);
            }
            long j3 = 1 << i3;
            long j4 = this.f5532a;
            boolean z3 = (j4 & j3) != 0;
            long j5 = j4 & (~j3);
            this.f5532a = j5;
            long j6 = j3 - 1;
            this.f5532a = (j5 & j6) | Long.rotateRight((~j6) & j5, 1);
            a aVar = this.f5533b;
            if (aVar != null) {
                if (aVar.d(0)) {
                    h(63);
                }
                this.f5533b.f(0);
            }
            return z3;
        }

        public void g() {
            this.f5532a = 0L;
            a aVar = this.f5533b;
            if (aVar != null) {
                aVar.g();
            }
        }

        public void h(int i3) {
            if (i3 < 64) {
                this.f5532a |= 1 << i3;
            } else {
                c();
                this.f5533b.h(i3 - 64);
            }
        }

        public String toString() {
            if (this.f5533b == null) {
                return Long.toBinaryString(this.f5532a);
            }
            return this.f5533b.toString() + "xx" + Long.toBinaryString(this.f5532a);
        }
    }

    /* JADX INFO: renamed from: w0.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChildHelper.java */
    public interface InterfaceC0069b {
        View a(int i3);

        void b(View view);

        void c(int i3);

        void d();

        RecyclerView.c0 e(View view);

        void f(int i3);

        void g(View view);

        void h(View view, int i3, ViewGroup.LayoutParams layoutParams);

        void i(View view, int i3);

        int j(View view);

        int k();
    }

    public b(InterfaceC0069b interfaceC0069b) {
        this.f5529a = interfaceC0069b;
    }

    public void a(View view, int i3, boolean z3) {
        int iK = i3 < 0 ? this.f5529a.k() : h(i3);
        this.f5530b.e(iK, z3);
        if (z3) {
            l(view);
        }
        this.f5529a.i(view, iK);
    }

    public void b(View view, boolean z3) {
        a(view, -1, z3);
    }

    public void c(View view, int i3, ViewGroup.LayoutParams layoutParams, boolean z3) {
        int iK = i3 < 0 ? this.f5529a.k() : h(i3);
        this.f5530b.e(iK, z3);
        if (z3) {
            l(view);
        }
        this.f5529a.h(view, iK, layoutParams);
    }

    public void d(int i3) {
        int iH = h(i3);
        this.f5530b.f(iH);
        this.f5529a.f(iH);
    }

    public View e(int i3) {
        int size = this.f5531c.size();
        for (int i4 = 0; i4 < size; i4++) {
            View view = this.f5531c.get(i4);
            RecyclerView.c0 c0VarE = this.f5529a.e(view);
            if (c0VarE.m() == i3 && !c0VarE.t() && !c0VarE.v()) {
                return view;
            }
        }
        return null;
    }

    public View f(int i3) {
        return this.f5529a.a(h(i3));
    }

    public int g() {
        return this.f5529a.k() - this.f5531c.size();
    }

    public final int h(int i3) {
        if (i3 < 0) {
            return -1;
        }
        int iK = this.f5529a.k();
        int i4 = i3;
        while (i4 < iK) {
            int iB = i3 - (i4 - this.f5530b.b(i4));
            if (iB == 0) {
                while (this.f5530b.d(i4)) {
                    i4++;
                }
                return i4;
            }
            i4 += iB;
        }
        return -1;
    }

    public View i(int i3) {
        return this.f5529a.a(i3);
    }

    public int j() {
        return this.f5529a.k();
    }

    public void k(View view) {
        int iJ = this.f5529a.j(view);
        if (iJ >= 0) {
            this.f5530b.h(iJ);
            l(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    public final void l(View view) {
        this.f5531c.add(view);
        this.f5529a.b(view);
    }

    public int m(View view) {
        int iJ = this.f5529a.j(view);
        if (iJ == -1 || this.f5530b.d(iJ)) {
            return -1;
        }
        return iJ - this.f5530b.b(iJ);
    }

    public boolean n(View view) {
        return this.f5531c.contains(view);
    }

    public void o() {
        this.f5530b.g();
        for (int size = this.f5531c.size() - 1; size >= 0; size--) {
            this.f5529a.g(this.f5531c.get(size));
            this.f5531c.remove(size);
        }
        this.f5529a.d();
    }

    public void p(View view) {
        int iJ = this.f5529a.j(view);
        if (iJ < 0) {
            return;
        }
        if (this.f5530b.f(iJ)) {
            t(view);
        }
        this.f5529a.c(iJ);
    }

    public void q(int i3) {
        int iH = h(i3);
        View viewA = this.f5529a.a(iH);
        if (viewA == null) {
            return;
        }
        if (this.f5530b.f(iH)) {
            t(viewA);
        }
        this.f5529a.c(iH);
    }

    public boolean r(View view) {
        int iJ = this.f5529a.j(view);
        if (iJ == -1) {
            t(view);
            return true;
        }
        if (!this.f5530b.d(iJ)) {
            return false;
        }
        this.f5530b.f(iJ);
        t(view);
        this.f5529a.c(iJ);
        return true;
    }

    public void s(View view) {
        int iJ = this.f5529a.j(view);
        if (iJ < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        if (this.f5530b.d(iJ)) {
            this.f5530b.a(iJ);
            t(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public final boolean t(View view) {
        if (!this.f5531c.remove(view)) {
            return false;
        }
        this.f5529a.g(view);
        return true;
    }

    public String toString() {
        return this.f5530b.toString() + ", hidden list:" + this.f5531c.size();
    }
}
