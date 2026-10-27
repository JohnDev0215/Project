package com.google.android.material.textfield;

import android.R;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.b0;
import androidx.appcompat.widget.s0;
import androidx.appcompat.widget.w;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.internal.CheckableImageButton;
import e3.k;
import i0.u;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import o2.j;
import o2.k;
import o2.l;

/* JADX INFO: loaded from: classes.dex */
public class TextInputLayout extends LinearLayout {
    private static final int R0 = k.Widget_Design_TextInputLayout;
    private ColorStateList A;
    private ColorStateList A0;
    private CharSequence B;
    private int B0;
    private final TextView C;
    private int C0;
    private CharSequence D;
    private int D0;
    private final TextView E;
    private ColorStateList E0;
    private boolean F;
    private int F0;
    private CharSequence G;
    private int G0;
    private boolean H;
    private int H0;
    private e3.g I;
    private int I0;
    private e3.g J;
    private int J0;
    private e3.k K;
    private boolean K0;
    private final int L;
    final com.google.android.material.internal.a L0;
    private int M;
    private boolean M0;
    private int N;
    private boolean N0;
    private int O;
    private ValueAnimator O0;
    private int P;
    private boolean P0;
    private int Q;
    private boolean Q0;
    private int R;
    private int S;
    private int T;
    private final Rect U;
    private final Rect V;
    private final RectF W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    private Typeface f4791a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    private final CheckableImageButton f4792b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    private ColorStateList f4793c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    private boolean f4794d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final FrameLayout f4795e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    private PorterDuff.Mode f4796e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    private boolean f4797f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final LinearLayout f4798g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private Drawable f4799g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final LinearLayout f4800h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private int f4801h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final FrameLayout f4802i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private View.OnLongClickListener f4803i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    EditText f4804j;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    private final LinkedHashSet<f> f4805j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private CharSequence f4806k;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    private int f4807k0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f4808l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    private final SparseArray<com.google.android.material.textfield.e> f4809l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f4810m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    private final CheckableImageButton f4811m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final com.google.android.material.textfield.f f4812n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    private final LinkedHashSet<g> f4813n0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    boolean f4814o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    private ColorStateList f4815o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f4816p;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    private boolean f4817p0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f4818q;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    private PorterDuff.Mode f4819q0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private TextView f4820r;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    private boolean f4821r0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f4822s;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    private Drawable f4823s0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f4824t;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    private int f4825t0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private CharSequence f4826u;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    private Drawable f4827u0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private boolean f4828v;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    private View.OnLongClickListener f4829v0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private TextView f4830w;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    private View.OnLongClickListener f4831w0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private ColorStateList f4832x;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    private final CheckableImageButton f4833x0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private int f4834y;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    private ColorStateList f4835y0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private ColorStateList f4836z;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    private ColorStateList f4837z0;

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        CharSequence f4838h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        boolean f4839i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        CharSequence f4840j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        CharSequence f4841k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        CharSequence f4842l;

