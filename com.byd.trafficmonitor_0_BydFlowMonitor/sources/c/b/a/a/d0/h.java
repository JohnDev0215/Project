package c.b.a.a.d0;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

/* JADX INFO: compiled from: StateListAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<b> f3776a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f3777b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ValueAnimator f3778c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Animator.AnimatorListener f3779d = new a();

    /* JADX INFO: compiled from: StateListAnimator.java */
    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            h hVar = h.this;
            if (hVar.f3778c == animator) {
                hVar.f3778c = null;
            }
        }
    }

    /* JADX INFO: compiled from: StateListAnimator.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int[] f3781a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ValueAnimator f3782b;

        public b(int[] iArr, ValueAnimator valueAnimator) {
            this.f3781a = iArr;
            this.f3782b = valueAnimator;
        }
    }

    public void a(int[] iArr, ValueAnimator valueAnimator) {
        b bVar = new b(iArr, valueAnimator);
        valueAnimator.addListener(this.f3779d);
        this.f3776a.add(bVar);
    }

    public final void b() {
        ValueAnimator valueAnimator = this.f3778c;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.f3778c = null;
        }
    }

    public void c() {
        ValueAnimator valueAnimator = this.f3778c;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.f3778c = null;
        }
    }

    public void d(int[] iArr) {
        b bVar;
        int size = this.f3776a.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                bVar = null;
                break;
            }
            bVar = this.f3776a.get(i2);
            if (StateSet.stateSetMatches(bVar.f3781a, iArr)) {
                break;
            } else {
                i2++;
            }
        }
        b bVar2 = this.f3777b;
        if (bVar == bVar2) {
            return;
        }
        if (bVar2 != null) {
            b();
        }
        this.f3777b = bVar;
        if (bVar != null) {
            e(bVar);
        }
    }

    public final void e(b bVar) {
        ValueAnimator valueAnimator = bVar.f3782b;
        this.f3778c = valueAnimator;
        valueAnimator.start();
    }
}
