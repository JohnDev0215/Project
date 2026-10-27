package y5;

import android.animation.Animator;

/* JADX INFO: compiled from: AnimatorTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Animator f9322a;

    public void a() {
        Animator animator = this.f9322a;
        if (animator != null) {
            animator.cancel();
        }
    }

    public void b() {
        this.f9322a = null;
    }

    public void c(Animator animator) {
        a();
        this.f9322a = animator;
    }
}
