package defpackage;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class J6 extends AbstractC1859z7 {
    public J6(int i) {
        if ((i & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.a0 = i;
    }

    @Override // defpackage.AbstractC1859z7
    public Animator J(ViewGroup viewGroup, View view, C0977j7 c0977j7, C0977j7 c0977j72) {
        Float f;
        AbstractC1419r7.a.c(view);
        return K(view, (c0977j7 == null || (f = (Float) c0977j7.a.get("android:fade:transitionAlpha")) == null) ? 1.0f : f.floatValue(), 0.0f);
    }

    public final Animator K(View view, float f, float f2) {
        if (f == f2) {
            return null;
        }
        AbstractC1419r7.a.e(view, f);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) AbstractC1419r7.d, f2);
        objectAnimatorOfFloat.addListener(new I6(view));
        a(new H6(this, view));
        return objectAnimatorOfFloat;
    }

    @Override // defpackage.Z6
    public void g(C0977j7 c0977j7) {
        H(c0977j7);
        c0977j7.a.put("android:fade:transitionAlpha", Float.valueOf(AbstractC1419r7.a(c0977j7.b)));
    }
}