        static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.f4838h) + " hint=" + ((Object) this.f4840j) + " helperText=" + ((Object) this.f4841k) + " placeholderText=" + ((Object) this.f4842l) + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            TextUtils.writeToParcel(this.f4838h, parcel, i7);
            parcel.writeInt(this.f4839i ? 1 : 0);
            TextUtils.writeToParcel(this.f4840j, parcel, i7);
            TextUtils.writeToParcel(this.f4841k, parcel, i7);
            TextUtils.writeToParcel(this.f4842l, parcel, i7);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f4838h = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f4839i = parcel.readInt() == 1;
            this.f4840j = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f4841k = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f4842l = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }
    }

    class a implements TextWatcher {
        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            TextInputLayout.this.v0(!r0.Q0);
            TextInputLayout textInputLayout = TextInputLayout.this;
            if (textInputLayout.f4814o) {
                textInputLayout.n0(editable.length());
            }
            if (TextInputLayout.this.f4828v) {
                TextInputLayout.this.z0(editable.length());
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.f4811m0.performClick();
            TextInputLayout.this.f4811m0.jumpDrawablesToCurrentState();
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.f4804j.requestLayout();
        }
    }

    class d implements ValueAnimator.AnimatorUpdateListener {
        d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            TextInputLayout.this.L0.a0(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public static class e extends i0.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final TextInputLayout f4847d;

        public e(TextInputLayout textInputLayout) {
            this.f4847d = textInputLayout;
        }

        @Override // i0.a
        public void g(View view, j0.c cVar) {
            super.g(view, cVar);
            EditText editText = this.f4847d.getEditText();
            CharSequence text = editText != null ? editText.getText() : null;
            CharSequence hint = this.f4847d.getHint();
            CharSequence error = this.f4847d.getError();
            CharSequence placeholderText = this.f4847d.getPlaceholderText();
            int counterMaxLength = this.f4847d.getCounterMaxLength();
            CharSequence counterOverflowDescription = this.f4847d.getCounterOverflowDescription();
            boolean z6 = !TextUtils.isEmpty(text);
            boolean z7 = !TextUtils.isEmpty(hint);
            boolean z8 = !this.f4847d.N();
            boolean z9 = !TextUtils.isEmpty(error);
            boolean z10 = z9 || !TextUtils.isEmpty(counterOverflowDescription);
            String string = z7 ? hint.toString() : "";
            if (z6) {
                cVar.l0(text);
            } else if (!TextUtils.isEmpty(string)) {
                cVar.l0(string);
                if (z8 && placeholderText != null) {
                    cVar.l0(string + ", " + ((Object) placeholderText));
                }
            } else if (placeholderText != null) {
                cVar.l0(placeholderText);
            }
            if (!TextUtils.isEmpty(string)) {
                cVar.Z(string);
                cVar.i0(!z6);
            }
            if (text == null || text.length() != counterMaxLength) {
                counterMaxLength = -1;
            }
            cVar.a0(counterMaxLength);
            if (z10) {
                if (!z9) {
                    error = counterOverflowDescription;
                }
                cVar.V(error);
            }
            if (editText != null) {
                editText.setLabelFor(o2.f.textinput_helper_text);
            }
        }
    }

    public interface f {
        void a(TextInputLayout textInputLayout);
    }

    public interface g {
        void a(TextInputLayout textInputLayout, int i7);
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, o2.b.textInputStyle);
    }

    private boolean A() {
        return this.F && !TextUtils.isEmpty(this.G) && (this.I instanceof com.google.android.material.textfield.c);
    }

    private void A0() {
        if (this.f4804j == null) {
            return;
        }
        u.w0(this.C, Q() ? 0 : u.E(this.f4804j), this.f4804j.getCompoundPaddingTop(), getContext().getResources().getDimensionPixelSize(o2.d.material_input_text_to_prefix_suffix_padding), this.f4804j.getCompoundPaddingBottom());
    }

    private void B() {
        Iterator<f> it = this.f4805j0.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    private void B0() {
        this.C.setVisibility((this.B == null || N()) ? 8 : 0);
        r0();
    }

    private void C(int i7) {
        Iterator<g> it = this.f4813n0.iterator();
        while (it.hasNext()) {
            it.next().a(this, i7);
        }
    }

    private void C0(boolean z6, boolean z7) {
        int defaultColor = this.E0.getDefaultColor();
        int colorForState = this.E0.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, defaultColor);
        int colorForState2 = this.E0.getColorForState(new int[]{R.attr.state_activated, R.attr.state_enabled}, defaultColor);
        if (z6) {
            this.S = colorForState2;
        } else if (z7) {
            this.S = colorForState;
        } else {
            this.S = defaultColor;
        }
    }

    private void D(Canvas canvas) {
        e3.g gVar = this.J;
        if (gVar != null) {
            Rect bounds = gVar.getBounds();
            bounds.top = bounds.bottom - this.P;
            this.J.draw(canvas);
        }
    }

    private void D0() {
        if (this.f4804j == null) {
            return;
        }
        u.w0(this.E, getContext().getResources().getDimensionPixelSize(o2.d.material_input_text_to_prefix_suffix_padding), this.f4804j.getPaddingTop(), (K() || L()) ? 0 : u.D(this.f4804j), this.f4804j.getPaddingBottom());
    }

    private void E(Canvas canvas) {
        if (this.F) {
            this.L0.m(canvas);
        }
    }

    private void E0() {
        int visibility = this.E.getVisibility();
        boolean z6 = (this.D == null || N()) ? false : true;
        this.E.setVisibility(z6 ? 0 : 8);
        if (visibility != this.E.getVisibility()) {
            getEndIconDelegate().c(z6);
        }
        r0();
    }

    private void F(boolean z6) {
        ValueAnimator valueAnimator = this.O0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.O0.cancel();
        }
        if (z6 && this.N0) {
            i(0.0f);
        } else {
            this.L0.a0(0.0f);
        }
        if (A() && ((com.google.android.material.textfield.c) this.I).i0()) {
            y();
        }
        this.K0 = true;
        J();
        B0();
        E0();
    }

    private int G(int i7, boolean z6) {
        int compoundPaddingLeft = i7 + this.f4804j.getCompoundPaddingLeft();
        return (this.B == null || z6) ? compoundPaddingLeft : (compoundPaddingLeft - this.C.getMeasuredWidth()) + this.C.getPaddingLeft();
    }

    private int H(int i7, boolean z6) {
        int compoundPaddingRight = i7 - this.f4804j.getCompoundPaddingRight();
        return (this.B == null || !z6) ? compoundPaddingRight : compoundPaddingRight + (this.C.getMeasuredWidth() - this.C.getPaddingRight());
    }

    private boolean I() {
        return this.f4807k0 != 0;
    }

    private void J() {
        TextView textView = this.f4830w;
        if (textView == null || !this.f4828v) {
            return;
        }
        textView.setText((CharSequence) null);
        this.f4830w.setVisibility(4);
    }

    private boolean L() {
        return this.f4833x0.getVisibility() == 0;
    }

    private boolean P() {
        return this.N == 1 && this.f4804j.getMinLines() <= 1;
    }

    private int[] R(CheckableImageButton checkableImageButton) {
        int[] drawableState = getDrawableState();
        int[] drawableState2 = checkableImageButton.getDrawableState();
        int length = drawableState.length;
        int[] iArrCopyOf = Arrays.copyOf(drawableState, drawableState.length + drawableState2.length);
        System.arraycopy(drawableState2, 0, iArrCopyOf, length, drawableState2.length);
        return iArrCopyOf;
    }

    private void S() {
        p();
        a0();
        F0();
        k0();
        h();
        if (this.N != 0) {
            u0();
        }
    }

    private void T() {
        if (A()) {
            RectF rectF = this.W;
            this.L0.p(rectF, this.f4804j.getWidth(), this.f4804j.getGravity());
            l(rectF);
            int i7 = this.P;
            this.M = i7;
            rectF.top = 0.0f;
            rectF.bottom = i7;
            rectF.offset(-getPaddingLeft(), 0.0f);
            ((com.google.android.material.textfield.c) this.I).o0(rectF);
        }
    }

    private static void U(ViewGroup viewGroup, boolean z6) {
        int childCount = viewGroup.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = viewGroup.getChildAt(i7);
            childAt.setEnabled(z6);
            if (childAt instanceof ViewGroup) {
                U((ViewGroup) childAt, z6);
            }
        }
    }

    private void X(CheckableImageButton checkableImageButton, ColorStateList colorStateList) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (checkableImageButton.getDrawable() == null || colorStateList == null || !colorStateList.isStateful()) {
            return;
        }
        int colorForState = colorStateList.getColorForState(R(checkableImageButton), colorStateList.getDefaultColor());
        Drawable drawableMutate = a0.a.l(drawable).mutate();
        a0.a.i(drawableMutate, ColorStateList.valueOf(colorForState));
        checkableImageButton.setImageDrawable(drawableMutate);
    }

    private void Z() {
        TextView textView = this.f4830w;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    private void a0() {
        if (h0()) {
            u.n0(this.f4804j, this.I);
        }
    }

    private static void b0(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        boolean zK = u.K(checkableImageButton);
        boolean z6 = onLongClickListener != null;
        boolean z7 = zK || z6;
        checkableImageButton.setFocusable(z7);
        checkableImageButton.setClickable(zK);
        checkableImageButton.setPressable(zK);
        checkableImageButton.setLongClickable(z6);
        u.t0(checkableImageButton, z7 ? 1 : 2);
    }

    private static void c0(CheckableImageButton checkableImageButton, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        checkableImageButton.setOnClickListener(onClickListener);
        b0(checkableImageButton, onLongClickListener);
    }

    private static void d0(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        b0(checkableImageButton, onLongClickListener);
    }

    private boolean f0() {
        return (this.f4833x0.getVisibility() == 0 || ((I() && K()) || this.D != null)) && this.f4800h.getMeasuredWidth() > 0;
    }

    private void g() {
        TextView textView = this.f4830w;
        if (textView != null) {
            this.f4795e.addView(textView);
            this.f4830w.setVisibility(0);
        }
    }

    private boolean g0() {
        return !(getStartIconDrawable() == null && this.B == null) && this.f4798g.getMeasuredWidth() > 0;
    }

    private com.google.android.material.textfield.e getEndIconDelegate() {
        com.google.android.material.textfield.e eVar = this.f4809l0.get(this.f4807k0);
        return eVar != null ? eVar : this.f4809l0.get(0);
    }

    private CheckableImageButton getEndIconToUpdateDummyDrawable() {
        if (this.f4833x0.getVisibility() == 0) {
            return this.f4833x0;
        }
        if (I() && K()) {
            return this.f4811m0;
        }
        return null;
    }

    private void h() {
        if (this.f4804j == null || this.N != 1) {
            return;
        }
        if (b3.c.h(getContext())) {
            EditText editText = this.f4804j;
            u.w0(editText, u.E(editText), getResources().getDimensionPixelSize(o2.d.material_filled_edittext_font_2_0_padding_top), u.D(this.f4804j), getResources().getDimensionPixelSize(o2.d.material_filled_edittext_font_2_0_padding_bottom));
        } else if (b3.c.g(getContext())) {
            EditText editText2 = this.f4804j;
            u.w0(editText2, u.E(editText2), getResources().getDimensionPixelSize(o2.d.material_filled_edittext_font_1_3_padding_top), u.D(this.f4804j), getResources().getDimensionPixelSize(o2.d.material_filled_edittext_font_1_3_padding_bottom));
        }
    }

    private boolean h0() {
        EditText editText = this.f4804j;
        return (editText == null || this.I == null || editText.getBackground() != null || this.N == 0) ? false : true;
    }

    private void i0() {
        TextView textView = this.f4830w;
        if (textView == null || !this.f4828v) {
            return;
        }
        textView.setText(this.f4826u);
        this.f4830w.setVisibility(0);
        this.f4830w.bringToFront();
    }

    private void j() {
        e3.g gVar = this.I;
        if (gVar == null) {
            return;
        }
        gVar.setShapeAppearanceModel(this.K);
        if (w()) {
            this.I.b0(this.P, this.S);
        }
        int iQ = q();
        this.T = iQ;
        this.I.X(ColorStateList.valueOf(iQ));
        if (this.f4807k0 == 3) {
            this.f4804j.getBackground().invalidateSelf();
        }
        k();
        invalidate();
    }

    private void j0(boolean z6) {
        if (!z6 || getEndIconDrawable() == null) {
            m();
            return;
        }
        Drawable drawableMutate = a0.a.l(getEndIconDrawable()).mutate();
        a0.a.h(drawableMutate, this.f4812n.o());
        this.f4811m0.setImageDrawable(drawableMutate);
    }

    private void k() {
        if (this.J == null) {
            return;
        }
        if (x()) {
            this.J.X(ColorStateList.valueOf(this.S));
        }
        invalidate();
    }

    private void k0() {
        if (this.N == 1) {
            if (b3.c.h(getContext())) {
                this.O = getResources().getDimensionPixelSize(o2.d.material_font_2_0_box_collapsed_padding_top);
            } else if (b3.c.g(getContext())) {
                this.O = getResources().getDimensionPixelSize(o2.d.material_font_1_3_box_collapsed_padding_top);
            }
        }
    }

    private void l(RectF rectF) {
        float f7 = rectF.left;
        int i7 = this.L;
        rectF.left = f7 - i7;
        rectF.right += i7;
    }

    private void l0(Rect rect) {
        e3.g gVar = this.J;
        if (gVar != null) {
            int i7 = rect.bottom;
            gVar.setBounds(rect.left, i7 - this.R, rect.right, i7);
        }
    }

    private void m() {
        n(this.f4811m0, this.f4817p0, this.f4815o0, this.f4821r0, this.f4819q0);
    }

    private void m0() {
        if (this.f4820r != null) {
            EditText editText = this.f4804j;
            n0(editText == null ? 0 : editText.getText().length());
        }
    }

    private void n(CheckableImageButton checkableImageButton, boolean z6, ColorStateList colorStateList, boolean z7, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null && (z6 || z7)) {
            drawable = a0.a.l(drawable).mutate();
            if (z6) {
                a0.a.i(drawable, colorStateList);
            }
            if (z7) {
                a0.a.j(drawable, mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    private void o() {
        n(this.f4792b0, this.f4794d0, this.f4793c0, this.f4797f0, this.f4796e0);
    }

    private static void o0(Context context, TextView textView, int i7, int i8, boolean z6) {
        textView.setContentDescription(context.getString(z6 ? j.character_counter_overflowed_content_description : j.character_counter_content_description, Integer.valueOf(i7), Integer.valueOf(i8)));
    }

    private void p() {
        int i7 = this.N;
        if (i7 == 0) {
            this.I = null;
            this.J = null;
            return;
        }
        if (i7 == 1) {
            this.I = new e3.g(this.K);
            this.J = new e3.g();
        } else {
            if (i7 != 2) {
                throw new IllegalArgumentException(this.N + " is illegal; only @BoxBackgroundMode constants are supported.");
            }
            if (!this.F || (this.I instanceof com.google.android.material.textfield.c)) {
                this.I = new e3.g(this.K);
            } else {
                this.I = new com.google.android.material.textfield.c(this.K);
            }
            this.J = null;
        }
    }

    private void p0() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        TextView textView = this.f4820r;
        if (textView != null) {
            e0(textView, this.f4818q ? this.f4822s : this.f4824t);
            if (!this.f4818q && (colorStateList2 = this.f4836z) != null) {
                this.f4820r.setTextColor(colorStateList2);
            }
            if (!this.f4818q || (colorStateList = this.A) == null) {
                return;
            }
            this.f4820r.setTextColor(colorStateList);
        }
    }

    private int q() {
        return this.N == 1 ? u2.a.e(u2.a.d(this, o2.b.colorSurface, 0), this.T) : this.T;
    }

    private void q0() {
        if (!A() || this.K0 || this.M == this.P) {
            return;
        }
        y();
        T();
    }

    private Rect r(Rect rect) {
        if (this.f4804j == null) {
            throw new IllegalStateException();
        }
        Rect rect2 = this.V;
        boolean z6 = u.y(this) == 1;
        rect2.bottom = rect.bottom;
        int i7 = this.N;
        if (i7 == 1) {
            rect2.left = G(rect.left, z6);
            rect2.top = rect.top + this.O;
            rect2.right = H(rect.right, z6);
            return rect2;
        }
        if (i7 != 2) {
            rect2.left = G(rect.left, z6);
            rect2.top = getPaddingTop();
            rect2.right = H(rect.right, z6);
            return rect2;
        }
        rect2.left = rect.left + this.f4804j.getPaddingLeft();
        rect2.top = rect.top - v();
        rect2.right = rect.right - this.f4804j.getPaddingRight();
        return rect2;
    }

    private boolean r0() {
        boolean z6;
        if (this.f4804j == null) {
            return false;
        }
        boolean z7 = true;
        if (g0()) {
            int measuredWidth = this.f4798g.getMeasuredWidth() - this.f4804j.getPaddingLeft();
            if (this.f4799g0 == null || this.f4801h0 != measuredWidth) {
                ColorDrawable colorDrawable = new ColorDrawable();
                this.f4799g0 = colorDrawable;
                this.f4801h0 = measuredWidth;
                colorDrawable.setBounds(0, 0, measuredWidth, 1);
            }
            Drawable[] drawableArrA = androidx.core.widget.i.a(this.f4804j);
            Drawable drawable = drawableArrA[0];
            Drawable drawable2 = this.f4799g0;
            if (drawable != drawable2) {
                androidx.core.widget.i.i(this.f4804j, drawable2, drawableArrA[1], drawableArrA[2], drawableArrA[3]);
                z6 = true;
            }
            z6 = false;
        } else {
            if (this.f4799g0 != null) {
                Drawable[] drawableArrA2 = androidx.core.widget.i.a(this.f4804j);
                androidx.core.widget.i.i(this.f4804j, null, drawableArrA2[1], drawableArrA2[2], drawableArrA2[3]);
                this.f4799g0 = null;
                z6 = true;
            }
            z6 = false;
        }
        if (f0()) {
            int measuredWidth2 = this.E.getMeasuredWidth() - this.f4804j.getPaddingRight();
            CheckableImageButton endIconToUpdateDummyDrawable = getEndIconToUpdateDummyDrawable();
            if (endIconToUpdateDummyDrawable != null) {
                measuredWidth2 = measuredWidth2 + endIconToUpdateDummyDrawable.getMeasuredWidth() + i0.h.b((ViewGroup.MarginLayoutParams) endIconToUpdateDummyDrawable.getLayoutParams());
            }
            Drawable[] drawableArrA3 = androidx.core.widget.i.a(this.f4804j);
            Drawable drawable3 = this.f4823s0;
            if (drawable3 == null || this.f4825t0 == measuredWidth2) {
                if (drawable3 == null) {
                    ColorDrawable colorDrawable2 = new ColorDrawable();
                    this.f4823s0 = colorDrawable2;
                    this.f4825t0 = measuredWidth2;
                    colorDrawable2.setBounds(0, 0, measuredWidth2, 1);
                }
                Drawable drawable4 = drawableArrA3[2];
                Drawable drawable5 = this.f4823s0;
                if (drawable4 != drawable5) {
                    this.f4827u0 = drawableArrA3[2];
                    androidx.core.widget.i.i(this.f4804j, drawableArrA3[0], drawableArrA3[1], drawable5, drawableArrA3[3]);
                } else {
                    z7 = z6;
                }
            } else {
                this.f4825t0 = measuredWidth2;
                drawable3.setBounds(0, 0, measuredWidth2, 1);
                androidx.core.widget.i.i(this.f4804j, drawableArrA3[0], drawableArrA3[1], this.f4823s0, drawableArrA3[3]);
            }
        } else {
            if (this.f4823s0 == null) {
                return z6;
            }
            Drawable[] drawableArrA4 = androidx.core.widget.i.a(this.f4804j);
            if (drawableArrA4[2] == this.f4823s0) {
                androidx.core.widget.i.i(this.f4804j, drawableArrA4[0], drawableArrA4[1], this.f4827u0, drawableArrA4[3]);
            } else {
                z7 = z6;
            }
            this.f4823s0 = null;
        }
        return z7;
    }

    private int s(Rect rect, Rect rect2, float f7) {
        return P() ? (int) (rect2.top + f7) : rect.bottom - this.f4804j.getCompoundPaddingBottom();
    }

    private void setEditText(EditText editText) {
        if (this.f4804j != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (this.f4807k0 != 3 && !(editText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.f4804j = editText;
        setMinWidth(this.f4808l);
        setMaxWidth(this.f4810m);
        S();
        setTextInputAccessibilityDelegate(new e(this));
        this.L0.g0(this.f4804j.getTypeface());
        this.L0.Y(this.f4804j.getTextSize());
        int gravity = this.f4804j.getGravity();
        this.L0.Q((gravity & (-113)) | 48);
        this.L0.X(gravity);
        this.f4804j.addTextChangedListener(new a());
        if (this.f4837z0 == null) {
            this.f4837z0 = this.f4804j.getHintTextColors();
        }
        if (this.F) {
            if (TextUtils.isEmpty(this.G)) {
                CharSequence hint = this.f4804j.getHint();
                this.f4806k = hint;
                setHint(hint);
                this.f4804j.setHint((CharSequence) null);
            }
            this.H = true;
        }
        if (this.f4820r != null) {
            n0(this.f4804j.getText().length());
        }
        s0();
        this.f4812n.e();
        this.f4798g.bringToFront();
        this.f4800h.bringToFront();
        this.f4802i.bringToFront();
        this.f4833x0.bringToFront();
        B();
        A0();
        D0();
        if (!isEnabled()) {
            editText.setEnabled(false);
        }
        w0(false, true);
    }

    private void setErrorIconVisible(boolean z6) {
        this.f4833x0.setVisibility(z6 ? 0 : 8);
        this.f4802i.setVisibility(z6 ? 8 : 0);
        D0();
        if (I()) {
            return;
        }
        r0();
    }

    private void setHintInternal(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.G)) {
            return;
        }
        this.G = charSequence;
        this.L0.e0(charSequence);
        if (this.K0) {
            return;
        }
        T();
    }

    private void setPlaceholderTextEnabled(boolean z6) {
        if (this.f4828v == z6) {
            return;
        }
        if (z6) {
            w wVar = new w(getContext());
            this.f4830w = wVar;
            wVar.setId(o2.f.textinput_placeholder);
            u.m0(this.f4830w, 1);
            setPlaceholderTextAppearance(this.f4834y);
            setPlaceholderTextColor(this.f4832x);
            g();
        } else {
            Z();
            this.f4830w = null;
        }
        this.f4828v = z6;
    }

    private int t(Rect rect, float f7) {
        return P() ? (int) (rect.centerY() - (f7 / 2.0f)) : rect.top + this.f4804j.getCompoundPaddingTop();
    }

    private boolean t0() {
        int iMax;
        if (this.f4804j == null || this.f4804j.getMeasuredHeight() >= (iMax = Math.max(this.f4800h.getMeasuredHeight(), this.f4798g.getMeasuredHeight()))) {
            return false;
        }
        this.f4804j.setMinimumHeight(iMax);
        return true;
    }

    private Rect u(Rect rect) {
        if (this.f4804j == null) {
            throw new IllegalStateException();
        }
        Rect rect2 = this.V;
        float fX = this.L0.x();
        rect2.left = rect.left + this.f4804j.getCompoundPaddingLeft();
        rect2.top = t(rect, fX);
        rect2.right = rect.right - this.f4804j.getCompoundPaddingRight();
        rect2.bottom = s(rect, rect2, fX);
        return rect2;
    }

    private void u0() {
        if (this.N != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f4795e.getLayoutParams();
            int iV = v();
            if (iV != layoutParams.topMargin) {
                layoutParams.topMargin = iV;
                this.f4795e.requestLayout();
            }
        }
    }

    private int v() {
        float fR;
        if (!this.F) {
            return 0;
        }
        int i7 = this.N;
        if (i7 == 0 || i7 == 1) {
            fR = this.L0.r();
        } else {
            if (i7 != 2) {
                return 0;
            }
            fR = this.L0.r() / 2.0f;
        }
        return (int) fR;
    }

    private boolean w() {
        return this.N == 2 && x();
    }

    private void w0(boolean z6, boolean z7) {
        ColorStateList colorStateList;
        TextView textView;
        boolean zIsEnabled = isEnabled();
        EditText editText = this.f4804j;
        boolean z8 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.f4804j;
        boolean z9 = editText2 != null && editText2.hasFocus();
        boolean zK = this.f4812n.k();
        ColorStateList colorStateList2 = this.f4837z0;
        if (colorStateList2 != null) {
            this.L0.P(colorStateList2);
            this.L0.W(this.f4837z0);
        }
        if (!zIsEnabled) {
            ColorStateList colorStateList3 = this.f4837z0;
            int colorForState = colorStateList3 != null ? colorStateList3.getColorForState(new int[]{-16842910}, this.J0) : this.J0;
            this.L0.P(ColorStateList.valueOf(colorForState));
            this.L0.W(ColorStateList.valueOf(colorForState));
        } else if (zK) {
            this.L0.P(this.f4812n.p());
        } else if (this.f4818q && (textView = this.f4820r) != null) {
            this.L0.P(textView.getTextColors());
        } else if (z9 && (colorStateList = this.A0) != null) {
            this.L0.P(colorStateList);
        }
        if (z8 || !this.M0 || (isEnabled() && z9)) {
            if (z7 || this.K0) {
                z(z6);
                return;
            }
            return;
        }
        if (z7 || !this.K0) {
            F(z6);
        }
    }

    private boolean x() {
        return this.P > -1 && this.S != 0;
    }

    private void x0() {
        EditText editText;
        if (this.f4830w == null || (editText = this.f4804j) == null) {
            return;
        }
        this.f4830w.setGravity(editText.getGravity());
        this.f4830w.setPadding(this.f4804j.getCompoundPaddingLeft(), this.f4804j.getCompoundPaddingTop(), this.f4804j.getCompoundPaddingRight(), this.f4804j.getCompoundPaddingBottom());
    }

    private void y() {
        if (A()) {
            ((com.google.android.material.textfield.c) this.I).l0();
        }
    }

    private void y0() {
        EditText editText = this.f4804j;
        z0(editText == null ? 0 : editText.getText().length());
    }

    private void z(boolean z6) {
        ValueAnimator valueAnimator = this.O0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.O0.cancel();
        }
        if (z6 && this.N0) {
            i(1.0f);
        } else {
            this.L0.a0(1.0f);
        }
        this.K0 = false;
        if (A()) {
            T();
        }
        y0();
        B0();
        E0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z0(int i7) {
        if (i7 != 0 || this.K0) {
            J();
        } else {
            i0();
        }
    }

    void F0() {
        TextView textView;
        EditText editText;
        EditText editText2;
        if (this.I == null || this.N == 0) {
            return;
        }
        boolean z6 = false;
        boolean z7 = isFocused() || ((editText2 = this.f4804j) != null && editText2.hasFocus());
        boolean z8 = isHovered() || ((editText = this.f4804j) != null && editText.isHovered());
        if (!isEnabled()) {
            this.S = this.J0;
        } else if (this.f4812n.k()) {
            if (this.E0 != null) {
                C0(z7, z8);
            } else {
                this.S = this.f4812n.o();
            }
        } else if (!this.f4818q || (textView = this.f4820r) == null) {
            if (z7) {
                this.S = this.D0;
            } else if (z8) {
                this.S = this.C0;
            } else {
                this.S = this.B0;
            }
        } else if (this.E0 != null) {
            C0(z7, z8);
        } else {
            this.S = textView.getCurrentTextColor();
        }
        if (getErrorIconDrawable() != null && this.f4812n.x() && this.f4812n.k()) {
            z6 = true;
        }
        setErrorIconVisible(z6);
        W();
        Y();
        V();
        if (getEndIconDelegate().d()) {
            j0(this.f4812n.k());
        }
        if (z7 && isEnabled()) {
            this.P = this.R;
        } else {
            this.P = this.Q;
        }
        if (this.N == 2) {
            q0();
        }
        if (this.N == 1) {
            if (!isEnabled()) {
                this.T = this.G0;
            } else if (z8 && !z7) {
                this.T = this.I0;
            } else if (z7) {
                this.T = this.H0;
            } else {
                this.T = this.F0;
            }
        }
        j();
    }

    public boolean K() {
        return this.f4802i.getVisibility() == 0 && this.f4811m0.getVisibility() == 0;
    }

    public boolean M() {
        return this.f4812n.y();
    }

    final boolean N() {
        return this.K0;
    }

    public boolean O() {
        return this.H;
    }

    public boolean Q() {
        return this.f4792b0.getVisibility() == 0;
    }

    public void V() {
        X(this.f4811m0, this.f4815o0);
    }

    public void W() {
        X(this.f4833x0, this.f4835y0);
    }

    public void Y() {
        X(this.f4792b0, this.f4793c0);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i7, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
        this.f4795e.addView(view, layoutParams2);
        this.f4795e.setLayoutParams(layoutParams);
        u0();
        setEditText((EditText) view);
    }

    @Override // android.view.ViewGroup, android.view.View
    @TargetApi(26)
    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i7) {
        EditText editText = this.f4804j;
        if (editText == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i7);
            return;
        }
        if (this.f4806k != null) {
            boolean z6 = this.H;
            this.H = false;
            CharSequence hint = editText.getHint();
            this.f4804j.setHint(this.f4806k);
            try {
                super.dispatchProvideAutofillStructure(viewStructure, i7);
                return;
            } finally {
                this.f4804j.setHint(hint);
                this.H = z6;
            }
        }
        viewStructure.setAutofillId(getAutofillId());
        onProvideAutofillStructure(viewStructure, i7);
        onProvideAutofillVirtualStructure(viewStructure, i7);
        viewStructure.setChildCount(this.f4795e.getChildCount());
        for (int i8 = 0; i8 < this.f4795e.getChildCount(); i8++) {
            View childAt = this.f4795e.getChildAt(i8);
            ViewStructure viewStructureNewChild = viewStructure.newChild(i8);
            childAt.dispatchProvideAutofillStructure(viewStructureNewChild, i7);
            if (childAt == this.f4804j) {
                viewStructureNewChild.setHint(getHint());
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        this.Q0 = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.Q0 = false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        E(canvas);
        D(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        if (this.P0) {
            return;
        }
        this.P0 = true;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        com.google.android.material.internal.a aVar = this.L0;
        boolean zD0 = aVar != null ? aVar.d0(drawableState) | false : false;
        if (this.f4804j != null) {
            v0(u.P(this) && isEnabled());
        }
        s0();
        F0();
        if (zD0) {
            invalidate();
        }
        this.P0 = false;
    }

    public void e(f fVar) {
        this.f4805j0.add(fVar);
        if (this.f4804j != null) {
            fVar.a(this);
        }
    }

    void e0(TextView textView, int i7) {
        boolean z6 = true;
        try {
            androidx.core.widget.i.n(textView, i7);
            if (textView.getTextColors().getDefaultColor() != -65281) {
                z6 = false;
            }
        } catch (Exception unused) {
        }
        if (z6) {
            androidx.core.widget.i.n(textView, k.TextAppearance_AppCompat_Caption);
            textView.setTextColor(x.a.b(getContext(), o2.c.design_error));
        }
    }

    public void f(g gVar) {
        this.f4813n0.add(gVar);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.f4804j;
        return editText != null ? editText.getBaseline() + getPaddingTop() + v() : super.getBaseline();
    }

    e3.g getBoxBackground() {
        int i7 = this.N;
        if (i7 == 1 || i7 == 2) {
            return this.I;
        }
        throw new IllegalStateException();
    }

    public int getBoxBackgroundColor() {
        return this.T;
    }

    public int getBoxBackgroundMode() {
        return this.N;
    }

    public float getBoxCornerRadiusBottomEnd() {
        return this.I.s();
    }

    public float getBoxCornerRadiusBottomStart() {
        return this.I.t();
    }

    public float getBoxCornerRadiusTopEnd() {
        return this.I.G();
    }

    public float getBoxCornerRadiusTopStart() {
        return this.I.F();
    }

    public int getBoxStrokeColor() {
        return this.D0;
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.E0;
    }

    public int getBoxStrokeWidth() {
        return this.Q;
    }

    public int getBoxStrokeWidthFocused() {
        return this.R;
    }

    public int getCounterMaxLength() {
        return this.f4816p;
    }

    CharSequence getCounterOverflowDescription() {
        TextView textView;
        if (this.f4814o && this.f4818q && (textView = this.f4820r) != null) {
            return textView.getContentDescription();
        }
        return null;
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.f4836z;
    }

    public ColorStateList getCounterTextColor() {
        return this.f4836z;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.f4837z0;
    }

    public EditText getEditText() {
        return this.f4804j;
    }

    public CharSequence getEndIconContentDescription() {
        return this.f4811m0.getContentDescription();
    }

    public Drawable getEndIconDrawable() {
        return this.f4811m0.getDrawable();
    }

    public int getEndIconMode() {
        return this.f4807k0;
    }

    CheckableImageButton getEndIconView() {
        return this.f4811m0;
    }

    public CharSequence getError() {
        if (this.f4812n.x()) {
            return this.f4812n.n();
        }
        return null;
    }

    public CharSequence getErrorContentDescription() {
        return this.f4812n.m();
    }

    public int getErrorCurrentTextColors() {
        return this.f4812n.o();
    }

    public Drawable getErrorIconDrawable() {
        return this.f4833x0.getDrawable();
    }

    final int getErrorTextCurrentColor() {
        return this.f4812n.o();
    }

    public CharSequence getHelperText() {
        if (this.f4812n.y()) {
            return this.f4812n.q();
        }
        return null;
    }

    public int getHelperTextCurrentTextColor() {
        return this.f4812n.r();
    }

    public CharSequence getHint() {
        if (this.F) {
            return this.G;
        }
        return null;
    }

    final float getHintCollapsedTextHeight() {
        return this.L0.r();
    }

    final int getHintCurrentCollapsedTextColor() {
        return this.L0.u();
    }

    public ColorStateList getHintTextColor() {
        return this.A0;
    }

    public int getMaxWidth() {
        return this.f4810m;
    }

    public int getMinWidth() {
        return this.f4808l;
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.f4811m0.getContentDescription();
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.f4811m0.getDrawable();
    }

    public CharSequence getPlaceholderText() {
        if (this.f4828v) {
            return this.f4826u;
        }
        return null;
    }

    public int getPlaceholderTextAppearance() {
        return this.f4834y;
    }

    public ColorStateList getPlaceholderTextColor() {
        return this.f4832x;
    }

    public CharSequence getPrefixText() {
        return this.B;
    }

    public ColorStateList getPrefixTextColor() {
        return this.C.getTextColors();
    }

    public TextView getPrefixTextView() {
        return this.C;
    }

    public CharSequence getStartIconContentDescription() {
        return this.f4792b0.getContentDescription();
    }

    public Drawable getStartIconDrawable() {
        return this.f4792b0.getDrawable();
    }

    public CharSequence getSuffixText() {
        return this.D;
    }

    public ColorStateList getSuffixTextColor() {
        return this.E.getTextColors();
    }

    public TextView getSuffixTextView() {
        return this.E;
    }

    public Typeface getTypeface() {
        return this.f4791a0;
    }

    void i(float f7) {
        if (this.L0.y() == f7) {
            return;
        }
        if (this.O0 == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.O0 = valueAnimator;
            valueAnimator.setInterpolator(p2.a.f8821b);
            this.O0.setDuration(167L);
            this.O0.addUpdateListener(new d());
        }
        this.O0.setFloatValues(this.L0.y(), f7);
        this.O0.start();
    }

    void n0(int i7) {
        boolean z6 = this.f4818q;
        int i8 = this.f4816p;
        if (i8 == -1) {
            this.f4820r.setText(String.valueOf(i7));
            this.f4820r.setContentDescription(null);
            this.f4818q = false;
        } else {
            this.f4818q = i7 > i8;
            o0(getContext(), this.f4820r, i7, this.f4816p, this.f4818q);
            if (z6 != this.f4818q) {
                p0();
            }
            this.f4820r.setText(g0.a.c().j(getContext().getString(j.character_counter_pattern, Integer.valueOf(i7), Integer.valueOf(this.f4816p))));
        }
        if (this.f4804j == null || z6 == this.f4818q) {
            return;
        }
        v0(false);
        F0();
        s0();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        EditText editText = this.f4804j;
        if (editText != null) {
            Rect rect = this.U;
            com.google.android.material.internal.b.a(this, editText, rect);
            l0(rect);
            if (this.F) {
                this.L0.Y(this.f4804j.getTextSize());
                int gravity = this.f4804j.getGravity();
                this.L0.Q((gravity & (-113)) | 48);
                this.L0.X(gravity);
                this.L0.M(r(rect));
                this.L0.U(u(rect));
                this.L0.I();
                if (!A() || this.K0) {
                    return;
                }
                T();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        boolean zT0 = t0();
        boolean zR0 = r0();
        if (zT0 || zR0) {
            this.f4804j.post(new c());
        }
        x0();
        A0();
        D0();
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.b());
        setError(savedState.f4838h);
        if (savedState.f4839i) {
            this.f4811m0.post(new b());
        }
        setHint(savedState.f4840j);
        setHelperText(savedState.f4841k);
        setPlaceholderText(savedState.f4842l);
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.f4812n.k()) {
            savedState.f4838h = getError();
        }
        savedState.f4839i = I() && this.f4811m0.isChecked();
        savedState.f4840j = getHint();
        savedState.f4841k = getHelperText();
        savedState.f4842l = getPlaceholderText();
        return savedState;
    }

    void s0() {
        Drawable background;
        TextView textView;
        EditText editText = this.f4804j;
        if (editText == null || this.N != 0 || (background = editText.getBackground()) == null) {
            return;
        }
        if (b0.a(background)) {
            background = background.mutate();
        }
        if (this.f4812n.k()) {
            background.setColorFilter(androidx.appcompat.widget.i.e(this.f4812n.o(), PorterDuff.Mode.SRC_IN));
        } else if (this.f4818q && (textView = this.f4820r) != null) {
            background.setColorFilter(androidx.appcompat.widget.i.e(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            a0.a.a(background);
            this.f4804j.refreshDrawableState();
        }
    }

    public void setBoxBackgroundColor(int i7) {
        if (this.T != i7) {
            this.T = i7;
            this.F0 = i7;
            this.H0 = i7;
            this.I0 = i7;
            j();
        }
    }

    public void setBoxBackgroundColorResource(int i7) {
        setBoxBackgroundColor(x.a.b(getContext(), i7));
    }

    public void setBoxBackgroundColorStateList(ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.F0 = defaultColor;
        this.T = defaultColor;
        this.G0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
        this.H0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        this.I0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
        j();
    }

    public void setBoxBackgroundMode(int i7) {
        if (i7 == this.N) {
            return;
        }
        this.N = i7;
        if (this.f4804j != null) {
            S();
        }
    }

    public void setBoxStrokeColor(int i7) {
        if (this.D0 != i7) {
            this.D0 = i7;
            F0();
        }
    }

    public void setBoxStrokeColorStateList(ColorStateList colorStateList) {
        if (colorStateList.isStateful()) {
            this.B0 = colorStateList.getDefaultColor();
            this.J0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.C0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            this.D0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        } else if (this.D0 != colorStateList.getDefaultColor()) {
            this.D0 = colorStateList.getDefaultColor();
        }
        F0();
    }

    public void setBoxStrokeErrorColor(ColorStateList colorStateList) {
        if (this.E0 != colorStateList) {
            this.E0 = colorStateList;
            F0();
        }
    }

    public void setBoxStrokeWidth(int i7) {
        this.Q = i7;
        F0();
    }

    public void setBoxStrokeWidthFocused(int i7) {
        this.R = i7;
        F0();
    }

    public void setBoxStrokeWidthFocusedResource(int i7) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(i7));
    }

    public void setBoxStrokeWidthResource(int i7) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(i7));
    }

    public void setCounterEnabled(boolean z6) {
        if (this.f4814o != z6) {
            if (z6) {
                w wVar = new w(getContext());
                this.f4820r = wVar;
                wVar.setId(o2.f.textinput_counter);
                Typeface typeface = this.f4791a0;
                if (typeface != null) {
                    this.f4820r.setTypeface(typeface);
                }
                this.f4820r.setMaxLines(1);
                this.f4812n.d(this.f4820r, 2);
                i0.h.d((ViewGroup.MarginLayoutParams) this.f4820r.getLayoutParams(), getResources().getDimensionPixelOffset(o2.d.mtrl_textinput_counter_margin_start));
                p0();
                m0();
            } else {
                this.f4812n.z(this.f4820r, 2);
                this.f4820r = null;
            }
            this.f4814o = z6;
        }
    }

    public void setCounterMaxLength(int i7) {
        if (this.f4816p != i7) {
            if (i7 > 0) {
                this.f4816p = i7;
            } else {
                this.f4816p = -1;
            }
            if (this.f4814o) {
                m0();
            }
        }
    }

    public void setCounterOverflowTextAppearance(int i7) {
        if (this.f4822s != i7) {
            this.f4822s = i7;
            p0();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList colorStateList) {
        if (this.A != colorStateList) {
            this.A = colorStateList;
            p0();
        }
    }

    public void setCounterTextAppearance(int i7) {
        if (this.f4824t != i7) {
            this.f4824t = i7;
            p0();
        }
    }

    public void setCounterTextColor(ColorStateList colorStateList) {
        if (this.f4836z != colorStateList) {
            this.f4836z = colorStateList;
            p0();
        }
    }

    public void setDefaultHintTextColor(ColorStateList colorStateList) {
        this.f4837z0 = colorStateList;
        this.A0 = colorStateList;
        if (this.f4804j != null) {
            v0(false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z6) {
        U(this, z6);
        super.setEnabled(z6);
    }

    public void setEndIconActivated(boolean z6) {
        this.f4811m0.setActivated(z6);
    }

    public void setEndIconCheckable(boolean z6) {
        this.f4811m0.setCheckable(z6);
    }

    public void setEndIconContentDescription(int i7) {
        setEndIconContentDescription(i7 != 0 ? getResources().getText(i7) : null);
    }

    public void setEndIconDrawable(int i7) {
        setEndIconDrawable(i7 != 0 ? f.a.b(getContext(), i7) : null);
    }

    public void setEndIconMode(int i7) {
        int i8 = this.f4807k0;
        this.f4807k0 = i7;
        C(i8);
        setEndIconVisible(i7 != 0);
        if (getEndIconDelegate().b(this.N)) {
            getEndIconDelegate().a();
            m();
            return;
        }
        throw new IllegalStateException("The current box background mode " + this.N + " is not supported by the end icon mode " + i7);
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        c0(this.f4811m0, onClickListener, this.f4829v0);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.f4829v0 = onLongClickListener;
        d0(this.f4811m0, onLongClickListener);
    }

    public void setEndIconTintList(ColorStateList colorStateList) {
        if (this.f4815o0 != colorStateList) {
            this.f4815o0 = colorStateList;
            this.f4817p0 = true;
            m();
        }
    }

    public void setEndIconTintMode(PorterDuff.Mode mode) {
        if (this.f4819q0 != mode) {
            this.f4819q0 = mode;
            this.f4821r0 = true;
            m();
        }
    }

    public void setEndIconVisible(boolean z6) {
        if (K() != z6) {
            this.f4811m0.setVisibility(z6 ? 0 : 8);
            D0();
            r0();
        }
    }

    public void setError(CharSequence charSequence) {
        if (!this.f4812n.x()) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.f4812n.t();
        } else {
            this.f4812n.M(charSequence);
        }
    }

    public void setErrorContentDescription(CharSequence charSequence) {
        this.f4812n.B(charSequence);
    }

    public void setErrorEnabled(boolean z6) {
        this.f4812n.C(z6);
    }

    public void setErrorIconDrawable(int i7) {
        setErrorIconDrawable(i7 != 0 ? f.a.b(getContext(), i7) : null);
        W();
    }

    public void setErrorIconOnClickListener(View.OnClickListener onClickListener) {
        c0(this.f4833x0, onClickListener, this.f4831w0);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.f4831w0 = onLongClickListener;
        d0(this.f4833x0, onLongClickListener);
    }

    public void setErrorIconTintList(ColorStateList colorStateList) {
        this.f4835y0 = colorStateList;
        Drawable drawable = this.f4833x0.getDrawable();
        if (drawable != null) {
            drawable = a0.a.l(drawable).mutate();
            a0.a.i(drawable, colorStateList);
        }
        if (this.f4833x0.getDrawable() != drawable) {
            this.f4833x0.setImageDrawable(drawable);
        }
    }

    public void setErrorIconTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f4833x0.getDrawable();
        if (drawable != null) {
            drawable = a0.a.l(drawable).mutate();
            a0.a.j(drawable, mode);
        }
        if (this.f4833x0.getDrawable() != drawable) {
            this.f4833x0.setImageDrawable(drawable);
        }
    }

    public void setErrorTextAppearance(int i7) {
        this.f4812n.D(i7);
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        this.f4812n.E(colorStateList);
    }

    public void setExpandedHintEnabled(boolean z6) {
        if (this.M0 != z6) {
            this.M0 = z6;
            v0(false);
        }
    }

    public void setHelperText(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            if (M()) {
                setHelperTextEnabled(false);
            }
        } else {
            if (!M()) {
                setHelperTextEnabled(true);
            }
            this.f4812n.N(charSequence);
        }
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        this.f4812n.H(colorStateList);
    }

    public void setHelperTextEnabled(boolean z6) {
        this.f4812n.G(z6);
    }

    public void setHelperTextTextAppearance(int i7) {
        this.f4812n.F(i7);
    }

    public void setHint(CharSequence charSequence) {
        if (this.F) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z6) {
        this.N0 = z6;
    }

    public void setHintEnabled(boolean z6) {
        if (z6 != this.F) {
            this.F = z6;
            if (z6) {
                CharSequence hint = this.f4804j.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.G)) {
                        setHint(hint);
                    }
                    this.f4804j.setHint((CharSequence) null);
                }
                this.H = true;
            } else {
                this.H = false;
                if (!TextUtils.isEmpty(this.G) && TextUtils.isEmpty(this.f4804j.getHint())) {
                    this.f4804j.setHint(this.G);
                }
                setHintInternal(null);
            }
            if (this.f4804j != null) {
                u0();
            }
        }
    }

    public void setHintTextAppearance(int i7) {
        this.L0.N(i7);
        this.A0 = this.L0.q();
        if (this.f4804j != null) {
            v0(false);
            u0();
        }
    }

    public void setHintTextColor(ColorStateList colorStateList) {
        if (this.A0 != colorStateList) {
            if (this.f4837z0 == null) {
                this.L0.P(colorStateList);
            }
            this.A0 = colorStateList;
            if (this.f4804j != null) {
                v0(false);
            }
        }
    }

    public void setMaxWidth(int i7) {
        this.f4810m = i7;
        EditText editText = this.f4804j;
        if (editText == null || i7 == -1) {
            return;
        }
        editText.setMaxWidth(i7);
    }

    public void setMaxWidthResource(int i7) {
        setMaxWidth(getContext().getResources().getDimensionPixelSize(i7));
    }

    public void setMinWidth(int i7) {
        this.f4808l = i7;
        EditText editText = this.f4804j;
        if (editText == null || i7 == -1) {
            return;
        }
        editText.setMinWidth(i7);
    }

    public void setMinWidthResource(int i7) {
        setMinWidth(getContext().getResources().getDimensionPixelSize(i7));
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(int i7) {
        setPasswordVisibilityToggleContentDescription(i7 != 0 ? getResources().getText(i7) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int i7) {
        setPasswordVisibilityToggleDrawable(i7 != 0 ? f.a.b(getContext(), i7) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z6) {
        if (z6 && this.f4807k0 != 1) {
            setEndIconMode(1);
        } else {
            if (z6) {
                return;
            }
            setEndIconMode(0);
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(ColorStateList colorStateList) {
        this.f4815o0 = colorStateList;
        this.f4817p0 = true;
        m();
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        this.f4819q0 = mode;
        this.f4821r0 = true;
        m();
    }

    public void setPlaceholderText(CharSequence charSequence) {
        if (this.f4828v && TextUtils.isEmpty(charSequence)) {
            setPlaceholderTextEnabled(false);
        } else {
            if (!this.f4828v) {
                setPlaceholderTextEnabled(true);
            }
            this.f4826u = charSequence;
        }
        y0();
    }

    public void setPlaceholderTextAppearance(int i7) {
        this.f4834y = i7;
        TextView textView = this.f4830w;
        if (textView != null) {
            androidx.core.widget.i.n(textView, i7);
        }
    }

    public void setPlaceholderTextColor(ColorStateList colorStateList) {
        if (this.f4832x != colorStateList) {
            this.f4832x = colorStateList;
            TextView textView = this.f4830w;
            if (textView == null || colorStateList == null) {
                return;
            }
            textView.setTextColor(colorStateList);
        }
    }

    public void setPrefixText(CharSequence charSequence) {
        this.B = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.C.setText(charSequence);
        B0();
    }

    public void setPrefixTextAppearance(int i7) {
        androidx.core.widget.i.n(this.C, i7);
    }

    public void setPrefixTextColor(ColorStateList colorStateList) {
        this.C.setTextColor(colorStateList);
    }

    public void setStartIconCheckable(boolean z6) {
        this.f4792b0.setCheckable(z6);
    }

    public void setStartIconContentDescription(int i7) {
        setStartIconContentDescription(i7 != 0 ? getResources().getText(i7) : null);
    }

    public void setStartIconDrawable(int i7) {
        setStartIconDrawable(i7 != 0 ? f.a.b(getContext(), i7) : null);
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        c0(this.f4792b0, onClickListener, this.f4803i0);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.f4803i0 = onLongClickListener;
        d0(this.f4792b0, onLongClickListener);
    }

    public void setStartIconTintList(ColorStateList colorStateList) {
        if (this.f4793c0 != colorStateList) {
            this.f4793c0 = colorStateList;
            this.f4794d0 = true;
            o();
        }
    }

    public void setStartIconTintMode(PorterDuff.Mode mode) {
        if (this.f4796e0 != mode) {
            this.f4796e0 = mode;
            this.f4797f0 = true;
            o();
        }
    }

    public void setStartIconVisible(boolean z6) {
        if (Q() != z6) {
            this.f4792b0.setVisibility(z6 ? 0 : 8);
            A0();
            r0();
        }
    }

    public void setSuffixText(CharSequence charSequence) {
        this.D = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.E.setText(charSequence);
        E0();
    }

    public void setSuffixTextAppearance(int i7) {
        androidx.core.widget.i.n(this.E, i7);
    }

    public void setSuffixTextColor(ColorStateList colorStateList) {
        this.E.setTextColor(colorStateList);
    }

    public void setTextInputAccessibilityDelegate(e eVar) {
        EditText editText = this.f4804j;
        if (editText != null) {
            u.k0(editText, eVar);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != this.f4791a0) {
            this.f4791a0 = typeface;
            this.L0.g0(typeface);
            this.f4812n.J(typeface);
            TextView textView = this.f4820r;
            if (textView != null) {
                textView.setTypeface(typeface);
            }
        }
    }

    void v0(boolean z6) {
        w0(z6, false);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v149 */
    /* JADX WARN: Type inference failed for: r2v46 */
    /* JADX WARN: Type inference failed for: r2v47, types: [boolean, int] */
    public TextInputLayout(Context context, AttributeSet attributeSet, int i7) {
        int i8;
        ?? r22;
        int i9 = R0;
        super(f3.a.c(context, attributeSet, i7, i9), attributeSet, i7);
        this.f4808l = -1;
        this.f4810m = -1;
        this.f4812n = new com.google.android.material.textfield.f(this);
        this.U = new Rect();
        this.V = new Rect();
        this.W = new RectF();
        this.f4805j0 = new LinkedHashSet<>();
        this.f4807k0 = 0;
        SparseArray<com.google.android.material.textfield.e> sparseArray = new SparseArray<>();
        this.f4809l0 = sparseArray;
        this.f4813n0 = new LinkedHashSet<>();
        com.google.android.material.internal.a aVar = new com.google.android.material.internal.a(this);
        this.L0 = aVar;
        Context context2 = getContext();
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        FrameLayout frameLayout = new FrameLayout(context2);
        this.f4795e = frameLayout;
        frameLayout.setAddStatesFromChildren(true);
        addView(frameLayout);
        LinearLayout linearLayout = new LinearLayout(context2);
        this.f4798g = linearLayout;
        linearLayout.setOrientation(0);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        frameLayout.addView(linearLayout);
        LinearLayout linearLayout2 = new LinearLayout(context2);
        this.f4800h = linearLayout2;
        linearLayout2.setOrientation(0);
        linearLayout2.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388613));
        frameLayout.addView(linearLayout2);
        FrameLayout frameLayout2 = new FrameLayout(context2);
        this.f4802i = frameLayout2;
        frameLayout2.setLayoutParams(new FrameLayout.LayoutParams(-2, -1));
        TimeInterpolator timeInterpolator = p2.a.f8820a;
        aVar.f0(timeInterpolator);
        aVar.c0(timeInterpolator);
        aVar.Q(8388659);
        int[] iArr = l.TextInputLayout;
        int i10 = l.TextInputLayout_counterTextAppearance;
        int i11 = l.TextInputLayout_counterOverflowTextAppearance;
        int i12 = l.TextInputLayout_errorTextAppearance;
        int i13 = l.TextInputLayout_helperTextTextAppearance;
        int i14 = l.TextInputLayout_hintTextAppearance;
        s0 s0VarI = com.google.android.material.internal.j.i(context2, attributeSet, iArr, i7, i9, i10, i11, i12, i13, i14);
        this.F = s0VarI.a(l.TextInputLayout_hintEnabled, true);
        setHint(s0VarI.p(l.TextInputLayout_android_hint));
        this.N0 = s0VarI.a(l.TextInputLayout_hintAnimationEnabled, true);
        this.M0 = s0VarI.a(l.TextInputLayout_expandedHintEnabled, true);
        int i15 = l.TextInputLayout_android_minWidth;
        if (s0VarI.s(i15)) {
            i8 = -1;
            setMinWidth(s0VarI.f(i15, -1));
        } else {
            i8 = -1;
        }
        int i16 = l.TextInputLayout_android_maxWidth;
        if (s0VarI.s(i16)) {
            setMaxWidth(s0VarI.f(i16, i8));
        }
        this.K = e3.k.e(context2, attributeSet, i7, i9).m();
        this.L = context2.getResources().getDimensionPixelOffset(o2.d.mtrl_textinput_box_label_cutout_padding);
        this.O = s0VarI.e(l.TextInputLayout_boxCollapsedPaddingTop, 0);
        this.Q = s0VarI.f(l.TextInputLayout_boxStrokeWidth, context2.getResources().getDimensionPixelSize(o2.d.mtrl_textinput_box_stroke_width_default));
        this.R = s0VarI.f(l.TextInputLayout_boxStrokeWidthFocused, context2.getResources().getDimensionPixelSize(o2.d.mtrl_textinput_box_stroke_width_focused));
        this.P = this.Q;
        float fD = s0VarI.d(l.TextInputLayout_boxCornerRadiusTopStart, -1.0f);
        float fD2 = s0VarI.d(l.TextInputLayout_boxCornerRadiusTopEnd, -1.0f);
        float fD3 = s0VarI.d(l.TextInputLayout_boxCornerRadiusBottomEnd, -1.0f);
        float fD4 = s0VarI.d(l.TextInputLayout_boxCornerRadiusBottomStart, -1.0f);
        k.b bVarV = this.K.v();
        if (fD >= 0.0f) {
            bVarV.A(fD);
        }
        if (fD2 >= 0.0f) {
            bVarV.E(fD2);
        }
        if (fD3 >= 0.0f) {
            bVarV.w(fD3);
        }
        if (fD4 >= 0.0f) {
            bVarV.s(fD4);
        }
        this.K = bVarV.m();
        ColorStateList colorStateListB = b3.c.b(context2, s0VarI, l.TextInputLayout_boxBackgroundColor);
        if (colorStateListB != null) {
            int defaultColor = colorStateListB.getDefaultColor();
            this.F0 = defaultColor;
            this.T = defaultColor;
            if (colorStateListB.isStateful()) {
                this.G0 = colorStateListB.getColorForState(new int[]{-16842910}, -1);
                this.H0 = colorStateListB.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
                this.I0 = colorStateListB.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            } else {
                this.H0 = this.F0;
                ColorStateList colorStateListA = f.a.a(context2, o2.c.mtrl_filled_background_color);
                this.G0 = colorStateListA.getColorForState(new int[]{-16842910}, -1);
                this.I0 = colorStateListA.getColorForState(new int[]{R.attr.state_hovered}, -1);
            }
        } else {
            this.T = 0;
            this.F0 = 0;
            this.G0 = 0;
            this.H0 = 0;
            this.I0 = 0;
        }
        int i17 = l.TextInputLayout_android_textColorHint;
        if (s0VarI.s(i17)) {
            ColorStateList colorStateListC = s0VarI.c(i17);
            this.A0 = colorStateListC;
            this.f4837z0 = colorStateListC;
        }
        int i18 = l.TextInputLayout_boxStrokeColor;
        ColorStateList colorStateListB2 = b3.c.b(context2, s0VarI, i18);
        this.D0 = s0VarI.b(i18, 0);
        this.B0 = x.a.b(context2, o2.c.mtrl_textinput_default_box_stroke_color);
        this.J0 = x.a.b(context2, o2.c.mtrl_textinput_disabled_color);
        this.C0 = x.a.b(context2, o2.c.mtrl_textinput_hovered_box_stroke_color);
        if (colorStateListB2 != null) {
            setBoxStrokeColorStateList(colorStateListB2);
        }
        int i19 = l.TextInputLayout_boxStrokeErrorColor;
        if (s0VarI.s(i19)) {
            setBoxStrokeErrorColor(b3.c.b(context2, s0VarI, i19));
        }
        if (s0VarI.n(i14, -1) != -1) {
            r22 = 0;
            setHintTextAppearance(s0VarI.n(i14, 0));
        } else {
            r22 = 0;
        }
        int iN = s0VarI.n(i12, r22);
        CharSequence charSequenceP = s0VarI.p(l.TextInputLayout_errorContentDescription);
        boolean zA = s0VarI.a(l.TextInputLayout_errorEnabled, r22);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int i20 = o2.h.design_text_input_end_icon;
        CheckableImageButton checkableImageButton = (CheckableImageButton) layoutInflaterFrom.inflate(i20, linearLayout2, (boolean) r22);
        this.f4833x0 = checkableImageButton;
        checkableImageButton.setId(o2.f.text_input_error_icon);
        checkableImageButton.setVisibility(8);
        if (b3.c.g(context2)) {
            i0.h.d((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams(), r22);
        }
        int i21 = l.TextInputLayout_errorIconDrawable;
        if (s0VarI.s(i21)) {
            setErrorIconDrawable(s0VarI.g(i21));
        }
        int i22 = l.TextInputLayout_errorIconTint;
        if (s0VarI.s(i22)) {
            setErrorIconTintList(b3.c.b(context2, s0VarI, i22));
        }
        int i23 = l.TextInputLayout_errorIconTintMode;
        if (s0VarI.s(i23)) {
            setErrorIconTintMode(com.google.android.material.internal.l.e(s0VarI.k(i23, -1), null));
        }
        checkableImageButton.setContentDescription(getResources().getText(j.error_icon_content_description));
        u.t0(checkableImageButton, 2);
        checkableImageButton.setClickable(false);
        checkableImageButton.setPressable(false);
        checkableImageButton.setFocusable(false);
        int iN2 = s0VarI.n(i13, 0);
        boolean zA2 = s0VarI.a(l.TextInputLayout_helperTextEnabled, false);
        CharSequence charSequenceP2 = s0VarI.p(l.TextInputLayout_helperText);
        int iN3 = s0VarI.n(l.TextInputLayout_placeholderTextAppearance, 0);
        CharSequence charSequenceP3 = s0VarI.p(l.TextInputLayout_placeholderText);
        int iN4 = s0VarI.n(l.TextInputLayout_prefixTextAppearance, 0);
        CharSequence charSequenceP4 = s0VarI.p(l.TextInputLayout_prefixText);
        int iN5 = s0VarI.n(l.TextInputLayout_suffixTextAppearance, 0);
        CharSequence charSequenceP5 = s0VarI.p(l.TextInputLayout_suffixText);
        boolean zA3 = s0VarI.a(l.TextInputLayout_counterEnabled, false);
        setCounterMaxLength(s0VarI.k(l.TextInputLayout_counterMaxLength, -1));
        this.f4824t = s0VarI.n(i10, 0);
        this.f4822s = s0VarI.n(i11, 0);
        CheckableImageButton checkableImageButton2 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(o2.h.design_text_input_start_icon, (ViewGroup) linearLayout, false);
        this.f4792b0 = checkableImageButton2;
        checkableImageButton2.setVisibility(8);
        if (b3.c.g(context2)) {
            i0.h.c((ViewGroup.MarginLayoutParams) checkableImageButton2.getLayoutParams(), 0);
        }
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        int i24 = l.TextInputLayout_startIconDrawable;
        if (s0VarI.s(i24)) {
            setStartIconDrawable(s0VarI.g(i24));
            int i25 = l.TextInputLayout_startIconContentDescription;
            if (s0VarI.s(i25)) {
                setStartIconContentDescription(s0VarI.p(i25));
            }
            setStartIconCheckable(s0VarI.a(l.TextInputLayout_startIconCheckable, true));
        }
        int i26 = l.TextInputLayout_startIconTint;
        if (s0VarI.s(i26)) {
            setStartIconTintList(b3.c.b(context2, s0VarI, i26));
        }
        int i27 = l.TextInputLayout_startIconTintMode;
        if (s0VarI.s(i27)) {
            setStartIconTintMode(com.google.android.material.internal.l.e(s0VarI.k(i27, -1), null));
        }
        setBoxBackgroundMode(s0VarI.k(l.TextInputLayout_boxBackgroundMode, 0));
        CheckableImageButton checkableImageButton3 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(i20, (ViewGroup) frameLayout2, false);
        this.f4811m0 = checkableImageButton3;
        frameLayout2.addView(checkableImageButton3);
        checkableImageButton3.setVisibility(8);
        if (b3.c.g(context2)) {
            i0.h.d((ViewGroup.MarginLayoutParams) checkableImageButton3.getLayoutParams(), 0);
        }
        sparseArray.append(-1, new com.google.android.material.textfield.b(this));
        sparseArray.append(0, new h(this));
        sparseArray.append(1, new i(this));
        sparseArray.append(2, new com.google.android.material.textfield.a(this));
        sparseArray.append(3, new com.google.android.material.textfield.d(this));
        int i28 = l.TextInputLayout_endIconMode;
        if (s0VarI.s(i28)) {
            setEndIconMode(s0VarI.k(i28, 0));
            int i29 = l.TextInputLayout_endIconDrawable;
            if (s0VarI.s(i29)) {
                setEndIconDrawable(s0VarI.g(i29));
            }
            int i30 = l.TextInputLayout_endIconContentDescription;
            if (s0VarI.s(i30)) {
                setEndIconContentDescription(s0VarI.p(i30));
            }
            setEndIconCheckable(s0VarI.a(l.TextInputLayout_endIconCheckable, true));
        } else {
            int i31 = l.TextInputLayout_passwordToggleEnabled;
            if (s0VarI.s(i31)) {
                setEndIconMode(s0VarI.a(i31, false) ? 1 : 0);
                setEndIconDrawable(s0VarI.g(l.TextInputLayout_passwordToggleDrawable));
                setEndIconContentDescription(s0VarI.p(l.TextInputLayout_passwordToggleContentDescription));
                int i32 = l.TextInputLayout_passwordToggleTint;
                if (s0VarI.s(i32)) {
                    setEndIconTintList(b3.c.b(context2, s0VarI, i32));
                }
                int i33 = l.TextInputLayout_passwordToggleTintMode;
                if (s0VarI.s(i33)) {
                    setEndIconTintMode(com.google.android.material.internal.l.e(s0VarI.k(i33, -1), null));
                }
            }
        }
        if (!s0VarI.s(l.TextInputLayout_passwordToggleEnabled)) {
            int i34 = l.TextInputLayout_endIconTint;
            if (s0VarI.s(i34)) {
                setEndIconTintList(b3.c.b(context2, s0VarI, i34));
            }
            int i35 = l.TextInputLayout_endIconTintMode;
            if (s0VarI.s(i35)) {
                setEndIconTintMode(com.google.android.material.internal.l.e(s0VarI.k(i35, -1), null));
            }
        }
        w wVar = new w(context2);
        this.C = wVar;
        wVar.setId(o2.f.textinput_prefix_text);
        wVar.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        u.m0(wVar, 1);
        linearLayout.addView(checkableImageButton2);
        linearLayout.addView(wVar);
        w wVar2 = new w(context2);
        this.E = wVar2;
        wVar2.setId(o2.f.textinput_suffix_text);
        wVar2.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 80));
        u.m0(wVar2, 1);
        linearLayout2.addView(wVar2);
        linearLayout2.addView(checkableImageButton);
        linearLayout2.addView(frameLayout2);
        setHelperTextEnabled(zA2);
        setHelperText(charSequenceP2);
        setHelperTextTextAppearance(iN2);
        setErrorEnabled(zA);
        setErrorTextAppearance(iN);
        setErrorContentDescription(charSequenceP);
        setCounterTextAppearance(this.f4824t);
        setCounterOverflowTextAppearance(this.f4822s);
        setPlaceholderText(charSequenceP3);
        setPlaceholderTextAppearance(iN3);
        setPrefixText(charSequenceP4);
        setPrefixTextAppearance(iN4);
        setSuffixText(charSequenceP5);
        setSuffixTextAppearance(iN5);
        int i36 = l.TextInputLayout_errorTextColor;
        if (s0VarI.s(i36)) {
            setErrorTextColor(s0VarI.c(i36));
        }
        int i37 = l.TextInputLayout_helperTextTextColor;
        if (s0VarI.s(i37)) {
            setHelperTextColor(s0VarI.c(i37));
        }
        int i38 = l.TextInputLayout_hintTextColor;
        if (s0VarI.s(i38)) {
            setHintTextColor(s0VarI.c(i38));
        }
        int i39 = l.TextInputLayout_counterTextColor;
        if (s0VarI.s(i39)) {
            setCounterTextColor(s0VarI.c(i39));
        }
        int i40 = l.TextInputLayout_counterOverflowTextColor;
        if (s0VarI.s(i40)) {
            setCounterOverflowTextColor(s0VarI.c(i40));
        }
        int i41 = l.TextInputLayout_placeholderTextColor;
        if (s0VarI.s(i41)) {
            setPlaceholderTextColor(s0VarI.c(i41));
        }
        int i42 = l.TextInputLayout_prefixTextColor;
        if (s0VarI.s(i42)) {
            setPrefixTextColor(s0VarI.c(i42));
        }
        int i43 = l.TextInputLayout_suffixTextColor;
        if (s0VarI.s(i43)) {
            setSuffixTextColor(s0VarI.c(i43));
        }
        setCounterEnabled(zA3);
        setEnabled(s0VarI.a(l.TextInputLayout_android_enabled, true));
        s0VarI.w();
        u.t0(this, 2);
        u.u0(this, 1);
    }

    public void setEndIconContentDescription(CharSequence charSequence) {
        if (getEndIconContentDescription() != charSequence) {
            this.f4811m0.setContentDescription(charSequence);
        }
    }

    public void setEndIconDrawable(Drawable drawable) {
        this.f4811m0.setImageDrawable(drawable);
        V();
    }

    public void setStartIconContentDescription(CharSequence charSequence) {
        if (getStartIconContentDescription() != charSequence) {
            this.f4792b0.setContentDescription(charSequence);
        }
    }

    public void setStartIconDrawable(Drawable drawable) {
        this.f4792b0.setImageDrawable(drawable);
        if (drawable != null) {
            setStartIconVisible(true);
            Y();
        } else {
            setStartIconVisible(false);
            setStartIconOnClickListener(null);
            setStartIconOnLongClickListener(null);
            setStartIconContentDescription((CharSequence) null);
        }
    }

    public void setErrorIconDrawable(Drawable drawable) {
        this.f4833x0.setImageDrawable(drawable);
        setErrorIconVisible(drawable != null && this.f4812n.x());
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence charSequence) {
        this.f4811m0.setContentDescription(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable drawable) {
        this.f4811m0.setImageDrawable(drawable);
    }

    public void setHint(int i7) {
        setHint(i7 != 0 ? getResources().getText(i7) : null);
    }
}
