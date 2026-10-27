package q2;

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
import l0.t;
import n0.i;

/* JADX INFO: compiled from: IndicatorViewController.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4608a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextInputLayout f4609b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LinearLayout f4610c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4611d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public FrameLayout f4612e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4613f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Animator f4614g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f4615h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4616i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4617j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public CharSequence f4618k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f4619l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public TextView f4620m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public CharSequence f4621n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f4622o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ColorStateList f4623p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public CharSequence f4624q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f4625r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public TextView f4626s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f4627t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ColorStateList f4628u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Typeface f4629v;

    /* JADX INFO: compiled from: IndicatorViewController.java */
    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f4630a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ TextView f4631b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f4632c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ TextView f4633d;

        public a(int i3, TextView textView, int i4, TextView textView2) {
            this.f4630a = i3;
            this.f4631b = textView;
            this.f4632c = i4;
            this.f4633d = textView2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f.this.f4616i = this.f4630a;
            f.this.f4614g = null;
            TextView textView = this.f4631b;
            if (textView != null) {
                textView.setVisibility(4);
                if (this.f4632c == 1 && f.this.f4620m != null) {
                    f.this.f4620m.setText((CharSequence) null);
                }
            }
            TextView textView2 = this.f4633d;
            if (textView2 != null) {
                textView2.setTranslationY(0.0f);
                this.f4633d.setAlpha(1.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            TextView textView = this.f4633d;
            if (textView != null) {
                textView.setVisibility(0);
            }
        }
    }

    public f(TextInputLayout textInputLayout) {
        this.f4608a = textInputLayout.getContext();
        this.f4609b = textInputLayout;
        this.f4615h = r0.getResources().getDimensionPixelSize(R$dimen.design_textinput_caption_translate_y);
    }

    public void A(CharSequence charSequence) {
        this.f4621n = charSequence;
        TextView textView = this.f4620m;
        if (textView != null) {
            textView.setContentDescription(charSequence);
        }
    }

    public void B(boolean z3) {
        if (this.f4619l == z3) {
            return;
        }
        g();
        if (z3) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f4608a);
            this.f4620m = appCompatTextView;
            appCompatTextView.setId(R$id.textinput_error);
            if (Build.VERSION.SDK_INT >= 17) {
                this.f4620m.setTextAlignment(5);
            }
            Typeface typeface = this.f4629v;
            if (typeface != null) {
                this.f4620m.setTypeface(typeface);
            }
            C(this.f4622o);
            D(this.f4623p);
            A(this.f4621n);
            this.f4620m.setVisibility(4);
            t.l0(this.f4620m, 1);
            d(this.f4620m, 0);
        } else {
            s();
            y(this.f4620m, 0);
            this.f4620m = null;
            this.f4609b.k0();
            this.f4609b.y0();
        }
        this.f4619l = z3;
    }

    public void C(int i3) {
        this.f4622o = i3;
        TextView textView = this.f4620m;
        if (textView != null) {
            this.f4609b.Y(textView, i3);
        }
    }

    public void D(ColorStateList colorStateList) {
        this.f4623p = colorStateList;
        TextView textView = this.f4620m;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    public void E(int i3) {
        this.f4627t = i3;
        TextView textView = this.f4626s;
        if (textView != null) {
            i.q(textView, i3);
        }
    }

    public void F(boolean z3) {
        if (this.f4625r == z3) {
            return;
        }
        g();
        if (z3) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f4608a);
            this.f4626s = appCompatTextView;
            appCompatTextView.setId(R$id.textinput_helper_text);
            if (Build.VERSION.SDK_INT >= 17) {
                this.f4626s.setTextAlignment(5);
            }
            Typeface typeface = this.f4629v;
            if (typeface != null) {
                this.f4626s.setTypeface(typeface);
            }
            this.f4626s.setVisibility(4);
            t.l0(this.f4626s, 1);
            E(this.f4627t);
            G(this.f4628u);
            d(this.f4626s, 1);
        } else {
            t();
            y(this.f4626s, 1);
            this.f4626s = null;
            this.f4609b.k0();
            this.f4609b.y0();
        }
        this.f4625r = z3;
    }

    public void G(ColorStateList colorStateList) {
        this.f4628u = colorStateList;
        TextView textView = this.f4626s;
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
        if (typeface != this.f4629v) {
            this.f4629v = typeface;
            H(this.f4620m, typeface);
            H(this.f4626s, typeface);
        }
    }

    public final void J(ViewGroup viewGroup, int i3) {
        if (i3 == 0) {
            viewGroup.setVisibility(8);
        }
    }

    public final boolean K(TextView textView, CharSequence charSequence) {
        return t.P(this.f4609b) && this.f4609b.isEnabled() && !(this.f4617j == this.f4616i && textView != null && TextUtils.equals(textView.getText(), charSequence));
    }

    public void L(CharSequence charSequence) {
        g();
        this.f4618k = charSequence;
        this.f4620m.setText(charSequence);
        int i3 = this.f4616i;
        if (i3 != 1) {
            this.f4617j = 1;
        }
        N(i3, this.f4617j, K(this.f4620m, charSequence));
    }

    public void M(CharSequence charSequence) {
        g();
        this.f4624q = charSequence;
        this.f4626s.setText(charSequence);
        int i3 = this.f4616i;
        if (i3 != 2) {
            this.f4617j = 2;
        }
        N(i3, this.f4617j, K(this.f4626s, charSequence));
    }

    public final void N(int i3, int i4, boolean z3) {
        if (i3 == i4) {
            return;
        }
        if (z3) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.f4614g = animatorSet;
            ArrayList arrayList = new ArrayList();
            h(arrayList, this.f4625r, this.f4626s, 2, i3, i4);
            h(arrayList, this.f4619l, this.f4620m, 1, i3, i4);
            s1.b.a(animatorSet, arrayList);
            animatorSet.addListener(new a(i4, l(i3), i3, l(i4)));
            animatorSet.start();
        } else {
            z(i3, i4);
        }
        this.f4609b.k0();
        this.f4609b.o0(z3);
        this.f4609b.y0();
    }

    public void d(TextView textView, int i3) {
        if (this.f4610c == null && this.f4612e == null) {
            LinearLayout linearLayout = new LinearLayout(this.f4608a);
            this.f4610c = linearLayout;
            linearLayout.setOrientation(0);
            this.f4609b.addView(this.f4610c, -1, -2);
            this.f4612e = new FrameLayout(this.f4608a);
            this.f4610c.addView(this.f4612e, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (this.f4609b.getEditText() != null) {
                e();
            }
        }
        if (v(i3)) {
            this.f4612e.setVisibility(0);
            this.f4612e.addView(textView);
            this.f4613f++;
        } else {
            this.f4610c.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.f4610c.setVisibility(0);
        this.f4611d++;
    }

    public void e() {
        if (f()) {
            t.w0(this.f4610c, t.E(this.f4609b.getEditText()), 0, t.D(this.f4609b.getEditText()), 0);
        }
    }

    public final boolean f() {
        return (this.f4610c == null || this.f4609b.getEditText() == null) ? false : true;
    }

    public void g() {
        Animator animator = this.f4614g;
        if (animator != null) {
            animator.cancel();
        }
    }

    public final void h(List<Animator> list, boolean z3, TextView textView, int i3, int i4, int i5) {
        if (textView == null || !z3) {
            return;
        }
        if (i3 == i5 || i3 == i4) {
            list.add(i(textView, i5 == i3));
            if (i5 == i3) {
                list.add(j(textView));
            }
        }
    }

    public final ObjectAnimator i(TextView textView, boolean z3) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.ALPHA, z3 ? 1.0f : 0.0f);
        objectAnimatorOfFloat.setDuration(167L);
        objectAnimatorOfFloat.setInterpolator(s1.a.f4901a);
        return objectAnimatorOfFloat;
    }

    public final ObjectAnimator j(TextView textView) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.TRANSLATION_Y, -this.f4615h, 0.0f);
        objectAnimatorOfFloat.setDuration(217L);
        objectAnimatorOfFloat.setInterpolator(s1.a.f4904d);
        return objectAnimatorOfFloat;
    }

    public boolean k() {
        return u(this.f4617j);
    }

    public final TextView l(int i3) {
        if (i3 == 1) {
            return this.f4620m;
        }
        if (i3 != 2) {
            return null;
        }
        return this.f4626s;
    }

    public CharSequence m() {
        return this.f4621n;
    }

    public CharSequence n() {
        return this.f4618k;
    }

    public int o() {
        TextView textView = this.f4620m;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public ColorStateList p() {
        TextView textView = this.f4620m;
        if (textView != null) {
            return textView.getTextColors();
        }
        return null;
    }

    public CharSequence q() {
        return this.f4624q;
    }

    public int r() {
        TextView textView = this.f4626s;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public void s() {
        this.f4618k = null;
        g();
        if (this.f4616i == 1) {
            if (!this.f4625r || TextUtils.isEmpty(this.f4624q)) {
                this.f4617j = 0;
            } else {
                this.f4617j = 2;
            }
        }
        N(this.f4616i, this.f4617j, K(this.f4620m, null));
    }

    public void t() {
        g();
        int i3 = this.f4616i;
        if (i3 == 2) {
            this.f4617j = 0;
        }
        N(i3, this.f4617j, K(this.f4626s, null));
    }

    public final boolean u(int i3) {
        return (i3 != 1 || this.f4620m == null || TextUtils.isEmpty(this.f4618k)) ? false : true;
    }

    public boolean v(int i3) {
        return i3 == 0 || i3 == 1;
    }

    public boolean w() {
        return this.f4619l;
    }

    public boolean x() {
        return this.f4625r;
    }

    public void y(TextView textView, int i3) {
        FrameLayout frameLayout;
        if (this.f4610c == null) {
            return;
        }
        if (!v(i3) || (frameLayout = this.f4612e) == null) {
            this.f4610c.removeView(textView);
        } else {
            int i4 = this.f4613f - 1;
            this.f4613f = i4;
            J(frameLayout, i4);
            this.f4612e.removeView(textView);
        }
        int i5 = this.f4611d - 1;
        this.f4611d = i5;
        J(this.f4610c, i5);
    }

    public final void z(int i3, int i4) {
        TextView textViewL;
        TextView textViewL2;
        if (i3 == i4) {
            return;
        }
        if (i4 != 0 && (textViewL2 = l(i4)) != null) {
            textViewL2.setVisibility(0);
            textViewL2.setAlpha(1.0f);
        }
        if (i3 != 0 && (textViewL = l(i3)) != null) {
            textViewL.setVisibility(4);
            if (i3 == 1) {
                textViewL.setText((CharSequence) null);
            }
        }
        this.f4616i = i4;
    }
}
