package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import c.b.a.a.m.h;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: BaseMotionStrategy.java */
/* JADX INFO: loaded from: classes.dex */
abstract class b implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f3846a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ExtendedFloatingActionButton f3847b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ArrayList<Animator.AnimatorListener> f3848c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a f3849d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private h f3850e;
    private h f;

    b(ExtendedFloatingActionButton extendedFloatingActionButton, a aVar) {
        this.f3847b = extendedFloatingActionButton;
        this.f3846a = extendedFloatingActionButton.getContext();
        this.f3849d = aVar;
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
        this.f3849d.b();
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public void d() {
        this.f3849d.b();
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public final List<Animator.AnimatorListener> e() {
        return this.f3848c;
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public final void h(h hVar) {
        this.f = hVar;
    }

    AnimatorSet k(h hVar) {
        ArrayList arrayList = new ArrayList();
        if (hVar.j("opacity")) {
            arrayList.add(hVar.f("opacity", this.f3847b, View.ALPHA));
        }
        if (hVar.j("scale")) {
            arrayList.add(hVar.f("scale", this.f3847b, View.SCALE_Y));
            arrayList.add(hVar.f("scale", this.f3847b, View.SCALE_X));
        }
        if (hVar.j("width")) {
            arrayList.add(hVar.f("width", this.f3847b, ExtendedFloatingActionButton.t));
        }
        if (hVar.j("height")) {
            arrayList.add(hVar.f("height", this.f3847b, ExtendedFloatingActionButton.u));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        c.b.a.a.m.b.a(animatorSet, arrayList);
        return animatorSet;
    }

    public final h l() {
        h hVar = this.f;
        if (hVar != null) {
            return hVar;
        }
        if (this.f3850e == null) {
            this.f3850e = h.d(this.f3846a, i());
        }
        return (h) b.g.j.h.c(this.f3850e);
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public void onAnimationStart(Animator animator) {
        this.f3849d.c(animator);
    }
}
