package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.w;
import i0.u;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: IndicatorViewController.java */
/* JADX INFO: loaded from: classes.dex */
final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f4897a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TextInputLayout f4898b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LinearLayout f4899c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4900d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private FrameLayout f4901e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Animator f4902f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final float f4903g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f4904h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f4905i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private CharSequence f4906j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f4907k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private TextView f4908l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private CharSequence f4909m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f4910n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private ColorStateList f4911o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private CharSequence f4912p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f4913q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private TextView f4914r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f4915s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private ColorStateList f4916t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private Typeface f4917u;

    /* JADX INFO: compiled from: IndicatorViewController.java */
    class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f4918a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ TextView f4919b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f4920c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ TextView f4921d;

        a(int i7, TextView textView, int i8, TextView textView2) {
            this.f4918a = i7;
            this.f4919b = textView;
            this.f4920c = i8;
            this.f4921d = textView2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f.this.f4904h = this.f4918a;
            f.this.f4902f = null;
            TextView textView = this.f4919b;
            if (textView != null) {
                textView.setVisibility(4);
                if (this.f4920c == 1 && f.this.f4908l != null) {
                    f.this.f4908l.setText((CharSequence) null);
                }
            }
            TextView textView2 = this.f4921d;
            if (textView2 != null) {
                textView2.setTranslationY(0.0f);
                this.f4921d.setAlpha(1.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            TextView textView = this.f4921d;
            if (textView != null) {
                textView.setVisibility(0);
            }
        }
    }

    public f(TextInputLayout textInputLayout) {
        this.f4897a = textInputLayout.getContext();
        this.f4898b = textInputLayout;
        this.f4903g = r0.getResources().getDimensionPixelSize(o2.d.design_textinput_caption_translate_y);
    }

    private void A(int i7, int i8) {
        TextView textViewL;
        TextView textViewL2;
        if (i7 == i8) {
            return;
        }
        if (i8 != 0 && (textViewL2 = l(i8)) != null) {
            textViewL2.setVisibility(0);
            textViewL2.setAlpha(1.0f);
        }
        if (i7 != 0 && (textViewL = l(i7)) != null) {
            textViewL.setVisibility(4);
            if (i7 == 1) {
                textViewL.setText((CharSequence) null);
            }
        }
        this.f4904h = i8;
    }

    private void I(TextView textView, Typeface typeface) {
        if (textView != null) {
            textView.setTypeface(typeface);
        }
    }

    private void K(ViewGroup viewGroup, int i7) {
        if (i7 == 0) {
            viewGroup.setVisibility(8);
        }
    }

    private boolean L(TextView textView, CharSequence charSequence) {
        return u.P(this.f4898b) && this.f4898b.isEnabled() && !(this.f4905i == this.f4904h && textView != null && TextUtils.equals(textView.getText(), charSequence));
    }

    private void O(int i7, int i8, boolean z6) {
        if (i7 == i8) {
            return;
        }
        if (z6) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.f4902f = animatorSet;
            ArrayList arrayList = new ArrayList();
            h(arrayList, this.f4913q, this.f4914r, 2, i7, i8);
            h(arrayList, this.f4907k, this.f4908l, 1, i7, i8);
            p2.b.a(animatorSet, arrayList);
            animatorSet.addListener(new a(i8, l(i7), i7, l(i8)));
            animatorSet.start();
        } else {
            A(i7, i8);
        }
        this.f4898b.s0();
        this.f4898b.v0(z6);
        this.f4898b.F0();
    }

    private boolean f() {
        return (this.f4899c == null || this.f4898b.getEditText() == null) ? false : true;
    }

    private void h(List<Animator> list, boolean z6, TextView textView, int i7, int i8, int i9) {
        if (textView == null || !z6) {
            return;
        }
        if (i7 == i9 || i7 == i8) {
            list.add(i(textView, i9 == i7));
            if (i9 == i7) {
                list.add(j(textView));
            }
        }
    }

    private ObjectAnimator i(TextView textView, boolean z6) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.ALPHA, z6 ? 1.0f : 0.0f);
        objectAnimatorOfFloat.setDuration(167L);
        objectAnimatorOfFloat.setInterpolator(p2.a.f8820a);
        return objectAnimatorOfFloat;
    }

    private ObjectAnimator j(TextView textView) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.TRANSLATION_Y, -this.f4903g, 0.0f);
        objectAnimatorOfFloat.setDuration(217L);
        objectAnimatorOfFloat.setInterpolator(p2.a.f8823d);
        return objectAnimatorOfFloat;
    }

    private TextView l(int i7) {
        if (i7 == 1) {
            return this.f4908l;
        }
        if (i7 != 2) {
            return null;
        }
        return this.f4914r;
    }

    private int s(boolean z6, int i7, int i8) {
        return z6 ? this.f4897a.getResources().getDimensionPixelSize(i7) : i8;
    }

    private boolean v(int i7) {
        return (i7 != 1 || this.f4908l == null || TextUtils.isEmpty(this.f4906j)) ? false : true;
    }

    void B(CharSequence charSequence) {
        this.f4909m = charSequence;
        TextView textView = this.f4908l;
        if (textView != null) {
            textView.setContentDescription(charSequence);
        }
    }

    void C(boolean z6) {
        if (this.f4907k == z6) {
            return;
        }
        g();
        if (z6) {
            w wVar = new w(this.f4897a);
            this.f4908l = wVar;
            wVar.setId(o2.f.textinput_error);
            this.f4908l.setTextAlignment(5);
            Typeface typeface = this.f4917u;
            if (typeface != null) {
                this.f4908l.setTypeface(typeface);
            }
            D(this.f4910n);
            E(this.f4911o);
            B(this.f4909m);
            this.f4908l.setVisibility(4);
            u.m0(this.f4908l, 1);
            d(this.f4908l, 0);
        } else {
            t();
            z(this.f4908l, 0);
            this.f4908l = null;
            this.f4898b.s0();
            this.f4898b.F0();
        }
        this.f4907k = z6;
    }

    void D(int i7) {
        this.f4910n = i7;
        TextView textView = this.f4908l;
        if (textView != null) {
            this.f4898b.e0(textView, i7);
        }
    }

    void E(ColorStateList colorStateList) {
        this.f4911o = colorStateList;
        TextView textView = this.f4908l;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    void F(int i7) {
        this.f4915s = i7;
        TextView textView = this.f4914r;
        if (textView != null) {
            androidx.core.widget.i.n(textView, i7);
        }
    }

    void G(boolean z6) {
        if (this.f4913q == z6) {
            return;
        }
        g();
        if (z6) {
            w wVar = new w(this.f4897a);
            this.f4914r = wVar;
            wVar.setId(o2.f.textinput_helper_text);
            this.f4914r.setTextAlignment(5);
            Typeface typeface = this.f4917u;
            if (typeface != null) {
                this.f4914r.setTypeface(typeface);
            }
            this.f4914r.setVisibility(4);
            u.m0(this.f4914r, 1);
            F(this.f4915s);
            H(this.f4916t);
            d(this.f4914r, 1);
        } else {
            u();
            z(this.f4914r, 1);
            this.f4914r = null;
            this.f4898b.s0();
            this.f4898b.F0();
        }
        this.f4913q = z6;
    }

    void H(ColorStateList colorStateList) {
        this.f4916t = colorStateList;
        TextView textView = this.f4914r;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    void J(Typeface typeface) {
        if (typeface != this.f4917u) {
            this.f4917u = typeface;
            I(this.f4908l, typeface);
            I(this.f4914r, typeface);
        }
    }

    void M(CharSequence charSequence) {
        g();
        this.f4906j = charSequence;
        this.f4908l.setText(charSequence);
        int i7 = this.f4904h;
        if (i7 != 1) {
            this.f4905i = 1;
        }
        O(i7, this.f4905i, L(this.f4908l, charSequence));
    }

    void N(CharSequence charSequence) {
        g();
        this.f4912p = charSequence;
        this.f4914r.setText(charSequence);
        int i7 = this.f4904h;
        if (i7 != 2) {
            this.f4905i = 2;
        }
        O(i7, this.f4905i, L(this.f4914r, charSequence));
    }

    void d(TextView textView, int i7) {
        if (this.f4899c == null && this.f4901e == null) {
            LinearLayout linearLayout = new LinearLayout(this.f4897a);
            this.f4899c = linearLayout;
            linearLayout.setOrientation(0);
            this.f4898b.addView(this.f4899c, -1, -2);
            this.f4901e = new FrameLayout(this.f4897a);
            this.f4899c.addView(this.f4901e, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (this.f4898b.getEditText() != null) {
                e();
            }
        }
        if (w(i7)) {
            this.f4901e.setVisibility(0);
            this.f4901e.addView(textView);
        } else {
            this.f4899c.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.f4899c.setVisibility(0);
        this.f4900d++;
    }

    void e() {
        if (f()) {
            EditText editText = this.f4898b.getEditText();
            boolean zG = b3.c.g(this.f4897a);
            LinearLayout linearLayout = this.f4899c;
            int i7 = o2.d.material_helper_text_font_1_3_padding_horizontal;
            u.w0(linearLayout, s(zG, i7, u.E(editText)), s(zG, o2.d.material_helper_text_font_1_3_padding_top, this.f4897a.getResources().getDimensionPixelSize(o2.d.material_helper_text_default_padding_top)), s(zG, i7, u.D(editText)), 0);
        }
    }

    void g() {
        Animator animator = this.f4902f;
        if (animator != null) {
            animator.cancel();
        }
    }

    boolean k() {
        return v(this.f4905i);
    }

    CharSequence m() {
        return this.f4909m;
    }

    CharSequence n() {
        return this.f4906j;
    }

    int o() {
        TextView textView = this.f4908l;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    ColorStateList p() {
        TextView textView = this.f4908l;
        if (textView != null) {
            return textView.getTextColors();
        }
        return null;
    }

    CharSequence q() {
        return this.f4912p;
    }

    int r() {
        TextView textView = this.f4914r;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    void t() {
        this.f4906j = null;
        g();
        if (this.f4904h == 1) {
            if (!this.f4913q || TextUtils.isEmpty(this.f4912p)) {
                this.f4905i = 0;
            } else {
                this.f4905i = 2;
            }
        }
        O(this.f4904h, this.f4905i, L(this.f4908l, null));
    }

    void u() {
        g();
        int i7 = this.f4904h;
        if (i7 == 2) {
            this.f4905i = 0;
        }
        O(i7, this.f4905i, L(this.f4914r, null));
    }

    boolean w(int i7) {
        return i7 == 0 || i7 == 1;
    }

    boolean x() {
        return this.f4907k;
    }

    boolean y() {
        return this.f4913q;
    }

    void z(TextView textView, int i7) {
        FrameLayout frameLayout;
        if (this.f4899c == null) {
            return;
        }
        if (!w(i7) || (frameLayout = this.f4901e) == null) {
            this.f4899c.removeView(textView);
        } else {
            frameLayout.removeView(textView);
        }
        int i8 = this.f4900d - 1;
        this.f4900d = i8;
        K(this.f4899c, i8);
    }
}
