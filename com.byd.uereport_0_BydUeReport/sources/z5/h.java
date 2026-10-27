package z5;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

/* JADX INFO: compiled from: StateListAnimator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<b> f9584a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f9585b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ValueAnimator f9586c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Animator.AnimatorListener f9587d = new a();

    /* JADX INFO: compiled from: StateListAnimator.java */
    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            h hVar = h.this;
            if (hVar.f9586c == animator) {
                hVar.f9586c = null;
            }
        }
    }

    /* JADX INFO: compiled from: StateListAnimator.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int[] f9589a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ValueAnimator f9590b;

        public b(int[] iArr, ValueAnimator valueAnimator) {
            this.f9589a = iArr;
            this.f9590b = valueAnimator;
        }
    }

    public void a(int[] iArr, ValueAnimator valueAnimator) {
        b bVar = new b(iArr, valueAnimator);
        valueAnimator.addListener(this.f9587d);
        this.f9584a.add(bVar);
    }

    public final void b() {
        ValueAnimator valueAnimator = this.f9586c;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.f9586c = null;
        }
    }

    public void c() {
        ValueAnimator valueAnimator = this.f9586c;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.f9586c = null;
        }
    }

    public void d(int[] iArr) {
        b bVar;
        int size = this.f9584a.size();
        int i8 = 0;
        while (true) {
            if (i8 >= size) {
                bVar = null;
                break;
            }
            bVar = this.f9584a.get(i8);
            if (StateSet.stateSetMatches(bVar.f9589a, iArr)) {
                break;
            } else {
                i8++;
            }
        }
        b bVar2 = this.f9585b;
        if (bVar == bVar2) {
            return;
        }
        if (bVar2 != null) {
            b();
        }
        this.f9585b = bVar;
        if (bVar != null) {
            e(bVar);
        }
    }

    public final void e(b bVar) {
        ValueAnimator valueAnimator = bVar.f9590b;
        this.f9586c = valueAnimator;
        valueAnimator.start();
    }
}
