package a5;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;
import com.google.android.material.textfield.TextInputLayout;
import com.stingray.drivemodedetector.R;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public final class h extends p {
    public final a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b f11148e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final c f11149f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final d f11150g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public AnimatorSet f11151h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ValueAnimator f11152i;

    public h(TextInputLayout textInputLayout) {
        super(textInputLayout);
        int i7 = 0;
        this.d = new a(this, 0);
        this.f11148e = new b(this, i7);
        this.f11149f = new c(this, i7);
        this.f11150g = new d(this, 0);
    }

    @Override // a5.p
    public final void a() {
        Drawable drawableK = h6.f.k(this.f11171b, R.drawable.mtrl_ic_cancel);
        TextInputLayout textInputLayout = this.f11170a;
        textInputLayout.setEndIconDrawable(drawableK);
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(R.string.clear_text_end_icon_content_description));
        textInputLayout.setEndIconOnClickListener(new e(this, 0));
        LinkedHashSet linkedHashSet = textInputLayout.f12926p0;
        c cVar = this.f11149f;
        linkedHashSet.add(cVar);
        if (textInputLayout.f12925p != null) {
            cVar.a(textInputLayout);
        }
        textInputLayout.f12934t0.add(this.f11150g);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.8f, 1.0f);
        valueAnimatorOfFloat.setInterpolator(L4.a.d);
        valueAnimatorOfFloat.setDuration(150L);
        valueAnimatorOfFloat.addUpdateListener(new g(this, 1));
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        LinearInterpolator linearInterpolator = L4.a.f4007a;
        valueAnimatorOfFloat2.setInterpolator(linearInterpolator);
        valueAnimatorOfFloat2.setDuration(100L);
        valueAnimatorOfFloat2.addUpdateListener(new g(this, 0));
        AnimatorSet animatorSet = new AnimatorSet();
        this.f11151h = animatorSet;
        animatorSet.playTogether(valueAnimatorOfFloat, valueAnimatorOfFloat2);
        this.f11151h.addListener(new f(this, 0));
        ValueAnimator valueAnimatorOfFloat3 = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat3.setInterpolator(linearInterpolator);
        valueAnimatorOfFloat3.setDuration(100L);
        valueAnimatorOfFloat3.addUpdateListener(new g(this, 0));
        this.f11152i = valueAnimatorOfFloat3;
        valueAnimatorOfFloat3.addListener(new f(this, 1));
    }

    @Override // a5.p
    public final void c(boolean z6) {
        if (this.f11170a.getSuffixText() == null) {
            return;
        }
        d(z6);
    }

    public final void d(boolean z6) {
        boolean z10 = this.f11170a.g() == z6;
        if (z6 && !this.f11151h.isRunning()) {
            this.f11152i.cancel();
            this.f11151h.start();
            if (z10) {
                this.f11151h.end();
                return;
            }
            return;
        }
        if (z6) {
            return;
        }
        this.f11151h.cancel();
        this.f11152i.start();
        if (z10) {
            this.f11152i.end();
        }
    }
}
