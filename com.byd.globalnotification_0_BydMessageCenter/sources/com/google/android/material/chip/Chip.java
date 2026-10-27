package com.google.android.material.chip;

import a.b.k.l;
import a.b.p.g;
import a.h.k.q;
import a.h.k.x.b;
import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import b.d.a.a.b0.d;
import b.d.a.a.e0.g;
import b.d.a.a.e0.n;
import b.d.a.a.f;
import b.d.a.a.i;
import b.d.a.a.j;
import b.d.a.a.t.b;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Chip extends g implements b.a, n {
    public static final int t = j.Widget_MaterialComponents_Chip_Action;
    public static final Rect u = new Rect();
    public static final int[] v = {R.attr.state_selected};
    public static final int[] w = {R.attr.state_checkable};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b.d.a.a.t.b f2423d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public InsetDrawable f2424e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public RippleDrawable f2425f;
    public View.OnClickListener g;
    public CompoundButton.OnCheckedChangeListener h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public int n;
    public int o;
    public final b p;
    public final Rect q;
    public final RectF r;
    public final d s;

    public class a extends d {
        public a() {
        }

        @Override // b.d.a.a.b0.d
        public void a(int i) {
        }

        @Override // b.d.a.a.b0.d
        public void b(Typeface typeface, boolean z) {
            Chip chip = Chip.this;
            b.d.a.a.t.b bVar = chip.f2423d;
            chip.setText(bVar.F0 ? bVar.F : chip.getText());
            Chip.this.requestLayout();
            Chip.this.invalidate();
        }
    }

    public class b extends a.j.b.a {
        public b(Chip chip) {
            super(chip);
        }

        @Override // a.j.b.a
        public void m(List<Integer> list) {
            boolean z = false;
            list.add(0);
            if (Chip.this.f()) {
                b.d.a.a.t.b bVar = Chip.this.f2423d;
                if (bVar != null && bVar.L) {
                    z = true;
                }
                if (!z || Chip.this.g == null) {
                    return;
                }
                list.add(1);
            }
        }

        @Override // a.j.b.a
        public boolean p(int i, int i2, Bundle bundle) {
            boolean z = false;
            if (i2 == 16) {
                if (i == 0) {
                    return Chip.this.performClick();
                }
                if (i == 1) {
                    Chip chip = Chip.this;
                    chip.playSoundEffect(0);
                    View.OnClickListener onClickListener = chip.g;
                    if (onClickListener != null) {
                        onClickListener.onClick(chip);
                        z = true;
                    }
                    chip.p.s(1, 1);
                }
            }
            return z;
        }

        @Override // a.j.b.a
        public void q(int i, a.h.k.x.b bVar) {
            if (i != 1) {
                bVar.f856a.setContentDescription("");
                bVar.f856a.setBoundsInParent(Chip.u);
                return;
            }
            CharSequence closeIconContentDescription = Chip.this.getCloseIconContentDescription();
            if (closeIconContentDescription == null) {
                CharSequence text = Chip.this.getText();
                Context context = Chip.this.getContext();
                int i2 = i.mtrl_chip_close_icon_content_description;
                Object[] objArr = new Object[1];
                objArr[0] = TextUtils.isEmpty(text) ? "" : text;
                closeIconContentDescription = context.getString(i2, objArr).trim();
            }
            bVar.f856a.setContentDescription(closeIconContentDescription);
            bVar.f856a.setBoundsInParent(Chip.this.getCloseIconTouchBoundsInt());
            bVar.a(b.a.f859e);
            bVar.f856a.setEnabled(Chip.this.isEnabled());
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0328  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public Chip(android.content.Context r12, android.util.AttributeSet r13) {
        /*
            Method dump skipped, instruction units count: 856
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RectF getCloseIconTouchBounds() {
        this.r.setEmpty();
        if (f() && this.g != null) {
            b.d.a.a.t.b bVar = this.f2423d;
            bVar.C(bVar.getBounds(), this.r);
        }
        return this.r;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect getCloseIconTouchBoundsInt() {
        RectF closeIconTouchBounds = getCloseIconTouchBounds();
        this.q.set((int) closeIconTouchBounds.left, (int) closeIconTouchBounds.top, (int) closeIconTouchBounds.right, (int) closeIconTouchBounds.bottom);
        return this.q;
    }

    private b.d.a.a.b0.b getTextAppearance() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.m0.f2186f;
        }
        return null;
    }

    private void setCloseIconHovered(boolean z) {
        if (this.k != z) {
            this.k = z;
            refreshDrawableState();
        }
    }

    private void setCloseIconPressed(boolean z) {
        if (this.j != z) {
            this.j = z;
            refreshDrawableState();
        }
    }

    @Override // b.d.a.a.t.b.a
    public void a() {
        e(this.o);
        requestLayout();
        invalidateOutline();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b3  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchHoverEvent(android.view.MotionEvent r10) {
        /*
            r9 = this;
            java.lang.Class<a.j.b.a> r0 = a.j.b.a.class
            int r1 = r10.getAction()
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 10
            r4 = 1
            r5 = 0
            if (r1 != r3) goto L4b
            java.lang.String r1 = "m"
            java.lang.reflect.Field r1 = r0.getDeclaredField(r1)     // Catch: java.lang.Throwable -> L43
            r1.setAccessible(r4)     // Catch: java.lang.Throwable -> L43
            com.google.android.material.chip.Chip$b r6 = r9.p     // Catch: java.lang.Throwable -> L43
            java.lang.Object r1 = r1.get(r6)     // Catch: java.lang.Throwable -> L43
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch: java.lang.Throwable -> L43
            int r1 = r1.intValue()     // Catch: java.lang.Throwable -> L43
            if (r1 == r2) goto L4b
            java.lang.String r1 = "t"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch: java.lang.Throwable -> L43
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch: java.lang.Throwable -> L43
            r6[r5] = r7     // Catch: java.lang.Throwable -> L43
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r1, r6)     // Catch: java.lang.Throwable -> L43
            r0.setAccessible(r4)     // Catch: java.lang.Throwable -> L43
            com.google.android.material.chip.Chip$b r1 = r9.p     // Catch: java.lang.Throwable -> L43
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L43
            java.lang.Integer r7 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L43
            r6[r5] = r7     // Catch: java.lang.Throwable -> L43
            r0.invoke(r1, r6)     // Catch: java.lang.Throwable -> L43
            r0 = r4
            goto L4c
        L43:
            r0 = move-exception
            java.lang.String r1 = "Chip"
            java.lang.String r6 = "Unable to send Accessibility Exit event"
            android.util.Log.e(r1, r6, r0)
        L4b:
            r0 = r5
        L4c:
            if (r0 != 0) goto Lbe
            com.google.android.material.chip.Chip$b r0 = r9.p
            android.view.accessibility.AccessibilityManager r1 = r0.h
            boolean r1 = r1.isEnabled()
            if (r1 == 0) goto Lb3
            android.view.accessibility.AccessibilityManager r1 = r0.h
            boolean r1 = r1.isTouchExplorationEnabled()
            if (r1 != 0) goto L61
            goto Lb3
        L61:
            int r1 = r10.getAction()
            r6 = 7
            r7 = 256(0x100, float:3.59E-43)
            r8 = 128(0x80, float:1.8E-43)
            if (r1 == r6) goto L83
            r6 = 9
            if (r1 == r6) goto L83
            if (r1 == r3) goto L73
            goto Lb3
        L73:
            int r1 = r0.m
            if (r1 == r2) goto Lb3
            if (r1 != r2) goto L7a
            goto Lb1
        L7a:
            r0.m = r2
            r0.s(r2, r8)
            r0.s(r1, r7)
            goto Lb1
        L83:
            float r1 = r10.getX()
            float r3 = r10.getY()
            com.google.android.material.chip.Chip r6 = com.google.android.material.chip.Chip.this
            boolean r6 = b(r6)
            if (r6 == 0) goto La1
            com.google.android.material.chip.Chip r6 = com.google.android.material.chip.Chip.this
            android.graphics.RectF r6 = c(r6)
            boolean r1 = r6.contains(r1, r3)
            if (r1 == 0) goto La1
            r1 = r4
            goto La2
        La1:
            r1 = r5
        La2:
            int r3 = r0.m
            if (r3 != r1) goto La7
            goto Laf
        La7:
            r0.m = r1
            r0.s(r1, r8)
            r0.s(r3, r7)
        Laf:
            if (r1 == r2) goto Lb3
        Lb1:
            r0 = r4
            goto Lb4
        Lb3:
            r0 = r5
        Lb4:
            if (r0 != 0) goto Lbe
            boolean r9 = super.dispatchHoverEvent(r10)
            if (r9 == 0) goto Lbd
            goto Lbe
        Lbd:
            r4 = r5
        Lbe:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.dispatchHoverEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x004d  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchKeyEvent(android.view.KeyEvent r10) {
        /*
            r9 = this;
            com.google.android.material.chip.Chip$b r0 = r9.p
            r1 = 0
            if (r0 == 0) goto L88
            int r2 = r10.getAction()
            r3 = 0
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = 1
            if (r2 == r5) goto L7a
            int r2 = r10.getKeyCode()
            r6 = 61
            if (r2 == r6) goto L64
            r6 = 66
            if (r2 == r6) goto L4d
            switch(r2) {
                case 19: goto L1f;
                case 20: goto L1f;
                case 21: goto L1f;
                case 22: goto L1f;
                case 23: goto L4d;
                default: goto L1e;
            }
        L1e:
            goto L7a
        L1f:
            boolean r7 = r10.hasNoModifiers()
            if (r7 == 0) goto L7a
            r7 = 19
            if (r2 == r7) goto L37
            r7 = 21
            if (r2 == r7) goto L34
            r7 = 22
            if (r2 == r7) goto L39
            r6 = 130(0x82, float:1.82E-43)
            goto L39
        L34:
            r6 = 17
            goto L39
        L37:
            r6 = 33
        L39:
            int r2 = r10.getRepeatCount()
            int r2 = r2 + r5
            r7 = r3
        L3f:
            if (r3 >= r2) goto L4b
            boolean r8 = r0.n(r6, r1)
            if (r8 == 0) goto L4b
            int r3 = r3 + 1
            r7 = r5
            goto L3f
        L4b:
            r3 = r7
            goto L7a
        L4d:
            boolean r2 = r10.hasNoModifiers()
            if (r2 == 0) goto L7a
            int r2 = r10.getRepeatCount()
            if (r2 != 0) goto L7a
            int r2 = r0.l
            if (r2 == r4) goto L62
            r3 = 16
            r0.p(r2, r3, r1)
        L62:
            r3 = r5
            goto L7a
        L64:
            boolean r2 = r10.hasNoModifiers()
            if (r2 == 0) goto L70
            r2 = 2
            boolean r3 = r0.n(r2, r1)
            goto L7a
        L70:
            boolean r2 = r10.hasModifiers(r5)
            if (r2 == 0) goto L7a
            boolean r3 = r0.n(r5, r1)
        L7a:
            if (r3 == 0) goto L83
            com.google.android.material.chip.Chip$b r0 = r9.p
            int r0 = r0.l
            if (r0 == r4) goto L83
            return r5
        L83:
            boolean r9 = super.dispatchKeyEvent(r10)
            return r9
        L88:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [boolean, int] */
    @Override // a.b.p.g, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        b.d.a.a.t.b bVar = this.f2423d;
        boolean zE0 = false;
        int i = 0;
        zE0 = false;
        if (bVar != null && b.d.a.a.t.b.I(bVar.M)) {
            b.d.a.a.t.b bVar2 = this.f2423d;
            ?? IsEnabled = isEnabled();
            int i2 = IsEnabled;
            if (this.l) {
                i2 = IsEnabled + 1;
            }
            int i3 = i2;
            if (this.k) {
                i3 = i2 + 1;
            }
            int i4 = i3;
            if (this.j) {
                i4 = i3 + 1;
            }
            int i5 = i4;
            if (isChecked()) {
                i5 = i4 + 1;
            }
            int[] iArr = new int[i5];
            if (isEnabled()) {
                iArr[0] = 16842910;
                i = 1;
            }
            if (this.l) {
                iArr[i] = 16842908;
                i++;
            }
            if (this.k) {
                iArr[i] = 16843623;
                i++;
            }
            if (this.j) {
                iArr[i] = 16842919;
                i++;
            }
            if (isChecked()) {
                iArr[i] = 16842913;
            }
            zE0 = bVar2.e0(iArr);
        }
        if (zE0) {
            invalidate();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean e(int r10) {
        /*
            r9 = this;
            r9.o = r10
            boolean r0 = r9.m
            r1 = 0
            if (r0 != 0) goto L15
            android.graphics.drawable.InsetDrawable r10 = r9.f2424e
            if (r10 == 0) goto Lf
            r9.h()
            goto L14
        Lf:
            boolean r10 = b.d.a.a.c0.a.f1815a
            r9.j()
        L14:
            return r1
        L15:
            b.d.a.a.t.b r0 = r9.f2423d
            float r0 = r0.A
            int r0 = (int) r0
            int r0 = r10 - r0
            int r0 = java.lang.Math.max(r1, r0)
            b.d.a.a.t.b r2 = r9.f2423d
            int r2 = r2.getIntrinsicWidth()
            int r2 = r10 - r2
            int r2 = java.lang.Math.max(r1, r2)
            if (r2 > 0) goto L3e
            if (r0 > 0) goto L3e
            android.graphics.drawable.InsetDrawable r10 = r9.f2424e
            if (r10 == 0) goto L38
            r9.h()
            goto L3d
        L38:
            boolean r10 = b.d.a.a.c0.a.f1815a
            r9.j()
        L3d:
            return r1
        L3e:
            if (r2 <= 0) goto L44
            int r2 = r2 / 2
            r7 = r2
            goto L45
        L44:
            r7 = r1
        L45:
            if (r0 <= 0) goto L49
            int r1 = r0 / 2
        L49:
            r8 = r1
            android.graphics.drawable.InsetDrawable r0 = r9.f2424e
            r1 = 1
            if (r0 == 0) goto L6f
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            android.graphics.drawable.InsetDrawable r2 = r9.f2424e
            r2.getPadding(r0)
            int r2 = r0.top
            if (r2 != r8) goto L6f
            int r2 = r0.bottom
            if (r2 != r8) goto L6f
            int r2 = r0.left
            if (r2 != r7) goto L6f
            int r0 = r0.right
            if (r0 != r7) goto L6f
        L69:
            boolean r10 = b.d.a.a.c0.a.f1815a
            r9.j()
            return r1
        L6f:
            int r0 = r9.getMinHeight()
            if (r0 == r10) goto L78
            r9.setMinHeight(r10)
        L78:
            int r0 = r9.getMinWidth()
            if (r0 == r10) goto L81
            r9.setMinWidth(r10)
        L81:
            android.graphics.drawable.InsetDrawable r10 = new android.graphics.drawable.InsetDrawable
            b.d.a.a.t.b r4 = r9.f2423d
            r3 = r10
            r5 = r7
            r6 = r8
            r3.<init>(r4, r5, r6, r7, r8)
            r9.f2424e = r10
            goto L69
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.e(int):boolean");
    }

    public final boolean f() {
        b.d.a.a.t.b bVar = this.f2423d;
        return (bVar == null || bVar.F() == null) ? false : true;
    }

    public boolean g() {
        b.d.a.a.t.b bVar = this.f2423d;
        return bVar != null && bVar.R;
    }

    public Drawable getBackgroundDrawable() {
        InsetDrawable insetDrawable = this.f2424e;
        return insetDrawable == null ? this.f2423d : insetDrawable;
    }

    public Drawable getCheckedIcon() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.T;
        }
        return null;
    }

    public ColorStateList getCheckedIconTint() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.U;
        }
        return null;
    }

    public ColorStateList getChipBackgroundColor() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.z;
        }
        return null;
    }

    public float getChipCornerRadius() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return Math.max(0.0f, bVar.E());
        }
        return 0.0f;
    }

    public Drawable getChipDrawable() {
        return this.f2423d;
    }

    public float getChipEndPadding() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.e0;
        }
        return 0.0f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Drawable getChipIcon() {
        Drawable drawable;
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar == null || (drawable = bVar.H) == 0) {
            return null;
        }
        return drawable instanceof a.h.e.f.a ? ((a.h.e.f.a) drawable).a() : drawable;
    }

    public float getChipIconSize() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.J;
        }
        return 0.0f;
    }

    public ColorStateList getChipIconTint() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.I;
        }
        return null;
    }

    public float getChipMinHeight() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.A;
        }
        return 0.0f;
    }

    public float getChipStartPadding() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.X;
        }
        return 0.0f;
    }

    public ColorStateList getChipStrokeColor() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.C;
        }
        return null;
    }

    public float getChipStrokeWidth() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.D;
        }
        return 0.0f;
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    public Drawable getCloseIcon() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.F();
        }
        return null;
    }

    public CharSequence getCloseIconContentDescription() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.Q;
        }
        return null;
    }

    public float getCloseIconEndPadding() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.d0;
        }
        return 0.0f;
    }

    public float getCloseIconSize() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.P;
        }
        return 0.0f;
    }

    public float getCloseIconStartPadding() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.c0;
        }
        return 0.0f;
    }

    public ColorStateList getCloseIconTint() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.O;
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextUtils.TruncateAt getEllipsize() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.E0;
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public void getFocusedRect(Rect rect) {
        b bVar = this.p;
        if (bVar.l == 1 || bVar.k == 1) {
            rect.set(getCloseIconTouchBoundsInt());
        } else {
            super.getFocusedRect(rect);
        }
    }

    public b.d.a.a.l.g getHideMotionSpec() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.W;
        }
        return null;
    }

    public float getIconEndPadding() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.Z;
        }
        return 0.0f;
    }

    public float getIconStartPadding() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.Y;
        }
        return 0.0f;
    }

    public ColorStateList getRippleColor() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.E;
        }
        return null;
    }

    public b.d.a.a.e0.j getShapeAppearanceModel() {
        return this.f2423d.f1828a.f1835a;
    }

    public b.d.a.a.l.g getShowMotionSpec() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.V;
        }
        return null;
    }

    public float getTextEndPadding() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.b0;
        }
        return 0.0f;
    }

    public float getTextStartPadding() {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            return bVar.a0;
        }
        return 0.0f;
    }

    public final void h() {
        if (this.f2424e != null) {
            this.f2424e = null;
            setMinWidth(0);
            setMinHeight((int) getChipMinHeight());
            boolean z = b.d.a.a.c0.a.f1815a;
            j();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i() {
        /*
            r1 = this;
            boolean r0 = r1.f()
            if (r0 == 0) goto L1a
            b.d.a.a.t.b r0 = r1.f2423d
            if (r0 == 0) goto L10
            boolean r0 = r0.L
            if (r0 == 0) goto L10
            r0 = 1
            goto L11
        L10:
            r0 = 0
        L11:
            if (r0 == 0) goto L1a
            android.view.View$OnClickListener r0 = r1.g
            if (r0 == 0) goto L1a
            com.google.android.material.chip.Chip$b r0 = r1.p
            goto L1b
        L1a:
            r0 = 0
        L1b:
            a.h.k.q.U(r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.i():void");
    }

    public final void j() {
        this.f2425f = new RippleDrawable(b.d.a.a.c0.a.a(this.f2423d.E), getBackgroundDrawable(), null);
        this.f2423d.o0(false);
        q.X(this, this.f2425f);
        k();
    }

    public final void k() {
        b.d.a.a.t.b bVar;
        if (TextUtils.isEmpty(getText()) || (bVar = this.f2423d) == null) {
            return;
        }
        int iD = (int) (bVar.D() + bVar.e0 + bVar.b0);
        b.d.a.a.t.b bVar2 = this.f2423d;
        int iA = (int) (bVar2.A() + bVar2.X + bVar2.a0);
        if (this.f2424e != null) {
            Rect rect = new Rect();
            this.f2424e.getPadding(rect);
            iA += rect.left;
            iD += rect.right;
        }
        q.d0(this, iA, getPaddingTop(), iD, getPaddingBottom());
    }

    public final void l() {
        TextPaint paint = getPaint();
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            paint.drawableState = bVar.getState();
        }
        b.d.a.a.b0.b textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.e(getContext(), paint, this.s);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        l.i.r0(this, this.f2423d);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (isChecked()) {
            CheckBox.mergeDrawableStates(iArrOnCreateDrawableState, v);
        }
        if (g()) {
            CheckBox.mergeDrawableStates(iArrOnCreateDrawableState, w);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.TextView, android.view.View
    public void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        b bVar = this.p;
        int i2 = bVar.l;
        if (i2 != Integer.MIN_VALUE) {
            bVar.k(i2);
        }
        if (z) {
            bVar.n(i, rect);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        boolean zContains;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 7) {
            if (actionMasked == 10) {
                zContains = false;
            }
            return super.onHoverEvent(motionEvent);
        }
        zContains = getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY());
        setCloseIconHovered(zContains);
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int i;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName((g() || isClickable()) ? g() ? "android.widget.CompoundButton" : "android.widget.Button" : "android.view.View");
        accessibilityNodeInfo.setCheckable(g());
        accessibilityNodeInfo.setClickable(isClickable());
        if (getParent() instanceof ChipGroup) {
            ChipGroup chipGroup = (ChipGroup) getParent();
            if (chipGroup.f2172c) {
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    if (i2 >= chipGroup.getChildCount()) {
                        i3 = -1;
                        break;
                    }
                    if (chipGroup.getChildAt(i2) instanceof Chip) {
                        if (((Chip) chipGroup.getChildAt(i2)) == this) {
                            break;
                        } else {
                            i3++;
                        }
                    }
                    i2++;
                }
                i = i3;
            } else {
                i = -1;
            }
            Object tag = getTag(f.row_index_key);
            accessibilityNodeInfo.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) b.c.a(tag instanceof Integer ? ((Integer) tag).intValue() : -1, 1, i, 1, false, isChecked()).f866a);
        }
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    @TargetApi(24)
    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        if (getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) && isEnabled()) {
            return PointerIcon.getSystemIcon(getContext(), 1002);
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    @TargetApi(17)
    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        if (this.n != i) {
            this.n = i;
            k();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
    
        if (r0 != 3) goto L25;
     */
    @Override // android.widget.TextView, android.view.View
    @android.annotation.SuppressLint({"ClickableViewAccessibility"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionMasked()
            android.graphics.RectF r1 = r5.getCloseIconTouchBounds()
            float r2 = r6.getX()
            float r3 = r6.getY()
            boolean r1 = r1.contains(r2, r3)
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L45
            if (r0 == r3) goto L2b
            r4 = 2
            if (r0 == r4) goto L21
            r1 = 3
            if (r0 == r1) goto L40
            goto L4c
        L21:
            boolean r0 = r5.j
            if (r0 == 0) goto L4c
            if (r1 != 0) goto L4a
            r5.setCloseIconPressed(r2)
            goto L4a
        L2b:
            boolean r0 = r5.j
            if (r0 == 0) goto L40
            r5.playSoundEffect(r2)
            android.view.View$OnClickListener r0 = r5.g
            if (r0 == 0) goto L39
            r0.onClick(r5)
        L39:
            com.google.android.material.chip.Chip$b r0 = r5.p
            r0.s(r3, r3)
            r0 = r3
            goto L41
        L40:
            r0 = r2
        L41:
            r5.setCloseIconPressed(r2)
            goto L4d
        L45:
            if (r1 == 0) goto L4c
            r5.setCloseIconPressed(r3)
        L4a:
            r0 = r3
            goto L4d
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
        if (drawable == getBackgroundDrawable() || drawable == this.f2425f) {
            super.setBackground(drawable);
        } else {
            Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        Log.w("Chip", "Do not set the background color; Chip manages its own background drawable.");
    }

    @Override // a.b.p.g, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.f2425f) {
            super.setBackgroundDrawable(drawable);
        } else {
            Log.w("Chip", "Do not set the background drawable; Chip manages its own background drawable.");
        }
    }

    @Override // a.b.p.g, android.view.View
    public void setBackgroundResource(int i) {
        Log.w("Chip", "Do not set the background resource; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        Log.w("Chip", "Do not set the background tint list; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        Log.w("Chip", "Do not set the background tint mode; Chip manages its own background drawable.");
    }

    public void setCheckable(boolean z) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.L(z);
        }
    }

    public void setCheckableResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.L(bVar.f0.getResources().getBoolean(i));
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar == null) {
            this.i = z;
            return;
        }
        if (bVar.R) {
            boolean zIsChecked = isChecked();
            super.setChecked(z);
            if (zIsChecked == z || (onCheckedChangeListener = this.h) == null) {
                return;
            }
            onCheckedChangeListener.onCheckedChanged(this, z);
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.M(drawable);
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
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.M(a.b.l.a.a.b(bVar.f0, i));
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.N(colorStateList);
        }
    }

    public void setCheckedIconTintResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.N(a.b.l.a.a.a(bVar.f0, i));
        }
    }

    public void setCheckedIconVisible(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.O(bVar.f0.getResources().getBoolean(i));
        }
    }

    public void setCheckedIconVisible(boolean z) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.O(z);
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar == null || bVar.z == colorStateList) {
            return;
        }
        bVar.z = colorStateList;
        bVar.onStateChange(bVar.getState());
    }

    public void setChipBackgroundColorResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.P(a.b.l.a.a.a(bVar.f0, i));
        }
    }

    @Deprecated
    public void setChipCornerRadius(float f2) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.Q(f2);
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.Q(bVar.f0.getResources().getDimension(i));
        }
    }

    public void setChipDrawable(b.d.a.a.t.b bVar) {
        b.d.a.a.t.b bVar2 = this.f2423d;
        if (bVar2 != bVar) {
            if (bVar2 != null) {
                bVar2.D0 = new WeakReference<>(null);
            }
            this.f2423d = bVar;
            bVar.F0 = false;
            if (bVar == null) {
                throw null;
            }
            bVar.D0 = new WeakReference<>(this);
            e(this.o);
        }
    }

    public void setChipEndPadding(float f2) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar == null || bVar.e0 == f2) {
            return;
        }
        bVar.e0 = f2;
        bVar.invalidateSelf();
        bVar.J();
    }

    public void setChipEndPaddingResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.R(bVar.f0.getResources().getDimension(i));
        }
    }

    public void setChipIcon(Drawable drawable) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.S(drawable);
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
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.S(a.b.l.a.a.b(bVar.f0, i));
        }
    }

    public void setChipIconSize(float f2) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.T(f2);
        }
    }

    public void setChipIconSizeResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.T(bVar.f0.getResources().getDimension(i));
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.U(colorStateList);
        }
    }

    public void setChipIconTintResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.U(a.b.l.a.a.a(bVar.f0, i));
        }
    }

    public void setChipIconVisible(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.V(bVar.f0.getResources().getBoolean(i));
        }
    }

    public void setChipIconVisible(boolean z) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.V(z);
        }
    }

    public void setChipMinHeight(float f2) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar == null || bVar.A == f2) {
            return;
        }
        bVar.A = f2;
        bVar.invalidateSelf();
        bVar.J();
    }

    public void setChipMinHeightResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.W(bVar.f0.getResources().getDimension(i));
        }
    }

    public void setChipStartPadding(float f2) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar == null || bVar.X == f2) {
            return;
        }
        bVar.X = f2;
        bVar.invalidateSelf();
        bVar.J();
    }

    public void setChipStartPaddingResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.X(bVar.f0.getResources().getDimension(i));
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.Y(colorStateList);
        }
    }

    public void setChipStrokeColorResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.Y(a.b.l.a.a.a(bVar.f0, i));
        }
    }

    public void setChipStrokeWidth(float f2) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.Z(f2);
        }
    }

    public void setChipStrokeWidthResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.Z(bVar.f0.getResources().getDimension(i));
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
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.a0(drawable);
        }
        i();
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar == null || bVar.Q == charSequence) {
            return;
        }
        a.h.i.a aVarC = a.h.i.a.c();
        bVar.Q = aVarC.d(charSequence, aVarC.f780c, true);
        bVar.invalidateSelf();
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z) {
        setCloseIconVisible(z);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i) {
        setCloseIconVisible(i);
    }

    public void setCloseIconEndPadding(float f2) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.b0(f2);
        }
    }

    public void setCloseIconEndPaddingResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.b0(bVar.f0.getResources().getDimension(i));
        }
    }

    public void setCloseIconResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.a0(a.b.l.a.a.b(bVar.f0, i));
        }
        i();
    }

    public void setCloseIconSize(float f2) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.c0(f2);
        }
    }

    public void setCloseIconSizeResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.c0(bVar.f0.getResources().getDimension(i));
        }
    }

    public void setCloseIconStartPadding(float f2) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.d0(f2);
        }
    }

    public void setCloseIconStartPaddingResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.d0(bVar.f0.getResources().getDimension(i));
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.f0(colorStateList);
        }
    }

    public void setCloseIconTintResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.f0(a.b.l.a.a.a(bVar.f0, i));
        }
    }

    public void setCloseIconVisible(int i) {
        setCloseIconVisible(getResources().getBoolean(i));
    }

    public void setCloseIconVisible(boolean z) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.g0(z);
        }
        i();
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i3 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i3 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            g.b bVar2 = bVar.f1828a;
            if (bVar2.o != f2) {
                bVar2.o = f2;
                bVar.x();
            }
        }
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.f2423d == null) {
            return;
        }
        if (truncateAt == TextUtils.TruncateAt.MARQUEE) {
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
        super.setEllipsize(truncateAt);
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.E0 = truncateAt;
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        this.m = z;
        e(this.o);
    }

    @Override // android.widget.TextView
    public void setGravity(int i) {
        if (i != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i);
        }
    }

    public void setHideMotionSpec(b.d.a.a.l.g gVar) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.W = gVar;
        }
    }

    public void setHideMotionSpecResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.W = b.d.a.a.l.g.b(bVar.f0, i);
        }
    }

    public void setIconEndPadding(float f2) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.h0(f2);
        }
    }

    public void setIconEndPaddingResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.h0(bVar.f0.getResources().getDimension(i));
        }
    }

    public void setIconStartPadding(float f2) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.i0(f2);
        }
    }

    public void setIconStartPaddingResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.i0(bVar.f0.getResources().getDimension(i));
        }
    }

    @Override // android.view.View
    public void setLayoutDirection(int i) {
        if (this.f2423d == null) {
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
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.G0 = i;
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
        this.h = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.g = onClickListener;
        i();
    }

    public void setRippleColor(ColorStateList colorStateList) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.j0(colorStateList);
        }
        if (this.f2423d.B0) {
            return;
        }
        j();
    }

    public void setRippleColorResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.j0(a.b.l.a.a.a(bVar.f0, i));
            if (this.f2423d.B0) {
                return;
            }
            j();
        }
    }

    @Override // b.d.a.a.e0.n
    public void setShapeAppearanceModel(b.d.a.a.e0.j jVar) {
        b.d.a.a.t.b bVar = this.f2423d;
        bVar.f1828a.f1835a = jVar;
        bVar.invalidateSelf();
    }

    public void setShowMotionSpec(b.d.a.a.l.g gVar) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.V = gVar;
        }
    }

    public void setShowMotionSpecResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.V = b.d.a.a.l.g.b(bVar.f0, i);
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
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        if (this.f2423d == null) {
            return;
        }
        if (charSequence == null) {
            charSequence = "";
        }
        super.setText(this.f2423d.F0 ? null : charSequence, bufferType);
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.k0(charSequence);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i) {
        super.setTextAppearance(i);
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.l0(new b.d.a.a.b0.b(bVar.f0, i));
        }
        l();
    }

    public void setTextAppearance(b.d.a.a.b0.b bVar) {
        b.d.a.a.t.b bVar2 = this.f2423d;
        if (bVar2 != null) {
            bVar2.l0(bVar);
        }
        l();
    }

    public void setTextAppearanceResource(int i) {
        setTextAppearance(getContext(), i);
    }

    public void setTextEndPadding(float f2) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar == null || bVar.b0 == f2) {
            return;
        }
        bVar.b0 = f2;
        bVar.invalidateSelf();
        bVar.J();
    }

    public void setTextEndPaddingResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.m0(bVar.f0.getResources().getDimension(i));
        }
    }

    public void setTextStartPadding(float f2) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar == null || bVar.a0 == f2) {
            return;
        }
        bVar.a0 = f2;
        bVar.invalidateSelf();
        bVar.J();
    }

    public void setTextStartPaddingResource(int i) {
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.n0(bVar.f0.getResources().getDimension(i));
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        b.d.a.a.t.b bVar = this.f2423d;
        if (bVar != null) {
            bVar.l0(new b.d.a.a.b0.b(bVar.f0, i));
        }
        l();
    }
}
