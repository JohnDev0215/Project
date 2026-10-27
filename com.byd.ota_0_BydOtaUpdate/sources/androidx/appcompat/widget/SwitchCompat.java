package androidx.appcompat.widget;

import android.R;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import b.b.j;
import b.b.q.e0;
import b.b.q.k0;
import b.b.q.m;
import b.b.q.q;
import b.b.q.z;
import b.f.m.s;
import b.f.n.i;

/* JADX INFO: loaded from: classes.dex */
public class SwitchCompat extends CompoundButton {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Property<SwitchCompat, Float> f272c = new a(Float.class, "thumbPos");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f273d = {R.attr.state_checked};
    public boolean E3;
    public boolean F3;
    public Drawable G3;
    public ColorStateList H3;
    public PorterDuff.Mode I3;
    public boolean J3;
    public boolean K3;
    public int L3;
    public int M3;
    public int N3;
    public boolean O3;
    public CharSequence P3;
    public CharSequence Q3;
    public boolean R3;
    public int S3;
    public int T3;
    public float U3;
    public float V3;
    public VelocityTracker W3;
    public int X3;
    public float Y3;
    public int Z3;
    public int a4;
    public int b4;
    public int c4;
    public int d4;
    public int e4;
    public int f4;
    public final TextPaint g4;
    public ColorStateList h4;
    public Layout i4;
    public Layout j4;
    public TransformationMethod k4;
    public ObjectAnimator l4;
    public final m m4;
    public final Rect n4;
    public Drawable s;
    public ColorStateList x;
    public PorterDuff.Mode y;

    public class a extends Property<SwitchCompat, Float> {
        public a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(SwitchCompat switchCompat) {
            return Float.valueOf(switchCompat.Y3);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(SwitchCompat switchCompat, Float f2) {
            switchCompat.setThumbPosition(f2.floatValue());
        }
    }

    public SwitchCompat(Context context) {
        this(context, null);
    }

    public static float f(float f2, float f3, float f4) {
        return f2 < f3 ? f3 : f2 > f4 ? f4 : f2;
    }

    private boolean getTargetCheckedState() {
        return this.Y3 > 0.5f;
    }

    private int getThumbOffset() {
        return (int) (((k0.b(this) ? 1.0f - this.Y3 : this.Y3) * getThumbScrollRange()) + 0.5f);
    }

    private int getThumbScrollRange() {
        Drawable drawable = this.G3;
        if (drawable == null) {
            return 0;
        }
        Rect rect = this.n4;
        drawable.getPadding(rect);
        Drawable drawable2 = this.s;
        Rect rectD = drawable2 != null ? q.d(drawable2) : q.f1093c;
        return ((((this.Z3 - this.b4) - rect.left) - rect.right) - rectD.left) - rectD.right;
    }

