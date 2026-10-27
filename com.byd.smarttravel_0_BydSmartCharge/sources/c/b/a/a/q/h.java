package c.b.a.a.q;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

/* JADX INFO: compiled from: StateListAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<b> f1791a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f1792b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ValueAnimator f1793c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Animator.AnimatorListener f1794d = new a();

    /* JADX INFO: compiled from: StateListAnimator.java */
    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            h hVar = h.this;
            if (hVar.f1793c == animator) {
                hVar.f1793c = null;
            }
        }
    }

    /* JADX INFO: compiled from: StateListAnimator.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int[] f1796a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ValueAnimator f1797b;

        public b(int[] iArr, ValueAnimator valueAnimator) {
            this.f1796a = iArr;
            this.f1797b = valueAnimator;
        }
    }

    public void a(int[] iArr, ValueAnimator valueAnimator) {
        b bVar = new b(iArr, valueAnimator);
        valueAnimator.addListener(this.f1794d);
        this.f1791a.add(bVar);
    }

    public final void b() {
        ValueAnimator valueAnimator = this.f1793c;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.f1793c = null;
        }
    }

    public void c() {
        ValueAnimator valueAnimator = this.f1793c;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.f1793c = null;
        }
    }

    public void d(int[] iArr) {
        b bVar;
        int size = this.f1791a.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                bVar = null;
                break;
            }
            bVar = this.f1791a.get(i);
            if (StateSet.stateSetMatches(bVar.f1796a, iArr)) {
                break;
            } else {
                i++;
            }
        }
        b bVar2 = this.f1792b;
        if (bVar == bVar2) {
            return;
        }
        if (bVar2 != null) {
            b();
        }
        this.f1792b = bVar;
        if (bVar != null) {
            e(bVar);
        }
    }

    public final void e(b bVar) {
        ValueAnimator valueAnimator = bVar.f1797b;
        this.f1793c = valueAnimator;
        valueAnimator.start();
    }
}
