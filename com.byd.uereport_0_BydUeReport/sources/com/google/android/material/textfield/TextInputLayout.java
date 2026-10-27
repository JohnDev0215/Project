package com.google.android.material.textfield;

import android.R;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
import androidx.appcompat.widget.AppCompatTextView;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$attr;
import com.google.android.material.R$color;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.CheckableImageButton;
import e6.h;
import e6.m;
import java.util.Iterator;
import java.util.LinkedHashSet;
import o0.t;
import r.e0;
import r.q;
import r0.i;
import z5.k;
import z5.p;

/* JADX INFO: loaded from: classes2.dex */
public class TextInputLayout extends LinearLayout {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f4110d = R$style.Widget_Design_TextInputLayout;
    public CharSequence A;
    public int A0;
    public final TextView B;
    public int B0;
    public boolean C;
    public int C0;
    public CharSequence D;
    public boolean D0;
    public boolean E;
    public final z5.a E0;
    public h F;
    public boolean F0;
    public h G;
    public ValueAnimator G0;
    public m H;
    public boolean H0;
    public final int I;
    public boolean I0;
    public int J;
    public final int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public int P;
    public final Rect Q;
    public final Rect R;
    public final RectF S;
    public Typeface T;
    public final CheckableImageButton U;
    public ColorStateList V;
    public boolean W;
    public PorterDuff.Mode X;
    public boolean Y;
    public Drawable Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public int f4111a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public View.OnLongClickListener f4112b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public final LinkedHashSet<f> f4113c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public int f4114d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final FrameLayout f4115e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final SparseArray<h6.e> f4116e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final LinearLayout f4117f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public final CheckableImageButton f4118f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final LinearLayout f4119g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final LinkedHashSet<g> f4120g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final FrameLayout f4121h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public ColorStateList f4122h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public EditText f4123i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public boolean f4124i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CharSequence f4125j;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public PorterDuff.Mode f4126j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final h6.f f4127k;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public boolean f4128k0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f4129l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public Drawable f4130l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4131m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public int f4132m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f4133n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public Drawable f4134n0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public TextView f4135o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public View.OnLongClickListener f4136o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f4137p;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public View.OnLongClickListener f4138p0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f4139q;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public final CheckableImageButton f4140q0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public CharSequence f4141r;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public ColorStateList f4142r0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f4143s;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public ColorStateList f4144s0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public TextView f4145t;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public ColorStateList f4146t0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ColorStateList f4147u;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public int f4148u0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f4149v;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public int f4150v0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ColorStateList f4151w;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public int f4152w0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ColorStateList f4153x;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public ColorStateList f4154x0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public CharSequence f4155y;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public int f4156y0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final TextView f4157z;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public int f4158z0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public CharSequence f4159f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f4160g;

