package p;

import android.util.SparseArray;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public final class f22 {
    public final Object a;
    public int b;
    public int c;
    public final Object d;
    public Object e;
    public final Object f;
    public Object g;
    public Object h;
    public final Object i;

    public f22(RecyclerView recyclerView) {
        this.i = recyclerView;
        ArrayList arrayList = new ArrayList();
        this.d = arrayList;
        this.e = null;
        this.f = new ArrayList();
        this.a = Collections.unmodifiableList(arrayList);
        this.b = 2;
        this.c = 2;
    }

    public final void a(n22 n22Var, boolean z) {
        RecyclerView.j(n22Var);
        p22 p22Var = ((RecyclerView) this.i).r0;
        if (p22Var != null) {
            o22 o22Var = p22Var.e;
            boolean z2 = o22Var instanceof o22;
            View view = n22Var.a;
            zt2.k(view, z2 ? (o2) o22Var.e.remove(view) : null);
        }
        if (z) {
            ((RecyclerView) this.i).getClass();
            Object obj = this.i;
            q12 q12Var = ((RecyclerView) obj).s;
            RecyclerView recyclerView = (RecyclerView) obj;
            if (recyclerView.k0 != null) {
                recyclerView.n.m(n22Var);
            }
        }
        n22Var.r = null;
        e22 e22VarC = c();
        e22VarC.getClass();
        int i = n22Var.f;
        ArrayList arrayList = e22VarC.a(i).a;
        if (((d22) e22VarC.a.get(i)).b <= arrayList.size()) {
            return;
        }
        n22Var.o();
        arrayList.add(n22Var);
    }

    public final int b(int i) {
        if (i >= 0 && i < ((RecyclerView) this.i).k0.b()) {
            Object obj = this.i;
            return !((RecyclerView) obj).k0.g ? i : ((RecyclerView) obj).l.f(i, 0);
        }
        StringBuilder sbO = u4.o("invalid position ", i, ". State item count is ");
        sbO.append(((RecyclerView) this.i).k0.b());
        throw new IndexOutOfBoundsException(u4.c((RecyclerView) this.i, sbO));
    }

    public final e22 c() {
        if (((e22) this.g) == null) {
            e22 e22Var = new e22();
            e22Var.a = new SparseArray();
            e22Var.b = 0;
            this.g = e22Var;
        }
        return (e22) this.g;
    }

    public final void d() {
        ArrayList arrayList = (ArrayList) this.f;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            e(size);
        }
        arrayList.clear();
        int[] iArr = RecyclerView.A0;
        b01 b01Var = ((RecyclerView) this.i).j0;
        int[] iArr2 = b01Var.c;
        if (iArr2 != null) {
            Arrays.fill(iArr2, -1);
        }
        b01Var.d = 0;
    }

    public final void e(int i) {
        Object obj = this.f;
        a((n22) ((ArrayList) obj).get(i), true);
        ((ArrayList) obj).remove(i);
    }

    public final void f(View view) {
        n22 n22VarI = RecyclerView.I(view);
        if (n22VarI.l()) {
            ((RecyclerView) this.i).removeDetachedView(view, false);
        }
        if (n22VarI.k()) {
            n22VarI.n.j(n22VarI);
        } else if (n22VarI.r()) {
            n22VarI.j &= -33;
        }
        g(n22VarI);
        if (((RecyclerView) this.i).P == null || n22VarI.i()) {
            return;
        }
        ((RecyclerView) this.i).P.d(n22VarI);
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x00a5, code lost:
    
        r4 = r4 - 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g(p.n22 r11) {
        /*
            Method dump skipped, instruction units count: 295
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.f22.g(p.n22):void");
    }

    public final void h(View view) {
        v12 v12Var;
        n22 n22VarI = RecyclerView.I(view);
        if (!n22VarI.e(12) && n22VarI.m() && (v12Var = ((RecyclerView) this.i).P) != null) {
            m70 m70Var = (m70) v12Var;
            if (n22VarI.d().isEmpty() && m70Var.g && !n22VarI.h()) {
                if (((ArrayList) this.e) == null) {
                    this.e = new ArrayList();
                }
                n22VarI.n = this;
                n22VarI.o = true;
                ((ArrayList) this.e).add(n22VarI);
                return;
            }
        }
        if (n22VarI.h() && !n22VarI.j() && !((RecyclerView) this.i).s.b) {
            throw new IllegalArgumentException(u4.c((RecyclerView) this.i, new StringBuilder("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.")));
        }
        n22VarI.n = this;
        n22VarI.o = false;
        ((ArrayList) this.d).add(n22VarI);
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x059c  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x05aa  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0170  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p.n22 i(int r21, long r22) {
        /*
            Method dump skipped, instruction units count: 1536
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.f22.i(int, long):p.n22");
    }

    public final void j(n22 n22Var) {
        if (n22Var.o) {
            ((ArrayList) this.e).remove(n22Var);
        } else {
            ((ArrayList) this.d).remove(n22Var);
        }
        n22Var.n = null;
        n22Var.o = false;
        n22Var.j &= -33;
    }

    public final void k() {
        Object obj = this.i;
        this.c = this.b + (((RecyclerView) obj).t != null ? ((RecyclerView) obj).t.j : 0);
        Object obj2 = this.f;
        for (int size = ((ArrayList) obj2).size() - 1; size >= 0 && ((ArrayList) obj2).size() > this.c; size--) {
            e(size);
        }
    }
}
