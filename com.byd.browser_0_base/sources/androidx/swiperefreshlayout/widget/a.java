package androidx.swiperefreshlayout.widget;

import android.animation.ValueAnimator;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

/* JADX INFO: compiled from: CircularProgressDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ CircularProgressDrawable.a e;
    public final /* synthetic */ CircularProgressDrawable f;

    public a(CircularProgressDrawable circularProgressDrawable, CircularProgressDrawable.a aVar) {
        this.f = circularProgressDrawable;
        this.e = aVar;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        CircularProgressDrawable circularProgressDrawable = this.f;
        circularProgressDrawable.getClass();
        CircularProgressDrawable.a aVar = this.e;
        CircularProgressDrawable.e(fFloatValue, aVar);
        circularProgressDrawable.b(fFloatValue, aVar, false);
        circularProgressDrawable.invalidateSelf();
    }
}
