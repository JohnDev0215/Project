package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class e extends n {
    private static TimeInterpolator s;
    private ArrayList<RecyclerView.A> h = new ArrayList<>();
    private ArrayList<RecyclerView.A> i = new ArrayList<>();
    private ArrayList<j> j = new ArrayList<>();
    private ArrayList<i> k = new ArrayList<>();
    ArrayList<ArrayList<RecyclerView.A>> l = new ArrayList<>();
    ArrayList<ArrayList<j>> m = new ArrayList<>();
    ArrayList<ArrayList<i>> n = new ArrayList<>();
    ArrayList<RecyclerView.A> o = new ArrayList<>();
    ArrayList<RecyclerView.A> p = new ArrayList<>();
    ArrayList<RecyclerView.A> q = new ArrayList<>();
    ArrayList<RecyclerView.A> r = new ArrayList<>();

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ArrayList f1165c;

        a(ArrayList arrayList) {
            this.f1165c = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (j jVar : this.f1165c) {
                e.this.S(jVar.f1186a, jVar.f1187b, jVar.f1188c, jVar.d, jVar.e);
            }
            this.f1165c.clear();
            e.this.m.remove(this.f1165c);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ArrayList f1166c;

        b(ArrayList arrayList) {
            this.f1166c = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f1166c.iterator();
            while (it.hasNext()) {
                e.this.R((i) it.next());
            }
            this.f1166c.clear();
            e.this.n.remove(this.f1166c);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ArrayList f1167c;

        c(ArrayList arrayList) {
            this.f1167c = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f1167c.iterator();
            while (it.hasNext()) {
                e.this.Q((RecyclerView.A) it.next());
            }
            this.f1167c.clear();
            e.this.l.remove(this.f1167c);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.A f1168a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f1169b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1170c;

        d(RecyclerView.A a2, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f1168a = a2;
            this.f1169b = viewPropertyAnimator;
            this.f1170c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1169b.setListener(null);
            this.f1170c.setAlpha(1.0f);
            e.this.G(this.f1168a);
            e.this.q.remove(this.f1168a);
            e.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            e.this.H(this.f1168a);
        }
    }

    /* JADX INFO: renamed from: androidx.recyclerview.widget.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    class C0040e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.A f1171a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1172b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f1173c;

        C0040e(RecyclerView.A a2, View view, ViewPropertyAnimator viewPropertyAnimator) {
            this.f1171a = a2;
            this.f1172b = view;
            this.f1173c = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1172b.setAlpha(1.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1173c.setListener(null);
            e.this.A(this.f1171a);
            e.this.o.remove(this.f1171a);
            e.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            e.this.B(this.f1171a);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class f extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.A f1174a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1175b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1176c;
        final /* synthetic */ int d;
        final /* synthetic */ ViewPropertyAnimator e;

        f(RecyclerView.A a2, int i, View view, int i2, ViewPropertyAnimator viewPropertyAnimator) {
            this.f1174a = a2;
            this.f1175b = i;
            this.f1176c = view;
            this.d = i2;
            this.e = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (this.f1175b != 0) {
                this.f1176c.setTranslationX(0.0f);
            }
            if (this.d != 0) {
                this.f1176c.setTranslationY(0.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.e.setListener(null);
            e.this.E(this.f1174a);
            e.this.p.remove(this.f1174a);
            e.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            e.this.F(this.f1174a);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class g extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ i f1177a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f1178b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1179c;

        g(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f1177a = iVar;
            this.f1178b = viewPropertyAnimator;
            this.f1179c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1178b.setListener(null);
            this.f1179c.setAlpha(1.0f);
            this.f1179c.setTranslationX(0.0f);
            this.f1179c.setTranslationY(0.0f);
            e.this.C(this.f1177a.f1183a, true);
            e.this.r.remove(this.f1177a.f1183a);
            e.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            e.this.D(this.f1177a.f1183a, true);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class h extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ i f1180a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f1181b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1182c;

        h(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f1180a = iVar;
            this.f1181b = viewPropertyAnimator;
            this.f1182c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1181b.setListener(null);
            this.f1182c.setAlpha(1.0f);
            this.f1182c.setTranslationX(0.0f);
            this.f1182c.setTranslationY(0.0f);
            e.this.C(this.f1180a.f1184b, false);
            e.this.r.remove(this.f1180a.f1184b);
            e.this.V();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            e.this.D(this.f1180a.f1184b, false);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.A f1186a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1187b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1188c;
        public int d;
        public int e;

        j(RecyclerView.A a2, int i, int i2, int i3, int i4) {
            this.f1186a = a2;
            this.f1187b = i;
            this.f1188c = i2;
            this.d = i3;
            this.e = i4;
        }
    }

    private void T(RecyclerView.A a2) {
        View view = a2.f1092a;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.q.add(a2);
        viewPropertyAnimatorAnimate.setDuration(o()).alpha(0.0f).setListener(new d(a2, viewPropertyAnimatorAnimate, view)).start();
    }

    private void W(List<i> list, RecyclerView.A a2) {
        for (int size = list.size() - 1; size >= 0; size--) {
            i iVar = list.get(size);
            if (Y(iVar, a2) && iVar.f1183a == null && iVar.f1184b == null) {
                list.remove(iVar);
            }
        }
    }

    private void X(i iVar) {
        RecyclerView.A a2 = iVar.f1183a;
        if (a2 != null) {
            Y(iVar, a2);
        }
        RecyclerView.A a3 = iVar.f1184b;
        if (a3 != null) {
            Y(iVar, a3);
        }
    }

    private boolean Y(i iVar, RecyclerView.A a2) {
        boolean z = false;
        if (iVar.f1184b == a2) {
            iVar.f1184b = null;
        } else {
            if (iVar.f1183a != a2) {
                return false;
            }
            iVar.f1183a = null;
            z = true;
        }
        a2.f1092a.setAlpha(1.0f);
        a2.f1092a.setTranslationX(0.0f);
        a2.f1092a.setTranslationY(0.0f);
        C(a2, z);
        return true;
    }

    private void Z(RecyclerView.A a2) {
        if (s == null) {
            s = new ValueAnimator().getInterpolator();
        }
        a2.f1092a.animate().setInterpolator(s);
        j(a2);
    }

    void Q(RecyclerView.A a2) {
        View view = a2.f1092a;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.o.add(a2);
        viewPropertyAnimatorAnimate.alpha(1.0f).setDuration(l()).setListener(new C0040e(a2, view, viewPropertyAnimatorAnimate)).start();
    }

    void R(i iVar) {
        RecyclerView.A a2 = iVar.f1183a;
        View view = a2 == null ? null : a2.f1092a;
        RecyclerView.A a3 = iVar.f1184b;
        View view2 = a3 != null ? a3.f1092a : null;
        if (view != null) {
            ViewPropertyAnimator duration = view.animate().setDuration(m());
            this.r.add(iVar.f1183a);
            duration.translationX(iVar.e - iVar.f1185c);
            duration.translationY(iVar.f - iVar.d);
            duration.alpha(0.0f).setListener(new g(iVar, duration, view)).start();
        }
        if (view2 != null) {
            ViewPropertyAnimator viewPropertyAnimatorAnimate = view2.animate();
            this.r.add(iVar.f1184b);
            viewPropertyAnimatorAnimate.translationX(0.0f).translationY(0.0f).setDuration(m()).alpha(1.0f).setListener(new h(iVar, viewPropertyAnimatorAnimate, view2)).start();
        }
    }

    void S(RecyclerView.A a2, int i2, int i3, int i4, int i5) {
        View view = a2.f1092a;
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        if (i6 != 0) {
            view.animate().translationX(0.0f);
        }
        if (i7 != 0) {
            view.animate().translationY(0.0f);
        }
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.p.add(a2);
        viewPropertyAnimatorAnimate.setDuration(n()).setListener(new f(a2, i6, view, i7, viewPropertyAnimatorAnimate)).start();
    }

    void U(List<RecyclerView.A> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            list.get(size).f1092a.animate().cancel();
        }
    }

    void V() {
        if (p()) {
            return;
        }
        i();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean g(@NonNull RecyclerView.A a2, @NonNull List<Object> list) {
        return !list.isEmpty() || super.g(a2, list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void j(RecyclerView.A a2) {
        View view = a2.f1092a;
        view.animate().cancel();
        int size = this.j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (this.j.get(size).f1186a == a2) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                E(a2);
                this.j.remove(size);
            }
        }
        W(this.k, a2);
        if (this.h.remove(a2)) {
            view.setAlpha(1.0f);
            G(a2);
        }
        if (this.i.remove(a2)) {
            view.setAlpha(1.0f);
            A(a2);
        }
        for (int size2 = this.n.size() - 1; size2 >= 0; size2--) {
            ArrayList<i> arrayList = this.n.get(size2);
            W(arrayList, a2);
            if (arrayList.isEmpty()) {
                this.n.remove(size2);
            }
        }
        for (int size3 = this.m.size() - 1; size3 >= 0; size3--) {
            ArrayList<j> arrayList2 = this.m.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (arrayList2.get(size4).f1186a == a2) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    E(a2);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.m.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.l.size() - 1; size5 >= 0; size5--) {
            ArrayList<RecyclerView.A> arrayList3 = this.l.get(size5);
            if (arrayList3.remove(a2)) {
                view.setAlpha(1.0f);
                A(a2);
                if (arrayList3.isEmpty()) {
                    this.l.remove(size5);
                }
            }
        }
        this.q.remove(a2);
        this.o.remove(a2);
        this.r.remove(a2);
        this.p.remove(a2);
        V();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void k() {
        int size = this.j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            j jVar = this.j.get(size);
            View view = jVar.f1186a.f1092a;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            E(jVar.f1186a);
            this.j.remove(size);
        }
        for (int size2 = this.h.size() - 1; size2 >= 0; size2--) {
            G(this.h.get(size2));
            this.h.remove(size2);
        }
        int size3 = this.i.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.A a2 = this.i.get(size3);
            a2.f1092a.setAlpha(1.0f);
            A(a2);
            this.i.remove(size3);
        }
        for (int size4 = this.k.size() - 1; size4 >= 0; size4--) {
            X(this.k.get(size4));
        }
        this.k.clear();
        if (p()) {
            for (int size5 = this.m.size() - 1; size5 >= 0; size5--) {
                ArrayList<j> arrayList = this.m.get(size5);
                for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                    j jVar2 = arrayList.get(size6);
                    View view2 = jVar2.f1186a.f1092a;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    E(jVar2.f1186a);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.m.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.l.size() - 1; size7 >= 0; size7--) {
                ArrayList<RecyclerView.A> arrayList2 = this.l.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    RecyclerView.A a3 = arrayList2.get(size8);
                    a3.f1092a.setAlpha(1.0f);
                    A(a3);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.l.remove(arrayList2);
                    }
                }
            }
            for (int size9 = this.n.size() - 1; size9 >= 0; size9--) {
                ArrayList<i> arrayList3 = this.n.get(size9);
                for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                    X(arrayList3.get(size10));
                    if (arrayList3.isEmpty()) {
                        this.n.remove(arrayList3);
                    }
                }
            }
            U(this.q);
            U(this.p);
            U(this.o);
            U(this.r);
            i();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean p() {
        return (this.i.isEmpty() && this.k.isEmpty() && this.j.isEmpty() && this.h.isEmpty() && this.p.isEmpty() && this.q.isEmpty() && this.o.isEmpty() && this.r.isEmpty() && this.m.isEmpty() && this.l.isEmpty() && this.n.isEmpty()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void u() {
        boolean z = !this.h.isEmpty();
        boolean z2 = !this.j.isEmpty();
        boolean z3 = !this.k.isEmpty();
        boolean z4 = !this.i.isEmpty();
        if (z || z2 || z4 || z3) {
            Iterator<RecyclerView.A> it = this.h.iterator();
            while (it.hasNext()) {
                T(it.next());
            }
            this.h.clear();
            if (z2) {
                ArrayList<j> arrayList = new ArrayList<>();
                arrayList.addAll(this.j);
                this.m.add(arrayList);
                this.j.clear();
                a aVar = new a(arrayList);
                if (z) {
                    ViewCompat.I(arrayList.get(0).f1186a.f1092a, aVar, o());
                } else {
                    aVar.run();
                }
            }
            if (z3) {
                ArrayList<i> arrayList2 = new ArrayList<>();
                arrayList2.addAll(this.k);
                this.n.add(arrayList2);
                this.k.clear();
                b bVar = new b(arrayList2);
                if (z) {
                    ViewCompat.I(arrayList2.get(0).f1183a.f1092a, bVar, o());
                } else {
                    bVar.run();
                }
            }
            if (z4) {
                ArrayList<RecyclerView.A> arrayList3 = new ArrayList<>();
                arrayList3.addAll(this.i);
                this.l.add(arrayList3);
                this.i.clear();
                c cVar = new c(arrayList3);
                if (z || z2 || z3) {
                    ViewCompat.I(arrayList3.get(0).f1092a, cVar, (z ? o() : 0L) + Math.max(z2 ? n() : 0L, z3 ? m() : 0L));
                } else {
                    cVar.run();
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.n
    public boolean w(RecyclerView.A a2) {
        Z(a2);
        a2.f1092a.setAlpha(0.0f);
        this.i.add(a2);
        return true;
    }

    @Override // androidx.recyclerview.widget.n
    public boolean x(RecyclerView.A a2, RecyclerView.A a3, int i2, int i3, int i4, int i5) {
        if (a2 == a3) {
            return y(a2, i2, i3, i4, i5);
        }
        float translationX = a2.f1092a.getTranslationX();
        float translationY = a2.f1092a.getTranslationY();
        float alpha = a2.f1092a.getAlpha();
        Z(a2);
        int i6 = (int) ((i4 - i2) - translationX);
        int i7 = (int) ((i5 - i3) - translationY);
        a2.f1092a.setTranslationX(translationX);
        a2.f1092a.setTranslationY(translationY);
        a2.f1092a.setAlpha(alpha);
        if (a3 != null) {
            Z(a3);
            a3.f1092a.setTranslationX(-i6);
            a3.f1092a.setTranslationY(-i7);
            a3.f1092a.setAlpha(0.0f);
        }
        this.k.add(new i(a2, a3, i2, i3, i4, i5));
        return true;
    }

    @Override // androidx.recyclerview.widget.n
    public boolean y(RecyclerView.A a2, int i2, int i3, int i4, int i5) {
        View view = a2.f1092a;
        int translationX = i2 + ((int) view.getTranslationX());
        int translationY = i3 + ((int) a2.f1092a.getTranslationY());
        Z(a2);
        int i6 = i4 - translationX;
        int i7 = i5 - translationY;
        if (i6 == 0 && i7 == 0) {
            E(a2);
            return false;
        }
        if (i6 != 0) {
            view.setTranslationX(-i6);
        }
        if (i7 != 0) {
            view.setTranslationY(-i7);
        }
        this.j.add(new j(a2, translationX, translationY, i4, i5));
        return true;
    }

    @Override // androidx.recyclerview.widget.n
    public boolean z(RecyclerView.A a2) {
        Z(a2);
        this.h.add(a2);
        return true;
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.A f1183a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView.A f1184b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1185c;
        public int d;
        public int e;
        public int f;

        private i(RecyclerView.A a2, RecyclerView.A a3) {
            this.f1183a = a2;
            this.f1184b = a3;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.f1183a + ", newHolder=" + this.f1184b + ", fromX=" + this.f1185c + ", fromY=" + this.d + ", toX=" + this.e + ", toY=" + this.f + '}';
        }

        i(RecyclerView.A a2, RecyclerView.A a3, int i, int i2, int i3, int i4) {
            this(a2, a3);
            this.f1185c = i;
            this.d = i2;
            this.e = i3;
            this.f = i4;
        }
    }
}
