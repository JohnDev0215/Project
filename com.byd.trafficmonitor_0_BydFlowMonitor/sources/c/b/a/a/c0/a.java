package c.b.a.a.c0;

import android.animation.Animator;

/* JADX INFO: compiled from: AnimatorTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Animator f3700a;

    public void a() {
        Animator animator = this.f3700a;
        if (animator != null) {
            animator.cancel();
        }
    }

    public void b() {
        this.f3700a = null;
    }

    public void c(Animator animator) {
        a();
        this.f3700a = animator;
    }
}
