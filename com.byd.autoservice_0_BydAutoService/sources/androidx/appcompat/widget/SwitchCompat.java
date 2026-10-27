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
import l0.t;
import n0.i;
import o.e0;
import o.k0;
import o.m;
import o.q;
import o.z;

/* JADX INFO: loaded from: classes.dex */
public class SwitchCompat extends CompoundButton {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Property<SwitchCompat, Float> f639b = new a(Float.class, "thumbPos");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f640c = {R.attr.state_checked};
    public float A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public final TextPaint I;
    public ColorStateList J;
    public Layout K;
    public Layout L;
    public TransformationMethod M;
    public ObjectAnimator N;
    public final m O;
    public final Rect P;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Drawable f641d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ColorStateList f642e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public PorterDuff.Mode f643f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f644g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f645h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Drawable f646i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ColorStateList f647j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public PorterDuff.Mode f648k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f649l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f650m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f651n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f652o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f653p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f654q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public CharSequence f655r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public CharSequence f656s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f657t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f658u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f659v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f660w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public float f661x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public VelocityTracker f662y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f663z;

    public class a extends Property<SwitchCompat, Float> {
        public a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(SwitchCompat switchCompat) {
            return Float.valueOf(switchCompat.A);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(SwitchCompat switchCompat, Float f3) {
            switchCompat.setThumbPosition(f3.floatValue());
        }
    }

    public SwitchCompat(Context context) {
        this(context, null);
    }

    public static float f(float f3, float f4, float f5) {
        return f3 < f4 ? f4 : f3 > f5 ? f5 : f3;
    }

    private boolean getTargetCheckedState() {
        return this.A > 0.5f;
    }

    private int getThumbOffset() {
        return (int) (((k0.b(this) ? 1.0f - this.A : this.A) * getThumbScrollRange()) + 0.5f);
    }

    private int getThumbScrollRange() {
        Drawable drawable = this.f646i;
        if (drawable == null) {
            return 0;
        }
        Rect rect = this.P;
        drawable.getPadding(rect);
        Drawable drawable2 = this.f641d;
        Rect rectD = drawable2 != null ? q.d(drawable2) : q.f4359c;
        return ((((this.B - this.D) - rect.left) - rect.right) - rectD.left) - rectD.right;
    }

