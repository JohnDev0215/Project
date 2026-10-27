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
import b.b.f.e0;
import b.b.f.q;
import b.g.j.t;
import b.g.k.i;
import c.b.a.a.q.k;
import c.b.a.a.q.p;
import c.b.a.a.v.h;
import c.b.a.a.v.m;
import com.google.android.material.R$attr;
import com.google.android.material.R$color;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.CheckableImageButton;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public class TextInputLayout extends LinearLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f2266b = R$style.Widget_Design_TextInputLayout;
    public boolean A;
    public int A0;
    public CharSequence B;
    public int B0;
    public boolean C;
    public int C0;
    public h D;
    public int D0;
    public h E;
    public boolean E0;
    public m F;
    public final c.b.a.a.q.a F0;
    public final int G;
    public boolean G0;
    public int H;
    public ValueAnimator H0;
    public final int I;
    public boolean I0;
    public int J;
    public boolean J0;
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
    public Drawable a0;
    public int b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final FrameLayout f2267c;
    public View.OnLongClickListener c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinearLayout f2268d;
    public final LinkedHashSet<f> d0;
    public final LinearLayout e;
    public int e0;
    public final FrameLayout f;
    public final SparseArray<c.b.a.a.y.e> f0;
    public EditText g;
    public final CheckableImageButton g0;
    public CharSequence h;
    public final LinkedHashSet<g> h0;
    public final c.b.a.a.y.f i;
    public ColorStateList i0;
    public boolean j;
    public boolean j0;
    public int k;
    public PorterDuff.Mode k0;
    public boolean l;
    public boolean l0;
    public TextView m;
    public Drawable m0;
    public int n;
    public int n0;
    public int o;
    public Drawable o0;
    public CharSequence p;
    public View.OnLongClickListener p0;
    public boolean q;
    public View.OnLongClickListener q0;
    public TextView r;
    public final CheckableImageButton r0;
    public ColorStateList s;
    public ColorStateList s0;
    public int t;
    public ColorStateList t0;
    public ColorStateList u;
    public ColorStateList u0;
    public ColorStateList v;
    public int v0;
    public CharSequence w;
    public int w0;
    public final TextView x;
    public int x0;
    public CharSequence y;
    public ColorStateList y0;
    public final TextView z;
    public int z0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CharSequence f2269d;
        public boolean e;

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
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.f2269d) + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(this.f2269d, parcel, i);
            parcel.writeInt(this.e ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2269d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.e = parcel.readInt() == 1;
        }
    }

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            TextInputLayout.this.o0(!r0.J0);
            TextInputLayout textInputLayout = TextInputLayout.this;
            if (textInputLayout.j) {
                textInputLayout.g0(editable.length());
            }
            if (TextInputLayout.this.q) {
                TextInputLayout.this.s0(editable.length());
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.g0.performClick();
            TextInputLayout.this.g0.jumpDrawablesToCurrentState();
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.g.requestLayout();
        }
    }

    public class d implements ValueAnimator.AnimatorUpdateListener {
        public d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            TextInputLayout.this.F0.d0(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public static class e extends b.g.j.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextInputLayout f2274d;

        public e(TextInputLayout textInputLayout) {
            this.f2274d = textInputLayout;
        }

        @Override // b.g.j.a
        public void g(View view, b.g.j.c0.c cVar) {
            super.g(view, cVar);
            EditText editText = this.f2274d.getEditText();
            CharSequence text = editText != null ? editText.getText() : null;
            CharSequence hint = this.f2274d.getHint();
            CharSequence helperText = this.f2274d.getHelperText();
            CharSequence error = this.f2274d.getError();
            int counterMaxLength = this.f2274d.getCounterMaxLength();
            CharSequence counterOverflowDescription = this.f2274d.getCounterOverflowDescription();
            boolean z = !TextUtils.isEmpty(text);
            boolean z2 = !TextUtils.isEmpty(hint);
            boolean z3 = !TextUtils.isEmpty(helperText);
            boolean z4 = !TextUtils.isEmpty(error);
            boolean z5 = z4 || !TextUtils.isEmpty(counterOverflowDescription);
            String string = z2 ? hint.toString() : "";
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append(((z4 || z3) && !TextUtils.isEmpty(string)) ? ", " : "");
            String string2 = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(string2);
            if (z4) {
                helperText = error;
            } else if (!z3) {
                helperText = "";
            }
            sb2.append((Object) helperText);
            String string3 = sb2.toString();
            if (z) {
                cVar.A0(text);
            } else if (!TextUtils.isEmpty(string3)) {
                cVar.A0(string3);
            }
            if (!TextUtils.isEmpty(string3)) {
                if (Build.VERSION.SDK_INT >= 26) {
                    cVar.l0(string3);
                } else {
                    if (z) {
                        string3 = ((Object) text) + ", " + string3;
                    }
                    cVar.A0(string3);
                }
                cVar.x0(!z);
            }
            if (text == null || text.length() != counterMaxLength) {
                counterMaxLength = -1;
            }
            cVar.n0(counterMaxLength);
            if (z5) {
                if (!z4) {
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
        void a(TextInputLayout textInputLayout, int i);
    }

    public TextInputLayout(Context context) {
        this(context, null);
    }

    public static void S(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                S((ViewGroup) childAt, z);
            }
        }
    }

    public static void V(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        boolean zK = t.K(checkableImageButton);
        boolean z = onLongClickListener != null;
        boolean z2 = zK || z;
        checkableImageButton.setFocusable(z2);
        checkableImageButton.setClickable(zK);
        checkableImageButton.setPressable(zK);
        checkableImageButton.setLongClickable(z);
        t.t0(checkableImageButton, z2 ? 1 : 2);
    }

    public static void W(CheckableImageButton checkableImageButton, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        checkableImageButton.setOnClickListener(onClickListener);
        V(checkableImageButton, onLongClickListener);
    }

    public static void X(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        V(checkableImageButton, onLongClickListener);
    }

    private c.b.a.a.y.e getEndIconDelegate() {
        c.b.a.a.y.e eVar = this.f0.get(this.e0);
        return eVar != null ? eVar : this.f0.get(0);
    }

    private CheckableImageButton getEndIconToUpdateDummyDrawable() {
        if (this.r0.getVisibility() == 0) {
            return this.r0;
        }
        if (H() && J()) {
            return this.g0;
        }
        return null;
    }

    public static void h0(Context context, TextView textView, int i, int i2, boolean z) {
        textView.setContentDescription(context.getString(z ? R$string.character_counter_overflowed_content_description : R$string.character_counter_content_description, Integer.valueOf(i), Integer.valueOf(i2)));
    }

    private void setEditText(EditText editText) {
        if (this.g != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (this.e0 != 3 && !(editText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.g = editText;
        Q();
        setTextInputAccessibilityDelegate(new e(this));
        this.F0.k0(this.g.getTypeface());
        this.F0.a0(this.g.getTextSize());
        int gravity = this.g.getGravity();
        this.F0.R((gravity & (-113)) | 48);
        this.F0.Z(gravity);
        this.g.addTextChangedListener(new a());
        if (this.t0 == null) {
            this.t0 = this.g.getHintTextColors();
        }
        if (this.A) {
            if (TextUtils.isEmpty(this.B)) {
                CharSequence hint = this.g.getHint();
                this.h = hint;
                setHint(hint);
                this.g.setHint((CharSequence) null);
            }
            this.C = true;
        }
        if (this.m != null) {
            g0(this.g.getText().length());
        }
        k0();
        this.i.e();
        this.f2268d.bringToFront();
        this.e.bringToFront();
        this.f.bringToFront();
        this.r0.bringToFront();
        A();
        t0();
        w0();
        if (!isEnabled()) {
            editText.setEnabled(false);
        }
        p0(false, true);
    }

    private void setErrorIconVisible(boolean z) {
        this.r0.setVisibility(z ? 0 : 8);
        this.f.setVisibility(z ? 8 : 0);
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
        this.F0.i0(charSequence);
        if (this.E0) {
            return;
        }
        R();
    }

    private void setPlaceholderTextEnabled(boolean z) {
        if (this.q == z) {
            return;
        }
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
            this.r = appCompatTextView;
            appCompatTextView.setId(R$id.textinput_placeholder);
            t.l0(this.r, 1);
            setPlaceholderTextAppearance(this.t);
            setPlaceholderTextColor(this.s);
            g();
        } else {
            T();
            this.r = null;
        }
        this.q = z;
    }

    public final void A() {
        Iterator<f> it = this.d0.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    public final void B(int i) {
        Iterator<g> it = this.h0.iterator();
        while (it.hasNext()) {
            it.next().a(this, i);
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
            this.F0.j(canvas);
        }
    }

    public final void E(boolean z) {
        ValueAnimator valueAnimator = this.H0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.H0.cancel();
        }
        if (z && this.G0) {
            h(0.0f);
        } else {
            this.F0.d0(0.0f);
        }
        if (z() && ((c.b.a.a.y.c) this.D).n0()) {
            x();
        }
        this.E0 = true;
        I();
        u0();
        x0();
    }

    public final int F(int i, boolean z) {
        int compoundPaddingLeft = i + this.g.getCompoundPaddingLeft();
        return (this.w == null || z) ? compoundPaddingLeft : (compoundPaddingLeft - this.x.getMeasuredWidth()) + this.x.getPaddingLeft();
    }

    public final int G(int i, boolean z) {
        int compoundPaddingRight = i - this.g.getCompoundPaddingRight();
        return (this.w == null || !z) ? compoundPaddingRight : compoundPaddingRight + (this.x.getMeasuredWidth() - this.x.getPaddingRight());
    }

    public final boolean H() {
        return this.e0 != 0;
    }

    public final void I() {
        TextView textView = this.r;
        if (textView == null || !this.q) {
            return;
        }
        textView.setText((CharSequence) null);
        this.r.setVisibility(4);
    }

    public boolean J() {
        return this.f.getVisibility() == 0 && this.g0.getVisibility() == 0;
    }

    public final boolean K() {
        return this.r0.getVisibility() == 0;
    }

    public boolean L() {
        return this.i.x();
    }

    public final boolean M() {
        return this.E0;
    }

    public boolean N() {
        return this.C;
    }

    public final boolean O() {
        return this.H == 1 && (Build.VERSION.SDK_INT < 16 || this.g.getMinLines() <= 1);
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
            this.F0.m(rectF, this.g.getWidth(), this.g.getGravity());
            k(rectF);
            rectF.offset(-getPaddingLeft(), -getPaddingTop());
            ((c.b.a.a.y.c) this.D).t0(rectF);
        }
    }

    public final void T() {
        TextView textView = this.r;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    public final void U() {
        if (b0()) {
            t.m0(this.g, this.D);
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
            b.g.k.i.q(r3, r4)     // Catch: java.lang.Exception -> L1a
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
            b.g.k.i.q(r3, r4)
            android.content.Context r4 = r2.getContext()
            int r0 = com.google.android.material.R$color.design_error
            int r4 = b.g.b.a.b(r4, r0)
            r3.setTextColor(r4)
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.Y(android.widget.TextView, int):void");
    }

    public final boolean Z() {
        return (this.r0.getVisibility() == 0 || ((H() && J()) || this.y != null)) && this.e.getMeasuredWidth() > 0;
    }

    public final boolean a0() {
        return !(getStartIconDrawable() == null && this.w == null) && this.f2268d.getMeasuredWidth() > 0;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
        this.f2267c.addView(view, layoutParams2);
        this.f2267c.setLayoutParams(layoutParams);
        n0();
        setEditText((EditText) view);
    }

    public final boolean b0() {
        EditText editText = this.g;
        return (editText == null || this.D == null || editText.getBackground() != null || this.H == 0) ? false : true;
    }

    public final void c0() {
        TextView textView = this.r;
        if (textView == null || !this.q) {
            return;
        }
        textView.setText(this.p);
        this.r.setVisibility(0);
        this.r.bringToFront();
    }

    public final void d0(boolean z) {
        if (!z || getEndIconDrawable() == null) {
            l();
            return;
        }
        Drawable drawableMutate = b.g.c.l.a.r(getEndIconDrawable()).mutate();
        b.g.c.l.a.n(drawableMutate, this.i.o());
        this.g0.setImageDrawable(drawableMutate);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i) {
        EditText editText;
        if (this.h == null || (editText = this.g) == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i);
            return;
        }
        boolean z = this.C;
        this.C = false;
        CharSequence hint = editText.getHint();
        this.g.setHint(this.h);
        try {
            super.dispatchProvideAutofillStructure(viewStructure, i);
        } finally {
            this.g.setHint(hint);
            this.C = z;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        this.J0 = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.J0 = false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        D(canvas);
        C(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        if (this.I0) {
            return;
        }
        this.I0 = true;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        c.b.a.a.q.a aVar = this.F0;
        boolean zH0 = aVar != null ? aVar.h0(drawableState) | false : false;
        if (this.g != null) {
            o0(t.P(this) && isEnabled());
        }
        k0();
        y0();
        if (zH0) {
            invalidate();
        }
        this.I0 = false;
    }

    public void e(f fVar) {
        this.d0.add(fVar);
        if (this.g != null) {
            fVar.a(this);
        }
    }

    public final void e0(Rect rect) {
        h hVar = this.E;
        if (hVar != null) {
            int i = rect.bottom;
            hVar.setBounds(rect.left, i - this.L, rect.right, i);
        }
    }

    public void f(g gVar) {
        this.h0.add(gVar);
    }

    public final void f0() {
        if (this.m != null) {
            EditText editText = this.g;
            g0(editText == null ? 0 : editText.getText().length());
        }
    }

    public final void g() {
        TextView textView = this.r;
        if (textView != null) {
            this.f2267c.addView(textView);
            this.r.setVisibility(0);
        }
    }

    public void g0(int i) {
        boolean z = this.l;
        int i2 = this.k;
        if (i2 == -1) {
            this.m.setText(String.valueOf(i));
            this.m.setContentDescription(null);
            this.l = false;
        } else {
            this.l = i > i2;
            h0(getContext(), this.m, i, this.k, this.l);
            if (z != this.l) {
                i0();
            }
            this.m.setText(b.g.h.a.c().j(getContext().getString(R$string.character_counter_pattern, Integer.valueOf(i), Integer.valueOf(this.k))));
        }
        if (this.g == null || z == this.l) {
            return;
        }
        o0(false);
        y0();
        k0();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.g;
        return editText != null ? editText.getBaseline() + getPaddingTop() + u() : super.getBaseline();
    }

    public h getBoxBackground() {
        int i = this.H;
        if (i == 1 || i == 2) {
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
        return this.x0;
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.y0;
    }

    public int getBoxStrokeWidth() {
        return this.K;
    }

    public int getBoxStrokeWidthFocused() {
        return this.L;
    }

    public int getCounterMaxLength() {
        return this.k;
    }

    public CharSequence getCounterOverflowDescription() {
        TextView textView;
        if (this.j && this.l && (textView = this.m) != null) {
            return textView.getContentDescription();
        }
        return null;
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.u;
    }

    public ColorStateList getCounterTextColor() {
        return this.u;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.t0;
    }

    public EditText getEditText() {
        return this.g;
    }

    public CharSequence getEndIconContentDescription() {
        return this.g0.getContentDescription();
    }

    public Drawable getEndIconDrawable() {
        return this.g0.getDrawable();
    }

    public int getEndIconMode() {
        return this.e0;
    }

    public CheckableImageButton getEndIconView() {
        return this.g0;
    }

    public CharSequence getError() {
        if (this.i.w()) {
            return this.i.n();
        }
        return null;
    }

    public CharSequence getErrorContentDescription() {
        return this.i.m();
    }

    public int getErrorCurrentTextColors() {
        return this.i.o();
    }

    public Drawable getErrorIconDrawable() {
        return this.r0.getDrawable();
    }

    public final int getErrorTextCurrentColor() {
        return this.i.o();
    }

    public CharSequence getHelperText() {
        if (this.i.x()) {
            return this.i.q();
        }
        return null;
    }

    public int getHelperTextCurrentTextColor() {
        return this.i.r();
    }

    public CharSequence getHint() {
        if (this.A) {
            return this.B;
        }
        return null;
    }

    public final float getHintCollapsedTextHeight() {
        return this.F0.p();
    }

    public final int getHintCurrentCollapsedTextColor() {
        return this.F0.t();
    }

    public ColorStateList getHintTextColor() {
        return this.u0;
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.g0.getContentDescription();
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.g0.getDrawable();
    }

    public CharSequence getPlaceholderText() {
        if (this.q) {
            return this.p;
        }
        return null;
    }

    public int getPlaceholderTextAppearance() {
        return this.t;
    }

    public ColorStateList getPlaceholderTextColor() {
        return this.s;
    }

    public CharSequence getPrefixText() {
        return this.w;
    }

    public ColorStateList getPrefixTextColor() {
        return this.x.getTextColors();
    }

    public TextView getPrefixTextView() {
        return this.x;
    }

    public CharSequence getStartIconContentDescription() {
        return this.S.getContentDescription();
    }

    public Drawable getStartIconDrawable() {
        return this.S.getDrawable();
    }

    public CharSequence getSuffixText() {
        return this.y;
    }

    public ColorStateList getSuffixTextColor() {
        return this.z.getTextColors();
    }

    public TextView getSuffixTextView() {
        return this.z;
    }

    public Typeface getTypeface() {
        return this.R;
    }

    public void h(float f2) {
        if (this.F0.z() == f2) {
            return;
        }
        if (this.H0 == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.H0 = valueAnimator;
            valueAnimator.setInterpolator(c.b.a.a.a.a.f1649b);
            this.H0.setDuration(167L);
            this.H0.addUpdateListener(new d());
        }
        this.H0.setFloatValues(this.F0.z(), f2);
        this.H0.start();
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
        if (this.e0 == 3) {
            this.g.getBackground().invalidateSelf();
        }
        j();
        invalidate();
    }

    public final void i0() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        TextView textView = this.m;
        if (textView != null) {
            Y(textView, this.l ? this.n : this.o);
            if (!this.l && (colorStateList2 = this.u) != null) {
                this.m.setTextColor(colorStateList2);
            }
            if (!this.l || (colorStateList = this.v) == null) {
                return;
            }
            this.m.setTextColor(colorStateList);
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
        boolean z;
        if (this.g == null) {
            return false;
        }
        boolean z2 = true;
        if (a0()) {
            int measuredWidth = this.f2268d.getMeasuredWidth() - this.g.getPaddingLeft();
            if (this.a0 == null || this.b0 != measuredWidth) {
                ColorDrawable colorDrawable = new ColorDrawable();
                this.a0 = colorDrawable;
                this.b0 = measuredWidth;
                colorDrawable.setBounds(0, 0, measuredWidth, 1);
            }
            Drawable[] drawableArrA = i.a(this.g);
            Drawable drawable = drawableArrA[0];
            Drawable drawable2 = this.a0;
            if (drawable != drawable2) {
                i.l(this.g, drawable2, drawableArrA[1], drawableArrA[2], drawableArrA[3]);
                z = true;
            }
            z = false;
        } else {
            if (this.a0 != null) {
                Drawable[] drawableArrA2 = i.a(this.g);
                i.l(this.g, null, drawableArrA2[1], drawableArrA2[2], drawableArrA2[3]);
                this.a0 = null;
                z = true;
            }
            z = false;
        }
        if (Z()) {
            int measuredWidth2 = this.z.getMeasuredWidth() - this.g.getPaddingRight();
            CheckableImageButton endIconToUpdateDummyDrawable = getEndIconToUpdateDummyDrawable();
            if (endIconToUpdateDummyDrawable != null) {
                measuredWidth2 = measuredWidth2 + endIconToUpdateDummyDrawable.getMeasuredWidth() + b.g.j.g.b((ViewGroup.MarginLayoutParams) endIconToUpdateDummyDrawable.getLayoutParams());
            }
            Drawable[] drawableArrA3 = i.a(this.g);
            Drawable drawable3 = this.m0;
            if (drawable3 == null || this.n0 == measuredWidth2) {
                if (drawable3 == null) {
                    ColorDrawable colorDrawable2 = new ColorDrawable();
                    this.m0 = colorDrawable2;
                    this.n0 = measuredWidth2;
                    colorDrawable2.setBounds(0, 0, measuredWidth2, 1);
                }
                Drawable drawable4 = drawableArrA3[2];
                Drawable drawable5 = this.m0;
                if (drawable4 != drawable5) {
                    this.o0 = drawableArrA3[2];
                    i.l(this.g, drawableArrA3[0], drawableArrA3[1], drawable5, drawableArrA3[3]);
                } else {
                    z2 = z;
                }
            } else {
                this.n0 = measuredWidth2;
                drawable3.setBounds(0, 0, measuredWidth2, 1);
                i.l(this.g, drawableArrA3[0], drawableArrA3[1], this.m0, drawableArrA3[3]);
            }
        } else {
            if (this.m0 == null) {
                return z;
            }
            Drawable[] drawableArrA4 = i.a(this.g);
            if (drawableArrA4[2] == this.m0) {
                i.l(this.g, drawableArrA4[0], drawableArrA4[1], this.o0, drawableArrA4[3]);
            } else {
                z2 = z;
            }
            this.m0 = null;
        }
        return z2;
    }

    public final void k(RectF rectF) {
        float f2 = rectF.left;
        int i = this.G;
        rectF.left = f2 - i;
        rectF.top -= i;
        rectF.right += i;
        rectF.bottom += i;
    }

    public void k0() {
        Drawable background;
        TextView textView;
        EditText editText = this.g;
        if (editText == null || this.H != 0 || (background = editText.getBackground()) == null) {
            return;
        }
        if (q.a(background)) {
            background = background.mutate();
        }
        if (this.i.k()) {
            background.setColorFilter(b.b.f.f.e(this.i.o(), PorterDuff.Mode.SRC_IN));
        } else if (this.l && (textView = this.m) != null) {
            background.setColorFilter(b.b.f.f.e(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            b.g.c.l.a.c(background);
            this.g.refreshDrawableState();
        }
    }

    public final void l() {
        m(this.g0, this.j0, this.i0, this.l0, this.k0);
    }

    public final boolean l0() {
        int iMax;
        if (this.g == null || this.g.getMeasuredHeight() >= (iMax = Math.max(this.e.getMeasuredHeight(), this.f2268d.getMeasuredHeight()))) {
            return false;
        }
        this.g.setMinimumHeight(iMax);
        return true;
    }

    public final void m(CheckableImageButton checkableImageButton, boolean z, ColorStateList colorStateList, boolean z2, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null && (z || z2)) {
            drawable = b.g.c.l.a.r(drawable).mutate();
            if (z) {
                b.g.c.l.a.o(drawable, colorStateList);
            }
            if (z2) {
                b.g.c.l.a.p(drawable, mode);
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
        Drawable drawableMutate = b.g.c.l.a.r(drawable).mutate();
        b.g.c.l.a.o(drawableMutate, ColorStateList.valueOf(colorForState));
        checkableImageButton.setImageDrawable(drawableMutate);
    }

    public final void n() {
        m(this.S, this.U, this.T, this.W, this.V);
    }

    public final void n0() {
        if (this.H != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f2267c.getLayoutParams();
            int iU = u();
            if (iU != layoutParams.topMargin) {
                layoutParams.topMargin = iU;
                this.f2267c.requestLayout();
            }
        }
    }

    public final void o() {
        int i = this.H;
        if (i == 0) {
            this.D = null;
            this.E = null;
            return;
        }
        if (i == 1) {
            this.D = new h(this.F);
            this.E = new h();
        } else {
            if (i != 2) {
                throw new IllegalArgumentException(this.H + " is illegal; only @BoxBackgroundMode constants are supported.");
            }
            if (!this.A || (this.D instanceof c.b.a.a.y.c)) {
                this.D = new h(this.F);
            } else {
                this.D = new c.b.a.a.y.c(this.F);
            }
            this.E = null;
        }
    }

    public void o0(boolean z) {
        p0(z, false);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        EditText editText = this.g;
        if (editText != null) {
            Rect rect = this.O;
            c.b.a.a.q.c.a(this, editText, rect);
            e0(rect);
            if (this.A) {
                this.F0.a0(this.g.getTextSize());
                int gravity = this.g.getGravity();
                this.F0.R((gravity & (-113)) | 48);
                this.F0.Z(gravity);
                this.F0.N(q(rect));
                this.F0.V(t(rect));
                this.F0.K();
                if (!z() || this.E0) {
                    return;
                }
                R();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        boolean zL0 = l0();
        boolean zJ0 = j0();
        if (zL0 || zJ0) {
            this.g.post(new c());
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
        setError(savedState.f2269d);
        if (savedState.e) {
            this.g0.post(new b());
        }
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.i.k()) {
            savedState.f2269d = getError();
        }
        savedState.e = H() && this.g0.isChecked();
        return savedState;
    }

    public final int p() {
        return this.H == 1 ? c.b.a.a.k.a.e(c.b.a.a.k.a.d(this, R$attr.colorSurface, 0), this.N) : this.N;
    }

    public final void p0(boolean z, boolean z2) {
        ColorStateList colorStateList;
        TextView textView;
        boolean zIsEnabled = isEnabled();
        EditText editText = this.g;
        boolean z3 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.g;
        boolean z4 = editText2 != null && editText2.hasFocus();
        boolean zK = this.i.k();
        ColorStateList colorStateList2 = this.t0;
        if (colorStateList2 != null) {
            this.F0.Q(colorStateList2);
            this.F0.Y(this.t0);
        }
        if (!zIsEnabled) {
            ColorStateList colorStateList3 = this.t0;
            int colorForState = colorStateList3 != null ? colorStateList3.getColorForState(new int[]{-16842910}, this.D0) : this.D0;
            this.F0.Q(ColorStateList.valueOf(colorForState));
            this.F0.Y(ColorStateList.valueOf(colorForState));
        } else if (zK) {
            this.F0.Q(this.i.p());
        } else if (this.l && (textView = this.m) != null) {
            this.F0.Q(textView.getTextColors());
        } else if (z4 && (colorStateList = this.u0) != null) {
            this.F0.Q(colorStateList);
        }
        if (z3 || (isEnabled() && (z4 || zK))) {
            if (z2 || this.E0) {
                y(z);
                return;
            }
            return;
        }
        if (z2 || !this.E0) {
            E(z);
        }
    }

    public final Rect q(Rect rect) {
        if (this.g == null) {
            throw new IllegalStateException();
        }
        Rect rect2 = this.P;
        boolean z = t.z(this) == 1;
        rect2.bottom = rect.bottom;
        int i = this.H;
        if (i == 1) {
            rect2.left = F(rect.left, z);
            rect2.top = rect.top + this.I;
            rect2.right = G(rect.right, z);
            return rect2;
        }
        if (i != 2) {
            rect2.left = F(rect.left, z);
            rect2.top = getPaddingTop();
            rect2.right = G(rect.right, z);
            return rect2;
        }
        rect2.left = rect.left + this.g.getPaddingLeft();
        rect2.top = rect.top - u();
        rect2.right = rect.right - this.g.getPaddingRight();
        return rect2;
    }

    public final void q0() {
        EditText editText;
        if (this.r == null || (editText = this.g) == null) {
            return;
        }
        this.r.setGravity(editText.getGravity());
        this.r.setPadding(this.g.getCompoundPaddingLeft(), this.g.getCompoundPaddingTop(), this.g.getCompoundPaddingRight(), this.g.getCompoundPaddingBottom());
    }

    public final int r(Rect rect, Rect rect2, float f2) {
        return O() ? (int) (rect2.top + f2) : rect.bottom - this.g.getCompoundPaddingBottom();
    }

    public final void r0() {
        EditText editText = this.g;
        s0(editText == null ? 0 : editText.getText().length());
    }

    public final int s(Rect rect, float f2) {
        return O() ? (int) (rect.centerY() - (f2 / 2.0f)) : rect.top + this.g.getCompoundPaddingTop();
    }

    public final void s0(int i) {
        if (i != 0 || this.E0) {
            I();
        } else {
            c0();
        }
    }

    public void setBoxBackgroundColor(int i) {
        if (this.N != i) {
            this.N = i;
            this.z0 = i;
            this.B0 = i;
            this.C0 = i;
            i();
        }
    }

    public void setBoxBackgroundColorResource(int i) {
        setBoxBackgroundColor(b.g.b.a.b(getContext(), i));
    }

    public void setBoxBackgroundColorStateList(ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.z0 = defaultColor;
        this.N = defaultColor;
        this.A0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
        this.B0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        this.C0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
        i();
    }

    public void setBoxBackgroundMode(int i) {
        if (i == this.H) {
            return;
        }
        this.H = i;
        if (this.g != null) {
            Q();
        }
    }

    public void setBoxStrokeColor(int i) {
        if (this.x0 != i) {
            this.x0 = i;
            y0();
        }
    }

    public void setBoxStrokeColorStateList(ColorStateList colorStateList) {
        if (colorStateList.isStateful()) {
            this.v0 = colorStateList.getDefaultColor();
            this.D0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.w0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            this.x0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        } else if (this.x0 != colorStateList.getDefaultColor()) {
            this.x0 = colorStateList.getDefaultColor();
        }
        y0();
    }

    public void setBoxStrokeErrorColor(ColorStateList colorStateList) {
        if (this.y0 != colorStateList) {
            this.y0 = colorStateList;
            y0();
        }
    }

    public void setBoxStrokeWidth(int i) {
        this.K = i;
        y0();
    }

    public void setBoxStrokeWidthFocused(int i) {
        this.L = i;
        y0();
    }

    public void setBoxStrokeWidthFocusedResource(int i) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(i));
    }

    public void setBoxStrokeWidthResource(int i) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(i));
    }

    public void setCounterEnabled(boolean z) {
        if (this.j != z) {
            if (z) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
                this.m = appCompatTextView;
                appCompatTextView.setId(R$id.textinput_counter);
                Typeface typeface = this.R;
                if (typeface != null) {
                    this.m.setTypeface(typeface);
                }
                this.m.setMaxLines(1);
                this.i.d(this.m, 2);
                b.g.j.g.d((ViewGroup.MarginLayoutParams) this.m.getLayoutParams(), getResources().getDimensionPixelOffset(R$dimen.mtrl_textinput_counter_margin_start));
                i0();
                f0();
            } else {
                this.i.y(this.m, 2);
                this.m = null;
            }
            this.j = z;
        }
    }

    public void setCounterMaxLength(int i) {
        if (this.k != i) {
            if (i > 0) {
                this.k = i;
            } else {
                this.k = -1;
            }
            if (this.j) {
                f0();
            }
        }
    }

    public void setCounterOverflowTextAppearance(int i) {
        if (this.n != i) {
            this.n = i;
            i0();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList colorStateList) {
        if (this.v != colorStateList) {
            this.v = colorStateList;
            i0();
        }
    }

    public void setCounterTextAppearance(int i) {
        if (this.o != i) {
            this.o = i;
            i0();
        }
    }

    public void setCounterTextColor(ColorStateList colorStateList) {
        if (this.u != colorStateList) {
            this.u = colorStateList;
            i0();
        }
    }

    public void setDefaultHintTextColor(ColorStateList colorStateList) {
        this.t0 = colorStateList;
        this.u0 = colorStateList;
        if (this.g != null) {
            o0(false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        S(this, z);
        super.setEnabled(z);
    }

    public void setEndIconActivated(boolean z) {
        this.g0.setActivated(z);
    }

    public void setEndIconCheckable(boolean z) {
        this.g0.setCheckable(z);
    }

    public void setEndIconContentDescription(int i) {
        setEndIconContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    public void setEndIconDrawable(int i) {
        setEndIconDrawable(i != 0 ? b.b.b.a.a.d(getContext(), i) : null);
    }

    public void setEndIconMode(int i) {
        int i2 = this.e0;
        this.e0 = i;
        B(i2);
        setEndIconVisible(i != 0);
        if (getEndIconDelegate().b(this.H)) {
            getEndIconDelegate().a();
            l();
            return;
        }
        throw new IllegalStateException("The current box background mode " + this.H + " is not supported by the end icon mode " + i);
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        W(this.g0, onClickListener, this.p0);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.p0 = onLongClickListener;
        X(this.g0, onLongClickListener);
    }

    public void setEndIconTintList(ColorStateList colorStateList) {
        if (this.i0 != colorStateList) {
            this.i0 = colorStateList;
            this.j0 = true;
            l();
        }
    }

    public void setEndIconTintMode(PorterDuff.Mode mode) {
        if (this.k0 != mode) {
            this.k0 = mode;
            this.l0 = true;
            l();
        }
    }

    public void setEndIconVisible(boolean z) {
        if (J() != z) {
            this.g0.setVisibility(z ? 0 : 8);
            w0();
            j0();
        }
    }

    public void setError(CharSequence charSequence) {
        if (!this.i.w()) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.i.s();
        } else {
            this.i.L(charSequence);
        }
    }

    public void setErrorContentDescription(CharSequence charSequence) {
        this.i.A(charSequence);
    }

    public void setErrorEnabled(boolean z) {
        this.i.B(z);
    }

    public void setErrorIconDrawable(int i) {
        setErrorIconDrawable(i != 0 ? b.b.b.a.a.d(getContext(), i) : null);
    }

    public void setErrorIconOnClickListener(View.OnClickListener onClickListener) {
        W(this.r0, onClickListener, this.q0);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.q0 = onLongClickListener;
        X(this.r0, onLongClickListener);
    }

    public void setErrorIconTintList(ColorStateList colorStateList) {
        this.s0 = colorStateList;
        Drawable drawable = this.r0.getDrawable();
        if (drawable != null) {
            drawable = b.g.c.l.a.r(drawable).mutate();
            b.g.c.l.a.o(drawable, colorStateList);
        }
        if (this.r0.getDrawable() != drawable) {
            this.r0.setImageDrawable(drawable);
        }
    }

    public void setErrorIconTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.r0.getDrawable();
        if (drawable != null) {
            drawable = b.g.c.l.a.r(drawable).mutate();
            b.g.c.l.a.p(drawable, mode);
        }
        if (this.r0.getDrawable() != drawable) {
            this.r0.setImageDrawable(drawable);
        }
    }

    public void setErrorTextAppearance(int i) {
        this.i.C(i);
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        this.i.D(colorStateList);
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
            this.i.M(charSequence);
        }
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        this.i.G(colorStateList);
    }

    public void setHelperTextEnabled(boolean z) {
        this.i.F(z);
    }

    public void setHelperTextTextAppearance(int i) {
        this.i.E(i);
    }

    public void setHint(CharSequence charSequence) {
        if (this.A) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z) {
        this.G0 = z;
    }

    public void setHintEnabled(boolean z) {
        if (z != this.A) {
            this.A = z;
            if (z) {
                CharSequence hint = this.g.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.B)) {
                        setHint(hint);
                    }
                    this.g.setHint((CharSequence) null);
                }
                this.C = true;
            } else {
                this.C = false;
                if (!TextUtils.isEmpty(this.B) && TextUtils.isEmpty(this.g.getHint())) {
                    this.g.setHint(this.B);
                }
                setHintInternal(null);
            }
            if (this.g != null) {
                n0();
            }
        }
    }

    public void setHintTextAppearance(int i) {
        this.F0.O(i);
        this.u0 = this.F0.n();
        if (this.g != null) {
            o0(false);
            n0();
        }
    }

    public void setHintTextColor(ColorStateList colorStateList) {
        if (this.u0 != colorStateList) {
            if (this.t0 == null) {
                this.F0.Q(colorStateList);
            }
            this.u0 = colorStateList;
            if (this.g != null) {
                o0(false);
            }
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(int i) {
        setPasswordVisibilityToggleContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int i) {
        setPasswordVisibilityToggleDrawable(i != 0 ? b.b.b.a.a.d(getContext(), i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z) {
        if (z && this.e0 != 1) {
            setEndIconMode(1);
        } else {
            if (z) {
                return;
            }
            setEndIconMode(0);
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(ColorStateList colorStateList) {
        this.i0 = colorStateList;
        this.j0 = true;
        l();
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        this.k0 = mode;
        this.l0 = true;
        l();
    }

    public void setPlaceholderText(CharSequence charSequence) {
        if (this.q && TextUtils.isEmpty(charSequence)) {
            setPlaceholderTextEnabled(false);
        } else {
            if (!this.q) {
                setPlaceholderTextEnabled(true);
            }
            this.p = charSequence;
        }
        r0();
    }

    public void setPlaceholderTextAppearance(int i) {
        this.t = i;
        TextView textView = this.r;
        if (textView != null) {
            i.q(textView, i);
        }
    }

    public void setPlaceholderTextColor(ColorStateList colorStateList) {
        if (this.s != colorStateList) {
            this.s = colorStateList;
            TextView textView = this.r;
            if (textView == null || colorStateList == null) {
                return;
            }
            textView.setTextColor(colorStateList);
        }
    }

    public void setPrefixText(CharSequence charSequence) {
        this.w = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.x.setText(charSequence);
        u0();
    }

    public void setPrefixTextAppearance(int i) {
        i.q(this.x, i);
    }

    public void setPrefixTextColor(ColorStateList colorStateList) {
        this.x.setTextColor(colorStateList);
    }

    public void setStartIconCheckable(boolean z) {
        this.S.setCheckable(z);
    }

    public void setStartIconContentDescription(int i) {
        setStartIconContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    public void setStartIconDrawable(int i) {
        setStartIconDrawable(i != 0 ? b.b.b.a.a.d(getContext(), i) : null);
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        W(this.S, onClickListener, this.c0);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.c0 = onLongClickListener;
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

    public void setStartIconVisible(boolean z) {
        if (P() != z) {
            this.S.setVisibility(z ? 0 : 8);
            t0();
            j0();
        }
    }

    public void setSuffixText(CharSequence charSequence) {
        this.y = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.z.setText(charSequence);
        x0();
    }

    public void setSuffixTextAppearance(int i) {
        i.q(this.z, i);
    }

    public void setSuffixTextColor(ColorStateList colorStateList) {
        this.z.setTextColor(colorStateList);
    }

    public void setTextInputAccessibilityDelegate(e eVar) {
        EditText editText = this.g;
        if (editText != null) {
            t.j0(editText, eVar);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != this.R) {
            this.R = typeface;
            this.F0.k0(typeface);
            this.i.I(typeface);
            TextView textView = this.m;
            if (textView != null) {
                textView.setTypeface(typeface);
            }
        }
    }

    public final Rect t(Rect rect) {
        if (this.g == null) {
            throw new IllegalStateException();
        }
        Rect rect2 = this.P;
        float fX = this.F0.x();
        rect2.left = rect.left + this.g.getCompoundPaddingLeft();
        rect2.top = s(rect, fX);
        rect2.right = rect.right - this.g.getCompoundPaddingRight();
        rect2.bottom = r(rect, rect2, fX);
        return rect2;
    }

    public final void t0() {
        if (this.g == null) {
            return;
        }
        t.w0(this.x, P() ? 0 : t.E(this.g), this.g.getCompoundPaddingTop(), 0, this.g.getCompoundPaddingBottom());
    }

    public final int u() {
        float fP;
        if (!this.A) {
            return 0;
        }
        int i = this.H;
        if (i == 0 || i == 1) {
            fP = this.F0.p();
        } else {
            if (i != 2) {
                return 0;
            }
            fP = this.F0.p() / 2.0f;
        }
        return (int) fP;
    }

    public final void u0() {
        this.x.setVisibility((this.w == null || M()) ? 8 : 0);
        j0();
    }

    public final boolean v() {
        return this.H == 2 && w();
    }

    public final void v0(boolean z, boolean z2) {
        int defaultColor = this.y0.getDefaultColor();
        int colorForState = this.y0.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, defaultColor);
        int colorForState2 = this.y0.getColorForState(new int[]{R.attr.state_activated, R.attr.state_enabled}, defaultColor);
        if (z) {
            this.M = colorForState2;
        } else if (z2) {
            this.M = colorForState;
        } else {
            this.M = defaultColor;
        }
    }

    public final boolean w() {
        return this.J > -1 && this.M != 0;
    }

    public final void w0() {
        if (this.g == null) {
            return;
        }
        t.w0(this.z, 0, this.g.getPaddingTop(), (J() || K()) ? 0 : t.D(this.g), this.g.getPaddingBottom());
    }

    public final void x() {
        if (z()) {
            ((c.b.a.a.y.c) this.D).q0();
        }
    }

    public final void x0() {
        int visibility = this.z.getVisibility();
        boolean z = (this.y == null || M()) ? false : true;
        this.z.setVisibility(z ? 0 : 8);
        if (visibility != this.z.getVisibility()) {
            getEndIconDelegate().c(z);
        }
        j0();
    }

    public final void y(boolean z) {
        ValueAnimator valueAnimator = this.H0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.H0.cancel();
        }
        if (z && this.G0) {
            h(1.0f);
        } else {
            this.F0.d0(1.0f);
        }
        this.E0 = false;
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
        boolean z = false;
        boolean z2 = isFocused() || ((editText2 = this.g) != null && editText2.hasFocus());
        boolean z3 = isHovered() || ((editText = this.g) != null && editText.isHovered());
        if (!isEnabled()) {
            this.M = this.D0;
        } else if (this.i.k()) {
            if (this.y0 != null) {
                v0(z2, z3);
            } else {
                this.M = this.i.o();
            }
        } else if (!this.l || (textView = this.m) == null) {
            if (z2) {
                this.M = this.x0;
            } else if (z3) {
                this.M = this.w0;
            } else {
                this.M = this.v0;
            }
        } else if (this.y0 != null) {
            v0(z2, z3);
        } else {
            this.M = textView.getCurrentTextColor();
        }
        if (getErrorIconDrawable() != null && this.i.w() && this.i.k()) {
            z = true;
        }
        setErrorIconVisible(z);
        m0(this.r0, this.s0);
        m0(this.S, this.T);
        m0(this.g0, this.i0);
        if (getEndIconDelegate().d()) {
            d0(this.i.k());
        }
        if (z2 && isEnabled()) {
            this.J = this.L;
        } else {
            this.J = this.K;
        }
        if (this.H == 1) {
            if (!isEnabled()) {
                this.N = this.A0;
            } else if (z3 && !z2) {
                this.N = this.C0;
            } else if (z2) {
                this.N = this.B0;
            } else {
                this.N = this.z0;
            }
        }
        i();
    }

    public final boolean z() {
        return this.A && !TextUtils.isEmpty(this.B) && (this.D instanceof c.b.a.a.y.c);
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.textInputStyle);
    }

    public void setEndIconContentDescription(CharSequence charSequence) {
        if (getEndIconContentDescription() != charSequence) {
            this.g0.setContentDescription(charSequence);
        }
    }

    public void setEndIconDrawable(Drawable drawable) {
        this.g0.setImageDrawable(drawable);
    }

    public void setErrorIconDrawable(Drawable drawable) {
        this.r0.setImageDrawable(drawable);
        setErrorIconVisible(drawable != null && this.i.w());
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
    public TextInputLayout(Context context, AttributeSet attributeSet, int i) {
        int i2;
        int i3 = f2266b;
        super(c.b.a.a.z.a.a.c(context, attributeSet, i, i3), attributeSet, i);
        this.i = new c.b.a.a.y.f(this);
        this.O = new Rect();
        this.P = new Rect();
        this.Q = new RectF();
        this.d0 = new LinkedHashSet<>();
        this.e0 = 0;
        this.f0 = new SparseArray<>();
        this.h0 = new LinkedHashSet<>();
        c.b.a.a.q.a aVar = new c.b.a.a.q.a(this);
        this.F0 = aVar;
        Context context2 = getContext();
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        FrameLayout frameLayout = new FrameLayout(context2);
        this.f2267c = frameLayout;
        frameLayout.setAddStatesFromChildren(true);
        addView(frameLayout);
        LinearLayout linearLayout = new LinearLayout(context2);
        this.f2268d = linearLayout;
        linearLayout.setOrientation(0);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        frameLayout.addView(linearLayout);
        LinearLayout linearLayout2 = new LinearLayout(context2);
        this.e = linearLayout2;
        linearLayout2.setOrientation(0);
        linearLayout2.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388613));
        frameLayout.addView(linearLayout2);
        FrameLayout frameLayout2 = new FrameLayout(context2);
        this.f = frameLayout2;
        frameLayout2.setLayoutParams(new FrameLayout.LayoutParams(-2, -1));
        TimeInterpolator timeInterpolator = c.b.a.a.a.a.f1648a;
        aVar.j0(timeInterpolator);
        aVar.g0(timeInterpolator);
        aVar.R(8388659);
        int[] iArr = R$styleable.TextInputLayout;
        int i4 = R$styleable.TextInputLayout_counterTextAppearance;
        int i5 = R$styleable.TextInputLayout_counterOverflowTextAppearance;
        int i6 = R$styleable.TextInputLayout_errorTextAppearance;
        int i7 = R$styleable.TextInputLayout_helperTextTextAppearance;
        int i8 = R$styleable.TextInputLayout_hintTextAppearance;
        e0 e0VarI = k.i(context2, attributeSet, iArr, i, i3, i4, i5, i6, i7, i8);
        this.A = e0VarI.a(R$styleable.TextInputLayout_hintEnabled, true);
        setHint(e0VarI.p(R$styleable.TextInputLayout_android_hint));
        this.G0 = e0VarI.a(R$styleable.TextInputLayout_hintAnimationEnabled, true);
        this.F = m.e(context2, attributeSet, i, i3).m();
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
        ColorStateList colorStateListB = c.b.a.a.s.c.b(context2, e0VarI, R$styleable.TextInputLayout_boxBackgroundColor);
        if (colorStateListB != null) {
            int defaultColor = colorStateListB.getDefaultColor();
            this.z0 = defaultColor;
            this.N = defaultColor;
            if (colorStateListB.isStateful()) {
                this.A0 = colorStateListB.getColorForState(new int[]{-16842910}, -1);
                i2 = 2;
                this.B0 = colorStateListB.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
                this.C0 = colorStateListB.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            } else {
                i2 = 2;
                this.B0 = this.z0;
                ColorStateList colorStateListC = b.b.b.a.a.c(context2, R$color.mtrl_filled_background_color);
                this.A0 = colorStateListC.getColorForState(new int[]{-16842910}, -1);
                this.C0 = colorStateListC.getColorForState(new int[]{R.attr.state_hovered}, -1);
            }
        } else {
            i2 = 2;
            this.N = 0;
            this.z0 = 0;
            this.A0 = 0;
            this.B0 = 0;
            this.C0 = 0;
        }
        int i9 = R$styleable.TextInputLayout_android_textColorHint;
        if (e0VarI.s(i9)) {
            ColorStateList colorStateListC2 = e0VarI.c(i9);
            this.u0 = colorStateListC2;
            this.t0 = colorStateListC2;
        }
        int i10 = R$styleable.TextInputLayout_boxStrokeColor;
        ColorStateList colorStateListB2 = c.b.a.a.s.c.b(context2, e0VarI, i10);
        this.x0 = e0VarI.b(i10, 0);
        this.v0 = b.g.b.a.b(context2, R$color.mtrl_textinput_default_box_stroke_color);
        this.D0 = b.g.b.a.b(context2, R$color.mtrl_textinput_disabled_color);
        this.w0 = b.g.b.a.b(context2, R$color.mtrl_textinput_hovered_box_stroke_color);
        if (colorStateListB2 != null) {
            setBoxStrokeColorStateList(colorStateListB2);
        }
        int i11 = R$styleable.TextInputLayout_boxStrokeErrorColor;
        if (e0VarI.s(i11)) {
            setBoxStrokeErrorColor(c.b.a.a.s.c.b(context2, e0VarI, i11));
        }
        if (e0VarI.n(i8, -1) != -1) {
            setHintTextAppearance(e0VarI.n(i8, 0));
        }
        int iN = e0VarI.n(i6, 0);
        CharSequence charSequenceP = e0VarI.p(R$styleable.TextInputLayout_errorContentDescription);
        boolean zA = e0VarI.a(R$styleable.TextInputLayout_errorEnabled, false);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int i12 = R$layout.design_text_input_end_icon;
        CheckableImageButton checkableImageButton = (CheckableImageButton) layoutInflaterFrom.inflate(i12, (ViewGroup) linearLayout2, false);
        this.r0 = checkableImageButton;
        checkableImageButton.setVisibility(8);
        int i13 = R$styleable.TextInputLayout_errorIconDrawable;
        if (e0VarI.s(i13)) {
            setErrorIconDrawable(e0VarI.g(i13));
        }
        int i14 = R$styleable.TextInputLayout_errorIconTint;
        if (e0VarI.s(i14)) {
            setErrorIconTintList(c.b.a.a.s.c.b(context2, e0VarI, i14));
        }
        int i15 = R$styleable.TextInputLayout_errorIconTintMode;
        if (e0VarI.s(i15)) {
            setErrorIconTintMode(p.i(e0VarI.k(i15, -1), null));
        }
        checkableImageButton.setContentDescription(getResources().getText(R$string.error_icon_content_description));
        t.t0(checkableImageButton, i2);
        checkableImageButton.setClickable(false);
        checkableImageButton.setPressable(false);
        checkableImageButton.setFocusable(false);
        int iN2 = e0VarI.n(i7, 0);
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
        this.o = e0VarI.n(i4, 0);
        this.n = e0VarI.n(i5, 0);
        CheckableImageButton checkableImageButton2 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(R$layout.design_text_input_start_icon, (ViewGroup) this.f2268d, false);
        this.S = checkableImageButton2;
        checkableImageButton2.setVisibility(8);
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        int i16 = R$styleable.TextInputLayout_startIconDrawable;
        if (e0VarI.s(i16)) {
            setStartIconDrawable(e0VarI.g(i16));
            int i17 = R$styleable.TextInputLayout_startIconContentDescription;
            if (e0VarI.s(i17)) {
                setStartIconContentDescription(e0VarI.p(i17));
            }
            setStartIconCheckable(e0VarI.a(R$styleable.TextInputLayout_startIconCheckable, true));
        }
        int i18 = R$styleable.TextInputLayout_startIconTint;
        if (e0VarI.s(i18)) {
            setStartIconTintList(c.b.a.a.s.c.b(context2, e0VarI, i18));
        }
        int i19 = R$styleable.TextInputLayout_startIconTintMode;
        if (e0VarI.s(i19)) {
            setStartIconTintMode(p.i(e0VarI.k(i19, -1), null));
        }
        setBoxBackgroundMode(e0VarI.k(R$styleable.TextInputLayout_boxBackgroundMode, 0));
        this.g0 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(i12, (ViewGroup) this.f, false);
        this.f.addView(this.g0);
        this.g0.setVisibility(8);
        this.f0.append(-1, new c.b.a.a.y.b(this));
        this.f0.append(0, new c.b.a.a.y.g(this));
        this.f0.append(1, new c.b.a.a.y.h(this));
        this.f0.append(2, new c.b.a.a.y.a(this));
        this.f0.append(3, new c.b.a.a.y.d(this));
        int i20 = R$styleable.TextInputLayout_endIconMode;
        if (e0VarI.s(i20)) {
            setEndIconMode(e0VarI.k(i20, 0));
            int i21 = R$styleable.TextInputLayout_endIconDrawable;
            if (e0VarI.s(i21)) {
                setEndIconDrawable(e0VarI.g(i21));
            }
            int i22 = R$styleable.TextInputLayout_endIconContentDescription;
            if (e0VarI.s(i22)) {
                setEndIconContentDescription(e0VarI.p(i22));
            }
            setEndIconCheckable(e0VarI.a(R$styleable.TextInputLayout_endIconCheckable, true));
        } else {
            int i23 = R$styleable.TextInputLayout_passwordToggleEnabled;
            if (e0VarI.s(i23)) {
                setEndIconMode(e0VarI.a(i23, false) ? 1 : 0);
                setEndIconDrawable(e0VarI.g(R$styleable.TextInputLayout_passwordToggleDrawable));
                setEndIconContentDescription(e0VarI.p(R$styleable.TextInputLayout_passwordToggleContentDescription));
                int i24 = R$styleable.TextInputLayout_passwordToggleTint;
                if (e0VarI.s(i24)) {
                    setEndIconTintList(c.b.a.a.s.c.b(context2, e0VarI, i24));
                }
                int i25 = R$styleable.TextInputLayout_passwordToggleTintMode;
                if (e0VarI.s(i25)) {
                    setEndIconTintMode(p.i(e0VarI.k(i25, -1), null));
                }
            }
        }
        if (!e0VarI.s(R$styleable.TextInputLayout_passwordToggleEnabled)) {
            int i26 = R$styleable.TextInputLayout_endIconTint;
            if (e0VarI.s(i26)) {
                setEndIconTintList(c.b.a.a.s.c.b(context2, e0VarI, i26));
            }
            int i27 = R$styleable.TextInputLayout_endIconTintMode;
            if (e0VarI.s(i27)) {
                setEndIconTintMode(p.i(e0VarI.k(i27, -1), null));
            }
        }
        AppCompatTextView appCompatTextView = new AppCompatTextView(context2);
        this.x = appCompatTextView;
        appCompatTextView.setId(R$id.textinput_prefix_text);
        appCompatTextView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        t.l0(appCompatTextView, 1);
        this.f2268d.addView(this.S);
        this.f2268d.addView(appCompatTextView);
        AppCompatTextView appCompatTextView2 = new AppCompatTextView(context2);
        this.z = appCompatTextView2;
        appCompatTextView2.setId(R$id.textinput_suffix_text);
        appCompatTextView2.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 80));
        t.l0(appCompatTextView2, 1);
        this.e.addView(appCompatTextView2);
        this.e.addView(this.r0);
        this.e.addView(this.f);
        setHelperTextEnabled(zA2);
        setHelperText(charSequenceP2);
        setHelperTextTextAppearance(iN2);
        setErrorEnabled(zA);
        setErrorTextAppearance(iN);
        setErrorContentDescription(charSequenceP);
        setCounterTextAppearance(this.o);
        setCounterOverflowTextAppearance(this.n);
        setPlaceholderText(charSequenceP3);
        setPlaceholderTextAppearance(iN3);
        setPrefixText(charSequenceP4);
        setPrefixTextAppearance(iN4);
        setSuffixText(charSequenceP5);
        setSuffixTextAppearance(iN5);
        int i28 = R$styleable.TextInputLayout_errorTextColor;
        if (e0VarI.s(i28)) {
            setErrorTextColor(e0VarI.c(i28));
        }
        int i29 = R$styleable.TextInputLayout_helperTextTextColor;
        if (e0VarI.s(i29)) {
            setHelperTextColor(e0VarI.c(i29));
        }
        int i30 = R$styleable.TextInputLayout_hintTextColor;
        if (e0VarI.s(i30)) {
            setHintTextColor(e0VarI.c(i30));
        }
        int i31 = R$styleable.TextInputLayout_counterTextColor;
        if (e0VarI.s(i31)) {
            setCounterTextColor(e0VarI.c(i31));
        }
        int i32 = R$styleable.TextInputLayout_counterOverflowTextColor;
        if (e0VarI.s(i32)) {
            setCounterOverflowTextColor(e0VarI.c(i32));
        }
        int i33 = R$styleable.TextInputLayout_placeholderTextColor;
        if (e0VarI.s(i33)) {
            setPlaceholderTextColor(e0VarI.c(i33));
        }
        int i34 = R$styleable.TextInputLayout_prefixTextColor;
        if (e0VarI.s(i34)) {
            setPrefixTextColor(e0VarI.c(i34));
        }
        int i35 = R$styleable.TextInputLayout_suffixTextColor;
        if (e0VarI.s(i35)) {
            setSuffixTextColor(e0VarI.c(i35));
        }
        setCounterEnabled(zA3);
        setEnabled(e0VarI.a(R$styleable.TextInputLayout_android_enabled, true));
        e0VarI.w();
        t.t0(this, 2);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence charSequence) {
        this.g0.setContentDescription(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable drawable) {
        this.g0.setImageDrawable(drawable);
    }
}