    public final void a(boolean z) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, f272c, z ? 1.0f : 0.0f);
        this.l4 = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(250L);
        if (Build.VERSION.SDK_INT >= 18) {
            this.l4.setAutoCancel(true);
        }
        this.l4.start();
    }

    public final void b() {
        Drawable drawable = this.s;
        if (drawable != null) {
            if (this.E3 || this.F3) {
                Drawable drawableMutate = b.f.g.l.a.q(drawable).mutate();
                this.s = drawableMutate;
                if (this.E3) {
                    b.f.g.l.a.n(drawableMutate, this.x);
                }
                if (this.F3) {
                    b.f.g.l.a.o(this.s, this.y);
                }
                if (this.s.isStateful()) {
                    this.s.setState(getDrawableState());
                }
            }
        }
    }

    public final void c() {
        Drawable drawable = this.G3;
        if (drawable != null) {
            if (this.J3 || this.K3) {
                Drawable drawableMutate = b.f.g.l.a.q(drawable).mutate();
                this.G3 = drawableMutate;
                if (this.J3) {
                    b.f.g.l.a.n(drawableMutate, this.H3);
                }
                if (this.K3) {
                    b.f.g.l.a.o(this.G3, this.I3);
                }
                if (this.G3.isStateful()) {
                    this.G3.setState(getDrawableState());
                }
            }
        }
    }

    public final void d() {
        ObjectAnimator objectAnimator = this.l4;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int i2;
        int i3;
        Rect rect = this.n4;
        int i4 = this.c4;
        int i5 = this.d4;
        int i6 = this.e4;
        int i7 = this.f4;
        int thumbOffset = getThumbOffset() + i4;
        Drawable drawable = this.s;
        Rect rectD = drawable != null ? q.d(drawable) : q.f1093c;
        Drawable drawable2 = this.G3;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            int i8 = rect.left;
            thumbOffset += i8;
            if (rectD != null) {
                int i9 = rectD.left;
                if (i9 > i8) {
                    i4 += i9 - i8;
                }
                int i10 = rectD.top;
                int i11 = rect.top;
                i2 = i10 > i11 ? (i10 - i11) + i5 : i5;
                int i12 = rectD.right;
                int i13 = rect.right;
                if (i12 > i13) {
                    i6 -= i12 - i13;
                }
                int i14 = rectD.bottom;
                int i15 = rect.bottom;
                if (i14 > i15) {
                    i3 = i7 - (i14 - i15);
                }
                this.G3.setBounds(i4, i2, i6, i3);
            } else {
                i2 = i5;
            }
            i3 = i7;
            this.G3.setBounds(i4, i2, i6, i3);
        }
        Drawable drawable3 = this.s;
        if (drawable3 != null) {
            drawable3.getPadding(rect);
            int i16 = thumbOffset - rect.left;
            int i17 = thumbOffset + this.b4 + rect.right;
            this.s.setBounds(i16, i5, i17, i7);
            Drawable background = getBackground();
            if (background != null) {
                b.f.g.l.a.k(background, i16, i5, i17, i7);
            }
        }
        super.draw(canvas);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableHotspotChanged(float f2, float f3) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.drawableHotspotChanged(f2, f3);
        }
        Drawable drawable = this.s;
        if (drawable != null) {
            b.f.g.l.a.j(drawable, f2, f3);
        }
        Drawable drawable2 = this.G3;
        if (drawable2 != null) {
            b.f.g.l.a.j(drawable2, f2, f3);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.s;
        boolean state = false;
        if (drawable != null && drawable.isStateful()) {
            state = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.G3;
        if (drawable2 != null && drawable2.isStateful()) {
            state |= drawable2.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    public final void e(MotionEvent motionEvent) {
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.setAction(3);
        super.onTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
    }

    public final boolean g(float f2, float f3) {
        if (this.s == null) {
            return false;
        }
        int thumbOffset = getThumbOffset();
        this.s.getPadding(this.n4);
        int i2 = this.d4;
        int i3 = this.T3;
        int i4 = i2 - i3;
        int i5 = (this.c4 + thumbOffset) - i3;
        int i6 = this.b4 + i5;
        Rect rect = this.n4;
        return f2 > ((float) i5) && f2 < ((float) (((i6 + rect.left) + rect.right) + i3)) && f3 > ((float) i4) && f3 < ((float) (this.f4 + i3));
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        if (!k0.b(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.Z3;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingLeft + this.N3 : compoundPaddingLeft;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingRight() {
        if (k0.b(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.Z3;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingRight + this.N3 : compoundPaddingRight;
    }

    public boolean getShowText() {
        return this.R3;
    }

    public boolean getSplitTrack() {
        return this.O3;
    }

    public int getSwitchMinWidth() {
        return this.M3;
    }

    public int getSwitchPadding() {
        return this.N3;
    }

    public CharSequence getTextOff() {
        return this.Q3;
    }

    public CharSequence getTextOn() {
        return this.P3;
    }

    public Drawable getThumbDrawable() {
        return this.s;
    }

    public int getThumbTextPadding() {
        return this.L3;
    }

    public ColorStateList getThumbTintList() {
        return this.x;
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.y;
    }

    public Drawable getTrackDrawable() {
        return this.G3;
    }

    public ColorStateList getTrackTintList() {
        return this.H3;
    }

    public PorterDuff.Mode getTrackTintMode() {
        return this.I3;
    }

    public final Layout h(CharSequence charSequence) {
        TransformationMethod transformationMethod = this.k4;
        if (transformationMethod != null) {
            charSequence = transformationMethod.getTransformation(charSequence, this);
        }
        CharSequence charSequence2 = charSequence;
        return new StaticLayout(charSequence2, this.g4, charSequence2 != null ? (int) Math.ceil(Layout.getDesiredWidth(charSequence2, r2)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    public void i(Context context, int i2) {
        e0 e0VarS = e0.s(context, i2, j.Z2);
        ColorStateList colorStateListC = e0VarS.c(j.d3);
        if (colorStateListC != null) {
            this.h4 = colorStateListC;
        } else {
            this.h4 = getTextColors();
        }
        int iE = e0VarS.e(j.a3, 0);
        if (iE != 0) {
            float f2 = iE;
            if (f2 != this.g4.getTextSize()) {
                this.g4.setTextSize(f2);
                requestLayout();
            }
        }
        k(e0VarS.j(j.b3, -1), e0VarS.j(j.c3, -1));
        if (e0VarS.a(j.k3, false)) {
            this.k4 = new b.b.o.a(getContext());
        } else {
            this.k4 = null;
        }
        e0VarS.v();
    }

    public void j(Typeface typeface, int i2) {
        if (i2 <= 0) {
            this.g4.setFakeBoldText(false);
            this.g4.setTextSkewX(0.0f);
            setSwitchTypeface(typeface);
        } else {
            Typeface typefaceDefaultFromStyle = typeface == null ? Typeface.defaultFromStyle(i2) : Typeface.create(typeface, i2);
            setSwitchTypeface(typefaceDefaultFromStyle);
            int i3 = (~(typefaceDefaultFromStyle != null ? typefaceDefaultFromStyle.getStyle() : 0)) & i2;
            this.g4.setFakeBoldText((i3 & 1) != 0);
            this.g4.setTextSkewX((i3 & 2) != 0 ? -0.25f : 0.0f);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.G3;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        ObjectAnimator objectAnimator = this.l4;
        if (objectAnimator == null || !objectAnimator.isStarted()) {
            return;
        }
        this.l4.end();
        this.l4 = null;
    }

    public final void k(int i2, int i3) {
        j(i2 != 1 ? i2 != 2 ? i2 != 3 ? null : Typeface.MONOSPACE : Typeface.SERIF : Typeface.SANS_SERIF, i3);
    }

    public final void l(MotionEvent motionEvent) {
        this.S3 = 0;
        boolean targetCheckedState = true;
        boolean z = motionEvent.getAction() == 1 && isEnabled();
        boolean zIsChecked = isChecked();
        if (z) {
            this.W3.computeCurrentVelocity(1000);
            float xVelocity = this.W3.getXVelocity();
            if (Math.abs(xVelocity) <= this.X3) {
                targetCheckedState = getTargetCheckedState();
            } else if (!k0.b(this) ? xVelocity <= 0.0f : xVelocity >= 0.0f) {
                targetCheckedState = false;
            }
        } else {
            targetCheckedState = zIsChecked;
        }
        if (targetCheckedState != zIsChecked) {
            playSoundEffect(0);
        }
        setChecked(targetCheckedState);
        e(motionEvent);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i2) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        if (isChecked()) {
            CompoundButton.mergeDrawableStates(iArrOnCreateDrawableState, f273d);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        int width;
        super.onDraw(canvas);
        Rect rect = this.n4;
        Drawable drawable = this.G3;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i2 = this.d4;
        int i3 = this.f4;
        int i4 = i2 + rect.top;
        int i5 = i3 - rect.bottom;
        Drawable drawable2 = this.s;
        if (drawable != null) {
            if (!this.O3 || drawable2 == null) {
                drawable.draw(canvas);
            } else {
                Rect rectD = q.d(drawable2);
                drawable2.copyBounds(rect);
                rect.left += rectD.left;
                rect.right -= rectD.right;
                int iSave = canvas.save();
                canvas.clipRect(rect, Region.Op.DIFFERENCE);
                drawable.draw(canvas);
                canvas.restoreToCount(iSave);
            }
        }
        int iSave2 = canvas.save();
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        Layout layout = getTargetCheckedState() ? this.i4 : this.j4;
        if (layout != null) {
            int[] drawableState = getDrawableState();
            ColorStateList colorStateList = this.h4;
            if (colorStateList != null) {
                this.g4.setColor(colorStateList.getColorForState(drawableState, 0));
            }
            this.g4.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                width = bounds.left + bounds.right;
            } else {
                width = getWidth();
            }
            canvas.translate((width / 2) - (layout.getWidth() / 2), ((i4 + i5) / 2) - (layout.getHeight() / 2));
            layout.draw(canvas);
        }
        canvas.restoreToCount(iSave2);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.widget.Switch");
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.widget.Switch");
        CharSequence charSequence = isChecked() ? this.P3 : this.Q3;
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        CharSequence text = accessibilityNodeInfo.getText();
        if (TextUtils.isEmpty(text)) {
            accessibilityNodeInfo.setText(charSequence);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(text);
        sb.append(' ');
        sb.append(charSequence);
        accessibilityNodeInfo.setText(sb);
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int iMax;
        int width;
        int paddingLeft;
        int i6;
        int paddingTop;
        int height;
        super.onLayout(z, i2, i3, i4, i5);
        int iMax2 = 0;
        if (this.s != null) {
            Rect rect = this.n4;
            Drawable drawable = this.G3;
            if (drawable != null) {
                drawable.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect rectD = q.d(this.s);
            iMax = Math.max(0, rectD.left - rect.left);
            iMax2 = Math.max(0, rectD.right - rect.right);
        } else {
            iMax = 0;
        }
        if (k0.b(this)) {
            paddingLeft = getPaddingLeft() + iMax;
            width = ((this.Z3 + paddingLeft) - iMax) - iMax2;
        } else {
            width = (getWidth() - getPaddingRight()) - iMax2;
            paddingLeft = (width - this.Z3) + iMax + iMax2;
        }
        int gravity = getGravity() & 112;
        if (gravity == 16) {
            int paddingTop2 = ((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2;
            i6 = this.a4;
            paddingTop = paddingTop2 - (i6 / 2);
        } else {
            if (gravity == 80) {
                height = getHeight() - getPaddingBottom();
                paddingTop = height - this.a4;
                this.c4 = paddingLeft;
                this.d4 = paddingTop;
                this.f4 = height;
                this.e4 = width;
            }
            paddingTop = getPaddingTop();
            i6 = this.a4;
        }
        height = i6 + paddingTop;
        this.c4 = paddingLeft;
        this.d4 = paddingTop;
        this.f4 = height;
        this.e4 = width;
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        int intrinsicWidth;
        int intrinsicHeight;
        if (this.R3) {
            if (this.i4 == null) {
                this.i4 = h(this.P3);
            }
            if (this.j4 == null) {
                this.j4 = h(this.Q3);
            }
        }
        Rect rect = this.n4;
        Drawable drawable = this.s;
        int intrinsicHeight2 = 0;
        if (drawable != null) {
            drawable.getPadding(rect);
            intrinsicWidth = (this.s.getIntrinsicWidth() - rect.left) - rect.right;
            intrinsicHeight = this.s.getIntrinsicHeight();
        } else {
            intrinsicWidth = 0;
            intrinsicHeight = 0;
        }
        this.b4 = Math.max(this.R3 ? Math.max(this.i4.getWidth(), this.j4.getWidth()) + (this.L3 * 2) : 0, intrinsicWidth);
        Drawable drawable2 = this.G3;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            intrinsicHeight2 = this.G3.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        int iMax = rect.left;
        int iMax2 = rect.right;
        Drawable drawable3 = this.s;
        if (drawable3 != null) {
            Rect rectD = q.d(drawable3);
            iMax = Math.max(iMax, rectD.left);
            iMax2 = Math.max(iMax2, rectD.right);
        }
        int iMax3 = Math.max(this.M3, (this.b4 * 2) + iMax + iMax2);
        int iMax4 = Math.max(intrinsicHeight2, intrinsicHeight);
        this.Z3 = iMax3;
        this.a4 = iMax4;
        super.onMeasure(i2, i3);
        if (getMeasuredHeight() < iMax4) {
            setMeasuredDimension(getMeasuredWidthAndState(), iMax4);
        }
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence charSequence = isChecked() ? this.P3 : this.Q3;
        if (charSequence != null) {
            accessibilityEvent.getText().add(charSequence);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0089  */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            android.view.VelocityTracker r0 = r6.W3
            r0.addMovement(r7)
            int r0 = r7.getActionMasked()
            r1 = 1
            if (r0 == 0) goto L9d
            r2 = 2
            if (r0 == r1) goto L89
            if (r0 == r2) goto L16
            r3 = 3
            if (r0 == r3) goto L89
            goto Lb7
        L16:
            int r0 = r6.S3
            if (r0 == r1) goto L55
            if (r0 == r2) goto L1e
            goto Lb7
        L1e:
            float r7 = r7.getX()
            int r0 = r6.getThumbScrollRange()
            float r2 = r6.U3
            float r2 = r7 - r2
            r3 = 1065353216(0x3f800000, float:1.0)
            r4 = 0
            if (r0 == 0) goto L32
            float r0 = (float) r0
            float r2 = r2 / r0
            goto L3b
        L32:
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L38
            r2 = r3
            goto L3b
        L38:
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            r2 = r0
        L3b:
            boolean r0 = b.b.q.k0.b(r6)
            if (r0 == 0) goto L42
            float r2 = -r2
        L42:
            float r0 = r6.Y3
            float r0 = r0 + r2
            float r0 = f(r0, r4, r3)
            float r2 = r6.Y3
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 == 0) goto L54
            r6.U3 = r7
            r6.setThumbPosition(r0)
        L54:
            return r1
        L55:
            float r0 = r7.getX()
            float r3 = r7.getY()
            float r4 = r6.U3
            float r4 = r0 - r4
            float r4 = java.lang.Math.abs(r4)
            int r5 = r6.T3
            float r5 = (float) r5
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 > 0) goto L7b
            float r4 = r6.V3
            float r4 = r3 - r4
            float r4 = java.lang.Math.abs(r4)
            int r5 = r6.T3
            float r5 = (float) r5
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto Lb7
        L7b:
            r6.S3 = r2
            android.view.ViewParent r7 = r6.getParent()
            r7.requestDisallowInterceptTouchEvent(r1)
            r6.U3 = r0
            r6.V3 = r3
            return r1
        L89:
            int r0 = r6.S3
            if (r0 != r2) goto L94
            r6.l(r7)
            super.onTouchEvent(r7)
            return r1
        L94:
            r0 = 0
            r6.S3 = r0
            android.view.VelocityTracker r0 = r6.W3
            r0.clear()
            goto Lb7
        L9d:
            float r0 = r7.getX()
            float r2 = r7.getY()
            boolean r3 = r6.isEnabled()
            if (r3 == 0) goto Lb7
            boolean r3 = r6.g(r0, r2)
            if (r3 == 0) goto Lb7
            r6.S3 = r1
            r6.U3 = r0
            r6.V3 = r2
        Lb7:
            boolean r7 = super.onTouchEvent(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SwitchCompat.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        super.setChecked(z);
        boolean zIsChecked = isChecked();
        if (getWindowToken() != null && s.G(this)) {
            a(zIsChecked);
        } else {
            d();
            setThumbPosition(zIsChecked ? 1.0f : 0.0f);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(i.n(this, callback));
    }

    public void setShowText(boolean z) {
        if (this.R3 != z) {
            this.R3 = z;
            requestLayout();
        }
    }

    public void setSplitTrack(boolean z) {
        this.O3 = z;
        invalidate();
    }

    public void setSwitchMinWidth(int i2) {
        this.M3 = i2;
        requestLayout();
    }

    public void setSwitchPadding(int i2) {
        this.N3 = i2;
        requestLayout();
    }

    public void setSwitchTypeface(Typeface typeface) {
        if ((this.g4.getTypeface() == null || this.g4.getTypeface().equals(typeface)) && (this.g4.getTypeface() != null || typeface == null)) {
            return;
        }
        this.g4.setTypeface(typeface);
        requestLayout();
        invalidate();
    }

    public void setTextOff(CharSequence charSequence) {
        this.Q3 = charSequence;
        requestLayout();
    }

    public void setTextOn(CharSequence charSequence) {
        this.P3 = charSequence;
        requestLayout();
    }

    public void setThumbDrawable(Drawable drawable) {
        Drawable drawable2 = this.s;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.s = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbPosition(float f2) {
        this.Y3 = f2;
        invalidate();
    }

    public void setThumbResource(int i2) {
        setThumbDrawable(b.b.l.a.a.d(getContext(), i2));
    }

    public void setThumbTextPadding(int i2) {
        this.L3 = i2;
        requestLayout();
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.x = colorStateList;
        this.E3 = true;
        b();
    }

    public void setThumbTintMode(PorterDuff.Mode mode) {
        this.y = mode;
        this.F3 = true;
        b();
    }

    public void setTrackDrawable(Drawable drawable) {
        Drawable drawable2 = this.G3;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.G3 = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i2) {
        setTrackDrawable(b.b.l.a.a.d(getContext(), i2));
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        this.H3 = colorStateList;
        this.J3 = true;
        c();
    }

    public void setTrackTintMode(PorterDuff.Mode mode) {
        this.I3 = mode;
        this.K3 = true;
        c();
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        setChecked(!isChecked());
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.s || drawable == this.G3;
    }

    public SwitchCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.b.a.L);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.x = null;
        this.y = null;
        this.E3 = false;
        this.F3 = false;
        this.H3 = null;
        this.I3 = null;
        this.J3 = false;
        this.K3 = false;
        this.W3 = VelocityTracker.obtain();
        this.n4 = new Rect();
        z.a(this, getContext());
        TextPaint textPaint = new TextPaint(1);
        this.g4 = textPaint;
        textPaint.density = getResources().getDisplayMetrics().density;
        int[] iArr = j.K2;
        e0 e0VarU = e0.u(context, attributeSet, iArr, i2, 0);
        s.R(this, context, iArr, attributeSet, e0VarU.q(), i2, 0);
        Drawable drawableF = e0VarU.f(j.N2);
        this.s = drawableF;
        if (drawableF != null) {
            drawableF.setCallback(this);
        }
        Drawable drawableF2 = e0VarU.f(j.W2);
        this.G3 = drawableF2;
        if (drawableF2 != null) {
            drawableF2.setCallback(this);
        }
        this.P3 = e0VarU.o(j.L2);
        this.Q3 = e0VarU.o(j.M2);
        this.R3 = e0VarU.a(j.O2, true);
        this.L3 = e0VarU.e(j.T2, 0);
        this.M3 = e0VarU.e(j.Q2, 0);
        this.N3 = e0VarU.e(j.R2, 0);
        this.O3 = e0VarU.a(j.P2, false);
        ColorStateList colorStateListC = e0VarU.c(j.U2);
        if (colorStateListC != null) {
            this.x = colorStateListC;
            this.E3 = true;
        }
        PorterDuff.Mode modeE = q.e(e0VarU.j(j.V2, -1), null);
        if (this.y != modeE) {
            this.y = modeE;
            this.F3 = true;
        }
        if (this.E3 || this.F3) {
            b();
        }
        ColorStateList colorStateListC2 = e0VarU.c(j.X2);
        if (colorStateListC2 != null) {
            this.H3 = colorStateListC2;
            this.J3 = true;
        }
        PorterDuff.Mode modeE2 = q.e(e0VarU.j(j.Y2, -1), null);
        if (this.I3 != modeE2) {
            this.I3 = modeE2;
            this.K3 = true;
        }
        if (this.J3 || this.K3) {
            c();
        }
        int iM = e0VarU.m(j.S2, 0);
        if (iM != 0) {
            i(context, iM);
        }
        m mVar = new m(this);
        this.m4 = mVar;
        mVar.m(attributeSet, i2);
        e0VarU.v();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.T3 = viewConfiguration.getScaledTouchSlop();
        this.X3 = viewConfiguration.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }
}
