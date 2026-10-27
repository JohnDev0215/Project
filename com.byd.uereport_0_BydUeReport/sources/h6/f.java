package h6;

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
import o0.t;
import r0.i;

/* JADX INFO: compiled from: IndicatorViewController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f5410a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextInputLayout f5411b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LinearLayout f5412c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5413d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public FrameLayout f5414e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5415f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Animator f5416g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f5417h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5418i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f5419j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public CharSequence f5420k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f5421l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public TextView f5422m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public CharSequence f5423n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f5424o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ColorStateList f5425p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public CharSequence f5426q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f5427r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public TextView f5428s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f5429t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ColorStateList f5430u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Typeface f5431v;

    /* JADX INFO: compiled from: IndicatorViewController.java */
    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f5432d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ TextView f5433e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ int f5434f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ TextView f5435g;

        public a(int i8, TextView textView, int i9, TextView textView2) {
            this.f5432d = i8;
            this.f5433e = textView;
            this.f5434f = i9;
            this.f5435g = textView2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f.this.f5418i = this.f5432d;
            f.this.f5416g = null;
            TextView textView = this.f5433e;
            if (textView != null) {
                textView.setVisibility(4);
                if (this.f5434f == 1 && f.this.f5422m != null) {
                    f.this.f5422m.setText((CharSequence) null);
                }
            }
            TextView textView2 = this.f5435g;
            if (textView2 != null) {
                textView2.setTranslationY(0.0f);
                this.f5435g.setAlpha(1.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            TextView textView = this.f5435g;
            if (textView != null) {
                textView.setVisibility(0);
            }
        }
    }

    public f(TextInputLayout textInputLayout) {
        this.f5410a = textInputLayout.getContext();
        this.f5411b = textInputLayout;
        this.f5417h = r0.getResources().getDimensionPixelSize(R$dimen.design_textinput_caption_translate_y);
    }

    public void A(CharSequence charSequence) {
        this.f5423n = charSequence;
        TextView textView = this.f5422m;
        if (textView != null) {
            textView.setContentDescription(charSequence);
        }
    }

    public void B(boolean z7) {
        if (this.f5421l == z7) {
            return;
        }
        g();
        if (z7) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f5410a);
            this.f5422m = appCompatTextView;
            appCompatTextView.setId(R$id.textinput_error);
            if (Build.VERSION.SDK_INT >= 17) {
                this.f5422m.setTextAlignment(5);
            }
            Typeface typeface = this.f5431v;
            if (typeface != null) {
                this.f5422m.setTypeface(typeface);
            }
            C(this.f5424o);
            D(this.f5425p);
            A(this.f5423n);
            this.f5422m.setVisibility(4);
            t.n0(this.f5422m, 1);
            d(this.f5422m, 0);
        } else {
            s();
            y(this.f5422m, 0);
            this.f5422m = null;
            this.f5411b.k0();
            this.f5411b.y0();
        }
        this.f5421l = z7;
    }

    public void C(int i8) {
        this.f5424o = i8;
        TextView textView = this.f5422m;
        if (textView != null) {
            this.f5411b.Y(textView, i8);
        }
    }

    public void D(ColorStateList colorStateList) {
        this.f5425p = colorStateList;
        TextView textView = this.f5422m;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    public void E(int i8) {
        this.f5429t = i8;
        TextView textView = this.f5428s;
        if (textView != null) {
            i.q(textView, i8);
        }
    }

    public void F(boolean z7) {
        if (this.f5427r == z7) {
            return;
        }
        g();
        if (z7) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f5410a);
            this.f5428s = appCompatTextView;
            appCompatTextView.setId(R$id.textinput_helper_text);
            if (Build.VERSION.SDK_INT >= 17) {
                this.f5428s.setTextAlignment(5);
            }
            Typeface typeface = this.f5431v;
            if (typeface != null) {
                this.f5428s.setTypeface(typeface);
            }
            this.f5428s.setVisibility(4);
            t.n0(this.f5428s, 1);
            E(this.f5429t);
            G(this.f5430u);
            d(this.f5428s, 1);
        } else {
            t();
            y(this.f5428s, 1);
            this.f5428s = null;
            this.f5411b.k0();
            this.f5411b.y0();
        }
        this.f5427r = z7;
    }

    public void G(ColorStateList colorStateList) {
        this.f5430u = colorStateList;
        TextView textView = this.f5428s;
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
        if (typeface != this.f5431v) {
            this.f5431v = typeface;
            H(this.f5422m, typeface);
            H(this.f5428s, typeface);
        }
    }

    public final void J(ViewGroup viewGroup, int i8) {
        if (i8 == 0) {
            viewGroup.setVisibility(8);
        }
    }

    public final boolean K(TextView textView, CharSequence charSequence) {
        return t.Q(this.f5411b) && this.f5411b.isEnabled() && !(this.f5419j == this.f5418i && textView != null && TextUtils.equals(textView.getText(), charSequence));
    }

    public void L(CharSequence charSequence) {
        g();
        this.f5420k = charSequence;
        this.f5422m.setText(charSequence);
        int i8 = this.f5418i;
        if (i8 != 1) {
            this.f5419j = 1;
        }
        N(i8, this.f5419j, K(this.f5422m, charSequence));
    }

    public void M(CharSequence charSequence) {
        g();
        this.f5426q = charSequence;
        this.f5428s.setText(charSequence);
        int i8 = this.f5418i;
        if (i8 != 2) {
            this.f5419j = 2;
        }
        N(i8, this.f5419j, K(this.f5428s, charSequence));
    }

    public final void N(int i8, int i9, boolean z7) {
        if (i8 == i9) {
            return;
        }
        if (z7) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.f5416g = animatorSet;
            ArrayList arrayList = new ArrayList();
            h(arrayList, this.f5427r, this.f5428s, 2, i8, i9);
            h(arrayList, this.f5421l, this.f5422m, 1, i8, i9);
            j5.b.a(animatorSet, arrayList);
            animatorSet.addListener(new a(i9, l(i8), i8, l(i9)));
            animatorSet.start();
        } else {
            z(i8, i9);
        }
        this.f5411b.k0();
        this.f5411b.o0(z7);
        this.f5411b.y0();
    }

    public void d(TextView textView, int i8) {
        if (this.f5412c == null && this.f5414e == null) {
            LinearLayout linearLayout = new LinearLayout(this.f5410a);
            this.f5412c = linearLayout;
            linearLayout.setOrientation(0);
            this.f5411b.addView(this.f5412c, -1, -2);
            this.f5414e = new FrameLayout(this.f5410a);
            this.f5412c.addView(this.f5414e, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (this.f5411b.getEditText() != null) {
                e();
            }
        }
        if (v(i8)) {
            this.f5414e.setVisibility(0);
            this.f5414e.addView(textView);
            this.f5415f++;
        } else {
            this.f5412c.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.f5412c.setVisibility(0);
        this.f5413d++;
    }

    public void e() {
        if (f()) {
            t.z0(this.f5412c, t.F(this.f5411b.getEditText()), 0, t.E(this.f5411b.getEditText()), 0);
        }
    }

    public final boolean f() {
        return (this.f5412c == null || this.f5411b.getEditText() == null) ? false : true;
    }

    public void g() {
        Animator animator = this.f5416g;
        if (animator != null) {
            animator.cancel();
        }
    }

    public final void h(List<Animator> list, boolean z7, TextView textView, int i8, int i9, int i10) {
        if (textView == null || !z7) {
            return;
        }
        if (i8 == i10 || i8 == i9) {
            list.add(i(textView, i10 == i8));
            if (i10 == i8) {
                list.add(j(textView));
            }
        }
    }

    public final ObjectAnimator i(TextView textView, boolean z7) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.ALPHA, z7 ? 1.0f : 0.0f);
        objectAnimatorOfFloat.setDuration(167L);
        objectAnimatorOfFloat.setInterpolator(j5.a.f5578a);
        return objectAnimatorOfFloat;
    }

    public final ObjectAnimator j(TextView textView) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.TRANSLATION_Y, -this.f5417h, 0.0f);
        objectAnimatorOfFloat.setDuration(217L);
        objectAnimatorOfFloat.setInterpolator(j5.a.f5581d);
        return objectAnimatorOfFloat;
    }

    public boolean k() {
        return u(this.f5419j);
    }

    public final TextView l(int i8) {
        if (i8 == 1) {
            return this.f5422m;
        }
        if (i8 != 2) {
            return null;
        }
        return this.f5428s;
    }

    public CharSequence m() {
        return this.f5423n;
    }

    public CharSequence n() {
        return this.f5420k;
    }

    public int o() {
        TextView textView = this.f5422m;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public ColorStateList p() {
        TextView textView = this.f5422m;
        if (textView != null) {
            return textView.getTextColors();
        }
        return null;
    }

    public CharSequence q() {
        return this.f5426q;
    }

    public int r() {
        TextView textView = this.f5428s;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public void s() {
        this.f5420k = null;
        g();
        if (this.f5418i == 1) {
            if (!this.f5427r || TextUtils.isEmpty(this.f5426q)) {
                this.f5419j = 0;
            } else {
                this.f5419j = 2;
            }
        }
        N(this.f5418i, this.f5419j, K(this.f5422m, null));
    }

    public void t() {
        g();
        int i8 = this.f5418i;
        if (i8 == 2) {
            this.f5419j = 0;
        }
        N(i8, this.f5419j, K(this.f5428s, null));
    }

    public final boolean u(int i8) {
        return (i8 != 1 || this.f5422m == null || TextUtils.isEmpty(this.f5420k)) ? false : true;
    }

    public boolean v(int i8) {
        return i8 == 0 || i8 == 1;
    }

    public boolean w() {
        return this.f5421l;
    }

    public boolean x() {
        return this.f5427r;
    }

    public void y(TextView textView, int i8) {
        FrameLayout frameLayout;
        if (this.f5412c == null) {
            return;
        }
        if (!v(i8) || (frameLayout = this.f5414e) == null) {
            this.f5412c.removeView(textView);
        } else {
            int i9 = this.f5415f - 1;
            this.f5415f = i9;
            J(frameLayout, i9);
            this.f5414e.removeView(textView);
        }
        int i10 = this.f5413d - 1;
        this.f5413d = i10;
        J(this.f5412c, i10);
    }

    public final void z(int i8, int i9) {
        TextView textViewL;
        TextView textViewL2;
        if (i8 == i9) {
            return;
        }
        if (i9 != 0 && (textViewL2 = l(i9)) != null) {
            textViewL2.setVisibility(0);
            textViewL2.setAlpha(1.0f);
        }
        if (i8 != 0 && (textViewL = l(i8)) != null) {
            textViewL.setVisibility(4);
            if (i8 == 1) {
                textViewL.setText((CharSequence) null);
            }
        }
        this.f5418i = i9;
    }
}
