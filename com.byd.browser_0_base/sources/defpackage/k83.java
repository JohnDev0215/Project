package defpackage;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.transition.Transition;
import java.util.HashMap;

/* JADX INFO: compiled from: TextScale.java */
/* JADX INFO: loaded from: classes.dex */
public final class k83 extends Transition {

    /* JADX INFO: compiled from: TextScale.java */
    public class a implements ValueAnimator.AnimatorUpdateListener {
        public final /* synthetic */ TextView e;

        public a(TextView textView) {
            this.e = textView;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            TextView textView = this.e;
            textView.setScaleX(fFloatValue);
            textView.setScaleY(fFloatValue);
        }
    }

    @Override // androidx.transition.Transition
    public final void i(df3 df3Var) {
        View view = df3Var.b;
        if (view instanceof TextView) {
            df3Var.a.put("android:textscale:scale", Float.valueOf(((TextView) view).getScaleX()));
        }
    }

    @Override // androidx.transition.Transition
    public final void l(df3 df3Var) {
        View view = df3Var.b;
        if (view instanceof TextView) {
            df3Var.a.put("android:textscale:scale", Float.valueOf(((TextView) view).getScaleX()));
        }
    }

    @Override // androidx.transition.Transition
    public final Animator p(ViewGroup viewGroup, df3 df3Var, df3 df3Var2) {
        if (df3Var == null || df3Var2 == null || !(df3Var.b instanceof TextView)) {
            return null;
        }
        View view = df3Var2.b;
        if (!(view instanceof TextView)) {
            return null;
        }
        TextView textView = (TextView) view;
        HashMap map = df3Var.a;
        HashMap map2 = df3Var2.a;
        float fFloatValue = map.get("android:textscale:scale") != null ? ((Float) map.get("android:textscale:scale")).floatValue() : 1.0f;
        float fFloatValue2 = map2.get("android:textscale:scale") != null ? ((Float) map2.get("android:textscale:scale")).floatValue() : 1.0f;
        if (fFloatValue == fFloatValue2) {
            return null;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fFloatValue, fFloatValue2);
        valueAnimatorOfFloat.addUpdateListener(new a(textView));
        return valueAnimatorOfFloat;
    }
}
