package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import i0.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: DefaultItemAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends q {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private static TimeInterpolator f3029s;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ArrayList<RecyclerView.c0> f3030h = new ArrayList<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private ArrayList<RecyclerView.c0> f3031i = new ArrayList<>();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private ArrayList<j> f3032j = new ArrayList<>();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private ArrayList<i> f3033k = new ArrayList<>();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    ArrayList<ArrayList<RecyclerView.c0>> f3034l = new ArrayList<>();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    ArrayList<ArrayList<j>> f3035m = new ArrayList<>();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    ArrayList<ArrayList<i>> f3036n = new ArrayList<>();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    ArrayList<RecyclerView.c0> f3037o = new ArrayList<>();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    ArrayList<RecyclerView.c0> f3038p = new ArrayList<>();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    ArrayList<RecyclerView.c0> f3039q = new ArrayList<>();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    ArrayList<RecyclerView.c0> f3040r = new ArrayList<>();

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    class a implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ ArrayList f3041e;

        a(ArrayList arrayList) {
            this.f3041e = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (j jVar : this.f3041e) {
                e.this.T(jVar.f3075a, jVar.f3076b, jVar.f3077c, jVar.f3078d, jVar.f3079e);
            }
            this.f3041e.clear();
            e.this.f3035m.remove(this.f3041e);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    class b implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ ArrayList f3043e;

        b(ArrayList arrayList) {
            this.f3043e = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f3043e.iterator();
            while (it.hasNext()) {
                e.this.S((i) it.next());
            }
            this.f3043e.clear();
            e.this.f3036n.remove(this.f3043e);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    class c implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ ArrayList f3045e;

        c(ArrayList arrayList) {
            this.f3045e = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f3045e.iterator();
            while (it.hasNext()) {
                e.this.R((RecyclerView.c0) it.next());
            }
            this.f3045e.clear();
            e.this.f3034l.remove(this.f3045e);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.c0 f3047a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f3048b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f3049c;

        d(RecyclerView.c0 c0Var, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f3047a = c0Var;
            this.f3048b = viewPropertyAnimator;
            this.f3049c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f3048b.setListener(null);
            this.f3049c.setAlpha(1.0f);
            e.this.G(this.f3047a);
            e.this.f3039q.remove(this.f3047a);
            e.this.W();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            e.this.H(this.f3047a);
        }
    }

    /* JADX INFO: renamed from: androidx.recyclerview.widget.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    class C0028e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.c0 f3051a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f3052b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f3053c;

        C0028e(RecyclerView.c0 c0Var, View view, ViewPropertyAnimator viewPropertyAnimator) {
            this.f3051a = c0Var;
            this.f3052b = view;
            this.f3053c = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f3052b.setAlpha(1.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f3053c.setListener(null);
            e.this.A(this.f3051a);
            e.this.f3037o.remove(this.f3051a);
            e.this.W();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            e.this.B(this.f3051a);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    class f extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.c0 f3055a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f3056b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f3057c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f3058d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f3059e;

        f(RecyclerView.c0 c0Var, int i7, View view, int i8, ViewPropertyAnimator viewPropertyAnimator) {
            this.f3055a = c0Var;
            this.f3056b = i7;
            this.f3057c = view;
            this.f3058d = i8;
            this.f3059e = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (this.f3056b != 0) {
                this.f3057c.setTranslationX(0.0f);
            }
            if (this.f3058d != 0) {
                this.f3057c.setTranslationY(0.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f3059e.setListener(null);
            e.this.E(this.f3055a);
            e.this.f3038p.remove(this.f3055a);
            e.this.W();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            e.this.F(this.f3055a);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    class g extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ i f3061a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f3062b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f3063c;

        g(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f3061a = iVar;
            this.f3062b = viewPropertyAnimator;
            this.f3063c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f3062b.setListener(null);
            this.f3063c.setAlpha(1.0f);
            this.f3063c.setTranslationX(0.0f);
            this.f3063c.setTranslationY(0.0f);
            e.this.C(this.f3061a.f3069a, true);
            e.this.f3040r.remove(this.f3061a.f3069a);
            e.this.W();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            e.this.D(this.f3061a.f3069a, true);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    class h extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ i f3065a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f3066b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f3067c;

        h(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f3065a = iVar;
            this.f3066b = viewPropertyAnimator;
            this.f3067c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f3066b.setListener(null);
            this.f3067c.setAlpha(1.0f);
            this.f3067c.setTranslationX(0.0f);
            this.f3067c.setTranslationY(0.0f);
            e.this.C(this.f3065a.f3070b, false);
            e.this.f3040r.remove(this.f3065a.f3070b);
            e.this.W();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            e.this.D(this.f3065a.f3070b, false);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    private static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.c0 f3075a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3076b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3077c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3078d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f3079e;

        j(RecyclerView.c0 c0Var, int i7, int i8, int i9, int i10) {
            this.f3075a = c0Var;
            this.f3076b = i7;
            this.f3077c = i8;
            this.f3078d = i9;
            this.f3079e = i10;
        }
    }

    private void U(RecyclerView.c0 c0Var) {
        View view = c0Var.f2857a;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.f3039q.add(c0Var);
        viewPropertyAnimatorAnimate.setDuration(o()).alpha(0.0f).setListener(new d(c0Var, viewPropertyAnimatorAnimate, view)).start();
    }

    private void X(List<i> list, RecyclerView.c0 c0Var) {
        for (int size = list.size() - 1; size >= 0; size--) {
            i iVar = list.get(size);
            if (Z(iVar, c0Var) && iVar.f3069a == null && iVar.f3070b == null) {
                list.remove(iVar);
            }
        }
    }

    private void Y(i iVar) {
        RecyclerView.c0 c0Var = iVar.f3069a;
        if (c0Var != null) {
            Z(iVar, c0Var);
        }
        RecyclerView.c0 c0Var2 = iVar.f3070b;
        if (c0Var2 != null) {
            Z(iVar, c0Var2);
        }
    }

    private boolean Z(i iVar, RecyclerView.c0 c0Var) {
        boolean z6 = false;
        if (iVar.f3070b == c0Var) {
            iVar.f3070b = null;
        } else {
            if (iVar.f3069a != c0Var) {
                return false;
            }
            iVar.f3069a = null;
            z6 = true;
        }
        c0Var.f2857a.setAlpha(1.0f);
        c0Var.f2857a.setTranslationX(0.0f);
        c0Var.f2857a.setTranslationY(0.0f);
        C(c0Var, z6);
        return true;
    }

    private void a0(RecyclerView.c0 c0Var) {
        if (f3029s == null) {
            f3029s = new ValueAnimator().getInterpolator();
        }
        c0Var.f2857a.animate().setInterpolator(f3029s);
        j(c0Var);
    }

    void R(RecyclerView.c0 c0Var) {
        View view = c0Var.f2857a;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.f3037o.add(c0Var);
        viewPropertyAnimatorAnimate.alpha(1.0f).setDuration(l()).setListener(new C0028e(c0Var, view, viewPropertyAnimatorAnimate)).start();
    }

    void S(i iVar) {
        RecyclerView.c0 c0Var = iVar.f3069a;
        View view = c0Var == null ? null : c0Var.f2857a;
        RecyclerView.c0 c0Var2 = iVar.f3070b;
        View view2 = c0Var2 != null ? c0Var2.f2857a : null;
        if (view != null) {
            ViewPropertyAnimator duration = view.animate().setDuration(m());
            this.f3040r.add(iVar.f3069a);
            duration.translationX(iVar.f3073e - iVar.f3071c);
            duration.translationY(iVar.f3074f - iVar.f3072d);
            duration.alpha(0.0f).setListener(new g(iVar, duration, view)).start();
        }
        if (view2 != null) {
            ViewPropertyAnimator viewPropertyAnimatorAnimate = view2.animate();
            this.f3040r.add(iVar.f3070b);
            viewPropertyAnimatorAnimate.translationX(0.0f).translationY(0.0f).setDuration(m()).alpha(1.0f).setListener(new h(iVar, viewPropertyAnimatorAnimate, view2)).start();
        }
    }

    void T(RecyclerView.c0 c0Var, int i7, int i8, int i9, int i10) {
        View view = c0Var.f2857a;
        int i11 = i9 - i7;
        int i12 = i10 - i8;
        if (i11 != 0) {
            view.animate().translationX(0.0f);
        }
        if (i12 != 0) {
            view.animate().translationY(0.0f);
        }
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.f3038p.add(c0Var);
        viewPropertyAnimatorAnimate.setDuration(n()).setListener(new f(c0Var, i11, view, i12, viewPropertyAnimatorAnimate)).start();
    }

    void V(List<RecyclerView.c0> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            list.get(size).f2857a.animate().cancel();
        }
    }

    void W() {
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
        View view = c0Var.f2857a;
        view.animate().cancel();
        int size = this.f3032j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (this.f3032j.get(size).f3075a == c0Var) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                E(c0Var);
                this.f3032j.remove(size);
            }
        }
        X(this.f3033k, c0Var);
        if (this.f3030h.remove(c0Var)) {
            view.setAlpha(1.0f);
            G(c0Var);
        }
        if (this.f3031i.remove(c0Var)) {
            view.setAlpha(1.0f);
            A(c0Var);
        }
        for (int size2 = this.f3036n.size() - 1; size2 >= 0; size2--) {
            ArrayList<i> arrayList = this.f3036n.get(size2);
            X(arrayList, c0Var);
            if (arrayList.isEmpty()) {
                this.f3036n.remove(size2);
            }
        }
        for (int size3 = this.f3035m.size() - 1; size3 >= 0; size3--) {
            ArrayList<j> arrayList2 = this.f3035m.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (arrayList2.get(size4).f3075a == c0Var) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    E(c0Var);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.f3035m.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.f3034l.size() - 1; size5 >= 0; size5--) {
            ArrayList<RecyclerView.c0> arrayList3 = this.f3034l.get(size5);
            if (arrayList3.remove(c0Var)) {
                view.setAlpha(1.0f);
                A(c0Var);
                if (arrayList3.isEmpty()) {
                    this.f3034l.remove(size5);
                }
            }
        }
        this.f3039q.remove(c0Var);
        this.f3037o.remove(c0Var);
        this.f3040r.remove(c0Var);
        this.f3038p.remove(c0Var);
        W();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void k() {
        int size = this.f3032j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            j jVar = this.f3032j.get(size);
            View view = jVar.f3075a.f2857a;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            E(jVar.f3075a);
            this.f3032j.remove(size);
        }
        for (int size2 = this.f3030h.size() - 1; size2 >= 0; size2--) {
            G(this.f3030h.get(size2));
            this.f3030h.remove(size2);
        }
        int size3 = this.f3031i.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.c0 c0Var = this.f3031i.get(size3);
            c0Var.f2857a.setAlpha(1.0f);
            A(c0Var);
            this.f3031i.remove(size3);
        }
        for (int size4 = this.f3033k.size() - 1; size4 >= 0; size4--) {
            Y(this.f3033k.get(size4));
        }
        this.f3033k.clear();
        if (p()) {
            for (int size5 = this.f3035m.size() - 1; size5 >= 0; size5--) {
                ArrayList<j> arrayList = this.f3035m.get(size5);
                for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                    j jVar2 = arrayList.get(size6);
                    View view2 = jVar2.f3075a.f2857a;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    E(jVar2.f3075a);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.f3035m.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.f3034l.size() - 1; size7 >= 0; size7--) {
                ArrayList<RecyclerView.c0> arrayList2 = this.f3034l.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    RecyclerView.c0 c0Var2 = arrayList2.get(size8);
                    c0Var2.f2857a.setAlpha(1.0f);
                    A(c0Var2);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.f3034l.remove(arrayList2);
                    }
                }
            }
            for (int size9 = this.f3036n.size() - 1; size9 >= 0; size9--) {
                ArrayList<i> arrayList3 = this.f3036n.get(size9);
                for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                    Y(arrayList3.get(size10));
                    if (arrayList3.isEmpty()) {
                        this.f3036n.remove(arrayList3);
                    }
                }
            }
            V(this.f3039q);
            V(this.f3038p);
            V(this.f3037o);
            V(this.f3040r);
            i();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean p() {
        return (this.f3031i.isEmpty() && this.f3033k.isEmpty() && this.f3032j.isEmpty() && this.f3030h.isEmpty() && this.f3038p.isEmpty() && this.f3039q.isEmpty() && this.f3037o.isEmpty() && this.f3040r.isEmpty() && this.f3035m.isEmpty() && this.f3034l.isEmpty() && this.f3036n.isEmpty()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void u() {
        boolean z6 = !this.f3030h.isEmpty();
        boolean z7 = !this.f3032j.isEmpty();
        boolean z8 = !this.f3033k.isEmpty();
        boolean z9 = !this.f3031i.isEmpty();
        if (z6 || z7 || z9 || z8) {
            Iterator<RecyclerView.c0> it = this.f3030h.iterator();
            while (it.hasNext()) {
                U(it.next());
            }
            this.f3030h.clear();
            if (z7) {
                ArrayList<j> arrayList = new ArrayList<>();
                arrayList.addAll(this.f3032j);
                this.f3035m.add(arrayList);
                this.f3032j.clear();
                a aVar = new a(arrayList);
                if (z6) {
                    u.d0(arrayList.get(0).f3075a.f2857a, aVar, o());
                } else {
                    aVar.run();
                }
            }
            if (z8) {
                ArrayList<i> arrayList2 = new ArrayList<>();
                arrayList2.addAll(this.f3033k);
                this.f3036n.add(arrayList2);
                this.f3033k.clear();
                b bVar = new b(arrayList2);
                if (z6) {
                    u.d0(arrayList2.get(0).f3069a.f2857a, bVar, o());
                } else {
                    bVar.run();
                }
            }
            if (z9) {
                ArrayList<RecyclerView.c0> arrayList3 = new ArrayList<>();
                arrayList3.addAll(this.f3031i);
                this.f3034l.add(arrayList3);
                this.f3031i.clear();
                c cVar = new c(arrayList3);
                if (z6 || z7 || z8) {
                    u.d0(arrayList3.get(0).f2857a, cVar, (z6 ? o() : 0L) + Math.max(z7 ? n() : 0L, z8 ? m() : 0L));
                } else {
                    cVar.run();
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.q
    public boolean w(RecyclerView.c0 c0Var) {
        a0(c0Var);
        c0Var.f2857a.setAlpha(0.0f);
        this.f3031i.add(c0Var);
        return true;
    }

    @Override // androidx.recyclerview.widget.q
    public boolean x(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, int i7, int i8, int i9, int i10) {
        if (c0Var == c0Var2) {
            return y(c0Var, i7, i8, i9, i10);
        }
        float translationX = c0Var.f2857a.getTranslationX();
        float translationY = c0Var.f2857a.getTranslationY();
        float alpha = c0Var.f2857a.getAlpha();
        a0(c0Var);
        int i11 = (int) ((i9 - i7) - translationX);
        int i12 = (int) ((i10 - i8) - translationY);
        c0Var.f2857a.setTranslationX(translationX);
        c0Var.f2857a.setTranslationY(translationY);
        c0Var.f2857a.setAlpha(alpha);
        if (c0Var2 != null) {
            a0(c0Var2);
            c0Var2.f2857a.setTranslationX(-i11);
            c0Var2.f2857a.setTranslationY(-i12);
            c0Var2.f2857a.setAlpha(0.0f);
        }
        this.f3033k.add(new i(c0Var, c0Var2, i7, i8, i9, i10));
        return true;
    }

    @Override // androidx.recyclerview.widget.q
    public boolean y(RecyclerView.c0 c0Var, int i7, int i8, int i9, int i10) {
        View view = c0Var.f2857a;
        int translationX = i7 + ((int) view.getTranslationX());
        int translationY = i8 + ((int) c0Var.f2857a.getTranslationY());
        a0(c0Var);
        int i11 = i9 - translationX;
        int i12 = i10 - translationY;
        if (i11 == 0 && i12 == 0) {
            E(c0Var);
            return false;
        }
        if (i11 != 0) {
            view.setTranslationX(-i11);
        }
        if (i12 != 0) {
            view.setTranslationY(-i12);
        }
        this.f3032j.add(new j(c0Var, translationX, translationY, i9, i10));
        return true;
    }

    @Override // androidx.recyclerview.widget.q
    public boolean z(RecyclerView.c0 c0Var) {
        a0(c0Var);
        this.f3030h.add(c0Var);
        return true;
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    private static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.c0 f3069a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView.c0 f3070b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3071c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3072d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f3073e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f3074f;

        private i(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2) {
            this.f3069a = c0Var;
            this.f3070b = c0Var2;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.f3069a + ", newHolder=" + this.f3070b + ", fromX=" + this.f3071c + ", fromY=" + this.f3072d + ", toX=" + this.f3073e + ", toY=" + this.f3074f + '}';
        }

        i(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, int i7, int i8, int i9, int i10) {
            this(c0Var, c0Var2);
            this.f3071c = i7;
            this.f3072d = i8;
            this.f3073e = i9;
            this.f3074f = i10;
        }
    }
}
