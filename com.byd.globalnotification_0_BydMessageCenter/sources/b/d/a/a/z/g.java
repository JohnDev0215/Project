package b.d.a.a.z;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<b> f2174a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f2175b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ValueAnimator f2176c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Animator.AnimatorListener f2177d = new a();

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            g gVar = g.this;
            if (gVar.f2176c == animator) {
                gVar.f2176c = null;
            }
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int[] f2179a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ValueAnimator f2180b;

        public b(int[] iArr, ValueAnimator valueAnimator) {
            this.f2179a = iArr;
            this.f2180b = valueAnimator;
        }
    }

    public void a(int[] iArr, ValueAnimator valueAnimator) {
        b bVar = new b(iArr, valueAnimator);
        valueAnimator.addListener(this.f2177d);
        this.f2174a.add(bVar);
    }
}
