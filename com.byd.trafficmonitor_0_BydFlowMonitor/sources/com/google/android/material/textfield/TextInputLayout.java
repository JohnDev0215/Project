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
import b.b.q.e0;
import b.b.q.q;
import b.h.m.t;
import b.h.n.i;
import c.b.a.a.d0.p;
import c.b.a.a.i0.h;
import c.b.a.a.i0.m;
import c.b.a.a.j;
import c.b.a.a.k;
import c.b.a.a.l;
import com.google.android.material.internal.CheckableImageButton;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public class TextInputLayout extends LinearLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f4883b = k.Widget_Design_TextInputLayout;
    public boolean A;
    public int A0;
    public CharSequence B;
    public boolean B0;
    public boolean C;
    public final c.b.a.a.d0.a C0;
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
    public final LinkedHashSet<f> a0;
    public int b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final FrameLayout f4884c;
    public final SparseArray<c.b.a.a.l0.e> c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinearLayout f4885d;
    public final CheckableImageButton d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LinearLayout f4886e;
    public final LinkedHashSet<g> e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final FrameLayout f4887f;
    public ColorStateList f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public EditText f4888g;
    public boolean g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public CharSequence f4889h;
    public PorterDuff.Mode h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final c.b.a.a.l0.f f4890i;
    public boolean i0;
    public boolean j;
    public Drawable j0;
    public int k;
    public int k0;
    public boolean l;
    public Drawable l0;
    public TextView m;
    public View.OnLongClickListener m0;
    public int n;
    public View.OnLongClickListener n0;
    public int o;
    public final CheckableImageButton o0;
    public CharSequence p;
    public ColorStateList p0;
    public boolean q;
    public ColorStateList q0;
    public TextView r;
    public ColorStateList r0;
    public ColorStateList s;
    public int s0;
    public int t;
    public int t0;
    public ColorStateList u;
    public int u0;
    public ColorStateList v;
    public ColorStateList v0;
    public CharSequence w;
    public int w0;
    public final TextView x;
    public int x0;
    public CharSequence y;
    public int y0;
    public final TextView z;
    public int z0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CharSequence f4891d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f4892e;

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
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.f4891d) + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            TextUtils.writeToParcel(this.f4891d, parcel, i2);
            parcel.writeInt(this.f4892e ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f4891d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f4892e = parcel.readInt() == 1;
        }
    }

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            TextInputLayout.this.o0(!r0.G0);
            TextInputLayout textInputLayout = TextInputLayout.this;
            if (textInputLayout.j) {
                textInputLayout.g0(editable.length());
            }
            if (TextInputLayout.this.q) {
                TextInputLayout.this.s0(editable.length());
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.d0.performClick();
            TextInputLayout.this.d0.jumpDrawablesToCurrentState();
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.f4888g.requestLayout();
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

    public static class e extends b.h.m.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextInputLayout f4897d;

        public e(TextInputLayout textInputLayout) {
            this.f4897d = textInputLayout;
        }

        @Override // b.h.m.a
        public void g(View view, b.h.m.c0.c cVar) {
            super.g(view, cVar);
            EditText editText = this.f4897d.getEditText();
            CharSequence text = editText != null ? editText.getText() : null;
            CharSequence hint = this.f4897d.getHint();
            CharSequence helperText = this.f4897d.getHelperText();
            CharSequence error = this.f4897d.getError();
            int counterMaxLength = this.f4897d.getCounterMaxLength();
            CharSequence counterOverflowDescription = this.f4897d.getCounterOverflowDescription();
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
                cVar.C0(text);
            } else if (!TextUtils.isEmpty(string3)) {
                cVar.C0(string3);
            }
            if (!TextUtils.isEmpty(string3)) {
                if (Build.VERSION.SDK_INT >= 26) {
                    cVar.m0(string3);
                } else {
                    if (z) {
                        string3 = ((Object) text) + ", " + string3;
                    }
                    cVar.C0(string3);
                }
                cVar.z0(!z);
            }
            if (text == null || text.length() != counterMaxLength) {
                counterMaxLength = -1;
            }
            cVar.o0(counterMaxLength);
            if (z5) {
                if (!z4) {
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
        void a(TextInputLayout textInputLayout, int i2);
    }

    public TextInputLayout(Context context) {
        this(context, null);
    }

    public static void S(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
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
        t.u0(checkableImageButton, z2 ? 1 : 2);
    }

    public static void W(CheckableImageButton checkableImageButton, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        checkableImageButton.setOnClickListener(onClickListener);
        V(checkableImageButton, onLongClickListener);
    }

    public static void X(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        V(checkableImageButton, onLongClickListener);
    }

    private c.b.a.a.l0.e getEndIconDelegate() {
        c.b.a.a.l0.e eVar = this.c0.get(this.b0);
        return eVar != null ? eVar : this.c0.get(0);
    }

    private CheckableImageButton getEndIconToUpdateDummyDrawable() {
        if (this.o0.getVisibility() == 0) {
            return this.o0;
        }
        if (H() && J()) {
            return this.d0;
        }
        return null;
    }

    public static void h0(Context context, TextView textView, int i2, int i3, boolean z) {
        textView.setContentDescription(context.getString(z ? j.character_counter_overflowed_content_description : j.character_counter_content_description, Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    private void setEditText(EditText editText) {
        if (this.f4888g != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (this.b0 != 3 && !(editText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.f4888g = editText;
        Q();
        setTextInputAccessibilityDelegate(new e(this));
        this.C0.k0(this.f4888g.getTypeface());
        this.C0.a0(this.f4888g.getTextSize());
        int gravity = this.f4888g.getGravity();
        this.C0.R((gravity & (-113)) | 48);
        this.C0.Z(gravity);
        this.f4888g.addTextChangedListener(new a());
        if (this.q0 == null) {
            this.q0 = this.f4888g.getHintTextColors();
        }
        if (this.A) {
            if (TextUtils.isEmpty(this.B)) {
                CharSequence hint = this.f4888g.getHint();
                this.f4889h = hint;
                setHint(hint);
                this.f4888g.setHint((CharSequence) null);
            }
            this.C = true;
        }
        if (this.m != null) {
            g0(this.f4888g.getText().length());
        }
        k0();
        this.f4890i.e();
        this.f4885d.bringToFront();
        this.f4886e.bringToFront();
        this.f4887f.bringToFront();
        this.o0.bringToFront();
        A();
        t0();
        w0();
        if (!isEnabled()) {
            editText.setEnabled(false);
        }
        p0(false, true);
    }

    private void setErrorIconVisible(boolean z) {
        this.o0.setVisibility(z ? 0 : 8);
        this.f4887f.setVisibility(z ? 8 : 0);
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

    private void setPlaceholderTextEnabled(boolean z) {
        if (this.q == z) {
            return;
        }
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
            this.r = appCompatTextView;
            appCompatTextView.setId(c.b.a.a.f.textinput_placeholder);
            t.m0(this.r, 1);
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
        Iterator<f> it = this.a0.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    public final void B(int i2) {
        Iterator<g> it = this.e0.iterator();
        while (it.hasNext()) {
            it.next().a(this, i2);
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

    public final void E(boolean z) {
        ValueAnimator valueAnimator = this.E0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.E0.cancel();
        }
        if (z && this.D0) {
            h(0.0f);
        } else {
            this.C0.d0(0.0f);
        }
        if (z() && ((c.b.a.a.l0.c) this.D).n0()) {
            x();
        }
        this.B0 = true;
        I();
        u0();
        x0();
    }

    public final int F(int i2, boolean z) {
        int compoundPaddingLeft = i2 + this.f4888g.getCompoundPaddingLeft();
        return (this.w == null || z) ? compoundPaddingLeft : (compoundPaddingLeft - this.x.getMeasuredWidth()) + this.x.getPaddingLeft();
    }

    public final int G(int i2, boolean z) {
        int compoundPaddingRight = i2 - this.f4888g.getCompoundPaddingRight();
        return (this.w == null || !z) ? compoundPaddingRight : compoundPaddingRight + (this.x.getMeasuredWidth() - this.x.getPaddingRight());
    }

    public final boolean H() {
        return this.b0 != 0;
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
        return this.f4887f.getVisibility() == 0 && this.d0.getVisibility() == 0;
    }

    public final boolean K() {
        return this.o0.getVisibility() == 0;
    }

    public boolean L() {
        return this.f4890i.x();
    }

    public final boolean M() {
        return this.B0;
    }

    public boolean N() {
        return this.C;
    }

    public final boolean O() {
        return this.H == 1 && (Build.VERSION.SDK_INT < 16 || this.f4888g.getMinLines() <= 1);
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
            this.C0.m(rectF, this.f4888g.getWidth(), this.f4888g.getGravity());
            k(rectF);
            rectF.offset(-getPaddingLeft(), -getPaddingTop());
            ((c.b.a.a.l0.c) this.D).t0(rectF);
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
            t.n0(this.f4888g, this.D);
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
            b.h.n.i.q(r3, r4)     // Catch: java.lang.Exception -> L1a
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
            int r4 = c.b.a.a.k.TextAppearance_AppCompat_Caption
            b.h.n.i.q(r3, r4)
            android.content.Context r4 = r2.getContext()
            int r0 = c.b.a.a.c.design_error
            int r4 = b.h.e.a.b(r4, r0)
            r3.setTextColor(r4)
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.Y(android.widget.TextView, int):void");
    }

    public final boolean Z() {
        return (this.o0.getVisibility() == 0 || ((H() && J()) || this.y != null)) && this.f4886e.getMeasuredWidth() > 0;
    }

    public final boolean a0() {
        return !(getStartIconDrawable() == null && this.w == null) && this.f4885d.getMeasuredWidth() > 0;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i2, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
        this.f4884c.addView(view, layoutParams2);
        this.f4884c.setLayoutParams(layoutParams);
        n0();
        setEditText((EditText) view);
    }

    public final boolean b0() {
        EditText editText = this.f4888g;
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
        Drawable drawableMutate = b.h.f.l.a.r(getEndIconDrawable()).mutate();
        b.h.f.l.a.n(drawableMutate, this.f4890i.o());
        this.d0.setImageDrawable(drawableMutate);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i2) {
        EditText editText;
        if (this.f4889h == null || (editText = this.f4888g) == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i2);
            return;
        }
        boolean z = this.C;
        this.C = false;
        CharSequence hint = editText.getHint();
        this.f4888g.setHint(this.f4889h);
        try {
            super.dispatchProvideAutofillStructure(viewStructure, i2);
        } finally {
            this.f4888g.setHint(hint);
            this.C = z;
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
        c.b.a.a.d0.a aVar = this.C0;
        boolean zH0 = aVar != null ? aVar.h0(drawableState) | false : false;
        if (this.f4888g != null) {
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
        this.a0.add(fVar);
        if (this.f4888g != null) {
            fVar.a(this);
        }
    }

    public final void e0(Rect rect) {
        h hVar = this.E;
        if (hVar != null) {
            int i2 = rect.bottom;
            hVar.setBounds(rect.left, i2 - this.L, rect.right, i2);
        }
    }

    public void f(g gVar) {
        this.e0.add(gVar);
    }

    public final void f0() {
        if (this.m != null) {
            EditText editText = this.f4888g;
            g0(editText == null ? 0 : editText.getText().length());
        }
    }

    public final void g() {
        TextView textView = this.r;
        if (textView != null) {
            this.f4884c.addView(textView);
            this.r.setVisibility(0);
        }
    }

    public void g0(int i2) {
        boolean z = this.l;
        int i3 = this.k;
        if (i3 == -1) {
            this.m.setText(String.valueOf(i2));
            this.m.setContentDescription(null);
            this.l = false;
        } else {
            this.l = i2 > i3;
            h0(getContext(), this.m, i2, this.k, this.l);
            if (z != this.l) {
                i0();
            }
            this.m.setText(b.h.k.a.c().j(getContext().getString(j.character_counter_pattern, Integer.valueOf(i2), Integer.valueOf(this.k))));
        }
        if (this.f4888g == null || z == this.l) {
            return;
        }
        o0(false);
        y0();
        k0();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.f4888g;
        return editText != null ? editText.getBaseline() + getPaddingTop() + u() : super.getBaseline();
    }

    public h getBoxBackground() {
        int i2 = this.H;
        if (i2 == 1 || i2 == 2) {
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
        return this.u0;
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.v0;
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
        return this.q0;
    }

    public EditText getEditText() {
        return this.f4888g;
    }

    public CharSequence getEndIconContentDescription() {
        return this.d0.getContentDescription();
    }

    public Drawable getEndIconDrawable() {
        return this.d0.getDrawable();
    }

    public int getEndIconMode() {
        return this.b0;
    }

    public CheckableImageButton getEndIconView() {
        return this.d0;
    }

    public CharSequence getError() {
        if (this.f4890i.w()) {
            return this.f4890i.n();
        }
        return null;
    }

    public CharSequence getErrorContentDescription() {
        return this.f4890i.m();
    }

    public int getErrorCurrentTextColors() {
        return this.f4890i.o();
    }

    public Drawable getErrorIconDrawable() {
        return this.o0.getDrawable();
    }

    public final int getErrorTextCurrentColor() {
        return this.f4890i.o();
    }

    public CharSequence getHelperText() {
        if (this.f4890i.x()) {
            return this.f4890i.q();
        }
        return null;
    }

    public int getHelperTextCurrentTextColor() {
        return this.f4890i.r();
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
        return this.r0;
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.d0.getContentDescription();
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.d0.getDrawable();
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
        if (this.C0.z() == f2) {
            return;
        }
        if (this.E0 == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.E0 = valueAnimator;
            valueAnimator.setInterpolator(c.b.a.a.m.a.f4009b);
            this.E0.setDuration(167L);
            this.E0.addUpdateListener(new d());
        }
        this.E0.setFloatValues(this.C0.z(), f2);
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
        if (this.b0 == 3) {
            this.f4888g.getBackground().invalidateSelf();
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
        if (this.f4888g == null) {
            return false;
        }
        boolean z2 = true;
        if (a0()) {
            int measuredWidth = this.f4885d.getMeasuredWidth() - this.f4888g.getPaddingLeft();
            if (this.X == null || this.Y != measuredWidth) {
                ColorDrawable colorDrawable = new ColorDrawable();
                this.X = colorDrawable;
                this.Y = measuredWidth;
                colorDrawable.setBounds(0, 0, measuredWidth, 1);
            }
            Drawable[] drawableArrA = i.a(this.f4888g);
            Drawable drawable = drawableArrA[0];
            Drawable drawable2 = this.X;
            if (drawable != drawable2) {
                i.l(this.f4888g, drawable2, drawableArrA[1], drawableArrA[2], drawableArrA[3]);
                z = true;
            }
            z = false;
        } else {
            if (this.X != null) {
                Drawable[] drawableArrA2 = i.a(this.f4888g);
                i.l(this.f4888g, null, drawableArrA2[1], drawableArrA2[2], drawableArrA2[3]);
                this.X = null;
                z = true;
            }
            z = false;
        }
        if (Z()) {
            int measuredWidth2 = this.z.getMeasuredWidth() - this.f4888g.getPaddingRight();
            CheckableImageButton endIconToUpdateDummyDrawable = getEndIconToUpdateDummyDrawable();
            if (endIconToUpdateDummyDrawable != null) {
                measuredWidth2 = measuredWidth2 + endIconToUpdateDummyDrawable.getMeasuredWidth() + b.h.m.g.b((ViewGroup.MarginLayoutParams) endIconToUpdateDummyDrawable.getLayoutParams());
            }
            Drawable[] drawableArrA3 = i.a(this.f4888g);
            Drawable drawable3 = this.j0;
            if (drawable3 == null || this.k0 == measuredWidth2) {
                if (drawable3 == null) {
                    ColorDrawable colorDrawable2 = new ColorDrawable();
                    this.j0 = colorDrawable2;
                    this.k0 = measuredWidth2;
                    colorDrawable2.setBounds(0, 0, measuredWidth2, 1);
                }
                Drawable drawable4 = drawableArrA3[2];
                Drawable drawable5 = this.j0;
                if (drawable4 != drawable5) {
                    this.l0 = drawableArrA3[2];
                    i.l(this.f4888g, drawableArrA3[0], drawableArrA3[1], drawable5, drawableArrA3[3]);
                } else {
                    z2 = z;
                }
            } else {
                this.k0 = measuredWidth2;
                drawable3.setBounds(0, 0, measuredWidth2, 1);
                i.l(this.f4888g, drawableArrA3[0], drawableArrA3[1], this.j0, drawableArrA3[3]);
            }
        } else {
            if (this.j0 == null) {
                return z;
            }
            Drawable[] drawableArrA4 = i.a(this.f4888g);
            if (drawableArrA4[2] == this.j0) {
                i.l(this.f4888g, drawableArrA4[0], drawableArrA4[1], this.l0, drawableArrA4[3]);
            } else {
                z2 = z;
            }
            this.j0 = null;
        }
        return z2;
    }

    public final void k(RectF rectF) {
        float f2 = rectF.left;
        int i2 = this.G;
        rectF.left = f2 - i2;
        rectF.top -= i2;
        rectF.right += i2;
        rectF.bottom += i2;
    }

    public void k0() {
        Drawable background;
        TextView textView;
        EditText editText = this.f4888g;
        if (editText == null || this.H != 0 || (background = editText.getBackground()) == null) {
            return;
        }
        if (q.a(background)) {
            background = background.mutate();
        }
        if (this.f4890i.k()) {
            background.setColorFilter(b.b.q.f.e(this.f4890i.o(), PorterDuff.Mode.SRC_IN));
        } else if (this.l && (textView = this.m) != null) {
            background.setColorFilter(b.b.q.f.e(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            b.h.f.l.a.c(background);
            this.f4888g.refreshDrawableState();
        }
    }

    public final void l() {
        m(this.d0, this.g0, this.f0, this.i0, this.h0);
    }

    public final boolean l0() {
        int iMax;
        if (this.f4888g == null || this.f4888g.getMeasuredHeight() >= (iMax = Math.max(this.f4886e.getMeasuredHeight(), this.f4885d.getMeasuredHeight()))) {
            return false;
        }
        this.f4888g.setMinimumHeight(iMax);
        return true;
    }

    public final void m(CheckableImageButton checkableImageButton, boolean z, ColorStateList colorStateList, boolean z2, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null && (z || z2)) {
            drawable = b.h.f.l.a.r(drawable).mutate();
            if (z) {
                b.h.f.l.a.o(drawable, colorStateList);
            }
            if (z2) {
                b.h.f.l.a.p(drawable, mode);
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
        Drawable drawableMutate = b.h.f.l.a.r(drawable).mutate();
        b.h.f.l.a.o(drawableMutate, ColorStateList.valueOf(colorForState));
        checkableImageButton.setImageDrawable(drawableMutate);
    }

    public final void n() {
        m(this.S, this.U, this.T, this.W, this.V);
    }

    public final void n0() {
        if (this.H != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f4884c.getLayoutParams();
            int iU = u();
            if (iU != layoutParams.topMargin) {
                layoutParams.topMargin = iU;
                this.f4884c.requestLayout();
            }
        }
    }

    public final void o() {
        int i2 = this.H;
        if (i2 == 0) {
            this.D = null;
            this.E = null;
            return;
        }
        if (i2 == 1) {
            this.D = new h(this.F);
            this.E = new h();
        } else {
            if (i2 != 2) {
                throw new IllegalArgumentException(this.H + " is illegal; only @BoxBackgroundMode constants are supported.");
            }
            if (!this.A || (this.D instanceof c.b.a.a.l0.c)) {
                this.D = new h(this.F);
            } else {
                this.D = new c.b.a.a.l0.c(this.F);
            }
            this.E = null;
        }
    }

    public void o0(boolean z) {
        p0(z, false);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        EditText editText = this.f4888g;
        if (editText != null) {
            Rect rect = this.O;
            c.b.a.a.d0.c.a(this, editText, rect);
            e0(rect);
            if (this.A) {
                this.C0.a0(this.f4888g.getTextSize());
                int gravity = this.f4888g.getGravity();
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
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        boolean zL0 = l0();
        boolean zJ0 = j0();
        if (zL0 || zJ0) {
            this.f4888g.post(new c());
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
        super.onRestoreInstanceState(savedState.j());
        setError(savedState.f4891d);
        if (savedState.f4892e) {
            this.d0.post(new b());
        }
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.f4890i.k()) {
            savedState.f4891d = getError();
        }
        savedState.f4892e = H() && this.d0.isChecked();
        return savedState;
    }

    public final int p() {
        return this.H == 1 ? c.b.a.a.w.a.e(c.b.a.a.w.a.d(this, c.b.a.a.b.colorSurface, 0), this.N) : this.N;
    }

    public final void p0(boolean z, boolean z2) {
        ColorStateList colorStateList;
        TextView textView;
        boolean zIsEnabled = isEnabled();
        EditText editText = this.f4888g;
        boolean z3 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.f4888g;
        boolean z4 = editText2 != null && editText2.hasFocus();
        boolean zK = this.f4890i.k();
        ColorStateList colorStateList2 = this.q0;
        if (colorStateList2 != null) {
            this.C0.Q(colorStateList2);
            this.C0.Y(this.q0);
        }
        if (!zIsEnabled) {
            ColorStateList colorStateList3 = this.q0;
            int colorForState = colorStateList3 != null ? colorStateList3.getColorForState(new int[]{-16842910}, this.A0) : this.A0;
            this.C0.Q(ColorStateList.valueOf(colorForState));
            this.C0.Y(ColorStateList.valueOf(colorForState));
        } else if (zK) {
            this.C0.Q(this.f4890i.p());
        } else if (this.l && (textView = this.m) != null) {
            this.C0.Q(textView.getTextColors());
        } else if (z4 && (colorStateList = this.r0) != null) {
            this.C0.Q(colorStateList);
        }
        if (z3 || (isEnabled() && (z4 || zK))) {
            if (z2 || this.B0) {
                y(z);
                return;
            }
            return;
        }
        if (z2 || !this.B0) {
            E(z);
        }
    }

    public final Rect q(Rect rect) {
        if (this.f4888g == null) {
            throw new IllegalStateException();
        }
        Rect rect2 = this.P;
        boolean z = t.z(this) == 1;
        rect2.bottom = rect.bottom;
        int i2 = this.H;
        if (i2 == 1) {
            rect2.left = F(rect.left, z);
            rect2.top = rect.top + this.I;
            rect2.right = G(rect.right, z);
            return rect2;
        }
        if (i2 != 2) {
            rect2.left = F(rect.left, z);
            rect2.top = getPaddingTop();
            rect2.right = G(rect.right, z);
            return rect2;
        }
        rect2.left = rect.left + this.f4888g.getPaddingLeft();
        rect2.top = rect.top - u();
        rect2.right = rect.right - this.f4888g.getPaddingRight();
        return rect2;
    }

    public final void q0() {
        EditText editText;
        if (this.r == null || (editText = this.f4888g) == null) {
            return;
        }
        this.r.setGravity(editText.getGravity());
        this.r.setPadding(this.f4888g.getCompoundPaddingLeft(), this.f4888g.getCompoundPaddingTop(), this.f4888g.getCompoundPaddingRight(), this.f4888g.getCompoundPaddingBottom());
    }

    public final int r(Rect rect, Rect rect2, float f2) {
        return O() ? (int) (rect2.top + f2) : rect.bottom - this.f4888g.getCompoundPaddingBottom();
    }

    public final void r0() {
        EditText editText = this.f4888g;
        s0(editText == null ? 0 : editText.getText().length());
    }

    public final int s(Rect rect, float f2) {
        return O() ? (int) (rect.centerY() - (f2 / 2.0f)) : rect.top + this.f4888g.getCompoundPaddingTop();
    }

    public final void s0(int i2) {
        if (i2 != 0 || this.B0) {
            I();
        } else {
            c0();
        }
    }

    public void setBoxBackgroundColor(int i2) {
        if (this.N != i2) {
            this.N = i2;
            this.w0 = i2;
            this.y0 = i2;
            this.z0 = i2;
            i();
        }
    }

    public void setBoxBackgroundColorResource(int i2) {
        setBoxBackgroundColor(b.h.e.a.b(getContext(), i2));
    }

    public void setBoxBackgroundColorStateList(ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.w0 = defaultColor;
        this.N = defaultColor;
        this.x0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
        this.y0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        this.z0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
        i();
    }

    public void setBoxBackgroundMode(int i2) {
        if (i2 == this.H) {
            return;
        }
        this.H = i2;
        if (this.f4888g != null) {
            Q();
        }
    }

    public void setBoxStrokeColor(int i2) {
        if (this.u0 != i2) {
            this.u0 = i2;
            y0();
        }
    }

    public void setBoxStrokeColorStateList(ColorStateList colorStateList) {
        if (colorStateList.isStateful()) {
            this.s0 = colorStateList.getDefaultColor();
            this.A0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.t0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            this.u0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        } else if (this.u0 != colorStateList.getDefaultColor()) {
            this.u0 = colorStateList.getDefaultColor();
        }
        y0();
    }

    public void setBoxStrokeErrorColor(ColorStateList colorStateList) {
        if (this.v0 != colorStateList) {
            this.v0 = colorStateList;
            y0();
        }
    }

    public void setBoxStrokeWidth(int i2) {
        this.K = i2;
        y0();
    }

    public void setBoxStrokeWidthFocused(int i2) {
        this.L = i2;
        y0();
    }

    public void setBoxStrokeWidthFocusedResource(int i2) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(i2));
    }

    public void setBoxStrokeWidthResource(int i2) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(i2));
    }

    public void setCounterEnabled(boolean z) {
        if (this.j != z) {
            if (z) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
                this.m = appCompatTextView;
                appCompatTextView.setId(c.b.a.a.f.textinput_counter);
                Typeface typeface = this.R;
                if (typeface != null) {
                    this.m.setTypeface(typeface);
                }
                this.m.setMaxLines(1);
                this.f4890i.d(this.m, 2);
                b.h.m.g.d((ViewGroup.MarginLayoutParams) this.m.getLayoutParams(), getResources().getDimensionPixelOffset(c.b.a.a.d.mtrl_textinput_counter_margin_start));
                i0();
                f0();
            } else {
                this.f4890i.y(this.m, 2);
                this.m = null;
            }
            this.j = z;
        }
    }

    public void setCounterMaxLength(int i2) {
        if (this.k != i2) {
            if (i2 > 0) {
                this.k = i2;
            } else {
                this.k = -1;
            }
            if (this.j) {
                f0();
            }
        }
    }

    public void setCounterOverflowTextAppearance(int i2) {
        if (this.n != i2) {
            this.n = i2;
            i0();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList colorStateList) {
        if (this.v != colorStateList) {
            this.v = colorStateList;
            i0();
        }
    }

    public void setCounterTextAppearance(int i2) {
        if (this.o != i2) {
            this.o = i2;
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
        this.q0 = colorStateList;
        this.r0 = colorStateList;
        if (this.f4888g != null) {
            o0(false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        S(this, z);
        super.setEnabled(z);
    }

    public void setEndIconActivated(boolean z) {
        this.d0.setActivated(z);
    }

    public void setEndIconCheckable(boolean z) {
        this.d0.setCheckable(z);
    }

    public void setEndIconContentDescription(int i2) {
        setEndIconContentDescription(i2 != 0 ? getResources().getText(i2) : null);
    }

    public void setEndIconDrawable(int i2) {
        setEndIconDrawable(i2 != 0 ? b.b.l.a.a.d(getContext(), i2) : null);
    }

    public void setEndIconMode(int i2) {
        int i3 = this.b0;
        this.b0 = i2;
        B(i3);
        setEndIconVisible(i2 != 0);
        if (getEndIconDelegate().b(this.H)) {
            getEndIconDelegate().a();
            l();
            return;
        }
        throw new IllegalStateException("The current box background mode " + this.H + " is not supported by the end icon mode " + i2);
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        W(this.d0, onClickListener, this.m0);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.m0 = onLongClickListener;
        X(this.d0, onLongClickListener);
    }

    public void setEndIconTintList(ColorStateList colorStateList) {
        if (this.f0 != colorStateList) {
            this.f0 = colorStateList;
            this.g0 = true;
            l();
        }
    }

    public void setEndIconTintMode(PorterDuff.Mode mode) {
        if (this.h0 != mode) {
            this.h0 = mode;
            this.i0 = true;
            l();
        }
    }

    public void setEndIconVisible(boolean z) {
        if (J() != z) {
            this.d0.setVisibility(z ? 0 : 8);
            w0();
            j0();
        }
    }

    public void setError(CharSequence charSequence) {
        if (!this.f4890i.w()) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.f4890i.s();
        } else {
            this.f4890i.L(charSequence);
        }
    }

    public void setErrorContentDescription(CharSequence charSequence) {
        this.f4890i.A(charSequence);
    }

    public void setErrorEnabled(boolean z) {
        this.f4890i.B(z);
    }

    public void setErrorIconDrawable(int i2) {
        setErrorIconDrawable(i2 != 0 ? b.b.l.a.a.d(getContext(), i2) : null);
    }

    public void setErrorIconOnClickListener(View.OnClickListener onClickListener) {
        W(this.o0, onClickListener, this.n0);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.n0 = onLongClickListener;
        X(this.o0, onLongClickListener);
    }

    public void setErrorIconTintList(ColorStateList colorStateList) {
        this.p0 = colorStateList;
        Drawable drawable = this.o0.getDrawable();
        if (drawable != null) {
            drawable = b.h.f.l.a.r(drawable).mutate();
            b.h.f.l.a.o(drawable, colorStateList);
        }
        if (this.o0.getDrawable() != drawable) {
            this.o0.setImageDrawable(drawable);
        }
    }

    public void setErrorIconTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.o0.getDrawable();
        if (drawable != null) {
            drawable = b.h.f.l.a.r(drawable).mutate();
            b.h.f.l.a.p(drawable, mode);
        }
        if (this.o0.getDrawable() != drawable) {
            this.o0.setImageDrawable(drawable);
        }
    }

    public void setErrorTextAppearance(int i2) {
        this.f4890i.C(i2);
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        this.f4890i.D(colorStateList);
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
            this.f4890i.M(charSequence);
        }
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        this.f4890i.G(colorStateList);
    }

    public void setHelperTextEnabled(boolean z) {
        this.f4890i.F(z);
    }

    public void setHelperTextTextAppearance(int i2) {
        this.f4890i.E(i2);
    }

    public void setHint(CharSequence charSequence) {
        if (this.A) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z) {
        this.D0 = z;
    }

    public void setHintEnabled(boolean z) {
        if (z != this.A) {
            this.A = z;
            if (z) {
                CharSequence hint = this.f4888g.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.B)) {
                        setHint(hint);
                    }
                    this.f4888g.setHint((CharSequence) null);
                }
                this.C = true;
            } else {
                this.C = false;
                if (!TextUtils.isEmpty(this.B) && TextUtils.isEmpty(this.f4888g.getHint())) {
                    this.f4888g.setHint(this.B);
                }
                setHintInternal(null);
            }
            if (this.f4888g != null) {
                n0();
            }
        }
    }

    public void setHintTextAppearance(int i2) {
        this.C0.O(i2);
        this.r0 = this.C0.n();
        if (this.f4888g != null) {
            o0(false);
            n0();
        }
    }

    public void setHintTextColor(ColorStateList colorStateList) {
        if (this.r0 != colorStateList) {
            if (this.q0 == null) {
                this.C0.Q(colorStateList);
            }
            this.r0 = colorStateList;
            if (this.f4888g != null) {
                o0(false);
            }
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(int i2) {
        setPasswordVisibilityToggleContentDescription(i2 != 0 ? getResources().getText(i2) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int i2) {
        setPasswordVisibilityToggleDrawable(i2 != 0 ? b.b.l.a.a.d(getContext(), i2) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z) {
        if (z && this.b0 != 1) {
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
        this.f0 = colorStateList;
        this.g0 = true;
        l();
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        this.h0 = mode;
        this.i0 = true;
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

    public void setPlaceholderTextAppearance(int i2) {
        this.t = i2;
        TextView textView = this.r;
        if (textView != null) {
            i.q(textView, i2);
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

    public void setPrefixTextAppearance(int i2) {
        i.q(this.x, i2);
    }

    public void setPrefixTextColor(ColorStateList colorStateList) {
        this.x.setTextColor(colorStateList);
    }

    public void setStartIconCheckable(boolean z) {
        this.S.setCheckable(z);
    }

    public void setStartIconContentDescription(int i2) {
        setStartIconContentDescription(i2 != 0 ? getResources().getText(i2) : null);
    }

    public void setStartIconDrawable(int i2) {
        setStartIconDrawable(i2 != 0 ? b.b.l.a.a.d(getContext(), i2) : null);
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

    public void setSuffixTextAppearance(int i2) {
        i.q(this.z, i2);
    }

    public void setSuffixTextColor(ColorStateList colorStateList) {
        this.z.setTextColor(colorStateList);
    }

    public void setTextInputAccessibilityDelegate(e eVar) {
        EditText editText = this.f4888g;
        if (editText != null) {
            t.k0(editText, eVar);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != this.R) {
            this.R = typeface;
            this.C0.k0(typeface);
            this.f4890i.I(typeface);
            TextView textView = this.m;
            if (textView != null) {
                textView.setTypeface(typeface);
            }
        }
    }

    public final Rect t(Rect rect) {
        if (this.f4888g == null) {
            throw new IllegalStateException();
        }
        Rect rect2 = this.P;
        float fX = this.C0.x();
        rect2.left = rect.left + this.f4888g.getCompoundPaddingLeft();
        rect2.top = s(rect, fX);
        rect2.right = rect.right - this.f4888g.getCompoundPaddingRight();
        rect2.bottom = r(rect, rect2, fX);
        return rect2;
    }

    public final void t0() {
        if (this.f4888g == null) {
            return;
        }
        t.y0(this.x, P() ? 0 : t.E(this.f4888g), this.f4888g.getCompoundPaddingTop(), 0, this.f4888g.getCompoundPaddingBottom());
    }

    public final int u() {
        float fP;
        if (!this.A) {
            return 0;
        }
        int i2 = this.H;
        if (i2 == 0 || i2 == 1) {
            fP = this.C0.p();
        } else {
            if (i2 != 2) {
                return 0;
            }
            fP = this.C0.p() / 2.0f;
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
        int defaultColor = this.v0.getDefaultColor();
        int colorForState = this.v0.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, defaultColor);
        int colorForState2 = this.v0.getColorForState(new int[]{R.attr.state_activated, R.attr.state_enabled}, defaultColor);
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
        if (this.f4888g == null) {
            return;
        }
        t.y0(this.z, 0, this.f4888g.getPaddingTop(), (J() || K()) ? 0 : t.D(this.f4888g), this.f4888g.getPaddingBottom());
    }

    public final void x() {
        if (z()) {
            ((c.b.a.a.l0.c) this.D).q0();
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
        ValueAnimator valueAnimator = this.E0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.E0.cancel();
        }
        if (z && this.D0) {
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
        boolean z = false;
        boolean z2 = isFocused() || ((editText2 = this.f4888g) != null && editText2.hasFocus());
        boolean z3 = isHovered() || ((editText = this.f4888g) != null && editText.isHovered());
        if (!isEnabled()) {
            this.M = this.A0;
        } else if (this.f4890i.k()) {
            if (this.v0 != null) {
                v0(z2, z3);
            } else {
                this.M = this.f4890i.o();
            }
        } else if (!this.l || (textView = this.m) == null) {
            if (z2) {
                this.M = this.u0;
            } else if (z3) {
                this.M = this.t0;
            } else {
                this.M = this.s0;
            }
        } else if (this.v0 != null) {
            v0(z2, z3);
        } else {
            this.M = textView.getCurrentTextColor();
        }
        if (getErrorIconDrawable() != null && this.f4890i.w() && this.f4890i.k()) {
            z = true;
        }
        setErrorIconVisible(z);
        m0(this.o0, this.p0);
        m0(this.S, this.T);
        m0(this.d0, this.f0);
        if (getEndIconDelegate().d()) {
            d0(this.f4890i.k());
        }
        if (z2 && isEnabled()) {
            this.J = this.L;
        } else {
            this.J = this.K;
        }
        if (this.H == 1) {
            if (!isEnabled()) {
                this.N = this.x0;
            } else if (z3 && !z2) {
                this.N = this.z0;
            } else if (z2) {
                this.N = this.y0;
            } else {
                this.N = this.w0;
            }
        }
        i();
    }

    public final boolean z() {
        return this.A && !TextUtils.isEmpty(this.B) && (this.D instanceof c.b.a.a.l0.c);
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.b.a.a.b.textInputStyle);
    }

    public void setEndIconContentDescription(CharSequence charSequence) {
        if (getEndIconContentDescription() != charSequence) {
            this.d0.setContentDescription(charSequence);
        }
    }

    public void setEndIconDrawable(Drawable drawable) {
        this.d0.setImageDrawable(drawable);
    }

    public void setErrorIconDrawable(Drawable drawable) {
        this.o0.setImageDrawable(drawable);
        setErrorIconVisible(drawable != null && this.f4890i.w());
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
    public TextInputLayout(Context context, AttributeSet attributeSet, int i2) {
        int i3;
        int i4 = f4883b;
        super(c.b.a.a.m0.a.a.c(context, attributeSet, i2, i4), attributeSet, i2);
        this.f4890i = new c.b.a.a.l0.f(this);
        this.O = new Rect();
        this.P = new Rect();
        this.Q = new RectF();
        this.a0 = new LinkedHashSet<>();
        this.b0 = 0;
        this.c0 = new SparseArray<>();
        this.e0 = new LinkedHashSet<>();
        c.b.a.a.d0.a aVar = new c.b.a.a.d0.a(this);
        this.C0 = aVar;
        Context context2 = getContext();
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        FrameLayout frameLayout = new FrameLayout(context2);
        this.f4884c = frameLayout;
        frameLayout.setAddStatesFromChildren(true);
        addView(frameLayout);
        LinearLayout linearLayout = new LinearLayout(context2);
        this.f4885d = linearLayout;
        linearLayout.setOrientation(0);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        frameLayout.addView(linearLayout);
        LinearLayout linearLayout2 = new LinearLayout(context2);
        this.f4886e = linearLayout2;
        linearLayout2.setOrientation(0);
        linearLayout2.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388613));
        frameLayout.addView(linearLayout2);
        FrameLayout frameLayout2 = new FrameLayout(context2);
        this.f4887f = frameLayout2;
        frameLayout2.setLayoutParams(new FrameLayout.LayoutParams(-2, -1));
        TimeInterpolator timeInterpolator = c.b.a.a.m.a.f4008a;
        aVar.j0(timeInterpolator);
        aVar.g0(timeInterpolator);
        aVar.R(8388659);
        int[] iArr = l.TextInputLayout;
        int i5 = l.TextInputLayout_counterTextAppearance;
        int i6 = l.TextInputLayout_counterOverflowTextAppearance;
        int i7 = l.TextInputLayout_errorTextAppearance;
        int i8 = l.TextInputLayout_helperTextTextAppearance;
        int i9 = l.TextInputLayout_hintTextAppearance;
        e0 e0VarI = c.b.a.a.d0.k.i(context2, attributeSet, iArr, i2, i4, i5, i6, i7, i8, i9);
        this.A = e0VarI.a(l.TextInputLayout_hintEnabled, true);
        setHint(e0VarI.p(l.TextInputLayout_android_hint));
        this.D0 = e0VarI.a(l.TextInputLayout_hintAnimationEnabled, true);
        this.F = m.e(context2, attributeSet, i2, i4).m();
        this.G = context2.getResources().getDimensionPixelOffset(c.b.a.a.d.mtrl_textinput_box_label_cutout_padding);
        this.I = e0VarI.e(l.TextInputLayout_boxCollapsedPaddingTop, 0);
        this.K = e0VarI.f(l.TextInputLayout_boxStrokeWidth, context2.getResources().getDimensionPixelSize(c.b.a.a.d.mtrl_textinput_box_stroke_width_default));
        this.L = e0VarI.f(l.TextInputLayout_boxStrokeWidthFocused, context2.getResources().getDimensionPixelSize(c.b.a.a.d.mtrl_textinput_box_stroke_width_focused));
        this.J = this.K;
        float fD = e0VarI.d(l.TextInputLayout_boxCornerRadiusTopStart, -1.0f);
        float fD2 = e0VarI.d(l.TextInputLayout_boxCornerRadiusTopEnd, -1.0f);
        float fD3 = e0VarI.d(l.TextInputLayout_boxCornerRadiusBottomEnd, -1.0f);
        float fD4 = e0VarI.d(l.TextInputLayout_boxCornerRadiusBottomStart, -1.0f);
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
        ColorStateList colorStateListB = c.b.a.a.f0.c.b(context2, e0VarI, l.TextInputLayout_boxBackgroundColor);
        if (colorStateListB != null) {
            int defaultColor = colorStateListB.getDefaultColor();
            this.w0 = defaultColor;
            this.N = defaultColor;
            if (colorStateListB.isStateful()) {
                this.x0 = colorStateListB.getColorForState(new int[]{-16842910}, -1);
                i3 = 2;
                this.y0 = colorStateListB.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
                this.z0 = colorStateListB.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            } else {
                i3 = 2;
                this.y0 = this.w0;
                ColorStateList colorStateListC = b.b.l.a.a.c(context2, c.b.a.a.c.mtrl_filled_background_color);
                this.x0 = colorStateListC.getColorForState(new int[]{-16842910}, -1);
                this.z0 = colorStateListC.getColorForState(new int[]{R.attr.state_hovered}, -1);
            }
        } else {
            i3 = 2;
            this.N = 0;
            this.w0 = 0;
            this.x0 = 0;
            this.y0 = 0;
            this.z0 = 0;
        }
        int i10 = l.TextInputLayout_android_textColorHint;
        if (e0VarI.s(i10)) {
            ColorStateList colorStateListC2 = e0VarI.c(i10);
            this.r0 = colorStateListC2;
            this.q0 = colorStateListC2;
        }
        int i11 = l.TextInputLayout_boxStrokeColor;
        ColorStateList colorStateListB2 = c.b.a.a.f0.c.b(context2, e0VarI, i11);
        this.u0 = e0VarI.b(i11, 0);
        this.s0 = b.h.e.a.b(context2, c.b.a.a.c.mtrl_textinput_default_box_stroke_color);
        this.A0 = b.h.e.a.b(context2, c.b.a.a.c.mtrl_textinput_disabled_color);
        this.t0 = b.h.e.a.b(context2, c.b.a.a.c.mtrl_textinput_hovered_box_stroke_color);
        if (colorStateListB2 != null) {
            setBoxStrokeColorStateList(colorStateListB2);
        }
        int i12 = l.TextInputLayout_boxStrokeErrorColor;
        if (e0VarI.s(i12)) {
            setBoxStrokeErrorColor(c.b.a.a.f0.c.b(context2, e0VarI, i12));
        }
        if (e0VarI.n(i9, -1) != -1) {
            setHintTextAppearance(e0VarI.n(i9, 0));
        }
        int iN = e0VarI.n(i7, 0);
        CharSequence charSequenceP = e0VarI.p(l.TextInputLayout_errorContentDescription);
        boolean zA = e0VarI.a(l.TextInputLayout_errorEnabled, false);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int i13 = c.b.a.a.h.design_text_input_end_icon;
        CheckableImageButton checkableImageButton = (CheckableImageButton) layoutInflaterFrom.inflate(i13, (ViewGroup) linearLayout2, false);
        this.o0 = checkableImageButton;
        checkableImageButton.setVisibility(8);
        int i14 = l.TextInputLayout_errorIconDrawable;
        if (e0VarI.s(i14)) {
            setErrorIconDrawable(e0VarI.g(i14));
        }
        int i15 = l.TextInputLayout_errorIconTint;
        if (e0VarI.s(i15)) {
            setErrorIconTintList(c.b.a.a.f0.c.b(context2, e0VarI, i15));
        }
        int i16 = l.TextInputLayout_errorIconTintMode;
        if (e0VarI.s(i16)) {
            setErrorIconTintMode(p.i(e0VarI.k(i16, -1), null));
        }
        checkableImageButton.setContentDescription(getResources().getText(j.error_icon_content_description));
        t.u0(checkableImageButton, i3);
        checkableImageButton.setClickable(false);
        checkableImageButton.setPressable(false);
        checkableImageButton.setFocusable(false);
        int iN2 = e0VarI.n(i8, 0);
        boolean zA2 = e0VarI.a(l.TextInputLayout_helperTextEnabled, false);
        CharSequence charSequenceP2 = e0VarI.p(l.TextInputLayout_helperText);
        int iN3 = e0VarI.n(l.TextInputLayout_placeholderTextAppearance, 0);
        CharSequence charSequenceP3 = e0VarI.p(l.TextInputLayout_placeholderText);
        int iN4 = e0VarI.n(l.TextInputLayout_prefixTextAppearance, 0);
        CharSequence charSequenceP4 = e0VarI.p(l.TextInputLayout_prefixText);
        int iN5 = e0VarI.n(l.TextInputLayout_suffixTextAppearance, 0);
        CharSequence charSequenceP5 = e0VarI.p(l.TextInputLayout_suffixText);
        boolean zA3 = e0VarI.a(l.TextInputLayout_counterEnabled, false);
        setCounterMaxLength(e0VarI.k(l.TextInputLayout_counterMaxLength, -1));
        this.o = e0VarI.n(i5, 0);
        this.n = e0VarI.n(i6, 0);
        CheckableImageButton checkableImageButton2 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(c.b.a.a.h.design_text_input_start_icon, (ViewGroup) this.f4885d, false);
        this.S = checkableImageButton2;
        checkableImageButton2.setVisibility(8);
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        int i17 = l.TextInputLayout_startIconDrawable;
        if (e0VarI.s(i17)) {
            setStartIconDrawable(e0VarI.g(i17));
            int i18 = l.TextInputLayout_startIconContentDescription;
            if (e0VarI.s(i18)) {
                setStartIconContentDescription(e0VarI.p(i18));
            }
            setStartIconCheckable(e0VarI.a(l.TextInputLayout_startIconCheckable, true));
        }
        int i19 = l.TextInputLayout_startIconTint;
        if (e0VarI.s(i19)) {
            setStartIconTintList(c.b.a.a.f0.c.b(context2, e0VarI, i19));
        }
        int i20 = l.TextInputLayout_startIconTintMode;
        if (e0VarI.s(i20)) {
            setStartIconTintMode(p.i(e0VarI.k(i20, -1), null));
        }
        setBoxBackgroundMode(e0VarI.k(l.TextInputLayout_boxBackgroundMode, 0));
        this.d0 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(i13, (ViewGroup) this.f4887f, false);
        this.f4887f.addView(this.d0);
        this.d0.setVisibility(8);
        this.c0.append(-1, new c.b.a.a.l0.b(this));
        this.c0.append(0, new c.b.a.a.l0.g(this));
        this.c0.append(1, new c.b.a.a.l0.h(this));
        this.c0.append(2, new c.b.a.a.l0.a(this));
        this.c0.append(3, new c.b.a.a.l0.d(this));
        int i21 = l.TextInputLayout_endIconMode;
        if (e0VarI.s(i21)) {
            setEndIconMode(e0VarI.k(i21, 0));
            int i22 = l.TextInputLayout_endIconDrawable;
            if (e0VarI.s(i22)) {
                setEndIconDrawable(e0VarI.g(i22));
            }
            int i23 = l.TextInputLayout_endIconContentDescription;
            if (e0VarI.s(i23)) {
                setEndIconContentDescription(e0VarI.p(i23));
            }
            setEndIconCheckable(e0VarI.a(l.TextInputLayout_endIconCheckable, true));
        } else {
            int i24 = l.TextInputLayout_passwordToggleEnabled;
            if (e0VarI.s(i24)) {
                setEndIconMode(e0VarI.a(i24, false) ? 1 : 0);
                setEndIconDrawable(e0VarI.g(l.TextInputLayout_passwordToggleDrawable));
                setEndIconContentDescription(e0VarI.p(l.TextInputLayout_passwordToggleContentDescription));
                int i25 = l.TextInputLayout_passwordToggleTint;
                if (e0VarI.s(i25)) {
                    setEndIconTintList(c.b.a.a.f0.c.b(context2, e0VarI, i25));
                }
                int i26 = l.TextInputLayout_passwordToggleTintMode;
                if (e0VarI.s(i26)) {
                    setEndIconTintMode(p.i(e0VarI.k(i26, -1), null));
                }
            }
        }
        if (!e0VarI.s(l.TextInputLayout_passwordToggleEnabled)) {
            int i27 = l.TextInputLayout_endIconTint;
            if (e0VarI.s(i27)) {
                setEndIconTintList(c.b.a.a.f0.c.b(context2, e0VarI, i27));
            }
            int i28 = l.TextInputLayout_endIconTintMode;
            if (e0VarI.s(i28)) {
                setEndIconTintMode(p.i(e0VarI.k(i28, -1), null));
            }
        }
        AppCompatTextView appCompatTextView = new AppCompatTextView(context2);
        this.x = appCompatTextView;
        appCompatTextView.setId(c.b.a.a.f.textinput_prefix_text);
        appCompatTextView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        t.m0(appCompatTextView, 1);
        this.f4885d.addView(this.S);
        this.f4885d.addView(appCompatTextView);
        AppCompatTextView appCompatTextView2 = new AppCompatTextView(context2);
        this.z = appCompatTextView2;
        appCompatTextView2.setId(c.b.a.a.f.textinput_suffix_text);
        appCompatTextView2.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 80));
        t.m0(appCompatTextView2, 1);
        this.f4886e.addView(appCompatTextView2);
        this.f4886e.addView(this.o0);
        this.f4886e.addView(this.f4887f);
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
        int i29 = l.TextInputLayout_errorTextColor;
        if (e0VarI.s(i29)) {
            setErrorTextColor(e0VarI.c(i29));
        }
        int i30 = l.TextInputLayout_helperTextTextColor;
        if (e0VarI.s(i30)) {
            setHelperTextColor(e0VarI.c(i30));
        }
        int i31 = l.TextInputLayout_hintTextColor;
        if (e0VarI.s(i31)) {
            setHintTextColor(e0VarI.c(i31));
        }
        int i32 = l.TextInputLayout_counterTextColor;
        if (e0VarI.s(i32)) {
            setCounterTextColor(e0VarI.c(i32));
        }
        int i33 = l.TextInputLayout_counterOverflowTextColor;
        if (e0VarI.s(i33)) {
            setCounterOverflowTextColor(e0VarI.c(i33));
        }
        int i34 = l.TextInputLayout_placeholderTextColor;
        if (e0VarI.s(i34)) {
            setPlaceholderTextColor(e0VarI.c(i34));
        }
        int i35 = l.TextInputLayout_prefixTextColor;
        if (e0VarI.s(i35)) {
            setPrefixTextColor(e0VarI.c(i35));
        }
        int i36 = l.TextInputLayout_suffixTextColor;
        if (e0VarI.s(i36)) {
            setSuffixTextColor(e0VarI.c(i36));
        }
        setCounterEnabled(zA3);
        setEnabled(e0VarI.a(l.TextInputLayout_android_enabled, true));
        e0VarI.w();
        t.u0(this, 2);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence charSequence) {
        this.d0.setContentDescription(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable drawable) {
        this.d0.setImageDrawable(drawable);
    }
}
