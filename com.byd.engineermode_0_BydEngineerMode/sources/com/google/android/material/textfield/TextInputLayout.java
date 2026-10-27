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
import androidx.appcompat.widget.e0;
import androidx.appcompat.widget.q;
import androidx.core.widget.i;
import androidx.customview.view.AbsSavedState;
import b.g.k.t;
import c.b.a.a.j;
import c.b.a.a.k;
import c.b.a.a.l;
import c.b.a.a.w.m;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.p;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public class TextInputLayout extends LinearLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f2914b = k.p;
    private boolean A;
    private int A0;
    private CharSequence B;
    private boolean B0;
    private boolean C;
    final com.google.android.material.internal.a C0;
    private c.b.a.a.w.h D;
    private boolean D0;
    private c.b.a.a.w.h E;
    private ValueAnimator E0;
    private m F;
    private boolean F0;
    private final int G;
    private boolean G0;
    private int H;
    private final int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private int N;
    private final Rect O;
    private final Rect P;
    private final RectF Q;
    private Typeface R;
    private final CheckableImageButton S;
    private ColorStateList T;
    private boolean U;
    private PorterDuff.Mode V;
    private boolean W;
    private Drawable X;
    private int Y;
    private View.OnLongClickListener Z;
    private final LinkedHashSet<f> a0;
    private int b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final FrameLayout f2915c;
    private final SparseArray<com.google.android.material.textfield.e> c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final LinearLayout f2916d;
    private final CheckableImageButton d0;
    private final LinearLayout e;
    private final LinkedHashSet<g> e0;
    private final FrameLayout f;
    private ColorStateList f0;
    EditText g;
    private boolean g0;
    private CharSequence h;
    private PorterDuff.Mode h0;
    private final com.google.android.material.textfield.f i;
    private boolean i0;
    boolean j;
    private Drawable j0;
    private int k;
    private int k0;
    private boolean l;
    private Drawable l0;
    private TextView m;
    private View.OnLongClickListener m0;
    private int n;
    private View.OnLongClickListener n0;
    private int o;
    private final CheckableImageButton o0;
    private CharSequence p;
    private ColorStateList p0;
    private boolean q;
    private ColorStateList q0;
    private TextView r;
    private ColorStateList r0;
    private ColorStateList s;
    private int s0;
    private int t;
    private int t0;
    private ColorStateList u;
    private int u0;
    private ColorStateList v;
    private ColorStateList v0;
    private CharSequence w;
    private int w0;
    private final TextView x;
    private int x0;
    private CharSequence y;
    private int y0;
    private final TextView z;
    private int z0;

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        CharSequence f2917d;
        boolean e;

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
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.f2917d) + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(this.f2917d, parcel, i);
            parcel.writeInt(this.e ? 1 : 0);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2917d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.e = parcel.readInt() == 1;
        }
    }

    class a implements TextWatcher {
        a() {
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
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.d0.performClick();
            TextInputLayout.this.d0.jumpDrawablesToCurrentState();
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.g.requestLayout();
        }
    }

    class d implements ValueAnimator.AnimatorUpdateListener {
        d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            TextInputLayout.this.C0.d0(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public static class e extends b.g.k.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final TextInputLayout f2922d;

        public e(TextInputLayout textInputLayout) {
            this.f2922d = textInputLayout;
        }

        @Override // b.g.k.a
        public void g(View view, b.g.k.c0.c cVar) {
            super.g(view, cVar);
            EditText editText = this.f2922d.getEditText();
            CharSequence text = editText != null ? editText.getText() : null;
            CharSequence hint = this.f2922d.getHint();
            CharSequence helperText = this.f2922d.getHelperText();
            CharSequence error = this.f2922d.getError();
            int counterMaxLength = this.f2922d.getCounterMaxLength();
            CharSequence counterOverflowDescription = this.f2922d.getCounterOverflowDescription();
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

    private void A() {
        Iterator<f> it = this.a0.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    private void B(int i) {
        Iterator<g> it = this.e0.iterator();
        while (it.hasNext()) {
            it.next().a(this, i);
        }
    }

    private void C(Canvas canvas) {
        c.b.a.a.w.h hVar = this.E;
        if (hVar != null) {
            Rect bounds = hVar.getBounds();
            bounds.top = bounds.bottom - this.J;
            this.E.draw(canvas);
        }
    }

    private void D(Canvas canvas) {
        if (this.A) {
            this.C0.j(canvas);
        }
    }

    private void E(boolean z) {
        ValueAnimator valueAnimator = this.E0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.E0.cancel();
        }
        if (z && this.D0) {
            h(0.0f);
        } else {
            this.C0.d0(0.0f);
        }
        if (z() && ((com.google.android.material.textfield.c) this.D).n0()) {
            x();
        }
        this.B0 = true;
        I();
        u0();
        x0();
    }

    private int F(int i, boolean z) {
        int compoundPaddingLeft = i + this.g.getCompoundPaddingLeft();
        return (this.w == null || z) ? compoundPaddingLeft : (compoundPaddingLeft - this.x.getMeasuredWidth()) + this.x.getPaddingLeft();
    }

    private int G(int i, boolean z) {
        int compoundPaddingRight = i - this.g.getCompoundPaddingRight();
        return (this.w == null || !z) ? compoundPaddingRight : compoundPaddingRight + (this.x.getMeasuredWidth() - this.x.getPaddingRight());
    }

    private boolean H() {
        return this.b0 != 0;
    }

    private void I() {
        TextView textView = this.r;
        if (textView == null || !this.q) {
            return;
        }
        textView.setText((CharSequence) null);
        this.r.setVisibility(4);
    }

    private boolean K() {
        return this.o0.getVisibility() == 0;
    }

    private boolean O() {
        return this.H == 1 && (Build.VERSION.SDK_INT < 16 || this.g.getMinLines() <= 1);
    }

    private void Q() {
        o();
        U();
        y0();
        if (this.H != 0) {
            n0();
        }
    }

    private void R() {
        if (z()) {
            RectF rectF = this.Q;
            this.C0.m(rectF, this.g.getWidth(), this.g.getGravity());
            k(rectF);
            rectF.offset(-getPaddingLeft(), -getPaddingTop());
            ((com.google.android.material.textfield.c) this.D).t0(rectF);
        }
    }

    private static void S(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                S((ViewGroup) childAt, z);
            }
        }
    }

    private void T() {
        TextView textView = this.r;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    private void U() {
        if (b0()) {
            t.m0(this.g, this.D);
        }
    }

    private static void V(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        boolean zK = t.K(checkableImageButton);
        boolean z = onLongClickListener != null;
        boolean z2 = zK || z;
        checkableImageButton.setFocusable(z2);
        checkableImageButton.setClickable(zK);
        checkableImageButton.setPressable(zK);
        checkableImageButton.setLongClickable(z);
        t.t0(checkableImageButton, z2 ? 1 : 2);
    }

    private static void W(CheckableImageButton checkableImageButton, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        checkableImageButton.setOnClickListener(onClickListener);
        V(checkableImageButton, onLongClickListener);
    }

    private static void X(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        V(checkableImageButton, onLongClickListener);
    }

    private boolean Z() {
        return (this.o0.getVisibility() == 0 || ((H() && J()) || this.y != null)) && this.e.getMeasuredWidth() > 0;
    }

    private boolean a0() {
        return !(getStartIconDrawable() == null && this.w == null) && this.f2916d.getMeasuredWidth() > 0;
    }

    private boolean b0() {
        EditText editText = this.g;
        return (editText == null || this.D == null || editText.getBackground() != null || this.H == 0) ? false : true;
    }

    private void c0() {
        TextView textView = this.r;
        if (textView == null || !this.q) {
            return;
        }
        textView.setText(this.p);
        this.r.setVisibility(0);
        this.r.bringToFront();
    }

    private void d0(boolean z) {
        if (!z || getEndIconDrawable() == null) {
            l();
            return;
        }
        Drawable drawableMutate = androidx.core.graphics.drawable.a.r(getEndIconDrawable()).mutate();
        androidx.core.graphics.drawable.a.n(drawableMutate, this.i.o());
        this.d0.setImageDrawable(drawableMutate);
    }

    private void e0(Rect rect) {
        c.b.a.a.w.h hVar = this.E;
        if (hVar != null) {
            int i = rect.bottom;
            hVar.setBounds(rect.left, i - this.L, rect.right, i);
        }
    }

    private void f0() {
        if (this.m != null) {
            EditText editText = this.g;
            g0(editText == null ? 0 : editText.getText().length());
        }
    }

    private void g() {
        TextView textView = this.r;
        if (textView != null) {
            this.f2915c.addView(textView);
            this.r.setVisibility(0);
        }
    }

    private com.google.android.material.textfield.e getEndIconDelegate() {
        com.google.android.material.textfield.e eVar = this.c0.get(this.b0);
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

    private static void h0(Context context, TextView textView, int i, int i2, boolean z) {
        textView.setContentDescription(context.getString(z ? j.f2120b : j.f2119a, Integer.valueOf(i), Integer.valueOf(i2)));
    }

    private void i() {
        c.b.a.a.w.h hVar = this.D;
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
            this.g.getBackground().invalidateSelf();
        }
        j();
        invalidate();
    }

    private void i0() {
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

    private void j() {
        if (this.E == null) {
            return;
        }
        if (w()) {
            this.E.X(ColorStateList.valueOf(this.M));
        }
        invalidate();
    }

    private boolean j0() {
        boolean z;
        if (this.g == null) {
            return false;
        }
        boolean z2 = true;
        if (a0()) {
            int measuredWidth = this.f2916d.getMeasuredWidth() - this.g.getPaddingLeft();
            if (this.X == null || this.Y != measuredWidth) {
                ColorDrawable colorDrawable = new ColorDrawable();
                this.X = colorDrawable;
                this.Y = measuredWidth;
                colorDrawable.setBounds(0, 0, measuredWidth, 1);
            }
            Drawable[] drawableArrA = i.a(this.g);
            Drawable drawable = drawableArrA[0];
            Drawable drawable2 = this.X;
            if (drawable != drawable2) {
                i.l(this.g, drawable2, drawableArrA[1], drawableArrA[2], drawableArrA[3]);
                z = true;
            }
            z = false;
        } else {
            if (this.X != null) {
                Drawable[] drawableArrA2 = i.a(this.g);
                i.l(this.g, null, drawableArrA2[1], drawableArrA2[2], drawableArrA2[3]);
                this.X = null;
                z = true;
            }
            z = false;
        }
        if (Z()) {
            int measuredWidth2 = this.z.getMeasuredWidth() - this.g.getPaddingRight();
            CheckableImageButton endIconToUpdateDummyDrawable = getEndIconToUpdateDummyDrawable();
            if (endIconToUpdateDummyDrawable != null) {
                measuredWidth2 = measuredWidth2 + endIconToUpdateDummyDrawable.getMeasuredWidth() + b.g.k.g.b((ViewGroup.MarginLayoutParams) endIconToUpdateDummyDrawable.getLayoutParams());
            }
            Drawable[] drawableArrA3 = i.a(this.g);
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
                    i.l(this.g, drawableArrA3[0], drawableArrA3[1], drawable5, drawableArrA3[3]);
                } else {
                    z2 = z;
                }
            } else {
                this.k0 = measuredWidth2;
                drawable3.setBounds(0, 0, measuredWidth2, 1);
                i.l(this.g, drawableArrA3[0], drawableArrA3[1], this.j0, drawableArrA3[3]);
            }
        } else {
            if (this.j0 == null) {
                return z;
            }
            Drawable[] drawableArrA4 = i.a(this.g);
            if (drawableArrA4[2] == this.j0) {
                i.l(this.g, drawableArrA4[0], drawableArrA4[1], this.l0, drawableArrA4[3]);
            } else {
                z2 = z;
            }
            this.j0 = null;
        }
        return z2;
    }

    private void k(RectF rectF) {
        float f2 = rectF.left;
        int i = this.G;
        rectF.left = f2 - i;
        rectF.top -= i;
        rectF.right += i;
        rectF.bottom += i;
    }

    private void l() {
        m(this.d0, this.g0, this.f0, this.i0, this.h0);
    }

    private boolean l0() {
        int iMax;
        if (this.g == null || this.g.getMeasuredHeight() >= (iMax = Math.max(this.e.getMeasuredHeight(), this.f2916d.getMeasuredHeight()))) {
            return false;
        }
        this.g.setMinimumHeight(iMax);
        return true;
    }

    private void m(CheckableImageButton checkableImageButton, boolean z, ColorStateList colorStateList, boolean z2, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null && (z || z2)) {
            drawable = androidx.core.graphics.drawable.a.r(drawable).mutate();
            if (z) {
                androidx.core.graphics.drawable.a.o(drawable, colorStateList);
            }
            if (z2) {
                androidx.core.graphics.drawable.a.p(drawable, mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    private void m0(CheckableImageButton checkableImageButton, ColorStateList colorStateList) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (checkableImageButton.getDrawable() == null || colorStateList == null || !colorStateList.isStateful()) {
            return;
        }
        int colorForState = colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
        Drawable drawableMutate = androidx.core.graphics.drawable.a.r(drawable).mutate();
        androidx.core.graphics.drawable.a.o(drawableMutate, ColorStateList.valueOf(colorForState));
        checkableImageButton.setImageDrawable(drawableMutate);
    }

    private void n() {
        m(this.S, this.U, this.T, this.W, this.V);
    }

    private void n0() {
        if (this.H != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f2915c.getLayoutParams();
            int iU = u();
            if (iU != layoutParams.topMargin) {
                layoutParams.topMargin = iU;
                this.f2915c.requestLayout();
            }
        }
    }

    private void o() {
        int i = this.H;
        if (i == 0) {
            this.D = null;
            this.E = null;
            return;
        }
        if (i == 1) {
            this.D = new c.b.a.a.w.h(this.F);
            this.E = new c.b.a.a.w.h();
        } else {
            if (i != 2) {
                throw new IllegalArgumentException(this.H + " is illegal; only @BoxBackgroundMode constants are supported.");
            }
            if (!this.A || (this.D instanceof com.google.android.material.textfield.c)) {
                this.D = new c.b.a.a.w.h(this.F);
            } else {
                this.D = new com.google.android.material.textfield.c(this.F);
            }
            this.E = null;
        }
    }

    private int p() {
        return this.H == 1 ? c.b.a.a.o.a.e(c.b.a.a.o.a.d(this, c.b.a.a.b.p, 0), this.N) : this.N;
    }

    private void p0(boolean z, boolean z2) {
        ColorStateList colorStateList;
        TextView textView;
        boolean zIsEnabled = isEnabled();
        EditText editText = this.g;
        boolean z3 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.g;
        boolean z4 = editText2 != null && editText2.hasFocus();
        boolean zK = this.i.k();
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
            this.C0.Q(this.i.p());
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

    private Rect q(Rect rect) {
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

    private void q0() {
        EditText editText;
        if (this.r == null || (editText = this.g) == null) {
            return;
        }
        this.r.setGravity(editText.getGravity());
        this.r.setPadding(this.g.getCompoundPaddingLeft(), this.g.getCompoundPaddingTop(), this.g.getCompoundPaddingRight(), this.g.getCompoundPaddingBottom());
    }

    private int r(Rect rect, Rect rect2, float f2) {
        return O() ? (int) (rect2.top + f2) : rect.bottom - this.g.getCompoundPaddingBottom();
    }

    private void r0() {
        EditText editText = this.g;
        s0(editText == null ? 0 : editText.getText().length());
    }

    private int s(Rect rect, float f2) {
        return O() ? (int) (rect.centerY() - (f2 / 2.0f)) : rect.top + this.g.getCompoundPaddingTop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s0(int i) {
        if (i != 0 || this.B0) {
            I();
        } else {
            c0();
        }
    }

    private void setEditText(EditText editText) {
        if (this.g != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (this.b0 != 3 && !(editText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.g = editText;
        Q();
        setTextInputAccessibilityDelegate(new e(this));
        this.C0.k0(this.g.getTypeface());
        this.C0.a0(this.g.getTextSize());
        int gravity = this.g.getGravity();
        this.C0.R((gravity & (-113)) | 48);
        this.C0.Z(gravity);
        this.g.addTextChangedListener(new a());
        if (this.q0 == null) {
            this.q0 = this.g.getHintTextColors();
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
        this.f2916d.bringToFront();
        this.e.bringToFront();
        this.f.bringToFront();
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
            appCompatTextView.setId(c.b.a.a.f.z);
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

    private Rect t(Rect rect) {
        if (this.g == null) {
            throw new IllegalStateException();
        }
        Rect rect2 = this.P;
        float fX = this.C0.x();
        rect2.left = rect.left + this.g.getCompoundPaddingLeft();
        rect2.top = s(rect, fX);
        rect2.right = rect.right - this.g.getCompoundPaddingRight();
        rect2.bottom = r(rect, rect2, fX);
        return rect2;
    }

    private void t0() {
        if (this.g == null) {
            return;
        }
        t.w0(this.x, P() ? 0 : t.E(this.g), this.g.getCompoundPaddingTop(), 0, this.g.getCompoundPaddingBottom());
    }

    private int u() {
        float fP;
        if (!this.A) {
            return 0;
        }
        int i = this.H;
        if (i == 0 || i == 1) {
            fP = this.C0.p();
        } else {
            if (i != 2) {
                return 0;
            }
            fP = this.C0.p() / 2.0f;
        }
        return (int) fP;
    }

    private void u0() {
        this.x.setVisibility((this.w == null || M()) ? 8 : 0);
        j0();
    }

    private boolean v() {
        return this.H == 2 && w();
    }

    private void v0(boolean z, boolean z2) {
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

    private boolean w() {
        return this.J > -1 && this.M != 0;
    }

    private void w0() {
        if (this.g == null) {
            return;
        }
        t.w0(this.z, 0, this.g.getPaddingTop(), (J() || K()) ? 0 : t.D(this.g), this.g.getPaddingBottom());
    }

    private void x() {
        if (z()) {
            ((com.google.android.material.textfield.c) this.D).q0();
        }
    }

    private void x0() {
        int visibility = this.z.getVisibility();
        boolean z = (this.y == null || M()) ? false : true;
        this.z.setVisibility(z ? 0 : 8);
        if (visibility != this.z.getVisibility()) {
            getEndIconDelegate().c(z);
        }
        j0();
    }

    private void y(boolean z) {
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

    private boolean z() {
        return this.A && !TextUtils.isEmpty(this.B) && (this.D instanceof com.google.android.material.textfield.c);
    }

    public boolean J() {
        return this.f.getVisibility() == 0 && this.d0.getVisibility() == 0;
    }

    public boolean L() {
        return this.i.x();
    }

    final boolean M() {
        return this.B0;
    }

    public boolean N() {
        return this.C;
    }

    public boolean P() {
        return this.S.getVisibility() == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void Y(android.widget.TextView r3, int r4) {
        /*
            r2 = this;
            r0 = 1
            androidx.core.widget.i.q(r3, r4)     // Catch: java.lang.Exception -> L1a
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
            int r4 = c.b.a.a.k.f2124b
            androidx.core.widget.i.q(r3, r4)
            android.content.Context r4 = r2.getContext()
            int r0 = c.b.a.a.c.f2097b
            int r4 = androidx.core.content.a.b(r4, r0)
            r3.setTextColor(r4)
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.Y(android.widget.TextView, int):void");
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
        this.f2915c.addView(view, layoutParams2);
        this.f2915c.setLayoutParams(layoutParams);
        n0();
        setEditText((EditText) view);
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
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
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
    protected void drawableStateChanged() {
        if (this.F0) {
            return;
        }
        this.F0 = true;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        com.google.android.material.internal.a aVar = this.C0;
        boolean zH0 = aVar != null ? aVar.h0(drawableState) | false : false;
        if (this.g != null) {
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
        if (this.g != null) {
            fVar.a(this);
        }
    }

    public void f(g gVar) {
        this.e0.add(gVar);
    }

    void g0(int i) {
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
            this.m.setText(b.g.i.a.c().j(getContext().getString(j.f2121c, Integer.valueOf(i), Integer.valueOf(this.k))));
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

    c.b.a.a.w.h getBoxBackground() {
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

    CharSequence getCounterOverflowDescription() {
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
        return this.g;
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

    CheckableImageButton getEndIconView() {
        return this.d0;
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
        return this.o0.getDrawable();
    }

    final int getErrorTextCurrentColor() {
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

    final float getHintCollapsedTextHeight() {
        return this.C0.p();
    }

    final int getHintCurrentCollapsedTextColor() {
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

    void h(float f2) {
        if (this.C0.z() == f2) {
            return;
        }
        if (this.E0 == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.E0 = valueAnimator;
            valueAnimator.setInterpolator(c.b.a.a.m.a.f2132b);
            this.E0.setDuration(167L);
            this.E0.addUpdateListener(new d());
        }
        this.E0.setFloatValues(this.C0.z(), f2);
        this.E0.start();
    }

    void k0() {
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
            background.setColorFilter(androidx.appcompat.widget.f.e(this.i.o(), PorterDuff.Mode.SRC_IN));
        } else if (this.l && (textView = this.m) != null) {
            background.setColorFilter(androidx.appcompat.widget.f.e(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            androidx.core.graphics.drawable.a.c(background);
            this.g.refreshDrawableState();
        }
    }

    void o0(boolean z) {
        p0(z, false);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        EditText editText = this.g;
        if (editText != null) {
            Rect rect = this.O;
            com.google.android.material.internal.c.a(this, editText, rect);
            e0(rect);
            if (this.A) {
                this.C0.a0(this.g.getTextSize());
                int gravity = this.g.getGravity();
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
    protected void onMeasure(int i, int i2) {
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
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f());
        setError(savedState.f2917d);
        if (savedState.e) {
            this.d0.post(new b());
        }
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.i.k()) {
            savedState.f2917d = getError();
        }
        savedState.e = H() && this.d0.isChecked();
        return savedState;
    }

    public void setBoxBackgroundColor(int i) {
        if (this.N != i) {
            this.N = i;
            this.w0 = i;
            this.y0 = i;
            this.z0 = i;
            i();
        }
    }

    public void setBoxBackgroundColorResource(int i) {
        setBoxBackgroundColor(androidx.core.content.a.b(getContext(), i));
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
        if (this.u0 != i) {
            this.u0 = i;
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
                appCompatTextView.setId(c.b.a.a.f.w);
                Typeface typeface = this.R;
                if (typeface != null) {
                    this.m.setTypeface(typeface);
                }
                this.m.setMaxLines(1);
                this.i.d(this.m, 2);
                b.g.k.g.d((ViewGroup.MarginLayoutParams) this.m.getLayoutParams(), getResources().getDimensionPixelOffset(c.b.a.a.d.S));
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
        this.q0 = colorStateList;
        this.r0 = colorStateList;
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
        this.d0.setActivated(z);
    }

    public void setEndIconCheckable(boolean z) {
        this.d0.setCheckable(z);
    }

    public void setEndIconContentDescription(int i) {
        setEndIconContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    public void setEndIconDrawable(int i) {
        setEndIconDrawable(i != 0 ? b.a.k.a.a.d(getContext(), i) : null);
    }

    public void setEndIconMode(int i) {
        int i2 = this.b0;
        this.b0 = i;
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
        setErrorIconDrawable(i != 0 ? b.a.k.a.a.d(getContext(), i) : null);
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
            drawable = androidx.core.graphics.drawable.a.r(drawable).mutate();
            androidx.core.graphics.drawable.a.o(drawable, colorStateList);
        }
        if (this.o0.getDrawable() != drawable) {
            this.o0.setImageDrawable(drawable);
        }
    }

    public void setErrorIconTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.o0.getDrawable();
        if (drawable != null) {
            drawable = androidx.core.graphics.drawable.a.r(drawable).mutate();
            androidx.core.graphics.drawable.a.p(drawable, mode);
        }
        if (this.o0.getDrawable() != drawable) {
            this.o0.setImageDrawable(drawable);
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
        this.D0 = z;
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
        this.C0.O(i);
        this.r0 = this.C0.n();
        if (this.g != null) {
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
        setPasswordVisibilityToggleDrawable(i != 0 ? b.a.k.a.a.d(getContext(), i) : null);
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
        setStartIconDrawable(i != 0 ? b.a.k.a.a.d(getContext(), i) : null);
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
            this.C0.k0(typeface);
            this.i.I(typeface);
            TextView textView = this.m;
            if (textView != null) {
                textView.setTypeface(typeface);
            }
        }
    }

    void y0() {
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
            this.M = this.A0;
        } else if (this.i.k()) {
            if (this.v0 != null) {
                v0(z2, z3);
            } else {
                this.M = this.i.o();
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
        if (getErrorIconDrawable() != null && this.i.w() && this.i.k()) {
            z = true;
        }
        setErrorIconVisible(z);
        m0(this.o0, this.p0);
        m0(this.S, this.T);
        m0(this.d0, this.f0);
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

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.b.a.a.b.M);
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
        int i3 = f2914b;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i, i3), attributeSet, i);
        this.i = new com.google.android.material.textfield.f(this);
        this.O = new Rect();
        this.P = new Rect();
        this.Q = new RectF();
        this.a0 = new LinkedHashSet<>();
        this.b0 = 0;
        this.c0 = new SparseArray<>();
        this.e0 = new LinkedHashSet<>();
        com.google.android.material.internal.a aVar = new com.google.android.material.internal.a(this);
        this.C0 = aVar;
        Context context2 = getContext();
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        FrameLayout frameLayout = new FrameLayout(context2);
        this.f2915c = frameLayout;
        frameLayout.setAddStatesFromChildren(true);
        addView(frameLayout);
        LinearLayout linearLayout = new LinearLayout(context2);
        this.f2916d = linearLayout;
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
        TimeInterpolator timeInterpolator = c.b.a.a.m.a.f2131a;
        aVar.j0(timeInterpolator);
        aVar.g0(timeInterpolator);
        aVar.R(8388659);
        int[] iArr = l.I6;
        int i4 = l.b7;
        int i5 = l.Z6;
        int i6 = l.o7;
        int i7 = l.s7;
        int i8 = l.w7;
        e0 e0VarI = com.google.android.material.internal.k.i(context2, attributeSet, iArr, i, i3, i4, i5, i6, i7, i8);
        this.A = e0VarI.a(l.v7, true);
        setHint(e0VarI.p(l.L6));
        this.D0 = e0VarI.a(l.u7, true);
        this.F = m.e(context2, attributeSet, i, i3).m();
        this.G = context2.getResources().getDimensionPixelOffset(c.b.a.a.d.P);
        this.I = e0VarI.e(l.O6, 0);
        this.K = e0VarI.f(l.V6, context2.getResources().getDimensionPixelSize(c.b.a.a.d.Q));
        this.L = e0VarI.f(l.W6, context2.getResources().getDimensionPixelSize(c.b.a.a.d.R));
        this.J = this.K;
        float fD = e0VarI.d(l.S6, -1.0f);
        float fD2 = e0VarI.d(l.R6, -1.0f);
        float fD3 = e0VarI.d(l.P6, -1.0f);
        float fD4 = e0VarI.d(l.Q6, -1.0f);
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
        ColorStateList colorStateListB = c.b.a.a.t.c.b(context2, e0VarI, l.M6);
        if (colorStateListB != null) {
            int defaultColor = colorStateListB.getDefaultColor();
            this.w0 = defaultColor;
            this.N = defaultColor;
            if (colorStateListB.isStateful()) {
                this.x0 = colorStateListB.getColorForState(new int[]{-16842910}, -1);
                i2 = 2;
                this.y0 = colorStateListB.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
                this.z0 = colorStateListB.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            } else {
                i2 = 2;
                this.y0 = this.w0;
                ColorStateList colorStateListC = b.a.k.a.a.c(context2, c.b.a.a.c.l);
                this.x0 = colorStateListC.getColorForState(new int[]{-16842910}, -1);
                this.z0 = colorStateListC.getColorForState(new int[]{R.attr.state_hovered}, -1);
            }
        } else {
            i2 = 2;
            this.N = 0;
            this.w0 = 0;
            this.x0 = 0;
            this.y0 = 0;
            this.z0 = 0;
        }
        int i9 = l.K6;
        if (e0VarI.s(i9)) {
            ColorStateList colorStateListC2 = e0VarI.c(i9);
            this.r0 = colorStateListC2;
            this.q0 = colorStateListC2;
        }
        int i10 = l.T6;
        ColorStateList colorStateListB2 = c.b.a.a.t.c.b(context2, e0VarI, i10);
        this.u0 = e0VarI.b(i10, 0);
        this.s0 = androidx.core.content.a.b(context2, c.b.a.a.c.m);
        this.A0 = androidx.core.content.a.b(context2, c.b.a.a.c.n);
        this.t0 = androidx.core.content.a.b(context2, c.b.a.a.c.o);
        if (colorStateListB2 != null) {
            setBoxStrokeColorStateList(colorStateListB2);
        }
        int i11 = l.U6;
        if (e0VarI.s(i11)) {
            setBoxStrokeErrorColor(c.b.a.a.t.c.b(context2, e0VarI, i11));
        }
        if (e0VarI.n(i8, -1) != -1) {
            setHintTextAppearance(e0VarI.n(i8, 0));
        }
        int iN = e0VarI.n(i6, 0);
        CharSequence charSequenceP = e0VarI.p(l.j7);
        boolean zA = e0VarI.a(l.k7, false);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int i12 = c.b.a.a.h.j;
        CheckableImageButton checkableImageButton = (CheckableImageButton) layoutInflaterFrom.inflate(i12, (ViewGroup) linearLayout2, false);
        this.o0 = checkableImageButton;
        checkableImageButton.setVisibility(8);
        int i13 = l.l7;
        if (e0VarI.s(i13)) {
            setErrorIconDrawable(e0VarI.g(i13));
        }
        int i14 = l.m7;
        if (e0VarI.s(i14)) {
            setErrorIconTintList(c.b.a.a.t.c.b(context2, e0VarI, i14));
        }
        int i15 = l.n7;
        if (e0VarI.s(i15)) {
            setErrorIconTintMode(p.i(e0VarI.k(i15, -1), null));
        }
        checkableImageButton.setContentDescription(getResources().getText(j.e));
        t.t0(checkableImageButton, i2);
        checkableImageButton.setClickable(false);
        checkableImageButton.setPressable(false);
        checkableImageButton.setFocusable(false);
        int iN2 = e0VarI.n(i7, 0);
        boolean zA2 = e0VarI.a(l.r7, false);
        CharSequence charSequenceP2 = e0VarI.p(l.q7);
        int iN3 = e0VarI.n(l.E7, 0);
        CharSequence charSequenceP3 = e0VarI.p(l.D7);
        int iN4 = e0VarI.n(l.H7, 0);
        CharSequence charSequenceP4 = e0VarI.p(l.G7);
        int iN5 = e0VarI.n(l.P7, 0);
        CharSequence charSequenceP5 = e0VarI.p(l.O7);
        boolean zA3 = e0VarI.a(l.X6, false);
        setCounterMaxLength(e0VarI.k(l.Y6, -1));
        this.o = e0VarI.n(i4, 0);
        this.n = e0VarI.n(i5, 0);
        CheckableImageButton checkableImageButton2 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(c.b.a.a.h.k, (ViewGroup) this.f2916d, false);
        this.S = checkableImageButton2;
        checkableImageButton2.setVisibility(8);
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        int i16 = l.L7;
        if (e0VarI.s(i16)) {
            setStartIconDrawable(e0VarI.g(i16));
            int i17 = l.K7;
            if (e0VarI.s(i17)) {
                setStartIconContentDescription(e0VarI.p(i17));
            }
            setStartIconCheckable(e0VarI.a(l.J7, true));
        }
        int i18 = l.M7;
        if (e0VarI.s(i18)) {
            setStartIconTintList(c.b.a.a.t.c.b(context2, e0VarI, i18));
        }
        int i19 = l.N7;
        if (e0VarI.s(i19)) {
            setStartIconTintMode(p.i(e0VarI.k(i19, -1), null));
        }
        setBoxBackgroundMode(e0VarI.k(l.N6, 0));
        this.d0 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(i12, (ViewGroup) this.f, false);
        this.f.addView(this.d0);
        this.d0.setVisibility(8);
        this.c0.append(-1, new com.google.android.material.textfield.b(this));
        this.c0.append(0, new com.google.android.material.textfield.g(this));
        this.c0.append(1, new h(this));
        this.c0.append(2, new com.google.android.material.textfield.a(this));
        this.c0.append(3, new com.google.android.material.textfield.d(this));
        int i20 = l.g7;
        if (e0VarI.s(i20)) {
            setEndIconMode(e0VarI.k(i20, 0));
            int i21 = l.f7;
            if (e0VarI.s(i21)) {
                setEndIconDrawable(e0VarI.g(i21));
            }
            int i22 = l.e7;
            if (e0VarI.s(i22)) {
                setEndIconContentDescription(e0VarI.p(i22));
            }
            setEndIconCheckable(e0VarI.a(l.d7, true));
        } else {
            int i23 = l.A7;
            if (e0VarI.s(i23)) {
                setEndIconMode(e0VarI.a(i23, false) ? 1 : 0);
                setEndIconDrawable(e0VarI.g(l.z7));
                setEndIconContentDescription(e0VarI.p(l.y7));
                int i24 = l.B7;
                if (e0VarI.s(i24)) {
                    setEndIconTintList(c.b.a.a.t.c.b(context2, e0VarI, i24));
                }
                int i25 = l.C7;
                if (e0VarI.s(i25)) {
                    setEndIconTintMode(p.i(e0VarI.k(i25, -1), null));
                }
            }
        }
        if (!e0VarI.s(l.A7)) {
            int i26 = l.h7;
            if (e0VarI.s(i26)) {
                setEndIconTintList(c.b.a.a.t.c.b(context2, e0VarI, i26));
            }
            int i27 = l.i7;
            if (e0VarI.s(i27)) {
                setEndIconTintMode(p.i(e0VarI.k(i27, -1), null));
            }
        }
        AppCompatTextView appCompatTextView = new AppCompatTextView(context2);
        this.x = appCompatTextView;
        appCompatTextView.setId(c.b.a.a.f.A);
        appCompatTextView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        t.l0(appCompatTextView, 1);
        this.f2916d.addView(this.S);
        this.f2916d.addView(appCompatTextView);
        AppCompatTextView appCompatTextView2 = new AppCompatTextView(context2);
        this.z = appCompatTextView2;
        appCompatTextView2.setId(c.b.a.a.f.B);
        appCompatTextView2.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 80));
        t.l0(appCompatTextView2, 1);
        this.e.addView(appCompatTextView2);
        this.e.addView(this.o0);
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
        int i28 = l.p7;
        if (e0VarI.s(i28)) {
            setErrorTextColor(e0VarI.c(i28));
        }
        int i29 = l.t7;
        if (e0VarI.s(i29)) {
            setHelperTextColor(e0VarI.c(i29));
        }
        int i30 = l.x7;
        if (e0VarI.s(i30)) {
            setHintTextColor(e0VarI.c(i30));
        }
        int i31 = l.c7;
        if (e0VarI.s(i31)) {
            setCounterTextColor(e0VarI.c(i31));
        }
        int i32 = l.a7;
        if (e0VarI.s(i32)) {
            setCounterOverflowTextColor(e0VarI.c(i32));
        }
        int i33 = l.F7;
        if (e0VarI.s(i33)) {
            setPlaceholderTextColor(e0VarI.c(i33));
        }
        int i34 = l.I7;
        if (e0VarI.s(i34)) {
            setPrefixTextColor(e0VarI.c(i34));
        }
        int i35 = l.Q7;
        if (e0VarI.s(i35)) {
            setSuffixTextColor(e0VarI.c(i35));
        }
        setCounterEnabled(zA3);
        setEnabled(e0VarI.a(l.J6, true));
        e0VarI.w();
        t.t0(this, 2);
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
