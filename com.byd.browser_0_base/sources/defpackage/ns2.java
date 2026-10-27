package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import java.util.ArrayList;

/* JADX INFO: compiled from: StateListAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public final class ns2 {
    public final ArrayList<b> a = new ArrayList<>();
    public ValueAnimator b = null;
    public final a c = new a();

    /* JADX INFO: compiled from: StateListAnimator.java */
    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            ns2 ns2Var = ns2.this;
            if (ns2Var.b == animator) {
                ns2Var.b = null;
            }
        }
    }

    /* JADX INFO: compiled from: StateListAnimator.java */
    public static class b {
        public b(int[] iArr, ValueAnimator valueAnimator) {
        }
    }

    public final void a(int[] iArr, ValueAnimator valueAnimator) {
        b bVar = new b(iArr, valueAnimator);
        valueAnimator.addListener(this.c);
        this.a.add(bVar);
    }
}
