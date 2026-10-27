package b.d.a.a.m;

import android.animation.ValueAnimator;
import com.google.android.material.appbar.AppBarLayout;

/* JADX INFO: loaded from: classes.dex */
public class a implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ b.d.a.a.e0.g f1989a;

    public a(AppBarLayout appBarLayout, b.d.a.a.e0.g gVar) {
        this.f1989a = gVar;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f1989a.p(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
