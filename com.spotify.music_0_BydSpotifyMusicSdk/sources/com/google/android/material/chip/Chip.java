package com.google.android.material.chip;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Locale;
import java.util.WeakHashMap;
import p.b3;
import p.ci1;
import p.f60;
import p.it2;
import p.jt2;
import p.kj2;
import p.ks;
import p.l13;
import p.ll1;
import p.ls;
import p.ms;
import p.ns;
import p.oc2;
import p.os;
import p.ot2;
import p.q30;
import p.v03;
import p.wl;
import p.wy2;
import p.x7;
import p.xy2;
import p.xz1;
import p.yj2;
import p.za0;
import p.zc2;
import p.zj2;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class Chip extends x7 implements ns, zc2 {
    public static final Rect D = new Rect();
    public static final int[] E = {R.attr.state_selected};
    public static final int[] F = {R.attr.state_checkable};
    public final Rect A;
    public final RectF B;
    public final ks C;
    public os m;
    public InsetDrawable n;
    public RippleDrawable o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public View.OnClickListener f32p;
    public CompoundButton.OnCheckedChangeListener q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;
    public int w;
    public int x;
    public final ms y;
    public boolean z;

    public Chip(Context context, AttributeSet attributeSet) {
        int resourceId;
        int resourceId2;
        int resourceId3;
        super(f60.S(context, attributeSet, com.spotify.music.R.attr.chipStyle, com.spotify.music.R.style.Widget_MaterialComponents_Chip_Action), attributeSet, com.spotify.music.R.attr.chipStyle);
        this.A = new Rect();
        this.B = new RectF();
        this.C = new ks(0, this);
        Context context2 = getContext();
        if (attributeSet != null) {
            attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "background");
            if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableLeft") != null) {
                throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
            }
            if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableStart") != null) {
                throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
            }
            if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableEnd") != null) {
                throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            }
            if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableRight") != null) {
                throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            }
            if (!attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/android", "singleLine", true) || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "lines", 1) != 1 || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "minLines", 1) != 1 || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLines", 1) != 1) {
                throw new UnsupportedOperationException("Chip does not support multi-line text");
            }
            attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "gravity", 8388627);
        }
        os osVar = new os(context2, attributeSet);
        Context context3 = osVar.m0;
        int[] iArr = xz1.b;
        TypedArray typedArrayE = v03.E(context3, attributeSet, iArr, com.spotify.music.R.attr.chipStyle, com.spotify.music.R.style.Widget_MaterialComponents_Chip_Action, new int[0]);
        osVar.N0 = typedArrayE.hasValue(37);
        Context context4 = osVar.m0;
        ColorStateList colorStateListV = ci1.v(context4, typedArrayE, 24);
        if (osVar.F != colorStateListV) {
            osVar.F = colorStateListV;
            osVar.onStateChange(osVar.getState());
        }
        ColorStateList colorStateListV2 = ci1.v(context4, typedArrayE, 11);
        if (osVar.G != colorStateListV2) {
            osVar.G = colorStateListV2;
            osVar.onStateChange(osVar.getState());
        }
        float dimension = typedArrayE.getDimension(19, 0.0f);
        if (osVar.H != dimension) {
            osVar.H = dimension;
            osVar.invalidateSelf();
            osVar.v();
        }
        if (typedArrayE.hasValue(12)) {
            osVar.B(typedArrayE.getDimension(12, 0.0f));
        }
        osVar.G(ci1.v(context4, typedArrayE, 22));
        osVar.H(typedArrayE.getDimension(23, 0.0f));
        osVar.Q(ci1.v(context4, typedArrayE, 36));
        String text = typedArrayE.getText(5);
        text = text == null ? "" : text;
        if (!TextUtils.equals(osVar.M, text)) {
            osVar.M = text;
            osVar.s0.d = true;
            osVar.invalidateSelf();
            osVar.v();
        }
        kj2 kj2Var = (!typedArrayE.hasValue(0) || (resourceId3 = typedArrayE.getResourceId(0, 0)) == 0) ? null : new kj2(context4, resourceId3);
        kj2Var.k = typedArrayE.getDimension(1, kj2Var.k);
        osVar.R(kj2Var);
        int i = typedArrayE.getInt(3, 0);
        if (i == 1) {
            osVar.K0 = TextUtils.TruncateAt.START;
        } else if (i == 2) {
            osVar.K0 = TextUtils.TruncateAt.MIDDLE;
        } else if (i == 3) {
            osVar.K0 = TextUtils.TruncateAt.END;
        }
        osVar.F(typedArrayE.getBoolean(18, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") == null) {
            osVar.F(typedArrayE.getBoolean(15, false));
        }
        osVar.C(ci1.x(context4, typedArrayE, 14));
        if (typedArrayE.hasValue(17)) {
            osVar.E(ci1.v(context4, typedArrayE, 17));
        }
        osVar.D(typedArrayE.getDimension(16, -1.0f));
        osVar.N(typedArrayE.getBoolean(31, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") == null) {
            osVar.N(typedArrayE.getBoolean(26, false));
        }
        osVar.I(ci1.x(context4, typedArrayE, 25));
        osVar.M(ci1.v(context4, typedArrayE, 30));
        osVar.K(typedArrayE.getDimension(28, 0.0f));
        osVar.x(typedArrayE.getBoolean(6, false));
        osVar.A(typedArrayE.getBoolean(10, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") == null) {
            osVar.A(typedArrayE.getBoolean(8, false));
        }
        osVar.y(ci1.x(context4, typedArrayE, 7));
        if (typedArrayE.hasValue(9)) {
            osVar.z(ci1.v(context4, typedArrayE, 9));
        }
        osVar.c0 = (!typedArrayE.hasValue(39) || (resourceId2 = typedArrayE.getResourceId(39, 0)) == 0) ? null : ll1.a(context4, resourceId2);
        osVar.d0 = (!typedArrayE.hasValue(33) || (resourceId = typedArrayE.getResourceId(33, 0)) == 0) ? null : ll1.a(context4, resourceId);
        float dimension2 = typedArrayE.getDimension(21, 0.0f);
        if (osVar.e0 != dimension2) {
            osVar.e0 = dimension2;
            osVar.invalidateSelf();
            osVar.v();
        }
        osVar.P(typedArrayE.getDimension(35, 0.0f));
        osVar.O(typedArrayE.getDimension(34, 0.0f));
        float dimension3 = typedArrayE.getDimension(41, 0.0f);
        if (osVar.h0 != dimension3) {
            osVar.h0 = dimension3;
            osVar.invalidateSelf();
            osVar.v();
        }
        float dimension4 = typedArrayE.getDimension(40, 0.0f);
        if (osVar.i0 != dimension4) {
            osVar.i0 = dimension4;
            osVar.invalidateSelf();
            osVar.v();
        }
        osVar.L(typedArrayE.getDimension(29, 0.0f));
        osVar.J(typedArrayE.getDimension(27, 0.0f));
        float dimension5 = typedArrayE.getDimension(13, 0.0f);
        if (osVar.l0 != dimension5) {
            osVar.l0 = dimension5;
            osVar.invalidateSelf();
            osVar.v();
        }
        osVar.M0 = typedArrayE.getDimensionPixelSize(4, Integer.MAX_VALUE);
        typedArrayE.recycle();
        v03.e(context2, attributeSet, com.spotify.music.R.attr.chipStyle, com.spotify.music.R.style.Widget_MaterialComponents_Chip_Action);
        v03.l(context2, attributeSet, iArr, com.spotify.music.R.attr.chipStyle, com.spotify.music.R.style.Widget_MaterialComponents_Chip_Action, new int[0]);
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, com.spotify.music.R.attr.chipStyle, com.spotify.music.R.style.Widget_MaterialComponents_Chip_Action);
        this.v = typedArrayObtainStyledAttributes.getBoolean(32, false);
        this.x = (int) Math.ceil(typedArrayObtainStyledAttributes.getDimension(20, (float) Math.ceil(TypedValue.applyDimension(1, 48, getContext().getResources().getDisplayMetrics()))));
        typedArrayObtainStyledAttributes.recycle();
        setChipDrawable(osVar);
        osVar.j(ot2.i(this));
        v03.e(context2, attributeSet, com.spotify.music.R.attr.chipStyle, com.spotify.music.R.style.Widget_MaterialComponents_Chip_Action);
        v03.l(context2, attributeSet, iArr, com.spotify.music.R.attr.chipStyle, com.spotify.music.R.style.Widget_MaterialComponents_Chip_Action, new int[0]);
        TypedArray typedArrayObtainStyledAttributes2 = context2.obtainStyledAttributes(attributeSet, iArr, com.spotify.music.R.attr.chipStyle, com.spotify.music.R.style.Widget_MaterialComponents_Chip_Action);
        boolean zHasValue = typedArrayObtainStyledAttributes2.hasValue(37);
        typedArrayObtainStyledAttributes2.recycle();
        this.y = new ms(this, this);
        e();
        if (!zHasValue) {
            setOutlineProvider(new ls(this));
        }
        setChecked(this.r);
        setText(osVar.M);
        setEllipsize(osVar.K0);
        h();
        if (!this.m.L0) {
            setLines(1);
            setHorizontallyScrolling(true);
        }
        setGravity(8388627);
        g();
        if (this.v) {
            setMinHeight(this.x);
        }
        this.w = jt2.d(this);
    }

    private RectF getCloseIconTouchBounds() {
        RectF rectF = this.B;
        rectF.setEmpty();
        if (c() && this.f32p != null) {
            os osVar = this.m;
            Rect bounds = osVar.getBounds();
            rectF.setEmpty();
            if (osVar.U()) {
                float f = osVar.l0 + osVar.k0 + osVar.W + osVar.j0 + osVar.i0;
                if (za0.a(osVar) == 0) {
                    float f2 = bounds.right;
                    rectF.right = f2;
                    rectF.left = f2 - f;
                } else {
                    float f3 = bounds.left;
                    rectF.left = f3;
                    rectF.right = f3 + f;
                }
                rectF.top = bounds.top;
                rectF.bottom = bounds.bottom;
            }
        }
        return rectF;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect getCloseIconTouchBoundsInt() {
        RectF closeIconTouchBounds = getCloseIconTouchBounds();
        int i = (int) closeIconTouchBounds.left;
        int i2 = (int) closeIconTouchBounds.top;
        int i3 = (int) closeIconTouchBounds.right;
        int i4 = (int) closeIconTouchBounds.bottom;
        Rect rect = this.A;
        rect.set(i, i2, i3, i4);
        return rect;
    }

    private kj2 getTextAppearance() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.s0.f;
        }
        return null;
    }

    private void setCloseIconHovered(boolean z) {
        if (this.t != z) {
            this.t = z;
            refreshDrawableState();
        }
    }

    private void setCloseIconPressed(boolean z) {
        if (this.s != z) {
            this.s = z;
            refreshDrawableState();
        }
    }

    public final void b(int i) {
        this.x = i;
        if (!this.v) {
            InsetDrawable insetDrawable = this.n;
            if (insetDrawable == null) {
                f();
                return;
            } else {
                if (insetDrawable != null) {
                    this.n = null;
                    setMinWidth(0);
                    setMinHeight((int) getChipMinHeight());
                    f();
                    return;
                }
                return;
            }
        }
        int iMax = Math.max(0, i - ((int) this.m.H));
        int iMax2 = Math.max(0, i - this.m.getIntrinsicWidth());
        if (iMax2 <= 0 && iMax <= 0) {
            InsetDrawable insetDrawable2 = this.n;
            if (insetDrawable2 == null) {
                f();
                return;
            } else {
                if (insetDrawable2 != null) {
                    this.n = null;
                    setMinWidth(0);
                    setMinHeight((int) getChipMinHeight());
                    f();
                    return;
                }
                return;
            }
        }
        int i2 = iMax2 > 0 ? iMax2 / 2 : 0;
        int i3 = iMax > 0 ? iMax / 2 : 0;
        if (this.n != null) {
            Rect rect = new Rect();
            this.n.getPadding(rect);
            if (rect.top == i3 && rect.bottom == i3 && rect.left == i2 && rect.right == i2) {
                f();
                return;
            }
        }
        if (getMinHeight() != i) {
            setMinHeight(i);
        }
        if (getMinWidth() != i) {
            setMinWidth(i);
        }
        this.n = new InsetDrawable((Drawable) this.m, i2, i3, i2, i3);
        f();
    }

    public final boolean c() {
        os osVar = this.m;
        if (osVar != null) {
            Object obj = osVar.T;
            if (obj == null) {
                obj = null;
            } else if (obj instanceof wy2) {
                ((xy2) ((wy2) obj)).getClass();
                obj = null;
            }
            if (obj != null) {
                return true;
            }
        }
        return false;
    }

    public final boolean d() {
        os osVar = this.m;
        return osVar != null && osVar.Y;
    }

    @Override // android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        int i;
        if (!this.z) {
            return super.dispatchHoverEvent(motionEvent);
        }
        ms msVar = this.y;
        AccessibilityManager accessibilityManager = msVar.h;
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            int action = motionEvent.getAction();
            if (action == 7 || action == 9) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                Chip chip = msVar.q;
                int i2 = (chip.c() && chip.getCloseIconTouchBounds().contains(x, y)) ? 1 : 0;
                int i3 = msVar.m;
                if (i3 != i2) {
                    msVar.m = i2;
                    msVar.q(i2, 128);
                    msVar.q(i3, 256);
                }
                if (i2 != Integer.MIN_VALUE) {
                    return true;
                }
            } else if (action == 10 && (i = msVar.m) != Integer.MIN_VALUE) {
                if (i == Integer.MIN_VALUE) {
                    return true;
                }
                msVar.m = Integer.MIN_VALUE;
                msVar.q(Integer.MIN_VALUE, 128);
                msVar.q(i, 256);
                return true;
            }
        }
        return super.dispatchHoverEvent(motionEvent);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a3 A[RETURN] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean dispatchKeyEvent(android.view.KeyEvent r10) {
        /*
            r9 = this;
            boolean r0 = r9.z
            if (r0 != 0) goto L9
            boolean r10 = super.dispatchKeyEvent(r10)
            return r10
        L9:
            p.ms r0 = r9.y
            r0.getClass()
            int r1 = r10.getAction()
            r2 = 1
            if (r1 == r2) goto La4
            int r1 = r10.getKeyCode()
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = 61
            r5 = 0
            if (r1 == r4) goto L87
            r4 = 0
            r6 = 66
            if (r1 == r6) goto L56
            switch(r1) {
                case 19: goto L2a;
                case 20: goto L2a;
                case 21: goto L2a;
                case 22: goto L2a;
                case 23: goto L56;
                default: goto L28;
            }
        L28:
            goto La4
        L2a:
            boolean r7 = r10.hasNoModifiers()
            if (r7 == 0) goto La4
            r7 = 19
            if (r1 == r7) goto L42
            r7 = 21
            if (r1 == r7) goto L3f
            r7 = 22
            if (r1 == r7) goto L44
            r6 = 130(0x82, float:1.82E-43)
            goto L44
        L3f:
            r6 = 17
            goto L44
        L42:
            r6 = 33
        L44:
            int r1 = r10.getRepeatCount()
            int r1 = r1 + r2
            r7 = r4
        L4a:
            if (r4 >= r1) goto L9d
            boolean r8 = r0.m(r6, r5)
            if (r8 == 0) goto L9d
            int r4 = r4 + 1
            r7 = r2
            goto L4a
        L56:
            boolean r1 = r10.hasNoModifiers()
            if (r1 == 0) goto La4
            int r1 = r10.getRepeatCount()
            if (r1 != 0) goto La4
            int r1 = r0.l
            if (r1 == r3) goto L9f
            com.google.android.material.chip.Chip r5 = r0.q
            if (r1 != 0) goto L6f
            boolean r1 = r5.performClick()
            goto L9f
        L6f:
            if (r1 != r2) goto L9f
            r5.playSoundEffect(r4)
            android.view.View$OnClickListener r1 = r5.f32p
            if (r1 == 0) goto L7c
            r1.onClick(r5)
            r4 = r2
        L7c:
            boolean r1 = r5.z
            if (r1 == 0) goto L85
            p.ms r1 = r5.y
            r1.q(r2, r2)
        L85:
            r1 = r4
            goto L9f
        L87:
            boolean r1 = r10.hasNoModifiers()
            if (r1 == 0) goto L93
            r1 = 2
            boolean r7 = r0.m(r1, r5)
            goto L9d
        L93:
            boolean r1 = r10.hasModifiers(r2)
            if (r1 == 0) goto La4
            boolean r7 = r0.m(r2, r5)
        L9d:
            if (r7 == 0) goto La4
        L9f:
            int r0 = r0.l
            if (r0 == r3) goto La4
            return r2
        La4:
            boolean r10 = super.dispatchKeyEvent(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [boolean, int] */
    @Override // p.x7, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        os osVar = this.m;
        if (osVar == null || !os.u(osVar.T)) {
            return;
        }
        os osVar2 = this.m;
        ?? IsEnabled = isEnabled();
        int i = IsEnabled;
        if (this.u) {
            i = IsEnabled + 1;
        }
        int i2 = i;
        if (this.t) {
            i2 = i + 1;
        }
        int i3 = i2;
        if (this.s) {
            i3 = i2 + 1;
        }
        int i4 = i3;
        if (isChecked()) {
            i4 = i3 + 1;
        }
        int[] iArr = new int[i4];
        int i5 = 0;
        if (isEnabled()) {
            iArr[0] = 16842910;
            i5 = 1;
        }
        if (this.u) {
            iArr[i5] = 16842908;
            i5++;
        }
        if (this.t) {
            iArr[i5] = 16843623;
            i5++;
        }
        if (this.s) {
            iArr[i5] = 16842919;
            i5++;
        }
        if (isChecked()) {
            iArr[i5] = 16842913;
        }
        if (Arrays.equals(osVar2.G0, iArr)) {
            return;
        }
        osVar2.G0 = iArr;
        if (osVar2.U() && osVar2.w(osVar2.getState(), iArr)) {
            invalidate();
        }
    }

    public final void e() {
        os osVar;
        if (!c() || (osVar = this.m) == null || !osVar.S || this.f32p == null) {
            zt2.k(this, null);
            this.z = false;
        } else {
            zt2.k(this, this.y);
            this.z = true;
        }
    }

    public final void f() {
        this.o = new RippleDrawable(q30.t(this.m.L), getBackgroundDrawable(), null);
        os osVar = this.m;
        if (osVar.H0) {
            osVar.H0 = false;
            osVar.I0 = null;
            osVar.onStateChange(osVar.getState());
        }
        RippleDrawable rippleDrawable = this.o;
        WeakHashMap weakHashMap = zt2.a;
        it2.q(this, rippleDrawable);
        g();
    }

    public final void g() {
        os osVar;
        if (TextUtils.isEmpty(getText()) || (osVar = this.m) == null) {
            return;
        }
        int iR = (int) (osVar.r() + osVar.l0 + osVar.i0);
        os osVar2 = this.m;
        int iQ = (int) (osVar2.q() + osVar2.e0 + osVar2.h0);
        if (this.n != null) {
            Rect rect = new Rect();
            this.n.getPadding(rect);
            iQ += rect.left;
            iR += rect.right;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        WeakHashMap weakHashMap = zt2.a;
        jt2.k(this, iQ, paddingTop, iR, paddingBottom);
    }

    @Override // android.widget.CheckBox, android.widget.CompoundButton, android.widget.Button, android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        if (!d()) {
            return isClickable() ? "android.widget.Button" : "android.view.View";
        }
        ViewParent parent = getParent();
        return ((parent instanceof ChipGroup) && ((ChipGroup) parent).o) ? "android.widget.RadioButton" : "android.widget.CompoundButton";
    }

    public Drawable getBackgroundDrawable() {
        InsetDrawable insetDrawable = this.n;
        return insetDrawable == null ? this.m : insetDrawable;
    }

    public Drawable getCheckedIcon() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.a0;
        }
        return null;
    }

    public ColorStateList getCheckedIconTint() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.b0;
        }
        return null;
    }

    public ColorStateList getChipBackgroundColor() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.G;
        }
        return null;
    }

    public float getChipCornerRadius() {
        os osVar = this.m;
        if (osVar != null) {
            return Math.max(0.0f, osVar.s());
        }
        return 0.0f;
    }

    public Drawable getChipDrawable() {
        return this.m;
    }

    public float getChipEndPadding() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.l0;
        }
        return 0.0f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Drawable getChipIcon() {
        Drawable drawable;
        os osVar = this.m;
        if (osVar == null || (drawable = osVar.O) == 0) {
            return null;
        }
        if (!(drawable instanceof wy2)) {
            return drawable;
        }
        ((xy2) ((wy2) drawable)).getClass();
        return null;
    }

    public float getChipIconSize() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.Q;
        }
        return 0.0f;
    }

    public ColorStateList getChipIconTint() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.P;
        }
        return null;
    }

    public float getChipMinHeight() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.H;
        }
        return 0.0f;
    }

    public float getChipStartPadding() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.e0;
        }
        return 0.0f;
    }

    public ColorStateList getChipStrokeColor() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.J;
        }
        return null;
    }

    public float getChipStrokeWidth() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.K;
        }
        return 0.0f;
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Drawable getCloseIcon() {
        Drawable drawable;
        os osVar = this.m;
        if (osVar == null || (drawable = osVar.T) == 0) {
            return null;
        }
        if (!(drawable instanceof wy2)) {
            return drawable;
        }
        ((xy2) ((wy2) drawable)).getClass();
        return null;
    }

    public CharSequence getCloseIconContentDescription() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.X;
        }
        return null;
    }

    public float getCloseIconEndPadding() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.k0;
        }
        return 0.0f;
    }

    public float getCloseIconSize() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.W;
        }
        return 0.0f;
    }

    public float getCloseIconStartPadding() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.j0;
        }
        return 0.0f;
    }

    public ColorStateList getCloseIconTint() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.V;
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextUtils.TruncateAt getEllipsize() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.K0;
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public final void getFocusedRect(Rect rect) {
        if (this.z) {
            ms msVar = this.y;
            if (msVar.l == 1 || msVar.k == 1) {
                rect.set(getCloseIconTouchBoundsInt());
                return;
            }
        }
        super.getFocusedRect(rect);
    }

    public ll1 getHideMotionSpec() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.d0;
        }
        return null;
    }

    public float getIconEndPadding() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.g0;
        }
        return 0.0f;
    }

    public float getIconStartPadding() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.f0;
        }
        return 0.0f;
    }

    public ColorStateList getRippleColor() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.L;
        }
        return null;
    }

    public oc2 getShapeAppearanceModel() {
        return this.m.c.a;
    }

    public ll1 getShowMotionSpec() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.c0;
        }
        return null;
    }

    public float getTextEndPadding() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.i0;
        }
        return 0.0f;
    }

    public float getTextStartPadding() {
        os osVar = this.m;
        if (osVar != null) {
            return osVar.h0;
        }
        return 0.0f;
    }

    public final void h() {
        TextPaint paint = getPaint();
        os osVar = this.m;
        if (osVar != null) {
            paint.drawableState = osVar.getState();
        }
        kj2 textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.e(getContext(), paint, this.C);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        f60.I(this, this.m);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, E);
        }
        if (d()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, F);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (this.z) {
            ms msVar = this.y;
            int i2 = msVar.l;
            if (i2 != Integer.MIN_VALUE) {
                msVar.j(i2);
            }
            if (z) {
                msVar.m(i, rect);
            }
        }
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7) {
            setCloseIconHovered(getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()));
        } else if (actionMasked == 10) {
            setCloseIconHovered(false);
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int i;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getAccessibilityClassName());
        accessibilityNodeInfo.setCheckable(d());
        accessibilityNodeInfo.setClickable(isClickable());
        if (getParent() instanceof ChipGroup) {
            ChipGroup chipGroup = (ChipGroup) getParent();
            if (chipGroup.k) {
                i = 0;
                for (int i2 = 0; i2 < chipGroup.getChildCount(); i2++) {
                    if (chipGroup.getChildAt(i2) instanceof Chip) {
                        if (((Chip) chipGroup.getChildAt(i2)) == this) {
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                i = -1;
            } else {
                i = -1;
            }
            Object tag = getTag(com.spotify.music.R.id.row_index_key);
            accessibilityNodeInfo.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) b3.a(tag instanceof Integer ? ((Integer) tag).intValue() : -1, 1, i, 1, isChecked()).a);
        }
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    public final PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        if (getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) && isEnabled()) {
            return PointerIcon.getSystemIcon(getContext(), 1002);
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        if (this.w != i) {
            this.w = i;
            g();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
    
        if (r0 != 3) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionMasked()
            android.graphics.RectF r1 = r5.getCloseIconTouchBounds()
            float r2 = r6.getX()
            float r3 = r6.getY()
            boolean r1 = r1.contains(r2, r3)
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L4b
            if (r0 == r3) goto L2b
            r4 = 2
            if (r0 == r4) goto L21
            r1 = 3
            if (r0 == r1) goto L44
            goto L51
        L21:
            boolean r0 = r5.s
            if (r0 == 0) goto L51
            if (r1 != 0) goto L57
            r5.setCloseIconPressed(r2)
            goto L57
        L2b:
            boolean r0 = r5.s
            if (r0 == 0) goto L44
            r5.playSoundEffect(r2)
            android.view.View$OnClickListener r0 = r5.f32p
            if (r0 == 0) goto L39
            r0.onClick(r5)
        L39:
            boolean r0 = r5.z
            if (r0 == 0) goto L42
            p.ms r0 = r5.y
            r0.q(r3, r3)
        L42:
            r0 = r3
            goto L45
        L44:
            r0 = r2
        L45:
            r5.setCloseIconPressed(r2)
            if (r0 != 0) goto L57
            goto L51
        L4b:
            if (r1 == 0) goto L51
            r5.setCloseIconPressed(r3)
            goto L57
        L51:
            boolean r6 = super.onTouchEvent(r6)
            if (r6 == 0) goto L58
        L57:
            r2 = r3
        L58:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.o) {
            super.setBackground(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
    }

    @Override // p.x7, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.o) {
            super.setBackgroundDrawable(drawable);
        }
    }

    @Override // p.x7, android.view.View
    public void setBackgroundResource(int i) {
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
    }

    public void setCheckable(boolean z) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.x(z);
        }
    }

    public void setCheckableResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.x(osVar.m0.getResources().getBoolean(i));
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        os osVar = this.m;
        if (osVar == null) {
            this.r = z;
            return;
        }
        if (osVar.Y) {
            boolean zIsChecked = isChecked();
            super.setChecked(z);
            if (zIsChecked == z || (onCheckedChangeListener = this.q) == null) {
                return;
            }
            onCheckedChangeListener.onCheckedChanged(this, z);
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.y(drawable);
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z) {
        setCheckedIconVisible(z);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int i) {
        setCheckedIconVisible(i);
    }

    public void setCheckedIconResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.y(l13.f(osVar.m0, i));
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.z(colorStateList);
        }
    }

    public void setCheckedIconTintResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.z(l13.d(osVar.m0, i));
        }
    }

    public void setCheckedIconVisible(boolean z) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.A(z);
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        os osVar = this.m;
        if (osVar == null || osVar.G == colorStateList) {
            return;
        }
        osVar.G = colorStateList;
        osVar.onStateChange(osVar.getState());
    }

    public void setChipBackgroundColorResource(int i) {
        ColorStateList colorStateListD;
        os osVar = this.m;
        if (osVar == null || osVar.G == (colorStateListD = l13.d(osVar.m0, i))) {
            return;
        }
        osVar.G = colorStateListD;
        osVar.onStateChange(osVar.getState());
    }

    @Deprecated
    public void setChipCornerRadius(float f) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.B(f);
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.B(osVar.m0.getResources().getDimension(i));
        }
    }

    public void setChipDrawable(os osVar) {
        os osVar2 = this.m;
        if (osVar2 != osVar) {
            if (osVar2 != null) {
                osVar2.J0 = new WeakReference(null);
            }
            this.m = osVar;
            osVar.L0 = false;
            osVar.J0 = new WeakReference(this);
            b(this.x);
        }
    }

    public void setChipEndPadding(float f) {
        os osVar = this.m;
        if (osVar == null || osVar.l0 == f) {
            return;
        }
        osVar.l0 = f;
        osVar.invalidateSelf();
        osVar.v();
    }

    public void setChipEndPaddingResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            float dimension = osVar.m0.getResources().getDimension(i);
            if (osVar.l0 != dimension) {
                osVar.l0 = dimension;
                osVar.invalidateSelf();
                osVar.v();
            }
        }
    }

    public void setChipIcon(Drawable drawable) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.C(drawable);
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z) {
        setChipIconVisible(z);
    }

    @Deprecated
    public void setChipIconEnabledResource(int i) {
        setChipIconVisible(i);
    }

    public void setChipIconResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.C(l13.f(osVar.m0, i));
        }
    }

    public void setChipIconSize(float f) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.D(f);
        }
    }

    public void setChipIconSizeResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.D(osVar.m0.getResources().getDimension(i));
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.E(colorStateList);
        }
    }

    public void setChipIconTintResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.E(l13.d(osVar.m0, i));
        }
    }

    public void setChipIconVisible(boolean z) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.F(z);
        }
    }

    public void setChipMinHeight(float f) {
        os osVar = this.m;
        if (osVar == null || osVar.H == f) {
            return;
        }
        osVar.H = f;
        osVar.invalidateSelf();
        osVar.v();
    }

    public void setChipMinHeightResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            float dimension = osVar.m0.getResources().getDimension(i);
            if (osVar.H != dimension) {
                osVar.H = dimension;
                osVar.invalidateSelf();
                osVar.v();
            }
        }
    }

    public void setChipStartPadding(float f) {
        os osVar = this.m;
        if (osVar == null || osVar.e0 == f) {
            return;
        }
        osVar.e0 = f;
        osVar.invalidateSelf();
        osVar.v();
    }

    public void setChipStartPaddingResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            float dimension = osVar.m0.getResources().getDimension(i);
            if (osVar.e0 != dimension) {
                osVar.e0 = dimension;
                osVar.invalidateSelf();
                osVar.v();
            }
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.G(colorStateList);
        }
    }

    public void setChipStrokeColorResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.G(l13.d(osVar.m0, i));
        }
    }

    public void setChipStrokeWidth(float f) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.H(f);
        }
    }

    public void setChipStrokeWidthResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.H(osVar.m0.getResources().getDimension(i));
        }
    }

    @Deprecated
    public void setChipText(CharSequence charSequence) {
        setText(charSequence);
    }

    @Deprecated
    public void setChipTextResource(int i) {
        setText(getResources().getString(i));
    }

    public void setCloseIcon(Drawable drawable) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.I(drawable);
        }
        e();
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        os osVar = this.m;
        if (osVar == null || osVar.X == charSequence) {
            return;
        }
        String str = wl.d;
        Locale locale = Locale.getDefault();
        int i = zj2.a;
        wl wlVar = yj2.a(locale) == 1 ? wl.g : wl.f;
        osVar.X = wlVar.c(charSequence, wlVar.c);
        osVar.invalidateSelf();
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z) {
        setCloseIconVisible(z);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i) {
        setCloseIconVisible(i);
    }

    public void setCloseIconEndPadding(float f) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.J(f);
        }
    }

    public void setCloseIconEndPaddingResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.J(osVar.m0.getResources().getDimension(i));
        }
    }

    public void setCloseIconResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.I(l13.f(osVar.m0, i));
        }
        e();
    }

    public void setCloseIconSize(float f) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.K(f);
        }
    }

    public void setCloseIconSizeResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.K(osVar.m0.getResources().getDimension(i));
        }
    }

    public void setCloseIconStartPadding(float f) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.L(f);
        }
    }

    public void setCloseIconStartPaddingResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.L(osVar.m0.getResources().getDimension(i));
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.M(colorStateList);
        }
    }

    public void setCloseIconTintResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.M(l13.d(osVar.m0, i));
        }
    }

    public void setCloseIconVisible(int i) {
        setCloseIconVisible(getResources().getBoolean(i));
    }

    @Override // p.x7, android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
    }

    @Override // p.x7, android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i3 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i3 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        os osVar = this.m;
        if (osVar != null) {
            osVar.j(f);
        }
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.m == null) {
            return;
        }
        if (truncateAt == TextUtils.TruncateAt.MARQUEE) {
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
        super.setEllipsize(truncateAt);
        os osVar = this.m;
        if (osVar != null) {
            osVar.K0 = truncateAt;
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        this.v = z;
        b(this.x);
    }

    @Override // android.widget.TextView
    public void setGravity(int i) {
        if (i != 8388627) {
            return;
        }
        super.setGravity(i);
    }

    public void setHideMotionSpec(ll1 ll1Var) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.d0 = ll1Var;
        }
    }

    public void setHideMotionSpecResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.d0 = ll1.a(osVar.m0, i);
        }
    }

    public void setIconEndPadding(float f) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.O(f);
        }
    }

    public void setIconEndPaddingResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.O(osVar.m0.getResources().getDimension(i));
        }
    }

    public void setIconStartPadding(float f) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.P(f);
        }
    }

    public void setIconStartPaddingResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.P(osVar.m0.getResources().getDimension(i));
        }
    }

    @Override // android.view.View
    public void setLayoutDirection(int i) {
        if (this.m == null) {
            return;
        }
        super.setLayoutDirection(i);
    }

    @Override // android.widget.TextView
    public void setLines(int i) {
        if (i > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setLines(i);
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i) {
        if (i > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMaxLines(i);
    }

    @Override // android.widget.TextView
    public void setMaxWidth(int i) {
        super.setMaxWidth(i);
        os osVar = this.m;
        if (osVar != null) {
            osVar.M0 = i;
        }
    }

    @Override // android.widget.TextView
    public void setMinLines(int i) {
        if (i > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMinLines(i);
    }

    public void setOnCheckedChangeListenerInternal(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.q = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.f32p = onClickListener;
        e();
    }

    public void setRippleColor(ColorStateList colorStateList) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.Q(colorStateList);
        }
        if (this.m.H0) {
            return;
        }
        f();
    }

    public void setRippleColorResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.Q(l13.d(osVar.m0, i));
            if (this.m.H0) {
                return;
            }
            f();
        }
    }

    @Override // p.zc2
    public void setShapeAppearanceModel(oc2 oc2Var) {
        this.m.setShapeAppearanceModel(oc2Var);
    }

    public void setShowMotionSpec(ll1 ll1Var) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.c0 = ll1Var;
        }
    }

    public void setShowMotionSpecResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.c0 = ll1.a(osVar.m0, i);
        }
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean z) {
        if (!z) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setSingleLine(z);
    }

    @Override // android.widget.TextView
    public final void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        os osVar = this.m;
        if (osVar == null) {
            return;
        }
        if (charSequence == null) {
            charSequence = "";
        }
        super.setText(osVar.L0 ? null : charSequence, bufferType);
        os osVar2 = this.m;
        if (osVar2 == null || TextUtils.equals(osVar2.M, charSequence)) {
            return;
        }
        osVar2.M = charSequence;
        osVar2.s0.d = true;
        osVar2.invalidateSelf();
        osVar2.v();
    }

    public void setTextAppearance(kj2 kj2Var) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.R(kj2Var);
        }
        h();
    }

    public void setTextAppearanceResource(int i) {
        setTextAppearance(getContext(), i);
    }

    public void setTextEndPadding(float f) {
        os osVar = this.m;
        if (osVar == null || osVar.i0 == f) {
            return;
        }
        osVar.i0 = f;
        osVar.invalidateSelf();
        osVar.v();
    }

    public void setTextEndPaddingResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            float dimension = osVar.m0.getResources().getDimension(i);
            if (osVar.i0 != dimension) {
                osVar.i0 = dimension;
                osVar.invalidateSelf();
                osVar.v();
            }
        }
    }

    public void setTextStartPadding(float f) {
        os osVar = this.m;
        if (osVar == null || osVar.h0 == f) {
            return;
        }
        osVar.h0 = f;
        osVar.invalidateSelf();
        osVar.v();
    }

    public void setTextStartPaddingResource(int i) {
        os osVar = this.m;
        if (osVar != null) {
            float dimension = osVar.m0.getResources().getDimension(i);
            if (osVar.h0 != dimension) {
                osVar.h0 = dimension;
                osVar.invalidateSelf();
                osVar.v();
            }
        }
    }

    public void setCheckedIconVisible(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.A(osVar.m0.getResources().getBoolean(i));
        }
    }

    public void setChipIconVisible(int i) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.F(osVar.m0.getResources().getBoolean(i));
        }
    }

    public void setCloseIconVisible(boolean z) {
        os osVar = this.m;
        if (osVar != null) {
            osVar.N(z);
        }
        e();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        os osVar = this.m;
        if (osVar != null) {
            osVar.R(new kj2(osVar.m0, i));
        }
        h();
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 == null) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
            return;
        }
        throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        }
        if (drawable3 == null) {
            super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
            return;
        }
        throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i) {
        super.setTextAppearance(i);
        os osVar = this.m;
        if (osVar != null) {
            osVar.R(new kj2(osVar.m0, i));
        }
        h();
    }
}
