package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.List;
import p2.h;

/* JADX INFO: compiled from: MotionStrategy.java */
/* JADX INFO: loaded from: classes.dex */
interface c {
    h a();

    AnimatorSet b();

    void c();

    void d();

    List<Animator.AnimatorListener> e();

    void f(ExtendedFloatingActionButton.f fVar);

    boolean g();

    void h(h hVar);

    void i();

    void onAnimationStart(Animator animator);
}
