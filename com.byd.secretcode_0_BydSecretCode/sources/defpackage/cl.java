package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: IndicatorViewController.java */
/* JADX INFO: loaded from: classes.dex */
public final class cl {
    public final Context a;
    public final TextInputLayout b;
    public LinearLayout c;
    public int d;
    public FrameLayout e;
    public int f;
    public Animator g;
    public final float h;
    public int i;
    public int j;
    public CharSequence k;
    public boolean l;
    public TextView m;
    public CharSequence n;
    public int o;
    public ColorStateList p;
    public CharSequence q;
    public boolean r;
    public TextView s;
    public int t;
    public ColorStateList u;
    public Typeface v;

    /* JADX INFO: compiled from: IndicatorViewController.java */
    public class a extends AnimatorListenerAdapter {
        public final /* synthetic */ int a;
        public final /* synthetic */ TextView b;
        public final /* synthetic */ int c;
        public final /* synthetic */ TextView d;

        public a(int i, TextView textView, int i2, TextView textView2) {
            this.a = i;
            this.b = textView;
            this.c = i2;
            this.d = textView2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            cl.this.i = this.a;
            cl.this.g = null;
            TextView textView = this.b;
            if (textView != null) {
                textView.setVisibility(4);
                if (this.c == 1 && cl.this.m != null) {
                    cl.this.m.setText((CharSequence) null);
                }
            }
            TextView textView2 = this.d;
            if (textView2 != null) {
                textView2.setTranslationY(0.0f);
                this.d.setAlpha(1.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            TextView textView = this.d;
            if (textView != null) {
                textView.setVisibility(0);
            }
        }
    }

    public cl(TextInputLayout textInputLayout) {
        this.a = textInputLayout.getContext();
        this.b = textInputLayout;
        this.h = r0.getResources().getDimensionPixelSize(R$dimen.design_textinput_caption_translate_y);
    }

    public void A(CharSequence charSequence) {
        this.n = charSequence;
        TextView textView = this.m;
        if (textView != null) {
            textView.setContentDescription(charSequence);
        }
    }

    public void B(boolean z) {
        if (this.l == z) {
            return;
        }
        g();
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.a);
            this.m = appCompatTextView;
            appCompatTextView.setId(R$id.textinput_error);
            if (Build.VERSION.SDK_INT >= 17) {
                this.m.setTextAlignment(5);
            }
            Typeface typeface = this.v;
            if (typeface != null) {
                this.m.setTypeface(typeface);
            }
            C(this.o);
            D(this.p);
            A(this.n);
            this.m.setVisibility(4);
            t9.l0(this.m, 1);
            d(this.m, 0);
        } else {
            s();
            y(this.m, 0);
            this.m = null;
            this.b.k0();
            this.b.y0();
        }
        this.l = z;
    }

    public void C(int i) {
        this.o = i;
        TextView textView = this.m;
        if (textView != null) {
            this.b.Y(textView, i);
        }
    }

