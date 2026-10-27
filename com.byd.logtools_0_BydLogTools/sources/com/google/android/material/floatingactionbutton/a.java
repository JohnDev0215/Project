package com.google.android.material.floatingactionbutton;

import android.animation.Animator;

/* JADX INFO: compiled from: AnimatorTracker.java */
/* JADX INFO: loaded from: classes.dex */
class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Animator f3627a;

    a() {
    }

    public void a() {
        Animator animator = this.f3627a;
        if (animator != null) {
            animator.cancel();
        }
    }

    public void b() {
        this.f3627a = null;
    }

    public void c(Animator animator) {
        a();
        this.f3627a = animator;
    }
}
