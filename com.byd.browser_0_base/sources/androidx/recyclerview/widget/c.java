package androidx.recyclerview.widget;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.in3;
import defpackage.om3;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: DefaultItemAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends z {
    public static TimeInterpolator s;
    public final ArrayList<RecyclerView.a0> h = new ArrayList<>();
    public final ArrayList<RecyclerView.a0> i = new ArrayList<>();
    public final ArrayList<e> j = new ArrayList<>();
    public final ArrayList<d> k = new ArrayList<>();
    public final ArrayList<ArrayList<RecyclerView.a0>> l = new ArrayList<>();
    public final ArrayList<ArrayList<e>> m = new ArrayList<>();
    public final ArrayList<ArrayList<d>> n = new ArrayList<>();
    public final ArrayList<RecyclerView.a0> o = new ArrayList<>();
    public final ArrayList<RecyclerView.a0> p = new ArrayList<>();
    public final ArrayList<RecyclerView.a0> q = new ArrayList<>();
    public final ArrayList<RecyclerView.a0> r = new ArrayList<>();

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class a implements Runnable {
        public final /* synthetic */ ArrayList e;

        public a(ArrayList arrayList) {
            this.e = arrayList;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ArrayList arrayList = this.e;
            Iterator it = arrayList.iterator();
            while (true) {
                boolean zHasNext = it.hasNext();
                c cVar = c.this;
                if (!zHasNext) {
                    arrayList.clear();
                    cVar.m.remove(arrayList);
                    return;
                }
                e eVar = (e) it.next();
                RecyclerView.a0 a0Var = eVar.a;
                cVar.getClass();
                View view = a0Var.e;
                int i = eVar.d - eVar.b;
                int i2 = eVar.e - eVar.c;
                if (i != 0) {
                    view.animate().translationX(0.0f);
                }
                if (i2 != 0) {
                    view.animate().translationY(0.0f);
                }
                ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
                cVar.p.add(a0Var);
                viewPropertyAnimatorAnimate.setDuration(cVar.e).setListener(new f(cVar, a0Var, i, view, i2, viewPropertyAnimatorAnimate)).start();
            }
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class b implements Runnable {
        public final /* synthetic */ ArrayList e;

        public b(ArrayList arrayList) {
            this.e = arrayList;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ArrayList arrayList = this.e;
            Iterator it = arrayList.iterator();
            while (true) {
                boolean zHasNext = it.hasNext();
                c cVar = c.this;
                if (!zHasNext) {
                    arrayList.clear();
                    cVar.n.remove(arrayList);
                    return;
                }
                d dVar = (d) it.next();
                cVar.getClass();
                RecyclerView.a0 a0Var = dVar.a;
                View view = a0Var == null ? null : a0Var.e;
                RecyclerView.a0 a0Var2 = dVar.b;
                View view2 = a0Var2 != null ? a0Var2.e : null;
                ArrayList<RecyclerView.a0> arrayList2 = cVar.r;
                if (view != null) {
                    ViewPropertyAnimator duration = view.animate().setDuration(cVar.f);
                    arrayList2.add(dVar.a);
                    duration.translationX(dVar.e - dVar.c);
                    duration.translationY(dVar.f - dVar.d);
                    duration.alpha(0.0f).setListener(new g(cVar, dVar, duration, view)).start();
                }
                if (view2 != null) {
                    ViewPropertyAnimator viewPropertyAnimatorAnimate = view2.animate();
                    arrayList2.add(dVar.b);
                    viewPropertyAnimatorAnimate.translationX(0.0f).translationY(0.0f).setDuration(cVar.f).alpha(1.0f).setListener(new h(cVar, dVar, viewPropertyAnimatorAnimate, view2)).start();
                }
            }
        }
    }

    /* JADX INFO: renamed from: androidx.recyclerview.widget.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class RunnableC0026c implements Runnable {
        public final /* synthetic */ ArrayList e;

        public RunnableC0026c(ArrayList arrayList) {
            this.e = arrayList;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ArrayList arrayList = this.e;
            Iterator it = arrayList.iterator();
            while (true) {
                boolean zHasNext = it.hasNext();
                c cVar = c.this;
                if (!zHasNext) {
                    arrayList.clear();
                    cVar.l.remove(arrayList);
                    return;
                }
                RecyclerView.a0 a0Var = (RecyclerView.a0) it.next();
                cVar.getClass();
                View view = a0Var.e;
                ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
                cVar.o.add(a0Var);
                viewPropertyAnimatorAnimate.alpha(1.0f).setDuration(cVar.c).setListener(new androidx.recyclerview.widget.e(view, viewPropertyAnimatorAnimate, cVar, a0Var)).start();
            }
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public static class d {
        public RecyclerView.a0 a;
        public RecyclerView.a0 b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;

        public d(RecyclerView.a0 a0Var, RecyclerView.a0 a0Var2, int i, int i2, int i3, int i4) {
            this.a = a0Var;
            this.b = a0Var2;
            this.c = i;
            this.d = i2;
            this.e = i3;
            this.f = i4;
        }

        @SuppressLint({"UnknownNullness"})
        public final String toString() {
            return "ChangeInfo{oldHolder=" + this.a + ", newHolder=" + this.b + ", fromX=" + this.c + ", fromY=" + this.d + ", toX=" + this.e + ", toY=" + this.f + '}';
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public static class e {
        public final RecyclerView.a0 a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;

        public e(RecyclerView.a0 a0Var, int i, int i2, int i3, int i4) {
            this.a = a0Var;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }
    }

    public static void n(ArrayList arrayList) {
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            } else {
                ((RecyclerView.a0) arrayList.get(size)).e.animate().cancel();
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.j
    public final boolean g(RecyclerView.a0 a0Var, List<Object> list) {
        return !list.isEmpty() || f(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.j
    @SuppressLint({"UnknownNullness"})
    public final void i(RecyclerView.a0 a0Var) {
        View view = a0Var.e;
        view.animate().cancel();
        ArrayList<e> arrayList = this.j;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (arrayList.get(size).a == a0Var) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                h(a0Var);
                arrayList.remove(size);
            }
        }
        p(a0Var, this.k);
        if (this.h.remove(a0Var)) {
            view.setAlpha(1.0f);
            h(a0Var);
        }
        if (this.i.remove(a0Var)) {
            view.setAlpha(1.0f);
            h(a0Var);
        }
        ArrayList<ArrayList<d>> arrayList2 = this.n;
        int size2 = arrayList2.size();
        while (true) {
            size2--;
            if (size2 < 0) {
                break;
            }
            ArrayList<d> arrayList3 = arrayList2.get(size2);
            p(a0Var, arrayList3);
            if (arrayList3.isEmpty()) {
                arrayList2.remove(size2);
            }
        }
        ArrayList<ArrayList<e>> arrayList4 = this.m;
        int size3 = arrayList4.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            ArrayList<e> arrayList5 = arrayList4.get(size3);
            int size4 = arrayList5.size();
            while (true) {
                size4--;
                if (size4 < 0) {
                    break;
                }
                if (arrayList5.get(size4).a == a0Var) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    h(a0Var);
                    arrayList5.remove(size4);
                    if (arrayList5.isEmpty()) {
                        arrayList4.remove(size3);
                    }
                }
            }
        }
        ArrayList<ArrayList<RecyclerView.a0>> arrayList6 = this.l;
        int size5 = arrayList6.size();
        while (true) {
            size5--;
            if (size5 < 0) {
                this.q.remove(a0Var);
                this.o.remove(a0Var);
                this.r.remove(a0Var);
                this.p.remove(a0Var);
                o();
                return;
            }
            ArrayList<RecyclerView.a0> arrayList7 = arrayList6.get(size5);
            if (arrayList7.remove(a0Var)) {
                view.setAlpha(1.0f);
                h(a0Var);
                if (arrayList7.isEmpty()) {
                    arrayList6.remove(size5);
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.j
    public final void j() {
        ArrayList<e> arrayList = this.j;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            e eVar = arrayList.get(size);
            View view = eVar.a.e;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            h(eVar.a);
            arrayList.remove(size);
        }
        ArrayList<RecyclerView.a0> arrayList2 = this.h;
        int size2 = arrayList2.size();
        while (true) {
            size2--;
            if (size2 < 0) {
                break;
            }
            h(arrayList2.get(size2));
            arrayList2.remove(size2);
        }
        ArrayList<RecyclerView.a0> arrayList3 = this.i;
        int size3 = arrayList3.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.a0 a0Var = arrayList3.get(size3);
            a0Var.e.setAlpha(1.0f);
            h(a0Var);
            arrayList3.remove(size3);
        }
        ArrayList<d> arrayList4 = this.k;
        int size4 = arrayList4.size();
        while (true) {
            size4--;
            if (size4 < 0) {
                break;
            }
            d dVar = arrayList4.get(size4);
            RecyclerView.a0 a0Var2 = dVar.a;
            if (a0Var2 != null) {
                q(dVar, a0Var2);
            }
            RecyclerView.a0 a0Var3 = dVar.b;
            if (a0Var3 != null) {
                q(dVar, a0Var3);
            }
        }
        arrayList4.clear();
        if (k()) {
            ArrayList<ArrayList<e>> arrayList5 = this.m;
            int size5 = arrayList5.size();
            while (true) {
                size5--;
                if (size5 < 0) {
                    break;
                }
                ArrayList<e> arrayList6 = arrayList5.get(size5);
                int size6 = arrayList6.size();
                while (true) {
                    size6--;
                    if (size6 >= 0) {
                        e eVar2 = arrayList6.get(size6);
                        View view2 = eVar2.a.e;
                        view2.setTranslationY(0.0f);
                        view2.setTranslationX(0.0f);
                        h(eVar2.a);
                        arrayList6.remove(size6);
                        if (arrayList6.isEmpty()) {
                            arrayList5.remove(arrayList6);
                        }
                    }
                }
            }
            ArrayList<ArrayList<RecyclerView.a0>> arrayList7 = this.l;
            int size7 = arrayList7.size();
            while (true) {
                size7--;
                if (size7 < 0) {
                    break;
                }
                ArrayList<RecyclerView.a0> arrayList8 = arrayList7.get(size7);
                int size8 = arrayList8.size();
                while (true) {
                    size8--;
                    if (size8 >= 0) {
                        RecyclerView.a0 a0Var4 = arrayList8.get(size8);
                        a0Var4.e.setAlpha(1.0f);
                        h(a0Var4);
                        arrayList8.remove(size8);
                        if (arrayList8.isEmpty()) {
                            arrayList7.remove(arrayList8);
                        }
                    }
                }
            }
            ArrayList<ArrayList<d>> arrayList9 = this.n;
            int size9 = arrayList9.size();
            while (true) {
                size9--;
                if (size9 < 0) {
                    break;
                }
                ArrayList<d> arrayList10 = arrayList9.get(size9);
                int size10 = arrayList10.size();
                while (true) {
                    size10--;
                    if (size10 >= 0) {
                        d dVar2 = arrayList10.get(size10);
                        RecyclerView.a0 a0Var5 = dVar2.a;
                        if (a0Var5 != null) {
                            q(dVar2, a0Var5);
                        }
                        RecyclerView.a0 a0Var6 = dVar2.b;
                        if (a0Var6 != null) {
                            q(dVar2, a0Var6);
                        }
                        if (arrayList10.isEmpty()) {
                            arrayList9.remove(arrayList10);
                        }
                    }
                }
            }
            n(this.q);
            n(this.p);
            n(this.o);
            n(this.r);
            ArrayList<RecyclerView.j.a> arrayList11 = this.b;
            int size11 = arrayList11.size();
            for (int i = 0; i < size11; i++) {
                arrayList11.get(i).a();
            }
            arrayList11.clear();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.j
    public final boolean k() {
        return (this.i.isEmpty() && this.k.isEmpty() && this.j.isEmpty() && this.h.isEmpty() && this.p.isEmpty() && this.q.isEmpty() && this.o.isEmpty() && this.r.isEmpty() && this.m.isEmpty() && this.l.isEmpty() && this.n.isEmpty()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.j
    public final void l() {
        long j;
        ArrayList<RecyclerView.a0> arrayList = this.h;
        boolean z = !arrayList.isEmpty();
        ArrayList<e> arrayList2 = this.j;
        boolean z2 = !arrayList2.isEmpty();
        ArrayList<d> arrayList3 = this.k;
        boolean z3 = !arrayList3.isEmpty();
        ArrayList<RecyclerView.a0> arrayList4 = this.i;
        boolean z4 = !arrayList4.isEmpty();
        if (z || z2 || z4 || z3) {
            Iterator<RecyclerView.a0> it = arrayList.iterator();
            while (true) {
                boolean zHasNext = it.hasNext();
                j = this.d;
                if (!zHasNext) {
                    break;
                }
                RecyclerView.a0 next = it.next();
                View view = next.e;
                ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
                this.q.add(next);
                viewPropertyAnimatorAnimate.setDuration(j).alpha(0.0f).setListener(new androidx.recyclerview.widget.d(view, viewPropertyAnimatorAnimate, this, next)).start();
            }
            arrayList.clear();
            if (z2) {
                ArrayList<e> arrayList5 = new ArrayList<>();
                arrayList5.addAll(arrayList2);
                this.m.add(arrayList5);
                arrayList2.clear();
                a aVar = new a(arrayList5);
                if (z) {
                    View view2 = arrayList5.get(0).a.e;
                    WeakHashMap<View, in3> weakHashMap = om3.a;
                    om3.d.l(view2, aVar, j);
                } else {
                    aVar.run();
                }
            }
            if (z3) {
                ArrayList<d> arrayList6 = new ArrayList<>();
                arrayList6.addAll(arrayList3);
                this.n.add(arrayList6);
                arrayList3.clear();
                b bVar = new b(arrayList6);
                if (z) {
                    View view3 = arrayList6.get(0).a.e;
                    WeakHashMap<View, in3> weakHashMap2 = om3.a;
                    om3.d.l(view3, bVar, j);
                } else {
                    bVar.run();
                }
            }
            if (z4) {
                ArrayList<RecyclerView.a0> arrayList7 = new ArrayList<>();
                arrayList7.addAll(arrayList4);
                this.l.add(arrayList7);
                arrayList4.clear();
                RunnableC0026c runnableC0026c = new RunnableC0026c(arrayList7);
                if (!z && !z2 && !z3) {
                    runnableC0026c.run();
                    return;
                }
                if (!z) {
                    j = 0;
                }
                long jMax = Math.max(z2 ? this.e : 0L, z3 ? this.f : 0L) + j;
                View view4 = arrayList7.get(0).e;
                WeakHashMap<View, in3> weakHashMap3 = om3.a;
                om3.d.l(view4, runnableC0026c, jMax);
            }
        }
    }

    @Override // androidx.recyclerview.widget.z
    @SuppressLint({"UnknownNullness"})
    public final boolean m(RecyclerView.a0 a0Var, int i, int i2, int i3, int i4) {
        View view = a0Var.e;
        int translationX = i + ((int) view.getTranslationX());
        int translationY = i2 + ((int) a0Var.e.getTranslationY());
        r(a0Var);
        int i5 = i3 - translationX;
        int i6 = i4 - translationY;
        if (i5 == 0 && i6 == 0) {
            h(a0Var);
            return false;
        }
        if (i5 != 0) {
            view.setTranslationX(-i5);
        }
        if (i6 != 0) {
            view.setTranslationY(-i6);
        }
        this.j.add(new e(a0Var, translationX, translationY, i3, i4));
        return true;
    }

    public final void o() {
        if (k()) {
            return;
        }
        ArrayList<RecyclerView.j.a> arrayList = this.b;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList.get(i).a();
        }
        arrayList.clear();
    }

    public final void p(RecyclerView.a0 a0Var, ArrayList arrayList) {
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            }
            d dVar = (d) arrayList.get(size);
            if (q(dVar, a0Var) && dVar.a == null && dVar.b == null) {
                arrayList.remove(dVar);
            }
        }
    }

    public final boolean q(d dVar, RecyclerView.a0 a0Var) {
        if (dVar.b == a0Var) {
            dVar.b = null;
        } else {
            if (dVar.a != a0Var) {
                return false;
            }
            dVar.a = null;
        }
        a0Var.e.setAlpha(1.0f);
        View view = a0Var.e;
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        h(a0Var);
        return true;
    }

    public final void r(RecyclerView.a0 a0Var) {
        if (s == null) {
            s = new ValueAnimator().getInterpolator();
        }
        a0Var.e.animate().setInterpolator(s);
        i(a0Var);
    }
}