    public final void a(boolean z3) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, f639b, z3 ? 1.0f : 0.0f);
        this.N = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(250L);
        if (Build.VERSION.SDK_INT >= 18) {
            this.N.setAutoCancel(true);
        }
        this.N.start();
    }

    public final void b() {
        Drawable drawable = this.f641d;
        if (drawable != null) {
            if (this.f644g || this.f645h) {
                Drawable drawableMutate = e0.a.r(drawable).mutate();
                this.f641d = drawableMutate;
                if (this.f644g) {
                    e0.a.o(drawableMutate, this.f642e);
                }
                if (this.f645h) {
                    e0.a.p(this.f641d, this.f643f);
                }
                if (this.f641d.isStateful()) {
                    this.f641d.setState(getDrawableState());
                }
            }
        }
    }

    public final void c() {
        Drawable drawable = this.f646i;
        if (drawable != null) {
            if (this.f649l || this.f650m) {
                Drawable drawableMutate = e0.a.r(drawable).mutate();
                this.f646i = drawableMutate;
                if (this.f649l) {
                    e0.a.o(drawableMutate, this.f647j);
                }
                if (this.f650m) {
                    e0.a.p(this.f646i, this.f648k);
                }
                if (this.f646i.isStateful()) {
                    this.f646i.setState(getDrawableState());
                }
            }
        }
    }

    public final void d() {
        ObjectAnimator objectAnimator = this.N;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int i3;
        int i4;
        Rect rect = this.P;
        int i5 = this.E;
        int i6 = this.F;
        int i7 = this.G;
        int i8 = this.H;
        int thumbOffset = getThumbOffset() + i5;
        Drawable drawable = this.f641d;
        Rect rectD = drawable != null ? q.d(drawable) : q.f4359c;
        Drawable drawable2 = this.f646i;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            int i9 = rect.left;
            thumbOffset += i9;
            if (rectD != null) {
                int i10 = rectD.left;
                if (i10 > i9) {
                    i5 += i10 - i9;
                }
                int i11 = rectD.top;
                int i12 = rect.top;
                i3 = i11 > i12 ? (i11 - i12) + i6 : i6;
                int i13 = rectD.right;
                int i14 = rect.right;
                if (i13 > i14) {
                    i7 -= i13 - i14;
                }
                int i15 = rectD.bottom;
                int i16 = rect.bottom;
                if (i15 > i16) {
                    i4 = i8 - (i15 - i16);
                }
                this.f646i.setBounds(i5, i3, i7, i4);
            } else {
                i3 = i6;
            }
            i4 = i8;
            this.f646i.setBounds(i5, i3, i7, i4);
        }
        Drawable drawable3 = this.f641d;
        if (drawable3 != null) {
            drawable3.getPadding(rect);
            int i17 = thumbOffset - rect.left;
            int i18 = thumbOffset + this.D + rect.right;
            this.f641d.setBounds(i17, i6, i18, i8);
            Drawable background = getBackground();
            if (background != null) {
                e0.a.l(background, i17, i6, i18, i8);
            }
        }
        super.draw(canvas);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableHotspotChanged(float f3, float f4) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.drawableHotspotChanged(f3, f4);
        }
        Drawable drawable = this.f641d;
        if (drawable != null) {
            e0.a.k(drawable, f3, f4);
        }
        Drawable drawable2 = this.f646i;
        if (drawable2 != null) {
            e0.a.k(drawable2, f3, f4);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f641d;
        boolean state = false;
        if (drawable != null && drawable.isStateful()) {
            state = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.f646i;
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

    public final boolean g(float f3, float f4) {
        if (this.f641d == null) {
            return false;
        }
        int thumbOffset = getThumbOffset();
        this.f641d.getPadding(this.P);
        int i3 = this.F;
        int i4 = this.f659v;
        int i5 = i3 - i4;
        int i6 = (this.E + thumbOffset) - i4;
        int i7 = this.D + i6;
        Rect rect = this.P;
        return f3 > ((float) i6) && f3 < ((float) (((i7 + rect.left) + rect.right) + i4)) && f4 > ((float) i5) && f4 < ((float) (this.H + i4));
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        if (!k0.b(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.B;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingLeft + this.f653p : compoundPaddingLeft;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingRight() {
        if (k0.b(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.B;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingRight + this.f653p : compoundPaddingRight;
    }

    public boolean getShowText() {
        return this.f657t;
    }

    public boolean getSplitTrack() {
        return this.f654q;
    }

    public int getSwitchMinWidth() {
        return this.f652o;
    }

    public int getSwitchPadding() {
        return this.f653p;
    }

    public CharSequence getTextOff() {
        return this.f656s;
    }

    public CharSequence getTextOn() {
        return this.f655r;
    }

    public Drawable getThumbDrawable() {
        return this.f641d;
    }

    public int getThumbTextPadding() {
        return this.f651n;
    }

    public ColorStateList getThumbTintList() {
        return this.f642e;
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.f643f;
    }

    public Drawable getTrackDrawable() {
        return this.f646i;
    }

    public ColorStateList getTrackTintList() {
        return this.f647j;
    }

    public PorterDuff.Mode getTrackTintMode() {
        return this.f648k;
    }

    public final Layout h(CharSequence charSequence) {
        TransformationMethod transformationMethod = this.M;
        if (transformationMethod != null) {
            charSequence = transformationMethod.getTransformation(charSequence, this);
        }
        CharSequence charSequence2 = charSequence;
        return new StaticLayout(charSequence2, this.I, charSequence2 != null ? (int) Math.ceil(Layout.getDesiredWidth(charSequence2, r2)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    public void i(Context context, int i3) {
        e0 e0VarT = e0.t(context, i3, R$styleable.TextAppearance);
        ColorStateList colorStateListC = e0VarT.c(R$styleable.TextAppearance_android_textColor);
        if (colorStateListC != null) {
            this.J = colorStateListC;
        } else {
            this.J = getTextColors();
        }
        int iF = e0VarT.f(R$styleable.TextAppearance_android_textSize, 0);
        if (iF != 0) {
            float f3 = iF;
            if (f3 != this.I.getTextSize()) {
                this.I.setTextSize(f3);
                requestLayout();
            }
        }
        k(e0VarT.k(R$styleable.TextAppearance_android_typeface, -1), e0VarT.k(R$styleable.TextAppearance_android_textStyle, -1));
        if (e0VarT.a(R$styleable.TextAppearance_textAllCaps, false)) {
            this.M = new l.a(getContext());
        } else {
            this.M = null;
        }
        e0VarT.w();
    }

    public void j(Typeface typeface, int i3) {
        if (i3 <= 0) {
            this.I.setFakeBoldText(false);
            this.I.setTextSkewX(0.0f);
            setSwitchTypeface(typeface);
        } else {
            Typeface typefaceDefaultFromStyle = typeface == null ? Typeface.defaultFromStyle(i3) : Typeface.create(typeface, i3);
            setSwitchTypeface(typefaceDefaultFromStyle);
            int i4 = (~(typefaceDefaultFromStyle != null ? typefaceDefaultFromStyle.getStyle() : 0)) & i3;
            this.I.setFakeBoldText((i4 & 1) != 0);
            this.I.setTextSkewX((i4 & 2) != 0 ? -0.25f : 0.0f);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f641d;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f646i;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        ObjectAnimator objectAnimator = this.N;
        if (objectAnimator == null || !objectAnimator.isStarted()) {
            return;
        }
        this.N.end();
        this.N = null;
    }

    public final void k(int i3, int i4) {
        j(i3 != 1 ? i3 != 2 ? i3 != 3 ? null : Typeface.MONOSPACE : Typeface.SERIF : Typeface.SANS_SERIF, i4);
    }

    public final void l(MotionEvent motionEvent) {
        this.f658u = 0;
        boolean targetCheckedState = true;
        boolean z3 = motionEvent.getAction() == 1 && isEnabled();
        boolean zIsChecked = isChecked();
        if (z3) {
            this.f662y.computeCurrentVelocity(1000);
            float xVelocity = this.f662y.getXVelocity();
            if (Math.abs(xVelocity) <= this.f663z) {
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
    public int[] onCreateDrawableState(int i3) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i3 + 1);
        if (isChecked()) {
            CompoundButton.mergeDrawableStates(iArrOnCreateDrawableState, f640c);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        int width;
        super.onDraw(canvas);
        Rect rect = this.P;
        Drawable drawable = this.f646i;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i3 = this.F;
        int i4 = this.H;
        int i5 = i3 + rect.top;
        int i6 = i4 - rect.bottom;
        Drawable drawable2 = this.f641d;
        if (drawable != null) {
            if (!this.f654q || drawable2 == null) {
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
        Layout layout = getTargetCheckedState() ? this.K : this.L;
        if (layout != null) {
            int[] drawableState = getDrawableState();
            ColorStateList colorStateList = this.J;
            if (colorStateList != null) {
                this.I.setColor(colorStateList.getColorForState(drawableState, 0));
            }
            this.I.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                width = bounds.left + bounds.right;
            } else {
                width = getWidth();
            }
            canvas.translate((width / 2) - (layout.getWidth() / 2), ((i5 + i6) / 2) - (layout.getHeight() / 2));
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
        CharSequence charSequence = isChecked() ? this.f655r : this.f656s;
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
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        int iMax;
        int width;
        int paddingLeft;
        int i7;
        int paddingTop;
        int height;
        super.onLayout(z3, i3, i4, i5, i6);
        int iMax2 = 0;
        if (this.f641d != null) {
            Rect rect = this.P;
            Drawable drawable = this.f646i;
            if (drawable != null) {
                drawable.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect rectD = q.d(this.f641d);
            iMax = Math.max(0, rectD.left - rect.left);
            iMax2 = Math.max(0, rectD.right - rect.right);
        } else {
            iMax = 0;
        }
        if (k0.b(this)) {
            paddingLeft = getPaddingLeft() + iMax;
            width = ((this.B + paddingLeft) - iMax) - iMax2;
        } else {
            width = (getWidth() - getPaddingRight()) - iMax2;
            paddingLeft = (width - this.B) + iMax + iMax2;
        }
        int gravity = getGravity() & 112;
        if (gravity == 16) {
            int paddingTop2 = ((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2;
            i7 = this.C;
            paddingTop = paddingTop2 - (i7 / 2);
        } else {
            if (gravity == 80) {
                height = getHeight() - getPaddingBottom();
                paddingTop = height - this.C;
                this.E = paddingLeft;
                this.F = paddingTop;
                this.H = height;
                this.G = width;
            }
            paddingTop = getPaddingTop();
            i7 = this.C;
        }
        height = i7 + paddingTop;
        this.E = paddingLeft;
        this.F = paddingTop;
        this.H = height;
        this.G = width;
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i3, int i4) {
        int intrinsicWidth;
        int intrinsicHeight;
        if (this.f657t) {
            if (this.K == null) {
                this.K = h(this.f655r);
            }
            if (this.L == null) {
                this.L = h(this.f656s);
            }
        }
        Rect rect = this.P;
        Drawable drawable = this.f641d;
        int intrinsicHeight2 = 0;
        if (drawable != null) {
            drawable.getPadding(rect);
            intrinsicWidth = (this.f641d.getIntrinsicWidth() - rect.left) - rect.right;
            intrinsicHeight = this.f641d.getIntrinsicHeight();
        } else {
            intrinsicWidth = 0;
            intrinsicHeight = 0;
        }
        this.D = Math.max(this.f657t ? Math.max(this.K.getWidth(), this.L.getWidth()) + (this.f651n * 2) : 0, intrinsicWidth);
        Drawable drawable2 = this.f646i;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            intrinsicHeight2 = this.f646i.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        int iMax = rect.left;
        int iMax2 = rect.right;
        Drawable drawable3 = this.f641d;
        if (drawable3 != null) {
            Rect rectD = q.d(drawable3);
            iMax = Math.max(iMax, rectD.left);
            iMax2 = Math.max(iMax2, rectD.right);
        }
        int iMax3 = Math.max(this.f652o, (this.D * 2) + iMax + iMax2);
        int iMax4 = Math.max(intrinsicHeight2, intrinsicHeight);
        this.B = iMax3;
        this.C = iMax4;
        super.onMeasure(i3, i4);
        if (getMeasuredHeight() < iMax4) {
            setMeasuredDimension(getMeasuredWidthAndState(), iMax4);
        }
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence charSequence = isChecked() ? this.f655r : this.f656s;
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
            android.view.VelocityTracker r0 = r6.f662y
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
            int r0 = r6.f658u
            if (r0 == r1) goto L55
            if (r0 == r2) goto L1e
            goto Lb7
        L1e:
            float r7 = r7.getX()
            int r0 = r6.getThumbScrollRange()
            float r2 = r6.f660w
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
            boolean r0 = o.k0.b(r6)
            if (r0 == 0) goto L42
            float r2 = -r2
        L42:
            float r0 = r6.A
            float r0 = r0 + r2
            float r0 = f(r0, r4, r3)
            float r2 = r6.A
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 == 0) goto L54
            r6.f660w = r7
            r6.setThumbPosition(r0)
        L54:
            return r1
        L55:
            float r0 = r7.getX()
            float r3 = r7.getY()
            float r4 = r6.f660w
            float r4 = r0 - r4
            float r4 = java.lang.Math.abs(r4)
            int r5 = r6.f659v
            float r5 = (float) r5
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 > 0) goto L7b
            float r4 = r6.f661x
            float r4 = r3 - r4
            float r4 = java.lang.Math.abs(r4)
            int r5 = r6.f659v
            float r5 = (float) r5
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto Lb7
        L7b:
            r6.f658u = r2
            android.view.ViewParent r7 = r6.getParent()
            r7.requestDisallowInterceptTouchEvent(r1)
            r6.f660w = r0
            r6.f661x = r3
            return r1
        L89:
            int r0 = r6.f658u
            if (r0 != r2) goto L94
            r6.l(r7)
            super.onTouchEvent(r7)
            return r1
        L94:
            r0 = 0
            r6.f658u = r0
            android.view.VelocityTracker r0 = r6.f662y
            r0.clear()
            goto Lb7
        L9d:
            float r0 = r7.getX()
            float r2 = r7.getY()
            boolean r3 = r6.isEnabled()
            if (r3 == 0) goto Lb7
            boolean r3 = r6.g(r0, r2)
            if (r3 == 0) goto Lb7
            r6.f658u = r1
            r6.f660w = r0
            r6.f661x = r2
        Lb7:
            boolean r7 = super.onTouchEvent(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SwitchCompat.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z3) {
        super.setChecked(z3);
        boolean zIsChecked = isChecked();
        if (getWindowToken() != null && t.P(this)) {
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

    public void setShowText(boolean z3) {
        if (this.f657t != z3) {
            this.f657t = z3;
            requestLayout();
        }
    }

    public void setSplitTrack(boolean z3) {
        this.f654q = z3;
        invalidate();
    }

    public void setSwitchMinWidth(int i3) {
        this.f652o = i3;
        requestLayout();
    }

    public void setSwitchPadding(int i3) {
        this.f653p = i3;
        requestLayout();
    }

    public void setSwitchTypeface(Typeface typeface) {
        if ((this.I.getTypeface() == null || this.I.getTypeface().equals(typeface)) && (this.I.getTypeface() != null || typeface == null)) {
            return;
        }
        this.I.setTypeface(typeface);
        requestLayout();
        invalidate();
    }

    public void setTextOff(CharSequence charSequence) {
        this.f656s = charSequence;
        requestLayout();
    }

    public void setTextOn(CharSequence charSequence) {
        this.f655r = charSequence;
        requestLayout();
    }

    public void setThumbDrawable(Drawable drawable) {
        Drawable drawable2 = this.f641d;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f641d = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbPosition(float f3) {
        this.A = f3;
        invalidate();
    }

    public void setThumbResource(int i3) {
        setThumbDrawable(j.a.d(getContext(), i3));
    }

    public void setThumbTextPadding(int i3) {
        this.f651n = i3;
        requestLayout();
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.f642e = colorStateList;
        this.f644g = true;
        b();
    }

    public void setThumbTintMode(PorterDuff.Mode mode) {
        this.f643f = mode;
        this.f645h = true;
        b();
    }

    public void setTrackDrawable(Drawable drawable) {
        Drawable drawable2 = this.f646i;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f646i = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i3) {
        setTrackDrawable(j.a.d(getContext(), i3));
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        this.f647j = colorStateList;
        this.f649l = true;
        c();
    }

    public void setTrackTintMode(PorterDuff.Mode mode) {
        this.f648k = mode;
        this.f650m = true;
        c();
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        setChecked(!isChecked());
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f641d || drawable == this.f646i;
    }

    public SwitchCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.switchStyle);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f642e = null;
        this.f643f = null;
        this.f644g = false;
        this.f645h = false;
        this.f647j = null;
        this.f648k = null;
        this.f649l = false;
        this.f650m = false;
        this.f662y = VelocityTracker.obtain();
        this.P = new Rect();
        z.a(this, getContext());
        TextPaint textPaint = new TextPaint(1);
        this.I = textPaint;
        textPaint.density = getResources().getDisplayMetrics().density;
        int[] iArr = R$styleable.SwitchCompat;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i3, 0);
        t.h0(this, context, iArr, attributeSet, e0VarV.r(), i3, 0);
        Drawable drawableG = e0VarV.g(R$styleable.SwitchCompat_android_thumb);
        this.f641d = drawableG;
        if (drawableG != null) {
            drawableG.setCallback(this);
        }
        Drawable drawableG2 = e0VarV.g(R$styleable.SwitchCompat_track);
        this.f646i = drawableG2;
        if (drawableG2 != null) {
            drawableG2.setCallback(this);
        }
        this.f655r = e0VarV.p(R$styleable.SwitchCompat_android_textOn);
        this.f656s = e0VarV.p(R$styleable.SwitchCompat_android_textOff);
        this.f657t = e0VarV.a(R$styleable.SwitchCompat_showText, true);
        this.f651n = e0VarV.f(R$styleable.SwitchCompat_thumbTextPadding, 0);
        this.f652o = e0VarV.f(R$styleable.SwitchCompat_switchMinWidth, 0);
        this.f653p = e0VarV.f(R$styleable.SwitchCompat_switchPadding, 0);
        this.f654q = e0VarV.a(R$styleable.SwitchCompat_splitTrack, false);
        ColorStateList colorStateListC = e0VarV.c(R$styleable.SwitchCompat_thumbTint);
        if (colorStateListC != null) {
            this.f642e = colorStateListC;
            this.f644g = true;
        }
        PorterDuff.Mode modeE = q.e(e0VarV.k(R$styleable.SwitchCompat_thumbTintMode, -1), null);
        if (this.f643f != modeE) {
            this.f643f = modeE;
            this.f645h = true;
        }
        if (this.f644g || this.f645h) {
            b();
        }
        ColorStateList colorStateListC2 = e0VarV.c(R$styleable.SwitchCompat_trackTint);
        if (colorStateListC2 != null) {
            this.f647j = colorStateListC2;
            this.f649l = true;
        }
        PorterDuff.Mode modeE2 = q.e(e0VarV.k(R$styleable.SwitchCompat_trackTintMode, -1), null);
        if (this.f648k != modeE2) {
            this.f648k = modeE2;
            this.f650m = true;
        }
        if (this.f649l || this.f650m) {
            c();
        }
        int iN = e0VarV.n(R$styleable.SwitchCompat_switchTextAppearance, 0);
        if (iN != 0) {
            i(context, iN);
        }
        m mVar = new m(this);
        this.O = mVar;
        mVar.m(attributeSet, i3);
        e0VarV.w();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f659v = viewConfiguration.getScaledTouchSlop();
        this.f663z = viewConfiguration.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }
}
