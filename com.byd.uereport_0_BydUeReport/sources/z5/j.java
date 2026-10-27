package z5;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Map;
import k1.r;

/* JADX INFO: compiled from: TextScale.java */
/* JADX INFO: loaded from: classes2.dex */
public class j extends k1.l {

    /* JADX INFO: compiled from: TextScale.java */
    public class a implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ TextView f9598d;

        public a(TextView textView) {
            this.f9598d = textView;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            this.f9598d.setScaleX(fFloatValue);
            this.f9598d.setScaleY(fFloatValue);
        }
    }

    public final void c0(r rVar) {
        View view = rVar.f5823b;
        if (view instanceof TextView) {
            rVar.f5822a.put("android:textscale:scale", Float.valueOf(((TextView) view).getScaleX()));
        }
    }

    @Override // k1.l
    public void g(r rVar) {
        c0(rVar);
    }

    @Override // k1.l
    public void j(r rVar) {
        c0(rVar);
    }

    @Override // k1.l
    public Animator n(ViewGroup viewGroup, r rVar, r rVar2) {
        if (rVar == null || rVar2 == null || !(rVar.f5823b instanceof TextView)) {
            return null;
        }
        View view = rVar2.f5823b;
        if (!(view instanceof TextView)) {
            return null;
        }
        TextView textView = (TextView) view;
        Map<String, Object> map = rVar.f5822a;
        Map<String, Object> map2 = rVar2.f5822a;
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