        public static class a implements Parcelable.ClassLoaderCreator<SavedState> {
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
            public SavedState[] newArray(int i8) {
                return new SavedState[i8];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.f4159f) + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            TextUtils.writeToParcel(this.f4159f, parcel, i8);
            parcel.writeInt(this.f4160g ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f4159f = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f4160g = parcel.readInt() == 1;
        }
    }

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            TextInputLayout.this.o0(!r0.I0);
            TextInputLayout textInputLayout = TextInputLayout.this;
            if (textInputLayout.f4129l) {
                textInputLayout.g0(editable.length());
            }
            if (TextInputLayout.this.f4143s) {
                TextInputLayout.this.s0(editable.length());
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.f4118f0.performClick();
            TextInputLayout.this.f4118f0.jumpDrawablesToCurrentState();
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.f4123i.requestLayout();
        }
    }

    public class d implements ValueAnimator.AnimatorUpdateListener {
        public d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            TextInputLayout.this.E0.d0(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public static class e extends o0.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextInputLayout f4165d;

        public e(TextInputLayout textInputLayout) {
            this.f4165d = textInputLayout;
        }

        @Override // o0.a
        public void g(View view, p0.c cVar) {
            super.g(view, cVar);
            EditText editText = this.f4165d.getEditText();
            CharSequence text = editText != null ? editText.getText() : null;
            CharSequence hint = this.f4165d.getHint();
            CharSequence helperText = this.f4165d.getHelperText();
            CharSequence error = this.f4165d.getError();
            int counterMaxLength = this.f4165d.getCounterMaxLength();
            CharSequence counterOverflowDescription = this.f4165d.getCounterOverflowDescription();
            boolean z7 = !TextUtils.isEmpty(text);
            boolean z8 = !TextUtils.isEmpty(hint);
            boolean z9 = !TextUtils.isEmpty(helperText);
            boolean z10 = !TextUtils.isEmpty(error);
            boolean z11 = z10 || !TextUtils.isEmpty(counterOverflowDescription);
            String string = z8 ? hint.toString() : "";
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append(((z10 || z9) && !TextUtils.isEmpty(string)) ? ", " : "");
            String string2 = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(string2);
            if (z10) {
                helperText = error;
            } else if (!z9) {
                helperText = "";
            }
            sb2.append((Object) helperText);
            String string3 = sb2.toString();
            if (z7) {
                cVar.C0(text);
            } else if (!TextUtils.isEmpty(string3)) {
                cVar.C0(string3);
            }
            if (!TextUtils.isEmpty(string3)) {
                if (Build.VERSION.SDK_INT >= 26) {
                    cVar.m0(string3);
                } else {
                    if (z7) {
                        string3 = ((Object) text) + ", " + string3;
                    }
                    cVar.C0(string3);
                }
                cVar.z0(!z7);
            }
            if (text == null || text.length() != counterMaxLength) {
                counterMaxLength = -1;
            }
            cVar.o0(counterMaxLength);
            if (z11) {
                if (!z10) {
                    error = counterOverflowDescription;
                }
                cVar.i0(error);
            }
        }
    }

    public interface f {
        void a(TextInputLayout textInputLayout);
    }

    public interface g {
        void a(TextInputLayout textInputLayout, int i8);
    }

    public TextInputLayout(Context context) {
        this(context, null);
    }

    public static void S(ViewGroup viewGroup, boolean z7) {
        int childCount = viewGroup.getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = viewGroup.getChildAt(i8);
            childAt.setEnabled(z7);
            if (childAt instanceof ViewGroup) {
                S((ViewGroup) childAt, z7);
            }
        }
    }

    public static void V(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        boolean zL = t.L(checkableImageButton);
        boolean z7 = onLongClickListener != null;
        boolean z8 = zL || z7;
        checkableImageButton.setFocusable(z8);
        checkableImageButton.setClickable(zL);
        checkableImageButton.setPressable(zL);
        checkableImageButton.setLongClickable(z7);
        t.v0(checkableImageButton, z8 ? 1 : 2);
    }

    public static void W(CheckableImageButton checkableImageButton, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        checkableImageButton.setOnClickListener(onClickListener);
        V(checkableImageButton, onLongClickListener);
    }

    public static void X(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        V(checkableImageButton, onLongClickListener);
    }

    private h6.e getEndIconDelegate() {
        h6.e eVar = this.f4116e0.get(this.f4114d0);
        return eVar != null ? eVar : this.f4116e0.get(0);
    }

    private CheckableImageButton getEndIconToUpdateDummyDrawable() {
        if (this.f4140q0.getVisibility() == 0) {
            return this.f4140q0;
        }
        if (H() && J()) {
            return this.f4118f0;
        }
        return null;
    }

    public static void h0(Context context, TextView textView, int i8, int i9, boolean z7) {
        textView.setContentDescription(context.getString(z7 ? R$string.character_counter_overflowed_content_description : R$string.character_counter_content_description, Integer.valueOf(i8), Integer.valueOf(i9)));
    }

    private void setEditText(EditText editText) {
        if (this.f4123i != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (this.f4114d0 != 3 && !(editText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.f4123i = editText;
        Q();
        setTextInputAccessibilityDelegate(new e(this));
        this.E0.k0(this.f4123i.getTypeface());
        this.E0.a0(this.f4123i.getTextSize());
        int gravity = this.f4123i.getGravity();
        this.E0.R((gravity & (-113)) | 48);
        this.E0.Z(gravity);
        this.f4123i.addTextChangedListener(new a());
        if (this.f4144s0 == null) {
            this.f4144s0 = this.f4123i.getHintTextColors();
        }
        if (this.C) {
            if (TextUtils.isEmpty(this.D)) {
                CharSequence hint = this.f4123i.getHint();
                this.f4125j = hint;
                setHint(hint);
                this.f4123i.setHint((CharSequence) null);
            }
            this.E = true;
        }
        if (this.f4135o != null) {
            g0(this.f4123i.getText().length());
        }
        k0();
        this.f4127k.e();
        this.f4117f.bringToFront();
        this.f4119g.bringToFront();
        this.f4121h.bringToFront();
        this.f4140q0.bringToFront();
        A();
        t0();
        w0();
        if (!isEnabled()) {
            editText.setEnabled(false);
        }
        p0(false, true);
    }

    private void setErrorIconVisible(boolean z7) {
        this.f4140q0.setVisibility(z7 ? 0 : 8);
        this.f4121h.setVisibility(z7 ? 8 : 0);
        w0();
        if (H()) {
            return;
        }
        j0();
    }

    private void setHintInternal(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.D)) {
            return;
        }
        this.D = charSequence;
        this.E0.i0(charSequence);
        if (this.D0) {
            return;
        }
        R();
    }

    private void setPlaceholderTextEnabled(boolean z7) {
        if (this.f4143s == z7) {
            return;
        }
        if (z7) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
            this.f4145t = appCompatTextView;
            appCompatTextView.setId(R$id.textinput_placeholder);
            t.n0(this.f4145t, 1);
            setPlaceholderTextAppearance(this.f4149v);
            setPlaceholderTextColor(this.f4147u);
            g();
        } else {
            T();
            this.f4145t = null;
        }
        this.f4143s = z7;
    }

    public final void A() {
        Iterator<f> it = this.f4113c0.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    public final void B(int i8) {
        Iterator<g> it = this.f4120g0.iterator();
        while (it.hasNext()) {
            it.next().a(this, i8);
        }
    }

    public final void C(Canvas canvas) {
        h hVar = this.G;
        if (hVar != null) {
            Rect bounds = hVar.getBounds();
            bounds.top = bounds.bottom - this.L;
            this.G.draw(canvas);
        }
    }

    public final void D(Canvas canvas) {
        if (this.C) {
            this.E0.j(canvas);
        }
    }

    public final void E(boolean z7) {
        ValueAnimator valueAnimator = this.G0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.G0.cancel();
        }
        if (z7 && this.F0) {
            h(0.0f);
        } else {
            this.E0.d0(0.0f);
        }
        if (z() && ((h6.c) this.F).n0()) {
            x();
        }
        this.D0 = true;
        I();
        u0();
        x0();
    }

    public final int F(int i8, boolean z7) {
        int compoundPaddingLeft = i8 + this.f4123i.getCompoundPaddingLeft();
        return (this.f4155y == null || z7) ? compoundPaddingLeft : (compoundPaddingLeft - this.f4157z.getMeasuredWidth()) + this.f4157z.getPaddingLeft();
    }

    public final int G(int i8, boolean z7) {
        int compoundPaddingRight = i8 - this.f4123i.getCompoundPaddingRight();
        return (this.f4155y == null || !z7) ? compoundPaddingRight : compoundPaddingRight + (this.f4157z.getMeasuredWidth() - this.f4157z.getPaddingRight());
    }

    public final boolean H() {
        return this.f4114d0 != 0;
    }

    public final void I() {
        TextView textView = this.f4145t;
        if (textView == null || !this.f4143s) {
            return;
        }
        textView.setText((CharSequence) null);
        this.f4145t.setVisibility(4);
    }

    public boolean J() {
        return this.f4121h.getVisibility() == 0 && this.f4118f0.getVisibility() == 0;
    }

    public final boolean K() {
        return this.f4140q0.getVisibility() == 0;
    }

    public boolean L() {
        return this.f4127k.x();
    }

    public final boolean M() {
        return this.D0;
    }

    public boolean N() {
        return this.E;
    }

    public final boolean O() {
        return this.J == 1 && (Build.VERSION.SDK_INT < 16 || this.f4123i.getMinLines() <= 1);
    }

    public boolean P() {
        return this.U.getVisibility() == 0;
    }

    public final void Q() {
        o();
        U();
        y0();
        if (this.J != 0) {
            n0();
        }
    }

    public final void R() {
        if (z()) {
            RectF rectF = this.S;
            this.E0.m(rectF, this.f4123i.getWidth(), this.f4123i.getGravity());
            k(rectF);
            rectF.offset(-getPaddingLeft(), -getPaddingTop());
            ((h6.c) this.F).t0(rectF);
        }
    }

    public final void T() {
        TextView textView = this.f4145t;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    public final void U() {
        if (b0()) {
            t.o0(this.f4123i, this.F);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void Y(android.widget.TextView r3, int r4) {
        /*
            r2 = this;
            r0 = 1
            r0.i.q(r3, r4)     // Catch: java.lang.Exception -> L1a
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L1a
            r1 = 23
            if (r4 < r1) goto L18
            android.content.res.ColorStateList r4 = r3.getTextColors()     // Catch: java.lang.Exception -> L1a
            int r4 = r4.getDefaultColor()     // Catch: java.lang.Exception -> L1a
            r1 = -65281(0xffffffffffff00ff, float:NaN)
            if (r4 != r1) goto L18
            goto L1a
        L18:
            r4 = 0
            r0 = r4
        L1a:
            if (r0 == 0) goto L2e
            int r4 = com.google.android.material.R$style.TextAppearance_AppCompat_Caption
            r0.i.q(r3, r4)
            android.content.Context r4 = r2.getContext()
            int r0 = com.google.android.material.R$color.design_error
            int r4 = e0.a.b(r4, r0)
            r3.setTextColor(r4)
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.Y(android.widget.TextView, int):void");
    }

    public final boolean Z() {
        return (this.f4140q0.getVisibility() == 0 || ((H() && J()) || this.A != null)) && this.f4119g.getMeasuredWidth() > 0;
    }

    public final boolean a0() {
        return !(getStartIconDrawable() == null && this.f4155y == null) && this.f4117f.getMeasuredWidth() > 0;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i8, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i8, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
        this.f4115e.addView(view, layoutParams2);
        this.f4115e.setLayoutParams(layoutParams);
        n0();
        setEditText((EditText) view);
    }

    public final boolean b0() {
        EditText editText = this.f4123i;
        return (editText == null || this.F == null || editText.getBackground() != null || this.J == 0) ? false : true;
    }

    public final void c0() {
        TextView textView = this.f4145t;
        if (textView == null || !this.f4143s) {
            return;
        }
        textView.setText(this.f4141r);
        this.f4145t.setVisibility(0);
        this.f4145t.bringToFront();
    }

    public final void d0(boolean z7) {
        if (!z7 || getEndIconDrawable() == null) {
            l();
            return;
        }
        Drawable drawableMutate = h0.a.r(getEndIconDrawable()).mutate();
        h0.a.n(drawableMutate, this.f4127k.o());
        this.f4118f0.setImageDrawable(drawableMutate);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i8) {
        EditText editText;
        if (this.f4125j == null || (editText = this.f4123i) == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i8);
            return;
        }
        boolean z7 = this.E;
        this.E = false;
        CharSequence hint = editText.getHint();
        this.f4123i.setHint(this.f4125j);
        try {
            super.dispatchProvideAutofillStructure(viewStructure, i8);
        } finally {
            this.f4123i.setHint(hint);
            this.E = z7;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        this.I0 = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.I0 = false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        D(canvas);
        C(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        if (this.H0) {
            return;
        }
        this.H0 = true;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        z5.a aVar = this.E0;
        boolean zH0 = aVar != null ? aVar.h0(drawableState) | false : false;
        if (this.f4123i != null) {
            o0(t.Q(this) && isEnabled());
        }
        k0();
        y0();
        if (zH0) {
            invalidate();
        }
        this.H0 = false;
    }

    public void e(f fVar) {
        this.f4113c0.add(fVar);
        if (this.f4123i != null) {
            fVar.a(this);
        }
    }

    public final void e0(Rect rect) {
        h hVar = this.G;
        if (hVar != null) {
            int i8 = rect.bottom;
            hVar.setBounds(rect.left, i8 - this.N, rect.right, i8);
        }
    }

    public void f(g gVar) {
        this.f4120g0.add(gVar);
    }

    public final void f0() {
        if (this.f4135o != null) {
            EditText editText = this.f4123i;
            g0(editText == null ? 0 : editText.getText().length());
        }
    }

    public final void g() {
        TextView textView = this.f4145t;
        if (textView != null) {
            this.f4115e.addView(textView);
            this.f4145t.setVisibility(0);
        }
    }

    public void g0(int i8) {
        boolean z7 = this.f4133n;
        int i9 = this.f4131m;
        if (i9 == -1) {
            this.f4135o.setText(String.valueOf(i8));
            this.f4135o.setContentDescription(null);
            this.f4133n = false;
        } else {
            this.f4133n = i8 > i9;
            h0(getContext(), this.f4135o, i8, this.f4131m, this.f4133n);
            if (z7 != this.f4133n) {
                i0();
            }
            this.f4135o.setText(m0.a.c().j(getContext().getString(R$string.character_counter_pattern, Integer.valueOf(i8), Integer.valueOf(this.f4131m))));
        }
        if (this.f4123i == null || z7 == this.f4133n) {
            return;
        }
        o0(false);
        y0();
        k0();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.f4123i;
        return editText != null ? editText.getBaseline() + getPaddingTop() + u() : super.getBaseline();
    }

    public h getBoxBackground() {
        int i8 = this.J;
        if (i8 == 1 || i8 == 2) {
            return this.F;
        }
        throw new IllegalStateException();
    }

    public int getBoxBackgroundColor() {
        return this.P;
    }

    public int getBoxBackgroundMode() {
        return this.J;
    }

    public float getBoxCornerRadiusBottomEnd() {
        return this.F.s();
    }

    public float getBoxCornerRadiusBottomStart() {
        return this.F.t();
    }

    public float getBoxCornerRadiusTopEnd() {
        return this.F.H();
    }

    public float getBoxCornerRadiusTopStart() {
        return this.F.G();
    }

    public int getBoxStrokeColor() {
        return this.f4152w0;
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.f4154x0;
    }

    public int getBoxStrokeWidth() {
        return this.M;
    }

    public int getBoxStrokeWidthFocused() {
        return this.N;
    }

    public int getCounterMaxLength() {
        return this.f4131m;
    }

    public CharSequence getCounterOverflowDescription() {
        TextView textView;
        if (this.f4129l && this.f4133n && (textView = this.f4135o) != null) {
            return textView.getContentDescription();
        }
        return null;
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.f4151w;
    }

    public ColorStateList getCounterTextColor() {
        return this.f4151w;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.f4144s0;
    }

    public EditText getEditText() {
        return this.f4123i;
    }

    public CharSequence getEndIconContentDescription() {
        return this.f4118f0.getContentDescription();
    }

    public Drawable getEndIconDrawable() {
        return this.f4118f0.getDrawable();
    }

    public int getEndIconMode() {
        return this.f4114d0;
    }

    public CheckableImageButton getEndIconView() {
        return this.f4118f0;
    }

    public CharSequence getError() {
        if (this.f4127k.w()) {
            return this.f4127k.n();
        }
        return null;
    }

    public CharSequence getErrorContentDescription() {
        return this.f4127k.m();
    }

    public int getErrorCurrentTextColors() {
        return this.f4127k.o();
    }

    public Drawable getErrorIconDrawable() {
        return this.f4140q0.getDrawable();
    }

    public final int getErrorTextCurrentColor() {
        return this.f4127k.o();
    }

    public CharSequence getHelperText() {
        if (this.f4127k.x()) {
            return this.f4127k.q();
        }
        return null;
    }

    public int getHelperTextCurrentTextColor() {
        return this.f4127k.r();
    }

    public CharSequence getHint() {
        if (this.C) {
            return this.D;
        }
        return null;
    }

    public final float getHintCollapsedTextHeight() {
        return this.E0.p();
    }

    public final int getHintCurrentCollapsedTextColor() {
        return this.E0.t();
    }

    public ColorStateList getHintTextColor() {
        return this.f4146t0;
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.f4118f0.getContentDescription();
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.f4118f0.getDrawable();
    }

    public CharSequence getPlaceholderText() {
        if (this.f4143s) {
            return this.f4141r;
        }
        return null;
    }

    public int getPlaceholderTextAppearance() {
        return this.f4149v;
    }

    public ColorStateList getPlaceholderTextColor() {
        return this.f4147u;
    }

    public CharSequence getPrefixText() {
        return this.f4155y;
    }

    public ColorStateList getPrefixTextColor() {
        return this.f4157z.getTextColors();
    }

    public TextView getPrefixTextView() {
        return this.f4157z;
    }

    public CharSequence getStartIconContentDescription() {
        return this.U.getContentDescription();
    }

    public Drawable getStartIconDrawable() {
        return this.U.getDrawable();
    }

    public CharSequence getSuffixText() {
        return this.A;
    }

    public ColorStateList getSuffixTextColor() {
        return this.B.getTextColors();
    }

    public TextView getSuffixTextView() {
        return this.B;
    }

    public Typeface getTypeface() {
        return this.T;
    }

    public void h(float f8) {
        if (this.E0.z() == f8) {
            return;
        }
        if (this.G0 == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.G0 = valueAnimator;
            valueAnimator.setInterpolator(j5.a.f5579b);
            this.G0.setDuration(167L);
            this.G0.addUpdateListener(new d());
        }
        this.G0.setFloatValues(this.E0.z(), f8);
        this.G0.start();
    }

    public final void i() {
        h hVar = this.F;
        if (hVar == null) {
            return;
        }
        hVar.setShapeAppearanceModel(this.H);
        if (v()) {
            this.F.g0(this.L, this.O);
        }
        int iP = p();
        this.P = iP;
        this.F.X(ColorStateList.valueOf(iP));
        if (this.f4114d0 == 3) {
            this.f4123i.getBackground().invalidateSelf();
        }
        j();
        invalidate();
    }

    public final void i0() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        TextView textView = this.f4135o;
        if (textView != null) {
            Y(textView, this.f4133n ? this.f4137p : this.f4139q);
            if (!this.f4133n && (colorStateList2 = this.f4151w) != null) {
                this.f4135o.setTextColor(colorStateList2);
            }
            if (!this.f4133n || (colorStateList = this.f4153x) == null) {
                return;
            }
            this.f4135o.setTextColor(colorStateList);
        }
    }

    public final void j() {
        if (this.G == null) {
            return;
        }
        if (w()) {
            this.G.X(ColorStateList.valueOf(this.O));
        }
        invalidate();
    }

    public final boolean j0() {
        boolean z7;
        if (this.f4123i == null) {
            return false;
        }
        boolean z8 = true;
        if (a0()) {
            int measuredWidth = this.f4117f.getMeasuredWidth() - this.f4123i.getPaddingLeft();
            if (this.Z == null || this.f4111a0 != measuredWidth) {
                ColorDrawable colorDrawable = new ColorDrawable();
                this.Z = colorDrawable;
                this.f4111a0 = measuredWidth;
                colorDrawable.setBounds(0, 0, measuredWidth, 1);
            }
            Drawable[] drawableArrA = i.a(this.f4123i);
            Drawable drawable = drawableArrA[0];
            Drawable drawable2 = this.Z;
            if (drawable != drawable2) {
                i.l(this.f4123i, drawable2, drawableArrA[1], drawableArrA[2], drawableArrA[3]);
                z7 = true;
            }
            z7 = false;
        } else {
            if (this.Z != null) {
                Drawable[] drawableArrA2 = i.a(this.f4123i);
                i.l(this.f4123i, null, drawableArrA2[1], drawableArrA2[2], drawableArrA2[3]);
                this.Z = null;
                z7 = true;
            }
            z7 = false;
        }
        if (Z()) {
            int measuredWidth2 = this.B.getMeasuredWidth() - this.f4123i.getPaddingRight();
            CheckableImageButton endIconToUpdateDummyDrawable = getEndIconToUpdateDummyDrawable();
            if (endIconToUpdateDummyDrawable != null) {
                measuredWidth2 = measuredWidth2 + endIconToUpdateDummyDrawable.getMeasuredWidth() + o0.g.b((ViewGroup.MarginLayoutParams) endIconToUpdateDummyDrawable.getLayoutParams());
            }
            Drawable[] drawableArrA3 = i.a(this.f4123i);
            Drawable drawable3 = this.f4130l0;
            if (drawable3 == null || this.f4132m0 == measuredWidth2) {
                if (drawable3 == null) {
                    ColorDrawable colorDrawable2 = new ColorDrawable();
                    this.f4130l0 = colorDrawable2;
                    this.f4132m0 = measuredWidth2;
                    colorDrawable2.setBounds(0, 0, measuredWidth2, 1);
                }
                Drawable drawable4 = drawableArrA3[2];
                Drawable drawable5 = this.f4130l0;
                if (drawable4 != drawable5) {
                    this.f4134n0 = drawableArrA3[2];
                    i.l(this.f4123i, drawableArrA3[0], drawableArrA3[1], drawable5, drawableArrA3[3]);
                } else {
                    z8 = z7;
                }
            } else {
                this.f4132m0 = measuredWidth2;
                drawable3.setBounds(0, 0, measuredWidth2, 1);
                i.l(this.f4123i, drawableArrA3[0], drawableArrA3[1], this.f4130l0, drawableArrA3[3]);
            }
        } else {
            if (this.f4130l0 == null) {
                return z7;
            }
            Drawable[] drawableArrA4 = i.a(this.f4123i);
            if (drawableArrA4[2] == this.f4130l0) {
                i.l(this.f4123i, drawableArrA4[0], drawableArrA4[1], this.f4134n0, drawableArrA4[3]);
            } else {
                z8 = z7;
            }
            this.f4130l0 = null;
        }
        return z8;
    }

    public final void k(RectF rectF) {
        float f8 = rectF.left;
        int i8 = this.I;
        rectF.left = f8 - i8;
        rectF.top -= i8;
        rectF.right += i8;
        rectF.bottom += i8;
    }

    public void k0() {
        Drawable background;
        TextView textView;
        EditText editText = this.f4123i;
        if (editText == null || this.J != 0 || (background = editText.getBackground()) == null) {
            return;
        }
        if (q.a(background)) {
            background = background.mutate();
        }
        if (this.f4127k.k()) {
            background.setColorFilter(r.f.e(this.f4127k.o(), PorterDuff.Mode.SRC_IN));
        } else if (this.f4133n && (textView = this.f4135o) != null) {
            background.setColorFilter(r.f.e(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            h0.a.c(background);
            this.f4123i.refreshDrawableState();
        }
    }

    public final void l() {
        m(this.f4118f0, this.f4124i0, this.f4122h0, this.f4128k0, this.f4126j0);
    }

    public final boolean l0() {
        int iMax;
        if (this.f4123i == null || this.f4123i.getMeasuredHeight() >= (iMax = Math.max(this.f4119g.getMeasuredHeight(), this.f4117f.getMeasuredHeight()))) {
            return false;
        }
        this.f4123i.setMinimumHeight(iMax);
        return true;
    }

    public final void m(CheckableImageButton checkableImageButton, boolean z7, ColorStateList colorStateList, boolean z8, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null && (z7 || z8)) {
            drawable = h0.a.r(drawable).mutate();
            if (z7) {
                h0.a.o(drawable, colorStateList);
            }
            if (z8) {
                h0.a.p(drawable, mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    public final void m0(CheckableImageButton checkableImageButton, ColorStateList colorStateList) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (checkableImageButton.getDrawable() == null || colorStateList == null || !colorStateList.isStateful()) {
            return;
        }
        int colorForState = colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
        Drawable drawableMutate = h0.a.r(drawable).mutate();
        h0.a.o(drawableMutate, ColorStateList.valueOf(colorForState));
        checkableImageButton.setImageDrawable(drawableMutate);
    }

    public final void n() {
        m(this.U, this.W, this.V, this.Y, this.X);
    }

    public final void n0() {
        if (this.J != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f4115e.getLayoutParams();
            int iU = u();
            if (iU != layoutParams.topMargin) {
                layoutParams.topMargin = iU;
                this.f4115e.requestLayout();
            }
        }
    }

    public final void o() {
        int i8 = this.J;
        if (i8 == 0) {
            this.F = null;
            this.G = null;
            return;
        }
        if (i8 == 1) {
            this.F = new h(this.H);
            this.G = new h();
        } else {
            if (i8 != 2) {
                throw new IllegalArgumentException(this.J + " is illegal; only @BoxBackgroundMode constants are supported.");
            }
            if (!this.C || (this.F instanceof h6.c)) {
                this.F = new h(this.H);
            } else {
                this.F = new h6.c(this.H);
            }
            this.G = null;
        }
    }

    public void o0(boolean z7) {
        p0(z7, false);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
        EditText editText = this.f4123i;
        if (editText != null) {
            Rect rect = this.Q;
            z5.c.a(this, editText, rect);
            e0(rect);
            if (this.C) {
                this.E0.a0(this.f4123i.getTextSize());
                int gravity = this.f4123i.getGravity();
                this.E0.R((gravity & (-113)) | 48);
                this.E0.Z(gravity);
                this.E0.N(q(rect));
                this.E0.V(t(rect));
                this.E0.K();
                if (!z() || this.D0) {
                    return;
                }
                R();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i8, int i9) {
        super.onMeasure(i8, i9);
        boolean zL0 = l0();
        boolean zJ0 = j0();
        if (zL0 || zJ0) {
            this.f4123i.post(new c());
        }
        q0();
        t0();
        w0();
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f());
        setError(savedState.f4159f);
        if (savedState.f4160g) {
            this.f4118f0.post(new b());
        }
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.f4127k.k()) {
            savedState.f4159f = getError();
        }
        savedState.f4160g = H() && this.f4118f0.isChecked();
        return savedState;
    }

    public final int p() {
        return this.J == 1 ? t5.a.e(t5.a.d(this, R$attr.colorSurface, 0), this.P) : this.P;
    }

    public final void p0(boolean z7, boolean z8) {
        ColorStateList colorStateList;
        TextView textView;
        boolean zIsEnabled = isEnabled();
        EditText editText = this.f4123i;
        boolean z9 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.f4123i;
        boolean z10 = editText2 != null && editText2.hasFocus();
        boolean zK = this.f4127k.k();
        ColorStateList colorStateList2 = this.f4144s0;
        if (colorStateList2 != null) {
            this.E0.Q(colorStateList2);
            this.E0.Y(this.f4144s0);
        }
        if (!zIsEnabled) {
            ColorStateList colorStateList3 = this.f4144s0;
            int colorForState = colorStateList3 != null ? colorStateList3.getColorForState(new int[]{-16842910}, this.C0) : this.C0;
            this.E0.Q(ColorStateList.valueOf(colorForState));
            this.E0.Y(ColorStateList.valueOf(colorForState));
        } else if (zK) {
            this.E0.Q(this.f4127k.p());
        } else if (this.f4133n && (textView = this.f4135o) != null) {
            this.E0.Q(textView.getTextColors());
        } else if (z10 && (colorStateList = this.f4146t0) != null) {
            this.E0.Q(colorStateList);
        }
        if (z9 || (isEnabled() && (z10 || zK))) {
            if (z8 || this.D0) {
                y(z7);
                return;
            }
            return;
        }
        if (z8 || !this.D0) {
            E(z7);
        }
    }

    public final Rect q(Rect rect) {
        if (this.f4123i == null) {
            throw new IllegalStateException();
        }
        Rect rect2 = this.R;
        boolean z7 = t.A(this) == 1;
        rect2.bottom = rect.bottom;
        int i8 = this.J;
        if (i8 == 1) {
            rect2.left = F(rect.left, z7);
            rect2.top = rect.top + this.K;
            rect2.right = G(rect.right, z7);
            return rect2;
        }
        if (i8 != 2) {
            rect2.left = F(rect.left, z7);
            rect2.top = getPaddingTop();
            rect2.right = G(rect.right, z7);
            return rect2;
        }
        rect2.left = rect.left + this.f4123i.getPaddingLeft();
        rect2.top = rect.top - u();
        rect2.right = rect.right - this.f4123i.getPaddingRight();
        return rect2;
    }

    public final void q0() {
        EditText editText;
        if (this.f4145t == null || (editText = this.f4123i) == null) {
            return;
        }
        this.f4145t.setGravity(editText.getGravity());
        this.f4145t.setPadding(this.f4123i.getCompoundPaddingLeft(), this.f4123i.getCompoundPaddingTop(), this.f4123i.getCompoundPaddingRight(), this.f4123i.getCompoundPaddingBottom());
    }

    public final int r(Rect rect, Rect rect2, float f8) {
        return O() ? (int) (rect2.top + f8) : rect.bottom - this.f4123i.getCompoundPaddingBottom();
    }

    public final void r0() {
        EditText editText = this.f4123i;
        s0(editText == null ? 0 : editText.getText().length());
    }

    public final int s(Rect rect, float f8) {
        return O() ? (int) (rect.centerY() - (f8 / 2.0f)) : rect.top + this.f4123i.getCompoundPaddingTop();
    }

    public final void s0(int i8) {
        if (i8 != 0 || this.D0) {
            I();
        } else {
            c0();
        }
    }

    public void setBoxBackgroundColor(int i8) {
        if (this.P != i8) {
            this.P = i8;
            this.f4156y0 = i8;
            this.A0 = i8;
            this.B0 = i8;
            i();
        }
    }

    public void setBoxBackgroundColorResource(int i8) {
        setBoxBackgroundColor(e0.a.b(getContext(), i8));
    }

    public void setBoxBackgroundColorStateList(ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.f4156y0 = defaultColor;
        this.P = defaultColor;
        this.f4158z0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
        this.A0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        this.B0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
        i();
    }

    public void setBoxBackgroundMode(int i8) {
        if (i8 == this.J) {
            return;
        }
        this.J = i8;
        if (this.f4123i != null) {
            Q();
        }
    }

    public void setBoxStrokeColor(int i8) {
        if (this.f4152w0 != i8) {
            this.f4152w0 = i8;
            y0();
        }
    }

    public void setBoxStrokeColorStateList(ColorStateList colorStateList) {
        if (colorStateList.isStateful()) {
            this.f4148u0 = colorStateList.getDefaultColor();
            this.C0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.f4150v0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            this.f4152w0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        } else if (this.f4152w0 != colorStateList.getDefaultColor()) {
            this.f4152w0 = colorStateList.getDefaultColor();
        }
        y0();
    }

    public void setBoxStrokeErrorColor(ColorStateList colorStateList) {
        if (this.f4154x0 != colorStateList) {
            this.f4154x0 = colorStateList;
            y0();
        }
    }

    public void setBoxStrokeWidth(int i8) {
        this.M = i8;
        y0();
    }

    public void setBoxStrokeWidthFocused(int i8) {
        this.N = i8;
        y0();
    }

    public void setBoxStrokeWidthFocusedResource(int i8) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(i8));
    }

    public void setBoxStrokeWidthResource(int i8) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(i8));
    }

    public void setCounterEnabled(boolean z7) {
        if (this.f4129l != z7) {
            if (z7) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
                this.f4135o = appCompatTextView;
                appCompatTextView.setId(R$id.textinput_counter);
                Typeface typeface = this.T;
                if (typeface != null) {
                    this.f4135o.setTypeface(typeface);
                }
                this.f4135o.setMaxLines(1);
                this.f4127k.d(this.f4135o, 2);
                o0.g.d((ViewGroup.MarginLayoutParams) this.f4135o.getLayoutParams(), getResources().getDimensionPixelOffset(R$dimen.mtrl_textinput_counter_margin_start));
                i0();
                f0();
            } else {
                this.f4127k.y(this.f4135o, 2);
                this.f4135o = null;
            }
            this.f4129l = z7;
        }
    }

    public void setCounterMaxLength(int i8) {
        if (this.f4131m != i8) {
            if (i8 > 0) {
                this.f4131m = i8;
            } else {
                this.f4131m = -1;
            }
            if (this.f4129l) {
                f0();
            }
        }
    }

    public void setCounterOverflowTextAppearance(int i8) {
        if (this.f4137p != i8) {
            this.f4137p = i8;
            i0();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList colorStateList) {
        if (this.f4153x != colorStateList) {
            this.f4153x = colorStateList;
            i0();
        }
    }

    public void setCounterTextAppearance(int i8) {
        if (this.f4139q != i8) {
            this.f4139q = i8;
            i0();
        }
    }

    public void setCounterTextColor(ColorStateList colorStateList) {
        if (this.f4151w != colorStateList) {
            this.f4151w = colorStateList;
            i0();
        }
    }

    public void setDefaultHintTextColor(ColorStateList colorStateList) {
        this.f4144s0 = colorStateList;
        this.f4146t0 = colorStateList;
        if (this.f4123i != null) {
            o0(false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z7) {
        S(this, z7);
        super.setEnabled(z7);
    }

    public void setEndIconActivated(boolean z7) {
        this.f4118f0.setActivated(z7);
    }

    public void setEndIconCheckable(boolean z7) {
        this.f4118f0.setCheckable(z7);
    }

    public void setEndIconContentDescription(int i8) {
        setEndIconContentDescription(i8 != 0 ? getResources().getText(i8) : null);
    }

    public void setEndIconDrawable(int i8) {
        setEndIconDrawable(i8 != 0 ? m.a.d(getContext(), i8) : null);
    }

    public void setEndIconMode(int i8) {
        int i9 = this.f4114d0;
        this.f4114d0 = i8;
        B(i9);
        setEndIconVisible(i8 != 0);
        if (getEndIconDelegate().b(this.J)) {
            getEndIconDelegate().a();
            l();
            return;
        }
        throw new IllegalStateException("The current box background mode " + this.J + " is not supported by the end icon mode " + i8);
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        W(this.f4118f0, onClickListener, this.f4136o0);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.f4136o0 = onLongClickListener;
        X(this.f4118f0, onLongClickListener);
    }

    public void setEndIconTintList(ColorStateList colorStateList) {
        if (this.f4122h0 != colorStateList) {
            this.f4122h0 = colorStateList;
            this.f4124i0 = true;
            l();
        }
    }

    public void setEndIconTintMode(PorterDuff.Mode mode) {
        if (this.f4126j0 != mode) {
            this.f4126j0 = mode;
            this.f4128k0 = true;
            l();
        }
    }

    public void setEndIconVisible(boolean z7) {
        if (J() != z7) {
            this.f4118f0.setVisibility(z7 ? 0 : 8);
            w0();
            j0();
        }
    }

    public void setError(CharSequence charSequence) {
        if (!this.f4127k.w()) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.f4127k.s();
        } else {
            this.f4127k.L(charSequence);
        }
    }

    public void setErrorContentDescription(CharSequence charSequence) {
        this.f4127k.A(charSequence);
    }

    public void setErrorEnabled(boolean z7) {
        this.f4127k.B(z7);
    }

    public void setErrorIconDrawable(int i8) {
        setErrorIconDrawable(i8 != 0 ? m.a.d(getContext(), i8) : null);
    }

    public void setErrorIconOnClickListener(View.OnClickListener onClickListener) {
        W(this.f4140q0, onClickListener, this.f4138p0);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.f4138p0 = onLongClickListener;
        X(this.f4140q0, onLongClickListener);
    }

    public void setErrorIconTintList(ColorStateList colorStateList) {
        this.f4142r0 = colorStateList;
        Drawable drawable = this.f4140q0.getDrawable();
        if (drawable != null) {
            drawable = h0.a.r(drawable).mutate();
            h0.a.o(drawable, colorStateList);
        }
        if (this.f4140q0.getDrawable() != drawable) {
            this.f4140q0.setImageDrawable(drawable);
        }
    }

    public void setErrorIconTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f4140q0.getDrawable();
        if (drawable != null) {
            drawable = h0.a.r(drawable).mutate();
            h0.a.p(drawable, mode);
        }
        if (this.f4140q0.getDrawable() != drawable) {
            this.f4140q0.setImageDrawable(drawable);
        }
    }

    public void setErrorTextAppearance(int i8) {
        this.f4127k.C(i8);
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        this.f4127k.D(colorStateList);
    }

    public void setHelperText(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            if (L()) {
                setHelperTextEnabled(false);
            }
        } else {
            if (!L()) {
                setHelperTextEnabled(true);
            }
            this.f4127k.M(charSequence);
        }
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        this.f4127k.G(colorStateList);
    }

    public void setHelperTextEnabled(boolean z7) {
        this.f4127k.F(z7);
    }

    public void setHelperTextTextAppearance(int i8) {
        this.f4127k.E(i8);
    }

    public void setHint(CharSequence charSequence) {
        if (this.C) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z7) {
        this.F0 = z7;
    }

    public void setHintEnabled(boolean z7) {
        if (z7 != this.C) {
            this.C = z7;
            if (z7) {
                CharSequence hint = this.f4123i.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.D)) {
                        setHint(hint);
                    }
                    this.f4123i.setHint((CharSequence) null);
                }
                this.E = true;
            } else {
                this.E = false;
                if (!TextUtils.isEmpty(this.D) && TextUtils.isEmpty(this.f4123i.getHint())) {
                    this.f4123i.setHint(this.D);
                }
                setHintInternal(null);
            }
            if (this.f4123i != null) {
                n0();
            }
        }
    }

    public void setHintTextAppearance(int i8) {
        this.E0.O(i8);
        this.f4146t0 = this.E0.n();
        if (this.f4123i != null) {
            o0(false);
            n0();
        }
    }

    public void setHintTextColor(ColorStateList colorStateList) {
        if (this.f4146t0 != colorStateList) {
            if (this.f4144s0 == null) {
                this.E0.Q(colorStateList);
            }
            this.f4146t0 = colorStateList;
            if (this.f4123i != null) {
                o0(false);
            }
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(int i8) {
        setPasswordVisibilityToggleContentDescription(i8 != 0 ? getResources().getText(i8) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int i8) {
        setPasswordVisibilityToggleDrawable(i8 != 0 ? m.a.d(getContext(), i8) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z7) {
        if (z7 && this.f4114d0 != 1) {
            setEndIconMode(1);
        } else {
            if (z7) {
                return;
            }
            setEndIconMode(0);
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(ColorStateList colorStateList) {
        this.f4122h0 = colorStateList;
        this.f4124i0 = true;
        l();
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        this.f4126j0 = mode;
        this.f4128k0 = true;
        l();
    }

    public void setPlaceholderText(CharSequence charSequence) {
        if (this.f4143s && TextUtils.isEmpty(charSequence)) {
            setPlaceholderTextEnabled(false);
        } else {
            if (!this.f4143s) {
                setPlaceholderTextEnabled(true);
            }
            this.f4141r = charSequence;
        }
        r0();
    }

    public void setPlaceholderTextAppearance(int i8) {
        this.f4149v = i8;
        TextView textView = this.f4145t;
        if (textView != null) {
            i.q(textView, i8);
        }
    }

    public void setPlaceholderTextColor(ColorStateList colorStateList) {
        if (this.f4147u != colorStateList) {
            this.f4147u = colorStateList;
            TextView textView = this.f4145t;
            if (textView == null || colorStateList == null) {
                return;
            }
            textView.setTextColor(colorStateList);
        }
    }

    public void setPrefixText(CharSequence charSequence) {
        this.f4155y = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.f4157z.setText(charSequence);
        u0();
    }

    public void setPrefixTextAppearance(int i8) {
        i.q(this.f4157z, i8);
    }

    public void setPrefixTextColor(ColorStateList colorStateList) {
        this.f4157z.setTextColor(colorStateList);
    }

    public void setStartIconCheckable(boolean z7) {
        this.U.setCheckable(z7);
    }

    public void setStartIconContentDescription(int i8) {
        setStartIconContentDescription(i8 != 0 ? getResources().getText(i8) : null);
    }

    public void setStartIconDrawable(int i8) {
        setStartIconDrawable(i8 != 0 ? m.a.d(getContext(), i8) : null);
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        W(this.U, onClickListener, this.f4112b0);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.f4112b0 = onLongClickListener;
        X(this.U, onLongClickListener);
    }

    public void setStartIconTintList(ColorStateList colorStateList) {
        if (this.V != colorStateList) {
            this.V = colorStateList;
            this.W = true;
            n();
        }
    }

    public void setStartIconTintMode(PorterDuff.Mode mode) {
        if (this.X != mode) {
            this.X = mode;
            this.Y = true;
            n();
        }
    }

    public void setStartIconVisible(boolean z7) {
        if (P() != z7) {
            this.U.setVisibility(z7 ? 0 : 8);
            t0();
            j0();
        }
    }

    public void setSuffixText(CharSequence charSequence) {
        this.A = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.B.setText(charSequence);
        x0();
    }

    public void setSuffixTextAppearance(int i8) {
        i.q(this.B, i8);
    }

    public void setSuffixTextColor(ColorStateList colorStateList) {
        this.B.setTextColor(colorStateList);
    }

    public void setTextInputAccessibilityDelegate(e eVar) {
        EditText editText = this.f4123i;
        if (editText != null) {
            t.l0(editText, eVar);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != this.T) {
            this.T = typeface;
            this.E0.k0(typeface);
            this.f4127k.I(typeface);
            TextView textView = this.f4135o;
            if (textView != null) {
                textView.setTypeface(typeface);
            }
        }
    }

    public final Rect t(Rect rect) {
        if (this.f4123i == null) {
            throw new IllegalStateException();
        }
        Rect rect2 = this.R;
        float fX = this.E0.x();
        rect2.left = rect.left + this.f4123i.getCompoundPaddingLeft();
        rect2.top = s(rect, fX);
        rect2.right = rect.right - this.f4123i.getCompoundPaddingRight();
        rect2.bottom = r(rect, rect2, fX);
        return rect2;
    }

    public final void t0() {
        if (this.f4123i == null) {
            return;
        }
        t.z0(this.f4157z, P() ? 0 : t.F(this.f4123i), this.f4123i.getCompoundPaddingTop(), 0, this.f4123i.getCompoundPaddingBottom());
    }

    public final int u() {
        float fP;
        if (!this.C) {
            return 0;
        }
        int i8 = this.J;
        if (i8 == 0 || i8 == 1) {
            fP = this.E0.p();
        } else {
            if (i8 != 2) {
                return 0;
            }
            fP = this.E0.p() / 2.0f;
        }
        return (int) fP;
    }

    public final void u0() {
        this.f4157z.setVisibility((this.f4155y == null || M()) ? 8 : 0);
        j0();
    }

    public final boolean v() {
        return this.J == 2 && w();
    }

    public final void v0(boolean z7, boolean z8) {
        int defaultColor = this.f4154x0.getDefaultColor();
        int colorForState = this.f4154x0.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, defaultColor);
        int colorForState2 = this.f4154x0.getColorForState(new int[]{R.attr.state_activated, R.attr.state_enabled}, defaultColor);
        if (z7) {
            this.O = colorForState2;
        } else if (z8) {
            this.O = colorForState;
        } else {
            this.O = defaultColor;
        }
    }

    public final boolean w() {
        return this.L > -1 && this.O != 0;
    }

    public final void w0() {
        if (this.f4123i == null) {
            return;
        }
        t.z0(this.B, 0, this.f4123i.getPaddingTop(), (J() || K()) ? 0 : t.E(this.f4123i), this.f4123i.getPaddingBottom());
    }

    public final void x() {
        if (z()) {
            ((h6.c) this.F).q0();
        }
    }

    public final void x0() {
        int visibility = this.B.getVisibility();
        boolean z7 = (this.A == null || M()) ? false : true;
        this.B.setVisibility(z7 ? 0 : 8);
        if (visibility != this.B.getVisibility()) {
            getEndIconDelegate().c(z7);
        }
        j0();
    }

    public final void y(boolean z7) {
        ValueAnimator valueAnimator = this.G0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.G0.cancel();
        }
        if (z7 && this.F0) {
            h(1.0f);
        } else {
            this.E0.d0(1.0f);
        }
        this.D0 = false;
        if (z()) {
            R();
        }
        r0();
        u0();
        x0();
    }

    public void y0() {
        TextView textView;
        EditText editText;
        EditText editText2;
        if (this.F == null || this.J == 0) {
            return;
        }
        boolean z7 = false;
        boolean z8 = isFocused() || ((editText2 = this.f4123i) != null && editText2.hasFocus());
        boolean z9 = isHovered() || ((editText = this.f4123i) != null && editText.isHovered());
        if (!isEnabled()) {
            this.O = this.C0;
        } else if (this.f4127k.k()) {
            if (this.f4154x0 != null) {
                v0(z8, z9);
            } else {
                this.O = this.f4127k.o();
            }
        } else if (!this.f4133n || (textView = this.f4135o) == null) {
            if (z8) {
                this.O = this.f4152w0;
            } else if (z9) {
                this.O = this.f4150v0;
            } else {
                this.O = this.f4148u0;
            }
        } else if (this.f4154x0 != null) {
            v0(z8, z9);
        } else {
            this.O = textView.getCurrentTextColor();
        }
        if (getErrorIconDrawable() != null && this.f4127k.w() && this.f4127k.k()) {
            z7 = true;
        }
        setErrorIconVisible(z7);
        m0(this.f4140q0, this.f4142r0);
        m0(this.U, this.V);
        m0(this.f4118f0, this.f4122h0);
        if (getEndIconDelegate().d()) {
            d0(this.f4127k.k());
        }
        if (z8 && isEnabled()) {
            this.L = this.N;
        } else {
            this.L = this.M;
        }
        if (this.J == 1) {
            if (!isEnabled()) {
                this.P = this.f4158z0;
            } else if (z9 && !z8) {
                this.P = this.B0;
            } else if (z8) {
                this.P = this.A0;
            } else {
                this.P = this.f4156y0;
            }
        }
        i();
    }

    public final boolean z() {
        return this.C && !TextUtils.isEmpty(this.D) && (this.F instanceof h6.c);
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.textInputStyle);
    }

    public void setEndIconContentDescription(CharSequence charSequence) {
        if (getEndIconContentDescription() != charSequence) {
            this.f4118f0.setContentDescription(charSequence);
        }
    }

    public void setEndIconDrawable(Drawable drawable) {
        this.f4118f0.setImageDrawable(drawable);
    }

    public void setErrorIconDrawable(Drawable drawable) {
        this.f4140q0.setImageDrawable(drawable);
        setErrorIconVisible(drawable != null && this.f4127k.w());
    }

    public void setStartIconContentDescription(CharSequence charSequence) {
        if (getStartIconContentDescription() != charSequence) {
            this.U.setContentDescription(charSequence);
        }
    }

    public void setStartIconDrawable(Drawable drawable) {
        this.U.setImageDrawable(drawable);
        if (drawable != null) {
            setStartIconVisible(true);
            n();
        } else {
            setStartIconVisible(false);
            setStartIconOnClickListener(null);
            setStartIconOnLongClickListener(null);
            setStartIconContentDescription((CharSequence) null);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TextInputLayout(Context context, AttributeSet attributeSet, int i8) {
        int i9;
        int i10 = f4110d;
        super(i6.a.c(context, attributeSet, i8, i10), attributeSet, i8);
        this.f4127k = new h6.f(this);
        this.Q = new Rect();
        this.R = new Rect();
        this.S = new RectF();
        this.f4113c0 = new LinkedHashSet<>();
        this.f4114d0 = 0;
        this.f4116e0 = new SparseArray<>();
        this.f4120g0 = new LinkedHashSet<>();
        z5.a aVar = new z5.a(this);
        this.E0 = aVar;
        Context context2 = getContext();
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        FrameLayout frameLayout = new FrameLayout(context2);
        this.f4115e = frameLayout;
        frameLayout.setAddStatesFromChildren(true);
        addView(frameLayout);
        LinearLayout linearLayout = new LinearLayout(context2);
        this.f4117f = linearLayout;
        linearLayout.setOrientation(0);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        frameLayout.addView(linearLayout);
        LinearLayout linearLayout2 = new LinearLayout(context2);
        this.f4119g = linearLayout2;
        linearLayout2.setOrientation(0);
        linearLayout2.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388613));
        frameLayout.addView(linearLayout2);
        FrameLayout frameLayout2 = new FrameLayout(context2);
        this.f4121h = frameLayout2;
        frameLayout2.setLayoutParams(new FrameLayout.LayoutParams(-2, -1));
        TimeInterpolator timeInterpolator = j5.a.f5578a;
        aVar.j0(timeInterpolator);
        aVar.g0(timeInterpolator);
        aVar.R(8388659);
        int[] iArr = R$styleable.TextInputLayout;
        int i11 = R$styleable.TextInputLayout_counterTextAppearance;
        int i12 = R$styleable.TextInputLayout_counterOverflowTextAppearance;
        int i13 = R$styleable.TextInputLayout_errorTextAppearance;
        int i14 = R$styleable.TextInputLayout_helperTextTextAppearance;
        int i15 = R$styleable.TextInputLayout_hintTextAppearance;
        e0 e0VarI = k.i(context2, attributeSet, iArr, i8, i10, i11, i12, i13, i14, i15);
        this.C = e0VarI.a(R$styleable.TextInputLayout_hintEnabled, true);
        setHint(e0VarI.p(R$styleable.TextInputLayout_android_hint));
        this.F0 = e0VarI.a(R$styleable.TextInputLayout_hintAnimationEnabled, true);
        this.H = m.e(context2, attributeSet, i8, i10).m();
        this.I = context2.getResources().getDimensionPixelOffset(R$dimen.mtrl_textinput_box_label_cutout_padding);
        this.K = e0VarI.e(R$styleable.TextInputLayout_boxCollapsedPaddingTop, 0);
        this.M = e0VarI.f(R$styleable.TextInputLayout_boxStrokeWidth, context2.getResources().getDimensionPixelSize(R$dimen.mtrl_textinput_box_stroke_width_default));
        this.N = e0VarI.f(R$styleable.TextInputLayout_boxStrokeWidthFocused, context2.getResources().getDimensionPixelSize(R$dimen.mtrl_textinput_box_stroke_width_focused));
        this.L = this.M;
        float fD = e0VarI.d(R$styleable.TextInputLayout_boxCornerRadiusTopStart, -1.0f);
        float fD2 = e0VarI.d(R$styleable.TextInputLayout_boxCornerRadiusTopEnd, -1.0f);
        float fD3 = e0VarI.d(R$styleable.TextInputLayout_boxCornerRadiusBottomEnd, -1.0f);
        float fD4 = e0VarI.d(R$styleable.TextInputLayout_boxCornerRadiusBottomStart, -1.0f);
        m.b bVarV = this.H.v();
        if (fD >= 0.0f) {
            bVarV.D(fD);
        }
        if (fD2 >= 0.0f) {
            bVarV.H(fD2);
        }
        if (fD3 >= 0.0f) {
            bVarV.y(fD3);
        }
        if (fD4 >= 0.0f) {
            bVarV.u(fD4);
        }
        this.H = bVarV.m();
        ColorStateList colorStateListB = b6.c.b(context2, e0VarI, R$styleable.TextInputLayout_boxBackgroundColor);
        if (colorStateListB != null) {
            int defaultColor = colorStateListB.getDefaultColor();
            this.f4156y0 = defaultColor;
            this.P = defaultColor;
            if (colorStateListB.isStateful()) {
                this.f4158z0 = colorStateListB.getColorForState(new int[]{-16842910}, -1);
                i9 = 2;
                this.A0 = colorStateListB.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
                this.B0 = colorStateListB.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            } else {
                i9 = 2;
                this.A0 = this.f4156y0;
                ColorStateList colorStateListC = m.a.c(context2, R$color.mtrl_filled_background_color);
                this.f4158z0 = colorStateListC.getColorForState(new int[]{-16842910}, -1);
                this.B0 = colorStateListC.getColorForState(new int[]{R.attr.state_hovered}, -1);
            }
        } else {
            i9 = 2;
            this.P = 0;
            this.f4156y0 = 0;
            this.f4158z0 = 0;
            this.A0 = 0;
            this.B0 = 0;
        }
        int i16 = R$styleable.TextInputLayout_android_textColorHint;
        if (e0VarI.s(i16)) {
            ColorStateList colorStateListC2 = e0VarI.c(i16);
            this.f4146t0 = colorStateListC2;
            this.f4144s0 = colorStateListC2;
        }
        int i17 = R$styleable.TextInputLayout_boxStrokeColor;
        ColorStateList colorStateListB2 = b6.c.b(context2, e0VarI, i17);
        this.f4152w0 = e0VarI.b(i17, 0);
        this.f4148u0 = e0.a.b(context2, R$color.mtrl_textinput_default_box_stroke_color);
        this.C0 = e0.a.b(context2, R$color.mtrl_textinput_disabled_color);
        this.f4150v0 = e0.a.b(context2, R$color.mtrl_textinput_hovered_box_stroke_color);
        if (colorStateListB2 != null) {
            setBoxStrokeColorStateList(colorStateListB2);
        }
        int i18 = R$styleable.TextInputLayout_boxStrokeErrorColor;
        if (e0VarI.s(i18)) {
            setBoxStrokeErrorColor(b6.c.b(context2, e0VarI, i18));
        }
        if (e0VarI.n(i15, -1) != -1) {
            setHintTextAppearance(e0VarI.n(i15, 0));
        }
        int iN = e0VarI.n(i13, 0);
        CharSequence charSequenceP = e0VarI.p(R$styleable.TextInputLayout_errorContentDescription);
        boolean zA = e0VarI.a(R$styleable.TextInputLayout_errorEnabled, false);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int i19 = R$layout.design_text_input_end_icon;
        CheckableImageButton checkableImageButton = (CheckableImageButton) layoutInflaterFrom.inflate(i19, (ViewGroup) linearLayout2, false);
        this.f4140q0 = checkableImageButton;
        checkableImageButton.setVisibility(8);
        int i20 = R$styleable.TextInputLayout_errorIconDrawable;
        if (e0VarI.s(i20)) {
            setErrorIconDrawable(e0VarI.g(i20));
        }
        int i21 = R$styleable.TextInputLayout_errorIconTint;
        if (e0VarI.s(i21)) {
            setErrorIconTintList(b6.c.b(context2, e0VarI, i21));
        }
        int i22 = R$styleable.TextInputLayout_errorIconTintMode;
        if (e0VarI.s(i22)) {
            setErrorIconTintMode(p.i(e0VarI.k(i22, -1), null));
        }
        checkableImageButton.setContentDescription(getResources().getText(R$string.error_icon_content_description));
        t.v0(checkableImageButton, i9);
        checkableImageButton.setClickable(false);
        checkableImageButton.setPressable(false);
        checkableImageButton.setFocusable(false);
        int iN2 = e0VarI.n(i14, 0);
        boolean zA2 = e0VarI.a(R$styleable.TextInputLayout_helperTextEnabled, false);
        CharSequence charSequenceP2 = e0VarI.p(R$styleable.TextInputLayout_helperText);
        int iN3 = e0VarI.n(R$styleable.TextInputLayout_placeholderTextAppearance, 0);
        CharSequence charSequenceP3 = e0VarI.p(R$styleable.TextInputLayout_placeholderText);
        int iN4 = e0VarI.n(R$styleable.TextInputLayout_prefixTextAppearance, 0);
        CharSequence charSequenceP4 = e0VarI.p(R$styleable.TextInputLayout_prefixText);
        int iN5 = e0VarI.n(R$styleable.TextInputLayout_suffixTextAppearance, 0);
        CharSequence charSequenceP5 = e0VarI.p(R$styleable.TextInputLayout_suffixText);
        boolean zA3 = e0VarI.a(R$styleable.TextInputLayout_counterEnabled, false);
        setCounterMaxLength(e0VarI.k(R$styleable.TextInputLayout_counterMaxLength, -1));
        this.f4139q = e0VarI.n(i11, 0);
        this.f4137p = e0VarI.n(i12, 0);
        CheckableImageButton checkableImageButton2 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(R$layout.design_text_input_start_icon, (ViewGroup) this.f4117f, false);
        this.U = checkableImageButton2;
        checkableImageButton2.setVisibility(8);
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        int i23 = R$styleable.TextInputLayout_startIconDrawable;
        if (e0VarI.s(i23)) {
            setStartIconDrawable(e0VarI.g(i23));
            int i24 = R$styleable.TextInputLayout_startIconContentDescription;
            if (e0VarI.s(i24)) {
                setStartIconContentDescription(e0VarI.p(i24));
            }
            setStartIconCheckable(e0VarI.a(R$styleable.TextInputLayout_startIconCheckable, true));
        }
        int i25 = R$styleable.TextInputLayout_startIconTint;
        if (e0VarI.s(i25)) {
            setStartIconTintList(b6.c.b(context2, e0VarI, i25));
        }
        int i26 = R$styleable.TextInputLayout_startIconTintMode;
        if (e0VarI.s(i26)) {
            setStartIconTintMode(p.i(e0VarI.k(i26, -1), null));
        }
        setBoxBackgroundMode(e0VarI.k(R$styleable.TextInputLayout_boxBackgroundMode, 0));
        this.f4118f0 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(i19, (ViewGroup) this.f4121h, false);
        this.f4121h.addView(this.f4118f0);
        this.f4118f0.setVisibility(8);
        this.f4116e0.append(-1, new h6.b(this));
        this.f4116e0.append(0, new h6.g(this));
        this.f4116e0.append(1, new h6.h(this));
        this.f4116e0.append(2, new h6.a(this));
        this.f4116e0.append(3, new h6.d(this));
        int i27 = R$styleable.TextInputLayout_endIconMode;
        if (e0VarI.s(i27)) {
            setEndIconMode(e0VarI.k(i27, 0));
            int i28 = R$styleable.TextInputLayout_endIconDrawable;
            if (e0VarI.s(i28)) {
                setEndIconDrawable(e0VarI.g(i28));
            }
            int i29 = R$styleable.TextInputLayout_endIconContentDescription;
            if (e0VarI.s(i29)) {
                setEndIconContentDescription(e0VarI.p(i29));
            }
            setEndIconCheckable(e0VarI.a(R$styleable.TextInputLayout_endIconCheckable, true));
        } else {
            int i30 = R$styleable.TextInputLayout_passwordToggleEnabled;
            if (e0VarI.s(i30)) {
                setEndIconMode(e0VarI.a(i30, false) ? 1 : 0);
                setEndIconDrawable(e0VarI.g(R$styleable.TextInputLayout_passwordToggleDrawable));
                setEndIconContentDescription(e0VarI.p(R$styleable.TextInputLayout_passwordToggleContentDescription));
                int i31 = R$styleable.TextInputLayout_passwordToggleTint;
                if (e0VarI.s(i31)) {
                    setEndIconTintList(b6.c.b(context2, e0VarI, i31));
                }
                int i32 = R$styleable.TextInputLayout_passwordToggleTintMode;
                if (e0VarI.s(i32)) {
                    setEndIconTintMode(p.i(e0VarI.k(i32, -1), null));
                }
            }
        }
        if (!e0VarI.s(R$styleable.TextInputLayout_passwordToggleEnabled)) {
            int i33 = R$styleable.TextInputLayout_endIconTint;
            if (e0VarI.s(i33)) {
                setEndIconTintList(b6.c.b(context2, e0VarI, i33));
            }
            int i34 = R$styleable.TextInputLayout_endIconTintMode;
            if (e0VarI.s(i34)) {
                setEndIconTintMode(p.i(e0VarI.k(i34, -1), null));
            }
        }
        AppCompatTextView appCompatTextView = new AppCompatTextView(context2);
        this.f4157z = appCompatTextView;
        appCompatTextView.setId(R$id.textinput_prefix_text);
        appCompatTextView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        t.n0(appCompatTextView, 1);
        this.f4117f.addView(this.U);
        this.f4117f.addView(appCompatTextView);
        AppCompatTextView appCompatTextView2 = new AppCompatTextView(context2);
        this.B = appCompatTextView2;
        appCompatTextView2.setId(R$id.textinput_suffix_text);
        appCompatTextView2.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 80));
        t.n0(appCompatTextView2, 1);
        this.f4119g.addView(appCompatTextView2);
        this.f4119g.addView(this.f4140q0);
        this.f4119g.addView(this.f4121h);
        setHelperTextEnabled(zA2);
        setHelperText(charSequenceP2);
        setHelperTextTextAppearance(iN2);
        setErrorEnabled(zA);
        setErrorTextAppearance(iN);
        setErrorContentDescription(charSequenceP);
        setCounterTextAppearance(this.f4139q);
        setCounterOverflowTextAppearance(this.f4137p);
        setPlaceholderText(charSequenceP3);
        setPlaceholderTextAppearance(iN3);
        setPrefixText(charSequenceP4);
        setPrefixTextAppearance(iN4);
        setSuffixText(charSequenceP5);
        setSuffixTextAppearance(iN5);
        int i35 = R$styleable.TextInputLayout_errorTextColor;
        if (e0VarI.s(i35)) {
            setErrorTextColor(e0VarI.c(i35));
        }
        int i36 = R$styleable.TextInputLayout_helperTextTextColor;
        if (e0VarI.s(i36)) {
            setHelperTextColor(e0VarI.c(i36));
        }
        int i37 = R$styleable.TextInputLayout_hintTextColor;
        if (e0VarI.s(i37)) {
            setHintTextColor(e0VarI.c(i37));
        }
        int i38 = R$styleable.TextInputLayout_counterTextColor;
        if (e0VarI.s(i38)) {
            setCounterTextColor(e0VarI.c(i38));
        }
        int i39 = R$styleable.TextInputLayout_counterOverflowTextColor;
        if (e0VarI.s(i39)) {
            setCounterOverflowTextColor(e0VarI.c(i39));
        }
        int i40 = R$styleable.TextInputLayout_placeholderTextColor;
        if (e0VarI.s(i40)) {
            setPlaceholderTextColor(e0VarI.c(i40));
        }
        int i41 = R$styleable.TextInputLayout_prefixTextColor;
        if (e0VarI.s(i41)) {
            setPrefixTextColor(e0VarI.c(i41));
        }
        int i42 = R$styleable.TextInputLayout_suffixTextColor;
        if (e0VarI.s(i42)) {
            setSuffixTextColor(e0VarI.c(i42));
        }
        setCounterEnabled(zA3);
        setEnabled(e0VarI.a(R$styleable.TextInputLayout_android_enabled, true));
        e0VarI.w();
        t.v0(this, 2);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence charSequence) {
        this.f4118f0.setContentDescription(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable drawable) {
        this.f4118f0.setImageDrawable(drawable);
    }
}
