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
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
import o0.t;
import r.e0;
import r.k0;
import r.m;
import r.q;
import r.z;
import r0.i;

/* JADX INFO: loaded from: classes.dex */
public class SwitchCompat extends CompoundButton {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Property<SwitchCompat, Float> f738d = new a(Float.class, "thumbPos");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f739e = {R.attr.state_checked};
    public VelocityTracker A;
    public int B;
    public float C;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public final TextPaint K;
    public ColorStateList L;
    public Layout M;
    public Layout N;
    public TransformationMethod O;
    public ObjectAnimator P;
    public final m Q;
    public final Rect R;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f740f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ColorStateList f741g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public PorterDuff.Mode f742h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f743i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f744j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Drawable f745k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ColorStateList f746l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public PorterDuff.Mode f747m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f748n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f749o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f750p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f751q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f752r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f753s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public CharSequence f754t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public CharSequence f755u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f756v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f757w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f758x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f759y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public float f760z;

    public class a extends Property<SwitchCompat, Float> {
        public a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(SwitchCompat switchCompat) {
            return Float.valueOf(switchCompat.C);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(SwitchCompat switchCompat, Float f8) {
            switchCompat.setThumbPosition(f8.floatValue());
        }
    }

    public SwitchCompat(Context context) {
        this(context, null);
    }

    public static float f(float f8, float f9, float f10) {
        return f8 < f9 ? f9 : f8 > f10 ? f10 : f8;
    }

    private boolean getTargetCheckedState() {
        return this.C > 0.5f;
    }

    private int getThumbOffset() {
        return (int) (((k0.b(this) ? 1.0f - this.C : this.C) * getThumbScrollRange()) + 0.5f);
    }

    private int getThumbScrollRange() {
        Drawable drawable = this.f745k;
        if (drawable == null) {
            return 0;
        }
        Rect rect = this.R;
        drawable.getPadding(rect);
        Drawable drawable2 = this.f740f;
        Rect rectD = drawable2 != null ? q.d(drawable2) : q.f7618c;
        return ((((this.D - this.F) - rect.left) - rect.right) - rectD.left) - rectD.right;
    }

