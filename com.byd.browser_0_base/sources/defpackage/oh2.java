package defpackage;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.View;
import android.widget.ImageButton;

/* JADX INFO: compiled from: SecurityButtonAnimationDelegate.java */
/* JADX INFO: loaded from: classes3.dex */
public final class oh2 {
    public final ImageButton a;
    public final View b;
    public final AnimatorSet c;
    public final AnimatorSet d;
    public final int e;

    /* JADX INFO: compiled from: SecurityButtonAnimationDelegate.java */
    public class a extends vl {
        public a() {
        }

        @Override // defpackage.vl
        public final void c() {
            oh2.this.a.setVisibility(0);
        }
    }

    /* JADX INFO: compiled from: SecurityButtonAnimationDelegate.java */
    public class b extends vl {
        public b() {
        }

        @Override // defpackage.vl
        public final void b() {
            oh2.this.a.setVisibility(4);
        }
    }

    public oh2(ImageButton imageButton, View view, int i) {
        this.a = imageButton;
        this.b = view;
        this.e = imageButton.getResources().getDimensionPixelSize(i);
        AnimatorSet animatorSet = new AnimatorSet();
        this.c = animatorSet;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.TRANSLATION_X, 0.0f);
        pc pcVar = pc.c;
        objectAnimatorOfFloat.setInterpolator(pcVar);
        objectAnimatorOfFloat.setDuration(200L);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(imageButton, (Property<ImageButton, Float>) View.ALPHA, 1.0f);
        objectAnimatorOfFloat2.setInterpolator(pc.e);
        objectAnimatorOfFloat2.setDuration(150L);
        objectAnimatorOfFloat2.addListener(new a());
        animatorSet.playSequentially(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.d = animatorSet2;
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(imageButton, (Property<ImageButton, Float>) View.ALPHA, 0.0f);
        objectAnimatorOfFloat3.setInterpolator(pc.d);
        objectAnimatorOfFloat3.setDuration(150L);
        objectAnimatorOfFloat3.addListener(new b());
        ObjectAnimator objectAnimatorOfFloat4 = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.TRANSLATION_X, -r3);
        objectAnimatorOfFloat4.setInterpolator(pcVar);
        objectAnimatorOfFloat4.setDuration(200L);
        animatorSet2.playSequentially(objectAnimatorOfFloat3, objectAnimatorOfFloat4);
    }
}
