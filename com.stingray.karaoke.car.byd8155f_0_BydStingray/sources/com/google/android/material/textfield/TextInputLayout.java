package com.google.android.material.textfield;

import A.C0050y0;
import F1.H;
import N1.C0420a;
import P8.n;
import U4.b;
import V4.a;
import Y4.f;
import Y4.g;
import Y4.k;
import a5.c;
import a5.d;
import a5.h;
import a5.j;
import a5.o;
import a5.p;
import a5.r;
import a5.t;
import a5.u;
import a5.v;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.internal.CheckableImageButton;
import com.stingray.drivemodedetector.R;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import r.AbstractC2225M;
import r.C2218F;
import r.C2258q;

/* JADX INFO: loaded from: classes.dex */
public class TextInputLayout extends LinearLayout {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public CharSequence f12865A;

    /* JADX INFO: renamed from: A0, reason: collision with root package name */
    public Drawable f12866A0;
    public boolean B;
    public View.OnLongClickListener B0;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public C2218F f12867C;

    /* JADX INFO: renamed from: C0, reason: collision with root package name */
    public View.OnLongClickListener f12868C0;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public ColorStateList f12869D;

    /* JADX INFO: renamed from: D0, reason: collision with root package name */
    public final CheckableImageButton f12870D0;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public int f12871E;

    /* JADX INFO: renamed from: E0, reason: collision with root package name */
    public ColorStateList f12872E0;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public ColorStateList f12873F;

    /* JADX INFO: renamed from: F0, reason: collision with root package name */
    public ColorStateList f12874F0;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public ColorStateList f12875G;
    public ColorStateList G0;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public CharSequence f12876H;

    /* JADX INFO: renamed from: H0, reason: collision with root package name */
    public int f12877H0;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final C2218F f12878I;

    /* JADX INFO: renamed from: I0, reason: collision with root package name */
    public int f12879I0;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public CharSequence f12880J;

    /* JADX INFO: renamed from: J0, reason: collision with root package name */
    public int f12881J0;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final C2218F f12882K;

    /* JADX INFO: renamed from: K0, reason: collision with root package name */
    public ColorStateList f12883K0;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public boolean f12884L;

    /* JADX INFO: renamed from: L0, reason: collision with root package name */
    public int f12885L0;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public CharSequence f12886M;

    /* JADX INFO: renamed from: M0, reason: collision with root package name */
    public int f12887M0;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public boolean f12888N;

    /* JADX INFO: renamed from: N0, reason: collision with root package name */
    public int f12889N0;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public g f12890O;

    /* JADX INFO: renamed from: O0, reason: collision with root package name */
    public int f12891O0;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public g f12892P;

    /* JADX INFO: renamed from: P0, reason: collision with root package name */
    public int f12893P0;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public final k f12894Q;

    /* JADX INFO: renamed from: Q0, reason: collision with root package name */
    public boolean f12895Q0;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public final int f12896R;

    /* JADX INFO: renamed from: R0, reason: collision with root package name */
    public final b f12897R0;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public int f12898S;

    /* JADX INFO: renamed from: S0, reason: collision with root package name */
    public boolean f12899S0;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public int f12900T;

    /* JADX INFO: renamed from: T0, reason: collision with root package name */
    public boolean f12901T0;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public int f12902U;

    /* JADX INFO: renamed from: U0, reason: collision with root package name */
    public ValueAnimator f12903U0;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public int f12904V;

    /* JADX INFO: renamed from: V0, reason: collision with root package name */
    public boolean f12905V0;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public int f12906W;