    public void D(ColorStateList colorStateList) {
        this.p = colorStateList;
        TextView textView = this.m;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    public void E(int i) {
        this.t = i;
        TextView textView = this.s;
        if (textView != null) {
            qa.q(textView, i);
        }
    }

    public void F(boolean z) {
        if (this.r == z) {
            return;
        }
        g();
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.a);
            this.s = appCompatTextView;
            appCompatTextView.setId(R$id.textinput_helper_text);
            if (Build.VERSION.SDK_INT >= 17) {
                this.s.setTextAlignment(5);
            }
            Typeface typeface = this.v;
            if (typeface != null) {
                this.s.setTypeface(typeface);
            }
            this.s.setVisibility(4);
            t9.l0(this.s, 1);
            E(this.t);
            G(this.u);
            d(this.s, 1);
        } else {
            t();
            y(this.s, 1);
            this.s = null;
            this.b.k0();
            this.b.y0();
        }
        this.r = z;
    }

    public void G(ColorStateList colorStateList) {
        this.u = colorStateList;
        TextView textView = this.s;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    public final void H(TextView textView, Typeface typeface) {
        if (textView != null) {
            textView.setTypeface(typeface);
        }
    }

    public void I(Typeface typeface) {
        if (typeface != this.v) {
            this.v = typeface;
            H(this.m, typeface);
            H(this.s, typeface);
        }
    }

    public final void J(ViewGroup viewGroup, int i) {
        if (i == 0) {
            viewGroup.setVisibility(8);
        }
    }

    public final boolean K(TextView textView, CharSequence charSequence) {
        return t9.P(this.b) && this.b.isEnabled() && !(this.j == this.i && textView != null && TextUtils.equals(textView.getText(), charSequence));
    }

    public void L(CharSequence charSequence) {
        g();
        this.k = charSequence;
        this.m.setText(charSequence);
        int i = this.i;
        if (i != 1) {
            this.j = 1;
        }
        N(i, this.j, K(this.m, charSequence));
    }

    public void M(CharSequence charSequence) {
        g();
        this.q = charSequence;
        this.s.setText(charSequence);
        int i = this.i;
        if (i != 2) {
            this.j = 2;
        }
        N(i, this.j, K(this.s, charSequence));
    }

    public final void N(int i, int i2, boolean z) {
        if (i == i2) {
            return;
        }
        if (z) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.g = animatorSet;
            ArrayList arrayList = new ArrayList();
            h(arrayList, this.r, this.s, 2, i, i2);
            h(arrayList, this.l, this.m, 1, i, i2);
            ch.a(animatorSet, arrayList);
            animatorSet.addListener(new a(i2, l(i), i, l(i2)));
            animatorSet.start();
        } else {
            z(i, i2);
        }
        this.b.k0();
        this.b.o0(z);
        this.b.y0();
    }

    public void d(TextView textView, int i) {
        if (this.c == null && this.e == null) {
            LinearLayout linearLayout = new LinearLayout(this.a);
            this.c = linearLayout;
            linearLayout.setOrientation(0);
            this.b.addView(this.c, -1, -2);
            this.e = new FrameLayout(this.a);
            this.c.addView(this.e, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (this.b.getEditText() != null) {
                e();
            }
        }
        if (v(i)) {
            this.e.setVisibility(0);
            this.e.addView(textView);
            this.f++;
        } else {
            this.c.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.c.setVisibility(0);
        this.d++;
    }

    public void e() {
        if (f()) {
            t9.w0(this.c, t9.E(this.b.getEditText()), 0, t9.D(this.b.getEditText()), 0);
        }
    }

    public final boolean f() {
        return (this.c == null || this.b.getEditText() == null) ? false : true;
    }

    public void g() {
        Animator animator = this.g;
        if (animator != null) {
            animator.cancel();
        }
    }

    public final void h(List<Animator> list, boolean z, TextView textView, int i, int i2, int i3) {
        if (textView == null || !z) {
            return;
        }
        if (i == i3 || i == i2) {
            list.add(i(textView, i3 == i));
            if (i3 == i) {
                list.add(j(textView));
            }
        }
    }

    public final ObjectAnimator i(TextView textView, boolean z) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.ALPHA, z ? 1.0f : 0.0f);
        objectAnimatorOfFloat.setDuration(167L);
        objectAnimatorOfFloat.setInterpolator(bh.a);
        return objectAnimatorOfFloat;
    }

    public final ObjectAnimator j(TextView textView) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.TRANSLATION_Y, -this.h, 0.0f);
        objectAnimatorOfFloat.setDuration(217L);
        objectAnimatorOfFloat.setInterpolator(bh.d);
        return objectAnimatorOfFloat;
    }

    public boolean k() {
        return u(this.j);
    }

    public final TextView l(int i) {
        if (i == 1) {
            return this.m;
        }
        if (i != 2) {
            return null;
        }
        return this.s;
    }

    public CharSequence m() {
        return this.n;
    }

    public CharSequence n() {
        return this.k;
    }

    public int o() {
        TextView textView = this.m;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public ColorStateList p() {
        TextView textView = this.m;
        if (textView != null) {
            return textView.getTextColors();
        }
        return null;
    }

    public CharSequence q() {
        return this.q;
    }

    public int r() {
        TextView textView = this.s;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public void s() {
        this.k = null;
        g();
        if (this.i == 1) {
            if (!this.r || TextUtils.isEmpty(this.q)) {
                this.j = 0;
            } else {
                this.j = 2;
            }
        }
        N(this.i, this.j, K(this.m, null));
    }

    public void t() {
        g();
        int i = this.i;
        if (i == 2) {
            this.j = 0;
        }
        N(i, this.j, K(this.s, null));
    }

    public final boolean u(int i) {
        return (i != 1 || this.m == null || TextUtils.isEmpty(this.k)) ? false : true;
    }

    public boolean v(int i) {
        return i == 0 || i == 1;
    }

    public boolean w() {
        return this.l;
    }

    public boolean x() {
        return this.r;
    }

    public void y(TextView textView, int i) {
        FrameLayout frameLayout;
        if (this.c == null) {
            return;
        }
        if (!v(i) || (frameLayout = this.e) == null) {
            this.c.removeView(textView);
        } else {
            int i2 = this.f - 1;
            this.f = i2;
            J(frameLayout, i2);
            this.e.removeView(textView);
        }
        int i3 = this.d - 1;
        this.d = i3;
        J(this.c, i3);
    }

    public final void z(int i, int i2) {
        TextView textViewL;
        TextView textViewL2;
        if (i == i2) {
            return;
        }
        if (i2 != 0 && (textViewL2 = l(i2)) != null) {
            textViewL2.setVisibility(0);
            textViewL2.setAlpha(1.0f);
        }
        if (i != 0 && (textViewL = l(i)) != null) {
            textViewL.setVisibility(4);
            if (i == 1) {
                textViewL.setText((CharSequence) null);
            }
        }
        this.i = i2;
    }
}
