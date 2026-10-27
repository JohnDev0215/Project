package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import c.a.a.a.m.h;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: BaseMotionStrategy.java */
/* JADX INFO: loaded from: classes.dex */
abstract class b implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f2371a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ExtendedFloatingActionButton f2372b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ArrayList<Animator.AnimatorListener> f2373c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a f2374d;
    private h e;
    private h f;

    b(ExtendedFloatingActionButton extendedFloatingActionButton, a aVar) {
        this.f2372b = extendedFloatingActionButton;
        this.f2371a = extendedFloatingActionButton.getContext();
        this.f2374d = aVar;
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public h a() {
        return this.f;
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public AnimatorSet b() {
        return k(l());
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public void c() {
        this.f2374d.b();
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public void d() {
        this.f2374d.b();
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public final List<Animator.AnimatorListener> e() {
        return this.f2373c;
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public final void h(h hVar) {
        this.f = hVar;
    }

    AnimatorSet k(h hVar) {
        ArrayList arrayList = new ArrayList();
        if (hVar.j("opacity")) {
            arrayList.add(hVar.f("opacity", this.f2372b, View.ALPHA));
        }
        if (hVar.j("scale")) {
            arrayList.add(hVar.f("scale", this.f2372b, View.SCALE_Y));
            arrayList.add(hVar.f("scale", this.f2372b, View.SCALE_X));
        }
        if (hVar.j("width")) {
            arrayList.add(hVar.f("width", this.f2372b, ExtendedFloatingActionButton.t));
        }
        if (hVar.j("height")) {
            arrayList.add(hVar.f("height", this.f2372b, ExtendedFloatingActionButton.u));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        c.a.a.a.m.b.a(animatorSet, arrayList);
        return animatorSet;
    }

    public final h l() {
        h hVar = this.f;
        if (hVar != null) {
            return hVar;
        }
        if (this.e == null) {
            this.e = h.d(this.f2371a, i());
        }
        return (h) b.g.j.h.c(this.e);
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public void onAnimationStart(Animator animator) {
        this.f2374d.c(animator);
    }
}
