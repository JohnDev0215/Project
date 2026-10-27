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
    private final Context f4350a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TextInputLayout f4351b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LinearLayout f4352c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4353d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private FrameLayout f4354e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4355f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Animator f4356g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final float f4357h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f4358i;
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
        final /* synthetic */ int f4359a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ TextView f4360b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f4361c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ TextView f4362d;

        a(int i2, TextView textView, int i3, TextView textView2) {
            this.f4359a = i2;
            this.f4360b = textView;
            this.f4361c = i3;
            this.f4362d = textView2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f.this.f4358i = this.f4359a;
            f.this.f4356g = null;
            TextView textView = this.f4360b;
            if (textView != null) {
                textView.setVisibility(4);
                if (this.f4361c == 1 && f.this.m != null) {
                    f.this.m.setText((CharSequence) null);
                }
            }
            TextView textView2 = this.f4362d;
            if (textView2 != null) {
                textView2.setTranslationY(0.0f);
                this.f4362d.setAlpha(1.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            TextView textView = this.f4362d;
            if (textView != null) {
                textView.setVisibility(0);
            }
        }
    }

    public f(TextInputLayout textInputLayout) {
        this.f4350a = textInputLayout.getContext();
        this.f4351b = textInputLayout;
        this.f4357h = r0.getResources().getDimensionPixelSize(d.b.a.a.d.design_textinput_caption_translate_y);
    }

    private void H(TextView textView, Typeface typeface) {
        if (textView != null) {
            textView.setTypeface(typeface);
        }
    }

    private void J(ViewGroup viewGroup, int i2) {
        if (i2 == 0) {
            viewGroup.setVisibility(8);
        }
    }

    private boolean K(TextView textView, CharSequence charSequence) {
        return t.P(this.f4351b) && this.f4351b.isEnabled() && !(this.j == this.f4358i && textView != null && TextUtils.equals(textView.getText(), charSequence));
    }

    private void N(int i2, int i3, boolean z) {
        if (i2 == i3) {
            return;
        }
        if (z) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.f4356g = animatorSet;
            ArrayList arrayList = new ArrayList();
            h(arrayList, this.r, this.s, 2, i2, i3);
            h(arrayList, this.l, this.m, 1, i2, i3);
            d.b.a.a.m.b.a(animatorSet, arrayList);
            animatorSet.addListener(new a(i3, l(i2), i2, l(i3)));
            animatorSet.start();
        } else {
            z(i2, i3);
        }
        this.f4351b.k0();
        this.f4351b.o0(z);
        this.f4351b.y0();
    }

    private boolean f() {
        return (this.f4352c == null || this.f4351b.getEditText() == null) ? false : true;
    }

    private void h(List<Animator> list, boolean z, TextView textView, int i2, int i3, int i4) {
        if (textView == null || !z) {
            return;
        }
        if (i2 == i4 || i2 == i3) {
            list.add(i(textView, i4 == i2));
            if (i4 == i2) {
                list.add(j(textView));
            }
        }
    }

    private ObjectAnimator i(TextView textView, boolean z) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.ALPHA, z ? 1.0f : 0.0f);
        objectAnimatorOfFloat.setDuration(167L);
        objectAnimatorOfFloat.setInterpolator(d.b.a.a.m.a.f4674a);
        return objectAnimatorOfFloat;
    }

    private ObjectAnimator j(TextView textView) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.TRANSLATION_Y, -this.f4357h, 0.0f);
        objectAnimatorOfFloat.setDuration(217L);
        objectAnimatorOfFloat.setInterpolator(d.b.a.a.m.a.f4677d);
        return objectAnimatorOfFloat;
    }

    private TextView l(int i2) {
        if (i2 == 1) {
            return this.m;
        }
        if (i2 != 2) {
            return null;
        }
        return this.s;
    }

    private boolean u(int i2) {
        return (i2 != 1 || this.m == null || TextUtils.isEmpty(this.k)) ? false : true;
    }

    private void z(int i2, int i3) {
        TextView textViewL;
        TextView textViewL2;
        if (i2 == i3) {
            return;
        }
        if (i3 != 0 && (textViewL2 = l(i3)) != null) {
            textViewL2.setVisibility(0);
            textViewL2.setAlpha(1.0f);
        }
        if (i2 != 0 && (textViewL = l(i2)) != null) {
            textViewL.setVisibility(4);
            if (i2 == 1) {
                textViewL.setText((CharSequence) null);
            }
        }
        this.f4358i = i3;
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
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f4350a);
            this.m = appCompatTextView;
            appCompatTextView.setId(d.b.a.a.f.textinput_error);
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
            this.f4351b.k0();
            this.f4351b.y0();
        }
        this.l = z;
    }

    void C(int i2) {
        this.o = i2;
        TextView textView = this.m;
        if (textView != null) {
            this.f4351b.Y(textView, i2);
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

    void E(int i2) {
        this.t = i2;
        TextView textView = this.s;
        if (textView != null) {
            i.q(textView, i2);
        }
    }

    void F(boolean z) {
        if (this.r == z) {
            return;
        }
        g();
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f4350a);
            this.s = appCompatTextView;
            appCompatTextView.setId(d.b.a.a.f.textinput_helper_text);
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
            this.f4351b.k0();
            this.f4351b.y0();
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
        int i2 = this.f4358i;
        if (i2 != 1) {
            this.j = 1;
        }
        N(i2, this.j, K(this.m, charSequence));
    }

    void M(CharSequence charSequence) {
        g();
        this.q = charSequence;
        this.s.setText(charSequence);
        int i2 = this.f4358i;
        if (i2 != 2) {
            this.j = 2;
        }
        N(i2, this.j, K(this.s, charSequence));
    }

    void d(TextView textView, int i2) {
        if (this.f4352c == null && this.f4354e == null) {
            LinearLayout linearLayout = new LinearLayout(this.f4350a);
            this.f4352c = linearLayout;
            linearLayout.setOrientation(0);
            this.f4351b.addView(this.f4352c, -1, -2);
            this.f4354e = new FrameLayout(this.f4350a);
            this.f4352c.addView(this.f4354e, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (this.f4351b.getEditText() != null) {
                e();
            }
        }
        if (v(i2)) {
            this.f4354e.setVisibility(0);
            this.f4354e.addView(textView);
            this.f4355f++;
        } else {
            this.f4352c.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.f4352c.setVisibility(0);
        this.f4353d++;
    }

    void e() {
        if (f()) {
            t.w0(this.f4352c, t.E(this.f4351b.getEditText()), 0, t.D(this.f4351b.getEditText()), 0);
        }
    }

    void g() {
        Animator animator = this.f4356g;
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
        if (this.f4358i == 1) {
            if (!this.r || TextUtils.isEmpty(this.q)) {
                this.j = 0;
            } else {
                this.j = 2;
            }
        }
        N(this.f4358i, this.j, K(this.m, null));
    }

    void t() {
        g();
        int i2 = this.f4358i;
        if (i2 == 2) {
            this.j = 0;
        }
        N(i2, this.j, K(this.s, null));
    }

    boolean v(int i2) {
        return i2 == 0 || i2 == 1;
    }

    boolean w() {
        return this.l;
    }

    boolean x() {
        return this.r;
    }

    void y(TextView textView, int i2) {
        FrameLayout frameLayout;
        if (this.f4352c == null) {
            return;
        }
        if (!v(i2) || (frameLayout = this.f4354e) == null) {
            this.f4352c.removeView(textView);
        } else {
            int i3 = this.f4355f - 1;
            this.f4355f = i3;
            J(frameLayout, i3);
            this.f4354e.removeView(textView);
        }
        int i4 = this.f4353d - 1;
        this.f4353d = i4;
        J(this.f4352c, i4);
    }
}
