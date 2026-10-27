package c.b.a.a.c0;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import b.h.l.g;
import c.b.a.a.m.h;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: BaseMotionStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f3701a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ExtendedFloatingActionButton f3702b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<Animator.AnimatorListener> f3703c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f3704d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public h f3705e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public h f3706f;

    public b(ExtendedFloatingActionButton extendedFloatingActionButton, a aVar) {
        this.f3702b = extendedFloatingActionButton;
        this.f3701a = extendedFloatingActionButton.getContext();
        this.f3704d = aVar;
    }

    @Override // c.b.a.a.c0.f
    public h a() {
        return this.f3706f;
    }

    @Override // c.b.a.a.c0.f
    public AnimatorSet b() {
        return k(l());
    }

    @Override // c.b.a.a.c0.f
    public void c() {
        this.f3704d.b();
    }

    @Override // c.b.a.a.c0.f
    public void d() {
        this.f3704d.b();
    }

    @Override // c.b.a.a.c0.f
    public final List<Animator.AnimatorListener> e() {
        return this.f3703c;
    }

    @Override // c.b.a.a.c0.f
    public final void h(h hVar) {
        this.f3706f = hVar;
    }

    public AnimatorSet k(h hVar) {
        ArrayList arrayList = new ArrayList();
        if (hVar.j("opacity")) {
            arrayList.add(hVar.f("opacity", this.f3702b, View.ALPHA));
        }
        if (hVar.j("scale")) {
            arrayList.add(hVar.f("scale", this.f3702b, View.SCALE_Y));
            arrayList.add(hVar.f("scale", this.f3702b, View.SCALE_X));
        }
        if (hVar.j("width")) {
            arrayList.add(hVar.f("width", this.f3702b, ExtendedFloatingActionButton.t));
        }
        if (hVar.j("height")) {
            arrayList.add(hVar.f("height", this.f3702b, ExtendedFloatingActionButton.u));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        c.b.a.a.m.b.a(animatorSet, arrayList);
        return animatorSet;
    }

    public final h l() {
        h hVar = this.f3706f;
        if (hVar != null) {
            return hVar;
        }
        if (this.f3705e == null) {
            this.f3705e = h.d(this.f3701a, i());
        }
        return (h) g.c(this.f3705e);
    }

    @Override // c.b.a.a.c0.f
    public void onAnimationStart(Animator animator) {
        this.f3704d.c(animator);
    }
}
