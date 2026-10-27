package c.b.a.a.p;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import c.b.a.a.a.h;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: BaseMotionStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1745a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ExtendedFloatingActionButton f1746b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<Animator.AnimatorListener> f1747c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f1748d;
    public h e;
    public h f;

    public b(ExtendedFloatingActionButton extendedFloatingActionButton, a aVar) {
        this.f1746b = extendedFloatingActionButton;
        this.f1745a = extendedFloatingActionButton.getContext();
        this.f1748d = aVar;
    }

    @Override // c.b.a.a.p.f
    public h a() {
        return this.f;
    }

    @Override // c.b.a.a.p.f
    public AnimatorSet b() {
        return k(l());
    }

    @Override // c.b.a.a.p.f
    public void c() {
        this.f1748d.b();
    }

    @Override // c.b.a.a.p.f
    public void d() {
        this.f1748d.b();
    }

    @Override // c.b.a.a.p.f
    public final List<Animator.AnimatorListener> e() {
        return this.f1747c;
    }

    @Override // c.b.a.a.p.f
    public final void h(h hVar) {
        this.f = hVar;
    }

    public AnimatorSet k(h hVar) {
        ArrayList arrayList = new ArrayList();
        if (hVar.j("opacity")) {
            arrayList.add(hVar.f("opacity", this.f1746b, View.ALPHA));
        }
        if (hVar.j("scale")) {
            arrayList.add(hVar.f("scale", this.f1746b, View.SCALE_Y));
            arrayList.add(hVar.f("scale", this.f1746b, View.SCALE_X));
        }
        if (hVar.j("width")) {
            arrayList.add(hVar.f("width", this.f1746b, ExtendedFloatingActionButton.t));
        }
        if (hVar.j("height")) {
            arrayList.add(hVar.f("height", this.f1746b, ExtendedFloatingActionButton.u));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        c.b.a.a.a.b.a(animatorSet, arrayList);
        return animatorSet;
    }

    public final h l() {
        h hVar = this.f;
        if (hVar != null) {
            return hVar;
        }
        if (this.e == null) {
            this.e = h.d(this.f1745a, i());
        }
        return (h) b.g.i.h.c(this.e);
    }

    @Override // c.b.a.a.p.f
    public void onAnimationStart(Animator animator) {
        this.f1748d.c(animator);
    }
}
