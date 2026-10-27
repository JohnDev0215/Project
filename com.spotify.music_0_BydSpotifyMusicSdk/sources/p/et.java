package p;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import com.spotify.music.R;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public final class et extends be0 {
    public final pa2 e;
    public final ra2 f;
    public final at g;
    public final bt h;
    public AnimatorSet i;
    public ValueAnimator j;

    public et(TextInputLayout textInputLayout, int i) {
        super(textInputLayout, i);
        this.e = new pa2(1, this);
        this.f = new ra2(1, this);
        this.g = new at(this, 0);
        this.h = new bt(this, 0);
    }

    public static boolean d(et etVar) {
        EditText editText = etVar.a.getEditText();
        return editText != null && (editText.hasFocus() || etVar.c.hasFocus()) && editText.getText().length() > 0;
    }

    @Override // p.be0
    public final void a() {
        int i = this.d;
        if (i == 0) {
            i = R.drawable.mtrl_ic_cancel;
        }
        TextInputLayout textInputLayout = this.a;
        textInputLayout.setEndIconDrawable(i);
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(R.string.clear_text_end_icon_content_description));
        textInputLayout.setEndIconOnClickListener(new e6(3, this));
        LinkedHashSet linkedHashSet = textInputLayout.n0;
        at atVar = this.g;
        linkedHashSet.add(atVar);
        if (textInputLayout.m != null) {
            atVar.a(textInputLayout);
        }
        textInputLayout.r0.add(this.h);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.8f, 1.0f);
        valueAnimatorOfFloat.setInterpolator(e7.d);
        valueAnimatorOfFloat.setDuration(150L);
        valueAnimatorOfFloat.addUpdateListener(new dt(this, 1));
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        LinearInterpolator linearInterpolator = e7.a;
        valueAnimatorOfFloat2.setInterpolator(linearInterpolator);
        valueAnimatorOfFloat2.setDuration(100L);
        valueAnimatorOfFloat2.addUpdateListener(new dt(this, 0));
        AnimatorSet animatorSet = new AnimatorSet();
        this.i = animatorSet;
        animatorSet.playTogether(valueAnimatorOfFloat, valueAnimatorOfFloat2);
        this.i.addListener(new ct(this, 0));
        ValueAnimator valueAnimatorOfFloat3 = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat3.setInterpolator(linearInterpolator);
        valueAnimatorOfFloat3.setDuration(100L);
        valueAnimatorOfFloat3.addUpdateListener(new dt(this, 0));
        this.j = valueAnimatorOfFloat3;
        valueAnimatorOfFloat3.addListener(new ct(this, 1));
    }

    @Override // p.be0
    public final void c(boolean z) {
        if (this.a.getSuffixText() == null) {
            return;
        }
        e(z);
    }

    public final void e(boolean z) {
        boolean z2 = this.a.g() == z;
        if (z && !this.i.isRunning()) {
            this.j.cancel();
            this.i.start();
            if (z2) {
                this.i.end();
                return;
            }
            return;
        }
        if (z) {
            return;
        }
        this.i.cancel();
        this.j.start();
        if (z2) {
            this.j.end();
        }
    }
}
