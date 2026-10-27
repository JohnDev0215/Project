package defpackage;

import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: SnackbarView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zp2 implements View.OnLayoutChangeListener {
    public final /* synthetic */ yp2 e;

    public zp2(yp2 yp2Var) {
        this.e = yp2Var;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        yp2 yp2Var = this.e;
        yp2Var.b.removeOnLayoutChangeListener(this);
        ViewGroup viewGroup = yp2Var.b;
        viewGroup.setTranslationY(viewGroup.getHeight() + ((FrameLayout.LayoutParams) viewGroup.getLayoutParams()).bottomMargin);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroup, (Property<ViewGroup, Float>) View.TRANSLATION_Y, 0.0f);
        objectAnimatorOfFloat.setInterpolator(h21.a);
        objectAnimatorOfFloat.setDuration(yp2Var.g);
        WindowAndroid windowAndroid = yp2Var.a;
        if (windowAndroid != null) {
            windowAndroid.v(objectAnimatorOfFloat);
        } else {
            objectAnimatorOfFloat.start();
        }
    }
}
