package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import b.g.k.t;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: DefaultItemAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends n {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static TimeInterpolator f1920h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private ArrayList<RecyclerView.c0> f1921i = new ArrayList<>();
    private ArrayList<RecyclerView.c0> j = new ArrayList<>();
    private ArrayList<j> k = new ArrayList<>();
    private ArrayList<i> l = new ArrayList<>();
    ArrayList<ArrayList<RecyclerView.c0>> m = new ArrayList<>();
    ArrayList<ArrayList<j>> n = new ArrayList<>();
    ArrayList<ArrayList<i>> o = new ArrayList<>();
    ArrayList<RecyclerView.c0> p = new ArrayList<>();
    ArrayList<RecyclerView.c0> q = new ArrayList<>();
    ArrayList<RecyclerView.c0> r = new ArrayList<>();
    ArrayList<RecyclerView.c0> s = new ArrayList<>();

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f1922b;

        a(ArrayList arrayList) {
            this.f1922b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (j jVar : this.f1922b) {
                c.this.S(jVar.f1956a, jVar.f1957b, jVar.f1958c, jVar.f1959d, jVar.f1960e);
            }
            this.f1922b.clear();
            c.this.n.remove(this.f1922b);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f1924b;

        b(ArrayList arrayList) {
            this.f1924b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f1924b.iterator();
            while (it.hasNext()) {
                c.this.R((i) it.next());
            }
            this.f1924b.clear();
            c.this.o.remove(this.f1924b);
        }
    }

    /* JADX INFO: renamed from: androidx.recyclerview.widget.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    class RunnableC0028c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f1926b;

        RunnableC0028c(ArrayList arrayList) {
            this.f1926b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f1926b.iterator();
            while (it.hasNext()) {
                c.this.Q((RecyclerView.c0) it.next());
            }
            this.f1926b.clear();
            c.this.m.remove(this.f1926b);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.c0 f1928a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f1929b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1930c;

        d(RecyclerView.c0 c0Var, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f1928a = c0Var;
            this.f1929b = viewPropertyAnimator;
            this.f1930c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1929b.setListener(null);
            this.f1930c.setAlpha(1.0f);
            c.this.G(this.f1928a);
            c.this.r.remove(this.f1928a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.H(this.f1928a);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.c0 f1932a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1933b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f1934c;

        e(RecyclerView.c0 c0Var, View view, ViewPropertyAnimator viewPropertyAnimator) {
            this.f1932a = c0Var;
            this.f1933b = view;
            this.f1934c = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1933b.setAlpha(1.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1934c.setListener(null);
            c.this.A(this.f1932a);
            c.this.p.remove(this.f1932a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.B(this.f1932a);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    class f extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.c0 f1936a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1937b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1938c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f1939d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f1940e;

        f(RecyclerView.c0 c0Var, int i2, View view, int i3, ViewPropertyAnimator viewPropertyAnimator) {
            this.f1936a = c0Var;
            this.f1937b = i2;
            this.f1938c = view;
            this.f1939d = i3;
            this.f1940e = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (this.f1937b != 0) {
                this.f1938c.setTranslationX(0.0f);
            }
            if (this.f1939d != 0) {
                this.f1938c.setTranslationY(0.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1940e.setListener(null);
            c.this.E(this.f1936a);
            c.this.q.remove(this.f1936a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.F(this.f1936a);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    class g extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ i f1942a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f1943b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1944c;

        g(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f1942a = iVar;
            this.f1943b = viewPropertyAnimator;
            this.f1944c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1943b.setListener(null);
            this.f1944c.setAlpha(1.0f);
            this.f1944c.setTranslationX(0.0f);
            this.f1944c.setTranslationY(0.0f);
            c.this.C(this.f1942a.f1950a, true);
            c.this.s.remove(this.f1942a.f1950a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.D(this.f1942a.f1950a, true);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    class h extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ i f1946a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f1947b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1948c;

        h(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f1946a = iVar;
            this.f1947b = viewPropertyAnimator;
            this.f1948c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1947b.setListener(null);
            this.f1948c.setAlpha(1.0f);
            this.f1948c.setTranslationX(0.0f);
            this.f1948c.setTranslationY(0.0f);
            c.this.C(this.f1946a.f1951b, false);
            c.this.s.remove(this.f1946a.f1951b);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.D(this.f1946a.f1951b, false);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    private static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.c0 f1956a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1957b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1958c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1959d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1960e;

        j(RecyclerView.c0 c0Var, int i2, int i3, int i4, int i5) {
            this.f1956a = c0Var;
            this.f1957b = i2;
            this.f1958c = i3;
            this.f1959d = i4;
            this.f1960e = i5;
        }
    }

    private void T(RecyclerView.c0 c0Var) {
        View view = c0Var.f1790b;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.r.add(c0Var);
        viewPropertyAnimatorAnimate.setDuration(o()).alpha(0.0f).setListener(new d(c0Var, viewPropertyAnimatorAnimate, view)).start();
    }

    private void W(List<i> list, RecyclerView.c0 c0Var) {
        for (int size = list.size() - 1; size >= 0; size--) {
            i iVar = list.get(size);
            if (Y(iVar, c0Var) && iVar.f1950a == null && iVar.f1951b == null) {
                list.remove(iVar);
            }
        }
    }

    private void X(i iVar) {
        RecyclerView.c0 c0Var = iVar.f1950a;
        if (c0Var != null) {
            Y(iVar, c0Var);
        }
        RecyclerView.c0 c0Var2 = iVar.f1951b;
        if (c0Var2 != null) {
            Y(iVar, c0Var2);
        }
    }

    private boolean Y(i iVar, RecyclerView.c0 c0Var) {
        boolean z = false;
        if (iVar.f1951b == c0Var) {
            iVar.f1951b = null;
        } else {
            if (iVar.f1950a != c0Var) {
                return false;
            }
            iVar.f1950a = null;
            z = true;
        }
        c0Var.f1790b.setAlpha(1.0f);
        c0Var.f1790b.setTranslationX(0.0f);
        c0Var.f1790b.setTranslationY(0.0f);
        C(c0Var, z);
        return true;
    }

    private void Z(RecyclerView.c0 c0Var) {
        if (f1920h == null) {
            f1920h = new ValueAnimator().getInterpolator();
        }
        c0Var.f1790b.animate().setInterpolator(f1920h);
        j(c0Var);
    }

    void Q(RecyclerView.c0 c0Var) {
        View view = c0Var.f1790b;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.p.add(c0Var);
        viewPropertyAnimatorAnimate.alpha(1.0f).setDuration(l()).setListener(new e(c0Var, view, viewPropertyAnimatorAnimate)).start();
    }

    void R(i iVar) {
        RecyclerView.c0 c0Var = iVar.f1950a;
        View view = c0Var == null ? null : c0Var.f1790b;
        RecyclerView.c0 c0Var2 = iVar.f1951b;
        View view2 = c0Var2 != null ? c0Var2.f1790b : null;
        if (view != null) {
            ViewPropertyAnimator duration = view.animate().setDuration(m());
            this.s.add(iVar.f1950a);
            duration.translationX(iVar.f1954e - iVar.f1952c);
            duration.translationY(iVar.f1955f - iVar.f1953d);
            duration.alpha(0.0f).setListener(new g(iVar, duration, view)).start();
        }
        if (view2 != null) {
            ViewPropertyAnimator viewPropertyAnimatorAnimate = view2.animate();
            this.s.add(iVar.f1951b);
            viewPropertyAnimatorAnimate.translationX(0.0f).translationY(0.0f).setDuration(m()).alpha(1.0f).setListener(new h(iVar, viewPropertyAnimatorAnimate, view2)).start();
        }
    }

    void S(RecyclerView.c0 c0Var, int i2, int i3, int i4, int i5) {
        View view = c0Var.f1790b;
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

    void U(List<RecyclerView.c0> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            list.get(size).f1790b.animate().cancel();
        }
    }

    void V() {
        if (p()) {
            return;
        }
        i();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean g(RecyclerView.c0 c0Var, List<Object> list) {
        return !list.isEmpty() || super.g(c0Var, list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void j(RecyclerView.c0 c0Var) {
        View view = c0Var.f1790b;
        view.animate().cancel();
        int size = this.k.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (this.k.get(size).f1956a == c0Var) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                E(c0Var);
                this.k.remove(size);
            }
        }
        W(this.l, c0Var);
        if (this.f1921i.remove(c0Var)) {
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
                if (arrayList2.get(size4).f1956a == c0Var) {
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
            View view = jVar.f1956a.f1790b;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            E(jVar.f1956a);
            this.k.remove(size);
        }
        for (int size2 = this.f1921i.size() - 1; size2 >= 0; size2--) {
            G(this.f1921i.get(size2));
            this.f1921i.remove(size2);
        }
        int size3 = this.j.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.c0 c0Var = this.j.get(size3);
            c0Var.f1790b.setAlpha(1.0f);
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
                    View view2 = jVar2.f1956a.f1790b;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    E(jVar2.f1956a);
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
                    c0Var2.f1790b.setAlpha(1.0f);
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
        return (this.j.isEmpty() && this.l.isEmpty() && this.k.isEmpty() && this.f1921i.isEmpty() && this.q.isEmpty() && this.r.isEmpty() && this.p.isEmpty() && this.s.isEmpty() && this.n.isEmpty() && this.m.isEmpty() && this.o.isEmpty()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void u() {
        boolean z = !this.f1921i.isEmpty();
        boolean z2 = !this.k.isEmpty();
        boolean z3 = !this.l.isEmpty();
        boolean z4 = !this.j.isEmpty();
        if (z || z2 || z4 || z3) {
            Iterator<RecyclerView.c0> it = this.f1921i.iterator();
            while (it.hasNext()) {
                T(it.next());
            }
            this.f1921i.clear();
            if (z2) {
                ArrayList<j> arrayList = new ArrayList<>();
                arrayList.addAll(this.k);
                this.n.add(arrayList);
                this.k.clear();
                a aVar = new a(arrayList);
                if (z) {
                    t.c0(arrayList.get(0).f1956a.f1790b, aVar, o());
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
                    t.c0(arrayList2.get(0).f1950a.f1790b, bVar, o());
                } else {
                    bVar.run();
                }
            }
            if (z4) {
                ArrayList<RecyclerView.c0> arrayList3 = new ArrayList<>();
                arrayList3.addAll(this.j);
                this.m.add(arrayList3);
                this.j.clear();
                RunnableC0028c runnableC0028c = new RunnableC0028c(arrayList3);
                if (z || z2 || z3) {
                    t.c0(arrayList3.get(0).f1790b, runnableC0028c, (z ? o() : 0L) + Math.max(z2 ? n() : 0L, z3 ? m() : 0L));
                } else {
                    runnableC0028c.run();
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.n
    public boolean w(RecyclerView.c0 c0Var) {
        Z(c0Var);
        c0Var.f1790b.setAlpha(0.0f);
        this.j.add(c0Var);
        return true;
    }

    @Override // androidx.recyclerview.widget.n
    public boolean x(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, int i2, int i3, int i4, int i5) {
        if (c0Var == c0Var2) {
            return y(c0Var, i2, i3, i4, i5);
        }
        float translationX = c0Var.f1790b.getTranslationX();
        float translationY = c0Var.f1790b.getTranslationY();
        float alpha = c0Var.f1790b.getAlpha();
        Z(c0Var);
        int i6 = (int) ((i4 - i2) - translationX);
        int i7 = (int) ((i5 - i3) - translationY);
        c0Var.f1790b.setTranslationX(translationX);
        c0Var.f1790b.setTranslationY(translationY);
        c0Var.f1790b.setAlpha(alpha);
        if (c0Var2 != null) {
            Z(c0Var2);
            c0Var2.f1790b.setTranslationX(-i6);
            c0Var2.f1790b.setTranslationY(-i7);
            c0Var2.f1790b.setAlpha(0.0f);
        }
        this.l.add(new i(c0Var, c0Var2, i2, i3, i4, i5));
        return true;
    }

    @Override // androidx.recyclerview.widget.n
    public boolean y(RecyclerView.c0 c0Var, int i2, int i3, int i4, int i5) {
        View view = c0Var.f1790b;
        int translationX = i2 + ((int) view.getTranslationX());
        int translationY = i3 + ((int) c0Var.f1790b.getTranslationY());
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

    @Override // androidx.recyclerview.widget.n
    public boolean z(RecyclerView.c0 c0Var) {
        Z(c0Var);
        this.f1921i.add(c0Var);
        return true;
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    private static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.c0 f1950a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView.c0 f1951b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1952c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1953d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1954e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1955f;

        private i(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2) {
            this.f1950a = c0Var;
            this.f1951b = c0Var2;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.f1950a + ", newHolder=" + this.f1951b + ", fromX=" + this.f1952c + ", fromY=" + this.f1953d + ", toX=" + this.f1954e + ", toY=" + this.f1955f + '}';
        }

        i(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, int i2, int i3, int i4, int i5) {
            this(c0Var, c0Var2);
            this.f1952c = i2;
            this.f1953d = i3;
            this.f1954e = i4;
            this.f1955f = i5;
        }
    }
}
