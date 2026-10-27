package com.google.android.material.textfield;

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
import androidx.core.widget.i;
import b.g.k.t;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: IndicatorViewController.java */
/* JADX INFO: loaded from: classes.dex */
final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f3919a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TextInputLayout f3920b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LinearLayout f3921c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3922d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private FrameLayout f3923e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3924f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Animator f3925g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final float f3926h;
    private int i;
    private int j;
    private CharSequence k;
    private boolean l;
    private TextView m;
    private CharSequence n;
    private int o;
    private ColorStateList p;
    private CharSequence q;
    private boolean r;
    private TextView s;
    private int t;
    private ColorStateList u;
    private Typeface v;

    /* JADX INFO: compiled from: IndicatorViewController.java */
    class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f3927a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ TextView f3928b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f3929c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ TextView f3930d;

        a(int i, TextView textView, int i2, TextView textView2) {
            this.f3927a = i;
            this.f3928b = textView;
            this.f3929c = i2;
            this.f3930d = textView2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f.this.i = this.f3927a;
            f.this.f3925g = null;
            TextView textView = this.f3928b;
            if (textView != null) {
                textView.setVisibility(4);
                if (this.f3929c == 1 && f.this.m != null) {
                    f.this.m.setText((CharSequence) null);
                }
            }
            TextView textView2 = this.f3930d;
            if (textView2 != null) {
                textView2.setTranslationY(0.0f);
                this.f3930d.setAlpha(1.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            TextView textView = this.f3930d;
            if (textView != null) {
                textView.setVisibility(0);
            }
        }
    }

    public f(TextInputLayout textInputLayout) {
        this.f3919a = textInputLayout.getContext();
        this.f3920b = textInputLayout;
        this.f3926h = r0.getResources().getDimensionPixelSize(d.b.a.a.d.s);
    }

    private void H(TextView textView, Typeface typeface) {
        if (textView != null) {
            textView.setTypeface(typeface);
        }
    }

    private void J(ViewGroup viewGroup, int i) {
        if (i == 0) {
            viewGroup.setVisibility(8);
        }
    }

    private boolean K(TextView textView, CharSequence charSequence) {
        return t.P(this.f3920b) && this.f3920b.isEnabled() && !(this.j == this.i && textView != null && TextUtils.equals(textView.getText(), charSequence));
    }

    private void N(int i, int i2, boolean z) {
        if (i == i2) {
            return;
        }
        if (z) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.f3925g = animatorSet;
            ArrayList arrayList = new ArrayList();
            h(arrayList, this.r, this.s, 2, i, i2);
            h(arrayList, this.l, this.m, 1, i, i2);
            d.b.a.a.m.b.a(animatorSet, arrayList);
            animatorSet.addListener(new a(i2, l(i), i, l(i2)));
            animatorSet.start();
        } else {
            z(i, i2);
        }
        this.f3920b.k0();
        this.f3920b.o0(z);
        this.f3920b.y0();
    }

    private boolean f() {
        return (this.f3921c == null || this.f3920b.getEditText() == null) ? false : true;
    }

    private void h(List<Animator> list, boolean z, TextView textView, int i, int i2, int i3) {
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

    private ObjectAnimator i(TextView textView, boolean z) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.ALPHA, z ? 1.0f : 0.0f);
        objectAnimatorOfFloat.setDuration(167L);
        objectAnimatorOfFloat.setInterpolator(d.b.a.a.m.a.f4760a);
        return objectAnimatorOfFloat;
    }

    private ObjectAnimator j(TextView textView) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.TRANSLATION_Y, -this.f3926h, 0.0f);
        objectAnimatorOfFloat.setDuration(217L);
        objectAnimatorOfFloat.setInterpolator(d.b.a.a.m.a.f4763d);
        return objectAnimatorOfFloat;
    }

    private TextView l(int i) {
        if (i == 1) {
            return this.m;
        }
        if (i != 2) {
            return null;
        }
        return this.s;
    }

    private boolean u(int i) {
        return (i != 1 || this.m == null || TextUtils.isEmpty(this.k)) ? false : true;
    }

    private void z(int i, int i2) {
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

    void A(CharSequence charSequence) {
        this.n = charSequence;
        TextView textView = this.m;
        if (textView != null) {
            textView.setContentDescription(charSequence);
        }
    }

    void B(boolean z) {
        if (this.l == z) {
            return;
        }
        g();
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f3919a);
            this.m = appCompatTextView;
            appCompatTextView.setId(d.b.a.a.f.x);
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
            t.l0(this.m, 1);
            d(this.m, 0);
        } else {
            s();
            y(this.m, 0);
            this.m = null;
            this.f3920b.k0();
            this.f3920b.y0();
        }
        this.l = z;
    }

    void C(int i) {
        this.o = i;
        TextView textView = this.m;
        if (textView != null) {
            this.f3920b.Y(textView, i);
        }
    }

    void D(ColorStateList colorStateList) {
        this.p = colorStateList;
        TextView textView = this.m;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    void E(int i) {
        this.t = i;
        TextView textView = this.s;
        if (textView != null) {
            i.q(textView, i);
        }
    }

    void F(boolean z) {
        if (this.r == z) {
            return;
        }
        g();
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f3919a);
            this.s = appCompatTextView;
            appCompatTextView.setId(d.b.a.a.f.y);
            if (Build.VERSION.SDK_INT >= 17) {
                this.s.setTextAlignment(5);
            }
            Typeface typeface = this.v;
            if (typeface != null) {
                this.s.setTypeface(typeface);
            }
            this.s.setVisibility(4);
            t.l0(this.s, 1);
            E(this.t);
            G(this.u);
            d(this.s, 1);
        } else {
            t();
            y(this.s, 1);
            this.s = null;
            this.f3920b.k0();
            this.f3920b.y0();
        }
        this.r = z;
    }

    void G(ColorStateList colorStateList) {
        this.u = colorStateList;
        TextView textView = this.s;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    void I(Typeface typeface) {
        if (typeface != this.v) {
            this.v = typeface;
            H(this.m, typeface);
            H(this.s, typeface);
        }
    }

    void L(CharSequence charSequence) {
        g();
        this.k = charSequence;
        this.m.setText(charSequence);
        int i = this.i;
        if (i != 1) {
            this.j = 1;
        }
        N(i, this.j, K(this.m, charSequence));
    }

    void M(CharSequence charSequence) {
        g();
        this.q = charSequence;
        this.s.setText(charSequence);
        int i = this.i;
        if (i != 2) {
            this.j = 2;
        }
        N(i, this.j, K(this.s, charSequence));
    }

    void d(TextView textView, int i) {
        if (this.f3921c == null && this.f3923e == null) {
            LinearLayout linearLayout = new LinearLayout(this.f3919a);
            this.f3921c = linearLayout;
            linearLayout.setOrientation(0);
            this.f3920b.addView(this.f3921c, -1, -2);
            this.f3923e = new FrameLayout(this.f3919a);
            this.f3921c.addView(this.f3923e, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (this.f3920b.getEditText() != null) {
                e();
            }
        }
        if (v(i)) {
            this.f3923e.setVisibility(0);
            this.f3923e.addView(textView);
            this.f3924f++;
        } else {
            this.f3921c.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.f3921c.setVisibility(0);
        this.f3922d++;
    }

    void e() {
        if (f()) {
            t.w0(this.f3921c, t.E(this.f3920b.getEditText()), 0, t.D(this.f3920b.getEditText()), 0);
        }
    }

    void g() {
        Animator animator = this.f3925g;
        if (animator != null) {
            animator.cancel();
        }
    }

    boolean k() {
        return u(this.j);
    }

    CharSequence m() {
        return this.n;
    }

    CharSequence n() {
        return this.k;
    }

    int o() {
        TextView textView = this.m;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    ColorStateList p() {
        TextView textView = this.m;
        if (textView != null) {
            return textView.getTextColors();
        }
        return null;
    }

    CharSequence q() {
        return this.q;
    }

    int r() {
        TextView textView = this.s;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    void s() {
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

    void t() {
        g();
        int i = this.i;
        if (i == 2) {
            this.j = 0;
        }
        N(i, this.j, K(this.s, null));
    }

    boolean v(int i) {
        return i == 0 || i == 1;
    }

    boolean w() {
        return this.l;
    }

    boolean x() {
        return this.r;
    }

    void y(TextView textView, int i) {
        FrameLayout frameLayout;
        if (this.f3921c == null) {
            return;
        }
        if (!v(i) || (frameLayout = this.f3923e) == null) {
            this.f3921c.removeView(textView);
        } else {
            int i2 = this.f3924f - 1;
            this.f3924f = i2;
            J(frameLayout, i2);
            this.f3923e.removeView(textView);
        }
        int i3 = this.f3922d - 1;
        this.f3922d = i3;
        J(this.f3921c, i3);
    }
}
