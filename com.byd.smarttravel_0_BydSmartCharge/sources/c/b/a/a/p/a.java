package c.b.a.a.p;

import android.animation.Animator;

/* JADX INFO: compiled from: AnimatorTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Animator f1744a;

    public void a() {
        Animator animator = this.f1744a;
        if (animator != null) {
            animator.cancel();
        }
    }

    public void b() {
        this.f1744a = null;
    }

    public void c(Animator animator) {
        a();
        this.f1744a = animator;
    }
}
