package androidx.recyclerview.widget;

import a.g.k.t;
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

/* JADX INFO: loaded from: classes.dex */
public class c extends n {
    private static TimeInterpolator h;
    private ArrayList<RecyclerView.d0> i = new ArrayList<>();
    private ArrayList<RecyclerView.d0> j = new ArrayList<>();
    private ArrayList<j> k = new ArrayList<>();
    private ArrayList<i> l = new ArrayList<>();
    ArrayList<ArrayList<RecyclerView.d0>> m = new ArrayList<>();
    ArrayList<ArrayList<j>> n = new ArrayList<>();
    ArrayList<ArrayList<i>> o = new ArrayList<>();
    ArrayList<RecyclerView.d0> p = new ArrayList<>();
    ArrayList<RecyclerView.d0> q = new ArrayList<>();
    ArrayList<RecyclerView.d0> r = new ArrayList<>();
    ArrayList<RecyclerView.d0> s = new ArrayList<>();

    class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f1533b;

        a(ArrayList arrayList) {
            this.f1533b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (j jVar : this.f1533b) {
                c.this.S(jVar.f1563a, jVar.f1564b, jVar.f1565c, jVar.f1566d, jVar.e);
            }
            this.f1533b.clear();
            c.this.n.remove(this.f1533b);
        }
    }

    class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f1535b;

        b(ArrayList arrayList) {
            this.f1535b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f1535b.iterator();
            while (it.hasNext()) {
                c.this.R((i) it.next());
            }
            this.f1535b.clear();
            c.this.o.remove(this.f1535b);
        }
    }

    /* JADX INFO: renamed from: androidx.recyclerview.widget.c$c, reason: collision with other inner class name */
    class RunnableC0047c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f1537b;

        RunnableC0047c(ArrayList arrayList) {
            this.f1537b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f1537b.iterator();
            while (it.hasNext()) {
                c.this.Q((RecyclerView.d0) it.next());
            }
            this.f1537b.clear();
            c.this.m.remove(this.f1537b);
        }
    }

    class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.d0 f1539a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f1540b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1541c;

        d(RecyclerView.d0 d0Var, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f1539a = d0Var;
            this.f1540b = viewPropertyAnimator;
            this.f1541c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1540b.setListener(null);
            this.f1541c.setAlpha(1.0f);
            c.this.G(this.f1539a);
            c.this.r.remove(this.f1539a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.H(this.f1539a);
        }
    }

    class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.d0 f1543a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1544b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f1545c;

        e(RecyclerView.d0 d0Var, View view, ViewPropertyAnimator viewPropertyAnimator) {
            this.f1543a = d0Var;
            this.f1544b = view;
            this.f1545c = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1544b.setAlpha(1.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1545c.setListener(null);
            c.this.A(this.f1543a);
            c.this.p.remove(this.f1543a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.B(this.f1543a);
        }
    }

    class f extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.d0 f1547a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1548b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1549c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f1550d;
        final /* synthetic */ ViewPropertyAnimator e;

        f(RecyclerView.d0 d0Var, int i, View view, int i2, ViewPropertyAnimator viewPropertyAnimator) {
            this.f1547a = d0Var;
            this.f1548b = i;
            this.f1549c = view;
            this.f1550d = i2;
            this.e = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (this.f1548b != 0) {
                this.f1549c.setTranslationX(0.0f);
            }
            if (this.f1550d != 0) {
                this.f1549c.setTranslationY(0.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.e.setListener(null);
            c.this.E(this.f1547a);
            c.this.q.remove(this.f1547a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.F(this.f1547a);
        }
    }

    class g extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ i f1551a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f1552b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1553c;

        g(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f1551a = iVar;
            this.f1552b = viewPropertyAnimator;
            this.f1553c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1552b.setListener(null);
            this.f1553c.setAlpha(1.0f);
            this.f1553c.setTranslationX(0.0f);
            this.f1553c.setTranslationY(0.0f);
            c.this.C(this.f1551a.f1559a, true);
            c.this.s.remove(this.f1551a.f1559a);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.D(this.f1551a.f1559a, true);
        }
    }

    class h extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ i f1555a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f1556b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1557c;

        h(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f1555a = iVar;
            this.f1556b = viewPropertyAnimator;
            this.f1557c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1556b.setListener(null);
            this.f1557c.setAlpha(1.0f);
            this.f1557c.setTranslationX(0.0f);
            this.f1557c.setTranslationY(0.0f);
            c.this.C(this.f1555a.f1560b, false);
            c.this.s.remove(this.f1555a.f1560b);
            c.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            c.this.D(this.f1555a.f1560b, false);
        }
    }

    private static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.d0 f1559a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView.d0 f1560b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1561c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1562d;
        public int e;
        public int f;

        private i(RecyclerView.d0 d0Var, RecyclerView.d0 d0Var2) {
            this.f1559a = d0Var;
            this.f1560b = d0Var2;
        }

        i(RecyclerView.d0 d0Var, RecyclerView.d0 d0Var2, int i, int i2, int i3, int i4) {
            this(d0Var, d0Var2);
            this.f1561c = i;
            this.f1562d = i2;
            this.e = i3;
            this.f = i4;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.f1559a + ", newHolder=" + this.f1560b + ", fromX=" + this.f1561c + ", fromY=" + this.f1562d + ", toX=" + this.e + ", toY=" + this.f + '}';
        }
    }

    private static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.d0 f1563a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1564b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1565c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1566d;
        public int e;

        j(RecyclerView.d0 d0Var, int i, int i2, int i3, int i4) {
            this.f1563a = d0Var;
            this.f1564b = i;
            this.f1565c = i2;
            this.f1566d = i3;
            this.e = i4;
        }
    }

    private void T(RecyclerView.d0 d0Var) {
        View view = d0Var.f1454b;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.r.add(d0Var);
        viewPropertyAnimatorAnimate.setDuration(o()).alpha(0.0f).setListener(new d(d0Var, viewPropertyAnimatorAnimate, view)).start();
    }

    private void W(List<i> list, RecyclerView.d0 d0Var) {
        for (int size = list.size() - 1; size >= 0; size--) {
            i iVar = list.get(size);
            if (Y(iVar, d0Var) && iVar.f1559a == null && iVar.f1560b == null) {
                list.remove(iVar);
            }
        }
    }

    private void X(i iVar) {
        RecyclerView.d0 d0Var = iVar.f1559a;
        if (d0Var != null) {
            Y(iVar, d0Var);
        }
        RecyclerView.d0 d0Var2 = iVar.f1560b;
        if (d0Var2 != null) {
            Y(iVar, d0Var2);
        }
    }

    private boolean Y(i iVar, RecyclerView.d0 d0Var) {
        boolean z = false;
        if (iVar.f1560b == d0Var) {
            iVar.f1560b = null;
        } else {
            if (iVar.f1559a != d0Var) {
                return false;
            }
            iVar.f1559a = null;
            z = true;
        }
        d0Var.f1454b.setAlpha(1.0f);
        d0Var.f1454b.setTranslationX(0.0f);
        d0Var.f1454b.setTranslationY(0.0f);
        C(d0Var, z);
        return true;
    }

    private void Z(RecyclerView.d0 d0Var) {
        if (h == null) {
            h = new ValueAnimator().getInterpolator();
        }
        d0Var.f1454b.animate().setInterpolator(h);
        j(d0Var);
    }

    void Q(RecyclerView.d0 d0Var) {
        View view = d0Var.f1454b;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.p.add(d0Var);
        viewPropertyAnimatorAnimate.alpha(1.0f).setDuration(l()).setListener(new e(d0Var, view, viewPropertyAnimatorAnimate)).start();
    }

    void R(i iVar) {
        RecyclerView.d0 d0Var = iVar.f1559a;
        View view = d0Var == null ? null : d0Var.f1454b;
        RecyclerView.d0 d0Var2 = iVar.f1560b;
        View view2 = d0Var2 != null ? d0Var2.f1454b : null;
        if (view != null) {
            ViewPropertyAnimator duration = view.animate().setDuration(m());
            this.s.add(iVar.f1559a);
            duration.translationX(iVar.e - iVar.f1561c);
            duration.translationY(iVar.f - iVar.f1562d);
            duration.alpha(0.0f).setListener(new g(iVar, duration, view)).start();
        }
        if (view2 != null) {
            ViewPropertyAnimator viewPropertyAnimatorAnimate = view2.animate();
            this.s.add(iVar.f1560b);
            viewPropertyAnimatorAnimate.translationX(0.0f).translationY(0.0f).setDuration(m()).alpha(1.0f).setListener(new h(iVar, viewPropertyAnimatorAnimate, view2)).start();
        }
    }

    void S(RecyclerView.d0 d0Var, int i2, int i3, int i4, int i5) {
        View view = d0Var.f1454b;
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        if (i6 != 0) {
            view.animate().translationX(0.0f);
        }
        if (i7 != 0) {
            view.animate().translationY(0.0f);
        }
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.q.add(d0Var);
        viewPropertyAnimatorAnimate.setDuration(n()).setListener(new f(d0Var, i6, view, i7, viewPropertyAnimatorAnimate)).start();
    }

    void U(List<RecyclerView.d0> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            list.get(size).f1454b.animate().cancel();
        }
    }

    void V() {
        if (p()) {
            return;
        }
        i();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean g(RecyclerView.d0 d0Var, List<Object> list) {
        return !list.isEmpty() || super.g(d0Var, list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void j(RecyclerView.d0 d0Var) {
        View view = d0Var.f1454b;
        view.animate().cancel();
        int size = this.k.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (this.k.get(size).f1563a == d0Var) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                E(d0Var);
                this.k.remove(size);
            }
        }
        W(this.l, d0Var);
        if (this.i.remove(d0Var)) {
            view.setAlpha(1.0f);
            G(d0Var);
        }
        if (this.j.remove(d0Var)) {
            view.setAlpha(1.0f);
            A(d0Var);
        }
        for (int size2 = this.o.size() - 1; size2 >= 0; size2--) {
            ArrayList<i> arrayList = this.o.get(size2);
            W(arrayList, d0Var);
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
                if (arrayList2.get(size4).f1563a == d0Var) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    E(d0Var);
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
            ArrayList<RecyclerView.d0> arrayList3 = this.m.get(size5);
            if (arrayList3.remove(d0Var)) {
                view.setAlpha(1.0f);
                A(d0Var);
                if (arrayList3.isEmpty()) {
                    this.m.remove(size5);
                }
            }
        }
        this.r.remove(d0Var);
        this.p.remove(d0Var);
        this.s.remove(d0Var);
        this.q.remove(d0Var);
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
            View view = jVar.f1563a.f1454b;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            E(jVar.f1563a);
            this.k.remove(size);
        }
        for (int size2 = this.i.size() - 1; size2 >= 0; size2--) {
            G(this.i.get(size2));
            this.i.remove(size2);
        }
        int size3 = this.j.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.d0 d0Var = this.j.get(size3);
            d0Var.f1454b.setAlpha(1.0f);
            A(d0Var);
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
                    View view2 = jVar2.f1563a.f1454b;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    E(jVar2.f1563a);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.n.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.m.size() - 1; size7 >= 0; size7--) {
                ArrayList<RecyclerView.d0> arrayList2 = this.m.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    RecyclerView.d0 d0Var2 = arrayList2.get(size8);
                    d0Var2.f1454b.setAlpha(1.0f);
                    A(d0Var2);
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
        return (this.j.isEmpty() && this.l.isEmpty() && this.k.isEmpty() && this.i.isEmpty() && this.q.isEmpty() && this.r.isEmpty() && this.p.isEmpty() && this.s.isEmpty() && this.n.isEmpty() && this.m.isEmpty() && this.o.isEmpty()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void u() {
        boolean z = !this.i.isEmpty();
        boolean z2 = !this.k.isEmpty();
        boolean z3 = !this.l.isEmpty();
        boolean z4 = !this.j.isEmpty();
        if (z || z2 || z4 || z3) {
            Iterator<RecyclerView.d0> it = this.i.iterator();
            while (it.hasNext()) {
                T(it.next());
            }
            this.i.clear();
            if (z2) {
                ArrayList<j> arrayList = new ArrayList<>();
                arrayList.addAll(this.k);
                this.n.add(arrayList);
                this.k.clear();
                a aVar = new a(arrayList);
                if (z) {
                    t.a0(arrayList.get(0).f1563a.f1454b, aVar, o());
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
                    t.a0(arrayList2.get(0).f1559a.f1454b, bVar, o());
                } else {
                    bVar.run();
                }
            }
            if (z4) {
                ArrayList<RecyclerView.d0> arrayList3 = new ArrayList<>();
                arrayList3.addAll(this.j);
                this.m.add(arrayList3);
                this.j.clear();
                RunnableC0047c runnableC0047c = new RunnableC0047c(arrayList3);
                if (z || z2 || z3) {
                    t.a0(arrayList3.get(0).f1454b, runnableC0047c, (z ? o() : 0L) + Math.max(z2 ? n() : 0L, z3 ? m() : 0L));
                } else {
                    runnableC0047c.run();
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.n
    public boolean w(RecyclerView.d0 d0Var) {
        Z(d0Var);
        d0Var.f1454b.setAlpha(0.0f);
        this.j.add(d0Var);
        return true;
    }

    @Override // androidx.recyclerview.widget.n
    public boolean x(RecyclerView.d0 d0Var, RecyclerView.d0 d0Var2, int i2, int i3, int i4, int i5) {
        if (d0Var == d0Var2) {
            return y(d0Var, i2, i3, i4, i5);
        }
        float translationX = d0Var.f1454b.getTranslationX();
        float translationY = d0Var.f1454b.getTranslationY();
        float alpha = d0Var.f1454b.getAlpha();
        Z(d0Var);
        int i6 = (int) ((i4 - i2) - translationX);
        int i7 = (int) ((i5 - i3) - translationY);
        d0Var.f1454b.setTranslationX(translationX);
        d0Var.f1454b.setTranslationY(translationY);
        d0Var.f1454b.setAlpha(alpha);
        if (d0Var2 != null) {
            Z(d0Var2);
            d0Var2.f1454b.setTranslationX(-i6);
            d0Var2.f1454b.setTranslationY(-i7);
            d0Var2.f1454b.setAlpha(0.0f);
        }
        this.l.add(new i(d0Var, d0Var2, i2, i3, i4, i5));
        return true;
    }

    @Override // androidx.recyclerview.widget.n
    public boolean y(RecyclerView.d0 d0Var, int i2, int i3, int i4, int i5) {
        View view = d0Var.f1454b;
        int translationX = i2 + ((int) view.getTranslationX());
        int translationY = i3 + ((int) d0Var.f1454b.getTranslationY());
        Z(d0Var);
        int i6 = i4 - translationX;
        int i7 = i5 - translationY;
        if (i6 == 0 && i7 == 0) {
            E(d0Var);
            return false;
        }
        if (i6 != 0) {
            view.setTranslationX(-i6);
        }
        if (i7 != 0) {
            view.setTranslationY(-i7);
        }
        this.k.add(new j(d0Var, translationX, translationY, i4, i5));
        return true;
    }

    @Override // androidx.recyclerview.widget.n
    public boolean z(RecyclerView.d0 d0Var) {
        Z(d0Var);
        this.i.add(d0Var);
        return true;
    }
}
