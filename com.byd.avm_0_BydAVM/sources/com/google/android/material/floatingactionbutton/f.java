package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import c.a.a.a.m.h;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.List;

/* JADX INFO: compiled from: MotionStrategy.java */
/* JADX INFO: loaded from: classes.dex */
interface f {
    h a();

    AnimatorSet b();

    void c();

    void d();

    List<Animator.AnimatorListener> e();

    void f(ExtendedFloatingActionButton.h hVar);

    boolean g();

    void h(h hVar);

    int i();

    void j();

    void onAnimationStart(Animator animator);
}
