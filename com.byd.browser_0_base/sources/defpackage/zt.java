package defpackage;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import com.google.android.material.R$drawable;
import com.google.android.material.R$string;
import com.google.android.material.textfield.a;

/* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class zt extends sh0 {
    public EditText e;
    public final vt f;
    public final wt g;
    public AnimatorSet h;
    public ValueAnimator i;

    public zt(a aVar) {
        super(aVar);
        int i = 0;
        this.f = new vt(i, this);
        this.g = new wt(i, this);
    }

    @Override // defpackage.sh0
    public final void a() {
        if (this.b.r != null) {
            return;
        }
        t(u());
    }

    @Override // defpackage.sh0
    public final int c() {
        return R$string.clear_text_end_icon_content_description;
    }

    @Override // defpackage.sh0
    public final int d() {
        return R$drawable.mtrl_ic_cancel;
    }

    @Override // defpackage.sh0
    public final View.OnFocusChangeListener e() {
        return this.g;
    }

    @Override // defpackage.sh0
    public final View.OnClickListener f() {
        return this.f;
    }

    @Override // defpackage.sh0
    public final View.OnFocusChangeListener g() {
        return this.g;
    }

    @Override // defpackage.sh0
    public final void m(EditText editText) {
        this.e = editText;
        this.a.setEndIconVisible(u());
    }

    @Override // defpackage.sh0
    public final void p(boolean z) {
        if (this.b.r == null) {
            return;
        }
        t(z);
    }

    @Override // defpackage.sh0
    public final void r() {
        int i = 2;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.8f, 1.0f);
        valueAnimatorOfFloat.setInterpolator(u5.d);
        valueAnimatorOfFloat.setDuration(150L);
        valueAnimatorOfFloat.addUpdateListener(new ut(0, this));
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        LinearInterpolator linearInterpolator = u5.a;
        valueAnimatorOfFloat2.setInterpolator(linearInterpolator);
        valueAnimatorOfFloat2.setDuration(100L);
        valueAnimatorOfFloat2.addUpdateListener(new ht2(i, this));
        AnimatorSet animatorSet = new AnimatorSet();
        this.h = animatorSet;
        animatorSet.playTogether(valueAnimatorOfFloat, valueAnimatorOfFloat2);
        this.h.addListener(new xt(this));
        ValueAnimator valueAnimatorOfFloat3 = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat3.setInterpolator(linearInterpolator);
        valueAnimatorOfFloat3.setDuration(100L);
        valueAnimatorOfFloat3.addUpdateListener(new ht2(i, this));
        this.i = valueAnimatorOfFloat3;
        valueAnimatorOfFloat3.addListener(new yt(this));
    }

    @Override // defpackage.sh0
    public final void s() {
        EditText editText = this.e;
        if (editText != null) {
            editText.post(new wv(8, this));
        }
    }

    public final void t(boolean z) {
        boolean z2 = this.b.c() == z;
        if (z && !this.h.isRunning()) {
            this.i.cancel();
            this.h.start();
            if (z2) {
                this.h.end();
                return;
            }
            return;
        }
        if (z) {
            return;
        }
        this.h.cancel();
        this.i.start();
        if (z2) {
            this.i.end();
        }
    }

    public final boolean u() {
        EditText editText = this.e;
        return editText != null && (editText.hasFocus() || this.d.hasFocus()) && this.e.getText().length() > 0;
    }
}
