package c.b.a.a.y;

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
import b.g.j.t;
import b.g.k.i;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: IndicatorViewController.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1934a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextInputLayout f1935b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LinearLayout f1936c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1937d;
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

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f1938a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ TextView f1939b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f1940c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ TextView f1941d;

        public a(int i, TextView textView, int i2, TextView textView2) {
            this.f1938a = i;
            this.f1939b = textView;
            this.f1940c = i2;
            this.f1941d = textView2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f.this.i = this.f1938a;
            f.this.g = null;
            TextView textView = this.f1939b;
            if (textView != null) {
                textView.setVisibility(4);
                if (this.f1940c == 1 && f.this.m != null) {
                    f.this.m.setText((CharSequence) null);
                }
            }
            TextView textView2 = this.f1941d;
            if (textView2 != null) {
                textView2.setTranslationY(0.0f);
                this.f1941d.setAlpha(1.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            TextView textView = this.f1941d;
            if (textView != null) {
                textView.setVisibility(0);
            }
        }
    }

    public f(TextInputLayout textInputLayout) {
        this.f1934a = textInputLayout.getContext();
        this.f1935b = textInputLayout;
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
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f1934a);
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
            t.l0(this.m, 1);
            d(this.m, 0);
        } else {
            s();
            y(this.m, 0);
            this.m = null;
            this.f1935b.k0();
            this.f1935b.y0();
        }
        this.l = z;
    }

    public void C(int i) {
        this.o = i;
        TextView textView = this.m;
        if (textView != null) {
            this.f1935b.Y(textView, i);
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
            i.q(textView, i);
        }
    }

    public void F(boolean z) {
        if (this.r == z) {
            return;
        }
        g();
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f1934a);
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
            t.l0(this.s, 1);
            E(this.t);
            G(this.u);
            d(this.s, 1);
        } else {
            t();
            y(this.s, 1);
            this.s = null;
            this.f1935b.k0();
            this.f1935b.y0();
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
        return t.P(this.f1935b) && this.f1935b.isEnabled() && !(this.j == this.i && textView != null && TextUtils.equals(textView.getText(), charSequence));
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
            c.b.a.a.a.b.a(animatorSet, arrayList);
            animatorSet.addListener(new a(i2, l(i), i, l(i2)));
            animatorSet.start();
        } else {
            z(i, i2);
        }
        this.f1935b.k0();
        this.f1935b.o0(z);
        this.f1935b.y0();
    }

    public void d(TextView textView, int i) {
        if (this.f1936c == null && this.e == null) {
            LinearLayout linearLayout = new LinearLayout(this.f1934a);
            this.f1936c = linearLayout;
            linearLayout.setOrientation(0);
            this.f1935b.addView(this.f1936c, -1, -2);
            this.e = new FrameLayout(this.f1934a);
            this.f1936c.addView(this.e, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (this.f1935b.getEditText() != null) {
                e();
            }
        }
        if (v(i)) {
            this.e.setVisibility(0);
            this.e.addView(textView);
            this.f++;
        } else {
            this.f1936c.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.f1936c.setVisibility(0);
        this.f1937d++;
    }

    public void e() {
        if (f()) {
            t.w0(this.f1936c, t.E(this.f1935b.getEditText()), 0, t.D(this.f1935b.getEditText()), 0);
        }
    }

    public final boolean f() {
        return (this.f1936c == null || this.f1935b.getEditText() == null) ? false : true;
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
        objectAnimatorOfFloat.setInterpolator(c.b.a.a.a.a.f1648a);
        return objectAnimatorOfFloat;
    }

    public final ObjectAnimator j(TextView textView) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.TRANSLATION_Y, -this.h, 0.0f);
        objectAnimatorOfFloat.setDuration(217L);
        objectAnimatorOfFloat.setInterpolator(c.b.a.a.a.a.f1651d);
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
        if (this.f1936c == null) {
            return;
        }
        if (!v(i) || (frameLayout = this.e) == null) {
            this.f1936c.removeView(textView);
        } else {
            int i2 = this.f - 1;
            this.f = i2;
            J(frameLayout, i2);
            this.e.removeView(textView);
        }
        int i3 = this.f1937d - 1;
        this.f1937d = i3;
        J(this.f1936c, i3);
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
