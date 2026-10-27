package b.d.a.a.h0;

import a.b.k.l;
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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1930a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextInputLayout f1931b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LinearLayout f1932c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1933d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public FrameLayout f1934e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Animator f1935f;
    public final float g;
    public int h;
    public int i;
    public CharSequence j;
    public boolean k;
    public TextView l;
    public CharSequence m;
    public int n;
    public ColorStateList o;
    public CharSequence p;
    public boolean q;
    public TextView r;
    public int s;
    public ColorStateList t;
    public Typeface u;

    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f1936a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ TextView f1937b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f1938c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ TextView f1939d;

        public a(int i, TextView textView, int i2, TextView textView2) {
            this.f1936a = i;
            this.f1937b = textView;
            this.f1938c = i2;
            this.f1939d = textView2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            TextView textView;
            n nVar = n.this;
            nVar.h = this.f1936a;
            nVar.f1935f = null;
            TextView textView2 = this.f1937b;
            if (textView2 != null) {
                textView2.setVisibility(4);
                if (this.f1938c == 1 && (textView = n.this.l) != null) {
                    textView.setText((CharSequence) null);
                }
            }
            TextView textView3 = this.f1939d;
            if (textView3 != null) {
                textView3.setTranslationY(0.0f);
                this.f1939d.setAlpha(1.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            TextView textView = this.f1939d;
            if (textView != null) {
                textView.setVisibility(0);
            }
        }
    }

    public n(TextInputLayout textInputLayout) {
        this.f1930a = textInputLayout.getContext();
        this.f1931b = textInputLayout;
        this.g = r0.getResources().getDimensionPixelSize(b.d.a.a.d.design_textinput_caption_translate_y);
    }

    public void a(TextView textView, int i) {
        if (this.f1932c == null && this.f1934e == null) {
            LinearLayout linearLayout = new LinearLayout(this.f1930a);
            this.f1932c = linearLayout;
            linearLayout.setOrientation(0);
            this.f1931b.addView(this.f1932c, -1, -2);
            this.f1934e = new FrameLayout(this.f1930a);
            this.f1932c.addView(this.f1934e, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (this.f1931b.getEditText() != null) {
                b();
            }
        }
        if (i == 0 || i == 1) {
            this.f1934e.setVisibility(0);
            this.f1934e.addView(textView);
        } else {
            this.f1932c.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.f1932c.setVisibility(0);
        this.f1933d++;
    }

    public void b() {
        if ((this.f1932c == null || this.f1931b.getEditText() == null) ? false : true) {
            EditText editText = this.f1931b.getEditText();
            boolean zK = l.i.K(this.f1930a);
            this.f1932c.setPaddingRelative(h(zK, b.d.a.a.d.material_helper_text_font_1_3_padding_horizontal, a.h.k.q.t(editText)), h(zK, b.d.a.a.d.material_helper_text_font_1_3_padding_top, this.f1930a.getResources().getDimensionPixelSize(b.d.a.a.d.material_helper_text_default_padding_top)), h(zK, b.d.a.a.d.material_helper_text_font_1_3_padding_horizontal, editText.getPaddingEnd()), 0);
        }
    }

    public void c() {
        Animator animator = this.f1935f;
        if (animator != null) {
            animator.cancel();
        }
    }

    public final void d(List<Animator> list, boolean z, TextView textView, int i, int i2, int i3) {
        if (textView == null || !z) {
            return;
        }
        if (i == i3 || i == i2) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.ALPHA, i3 == i ? 1.0f : 0.0f);
            objectAnimatorOfFloat.setDuration(167L);
            objectAnimatorOfFloat.setInterpolator(b.d.a.a.l.a.f1966a);
            list.add(objectAnimatorOfFloat);
            if (i3 == i) {
                ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.TRANSLATION_Y, -this.g, 0.0f);
                objectAnimatorOfFloat2.setDuration(217L);
                objectAnimatorOfFloat2.setInterpolator(b.d.a.a.l.a.f1969d);
                list.add(objectAnimatorOfFloat2);
            }
        }
    }

    public boolean e() {
        return (this.i != 1 || this.l == null || TextUtils.isEmpty(this.j)) ? false : true;
    }

    public final TextView f(int i) {
        if (i == 1) {
            return this.l;
        }
        if (i != 2) {
            return null;
        }
        return this.r;
    }

    public int g() {
        TextView textView = this.l;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public final int h(boolean z, int i, int i2) {
        return z ? this.f1930a.getResources().getDimensionPixelSize(i) : i2;
    }

    public void i() {
        this.j = null;
        c();
        if (this.h == 1) {
            this.i = (!this.q || TextUtils.isEmpty(this.p)) ? 0 : 2;
        }
        l(this.h, this.i, k(this.l, null));
    }

    public void j(TextView textView, int i) {
        FrameLayout frameLayout;
        if (this.f1932c == null) {
            return;
        }
        if (!(i == 0 || i == 1) || (frameLayout = this.f1934e) == null) {
            this.f1932c.removeView(textView);
        } else {
            frameLayout.removeView(textView);
        }
        int i2 = this.f1933d - 1;
        this.f1933d = i2;
        LinearLayout linearLayout = this.f1932c;
        if (i2 == 0) {
            linearLayout.setVisibility(8);
        }
    }

    public final boolean k(TextView textView, CharSequence charSequence) {
        return a.h.k.q.C(this.f1931b) && this.f1931b.isEnabled() && !(this.i == this.h && textView != null && TextUtils.equals(textView.getText(), charSequence));
    }

    public final void l(int i, int i2, boolean z) {
        TextView textViewF;
        TextView textViewF2;
        if (i == i2) {
            return;
        }
        if (z) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.f1935f = animatorSet;
            ArrayList arrayList = new ArrayList();
            d(arrayList, this.q, this.r, 2, i, i2);
            d(arrayList, this.k, this.l, 1, i, i2);
            l.i.f0(animatorSet, arrayList);
            animatorSet.addListener(new a(i2, f(i), i, f(i2)));
            animatorSet.start();
        } else if (i != i2) {
            if (i2 != 0 && (textViewF2 = f(i2)) != null) {
                textViewF2.setVisibility(0);
                textViewF2.setAlpha(1.0f);
            }
            if (i != 0 && (textViewF = f(i)) != null) {
                textViewF.setVisibility(4);
                if (i == 1) {
                    textViewF.setText((CharSequence) null);
                }
            }
            this.h = i2;
        }
        this.f1931b.w();
        this.f1931b.y(z, false);
        this.f1931b.F();
    }
}
