package a.p.e;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f1144a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f1145b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List<View> f1146c = new ArrayList();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f1147a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public a f1148b;

        public void a(int i) {
            if (i < 64) {
                this.f1147a &= ~(1 << i);
                return;
            }
            a aVar = this.f1148b;
            if (aVar != null) {
                aVar.a(i - 64);
            }
        }

        public int b(int i) {
            a aVar = this.f1148b;
            if (aVar == null) {
                if (i >= 64) {
                    return Long.bitCount(this.f1147a);
                }
                return Long.bitCount(((1 << i) - 1) & this.f1147a);
            }
            if (i < 64) {
                return Long.bitCount(((1 << i) - 1) & this.f1147a);
            }
            return Long.bitCount(this.f1147a) + aVar.b(i - 64);
        }

        public final void c() {
            if (this.f1148b == null) {
                this.f1148b = new a();
            }
        }

        public boolean d(int i) {
            if (i < 64) {
                return ((1 << i) & this.f1147a) != 0;
            }
            c();
            return this.f1148b.d(i - 64);
        }

        public void e(int i, boolean z) {
            if (i >= 64) {
                c();
                this.f1148b.e(i - 64, z);
                return;
            }
            boolean z2 = (this.f1147a & Long.MIN_VALUE) != 0;
            long j = (1 << i) - 1;
            long j2 = this.f1147a;
            this.f1147a = ((j2 & (~j)) << 1) | (j2 & j);
            if (z) {
                h(i);
            } else {
                a(i);
            }
            if (z2 || this.f1148b != null) {
                c();
                this.f1148b.e(0, z2);
            }
        }

        public boolean f(int i) {
            if (i >= 64) {
                c();
                return this.f1148b.f(i - 64);
            }
            long j = 1 << i;
            boolean z = (this.f1147a & j) != 0;
            long j2 = this.f1147a & (~j);
            this.f1147a = j2;
            long j3 = j - 1;
            this.f1147a = (j2 & j3) | Long.rotateRight((~j3) & j2, 1);
            a aVar = this.f1148b;
            if (aVar != null) {
                if (aVar.d(0)) {
                    h(63);
                }
                this.f1148b.f(0);
            }
            return z;
        }

        public void g() {
            this.f1147a = 0L;
            a aVar = this.f1148b;
            if (aVar != null) {
                aVar.g();
            }
        }

        public void h(int i) {
            if (i < 64) {
                this.f1147a |= 1 << i;
            } else {
                c();
                this.f1148b.h(i - 64);
            }
        }

        public String toString() {
            if (this.f1148b == null) {
                return Long.toBinaryString(this.f1147a);
            }
            return this.f1148b.toString() + "xx" + Long.toBinaryString(this.f1147a);
        }
    }

    public interface b {
    }

    public c(b bVar) {
        this.f1144a = bVar;
    }

    public void a(View view, int i, boolean z) {
        int iB = i < 0 ? ((z) this.f1144a).b() : f(i);
        this.f1145b.e(iB, z);
        if (z) {
            i(view);
        }
        z zVar = (z) this.f1144a;
        zVar.f1269a.addView(view, iB);
        RecyclerView recyclerView = zVar.f1269a;
        if (recyclerView == null) {
            throw null;
        }
        RecyclerView.K(view);
        recyclerView.T();
        List<RecyclerView.o> list = recyclerView.C;
        if (list == null) {
            return;
        }
        int size = list.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            } else {
                recyclerView.C.get(size).e(view);
            }
        }
    }

    public void b(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        int iB = i < 0 ? ((z) this.f1144a).b() : f(i);
        this.f1145b.e(iB, z);
        if (z) {
            i(view);
        }
        z zVar = (z) this.f1144a;
        if (zVar == null) {
            throw null;
        }
        RecyclerView.b0 b0VarK = RecyclerView.K(view);
        if (b0VarK != null) {
            if (!b0VarK.o() && !b0VarK.u()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Called attach on a child which is not detached: ");
                sb.append(b0VarK);
                throw new IllegalArgumentException(b.a.a.a.a.b(zVar.f1269a, sb));
            }
            b0VarK.j &= -257;
        }
        zVar.f1269a.attachViewToParent(view, iB, layoutParams);
    }

    public void c(int i) {
        RecyclerView.b0 b0VarK;
        int iF = f(i);
        this.f1145b.f(iF);
        z zVar = (z) this.f1144a;
        View childAt = zVar.f1269a.getChildAt(iF);
        if (childAt != null && (b0VarK = RecyclerView.K(childAt)) != null) {
            if (b0VarK.o() && !b0VarK.u()) {
                StringBuilder sb = new StringBuilder();
                sb.append("called detach on an already detached child ");
                sb.append(b0VarK);
                throw new IllegalArgumentException(b.a.a.a.a.b(zVar.f1269a, sb));
            }
            b0VarK.b(256);
        }
        zVar.f1269a.detachViewFromParent(iF);
    }

    public View d(int i) {
        return ((z) this.f1144a).a(f(i));
    }

    public int e() {
        return ((z) this.f1144a).b() - this.f1146c.size();
    }

    public final int f(int i) {
        if (i < 0) {
            return -1;
        }
        int iB = ((z) this.f1144a).b();
        int i2 = i;
        while (i2 < iB) {
            int iB2 = i - (i2 - this.f1145b.b(i2));
            if (iB2 == 0) {
                while (this.f1145b.d(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += iB2;
        }
        return -1;
    }

    public View g(int i) {
        return ((z) this.f1144a).f1269a.getChildAt(i);
    }

    public int h() {
        return ((z) this.f1144a).b();
    }

    public final void i(View view) {
        this.f1146c.add(view);
        z zVar = (z) this.f1144a;
        if (zVar == null) {
            throw null;
        }
        RecyclerView.b0 b0VarK = RecyclerView.K(view);
        if (b0VarK != null) {
            RecyclerView recyclerView = zVar.f1269a;
            int iN = b0VarK.q;
            if (iN == -1) {
                iN = a.h.k.q.n(b0VarK.f1629a);
            }
            b0VarK.p = iN;
            recyclerView.l0(b0VarK, 4);
        }
    }

    public int j(View view) {
        int iIndexOfChild = ((z) this.f1144a).f1269a.indexOfChild(view);
        if (iIndexOfChild == -1 || this.f1145b.d(iIndexOfChild)) {
            return -1;
        }
        return iIndexOfChild - this.f1145b.b(iIndexOfChild);
    }

    public boolean k(View view) {
        return this.f1146c.contains(view);
    }

    public final boolean l(View view) {
        if (!this.f1146c.remove(view)) {
            return false;
        }
        z zVar = (z) this.f1144a;
        if (zVar == null) {
            throw null;
        }
        RecyclerView.b0 b0VarK = RecyclerView.K(view);
        if (b0VarK == null) {
            return true;
        }
        zVar.f1269a.l0(b0VarK, b0VarK.p);
        b0VarK.p = 0;
        return true;
    }

    public String toString() {
        return this.f1145b.toString() + ", hidden list:" + this.f1146c.size();
    }
}
