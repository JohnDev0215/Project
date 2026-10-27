package b.o.a;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ChildHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0035b f1338a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f1339b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List<View> f1340c = new ArrayList();

    /* JADX INFO: compiled from: ChildHelper.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f1341a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public a f1342b;

        public void a(int i) {
            if (i < 64) {
                this.f1341a &= ~(1 << i);
                return;
            }
            a aVar = this.f1342b;
            if (aVar != null) {
                aVar.a(i - 64);
            }
        }

        public int b(int i) {
            a aVar = this.f1342b;
            return aVar == null ? i >= 64 ? Long.bitCount(this.f1341a) : Long.bitCount(this.f1341a & ((1 << i) - 1)) : i < 64 ? Long.bitCount(this.f1341a & ((1 << i) - 1)) : aVar.b(i - 64) + Long.bitCount(this.f1341a);
        }

        public final void c() {
            if (this.f1342b == null) {
                this.f1342b = new a();
            }
        }

        public boolean d(int i) {
            if (i < 64) {
                return (this.f1341a & (1 << i)) != 0;
            }
            c();
            return this.f1342b.d(i - 64);
        }

        public void e(int i, boolean z) {
            if (i >= 64) {
                c();
                this.f1342b.e(i - 64, z);
                return;
            }
            long j = this.f1341a;
            boolean z2 = (Long.MIN_VALUE & j) != 0;
            long j2 = (1 << i) - 1;
            this.f1341a = ((j & (~j2)) << 1) | (j & j2);
            if (z) {
                h(i);
            } else {
                a(i);
            }
            if (z2 || this.f1342b != null) {
                c();
                this.f1342b.e(0, z2);
            }
        }

        public boolean f(int i) {
            if (i >= 64) {
                c();
                return this.f1342b.f(i - 64);
            }
            long j = 1 << i;
            long j2 = this.f1341a;
            boolean z = (j2 & j) != 0;
            long j3 = j2 & (~j);
            this.f1341a = j3;
            long j4 = j - 1;
            this.f1341a = (j3 & j4) | Long.rotateRight((~j4) & j3, 1);
            a aVar = this.f1342b;
            if (aVar != null) {
                if (aVar.d(0)) {
                    h(63);
                }
                this.f1342b.f(0);
            }
            return z;
        }

        public void g() {
            this.f1341a = 0L;
            a aVar = this.f1342b;
            if (aVar != null) {
                aVar.g();
            }
        }

        public void h(int i) {
            if (i < 64) {
                this.f1341a |= 1 << i;
            } else {
                c();
                this.f1342b.h(i - 64);
            }
        }

        public String toString() {
            if (this.f1342b == null) {
                return Long.toBinaryString(this.f1341a);
            }
            return this.f1342b.toString() + "xx" + Long.toBinaryString(this.f1341a);
        }
    }

    /* JADX INFO: renamed from: b.o.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChildHelper.java */
    public interface InterfaceC0035b {
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

    public b(InterfaceC0035b interfaceC0035b) {
        this.f1338a = interfaceC0035b;
    }

    public void a(View view, int i, boolean z) {
        int iK = i < 0 ? this.f1338a.k() : h(i);
        this.f1339b.e(iK, z);
        if (z) {
            l(view);
        }
        this.f1338a.i(view, iK);
    }

    public void b(View view, boolean z) {
        a(view, -1, z);
    }

    public void c(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        int iK = i < 0 ? this.f1338a.k() : h(i);
        this.f1339b.e(iK, z);
        if (z) {
            l(view);
        }
        this.f1338a.h(view, iK, layoutParams);
    }

    public void d(int i) {
        int iH = h(i);
        this.f1339b.f(iH);
        this.f1338a.f(iH);
    }

    public View e(int i) {
        int size = this.f1340c.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = this.f1340c.get(i2);
            RecyclerView.c0 c0VarE = this.f1338a.e(view);
            if (c0VarE.m() == i && !c0VarE.t() && !c0VarE.v()) {
                return view;
            }
        }
        return null;
    }

    public View f(int i) {
        return this.f1338a.a(h(i));
    }

    public int g() {
        return this.f1338a.k() - this.f1340c.size();
    }

    public final int h(int i) {
        if (i < 0) {
            return -1;
        }
        int iK = this.f1338a.k();
        int i2 = i;
        while (i2 < iK) {
            int iB = i - (i2 - this.f1339b.b(i2));
            if (iB == 0) {
                while (this.f1339b.d(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += iB;
        }
        return -1;
    }

    public View i(int i) {
        return this.f1338a.a(i);
    }

    public int j() {
        return this.f1338a.k();
    }

    public void k(View view) {
        int iJ = this.f1338a.j(view);
        if (iJ >= 0) {
            this.f1339b.h(iJ);
            l(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    public final void l(View view) {
        this.f1340c.add(view);
        this.f1338a.b(view);
    }

    public int m(View view) {
        int iJ = this.f1338a.j(view);
        if (iJ == -1 || this.f1339b.d(iJ)) {
            return -1;
        }
        return iJ - this.f1339b.b(iJ);
    }

    public boolean n(View view) {
        return this.f1340c.contains(view);
    }

    public void o() {
        this.f1339b.g();
        for (int size = this.f1340c.size() - 1; size >= 0; size--) {
            this.f1338a.g(this.f1340c.get(size));
            this.f1340c.remove(size);
        }
        this.f1338a.d();
    }

    public void p(View view) {
        int iJ = this.f1338a.j(view);
        if (iJ < 0) {
            return;
        }
        if (this.f1339b.f(iJ)) {
            t(view);
        }
        this.f1338a.c(iJ);
    }

    public void q(int i) {
        int iH = h(i);
        View viewA = this.f1338a.a(iH);
        if (viewA == null) {
            return;
        }
        if (this.f1339b.f(iH)) {
            t(viewA);
        }
        this.f1338a.c(iH);
    }

    public boolean r(View view) {
        int iJ = this.f1338a.j(view);
        if (iJ == -1) {
            t(view);
            return true;
        }
        if (!this.f1339b.d(iJ)) {
            return false;
        }
        this.f1339b.f(iJ);
        t(view);
        this.f1338a.c(iJ);
        return true;
    }

    public void s(View view) {
        int iJ = this.f1338a.j(view);
        if (iJ < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        if (this.f1339b.d(iJ)) {
            this.f1339b.a(iJ);
            t(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public final boolean t(View view) {
        if (!this.f1340c.remove(view)) {
            return false;
        }
        this.f1338a.g(view);
        return true;
    }

    public String toString() {
        return this.f1339b.toString() + ", hidden list:" + this.f1340c.size();
    }
}
