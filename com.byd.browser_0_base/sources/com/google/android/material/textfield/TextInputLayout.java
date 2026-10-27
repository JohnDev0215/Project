package com.google.android.material.textfield;

import android.R;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.LinearInterpolator;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.DrawableUtils;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import androidx.transition.Fade;
import com.google.android.material.R$attr;
import com.google.android.material.R$color;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.CheckableImageButton;
import defpackage.C0214v;
import defpackage.bz;
import defpackage.i83;
import defpackage.in3;
import defpackage.jr2;
import defpackage.l00;
import defpackage.me;
import defpackage.oj2;
import defpackage.om3;
import defpackage.ou;
import defpackage.p80;
import defpackage.pe0;
import defpackage.qz0;
import defpackage.r50;
import defpackage.r83;
import defpackage.rc1;
import defpackage.rw0;
import defpackage.rz0;
import defpackage.sc1;
import defpackage.u5;
import defpackage.uc1;
import defpackage.uj2;
import defpackage.yn3;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class TextInputLayout extends LinearLayout {
    public static final int C0 = R$style.Widget_Design_TextInputLayout;
    public static final int[][] D0 = {new int[]{R.attr.state_pressed}, new int[0]};
    public Fade A;
    public boolean A0;
    public Fade B;
    public boolean B0;
    public ColorStateList C;
    public ColorStateList D;
    public boolean E;
    public CharSequence F;
    public boolean G;
    public sc1 H;
    public sc1 I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public StateListDrawable f36J;
    public boolean K;
    public sc1 L;
    public sc1 M;
    public uj2 N;
    public boolean O;
    public final int P;
    public int Q;
    public int R;
    public int S;
    public int T;
    public int U;
    public int V;
    public int W;
    public final Rect a0;
    public final Rect b0;
    public final RectF c0;
    public Typeface d0;
    public final FrameLayout e;
    public ColorDrawable e0;
    public final jr2 f;
    public int f0;
    public final com.google.android.material.textfield.a g;
    public final LinkedHashSet<g> g0;
    public EditText h;
    public ColorDrawable h0;
    public CharSequence i;
    public int i0;
    public int j;
    public Drawable j0;
    public int k;
    public ColorStateList k0;
    public int l;
    public ColorStateList l0;
    public int m;
    public int m0;
    public final qz0 n;
    public int n0;
    public boolean o;
    public int o0;
    public int p;
    public ColorStateList p0;
    public boolean q;
    public int q0;
    public f r;
    public int r0;
    public AppCompatTextView s;
    public int s0;
    public int t;
    public int t0;
    public int u;
    public int u0;
    public CharSequence v;
    public boolean v0;
    public boolean w;
    public final ou w0;
    public AppCompatTextView x;
    public boolean x0;
    public ColorStateList y;
    public boolean y0;
    public int z;
    public ValueAnimator z0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public CharSequence g;
        public boolean h;
        public CharSequence i;
        public CharSequence j;
        public CharSequence k;

        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.ClassLoaderCreator
            public final SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public final String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.g) + " hint=" + ((Object) this.i) + " helperText=" + ((Object) this.j) + " placeholderText=" + ((Object) this.k) + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.e, i);
            TextUtils.writeToParcel(this.g, parcel, i);
            parcel.writeInt(this.h ? 1 : 0);
            TextUtils.writeToParcel(this.i, parcel, i);
            TextUtils.writeToParcel(this.j, parcel, i);
            TextUtils.writeToParcel(this.k, parcel, i);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.h = parcel.readInt() == 1;
            this.i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.j = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.k = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            CheckableImageButton checkableImageButton = TextInputLayout.this.g.k;
            checkableImageButton.performClick();
            checkableImageButton.jumpDrawablesToCurrentState();
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            TextInputLayout.this.h.requestLayout();
        }
    }

    public class d implements ValueAnimator.AnimatorUpdateListener {
        public d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            TextInputLayout.this.w0.p(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public static class e extends C0214v {
        public final TextInputLayout d;

        public e(TextInputLayout textInputLayout) {
            this.d = textInputLayout;
        }

        @Override // defpackage.C0214v
        public final void d(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            View.AccessibilityDelegate accessibilityDelegate = this.a;
            AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.a;
            accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            TextInputLayout textInputLayout = this.d;
            EditText editText = textInputLayout.getEditText();
            CharSequence text = editText != null ? editText.getText() : null;
            CharSequence hint = textInputLayout.getHint();
            CharSequence error = textInputLayout.getError();
            CharSequence placeholderText = textInputLayout.getPlaceholderText();
            int counterMaxLength = textInputLayout.getCounterMaxLength();
            CharSequence counterOverflowDescription = textInputLayout.getCounterOverflowDescription();
            boolean z = !TextUtils.isEmpty(text);
            boolean z2 = !TextUtils.isEmpty(hint);
            boolean z3 = !textInputLayout.v0;
            boolean z4 = !TextUtils.isEmpty(error);
            boolean z5 = z4 || !TextUtils.isEmpty(counterOverflowDescription);
            String string = z2 ? hint.toString() : "";
            jr2 jr2Var = textInputLayout.f;
            AppCompatTextView appCompatTextView = jr2Var.f;
            if (appCompatTextView.getVisibility() == 0) {
                accessibilityNodeInfo.setLabelFor(appCompatTextView);
                accessibilityNodeInfo.setTraversalAfter(appCompatTextView);
            } else {
                accessibilityNodeInfo.setTraversalAfter(jr2Var.h);
            }
            if (z) {
                accessibilityNodeInfoCompat.t(text);
            } else if (!TextUtils.isEmpty(string)) {
                accessibilityNodeInfoCompat.t(string);
                if (z3 && placeholderText != null) {
                    accessibilityNodeInfoCompat.t(string + ", " + ((Object) placeholderText));
                }
            } else if (placeholderText != null) {
                accessibilityNodeInfoCompat.t(placeholderText);
            }
            if (!TextUtils.isEmpty(string)) {
                accessibilityNodeInfo.setHintText(string);
                accessibilityNodeInfo.setShowingHintText(!z);
            }
            if (text == null || text.length() != counterMaxLength) {
                counterMaxLength = -1;
            }
            accessibilityNodeInfo.setMaxTextLength(counterMaxLength);
            if (z5) {
                if (!z4) {
                    error = counterOverflowDescription;
                }
                accessibilityNodeInfo.setError(error);
            }
            AppCompatTextView appCompatTextView2 = textInputLayout.n.r;
            if (appCompatTextView2 != null) {
                accessibilityNodeInfo.setLabelFor(appCompatTextView2);
            }
            textInputLayout.g.b().n(accessibilityNodeInfoCompat);
        }

        @Override // defpackage.C0214v
        public final void e(View view, AccessibilityEvent accessibilityEvent) {
            super.e(view, accessibilityEvent);
            this.d.g.b().o(accessibilityEvent);
        }
    }

    public interface f {
    }

    public interface g {
        void a(TextInputLayout textInputLayout);
    }

    public interface h {
        void a();
    }

    public TextInputLayout(Context context) {
        this(context, null);
    }

    private Drawable getEditTextBoxBackground() {
        EditText editText = this.h;
        if (editText instanceof AutoCompleteTextView) {
            if (!(editText.getInputType() != 0)) {
                int iP = oj2.p(this.h, R$attr.colorControlHighlight);
                int i = this.Q;
                int[][] iArr = D0;
                if (i != 2) {
                    if (i != 1) {
                        return null;
                    }
                    sc1 sc1Var = this.H;
                    int i2 = this.W;
                    return new RippleDrawable(new ColorStateList(iArr, new int[]{oj2.J(iP, i2, 0.1f), i2}), sc1Var, sc1Var);
                }
                Context context = getContext();
                sc1 sc1Var2 = this.H;
                int iN = oj2.n(R$attr.colorSurface, context, "TextInputLayout");
                sc1 sc1Var3 = new sc1(sc1Var2.e.a);
                int iJ = oj2.J(iP, iN, 0.1f);
                sc1Var3.n(new ColorStateList(iArr, new int[]{iJ, 0}));
                sc1Var3.setTint(iN);
                ColorStateList colorStateList = new ColorStateList(iArr, new int[]{iJ, iN});
                sc1 sc1Var4 = new sc1(sc1Var2.e.a);
                sc1Var4.setTint(-1);
                return new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, sc1Var3, sc1Var4), sc1Var2});
            }
        }
        return this.H;
    }

    private Drawable getOrCreateFilledDropDownMenuBackground() {
        if (this.f36J == null) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            this.f36J = stateListDrawable;
            stateListDrawable.addState(new int[]{R.attr.state_above_anchor}, getOrCreateOutlinedDropDownMenuBackground());
            this.f36J.addState(new int[0], f(false));
        }
        return this.f36J;
    }

    private Drawable getOrCreateOutlinedDropDownMenuBackground() {
        if (this.I == null) {
            this.I = f(true);
        }
        return this.I;
    }

    public static void k(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                k((ViewGroup) childAt, z);
            }
        }
    }

    private void setEditText(EditText editText) {
        if (this.h != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (getEndIconMode() != 3 && !(editText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.h = editText;
        int i = this.j;
        if (i != -1) {
            setMinEms(i);
        } else {
            setMinWidth(this.l);
        }
        int i2 = this.k;
        if (i2 != -1) {
            setMaxEms(i2);
        } else {
            setMaxWidth(this.m);
        }
        this.K = false;
        i();
        setTextInputAccessibilityDelegate(new e(this));
        Typeface typeface = this.h.getTypeface();
        ou ouVar = this.w0;
        boolean zM = ouVar.m(typeface);
        boolean zO = ouVar.o(typeface);
        if (zM || zO) {
            ouVar.i(false);
        }
        float textSize = this.h.getTextSize();
        if (ouVar.l != textSize) {
            ouVar.l = textSize;
            ouVar.i(false);
        }
        float letterSpacing = this.h.getLetterSpacing();
        if (ouVar.g0 != letterSpacing) {
            ouVar.g0 = letterSpacing;
            ouVar.i(false);
        }
        int gravity = this.h.getGravity();
        ouVar.l((gravity & (-113)) | 48);
        if (ouVar.j != gravity) {
            ouVar.j = gravity;
            ouVar.i(false);
        }
        this.h.addTextChangedListener(new a());
        if (this.k0 == null) {
            this.k0 = this.h.getHintTextColors();
        }
        if (this.E) {
            if (TextUtils.isEmpty(this.F)) {
                CharSequence hint = this.h.getHint();
                this.i = hint;
                setHint(hint);
                this.h.setHint((CharSequence) null);
            }
            this.G = true;
        }
        if (this.s != null) {
            n(this.h.getText());
        }
        q();
        this.n.b();
        this.f.bringToFront();
        com.google.android.material.textfield.a aVar = this.g;
        aVar.bringToFront();
        Iterator<g> it = this.g0.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
        aVar.l();
        if (!isEnabled()) {
            editText.setEnabled(false);
        }
        u(false, true);
    }

    private void setHintInternal(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.F)) {
            return;
        }
        this.F = charSequence;
        ou ouVar = this.w0;
        if (charSequence == null || !TextUtils.equals(ouVar.G, charSequence)) {
            ouVar.G = charSequence;
            ouVar.H = null;
            Bitmap bitmap = ouVar.K;
            if (bitmap != null) {
                bitmap.recycle();
                ouVar.K = null;
            }
            ouVar.i(false);
        }
        if (this.v0) {
            return;
        }
        j();
    }

    private void setPlaceholderTextEnabled(boolean z) {
        if (this.w == z) {
            return;
        }
        if (z) {
            AppCompatTextView appCompatTextView = this.x;
            if (appCompatTextView != null) {
                this.e.addView(appCompatTextView);
                this.x.setVisibility(0);
            }
        } else {
            AppCompatTextView appCompatTextView2 = this.x;
            if (appCompatTextView2 != null) {
                appCompatTextView2.setVisibility(8);
            }
            this.x = null;
        }
        this.w = z;
    }

    public final void a(float f2) {
        ou ouVar = this.w0;
        if (ouVar.b == f2) {
            return;
        }
        if (this.z0 == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.z0 = valueAnimator;
            valueAnimator.setInterpolator(u5.b);
            this.z0.setDuration(167L);
            this.z0.addUpdateListener(new d());
        }
        this.z0.setFloatValues(ouVar.b, f2);
        this.z0.start();
    }

    public void addOnEditTextAttachedListener(g gVar) {
        this.g0.add(gVar);
        if (this.h != null) {
            gVar.a(this);
        }
    }

    public void addOnEndIconChangedListener(h hVar) {
        this.g.addOnEndIconChangedListener(hVar);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
        FrameLayout frameLayout = this.e;
        frameLayout.addView(view, layoutParams2);
        frameLayout.setLayoutParams(layoutParams);
        s();
        setEditText((EditText) view);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b() {
        /*
            r7 = this;
            sc1 r0 = r7.H
            if (r0 != 0) goto L5
            return
        L5:
            sc1$b r1 = r0.e
            uj2 r1 = r1.a
            uj2 r2 = r7.N
            if (r1 == r2) goto L10
            r0.setShapeAppearanceModel(r2)
        L10:
            int r0 = r7.Q
            r1 = 2
            r2 = -1
            r3 = 0
            r4 = 1
            if (r0 != r1) goto L27
            int r0 = r7.S
            if (r0 <= r2) goto L22
            int r0 = r7.V
            if (r0 == 0) goto L22
            r0 = r4
            goto L23
        L22:
            r0 = r3
        L23:
            if (r0 == 0) goto L27
            r0 = r4
            goto L28
        L27:
            r0 = r3
        L28:
            if (r0 == 0) goto L3f
            sc1 r0 = r7.H
            int r1 = r7.S
            float r1 = (float) r1
            int r5 = r7.V
            sc1$b r6 = r0.e
            r6.k = r1
            r0.invalidateSelf()
            android.content.res.ColorStateList r1 = android.content.res.ColorStateList.valueOf(r5)
            r0.s(r1)
        L3f:
            int r0 = r7.W
            int r1 = r7.Q
            if (r1 != r4) goto L55
            int r0 = com.google.android.material.R$attr.colorSurface
            android.content.Context r1 = r7.getContext()
            int r0 = defpackage.oj2.o(r1, r0, r3)
            int r1 = r7.W
            int r0 = defpackage.gv.f(r1, r0)
        L55:
            r7.W = r0
            sc1 r1 = r7.H
            android.content.res.ColorStateList r0 = android.content.res.ColorStateList.valueOf(r0)
            r1.n(r0)
            sc1 r0 = r7.L
            if (r0 == 0) goto L9a
            sc1 r1 = r7.M
            if (r1 != 0) goto L69
            goto L9a
        L69:
            int r1 = r7.S
            if (r1 <= r2) goto L72
            int r1 = r7.V
            if (r1 == 0) goto L72
            r3 = r4
        L72:
            if (r3 == 0) goto L97
            android.widget.EditText r1 = r7.h
            boolean r1 = r1.isFocused()
            if (r1 == 0) goto L83
            int r1 = r7.m0
            android.content.res.ColorStateList r1 = android.content.res.ColorStateList.valueOf(r1)
            goto L89
        L83:
            int r1 = r7.V
            android.content.res.ColorStateList r1 = android.content.res.ColorStateList.valueOf(r1)
        L89:
            r0.n(r1)
            sc1 r0 = r7.M
            int r1 = r7.V
            android.content.res.ColorStateList r1 = android.content.res.ColorStateList.valueOf(r1)
            r0.n(r1)
        L97:
            r7.invalidate()
        L9a:
            r7.r()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.b():void");
    }

    public final int c() {
        float fE;
        if (!this.E) {
            return 0;
        }
        int i = this.Q;
        ou ouVar = this.w0;
        if (i == 0) {
            fE = ouVar.e();
        } else {
            if (i != 2) {
                return 0;
            }
            fE = ouVar.e() / 2.0f;
        }
        return (int) fE;
    }

    public final boolean d() {
        return this.E && !TextUtils.isEmpty(this.F) && (this.H instanceof r50);
    }

    @Override // android.view.ViewGroup, android.view.View
    @TargetApi(26)
    public final void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i) {
        EditText editText = this.h;
        if (editText == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i);
            return;
        }
        if (this.i != null) {
            boolean z = this.G;
            this.G = false;
            CharSequence hint = editText.getHint();
            this.h.setHint(this.i);
            try {
                super.dispatchProvideAutofillStructure(viewStructure, i);
                return;
            } finally {
                this.h.setHint(hint);
                this.G = z;
            }
        }
        viewStructure.setAutofillId(getAutofillId());
        onProvideAutofillStructure(viewStructure, i);
        onProvideAutofillVirtualStructure(viewStructure, i);
        FrameLayout frameLayout = this.e;
        viewStructure.setChildCount(frameLayout.getChildCount());
        for (int i2 = 0; i2 < frameLayout.getChildCount(); i2++) {
            View childAt = frameLayout.getChildAt(i2);
            ViewStructure viewStructureNewChild = viewStructure.newChild(i2);
            childAt.dispatchProvideAutofillStructure(viewStructureNewChild, i);
            if (childAt == this.h) {
                viewStructureNewChild.setHint(getHint());
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        this.B0 = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.B0 = false;
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        sc1 sc1Var;
        super.draw(canvas);
        boolean z = this.E;
        ou ouVar = this.w0;
        if (z) {
            ouVar.d(canvas);
        }
        if (this.M == null || (sc1Var = this.L) == null) {
            return;
        }
        sc1Var.draw(canvas);
        if (this.h.isFocused()) {
            Rect bounds = this.M.getBounds();
            Rect bounds2 = this.L.getBounds();
            float f2 = ouVar.b;
            int iCenterX = bounds2.centerX();
            bounds.left = u5.b(iCenterX, bounds2.left, f2);
            bounds.right = u5.b(iCenterX, bounds2.right, f2);
            this.M.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        if (this.A0) {
            return;
        }
        this.A0 = true;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        ou ouVar = this.w0;
        boolean zR = ouVar != null ? ouVar.r(drawableState) | false : false;
        if (this.h != null) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            t(om3.g.c(this) && isEnabled());
        }
        q();
        x();
        if (zR) {
            invalidate();
        }
        this.A0 = false;
    }

    public final sc1 f(boolean z) {
        float dimensionPixelOffset = getResources().getDimensionPixelOffset(R$dimen.mtrl_shape_corner_size_small_component);
        float f2 = z ? dimensionPixelOffset : 0.0f;
        EditText editText = this.h;
        float popupElevation = editText instanceof MaterialAutoCompleteTextView ? ((MaterialAutoCompleteTextView) editText).getPopupElevation() : getResources().getDimensionPixelOffset(R$dimen.mtrl_exposed_dropdown_menu_popup_elevation);
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R$dimen.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        uj2.a aVar = new uj2.a();
        aVar.f(f2);
        aVar.g(f2);
        aVar.d(dimensionPixelOffset);
        aVar.e(dimensionPixelOffset);
        uj2 uj2Var = new uj2(aVar);
        Context context = getContext();
        Paint paint = sc1.A;
        int iN = oj2.n(R$attr.colorSurface, context, sc1.class.getSimpleName());
        sc1 sc1Var = new sc1();
        sc1Var.k(context);
        sc1Var.n(ColorStateList.valueOf(iN));
        sc1Var.m(popupElevation);
        sc1Var.setShapeAppearanceModel(uj2Var);
        sc1.b bVar = sc1Var.e;
        if (bVar.h == null) {
            bVar.h = new Rect();
        }
        sc1Var.e.h.set(0, dimensionPixelOffset2, 0, dimensionPixelOffset2);
        sc1Var.invalidateSelf();
        return sc1Var;
    }

    public final int g(int i, boolean z) {
        int compoundPaddingLeft = this.h.getCompoundPaddingLeft() + i;
        return (getPrefixText() == null || z) ? compoundPaddingLeft : (compoundPaddingLeft - getPrefixTextView().getMeasuredWidth()) + getPrefixTextView().getPaddingLeft();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.h;
        if (editText == null) {
            return super.getBaseline();
        }
        return c() + getPaddingTop() + editText.getBaseline();
    }

    public sc1 getBoxBackground() {
        int i = this.Q;
        if (i == 1 || i == 2) {
            return this.H;
        }
        throw new IllegalStateException();
    }

    public int getBoxBackgroundColor() {
        return this.W;
    }

    public int getBoxBackgroundMode() {
        return this.Q;
    }

    public int getBoxCollapsedPaddingTop() {
        return this.R;
    }

    public float getBoxCornerRadiusBottomEnd() {
        boolean zD = yn3.d(this);
        RectF rectF = this.c0;
        return zD ? this.N.h.a(rectF) : this.N.g.a(rectF);
    }

    public float getBoxCornerRadiusBottomStart() {
        boolean zD = yn3.d(this);
        RectF rectF = this.c0;
        return zD ? this.N.g.a(rectF) : this.N.h.a(rectF);
    }

    public float getBoxCornerRadiusTopEnd() {
        boolean zD = yn3.d(this);
        RectF rectF = this.c0;
        return zD ? this.N.e.a(rectF) : this.N.f.a(rectF);
    }

    public float getBoxCornerRadiusTopStart() {
        boolean zD = yn3.d(this);
        RectF rectF = this.c0;
        return zD ? this.N.f.a(rectF) : this.N.e.a(rectF);
    }

    public int getBoxStrokeColor() {
        return this.o0;
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.p0;
    }

    public int getBoxStrokeWidth() {
        return this.T;
    }

    public int getBoxStrokeWidthFocused() {
        return this.U;
    }

    public int getCounterMaxLength() {
        return this.p;
    }

    public CharSequence getCounterOverflowDescription() {
        AppCompatTextView appCompatTextView;
        if (this.o && this.q && (appCompatTextView = this.s) != null) {
            return appCompatTextView.getContentDescription();
        }
        return null;
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.C;
    }

    public ColorStateList getCounterTextColor() {
        return this.C;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.k0;
    }

    public EditText getEditText() {
        return this.h;
    }

    public CharSequence getEndIconContentDescription() {
        return this.g.k.getContentDescription();
    }

    public Drawable getEndIconDrawable() {
        return this.g.k.getDrawable();
    }

    public int getEndIconMode() {
        return this.g.m;
    }

    public CheckableImageButton getEndIconView() {
        return this.g.k;
    }

    public CharSequence getError() {
        qz0 qz0Var = this.n;
        if (qz0Var.k) {
            return qz0Var.j;
        }
        return null;
    }

    public CharSequence getErrorContentDescription() {
        return this.n.m;
    }

    public int getErrorCurrentTextColors() {
        AppCompatTextView appCompatTextView = this.n.l;
        if (appCompatTextView != null) {
            return appCompatTextView.getCurrentTextColor();
        }
        return -1;
    }

    public Drawable getErrorIconDrawable() {
        return this.g.g.getDrawable();
    }

    public CharSequence getHelperText() {
        qz0 qz0Var = this.n;
        if (qz0Var.q) {
            return qz0Var.p;
        }
        return null;
    }

    public int getHelperTextCurrentTextColor() {
        AppCompatTextView appCompatTextView = this.n.r;
        if (appCompatTextView != null) {
            return appCompatTextView.getCurrentTextColor();
        }
        return -1;
    }

    public CharSequence getHint() {
        if (this.E) {
            return this.F;
        }
        return null;
    }

    public final float getHintCollapsedTextHeight() {
        return this.w0.e();
    }

    public final int getHintCurrentCollapsedTextColor() {
        ou ouVar = this.w0;
        return ouVar.f(ouVar.o);
    }

    public ColorStateList getHintTextColor() {
        return this.l0;
    }

    public f getLengthCounter() {
        return this.r;
    }

    public int getMaxEms() {
        return this.k;
    }

    public int getMaxWidth() {
        return this.m;
    }

    public int getMinEms() {
        return this.j;
    }

    public int getMinWidth() {
        return this.l;
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.g.k.getContentDescription();
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.g.k.getDrawable();
    }

    public CharSequence getPlaceholderText() {
        if (this.w) {
            return this.v;
        }
        return null;
    }

    public int getPlaceholderTextAppearance() {
        return this.z;
    }

    public ColorStateList getPlaceholderTextColor() {
        return this.y;
    }

    public CharSequence getPrefixText() {
        return this.f.g;
    }

    public ColorStateList getPrefixTextColor() {
        return this.f.f.getTextColors();
    }

    public TextView getPrefixTextView() {
        return this.f.f;
    }

    public CharSequence getStartIconContentDescription() {
        return this.f.h.getContentDescription();
    }

    public Drawable getStartIconDrawable() {
        return this.f.h.getDrawable();
    }

    public CharSequence getSuffixText() {
        return this.g.r;
    }

    public ColorStateList getSuffixTextColor() {
        return this.g.s.getTextColors();
    }

    public TextView getSuffixTextView() {
        return this.g.s;
    }

    public Typeface getTypeface() {
        return this.d0;
    }

    public final int h(int i, boolean z) {
        int compoundPaddingRight = i - this.h.getCompoundPaddingRight();
        return (getPrefixText() == null || !z) ? compoundPaddingRight : compoundPaddingRight + (getPrefixTextView().getMeasuredWidth() - getPrefixTextView().getPaddingRight());
    }

    public final void i() {
        int i = this.Q;
        if (i == 0) {
            this.H = null;
            this.L = null;
            this.M = null;
        } else if (i == 1) {
            this.H = new sc1(this.N);
            this.L = new sc1();
            this.M = new sc1();
        } else {
            if (i != 2) {
                throw new IllegalArgumentException(this.Q + " is illegal; only @BoxBackgroundMode constants are supported.");
            }
            if (!this.E || (this.H instanceof r50)) {
                this.H = new sc1(this.N);
            } else {
                this.H = new r50(this.N);
            }
            this.L = null;
            this.M = null;
        }
        r();
        x();
        if (this.Q == 1) {
            if (getContext().getResources().getConfiguration().fontScale >= 2.0f) {
                this.R = getResources().getDimensionPixelSize(R$dimen.material_font_2_0_box_collapsed_padding_top);
            } else if (rc1.e(getContext())) {
                this.R = getResources().getDimensionPixelSize(R$dimen.material_font_1_3_box_collapsed_padding_top);
            }
        }
        if (this.h != null && this.Q == 1) {
            if (getContext().getResources().getConfiguration().fontScale >= 2.0f) {
                EditText editText = this.h;
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.e.i(editText, om3.e.e(editText), getResources().getDimensionPixelSize(R$dimen.material_filled_edittext_font_2_0_padding_top), om3.e.d(this.h), getResources().getDimensionPixelSize(R$dimen.material_filled_edittext_font_2_0_padding_bottom));
            } else if (rc1.e(getContext())) {
                EditText editText2 = this.h;
                WeakHashMap<View, in3> weakHashMap2 = om3.a;
                om3.e.i(editText2, om3.e.e(editText2), getResources().getDimensionPixelSize(R$dimen.material_filled_edittext_font_1_3_padding_top), om3.e.d(this.h), getResources().getDimensionPixelSize(R$dimen.material_filled_edittext_font_1_3_padding_bottom));
            }
        }
        if (this.Q != 0) {
            s();
        }
        EditText editText3 = this.h;
        if (editText3 instanceof AutoCompleteTextView) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText3;
            if (autoCompleteTextView.getDropDownBackground() == null) {
                int i2 = this.Q;
                if (i2 == 2) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(getOrCreateOutlinedDropDownMenuBackground());
                } else if (i2 == 1) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(getOrCreateFilledDropDownMenuBackground());
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void j() {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.j():void");
    }

    public final void l(TextView textView, int i) {
        boolean z = true;
        try {
            textView.setTextAppearance(i);
            if (textView.getTextColors().getDefaultColor() != -65281) {
                z = false;
            }
        } catch (Exception unused) {
        }
        if (z) {
            textView.setTextAppearance(R$style.TextAppearance_AppCompat_Caption);
            Context context = getContext();
            int i2 = R$color.design_error;
            Object obj = bz.a;
            textView.setTextColor(context.getColor(i2));
        }
    }

    public final boolean m() {
        qz0 qz0Var = this.n;
        return (qz0Var.i != 1 || qz0Var.l == null || TextUtils.isEmpty(qz0Var.j)) ? false : true;
    }

    public final void n(Editable editable) {
        ((i83) this.r).getClass();
        int length = editable != null ? editable.length() : 0;
        boolean z = this.q;
        int i = this.p;
        String string = null;
        if (i == -1) {
            this.s.setText(String.valueOf(length));
            this.s.setContentDescription(null);
            this.q = false;
        } else {
            this.q = length > i;
            Context context = getContext();
            this.s.setContentDescription(context.getString(this.q ? R$string.character_counter_overflowed_content_description : R$string.character_counter_content_description, Integer.valueOf(length), Integer.valueOf(this.p)));
            if (z != this.q) {
                o();
            }
            me meVarC = me.c();
            AppCompatTextView appCompatTextView = this.s;
            String string2 = getContext().getString(R$string.character_counter_pattern, Integer.valueOf(length), Integer.valueOf(this.p));
            if (string2 == null) {
                meVarC.getClass();
            } else {
                string = meVarC.d(string2, meVarC.c).toString();
            }
            appCompatTextView.setText(string);
        }
        if (this.h == null || z == this.q) {
            return;
        }
        t(false);
        x();
        q();
    }

    public final void o() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        AppCompatTextView appCompatTextView = this.s;
        if (appCompatTextView != null) {
            l(appCompatTextView, this.q ? this.t : this.u);
            if (!this.q && (colorStateList2 = this.C) != null) {
                this.s.setTextColor(colorStateList2);
            }
            if (!this.q || (colorStateList = this.D) == null) {
                return;
            }
            this.s.setTextColor(colorStateList);
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.w0.h(configuration);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        EditText editText = this.h;
        if (editText != null) {
            Rect rect = this.a0;
            p80.a(this, editText, rect);
            sc1 sc1Var = this.L;
            if (sc1Var != null) {
                int i5 = rect.bottom;
                sc1Var.setBounds(rect.left, i5 - this.T, rect.right, i5);
            }
            sc1 sc1Var2 = this.M;
            if (sc1Var2 != null) {
                int i6 = rect.bottom;
                sc1Var2.setBounds(rect.left, i6 - this.U, rect.right, i6);
            }
            if (this.E) {
                float textSize = this.h.getTextSize();
                ou ouVar = this.w0;
                if (ouVar.l != textSize) {
                    ouVar.l = textSize;
                    ouVar.i(false);
                }
                int gravity = this.h.getGravity();
                ouVar.l((gravity & (-113)) | 48);
                if (ouVar.j != gravity) {
                    ouVar.j = gravity;
                    ouVar.i(false);
                }
                if (this.h == null) {
                    throw new IllegalStateException();
                }
                boolean zD = yn3.d(this);
                int i7 = rect.bottom;
                Rect rect2 = this.b0;
                rect2.bottom = i7;
                int i8 = this.Q;
                if (i8 == 1) {
                    rect2.left = g(rect.left, zD);
                    rect2.top = rect.top + this.R;
                    rect2.right = h(rect.right, zD);
                } else if (i8 != 2) {
                    rect2.left = g(rect.left, zD);
                    rect2.top = getPaddingTop();
                    rect2.right = h(rect.right, zD);
                } else {
                    rect2.left = this.h.getPaddingLeft() + rect.left;
                    rect2.top = rect.top - c();
                    rect2.right = rect.right - this.h.getPaddingRight();
                }
                int i9 = rect2.left;
                int i10 = rect2.top;
                int i11 = rect2.right;
                int i12 = rect2.bottom;
                Rect rect3 = ouVar.h;
                if (!(rect3.left == i9 && rect3.top == i10 && rect3.right == i11 && rect3.bottom == i12)) {
                    rect3.set(i9, i10, i11, i12);
                    ouVar.S = true;
                }
                if (this.h == null) {
                    throw new IllegalStateException();
                }
                TextPaint textPaint = ouVar.U;
                textPaint.setTextSize(ouVar.l);
                textPaint.setTypeface(ouVar.z);
                textPaint.setLetterSpacing(ouVar.g0);
                float f2 = -textPaint.ascent();
                rect2.left = this.h.getCompoundPaddingLeft() + rect.left;
                rect2.top = this.Q == 1 && this.h.getMinLines() <= 1 ? (int) (rect.centerY() - (f2 / 2.0f)) : rect.top + this.h.getCompoundPaddingTop();
                rect2.right = rect.right - this.h.getCompoundPaddingRight();
                int compoundPaddingBottom = this.Q == 1 && this.h.getMinLines() <= 1 ? (int) (rect2.top + f2) : rect.bottom - this.h.getCompoundPaddingBottom();
                rect2.bottom = compoundPaddingBottom;
                int i13 = rect2.left;
                int i14 = rect2.top;
                int i15 = rect2.right;
                Rect rect4 = ouVar.g;
                if (!(rect4.left == i13 && rect4.top == i14 && rect4.right == i15 && rect4.bottom == compoundPaddingBottom)) {
                    rect4.set(i13, i14, i15, compoundPaddingBottom);
                    ouVar.S = true;
                }
                ouVar.i(false);
                if (!d() || this.v0) {
                    return;
                }
                j();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        boolean z;
        EditText editText;
        int iMax;
        super.onMeasure(i, i2);
        EditText editText2 = this.h;
        com.google.android.material.textfield.a aVar = this.g;
        if (editText2 != null && this.h.getMeasuredHeight() < (iMax = Math.max(aVar.getMeasuredHeight(), this.f.getMeasuredHeight()))) {
            this.h.setMinimumHeight(iMax);
            z = true;
        } else {
            z = false;
        }
        boolean zP = p();
        if (z || zP) {
            this.h.post(new c());
        }
        if (this.x != null && (editText = this.h) != null) {
            this.x.setGravity(editText.getGravity());
            this.x.setPadding(this.h.getCompoundPaddingLeft(), this.h.getCompoundPaddingTop(), this.h.getCompoundPaddingRight(), this.h.getCompoundPaddingBottom());
        }
        aVar.l();
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.e);
        setError(savedState.g);
        if (savedState.h) {
            post(new b());
        }
        setHint(savedState.i);
        setHelperText(savedState.j);
        setPlaceholderText(savedState.k);
        requestLayout();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        boolean z = false;
        boolean z2 = i == 1;
        boolean z3 = this.O;
        if (z2 != z3) {
            if (z2 && !z3) {
                z = true;
            }
            l00 l00Var = this.N.e;
            RectF rectF = this.c0;
            float fA = l00Var.a(rectF);
            float fA2 = this.N.f.a(rectF);
            float fA3 = this.N.h.a(rectF);
            float fA4 = this.N.g.a(rectF);
            float f2 = z ? fA : fA2;
            if (z) {
                fA = fA2;
            }
            float f3 = z ? fA3 : fA4;
            if (z) {
                fA3 = fA4;
            }
            boolean zD = yn3.d(this);
            this.O = zD;
            float f4 = zD ? fA : f2;
            if (!zD) {
                f2 = fA;
            }
            float f5 = zD ? fA3 : f3;
            if (!zD) {
                f3 = fA3;
            }
            sc1 sc1Var = this.H;
            if (sc1Var != null && sc1Var.j() == f4) {
                sc1 sc1Var2 = this.H;
                if (sc1Var2.e.a.f.a(sc1Var2.h()) == f2) {
                    sc1 sc1Var3 = this.H;
                    if (sc1Var3.e.a.h.a(sc1Var3.h()) == f5) {
                        sc1 sc1Var4 = this.H;
                        if (sc1Var4.e.a.g.a(sc1Var4.h()) == f3) {
                            return;
                        }
                    }
                }
            }
            uj2 uj2Var = this.N;
            uj2Var.getClass();
            uj2.a aVar = new uj2.a(uj2Var);
            aVar.f(f4);
            aVar.g(f2);
            aVar.d(f5);
            aVar.e(f3);
            this.N = new uj2(aVar);
            b();
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (m()) {
            savedState.g = getError();
        }
        com.google.android.material.textfield.a aVar = this.g;
        savedState.h = (aVar.m != 0) && aVar.k.isChecked();
        savedState.i = getHint();
        savedState.j = getHelperText();
        savedState.k = getPlaceholderText();
        return savedState;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean p() {
        /*
            Method dump skipped, instruction units count: 326
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.p():boolean");
    }

    public void q() {
        Drawable background;
        AppCompatTextView appCompatTextView;
        EditText editText = this.h;
        if (editText == null || this.Q != 0 || (background = editText.getBackground()) == null) {
            return;
        }
        int[] iArr = DrawableUtils.a;
        Drawable drawableMutate = background.mutate();
        if (m()) {
            drawableMutate.setColorFilter(AppCompatDrawableManager.c(getErrorCurrentTextColors(), PorterDuff.Mode.SRC_IN));
        } else if (this.q && (appCompatTextView = this.s) != null) {
            drawableMutate.setColorFilter(AppCompatDrawableManager.c(appCompatTextView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            drawableMutate.clearColorFilter();
            this.h.refreshDrawableState();
        }
    }

    public final void r() {
        EditText editText = this.h;
        if (editText == null || this.H == null) {
            return;
        }
        if ((this.K || editText.getBackground() == null) && this.Q != 0) {
            EditText editText2 = this.h;
            Drawable editTextBoxBackground = getEditTextBoxBackground();
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.n(editText2, editTextBoxBackground);
            this.K = true;
        }
    }

    public void removeOnEditTextAttachedListener(g gVar) {
        this.g0.remove(gVar);
    }

    public void removeOnEndIconChangedListener(h hVar) {
        this.g.removeOnEndIconChangedListener(hVar);
    }

    public final void s() {
        if (this.Q != 1) {
            FrameLayout frameLayout = this.e;
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
            int iC = c();
            if (iC != layoutParams.topMargin) {
                layoutParams.topMargin = iC;
                frameLayout.requestLayout();
            }
        }
    }

    public void setBoxBackgroundColor(int i) {
        if (this.W != i) {
            this.W = i;
            this.q0 = i;
            this.s0 = i;
            this.t0 = i;
            b();
        }
    }

    public void setBoxBackgroundColorResource(int i) {
        Context context = getContext();
        Object obj = bz.a;
        setBoxBackgroundColor(context.getColor(i));
    }

    public void setBoxBackgroundColorStateList(ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.q0 = defaultColor;
        this.W = defaultColor;
        this.r0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
        this.s0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        this.t0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
        b();
    }

    public void setBoxBackgroundMode(int i) {
        if (i == this.Q) {
            return;
        }
        this.Q = i;
        if (this.h != null) {
            i();
        }
    }

    public void setBoxCollapsedPaddingTop(int i) {
        this.R = i;
    }

    public void setBoxStrokeColor(int i) {
        if (this.o0 != i) {
            this.o0 = i;
            x();
        }
    }

    public void setBoxStrokeColorStateList(ColorStateList colorStateList) {
        if (colorStateList.isStateful()) {
            this.m0 = colorStateList.getDefaultColor();
            this.u0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.n0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            this.o0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        } else if (this.o0 != colorStateList.getDefaultColor()) {
            this.o0 = colorStateList.getDefaultColor();
        }
        x();
    }

    public void setBoxStrokeErrorColor(ColorStateList colorStateList) {
        if (this.p0 != colorStateList) {
            this.p0 = colorStateList;
            x();
        }
    }

    public void setBoxStrokeWidth(int i) {
        this.T = i;
        x();
    }

    public void setBoxStrokeWidthFocused(int i) {
        this.U = i;
        x();
    }

    public void setBoxStrokeWidthFocusedResource(int i) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(i));
    }

    public void setBoxStrokeWidthResource(int i) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(i));
    }

    public void setCounterEnabled(boolean z) {
        if (this.o != z) {
            qz0 qz0Var = this.n;
            if (z) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
                this.s = appCompatTextView;
                appCompatTextView.setId(R$id.textinput_counter);
                Typeface typeface = this.d0;
                if (typeface != null) {
                    this.s.setTypeface(typeface);
                }
                this.s.setMaxLines(1);
                qz0Var.a(this.s, 2);
                ((ViewGroup.MarginLayoutParams) this.s.getLayoutParams()).setMarginStart(getResources().getDimensionPixelOffset(R$dimen.mtrl_textinput_counter_margin_start));
                o();
                if (this.s != null) {
                    EditText editText = this.h;
                    n(editText != null ? editText.getText() : null);
                }
            } else {
                qz0Var.g(this.s, 2);
                this.s = null;
            }
            this.o = z;
        }
    }

    public void setCounterMaxLength(int i) {
        if (this.p != i) {
            if (i > 0) {
                this.p = i;
            } else {
                this.p = -1;
            }
            if (!this.o || this.s == null) {
                return;
            }
            EditText editText = this.h;
            n(editText == null ? null : editText.getText());
        }
    }

    public void setCounterOverflowTextAppearance(int i) {
        if (this.t != i) {
            this.t = i;
            o();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList colorStateList) {
        if (this.D != colorStateList) {
            this.D = colorStateList;
            o();
        }
    }

    public void setCounterTextAppearance(int i) {
        if (this.u != i) {
            this.u = i;
            o();
        }
    }

    public void setCounterTextColor(ColorStateList colorStateList) {
        if (this.C != colorStateList) {
            this.C = colorStateList;
            o();
        }
    }

    public void setDefaultHintTextColor(ColorStateList colorStateList) {
        this.k0 = colorStateList;
        this.l0 = colorStateList;
        if (this.h != null) {
            t(false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        k(this, z);
        super.setEnabled(z);
    }

    public void setEndIconActivated(boolean z) {
        this.g.k.setActivated(z);
    }

    public void setEndIconCheckable(boolean z) {
        this.g.k.setCheckable(z);
    }

    public void setEndIconContentDescription(int i) {
        com.google.android.material.textfield.a aVar = this.g;
        CharSequence text = i != 0 ? aVar.getResources().getText(i) : null;
        CheckableImageButton checkableImageButton = aVar.k;
        if (checkableImageButton.getContentDescription() != text) {
            checkableImageButton.setContentDescription(text);
        }
    }

    public void setEndIconDrawable(int i) {
        com.google.android.material.textfield.a aVar = this.g;
        Drawable drawableA = i != 0 ? AppCompatResources.a(aVar.getContext(), i) : null;
        CheckableImageButton checkableImageButton = aVar.k;
        checkableImageButton.setImageDrawable(drawableA);
        if (drawableA != null) {
            ColorStateList colorStateList = aVar.o;
            PorterDuff.Mode mode = aVar.p;
            TextInputLayout textInputLayout = aVar.e;
            rw0.a(textInputLayout, checkableImageButton, colorStateList, mode);
            rw0.b(textInputLayout, checkableImageButton, aVar.o);
        }
    }

    public void setEndIconMode(int i) {
        this.g.f(i);
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        this.g.setEndIconOnClickListener(onClickListener);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.g.setEndIconOnLongClickListener(onLongClickListener);
    }

    public void setEndIconTintList(ColorStateList colorStateList) {
        com.google.android.material.textfield.a aVar = this.g;
        if (aVar.o != colorStateList) {
            aVar.o = colorStateList;
            rw0.a(aVar.e, aVar.k, colorStateList, aVar.p);
        }
    }

    public void setEndIconTintMode(PorterDuff.Mode mode) {
        com.google.android.material.textfield.a aVar = this.g;
        if (aVar.p != mode) {
            aVar.p = mode;
            rw0.a(aVar.e, aVar.k, aVar.o, mode);
        }
    }

    public void setEndIconVisible(boolean z) {
        this.g.g(z);
    }

    public void setError(CharSequence charSequence) {
        qz0 qz0Var = this.n;
        if (!qz0Var.k) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            qz0Var.f();
            return;
        }
        qz0Var.c();
        qz0Var.j = charSequence;
        qz0Var.l.setText(charSequence);
        int i = qz0Var.h;
        if (i != 1) {
            qz0Var.i = 1;
        }
        qz0Var.i(i, qz0Var.i, qz0Var.h(qz0Var.l, charSequence));
    }

    public void setErrorContentDescription(CharSequence charSequence) {
        qz0 qz0Var = this.n;
        qz0Var.m = charSequence;
        AppCompatTextView appCompatTextView = qz0Var.l;
        if (appCompatTextView != null) {
            appCompatTextView.setContentDescription(charSequence);
        }
    }

    public void setErrorEnabled(boolean z) {
        qz0 qz0Var = this.n;
        if (qz0Var.k == z) {
            return;
        }
        qz0Var.c();
        TextInputLayout textInputLayout = qz0Var.b;
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(qz0Var.a);
            qz0Var.l = appCompatTextView;
            appCompatTextView.setId(R$id.textinput_error);
            qz0Var.l.setTextAlignment(5);
            Typeface typeface = qz0Var.u;
            if (typeface != null) {
                qz0Var.l.setTypeface(typeface);
            }
            int i = qz0Var.n;
            qz0Var.n = i;
            AppCompatTextView appCompatTextView2 = qz0Var.l;
            if (appCompatTextView2 != null) {
                textInputLayout.l(appCompatTextView2, i);
            }
            ColorStateList colorStateList = qz0Var.o;
            qz0Var.o = colorStateList;
            AppCompatTextView appCompatTextView3 = qz0Var.l;
            if (appCompatTextView3 != null && colorStateList != null) {
                appCompatTextView3.setTextColor(colorStateList);
            }
            CharSequence charSequence = qz0Var.m;
            qz0Var.m = charSequence;
            AppCompatTextView appCompatTextView4 = qz0Var.l;
            if (appCompatTextView4 != null) {
                appCompatTextView4.setContentDescription(charSequence);
            }
            qz0Var.l.setVisibility(4);
            AppCompatTextView appCompatTextView5 = qz0Var.l;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.g.e(appCompatTextView5, 1);
            qz0Var.a(qz0Var.l, 0);
        } else {
            qz0Var.f();
            qz0Var.g(qz0Var.l, 0);
            qz0Var.l = null;
            textInputLayout.q();
            textInputLayout.x();
        }
        qz0Var.k = z;
    }

    public void setErrorIconDrawable(int i) {
        com.google.android.material.textfield.a aVar = this.g;
        aVar.h(i != 0 ? AppCompatResources.a(aVar.getContext(), i) : null);
        rw0.b(aVar.e, aVar.g, aVar.h);
    }

    public void setErrorIconOnClickListener(View.OnClickListener onClickListener) {
        this.g.setErrorIconOnClickListener(onClickListener);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.g.setErrorIconOnLongClickListener(onLongClickListener);
    }

    public void setErrorIconTintList(ColorStateList colorStateList) {
        com.google.android.material.textfield.a aVar = this.g;
        if (aVar.h != colorStateList) {
            aVar.h = colorStateList;
            rw0.a(aVar.e, aVar.g, colorStateList, aVar.i);
        }
    }

    public void setErrorIconTintMode(PorterDuff.Mode mode) {
        com.google.android.material.textfield.a aVar = this.g;
        if (aVar.i != mode) {
            aVar.i = mode;
            rw0.a(aVar.e, aVar.g, aVar.h, mode);
        }
    }

    public void setErrorTextAppearance(int i) {
        qz0 qz0Var = this.n;
        qz0Var.n = i;
        AppCompatTextView appCompatTextView = qz0Var.l;
        if (appCompatTextView != null) {
            qz0Var.b.l(appCompatTextView, i);
        }
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        qz0 qz0Var = this.n;
        qz0Var.o = colorStateList;
        AppCompatTextView appCompatTextView = qz0Var.l;
        if (appCompatTextView == null || colorStateList == null) {
            return;
        }
        appCompatTextView.setTextColor(colorStateList);
    }

    public void setExpandedHintEnabled(boolean z) {
        if (this.x0 != z) {
            this.x0 = z;
            t(false);
        }
    }

    public void setHelperText(CharSequence charSequence) {
        boolean zIsEmpty = TextUtils.isEmpty(charSequence);
        qz0 qz0Var = this.n;
        if (zIsEmpty) {
            if (qz0Var.q) {
                setHelperTextEnabled(false);
                return;
            }
            return;
        }
        if (!qz0Var.q) {
            setHelperTextEnabled(true);
        }
        qz0Var.c();
        qz0Var.p = charSequence;
        qz0Var.r.setText(charSequence);
        int i = qz0Var.h;
        if (i != 2) {
            qz0Var.i = 2;
        }
        qz0Var.i(i, qz0Var.i, qz0Var.h(qz0Var.r, charSequence));
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        qz0 qz0Var = this.n;
        qz0Var.t = colorStateList;
        AppCompatTextView appCompatTextView = qz0Var.r;
        if (appCompatTextView == null || colorStateList == null) {
            return;
        }
        appCompatTextView.setTextColor(colorStateList);
    }

    public void setHelperTextEnabled(boolean z) {
        qz0 qz0Var = this.n;
        if (qz0Var.q == z) {
            return;
        }
        qz0Var.c();
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(qz0Var.a);
            qz0Var.r = appCompatTextView;
            appCompatTextView.setId(R$id.textinput_helper_text);
            qz0Var.r.setTextAlignment(5);
            Typeface typeface = qz0Var.u;
            if (typeface != null) {
                qz0Var.r.setTypeface(typeface);
            }
            qz0Var.r.setVisibility(4);
            AppCompatTextView appCompatTextView2 = qz0Var.r;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.g.e(appCompatTextView2, 1);
            int i = qz0Var.s;
            qz0Var.s = i;
            AppCompatTextView appCompatTextView3 = qz0Var.r;
            if (appCompatTextView3 != null) {
                appCompatTextView3.setTextAppearance(i);
            }
            ColorStateList colorStateList = qz0Var.t;
            qz0Var.t = colorStateList;
            AppCompatTextView appCompatTextView4 = qz0Var.r;
            if (appCompatTextView4 != null && colorStateList != null) {
                appCompatTextView4.setTextColor(colorStateList);
            }
            qz0Var.a(qz0Var.r, 1);
            qz0Var.r.setAccessibilityDelegate(new rz0(qz0Var));
        } else {
            qz0Var.c();
            int i2 = qz0Var.h;
            if (i2 == 2) {
                qz0Var.i = 0;
            }
            qz0Var.i(i2, qz0Var.i, qz0Var.h(qz0Var.r, ""));
            qz0Var.g(qz0Var.r, 1);
            qz0Var.r = null;
            TextInputLayout textInputLayout = qz0Var.b;
            textInputLayout.q();
            textInputLayout.x();
        }
        qz0Var.q = z;
    }

    public void setHelperTextTextAppearance(int i) {
        qz0 qz0Var = this.n;
        qz0Var.s = i;
        AppCompatTextView appCompatTextView = qz0Var.r;
        if (appCompatTextView != null) {
            appCompatTextView.setTextAppearance(i);
        }
    }

    public void setHint(CharSequence charSequence) {
        if (this.E) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z) {
        this.y0 = z;
    }

    public void setHintEnabled(boolean z) {
        if (z != this.E) {
            this.E = z;
            if (z) {
                CharSequence hint = this.h.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.F)) {
                        setHint(hint);
                    }
                    this.h.setHint((CharSequence) null);
                }
                this.G = true;
            } else {
                this.G = false;
                if (!TextUtils.isEmpty(this.F) && TextUtils.isEmpty(this.h.getHint())) {
                    this.h.setHint(this.F);
                }
                setHintInternal(null);
            }
            if (this.h != null) {
                s();
            }
        }
    }

    public void setHintTextAppearance(int i) {
        ou ouVar = this.w0;
        ouVar.j(i);
        this.l0 = ouVar.o;
        if (this.h != null) {
            t(false);
            s();
        }
    }

    public void setHintTextColor(ColorStateList colorStateList) {
        if (this.l0 != colorStateList) {
            if (this.k0 == null) {
                this.w0.k(colorStateList);
            }
            this.l0 = colorStateList;
            if (this.h != null) {
                t(false);
            }
        }
    }

    public void setLengthCounter(f fVar) {
        this.r = fVar;
    }

    public void setMaxEms(int i) {
        this.k = i;
        EditText editText = this.h;
        if (editText == null || i == -1) {
            return;
        }
        editText.setMaxEms(i);
    }

    public void setMaxWidth(int i) {
        this.m = i;
        EditText editText = this.h;
        if (editText == null || i == -1) {
            return;
        }
        editText.setMaxWidth(i);
    }

    public void setMaxWidthResource(int i) {
        setMaxWidth(getContext().getResources().getDimensionPixelSize(i));
    }

    public void setMinEms(int i) {
        this.j = i;
        EditText editText = this.h;
        if (editText == null || i == -1) {
            return;
        }
        editText.setMinEms(i);
    }

    public void setMinWidth(int i) {
        this.l = i;
        EditText editText = this.h;
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
        com.google.android.material.textfield.a aVar = this.g;
        aVar.k.setContentDescription(i != 0 ? aVar.getResources().getText(i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int i) {
        com.google.android.material.textfield.a aVar = this.g;
        aVar.k.setImageDrawable(i != 0 ? AppCompatResources.a(aVar.getContext(), i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z) {
        com.google.android.material.textfield.a aVar = this.g;
        if (z && aVar.m != 1) {
            aVar.f(1);
        } else if (z) {
            aVar.getClass();
        } else {
            aVar.f(0);
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(ColorStateList colorStateList) {
        com.google.android.material.textfield.a aVar = this.g;
        aVar.o = colorStateList;
        rw0.a(aVar.e, aVar.k, colorStateList, aVar.p);
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        com.google.android.material.textfield.a aVar = this.g;
        aVar.p = mode;
        rw0.a(aVar.e, aVar.k, aVar.o, mode);
    }

    public void setPlaceholderText(CharSequence charSequence) {
        if (this.x == null) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
            this.x = appCompatTextView;
            appCompatTextView.setId(R$id.textinput_placeholder);
            AppCompatTextView appCompatTextView2 = this.x;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.p(appCompatTextView2, 2);
            Fade fade = new Fade();
            fade.g = 87L;
            LinearInterpolator linearInterpolator = u5.a;
            fade.h = linearInterpolator;
            this.A = fade;
            fade.f = 67L;
            Fade fade2 = new Fade();
            fade2.g = 87L;
            fade2.h = linearInterpolator;
            this.B = fade2;
            setPlaceholderTextAppearance(this.z);
            setPlaceholderTextColor(this.y);
        }
        if (TextUtils.isEmpty(charSequence)) {
            setPlaceholderTextEnabled(false);
        } else {
            if (!this.w) {
                setPlaceholderTextEnabled(true);
            }
            this.v = charSequence;
        }
        EditText editText = this.h;
        v(editText == null ? null : editText.getText());
    }

    public void setPlaceholderTextAppearance(int i) {
        this.z = i;
        AppCompatTextView appCompatTextView = this.x;
        if (appCompatTextView != null) {
            appCompatTextView.setTextAppearance(i);
        }
    }

    public void setPlaceholderTextColor(ColorStateList colorStateList) {
        if (this.y != colorStateList) {
            this.y = colorStateList;
            AppCompatTextView appCompatTextView = this.x;
            if (appCompatTextView == null || colorStateList == null) {
                return;
            }
            appCompatTextView.setTextColor(colorStateList);
        }
    }

    public void setPrefixText(CharSequence charSequence) {
        jr2 jr2Var = this.f;
        jr2Var.getClass();
        jr2Var.g = TextUtils.isEmpty(charSequence) ? null : charSequence;
        jr2Var.f.setText(charSequence);
        jr2Var.d();
    }

    public void setPrefixTextAppearance(int i) {
        this.f.f.setTextAppearance(i);
    }

    public void setPrefixTextColor(ColorStateList colorStateList) {
        this.f.f.setTextColor(colorStateList);
    }

    public void setStartIconCheckable(boolean z) {
        this.f.h.setCheckable(z);
    }

    public void setStartIconContentDescription(CharSequence charSequence) {
        CheckableImageButton checkableImageButton = this.f.h;
        if (checkableImageButton.getContentDescription() != charSequence) {
            checkableImageButton.setContentDescription(charSequence);
        }
    }

    public void setStartIconDrawable(int i) {
        setStartIconDrawable(i != 0 ? AppCompatResources.a(getContext(), i) : null);
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        this.f.setStartIconOnClickListener(onClickListener);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.f.setStartIconOnLongClickListener(onLongClickListener);
    }

    public void setStartIconTintList(ColorStateList colorStateList) {
        jr2 jr2Var = this.f;
        if (jr2Var.i != colorStateList) {
            jr2Var.i = colorStateList;
            rw0.a(jr2Var.e, jr2Var.h, colorStateList, jr2Var.j);
        }
    }

    public void setStartIconTintMode(PorterDuff.Mode mode) {
        jr2 jr2Var = this.f;
        if (jr2Var.j != mode) {
            jr2Var.j = mode;
            rw0.a(jr2Var.e, jr2Var.h, jr2Var.i, mode);
        }
    }

    public void setStartIconVisible(boolean z) {
        this.f.b(z);
    }

    public void setSuffixText(CharSequence charSequence) {
        com.google.android.material.textfield.a aVar = this.g;
        aVar.getClass();
        aVar.r = TextUtils.isEmpty(charSequence) ? null : charSequence;
        aVar.s.setText(charSequence);
        aVar.m();
    }

    public void setSuffixTextAppearance(int i) {
        this.g.s.setTextAppearance(i);
    }

    public void setSuffixTextColor(ColorStateList colorStateList) {
        this.g.s.setTextColor(colorStateList);
    }

    public void setTextInputAccessibilityDelegate(e eVar) {
        EditText editText = this.h;
        if (editText != null) {
            om3.k(editText, eVar);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != this.d0) {
            this.d0 = typeface;
            ou ouVar = this.w0;
            boolean zM = ouVar.m(typeface);
            boolean zO = ouVar.o(typeface);
            if (zM || zO) {
                ouVar.i(false);
            }
            qz0 qz0Var = this.n;
            if (typeface != qz0Var.u) {
                qz0Var.u = typeface;
                AppCompatTextView appCompatTextView = qz0Var.l;
                if (appCompatTextView != null) {
                    appCompatTextView.setTypeface(typeface);
                }
                AppCompatTextView appCompatTextView2 = qz0Var.r;
                if (appCompatTextView2 != null) {
                    appCompatTextView2.setTypeface(typeface);
                }
            }
            AppCompatTextView appCompatTextView3 = this.s;
            if (appCompatTextView3 != null) {
                appCompatTextView3.setTypeface(typeface);
            }
        }
    }

    public void t(boolean z) {
        u(z, false);
    }

    public final void u(boolean z, boolean z2) {
        ColorStateList colorStateList;
        AppCompatTextView appCompatTextView;
        boolean zIsEnabled = isEnabled();
        EditText editText = this.h;
        boolean z3 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.h;
        boolean z4 = editText2 != null && editText2.hasFocus();
        ColorStateList colorStateList2 = this.k0;
        ou ouVar = this.w0;
        if (colorStateList2 != null) {
            ouVar.k(colorStateList2);
            ColorStateList colorStateList3 = this.k0;
            if (ouVar.n != colorStateList3) {
                ouVar.n = colorStateList3;
                ouVar.i(false);
            }
        }
        if (!zIsEnabled) {
            ColorStateList colorStateList4 = this.k0;
            int colorForState = colorStateList4 != null ? colorStateList4.getColorForState(new int[]{-16842910}, this.u0) : this.u0;
            ouVar.k(ColorStateList.valueOf(colorForState));
            ColorStateList colorStateListValueOf = ColorStateList.valueOf(colorForState);
            if (ouVar.n != colorStateListValueOf) {
                ouVar.n = colorStateListValueOf;
                ouVar.i(false);
            }
        } else if (m()) {
            AppCompatTextView appCompatTextView2 = this.n.l;
            ouVar.k(appCompatTextView2 != null ? appCompatTextView2.getTextColors() : null);
        } else if (this.q && (appCompatTextView = this.s) != null) {
            ouVar.k(appCompatTextView.getTextColors());
        } else if (z4 && (colorStateList = this.l0) != null) {
            ouVar.k(colorStateList);
        }
        com.google.android.material.textfield.a aVar = this.g;
        jr2 jr2Var = this.f;
        if (z3 || !this.x0 || (isEnabled() && z4)) {
            if (z2 || this.v0) {
                ValueAnimator valueAnimator = this.z0;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.z0.cancel();
                }
                if (z && this.y0) {
                    a(1.0f);
                } else {
                    ouVar.p(1.0f);
                }
                this.v0 = false;
                if (d()) {
                    j();
                }
                EditText editText3 = this.h;
                v(editText3 != null ? editText3.getText() : null);
                jr2Var.l = false;
                jr2Var.d();
                aVar.t = false;
                aVar.m();
                return;
            }
            return;
        }
        if (z2 || !this.v0) {
            ValueAnimator valueAnimator2 = this.z0;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.z0.cancel();
            }
            if (z && this.y0) {
                a(0.0f);
            } else {
                ouVar.p(0.0f);
            }
            if (d() && (!((r50) this.H).B.isEmpty()) && d()) {
                ((r50) this.H).w(0.0f, 0.0f, 0.0f, 0.0f);
            }
            this.v0 = true;
            AppCompatTextView appCompatTextView3 = this.x;
            if (appCompatTextView3 != null && this.w) {
                appCompatTextView3.setText((CharSequence) null);
                androidx.transition.h.a(this.e, this.B);
                this.x.setVisibility(4);
            }
            jr2Var.l = true;
            jr2Var.d();
            aVar.t = true;
            aVar.m();
        }
    }

    public final void v(Editable editable) {
        ((i83) this.r).getClass();
        int length = editable != null ? editable.length() : 0;
        FrameLayout frameLayout = this.e;
        if (length != 0 || this.v0) {
            AppCompatTextView appCompatTextView = this.x;
            if (appCompatTextView == null || !this.w) {
                return;
            }
            appCompatTextView.setText((CharSequence) null);
            androidx.transition.h.a(frameLayout, this.B);
            this.x.setVisibility(4);
            return;
        }
        if (this.x == null || !this.w || TextUtils.isEmpty(this.v)) {
            return;
        }
        this.x.setText(this.v);
        androidx.transition.h.a(frameLayout, this.A);
        this.x.setVisibility(0);
        this.x.bringToFront();
        announceForAccessibility(this.v);
    }

    public final void w(boolean z, boolean z2) {
        int defaultColor = this.p0.getDefaultColor();
        int colorForState = this.p0.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, defaultColor);
        int colorForState2 = this.p0.getColorForState(new int[]{R.attr.state_activated, R.attr.state_enabled}, defaultColor);
        if (z) {
            this.V = colorForState2;
        } else if (z2) {
            this.V = colorForState;
        } else {
            this.V = defaultColor;
        }
    }

    public final void x() {
        AppCompatTextView appCompatTextView;
        EditText editText;
        EditText editText2;
        if (this.H == null || this.Q == 0) {
            return;
        }
        boolean z = false;
        boolean z2 = isFocused() || ((editText2 = this.h) != null && editText2.hasFocus());
        if (isHovered() || ((editText = this.h) != null && editText.isHovered())) {
            z = true;
        }
        if (!isEnabled()) {
            this.V = this.u0;
        } else if (m()) {
            if (this.p0 != null) {
                w(z2, z);
            } else {
                this.V = getErrorCurrentTextColors();
            }
        } else if (!this.q || (appCompatTextView = this.s) == null) {
            if (z2) {
                this.V = this.o0;
            } else if (z) {
                this.V = this.n0;
            } else {
                this.V = this.m0;
            }
        } else if (this.p0 != null) {
            w(z2, z);
        } else {
            this.V = appCompatTextView.getCurrentTextColor();
        }
        com.google.android.material.textfield.a aVar = this.g;
        aVar.k();
        CheckableImageButton checkableImageButton = aVar.g;
        ColorStateList colorStateList = aVar.h;
        TextInputLayout textInputLayout = aVar.e;
        rw0.b(textInputLayout, checkableImageButton, colorStateList);
        ColorStateList colorStateList2 = aVar.o;
        CheckableImageButton checkableImageButton2 = aVar.k;
        rw0.b(textInputLayout, checkableImageButton2, colorStateList2);
        if (aVar.b() instanceof pe0) {
            if (!textInputLayout.m() || checkableImageButton2.getDrawable() == null) {
                rw0.a(textInputLayout, checkableImageButton2, aVar.o, aVar.p);
            } else {
                Drawable drawableMutate = checkableImageButton2.getDrawable().mutate();
                drawableMutate.setTint(textInputLayout.getErrorCurrentTextColors());
                checkableImageButton2.setImageDrawable(drawableMutate);
            }
        }
        jr2 jr2Var = this.f;
        rw0.b(jr2Var.e, jr2Var.h, jr2Var.i);
        if (this.Q == 2) {
            int i = this.S;
            if (z2 && isEnabled()) {
                this.S = this.U;
            } else {
                this.S = this.T;
            }
            if (this.S != i && d() && !this.v0) {
                if (d()) {
                    ((r50) this.H).w(0.0f, 0.0f, 0.0f, 0.0f);
                }
                j();
            }
        }
        if (this.Q == 1) {
            if (!isEnabled()) {
                this.W = this.r0;
            } else if (z && !z2) {
                this.W = this.t0;
            } else if (z2) {
                this.W = this.s0;
            } else {
                this.W = this.q0;
            }
        }
        b();
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.textInputStyle);
    }

    public void setStartIconDrawable(Drawable drawable) {
        this.f.a(drawable);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v44 */
    /* JADX WARN: Type inference failed for: r3v45, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v65 */
    public TextInputLayout(Context context, AttributeSet attributeSet, int i) {
        ?? r3;
        int i2 = C0;
        super(uc1.a(context, attributeSet, i, i2), attributeSet, i);
        this.j = -1;
        this.k = -1;
        this.l = -1;
        this.m = -1;
        this.n = new qz0(this);
        this.r = new i83(0);
        this.a0 = new Rect();
        this.b0 = new Rect();
        this.c0 = new RectF();
        this.g0 = new LinkedHashSet<>();
        ou ouVar = new ou(this);
        this.w0 = ouVar;
        Context context2 = getContext();
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        FrameLayout frameLayout = new FrameLayout(context2);
        this.e = frameLayout;
        frameLayout.setAddStatesFromChildren(true);
        LinearInterpolator linearInterpolator = u5.a;
        ouVar.W = linearInterpolator;
        ouVar.i(false);
        ouVar.V = linearInterpolator;
        ouVar.i(false);
        ouVar.l(8388659);
        int[] iArr = R$styleable.TextInputLayout;
        int i3 = R$styleable.TextInputLayout_counterTextAppearance;
        int i4 = R$styleable.TextInputLayout_counterOverflowTextAppearance;
        int i5 = R$styleable.TextInputLayout_errorTextAppearance;
        int i6 = R$styleable.TextInputLayout_helperTextTextAppearance;
        int i7 = R$styleable.TextInputLayout_hintTextAppearance;
        TintTypedArray tintTypedArrayE = r83.e(context2, attributeSet, iArr, i, i2, i3, i4, i5, i6, i7);
        jr2 jr2Var = new jr2(this, tintTypedArrayE);
        this.f = jr2Var;
        this.E = tintTypedArrayE.a(R$styleable.TextInputLayout_hintEnabled, true);
        setHint(tintTypedArrayE.k(R$styleable.TextInputLayout_android_hint));
        this.y0 = tintTypedArrayE.a(R$styleable.TextInputLayout_hintAnimationEnabled, true);
        this.x0 = tintTypedArrayE.a(R$styleable.TextInputLayout_expandedHintEnabled, true);
        int i8 = R$styleable.TextInputLayout_android_minEms;
        if (tintTypedArrayE.l(i8)) {
            setMinEms(tintTypedArrayE.h(i8, -1));
        } else {
            int i9 = R$styleable.TextInputLayout_android_minWidth;
            if (tintTypedArrayE.l(i9)) {
                setMinWidth(tintTypedArrayE.d(i9, -1));
            }
        }
        int i10 = R$styleable.TextInputLayout_android_maxEms;
        if (tintTypedArrayE.l(i10)) {
            setMaxEms(tintTypedArrayE.h(i10, -1));
        } else {
            int i11 = R$styleable.TextInputLayout_android_maxWidth;
            if (tintTypedArrayE.l(i11)) {
                setMaxWidth(tintTypedArrayE.d(i11, -1));
            }
        }
        this.N = new uj2(uj2.b(context2, attributeSet, i, i2));
        this.P = context2.getResources().getDimensionPixelOffset(R$dimen.mtrl_textinput_box_label_cutout_padding);
        this.R = tintTypedArrayE.c(R$styleable.TextInputLayout_boxCollapsedPaddingTop, 0);
        this.T = tintTypedArrayE.d(R$styleable.TextInputLayout_boxStrokeWidth, context2.getResources().getDimensionPixelSize(R$dimen.mtrl_textinput_box_stroke_width_default));
        this.U = tintTypedArrayE.d(R$styleable.TextInputLayout_boxStrokeWidthFocused, context2.getResources().getDimensionPixelSize(R$dimen.mtrl_textinput_box_stroke_width_focused));
        this.S = this.T;
        int i12 = R$styleable.TextInputLayout_boxCornerRadiusTopStart;
        TypedArray typedArray = tintTypedArrayE.b;
        float dimension = typedArray.getDimension(i12, -1.0f);
        float dimension2 = typedArray.getDimension(R$styleable.TextInputLayout_boxCornerRadiusTopEnd, -1.0f);
        float dimension3 = typedArray.getDimension(R$styleable.TextInputLayout_boxCornerRadiusBottomEnd, -1.0f);
        float dimension4 = typedArray.getDimension(R$styleable.TextInputLayout_boxCornerRadiusBottomStart, -1.0f);
        uj2 uj2Var = this.N;
        uj2Var.getClass();
        uj2.a aVar = new uj2.a(uj2Var);
        if (dimension >= 0.0f) {
            aVar.f(dimension);
        }
        if (dimension2 >= 0.0f) {
            aVar.g(dimension2);
        }
        if (dimension3 >= 0.0f) {
            aVar.e(dimension3);
        }
        if (dimension4 >= 0.0f) {
            aVar.d(dimension4);
        }
        this.N = new uj2(aVar);
        ColorStateList colorStateListB = rc1.b(context2, tintTypedArrayE, R$styleable.TextInputLayout_boxBackgroundColor);
        if (colorStateListB != null) {
            int defaultColor = colorStateListB.getDefaultColor();
            this.q0 = defaultColor;
            this.W = defaultColor;
            if (colorStateListB.isStateful()) {
                this.r0 = colorStateListB.getColorForState(new int[]{-16842910}, -1);
                this.s0 = colorStateListB.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
                this.t0 = colorStateListB.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            } else {
                this.s0 = this.q0;
                ColorStateList colorStateListB2 = bz.b(context2, R$color.mtrl_filled_background_color);
                this.r0 = colorStateListB2.getColorForState(new int[]{-16842910}, -1);
                this.t0 = colorStateListB2.getColorForState(new int[]{R.attr.state_hovered}, -1);
            }
        } else {
            this.W = 0;
            this.q0 = 0;
            this.r0 = 0;
            this.s0 = 0;
            this.t0 = 0;
        }
        int i13 = R$styleable.TextInputLayout_android_textColorHint;
        if (tintTypedArrayE.l(i13)) {
            ColorStateList colorStateListB3 = tintTypedArrayE.b(i13);
            this.l0 = colorStateListB3;
            this.k0 = colorStateListB3;
        }
        int i14 = R$styleable.TextInputLayout_boxStrokeColor;
        ColorStateList colorStateListB4 = rc1.b(context2, tintTypedArrayE, i14);
        this.o0 = typedArray.getColor(i14, 0);
        int i15 = R$color.mtrl_textinput_default_box_stroke_color;
        Object obj = bz.a;
        this.m0 = context2.getColor(i15);
        this.u0 = context2.getColor(R$color.mtrl_textinput_disabled_color);
        this.n0 = context2.getColor(R$color.mtrl_textinput_hovered_box_stroke_color);
        if (colorStateListB4 != null) {
            setBoxStrokeColorStateList(colorStateListB4);
        }
        int i16 = R$styleable.TextInputLayout_boxStrokeErrorColor;
        if (tintTypedArrayE.l(i16)) {
            setBoxStrokeErrorColor(rc1.b(context2, tintTypedArrayE, i16));
        }
        if (tintTypedArrayE.i(i7, -1) != -1) {
            r3 = 0;
            setHintTextAppearance(tintTypedArrayE.i(i7, 0));
        } else {
            r3 = 0;
        }
        int i17 = tintTypedArrayE.i(i5, r3);
        CharSequence charSequenceK = tintTypedArrayE.k(R$styleable.TextInputLayout_errorContentDescription);
        boolean zA = tintTypedArrayE.a(R$styleable.TextInputLayout_errorEnabled, r3);
        int i18 = tintTypedArrayE.i(i6, r3);
        boolean zA2 = tintTypedArrayE.a(R$styleable.TextInputLayout_helperTextEnabled, r3);
        CharSequence charSequenceK2 = tintTypedArrayE.k(R$styleable.TextInputLayout_helperText);
        int i19 = tintTypedArrayE.i(R$styleable.TextInputLayout_placeholderTextAppearance, r3);
        CharSequence charSequenceK3 = tintTypedArrayE.k(R$styleable.TextInputLayout_placeholderText);
        boolean zA3 = tintTypedArrayE.a(R$styleable.TextInputLayout_counterEnabled, r3);
        setCounterMaxLength(tintTypedArrayE.h(R$styleable.TextInputLayout_counterMaxLength, -1));
        this.u = tintTypedArrayE.i(i3, 0);
        this.t = tintTypedArrayE.i(i4, 0);
        setBoxBackgroundMode(tintTypedArrayE.h(R$styleable.TextInputLayout_boxBackgroundMode, 0));
        setErrorContentDescription(charSequenceK);
        setCounterOverflowTextAppearance(this.t);
        setHelperTextTextAppearance(i18);
        setErrorTextAppearance(i17);
        setCounterTextAppearance(this.u);
        setPlaceholderText(charSequenceK3);
        setPlaceholderTextAppearance(i19);
        int i20 = R$styleable.TextInputLayout_errorTextColor;
        if (tintTypedArrayE.l(i20)) {
            setErrorTextColor(tintTypedArrayE.b(i20));
        }
        int i21 = R$styleable.TextInputLayout_helperTextTextColor;
        if (tintTypedArrayE.l(i21)) {
            setHelperTextColor(tintTypedArrayE.b(i21));
        }
        int i22 = R$styleable.TextInputLayout_hintTextColor;
        if (tintTypedArrayE.l(i22)) {
            setHintTextColor(tintTypedArrayE.b(i22));
        }
        int i23 = R$styleable.TextInputLayout_counterTextColor;
        if (tintTypedArrayE.l(i23)) {
            setCounterTextColor(tintTypedArrayE.b(i23));
        }
        int i24 = R$styleable.TextInputLayout_counterOverflowTextColor;
        if (tintTypedArrayE.l(i24)) {
            setCounterOverflowTextColor(tintTypedArrayE.b(i24));
        }
        int i25 = R$styleable.TextInputLayout_placeholderTextColor;
        if (tintTypedArrayE.l(i25)) {
            setPlaceholderTextColor(tintTypedArrayE.b(i25));
        }
        com.google.android.material.textfield.a aVar2 = new com.google.android.material.textfield.a(this, tintTypedArrayE);
        this.g = aVar2;
        setEnabled(tintTypedArrayE.a(R$styleable.TextInputLayout_android_enabled, true));
        tintTypedArrayE.n();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.p(this, 2);
        om3.l.b(this, 1);
        frameLayout.addView(jr2Var);
        frameLayout.addView(aVar2);
        addView(frameLayout);
        setHelperTextEnabled(zA2);
        setErrorEnabled(zA);
        setCounterEnabled(zA3);
        setHelperText(charSequenceK2);
    }

    public void setHint(int i) {
        setHint(i != 0 ? getResources().getText(i) : null);
    }

    public void setStartIconContentDescription(int i) {
        setStartIconContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence charSequence) {
        this.g.k.setContentDescription(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable drawable) {
        this.g.k.setImageDrawable(drawable);
    }

    public void setErrorIconDrawable(Drawable drawable) {
        this.g.h(drawable);
    }

    public void setEndIconContentDescription(CharSequence charSequence) {
        CheckableImageButton checkableImageButton = this.g.k;
        if (checkableImageButton.getContentDescription() != charSequence) {
            checkableImageButton.setContentDescription(charSequence);
        }
    }

    public void setEndIconDrawable(Drawable drawable) {
        com.google.android.material.textfield.a aVar = this.g;
        CheckableImageButton checkableImageButton = aVar.k;
        checkableImageButton.setImageDrawable(drawable);
        if (drawable != null) {
            ColorStateList colorStateList = aVar.o;
            PorterDuff.Mode mode = aVar.p;
            TextInputLayout textInputLayout = aVar.e;
            rw0.a(textInputLayout, checkableImageButton, colorStateList, mode);
            rw0.b(textInputLayout, checkableImageButton, aVar.o);
        }
    }

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            TextInputLayout textInputLayout = TextInputLayout.this;
            textInputLayout.t(!textInputLayout.B0);
            if (textInputLayout.o) {
                textInputLayout.n(editable);
            }
            if (textInputLayout.w) {
                textInputLayout.v(editable);
            }
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }
}
