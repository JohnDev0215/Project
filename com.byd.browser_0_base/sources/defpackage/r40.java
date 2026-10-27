package defpackage;

import android.animation.AnimatorSet;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.a;

/* JADX INFO: compiled from: CustomTabToolbarAnimationDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class r40 {
    public final oh2 a;
    public final cj b;
    public TextView c;
    public TextView d;
    public boolean e;
    public boolean f;
    public int g;
    public boolean h;

    public r40(ImageButton imageButton, View view, int i) {
        view.setTranslationX(-imageButton.getResources().getDimensionPixelSize(i));
        this.a = new oh2(imageButton, view, i);
        this.b = new cj(imageButton);
    }

    public final void a(int i, boolean z) {
        int i2 = 1;
        if (this.f && z) {
            cj cjVar = this.b;
            if (cjVar.b != i) {
                cjVar.b = i;
                ImageView imageView = cjVar.c;
                if (imageView.getVisibility() != 0 || imageView.getDrawable() == null) {
                    imageView.setImageResource(i);
                } else {
                    if (cjVar.a) {
                        cjVar.a = false;
                        if (imageView.getDrawable() instanceof TransitionDrawable) {
                            ((TransitionDrawable) imageView.getDrawable()).resetTransition();
                        }
                    }
                    Drawable drawableC = c6.c(imageView.getContext().getResources(), i, 0);
                    Drawable drawable = imageView.getDrawable();
                    if (drawable instanceof TransitionDrawable) {
                        TransitionDrawable transitionDrawable = (TransitionDrawable) drawable;
                        if (transitionDrawable.getNumberOfLayers() == 2) {
                            drawable = transitionDrawable.getDrawable(1);
                        }
                    }
                    Resources resources = imageView.getResources();
                    int iMax = Math.max(drawableC.getIntrinsicWidth(), drawable.getIntrinsicWidth());
                    int iMax2 = Math.max(drawableC.getIntrinsicHeight(), drawable.getIntrinsicHeight());
                    BitmapDrawable bitmapDrawableA = cj.a(resources, drawableC, iMax, iMax2);
                    RotateDrawable rotateDrawable = new RotateDrawable();
                    rotateDrawable.setDrawable(bitmapDrawableA);
                    rotateDrawable.setToDegrees(180.0f);
                    rotateDrawable.setLevel(TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE);
                    TransitionDrawable transitionDrawable2 = new TransitionDrawable(new Drawable[]{cj.a(resources, drawable, iMax, iMax2), rotateDrawable});
                    transitionDrawable2.setCrossFadeEnabled(true);
                    imageView.setImageDrawable(transitionDrawable2);
                    cjVar.a = true;
                    imageView.animate().setDuration(250L).rotationBy(180.0f).setInterpolator(h21.e).withStartAction(new a(27, transitionDrawable2)).withEndAction(new b50(i, i2, cjVar)).start();
                }
            }
        } else {
            if (cc3.b()) {
                i2 = i != this.g ? 1 : 0;
            }
            oh2 oh2Var = this.a;
            AnimatorSet animatorSet = oh2Var.d;
            AnimatorSet animatorSet2 = oh2Var.c;
            ImageButton imageButton = oh2Var.a;
            if (i == 0) {
                imageButton.setImageDrawable(null);
                if (animatorSet2.isStarted()) {
                    animatorSet2.cancel();
                }
                if (!animatorSet.isStarted() && oh2Var.b.getTranslationX() != (-oh2Var.e)) {
                    animatorSet.start();
                    if (!z) {
                        animatorSet.end();
                    }
                }
            } else {
                if (i2 != 0) {
                    imageButton.setImageResource(i);
                }
                if (animatorSet.isStarted()) {
                    animatorSet.cancel();
                }
                if (!animatorSet2.isStarted() && imageButton.getVisibility() != 0) {
                    animatorSet2.start();
                    if (!z) {
                        animatorSet2.end();
                    }
                }
            }
        }
        this.g = i;
    }
}
