package c1;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ChildHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0009b f2157a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f2158b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List<View> f2159c = new ArrayList();

    /* JADX INFO: compiled from: ChildHelper.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f2160a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public a f2161b;

        public void a(int i8) {
            if (i8 < 64) {
                this.f2160a &= ~(1 << i8);
                return;
            }
            a aVar = this.f2161b;
            if (aVar != null) {
                aVar.a(i8 - 64);
            }
        }

        public int b(int i8) {
            a aVar = this.f2161b;
            return aVar == null ? i8 >= 64 ? Long.bitCount(this.f2160a) : Long.bitCount(this.f2160a & ((1 << i8) - 1)) : i8 < 64 ? Long.bitCount(this.f2160a & ((1 << i8) - 1)) : aVar.b(i8 - 64) + Long.bitCount(this.f2160a);
        }

        public final void c() {
            if (this.f2161b == null) {
                this.f2161b = new a();
            }
        }

        public boolean d(int i8) {
            if (i8 < 64) {
                return (this.f2160a & (1 << i8)) != 0;
            }
            c();
            return this.f2161b.d(i8 - 64);
        }

        public void e(int i8, boolean z7) {
            if (i8 >= 64) {
                c();
                this.f2161b.e(i8 - 64, z7);
                return;
            }
            long j8 = this.f2160a;
            boolean z8 = (Long.MIN_VALUE & j8) != 0;
            long j9 = (1 << i8) - 1;
            this.f2160a = ((j8 & (~j9)) << 1) | (j8 & j9);
            if (z7) {
                h(i8);
            } else {
                a(i8);
            }
            if (z8 || this.f2161b != null) {
                c();
                this.f2161b.e(0, z8);
            }
        }

        public boolean f(int i8) {
            if (i8 >= 64) {
                c();
                return this.f2161b.f(i8 - 64);
            }
            long j8 = 1 << i8;
            long j9 = this.f2160a;
            boolean z7 = (j9 & j8) != 0;
            long j10 = j9 & (~j8);
            this.f2160a = j10;
            long j11 = j8 - 1;
            this.f2160a = (j10 & j11) | Long.rotateRight((~j11) & j10, 1);
            a aVar = this.f2161b;
            if (aVar != null) {
                if (aVar.d(0)) {
                    h(63);
                }
                this.f2161b.f(0);
            }
            return z7;
        }

        public void g() {
            this.f2160a = 0L;
            a aVar = this.f2161b;
            if (aVar != null) {
                aVar.g();
            }
        }

        public void h(int i8) {
            if (i8 < 64) {
                this.f2160a |= 1 << i8;
            } else {
                c();
                this.f2161b.h(i8 - 64);
            }
        }

        public String toString() {
            if (this.f2161b == null) {
                return Long.toBinaryString(this.f2160a);
            }
            return this.f2161b.toString() + "xx" + Long.toBinaryString(this.f2160a);
        }
    }

    /* JADX INFO: renamed from: c1.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChildHelper.java */
    public interface InterfaceC0009b {
        View a(int i8);

        void b(View view);

        RecyclerView.c0 c(View view);

        void d(int i8);

        void e(View view);

        void f(View view, int i8);

        int g();

        void h(int i8);

        void i();

        void j(View view, int i8, ViewGroup.LayoutParams layoutParams);

        int k(View view);
    }

    public b(InterfaceC0009b interfaceC0009b) {
        this.f2157a = interfaceC0009b;
    }

    public void a(View view, int i8, boolean z7) {
        int iG = i8 < 0 ? this.f2157a.g() : h(i8);
        this.f2158b.e(iG, z7);
        if (z7) {
            l(view);
        }
        this.f2157a.f(view, iG);
    }

    public void b(View view, boolean z7) {
        a(view, -1, z7);
    }

    public void c(View view, int i8, ViewGroup.LayoutParams layoutParams, boolean z7) {
        int iG = i8 < 0 ? this.f2157a.g() : h(i8);
        this.f2158b.e(iG, z7);
        if (z7) {
            l(view);
        }
        this.f2157a.j(view, iG, layoutParams);
    }

    public void d(int i8) {
        int iH = h(i8);
        this.f2158b.f(iH);
        this.f2157a.d(iH);
    }

    public View e(int i8) {
        int size = this.f2159c.size();
        for (int i9 = 0; i9 < size; i9++) {
            View view = this.f2159c.get(i9);
            RecyclerView.c0 c0VarC = this.f2157a.c(view);
            if (c0VarC.getLayoutPosition() == i8 && !c0VarC.isInvalid() && !c0VarC.isRemoved()) {
                return view;
            }
        }
        return null;
    }

    public View f(int i8) {
        return this.f2157a.a(h(i8));
    }

    public int g() {
        return this.f2157a.g() - this.f2159c.size();
    }

    public final int h(int i8) {
        if (i8 < 0) {
            return -1;
        }
        int iG = this.f2157a.g();
        int i9 = i8;
        while (i9 < iG) {
            int iB = i8 - (i9 - this.f2158b.b(i9));
            if (iB == 0) {
                while (this.f2158b.d(i9)) {
                    i9++;
                }
                return i9;
            }
            i9 += iB;
        }
        return -1;
    }

    public View i(int i8) {
        return this.f2157a.a(i8);
    }

    public int j() {
        return this.f2157a.g();
    }

    public void k(View view) {
        int iK = this.f2157a.k(view);
        if (iK >= 0) {
            this.f2158b.h(iK);
            l(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    public final void l(View view) {
        this.f2159c.add(view);
        this.f2157a.b(view);
    }

    public int m(View view) {
        int iK = this.f2157a.k(view);
        if (iK == -1 || this.f2158b.d(iK)) {
            return -1;
        }
        return iK - this.f2158b.b(iK);
    }

    public boolean n(View view) {
        return this.f2159c.contains(view);
    }

    public void o() {
        this.f2158b.g();
        for (int size = this.f2159c.size() - 1; size >= 0; size--) {
            this.f2157a.e(this.f2159c.get(size));
            this.f2159c.remove(size);
        }
        this.f2157a.i();
    }

    public void p(View view) {
        int iK = this.f2157a.k(view);
        if (iK < 0) {
            return;
        }
        if (this.f2158b.f(iK)) {
            t(view);
        }
        this.f2157a.h(iK);
    }

    public void q(int i8) {
        int iH = h(i8);
        View viewA = this.f2157a.a(iH);
        if (viewA == null) {
            return;
        }
        if (this.f2158b.f(iH)) {
            t(viewA);
        }
        this.f2157a.h(iH);
    }

    public boolean r(View view) {
        int iK = this.f2157a.k(view);
        if (iK == -1) {
            t(view);
            return true;
        }
        if (!this.f2158b.d(iK)) {
            return false;
        }
        this.f2158b.f(iK);
        t(view);
        this.f2157a.h(iK);
        return true;
    }

    public void s(View view) {
        int iK = this.f2157a.k(view);
        if (iK < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        if (this.f2158b.d(iK)) {
            this.f2158b.a(iK);
            t(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public final boolean t(View view) {
        if (!this.f2159c.remove(view)) {
            return false;
        }
        this.f2157a.e(view);
        return true;
    }

    public String toString() {
        return this.f2158b.toString() + ", hidden list:" + this.f2159c.size();
    }
}
