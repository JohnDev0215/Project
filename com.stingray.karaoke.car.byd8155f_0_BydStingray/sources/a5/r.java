package a5;

import F1.H;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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
import com.stingray.drivemodedetector.R;
import java.util.ArrayList;
import r.C2218F;

/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f11175a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextInputLayout f11176b;
    public LinearLayout c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public FrameLayout f11177e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public AnimatorSet f11178f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f11179g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f11180h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f11181i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CharSequence f11182j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f11183k;
    public C2218F l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public CharSequence f11184m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f11185n;
    public ColorStateList o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public CharSequence f11186p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f11187q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public C2218F f11188r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f11189s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ColorStateList f11190t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Typeface f11191u;

    public r(TextInputLayout textInputLayout) {
        this.f11175a = textInputLayout.getContext();
        this.f11176b = textInputLayout;
        this.f11179g = r0.getResources().getDimensionPixelSize(R.dimen.design_textinput_caption_translate_y);
    }

    public final void a(C2218F c2218f, int i7) {
        if (this.c == null && this.f11177e == null) {
            Context context = this.f11175a;
            LinearLayout linearLayout = new LinearLayout(context);
            this.c = linearLayout;
            linearLayout.setOrientation(0);
            LinearLayout linearLayout2 = this.c;
            TextInputLayout textInputLayout = this.f11176b;
            textInputLayout.addView(linearLayout2, -1, -2);
            this.f11177e = new FrameLayout(context);
            this.c.addView(this.f11177e, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (textInputLayout.getEditText() != null) {
                b();
            }
        }
        if (i7 == 0 || i7 == 1) {
            this.f11177e.setVisibility(0);
            this.f11177e.addView(c2218f);
        } else {
            this.c.addView(c2218f, new LinearLayout.LayoutParams(-2, -2));
        }
        this.c.setVisibility(0);
        this.d++;
    }

    public final void b() {
        if (this.c != null) {
            TextInputLayout textInputLayout = this.f11176b;
            if (textInputLayout.getEditText() != null) {
                EditText editText = textInputLayout.getEditText();
                Context context = this.f11175a;
                boolean zP0 = com.bumptech.glide.c.p0(context);
                LinearLayout linearLayout = this.c;
                int[] iArr = H.f2303a;
                int paddingStart = editText.getPaddingStart();
                if (zP0) {
                    paddingStart = context.getResources().getDimensionPixelSize(R.dimen.material_helper_text_font_1_3_padding_horizontal);
                }
                int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.material_helper_text_default_padding_top);
                if (zP0) {
                    dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.material_helper_text_font_1_3_padding_top);
                }
                int paddingEnd = editText.getPaddingEnd();
                if (zP0) {
                    paddingEnd = context.getResources().getDimensionPixelSize(R.dimen.material_helper_text_font_1_3_padding_horizontal);
                }
                linearLayout.setPaddingRelative(paddingStart, dimensionPixelSize, paddingEnd, 0);
            }
        }
    }

    public final void c() {
        AnimatorSet animatorSet = this.f11178f;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
    }

    public final void d(ArrayList arrayList, boolean z6, C2218F c2218f, int i7, int i10, int i11) {
        if (c2218f == null || !z6) {
            return;
        }
        if (i7 == i11 || i7 == i10) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(c2218f, (Property<C2218F, Float>) View.ALPHA, i11 == i7 ? 1.0f : 0.0f);
            objectAnimatorOfFloat.setDuration(167L);
            objectAnimatorOfFloat.setInterpolator(L4.a.f4007a);
            arrayList.add(objectAnimatorOfFloat);
            if (i11 == i7) {
                ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(c2218f, (Property<C2218F, Float>) View.TRANSLATION_Y, -this.f11179g, 0.0f);
                objectAnimatorOfFloat2.setDuration(217L);
                objectAnimatorOfFloat2.setInterpolator(L4.a.d);
                arrayList.add(objectAnimatorOfFloat2);
            }
        }
    }

    public final boolean e() {
        return (this.f11181i != 1 || this.l == null || TextUtils.isEmpty(this.f11182j)) ? false : true;
    }

    public final TextView f(int i7) {
        if (i7 == 1) {
            return this.l;
        }
        if (i7 != 2) {
            return null;
        }
        return this.f11188r;
    }

    public final void g() {
        this.f11182j = null;
        c();
        if (this.f11180h == 1) {
            if (!this.f11187q || TextUtils.isEmpty(this.f11186p)) {
                this.f11181i = 0;
            } else {
                this.f11181i = 2;
            }
        }
        j(this.f11180h, this.f11181i, i(this.l, null));
    }

    public final void h(C2218F c2218f, int i7) {
        FrameLayout frameLayout;
        LinearLayout linearLayout = this.c;
        if (linearLayout == null) {
            return;
        }
        if ((i7 == 0 || i7 == 1) && (frameLayout = this.f11177e) != null) {
            frameLayout.removeView(c2218f);
        } else {
            linearLayout.removeView(c2218f);
        }
        int i10 = this.d - 1;
        this.d = i10;
        LinearLayout linearLayout2 = this.c;
        if (i10 == 0) {
            linearLayout2.setVisibility(8);
        }
    }

    public final boolean i(C2218F c2218f, CharSequence charSequence) {
        int[] iArr = H.f2303a;
        TextInputLayout textInputLayout = this.f11176b;
        return textInputLayout.isLaidOut() && textInputLayout.isEnabled() && !(this.f11181i == this.f11180h && c2218f != null && TextUtils.equals(c2218f.getText(), charSequence));
    }

    public final void j(int i7, int i10, boolean z6) {
        TextView textViewF;
        TextView textViewF2;
        if (i7 == i10) {
            return;
        }
        if (z6) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.f11178f = animatorSet;
            ArrayList arrayList = new ArrayList();
            d(arrayList, this.f11187q, this.f11188r, 2, i7, i10);
            d(arrayList, this.f11183k, this.l, 1, i7, i10);
            int size = arrayList.size();
            long jMax = 0;
            for (int i11 = 0; i11 < size; i11++) {
                Animator animator = (Animator) arrayList.get(i11);
                jMax = Math.max(jMax, animator.getDuration() + animator.getStartDelay());
            }
            ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, 0);
            valueAnimatorOfInt.setDuration(jMax);
            arrayList.add(0, valueAnimatorOfInt);
            animatorSet.playTogether(arrayList);
            animatorSet.addListener(new q(this, i10, f(i7), i7, f(i10)));
            animatorSet.start();
        } else if (i7 != i10) {
            if (i10 != 0 && (textViewF2 = f(i10)) != null) {
                textViewF2.setVisibility(0);
                textViewF2.setAlpha(1.0f);
            }
            if (i7 != 0 && (textViewF = f(i7)) != null) {
                textViewF.setVisibility(4);
                if (i7 == 1) {
                    textViewF.setText((CharSequence) null);
                }
            }
            this.f11180h = i10;
        }
        TextInputLayout textInputLayout = this.f11176b;
        textInputLayout.q();
        textInputLayout.s(z6, false);
        textInputLayout.z();
    }
}
