package b.n.d;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import b.f.m.s;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: DefaultItemAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends k {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static TimeInterpolator f2025h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList<RecyclerView.c0> f2026i = new ArrayList<>();
    public ArrayList<RecyclerView.c0> j = new ArrayList<>();
    public ArrayList<j> k = new ArrayList<>();
    public ArrayList<i> l = new ArrayList<>();
    public ArrayList<ArrayList<RecyclerView.c0>> m = new ArrayList<>();
    public ArrayList<ArrayList<j>> n = new ArrayList<>();
    public ArrayList<ArrayList<i>> o = new ArrayList<>();
    public ArrayList<RecyclerView.c0> p = new ArrayList<>();
    public ArrayList<RecyclerView.c0> q = new ArrayList<>();
    public ArrayList<RecyclerView.c0> r = new ArrayList<>();
    public ArrayList<RecyclerView.c0> s = new ArrayList<>();

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ ArrayList f2027c;

        public a(ArrayList arrayList) {
            this.f2027c = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (j jVar : this.f2027c) {
                c.this.S(jVar.f2061a, jVar.f2062b, jVar.f2063c, jVar.f2064d, jVar.f2065e);
            }
            this.f2027c.clear();
            c.this.n.remove(this.f2027c);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ ArrayList f2029c;

        public b(ArrayList arrayList) {
            this.f2029c = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f2029c.iterator();
            while (it.hasNext()) {
                c.this.R((i) it.next());
            }
            this.f2029c.clear();
            c.this.o.remove(this.f2029c);
        }
    }

    /* JADX INFO: renamed from: b.n.d.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class RunnableC0040c implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ ArrayList f2031c;

        public RunnableC0040c(ArrayList arrayList) {
            this.f2031c = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f2031c.iterator();
            while (it.hasNext()) {
                c.this.Q((RecyclerView.c0) it.next());
            }
            this.f2031c.clear();
            c.this.m.remove(this.f2031c);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.c0 f2033a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ViewPropertyAnimator f2034b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f2035c;

        public d(RecyclerView.c0 c0Var, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f2033a = c0Var;
            this.f2034b = viewPropertyAnimator;
            this.f2035c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2034b.setListener(null);
            this.f2035c.setAlpha(1.0f);
            c.this.G(this.f2033a);
            c.this.r.remove(this.f2033a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.H(this.f2033a);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.c0 f2037a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f2038b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ ViewPropertyAnimator f2039c;

        public e(RecyclerView.c0 c0Var, View view, ViewPropertyAnimator viewPropertyAnimator) {
            this.f2037a = c0Var;
            this.f2038b = view;
            this.f2039c = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f2038b.setAlpha(1.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2039c.setListener(null);
            c.this.A(this.f2037a);
            c.this.p.remove(this.f2037a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.B(this.f2037a);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class f extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.c0 f2041a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2042b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f2043c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f2044d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ ViewPropertyAnimator f2045e;

        public f(RecyclerView.c0 c0Var, int i2, View view, int i3, ViewPropertyAnimator viewPropertyAnimator) {
            this.f2041a = c0Var;
            this.f2042b = i2;
            this.f2043c = view;
            this.f2044d = i3;
            this.f2045e = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (this.f2042b != 0) {
                this.f2043c.setTranslationX(0.0f);
            }
            if (this.f2044d != 0) {
                this.f2043c.setTranslationY(0.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2045e.setListener(null);
            c.this.E(this.f2041a);
            c.this.q.remove(this.f2041a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.F(this.f2041a);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class g extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ i f2047a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ViewPropertyAnimator f2048b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f2049c;

        public g(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f2047a = iVar;
            this.f2048b = viewPropertyAnimator;
            this.f2049c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2048b.setListener(null);
            this.f2049c.setAlpha(1.0f);
            this.f2049c.setTranslationX(0.0f);
            this.f2049c.setTranslationY(0.0f);
            c.this.C(this.f2047a.f2055a, true);
            c.this.s.remove(this.f2047a.f2055a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.D(this.f2047a.f2055a, true);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class h extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ i f2051a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ViewPropertyAnimator f2052b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f2053c;

        public h(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f2051a = iVar;
            this.f2052b = viewPropertyAnimator;
            this.f2053c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2052b.setListener(null);
            this.f2053c.setAlpha(1.0f);
            this.f2053c.setTranslationX(0.0f);
            this.f2053c.setTranslationY(0.0f);
            c.this.C(this.f2051a.f2056b, false);
            c.this.s.remove(this.f2051a.f2056b);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.D(this.f2051a.f2056b, false);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.c0 f2061a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2062b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2063c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2064d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2065e;

        public j(RecyclerView.c0 c0Var, int i2, int i3, int i4, int i5) {
            this.f2061a = c0Var;
            this.f2062b = i2;
            this.f2063c = i3;
            this.f2064d = i4;
            this.f2065e = i5;
        }
    }

    public void Q(RecyclerView.c0 c0Var) {
        View view = c0Var.f580b;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.p.add(c0Var);
        viewPropertyAnimatorAnimate.alpha(1.0f).setDuration(l()).setListener(new e(c0Var, view, viewPropertyAnimatorAnimate)).start();
    }

    public void R(i iVar) {
        RecyclerView.c0 c0Var = iVar.f2055a;
        View view = c0Var == null ? null : c0Var.f580b;
        RecyclerView.c0 c0Var2 = iVar.f2056b;
        View view2 = c0Var2 != null ? c0Var2.f580b : null;
        if (view != null) {
            ViewPropertyAnimator duration = view.animate().setDuration(m());
            this.s.add(iVar.f2055a);
            duration.translationX(iVar.f2059e - iVar.f2057c);
            duration.translationY(iVar.f2060f - iVar.f2058d);
            duration.alpha(0.0f).setListener(new g(iVar, duration, view)).start();
        }
        if (view2 != null) {
            ViewPropertyAnimator viewPropertyAnimatorAnimate = view2.animate();
            this.s.add(iVar.f2056b);
            viewPropertyAnimatorAnimate.translationX(0.0f).translationY(0.0f).setDuration(m()).alpha(1.0f).setListener(new h(iVar, viewPropertyAnimatorAnimate, view2)).start();
        }
    }

    public void S(RecyclerView.c0 c0Var, int i2, int i3, int i4, int i5) {
        View view = c0Var.f580b;
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        if (i6 != 0) {
            view.animate().translationX(0.0f);
        }
        if (i7 != 0) {
            view.animate().translationY(0.0f);
        }
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.q.add(c0Var);
        viewPropertyAnimatorAnimate.setDuration(n()).setListener(new f(c0Var, i6, view, i7, viewPropertyAnimatorAnimate)).start();
    }

    public final void T(RecyclerView.c0 c0Var) {
        View view = c0Var.f580b;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.r.add(c0Var);
        viewPropertyAnimatorAnimate.setDuration(o()).alpha(0.0f).setListener(new d(c0Var, viewPropertyAnimatorAnimate, view)).start();
    }

    public void U(List<RecyclerView.c0> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            list.get(size).f580b.animate().cancel();
        }
    }

    public void V() {
        if (p()) {
            return;
        }
        i();
    }

    public final void W(List<i> list, RecyclerView.c0 c0Var) {
        for (int size = list.size() - 1; size >= 0; size--) {
            i iVar = list.get(size);
            if (Y(iVar, c0Var) && iVar.f2055a == null && iVar.f2056b == null) {
                list.remove(iVar);
            }
        }
    }

    public final void X(i iVar) {
        RecyclerView.c0 c0Var = iVar.f2055a;
        if (c0Var != null) {
            Y(iVar, c0Var);
        }
        RecyclerView.c0 c0Var2 = iVar.f2056b;
        if (c0Var2 != null) {
            Y(iVar, c0Var2);
        }
    }

    public final boolean Y(i iVar, RecyclerView.c0 c0Var) {
        boolean z = false;
        if (iVar.f2056b == c0Var) {
            iVar.f2056b = null;
        } else {
            if (iVar.f2055a != c0Var) {
                return false;
            }
            iVar.f2055a = null;
            z = true;
        }
        c0Var.f580b.setAlpha(1.0f);
        c0Var.f580b.setTranslationX(0.0f);
        c0Var.f580b.setTranslationY(0.0f);
        C(c0Var, z);
        return true;
    }

    public final void Z(RecyclerView.c0 c0Var) {
        if (f2025h == null) {
            f2025h = new ValueAnimator().getInterpolator();
        }
        c0Var.f580b.animate().setInterpolator(f2025h);
        j(c0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean g(RecyclerView.c0 c0Var, List<Object> list) {
        return !list.isEmpty() || super.g(c0Var, list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void j(RecyclerView.c0 c0Var) {
        View view = c0Var.f580b;
        view.animate().cancel();
        int size = this.k.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (this.k.get(size).f2061a == c0Var) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                E(c0Var);
                this.k.remove(size);
            }
        }
        W(this.l, c0Var);
        if (this.f2026i.remove(c0Var)) {
            view.setAlpha(1.0f);
            G(c0Var);
        }
        if (this.j.remove(c0Var)) {
            view.setAlpha(1.0f);
            A(c0Var);
        }
        for (int size2 = this.o.size() - 1; size2 >= 0; size2--) {
            ArrayList<i> arrayList = this.o.get(size2);
            W(arrayList, c0Var);
            if (arrayList.isEmpty()) {
                this.o.remove(size2);
            }
        }
        for (int size3 = this.n.size() - 1; size3 >= 0; size3--) {
            ArrayList<j> arrayList2 = this.n.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (arrayList2.get(size4).f2061a == c0Var) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    E(c0Var);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.n.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.m.size() - 1; size5 >= 0; size5--) {
            ArrayList<RecyclerView.c0> arrayList3 = this.m.get(size5);
            if (arrayList3.remove(c0Var)) {
                view.setAlpha(1.0f);
                A(c0Var);
                if (arrayList3.isEmpty()) {
                    this.m.remove(size5);
                }
            }
        }
        this.r.remove(c0Var);
        this.p.remove(c0Var);
        this.s.remove(c0Var);
        this.q.remove(c0Var);
        V();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void k() {
        int size = this.k.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            j jVar = this.k.get(size);
            View view = jVar.f2061a.f580b;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            E(jVar.f2061a);
            this.k.remove(size);
        }
        for (int size2 = this.f2026i.size() - 1; size2 >= 0; size2--) {
            G(this.f2026i.get(size2));
            this.f2026i.remove(size2);
        }
        int size3 = this.j.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.c0 c0Var = this.j.get(size3);
            c0Var.f580b.setAlpha(1.0f);
            A(c0Var);
            this.j.remove(size3);
        }
        for (int size4 = this.l.size() - 1; size4 >= 0; size4--) {
            X(this.l.get(size4));
        }
        this.l.clear();
        if (p()) {
            for (int size5 = this.n.size() - 1; size5 >= 0; size5--) {
                ArrayList<j> arrayList = this.n.get(size5);
                for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                    j jVar2 = arrayList.get(size6);
                    View view2 = jVar2.f2061a.f580b;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    E(jVar2.f2061a);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.n.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.m.size() - 1; size7 >= 0; size7--) {
                ArrayList<RecyclerView.c0> arrayList2 = this.m.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    RecyclerView.c0 c0Var2 = arrayList2.get(size8);
                    c0Var2.f580b.setAlpha(1.0f);
                    A(c0Var2);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.m.remove(arrayList2);
                    }
                }
            }
            for (int size9 = this.o.size() - 1; size9 >= 0; size9--) {
                ArrayList<i> arrayList3 = this.o.get(size9);
                for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                    X(arrayList3.get(size10));
                    if (arrayList3.isEmpty()) {
                        this.o.remove(arrayList3);
                    }
                }
            }
            U(this.r);
            U(this.q);
            U(this.p);
            U(this.s);
            i();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean p() {
        return (this.j.isEmpty() && this.l.isEmpty() && this.k.isEmpty() && this.f2026i.isEmpty() && this.q.isEmpty() && this.r.isEmpty() && this.p.isEmpty() && this.s.isEmpty() && this.n.isEmpty() && this.m.isEmpty() && this.o.isEmpty()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void u() {
        boolean z = !this.f2026i.isEmpty();
        boolean z2 = !this.k.isEmpty();
        boolean z3 = !this.l.isEmpty();
        boolean z4 = !this.j.isEmpty();
        if (z || z2 || z4 || z3) {
            Iterator<RecyclerView.c0> it = this.f2026i.iterator();
            while (it.hasNext()) {
                T(it.next());
            }
            this.f2026i.clear();
            if (z2) {
                ArrayList<j> arrayList = new ArrayList<>();
                arrayList.addAll(this.k);
                this.n.add(arrayList);
                this.k.clear();
                a aVar = new a(arrayList);
                if (z) {
                    s.P(arrayList.get(0).f2061a.f580b, aVar, o());
                } else {
                    aVar.run();
                }
            }
            if (z3) {
                ArrayList<i> arrayList2 = new ArrayList<>();
                arrayList2.addAll(this.l);
                this.o.add(arrayList2);
                this.l.clear();
                b bVar = new b(arrayList2);
                if (z) {
                    s.P(arrayList2.get(0).f2055a.f580b, bVar, o());
                } else {
                    bVar.run();
                }
            }
            if (z4) {
                ArrayList<RecyclerView.c0> arrayList3 = new ArrayList<>();
                arrayList3.addAll(this.j);
                this.m.add(arrayList3);
                this.j.clear();
                RunnableC0040c runnableC0040c = new RunnableC0040c(arrayList3);
                if (z || z2 || z3) {
                    s.P(arrayList3.get(0).f580b, runnableC0040c, (z ? o() : 0L) + Math.max(z2 ? n() : 0L, z3 ? m() : 0L));
                } else {
                    runnableC0040c.run();
                }
            }
        }
    }

    @Override // b.n.d.k
    public boolean w(RecyclerView.c0 c0Var) {
        Z(c0Var);
        c0Var.f580b.setAlpha(0.0f);
        this.j.add(c0Var);
        return true;
    }

    @Override // b.n.d.k
    public boolean x(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, int i2, int i3, int i4, int i5) {
        if (c0Var == c0Var2) {
            return y(c0Var, i2, i3, i4, i5);
        }
        float translationX = c0Var.f580b.getTranslationX();
        float translationY = c0Var.f580b.getTranslationY();
        float alpha = c0Var.f580b.getAlpha();
        Z(c0Var);
        int i6 = (int) ((i4 - i2) - translationX);
        int i7 = (int) ((i5 - i3) - translationY);
        c0Var.f580b.setTranslationX(translationX);
        c0Var.f580b.setTranslationY(translationY);
        c0Var.f580b.setAlpha(alpha);
        if (c0Var2 != null) {
            Z(c0Var2);
            c0Var2.f580b.setTranslationX(-i6);
            c0Var2.f580b.setTranslationY(-i7);
            c0Var2.f580b.setAlpha(0.0f);
        }
        this.l.add(new i(c0Var, c0Var2, i2, i3, i4, i5));
        return true;
    }

    @Override // b.n.d.k
    public boolean y(RecyclerView.c0 c0Var, int i2, int i3, int i4, int i5) {
        View view = c0Var.f580b;
        int translationX = i2 + ((int) view.getTranslationX());
        int translationY = i3 + ((int) c0Var.f580b.getTranslationY());
        Z(c0Var);
        int i6 = i4 - translationX;
        int i7 = i5 - translationY;
        if (i6 == 0 && i7 == 0) {
            E(c0Var);
            return false;
        }
        if (i6 != 0) {
            view.setTranslationX(-i6);
        }
        if (i7 != 0) {
            view.setTranslationY(-i7);
        }
        this.k.add(new j(c0Var, translationX, translationY, i4, i5));
        return true;
    }

    @Override // b.n.d.k
    public boolean z(RecyclerView.c0 c0Var) {
        Z(c0Var);
        this.f2026i.add(c0Var);
        return true;
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.c0 f2055a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView.c0 f2056b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2057c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2058d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2059e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f2060f;

        public i(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2) {
            this.f2055a = c0Var;
            this.f2056b = c0Var2;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.f2055a + ", newHolder=" + this.f2056b + ", fromX=" + this.f2057c + ", fromY=" + this.f2058d + ", toX=" + this.f2059e + ", toY=" + this.f2060f + '}';
        }

        public i(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, int i2, int i3, int i4, int i5) {
            this(c0Var, c0Var2);
            this.f2057c = i2;
            this.f2058d = i3;
            this.f2059e = i4;
            this.f2060f = i5;
        }
    }
}
