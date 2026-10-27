package b.d.a.a.k0;

import android.animation.ValueAnimator;
import android.view.View;
import com.google.android.material.transformation.FabTransformationBehavior;

/* JADX INFO: loaded from: classes.dex */
public class a implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ View f1962a;

    public a(FabTransformationBehavior fabTransformationBehavior, View view) {
        this.f1962a = view;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f1962a.invalidate();
    }
}
