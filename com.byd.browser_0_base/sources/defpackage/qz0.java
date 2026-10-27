package defpackage;

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
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.R$dimen;
import com.google.android.material.textfield.TextInputLayout;
import defpackage.om3;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: IndicatorViewController.java */
/* JADX INFO: loaded from: classes.dex */
public final class qz0 {
    public final Context a;
    public final TextInputLayout b;
    public LinearLayout c;
    public int d;
    public FrameLayout e;
    public Animator f;
    public final float g;
    public int h;
    public int i;
    public CharSequence j;
    public boolean k;
    public AppCompatTextView l;
    public CharSequence m;
    public int n;
    public ColorStateList o;
    public CharSequence p;
    public boolean q;
    public AppCompatTextView r;
    public int s;
    public ColorStateList t;
    public Typeface u;

    /* JADX INFO: compiled from: IndicatorViewController.java */
    public class a extends AnimatorListenerAdapter {
        public final /* synthetic */ int e;
        public final /* synthetic */ TextView f;
        public final /* synthetic */ int g;
        public final /* synthetic */ TextView h;

        public a(int i, TextView textView, int i2, TextView textView2) {
            this.e = i;
            this.f = textView;
            this.g = i2;
            this.h = textView2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            AppCompatTextView appCompatTextView;
            int i = this.e;
            qz0 qz0Var = qz0.this;
            qz0Var.h = i;
            qz0Var.f = null;
            TextView textView = this.f;
            if (textView != null) {
                textView.setVisibility(4);
                if (this.g == 1 && (appCompatTextView = qz0Var.l) != null) {
                    appCompatTextView.setText((CharSequence) null);
                }
            }
            TextView textView2 = this.h;
            if (textView2 != null) {
                textView2.setTranslationY(0.0f);
                textView2.setAlpha(1.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            TextView textView = this.h;
            if (textView != null) {
                textView.setVisibility(0);
            }
        }
    }

    public qz0(TextInputLayout textInputLayout) {
        this.a = textInputLayout.getContext();
        this.b = textInputLayout;
        this.g = r0.getResources().getDimensionPixelSize(R$dimen.design_textinput_caption_translate_y);
    }

    public final void a(TextView textView, int i) {
        if (this.c == null && this.e == null) {
            Context context = this.a;
            LinearLayout linearLayout = new LinearLayout(context);
            this.c = linearLayout;
            linearLayout.setOrientation(0);
            LinearLayout linearLayout2 = this.c;
            TextInputLayout textInputLayout = this.b;
            textInputLayout.addView(linearLayout2, -1, -2);
            this.e = new FrameLayout(context);
            this.c.addView(this.e, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (textInputLayout.getEditText() != null) {
                b();
            }
        }
        if (i == 0 || i == 1) {
            this.e.setVisibility(0);
            this.e.addView(textView);
        } else {
            this.c.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.c.setVisibility(0);
        this.d++;
    }

    public final void b() {
        LinearLayout linearLayout = this.c;
        TextInputLayout textInputLayout = this.b;
        if ((linearLayout == null || textInputLayout.getEditText() == null) ? false : true) {
            EditText editText = textInputLayout.getEditText();
            Context context = this.a;
            boolean zE = rc1.e(context);
            LinearLayout linearLayout2 = this.c;
            int i = R$dimen.material_helper_text_font_1_3_padding_horizontal;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            int iE = om3.e.e(editText);
            if (zE) {
                iE = context.getResources().getDimensionPixelSize(i);
            }
            int i2 = R$dimen.material_helper_text_font_1_3_padding_top;
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.material_helper_text_default_padding_top);
            if (zE) {
                dimensionPixelSize = context.getResources().getDimensionPixelSize(i2);
            }
            int iD = om3.e.d(editText);
            if (zE) {
                iD = context.getResources().getDimensionPixelSize(i);
            }
            om3.e.i(linearLayout2, iE, dimensionPixelSize, iD, 0);
        }
    }

    public final void c() {
        Animator animator = this.f;
        if (animator != null) {
            animator.cancel();
        }
    }

    public final void d(ArrayList arrayList, boolean z, TextView textView, int i, int i2, int i3) {
        if (textView == null || !z) {
            return;
        }
        if (i == i3 || i == i2) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.ALPHA, i3 == i ? 1.0f : 0.0f);
            objectAnimatorOfFloat.setDuration(167L);
            objectAnimatorOfFloat.setInterpolator(u5.a);
            arrayList.add(objectAnimatorOfFloat);
            if (i3 == i) {
                ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.TRANSLATION_Y, -this.g, 0.0f);
                objectAnimatorOfFloat2.setDuration(217L);
                objectAnimatorOfFloat2.setInterpolator(u5.d);
                arrayList.add(objectAnimatorOfFloat2);
            }
        }
    }

    public final TextView e(int i) {
        if (i == 1) {
            return this.l;
        }
        if (i != 2) {
            return null;
        }
        return this.r;
    }

    public final void f() {
        this.j = null;
        c();
        if (this.h == 1) {
            if (!this.q || TextUtils.isEmpty(this.p)) {
                this.i = 0;
            } else {
                this.i = 2;
            }
        }
        i(this.h, this.i, h(this.l, ""));
    }

    public final void g(TextView textView, int i) {
        FrameLayout frameLayout;
        LinearLayout linearLayout = this.c;
        if (linearLayout == null) {
            return;
        }
        boolean z = true;
        if (i != 0 && i != 1) {
            z = false;
        }
        if (!z || (frameLayout = this.e) == null) {
            linearLayout.removeView(textView);
        } else {
            frameLayout.removeView(textView);
        }
        int i2 = this.d - 1;
        this.d = i2;
        LinearLayout linearLayout2 = this.c;
        if (i2 == 0) {
            linearLayout2.setVisibility(8);
        }
    }

    public final boolean h(TextView textView, CharSequence charSequence) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        TextInputLayout textInputLayout = this.b;
        return om3.g.c(textInputLayout) && textInputLayout.isEnabled() && !(this.i == this.h && textView != null && TextUtils.equals(textView.getText(), charSequence));
    }

    public final void i(int i, int i2, boolean z) {
        TextView textViewE;
        TextView textViewE2;
        if (i == i2) {
            return;
        }
        if (z) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.f = animatorSet;
            ArrayList arrayList = new ArrayList();
            d(arrayList, this.q, this.r, 2, i, i2);
            d(arrayList, this.k, this.l, 1, i, i2);
            a8.a0(animatorSet, arrayList);
            animatorSet.addListener(new a(i2, e(i), i, e(i2)));
            animatorSet.start();
        } else if (i != i2) {
            if (i2 != 0 && (textViewE2 = e(i2)) != null) {
                textViewE2.setVisibility(0);
                textViewE2.setAlpha(1.0f);
            }
            if (i != 0 && (textViewE = e(i)) != null) {
                textViewE.setVisibility(4);
                if (i == 1) {
                    textViewE.setText((CharSequence) null);
                }
            }
            this.h = i2;
        }
        TextInputLayout textInputLayout = this.b;
        textInputLayout.q();
        textInputLayout.t(z);
        textInputLayout.x();
    }
}
