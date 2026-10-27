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
import i2.k;
import i2.p;
import java.util.Iterator;
import java.util.LinkedHashSet;
import l0.t;
import n0.i;
import n2.h;
import n2.m;
import o.e0;
import o.q;

/* JADX INFO: loaded from: classes.dex */
public class TextInputLayout extends LinearLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f2533b = R$style.Widget_Design_TextInputLayout;
    public boolean A;
    public int A0;
    public CharSequence B;
    public boolean B0;
    public boolean C;
    public final i2.a C0;
    public h D;
    public boolean D0;
    public h E;
    public ValueAnimator E0;
    public m F;
    public boolean F0;
    public final int G;
    public boolean G0;
    public int H;
    public final int I;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public final Rect O;
    public final Rect P;
    public final RectF Q;
    public Typeface R;
    public final CheckableImageButton S;
    public ColorStateList T;
    public boolean U;
    public PorterDuff.Mode V;
    public boolean W;
    public Drawable X;
    public int Y;
    public View.OnLongClickListener Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public final LinkedHashSet<f> f2534a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public int f2535b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final FrameLayout f2536c;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public final SparseArray<q2.e> f2537c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinearLayout f2538d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public final CheckableImageButton f2539d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LinearLayout f2540e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final LinkedHashSet<g> f2541e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final FrameLayout f2542f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public ColorStateList f2543f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public EditText f2544g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public boolean f2545g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public CharSequence f2546h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public PorterDuff.Mode f2547h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final q2.f f2548i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public boolean f2549i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f2550j;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public Drawable f2551j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2552k;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public int f2553k0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f2554l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public Drawable f2555l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public TextView f2556m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public View.OnLongClickListener f2557m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2558n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public View.OnLongClickListener f2559n0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f2560o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public final CheckableImageButton f2561o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public CharSequence f2562p;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public ColorStateList f2563p0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f2564q;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public ColorStateList f2565q0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public TextView f2566r;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public ColorStateList f2567r0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ColorStateList f2568s;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public int f2569s0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f2570t;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public int f2571t0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ColorStateList f2572u;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public int f2573u0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ColorStateList f2574v;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public ColorStateList f2575v0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public CharSequence f2576w;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public int f2577w0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final TextView f2578x;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public int f2579x0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public CharSequence f2580y;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public int f2581y0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final TextView f2582z;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public int f2583z0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CharSequence f2584d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f2585e;

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
            public SavedState[] newArray(int i3) {
                return new SavedState[i3];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.f2584d) + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            TextUtils.writeToParcel(this.f2584d, parcel, i3);
            parcel.writeInt(this.f2585e ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2584d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f2585e = parcel.readInt() == 1;
        }
    }

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            TextInputLayout.this.o0(!r0.G0);
            TextInputLayout textInputLayout = TextInputLayout.this;
            if (textInputLayout.f2550j) {
                textInputLayout.g0(editable.length());
            }
            if (TextInputLayout.this.f2564q) {
                TextInputLayout.this.s0(editable.length());
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.f2539d0.performClick();
            TextInputLayout.this.f2539d0.jumpDrawablesToCurrentState();
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.f2544g.requestLayout();
        }
    }

    public class d implements ValueAnimator.AnimatorUpdateListener {
        public d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            TextInputLayout.this.C0.d0(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public static class e extends l0.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextInputLayout f2590d;

        public e(TextInputLayout textInputLayout) {
            this.f2590d = textInputLayout;
        }

        @Override // l0.a
        public void g(View view, m0.c cVar) {
            super.g(view, cVar);
            EditText editText = this.f2590d.getEditText();
            CharSequence text = editText != null ? editText.getText() : null;
            CharSequence hint = this.f2590d.getHint();
            CharSequence helperText = this.f2590d.getHelperText();
            CharSequence error = this.f2590d.getError();
            int counterMaxLength = this.f2590d.getCounterMaxLength();
            CharSequence counterOverflowDescription = this.f2590d.getCounterOverflowDescription();
            boolean z3 = !TextUtils.isEmpty(text);
            boolean z4 = !TextUtils.isEmpty(hint);
            boolean z5 = !TextUtils.isEmpty(helperText);
            boolean z6 = !TextUtils.isEmpty(error);
            boolean z7 = z6 || !TextUtils.isEmpty(counterOverflowDescription);
            String string = z4 ? hint.toString() : "";
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append(((z6 || z5) && !TextUtils.isEmpty(string)) ? ", " : "");
            String string2 = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(string2);
            if (z6) {
                helperText = error;
            } else if (!z5) {
                helperText = "";
            }
            sb2.append((Object) helperText);
            String string3 = sb2.toString();
            if (z3) {
                cVar.A0(text);
            } else if (!TextUtils.isEmpty(string3)) {
                cVar.A0(string3);
            }
            if (!TextUtils.isEmpty(string3)) {
                if (Build.VERSION.SDK_INT >= 26) {
                    cVar.l0(string3);
                } else {
                    if (z3) {
                        string3 = ((Object) text) + ", " + string3;
                    }
                    cVar.A0(string3);
                }
                cVar.x0(!z3);
            }
            if (text == null || text.length() != counterMaxLength) {
                counterMaxLength = -1;
            }
            cVar.n0(counterMaxLength);
            if (z7) {
                if (!z6) {
                    error = counterOverflowDescription;
                }
                cVar.h0(error);
            }
        }
    }

    public interface f {
        void a(TextInputLayout textInputLayout);
    }

    public interface g {
        void a(TextInputLayout textInputLayout, int i3);
    }

    public TextInputLayout(Context context) {
        this(context, null);
    }

    public static void S(ViewGroup viewGroup, boolean z3) {
        int childCount = viewGroup.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = viewGroup.getChildAt(i3);
            childAt.setEnabled(z3);
            if (childAt instanceof ViewGroup) {
                S((ViewGroup) childAt, z3);
            }
        }
    }

    public static void V(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        boolean zK = t.K(checkableImageButton);
        boolean z3 = onLongClickListener != null;
        boolean z4 = zK || z3;
        checkableImageButton.setFocusable(z4);
        checkableImageButton.setClickable(zK);
        checkableImageButton.setPressable(zK);
        checkableImageButton.setLongClickable(z3);
        t.t0(checkableImageButton, z4 ? 1 : 2);
    }

    public static void W(CheckableImageButton checkableImageButton, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        checkableImageButton.setOnClickListener(onClickListener);
        V(checkableImageButton, onLongClickListener);
    }

    public static void X(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        V(checkableImageButton, onLongClickListener);
    }

    private q2.e getEndIconDelegate() {
        q2.e eVar = this.f2537c0.get(this.f2535b0);
        return eVar != null ? eVar : this.f2537c0.get(0);
    }

    private CheckableImageButton getEndIconToUpdateDummyDrawable() {
        if (this.f2561o0.getVisibility() == 0) {
            return this.f2561o0;
        }
        if (H() && J()) {
            return this.f2539d0;
        }
        return null;
    }

    public static void h0(Context context, TextView textView, int i3, int i4, boolean z3) {
        textView.setContentDescription(context.getString(z3 ? R$string.character_counter_overflowed_content_description : R$string.character_counter_content_description, Integer.valueOf(i3), Integer.valueOf(i4)));
    }

    private void setEditText(EditText editText) {
        if (this.f2544g != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (this.f2535b0 != 3 && !(editText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.f2544g = editText;
        Q();
        setTextInputAccessibilityDelegate(new e(this));
        this.C0.k0(this.f2544g.getTypeface());
        this.C0.a0(this.f2544g.getTextSize());
        int gravity = this.f2544g.getGravity();
        this.C0.R((gravity & (-113)) | 48);
        this.C0.Z(gravity);
        this.f2544g.addTextChangedListener(new a());
        if (this.f2565q0 == null) {
            this.f2565q0 = this.f2544g.getHintTextColors();
        }
        if (this.A) {
            if (TextUtils.isEmpty(this.B)) {
                CharSequence hint = this.f2544g.getHint();
                this.f2546h = hint;
                setHint(hint);
                this.f2544g.setHint((CharSequence) null);
            }
            this.C = true;
        }
        if (this.f2556m != null) {
            g0(this.f2544g.getText().length());
        }
        k0();
        this.f2548i.e();
        this.f2538d.bringToFront();
        this.f2540e.bringToFront();
        this.f2542f.bringToFront();
        this.f2561o0.bringToFront();
        A();
        t0();
        w0();
        if (!isEnabled()) {
            editText.setEnabled(false);
        }
        p0(false, true);
    }

    private void setErrorIconVisible(boolean z3) {
        this.f2561o0.setVisibility(z3 ? 0 : 8);
        this.f2542f.setVisibility(z3 ? 8 : 0);
        w0();
        if (H()) {
            return;
        }
        j0();
    }

    private void setHintInternal(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.B)) {
            return;
        }
        this.B = charSequence;
        this.C0.i0(charSequence);
        if (this.B0) {
            return;
        }
        R();
    }

    private void setPlaceholderTextEnabled(boolean z3) {
        if (this.f2564q == z3) {
            return;
        }
        if (z3) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
            this.f2566r = appCompatTextView;
            appCompatTextView.setId(R$id.textinput_placeholder);
            t.l0(this.f2566r, 1);
            setPlaceholderTextAppearance(this.f2570t);
            setPlaceholderTextColor(this.f2568s);
            g();
        } else {
            T();
            this.f2566r = null;
        }
        this.f2564q = z3;
    }

    public final void A() {
        Iterator<f> it = this.f2534a0.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    public final void B(int i3) {
        Iterator<g> it = this.f2541e0.iterator();
        while (it.hasNext()) {
            it.next().a(this, i3);
        }
    }

    public final void C(Canvas canvas) {
        h hVar = this.E;
        if (hVar != null) {
            Rect bounds = hVar.getBounds();
            bounds.top = bounds.bottom - this.J;
            this.E.draw(canvas);
        }
    }

    public final void D(Canvas canvas) {
        if (this.A) {
            this.C0.j(canvas);
        }
    }

    public final void E(boolean z3) {
        ValueAnimator valueAnimator = this.E0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.E0.cancel();
        }
        if (z3 && this.D0) {
            h(0.0f);
        } else {
            this.C0.d0(0.0f);
        }
        if (z() && ((q2.c) this.D).n0()) {
            x();
        }
        this.B0 = true;
        I();
        u0();
        x0();
    }

    public final int F(int i3, boolean z3) {
        int compoundPaddingLeft = i3 + this.f2544g.getCompoundPaddingLeft();
        return (this.f2576w == null || z3) ? compoundPaddingLeft : (compoundPaddingLeft - this.f2578x.getMeasuredWidth()) + this.f2578x.getPaddingLeft();
    }

    public final int G(int i3, boolean z3) {
        int compoundPaddingRight = i3 - this.f2544g.getCompoundPaddingRight();
        return (this.f2576w == null || !z3) ? compoundPaddingRight : compoundPaddingRight + (this.f2578x.getMeasuredWidth() - this.f2578x.getPaddingRight());
    }

    public final boolean H() {
        return this.f2535b0 != 0;
    }

    public final void I() {
        TextView textView = this.f2566r;
        if (textView == null || !this.f2564q) {
            return;
        }
        textView.setText((CharSequence) null);
        this.f2566r.setVisibility(4);
    }

    public boolean J() {
        return this.f2542f.getVisibility() == 0 && this.f2539d0.getVisibility() == 0;
    }

    public final boolean K() {
        return this.f2561o0.getVisibility() == 0;
    }

    public boolean L() {
        return this.f2548i.x();
    }

    public final boolean M() {
        return this.B0;
    }

    public boolean N() {
        return this.C;
    }

    public final boolean O() {
        return this.H == 1 && (Build.VERSION.SDK_INT < 16 || this.f2544g.getMinLines() <= 1);
    }

    public boolean P() {
        return this.S.getVisibility() == 0;
    }

    public final void Q() {
        o();
        U();
        y0();
        if (this.H != 0) {
            n0();
        }
    }

    public final void R() {
        if (z()) {
            RectF rectF = this.Q;
            this.C0.m(rectF, this.f2544g.getWidth(), this.f2544g.getGravity());
            k(rectF);
            rectF.offset(-getPaddingLeft(), -getPaddingTop());
            ((q2.c) this.D).t0(rectF);
        }
    }

    public final void T() {
        TextView textView = this.f2566r;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    public final void U() {
        if (b0()) {
            t.m0(this.f2544g, this.D);
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
            n0.i.q(r3, r4)     // Catch: java.lang.Exception -> L1a
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
            n0.i.q(r3, r4)
            android.content.Context r4 = r2.getContext()
            int r0 = com.google.android.material.R$color.design_error
            int r4 = b0.a.b(r4, r0)
            r3.setTextColor(r4)
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.Y(android.widget.TextView, int):void");
    }

    public final boolean Z() {
        return (this.f2561o0.getVisibility() == 0 || ((H() && J()) || this.f2580y != null)) && this.f2540e.getMeasuredWidth() > 0;
    }

    public final boolean a0() {
        return !(getStartIconDrawable() == null && this.f2576w == null) && this.f2538d.getMeasuredWidth() > 0;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i3, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i3, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
        this.f2536c.addView(view, layoutParams2);
        this.f2536c.setLayoutParams(layoutParams);
        n0();
        setEditText((EditText) view);
    }

    public final boolean b0() {
        EditText editText = this.f2544g;
        return (editText == null || this.D == null || editText.getBackground() != null || this.H == 0) ? false : true;
    }

    public final void c0() {
        TextView textView = this.f2566r;
        if (textView == null || !this.f2564q) {
            return;
        }
        textView.setText(this.f2562p);
        this.f2566r.setVisibility(0);
        this.f2566r.bringToFront();
    }

    public final void d0(boolean z3) {
        if (!z3 || getEndIconDrawable() == null) {
            l();
            return;
        }
        Drawable drawableMutate = e0.a.r(getEndIconDrawable()).mutate();
        e0.a.n(drawableMutate, this.f2548i.o());
        this.f2539d0.setImageDrawable(drawableMutate);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i3) {
        EditText editText;
        if (this.f2546h == null || (editText = this.f2544g) == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i3);
            return;
        }
        boolean z3 = this.C;
        this.C = false;
        CharSequence hint = editText.getHint();
        this.f2544g.setHint(this.f2546h);
        try {
            super.dispatchProvideAutofillStructure(viewStructure, i3);
        } finally {
            this.f2544g.setHint(hint);
            this.C = z3;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        this.G0 = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.G0 = false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        D(canvas);
        C(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        if (this.F0) {
            return;
        }
        this.F0 = true;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        i2.a aVar = this.C0;
        boolean zH0 = aVar != null ? aVar.h0(drawableState) | false : false;
        if (this.f2544g != null) {
            o0(t.P(this) && isEnabled());
        }
        k0();
        y0();
        if (zH0) {
            invalidate();
        }
        this.F0 = false;
    }

    public void e(f fVar) {
        this.f2534a0.add(fVar);
        if (this.f2544g != null) {
            fVar.a(this);
        }
    }

    public final void e0(Rect rect) {
        h hVar = this.E;
        if (hVar != null) {
            int i3 = rect.bottom;
            hVar.setBounds(rect.left, i3 - this.L, rect.right, i3);
        }
    }

    public void f(g gVar) {
        this.f2541e0.add(gVar);
    }

    public final void f0() {
        if (this.f2556m != null) {
            EditText editText = this.f2544g;
            g0(editText == null ? 0 : editText.getText().length());
        }
    }

    public final void g() {
        TextView textView = this.f2566r;
        if (textView != null) {
            this.f2536c.addView(textView);
            this.f2566r.setVisibility(0);
        }
    }

    public void g0(int i3) {
        boolean z3 = this.f2554l;
        int i4 = this.f2552k;
        if (i4 == -1) {
            this.f2556m.setText(String.valueOf(i3));
            this.f2556m.setContentDescription(null);
            this.f2554l = false;
        } else {
            this.f2554l = i3 > i4;
            h0(getContext(), this.f2556m, i3, this.f2552k, this.f2554l);
            if (z3 != this.f2554l) {
                i0();
            }
            this.f2556m.setText(j0.a.c().j(getContext().getString(R$string.character_counter_pattern, Integer.valueOf(i3), Integer.valueOf(this.f2552k))));
        }
        if (this.f2544g == null || z3 == this.f2554l) {
            return;
        }
        o0(false);
        y0();
        k0();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.f2544g;
        return editText != null ? editText.getBaseline() + getPaddingTop() + u() : super.getBaseline();
    }

    public h getBoxBackground() {
        int i3 = this.H;
        if (i3 == 1 || i3 == 2) {
            return this.D;
        }
        throw new IllegalStateException();
    }

    public int getBoxBackgroundColor() {
        return this.N;
    }

    public int getBoxBackgroundMode() {
        return this.H;
    }

    public float getBoxCornerRadiusBottomEnd() {
        return this.D.s();
    }

    public float getBoxCornerRadiusBottomStart() {
        return this.D.t();
    }

    public float getBoxCornerRadiusTopEnd() {
        return this.D.H();
    }

    public float getBoxCornerRadiusTopStart() {
        return this.D.G();
    }

    public int getBoxStrokeColor() {
        return this.f2573u0;
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.f2575v0;
    }

    public int getBoxStrokeWidth() {
        return this.K;
    }

    public int getBoxStrokeWidthFocused() {
        return this.L;
    }

    public int getCounterMaxLength() {
        return this.f2552k;
    }

    public CharSequence getCounterOverflowDescription() {
        TextView textView;
        if (this.f2550j && this.f2554l && (textView = this.f2556m) != null) {
            return textView.getContentDescription();
        }
        return null;
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.f2572u;
    }

    public ColorStateList getCounterTextColor() {
        return this.f2572u;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.f2565q0;
    }

    public EditText getEditText() {
        return this.f2544g;
    }

    public CharSequence getEndIconContentDescription() {
        return this.f2539d0.getContentDescription();
    }

    public Drawable getEndIconDrawable() {
        return this.f2539d0.getDrawable();
    }

    public int getEndIconMode() {
        return this.f2535b0;
    }

    public CheckableImageButton getEndIconView() {
        return this.f2539d0;
    }

    public CharSequence getError() {
        if (this.f2548i.w()) {
            return this.f2548i.n();
        }
        return null;
    }

    public CharSequence getErrorContentDescription() {
        return this.f2548i.m();
    }

    public int getErrorCurrentTextColors() {
        return this.f2548i.o();
    }

    public Drawable getErrorIconDrawable() {
        return this.f2561o0.getDrawable();
    }

    public final int getErrorTextCurrentColor() {
        return this.f2548i.o();
    }

    public CharSequence getHelperText() {
        if (this.f2548i.x()) {
            return this.f2548i.q();
        }
        return null;
    }

    public int getHelperTextCurrentTextColor() {
        return this.f2548i.r();
    }

    public CharSequence getHint() {
        if (this.A) {
            return this.B;
        }
        return null;
    }

    public final float getHintCollapsedTextHeight() {
        return this.C0.p();
    }

    public final int getHintCurrentCollapsedTextColor() {
        return this.C0.t();
    }

    public ColorStateList getHintTextColor() {
        return this.f2567r0;
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.f2539d0.getContentDescription();
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.f2539d0.getDrawable();
    }

    public CharSequence getPlaceholderText() {
        if (this.f2564q) {
            return this.f2562p;
        }
        return null;
    }

    public int getPlaceholderTextAppearance() {
        return this.f2570t;
    }

    public ColorStateList getPlaceholderTextColor() {
        return this.f2568s;
    }

    public CharSequence getPrefixText() {
        return this.f2576w;
    }

    public ColorStateList getPrefixTextColor() {
        return this.f2578x.getTextColors();
    }

    public TextView getPrefixTextView() {
        return this.f2578x;
    }

    public CharSequence getStartIconContentDescription() {
        return this.S.getContentDescription();
    }

    public Drawable getStartIconDrawable() {
        return this.S.getDrawable();
    }

    public CharSequence getSuffixText() {
        return this.f2580y;
    }

    public ColorStateList getSuffixTextColor() {
        return this.f2582z.getTextColors();
    }

    public TextView getSuffixTextView() {
        return this.f2582z;
    }

    public Typeface getTypeface() {
        return this.R;
    }

    public void h(float f3) {
        if (this.C0.z() == f3) {
            return;
        }
        if (this.E0 == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.E0 = valueAnimator;
            valueAnimator.setInterpolator(s1.a.f4902b);
            this.E0.setDuration(167L);
            this.E0.addUpdateListener(new d());
        }
        this.E0.setFloatValues(this.C0.z(), f3);
        this.E0.start();
    }

    public final void i() {
        h hVar = this.D;
        if (hVar == null) {
            return;
        }
        hVar.setShapeAppearanceModel(this.F);
        if (v()) {
            this.D.g0(this.J, this.M);
        }
        int iP = p();
        this.N = iP;
        this.D.X(ColorStateList.valueOf(iP));
        if (this.f2535b0 == 3) {
            this.f2544g.getBackground().invalidateSelf();
        }
        j();
        invalidate();
    }

    public final void i0() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        TextView textView = this.f2556m;
        if (textView != null) {
            Y(textView, this.f2554l ? this.f2558n : this.f2560o);
            if (!this.f2554l && (colorStateList2 = this.f2572u) != null) {
                this.f2556m.setTextColor(colorStateList2);
            }
            if (!this.f2554l || (colorStateList = this.f2574v) == null) {
                return;
            }
            this.f2556m.setTextColor(colorStateList);
        }
    }

    public final void j() {
        if (this.E == null) {
            return;
        }
        if (w()) {
            this.E.X(ColorStateList.valueOf(this.M));
        }
        invalidate();
    }

    public final boolean j0() {
        boolean z3;
        if (this.f2544g == null) {
            return false;
        }
        boolean z4 = true;
        if (a0()) {
            int measuredWidth = this.f2538d.getMeasuredWidth() - this.f2544g.getPaddingLeft();
            if (this.X == null || this.Y != measuredWidth) {
                ColorDrawable colorDrawable = new ColorDrawable();
                this.X = colorDrawable;
                this.Y = measuredWidth;
                colorDrawable.setBounds(0, 0, measuredWidth, 1);
            }
            Drawable[] drawableArrA = i.a(this.f2544g);
            Drawable drawable = drawableArrA[0];
            Drawable drawable2 = this.X;
            if (drawable != drawable2) {
                i.l(this.f2544g, drawable2, drawableArrA[1], drawableArrA[2], drawableArrA[3]);
                z3 = true;
            }
            z3 = false;
        } else {
            if (this.X != null) {
                Drawable[] drawableArrA2 = i.a(this.f2544g);
                i.l(this.f2544g, null, drawableArrA2[1], drawableArrA2[2], drawableArrA2[3]);
                this.X = null;
                z3 = true;
            }
            z3 = false;
        }
        if (Z()) {
            int measuredWidth2 = this.f2582z.getMeasuredWidth() - this.f2544g.getPaddingRight();
            CheckableImageButton endIconToUpdateDummyDrawable = getEndIconToUpdateDummyDrawable();
            if (endIconToUpdateDummyDrawable != null) {
                measuredWidth2 = measuredWidth2 + endIconToUpdateDummyDrawable.getMeasuredWidth() + l0.g.b((ViewGroup.MarginLayoutParams) endIconToUpdateDummyDrawable.getLayoutParams());
            }
            Drawable[] drawableArrA3 = i.a(this.f2544g);
            Drawable drawable3 = this.f2551j0;
            if (drawable3 == null || this.f2553k0 == measuredWidth2) {
                if (drawable3 == null) {
                    ColorDrawable colorDrawable2 = new ColorDrawable();
                    this.f2551j0 = colorDrawable2;
                    this.f2553k0 = measuredWidth2;
                    colorDrawable2.setBounds(0, 0, measuredWidth2, 1);
                }
                Drawable drawable4 = drawableArrA3[2];
                Drawable drawable5 = this.f2551j0;
                if (drawable4 != drawable5) {
                    this.f2555l0 = drawableArrA3[2];
                    i.l(this.f2544g, drawableArrA3[0], drawableArrA3[1], drawable5, drawableArrA3[3]);
                } else {
                    z4 = z3;
                }
            } else {
                this.f2553k0 = measuredWidth2;
                drawable3.setBounds(0, 0, measuredWidth2, 1);
                i.l(this.f2544g, drawableArrA3[0], drawableArrA3[1], this.f2551j0, drawableArrA3[3]);
            }
        } else {
            if (this.f2551j0 == null) {
                return z3;
            }
            Drawable[] drawableArrA4 = i.a(this.f2544g);
            if (drawableArrA4[2] == this.f2551j0) {
                i.l(this.f2544g, drawableArrA4[0], drawableArrA4[1], this.f2555l0, drawableArrA4[3]);
            } else {
                z4 = z3;
            }
            this.f2551j0 = null;
        }
        return z4;
    }

    public final void k(RectF rectF) {
        float f3 = rectF.left;
        int i3 = this.G;
        rectF.left = f3 - i3;
        rectF.top -= i3;
        rectF.right += i3;
        rectF.bottom += i3;
    }

    public void k0() {
        Drawable background;
        TextView textView;
        EditText editText = this.f2544g;
        if (editText == null || this.H != 0 || (background = editText.getBackground()) == null) {
            return;
        }
        if (q.a(background)) {
            background = background.mutate();
        }
        if (this.f2548i.k()) {
            background.setColorFilter(o.f.e(this.f2548i.o(), PorterDuff.Mode.SRC_IN));
        } else if (this.f2554l && (textView = this.f2556m) != null) {
            background.setColorFilter(o.f.e(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            e0.a.c(background);
            this.f2544g.refreshDrawableState();
        }
    }

    public final void l() {
        m(this.f2539d0, this.f2545g0, this.f2543f0, this.f2549i0, this.f2547h0);
    }

    public final boolean l0() {
        int iMax;
        if (this.f2544g == null || this.f2544g.getMeasuredHeight() >= (iMax = Math.max(this.f2540e.getMeasuredHeight(), this.f2538d.getMeasuredHeight()))) {
            return false;
        }
        this.f2544g.setMinimumHeight(iMax);
        return true;
    }

    public final void m(CheckableImageButton checkableImageButton, boolean z3, ColorStateList colorStateList, boolean z4, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null && (z3 || z4)) {
            drawable = e0.a.r(drawable).mutate();
            if (z3) {
                e0.a.o(drawable, colorStateList);
            }
            if (z4) {
                e0.a.p(drawable, mode);
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
        Drawable drawableMutate = e0.a.r(drawable).mutate();
        e0.a.o(drawableMutate, ColorStateList.valueOf(colorForState));
        checkableImageButton.setImageDrawable(drawableMutate);
    }

    public final void n() {
        m(this.S, this.U, this.T, this.W, this.V);
    }

    public final void n0() {
        if (this.H != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f2536c.getLayoutParams();
            int iU = u();
            if (iU != layoutParams.topMargin) {
                layoutParams.topMargin = iU;
                this.f2536c.requestLayout();
            }
        }
    }

    public final void o() {
        int i3 = this.H;
        if (i3 == 0) {
            this.D = null;
            this.E = null;
            return;
        }
        if (i3 == 1) {
            this.D = new h(this.F);
            this.E = new h();
        } else {
            if (i3 != 2) {
                throw new IllegalArgumentException(this.H + " is illegal; only @BoxBackgroundMode constants are supported.");
            }
            if (!this.A || (this.D instanceof q2.c)) {
                this.D = new h(this.F);
            } else {
                this.D = new q2.c(this.F);
            }
            this.E = null;
        }
    }

    public void o0(boolean z3) {
        p0(z3, false);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        super.onLayout(z3, i3, i4, i5, i6);
        EditText editText = this.f2544g;
        if (editText != null) {
            Rect rect = this.O;
            i2.c.a(this, editText, rect);
            e0(rect);
            if (this.A) {
                this.C0.a0(this.f2544g.getTextSize());
                int gravity = this.f2544g.getGravity();
                this.C0.R((gravity & (-113)) | 48);
                this.C0.Z(gravity);
                this.C0.N(q(rect));
                this.C0.V(t(rect));
                this.C0.K();
                if (!z() || this.B0) {
                    return;
                }
                R();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i3, int i4) {
        super.onMeasure(i3, i4);
        boolean zL0 = l0();
        boolean zJ0 = j0();
        if (zL0 || zJ0) {
            this.f2544g.post(new c());
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
        setError(savedState.f2584d);
        if (savedState.f2585e) {
            this.f2539d0.post(new b());
        }
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.f2548i.k()) {
            savedState.f2584d = getError();
        }
        savedState.f2585e = H() && this.f2539d0.isChecked();
        return savedState;
    }

    public final int p() {
        return this.H == 1 ? c2.a.e(c2.a.d(this, R$attr.colorSurface, 0), this.N) : this.N;
    }

    public final void p0(boolean z3, boolean z4) {
        ColorStateList colorStateList;
        TextView textView;
        boolean zIsEnabled = isEnabled();
        EditText editText = this.f2544g;
        boolean z5 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.f2544g;
        boolean z6 = editText2 != null && editText2.hasFocus();
        boolean zK = this.f2548i.k();
        ColorStateList colorStateList2 = this.f2565q0;
        if (colorStateList2 != null) {
            this.C0.Q(colorStateList2);
            this.C0.Y(this.f2565q0);
        }
        if (!zIsEnabled) {
            ColorStateList colorStateList3 = this.f2565q0;
            int colorForState = colorStateList3 != null ? colorStateList3.getColorForState(new int[]{-16842910}, this.A0) : this.A0;
            this.C0.Q(ColorStateList.valueOf(colorForState));
            this.C0.Y(ColorStateList.valueOf(colorForState));
        } else if (zK) {
            this.C0.Q(this.f2548i.p());
        } else if (this.f2554l && (textView = this.f2556m) != null) {
            this.C0.Q(textView.getTextColors());
        } else if (z6 && (colorStateList = this.f2567r0) != null) {
            this.C0.Q(colorStateList);
        }
        if (z5 || (isEnabled() && (z6 || zK))) {
            if (z4 || this.B0) {
                y(z3);
                return;
            }
            return;
        }
        if (z4 || !this.B0) {
            E(z3);
        }
    }

    public final Rect q(Rect rect) {
        if (this.f2544g == null) {
            throw new IllegalStateException();
        }
        Rect rect2 = this.P;
        boolean z3 = t.z(this) == 1;
        rect2.bottom = rect.bottom;
        int i3 = this.H;
        if (i3 == 1) {
            rect2.left = F(rect.left, z3);
            rect2.top = rect.top + this.I;
            rect2.right = G(rect.right, z3);
            return rect2;
        }
        if (i3 != 2) {
            rect2.left = F(rect.left, z3);
            rect2.top = getPaddingTop();
            rect2.right = G(rect.right, z3);
            return rect2;
        }
        rect2.left = rect.left + this.f2544g.getPaddingLeft();
        rect2.top = rect.top - u();
        rect2.right = rect.right - this.f2544g.getPaddingRight();
        return rect2;
    }

    public final void q0() {
        EditText editText;
        if (this.f2566r == null || (editText = this.f2544g) == null) {
            return;
        }
        this.f2566r.setGravity(editText.getGravity());
        this.f2566r.setPadding(this.f2544g.getCompoundPaddingLeft(), this.f2544g.getCompoundPaddingTop(), this.f2544g.getCompoundPaddingRight(), this.f2544g.getCompoundPaddingBottom());
    }

    public final int r(Rect rect, Rect rect2, float f3) {
        return O() ? (int) (rect2.top + f3) : rect.bottom - this.f2544g.getCompoundPaddingBottom();
    }

    public final void r0() {
        EditText editText = this.f2544g;
        s0(editText == null ? 0 : editText.getText().length());
    }

    public final int s(Rect rect, float f3) {
        return O() ? (int) (rect.centerY() - (f3 / 2.0f)) : rect.top + this.f2544g.getCompoundPaddingTop();
    }

    public final void s0(int i3) {
        if (i3 != 0 || this.B0) {
            I();
        } else {
            c0();
        }
    }

    public void setBoxBackgroundColor(int i3) {
        if (this.N != i3) {
            this.N = i3;
            this.f2577w0 = i3;
            this.f2581y0 = i3;
            this.f2583z0 = i3;
            i();
        }
    }

    public void setBoxBackgroundColorResource(int i3) {
        setBoxBackgroundColor(b0.a.b(getContext(), i3));
    }

    public void setBoxBackgroundColorStateList(ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.f2577w0 = defaultColor;
        this.N = defaultColor;
        this.f2579x0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
        this.f2581y0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        this.f2583z0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
        i();
    }

    public void setBoxBackgroundMode(int i3) {
        if (i3 == this.H) {
            return;
        }
        this.H = i3;
        if (this.f2544g != null) {
            Q();
        }
    }

    public void setBoxStrokeColor(int i3) {
        if (this.f2573u0 != i3) {
            this.f2573u0 = i3;
            y0();
        }
    }

    public void setBoxStrokeColorStateList(ColorStateList colorStateList) {
        if (colorStateList.isStateful()) {
            this.f2569s0 = colorStateList.getDefaultColor();
            this.A0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.f2571t0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            this.f2573u0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        } else if (this.f2573u0 != colorStateList.getDefaultColor()) {
            this.f2573u0 = colorStateList.getDefaultColor();
        }
        y0();
    }

    public void setBoxStrokeErrorColor(ColorStateList colorStateList) {
        if (this.f2575v0 != colorStateList) {
            this.f2575v0 = colorStateList;
            y0();
        }
    }

    public void setBoxStrokeWidth(int i3) {
        this.K = i3;
        y0();
    }

    public void setBoxStrokeWidthFocused(int i3) {
        this.L = i3;
        y0();
    }

    public void setBoxStrokeWidthFocusedResource(int i3) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(i3));
    }

    public void setBoxStrokeWidthResource(int i3) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(i3));
    }

    public void setCounterEnabled(boolean z3) {
        if (this.f2550j != z3) {
            if (z3) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
                this.f2556m = appCompatTextView;
                appCompatTextView.setId(R$id.textinput_counter);
                Typeface typeface = this.R;
                if (typeface != null) {
                    this.f2556m.setTypeface(typeface);
                }
                this.f2556m.setMaxLines(1);
                this.f2548i.d(this.f2556m, 2);
                l0.g.d((ViewGroup.MarginLayoutParams) this.f2556m.getLayoutParams(), getResources().getDimensionPixelOffset(R$dimen.mtrl_textinput_counter_margin_start));
                i0();
                f0();
            } else {
                this.f2548i.y(this.f2556m, 2);
                this.f2556m = null;
            }
            this.f2550j = z3;
        }
    }

    public void setCounterMaxLength(int i3) {
        if (this.f2552k != i3) {
            if (i3 > 0) {
                this.f2552k = i3;
            } else {
                this.f2552k = -1;
            }
            if (this.f2550j) {
                f0();
            }
        }
    }

    public void setCounterOverflowTextAppearance(int i3) {
        if (this.f2558n != i3) {
            this.f2558n = i3;
            i0();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList colorStateList) {
        if (this.f2574v != colorStateList) {
            this.f2574v = colorStateList;
            i0();
        }
    }

    public void setCounterTextAppearance(int i3) {
        if (this.f2560o != i3) {
            this.f2560o = i3;
            i0();
        }
    }

    public void setCounterTextColor(ColorStateList colorStateList) {
        if (this.f2572u != colorStateList) {
            this.f2572u = colorStateList;
            i0();
        }
    }

    public void setDefaultHintTextColor(ColorStateList colorStateList) {
        this.f2565q0 = colorStateList;
        this.f2567r0 = colorStateList;
        if (this.f2544g != null) {
            o0(false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z3) {
        S(this, z3);
        super.setEnabled(z3);
    }

    public void setEndIconActivated(boolean z3) {
        this.f2539d0.setActivated(z3);
    }

    public void setEndIconCheckable(boolean z3) {
        this.f2539d0.setCheckable(z3);
    }

    public void setEndIconContentDescription(int i3) {
        setEndIconContentDescription(i3 != 0 ? getResources().getText(i3) : null);
    }

    public void setEndIconDrawable(int i3) {
        setEndIconDrawable(i3 != 0 ? j.a.d(getContext(), i3) : null);
    }

    public void setEndIconMode(int i3) {
        int i4 = this.f2535b0;
        this.f2535b0 = i3;
        B(i4);
        setEndIconVisible(i3 != 0);
        if (getEndIconDelegate().b(this.H)) {
            getEndIconDelegate().a();
            l();
            return;
        }
        throw new IllegalStateException("The current box background mode " + this.H + " is not supported by the end icon mode " + i3);
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        W(this.f2539d0, onClickListener, this.f2557m0);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.f2557m0 = onLongClickListener;
        X(this.f2539d0, onLongClickListener);
    }

    public void setEndIconTintList(ColorStateList colorStateList) {
        if (this.f2543f0 != colorStateList) {
            this.f2543f0 = colorStateList;
            this.f2545g0 = true;
            l();
        }
    }

    public void setEndIconTintMode(PorterDuff.Mode mode) {
        if (this.f2547h0 != mode) {
            this.f2547h0 = mode;
            this.f2549i0 = true;
            l();
        }
    }

    public void setEndIconVisible(boolean z3) {
        if (J() != z3) {
            this.f2539d0.setVisibility(z3 ? 0 : 8);
            w0();
            j0();
        }
    }

    public void setError(CharSequence charSequence) {
        if (!this.f2548i.w()) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.f2548i.s();
        } else {
            this.f2548i.L(charSequence);
        }
    }

    public void setErrorContentDescription(CharSequence charSequence) {
        this.f2548i.A(charSequence);
    }

    public void setErrorEnabled(boolean z3) {
        this.f2548i.B(z3);
    }

    public void setErrorIconDrawable(int i3) {
        setErrorIconDrawable(i3 != 0 ? j.a.d(getContext(), i3) : null);
    }

    public void setErrorIconOnClickListener(View.OnClickListener onClickListener) {
        W(this.f2561o0, onClickListener, this.f2559n0);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.f2559n0 = onLongClickListener;
        X(this.f2561o0, onLongClickListener);
    }

    public void setErrorIconTintList(ColorStateList colorStateList) {
        this.f2563p0 = colorStateList;
        Drawable drawable = this.f2561o0.getDrawable();
        if (drawable != null) {
            drawable = e0.a.r(drawable).mutate();
            e0.a.o(drawable, colorStateList);
        }
        if (this.f2561o0.getDrawable() != drawable) {
            this.f2561o0.setImageDrawable(drawable);
        }
    }

    public void setErrorIconTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f2561o0.getDrawable();
        if (drawable != null) {
            drawable = e0.a.r(drawable).mutate();
            e0.a.p(drawable, mode);
        }
        if (this.f2561o0.getDrawable() != drawable) {
            this.f2561o0.setImageDrawable(drawable);
        }
    }

    public void setErrorTextAppearance(int i3) {
        this.f2548i.C(i3);
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        this.f2548i.D(colorStateList);
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
            this.f2548i.M(charSequence);
        }
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        this.f2548i.G(colorStateList);
    }

    public void setHelperTextEnabled(boolean z3) {
        this.f2548i.F(z3);
    }

    public void setHelperTextTextAppearance(int i3) {
        this.f2548i.E(i3);
    }

    public void setHint(CharSequence charSequence) {
        if (this.A) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z3) {
        this.D0 = z3;
    }

    public void setHintEnabled(boolean z3) {
        if (z3 != this.A) {
            this.A = z3;
            if (z3) {
                CharSequence hint = this.f2544g.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.B)) {
                        setHint(hint);
                    }
                    this.f2544g.setHint((CharSequence) null);
                }
                this.C = true;
            } else {
                this.C = false;
                if (!TextUtils.isEmpty(this.B) && TextUtils.isEmpty(this.f2544g.getHint())) {
                    this.f2544g.setHint(this.B);
                }
                setHintInternal(null);
            }
            if (this.f2544g != null) {
                n0();
            }
        }
    }

    public void setHintTextAppearance(int i3) {
        this.C0.O(i3);
        this.f2567r0 = this.C0.n();
        if (this.f2544g != null) {
            o0(false);
            n0();
        }
    }

    public void setHintTextColor(ColorStateList colorStateList) {
        if (this.f2567r0 != colorStateList) {
            if (this.f2565q0 == null) {
                this.C0.Q(colorStateList);
            }
            this.f2567r0 = colorStateList;
            if (this.f2544g != null) {
                o0(false);
            }
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(int i3) {
        setPasswordVisibilityToggleContentDescription(i3 != 0 ? getResources().getText(i3) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int i3) {
        setPasswordVisibilityToggleDrawable(i3 != 0 ? j.a.d(getContext(), i3) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z3) {
        if (z3 && this.f2535b0 != 1) {
            setEndIconMode(1);
        } else {
            if (z3) {
                return;
            }
            setEndIconMode(0);
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(ColorStateList colorStateList) {
        this.f2543f0 = colorStateList;
        this.f2545g0 = true;
        l();
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        this.f2547h0 = mode;
        this.f2549i0 = true;
        l();
    }

    public void setPlaceholderText(CharSequence charSequence) {
        if (this.f2564q && TextUtils.isEmpty(charSequence)) {
            setPlaceholderTextEnabled(false);
        } else {
            if (!this.f2564q) {
                setPlaceholderTextEnabled(true);
            }
            this.f2562p = charSequence;
        }
        r0();
    }

    public void setPlaceholderTextAppearance(int i3) {
        this.f2570t = i3;
        TextView textView = this.f2566r;
        if (textView != null) {
            i.q(textView, i3);
        }
    }

    public void setPlaceholderTextColor(ColorStateList colorStateList) {
        if (this.f2568s != colorStateList) {
            this.f2568s = colorStateList;
            TextView textView = this.f2566r;
            if (textView == null || colorStateList == null) {
                return;
            }
            textView.setTextColor(colorStateList);
        }
    }

    public void setPrefixText(CharSequence charSequence) {
        this.f2576w = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.f2578x.setText(charSequence);
        u0();
    }

    public void setPrefixTextAppearance(int i3) {
        i.q(this.f2578x, i3);
    }

    public void setPrefixTextColor(ColorStateList colorStateList) {
        this.f2578x.setTextColor(colorStateList);
    }

    public void setStartIconCheckable(boolean z3) {
        this.S.setCheckable(z3);
    }

    public void setStartIconContentDescription(int i3) {
        setStartIconContentDescription(i3 != 0 ? getResources().getText(i3) : null);
    }

    public void setStartIconDrawable(int i3) {
        setStartIconDrawable(i3 != 0 ? j.a.d(getContext(), i3) : null);
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        W(this.S, onClickListener, this.Z);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.Z = onLongClickListener;
        X(this.S, onLongClickListener);
    }

    public void setStartIconTintList(ColorStateList colorStateList) {
        if (this.T != colorStateList) {
            this.T = colorStateList;
            this.U = true;
            n();
        }
    }

    public void setStartIconTintMode(PorterDuff.Mode mode) {
        if (this.V != mode) {
            this.V = mode;
            this.W = true;
            n();
        }
    }

    public void setStartIconVisible(boolean z3) {
        if (P() != z3) {
            this.S.setVisibility(z3 ? 0 : 8);
            t0();
            j0();
        }
    }

    public void setSuffixText(CharSequence charSequence) {
        this.f2580y = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.f2582z.setText(charSequence);
        x0();
    }

    public void setSuffixTextAppearance(int i3) {
        i.q(this.f2582z, i3);
    }

    public void setSuffixTextColor(ColorStateList colorStateList) {
        this.f2582z.setTextColor(colorStateList);
    }

    public void setTextInputAccessibilityDelegate(e eVar) {
        EditText editText = this.f2544g;
        if (editText != null) {
            t.j0(editText, eVar);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != this.R) {
            this.R = typeface;
            this.C0.k0(typeface);
            this.f2548i.I(typeface);
            TextView textView = this.f2556m;
            if (textView != null) {
                textView.setTypeface(typeface);
            }
        }
    }

    public final Rect t(Rect rect) {
        if (this.f2544g == null) {
            throw new IllegalStateException();
        }
        Rect rect2 = this.P;
        float fX = this.C0.x();
        rect2.left = rect.left + this.f2544g.getCompoundPaddingLeft();
        rect2.top = s(rect, fX);
        rect2.right = rect.right - this.f2544g.getCompoundPaddingRight();
        rect2.bottom = r(rect, rect2, fX);
        return rect2;
    }

    public final void t0() {
        if (this.f2544g == null) {
            return;
        }
        t.w0(this.f2578x, P() ? 0 : t.E(this.f2544g), this.f2544g.getCompoundPaddingTop(), 0, this.f2544g.getCompoundPaddingBottom());
    }

    public final int u() {
        float fP;
        if (!this.A) {
            return 0;
        }
        int i3 = this.H;
        if (i3 == 0 || i3 == 1) {
            fP = this.C0.p();
        } else {
            if (i3 != 2) {
                return 0;
            }
            fP = this.C0.p() / 2.0f;
        }
        return (int) fP;
    }

    public final void u0() {
        this.f2578x.setVisibility((this.f2576w == null || M()) ? 8 : 0);
        j0();
    }

    public final boolean v() {
        return this.H == 2 && w();
    }

    public final void v0(boolean z3, boolean z4) {
        int defaultColor = this.f2575v0.getDefaultColor();
        int colorForState = this.f2575v0.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, defaultColor);
        int colorForState2 = this.f2575v0.getColorForState(new int[]{R.attr.state_activated, R.attr.state_enabled}, defaultColor);
        if (z3) {
            this.M = colorForState2;
        } else if (z4) {
            this.M = colorForState;
        } else {
            this.M = defaultColor;
        }
    }

    public final boolean w() {
        return this.J > -1 && this.M != 0;
    }

    public final void w0() {
        if (this.f2544g == null) {
            return;
        }
        t.w0(this.f2582z, 0, this.f2544g.getPaddingTop(), (J() || K()) ? 0 : t.D(this.f2544g), this.f2544g.getPaddingBottom());
    }

    public final void x() {
        if (z()) {
            ((q2.c) this.D).q0();
        }
    }

    public final void x0() {
        int visibility = this.f2582z.getVisibility();
        boolean z3 = (this.f2580y == null || M()) ? false : true;
        this.f2582z.setVisibility(z3 ? 0 : 8);
        if (visibility != this.f2582z.getVisibility()) {
            getEndIconDelegate().c(z3);
        }
        j0();
    }

    public final void y(boolean z3) {
        ValueAnimator valueAnimator = this.E0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.E0.cancel();
        }
        if (z3 && this.D0) {
            h(1.0f);
        } else {
            this.C0.d0(1.0f);
        }
        this.B0 = false;
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
        if (this.D == null || this.H == 0) {
            return;
        }
        boolean z3 = false;
        boolean z4 = isFocused() || ((editText2 = this.f2544g) != null && editText2.hasFocus());
        boolean z5 = isHovered() || ((editText = this.f2544g) != null && editText.isHovered());
        if (!isEnabled()) {
            this.M = this.A0;
        } else if (this.f2548i.k()) {
            if (this.f2575v0 != null) {
                v0(z4, z5);
            } else {
                this.M = this.f2548i.o();
            }
        } else if (!this.f2554l || (textView = this.f2556m) == null) {
            if (z4) {
                this.M = this.f2573u0;
            } else if (z5) {
                this.M = this.f2571t0;
            } else {
                this.M = this.f2569s0;
            }
        } else if (this.f2575v0 != null) {
            v0(z4, z5);
        } else {
            this.M = textView.getCurrentTextColor();
        }
        if (getErrorIconDrawable() != null && this.f2548i.w() && this.f2548i.k()) {
            z3 = true;
        }
        setErrorIconVisible(z3);
        m0(this.f2561o0, this.f2563p0);
        m0(this.S, this.T);
        m0(this.f2539d0, this.f2543f0);
        if (getEndIconDelegate().d()) {
            d0(this.f2548i.k());
        }
        if (z4 && isEnabled()) {
            this.J = this.L;
        } else {
            this.J = this.K;
        }
        if (this.H == 1) {
            if (!isEnabled()) {
                this.N = this.f2579x0;
            } else if (z5 && !z4) {
                this.N = this.f2583z0;
            } else if (z4) {
                this.N = this.f2581y0;
            } else {
                this.N = this.f2577w0;
            }
        }
        i();
    }

    public final boolean z() {
        return this.A && !TextUtils.isEmpty(this.B) && (this.D instanceof q2.c);
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.textInputStyle);
    }

    public void setEndIconContentDescription(CharSequence charSequence) {
        if (getEndIconContentDescription() != charSequence) {
            this.f2539d0.setContentDescription(charSequence);
        }
    }

    public void setEndIconDrawable(Drawable drawable) {
        this.f2539d0.setImageDrawable(drawable);
    }

    public void setErrorIconDrawable(Drawable drawable) {
        this.f2561o0.setImageDrawable(drawable);
        setErrorIconVisible(drawable != null && this.f2548i.w());
    }

    public void setStartIconContentDescription(CharSequence charSequence) {
        if (getStartIconContentDescription() != charSequence) {
            this.S.setContentDescription(charSequence);
        }
    }

    public void setStartIconDrawable(Drawable drawable) {
        this.S.setImageDrawable(drawable);
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
    public TextInputLayout(Context context, AttributeSet attributeSet, int i3) {
        int i4;
        int i5 = f2533b;
        super(r2.a.c(context, attributeSet, i3, i5), attributeSet, i3);
        this.f2548i = new q2.f(this);
        this.O = new Rect();
        this.P = new Rect();
        this.Q = new RectF();
        this.f2534a0 = new LinkedHashSet<>();
        this.f2535b0 = 0;
        this.f2537c0 = new SparseArray<>();
        this.f2541e0 = new LinkedHashSet<>();
        i2.a aVar = new i2.a(this);
        this.C0 = aVar;
        Context context2 = getContext();
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        FrameLayout frameLayout = new FrameLayout(context2);
        this.f2536c = frameLayout;
        frameLayout.setAddStatesFromChildren(true);
        addView(frameLayout);
        LinearLayout linearLayout = new LinearLayout(context2);
        this.f2538d = linearLayout;
        linearLayout.setOrientation(0);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        frameLayout.addView(linearLayout);
        LinearLayout linearLayout2 = new LinearLayout(context2);
        this.f2540e = linearLayout2;
        linearLayout2.setOrientation(0);
        linearLayout2.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388613));
        frameLayout.addView(linearLayout2);
        FrameLayout frameLayout2 = new FrameLayout(context2);
        this.f2542f = frameLayout2;
        frameLayout2.setLayoutParams(new FrameLayout.LayoutParams(-2, -1));
        TimeInterpolator timeInterpolator = s1.a.f4901a;
        aVar.j0(timeInterpolator);
        aVar.g0(timeInterpolator);
        aVar.R(8388659);
        int[] iArr = R$styleable.TextInputLayout;
        int i6 = R$styleable.TextInputLayout_counterTextAppearance;
        int i7 = R$styleable.TextInputLayout_counterOverflowTextAppearance;
        int i8 = R$styleable.TextInputLayout_errorTextAppearance;
        int i9 = R$styleable.TextInputLayout_helperTextTextAppearance;
        int i10 = R$styleable.TextInputLayout_hintTextAppearance;
        e0 e0VarI = k.i(context2, attributeSet, iArr, i3, i5, i6, i7, i8, i9, i10);
        this.A = e0VarI.a(R$styleable.TextInputLayout_hintEnabled, true);
        setHint(e0VarI.p(R$styleable.TextInputLayout_android_hint));
        this.D0 = e0VarI.a(R$styleable.TextInputLayout_hintAnimationEnabled, true);
        this.F = m.e(context2, attributeSet, i3, i5).m();
        this.G = context2.getResources().getDimensionPixelOffset(R$dimen.mtrl_textinput_box_label_cutout_padding);
        this.I = e0VarI.e(R$styleable.TextInputLayout_boxCollapsedPaddingTop, 0);
        this.K = e0VarI.f(R$styleable.TextInputLayout_boxStrokeWidth, context2.getResources().getDimensionPixelSize(R$dimen.mtrl_textinput_box_stroke_width_default));
        this.L = e0VarI.f(R$styleable.TextInputLayout_boxStrokeWidthFocused, context2.getResources().getDimensionPixelSize(R$dimen.mtrl_textinput_box_stroke_width_focused));
        this.J = this.K;
        float fD = e0VarI.d(R$styleable.TextInputLayout_boxCornerRadiusTopStart, -1.0f);
        float fD2 = e0VarI.d(R$styleable.TextInputLayout_boxCornerRadiusTopEnd, -1.0f);
        float fD3 = e0VarI.d(R$styleable.TextInputLayout_boxCornerRadiusBottomEnd, -1.0f);
        float fD4 = e0VarI.d(R$styleable.TextInputLayout_boxCornerRadiusBottomStart, -1.0f);
        m.b bVarV = this.F.v();
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
        this.F = bVarV.m();
        ColorStateList colorStateListB = k2.c.b(context2, e0VarI, R$styleable.TextInputLayout_boxBackgroundColor);
        if (colorStateListB != null) {
            int defaultColor = colorStateListB.getDefaultColor();
            this.f2577w0 = defaultColor;
            this.N = defaultColor;
            if (colorStateListB.isStateful()) {
                this.f2579x0 = colorStateListB.getColorForState(new int[]{-16842910}, -1);
                i4 = 2;
                this.f2581y0 = colorStateListB.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
                this.f2583z0 = colorStateListB.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            } else {
                i4 = 2;
                this.f2581y0 = this.f2577w0;
                ColorStateList colorStateListC = j.a.c(context2, R$color.mtrl_filled_background_color);
                this.f2579x0 = colorStateListC.getColorForState(new int[]{-16842910}, -1);
                this.f2583z0 = colorStateListC.getColorForState(new int[]{R.attr.state_hovered}, -1);
            }
        } else {
            i4 = 2;
            this.N = 0;
            this.f2577w0 = 0;
            this.f2579x0 = 0;
            this.f2581y0 = 0;
            this.f2583z0 = 0;
        }
        int i11 = R$styleable.TextInputLayout_android_textColorHint;
        if (e0VarI.s(i11)) {
            ColorStateList colorStateListC2 = e0VarI.c(i11);
            this.f2567r0 = colorStateListC2;
            this.f2565q0 = colorStateListC2;
        }
        int i12 = R$styleable.TextInputLayout_boxStrokeColor;
        ColorStateList colorStateListB2 = k2.c.b(context2, e0VarI, i12);
        this.f2573u0 = e0VarI.b(i12, 0);
        this.f2569s0 = b0.a.b(context2, R$color.mtrl_textinput_default_box_stroke_color);
        this.A0 = b0.a.b(context2, R$color.mtrl_textinput_disabled_color);
        this.f2571t0 = b0.a.b(context2, R$color.mtrl_textinput_hovered_box_stroke_color);
        if (colorStateListB2 != null) {
            setBoxStrokeColorStateList(colorStateListB2);
        }
        int i13 = R$styleable.TextInputLayout_boxStrokeErrorColor;
        if (e0VarI.s(i13)) {
            setBoxStrokeErrorColor(k2.c.b(context2, e0VarI, i13));
        }
        if (e0VarI.n(i10, -1) != -1) {
            setHintTextAppearance(e0VarI.n(i10, 0));
        }
        int iN = e0VarI.n(i8, 0);
        CharSequence charSequenceP = e0VarI.p(R$styleable.TextInputLayout_errorContentDescription);
        boolean zA = e0VarI.a(R$styleable.TextInputLayout_errorEnabled, false);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int i14 = R$layout.design_text_input_end_icon;
        CheckableImageButton checkableImageButton = (CheckableImageButton) layoutInflaterFrom.inflate(i14, (ViewGroup) linearLayout2, false);
        this.f2561o0 = checkableImageButton;
        checkableImageButton.setVisibility(8);
        int i15 = R$styleable.TextInputLayout_errorIconDrawable;
        if (e0VarI.s(i15)) {
            setErrorIconDrawable(e0VarI.g(i15));
        }
        int i16 = R$styleable.TextInputLayout_errorIconTint;
        if (e0VarI.s(i16)) {
            setErrorIconTintList(k2.c.b(context2, e0VarI, i16));
        }
        int i17 = R$styleable.TextInputLayout_errorIconTintMode;
        if (e0VarI.s(i17)) {
            setErrorIconTintMode(p.i(e0VarI.k(i17, -1), null));
        }
        checkableImageButton.setContentDescription(getResources().getText(R$string.error_icon_content_description));
        t.t0(checkableImageButton, i4);
        checkableImageButton.setClickable(false);
        checkableImageButton.setPressable(false);
        checkableImageButton.setFocusable(false);
        int iN2 = e0VarI.n(i9, 0);
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
        this.f2560o = e0VarI.n(i6, 0);
        this.f2558n = e0VarI.n(i7, 0);
        CheckableImageButton checkableImageButton2 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(R$layout.design_text_input_start_icon, (ViewGroup) this.f2538d, false);
        this.S = checkableImageButton2;
        checkableImageButton2.setVisibility(8);
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        int i18 = R$styleable.TextInputLayout_startIconDrawable;
        if (e0VarI.s(i18)) {
            setStartIconDrawable(e0VarI.g(i18));
            int i19 = R$styleable.TextInputLayout_startIconContentDescription;
            if (e0VarI.s(i19)) {
                setStartIconContentDescription(e0VarI.p(i19));
            }
            setStartIconCheckable(e0VarI.a(R$styleable.TextInputLayout_startIconCheckable, true));
        }
        int i20 = R$styleable.TextInputLayout_startIconTint;
        if (e0VarI.s(i20)) {
            setStartIconTintList(k2.c.b(context2, e0VarI, i20));
        }
        int i21 = R$styleable.TextInputLayout_startIconTintMode;
        if (e0VarI.s(i21)) {
            setStartIconTintMode(p.i(e0VarI.k(i21, -1), null));
        }
        setBoxBackgroundMode(e0VarI.k(R$styleable.TextInputLayout_boxBackgroundMode, 0));
        this.f2539d0 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(i14, (ViewGroup) this.f2542f, false);
        this.f2542f.addView(this.f2539d0);
        this.f2539d0.setVisibility(8);
        this.f2537c0.append(-1, new q2.b(this));
        this.f2537c0.append(0, new q2.g(this));
        this.f2537c0.append(1, new q2.h(this));
        this.f2537c0.append(2, new q2.a(this));
        this.f2537c0.append(3, new q2.d(this));
        int i22 = R$styleable.TextInputLayout_endIconMode;
        if (e0VarI.s(i22)) {
            setEndIconMode(e0VarI.k(i22, 0));
            int i23 = R$styleable.TextInputLayout_endIconDrawable;
            if (e0VarI.s(i23)) {
                setEndIconDrawable(e0VarI.g(i23));
            }
            int i24 = R$styleable.TextInputLayout_endIconContentDescription;
            if (e0VarI.s(i24)) {
                setEndIconContentDescription(e0VarI.p(i24));
            }
            setEndIconCheckable(e0VarI.a(R$styleable.TextInputLayout_endIconCheckable, true));
        } else {
            int i25 = R$styleable.TextInputLayout_passwordToggleEnabled;
            if (e0VarI.s(i25)) {
                setEndIconMode(e0VarI.a(i25, false) ? 1 : 0);
                setEndIconDrawable(e0VarI.g(R$styleable.TextInputLayout_passwordToggleDrawable));
                setEndIconContentDescription(e0VarI.p(R$styleable.TextInputLayout_passwordToggleContentDescription));
                int i26 = R$styleable.TextInputLayout_passwordToggleTint;
                if (e0VarI.s(i26)) {
                    setEndIconTintList(k2.c.b(context2, e0VarI, i26));
                }
                int i27 = R$styleable.TextInputLayout_passwordToggleTintMode;
                if (e0VarI.s(i27)) {
                    setEndIconTintMode(p.i(e0VarI.k(i27, -1), null));
                }
            }
        }
        if (!e0VarI.s(R$styleable.TextInputLayout_passwordToggleEnabled)) {
            int i28 = R$styleable.TextInputLayout_endIconTint;
            if (e0VarI.s(i28)) {
                setEndIconTintList(k2.c.b(context2, e0VarI, i28));
            }
            int i29 = R$styleable.TextInputLayout_endIconTintMode;
            if (e0VarI.s(i29)) {
                setEndIconTintMode(p.i(e0VarI.k(i29, -1), null));
            }
        }
        AppCompatTextView appCompatTextView = new AppCompatTextView(context2);
        this.f2578x = appCompatTextView;
        appCompatTextView.setId(R$id.textinput_prefix_text);
        appCompatTextView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        t.l0(appCompatTextView, 1);
        this.f2538d.addView(this.S);
        this.f2538d.addView(appCompatTextView);
        AppCompatTextView appCompatTextView2 = new AppCompatTextView(context2);
        this.f2582z = appCompatTextView2;
        appCompatTextView2.setId(R$id.textinput_suffix_text);
        appCompatTextView2.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 80));
        t.l0(appCompatTextView2, 1);
        this.f2540e.addView(appCompatTextView2);
        this.f2540e.addView(this.f2561o0);
        this.f2540e.addView(this.f2542f);
        setHelperTextEnabled(zA2);
        setHelperText(charSequenceP2);
        setHelperTextTextAppearance(iN2);
        setErrorEnabled(zA);
        setErrorTextAppearance(iN);
        setErrorContentDescription(charSequenceP);
        setCounterTextAppearance(this.f2560o);
        setCounterOverflowTextAppearance(this.f2558n);
        setPlaceholderText(charSequenceP3);
        setPlaceholderTextAppearance(iN3);
        setPrefixText(charSequenceP4);
        setPrefixTextAppearance(iN4);
        setSuffixText(charSequenceP5);
        setSuffixTextAppearance(iN5);
        int i30 = R$styleable.TextInputLayout_errorTextColor;
        if (e0VarI.s(i30)) {
            setErrorTextColor(e0VarI.c(i30));
        }
        int i31 = R$styleable.TextInputLayout_helperTextTextColor;
        if (e0VarI.s(i31)) {
            setHelperTextColor(e0VarI.c(i31));
        }
        int i32 = R$styleable.TextInputLayout_hintTextColor;
        if (e0VarI.s(i32)) {
            setHintTextColor(e0VarI.c(i32));
        }
        int i33 = R$styleable.TextInputLayout_counterTextColor;
        if (e0VarI.s(i33)) {
            setCounterTextColor(e0VarI.c(i33));
        }
        int i34 = R$styleable.TextInputLayout_counterOverflowTextColor;
        if (e0VarI.s(i34)) {
            setCounterOverflowTextColor(e0VarI.c(i34));
        }
        int i35 = R$styleable.TextInputLayout_placeholderTextColor;
        if (e0VarI.s(i35)) {
            setPlaceholderTextColor(e0VarI.c(i35));
        }
        int i36 = R$styleable.TextInputLayout_prefixTextColor;
        if (e0VarI.s(i36)) {
            setPrefixTextColor(e0VarI.c(i36));
        }
        int i37 = R$styleable.TextInputLayout_suffixTextColor;
        if (e0VarI.s(i37)) {
            setSuffixTextColor(e0VarI.c(i37));
        }
        setCounterEnabled(zA3);
        setEnabled(e0VarI.a(R$styleable.TextInputLayout_android_enabled, true));
        e0VarI.w();
        t.t0(this, 2);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence charSequence) {
        this.f2539d0.setContentDescription(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable drawable) {
        this.f2539d0.setImageDrawable(drawable);
    }
}
