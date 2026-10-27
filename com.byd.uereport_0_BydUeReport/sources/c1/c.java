package c1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import o0.t;

/* JADX INFO: compiled from: DefaultItemAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends n {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static TimeInterpolator f2162h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList<RecyclerView.c0> f2163i = new ArrayList<>();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList<RecyclerView.c0> f2164j = new ArrayList<>();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ArrayList<j> f2165k = new ArrayList<>();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ArrayList<i> f2166l = new ArrayList<>();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ArrayList<ArrayList<RecyclerView.c0>> f2167m = new ArrayList<>();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ArrayList<ArrayList<j>> f2168n = new ArrayList<>();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ArrayList<ArrayList<i>> f2169o = new ArrayList<>();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList<RecyclerView.c0> f2170p = new ArrayList<>();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ArrayList<RecyclerView.c0> f2171q = new ArrayList<>();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ArrayList<RecyclerView.c0> f2172r = new ArrayList<>();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ArrayList<RecyclerView.c0> f2173s = new ArrayList<>();

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ ArrayList f2174d;

        public a(ArrayList arrayList) {
            this.f2174d = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (j jVar : this.f2174d) {
                c.this.S(jVar.f2208a, jVar.f2209b, jVar.f2210c, jVar.f2211d, jVar.f2212e);
            }
            this.f2174d.clear();
            c.this.f2168n.remove(this.f2174d);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ ArrayList f2176d;

        public b(ArrayList arrayList) {
            this.f2176d = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f2176d.iterator();
            while (it.hasNext()) {
                c.this.R((i) it.next());
            }
            this.f2176d.clear();
            c.this.f2169o.remove(this.f2176d);
        }
    }

    /* JADX INFO: renamed from: c1.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class RunnableC0010c implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ ArrayList f2178d;

        public RunnableC0010c(ArrayList arrayList) {
            this.f2178d = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f2178d.iterator();
            while (it.hasNext()) {
                c.this.Q((RecyclerView.c0) it.next());
            }
            this.f2178d.clear();
            c.this.f2167m.remove(this.f2178d);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.c0 f2180d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ ViewPropertyAnimator f2181e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ View f2182f;

        public d(RecyclerView.c0 c0Var, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f2180d = c0Var;
            this.f2181e = viewPropertyAnimator;
            this.f2182f = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2181e.setListener(null);
            this.f2182f.setAlpha(1.0f);
            c.this.G(this.f2180d);
            c.this.f2172r.remove(this.f2180d);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.H(this.f2180d);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.c0 f2184d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ View f2185e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ ViewPropertyAnimator f2186f;

        public e(RecyclerView.c0 c0Var, View view, ViewPropertyAnimator viewPropertyAnimator) {
            this.f2184d = c0Var;
            this.f2185e = view;
            this.f2186f = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f2185e.setAlpha(1.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2186f.setListener(null);
            c.this.A(this.f2184d);
            c.this.f2170p.remove(this.f2184d);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.B(this.f2184d);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class f extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.c0 f2188d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f2189e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ View f2190f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ int f2191g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ ViewPropertyAnimator f2192h;

        public f(RecyclerView.c0 c0Var, int i8, View view, int i9, ViewPropertyAnimator viewPropertyAnimator) {
            this.f2188d = c0Var;
            this.f2189e = i8;
            this.f2190f = view;
            this.f2191g = i9;
            this.f2192h = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (this.f2189e != 0) {
                this.f2190f.setTranslationX(0.0f);
            }
            if (this.f2191g != 0) {
                this.f2190f.setTranslationY(0.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2192h.setListener(null);
            c.this.E(this.f2188d);
            c.this.f2171q.remove(this.f2188d);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.F(this.f2188d);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class g extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ i f2194d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ ViewPropertyAnimator f2195e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ View f2196f;

        public g(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f2194d = iVar;
            this.f2195e = viewPropertyAnimator;
            this.f2196f = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2195e.setListener(null);
            this.f2196f.setAlpha(1.0f);
            this.f2196f.setTranslationX(0.0f);
            this.f2196f.setTranslationY(0.0f);
            c.this.C(this.f2194d.f2202a, true);
            c.this.f2173s.remove(this.f2194d.f2202a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.D(this.f2194d.f2202a, true);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class h extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ i f2198d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ ViewPropertyAnimator f2199e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ View f2200f;

        public h(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f2198d = iVar;
            this.f2199e = viewPropertyAnimator;
            this.f2200f = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2199e.setListener(null);
            this.f2200f.setAlpha(1.0f);
            this.f2200f.setTranslationX(0.0f);
            this.f2200f.setTranslationY(0.0f);
            c.this.C(this.f2198d.f2203b, false);
            c.this.f2173s.remove(this.f2198d.f2203b);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.D(this.f2198d.f2203b, false);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.c0 f2208a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2209b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2210c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2211d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2212e;

        public j(RecyclerView.c0 c0Var, int i8, int i9, int i10, int i11) {
            this.f2208a = c0Var;
            this.f2209b = i8;
            this.f2210c = i9;
            this.f2211d = i10;
            this.f2212e = i11;
        }
    }

    public void Q(RecyclerView.c0 c0Var) {
        View view = c0Var.itemView;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.f2170p.add(c0Var);
        viewPropertyAnimatorAnimate.alpha(1.0f).setDuration(l()).setListener(new e(c0Var, view, viewPropertyAnimatorAnimate)).start();
    }

    public void R(i iVar) {
        RecyclerView.c0 c0Var = iVar.f2202a;
        View view = c0Var == null ? null : c0Var.itemView;
        RecyclerView.c0 c0Var2 = iVar.f2203b;
        View view2 = c0Var2 != null ? c0Var2.itemView : null;
        if (view != null) {
            ViewPropertyAnimator duration = view.animate().setDuration(m());
            this.f2173s.add(iVar.f2202a);
            duration.translationX(iVar.f2206e - iVar.f2204c);
            duration.translationY(iVar.f2207f - iVar.f2205d);
            duration.alpha(0.0f).setListener(new g(iVar, duration, view)).start();
        }
        if (view2 != null) {
            ViewPropertyAnimator viewPropertyAnimatorAnimate = view2.animate();
            this.f2173s.add(iVar.f2203b);
            viewPropertyAnimatorAnimate.translationX(0.0f).translationY(0.0f).setDuration(m()).alpha(1.0f).setListener(new h(iVar, viewPropertyAnimatorAnimate, view2)).start();
        }
    }

    public void S(RecyclerView.c0 c0Var, int i8, int i9, int i10, int i11) {
        View view = c0Var.itemView;
        int i12 = i10 - i8;
        int i13 = i11 - i9;
        if (i12 != 0) {
            view.animate().translationX(0.0f);
        }
        if (i13 != 0) {
            view.animate().translationY(0.0f);
        }
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.f2171q.add(c0Var);
        viewPropertyAnimatorAnimate.setDuration(n()).setListener(new f(c0Var, i12, view, i13, viewPropertyAnimatorAnimate)).start();
    }

    public final void T(RecyclerView.c0 c0Var) {
        View view = c0Var.itemView;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.f2172r.add(c0Var);
        viewPropertyAnimatorAnimate.setDuration(o()).alpha(0.0f).setListener(new d(c0Var, viewPropertyAnimatorAnimate, view)).start();
    }

    public void U(List<RecyclerView.c0> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            list.get(size).itemView.animate().cancel();
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
            if (Y(iVar, c0Var) && iVar.f2202a == null && iVar.f2203b == null) {
                list.remove(iVar);
            }
        }
    }

    public final void X(i iVar) {
        RecyclerView.c0 c0Var = iVar.f2202a;
        if (c0Var != null) {
            Y(iVar, c0Var);
        }
        RecyclerView.c0 c0Var2 = iVar.f2203b;
        if (c0Var2 != null) {
            Y(iVar, c0Var2);
        }
    }

    public final boolean Y(i iVar, RecyclerView.c0 c0Var) {
        boolean z7 = false;
        if (iVar.f2203b == c0Var) {
            iVar.f2203b = null;
        } else {
            if (iVar.f2202a != c0Var) {
                return false;
            }
            iVar.f2202a = null;
            z7 = true;
        }
        c0Var.itemView.setAlpha(1.0f);
        c0Var.itemView.setTranslationX(0.0f);
        c0Var.itemView.setTranslationY(0.0f);
        C(c0Var, z7);
        return true;
    }

    public final void Z(RecyclerView.c0 c0Var) {
        if (f2162h == null) {
            f2162h = new ValueAnimator().getInterpolator();
        }
        c0Var.itemView.animate().setInterpolator(f2162h);
        j(c0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean g(RecyclerView.c0 c0Var, List<Object> list) {
        return !list.isEmpty() || super.g(c0Var, list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void j(RecyclerView.c0 c0Var) {
        View view = c0Var.itemView;
        view.animate().cancel();
        int size = this.f2165k.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (this.f2165k.get(size).f2208a == c0Var) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                E(c0Var);
                this.f2165k.remove(size);
            }
        }
        W(this.f2166l, c0Var);
        if (this.f2163i.remove(c0Var)) {
            view.setAlpha(1.0f);
            G(c0Var);
        }
        if (this.f2164j.remove(c0Var)) {
            view.setAlpha(1.0f);
            A(c0Var);
        }
        for (int size2 = this.f2169o.size() - 1; size2 >= 0; size2--) {
            ArrayList<i> arrayList = this.f2169o.get(size2);
            W(arrayList, c0Var);
            if (arrayList.isEmpty()) {
                this.f2169o.remove(size2);
            }
        }
        for (int size3 = this.f2168n.size() - 1; size3 >= 0; size3--) {
            ArrayList<j> arrayList2 = this.f2168n.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (arrayList2.get(size4).f2208a == c0Var) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    E(c0Var);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.f2168n.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.f2167m.size() - 1; size5 >= 0; size5--) {
            ArrayList<RecyclerView.c0> arrayList3 = this.f2167m.get(size5);
            if (arrayList3.remove(c0Var)) {
                view.setAlpha(1.0f);
                A(c0Var);
                if (arrayList3.isEmpty()) {
                    this.f2167m.remove(size5);
                }
            }
        }
        this.f2172r.remove(c0Var);
        this.f2170p.remove(c0Var);
        this.f2173s.remove(c0Var);
        this.f2171q.remove(c0Var);
        V();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void k() {
        int size = this.f2165k.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            j jVar = this.f2165k.get(size);
            View view = jVar.f2208a.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            E(jVar.f2208a);
            this.f2165k.remove(size);
        }
        for (int size2 = this.f2163i.size() - 1; size2 >= 0; size2--) {
            G(this.f2163i.get(size2));
            this.f2163i.remove(size2);
        }
        int size3 = this.f2164j.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.c0 c0Var = this.f2164j.get(size3);
            c0Var.itemView.setAlpha(1.0f);
            A(c0Var);
            this.f2164j.remove(size3);
        }
        for (int size4 = this.f2166l.size() - 1; size4 >= 0; size4--) {
            X(this.f2166l.get(size4));
        }
        this.f2166l.clear();
        if (p()) {
            for (int size5 = this.f2168n.size() - 1; size5 >= 0; size5--) {
                ArrayList<j> arrayList = this.f2168n.get(size5);
                for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                    j jVar2 = arrayList.get(size6);
                    View view2 = jVar2.f2208a.itemView;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    E(jVar2.f2208a);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.f2168n.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.f2167m.size() - 1; size7 >= 0; size7--) {
                ArrayList<RecyclerView.c0> arrayList2 = this.f2167m.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    RecyclerView.c0 c0Var2 = arrayList2.get(size8);
                    c0Var2.itemView.setAlpha(1.0f);
                    A(c0Var2);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.f2167m.remove(arrayList2);
                    }
                }
            }
            for (int size9 = this.f2169o.size() - 1; size9 >= 0; size9--) {
                ArrayList<i> arrayList3 = this.f2169o.get(size9);
                for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                    X(arrayList3.get(size10));
                    if (arrayList3.isEmpty()) {
                        this.f2169o.remove(arrayList3);
                    }
                }
            }
            U(this.f2172r);
            U(this.f2171q);
            U(this.f2170p);
            U(this.f2173s);
            i();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean p() {
        return (this.f2164j.isEmpty() && this.f2166l.isEmpty() && this.f2165k.isEmpty() && this.f2163i.isEmpty() && this.f2171q.isEmpty() && this.f2172r.isEmpty() && this.f2170p.isEmpty() && this.f2173s.isEmpty() && this.f2168n.isEmpty() && this.f2167m.isEmpty() && this.f2169o.isEmpty()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void u() {
        boolean z7 = !this.f2163i.isEmpty();
        boolean z8 = !this.f2165k.isEmpty();
        boolean z9 = !this.f2166l.isEmpty();
        boolean z10 = !this.f2164j.isEmpty();
        if (z7 || z8 || z10 || z9) {
            Iterator<RecyclerView.c0> it = this.f2163i.iterator();
            while (it.hasNext()) {
                T(it.next());
            }
            this.f2163i.clear();
            if (z8) {
                ArrayList<j> arrayList = new ArrayList<>();
                arrayList.addAll(this.f2165k);
                this.f2168n.add(arrayList);
                this.f2165k.clear();
                a aVar = new a(arrayList);
                if (z7) {
                    t.e0(arrayList.get(0).f2208a.itemView, aVar, o());
                } else {
                    aVar.run();
                }
            }
            if (z9) {
                ArrayList<i> arrayList2 = new ArrayList<>();
                arrayList2.addAll(this.f2166l);
                this.f2169o.add(arrayList2);
                this.f2166l.clear();
                b bVar = new b(arrayList2);
                if (z7) {
                    t.e0(arrayList2.get(0).f2202a.itemView, bVar, o());
                } else {
                    bVar.run();
                }
            }
            if (z10) {
                ArrayList<RecyclerView.c0> arrayList3 = new ArrayList<>();
                arrayList3.addAll(this.f2164j);
                this.f2167m.add(arrayList3);
                this.f2164j.clear();
                RunnableC0010c runnableC0010c = new RunnableC0010c(arrayList3);
                if (z7 || z8 || z9) {
                    t.e0(arrayList3.get(0).itemView, runnableC0010c, (z7 ? o() : 0L) + Math.max(z8 ? n() : 0L, z9 ? m() : 0L));
                } else {
                    runnableC0010c.run();
                }
            }
        }
    }

    @Override // c1.n
    public boolean w(RecyclerView.c0 c0Var) {
        Z(c0Var);
        c0Var.itemView.setAlpha(0.0f);
        this.f2164j.add(c0Var);
        return true;
    }

    @Override // c1.n
    public boolean x(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, int i8, int i9, int i10, int i11) {
        if (c0Var == c0Var2) {
            return y(c0Var, i8, i9, i10, i11);
        }
        float translationX = c0Var.itemView.getTranslationX();
        float translationY = c0Var.itemView.getTranslationY();
        float alpha = c0Var.itemView.getAlpha();
        Z(c0Var);
        int i12 = (int) ((i10 - i8) - translationX);
        int i13 = (int) ((i11 - i9) - translationY);
        c0Var.itemView.setTranslationX(translationX);
        c0Var.itemView.setTranslationY(translationY);
        c0Var.itemView.setAlpha(alpha);
        if (c0Var2 != null) {
            Z(c0Var2);
            c0Var2.itemView.setTranslationX(-i12);
            c0Var2.itemView.setTranslationY(-i13);
            c0Var2.itemView.setAlpha(0.0f);
        }
        this.f2166l.add(new i(c0Var, c0Var2, i8, i9, i10, i11));
        return true;
    }

    @Override // c1.n
    public boolean y(RecyclerView.c0 c0Var, int i8, int i9, int i10, int i11) {
        View view = c0Var.itemView;
        int translationX = i8 + ((int) view.getTranslationX());
        int translationY = i9 + ((int) c0Var.itemView.getTranslationY());
        Z(c0Var);
        int i12 = i10 - translationX;
        int i13 = i11 - translationY;
        if (i12 == 0 && i13 == 0) {
            E(c0Var);
            return false;
        }
        if (i12 != 0) {
            view.setTranslationX(-i12);
        }
        if (i13 != 0) {
            view.setTranslationY(-i13);
        }
        this.f2165k.add(new j(c0Var, translationX, translationY, i10, i11));
        return true;
    }

    @Override // c1.n
    public boolean z(RecyclerView.c0 c0Var) {
        Z(c0Var);
        this.f2163i.add(c0Var);
        return true;
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.c0 f2202a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView.c0 f2203b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2204c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2205d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2206e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f2207f;

        public i(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2) {
            this.f2202a = c0Var;
            this.f2203b = c0Var2;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.f2202a + ", newHolder=" + this.f2203b + ", fromX=" + this.f2204c + ", fromY=" + this.f2205d + ", toX=" + this.f2206e + ", toY=" + this.f2207f + '}';
        }

        public i(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, int i8, int i9, int i10, int i11) {
            this(c0Var, c0Var2);
            this.f2204c = i8;
            this.f2205d = i9;
            this.f2206e = i10;
            this.f2207f = i11;
        }
    }
}
