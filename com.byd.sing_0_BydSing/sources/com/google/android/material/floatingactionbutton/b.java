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
    private final Context f3564a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ExtendedFloatingActionButton f3565b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ArrayList<Animator.AnimatorListener> f3566c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a f3567d;
    private h e;
    private h f;

    b(ExtendedFloatingActionButton extendedFloatingActionButton, a aVar) {
        this.f3565b = extendedFloatingActionButton;
        this.f3564a = extendedFloatingActionButton.getContext();
        this.f3567d = aVar;
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public void a() {
        this.f3567d.b();
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public void b() {
        this.f3567d.b();
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public final void c(h hVar) {
        this.f = hVar;
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public h f() {
        return this.f;
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public AnimatorSet g() {
        return k(l());
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public final List<Animator.AnimatorListener> h() {
        return this.f3566c;
    }

    AnimatorSet k(h hVar) {
        ArrayList arrayList = new ArrayList();
        if (hVar.j("opacity")) {
            arrayList.add(hVar.f("opacity", this.f3565b, View.ALPHA));
        }
        if (hVar.j("scale")) {
            arrayList.add(hVar.f("scale", this.f3565b, View.SCALE_Y));
            arrayList.add(hVar.f("scale", this.f3565b, View.SCALE_X));
        }
        if (hVar.j("width")) {
            arrayList.add(hVar.f("width", this.f3565b, ExtendedFloatingActionButton.t));
        }
        if (hVar.j("height")) {
            arrayList.add(hVar.f("height", this.f3565b, ExtendedFloatingActionButton.u));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        d.b.a.a.m.b.a(animatorSet, arrayList);
        return animatorSet;
    }

    public final h l() {
        h hVar = this.f;
        if (hVar != null) {
            return hVar;
        }
        if (this.e == null) {
            this.e = h.d(this.f3564a, d());
        }
        return (h) c.g.j.h.c(this.e);
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public void onAnimationStart(Animator animator) {
        this.f3567d.c(animator);
    }
}
