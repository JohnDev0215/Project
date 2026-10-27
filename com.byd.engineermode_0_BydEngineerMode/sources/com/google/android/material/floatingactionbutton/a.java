package com.google.android.material.floatingactionbutton;

import android.animation.Animator;

/* JADX INFO: compiled from: AnimatorTracker.java */
/* JADX INFO: loaded from: classes.dex */
class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Animator f2754a;

    a() {
    }

    public void a() {
        Animator animator = this.f2754a;
        if (animator != null) {
            animator.cancel();
        }
    }

    public void b() {
        this.f2754a = null;
    }

    public void c(Animator animator) {
        a();
        this.f2754a = animator;
    }
}
