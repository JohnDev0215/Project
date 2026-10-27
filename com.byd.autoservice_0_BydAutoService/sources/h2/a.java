package h2;

import android.animation.Animator;

/* JADX INFO: compiled from: AnimatorTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Animator f3066a;

    public void a() {
        Animator animator = this.f3066a;
        if (animator != null) {
            animator.cancel();
        }
    }

    public void b() {
        this.f3066a = null;
    }

    public void c(Animator animator) {
        a();
        this.f3066a = animator;
    }
}
