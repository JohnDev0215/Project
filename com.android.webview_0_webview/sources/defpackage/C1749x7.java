package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: renamed from: x7, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1749x7 extends AnimatorListenerAdapter implements Y6 {
    public final View a;
    public final int b;
    public final ViewGroup c;
    public final boolean d;
    public boolean e;
    public boolean f = false;

    public C1749x7(View view, int i, boolean z) {
        this.a = view;
        this.b = i;
        this.c = (ViewGroup) view.getParent();
        this.d = z;
        f(true);
    }

    @Override // defpackage.Y6
    public void a(Z6 z6) {
        f(false);
    }

    @Override // defpackage.Y6
    public void b(Z6 z6) {
        f(true);
    }

    @Override // defpackage.Y6
    public void c(Z6 z6) {
    }

    @Override // defpackage.Y6
    public void d(Z6 z6) {
        e();
        z6.v(this);
    }

    public final void e() {
        if (!this.f) {
            AbstractC1419r7.d(this.a, this.b);
            ViewGroup viewGroup = this.c;
            if (viewGroup != null) {
                viewGroup.invalidate();
            }
        }
        f(false);
    }

    public final void f(boolean z) {
        ViewGroup viewGroup;
        if (!this.d || this.e == z || (viewGroup = this.c) == null) {
            return;
        }
        this.e = z;
        AbstractC1200n7.a(viewGroup, z);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        this.f = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        e();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public void onAnimationPause(Animator animator) {
        if (this.f) {
            return;
        }
        AbstractC1419r7.d(this.a, this.b);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public void onAnimationResume(Animator animator) {
        if (this.f) {
            return;
        }
        AbstractC1419r7.d(this.a, 0);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
    }
}
