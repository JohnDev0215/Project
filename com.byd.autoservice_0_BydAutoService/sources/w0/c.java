package w0;

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
import l0.t;

/* JADX INFO: compiled from: DefaultItemAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends n {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static TimeInterpolator f5534h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList<RecyclerView.c0> f5535i = new ArrayList<>();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList<RecyclerView.c0> f5536j = new ArrayList<>();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ArrayList<j> f5537k = new ArrayList<>();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ArrayList<i> f5538l = new ArrayList<>();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ArrayList<ArrayList<RecyclerView.c0>> f5539m = new ArrayList<>();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ArrayList<ArrayList<j>> f5540n = new ArrayList<>();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ArrayList<ArrayList<i>> f5541o = new ArrayList<>();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList<RecyclerView.c0> f5542p = new ArrayList<>();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ArrayList<RecyclerView.c0> f5543q = new ArrayList<>();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ArrayList<RecyclerView.c0> f5544r = new ArrayList<>();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ArrayList<RecyclerView.c0> f5545s = new ArrayList<>();

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ArrayList f5546b;

        public a(ArrayList arrayList) {
            this.f5546b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (j jVar : this.f5546b) {
                c.this.S(jVar.f5580a, jVar.f5581b, jVar.f5582c, jVar.f5583d, jVar.f5584e);
            }
            this.f5546b.clear();
            c.this.f5540n.remove(this.f5546b);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ArrayList f5548b;

        public b(ArrayList arrayList) {
            this.f5548b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f5548b.iterator();
            while (it.hasNext()) {
                c.this.R((i) it.next());
            }
            this.f5548b.clear();
            c.this.f5541o.remove(this.f5548b);
        }
    }

    /* JADX INFO: renamed from: w0.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class RunnableC0070c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ArrayList f5550b;

        public RunnableC0070c(ArrayList arrayList) {
            this.f5550b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f5550b.iterator();
            while (it.hasNext()) {
                c.this.Q((RecyclerView.c0) it.next());
            }
            this.f5550b.clear();
            c.this.f5539m.remove(this.f5550b);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.c0 f5552a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ViewPropertyAnimator f5553b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f5554c;

        public d(RecyclerView.c0 c0Var, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f5552a = c0Var;
            this.f5553b = viewPropertyAnimator;
            this.f5554c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f5553b.setListener(null);
            this.f5554c.setAlpha(1.0f);
            c.this.G(this.f5552a);
            c.this.f5544r.remove(this.f5552a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.H(this.f5552a);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.c0 f5556a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f5557b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ ViewPropertyAnimator f5558c;

        public e(RecyclerView.c0 c0Var, View view, ViewPropertyAnimator viewPropertyAnimator) {
            this.f5556a = c0Var;
            this.f5557b = view;
            this.f5558c = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f5557b.setAlpha(1.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f5558c.setListener(null);
            c.this.A(this.f5556a);
            c.this.f5542p.remove(this.f5556a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.B(this.f5556a);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class f extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.c0 f5560a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f5561b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f5562c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f5563d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ ViewPropertyAnimator f5564e;

        public f(RecyclerView.c0 c0Var, int i3, View view, int i4, ViewPropertyAnimator viewPropertyAnimator) {
            this.f5560a = c0Var;
            this.f5561b = i3;
            this.f5562c = view;
            this.f5563d = i4;
            this.f5564e = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (this.f5561b != 0) {
                this.f5562c.setTranslationX(0.0f);
            }
            if (this.f5563d != 0) {
                this.f5562c.setTranslationY(0.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f5564e.setListener(null);
            c.this.E(this.f5560a);
            c.this.f5543q.remove(this.f5560a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.F(this.f5560a);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class g extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ i f5566a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ViewPropertyAnimator f5567b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f5568c;

        public g(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f5566a = iVar;
            this.f5567b = viewPropertyAnimator;
            this.f5568c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f5567b.setListener(null);
            this.f5568c.setAlpha(1.0f);
            this.f5568c.setTranslationX(0.0f);
            this.f5568c.setTranslationY(0.0f);
            c.this.C(this.f5566a.f5574a, true);
            c.this.f5545s.remove(this.f5566a.f5574a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.D(this.f5566a.f5574a, true);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class h extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ i f5570a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ViewPropertyAnimator f5571b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f5572c;

        public h(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f5570a = iVar;
            this.f5571b = viewPropertyAnimator;
            this.f5572c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f5571b.setListener(null);
            this.f5572c.setAlpha(1.0f);
            this.f5572c.setTranslationX(0.0f);
            this.f5572c.setTranslationY(0.0f);
            c.this.C(this.f5570a.f5575b, false);
            c.this.f5545s.remove(this.f5570a.f5575b);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.D(this.f5570a.f5575b, false);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.c0 f5580a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5581b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5582c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5583d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5584e;

        public j(RecyclerView.c0 c0Var, int i3, int i4, int i5, int i6) {
            this.f5580a = c0Var;
            this.f5581b = i3;
            this.f5582c = i4;
            this.f5583d = i5;
            this.f5584e = i6;
        }
    }

    public void Q(RecyclerView.c0 c0Var) {
        View view = c0Var.f1474b;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.f5542p.add(c0Var);
        viewPropertyAnimatorAnimate.alpha(1.0f).setDuration(l()).setListener(new e(c0Var, view, viewPropertyAnimatorAnimate)).start();
    }

    public void R(i iVar) {
        RecyclerView.c0 c0Var = iVar.f5574a;
        View view = c0Var == null ? null : c0Var.f1474b;
        RecyclerView.c0 c0Var2 = iVar.f5575b;
        View view2 = c0Var2 != null ? c0Var2.f1474b : null;
        if (view != null) {
            ViewPropertyAnimator duration = view.animate().setDuration(m());
            this.f5545s.add(iVar.f5574a);
            duration.translationX(iVar.f5578e - iVar.f5576c);
            duration.translationY(iVar.f5579f - iVar.f5577d);
            duration.alpha(0.0f).setListener(new g(iVar, duration, view)).start();
        }
        if (view2 != null) {
            ViewPropertyAnimator viewPropertyAnimatorAnimate = view2.animate();
            this.f5545s.add(iVar.f5575b);
            viewPropertyAnimatorAnimate.translationX(0.0f).translationY(0.0f).setDuration(m()).alpha(1.0f).setListener(new h(iVar, viewPropertyAnimatorAnimate, view2)).start();
        }
    }

    public void S(RecyclerView.c0 c0Var, int i3, int i4, int i5, int i6) {
        View view = c0Var.f1474b;
        int i7 = i5 - i3;
        int i8 = i6 - i4;
        if (i7 != 0) {
            view.animate().translationX(0.0f);
        }
        if (i8 != 0) {
            view.animate().translationY(0.0f);
        }
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.f5543q.add(c0Var);
        viewPropertyAnimatorAnimate.setDuration(n()).setListener(new f(c0Var, i7, view, i8, viewPropertyAnimatorAnimate)).start();
    }

    public final void T(RecyclerView.c0 c0Var) {
        View view = c0Var.f1474b;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.f5544r.add(c0Var);
        viewPropertyAnimatorAnimate.setDuration(o()).alpha(0.0f).setListener(new d(c0Var, viewPropertyAnimatorAnimate, view)).start();
    }

    public void U(List<RecyclerView.c0> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            list.get(size).f1474b.animate().cancel();
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
            if (Y(iVar, c0Var) && iVar.f5574a == null && iVar.f5575b == null) {
                list.remove(iVar);
            }
        }
    }

    public final void X(i iVar) {
        RecyclerView.c0 c0Var = iVar.f5574a;
        if (c0Var != null) {
            Y(iVar, c0Var);
        }
        RecyclerView.c0 c0Var2 = iVar.f5575b;
        if (c0Var2 != null) {
            Y(iVar, c0Var2);
        }
    }

    public final boolean Y(i iVar, RecyclerView.c0 c0Var) {
        boolean z3 = false;
        if (iVar.f5575b == c0Var) {
            iVar.f5575b = null;
        } else {
            if (iVar.f5574a != c0Var) {
                return false;
            }
            iVar.f5574a = null;
            z3 = true;
        }
        c0Var.f1474b.setAlpha(1.0f);
        c0Var.f1474b.setTranslationX(0.0f);
        c0Var.f1474b.setTranslationY(0.0f);
        C(c0Var, z3);
        return true;
    }

    public final void Z(RecyclerView.c0 c0Var) {
        if (f5534h == null) {
            f5534h = new ValueAnimator().getInterpolator();
        }
        c0Var.f1474b.animate().setInterpolator(f5534h);
        j(c0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean g(RecyclerView.c0 c0Var, List<Object> list) {
        return !list.isEmpty() || super.g(c0Var, list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void j(RecyclerView.c0 c0Var) {
        View view = c0Var.f1474b;
        view.animate().cancel();
        int size = this.f5537k.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (this.f5537k.get(size).f5580a == c0Var) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                E(c0Var);
                this.f5537k.remove(size);
            }
        }
        W(this.f5538l, c0Var);
        if (this.f5535i.remove(c0Var)) {
            view.setAlpha(1.0f);
            G(c0Var);
        }
        if (this.f5536j.remove(c0Var)) {
            view.setAlpha(1.0f);
            A(c0Var);
        }
        for (int size2 = this.f5541o.size() - 1; size2 >= 0; size2--) {
            ArrayList<i> arrayList = this.f5541o.get(size2);
            W(arrayList, c0Var);
            if (arrayList.isEmpty()) {
                this.f5541o.remove(size2);
            }
        }
        for (int size3 = this.f5540n.size() - 1; size3 >= 0; size3--) {
            ArrayList<j> arrayList2 = this.f5540n.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (arrayList2.get(size4).f5580a == c0Var) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    E(c0Var);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.f5540n.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.f5539m.size() - 1; size5 >= 0; size5--) {
            ArrayList<RecyclerView.c0> arrayList3 = this.f5539m.get(size5);
            if (arrayList3.remove(c0Var)) {
                view.setAlpha(1.0f);
                A(c0Var);
                if (arrayList3.isEmpty()) {
                    this.f5539m.remove(size5);
                }
            }
        }
        this.f5544r.remove(c0Var);
        this.f5542p.remove(c0Var);
        this.f5545s.remove(c0Var);
        this.f5543q.remove(c0Var);
        V();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void k() {
        int size = this.f5537k.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            j jVar = this.f5537k.get(size);
            View view = jVar.f5580a.f1474b;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            E(jVar.f5580a);
            this.f5537k.remove(size);
        }
        for (int size2 = this.f5535i.size() - 1; size2 >= 0; size2--) {
            G(this.f5535i.get(size2));
            this.f5535i.remove(size2);
        }
        int size3 = this.f5536j.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.c0 c0Var = this.f5536j.get(size3);
            c0Var.f1474b.setAlpha(1.0f);
            A(c0Var);
            this.f5536j.remove(size3);
        }
        for (int size4 = this.f5538l.size() - 1; size4 >= 0; size4--) {
            X(this.f5538l.get(size4));
        }
        this.f5538l.clear();
        if (p()) {
            for (int size5 = this.f5540n.size() - 1; size5 >= 0; size5--) {
                ArrayList<j> arrayList = this.f5540n.get(size5);
                for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                    j jVar2 = arrayList.get(size6);
                    View view2 = jVar2.f5580a.f1474b;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    E(jVar2.f5580a);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.f5540n.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.f5539m.size() - 1; size7 >= 0; size7--) {
                ArrayList<RecyclerView.c0> arrayList2 = this.f5539m.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    RecyclerView.c0 c0Var2 = arrayList2.get(size8);
                    c0Var2.f1474b.setAlpha(1.0f);
                    A(c0Var2);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.f5539m.remove(arrayList2);
                    }
                }
            }
            for (int size9 = this.f5541o.size() - 1; size9 >= 0; size9--) {
                ArrayList<i> arrayList3 = this.f5541o.get(size9);
                for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                    X(arrayList3.get(size10));
                    if (arrayList3.isEmpty()) {
                        this.f5541o.remove(arrayList3);
                    }
                }
            }
            U(this.f5544r);
            U(this.f5543q);
            U(this.f5542p);
            U(this.f5545s);
            i();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean p() {
        return (this.f5536j.isEmpty() && this.f5538l.isEmpty() && this.f5537k.isEmpty() && this.f5535i.isEmpty() && this.f5543q.isEmpty() && this.f5544r.isEmpty() && this.f5542p.isEmpty() && this.f5545s.isEmpty() && this.f5540n.isEmpty() && this.f5539m.isEmpty() && this.f5541o.isEmpty()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void u() {
        boolean z3 = !this.f5535i.isEmpty();
        boolean z4 = !this.f5537k.isEmpty();
        boolean z5 = !this.f5538l.isEmpty();
        boolean z6 = !this.f5536j.isEmpty();
        if (z3 || z4 || z6 || z5) {
            Iterator<RecyclerView.c0> it = this.f5535i.iterator();
            while (it.hasNext()) {
                T(it.next());
            }
            this.f5535i.clear();
            if (z4) {
                ArrayList<j> arrayList = new ArrayList<>();
                arrayList.addAll(this.f5537k);
                this.f5540n.add(arrayList);
                this.f5537k.clear();
                a aVar = new a(arrayList);
                if (z3) {
                    t.c0(arrayList.get(0).f5580a.f1474b, aVar, o());
                } else {
                    aVar.run();
                }
            }
            if (z5) {
                ArrayList<i> arrayList2 = new ArrayList<>();
                arrayList2.addAll(this.f5538l);
                this.f5541o.add(arrayList2);
                this.f5538l.clear();
                b bVar = new b(arrayList2);
                if (z3) {
                    t.c0(arrayList2.get(0).f5574a.f1474b, bVar, o());
                } else {
                    bVar.run();
                }
            }
            if (z6) {
                ArrayList<RecyclerView.c0> arrayList3 = new ArrayList<>();
                arrayList3.addAll(this.f5536j);
                this.f5539m.add(arrayList3);
                this.f5536j.clear();
                RunnableC0070c runnableC0070c = new RunnableC0070c(arrayList3);
                if (z3 || z4 || z5) {
                    t.c0(arrayList3.get(0).f1474b, runnableC0070c, (z3 ? o() : 0L) + Math.max(z4 ? n() : 0L, z5 ? m() : 0L));
                } else {
                    runnableC0070c.run();
                }
            }
        }
    }

    @Override // w0.n
    public boolean w(RecyclerView.c0 c0Var) {
        Z(c0Var);
        c0Var.f1474b.setAlpha(0.0f);
        this.f5536j.add(c0Var);
        return true;
    }

    @Override // w0.n
    public boolean x(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, int i3, int i4, int i5, int i6) {
        if (c0Var == c0Var2) {
            return y(c0Var, i3, i4, i5, i6);
        }
        float translationX = c0Var.f1474b.getTranslationX();
        float translationY = c0Var.f1474b.getTranslationY();
        float alpha = c0Var.f1474b.getAlpha();
        Z(c0Var);
        int i7 = (int) ((i5 - i3) - translationX);
        int i8 = (int) ((i6 - i4) - translationY);
        c0Var.f1474b.setTranslationX(translationX);
        c0Var.f1474b.setTranslationY(translationY);
        c0Var.f1474b.setAlpha(alpha);
        if (c0Var2 != null) {
            Z(c0Var2);
            c0Var2.f1474b.setTranslationX(-i7);
            c0Var2.f1474b.setTranslationY(-i8);
            c0Var2.f1474b.setAlpha(0.0f);
        }
        this.f5538l.add(new i(c0Var, c0Var2, i3, i4, i5, i6));
        return true;
    }

    @Override // w0.n
    public boolean y(RecyclerView.c0 c0Var, int i3, int i4, int i5, int i6) {
        View view = c0Var.f1474b;
        int translationX = i3 + ((int) view.getTranslationX());
        int translationY = i4 + ((int) c0Var.f1474b.getTranslationY());
        Z(c0Var);
        int i7 = i5 - translationX;
        int i8 = i6 - translationY;
        if (i7 == 0 && i8 == 0) {
            E(c0Var);
            return false;
        }
        if (i7 != 0) {
            view.setTranslationX(-i7);
        }
        if (i8 != 0) {
            view.setTranslationY(-i8);
        }
        this.f5537k.add(new j(c0Var, translationX, translationY, i5, i6));
        return true;
    }

    @Override // w0.n
    public boolean z(RecyclerView.c0 c0Var) {
        Z(c0Var);
        this.f5535i.add(c0Var);
        return true;
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.c0 f5574a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView.c0 f5575b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5576c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5577d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5578e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f5579f;

        public i(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2) {
            this.f5574a = c0Var;
            this.f5575b = c0Var2;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.f5574a + ", newHolder=" + this.f5575b + ", fromX=" + this.f5576c + ", fromY=" + this.f5577d + ", toX=" + this.f5578e + ", toY=" + this.f5579f + '}';
        }

        public i(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, int i3, int i4, int i5, int i6) {
            this(c0Var, c0Var2);
            this.f5576c = i3;
            this.f5577d = i4;
            this.f5578e = i5;
            this.f5579f = i6;
        }
    }
}
