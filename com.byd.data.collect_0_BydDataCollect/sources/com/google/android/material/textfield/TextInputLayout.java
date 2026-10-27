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
import androidx.appcompat.widget.f0;
import androidx.appcompat.widget.q;
import androidx.core.widget.i;
import androidx.customview.view.AbsSavedState;
import b.g.k.t;
import com.google.android.material.internal.CheckableImageButton;
import d.b.a.a.j;
import d.b.a.a.k;
import d.b.a.a.l;
import d.b.a.a.x.m;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public class TextInputLayout extends LinearLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f4298b = k.Widget_Design_TextInputLayout;
    private boolean A;
    private int A0;
    private CharSequence B;
    private boolean B0;
    private boolean C;
    final com.google.android.material.internal.a C0;
    private d.b.a.a.x.h D;
    private boolean D0;
    private d.b.a.a.x.h E;
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
    private final FrameLayout f4299c;
    private final SparseArray<com.google.android.material.textfield.e> c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final LinearLayout f4300d;
    private final CheckableImageButton d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final LinearLayout f4301e;
    private final LinkedHashSet<g> e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final FrameLayout f4302f;
    private ColorStateList f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    EditText f4303g;
    private boolean g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private CharSequence f4304h;
    private PorterDuff.Mode h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final com.google.android.material.textfield.f f4305i;
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
        CharSequence f4306d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f4307e;

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
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.f4306d) + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            TextUtils.writeToParcel(this.f4306d, parcel, i2);
            parcel.writeInt(this.f4307e ? 1 : 0);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f4306d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f4307e = parcel.readInt() == 1;
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
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
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
            TextInputLayout.this.f4303g.requestLayout();
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
        private final TextInputLayout f4312d;

        public e(TextInputLayout textInputLayout) {
            this.f4312d = textInputLayout;
        }

        @Override // b.g.k.a
        public void g(View view, b.g.k.c0.c cVar) {
            super.g(view, cVar);
            EditText editText = this.f4312d.getEditText();
            CharSequence text = editText != null ? editText.getText() : null;
            CharSequence hint = this.f4312d.getHint();
            CharSequence helperText = this.f4312d.getHelperText();
            CharSequence error = this.f4312d.getError();
            int counterMaxLength = this.f4312d.getCounterMaxLength();
            CharSequence counterOverflowDescription = this.f4312d.getCounterOverflowDescription();
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
                cVar.z0(text);
            } else if (!TextUtils.isEmpty(string3)) {
                cVar.z0(string3);
            }
            if (!TextUtils.isEmpty(string3)) {
                if (Build.VERSION.SDK_INT >= 26) {
                    cVar.l0(string3);
                } else {
                    if (z) {
                        string3 = ((Object) text) + ", " + string3;
                    }
                    cVar.z0(string3);
                }
                cVar.w0(!z);
            }
            if (text == null || text.length() != counterMaxLength) {
                counterMaxLength = -1;
            }
            cVar.m0(counterMaxLength);
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
        void a(TextInputLayout textInputLayout, int i2);
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

    private void B(int i2) {
        Iterator<g> it = this.e0.iterator();
        while (it.hasNext()) {
            it.next().a(this, i2);
        }
    }

    private void C(Canvas canvas) {
        d.b.a.a.x.h hVar = this.E;
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

    private int F(int i2, boolean z) {
        int compoundPaddingLeft = i2 + this.f4303g.getCompoundPaddingLeft();
        return (this.w == null || z) ? compoundPaddingLeft : (compoundPaddingLeft - this.x.getMeasuredWidth()) + this.x.getPaddingLeft();
    }

    private int G(int i2, boolean z) {
        int compoundPaddingRight = i2 - this.f4303g.getCompoundPaddingRight();
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
        return this.H == 1 && (Build.VERSION.SDK_INT < 16 || this.f4303g.getMinLines() <= 1);
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
            this.C0.m(rectF, this.f4303g.getWidth(), this.f4303g.getGravity());
            k(rectF);
            rectF.offset(-getPaddingLeft(), -getPaddingTop());
            ((com.google.android.material.textfield.c) this.D).t0(rectF);
        }
    }

    private static void S(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
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
            t.m0(this.f4303g, this.D);
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
        return (this.o0.getVisibility() == 0 || ((H() && J()) || this.y != null)) && this.f4301e.getMeasuredWidth() > 0;
    }

    private boolean a0() {
        return !(getStartIconDrawable() == null && this.w == null) && this.f4300d.getMeasuredWidth() > 0;
    }

    private boolean b0() {
        EditText editText = this.f4303g;
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
        androidx.core.graphics.drawable.a.n(drawableMutate, this.f4305i.o());
        this.d0.setImageDrawable(drawableMutate);
    }

    private void e0(Rect rect) {
        d.b.a.a.x.h hVar = this.E;
        if (hVar != null) {
            int i2 = rect.bottom;
            hVar.setBounds(rect.left, i2 - this.L, rect.right, i2);
        }
    }

    private void f0() {
        if (this.m != null) {
            EditText editText = this.f4303g;
            g0(editText == null ? 0 : editText.getText().length());
        }
    }

    private void g() {
        TextView textView = this.r;
        if (textView != null) {
            this.f4299c.addView(textView);
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

    private static void h0(Context context, TextView textView, int i2, int i3, boolean z) {
        textView.setContentDescription(context.getString(z ? j.character_counter_overflowed_content_description : j.character_counter_content_description, Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    private void i() {
        d.b.a.a.x.h hVar = this.D;
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
            this.f4303g.getBackground().invalidateSelf();
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
        if (this.f4303g == null) {
            return false;
        }
        boolean z2 = true;
        if (a0()) {
            int measuredWidth = this.f4300d.getMeasuredWidth() - this.f4303g.getPaddingLeft();
            if (this.X == null || this.Y != measuredWidth) {
                ColorDrawable colorDrawable = new ColorDrawable();
                this.X = colorDrawable;
                this.Y = measuredWidth;
                colorDrawable.setBounds(0, 0, measuredWidth, 1);
            }
            Drawable[] drawableArrA = i.a(this.f4303g);
            Drawable drawable = drawableArrA[0];
            Drawable drawable2 = this.X;
            if (drawable != drawable2) {
                i.l(this.f4303g, drawable2, drawableArrA[1], drawableArrA[2], drawableArrA[3]);
                z = true;
            }
            z = false;
        } else {
            if (this.X != null) {
                Drawable[] drawableArrA2 = i.a(this.f4303g);
                i.l(this.f4303g, null, drawableArrA2[1], drawableArrA2[2], drawableArrA2[3]);
                this.X = null;
                z = true;
            }
            z = false;
        }
        if (Z()) {
            int measuredWidth2 = this.z.getMeasuredWidth() - this.f4303g.getPaddingRight();
            CheckableImageButton endIconToUpdateDummyDrawable = getEndIconToUpdateDummyDrawable();
            if (endIconToUpdateDummyDrawable != null) {
                measuredWidth2 = measuredWidth2 + endIconToUpdateDummyDrawable.getMeasuredWidth() + b.g.k.g.b((ViewGroup.MarginLayoutParams) endIconToUpdateDummyDrawable.getLayoutParams());
            }
            Drawable[] drawableArrA3 = i.a(this.f4303g);
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
                    i.l(this.f4303g, drawableArrA3[0], drawableArrA3[1], drawable5, drawableArrA3[3]);
                } else {
                    z2 = z;
                }
            } else {
                this.k0 = measuredWidth2;
                drawable3.setBounds(0, 0, measuredWidth2, 1);
                i.l(this.f4303g, drawableArrA3[0], drawableArrA3[1], this.j0, drawableArrA3[3]);
            }
        } else {
            if (this.j0 == null) {
                return z;
            }
            Drawable[] drawableArrA4 = i.a(this.f4303g);
            if (drawableArrA4[2] == this.j0) {
                i.l(this.f4303g, drawableArrA4[0], drawableArrA4[1], this.l0, drawableArrA4[3]);
            } else {
                z2 = z;
            }
            this.j0 = null;
        }
        return z2;
    }

    private void k(RectF rectF) {
        float f2 = rectF.left;
        int i2 = this.G;
        rectF.left = f2 - i2;
        rectF.top -= i2;
        rectF.right += i2;
        rectF.bottom += i2;
    }

    private void l() {
        m(this.d0, this.g0, this.f0, this.i0, this.h0);
    }

    private boolean l0() {
        int iMax;
        if (this.f4303g == null || this.f4303g.getMeasuredHeight() >= (iMax = Math.max(this.f4301e.getMeasuredHeight(), this.f4300d.getMeasuredHeight()))) {
            return false;
        }
        this.f4303g.setMinimumHeight(iMax);
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
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f4299c.getLayoutParams();
            int iU = u();
            if (iU != layoutParams.topMargin) {
                layoutParams.topMargin = iU;
                this.f4299c.requestLayout();
            }
        }
    }

    private void o() {
        int i2 = this.H;
        if (i2 == 0) {
            this.D = null;
            this.E = null;
            return;
        }
        if (i2 == 1) {
            this.D = new d.b.a.a.x.h(this.F);
            this.E = new d.b.a.a.x.h();
        } else {
            if (i2 != 2) {
                throw new IllegalArgumentException(this.H + " is illegal; only @BoxBackgroundMode constants are supported.");
            }
            if (!this.A || (this.D instanceof com.google.android.material.textfield.c)) {
                this.D = new d.b.a.a.x.h(this.F);
            } else {
                this.D = new com.google.android.material.textfield.c(this.F);
            }
            this.E = null;
        }
    }

    private int p() {
        return this.H == 1 ? d.b.a.a.o.a.e(d.b.a.a.o.a.d(this, d.b.a.a.b.colorSurface, 0), this.N) : this.N;
    }

    private void p0(boolean z, boolean z2) {
        ColorStateList colorStateList;
        TextView textView;
        boolean zIsEnabled = isEnabled();
        EditText editText = this.f4303g;
        boolean z3 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.f4303g;
        boolean z4 = editText2 != null && editText2.hasFocus();
        boolean zK = this.f4305i.k();
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
            this.C0.Q(this.f4305i.p());
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
        if (this.f4303g == null) {
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
        rect2.left = rect.left + this.f4303g.getPaddingLeft();
        rect2.top = rect.top - u();
        rect2.right = rect.right - this.f4303g.getPaddingRight();
        return rect2;
    }

    private void q0() {
        EditText editText;
        if (this.r == null || (editText = this.f4303g) == null) {
            return;
        }
        this.r.setGravity(editText.getGravity());
        this.r.setPadding(this.f4303g.getCompoundPaddingLeft(), this.f4303g.getCompoundPaddingTop(), this.f4303g.getCompoundPaddingRight(), this.f4303g.getCompoundPaddingBottom());
    }

    private int r(Rect rect, Rect rect2, float f2) {
        return O() ? (int) (rect2.top + f2) : rect.bottom - this.f4303g.getCompoundPaddingBottom();
    }

    private void r0() {
        EditText editText = this.f4303g;
        s0(editText == null ? 0 : editText.getText().length());
    }

    private int s(Rect rect, float f2) {
        return O() ? (int) (rect.centerY() - (f2 / 2.0f)) : rect.top + this.f4303g.getCompoundPaddingTop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s0(int i2) {
        if (i2 != 0 || this.B0) {
            I();
        } else {
            c0();
        }
    }

    private void setEditText(EditText editText) {
        if (this.f4303g != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (this.b0 != 3 && !(editText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.f4303g = editText;
        Q();
        setTextInputAccessibilityDelegate(new e(this));
        this.C0.k0(this.f4303g.getTypeface());
        this.C0.a0(this.f4303g.getTextSize());
        int gravity = this.f4303g.getGravity();
        this.C0.R((gravity & (-113)) | 48);
        this.C0.Z(gravity);
        this.f4303g.addTextChangedListener(new a());
        if (this.q0 == null) {
            this.q0 = this.f4303g.getHintTextColors();
        }
        if (this.A) {
            if (TextUtils.isEmpty(this.B)) {
                CharSequence hint = this.f4303g.getHint();
                this.f4304h = hint;
                setHint(hint);
                this.f4303g.setHint((CharSequence) null);
            }
            this.C = true;
        }
        if (this.m != null) {
            g0(this.f4303g.getText().length());
        }
        k0();
        this.f4305i.e();
        this.f4300d.bringToFront();
        this.f4301e.bringToFront();
        this.f4302f.bringToFront();
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
        this.f4302f.setVisibility(z ? 8 : 0);
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
            appCompatTextView.setId(d.b.a.a.f.textinput_placeholder);
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
        if (this.f4303g == null) {
            throw new IllegalStateException();
        }
        Rect rect2 = this.P;
        float fX = this.C0.x();
        rect2.left = rect.left + this.f4303g.getCompoundPaddingLeft();
        rect2.top = s(rect, fX);
        rect2.right = rect.right - this.f4303g.getCompoundPaddingRight();
        rect2.bottom = r(rect, rect2, fX);
        return rect2;
    }

    private void t0() {
        if (this.f4303g == null) {
            return;
        }
        t.w0(this.x, P() ? 0 : t.E(this.f4303g), this.f4303g.getCompoundPaddingTop(), 0, this.f4303g.getCompoundPaddingBottom());
    }

    private int u() {
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
        if (this.f4303g == null) {
            return;
        }
        t.w0(this.z, 0, this.f4303g.getPaddingTop(), (J() || K()) ? 0 : t.D(this.f4303g), this.f4303g.getPaddingBottom());
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
        return this.f4302f.getVisibility() == 0 && this.d0.getVisibility() == 0;
    }

    public boolean L() {
        return this.f4305i.x();
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
            int r4 = d.b.a.a.k.TextAppearance_AppCompat_Caption
            androidx.core.widget.i.q(r3, r4)
            android.content.Context r4 = r2.getContext()
            int r0 = d.b.a.a.c.design_error
            int r4 = androidx.core.content.a.b(r4, r0)
            r3.setTextColor(r4)
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.Y(android.widget.TextView, int):void");
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i2, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
        this.f4299c.addView(view, layoutParams2);
        this.f4299c.setLayoutParams(layoutParams);
        n0();
        setEditText((EditText) view);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i2) {
        EditText editText;
        if (this.f4304h == null || (editText = this.f4303g) == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i2);
            return;
        }
        boolean z = this.C;
        this.C = false;
        CharSequence hint = editText.getHint();
        this.f4303g.setHint(this.f4304h);
        try {
            super.dispatchProvideAutofillStructure(viewStructure, i2);
        } finally {
            this.f4303g.setHint(hint);
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
        if (this.f4303g != null) {
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
        if (this.f4303g != null) {
            fVar.a(this);
        }
    }

    public void f(g gVar) {
        this.e0.add(gVar);
    }

    void g0(int i2) {
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
            this.m.setText(b.g.i.a.c().j(getContext().getString(j.character_counter_pattern, Integer.valueOf(i2), Integer.valueOf(this.k))));
        }
        if (this.f4303g == null || z == this.l) {
            return;
        }
        o0(false);
        y0();
        k0();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.f4303g;
        return editText != null ? editText.getBaseline() + getPaddingTop() + u() : super.getBaseline();
    }

    d.b.a.a.x.h getBoxBackground() {
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
        return this.f4303g;
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
        if (this.f4305i.w()) {
            return this.f4305i.n();
        }
        return null;
    }

    public CharSequence getErrorContentDescription() {
        return this.f4305i.m();
    }

    public int getErrorCurrentTextColors() {
        return this.f4305i.o();
    }

    public Drawable getErrorIconDrawable() {
        return this.o0.getDrawable();
    }

    final int getErrorTextCurrentColor() {
        return this.f4305i.o();
    }

    public CharSequence getHelperText() {
        if (this.f4305i.x()) {
            return this.f4305i.q();
        }
        return null;
    }

    public int getHelperTextCurrentTextColor() {
        return this.f4305i.r();
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
            valueAnimator.setInterpolator(d.b.a.a.m.a.f4675b);
            this.E0.setDuration(167L);
            this.E0.addUpdateListener(new d());
        }
        this.E0.setFloatValues(this.C0.z(), f2);
        this.E0.start();
    }

    void k0() {
        Drawable background;
        TextView textView;
        EditText editText = this.f4303g;
        if (editText == null || this.H != 0 || (background = editText.getBackground()) == null) {
            return;
        }
        if (q.a(background)) {
            background = background.mutate();
        }
        if (this.f4305i.k()) {
            background.setColorFilter(androidx.appcompat.widget.f.e(this.f4305i.o(), PorterDuff.Mode.SRC_IN));
        } else if (this.l && (textView = this.m) != null) {
            background.setColorFilter(androidx.appcompat.widget.f.e(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            androidx.core.graphics.drawable.a.c(background);
            this.f4303g.refreshDrawableState();
        }
    }

    void o0(boolean z) {
        p0(z, false);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        EditText editText = this.f4303g;
        if (editText != null) {
            Rect rect = this.O;
            com.google.android.material.internal.c.a(this, editText, rect);
            e0(rect);
            if (this.A) {
                this.C0.a0(this.f4303g.getTextSize());
                int gravity = this.f4303g.getGravity();
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
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        boolean zL0 = l0();
        boolean zJ0 = j0();
        if (zL0 || zJ0) {
            this.f4303g.post(new c());
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
        super.onRestoreInstanceState(savedState.a());
        setError(savedState.f4306d);
        if (savedState.f4307e) {
            this.d0.post(new b());
        }
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.f4305i.k()) {
            savedState.f4306d = getError();
        }
        savedState.f4307e = H() && this.d0.isChecked();
        return savedState;
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
        setBoxBackgroundColor(androidx.core.content.a.b(getContext(), i2));
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
        if (this.f4303g != null) {
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
                appCompatTextView.setId(d.b.a.a.f.textinput_counter);
                Typeface typeface = this.R;
                if (typeface != null) {
                    this.m.setTypeface(typeface);
                }
                this.m.setMaxLines(1);
                this.f4305i.d(this.m, 2);
                b.g.k.g.d((ViewGroup.MarginLayoutParams) this.m.getLayoutParams(), getResources().getDimensionPixelOffset(d.b.a.a.d.mtrl_textinput_counter_margin_start));
                i0();
                f0();
            } else {
                this.f4305i.y(this.m, 2);
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
        if (this.f4303g != null) {
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
        setEndIconDrawable(i2 != 0 ? b.a.k.a.a.d(getContext(), i2) : null);
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
        if (!this.f4305i.w()) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.f4305i.s();
        } else {
            this.f4305i.L(charSequence);
        }
    }

    public void setErrorContentDescription(CharSequence charSequence) {
        this.f4305i.A(charSequence);
    }

    public void setErrorEnabled(boolean z) {
        this.f4305i.B(z);
    }

    public void setErrorIconDrawable(int i2) {
        setErrorIconDrawable(i2 != 0 ? b.a.k.a.a.d(getContext(), i2) : null);
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

    public void setErrorTextAppearance(int i2) {
        this.f4305i.C(i2);
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        this.f4305i.D(colorStateList);
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
            this.f4305i.M(charSequence);
        }
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        this.f4305i.G(colorStateList);
    }

    public void setHelperTextEnabled(boolean z) {
        this.f4305i.F(z);
    }

    public void setHelperTextTextAppearance(int i2) {
        this.f4305i.E(i2);
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
                CharSequence hint = this.f4303g.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.B)) {
                        setHint(hint);
                    }
                    this.f4303g.setHint((CharSequence) null);
                }
                this.C = true;
            } else {
                this.C = false;
                if (!TextUtils.isEmpty(this.B) && TextUtils.isEmpty(this.f4303g.getHint())) {
                    this.f4303g.setHint(this.B);
                }
                setHintInternal(null);
            }
            if (this.f4303g != null) {
                n0();
            }
        }
    }

    public void setHintTextAppearance(int i2) {
        this.C0.O(i2);
        this.r0 = this.C0.n();
        if (this.f4303g != null) {
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
            if (this.f4303g != null) {
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
        setPasswordVisibilityToggleDrawable(i2 != 0 ? b.a.k.a.a.d(getContext(), i2) : null);
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
        setStartIconDrawable(i2 != 0 ? b.a.k.a.a.d(getContext(), i2) : null);
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
        EditText editText = this.f4303g;
        if (editText != null) {
            t.j0(editText, eVar);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != this.R) {
            this.R = typeface;
            this.C0.k0(typeface);
            this.f4305i.I(typeface);
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
        boolean z2 = isFocused() || ((editText2 = this.f4303g) != null && editText2.hasFocus());
        boolean z3 = isHovered() || ((editText = this.f4303g) != null && editText.isHovered());
        if (!isEnabled()) {
            this.M = this.A0;
        } else if (this.f4305i.k()) {
            if (this.v0 != null) {
                v0(z2, z3);
            } else {
                this.M = this.f4305i.o();
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
        if (getErrorIconDrawable() != null && this.f4305i.w() && this.f4305i.k()) {
            z = true;
        }
        setErrorIconVisible(z);
        m0(this.o0, this.p0);
        m0(this.S, this.T);
        m0(this.d0, this.f0);
        if (getEndIconDelegate().d()) {
            d0(this.f4305i.k());
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
        this(context, attributeSet, d.b.a.a.b.textInputStyle);
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
        setErrorIconVisible(drawable != null && this.f4305i.w());
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
        int i4 = f4298b;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i2, i4), attributeSet, i2);
        this.f4305i = new com.google.android.material.textfield.f(this);
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
        this.f4299c = frameLayout;
        frameLayout.setAddStatesFromChildren(true);
        addView(frameLayout);
        LinearLayout linearLayout = new LinearLayout(context2);
        this.f4300d = linearLayout;
        linearLayout.setOrientation(0);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        frameLayout.addView(linearLayout);
        LinearLayout linearLayout2 = new LinearLayout(context2);
        this.f4301e = linearLayout2;
        linearLayout2.setOrientation(0);
        linearLayout2.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388613));
        frameLayout.addView(linearLayout2);
        FrameLayout frameLayout2 = new FrameLayout(context2);
        this.f4302f = frameLayout2;
        frameLayout2.setLayoutParams(new FrameLayout.LayoutParams(-2, -1));
        TimeInterpolator timeInterpolator = d.b.a.a.m.a.f4674a;
        aVar.j0(timeInterpolator);
        aVar.g0(timeInterpolator);
        aVar.R(8388659);
        int[] iArr = l.TextInputLayout;
        int i5 = l.TextInputLayout_counterTextAppearance;
        int i6 = l.TextInputLayout_counterOverflowTextAppearance;
        int i7 = l.TextInputLayout_errorTextAppearance;
        int i8 = l.TextInputLayout_helperTextTextAppearance;
        int i9 = l.TextInputLayout_hintTextAppearance;
        f0 f0VarI = com.google.android.material.internal.l.i(context2, attributeSet, iArr, i2, i4, i5, i6, i7, i8, i9);
        this.A = f0VarI.a(l.TextInputLayout_hintEnabled, true);
        setHint(f0VarI.p(l.TextInputLayout_android_hint));
        this.D0 = f0VarI.a(l.TextInputLayout_hintAnimationEnabled, true);
        this.F = m.e(context2, attributeSet, i2, i4).m();
        this.G = context2.getResources().getDimensionPixelOffset(d.b.a.a.d.mtrl_textinput_box_label_cutout_padding);
        this.I = f0VarI.e(l.TextInputLayout_boxCollapsedPaddingTop, 0);
        this.K = f0VarI.f(l.TextInputLayout_boxStrokeWidth, context2.getResources().getDimensionPixelSize(d.b.a.a.d.mtrl_textinput_box_stroke_width_default));
        this.L = f0VarI.f(l.TextInputLayout_boxStrokeWidthFocused, context2.getResources().getDimensionPixelSize(d.b.a.a.d.mtrl_textinput_box_stroke_width_focused));
        this.J = this.K;
        float fD = f0VarI.d(l.TextInputLayout_boxCornerRadiusTopStart, -1.0f);
        float fD2 = f0VarI.d(l.TextInputLayout_boxCornerRadiusTopEnd, -1.0f);
        float fD3 = f0VarI.d(l.TextInputLayout_boxCornerRadiusBottomEnd, -1.0f);
        float fD4 = f0VarI.d(l.TextInputLayout_boxCornerRadiusBottomStart, -1.0f);
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
        ColorStateList colorStateListB = d.b.a.a.u.c.b(context2, f0VarI, l.TextInputLayout_boxBackgroundColor);
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
                ColorStateList colorStateListC = b.a.k.a.a.c(context2, d.b.a.a.c.mtrl_filled_background_color);
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
        if (f0VarI.s(i10)) {
            ColorStateList colorStateListC2 = f0VarI.c(i10);
            this.r0 = colorStateListC2;
            this.q0 = colorStateListC2;
        }
        int i11 = l.TextInputLayout_boxStrokeColor;
        ColorStateList colorStateListB2 = d.b.a.a.u.c.b(context2, f0VarI, i11);
        this.u0 = f0VarI.b(i11, 0);
        this.s0 = androidx.core.content.a.b(context2, d.b.a.a.c.mtrl_textinput_default_box_stroke_color);
        this.A0 = androidx.core.content.a.b(context2, d.b.a.a.c.mtrl_textinput_disabled_color);
        this.t0 = androidx.core.content.a.b(context2, d.b.a.a.c.mtrl_textinput_hovered_box_stroke_color);
        if (colorStateListB2 != null) {
            setBoxStrokeColorStateList(colorStateListB2);
        }
        int i12 = l.TextInputLayout_boxStrokeErrorColor;
        if (f0VarI.s(i12)) {
            setBoxStrokeErrorColor(d.b.a.a.u.c.b(context2, f0VarI, i12));
        }
        if (f0VarI.n(i9, -1) != -1) {
            setHintTextAppearance(f0VarI.n(i9, 0));
        }
        int iN = f0VarI.n(i7, 0);
        CharSequence charSequenceP = f0VarI.p(l.TextInputLayout_errorContentDescription);
        boolean zA = f0VarI.a(l.TextInputLayout_errorEnabled, false);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int i13 = d.b.a.a.h.design_text_input_end_icon;
        CheckableImageButton checkableImageButton = (CheckableImageButton) layoutInflaterFrom.inflate(i13, (ViewGroup) linearLayout2, false);
        this.o0 = checkableImageButton;
        checkableImageButton.setVisibility(8);
        int i14 = l.TextInputLayout_errorIconDrawable;
        if (f0VarI.s(i14)) {
            setErrorIconDrawable(f0VarI.g(i14));
        }
        int i15 = l.TextInputLayout_errorIconTint;
        if (f0VarI.s(i15)) {
            setErrorIconTintList(d.b.a.a.u.c.b(context2, f0VarI, i15));
        }
        int i16 = l.TextInputLayout_errorIconTintMode;
        if (f0VarI.s(i16)) {
            setErrorIconTintMode(com.google.android.material.internal.q.i(f0VarI.k(i16, -1), null));
        }
        checkableImageButton.setContentDescription(getResources().getText(j.error_icon_content_description));
        t.t0(checkableImageButton, i3);
        checkableImageButton.setClickable(false);
        checkableImageButton.setPressable(false);
        checkableImageButton.setFocusable(false);
        int iN2 = f0VarI.n(i8, 0);
        boolean zA2 = f0VarI.a(l.TextInputLayout_helperTextEnabled, false);
        CharSequence charSequenceP2 = f0VarI.p(l.TextInputLayout_helperText);
        int iN3 = f0VarI.n(l.TextInputLayout_placeholderTextAppearance, 0);
        CharSequence charSequenceP3 = f0VarI.p(l.TextInputLayout_placeholderText);
        int iN4 = f0VarI.n(l.TextInputLayout_prefixTextAppearance, 0);
        CharSequence charSequenceP4 = f0VarI.p(l.TextInputLayout_prefixText);
        int iN5 = f0VarI.n(l.TextInputLayout_suffixTextAppearance, 0);
        CharSequence charSequenceP5 = f0VarI.p(l.TextInputLayout_suffixText);
        boolean zA3 = f0VarI.a(l.TextInputLayout_counterEnabled, false);
        setCounterMaxLength(f0VarI.k(l.TextInputLayout_counterMaxLength, -1));
        this.o = f0VarI.n(i5, 0);
        this.n = f0VarI.n(i6, 0);
        CheckableImageButton checkableImageButton2 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(d.b.a.a.h.design_text_input_start_icon, (ViewGroup) this.f4300d, false);
        this.S = checkableImageButton2;
        checkableImageButton2.setVisibility(8);
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        int i17 = l.TextInputLayout_startIconDrawable;
        if (f0VarI.s(i17)) {
            setStartIconDrawable(f0VarI.g(i17));
            int i18 = l.TextInputLayout_startIconContentDescription;
            if (f0VarI.s(i18)) {
                setStartIconContentDescription(f0VarI.p(i18));
            }
            setStartIconCheckable(f0VarI.a(l.TextInputLayout_startIconCheckable, true));
        }
        int i19 = l.TextInputLayout_startIconTint;
        if (f0VarI.s(i19)) {
            setStartIconTintList(d.b.a.a.u.c.b(context2, f0VarI, i19));
        }
        int i20 = l.TextInputLayout_startIconTintMode;
        if (f0VarI.s(i20)) {
            setStartIconTintMode(com.google.android.material.internal.q.i(f0VarI.k(i20, -1), null));
        }
        setBoxBackgroundMode(f0VarI.k(l.TextInputLayout_boxBackgroundMode, 0));
        this.d0 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(i13, (ViewGroup) this.f4302f, false);
        this.f4302f.addView(this.d0);
        this.d0.setVisibility(8);
        this.c0.append(-1, new com.google.android.material.textfield.b(this));
        this.c0.append(0, new com.google.android.material.textfield.g(this));
        this.c0.append(1, new h(this));
        this.c0.append(2, new com.google.android.material.textfield.a(this));
        this.c0.append(3, new com.google.android.material.textfield.d(this));
        int i21 = l.TextInputLayout_endIconMode;
        if (f0VarI.s(i21)) {
            setEndIconMode(f0VarI.k(i21, 0));
            int i22 = l.TextInputLayout_endIconDrawable;
            if (f0VarI.s(i22)) {
                setEndIconDrawable(f0VarI.g(i22));
            }
            int i23 = l.TextInputLayout_endIconContentDescription;
            if (f0VarI.s(i23)) {
                setEndIconContentDescription(f0VarI.p(i23));
            }
            setEndIconCheckable(f0VarI.a(l.TextInputLayout_endIconCheckable, true));
        } else {
            int i24 = l.TextInputLayout_passwordToggleEnabled;
            if (f0VarI.s(i24)) {
                setEndIconMode(f0VarI.a(i24, false) ? 1 : 0);
                setEndIconDrawable(f0VarI.g(l.TextInputLayout_passwordToggleDrawable));
                setEndIconContentDescription(f0VarI.p(l.TextInputLayout_passwordToggleContentDescription));
                int i25 = l.TextInputLayout_passwordToggleTint;
                if (f0VarI.s(i25)) {
                    setEndIconTintList(d.b.a.a.u.c.b(context2, f0VarI, i25));
                }
                int i26 = l.TextInputLayout_passwordToggleTintMode;
                if (f0VarI.s(i26)) {
                    setEndIconTintMode(com.google.android.material.internal.q.i(f0VarI.k(i26, -1), null));
                }
            }
        }
        if (!f0VarI.s(l.TextInputLayout_passwordToggleEnabled)) {
            int i27 = l.TextInputLayout_endIconTint;
            if (f0VarI.s(i27)) {
                setEndIconTintList(d.b.a.a.u.c.b(context2, f0VarI, i27));
            }
            int i28 = l.TextInputLayout_endIconTintMode;
            if (f0VarI.s(i28)) {
                setEndIconTintMode(com.google.android.material.internal.q.i(f0VarI.k(i28, -1), null));
            }
        }
        AppCompatTextView appCompatTextView = new AppCompatTextView(context2);
        this.x = appCompatTextView;
        appCompatTextView.setId(d.b.a.a.f.textinput_prefix_text);
        appCompatTextView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        t.l0(appCompatTextView, 1);
        this.f4300d.addView(this.S);
        this.f4300d.addView(appCompatTextView);
        AppCompatTextView appCompatTextView2 = new AppCompatTextView(context2);
        this.z = appCompatTextView2;
        appCompatTextView2.setId(d.b.a.a.f.textinput_suffix_text);
        appCompatTextView2.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 80));
        t.l0(appCompatTextView2, 1);
        this.f4301e.addView(appCompatTextView2);
        this.f4301e.addView(this.o0);
        this.f4301e.addView(this.f4302f);
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
        if (f0VarI.s(i29)) {
            setErrorTextColor(f0VarI.c(i29));
        }
        int i30 = l.TextInputLayout_helperTextTextColor;
        if (f0VarI.s(i30)) {
            setHelperTextColor(f0VarI.c(i30));
        }
        int i31 = l.TextInputLayout_hintTextColor;
        if (f0VarI.s(i31)) {
            setHintTextColor(f0VarI.c(i31));
        }
        int i32 = l.TextInputLayout_counterTextColor;
        if (f0VarI.s(i32)) {
            setCounterTextColor(f0VarI.c(i32));
        }
        int i33 = l.TextInputLayout_counterOverflowTextColor;
        if (f0VarI.s(i33)) {
            setCounterOverflowTextColor(f0VarI.c(i33));
        }
        int i34 = l.TextInputLayout_placeholderTextColor;
        if (f0VarI.s(i34)) {
            setPlaceholderTextColor(f0VarI.c(i34));
        }
        int i35 = l.TextInputLayout_prefixTextColor;
        if (f0VarI.s(i35)) {
            setPrefixTextColor(f0VarI.c(i35));
        }
        int i36 = l.TextInputLayout_suffixTextColor;
        if (f0VarI.s(i36)) {
            setSuffixTextColor(f0VarI.c(i36));
        }
        setCounterEnabled(zA3);
        setEnabled(f0VarI.a(l.TextInputLayout_android_enabled, true));
        f0VarI.w();
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
