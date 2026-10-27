package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import d.b.a.a.m.h;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: BaseMotionStrategy.java */
/* JADX INFO: loaded from: classes.dex */
abstract class b implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f3628a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ExtendedFloatingActionButton f3629b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ArrayList<Animator.AnimatorListener> f3630c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a f3631d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private h f3632e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private h f3633f;

    b(ExtendedFloatingActionButton extendedFloatingActionButton, a aVar) {
        this.f3629b = extendedFloatingActionButton;
        this.f3628a = extendedFloatingActionButton.getContext();
        this.f3631d = aVar;
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public h a() {
        return this.f3633f;
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public AnimatorSet b() {
        return k(l());
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public void c() {
        this.f3631d.b();
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public void d() {
        this.f3631d.b();
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public final List<Animator.AnimatorListener> e() {
        return this.f3630c;
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public final void h(h hVar) {
        this.f3633f = hVar;
    }

    AnimatorSet k(h hVar) {
        ArrayList arrayList = new ArrayList();
        if (hVar.j("opacity")) {
            arrayList.add(hVar.f("opacity", this.f3629b, View.ALPHA));
        }
        if (hVar.j("scale")) {
            arrayList.add(hVar.f("scale", this.f3629b, View.SCALE_Y));
            arrayList.add(hVar.f("scale", this.f3629b, View.SCALE_X));
        }
        if (hVar.j("width")) {
            arrayList.add(hVar.f("width", this.f3629b, ExtendedFloatingActionButton.t));
        }
        if (hVar.j("height")) {
            arrayList.add(hVar.f("height", this.f3629b, ExtendedFloatingActionButton.u));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        d.b.a.a.m.b.a(animatorSet, arrayList);
        return animatorSet;
    }

    public final h l() {
        h hVar = this.f3633f;
        if (hVar != null) {
            return hVar;
        }
        if (this.f3632e == null) {
            this.f3632e = h.d(this.f3628a, i());
        }
        return (h) b.g.j.h.c(this.f3632e);
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public void onAnimationStart(Animator animator) {
        this.f3631d.c(animator);
    }
}
