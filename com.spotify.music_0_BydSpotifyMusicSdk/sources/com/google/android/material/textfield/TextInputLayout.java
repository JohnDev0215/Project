package com.google.android.material.textfield;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.animation.LinearInterpolator;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.internal.CheckableImageButton;
import com.spotify.music.R;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.WeakHashMap;
import p.at;
import p.be0;
import p.bt;
import p.c50;
import p.cb0;
import p.ci1;
import p.e7;
import p.et;
import p.fa;
import p.h42;
import p.he1;
import p.hk0;
import p.ht2;
import p.i90;
import p.it2;
import p.iu;
import p.jn;
import p.jt2;
import p.kj2;
import p.l13;
import p.l41;
import p.lt2;
import p.oc2;
import p.pa2;
import p.pb0;
import p.pf1;
import p.pz;
import p.qf1;
import p.qu;
import p.rj2;
import p.sj2;
import p.sz;
import p.tj2;
import p.u4;
import p.u8;
import p.uj2;
import p.vj2;
import p.wl;
import p.wp;
import p.xe1;
import p.xl0;
import p.ya0;
import p.yf1;
import p.yj2;
import p.zj2;
import p.zn2;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class TextInputLayout extends LinearLayout {
    public ColorStateList A;
    public View.OnLongClickListener A0;
    public int B;
    public final CheckableImageButton B0;
    public xl0 C;
    public ColorStateList C0;
    public xl0 D;
    public ColorStateList D0;
    public ColorStateList E;
    public ColorStateList E0;
    public ColorStateList F;
    public int F0;
    public CharSequence G;
    public int G0;
    public final fa H;
    public int H0;
    public CharSequence I;
    public ColorStateList I0;
    public final fa J;
    public int J0;
    public boolean K;
    public int K0;
    public CharSequence L;
    public int L0;
    public boolean M;
    public int M0;
    public qf1 N;
    public int N0;
    public qf1 O;
    public boolean O0;
    public final oc2 P;
    public final iu P0;
    public final int Q;
    public boolean Q0;
    public int R;
    public boolean R0;
    public int S;
    public ValueAnimator S0;
    public int T;
    public boolean T0;
    public int U;
    public boolean U0;
    public int V;
    public int W;
    public int a0;
    public final Rect b0;
    public final FrameLayout c;
    public final Rect c0;
    public final RectF d0;
    public final LinearLayout e;
    public Typeface e0;
    public final CheckableImageButton f0;
    public ColorStateList g0;
    public boolean h0;
    public PorterDuff.Mode i0;
    public boolean j0;
    public final LinearLayout k;
    public ColorDrawable k0;
    public final FrameLayout l;
    public int l0;
    public EditText m;
    public View.OnLongClickListener m0;
    public CharSequence n;
    public final LinkedHashSet n0;
    public int o;
    public int o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f35p;
    public final SparseArray p0;
    public final l41 q;
    public final CheckableImageButton q0;
    public boolean r;
    public final LinkedHashSet r0;
    public int s;
    public ColorStateList s0;
    public boolean t;
    public boolean t0;
    public fa u;
    public PorterDuff.Mode u0;
    public int v;
    public boolean v0;
    public int w;
    public ColorDrawable w0;
    public CharSequence x;
    public int x0;
    public boolean y;
    public Drawable y0;
    public fa z;
    public View.OnLongClickListener z0;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0532  */
    /* JADX WARN: Type inference failed for: r3v110 */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v30, types: [boolean, int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public TextInputLayout(android.content.Context r29, android.util.AttributeSet r30) {
        /*
            Method dump skipped, instruction units count: 1609
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public static void d(CheckableImageButton checkableImageButton, boolean z, ColorStateList colorStateList, boolean z2, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null && (z || z2)) {
            drawable = drawable.mutate();
            if (z) {
                ya0.h(drawable, colorStateList);
            }
            if (z2) {
                ya0.i(drawable, mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    private be0 getEndIconDelegate() {
        SparseArray sparseArray = this.p0;
        be0 be0Var = (be0) sparseArray.get(this.o0);
        return be0Var != null ? be0Var : (be0) sparseArray.get(0);
    }

    private CheckableImageButton getEndIconToUpdateDummyDrawable() {
        CheckableImageButton checkableImageButton = this.B0;
        if (checkableImageButton.getVisibility() == 0) {
            return checkableImageButton;
        }
        if (this.o0 == 0 || !g()) {
            return null;
        }
        return this.q0;
    }

    public static void j(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                j((ViewGroup) childAt, z);
            }
        }
    }

    public static void l(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        WeakHashMap weakHashMap = zt2.a;
        boolean zA = ht2.a(checkableImageButton);
        boolean z = onLongClickListener != null;
        boolean z2 = zA || z;
        checkableImageButton.setFocusable(z2);
        checkableImageButton.setClickable(zA);
        checkableImageButton.setPressable(zA);
        checkableImageButton.setLongClickable(z);
        it2.s(checkableImageButton, z2 ? 1 : 2);
    }

    private void setEditText(EditText editText) {
        boolean z;
        boolean z2;
        if (this.m != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (this.o0 != 3) {
            boolean z3 = editText instanceof TextInputEditText;
        }
        this.m = editText;
        setMinWidth(this.o);
        setMaxWidth(this.f35p);
        h();
        setTextInputAccessibilityDelegate(new sj2(this));
        Typeface typeface = this.m.getTypeface();
        iu iuVar = this.P0;
        wp wpVar = iuVar.v;
        if (wpVar != null) {
            wpVar.k = true;
        }
        if (iuVar.s != typeface) {
            iuVar.s = typeface;
            z = true;
        } else {
            z = false;
        }
        if (iuVar.t != typeface) {
            iuVar.t = typeface;
            z2 = true;
        } else {
            z2 = false;
        }
        if (z || z2) {
            iuVar.g();
        }
        float textSize = this.m.getTextSize();
        if (iuVar.i != textSize) {
            iuVar.i = textSize;
            iuVar.g();
        }
        int gravity = this.m.getGravity();
        int i = (gravity & (-113)) | 48;
        if (iuVar.h != i) {
            iuVar.h = i;
            iuVar.g();
        }
        if (iuVar.g != gravity) {
            iuVar.g = gravity;
            iuVar.g();
        }
        this.m.addTextChangedListener(new pa2(2, this));
        if (this.D0 == null) {
            this.D0 = this.m.getHintTextColors();
        }
        if (this.K) {
            if (TextUtils.isEmpty(this.L)) {
                CharSequence hint = this.m.getHint();
                this.n = hint;
                setHint(hint);
                this.m.setHint((CharSequence) null);
            }
            this.M = true;
        }
        if (this.u != null) {
            n(this.m.getText().length());
        }
        q();
        this.q.b();
        this.e.bringToFront();
        this.k.bringToFront();
        this.l.bringToFront();
        this.B0.bringToFront();
        Iterator it = this.n0.iterator();
        while (it.hasNext()) {
            ((at) ((tj2) it.next())).a(this);
        }
        u();
        x();
        if (!isEnabled()) {
            editText.setEnabled(false);
        }
        s(false, true);
    }

    private void setErrorIconVisible(boolean z) {
        this.B0.setVisibility(z ? 0 : 8);
        this.l.setVisibility(z ? 8 : 0);
        x();
        if (this.o0 != 0) {
            return;
        }
        p();
    }

    private void setHintInternal(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.L)) {
            return;
        }
        this.L = charSequence;
        iu iuVar = this.P0;
        if (charSequence == null || !TextUtils.equals(iuVar.w, charSequence)) {
            iuVar.w = charSequence;
            iuVar.x = null;
            Bitmap bitmap = iuVar.z;
            if (bitmap != null) {
                bitmap.recycle();
                iuVar.z = null;
            }
            iuVar.g();
        }
        if (this.O0) {
            return;
        }
        i();
    }

    private void setPlaceholderTextEnabled(boolean z) {
        if (this.y == z) {
            return;
        }
        if (z) {
            fa faVar = new fa(getContext(), null);
            this.z = faVar;
            faVar.setId(R.id.textinput_placeholder);
            xl0 xl0Var = new xl0();
            xl0Var.F = 3;
            xl0Var.k = 87L;
            LinearInterpolator linearInterpolator = e7.a;
            xl0Var.l = linearInterpolator;
            this.C = xl0Var;
            xl0Var.e = 67L;
            xl0 xl0Var2 = new xl0();
            xl0Var2.F = 3;
            xl0Var2.k = 87L;
            xl0Var2.l = linearInterpolator;
            this.D = xl0Var2;
            lt2.f(this.z, 1);
            setPlaceholderTextAppearance(this.B);
            setPlaceholderTextColor(this.A);
            fa faVar2 = this.z;
            if (faVar2 != null) {
                this.c.addView(faVar2);
                this.z.setVisibility(0);
            }
        } else {
            fa faVar3 = this.z;
            if (faVar3 != null) {
                faVar3.setVisibility(8);
            }
            this.z = null;
        }
        this.y = z;
    }

    public final void a(float f) {
        iu iuVar = this.P0;
        if (iuVar.c == f) {
            return;
        }
        int i = 2;
        if (this.S0 == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.S0 = valueAnimator;
            valueAnimator.setInterpolator(e7.b);
            this.S0.setDuration(167L);
            this.S0.addUpdateListener(new jn(i, this));
        }
        this.S0.setFloatValues(iuVar.c, f);
        this.S0.start();
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
        FrameLayout frameLayout = this.c;
        frameLayout.addView(view, layoutParams2);
        frameLayout.setLayoutParams(layoutParams);
        r();
        setEditText((EditText) view);
    }

    public final void b() {
        int i;
        int i2;
        int i3;
        qf1 qf1Var = this.N;
        if (qf1Var == null) {
            return;
        }
        qf1Var.setShapeAppearanceModel(this.P);
        if (this.R == 2 && (i2 = this.T) > -1 && (i3 = this.W) != 0) {
            qf1 qf1Var2 = this.N;
            qf1Var2.c.k = i2;
            qf1Var2.invalidateSelf();
            ColorStateList colorStateListValueOf = ColorStateList.valueOf(i3);
            pf1 pf1Var = qf1Var2.c;
            if (pf1Var.d != colorStateListValueOf) {
                pf1Var.d = colorStateListValueOf;
                qf1Var2.onStateChange(qf1Var2.getState());
            }
        }
        int iB = this.a0;
        if (this.R == 1) {
            TypedValue typedValueN = he1.N(getContext(), R.attr.colorSurface);
            iB = qu.b(this.a0, typedValueN != null ? typedValueN.data : 0);
        }
        this.a0 = iB;
        this.N.k(ColorStateList.valueOf(iB));
        if (this.o0 == 3) {
            this.m.getBackground().invalidateSelf();
        }
        qf1 qf1Var3 = this.O;
        if (qf1Var3 != null) {
            if (this.T > -1 && (i = this.W) != 0) {
                qf1Var3.k(ColorStateList.valueOf(i));
            }
            invalidate();
        }
        invalidate();
    }

    public final void c() {
        d(this.q0, this.t0, this.s0, this.v0, this.u0);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i) {
        EditText editText = this.m;
        if (editText == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i);
            return;
        }
        if (this.n != null) {
            boolean z = this.M;
            this.M = false;
            CharSequence hint = editText.getHint();
            this.m.setHint(this.n);
            try {
                super.dispatchProvideAutofillStructure(viewStructure, i);
                return;
            } finally {
                this.m.setHint(hint);
                this.M = z;
            }
        }
        viewStructure.setAutofillId(getAutofillId());
        onProvideAutofillStructure(viewStructure, i);
        onProvideAutofillVirtualStructure(viewStructure, i);
        FrameLayout frameLayout = this.c;
        viewStructure.setChildCount(frameLayout.getChildCount());
        for (int i2 = 0; i2 < frameLayout.getChildCount(); i2++) {
            View childAt = frameLayout.getChildAt(i2);
            ViewStructure viewStructureNewChild = viewStructure.newChild(i2);
            childAt.dispatchProvideAutofillStructure(viewStructureNewChild, i);
            if (childAt == this.m) {
                viewStructureNewChild.setHint(getHint());
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        this.U0 = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.U0 = false;
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.K) {
            iu iuVar = this.P0;
            iuVar.getClass();
            int iSave = canvas.save();
            if (iuVar.x != null && iuVar.b) {
                iuVar.N.getLineLeft(0);
                iuVar.E.setTextSize(iuVar.B);
                float f = iuVar.q;
                float f2 = iuVar.r;
                float f3 = iuVar.A;
                if (f3 != 1.0f) {
                    canvas.scale(f3, f3, f, f2);
                }
                canvas.translate(f, f2);
                iuVar.N.draw(canvas);
                canvas.restoreToCount(iSave);
            }
        }
        qf1 qf1Var = this.O;
        if (qf1Var != null) {
            Rect bounds = qf1Var.getBounds();
            bounds.top = bounds.bottom - this.T;
            this.O.draw(canvas);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002f  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void drawableStateChanged() {
        /*
            r4 = this;
            boolean r0 = r4.T0
            if (r0 == 0) goto L5
            return
        L5:
            r0 = 1
            r4.T0 = r0
            super.drawableStateChanged()
            int[] r1 = r4.getDrawableState()
            r2 = 0
            p.iu r3 = r4.P0
            if (r3 == 0) goto L2f
            r3.C = r1
            android.content.res.ColorStateList r1 = r3.l
            if (r1 == 0) goto L20
            boolean r1 = r1.isStateful()
            if (r1 != 0) goto L2a
        L20:
            android.content.res.ColorStateList r1 = r3.k
            if (r1 == 0) goto L2f
            boolean r1 = r1.isStateful()
            if (r1 == 0) goto L2f
        L2a:
            r3.g()
            r1 = r0
            goto L30
        L2f:
            r1 = r2
        L30:
            android.widget.EditText r3 = r4.m
            if (r3 == 0) goto L47
            java.util.WeakHashMap r3 = p.zt2.a
            boolean r3 = p.lt2.c(r4)
            if (r3 == 0) goto L43
            boolean r3 = r4.isEnabled()
            if (r3 == 0) goto L43
            goto L44
        L43:
            r0 = r2
        L44:
            r4.s(r0, r2)
        L47:
            r4.q()
            r4.z()
            if (r1 == 0) goto L52
            r4.invalidate()
        L52:
            r4.T0 = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.drawableStateChanged():void");
    }

    public final int e() {
        float fC;
        if (!this.K) {
            return 0;
        }
        int i = this.R;
        iu iuVar = this.P0;
        if (i == 0 || i == 1) {
            fC = iuVar.c();
        } else {
            if (i != 2) {
                return 0;
            }
            fC = iuVar.c() / 2.0f;
        }
        return (int) fC;
    }

    public final boolean f() {
        return this.K && !TextUtils.isEmpty(this.L) && (this.N instanceof c50);
    }

    public final boolean g() {
        return this.l.getVisibility() == 0 && this.q0.getVisibility() == 0;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.m;
        if (editText == null) {
            return super.getBaseline();
        }
        return e() + getPaddingTop() + editText.getBaseline();
    }

    public qf1 getBoxBackground() {
        int i = this.R;
        if (i == 1 || i == 2) {
            return this.N;
        }
        throw new IllegalStateException();
    }

    public int getBoxBackgroundColor() {
        return this.a0;
    }

    public int getBoxBackgroundMode() {
        return this.R;
    }

    public int getBoxCollapsedPaddingTop() {
        return this.S;
    }

    public float getBoxCornerRadiusBottomEnd() {
        qf1 qf1Var = this.N;
        return qf1Var.c.a.h.a(qf1Var.g());
    }

    public float getBoxCornerRadiusBottomStart() {
        qf1 qf1Var = this.N;
        return qf1Var.c.a.g.a(qf1Var.g());
    }

    public float getBoxCornerRadiusTopEnd() {
        qf1 qf1Var = this.N;
        return qf1Var.c.a.f.a(qf1Var.g());
    }

    public float getBoxCornerRadiusTopStart() {
        qf1 qf1Var = this.N;
        return qf1Var.c.a.e.a(qf1Var.g());
    }

    public int getBoxStrokeColor() {
        return this.H0;
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.I0;
    }

    public int getBoxStrokeWidth() {
        return this.U;
    }

    public int getBoxStrokeWidthFocused() {
        return this.V;
    }

    public int getCounterMaxLength() {
        return this.s;
    }

    public CharSequence getCounterOverflowDescription() {
        fa faVar;
        if (this.r && this.t && (faVar = this.u) != null) {
            return faVar.getContentDescription();
        }
        return null;
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.E;
    }

    public ColorStateList getCounterTextColor() {
        return this.E;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.D0;
    }

    public EditText getEditText() {
        return this.m;
    }

    public CharSequence getEndIconContentDescription() {
        return this.q0.getContentDescription();
    }

    public Drawable getEndIconDrawable() {
        return this.q0.getDrawable();
    }

    public int getEndIconMode() {
        return this.o0;
    }

    public CheckableImageButton getEndIconView() {
        return this.q0;
    }

    public CharSequence getError() {
        l41 l41Var = this.q;
        if (l41Var.k) {
            return l41Var.j;
        }
        return null;
    }

    public CharSequence getErrorContentDescription() {
        return this.q.m;
    }

    public int getErrorCurrentTextColors() {
        fa faVar = this.q.l;
        if (faVar != null) {
            return faVar.getCurrentTextColor();
        }
        return -1;
    }

    public Drawable getErrorIconDrawable() {
        return this.B0.getDrawable();
    }

    public final int getErrorTextCurrentColor() {
        fa faVar = this.q.l;
        if (faVar != null) {
            return faVar.getCurrentTextColor();
        }
        return -1;
    }

    public CharSequence getHelperText() {
        l41 l41Var = this.q;
        if (l41Var.q) {
            return l41Var.f146p;
        }
        return null;
    }

    public int getHelperTextCurrentTextColor() {
        fa faVar = this.q.r;
        if (faVar != null) {
            return faVar.getCurrentTextColor();
        }
        return -1;
    }

    public CharSequence getHint() {
        if (this.K) {
            return this.L;
        }
        return null;
    }

    public final float getHintCollapsedTextHeight() {
        return this.P0.c();
    }

    public final int getHintCurrentCollapsedTextColor() {
        iu iuVar = this.P0;
        return iuVar.d(iuVar.l);
    }

    public ColorStateList getHintTextColor() {
        return this.E0;
    }

    public int getMaxWidth() {
        return this.f35p;
    }

    public int getMinWidth() {
        return this.o;
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.q0.getContentDescription();
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.q0.getDrawable();
    }

    public CharSequence getPlaceholderText() {
        if (this.y) {
            return this.x;
        }
        return null;
    }

    public int getPlaceholderTextAppearance() {
        return this.B;
    }

    public ColorStateList getPlaceholderTextColor() {
        return this.A;
    }

    public CharSequence getPrefixText() {
        return this.G;
    }

    public ColorStateList getPrefixTextColor() {
        return this.H.getTextColors();
    }

    public TextView getPrefixTextView() {
        return this.H;
    }

    public CharSequence getStartIconContentDescription() {
        return this.f0.getContentDescription();
    }

    public Drawable getStartIconDrawable() {
        return this.f0.getDrawable();
    }

    public CharSequence getSuffixText() {
        return this.I;
    }

    public ColorStateList getSuffixTextColor() {
        return this.J.getTextColors();
    }

    public TextView getSuffixTextView() {
        return this.J;
    }

    public Typeface getTypeface() {
        return this.e0;
    }

    public final void h() {
        int i = this.R;
        if (i != 0) {
            oc2 oc2Var = this.P;
            if (i == 1) {
                this.N = new qf1(oc2Var);
                this.O = new qf1();
            } else {
                if (i != 2) {
                    throw new IllegalArgumentException(u4.l(new StringBuilder(), this.R, " is illegal; only @BoxBackgroundMode constants are supported."));
                }
                if (!this.K || (this.N instanceof c50)) {
                    this.N = new qf1(oc2Var);
                } else {
                    this.N = new c50(oc2Var);
                }
                this.O = null;
            }
        } else {
            this.N = null;
            this.O = null;
        }
        EditText editText = this.m;
        if (editText != null && this.N != null && editText.getBackground() == null && this.R != 0) {
            EditText editText2 = this.m;
            qf1 qf1Var = this.N;
            WeakHashMap weakHashMap = zt2.a;
            it2.q(editText2, qf1Var);
        }
        z();
        if (this.R == 1) {
            if (getContext().getResources().getConfiguration().fontScale >= 2.0f) {
                this.S = getResources().getDimensionPixelSize(R.dimen.material_font_2_0_box_collapsed_padding_top);
            } else if (ci1.z(getContext())) {
                this.S = getResources().getDimensionPixelSize(R.dimen.material_font_1_3_box_collapsed_padding_top);
            }
        }
        if (this.m != null && this.R == 1) {
            if (getContext().getResources().getConfiguration().fontScale >= 2.0f) {
                EditText editText3 = this.m;
                WeakHashMap weakHashMap2 = zt2.a;
                jt2.k(editText3, jt2.f(editText3), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_2_0_padding_top), jt2.e(this.m), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_2_0_padding_bottom));
            } else if (ci1.z(getContext())) {
                EditText editText4 = this.m;
                WeakHashMap weakHashMap3 = zt2.a;
                jt2.k(editText4, jt2.f(editText4), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_1_3_padding_top), jt2.e(this.m), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_1_3_padding_bottom));
            }
        }
        if (this.R != 0) {
            r();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0095  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i() {
        /*
            Method dump skipped, instruction units count: 221
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.i():void");
    }

    public final void k(CheckableImageButton checkableImageButton, ColorStateList colorStateList) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (checkableImageButton.getDrawable() == null || colorStateList == null || !colorStateList.isStateful()) {
            return;
        }
        int[] drawableState = getDrawableState();
        int[] drawableState2 = checkableImageButton.getDrawableState();
        int length = drawableState.length;
        int[] iArrCopyOf = Arrays.copyOf(drawableState, drawableState.length + drawableState2.length);
        System.arraycopy(drawableState2, 0, iArrCopyOf, length, drawableState2.length);
        int colorForState = colorStateList.getColorForState(iArrCopyOf, colorStateList.getDefaultColor());
        Drawable drawableMutate = drawable.mutate();
        ya0.h(drawableMutate, ColorStateList.valueOf(colorForState));
        checkableImageButton.setImageDrawable(drawableMutate);
    }

    public final void m(TextView textView, int i) {
        try {
            textView.setTextAppearance(i);
            if (textView.getTextColors().getDefaultColor() != -65281) {
                return;
            }
        } catch (Exception unused) {
        }
        textView.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
        Context context = getContext();
        Object obj = sz.a;
        textView.setTextColor(pz.a(context, R.color.design_error));
    }

    public final void n(int i) {
        boolean z = this.t;
        int i2 = this.s;
        String string = null;
        if (i2 == -1) {
            this.u.setText(String.valueOf(i));
            this.u.setContentDescription(null);
            this.t = false;
        } else {
            this.t = i > i2;
            Context context = getContext();
            this.u.setContentDescription(context.getString(this.t ? R.string.character_counter_overflowed_content_description : R.string.character_counter_content_description, Integer.valueOf(i), Integer.valueOf(this.s)));
            if (z != this.t) {
                o();
            }
            String str = wl.d;
            Locale locale = Locale.getDefault();
            int i3 = zj2.a;
            wl wlVar = yj2.a(locale) == 1 ? wl.g : wl.f;
            fa faVar = this.u;
            String string2 = getContext().getString(R.string.character_counter_pattern, Integer.valueOf(i), Integer.valueOf(this.s));
            if (string2 == null) {
                wlVar.getClass();
            } else {
                string = wlVar.c(string2, wlVar.c).toString();
            }
            faVar.setText(string);
        }
        if (this.m == null || z == this.t) {
            return;
        }
        s(false, false);
        z();
        q();
    }

    public final void o() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        fa faVar = this.u;
        if (faVar != null) {
            m(faVar, this.t ? this.v : this.w);
            if (!this.t && (colorStateList2 = this.E) != null) {
                this.u.setTextColor(colorStateList2);
            }
            if (!this.t || (colorStateList = this.F) == null) {
                return;
            }
            this.u.setTextColor(colorStateList);
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        EditText editText = this.m;
        if (editText != null) {
            ThreadLocal threadLocal = i90.a;
            int width = editText.getWidth();
            int height = editText.getHeight();
            Rect rect = this.b0;
            rect.set(0, 0, width, height);
            ThreadLocal threadLocal2 = i90.a;
            Matrix matrix = (Matrix) threadLocal2.get();
            if (matrix == null) {
                matrix = new Matrix();
                threadLocal2.set(matrix);
            } else {
                matrix.reset();
            }
            i90.a(this, editText, matrix);
            ThreadLocal threadLocal3 = i90.b;
            RectF rectF = (RectF) threadLocal3.get();
            if (rectF == null) {
                rectF = new RectF();
                threadLocal3.set(rectF);
            }
            rectF.set(rect);
            matrix.mapRect(rectF);
            rect.set((int) (rectF.left + 0.5f), (int) (rectF.top + 0.5f), (int) (rectF.right + 0.5f), (int) (rectF.bottom + 0.5f));
            qf1 qf1Var = this.O;
            if (qf1Var != null) {
                int i5 = rect.bottom;
                qf1Var.setBounds(rect.left, i5 - this.V, rect.right, i5);
            }
            if (this.K) {
                float textSize = this.m.getTextSize();
                iu iuVar = this.P0;
                if (iuVar.i != textSize) {
                    iuVar.i = textSize;
                    iuVar.g();
                }
                int gravity = this.m.getGravity();
                int i6 = (gravity & (-113)) | 48;
                if (iuVar.h != i6) {
                    iuVar.h = i6;
                    iuVar.g();
                }
                if (iuVar.g != gravity) {
                    iuVar.g = gravity;
                    iuVar.g();
                }
                if (this.m == null) {
                    throw new IllegalStateException();
                }
                WeakHashMap weakHashMap = zt2.a;
                boolean z2 = jt2.d(this) == 1;
                int i7 = rect.bottom;
                Rect rect2 = this.c0;
                rect2.bottom = i7;
                int i8 = this.R;
                fa faVar = this.H;
                if (i8 == 1) {
                    int compoundPaddingLeft = this.m.getCompoundPaddingLeft() + rect.left;
                    if (this.G != null && !z2) {
                        compoundPaddingLeft = (compoundPaddingLeft - faVar.getMeasuredWidth()) + faVar.getPaddingLeft();
                    }
                    rect2.left = compoundPaddingLeft;
                    rect2.top = rect.top + this.S;
                    int compoundPaddingRight = rect.right - this.m.getCompoundPaddingRight();
                    if (this.G != null && z2) {
                        compoundPaddingRight += faVar.getMeasuredWidth() - faVar.getPaddingRight();
                    }
                    rect2.right = compoundPaddingRight;
                } else if (i8 != 2) {
                    int compoundPaddingLeft2 = this.m.getCompoundPaddingLeft() + rect.left;
                    if (this.G != null && !z2) {
                        compoundPaddingLeft2 = (compoundPaddingLeft2 - faVar.getMeasuredWidth()) + faVar.getPaddingLeft();
                    }
                    rect2.left = compoundPaddingLeft2;
                    rect2.top = getPaddingTop();
                    int compoundPaddingRight2 = rect.right - this.m.getCompoundPaddingRight();
                    if (this.G != null && z2) {
                        compoundPaddingRight2 += faVar.getMeasuredWidth() - faVar.getPaddingRight();
                    }
                    rect2.right = compoundPaddingRight2;
                } else {
                    rect2.left = this.m.getPaddingLeft() + rect.left;
                    rect2.top = rect.top - e();
                    rect2.right = rect.right - this.m.getPaddingRight();
                }
                int i9 = rect2.left;
                int i10 = rect2.top;
                int i11 = rect2.right;
                int i12 = rect2.bottom;
                Rect rect3 = iuVar.e;
                if (rect3.left != i9 || rect3.top != i10 || rect3.right != i11 || rect3.bottom != i12) {
                    rect3.set(i9, i10, i11, i12);
                    iuVar.D = true;
                    iuVar.f();
                }
                if (this.m == null) {
                    throw new IllegalStateException();
                }
                TextPaint textPaint = iuVar.F;
                textPaint.setTextSize(iuVar.i);
                textPaint.setTypeface(iuVar.t);
                textPaint.setLetterSpacing(0.0f);
                float f = -textPaint.ascent();
                rect2.left = this.m.getCompoundPaddingLeft() + rect.left;
                rect2.top = (this.R != 1 || this.m.getMinLines() > 1) ? rect.top + this.m.getCompoundPaddingTop() : (int) (rect.centerY() - (f / 2.0f));
                rect2.right = rect.right - this.m.getCompoundPaddingRight();
                int compoundPaddingBottom = (this.R != 1 || this.m.getMinLines() > 1) ? rect.bottom - this.m.getCompoundPaddingBottom() : (int) (rect2.top + f);
                rect2.bottom = compoundPaddingBottom;
                int i13 = rect2.left;
                int i14 = rect2.top;
                int i15 = rect2.right;
                Rect rect4 = iuVar.d;
                if (rect4.left != i13 || rect4.top != i14 || rect4.right != i15 || rect4.bottom != compoundPaddingBottom) {
                    rect4.set(i13, i14, i15, compoundPaddingBottom);
                    iuVar.D = true;
                    iuVar.f();
                }
                iuVar.g();
                if (!f() || this.O0) {
                    return;
                }
                i();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        EditText editText;
        int iMax;
        super.onMeasure(i, i2);
        int i3 = 1;
        boolean z = false;
        if (this.m != null && this.m.getMeasuredHeight() < (iMax = Math.max(this.k.getMeasuredHeight(), this.e.getMeasuredHeight()))) {
            this.m.setMinimumHeight(iMax);
            z = true;
        }
        boolean zP = p();
        if (z || zP) {
            this.m.post(new rj2(this, i3));
        }
        if (this.z != null && (editText = this.m) != null) {
            this.z.setGravity(editText.getGravity());
            this.z.setPadding(this.m.getCompoundPaddingLeft(), this.m.getCompoundPaddingTop(), this.m.getCompoundPaddingRight(), this.m.getCompoundPaddingBottom());
        }
        u();
        x();
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof vj2)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        vj2 vj2Var = (vj2) parcelable;
        super.onRestoreInstanceState(vj2Var.c);
        setError(vj2Var.k);
        if (vj2Var.l) {
            this.q0.post(new rj2(this, 0));
        }
        setHint(vj2Var.m);
        setHelperText(vj2Var.n);
        setPlaceholderText(vj2Var.o);
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        vj2 vj2Var = new vj2(super.onSaveInstanceState());
        if (this.q.e()) {
            vj2Var.k = getError();
        }
        vj2Var.l = this.o0 != 0 && this.q0.isChecked();
        vj2Var.m = getHint();
        vj2Var.n = getHelperText();
        vj2Var.o = getPlaceholderText();
        return vj2Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean p() {
        /*
            Method dump skipped, instruction units count: 279
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.p():boolean");
    }

    public final void q() {
        Drawable background;
        fa faVar;
        PorterDuffColorFilter porterDuffColorFilterG;
        PorterDuffColorFilter porterDuffColorFilterG2;
        EditText editText = this.m;
        if (editText == null || this.R != 0 || (background = editText.getBackground()) == null) {
            return;
        }
        int[] iArr = cb0.a;
        Drawable drawableMutate = background.mutate();
        l41 l41Var = this.q;
        if (l41Var.e()) {
            fa faVar2 = l41Var.l;
            int currentTextColor = faVar2 != null ? faVar2.getCurrentTextColor() : -1;
            PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
            PorterDuff.Mode mode2 = u8.b;
            synchronized (u8.class) {
                porterDuffColorFilterG2 = h42.g(currentTextColor, mode);
            }
            drawableMutate.setColorFilter(porterDuffColorFilterG2);
            return;
        }
        if (!this.t || (faVar = this.u) == null) {
            drawableMutate.clearColorFilter();
            this.m.refreshDrawableState();
            return;
        }
        int currentTextColor2 = faVar.getCurrentTextColor();
        PorterDuff.Mode mode3 = PorterDuff.Mode.SRC_IN;
        PorterDuff.Mode mode4 = u8.b;
        synchronized (u8.class) {
            porterDuffColorFilterG = h42.g(currentTextColor2, mode3);
        }
        drawableMutate.setColorFilter(porterDuffColorFilterG);
    }

    public final void r() {
        if (this.R != 1) {
            FrameLayout frameLayout = this.c;
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
            int iE = e();
            if (iE != layoutParams.topMargin) {
                layoutParams.topMargin = iE;
                frameLayout.requestLayout();
            }
        }
    }

    public final void s(boolean z, boolean z2) {
        ColorStateList colorStateList;
        fa faVar;
        boolean zIsEnabled = isEnabled();
        EditText editText = this.m;
        boolean z3 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.m;
        boolean z4 = editText2 != null && editText2.hasFocus();
        l41 l41Var = this.q;
        boolean zE = l41Var.e();
        ColorStateList colorStateList2 = this.D0;
        iu iuVar = this.P0;
        if (colorStateList2 != null) {
            iuVar.h(colorStateList2);
            ColorStateList colorStateList3 = this.D0;
            if (iuVar.k != colorStateList3) {
                iuVar.k = colorStateList3;
                iuVar.g();
            }
        }
        if (!zIsEnabled) {
            ColorStateList colorStateList4 = this.D0;
            int colorForState = colorStateList4 != null ? colorStateList4.getColorForState(new int[]{-16842910}, this.N0) : this.N0;
            iuVar.h(ColorStateList.valueOf(colorForState));
            ColorStateList colorStateListValueOf = ColorStateList.valueOf(colorForState);
            if (iuVar.k != colorStateListValueOf) {
                iuVar.k = colorStateListValueOf;
                iuVar.g();
            }
        } else if (zE) {
            fa faVar2 = l41Var.l;
            iuVar.h(faVar2 != null ? faVar2.getTextColors() : null);
        } else if (this.t && (faVar = this.u) != null) {
            iuVar.h(faVar.getTextColors());
        } else if (z4 && (colorStateList = this.E0) != null) {
            iuVar.h(colorStateList);
        }
        if (z3 || !this.Q0 || (isEnabled() && z4)) {
            if (z2 || this.O0) {
                ValueAnimator valueAnimator = this.S0;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.S0.cancel();
                }
                if (z && this.R0) {
                    a(1.0f);
                } else {
                    iuVar.i(1.0f);
                }
                this.O0 = false;
                if (f()) {
                    i();
                }
                EditText editText3 = this.m;
                t(editText3 != null ? editText3.getText().length() : 0);
                v();
                y();
                return;
            }
            return;
        }
        if (z2 || !this.O0) {
            ValueAnimator valueAnimator2 = this.S0;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.S0.cancel();
            }
            if (z && this.R0) {
                a(0.0f);
            } else {
                iuVar.i(0.0f);
            }
            if (f() && (!((c50) this.N).G.isEmpty()) && f()) {
                ((c50) this.N).o(0.0f, 0.0f, 0.0f, 0.0f);
            }
            this.O0 = true;
            fa faVar3 = this.z;
            if (faVar3 != null && this.y) {
                faVar3.setText((CharSequence) null);
                zn2.a(this.c, this.D);
                this.z.setVisibility(4);
            }
            v();
            y();
        }
    }

    public void setBoxBackgroundColor(int i) {
        if (this.a0 != i) {
            this.a0 = i;
            this.J0 = i;
            this.L0 = i;
            this.M0 = i;
            b();
        }
    }

    public void setBoxBackgroundColorResource(int i) {
        Context context = getContext();
        Object obj = sz.a;
        setBoxBackgroundColor(pz.a(context, i));
    }

    public void setBoxBackgroundColorStateList(ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.J0 = defaultColor;
        this.a0 = defaultColor;
        this.K0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
        this.L0 = colorStateList.getColorForState(new int[]{android.R.attr.state_focused, android.R.attr.state_enabled}, -1);
        this.M0 = colorStateList.getColorForState(new int[]{android.R.attr.state_hovered, android.R.attr.state_enabled}, -1);
        b();
    }

    public void setBoxBackgroundMode(int i) {
        if (i == this.R) {
            return;
        }
        this.R = i;
        if (this.m != null) {
            h();
        }
    }

    public void setBoxCollapsedPaddingTop(int i) {
        this.S = i;
    }

    public void setBoxStrokeColor(int i) {
        if (this.H0 != i) {
            this.H0 = i;
            z();
        }
    }

    public void setBoxStrokeColorStateList(ColorStateList colorStateList) {
        if (colorStateList.isStateful()) {
            this.F0 = colorStateList.getDefaultColor();
            this.N0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.G0 = colorStateList.getColorForState(new int[]{android.R.attr.state_hovered, android.R.attr.state_enabled}, -1);
            this.H0 = colorStateList.getColorForState(new int[]{android.R.attr.state_focused, android.R.attr.state_enabled}, -1);
        } else if (this.H0 != colorStateList.getDefaultColor()) {
            this.H0 = colorStateList.getDefaultColor();
        }
        z();
    }

    public void setBoxStrokeErrorColor(ColorStateList colorStateList) {
        if (this.I0 != colorStateList) {
            this.I0 = colorStateList;
            z();
        }
    }

    public void setBoxStrokeWidth(int i) {
        this.U = i;
        z();
    }

    public void setBoxStrokeWidthFocused(int i) {
        this.V = i;
        z();
    }

    public void setBoxStrokeWidthFocusedResource(int i) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(i));
    }

    public void setBoxStrokeWidthResource(int i) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(i));
    }

    public void setCounterEnabled(boolean z) {
        if (this.r != z) {
            l41 l41Var = this.q;
            if (z) {
                fa faVar = new fa(getContext(), null);
                this.u = faVar;
                faVar.setId(R.id.textinput_counter);
                Typeface typeface = this.e0;
                if (typeface != null) {
                    this.u.setTypeface(typeface);
                }
                this.u.setMaxLines(1);
                l41Var.a(this.u, 2);
                xe1.h((ViewGroup.MarginLayoutParams) this.u.getLayoutParams(), getResources().getDimensionPixelOffset(R.dimen.mtrl_textinput_counter_margin_start));
                o();
                if (this.u != null) {
                    EditText editText = this.m;
                    n(editText == null ? 0 : editText.getText().length());
                }
            } else {
                l41Var.h(this.u, 2);
                this.u = null;
            }
            this.r = z;
        }
    }

    public void setCounterMaxLength(int i) {
        if (this.s != i) {
            if (i > 0) {
                this.s = i;
            } else {
                this.s = -1;
            }
            if (!this.r || this.u == null) {
                return;
            }
            EditText editText = this.m;
            n(editText == null ? 0 : editText.getText().length());
        }
    }

    public void setCounterOverflowTextAppearance(int i) {
        if (this.v != i) {
            this.v = i;
            o();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList colorStateList) {
        if (this.F != colorStateList) {
            this.F = colorStateList;
            o();
        }
    }

    public void setCounterTextAppearance(int i) {
        if (this.w != i) {
            this.w = i;
            o();
        }
    }

    public void setCounterTextColor(ColorStateList colorStateList) {
        if (this.E != colorStateList) {
            this.E = colorStateList;
            o();
        }
    }

    public void setDefaultHintTextColor(ColorStateList colorStateList) {
        this.D0 = colorStateList;
        this.E0 = colorStateList;
        if (this.m != null) {
            s(false, false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        j(this, z);
        super.setEnabled(z);
    }

    public void setEndIconActivated(boolean z) {
        this.q0.setActivated(z);
    }

    public void setEndIconCheckable(boolean z) {
        this.q0.setCheckable(z);
    }

    public void setEndIconContentDescription(int i) {
        setEndIconContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    public void setEndIconDrawable(int i) {
        setEndIconDrawable(i != 0 ? l13.f(getContext(), i) : null);
    }

    public void setEndIconMode(int i) {
        int i2 = this.o0;
        this.o0 = i;
        Iterator it = this.r0.iterator();
        while (true) {
            if (!it.hasNext()) {
                setEndIconVisible(i != 0);
                if (!getEndIconDelegate().b(this.R)) {
                    throw new IllegalStateException("The current box background mode " + this.R + " is not supported by the end icon mode " + i);
                }
                getEndIconDelegate().a();
                c();
                return;
            }
            bt btVar = (bt) ((uj2) it.next());
            int i3 = btVar.a;
            be0 be0Var = btVar.b;
            switch (i3) {
                case 0:
                    EditText editText = getEditText();
                    if (editText != null && i2 == 2) {
                        editText.post(new yf1(btVar, 19, editText));
                        et etVar = (et) be0Var;
                        if (editText.getOnFocusChangeListener() == etVar.f) {
                            editText.setOnFocusChangeListener(null);
                        }
                        CheckableImageButton checkableImageButton = etVar.c;
                        if (checkableImageButton.getOnFocusChangeListener() == etVar.f) {
                            checkableImageButton.setOnFocusChangeListener(null);
                        }
                    }
                    break;
                case 1:
                    AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) getEditText();
                    if (autoCompleteTextView != null && i2 == 3) {
                        autoCompleteTextView.post(new yf1(btVar, 21, autoCompleteTextView));
                        if (autoCompleteTextView.getOnFocusChangeListener() == ((pb0) be0Var).f) {
                            autoCompleteTextView.setOnFocusChangeListener(null);
                        }
                        autoCompleteTextView.setOnTouchListener(null);
                        autoCompleteTextView.setOnDismissListener(null);
                    }
                    break;
                default:
                    EditText editText2 = getEditText();
                    if (editText2 != null && i2 == 1) {
                        editText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        editText2.post(new yf1(btVar, 22, editText2));
                    }
                    break;
            }
        }
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        View.OnLongClickListener onLongClickListener = this.z0;
        CheckableImageButton checkableImageButton = this.q0;
        checkableImageButton.setOnClickListener(onClickListener);
        l(checkableImageButton, onLongClickListener);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.z0 = onLongClickListener;
        CheckableImageButton checkableImageButton = this.q0;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        l(checkableImageButton, onLongClickListener);
    }

    public void setEndIconTintList(ColorStateList colorStateList) {
        if (this.s0 != colorStateList) {
            this.s0 = colorStateList;
            this.t0 = true;
            c();
        }
    }

    public void setEndIconTintMode(PorterDuff.Mode mode) {
        if (this.u0 != mode) {
            this.u0 = mode;
            this.v0 = true;
            c();
        }
    }

    public void setEndIconVisible(boolean z) {
        if (g() != z) {
            this.q0.setVisibility(z ? 0 : 8);
            x();
            p();
        }
    }

    public void setError(CharSequence charSequence) {
        l41 l41Var = this.q;
        if (!l41Var.k) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            l41Var.g();
            return;
        }
        l41Var.c();
        l41Var.j = charSequence;
        l41Var.l.setText(charSequence);
        int i = l41Var.h;
        if (i != 1) {
            l41Var.i = 1;
        }
        l41Var.j(i, l41Var.i, l41Var.i(l41Var.l, charSequence));
    }

    public void setErrorContentDescription(CharSequence charSequence) {
        l41 l41Var = this.q;
        l41Var.m = charSequence;
        fa faVar = l41Var.l;
        if (faVar != null) {
            faVar.setContentDescription(charSequence);
        }
    }

    public void setErrorEnabled(boolean z) {
        l41 l41Var = this.q;
        if (l41Var.k == z) {
            return;
        }
        l41Var.c();
        TextInputLayout textInputLayout = l41Var.b;
        if (z) {
            fa faVar = new fa(l41Var.a, null);
            l41Var.l = faVar;
            faVar.setId(R.id.textinput_error);
            l41Var.l.setTextAlignment(5);
            Typeface typeface = l41Var.u;
            if (typeface != null) {
                l41Var.l.setTypeface(typeface);
            }
            int i = l41Var.n;
            l41Var.n = i;
            fa faVar2 = l41Var.l;
            if (faVar2 != null) {
                textInputLayout.m(faVar2, i);
            }
            ColorStateList colorStateList = l41Var.o;
            l41Var.o = colorStateList;
            fa faVar3 = l41Var.l;
            if (faVar3 != null && colorStateList != null) {
                faVar3.setTextColor(colorStateList);
            }
            CharSequence charSequence = l41Var.m;
            l41Var.m = charSequence;
            fa faVar4 = l41Var.l;
            if (faVar4 != null) {
                faVar4.setContentDescription(charSequence);
            }
            l41Var.l.setVisibility(4);
            lt2.f(l41Var.l, 1);
            l41Var.a(l41Var.l, 0);
        } else {
            l41Var.g();
            l41Var.h(l41Var.l, 0);
            l41Var.l = null;
            textInputLayout.q();
            textInputLayout.z();
        }
        l41Var.k = z;
    }

    public void setErrorIconDrawable(int i) {
        setErrorIconDrawable(i != 0 ? l13.f(getContext(), i) : null);
        k(this.B0, this.C0);
    }

    public void setErrorIconOnClickListener(View.OnClickListener onClickListener) {
        View.OnLongClickListener onLongClickListener = this.A0;
        CheckableImageButton checkableImageButton = this.B0;
        checkableImageButton.setOnClickListener(onClickListener);
        l(checkableImageButton, onLongClickListener);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.A0 = onLongClickListener;
        CheckableImageButton checkableImageButton = this.B0;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        l(checkableImageButton, onLongClickListener);
    }

    public void setErrorIconTintList(ColorStateList colorStateList) {
        this.C0 = colorStateList;
        CheckableImageButton checkableImageButton = this.B0;
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null) {
            drawable = drawable.mutate();
            ya0.h(drawable, colorStateList);
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    public void setErrorIconTintMode(PorterDuff.Mode mode) {
        CheckableImageButton checkableImageButton = this.B0;
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null) {
            drawable = drawable.mutate();
            ya0.i(drawable, mode);
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    public void setErrorTextAppearance(int i) {
        l41 l41Var = this.q;
        l41Var.n = i;
        fa faVar = l41Var.l;
        if (faVar != null) {
            l41Var.b.m(faVar, i);
        }
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        l41 l41Var = this.q;
        l41Var.o = colorStateList;
        fa faVar = l41Var.l;
        if (faVar == null || colorStateList == null) {
            return;
        }
        faVar.setTextColor(colorStateList);
    }

    public void setExpandedHintEnabled(boolean z) {
        if (this.Q0 != z) {
            this.Q0 = z;
            s(false, false);
        }
    }

    public void setHelperText(CharSequence charSequence) {
        boolean zIsEmpty = TextUtils.isEmpty(charSequence);
        l41 l41Var = this.q;
        if (zIsEmpty) {
            if (l41Var.q) {
                setHelperTextEnabled(false);
                return;
            }
            return;
        }
        if (!l41Var.q) {
            setHelperTextEnabled(true);
        }
        l41Var.c();
        l41Var.f146p = charSequence;
        l41Var.r.setText(charSequence);
        int i = l41Var.h;
        if (i != 2) {
            l41Var.i = 2;
        }
        l41Var.j(i, l41Var.i, l41Var.i(l41Var.r, charSequence));
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        l41 l41Var = this.q;
        l41Var.t = colorStateList;
        fa faVar = l41Var.r;
        if (faVar == null || colorStateList == null) {
            return;
        }
        faVar.setTextColor(colorStateList);
    }

    public void setHelperTextEnabled(boolean z) {
        l41 l41Var = this.q;
        if (l41Var.q == z) {
            return;
        }
        l41Var.c();
        if (z) {
            fa faVar = new fa(l41Var.a, null);
            l41Var.r = faVar;
            faVar.setId(R.id.textinput_helper_text);
            l41Var.r.setTextAlignment(5);
            Typeface typeface = l41Var.u;
            if (typeface != null) {
                l41Var.r.setTypeface(typeface);
            }
            l41Var.r.setVisibility(4);
            lt2.f(l41Var.r, 1);
            int i = l41Var.s;
            l41Var.s = i;
            fa faVar2 = l41Var.r;
            if (faVar2 != null) {
                faVar2.setTextAppearance(i);
            }
            ColorStateList colorStateList = l41Var.t;
            l41Var.t = colorStateList;
            fa faVar3 = l41Var.r;
            if (faVar3 != null && colorStateList != null) {
                faVar3.setTextColor(colorStateList);
            }
            l41Var.a(l41Var.r, 1);
        } else {
            l41Var.c();
            int i2 = l41Var.h;
            if (i2 == 2) {
                l41Var.i = 0;
            }
            l41Var.j(i2, l41Var.i, l41Var.i(l41Var.r, null));
            l41Var.h(l41Var.r, 1);
            l41Var.r = null;
            TextInputLayout textInputLayout = l41Var.b;
            textInputLayout.q();
            textInputLayout.z();
        }
        l41Var.q = z;
    }

    public void setHelperTextTextAppearance(int i) {
        l41 l41Var = this.q;
        l41Var.s = i;
        fa faVar = l41Var.r;
        if (faVar != null) {
            faVar.setTextAppearance(i);
        }
    }

    public void setHint(CharSequence charSequence) {
        if (this.K) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z) {
        this.R0 = z;
    }

    public void setHintEnabled(boolean z) {
        if (z != this.K) {
            this.K = z;
            if (z) {
                CharSequence hint = this.m.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.L)) {
                        setHint(hint);
                    }
                    this.m.setHint((CharSequence) null);
                }
                this.M = true;
            } else {
                this.M = false;
                if (!TextUtils.isEmpty(this.L) && TextUtils.isEmpty(this.m.getHint())) {
                    this.m.setHint(this.L);
                }
                setHintInternal(null);
            }
            if (this.m != null) {
                r();
            }
        }
    }

    public void setHintTextAppearance(int i) {
        iu iuVar = this.P0;
        View view = iuVar.a;
        kj2 kj2Var = new kj2(view.getContext(), i);
        ColorStateList colorStateList = kj2Var.j;
        if (colorStateList != null) {
            iuVar.l = colorStateList;
        }
        float f = kj2Var.k;
        if (f != 0.0f) {
            iuVar.j = f;
        }
        ColorStateList colorStateList2 = kj2Var.a;
        if (colorStateList2 != null) {
            iuVar.L = colorStateList2;
        }
        iuVar.J = kj2Var.e;
        iuVar.K = kj2Var.f;
        iuVar.I = kj2Var.g;
        iuVar.M = kj2Var.i;
        wp wpVar = iuVar.v;
        if (wpVar != null) {
            wpVar.k = true;
        }
        hk0 hk0Var = new hk0(17, iuVar);
        kj2Var.a();
        iuVar.v = new wp(hk0Var, kj2Var.n);
        kj2Var.c(view.getContext(), iuVar.v);
        iuVar.g();
        this.E0 = iuVar.l;
        if (this.m != null) {
            s(false, false);
            r();
        }
    }

    public void setHintTextColor(ColorStateList colorStateList) {
        if (this.E0 != colorStateList) {
            if (this.D0 == null) {
                this.P0.h(colorStateList);
            }
            this.E0 = colorStateList;
            if (this.m != null) {
                s(false, false);
            }
        }
    }

    public void setMaxWidth(int i) {
        this.f35p = i;
        EditText editText = this.m;
        if (editText == null || i == -1) {
            return;
        }
        editText.setMaxWidth(i);
    }

    public void setMaxWidthResource(int i) {
        setMaxWidth(getContext().getResources().getDimensionPixelSize(i));
    }

    public void setMinWidth(int i) {
        this.o = i;
        EditText editText = this.m;
        if (editText == null || i == -1) {
            return;
        }
        editText.setMinWidth(i);
    }

    public void setMinWidthResource(int i) {
        setMinWidth(getContext().getResources().getDimensionPixelSize(i));
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(int i) {
        setPasswordVisibilityToggleContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int i) {
        setPasswordVisibilityToggleDrawable(i != 0 ? l13.f(getContext(), i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z) {
        if (z && this.o0 != 1) {
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
        this.s0 = colorStateList;
        this.t0 = true;
        c();
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        this.u0 = mode;
        this.v0 = true;
        c();
    }

    public void setPlaceholderText(CharSequence charSequence) {
        if (this.y && TextUtils.isEmpty(charSequence)) {
            setPlaceholderTextEnabled(false);
        } else {
            if (!this.y) {
                setPlaceholderTextEnabled(true);
            }
            this.x = charSequence;
        }
        EditText editText = this.m;
        t(editText != null ? editText.getText().length() : 0);
    }

    public void setPlaceholderTextAppearance(int i) {
        this.B = i;
        fa faVar = this.z;
        if (faVar != null) {
            faVar.setTextAppearance(i);
        }
    }

    public void setPlaceholderTextColor(ColorStateList colorStateList) {
        if (this.A != colorStateList) {
            this.A = colorStateList;
            fa faVar = this.z;
            if (faVar == null || colorStateList == null) {
                return;
            }
            faVar.setTextColor(colorStateList);
        }
    }

    public void setPrefixText(CharSequence charSequence) {
        this.G = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.H.setText(charSequence);
        v();
    }

    public void setPrefixTextAppearance(int i) {
        this.H.setTextAppearance(i);
    }

    public void setPrefixTextColor(ColorStateList colorStateList) {
        this.H.setTextColor(colorStateList);
    }

    public void setStartIconCheckable(boolean z) {
        this.f0.setCheckable(z);
    }

    public void setStartIconContentDescription(int i) {
        setStartIconContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    public void setStartIconDrawable(int i) {
        setStartIconDrawable(i != 0 ? l13.f(getContext(), i) : null);
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        View.OnLongClickListener onLongClickListener = this.m0;
        CheckableImageButton checkableImageButton = this.f0;
        checkableImageButton.setOnClickListener(onClickListener);
        l(checkableImageButton, onLongClickListener);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.m0 = onLongClickListener;
        CheckableImageButton checkableImageButton = this.f0;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        l(checkableImageButton, onLongClickListener);
    }

    public void setStartIconTintList(ColorStateList colorStateList) {
        if (this.g0 != colorStateList) {
            this.g0 = colorStateList;
            this.h0 = true;
            d(this.f0, true, colorStateList, this.j0, this.i0);
        }
    }

    public void setStartIconTintMode(PorterDuff.Mode mode) {
        if (this.i0 != mode) {
            this.i0 = mode;
            this.j0 = true;
            d(this.f0, this.h0, this.g0, true, mode);
        }
    }

    public void setStartIconVisible(boolean z) {
        CheckableImageButton checkableImageButton = this.f0;
        if ((checkableImageButton.getVisibility() == 0) != z) {
            checkableImageButton.setVisibility(z ? 0 : 8);
            u();
            p();
        }
    }

    public void setSuffixText(CharSequence charSequence) {
        this.I = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.J.setText(charSequence);
        y();
    }

    public void setSuffixTextAppearance(int i) {
        this.J.setTextAppearance(i);
    }

    public void setSuffixTextColor(ColorStateList colorStateList) {
        this.J.setTextColor(colorStateList);
    }

    public void setTextInputAccessibilityDelegate(sj2 sj2Var) {
        EditText editText = this.m;
        if (editText != null) {
            zt2.k(editText, sj2Var);
        }
    }

    public void setTypeface(Typeface typeface) {
        boolean z;
        if (typeface != this.e0) {
            this.e0 = typeface;
            iu iuVar = this.P0;
            wp wpVar = iuVar.v;
            boolean z2 = true;
            if (wpVar != null) {
                wpVar.k = true;
            }
            if (iuVar.s != typeface) {
                iuVar.s = typeface;
                z = true;
            } else {
                z = false;
            }
            if (iuVar.t != typeface) {
                iuVar.t = typeface;
            } else {
                z2 = false;
            }
            if (z || z2) {
                iuVar.g();
            }
            l41 l41Var = this.q;
            if (typeface != l41Var.u) {
                l41Var.u = typeface;
                fa faVar = l41Var.l;
                if (faVar != null) {
                    faVar.setTypeface(typeface);
                }
                fa faVar2 = l41Var.r;
                if (faVar2 != null) {
                    faVar2.setTypeface(typeface);
                }
            }
            fa faVar3 = this.u;
            if (faVar3 != null) {
                faVar3.setTypeface(typeface);
            }
        }
    }

    public final void t(int i) {
        FrameLayout frameLayout = this.c;
        if (i != 0 || this.O0) {
            fa faVar = this.z;
            if (faVar == null || !this.y) {
                return;
            }
            faVar.setText((CharSequence) null);
            zn2.a(frameLayout, this.D);
            this.z.setVisibility(4);
            return;
        }
        fa faVar2 = this.z;
        if (faVar2 == null || !this.y) {
            return;
        }
        faVar2.setText(this.x);
        zn2.a(frameLayout, this.C);
        this.z.setVisibility(0);
        this.z.bringToFront();
    }

    public final void u() {
        int iF;
        if (this.m == null) {
            return;
        }
        if (this.f0.getVisibility() == 0) {
            iF = 0;
        } else {
            EditText editText = this.m;
            WeakHashMap weakHashMap = zt2.a;
            iF = jt2.f(editText);
        }
        fa faVar = this.H;
        int compoundPaddingTop = this.m.getCompoundPaddingTop();
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.material_input_text_to_prefix_suffix_padding);
        int compoundPaddingBottom = this.m.getCompoundPaddingBottom();
        WeakHashMap weakHashMap2 = zt2.a;
        jt2.k(faVar, iF, compoundPaddingTop, dimensionPixelSize, compoundPaddingBottom);
    }

    public final void v() {
        this.H.setVisibility((this.G == null || this.O0) ? 8 : 0);
        p();
    }

    public final void w(boolean z, boolean z2) {
        int defaultColor = this.I0.getDefaultColor();
        int colorForState = this.I0.getColorForState(new int[]{android.R.attr.state_hovered, android.R.attr.state_enabled}, defaultColor);
        int colorForState2 = this.I0.getColorForState(new int[]{android.R.attr.state_activated, android.R.attr.state_enabled}, defaultColor);
        if (z) {
            this.W = colorForState2;
        } else if (z2) {
            this.W = colorForState;
        } else {
            this.W = defaultColor;
        }
    }

    public final void x() {
        int iE;
        if (this.m == null) {
            return;
        }
        if (g() || this.B0.getVisibility() == 0) {
            iE = 0;
        } else {
            EditText editText = this.m;
            WeakHashMap weakHashMap = zt2.a;
            iE = jt2.e(editText);
        }
        fa faVar = this.J;
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.material_input_text_to_prefix_suffix_padding);
        int paddingTop = this.m.getPaddingTop();
        int paddingBottom = this.m.getPaddingBottom();
        WeakHashMap weakHashMap2 = zt2.a;
        jt2.k(faVar, dimensionPixelSize, paddingTop, iE, paddingBottom);
    }

    public final void y() {
        fa faVar = this.J;
        int visibility = faVar.getVisibility();
        boolean z = (this.I == null || this.O0) ? false : true;
        faVar.setVisibility(z ? 0 : 8);
        if (visibility != faVar.getVisibility()) {
            getEndIconDelegate().c(z);
        }
        p();
    }

    public final void z() {
        fa faVar;
        EditText editText;
        EditText editText2;
        if (this.N == null || this.R == 0) {
            return;
        }
        boolean z = false;
        boolean z2 = isFocused() || ((editText2 = this.m) != null && editText2.hasFocus());
        boolean z3 = isHovered() || ((editText = this.m) != null && editText.isHovered());
        boolean zIsEnabled = isEnabled();
        l41 l41Var = this.q;
        if (!zIsEnabled) {
            this.W = this.N0;
        } else if (l41Var.e()) {
            if (this.I0 != null) {
                w(z2, z3);
            } else {
                fa faVar2 = l41Var.l;
                this.W = faVar2 != null ? faVar2.getCurrentTextColor() : -1;
            }
        } else if (!this.t || (faVar = this.u) == null) {
            if (z2) {
                this.W = this.H0;
            } else if (z3) {
                this.W = this.G0;
            } else {
                this.W = this.F0;
            }
        } else if (this.I0 != null) {
            w(z2, z3);
        } else {
            this.W = faVar.getCurrentTextColor();
        }
        if (getErrorIconDrawable() != null && l41Var.k && l41Var.e()) {
            z = true;
        }
        setErrorIconVisible(z);
        k(this.B0, this.C0);
        k(this.f0, this.g0);
        ColorStateList colorStateList = this.s0;
        CheckableImageButton checkableImageButton = this.q0;
        k(checkableImageButton, colorStateList);
        be0 endIconDelegate = getEndIconDelegate();
        endIconDelegate.getClass();
        if (endIconDelegate instanceof pb0) {
            if (!l41Var.e() || getEndIconDrawable() == null) {
                c();
            } else {
                Drawable drawableMutate = getEndIconDrawable().mutate();
                fa faVar3 = l41Var.l;
                ya0.g(drawableMutate, faVar3 != null ? faVar3.getCurrentTextColor() : -1);
                checkableImageButton.setImageDrawable(drawableMutate);
            }
        }
        int i = this.T;
        if (z2 && isEnabled()) {
            this.T = this.V;
        } else {
            this.T = this.U;
        }
        if (this.T != i && this.R == 2 && f() && !this.O0) {
            if (f()) {
                ((c50) this.N).o(0.0f, 0.0f, 0.0f, 0.0f);
            }
            i();
        }
        if (this.R == 1) {
            if (!isEnabled()) {
                this.a0 = this.K0;
            } else if (z3 && !z2) {
                this.a0 = this.M0;
            } else if (z2) {
                this.a0 = this.L0;
            } else {
                this.a0 = this.J0;
            }
        }
        b();
    }

    public void setEndIconContentDescription(CharSequence charSequence) {
        if (getEndIconContentDescription() != charSequence) {
            this.q0.setContentDescription(charSequence);
        }
    }

    public void setEndIconDrawable(Drawable drawable) {
        CheckableImageButton checkableImageButton = this.q0;
        checkableImageButton.setImageDrawable(drawable);
        if (drawable != null) {
            c();
            k(checkableImageButton, this.s0);
        }
    }

    public void setStartIconContentDescription(CharSequence charSequence) {
        if (getStartIconContentDescription() != charSequence) {
            this.f0.setContentDescription(charSequence);
        }
    }

    public void setStartIconDrawable(Drawable drawable) {
        CheckableImageButton checkableImageButton = this.f0;
        checkableImageButton.setImageDrawable(drawable);
        if (drawable != null) {
            d(checkableImageButton, this.h0, this.g0, this.j0, this.i0);
            setStartIconVisible(true);
            k(checkableImageButton, this.g0);
        } else {
            setStartIconVisible(false);
            setStartIconOnClickListener(null);
            setStartIconOnLongClickListener(null);
            setStartIconContentDescription((CharSequence) null);
        }
    }

    public void setErrorIconDrawable(Drawable drawable) {
        this.B0.setImageDrawable(drawable);
        setErrorIconVisible(drawable != null && this.q.k);
    }

    public void setHint(int i) {
        setHint(i != 0 ? getResources().getText(i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence charSequence) {
        this.q0.setContentDescription(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable drawable) {
        this.q0.setImageDrawable(drawable);
    }
}