    /* JADX INFO: renamed from: W0, reason: collision with root package name */
    public boolean f12907W0;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public int f12908a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public int f12909b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public int f12910c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public final Rect f12911d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final Rect f12912e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public final RectF f12913f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public Typeface f12914g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public final CheckableImageButton f12915h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public ColorStateList f12916i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public boolean f12917j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public PorterDuff.Mode f12918k0;
    public final FrameLayout l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public boolean f12919l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final LinearLayout f12920m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public ColorDrawable f12921m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final LinearLayout f12922n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public int f12923n0;
    public final FrameLayout o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public View.OnLongClickListener f12924o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public EditText f12925p;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public final LinkedHashSet f12926p0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public CharSequence f12927q;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public int f12928q0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f12929r;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public final SparseArray f12930r0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f12931s;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public final CheckableImageButton f12932s0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final r f12933t;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public final LinkedHashSet f12934t0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f12935u;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public ColorStateList f12936u0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f12937v;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public boolean f12938v0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f12939w;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public PorterDuff.Mode f12940w0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public C2218F f12941x;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public boolean f12942x0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f12943y;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public ColorDrawable f12944y0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f12945z;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public int f12946z0;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:99:0x051e  */
    /* JADX WARN: Type inference failed for: r3v30 */
    /* JADX WARN: Type inference failed for: r3v31, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v93 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public TextInputLayout(android.content.Context r31, android.util.AttributeSet r32) {
        /*
            Method dump skipped, instruction units count: 1589
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public static void d(CheckableImageButton checkableImageButton, boolean z6, ColorStateList colorStateList, boolean z10, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null && (z6 || z10)) {
            drawable = drawable.mutate();
            if (z6) {
                drawable.setTintList(colorStateList);
            }
            if (z10) {
                drawable.setTintMode(mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    private p getEndIconDelegate() {
        SparseArray sparseArray = this.f12930r0;
        p pVar = (p) sparseArray.get(this.f12928q0);
        return pVar != null ? pVar : (p) sparseArray.get(0);
    }

    private CheckableImageButton getEndIconToUpdateDummyDrawable() {
        CheckableImageButton checkableImageButton = this.f12870D0;
        if (checkableImageButton.getVisibility() == 0) {
            return checkableImageButton;
        }
        if (this.f12928q0 == 0 || !g()) {
            return null;
        }
        return this.f12932s0;
    }

    public static void j(ViewGroup viewGroup, boolean z6) {
        int childCount = viewGroup.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = viewGroup.getChildAt(i7);
            childAt.setEnabled(z6);
            if (childAt instanceof ViewGroup) {
                j((ViewGroup) childAt, z6);
            }
        }
    }

    public static void l(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        int[] iArr = H.f2303a;
        boolean zHasOnClickListeners = checkableImageButton.hasOnClickListeners();
        boolean z6 = onLongClickListener != null;
        boolean z10 = zHasOnClickListeners || z6;
        checkableImageButton.setFocusable(z10);
        checkableImageButton.setClickable(zHasOnClickListeners);
        checkableImageButton.setPressable(zHasOnClickListeners);
        checkableImageButton.setLongClickable(z6);
        checkableImageButton.setImportantForAccessibility(z10 ? 1 : 2);
    }

    private void setEditText(EditText editText) {
        boolean z6;
        boolean z10;
        if (this.f12925p != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (this.f12928q0 != 3 && !(editText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.f12925p = editText;
        setMinWidth(this.f12929r);
        setMaxWidth(this.f12931s);
        h();
        setTextInputAccessibilityDelegate(new u(this));
        Typeface typeface = this.f12925p.getTypeface();
        b bVar = this.f12897R0;
        a aVar = bVar.f9048v;
        if (aVar != null) {
            aVar.f9462f = true;
        }
        if (bVar.f9045s != typeface) {
            bVar.f9045s = typeface;
            z6 = true;
        } else {
            z6 = false;
        }
        if (bVar.f9046t != typeface) {
            bVar.f9046t = typeface;
            z10 = true;
        } else {
            z10 = false;
        }
        if (z6 || z10) {
            bVar.g();
        }
        float textSize = this.f12925p.getTextSize();
        if (bVar.f9037i != textSize) {
            bVar.f9037i = textSize;
            bVar.g();
        }
        int gravity = this.f12925p.getGravity();
        int i7 = (gravity & (-113)) | 48;
        if (bVar.f9036h != i7) {
            bVar.f9036h = i7;
            bVar.g();
        }
        if (bVar.f9035g != gravity) {
            bVar.f9035g = gravity;
            bVar.g();
        }
        this.f12925p.addTextChangedListener(new a5.a(this, 1));
        if (this.f12874F0 == null) {
            this.f12874F0 = this.f12925p.getHintTextColors();
        }
        if (this.f12884L) {
            if (TextUtils.isEmpty(this.f12886M)) {
                CharSequence hint = this.f12925p.getHint();
                this.f12927q = hint;
                setHint(hint);
                this.f12925p.setHint((CharSequence) null);
            }
            this.f12888N = true;
        }
        if (this.f12941x != null) {
            n(this.f12925p.getText().length());
        }
        q();
        this.f12933t.b();
        this.f12920m.bringToFront();
        this.f12922n.bringToFront();
        this.o.bringToFront();
        this.f12870D0.bringToFront();
        Iterator it = this.f12926p0.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(this);
        }
        u();
        x();
        if (!isEnabled()) {
            editText.setEnabled(false);
        }
        s(false, true);
    }

    private void setErrorIconVisible(boolean z6) {
        this.f12870D0.setVisibility(z6 ? 0 : 8);
        this.o.setVisibility(z6 ? 8 : 0);
        x();
        if (this.f12928q0 != 0) {
            return;
        }
        p();
    }

    private void setHintInternal(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.f12886M)) {
            return;
        }
        this.f12886M = charSequence;
        b bVar = this.f12897R0;
        if (charSequence == null || !TextUtils.equals(bVar.f9049w, charSequence)) {
            bVar.f9049w = charSequence;
            bVar.f9050x = null;
            Bitmap bitmap = bVar.f9052z;
            if (bitmap != null) {
                bitmap.recycle();
                bVar.f9052z = null;
            }
            bVar.g();
        }
        if (this.f12895Q0) {
            return;
        }
        i();
    }

    private void setPlaceholderTextEnabled(boolean z6) {
        if (this.B == z6) {
            return;
        }
        if (z6) {
            C2218F c2218f = new C2218F(getContext(), null);
            this.f12867C = c2218f;
            c2218f.setId(R.id.textinput_placeholder);
            C2218F c2218f2 = this.f12867C;
            int[] iArr = H.f2303a;
            c2218f2.setAccessibilityLiveRegion(1);
            setPlaceholderTextAppearance(this.f12871E);
            setPlaceholderTextColor(this.f12869D);
            C2218F c2218f3 = this.f12867C;
            if (c2218f3 != null) {
                this.l.addView(c2218f3);
                this.f12867C.setVisibility(0);
            }
        } else {
            C2218F c2218f4 = this.f12867C;
            if (c2218f4 != null) {
                c2218f4.setVisibility(8);
            }
            this.f12867C = null;
        }
        this.B = z6;
    }

    public final void a(float f10) {
        int i7 = 2;
        b bVar = this.f12897R0;
        if (bVar.c == f10) {
            return;
        }
        if (this.f12903U0 == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.f12903U0 = valueAnimator;
            valueAnimator.setInterpolator(L4.a.f4008b);
            this.f12903U0.setDuration(167L);
            this.f12903U0.addUpdateListener(new P4.b(this, i7));
        }
        this.f12903U0.setFloatValues(bVar.c, f10);
        this.f12903U0.start();
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i7, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
        FrameLayout frameLayout = this.l;
        frameLayout.addView(view, layoutParams2);
        frameLayout.setLayoutParams(layoutParams);
        r();
        setEditText((EditText) view);
    }

    public final void b() {
        int i7;
        int i10;
        int i11;
        g gVar = this.f12890O;
        if (gVar == null) {
            return;
        }
        gVar.setShapeAppearanceModel(this.f12894Q);
        if (this.f12900T == 2 && (i10 = this.f12904V) > -1 && (i11 = this.f12909b0) != 0) {
            g gVar2 = this.f12890O;
            gVar2.l.f10715j = i10;
            gVar2.invalidateSelf();
            ColorStateList colorStateListValueOf = ColorStateList.valueOf(i11);
            f fVar = gVar2.l;
            if (fVar.d != colorStateListValueOf) {
                fVar.d = colorStateListValueOf;
                gVar2.onStateChange(gVar2.getState());
            }
        }
        int iF = this.f12910c0;
        if (this.f12900T == 1) {
            TypedValue typedValueS = n.S(getContext(), R.attr.colorSurface);
            iF = y1.a.f(this.f12910c0, typedValueS != null ? typedValueS.data : 0);
        }
        this.f12910c0 = iF;
        this.f12890O.h(ColorStateList.valueOf(iF));
        if (this.f12928q0 == 3) {
            this.f12925p.getBackground().invalidateSelf();
        }
        g gVar3 = this.f12892P;
        if (gVar3 != null) {
            if (this.f12904V > -1 && (i7 = this.f12909b0) != 0) {
                gVar3.h(ColorStateList.valueOf(i7));
            }
            invalidate();
        }
        invalidate();
    }

    public final void c() {
        d(this.f12932s0, this.f12938v0, this.f12936u0, this.f12942x0, this.f12940w0);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i7) {
        EditText editText = this.f12925p;
        if (editText == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i7);
            return;
        }
        if (this.f12927q != null) {
            boolean z6 = this.f12888N;
            this.f12888N = false;
            CharSequence hint = editText.getHint();
            this.f12925p.setHint(this.f12927q);
            try {
                super.dispatchProvideAutofillStructure(viewStructure, i7);
                return;
            } finally {
                this.f12925p.setHint(hint);
                this.f12888N = z6;
            }
        }
        viewStructure.setAutofillId(getAutofillId());
        onProvideAutofillStructure(viewStructure, i7);
        onProvideAutofillVirtualStructure(viewStructure, i7);
        FrameLayout frameLayout = this.l;
        viewStructure.setChildCount(frameLayout.getChildCount());
        for (int i10 = 0; i10 < frameLayout.getChildCount(); i10++) {
            View childAt = frameLayout.getChildAt(i10);
            ViewStructure viewStructureNewChild = viewStructure.newChild(i10);
            childAt.dispatchProvideAutofillStructure(viewStructureNewChild, i7);
            if (childAt == this.f12925p) {
                viewStructureNewChild.setHint(getHint());
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        this.f12907W0 = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.f12907W0 = false;
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f12884L) {
            b bVar = this.f12897R0;
            bVar.getClass();
            int iSave = canvas.save();
            if (bVar.f9050x != null && bVar.f9032b) {
                bVar.f9029N.getLineLeft(0);
                bVar.f9020E.setTextSize(bVar.B);
                float f10 = bVar.f9043q;
                float f11 = bVar.f9044r;
                float f12 = bVar.f9017A;
                if (f12 != 1.0f) {
                    canvas.scale(f12, f12, f10, f11);
                }
                canvas.translate(f10, f11);
                bVar.f9029N.draw(canvas);
                canvas.restoreToCount(iSave);
            }
        }
        g gVar = this.f12892P;
        if (gVar != null) {
            Rect bounds = gVar.getBounds();
            bounds.top = bounds.bottom - this.f12904V;
            this.f12892P.draw(canvas);
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
            boolean r0 = r4.f12905V0
            if (r0 == 0) goto L5
            return
        L5:
            r0 = 1
            r4.f12905V0 = r0
            super.drawableStateChanged()
            int[] r1 = r4.getDrawableState()
            r2 = 0
            U4.b r3 = r4.f12897R0
            if (r3 == 0) goto L2f
            r3.f9018C = r1
            android.content.res.ColorStateList r1 = r3.l
            if (r1 == 0) goto L20
            boolean r1 = r1.isStateful()
            if (r1 != 0) goto L2a
        L20:
            android.content.res.ColorStateList r1 = r3.f9039k
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
            android.widget.EditText r3 = r4.f12925p
            if (r3 == 0) goto L47
            int[] r3 = F1.H.f2303a
            boolean r3 = r4.isLaidOut()
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
            r4.f12905V0 = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.drawableStateChanged():void");
    }

    public final int e() {
        if (!this.f12884L) {
            return 0;
        }
        int i7 = this.f12900T;
        b bVar = this.f12897R0;
        if (i7 == 0 || i7 == 1) {
            TextPaint textPaint = bVar.f9021F;
            textPaint.setTextSize(bVar.f9038j);
            textPaint.setTypeface(bVar.f9045s);
            textPaint.setLetterSpacing(bVar.f9028M);
            return (int) (-textPaint.ascent());
        }
        if (i7 != 2) {
            return 0;
        }
        TextPaint textPaint2 = bVar.f9021F;
        textPaint2.setTextSize(bVar.f9038j);
        textPaint2.setTypeface(bVar.f9045s);
        textPaint2.setLetterSpacing(bVar.f9028M);
        return (int) ((-textPaint2.ascent()) / 2.0f);
    }

    public final boolean f() {
        return this.f12884L && !TextUtils.isEmpty(this.f12886M) && (this.f12890O instanceof j);
    }

    public final boolean g() {
        return this.o.getVisibility() == 0 && this.f12932s0.getVisibility() == 0;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.f12925p;
        if (editText == null) {
            return super.getBaseline();
        }
        return e() + getPaddingTop() + editText.getBaseline();
    }

    public g getBoxBackground() {
        int i7 = this.f12900T;
        if (i7 == 1 || i7 == 2) {
            return this.f12890O;
        }
        throw new IllegalStateException();
    }

    public int getBoxBackgroundColor() {
        return this.f12910c0;
    }

    public int getBoxBackgroundMode() {
        return this.f12900T;
    }

    public float getBoxCornerRadiusBottomEnd() {
        g gVar = this.f12890O;
        return gVar.l.f10708a.f10753h.a(gVar.d());
    }

    public float getBoxCornerRadiusBottomStart() {
        g gVar = this.f12890O;
        return gVar.l.f10708a.f10752g.a(gVar.d());
    }

    public float getBoxCornerRadiusTopEnd() {
        g gVar = this.f12890O;
        return gVar.l.f10708a.f10751f.a(gVar.d());
    }

    public float getBoxCornerRadiusTopStart() {
        g gVar = this.f12890O;
        return gVar.l.f10708a.f10750e.a(gVar.d());
    }

    public int getBoxStrokeColor() {
        return this.f12881J0;
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.f12883K0;
    }

    public int getBoxStrokeWidth() {
        return this.f12906W;
    }

    public int getBoxStrokeWidthFocused() {
        return this.f12908a0;
    }

    public int getCounterMaxLength() {
        return this.f12937v;
    }

    public CharSequence getCounterOverflowDescription() {
        C2218F c2218f;
        if (this.f12935u && this.f12939w && (c2218f = this.f12941x) != null) {
            return c2218f.getContentDescription();
        }
        return null;
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.f12873F;
    }

    public ColorStateList getCounterTextColor() {
        return this.f12873F;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.f12874F0;
    }

    public EditText getEditText() {
        return this.f12925p;
    }

    public CharSequence getEndIconContentDescription() {
        return this.f12932s0.getContentDescription();
    }

    public Drawable getEndIconDrawable() {
        return this.f12932s0.getDrawable();
    }

    public int getEndIconMode() {
        return this.f12928q0;
    }

    public CheckableImageButton getEndIconView() {
        return this.f12932s0;
    }

    public CharSequence getError() {
        r rVar = this.f12933t;
        if (rVar.f11183k) {
            return rVar.f11182j;
        }
        return null;
    }

    public CharSequence getErrorContentDescription() {
        return this.f12933t.f11184m;
    }

    public int getErrorCurrentTextColors() {
        C2218F c2218f = this.f12933t.l;
        if (c2218f != null) {
            return c2218f.getCurrentTextColor();
        }
        return -1;
    }

    public Drawable getErrorIconDrawable() {
        return this.f12870D0.getDrawable();
    }

    public final int getErrorTextCurrentColor() {
        C2218F c2218f = this.f12933t.l;
        if (c2218f != null) {
            return c2218f.getCurrentTextColor();
        }
        return -1;
    }

    public CharSequence getHelperText() {
        r rVar = this.f12933t;
        if (rVar.f11187q) {
            return rVar.f11186p;
        }
        return null;
    }

    public int getHelperTextCurrentTextColor() {
        C2218F c2218f = this.f12933t.f11188r;
        if (c2218f != null) {
            return c2218f.getCurrentTextColor();
        }
        return -1;
    }

    public CharSequence getHint() {
        if (this.f12884L) {
            return this.f12886M;
        }
        return null;
    }

    public final float getHintCollapsedTextHeight() {
        b bVar = this.f12897R0;
        TextPaint textPaint = bVar.f9021F;
        textPaint.setTextSize(bVar.f9038j);
        textPaint.setTypeface(bVar.f9045s);
        textPaint.setLetterSpacing(bVar.f9028M);
        return -textPaint.ascent();
    }

    public final int getHintCurrentCollapsedTextColor() {
        b bVar = this.f12897R0;
        return bVar.d(bVar.l);
    }

    public ColorStateList getHintTextColor() {
        return this.G0;
    }

    public int getMaxWidth() {
        return this.f12931s;
    }

    public int getMinWidth() {
        return this.f12929r;
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.f12932s0.getContentDescription();
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.f12932s0.getDrawable();
    }

    public CharSequence getPlaceholderText() {
        if (this.B) {
            return this.f12865A;
        }
        return null;
    }

    public int getPlaceholderTextAppearance() {
        return this.f12871E;
    }

    public ColorStateList getPlaceholderTextColor() {
        return this.f12869D;
    }

    public CharSequence getPrefixText() {
        return this.f12876H;
    }

    public ColorStateList getPrefixTextColor() {
        return this.f12878I.getTextColors();
    }

    public TextView getPrefixTextView() {
        return this.f12878I;
    }

    public CharSequence getStartIconContentDescription() {
        return this.f12915h0.getContentDescription();
    }

    public Drawable getStartIconDrawable() {
        return this.f12915h0.getDrawable();
    }

    public CharSequence getSuffixText() {
        return this.f12880J;
    }

    public ColorStateList getSuffixTextColor() {
        return this.f12882K.getTextColors();
    }

    public TextView getSuffixTextView() {
        return this.f12882K;
    }

    public Typeface getTypeface() {
        return this.f12914g0;
    }

    public final void h() {
        int i7 = this.f12900T;
        if (i7 != 0) {
            k kVar = this.f12894Q;
            if (i7 == 1) {
                this.f12890O = new g(kVar);
                this.f12892P = new g();
            } else {
                if (i7 != 2) {
                    throw new IllegalArgumentException(M5.b.h(new StringBuilder(), this.f12900T, " is illegal; only @BoxBackgroundMode constants are supported."));
                }
                if (!this.f12884L || (this.f12890O instanceof j)) {
                    this.f12890O = new g(kVar);
                } else {
                    this.f12890O = new j(kVar);
                }
                this.f12892P = null;
            }
        } else {
            this.f12890O = null;
            this.f12892P = null;
        }
        EditText editText = this.f12925p;
        if (editText != null && this.f12890O != null && editText.getBackground() == null && this.f12900T != 0) {
            EditText editText2 = this.f12925p;
            g gVar = this.f12890O;
            int[] iArr = H.f2303a;
            editText2.setBackground(gVar);
        }
        z();
        if (this.f12900T == 1) {
            if (getContext().getResources().getConfiguration().fontScale >= 2.0f) {
                this.f12902U = getResources().getDimensionPixelSize(R.dimen.material_font_2_0_box_collapsed_padding_top);
            } else if (com.bumptech.glide.c.p0(getContext())) {
                this.f12902U = getResources().getDimensionPixelSize(R.dimen.material_font_1_3_box_collapsed_padding_top);
            }
        }
        if (this.f12925p != null && this.f12900T == 1) {
            if (getContext().getResources().getConfiguration().fontScale >= 2.0f) {
                EditText editText3 = this.f12925p;
                int[] iArr2 = H.f2303a;
                editText3.setPaddingRelative(editText3.getPaddingStart(), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_2_0_padding_top), this.f12925p.getPaddingEnd(), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_2_0_padding_bottom));
            } else if (com.bumptech.glide.c.p0(getContext())) {
                EditText editText4 = this.f12925p;
                int[] iArr3 = H.f2303a;
                editText4.setPaddingRelative(editText4.getPaddingStart(), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_1_3_padding_top), this.f12925p.getPaddingEnd(), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_1_3_padding_bottom));
            }
        }
        if (this.f12900T != 0) {
            r();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i() {
        /*
            Method dump skipped, instruction units count: 242
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
        drawableMutate.setTintList(ColorStateList.valueOf(colorForState));
        checkableImageButton.setImageDrawable(drawableMutate);
    }

    public final void m(C2218F c2218f, int i7) {
        try {
            c2218f.setTextAppearance(i7);
            if (c2218f.getTextColors().getDefaultColor() != -65281) {
                return;
            }
        } catch (Exception unused) {
        }
        c2218f.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
        c2218f.setTextColor(getContext().getColor(R.color.design_error));
    }

    public final void n(int i7) {
        boolean z6 = this.f12939w;
        int i10 = this.f12937v;
        String string = null;
        if (i10 == -1) {
            this.f12941x.setText(String.valueOf(i7));
            this.f12941x.setContentDescription(null);
            this.f12939w = false;
        } else {
            this.f12939w = i7 > i10;
            Context context = getContext();
            this.f12941x.setContentDescription(context.getString(this.f12939w ? R.string.character_counter_overflowed_content_description : R.string.character_counter_content_description, Integer.valueOf(i7), Integer.valueOf(this.f12937v)));
            if (z6 != this.f12939w) {
                o();
            }
            D1.b bVarC = D1.b.c();
            C2218F c2218f = this.f12941x;
            String string2 = getContext().getString(R.string.character_counter_pattern, Integer.valueOf(i7), Integer.valueOf(this.f12937v));
            if (string2 == null) {
                bVarC.getClass();
            } else {
                bVarC.getClass();
                C0050y0 c0050y0 = D1.f.f1137a;
                string = bVarC.d(string2).toString();
            }
            c2218f.setText(string);
        }
        if (this.f12925p == null || z6 == this.f12939w) {
            return;
        }
        s(false, false);
        z();
        q();
    }

    public final void o() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        C2218F c2218f = this.f12941x;
        if (c2218f != null) {
            m(c2218f, this.f12939w ? this.f12943y : this.f12945z);
            if (!this.f12939w && (colorStateList2 = this.f12873F) != null) {
                this.f12941x.setTextColor(colorStateList2);
            }
            if (!this.f12939w || (colorStateList = this.f12875G) == null) {
                return;
            }
            this.f12941x.setTextColor(colorStateList);
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        super.onLayout(z6, i7, i10, i11, i12);
        EditText editText = this.f12925p;
        if (editText != null) {
            ThreadLocal threadLocal = U4.c.f9053a;
            int width = editText.getWidth();
            int height = editText.getHeight();
            Rect rect = this.f12911d0;
            rect.set(0, 0, width, height);
            ThreadLocal threadLocal2 = U4.c.f9053a;
            Matrix matrix = (Matrix) threadLocal2.get();
            if (matrix == null) {
                matrix = new Matrix();
                threadLocal2.set(matrix);
            } else {
                matrix.reset();
            }
            U4.c.a(this, editText, matrix);
            ThreadLocal threadLocal3 = U4.c.f9054b;
            RectF rectF = (RectF) threadLocal3.get();
            if (rectF == null) {
                rectF = new RectF();
                threadLocal3.set(rectF);
            }
            rectF.set(rect);
            matrix.mapRect(rectF);
            rect.set((int) (rectF.left + 0.5f), (int) (rectF.top + 0.5f), (int) (rectF.right + 0.5f), (int) (rectF.bottom + 0.5f));
            g gVar = this.f12892P;
            if (gVar != null) {
                int i13 = rect.bottom;
                gVar.setBounds(rect.left, i13 - this.f12908a0, rect.right, i13);
            }
            if (this.f12884L) {
                float textSize = this.f12925p.getTextSize();
                b bVar = this.f12897R0;
                if (bVar.f9037i != textSize) {
                    bVar.f9037i = textSize;
                    bVar.g();
                }
                int gravity = this.f12925p.getGravity();
                int i14 = (gravity & (-113)) | 48;
                if (bVar.f9036h != i14) {
                    bVar.f9036h = i14;
                    bVar.g();
                }
                if (bVar.f9035g != gravity) {
                    bVar.f9035g = gravity;
                    bVar.g();
                }
                if (this.f12925p == null) {
                    throw new IllegalStateException();
                }
                int[] iArr = H.f2303a;
                boolean z10 = getLayoutDirection() == 1;
                int i15 = rect.bottom;
                Rect rect2 = this.f12912e0;
                rect2.bottom = i15;
                int i16 = this.f12900T;
                C2218F c2218f = this.f12878I;
                if (i16 == 1) {
                    int compoundPaddingLeft = this.f12925p.getCompoundPaddingLeft() + rect.left;
                    if (this.f12876H != null && !z10) {
                        compoundPaddingLeft = (compoundPaddingLeft - c2218f.getMeasuredWidth()) + c2218f.getPaddingLeft();
                    }
                    rect2.left = compoundPaddingLeft;
                    rect2.top = rect.top + this.f12902U;
                    int compoundPaddingRight = rect.right - this.f12925p.getCompoundPaddingRight();
                    if (this.f12876H != null && z10) {
                        compoundPaddingRight += c2218f.getMeasuredWidth() - c2218f.getPaddingRight();
                    }
                    rect2.right = compoundPaddingRight;
                } else if (i16 != 2) {
                    int compoundPaddingLeft2 = this.f12925p.getCompoundPaddingLeft() + rect.left;
                    if (this.f12876H != null && !z10) {
                        compoundPaddingLeft2 = (compoundPaddingLeft2 - c2218f.getMeasuredWidth()) + c2218f.getPaddingLeft();
                    }
                    rect2.left = compoundPaddingLeft2;
                    rect2.top = getPaddingTop();
                    int compoundPaddingRight2 = rect.right - this.f12925p.getCompoundPaddingRight();
                    if (this.f12876H != null && z10) {
                        compoundPaddingRight2 += c2218f.getMeasuredWidth() - c2218f.getPaddingRight();
                    }
                    rect2.right = compoundPaddingRight2;
                } else {
                    rect2.left = this.f12925p.getPaddingLeft() + rect.left;
                    rect2.top = rect.top - e();
                    rect2.right = rect.right - this.f12925p.getPaddingRight();
                }
                int i17 = rect2.left;
                int i18 = rect2.top;
                int i19 = rect2.right;
                int i20 = rect2.bottom;
                Rect rect3 = bVar.f9033e;
                if (rect3.left != i17 || rect3.top != i18 || rect3.right != i19 || rect3.bottom != i20) {
                    rect3.set(i17, i18, i19, i20);
                    bVar.f9019D = true;
                    bVar.f();
                }
                if (this.f12925p == null) {
                    throw new IllegalStateException();
                }
                TextPaint textPaint = bVar.f9021F;
                textPaint.setTextSize(bVar.f9037i);
                textPaint.setTypeface(bVar.f9046t);
                textPaint.setLetterSpacing(0.0f);
                float f10 = -textPaint.ascent();
                rect2.left = this.f12925p.getCompoundPaddingLeft() + rect.left;
                rect2.top = (this.f12900T != 1 || this.f12925p.getMinLines() > 1) ? rect.top + this.f12925p.getCompoundPaddingTop() : (int) (rect.centerY() - (f10 / 2.0f));
                rect2.right = rect.right - this.f12925p.getCompoundPaddingRight();
                int compoundPaddingBottom = (this.f12900T != 1 || this.f12925p.getMinLines() > 1) ? rect.bottom - this.f12925p.getCompoundPaddingBottom() : (int) (rect2.top + f10);
                rect2.bottom = compoundPaddingBottom;
                int i21 = rect2.left;
                int i22 = rect2.top;
                int i23 = rect2.right;
                Rect rect4 = bVar.d;
                if (rect4.left != i21 || rect4.top != i22 || rect4.right != i23 || rect4.bottom != compoundPaddingBottom) {
                    rect4.set(i21, i22, i23, compoundPaddingBottom);
                    bVar.f9019D = true;
                    bVar.f();
                }
                bVar.g();
                if (!f() || this.f12895Q0) {
                    return;
                }
                i();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i7, int i10) {
        EditText editText;
        int iMax;
        super.onMeasure(i7, i10);
        boolean z6 = false;
        if (this.f12925p != null && this.f12925p.getMeasuredHeight() < (iMax = Math.max(this.f12922n.getMeasuredHeight(), this.f12920m.getMeasuredHeight()))) {
            this.f12925p.setMinimumHeight(iMax);
            z6 = true;
        }
        boolean zP = p();
        if (z6 || zP) {
            this.f12925p.post(new t(this, 1));
        }
        if (this.f12867C != null && (editText = this.f12925p) != null) {
            this.f12867C.setGravity(editText.getGravity());
            this.f12867C.setPadding(this.f12925p.getCompoundPaddingLeft(), this.f12925p.getCompoundPaddingTop(), this.f12925p.getCompoundPaddingRight(), this.f12925p.getCompoundPaddingBottom());
        }
        u();
        x();
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof v)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        v vVar = (v) parcelable;
        super.onRestoreInstanceState(vVar.l);
        setError(vVar.f11195n);
        if (vVar.o) {
            this.f12932s0.post(new t(this, 0));
        }
        setHint(vVar.f11196p);
        setHelperText(vVar.f11197q);
        setPlaceholderText(vVar.f11198r);
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        v vVar = new v(super.onSaveInstanceState());
        if (this.f12933t.e()) {
            vVar.f11195n = getError();
        }
        vVar.o = this.f12928q0 != 0 && this.f12932s0.o;
        vVar.f11196p = getHint();
        vVar.f11197q = getHelperText();
        vVar.f11198r = getPlaceholderText();
        return vVar;
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
        C2218F c2218f;
        EditText editText = this.f12925p;
        if (editText == null || this.f12900T != 0 || (background = editText.getBackground()) == null) {
            return;
        }
        int[] iArr = AbstractC2225M.f21440a;
        Drawable drawableMutate = background.mutate();
        r rVar = this.f12933t;
        if (rVar.e()) {
            C2218F c2218f2 = rVar.l;
            drawableMutate.setColorFilter(C2258q.b(c2218f2 != null ? c2218f2.getCurrentTextColor() : -1, PorterDuff.Mode.SRC_IN));
        } else if (this.f12939w && (c2218f = this.f12941x) != null) {
            drawableMutate.setColorFilter(C2258q.b(c2218f.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            drawableMutate.clearColorFilter();
            this.f12925p.refreshDrawableState();
        }
    }

    public final void r() {
        if (this.f12900T != 1) {
            FrameLayout frameLayout = this.l;
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
            int iE = e();
            if (iE != layoutParams.topMargin) {
                layoutParams.topMargin = iE;
                frameLayout.requestLayout();
            }
        }
    }

    public final void s(boolean z6, boolean z10) {
        ColorStateList colorStateList;
        C2218F c2218f;
        boolean zIsEnabled = isEnabled();
        EditText editText = this.f12925p;
        boolean z11 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.f12925p;
        boolean z12 = editText2 != null && editText2.hasFocus();
        r rVar = this.f12933t;
        boolean zE = rVar.e();
        ColorStateList colorStateList2 = this.f12874F0;
        b bVar = this.f12897R0;
        if (colorStateList2 != null) {
            bVar.h(colorStateList2);
            ColorStateList colorStateList3 = this.f12874F0;
            if (bVar.f9039k != colorStateList3) {
                bVar.f9039k = colorStateList3;
                bVar.g();
            }
        }
        if (!zIsEnabled) {
            ColorStateList colorStateList4 = this.f12874F0;
            int colorForState = colorStateList4 != null ? colorStateList4.getColorForState(new int[]{-16842910}, this.f12893P0) : this.f12893P0;
            bVar.h(ColorStateList.valueOf(colorForState));
            ColorStateList colorStateListValueOf = ColorStateList.valueOf(colorForState);
            if (bVar.f9039k != colorStateListValueOf) {
                bVar.f9039k = colorStateListValueOf;
                bVar.g();
            }
        } else if (zE) {
            C2218F c2218f2 = rVar.l;
            bVar.h(c2218f2 != null ? c2218f2.getTextColors() : null);
        } else if (this.f12939w && (c2218f = this.f12941x) != null) {
            bVar.h(c2218f.getTextColors());
        } else if (z12 && (colorStateList = this.G0) != null) {
            bVar.h(colorStateList);
        }
        if (z11 || !this.f12899S0 || (isEnabled() && z12)) {
            if (z10 || this.f12895Q0) {
                ValueAnimator valueAnimator = this.f12903U0;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.f12903U0.cancel();
                }
                if (z6 && this.f12901T0) {
                    a(1.0f);
                } else {
                    bVar.i(1.0f);
                }
                this.f12895Q0 = false;
                if (f()) {
                    i();
                }
                EditText editText3 = this.f12925p;
                t(editText3 != null ? editText3.getText().length() : 0);
                v();
                y();
                return;
            }
            return;
        }
        if (z10 || !this.f12895Q0) {
            ValueAnimator valueAnimator2 = this.f12903U0;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.f12903U0.cancel();
            }
            if (z6 && this.f12901T0) {
                a(0.0f);
            } else {
                bVar.i(0.0f);
            }
            if (f() && !((j) this.f12890O).f11154H.isEmpty() && f()) {
                ((j) this.f12890O).l(0.0f, 0.0f, 0.0f, 0.0f);
            }
            this.f12895Q0 = true;
            C2218F c2218f3 = this.f12867C;
            if (c2218f3 != null && this.B) {
                c2218f3.setText((CharSequence) null);
                this.f12867C.setVisibility(4);
            }
            v();
            y();
        }
    }

    public void setBoxBackgroundColor(int i7) {
        if (this.f12910c0 != i7) {
            this.f12910c0 = i7;
            this.f12885L0 = i7;
            this.f12889N0 = i7;
            this.f12891O0 = i7;
            b();
        }
    }

    public void setBoxBackgroundColorResource(int i7) {
        setBoxBackgroundColor(getContext().getColor(i7));
    }

    public void setBoxBackgroundColorStateList(ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.f12885L0 = defaultColor;
        this.f12910c0 = defaultColor;
        this.f12887M0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
        this.f12889N0 = colorStateList.getColorForState(new int[]{android.R.attr.state_focused, android.R.attr.state_enabled}, -1);
        this.f12891O0 = colorStateList.getColorForState(new int[]{android.R.attr.state_hovered, android.R.attr.state_enabled}, -1);
        b();
    }

    public void setBoxBackgroundMode(int i7) {
        if (i7 == this.f12900T) {
            return;
        }
        this.f12900T = i7;
        if (this.f12925p != null) {
            h();
        }
    }

    public void setBoxStrokeColor(int i7) {
        if (this.f12881J0 != i7) {
            this.f12881J0 = i7;
            z();
        }
    }

    public void setBoxStrokeColorStateList(ColorStateList colorStateList) {
        if (colorStateList.isStateful()) {
            this.f12877H0 = colorStateList.getDefaultColor();
            this.f12893P0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.f12879I0 = colorStateList.getColorForState(new int[]{android.R.attr.state_hovered, android.R.attr.state_enabled}, -1);
            this.f12881J0 = colorStateList.getColorForState(new int[]{android.R.attr.state_focused, android.R.attr.state_enabled}, -1);
        } else if (this.f12881J0 != colorStateList.getDefaultColor()) {
            this.f12881J0 = colorStateList.getDefaultColor();
        }
        z();
    }

    public void setBoxStrokeErrorColor(ColorStateList colorStateList) {
        if (this.f12883K0 != colorStateList) {
            this.f12883K0 = colorStateList;
            z();
        }
    }

    public void setBoxStrokeWidth(int i7) {
        this.f12906W = i7;
        z();
    }

    public void setBoxStrokeWidthFocused(int i7) {
        this.f12908a0 = i7;
        z();
    }

    public void setBoxStrokeWidthFocusedResource(int i7) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(i7));
    }

    public void setBoxStrokeWidthResource(int i7) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(i7));
    }

    public void setCounterEnabled(boolean z6) {
        if (this.f12935u != z6) {
            r rVar = this.f12933t;
            if (z6) {
                C2218F c2218f = new C2218F(getContext(), null);
                this.f12941x = c2218f;
                c2218f.setId(R.id.textinput_counter);
                Typeface typeface = this.f12914g0;
                if (typeface != null) {
                    this.f12941x.setTypeface(typeface);
                }
                this.f12941x.setMaxLines(1);
                rVar.a(this.f12941x, 2);
                ((ViewGroup.MarginLayoutParams) this.f12941x.getLayoutParams()).setMarginStart(getResources().getDimensionPixelOffset(R.dimen.mtrl_textinput_counter_margin_start));
                o();
                if (this.f12941x != null) {
                    EditText editText = this.f12925p;
                    n(editText == null ? 0 : editText.getText().length());
                }
            } else {
                rVar.h(this.f12941x, 2);
                this.f12941x = null;
            }
            this.f12935u = z6;
        }
    }

    public void setCounterMaxLength(int i7) {
        if (this.f12937v != i7) {
            if (i7 > 0) {
                this.f12937v = i7;
            } else {
                this.f12937v = -1;
            }
            if (!this.f12935u || this.f12941x == null) {
                return;
            }
            EditText editText = this.f12925p;
            n(editText == null ? 0 : editText.getText().length());
        }
    }

    public void setCounterOverflowTextAppearance(int i7) {
        if (this.f12943y != i7) {
            this.f12943y = i7;
            o();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList colorStateList) {
        if (this.f12875G != colorStateList) {
            this.f12875G = colorStateList;
            o();
        }
    }

    public void setCounterTextAppearance(int i7) {
        if (this.f12945z != i7) {
            this.f12945z = i7;
            o();
        }
    }

    public void setCounterTextColor(ColorStateList colorStateList) {
        if (this.f12873F != colorStateList) {
            this.f12873F = colorStateList;
            o();
        }
    }

    public void setDefaultHintTextColor(ColorStateList colorStateList) {
        this.f12874F0 = colorStateList;
        this.G0 = colorStateList;
        if (this.f12925p != null) {
            s(false, false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z6) {
        j(this, z6);
        super.setEnabled(z6);
    }

    public void setEndIconActivated(boolean z6) {
        this.f12932s0.setActivated(z6);
    }

    public void setEndIconCheckable(boolean z6) {
        this.f12932s0.setCheckable(z6);
    }

    public void setEndIconContentDescription(int i7) {
        setEndIconContentDescription(i7 != 0 ? getResources().getText(i7) : null);
    }

    public void setEndIconDrawable(int i7) {
        setEndIconDrawable(i7 != 0 ? h6.f.k(getContext(), i7) : null);
    }

    public void setEndIconMode(int i7) {
        int i10 = this.f12928q0;
        this.f12928q0 = i7;
        for (d dVar : this.f12934t0) {
            switch (dVar.f11141a) {
                case 0:
                    EditText editText = getEditText();
                    if (editText != null && i10 == 2) {
                        editText.post(new h5.t(10, dVar, editText, false));
                        if (editText.getOnFocusChangeListener() == ((h) dVar.f11142b).f11148e) {
                            editText.setOnFocusChangeListener(null);
                        }
                    }
                    break;
                case 1:
                    AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) getEditText();
                    if (autoCompleteTextView != null && i10 == 3) {
                        autoCompleteTextView.post(new h5.t(12, dVar, autoCompleteTextView, false));
                        if (autoCompleteTextView.getOnFocusChangeListener() == ((o) dVar.f11142b).f11160e) {
                            autoCompleteTextView.setOnFocusChangeListener(null);
                        }
                        autoCompleteTextView.setOnTouchListener(null);
                        autoCompleteTextView.setOnDismissListener(null);
                    }
                    break;
                default:
                    EditText editText2 = getEditText();
                    if (editText2 != null && i10 == 1) {
                        editText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        editText2.post(new h5.t(13, dVar, editText2, false));
                    }
                    break;
            }
        }
        setEndIconVisible(i7 != 0);
        if (!getEndIconDelegate().b(this.f12900T)) {
            throw new IllegalStateException("The current box background mode " + this.f12900T + " is not supported by the end icon mode " + i7);
        }
        getEndIconDelegate().a();
        c();
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        View.OnLongClickListener onLongClickListener = this.B0;
        CheckableImageButton checkableImageButton = this.f12932s0;
        checkableImageButton.setOnClickListener(onClickListener);
        l(checkableImageButton, onLongClickListener);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.B0 = onLongClickListener;
        CheckableImageButton checkableImageButton = this.f12932s0;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        l(checkableImageButton, onLongClickListener);
    }

    public void setEndIconTintList(ColorStateList colorStateList) {
        if (this.f12936u0 != colorStateList) {
            this.f12936u0 = colorStateList;
            this.f12938v0 = true;
            c();
        }
    }

    public void setEndIconTintMode(PorterDuff.Mode mode) {
        if (this.f12940w0 != mode) {
            this.f12940w0 = mode;
            this.f12942x0 = true;
            c();
        }
    }

    public void setEndIconVisible(boolean z6) {
        if (g() != z6) {
            this.f12932s0.setVisibility(z6 ? 0 : 8);
            x();
            p();
        }
    }

    public void setError(CharSequence charSequence) {
        r rVar = this.f12933t;
        if (!rVar.f11183k) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            rVar.g();
            return;
        }
        rVar.c();
        rVar.f11182j = charSequence;
        rVar.l.setText(charSequence);
        int i7 = rVar.f11180h;
        if (i7 != 1) {
            rVar.f11181i = 1;
        }
        rVar.j(i7, rVar.f11181i, rVar.i(rVar.l, charSequence));
    }

    public void setErrorContentDescription(CharSequence charSequence) {
        r rVar = this.f12933t;
        rVar.f11184m = charSequence;
        C2218F c2218f = rVar.l;
        if (c2218f != null) {
            c2218f.setContentDescription(charSequence);
        }
    }

    public void setErrorEnabled(boolean z6) {
        r rVar = this.f12933t;
        if (rVar.f11183k == z6) {
            return;
        }
        rVar.c();
        TextInputLayout textInputLayout = rVar.f11176b;
        if (z6) {
            C2218F c2218f = new C2218F(rVar.f11175a, null);
            rVar.l = c2218f;
            c2218f.setId(R.id.textinput_error);
            rVar.l.setTextAlignment(5);
            Typeface typeface = rVar.f11191u;
            if (typeface != null) {
                rVar.l.setTypeface(typeface);
            }
            int i7 = rVar.f11185n;
            rVar.f11185n = i7;
            C2218F c2218f2 = rVar.l;
            if (c2218f2 != null) {
                textInputLayout.m(c2218f2, i7);
            }
            ColorStateList colorStateList = rVar.o;
            rVar.o = colorStateList;
            C2218F c2218f3 = rVar.l;
            if (c2218f3 != null && colorStateList != null) {
                c2218f3.setTextColor(colorStateList);
            }
            CharSequence charSequence = rVar.f11184m;
            rVar.f11184m = charSequence;
            C2218F c2218f4 = rVar.l;
            if (c2218f4 != null) {
                c2218f4.setContentDescription(charSequence);
            }
            rVar.l.setVisibility(4);
            C2218F c2218f5 = rVar.l;
            int[] iArr = H.f2303a;
            c2218f5.setAccessibilityLiveRegion(1);
            rVar.a(rVar.l, 0);
        } else {
            rVar.g();
            rVar.h(rVar.l, 0);
            rVar.l = null;
            textInputLayout.q();
            textInputLayout.z();
        }
        rVar.f11183k = z6;
    }

    public void setErrorIconDrawable(int i7) {
        setErrorIconDrawable(i7 != 0 ? h6.f.k(getContext(), i7) : null);
        k(this.f12870D0, this.f12872E0);
    }

    public void setErrorIconOnClickListener(View.OnClickListener onClickListener) {
        View.OnLongClickListener onLongClickListener = this.f12868C0;
        CheckableImageButton checkableImageButton = this.f12870D0;
        checkableImageButton.setOnClickListener(onClickListener);
        l(checkableImageButton, onLongClickListener);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.f12868C0 = onLongClickListener;
        CheckableImageButton checkableImageButton = this.f12870D0;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        l(checkableImageButton, onLongClickListener);
    }

    public void setErrorIconTintList(ColorStateList colorStateList) {
        this.f12872E0 = colorStateList;
        CheckableImageButton checkableImageButton = this.f12870D0;
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null) {
            drawable = drawable.mutate();
            drawable.setTintList(colorStateList);
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    public void setErrorIconTintMode(PorterDuff.Mode mode) {
        CheckableImageButton checkableImageButton = this.f12870D0;
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null) {
            drawable = drawable.mutate();
            drawable.setTintMode(mode);
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    public void setErrorTextAppearance(int i7) {
        r rVar = this.f12933t;
        rVar.f11185n = i7;
        C2218F c2218f = rVar.l;
        if (c2218f != null) {
            rVar.f11176b.m(c2218f, i7);
        }
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        r rVar = this.f12933t;
        rVar.o = colorStateList;
        C2218F c2218f = rVar.l;
        if (c2218f == null || colorStateList == null) {
            return;
        }
        c2218f.setTextColor(colorStateList);
    }

    public void setExpandedHintEnabled(boolean z6) {
        if (this.f12899S0 != z6) {
            this.f12899S0 = z6;
            s(false, false);
        }
    }

    public void setHelperText(CharSequence charSequence) {
        boolean zIsEmpty = TextUtils.isEmpty(charSequence);
        r rVar = this.f12933t;
        if (zIsEmpty) {
            if (rVar.f11187q) {
                setHelperTextEnabled(false);
                return;
            }
            return;
        }
        if (!rVar.f11187q) {
            setHelperTextEnabled(true);
        }
        rVar.c();
        rVar.f11186p = charSequence;
        rVar.f11188r.setText(charSequence);
        int i7 = rVar.f11180h;
        if (i7 != 2) {
            rVar.f11181i = 2;
        }
        rVar.j(i7, rVar.f11181i, rVar.i(rVar.f11188r, charSequence));
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        r rVar = this.f12933t;
        rVar.f11190t = colorStateList;
        C2218F c2218f = rVar.f11188r;
        if (c2218f == null || colorStateList == null) {
            return;
        }
        c2218f.setTextColor(colorStateList);
    }

    public void setHelperTextEnabled(boolean z6) {
        r rVar = this.f12933t;
        if (rVar.f11187q == z6) {
            return;
        }
        rVar.c();
        if (z6) {
            C2218F c2218f = new C2218F(rVar.f11175a, null);
            rVar.f11188r = c2218f;
            c2218f.setId(R.id.textinput_helper_text);
            rVar.f11188r.setTextAlignment(5);
            Typeface typeface = rVar.f11191u;
            if (typeface != null) {
                rVar.f11188r.setTypeface(typeface);
            }
            rVar.f11188r.setVisibility(4);
            C2218F c2218f2 = rVar.f11188r;
            int[] iArr = H.f2303a;
            c2218f2.setAccessibilityLiveRegion(1);
            int i7 = rVar.f11189s;
            rVar.f11189s = i7;
            C2218F c2218f3 = rVar.f11188r;
            if (c2218f3 != null) {
                c2218f3.setTextAppearance(i7);
            }
            ColorStateList colorStateList = rVar.f11190t;
            rVar.f11190t = colorStateList;
            C2218F c2218f4 = rVar.f11188r;
            if (c2218f4 != null && colorStateList != null) {
                c2218f4.setTextColor(colorStateList);
            }
            rVar.a(rVar.f11188r, 1);
        } else {
            rVar.c();
            int i10 = rVar.f11180h;
            if (i10 == 2) {
                rVar.f11181i = 0;
            }
            rVar.j(i10, rVar.f11181i, rVar.i(rVar.f11188r, null));
            rVar.h(rVar.f11188r, 1);
            rVar.f11188r = null;
            TextInputLayout textInputLayout = rVar.f11176b;
            textInputLayout.q();
            textInputLayout.z();
        }
        rVar.f11187q = z6;
    }

    public void setHelperTextTextAppearance(int i7) {
        r rVar = this.f12933t;
        rVar.f11189s = i7;
        C2218F c2218f = rVar.f11188r;
        if (c2218f != null) {
            c2218f.setTextAppearance(i7);
        }
    }

    public void setHint(CharSequence charSequence) {
        if (this.f12884L) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z6) {
        this.f12901T0 = z6;
    }

    public void setHintEnabled(boolean z6) {
        if (z6 != this.f12884L) {
            this.f12884L = z6;
            if (z6) {
                CharSequence hint = this.f12925p.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.f12886M)) {
                        setHint(hint);
                    }
                    this.f12925p.setHint((CharSequence) null);
                }
                this.f12888N = true;
            } else {
                this.f12888N = false;
                if (!TextUtils.isEmpty(this.f12886M) && TextUtils.isEmpty(this.f12925p.getHint())) {
                    this.f12925p.setHint(this.f12886M);
                }
                setHintInternal(null);
            }
            if (this.f12925p != null) {
                r();
            }
        }
    }

    public void setHintTextAppearance(int i7) {
        b bVar = this.f12897R0;
        TextInputLayout textInputLayout = bVar.f9031a;
        V4.c cVar = new V4.c(textInputLayout.getContext(), i7);
        ColorStateList colorStateList = cVar.f9465a;
        if (colorStateList != null) {
            bVar.l = colorStateList;
        }
        float f10 = cVar.f9473k;
        if (f10 != 0.0f) {
            bVar.f9038j = f10;
        }
        ColorStateList colorStateList2 = cVar.f9466b;
        if (colorStateList2 != null) {
            bVar.f9027L = colorStateList2;
        }
        bVar.f9025J = cVar.f9468f;
        bVar.f9026K = cVar.f9469g;
        bVar.f9024I = cVar.f9470h;
        bVar.f9028M = cVar.f9472j;
        a aVar = bVar.f9048v;
        if (aVar != null) {
            aVar.f9462f = true;
        }
        C0420a c0420a = new C0420a(bVar);
        cVar.a();
        bVar.f9048v = new a(c0420a, cVar.f9475n);
        cVar.c(textInputLayout.getContext(), bVar.f9048v);
        bVar.g();
        this.G0 = bVar.l;
        if (this.f12925p != null) {
            s(false, false);
            r();
        }
    }

    public void setHintTextColor(ColorStateList colorStateList) {
        if (this.G0 != colorStateList) {
            if (this.f12874F0 == null) {
                this.f12897R0.h(colorStateList);
            }
            this.G0 = colorStateList;
            if (this.f12925p != null) {
                s(false, false);
            }
        }
    }

    public void setMaxWidth(int i7) {
        this.f12931s = i7;
        EditText editText = this.f12925p;
        if (editText == null || i7 == -1) {
            return;
        }
        editText.setMaxWidth(i7);
    }

    public void setMaxWidthResource(int i7) {
        setMaxWidth(getContext().getResources().getDimensionPixelSize(i7));
    }

    public void setMinWidth(int i7) {
        this.f12929r = i7;
        EditText editText = this.f12925p;
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
        setPasswordVisibilityToggleDrawable(i7 != 0 ? h6.f.k(getContext(), i7) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z6) {
        if (z6 && this.f12928q0 != 1) {
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
        this.f12936u0 = colorStateList;
        this.f12938v0 = true;
        c();
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        this.f12940w0 = mode;
        this.f12942x0 = true;
        c();
    }

    public void setPlaceholderText(CharSequence charSequence) {
        if (this.B && TextUtils.isEmpty(charSequence)) {
            setPlaceholderTextEnabled(false);
        } else {
            if (!this.B) {
                setPlaceholderTextEnabled(true);
            }
            this.f12865A = charSequence;
        }
        EditText editText = this.f12925p;
        t(editText != null ? editText.getText().length() : 0);
    }

    public void setPlaceholderTextAppearance(int i7) {
        this.f12871E = i7;
        C2218F c2218f = this.f12867C;
        if (c2218f != null) {
            c2218f.setTextAppearance(i7);
        }
    }

    public void setPlaceholderTextColor(ColorStateList colorStateList) {
        if (this.f12869D != colorStateList) {
            this.f12869D = colorStateList;
            C2218F c2218f = this.f12867C;
            if (c2218f == null || colorStateList == null) {
                return;
            }
            c2218f.setTextColor(colorStateList);
        }
    }

    public void setPrefixText(CharSequence charSequence) {
        this.f12876H = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.f12878I.setText(charSequence);
        v();
    }

    public void setPrefixTextAppearance(int i7) {
        this.f12878I.setTextAppearance(i7);
    }

    public void setPrefixTextColor(ColorStateList colorStateList) {
        this.f12878I.setTextColor(colorStateList);
    }

    public void setStartIconCheckable(boolean z6) {
        this.f12915h0.setCheckable(z6);
    }

    public void setStartIconContentDescription(int i7) {
        setStartIconContentDescription(i7 != 0 ? getResources().getText(i7) : null);
    }

    public void setStartIconDrawable(int i7) {
        setStartIconDrawable(i7 != 0 ? h6.f.k(getContext(), i7) : null);
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        View.OnLongClickListener onLongClickListener = this.f12924o0;
        CheckableImageButton checkableImageButton = this.f12915h0;
        checkableImageButton.setOnClickListener(onClickListener);
        l(checkableImageButton, onLongClickListener);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.f12924o0 = onLongClickListener;
        CheckableImageButton checkableImageButton = this.f12915h0;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        l(checkableImageButton, onLongClickListener);
    }

    public void setStartIconTintList(ColorStateList colorStateList) {
        if (this.f12916i0 != colorStateList) {
            this.f12916i0 = colorStateList;
            this.f12917j0 = true;
            d(this.f12915h0, true, colorStateList, this.f12919l0, this.f12918k0);
        }
    }

    public void setStartIconTintMode(PorterDuff.Mode mode) {
        if (this.f12918k0 != mode) {
            this.f12918k0 = mode;
            this.f12919l0 = true;
            d(this.f12915h0, this.f12917j0, this.f12916i0, true, mode);
        }
    }

    public void setStartIconVisible(boolean z6) {
        CheckableImageButton checkableImageButton = this.f12915h0;
        if ((checkableImageButton.getVisibility() == 0) != z6) {
            checkableImageButton.setVisibility(z6 ? 0 : 8);
            u();
            p();
        }
    }

    public void setSuffixText(CharSequence charSequence) {
        this.f12880J = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.f12882K.setText(charSequence);
        y();
    }

    public void setSuffixTextAppearance(int i7) {
        this.f12882K.setTextAppearance(i7);
    }

    public void setSuffixTextColor(ColorStateList colorStateList) {
        this.f12882K.setTextColor(colorStateList);
    }

    public void setTextInputAccessibilityDelegate(u uVar) {
        EditText editText = this.f12925p;
        if (editText != null) {
            H.g(editText, uVar);
        }
    }

    public void setTypeface(Typeface typeface) {
        boolean z6;
        if (typeface != this.f12914g0) {
            this.f12914g0 = typeface;
            b bVar = this.f12897R0;
            a aVar = bVar.f9048v;
            boolean z10 = true;
            if (aVar != null) {
                aVar.f9462f = true;
            }
            if (bVar.f9045s != typeface) {
                bVar.f9045s = typeface;
                z6 = true;
            } else {
                z6 = false;
            }
            if (bVar.f9046t != typeface) {
                bVar.f9046t = typeface;
            } else {
                z10 = false;
            }
            if (z6 || z10) {
                bVar.g();
            }
            r rVar = this.f12933t;
            if (typeface != rVar.f11191u) {
                rVar.f11191u = typeface;
                C2218F c2218f = rVar.l;
                if (c2218f != null) {
                    c2218f.setTypeface(typeface);
                }
                C2218F c2218f2 = rVar.f11188r;
                if (c2218f2 != null) {
                    c2218f2.setTypeface(typeface);
                }
            }
            C2218F c2218f3 = this.f12941x;
            if (c2218f3 != null) {
                c2218f3.setTypeface(typeface);
            }
        }
    }

    public final void t(int i7) {
        if (i7 != 0 || this.f12895Q0) {
            C2218F c2218f = this.f12867C;
            if (c2218f == null || !this.B) {
                return;
            }
            c2218f.setText((CharSequence) null);
            this.f12867C.setVisibility(4);
            return;
        }
        C2218F c2218f2 = this.f12867C;
        if (c2218f2 == null || !this.B) {
            return;
        }
        c2218f2.setText(this.f12865A);
        this.f12867C.setVisibility(0);
        this.f12867C.bringToFront();
    }

    public final void u() {
        int paddingStart;
        if (this.f12925p == null) {
            return;
        }
        if (this.f12915h0.getVisibility() == 0) {
            paddingStart = 0;
        } else {
            EditText editText = this.f12925p;
            int[] iArr = H.f2303a;
            paddingStart = editText.getPaddingStart();
        }
        C2218F c2218f = this.f12878I;
        int compoundPaddingTop = this.f12925p.getCompoundPaddingTop();
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.material_input_text_to_prefix_suffix_padding);
        int compoundPaddingBottom = this.f12925p.getCompoundPaddingBottom();
        int[] iArr2 = H.f2303a;
        c2218f.setPaddingRelative(paddingStart, compoundPaddingTop, dimensionPixelSize, compoundPaddingBottom);
    }

    public final void v() {
        this.f12878I.setVisibility((this.f12876H == null || this.f12895Q0) ? 8 : 0);
        p();
    }

    public final void w(boolean z6, boolean z10) {
        int defaultColor = this.f12883K0.getDefaultColor();
        int colorForState = this.f12883K0.getColorForState(new int[]{android.R.attr.state_hovered, android.R.attr.state_enabled}, defaultColor);
        int colorForState2 = this.f12883K0.getColorForState(new int[]{android.R.attr.state_activated, android.R.attr.state_enabled}, defaultColor);
        if (z6) {
            this.f12909b0 = colorForState2;
        } else if (z10) {
            this.f12909b0 = colorForState;
        } else {
            this.f12909b0 = defaultColor;
        }
    }

    public final void x() {
        int paddingEnd;
        if (this.f12925p == null) {
            return;
        }
        if (g() || this.f12870D0.getVisibility() == 0) {
            paddingEnd = 0;
        } else {
            EditText editText = this.f12925p;
            int[] iArr = H.f2303a;
            paddingEnd = editText.getPaddingEnd();
        }
        C2218F c2218f = this.f12882K;
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.material_input_text_to_prefix_suffix_padding);
        int paddingTop = this.f12925p.getPaddingTop();
        int paddingBottom = this.f12925p.getPaddingBottom();
        int[] iArr2 = H.f2303a;
        c2218f.setPaddingRelative(dimensionPixelSize, paddingTop, paddingEnd, paddingBottom);
    }

    public final void y() {
        C2218F c2218f = this.f12882K;
        int visibility = c2218f.getVisibility();
        boolean z6 = (this.f12880J == null || this.f12895Q0) ? false : true;
        c2218f.setVisibility(z6 ? 0 : 8);
        if (visibility != c2218f.getVisibility()) {
            getEndIconDelegate().c(z6);
        }
        p();
    }

    public final void z() {
        C2218F c2218f;
        EditText editText;
        EditText editText2;
        if (this.f12890O == null || this.f12900T == 0) {
            return;
        }
        boolean z6 = false;
        boolean z10 = isFocused() || ((editText2 = this.f12925p) != null && editText2.hasFocus());
        boolean z11 = isHovered() || ((editText = this.f12925p) != null && editText.isHovered());
        boolean zIsEnabled = isEnabled();
        r rVar = this.f12933t;
        if (!zIsEnabled) {
            this.f12909b0 = this.f12893P0;
        } else if (rVar.e()) {
            if (this.f12883K0 != null) {
                w(z10, z11);
            } else {
                C2218F c2218f2 = rVar.l;
                this.f12909b0 = c2218f2 != null ? c2218f2.getCurrentTextColor() : -1;
            }
        } else if (!this.f12939w || (c2218f = this.f12941x) == null) {
            if (z10) {
                this.f12909b0 = this.f12881J0;
            } else if (z11) {
                this.f12909b0 = this.f12879I0;
            } else {
                this.f12909b0 = this.f12877H0;
            }
        } else if (this.f12883K0 != null) {
            w(z10, z11);
        } else {
            this.f12909b0 = c2218f.getCurrentTextColor();
        }
        if (getErrorIconDrawable() != null && rVar.f11183k && rVar.e()) {
            z6 = true;
        }
        setErrorIconVisible(z6);
        k(this.f12870D0, this.f12872E0);
        k(this.f12915h0, this.f12916i0);
        ColorStateList colorStateList = this.f12936u0;
        CheckableImageButton checkableImageButton = this.f12932s0;
        k(checkableImageButton, colorStateList);
        p endIconDelegate = getEndIconDelegate();
        endIconDelegate.getClass();
        if (endIconDelegate instanceof o) {
            if (!rVar.e() || getEndIconDrawable() == null) {
                c();
            } else {
                Drawable drawableMutate = getEndIconDrawable().mutate();
                C2218F c2218f3 = rVar.l;
                drawableMutate.setTint(c2218f3 != null ? c2218f3.getCurrentTextColor() : -1);
                checkableImageButton.setImageDrawable(drawableMutate);
            }
        }
        if (z10 && isEnabled()) {
            this.f12904V = this.f12908a0;
        } else {
            this.f12904V = this.f12906W;
        }
        if (this.f12900T == 2 && f() && !this.f12895Q0 && this.f12898S != this.f12904V) {
            if (f()) {
                ((j) this.f12890O).l(0.0f, 0.0f, 0.0f, 0.0f);
            }
            i();
        }
        if (this.f12900T == 1) {
            if (!isEnabled()) {
                this.f12910c0 = this.f12887M0;
            } else if (z11 && !z10) {
                this.f12910c0 = this.f12891O0;
            } else if (z10) {
                this.f12910c0 = this.f12889N0;
            } else {
                this.f12910c0 = this.f12885L0;
            }
        }
        b();
    }

    public void setEndIconContentDescription(CharSequence charSequence) {
        if (getEndIconContentDescription() != charSequence) {
            this.f12932s0.setContentDescription(charSequence);
        }
    }

    public void setEndIconDrawable(Drawable drawable) {
        CheckableImageButton checkableImageButton = this.f12932s0;
        checkableImageButton.setImageDrawable(drawable);
        k(checkableImageButton, this.f12936u0);
    }

    public void setStartIconContentDescription(CharSequence charSequence) {
        if (getStartIconContentDescription() != charSequence) {
            this.f12915h0.setContentDescription(charSequence);
        }
    }

    public void setStartIconDrawable(Drawable drawable) {
        CheckableImageButton checkableImageButton = this.f12915h0;
        checkableImageButton.setImageDrawable(drawable);
        if (drawable != null) {
            setStartIconVisible(true);
            k(checkableImageButton, this.f12916i0);
        } else {
            setStartIconVisible(false);
            setStartIconOnClickListener(null);
            setStartIconOnLongClickListener(null);
            setStartIconContentDescription((CharSequence) null);
        }
    }

    public void setErrorIconDrawable(Drawable drawable) {
        this.f12870D0.setImageDrawable(drawable);
        setErrorIconVisible(drawable != null && this.f12933t.f11183k);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence charSequence) {
        this.f12932s0.setContentDescription(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable drawable) {
        this.f12932s0.setImageDrawable(drawable);
    }

    public void setHint(int i7) {
        setHint(i7 != 0 ? getResources().getText(i7) : null);
    }
}
