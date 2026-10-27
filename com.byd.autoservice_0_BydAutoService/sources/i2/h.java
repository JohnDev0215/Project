package i2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

/* JADX INFO: compiled from: StateListAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<b> f3353a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f3354b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ValueAnimator f3355c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Animator.AnimatorListener f3356d = new a();

    /* JADX INFO: compiled from: StateListAnimator.java */
    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            h hVar = h.this;
            if (hVar.f3355c == animator) {
                hVar.f3355c = null;
            }
        }
    }

    /* JADX INFO: compiled from: StateListAnimator.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int[] f3358a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ValueAnimator f3359b;

        public b(int[] iArr, ValueAnimator valueAnimator) {
            this.f3358a = iArr;
            this.f3359b = valueAnimator;
        }
    }

    public void a(int[] iArr, ValueAnimator valueAnimator) {
        b bVar = new b(iArr, valueAnimator);
        valueAnimator.addListener(this.f3356d);
        this.f3353a.add(bVar);
    }

    public final void b() {
        ValueAnimator valueAnimator = this.f3355c;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.f3355c = null;
        }
    }

    public void c() {
        ValueAnimator valueAnimator = this.f3355c;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.f3355c = null;
        }
    }

    public void d(int[] iArr) {
        b bVar;
        int size = this.f3353a.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                bVar = null;
                break;
            }
            bVar = this.f3353a.get(i3);
            if (StateSet.stateSetMatches(bVar.f3358a, iArr)) {
                break;
            } else {
                i3++;
            }
        }
        b bVar2 = this.f3354b;
        if (bVar == bVar2) {
            return;
        }
        if (bVar2 != null) {
            b();
        }
        this.f3354b = bVar;
        if (bVar != null) {
            e(bVar);
        }
    }

    public final void e(b bVar) {
        ValueAnimator valueAnimator = bVar.f3359b;
        this.f3355c = valueAnimator;
        valueAnimator.start();
    }
}
