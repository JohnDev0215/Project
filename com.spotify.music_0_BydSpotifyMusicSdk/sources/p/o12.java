package p;

import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class o12 implements Runnable {
    public final /* synthetic */ RecyclerView c;

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList arrayList;
        long j;
        RecyclerView recyclerView = this.c;
        v12 v12Var = recyclerView.P;
        if (v12Var != null) {
            m70 m70Var = (m70) v12Var;
            ArrayList arrayList2 = m70Var.h;
            boolean z = !arrayList2.isEmpty();
            ArrayList arrayList3 = m70Var.j;
            boolean z2 = !arrayList3.isEmpty();
            ArrayList arrayList4 = m70Var.k;
            boolean z3 = !arrayList4.isEmpty();
            ArrayList arrayList5 = m70Var.i;
            boolean z4 = !arrayList5.isEmpty();
            if (z || z2 || z4 || z3) {
                Iterator it = arrayList2.iterator();
                while (true) {
                    boolean zHasNext = it.hasNext();
                    arrayList = arrayList3;
                    j = m70Var.d;
                    if (!zHasNext) {
                        break;
                    }
                    n22 n22Var = (n22) it.next();
                    View view = n22Var.a;
                    ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
                    m70Var.q.add(n22Var);
                    viewPropertyAnimatorAnimate.setDuration(j).alpha(0.0f).setListener(new h70(m70Var, n22Var, viewPropertyAnimatorAnimate, view)).start();
                    arrayList3 = arrayList;
                }
                arrayList2.clear();
                if (z2) {
                    ArrayList arrayList6 = new ArrayList();
                    arrayList6.addAll(arrayList);
                    m70Var.m.add(arrayList6);
                    arrayList.clear();
                    g70 g70Var = new g70(m70Var, arrayList6, 0);
                    if (z) {
                        View view2 = ((l70) arrayList6.get(0)).a.a;
                        WeakHashMap weakHashMap = zt2.a;
                        it2.n(view2, g70Var, j);
                    } else {
                        g70Var.run();
                    }
                }
                if (z3) {
                    ArrayList arrayList7 = new ArrayList();
                    arrayList7.addAll(arrayList4);
                    m70Var.n.add(arrayList7);
                    arrayList4.clear();
                    g70 g70Var2 = new g70(m70Var, arrayList7, 1);
                    if (z) {
                        View view3 = ((k70) arrayList7.get(0)).a.a;
                        WeakHashMap weakHashMap2 = zt2.a;
                        it2.n(view3, g70Var2, j);
                    } else {
                        g70Var2.run();
                    }
                }
                if (z4) {
                    ArrayList arrayList8 = new ArrayList();
                    arrayList8.addAll(arrayList5);
                    m70Var.l.add(arrayList8);
                    arrayList5.clear();
                    g70 g70Var3 = new g70(m70Var, arrayList8, 2);
                    if (z || z2 || z3) {
                        if (!z) {
                            j = 0;
                        }
                        long jMax = Math.max(z2 ? m70Var.e : 0L, z3 ? m70Var.f : 0L) + j;
                        View view4 = ((n22) arrayList8.get(0)).a;
                        WeakHashMap weakHashMap3 = zt2.a;
                        it2.n(view4, g70Var3, jMax);
                    } else {
                        g70Var3.run();
                    }
                }
            }
        }
        recyclerView.q0 = false;
    }
}
