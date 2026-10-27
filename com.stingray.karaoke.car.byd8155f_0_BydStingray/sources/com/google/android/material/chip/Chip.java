package com.google.android.material.chip;

import A.C0050y0;
import B2.O;
import F1.A;
import F1.H;
import L4.b;
import R4.a;
import R4.c;
import R4.d;
import R4.e;
import U4.k;
import Y4.u;
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
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import android.widget.TextView;
import b5.AbstractC0997a;
import h6.f;
import h6.n;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import r.AbstractC2256o;
import z1.InterfaceC2753a;

/* JADX INFO: loaded from: classes.dex */
public class Chip extends AbstractC2256o implements d, u {

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final Rect f12817F = new Rect();

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final int[] f12818G = {R.attr.state_selected};

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public static final int[] f12819H = {R.attr.state_checkable};

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f12820A;
    public final c B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final Rect f12821C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final RectF f12822D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final a f12823E;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public e f12824p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public InsetDrawable f12825q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public RippleDrawable f12826r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public View.OnClickListener f12827s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public CompoundButton.OnCheckedChangeListener f12828t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f12829u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f12830v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f12831w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f12832x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f12833y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f12834z;

    public Chip(Context context, AttributeSet attributeSet) {
        int resourceId;
        int resourceId2;
        int resourceId3;
        super(AbstractC0997a.a(context, attributeSet, com.stingray.drivemodedetector.R.attr.chipStyle, com.stingray.drivemodedetector.R.style.Widget_MaterialComponents_Chip_Action), attributeSet);
        this.f12821C = new Rect();
        this.f12822D = new RectF();
        this.f12823E = new a(this, 0);
        Context context2 = getContext();
        if (attributeSet != null) {
            if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "background") != null) {
                n.t("Chip", "Do not set the background; Chip manages its own background drawable.");
            }
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
            if (attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "gravity", 8388627) != 8388627) {
                n.t("Chip", "Chip text must be vertically center and start aligned");
            }
        }
        e eVar = new e(context2, attributeSet);
        int[] iArr = K4.a.f3586b;
        TypedArray typedArrayE = k.e(eVar.f7476n0, attributeSet, iArr, com.stingray.drivemodedetector.R.attr.chipStyle, com.stingray.drivemodedetector.R.style.Widget_MaterialComponents_Chip_Action, new int[0]);
        eVar.f7450N0 = typedArrayE.hasValue(37);
        Context context3 = eVar.f7476n0;
        ColorStateList colorStateListZ = com.bumptech.glide.c.Z(context3, typedArrayE, 24);
        if (eVar.f7436G != colorStateListZ) {
            eVar.f7436G = colorStateListZ;
            eVar.onStateChange(eVar.getState());
        }
        ColorStateList colorStateListZ2 = com.bumptech.glide.c.Z(context3, typedArrayE, 11);
        if (eVar.f7437H != colorStateListZ2) {
            eVar.f7437H = colorStateListZ2;
            eVar.onStateChange(eVar.getState());
        }
        float dimension = typedArrayE.getDimension(19, 0.0f);
        if (eVar.f7439I != dimension) {
            eVar.f7439I = dimension;
            eVar.invalidateSelf();
            eVar.s();
        }
        if (typedArrayE.hasValue(12)) {
            eVar.y(typedArrayE.getDimension(12, 0.0f));
        }
        eVar.D(com.bumptech.glide.c.Z(context3, typedArrayE, 22));
        eVar.E(typedArrayE.getDimension(23, 0.0f));
        eVar.N(com.bumptech.glide.c.Z(context3, typedArrayE, 36));
        String text = typedArrayE.getText(5);
        text = text == null ? "" : text;
        if (!TextUtils.equals(eVar.f7449N, text)) {
            eVar.f7449N = text;
            eVar.f7482t0.d = true;
            eVar.invalidateSelf();
            eVar.s();
        }
        V4.c cVar = (!typedArrayE.hasValue(0) || (resourceId3 = typedArrayE.getResourceId(0, 0)) == 0) ? null : new V4.c(context3, resourceId3);
        cVar.f9473k = typedArrayE.getDimension(1, cVar.f9473k);
        eVar.O(cVar);
        int i7 = typedArrayE.getInt(3, 0);
        if (i7 == 1) {
            eVar.f7444K0 = TextUtils.TruncateAt.START;
        } else if (i7 == 2) {
            eVar.f7444K0 = TextUtils.TruncateAt.MIDDLE;
        } else if (i7 == 3) {
            eVar.f7444K0 = TextUtils.TruncateAt.END;
        }
        eVar.C(typedArrayE.getBoolean(18, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") == null) {
            eVar.C(typedArrayE.getBoolean(15, false));
        }
        eVar.z(com.bumptech.glide.c.c0(context3, typedArrayE, 14));
        if (typedArrayE.hasValue(17)) {
            eVar.B(com.bumptech.glide.c.Z(context3, typedArrayE, 17));
        }
        eVar.A(typedArrayE.getDimension(16, -1.0f));
        eVar.K(typedArrayE.getBoolean(31, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") == null) {
            eVar.K(typedArrayE.getBoolean(26, false));
        }
        eVar.F(com.bumptech.glide.c.c0(context3, typedArrayE, 25));
        eVar.J(com.bumptech.glide.c.Z(context3, typedArrayE, 30));
        eVar.H(typedArrayE.getDimension(28, 0.0f));
        eVar.u(typedArrayE.getBoolean(6, false));
        eVar.x(typedArrayE.getBoolean(10, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") == null) {
            eVar.x(typedArrayE.getBoolean(8, false));
        }
        eVar.v(com.bumptech.glide.c.c0(context3, typedArrayE, 7));
        if (typedArrayE.hasValue(9)) {
            eVar.w(com.bumptech.glide.c.Z(context3, typedArrayE, 9));
        }
        eVar.f7466d0 = (!typedArrayE.hasValue(39) || (resourceId2 = typedArrayE.getResourceId(39, 0)) == 0) ? null : b.a(context3, resourceId2);
        eVar.f7467e0 = (!typedArrayE.hasValue(33) || (resourceId = typedArrayE.getResourceId(33, 0)) == 0) ? null : b.a(context3, resourceId);
        float dimension2 = typedArrayE.getDimension(21, 0.0f);
        if (eVar.f7468f0 != dimension2) {
            eVar.f7468f0 = dimension2;
            eVar.invalidateSelf();
            eVar.s();
        }
        eVar.M(typedArrayE.getDimension(35, 0.0f));
        eVar.L(typedArrayE.getDimension(34, 0.0f));
        float dimension3 = typedArrayE.getDimension(41, 0.0f);
        if (eVar.f7471i0 != dimension3) {
            eVar.f7471i0 = dimension3;
            eVar.invalidateSelf();
            eVar.s();
        }
        float dimension4 = typedArrayE.getDimension(40, 0.0f);
        if (eVar.f7472j0 != dimension4) {
            eVar.f7472j0 = dimension4;
            eVar.invalidateSelf();
            eVar.s();
        }
        eVar.I(typedArrayE.getDimension(29, 0.0f));
        eVar.G(typedArrayE.getDimension(27, 0.0f));
        float dimension5 = typedArrayE.getDimension(13, 0.0f);
        if (eVar.f7475m0 != dimension5) {
            eVar.f7475m0 = dimension5;
            eVar.invalidateSelf();
            eVar.s();
        }
        eVar.f7448M0 = typedArrayE.getDimensionPixelSize(4, Integer.MAX_VALUE);
        typedArrayE.recycle();
        k.a(context2, attributeSet, com.stingray.drivemodedetector.R.attr.chipStyle, com.stingray.drivemodedetector.R.style.Widget_MaterialComponents_Chip_Action);
        k.b(context2, attributeSet, iArr, com.stingray.drivemodedetector.R.attr.chipStyle, com.stingray.drivemodedetector.R.style.Widget_MaterialComponents_Chip_Action, new int[0]);
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, com.stingray.drivemodedetector.R.attr.chipStyle, com.stingray.drivemodedetector.R.style.Widget_MaterialComponents_Chip_Action);
        this.f12833y = typedArrayObtainStyledAttributes.getBoolean(32, false);
        this.f12820A = (int) Math.ceil(typedArrayObtainStyledAttributes.getDimension(20, (float) Math.ceil(TypedValue.applyDimension(1, 48, getContext().getResources().getDisplayMetrics()))));
        typedArrayObtainStyledAttributes.recycle();
        setChipDrawable(eVar);
        int[] iArr2 = H.f2303a;
        eVar.g(A.e(this));
        k.a(context2, attributeSet, com.stingray.drivemodedetector.R.attr.chipStyle, com.stingray.drivemodedetector.R.style.Widget_MaterialComponents_Chip_Action);
        k.b(context2, attributeSet, iArr, com.stingray.drivemodedetector.R.attr.chipStyle, com.stingray.drivemodedetector.R.style.Widget_MaterialComponents_Chip_Action, new int[0]);
        TypedArray typedArrayObtainStyledAttributes2 = context2.obtainStyledAttributes(attributeSet, iArr, com.stingray.drivemodedetector.R.attr.chipStyle, com.stingray.drivemodedetector.R.style.Widget_MaterialComponents_Chip_Action);
        boolean zHasValue = typedArrayObtainStyledAttributes2.hasValue(37);
        typedArrayObtainStyledAttributes2.recycle();
        this.B = new c(this, this);
        f();
        if (!zHasValue) {
            setOutlineProvider(new R4.b(this));
        }
        setChecked(this.f12829u);
        setText(eVar.f7449N);
        setEllipsize(eVar.f7444K0);
        i();
        if (!this.f12824p.f7446L0) {
            setLines(1);
            setHorizontallyScrolling(true);
        }
        setGravity(8388627);
        h();
        if (this.f12833y) {
            setMinHeight(this.f12820A);
        }
        this.f12834z = getLayoutDirection();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RectF getCloseIconTouchBounds() {
        RectF rectF = this.f12822D;
        rectF.setEmpty();
        if (d() && this.f12827s != null) {
            e eVar = this.f12824p;
            Rect bounds = eVar.getBounds();
            rectF.setEmpty();
            if (eVar.R()) {
                float f10 = eVar.f7475m0 + eVar.f7474l0 + eVar.f7460X + eVar.f7473k0 + eVar.f7472j0;
                if (eVar.getLayoutDirection() == 0) {
                    float f11 = bounds.right;
                    rectF.right = f11;
                    rectF.left = f11 - f10;
                } else {
                    float f12 = bounds.left;
                    rectF.left = f12;
                    rectF.right = f12 + f10;
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
        int i7 = (int) closeIconTouchBounds.left;
        int i10 = (int) closeIconTouchBounds.top;
        int i11 = (int) closeIconTouchBounds.right;
        int i12 = (int) closeIconTouchBounds.bottom;
        Rect rect = this.f12821C;
        rect.set(i7, i10, i11, i12);
        return rect;
    }

    private V4.c getTextAppearance() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7482t0.f9074f;
        }
        return null;
    }

    private void setCloseIconHovered(boolean z6) {
        if (this.f12831w != z6) {
            this.f12831w = z6;
            refreshDrawableState();
        }
    }

    private void setCloseIconPressed(boolean z6) {
        if (this.f12830v != z6) {
            this.f12830v = z6;
            refreshDrawableState();
        }
    }

    public final void c(int i7) {
        this.f12820A = i7;
        if (!this.f12833y) {
            InsetDrawable insetDrawable = this.f12825q;
            if (insetDrawable == null) {
                g();
                return;
            } else {
                if (insetDrawable != null) {
                    this.f12825q = null;
                    setMinWidth(0);
                    setMinHeight((int) getChipMinHeight());
                    g();
                    return;
                }
                return;
            }
        }
        int iMax = Math.max(0, i7 - ((int) this.f12824p.f7439I));
        int iMax2 = Math.max(0, i7 - this.f12824p.getIntrinsicWidth());
        if (iMax2 <= 0 && iMax <= 0) {
            InsetDrawable insetDrawable2 = this.f12825q;
            if (insetDrawable2 == null) {
                g();
                return;
            } else {
                if (insetDrawable2 != null) {
                    this.f12825q = null;
                    setMinWidth(0);
                    setMinHeight((int) getChipMinHeight());
                    g();
                    return;
                }
                return;
            }
        }
        int i10 = iMax2 > 0 ? iMax2 / 2 : 0;
        int i11 = iMax > 0 ? iMax / 2 : 0;
        if (this.f12825q != null) {
            Rect rect = new Rect();
            this.f12825q.getPadding(rect);
            if (rect.top == i11 && rect.bottom == i11 && rect.left == i10 && rect.right == i10) {
                g();
                return;
            }
        }
        if (getMinHeight() != i7) {
            setMinHeight(i7);
        }
        if (getMinWidth() != i7) {
            setMinWidth(i7);
        }
        this.f12825q = new InsetDrawable((Drawable) this.f12824p, i10, i11, i10, i11);
        g();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0011 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean d() {
        /*
            r1 = this;
            R4.e r1 = r1.f12824p
            if (r1 == 0) goto L13
            android.graphics.drawable.Drawable r1 = r1.f7457U
            if (r1 == 0) goto Le
            boolean r0 = r1 instanceof z1.InterfaceC2753a
            if (r0 == 0) goto Lf
            z1.a r1 = (z1.InterfaceC2753a) r1
        Le:
            r1 = 0
        Lf:
            if (r1 == 0) goto L13
            r1 = 1
            goto L14
        L13:
            r1 = 0
        L14:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.d():boolean");
    }

    @Override // android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        c cVar = this.B;
        if (action == 10) {
            try {
                Field declaredField = L1.a.class.getDeclaredField("m");
                declaredField.setAccessible(true);
                if (((Integer) declaredField.get(cVar)).intValue() != Integer.MIN_VALUE) {
                    Method declaredMethod = L1.a.class.getDeclaredMethod("s", Integer.TYPE);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(cVar, Integer.MIN_VALUE);
                    return true;
                }
            } catch (IllegalAccessException e10) {
                n.i("Chip", "Unable to send Accessibility Exit event", e10);
            } catch (NoSuchFieldException e11) {
                n.i("Chip", "Unable to send Accessibility Exit event", e11);
            } catch (NoSuchMethodException e12) {
                n.i("Chip", "Unable to send Accessibility Exit event", e12);
            } catch (InvocationTargetException e13) {
                n.i("Chip", "Unable to send Accessibility Exit event", e13);
            }
        }
        return cVar.l(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x004f  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean dispatchKeyEvent(android.view.KeyEvent r10) {
        /*
            r9 = this;
            R4.c r0 = r9.B
            r0.getClass()
            int r1 = r10.getAction()
            r2 = 0
            r3 = 1
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 == r3) goto L92
            int r1 = r10.getKeyCode()
            r5 = 61
            r6 = 0
            if (r1 == r5) goto L7c
            r5 = 66
            if (r1 == r5) goto L4f
            switch(r1) {
                case 19: goto L21;
                case 20: goto L21;
                case 21: goto L21;
                case 22: goto L21;
                case 23: goto L4f;
                default: goto L1f;
            }
        L1f:
            goto L92
        L21:
            boolean r7 = r10.hasNoModifiers()
            if (r7 == 0) goto L92
            r7 = 19
            if (r1 == r7) goto L39
            r7 = 21
            if (r1 == r7) goto L36
            r7 = 22
            if (r1 == r7) goto L3b
            r5 = 130(0x82, float:1.82E-43)
            goto L3b
        L36:
            r5 = 17
            goto L3b
        L39:
            r5 = 33
        L3b:
            int r1 = r10.getRepeatCount()
            int r1 = r1 + r3
            r7 = r2
        L41:
            if (r2 >= r1) goto L4d
            boolean r8 = r0.n(r5, r6)
            if (r8 == 0) goto L4d
            int r2 = r2 + 1
            r7 = r3
            goto L41
        L4d:
            r2 = r7
            goto L92
        L4f:
            boolean r1 = r10.hasNoModifiers()
            if (r1 == 0) goto L92
            int r1 = r10.getRepeatCount()
            if (r1 != 0) goto L92
            int r1 = r0.l
            if (r1 == r4) goto L7a
            com.google.android.material.chip.Chip r5 = r0.f7428q
            if (r1 != 0) goto L68
            boolean r2 = r5.performClick()
            goto L7a
        L68:
            if (r1 != r3) goto L7a
            r5.playSoundEffect(r2)
            android.view.View$OnClickListener r1 = r5.f12827s
            if (r1 == 0) goto L75
            r1.onClick(r5)
            r2 = r3
        L75:
            R4.c r1 = r5.B
            r1.r(r3, r3)
        L7a:
            r2 = r3
            goto L92
        L7c:
            boolean r1 = r10.hasNoModifiers()
            if (r1 == 0) goto L88
            r1 = 2
            boolean r2 = r0.n(r1, r6)
            goto L92
        L88:
            boolean r1 = r10.hasModifiers(r3)
            if (r1 == 0) goto L92
            boolean r2 = r0.n(r3, r6)
        L92:
            if (r2 == 0) goto L99
            int r0 = r0.l
            if (r0 == r4) goto L99
            return r3
        L99:
            boolean r9 = super.dispatchKeyEvent(r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [boolean, int] */
    @Override // r.AbstractC2256o, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        int i7;
        super.drawableStateChanged();
        e eVar = this.f12824p;
        boolean zT = false;
        if (eVar != null && e.r(eVar.f7457U)) {
            e eVar2 = this.f12824p;
            ?? IsEnabled = isEnabled();
            int i10 = IsEnabled;
            if (this.f12832x) {
                i10 = IsEnabled + 1;
            }
            int i11 = i10;
            if (this.f12831w) {
                i11 = i10 + 1;
            }
            int i12 = i11;
            if (this.f12830v) {
                i12 = i11 + 1;
            }
            int i13 = i12;
            if (isChecked()) {
                i13 = i12 + 1;
            }
            int[] iArr = new int[i13];
            if (isEnabled()) {
                iArr[0] = 16842910;
                i7 = 1;
            } else {
                i7 = 0;
            }
            if (this.f12832x) {
                iArr[i7] = 16842908;
                i7++;
            }
            if (this.f12831w) {
                iArr[i7] = 16843623;
                i7++;
            }
            if (this.f12830v) {
                iArr[i7] = 16842919;
                i7++;
            }
            if (isChecked()) {
                iArr[i7] = 16842913;
            }
            if (!Arrays.equals(eVar2.f7438H0, iArr)) {
                eVar2.f7438H0 = iArr;
                if (eVar2.R()) {
                    zT = eVar2.t(eVar2.getState(), iArr);
                }
            }
        }
        if (zT) {
            invalidate();
        }
    }

    public final boolean e() {
        e eVar = this.f12824p;
        return eVar != null && eVar.f7462Z;
    }

    public final void f() {
        e eVar;
        if (!d() || (eVar = this.f12824p) == null || !eVar.f7456T || this.f12827s == null) {
            H.g(this, null);
        } else {
            H.g(this, this.B);
        }
    }

    public final void g() {
        ColorStateList colorStateListValueOf = this.f12824p.f7447M;
        if (colorStateListValueOf == null) {
            colorStateListValueOf = ColorStateList.valueOf(0);
        }
        this.f12826r = new RippleDrawable(colorStateListValueOf, getBackgroundDrawable(), null);
        this.f12824p.getClass();
        RippleDrawable rippleDrawable = this.f12826r;
        int[] iArr = H.f2303a;
        setBackground(rippleDrawable);
        h();
    }

    public Drawable getBackgroundDrawable() {
        InsetDrawable insetDrawable = this.f12825q;
        return insetDrawable == null ? this.f12824p : insetDrawable;
    }

    public Drawable getCheckedIcon() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7464b0;
        }
        return null;
    }

    public ColorStateList getCheckedIconTint() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7465c0;
        }
        return null;
    }

    public ColorStateList getChipBackgroundColor() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7437H;
        }
        return null;
    }

    public float getChipCornerRadius() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return Math.max(0.0f, eVar.p());
        }
        return 0.0f;
    }

    public Drawable getChipDrawable() {
        return this.f12824p;
    }

    public float getChipEndPadding() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7475m0;
        }
        return 0.0f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.graphics.drawable.Drawable] */
    public Drawable getChipIcon() {
        Object obj;
        e eVar = this.f12824p;
        if (eVar == null || (obj = eVar.f7452P) == null) {
            return null;
        }
        if (obj instanceof InterfaceC2753a) {
            obj = null;
        }
        return obj;
    }

    public float getChipIconSize() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7454R;
        }
        return 0.0f;
    }

    public ColorStateList getChipIconTint() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7453Q;
        }
        return null;
    }

    public float getChipMinHeight() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7439I;
        }
        return 0.0f;
    }

    public float getChipStartPadding() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7468f0;
        }
        return 0.0f;
    }

    public ColorStateList getChipStrokeColor() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7443K;
        }
        return null;
    }

    public float getChipStrokeWidth() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7445L;
        }
        return 0.0f;
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.graphics.drawable.Drawable] */
    public Drawable getCloseIcon() {
        Object obj;
        e eVar = this.f12824p;
        if (eVar == null || (obj = eVar.f7457U) == null) {
            return null;
        }
        if (obj instanceof InterfaceC2753a) {
            obj = null;
        }
        return obj;
    }

    public CharSequence getCloseIconContentDescription() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7461Y;
        }
        return null;
    }

    public float getCloseIconEndPadding() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7474l0;
        }
        return 0.0f;
    }

    public float getCloseIconSize() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7460X;
        }
        return 0.0f;
    }

    public float getCloseIconStartPadding() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7473k0;
        }
        return 0.0f;
    }

    public ColorStateList getCloseIconTint() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7459W;
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextUtils.TruncateAt getEllipsize() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7444K0;
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public final void getFocusedRect(Rect rect) {
        c cVar = this.B;
        if (cVar.l == 1 || cVar.f3975k == 1) {
            rect.set(getCloseIconTouchBoundsInt());
        } else {
            super.getFocusedRect(rect);
        }
    }

    public b getHideMotionSpec() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7467e0;
        }
        return null;
    }

    public float getIconEndPadding() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7470h0;
        }
        return 0.0f;
    }

    public float getIconStartPadding() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7469g0;
        }
        return 0.0f;
    }

    public ColorStateList getRippleColor() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7447M;
        }
        return null;
    }

    public Y4.k getShapeAppearanceModel() {
        return this.f12824p.l.f10708a;
    }

    public b getShowMotionSpec() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7466d0;
        }
        return null;
    }

    public float getTextEndPadding() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7472j0;
        }
        return 0.0f;
    }

    public float getTextStartPadding() {
        e eVar = this.f12824p;
        if (eVar != null) {
            return eVar.f7471i0;
        }
        return 0.0f;
    }

    public final void h() {
        e eVar;
        if (TextUtils.isEmpty(getText()) || (eVar = this.f12824p) == null) {
            return;
        }
        int iO = (int) (eVar.o() + eVar.f7475m0 + eVar.f7472j0);
        e eVar2 = this.f12824p;
        int iN = (int) (eVar2.n() + eVar2.f7468f0 + eVar2.f7471i0);
        if (this.f12825q != null) {
            Rect rect = new Rect();
            this.f12825q.getPadding(rect);
            iN += rect.left;
            iO += rect.right;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int[] iArr = H.f2303a;
        setPaddingRelative(iN, paddingTop, iO, paddingBottom);
    }

    public final void i() {
        TextPaint paint = getPaint();
        e eVar = this.f12824p;
        if (eVar != null) {
            paint.drawableState = eVar.getState();
        }
        V4.c textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.e(getContext(), paint, this.f12823E);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        com.bumptech.glide.d.L(this, this.f12824p);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i7) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i7 + 2);
        if (isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, f12818G);
        }
        if (e()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, f12819H);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onFocusChanged(boolean z6, int i7, Rect rect) {
        super.onFocusChanged(z6, i7, rect);
        c cVar = this.B;
        int i10 = cVar.l;
        if (i10 != Integer.MIN_VALUE) {
            cVar.j(i10);
        }
        if (z6) {
            cVar.n(i7, rect);
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
        int i7;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (e() || isClickable()) {
            accessibilityNodeInfo.setClassName(e() ? "android.widget.CompoundButton" : "android.widget.Button");
        } else {
            accessibilityNodeInfo.setClassName("android.view.View");
        }
        accessibilityNodeInfo.setCheckable(e());
        accessibilityNodeInfo.setClickable(isClickable());
        if (getParent() instanceof ChipGroup) {
            ChipGroup chipGroup = (ChipGroup) getParent();
            if (chipGroup.f9056n) {
                i7 = 0;
                for (int i10 = 0; i10 < chipGroup.getChildCount(); i10++) {
                    if (chipGroup.getChildAt(i10) instanceof Chip) {
                        if (((Chip) chipGroup.getChildAt(i10)) == this) {
                            break;
                        } else {
                            i7++;
                        }
                    }
                }
                i7 = -1;
            } else {
                i7 = -1;
            }
            Object tag = getTag(com.stingray.drivemodedetector.R.id.row_index_key);
            accessibilityNodeInfo.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) O.f0(isChecked(), tag instanceof Integer ? ((Integer) tag).intValue() : -1, 1, i7, 1).f527m);
        }
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    public final PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i7) {
        if (getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) && isEnabled()) {
            return PointerIcon.getSystemIcon(getContext(), 1002);
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRtlPropertiesChanged(int i7) {
        super.onRtlPropertiesChanged(i7);
        if (this.f12834z != i7) {
            this.f12834z = i7;
            h();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
    
        if (r0 != 3) goto L25;
     */
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
            if (r0 == 0) goto L46
            if (r0 == r3) goto L2c
            r4 = 2
            if (r0 == r4) goto L21
            r1 = 3
            if (r0 == r1) goto L41
            goto L4c
        L21:
            boolean r0 = r5.f12830v
            if (r0 == 0) goto L4c
            if (r1 != 0) goto L2a
            r5.setCloseIconPressed(r2)
        L2a:
            r0 = r3
            goto L4d
        L2c:
            boolean r0 = r5.f12830v
            if (r0 == 0) goto L41
            r5.playSoundEffect(r2)
            android.view.View$OnClickListener r0 = r5.f12827s
            if (r0 == 0) goto L3a
            r0.onClick(r5)
        L3a:
            R4.c r0 = r5.B
            r0.r(r3, r3)
            r0 = r3
            goto L42
        L41:
            r0 = r2
        L42:
            r5.setCloseIconPressed(r2)
            goto L4d
        L46:
            if (r1 == 0) goto L4c
            r5.setCloseIconPressed(r3)
            goto L2a
        L4c:
            r0 = r2
        L4d:
            if (r0 != 0) goto L55
            boolean r5 = super.onTouchEvent(r6)
            if (r5 == 0) goto L56
        L55:
            r2 = r3
        L56:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.f12826r) {
            super.setBackground(drawable);
        } else {
            n.t("Chip", "Do not set the background; Chip manages its own background drawable.");
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i7) {
        n.t("Chip", "Do not set the background color; Chip manages its own background drawable.");
    }

    @Override // r.AbstractC2256o, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.f12826r) {
            super.setBackgroundDrawable(drawable);
        } else {
            n.t("Chip", "Do not set the background drawable; Chip manages its own background drawable.");
        }
    }

    @Override // r.AbstractC2256o, android.view.View
    public void setBackgroundResource(int i7) {
        n.t("Chip", "Do not set the background resource; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        n.t("Chip", "Do not set the background tint list; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        n.t("Chip", "Do not set the background tint mode; Chip manages its own background drawable.");
    }

    public void setCheckable(boolean z6) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.u(z6);
        }
    }

    public void setCheckableResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.u(eVar.f7476n0.getResources().getBoolean(i7));
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z6) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        e eVar = this.f12824p;
        if (eVar == null) {
            this.f12829u = z6;
            return;
        }
        if (eVar.f7462Z) {
            boolean zIsChecked = isChecked();
            super.setChecked(z6);
            if (zIsChecked == z6 || (onCheckedChangeListener = this.f12828t) == null) {
                return;
            }
            onCheckedChangeListener.onCheckedChanged(this, z6);
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.v(drawable);
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z6) {
        setCheckedIconVisible(z6);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int i7) {
        setCheckedIconVisible(i7);
    }

    public void setCheckedIconResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.v(f.k(eVar.f7476n0, i7));
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.w(colorStateList);
        }
    }

    public void setCheckedIconTintResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.w(f.h(eVar.f7476n0, i7));
        }
    }

    public void setCheckedIconVisible(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.x(eVar.f7476n0.getResources().getBoolean(i7));
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        e eVar = this.f12824p;
        if (eVar == null || eVar.f7437H == colorStateList) {
            return;
        }
        eVar.f7437H = colorStateList;
        eVar.onStateChange(eVar.getState());
    }

    public void setChipBackgroundColorResource(int i7) {
        ColorStateList colorStateListH;
        e eVar = this.f12824p;
        if (eVar == null || eVar.f7437H == (colorStateListH = f.h(eVar.f7476n0, i7))) {
            return;
        }
        eVar.f7437H = colorStateListH;
        eVar.onStateChange(eVar.getState());
    }

    @Deprecated
    public void setChipCornerRadius(float f10) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.y(f10);
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.y(eVar.f7476n0.getResources().getDimension(i7));
        }
    }

    public void setChipDrawable(e eVar) {
        e eVar2 = this.f12824p;
        if (eVar2 != eVar) {
            if (eVar2 != null) {
                eVar2.f7442J0 = new WeakReference(null);
            }
            this.f12824p = eVar;
            eVar.f7446L0 = false;
            eVar.f7442J0 = new WeakReference(this);
            c(this.f12820A);
        }
    }

    public void setChipEndPadding(float f10) {
        e eVar = this.f12824p;
        if (eVar == null || eVar.f7475m0 == f10) {
            return;
        }
        eVar.f7475m0 = f10;
        eVar.invalidateSelf();
        eVar.s();
    }

    public void setChipEndPaddingResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            float dimension = eVar.f7476n0.getResources().getDimension(i7);
            if (eVar.f7475m0 != dimension) {
                eVar.f7475m0 = dimension;
                eVar.invalidateSelf();
                eVar.s();
            }
        }
    }

    public void setChipIcon(Drawable drawable) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.z(drawable);
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z6) {
        setChipIconVisible(z6);
    }

    @Deprecated
    public void setChipIconEnabledResource(int i7) {
        setChipIconVisible(i7);
    }

    public void setChipIconResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.z(f.k(eVar.f7476n0, i7));
        }
    }

    public void setChipIconSize(float f10) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.A(f10);
        }
    }

    public void setChipIconSizeResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.A(eVar.f7476n0.getResources().getDimension(i7));
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.B(colorStateList);
        }
    }

    public void setChipIconTintResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.B(f.h(eVar.f7476n0, i7));
        }
    }

    public void setChipIconVisible(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.C(eVar.f7476n0.getResources().getBoolean(i7));
        }
    }

    public void setChipMinHeight(float f10) {
        e eVar = this.f12824p;
        if (eVar == null || eVar.f7439I == f10) {
            return;
        }
        eVar.f7439I = f10;
        eVar.invalidateSelf();
        eVar.s();
    }

    public void setChipMinHeightResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            float dimension = eVar.f7476n0.getResources().getDimension(i7);
            if (eVar.f7439I != dimension) {
                eVar.f7439I = dimension;
                eVar.invalidateSelf();
                eVar.s();
            }
        }
    }

    public void setChipStartPadding(float f10) {
        e eVar = this.f12824p;
        if (eVar == null || eVar.f7468f0 == f10) {
            return;
        }
        eVar.f7468f0 = f10;
        eVar.invalidateSelf();
        eVar.s();
    }

    public void setChipStartPaddingResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            float dimension = eVar.f7476n0.getResources().getDimension(i7);
            if (eVar.f7468f0 != dimension) {
                eVar.f7468f0 = dimension;
                eVar.invalidateSelf();
                eVar.s();
            }
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.D(colorStateList);
        }
    }

    public void setChipStrokeColorResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.D(f.h(eVar.f7476n0, i7));
        }
    }

    public void setChipStrokeWidth(float f10) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.E(f10);
        }
    }

    public void setChipStrokeWidthResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.E(eVar.f7476n0.getResources().getDimension(i7));
        }
    }

    @Deprecated
    public void setChipText(CharSequence charSequence) {
        setText(charSequence);
    }

    @Deprecated
    public void setChipTextResource(int i7) {
        setText(getResources().getString(i7));
    }

    public void setCloseIcon(Drawable drawable) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.F(drawable);
        }
        f();
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        e eVar = this.f12824p;
        if (eVar == null || eVar.f7461Y == charSequence) {
            return;
        }
        D1.b bVarC = D1.b.c();
        bVarC.getClass();
        C0050y0 c0050y0 = D1.f.f1137a;
        eVar.f7461Y = bVarC.d(charSequence);
        eVar.invalidateSelf();
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z6) {
        setCloseIconVisible(z6);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i7) {
        setCloseIconVisible(i7);
    }

    public void setCloseIconEndPadding(float f10) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.G(f10);
        }
    }

    public void setCloseIconEndPaddingResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.G(eVar.f7476n0.getResources().getDimension(i7));
        }
    }

    public void setCloseIconResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.F(f.k(eVar.f7476n0, i7));
        }
        f();
    }

    public void setCloseIconSize(float f10) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.H(f10);
        }
    }

    public void setCloseIconSizeResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.H(eVar.f7476n0.getResources().getDimension(i7));
        }
    }

    public void setCloseIconStartPadding(float f10) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.I(f10);
        }
    }

    public void setCloseIconStartPaddingResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.I(eVar.f7476n0.getResources().getDimension(i7));
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.J(colorStateList);
        }
    }

    public void setCloseIconTintResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.J(f.h(eVar.f7476n0, i7));
        }
    }

    public void setCloseIconVisible(int i7) {
        setCloseIconVisible(getResources().getBoolean(i7));
    }

    @Override // r.AbstractC2256o, android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
    }

    @Override // r.AbstractC2256o, android.widget.TextView
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
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(int i7, int i10, int i11, int i12) {
        if (i7 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i11 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i7, i10, i11, i12);
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(int i7, int i10, int i11, int i12) {
        if (i7 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i11 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(i7, i10, i11, i12);
    }

    @Override // android.view.View
    public void setElevation(float f10) {
        super.setElevation(f10);
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.g(f10);
        }
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.f12824p == null) {
            return;
        }
        if (truncateAt == TextUtils.TruncateAt.MARQUEE) {
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
        super.setEllipsize(truncateAt);
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.f7444K0 = truncateAt;
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z6) {
        this.f12833y = z6;
        c(this.f12820A);
    }

    @Override // android.widget.TextView
    public void setGravity(int i7) {
        if (i7 != 8388627) {
            n.t("Chip", "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i7);
        }
    }

    public void setHideMotionSpec(b bVar) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.f7467e0 = bVar;
        }
    }

    public void setHideMotionSpecResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.f7467e0 = b.a(eVar.f7476n0, i7);
        }
    }

    public void setIconEndPadding(float f10) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.L(f10);
        }
    }

    public void setIconEndPaddingResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.L(eVar.f7476n0.getResources().getDimension(i7));
        }
    }

    public void setIconStartPadding(float f10) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.M(f10);
        }
    }

    public void setIconStartPaddingResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.M(eVar.f7476n0.getResources().getDimension(i7));
        }
    }

    @Override // android.view.View
    public void setLayoutDirection(int i7) {
        if (this.f12824p == null) {
            return;
        }
        super.setLayoutDirection(i7);
    }

    @Override // android.widget.TextView
    public void setLines(int i7) {
        if (i7 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setLines(i7);
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i7) {
        if (i7 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMaxLines(i7);
    }

    @Override // android.widget.TextView
    public void setMaxWidth(int i7) {
        super.setMaxWidth(i7);
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.f7448M0 = i7;
        }
    }

    @Override // android.widget.TextView
    public void setMinLines(int i7) {
        if (i7 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMinLines(i7);
    }

    public void setOnCheckedChangeListenerInternal(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.f12828t = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.f12827s = onClickListener;
        f();
    }

    public void setRippleColor(ColorStateList colorStateList) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.N(colorStateList);
        }
        this.f12824p.getClass();
        g();
    }

    public void setRippleColorResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.N(f.h(eVar.f7476n0, i7));
            this.f12824p.getClass();
            g();
        }
    }

    @Override // Y4.u
    public void setShapeAppearanceModel(Y4.k kVar) {
        this.f12824p.setShapeAppearanceModel(kVar);
    }

    public void setShowMotionSpec(b bVar) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.f7466d0 = bVar;
        }
    }

    public void setShowMotionSpecResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.f7466d0 = b.a(eVar.f7476n0, i7);
        }
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean z6) {
        if (!z6) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setSingleLine(z6);
    }

    @Override // android.widget.TextView
    public final void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        e eVar = this.f12824p;
        if (eVar == null) {
            return;
        }
        if (charSequence == null) {
            charSequence = "";
        }
        super.setText(eVar.f7446L0 ? null : charSequence, bufferType);
        e eVar2 = this.f12824p;
        if (eVar2 == null || TextUtils.equals(eVar2.f7449N, charSequence)) {
            return;
        }
        eVar2.f7449N = charSequence;
        eVar2.f7482t0.d = true;
        eVar2.invalidateSelf();
        eVar2.s();
    }

    public void setTextAppearance(V4.c cVar) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.O(cVar);
        }
        i();
    }

    public void setTextAppearanceResource(int i7) {
        setTextAppearance(getContext(), i7);
    }

    public void setTextEndPadding(float f10) {
        e eVar = this.f12824p;
        if (eVar == null || eVar.f7472j0 == f10) {
            return;
        }
        eVar.f7472j0 = f10;
        eVar.invalidateSelf();
        eVar.s();
    }

    public void setTextEndPaddingResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            float dimension = eVar.f7476n0.getResources().getDimension(i7);
            if (eVar.f7472j0 != dimension) {
                eVar.f7472j0 = dimension;
                eVar.invalidateSelf();
                eVar.s();
            }
        }
    }

    public void setTextStartPadding(float f10) {
        e eVar = this.f12824p;
        if (eVar == null || eVar.f7471i0 == f10) {
            return;
        }
        eVar.f7471i0 = f10;
        eVar.invalidateSelf();
        eVar.s();
    }

    public void setTextStartPaddingResource(int i7) {
        e eVar = this.f12824p;
        if (eVar != null) {
            float dimension = eVar.f7476n0.getResources().getDimension(i7);
            if (eVar.f7471i0 != dimension) {
                eVar.f7471i0 = dimension;
                eVar.invalidateSelf();
                eVar.s();
            }
        }
    }

    public void setCloseIconVisible(boolean z6) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.K(z6);
        }
        f();
    }

    public void setCheckedIconVisible(boolean z6) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.x(z6);
        }
    }

    public void setChipIconVisible(boolean z6) {
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.C(z6);
        }
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
    public final void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.O(new V4.c(eVar.f7476n0, i7));
        }
        i();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i7) {
        super.setTextAppearance(i7);
        e eVar = this.f12824p;
        if (eVar != null) {
            eVar.O(new V4.c(eVar.f7476n0, i7));
        }
        i();
    }
}