    public final void a(boolean z7) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, f738d, z7 ? 1.0f : 0.0f);
        this.P = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(250L);
        if (Build.VERSION.SDK_INT >= 18) {
            this.P.setAutoCancel(true);
        }
        this.P.start();
    }

    public final void b() {
        Drawable drawable = this.f740f;
        if (drawable != null) {
            if (this.f743i || this.f744j) {
                Drawable drawableMutate = h0.a.r(drawable).mutate();
                this.f740f = drawableMutate;
                if (this.f743i) {
                    h0.a.o(drawableMutate, this.f741g);
                }
                if (this.f744j) {
                    h0.a.p(this.f740f, this.f742h);
                }
                if (this.f740f.isStateful()) {
                    this.f740f.setState(getDrawableState());
                }
            }
        }
    }

    public final void c() {
        Drawable drawable = this.f745k;
        if (drawable != null) {
            if (this.f748n || this.f749o) {
                Drawable drawableMutate = h0.a.r(drawable).mutate();
                this.f745k = drawableMutate;
                if (this.f748n) {
                    h0.a.o(drawableMutate, this.f746l);
                }
                if (this.f749o) {
                    h0.a.p(this.f745k, this.f747m);
                }
                if (this.f745k.isStateful()) {
                    this.f745k.setState(getDrawableState());
                }
            }
        }
    }

    public final void d() {
        ObjectAnimator objectAnimator = this.P;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int i8;
        int i9;
        Rect rect = this.R;
        int i10 = this.G;
        int i11 = this.H;
        int i12 = this.I;
        int i13 = this.J;
        int thumbOffset = getThumbOffset() + i10;
        Drawable drawable = this.f740f;
        Rect rectD = drawable != null ? q.d(drawable) : q.f7618c;
        Drawable drawable2 = this.f745k;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            int i14 = rect.left;
            thumbOffset += i14;
            if (rectD != null) {
                int i15 = rectD.left;
                if (i15 > i14) {
                    i10 += i15 - i14;
                }
                int i16 = rectD.top;
                int i17 = rect.top;
                i8 = i16 > i17 ? (i16 - i17) + i11 : i11;
                int i18 = rectD.right;
                int i19 = rect.right;
                if (i18 > i19) {
                    i12 -= i18 - i19;
                }
                int i20 = rectD.bottom;
                int i21 = rect.bottom;
                if (i20 > i21) {
                    i9 = i13 - (i20 - i21);
                }
                this.f745k.setBounds(i10, i8, i12, i9);
            } else {
                i8 = i11;
            }
            i9 = i13;
            this.f745k.setBounds(i10, i8, i12, i9);
        }
        Drawable drawable3 = this.f740f;
        if (drawable3 != null) {
            drawable3.getPadding(rect);
            int i22 = thumbOffset - rect.left;
            int i23 = thumbOffset + this.F + rect.right;
            this.f740f.setBounds(i22, i11, i23, i13);
            Drawable background = getBackground();
            if (background != null) {
                h0.a.l(background, i22, i11, i23, i13);
            }
        }
        super.draw(canvas);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableHotspotChanged(float f8, float f9) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.drawableHotspotChanged(f8, f9);
        }
        Drawable drawable = this.f740f;
        if (drawable != null) {
            h0.a.k(drawable, f8, f9);
        }
        Drawable drawable2 = this.f745k;
        if (drawable2 != null) {
            h0.a.k(drawable2, f8, f9);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f740f;
        boolean state = false;
        if (drawable != null && drawable.isStateful()) {
            state = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.f745k;
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

    public final boolean g(float f8, float f9) {
        if (this.f740f == null) {
            return false;
        }
        int thumbOffset = getThumbOffset();
        this.f740f.getPadding(this.R);
        int i8 = this.H;
        int i9 = this.f758x;
        int i10 = i8 - i9;
        int i11 = (this.G + thumbOffset) - i9;
        int i12 = this.F + i11;
        Rect rect = this.R;
        return f8 > ((float) i11) && f8 < ((float) (((i12 + rect.left) + rect.right) + i9)) && f9 > ((float) i10) && f9 < ((float) (this.J + i9));
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        if (!k0.b(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.D;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingLeft + this.f752r : compoundPaddingLeft;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingRight() {
        if (k0.b(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.D;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingRight + this.f752r : compoundPaddingRight;
    }

    public boolean getShowText() {
        return this.f756v;
    }

    public boolean getSplitTrack() {
        return this.f753s;
    }

    public int getSwitchMinWidth() {
        return this.f751q;
    }

    public int getSwitchPadding() {
        return this.f752r;
    }

    public CharSequence getTextOff() {
        return this.f755u;
    }

    public CharSequence getTextOn() {
        return this.f754t;
    }

    public Drawable getThumbDrawable() {
        return this.f740f;
    }

    public int getThumbTextPadding() {
        return this.f750p;
    }

    public ColorStateList getThumbTintList() {
        return this.f741g;
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.f742h;
    }

    public Drawable getTrackDrawable() {
        return this.f745k;
    }

    public ColorStateList getTrackTintList() {
        return this.f746l;
    }

    public PorterDuff.Mode getTrackTintMode() {
        return this.f747m;
    }

    public final Layout h(CharSequence charSequence) {
        TransformationMethod transformationMethod = this.O;
        if (transformationMethod != null) {
            charSequence = transformationMethod.getTransformation(charSequence, this);
        }
        CharSequence charSequence2 = charSequence;
        return new StaticLayout(charSequence2, this.K, charSequence2 != null ? (int) Math.ceil(Layout.getDesiredWidth(charSequence2, r2)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    public void i(Context context, int i8) {
        e0 e0VarT = e0.t(context, i8, R$styleable.TextAppearance);
        ColorStateList colorStateListC = e0VarT.c(R$styleable.TextAppearance_android_textColor);
        if (colorStateListC != null) {
            this.L = colorStateListC;
        } else {
            this.L = getTextColors();
        }
        int iF = e0VarT.f(R$styleable.TextAppearance_android_textSize, 0);
        if (iF != 0) {
            float f8 = iF;
            if (f8 != this.K.getTextSize()) {
                this.K.setTextSize(f8);
                requestLayout();
            }
        }
        k(e0VarT.k(R$styleable.TextAppearance_android_typeface, -1), e0VarT.k(R$styleable.TextAppearance_android_textStyle, -1));
        if (e0VarT.a(R$styleable.TextAppearance_textAllCaps, false)) {
            this.O = new o.a(getContext());
        } else {
            this.O = null;
        }
        e0VarT.w();
    }

    public void j(Typeface typeface, int i8) {
        if (i8 <= 0) {
            this.K.setFakeBoldText(false);
            this.K.setTextSkewX(0.0f);
            setSwitchTypeface(typeface);
        } else {
            Typeface typefaceDefaultFromStyle = typeface == null ? Typeface.defaultFromStyle(i8) : Typeface.create(typeface, i8);
            setSwitchTypeface(typefaceDefaultFromStyle);
            int i9 = (~(typefaceDefaultFromStyle != null ? typefaceDefaultFromStyle.getStyle() : 0)) & i8;
            this.K.setFakeBoldText((i9 & 1) != 0);
            this.K.setTextSkewX((i9 & 2) != 0 ? -0.25f : 0.0f);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f740f;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f745k;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        ObjectAnimator objectAnimator = this.P;
        if (objectAnimator == null || !objectAnimator.isStarted()) {
            return;
        }
        this.P.end();
        this.P = null;
    }

    public final void k(int i8, int i9) {
        j(i8 != 1 ? i8 != 2 ? i8 != 3 ? null : Typeface.MONOSPACE : Typeface.SERIF : Typeface.SANS_SERIF, i9);
    }

    public final void l(MotionEvent motionEvent) {
        this.f757w = 0;
        boolean targetCheckedState = true;
        boolean z7 = motionEvent.getAction() == 1 && isEnabled();
        boolean zIsChecked = isChecked();
        if (z7) {
            this.A.computeCurrentVelocity(1000);
            float xVelocity = this.A.getXVelocity();
            if (Math.abs(xVelocity) <= this.B) {
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
    public int[] onCreateDrawableState(int i8) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i8 + 1);
        if (isChecked()) {
            CompoundButton.mergeDrawableStates(iArrOnCreateDrawableState, f739e);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        int width;
        super.onDraw(canvas);
        Rect rect = this.R;
        Drawable drawable = this.f745k;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i8 = this.H;
        int i9 = this.J;
        int i10 = i8 + rect.top;
        int i11 = i9 - rect.bottom;
        Drawable drawable2 = this.f740f;
        if (drawable != null) {
            if (!this.f753s || drawable2 == null) {
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
        Layout layout = getTargetCheckedState() ? this.M : this.N;
        if (layout != null) {
            int[] drawableState = getDrawableState();
            ColorStateList colorStateList = this.L;
            if (colorStateList != null) {
                this.K.setColor(colorStateList.getColorForState(drawableState, 0));
            }
            this.K.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                width = bounds.left + bounds.right;
            } else {
                width = getWidth();
            }
            canvas.translate((width / 2) - (layout.getWidth() / 2), ((i10 + i11) / 2) - (layout.getHeight() / 2));
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
        CharSequence charSequence = isChecked() ? this.f754t : this.f755u;
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
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        int iMax;
        int width;
        int paddingLeft;
        int i12;
        int paddingTop;
        int height;
        super.onLayout(z7, i8, i9, i10, i11);
        int iMax2 = 0;
        if (this.f740f != null) {
            Rect rect = this.R;
            Drawable drawable = this.f745k;
            if (drawable != null) {
                drawable.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect rectD = q.d(this.f740f);
            iMax = Math.max(0, rectD.left - rect.left);
            iMax2 = Math.max(0, rectD.right - rect.right);
        } else {
            iMax = 0;
        }
        if (k0.b(this)) {
            paddingLeft = getPaddingLeft() + iMax;
            width = ((this.D + paddingLeft) - iMax) - iMax2;
        } else {
            width = (getWidth() - getPaddingRight()) - iMax2;
            paddingLeft = (width - this.D) + iMax + iMax2;
        }
        int gravity = getGravity() & 112;
        if (gravity == 16) {
            int paddingTop2 = ((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2;
            i12 = this.E;
            paddingTop = paddingTop2 - (i12 / 2);
        } else {
            if (gravity == 80) {
                height = getHeight() - getPaddingBottom();
                paddingTop = height - this.E;
                this.G = paddingLeft;
                this.H = paddingTop;
                this.J = height;
                this.I = width;
            }
            paddingTop = getPaddingTop();
            i12 = this.E;
        }
        height = i12 + paddingTop;
        this.G = paddingLeft;
        this.H = paddingTop;
        this.J = height;
        this.I = width;
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i8, int i9) {
        int intrinsicWidth;
        int intrinsicHeight;
        if (this.f756v) {
            if (this.M == null) {
                this.M = h(this.f754t);
            }
            if (this.N == null) {
                this.N = h(this.f755u);
            }
        }
        Rect rect = this.R;
        Drawable drawable = this.f740f;
        int intrinsicHeight2 = 0;
        if (drawable != null) {
            drawable.getPadding(rect);
            intrinsicWidth = (this.f740f.getIntrinsicWidth() - rect.left) - rect.right;
            intrinsicHeight = this.f740f.getIntrinsicHeight();
        } else {
            intrinsicWidth = 0;
            intrinsicHeight = 0;
        }
        this.F = Math.max(this.f756v ? Math.max(this.M.getWidth(), this.N.getWidth()) + (this.f750p * 2) : 0, intrinsicWidth);
        Drawable drawable2 = this.f745k;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            intrinsicHeight2 = this.f745k.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        int iMax = rect.left;
        int iMax2 = rect.right;
        Drawable drawable3 = this.f740f;
        if (drawable3 != null) {
            Rect rectD = q.d(drawable3);
            iMax = Math.max(iMax, rectD.left);
            iMax2 = Math.max(iMax2, rectD.right);
        }
        int iMax3 = Math.max(this.f751q, (this.F * 2) + iMax + iMax2);
        int iMax4 = Math.max(intrinsicHeight2, intrinsicHeight);
        this.D = iMax3;
        this.E = iMax4;
        super.onMeasure(i8, i9);
        if (getMeasuredHeight() < iMax4) {
            setMeasuredDimension(getMeasuredWidthAndState(), iMax4);
        }
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence charSequence = isChecked() ? this.f754t : this.f755u;
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
            android.view.VelocityTracker r0 = r6.A
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
            int r0 = r6.f757w
            if (r0 == r1) goto L55
            if (r0 == r2) goto L1e
            goto Lb7
        L1e:
            float r7 = r7.getX()
            int r0 = r6.getThumbScrollRange()
            float r2 = r6.f759y
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
            boolean r0 = r.k0.b(r6)
            if (r0 == 0) goto L42
            float r2 = -r2
        L42:
            float r0 = r6.C
            float r0 = r0 + r2
            float r0 = f(r0, r4, r3)
            float r2 = r6.C
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 == 0) goto L54
            r6.f759y = r7
            r6.setThumbPosition(r0)
        L54:
            return r1
        L55:
            float r0 = r7.getX()
            float r3 = r7.getY()
            float r4 = r6.f759y
            float r4 = r0 - r4
            float r4 = java.lang.Math.abs(r4)
            int r5 = r6.f758x
            float r5 = (float) r5
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 > 0) goto L7b
            float r4 = r6.f760z
            float r4 = r3 - r4
            float r4 = java.lang.Math.abs(r4)
            int r5 = r6.f758x
            float r5 = (float) r5
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto Lb7
        L7b:
            r6.f757w = r2
            android.view.ViewParent r7 = r6.getParent()
            r7.requestDisallowInterceptTouchEvent(r1)
            r6.f759y = r0
            r6.f760z = r3
            return r1
        L89:
            int r0 = r6.f757w
            if (r0 != r2) goto L94
            r6.l(r7)
            super.onTouchEvent(r7)
            return r1
        L94:
            r0 = 0
            r6.f757w = r0
            android.view.VelocityTracker r0 = r6.A
            r0.clear()
            goto Lb7
        L9d:
            float r0 = r7.getX()
            float r2 = r7.getY()
            boolean r3 = r6.isEnabled()
            if (r3 == 0) goto Lb7
            boolean r3 = r6.g(r0, r2)
            if (r3 == 0) goto Lb7
            r6.f757w = r1
            r6.f759y = r0
            r6.f760z = r2
        Lb7:
            boolean r7 = super.onTouchEvent(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SwitchCompat.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z7) {
        super.setChecked(z7);
        boolean zIsChecked = isChecked();
        if (getWindowToken() != null && t.Q(this)) {
            a(zIsChecked);
        } else {
            d();
            setThumbPosition(zIsChecked ? 1.0f : 0.0f);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(i.s(this, callback));
    }

    public void setShowText(boolean z7) {
        if (this.f756v != z7) {
            this.f756v = z7;
            requestLayout();
        }
    }

    public void setSplitTrack(boolean z7) {
        this.f753s = z7;
        invalidate();
    }

    public void setSwitchMinWidth(int i8) {
        this.f751q = i8;
        requestLayout();
    }

    public void setSwitchPadding(int i8) {
        this.f752r = i8;
        requestLayout();
    }

    public void setSwitchTypeface(Typeface typeface) {
        if ((this.K.getTypeface() == null || this.K.getTypeface().equals(typeface)) && (this.K.getTypeface() != null || typeface == null)) {
            return;
        }
        this.K.setTypeface(typeface);
        requestLayout();
        invalidate();
    }

    public void setTextOff(CharSequence charSequence) {
        this.f755u = charSequence;
        requestLayout();
    }

    public void setTextOn(CharSequence charSequence) {
        this.f754t = charSequence;
        requestLayout();
    }

    public void setThumbDrawable(Drawable drawable) {
        Drawable drawable2 = this.f740f;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f740f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbPosition(float f8) {
        this.C = f8;
        invalidate();
    }

    public void setThumbResource(int i8) {
        setThumbDrawable(m.a.d(getContext(), i8));
    }

    public void setThumbTextPadding(int i8) {
        this.f750p = i8;
        requestLayout();
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.f741g = colorStateList;
        this.f743i = true;
        b();
    }

    public void setThumbTintMode(PorterDuff.Mode mode) {
        this.f742h = mode;
        this.f744j = true;
        b();
    }

    public void setTrackDrawable(Drawable drawable) {
        Drawable drawable2 = this.f745k;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f745k = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i8) {
        setTrackDrawable(m.a.d(getContext(), i8));
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        this.f746l = colorStateList;
        this.f748n = true;
        c();
    }

    public void setTrackTintMode(PorterDuff.Mode mode) {
        this.f747m = mode;
        this.f749o = true;
        c();
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        setChecked(!isChecked());
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f740f || drawable == this.f745k;
    }

    public SwitchCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.switchStyle);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f741g = null;
        this.f742h = null;
        this.f743i = false;
        this.f744j = false;
        this.f746l = null;
        this.f747m = null;
        this.f748n = false;
        this.f749o = false;
        this.A = VelocityTracker.obtain();
        this.R = new Rect();
        z.a(this, getContext());
        TextPaint textPaint = new TextPaint(1);
        this.K = textPaint;
        textPaint.density = getResources().getDisplayMetrics().density;
        int[] iArr = R$styleable.SwitchCompat;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i8, 0);
        t.j0(this, context, iArr, attributeSet, e0VarV.r(), i8, 0);
        Drawable drawableG = e0VarV.g(R$styleable.SwitchCompat_android_thumb);
        this.f740f = drawableG;
        if (drawableG != null) {
            drawableG.setCallback(this);
        }
        Drawable drawableG2 = e0VarV.g(R$styleable.SwitchCompat_track);
        this.f745k = drawableG2;
        if (drawableG2 != null) {
            drawableG2.setCallback(this);
        }
        this.f754t = e0VarV.p(R$styleable.SwitchCompat_android_textOn);
        this.f755u = e0VarV.p(R$styleable.SwitchCompat_android_textOff);
        this.f756v = e0VarV.a(R$styleable.SwitchCompat_showText, true);
        this.f750p = e0VarV.f(R$styleable.SwitchCompat_thumbTextPadding, 0);
        this.f751q = e0VarV.f(R$styleable.SwitchCompat_switchMinWidth, 0);
        this.f752r = e0VarV.f(R$styleable.SwitchCompat_switchPadding, 0);
        this.f753s = e0VarV.a(R$styleable.SwitchCompat_splitTrack, false);
        ColorStateList colorStateListC = e0VarV.c(R$styleable.SwitchCompat_thumbTint);
        if (colorStateListC != null) {
            this.f741g = colorStateListC;
            this.f743i = true;
        }
        PorterDuff.Mode modeE = q.e(e0VarV.k(R$styleable.SwitchCompat_thumbTintMode, -1), null);
        if (this.f742h != modeE) {
            this.f742h = modeE;
            this.f744j = true;
        }
        if (this.f743i || this.f744j) {
            b();
        }
        ColorStateList colorStateListC2 = e0VarV.c(R$styleable.SwitchCompat_trackTint);
        if (colorStateListC2 != null) {
            this.f746l = colorStateListC2;
            this.f748n = true;
        }
        PorterDuff.Mode modeE2 = q.e(e0VarV.k(R$styleable.SwitchCompat_trackTintMode, -1), null);
        if (this.f747m != modeE2) {
            this.f747m = modeE2;
            this.f749o = true;
        }
        if (this.f748n || this.f749o) {
            c();
        }
        int iN = e0VarV.n(R$styleable.SwitchCompat_switchTextAppearance, 0);
        if (iN != 0) {
            i(context, iN);
        }
        m mVar = new m(this);
        this.Q = mVar;
        mVar.m(attributeSet, i8);
        e0VarV.w();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f758x = viewConfiguration.getScaledTouchSlop();
        this.B = viewConfiguration.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }
}
