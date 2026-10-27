package O3;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import m3.C1805e;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5088a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Drawable.Callback f5089b;

    public /* synthetic */ f(Drawable.Callback callback, int i7) {
        this.f5088a = i7;
        this.f5089b = callback;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.f5088a) {
            case 0:
                j jVar = (j) this.f5089b;
                int i7 = jVar.f5117W;
                if (i7 == 0) {
                    i7 = 1;
                }
                if (!(i7 == 2)) {
                    X3.c cVar = jVar.f5127w;
                    if (cVar != null) {
                        cVar.m(jVar.f5118m.a());
                    }
                } else {
                    jVar.invalidateSelf();
                }
                break;
            default:
                C1805e c1805e = (C1805e) this.f5089b;
                c1805e.getClass();
                c1805e.f19032Q = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                c1805e.invalidate(c1805e.l);
                break;
        }
    }
}
