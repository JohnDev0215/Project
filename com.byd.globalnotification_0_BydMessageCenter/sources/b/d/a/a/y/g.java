package b.d.a.a.y;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.util.Property;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class g extends e {
    public g(FloatingActionButton floatingActionButton, b.d.a.a.d0.b bVar) {
        super(floatingActionButton, bVar);
    }

    @Override // b.d.a.a.y.e
    public float c() {
        return this.t.getElevation();
    }

    @Override // b.d.a.a.y.e
    public void d(Rect rect) {
        if (FloatingActionButton.this.j) {
            super.d(rect);
        } else {
            int sizeDimension = r() ? 0 : (0 - this.t.getSizeDimension()) / 2;
            rect.set(sizeDimension, sizeDimension, sizeDimension, sizeDimension);
        }
    }

    @Override // b.d.a.a.y.e
    public void g() {
    }

    @Override // b.d.a.a.y.e
    public void h() {
        t();
    }

    @Override // b.d.a.a.y.e
    public void i(int[] iArr) {
    }

    @Override // b.d.a.a.y.e
    public void j(float f2, float f3, float f4) {
        StateListAnimator stateListAnimator = new StateListAnimator();
        stateListAnimator.addState(e.B, u(f2, f4));
        stateListAnimator.addState(e.C, u(f2, f3));
        stateListAnimator.addState(e.D, u(f2, f3));
        stateListAnimator.addState(e.E, u(f2, f3));
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        arrayList.add(ObjectAnimator.ofFloat(this.t, "elevation", f2).setDuration(0L));
        arrayList.add(ObjectAnimator.ofFloat(this.t, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, 0.0f).setDuration(100L));
        animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
        animatorSet.setInterpolator(e.A);
        stateListAnimator.addState(e.F, animatorSet);
        stateListAnimator.addState(e.G, u(0.0f, 0.0f));
        this.t.setStateListAnimator(stateListAnimator);
        if (p()) {
            t();
        }
    }

    @Override // b.d.a.a.y.e
    public boolean m() {
        return false;
    }

    @Override // b.d.a.a.y.e
    public void o(ColorStateList colorStateList) {
    }

    @Override // b.d.a.a.y.e
    public boolean p() {
        return FloatingActionButton.this.j || !r();
    }

    @Override // b.d.a.a.y.e
    public void s() {
    }

    public final Animator u(float f2, float f3) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.t, "elevation", f2).setDuration(0L)).with(ObjectAnimator.ofFloat(this.t, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, f3).setDuration(100L));
        animatorSet.setInterpolator(e.A);
        return animatorSet;
    }
}
