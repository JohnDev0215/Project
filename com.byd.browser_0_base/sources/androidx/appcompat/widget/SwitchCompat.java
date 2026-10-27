package androidx.appcompat.widget;

import android.R;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import android.widget.CompoundButton;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.text.AllCapsTransformationMethod;
import androidx.core.R$id;
import androidx.emoji2.text.d;
import defpackage.in3;
import defpackage.mm3;
import defpackage.o83;
import defpackage.om3;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SwitchCompat extends CompoundButton implements EmojiCompatConfigurationView {
    public static final Property<SwitchCompat, Float> V = new Property<SwitchCompat, Float>() { // from class: androidx.appcompat.widget.SwitchCompat.1
        @Override // android.util.Property
        public final Float get(SwitchCompat switchCompat) {
            return Float.valueOf(switchCompat.D);
        }

        @Override // android.util.Property
        public final void set(SwitchCompat switchCompat, Float f) {
            switchCompat.setThumbPosition(f.floatValue());
        }
    };
    public static final int[] W = {R.attr.state_checked};
    public float A;
    public final VelocityTracker B;
    public final int C;
    public float D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f4J;
    public int K;
    public boolean L;
    public final TextPaint M;
    public ColorStateList N;
    public StaticLayout O;
    public StaticLayout P;
    public AllCapsTransformationMethod Q;
    public ObjectAnimator R;
    public AppCompatEmojiTextHelper S;
    public EmojiCompatInitCallback T;
    public final Rect U;
    public Drawable e;
    public ColorStateList f;
    public PorterDuff.Mode g;
    public boolean h;
    public boolean i;
    public Drawable j;
    public ColorStateList k;
    public PorterDuff.Mode l;
    public boolean m;
    public boolean n;
    public int o;
    public int p;
    public int q;
    public boolean r;
    public CharSequence s;
    public CharSequence t;
    public CharSequence u;
    public CharSequence v;
    public boolean w;
    public int x;
    public final int y;
    public float z;

    public static class Api18Impl {
        private Api18Impl() {
        }
    }

    public static class EmojiCompatInitCallback extends d.f {
        public final WeakReference a;

        public EmojiCompatInitCallback(SwitchCompat switchCompat) {
            this.a = new WeakReference(switchCompat);
        }

        @Override // androidx.emoji2.text.d.f
        public final void a() {
            SwitchCompat switchCompat = (SwitchCompat) this.a.get();
            if (switchCompat != null) {
                switchCompat.d();
            }
        }

        @Override // androidx.emoji2.text.d.f
        public final void b() {
            SwitchCompat switchCompat = (SwitchCompat) this.a.get();
            if (switchCompat != null) {
                switchCompat.d();
            }
        }
    }

    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion<SwitchCompat> {
        public boolean a = false;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;
        public int j;
        public int k;
        public int l;
        public int m;
        public int n;

        @Override // android.view.inspector.InspectionCompanion
        public final void mapProperties(PropertyMapper propertyMapper) {
            this.b = propertyMapper.mapObject("textOff", R.attr.textOff);
            this.c = propertyMapper.mapObject("textOn", R.attr.textOn);
            this.d = propertyMapper.mapObject("thumb", R.attr.thumb);
            this.e = propertyMapper.mapBoolean("showText", androidx.appcompat.R.attr.showText);
            this.f = propertyMapper.mapBoolean("splitTrack", androidx.appcompat.R.attr.splitTrack);
            this.g = propertyMapper.mapInt("switchMinWidth", androidx.appcompat.R.attr.switchMinWidth);
            this.h = propertyMapper.mapInt("switchPadding", androidx.appcompat.R.attr.switchPadding);
            this.i = propertyMapper.mapInt("thumbTextPadding", androidx.appcompat.R.attr.thumbTextPadding);
            this.j = propertyMapper.mapObject("thumbTint", androidx.appcompat.R.attr.thumbTint);
            this.k = propertyMapper.mapObject("thumbTintMode", androidx.appcompat.R.attr.thumbTintMode);
            this.l = propertyMapper.mapObject("track", androidx.appcompat.R.attr.track);
            this.m = propertyMapper.mapObject("trackTint", androidx.appcompat.R.attr.trackTint);
            this.n = propertyMapper.mapObject("trackTintMode", androidx.appcompat.R.attr.trackTintMode);
            this.a = true;
        }

        @Override // android.view.inspector.InspectionCompanion
        public final void readProperties(SwitchCompat switchCompat, PropertyReader propertyReader) {
            SwitchCompat switchCompat2 = switchCompat;
            if (!this.a) {
                throw new InspectionCompanion.UninitializedPropertyMapException();
            }
            propertyReader.readObject(this.b, switchCompat2.getTextOff());
            propertyReader.readObject(this.c, switchCompat2.getTextOn());
            propertyReader.readObject(this.d, switchCompat2.getThumbDrawable());
            propertyReader.readBoolean(this.e, switchCompat2.getShowText());
            propertyReader.readBoolean(this.f, switchCompat2.getSplitTrack());
            propertyReader.readInt(this.g, switchCompat2.getSwitchMinWidth());
            propertyReader.readInt(this.h, switchCompat2.getSwitchPadding());
            propertyReader.readInt(this.i, switchCompat2.getThumbTextPadding());
            propertyReader.readObject(this.j, switchCompat2.getThumbTintList());
            propertyReader.readObject(this.k, switchCompat2.getThumbTintMode());
            propertyReader.readObject(this.l, switchCompat2.getTrackDrawable());
            propertyReader.readObject(this.m, switchCompat2.getTrackTintList());
            propertyReader.readObject(this.n, switchCompat2.getTrackTintMode());
        }
    }

    public SwitchCompat(Context context) {
        this(context, null);
    }

    private AppCompatEmojiTextHelper getEmojiTextViewHelper() {
        if (this.S == null) {
            this.S = new AppCompatEmojiTextHelper(this);
        }
        return this.S;
    }

    private boolean getTargetCheckedState() {
        return this.D > 0.5f;
    }

    private int getThumbOffset() {
        return (int) (((ViewUtils.a(this) ? 1.0f - this.D : this.D) * getThumbScrollRange()) + 0.5f);
    }

    private int getThumbScrollRange() {
        Drawable drawable = this.j;
        if (drawable == null) {
            return 0;
        }
        Rect rect = this.U;
        drawable.getPadding(rect);
        Drawable drawable2 = this.e;
        Rect rectB = drawable2 != null ? DrawableUtils.b(drawable2) : DrawableUtils.c;
        return ((((this.E - this.G) - rect.left) - rect.right) - rectB.left) - rectB.right;
    }

    private void setTextOffInternal(CharSequence charSequence) {
        this.u = charSequence;
        AppCompatEmojiTextHelper emojiTextViewHelper = getEmojiTextViewHelper();
        TransformationMethod transformationMethodE = emojiTextViewHelper.b.a.e(this.Q);
        if (transformationMethodE != null) {
            charSequence = transformationMethodE.getTransformation(charSequence, this);
        }
        this.v = charSequence;
        this.P = null;
        if (this.w) {
            e();
        }
    }

    private void setTextOnInternal(CharSequence charSequence) {
        this.s = charSequence;
        AppCompatEmojiTextHelper emojiTextViewHelper = getEmojiTextViewHelper();
        TransformationMethod transformationMethodE = emojiTextViewHelper.b.a.e(this.Q);
        if (transformationMethodE != null) {
            charSequence = transformationMethodE.getTransformation(charSequence, this);
        }
        this.t = charSequence;
        this.O = null;
        if (this.w) {
            e();
        }
    }

    public final void a() {
        Drawable drawable = this.e;
        if (drawable != null) {
            if (this.h || this.i) {
                Drawable drawableMutate = drawable.mutate();
                this.e = drawableMutate;
                if (this.h) {
                    drawableMutate.setTintList(this.f);
                }
                if (this.i) {
                    this.e.setTintMode(this.g);
                }
                if (this.e.isStateful()) {
                    this.e.setState(getDrawableState());
                }
            }
        }
    }

    public final void b() {
        Drawable drawable = this.j;
        if (drawable != null) {
            if (this.m || this.n) {
                Drawable drawableMutate = drawable.mutate();
                this.j = drawableMutate;
                if (this.m) {
                    drawableMutate.setTintList(this.k);
                }
                if (this.n) {
                    this.j.setTintMode(this.l);
                }
                if (this.j.isStateful()) {
                    this.j.setState(getDrawableState());
                }
            }
        }
    }

    public final StaticLayout c(CharSequence charSequence) {
        return new StaticLayout(charSequence, this.M, charSequence != null ? (int) Math.ceil(Layout.getDesiredWidth(charSequence, r2)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    public final void d() {
        setTextOnInternal(this.s);
        setTextOffInternal(this.u);
        requestLayout();
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int i;
        int i2;
        int i3 = this.H;
        int i4 = this.I;
        int i5 = this.f4J;
        int i6 = this.K;
        int thumbOffset = getThumbOffset() + i3;
        Drawable drawable = this.e;
        Rect rectB = drawable != null ? DrawableUtils.b(drawable) : DrawableUtils.c;
        Drawable drawable2 = this.j;
        Rect rect = this.U;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            int i7 = rect.left;
            thumbOffset += i7;
            if (rectB != null) {
                int i8 = rectB.left;
                if (i8 > i7) {
                    i3 += i8 - i7;
                }
                int i9 = rectB.top;
                int i10 = rect.top;
                i = i9 > i10 ? (i9 - i10) + i4 : i4;
                int i11 = rectB.right;
                int i12 = rect.right;
                if (i11 > i12) {
                    i5 -= i11 - i12;
                }
                int i13 = rectB.bottom;
                int i14 = rect.bottom;
                if (i13 > i14) {
                    i2 = i6 - (i13 - i14);
                }
                this.j.setBounds(i3, i, i5, i2);
            } else {
                i = i4;
            }
            i2 = i6;
            this.j.setBounds(i3, i, i5, i2);
        }
        Drawable drawable3 = this.e;
        if (drawable3 != null) {
            drawable3.getPadding(rect);
            int i15 = thumbOffset - rect.left;
            int i16 = thumbOffset + this.G + rect.right;
            this.e.setBounds(i15, i4, i16, i6);
            Drawable background = getBackground();
            if (background != null) {
                background.setHotspotBounds(i15, i4, i16, i6);
            }
        }
        super.draw(canvas);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableHotspotChanged(float f, float f2) {
        super.drawableHotspotChanged(f, f2);
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.setHotspot(f, f2);
        }
        Drawable drawable2 = this.j;
        if (drawable2 != null) {
            drawable2.setHotspot(f, f2);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.e;
        boolean state = false;
        if (drawable != null && drawable.isStateful()) {
            state = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.j;
        if (drawable2 != null && drawable2.isStateful()) {
            state |= drawable2.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    public final void e() {
        if (this.T == null && this.S.b.a.b()) {
            if (d.k != null) {
                d dVarA = d.a();
                int iB = dVarA.b();
                if (iB == 3 || iB == 0) {
                    EmojiCompatInitCallback emojiCompatInitCallback = new EmojiCompatInitCallback(this);
                    this.T = emojiCompatInitCallback;
                    dVarA.g(emojiCompatInitCallback);
                }
            }
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        if (!ViewUtils.a(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.E;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingLeft + this.q : compoundPaddingLeft;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingRight() {
        if (ViewUtils.a(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.E;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingRight + this.q : compoundPaddingRight;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return o83.a(super.getCustomSelectionActionModeCallback());
    }

    public boolean getShowText() {
        return this.w;
    }

    public boolean getSplitTrack() {
        return this.r;
    }

    public int getSwitchMinWidth() {
        return this.p;
    }

    public int getSwitchPadding() {
        return this.q;
    }

    public CharSequence getTextOff() {
        return this.u;
    }

    public CharSequence getTextOn() {
        return this.s;
    }

    public Drawable getThumbDrawable() {
        return this.e;
    }

    public final float getThumbPosition() {
        return this.D;
    }

    public int getThumbTextPadding() {
        return this.o;
    }

    public ColorStateList getThumbTintList() {
        return this.f;
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.g;
    }

    public Drawable getTrackDrawable() {
        return this.j;
    }

    public ColorStateList getTrackTintList() {
        return this.k;
    }

    public PorterDuff.Mode getTrackTintMode() {
        return this.l;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.j;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        ObjectAnimator objectAnimator = this.R;
        if (objectAnimator == null || !objectAnimator.isStarted()) {
            return;
        }
        this.R.end();
        this.R = null;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, W);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        int width;
        super.onDraw(canvas);
        Drawable drawable = this.j;
        Rect rect = this.U;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i = this.I;
        int i2 = this.K;
        int i3 = i + rect.top;
        int i4 = i2 - rect.bottom;
        Drawable drawable2 = this.e;
        if (drawable != null) {
            if (!this.r || drawable2 == null) {
                drawable.draw(canvas);
            } else {
                Rect rectB = DrawableUtils.b(drawable2);
                drawable2.copyBounds(rect);
                rect.left += rectB.left;
                rect.right -= rectB.right;
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
        StaticLayout staticLayout = getTargetCheckedState() ? this.O : this.P;
        if (staticLayout != null) {
            int[] drawableState = getDrawableState();
            ColorStateList colorStateList = this.N;
            TextPaint textPaint = this.M;
            if (colorStateList != null) {
                textPaint.setColor(colorStateList.getColorForState(drawableState, 0));
            }
            textPaint.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                width = bounds.left + bounds.right;
            } else {
                width = getWidth();
            }
            canvas.translate((width / 2) - (staticLayout.getWidth() / 2), ((i3 + i4) / 2) - (staticLayout.getHeight() / 2));
            staticLayout.draw(canvas);
        }
        canvas.restoreToCount(iSave2);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.widget.Switch");
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.widget.Switch");
        if (Build.VERSION.SDK_INT < 30) {
            CharSequence charSequence = isChecked() ? this.s : this.u;
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
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int iMax;
        int width;
        int paddingLeft;
        int height;
        int paddingTop;
        super.onLayout(z, i, i2, i3, i4);
        int iMax2 = 0;
        if (this.e != null) {
            Drawable drawable = this.j;
            Rect rect = this.U;
            if (drawable != null) {
                drawable.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect rectB = DrawableUtils.b(this.e);
            iMax = Math.max(0, rectB.left - rect.left);
            iMax2 = Math.max(0, rectB.right - rect.right);
        } else {
            iMax = 0;
        }
        if (ViewUtils.a(this)) {
            paddingLeft = getPaddingLeft() + iMax;
            width = ((this.E + paddingLeft) - iMax) - iMax2;
        } else {
            width = (getWidth() - getPaddingRight()) - iMax2;
            paddingLeft = (width - this.E) + iMax + iMax2;
        }
        int gravity = getGravity() & 112;
        if (gravity == 16) {
            int height2 = ((getHeight() + getPaddingTop()) - getPaddingBottom()) / 2;
            int i5 = this.F;
            int i6 = height2 - (i5 / 2);
            height = i5 + i6;
            paddingTop = i6;
        } else if (gravity != 80) {
            paddingTop = getPaddingTop();
            height = this.F + paddingTop;
        } else {
            height = getHeight() - getPaddingBottom();
            paddingTop = height - this.F;
        }
        this.H = paddingLeft;
        this.I = paddingTop;
        this.K = height;
        this.f4J = width;
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        int intrinsicWidth;
        int intrinsicHeight;
        if (this.w) {
            if (this.O == null) {
                this.O = c(this.t);
            }
            if (this.P == null) {
                this.P = c(this.v);
            }
        }
        Drawable drawable = this.e;
        int intrinsicHeight2 = 0;
        Rect rect = this.U;
        if (drawable != null) {
            drawable.getPadding(rect);
            intrinsicWidth = (this.e.getIntrinsicWidth() - rect.left) - rect.right;
            intrinsicHeight = this.e.getIntrinsicHeight();
        } else {
            intrinsicWidth = 0;
            intrinsicHeight = 0;
        }
        this.G = Math.max(this.w ? (this.o * 2) + Math.max(this.O.getWidth(), this.P.getWidth()) : 0, intrinsicWidth);
        Drawable drawable2 = this.j;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            intrinsicHeight2 = this.j.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        int iMax = rect.left;
        int iMax2 = rect.right;
        Drawable drawable3 = this.e;
        if (drawable3 != null) {
            Rect rectB = DrawableUtils.b(drawable3);
            iMax = Math.max(iMax, rectB.left);
            iMax2 = Math.max(iMax2, rectB.right);
        }
        int iMax3 = this.L ? Math.max(this.p, (this.G * 2) + iMax + iMax2) : this.p;
        int iMax4 = Math.max(intrinsicHeight2, intrinsicHeight);
        this.E = iMax3;
        this.F = iMax4;
        super.onMeasure(i, i2);
        if (getMeasuredHeight() < iMax4) {
            setMeasuredDimension(getMeasuredWidthAndState(), iMax4);
        }
    }

    @Override // android.view.View
    public final void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence charSequence = isChecked() ? this.s : this.u;
        if (charSequence != null) {
            accessibilityEvent.getText().add(charSequence);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x008e  */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r11) {
        /*
            Method dump skipped, instruction units count: 329
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SwitchCompat.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().c(z);
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        super.setChecked(z);
        boolean zIsChecked = isChecked();
        if (zIsChecked) {
            if (Build.VERSION.SDK_INT >= 30) {
                Object string = this.s;
                if (string == null) {
                    string = getResources().getString(androidx.appcompat.R.string.abc_capital_on);
                }
                WeakHashMap<View, in3> weakHashMap = om3.a;
                new mm3(R$id.tag_state_description).e(this, string);
            }
        } else if (Build.VERSION.SDK_INT >= 30) {
            Object string2 = this.u;
            if (string2 == null) {
                string2 = getResources().getString(androidx.appcompat.R.string.abc_capital_off);
            }
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            new mm3(R$id.tag_state_description).e(this, string2);
        }
        if (getWindowToken() != null) {
            WeakHashMap<View, in3> weakHashMap3 = om3.a;
            if (om3.g.c(this)) {
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, V, zIsChecked ? 1.0f : 0.0f);
                this.R = objectAnimatorOfFloat;
                objectAnimatorOfFloat.setDuration(250L);
                this.R.setAutoCancel(true);
                this.R.start();
                return;
            }
        }
        ObjectAnimator objectAnimator = this.R;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        setThumbPosition(zIsChecked ? 1.0f : 0.0f);
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(callback);
    }

    public void setEmojiCompatEnabled(boolean z) {
        getEmojiTextViewHelper().d(z);
        setTextOnInternal(this.s);
        setTextOffInternal(this.u);
        requestLayout();
    }

    public final void setEnforceSwitchWidth(boolean z) {
        this.L = z;
        invalidate();
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().a(inputFilterArr));
    }

    public void setShowText(boolean z) {
        if (this.w != z) {
            this.w = z;
            requestLayout();
            if (z) {
                e();
            }
        }
    }

    public void setSplitTrack(boolean z) {
        this.r = z;
        invalidate();
    }

    public void setSwitchMinWidth(int i) {
        this.p = i;
        requestLayout();
    }

    public void setSwitchPadding(int i) {
        this.q = i;
        requestLayout();
    }

    public void setSwitchTypeface(Typeface typeface) {
        TextPaint textPaint = this.M;
        if ((textPaint.getTypeface() == null || textPaint.getTypeface().equals(typeface)) && (textPaint.getTypeface() != null || typeface == null)) {
            return;
        }
        textPaint.setTypeface(typeface);
        requestLayout();
        invalidate();
    }

    public void setTextOff(CharSequence charSequence) {
        setTextOffInternal(charSequence);
        requestLayout();
        if (isChecked() || Build.VERSION.SDK_INT < 30) {
            return;
        }
        Object string = this.u;
        if (string == null) {
            string = getResources().getString(androidx.appcompat.R.string.abc_capital_off);
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        new mm3(R$id.tag_state_description).e(this, string);
    }

    public void setTextOn(CharSequence charSequence) {
        setTextOnInternal(charSequence);
        requestLayout();
        if (!isChecked() || Build.VERSION.SDK_INT < 30) {
            return;
        }
        Object string = this.s;
        if (string == null) {
            string = getResources().getString(androidx.appcompat.R.string.abc_capital_on);
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        new mm3(R$id.tag_state_description).e(this, string);
    }

    public void setThumbDrawable(Drawable drawable) {
        Drawable drawable2 = this.e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.e = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbPosition(float f) {
        this.D = f;
        invalidate();
    }

    public void setThumbResource(int i) {
        setThumbDrawable(AppCompatResources.a(getContext(), i));
    }

    public void setThumbTextPadding(int i) {
        this.o = i;
        requestLayout();
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.f = colorStateList;
        this.h = true;
        a();
    }

    public void setThumbTintMode(PorterDuff.Mode mode) {
        this.g = mode;
        this.i = true;
        a();
    }

    public void setTrackDrawable(Drawable drawable) {
        Drawable drawable2 = this.j;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.j = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i) {
        setTrackDrawable(AppCompatResources.a(getContext(), i));
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        this.k = colorStateList;
        this.m = true;
        b();
    }

    public void setTrackTintMode(PorterDuff.Mode mode) {
        this.l = mode;
        this.n = true;
        b();
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public final void toggle() {
        setChecked(!isChecked());
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.e || drawable == this.j;
    }

    public SwitchCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, androidx.appcompat.R.attr.switchStyle);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i) {
        Typeface typeface;
        Typeface typefaceCreate;
        super(context, attributeSet, i);
        this.f = null;
        this.g = null;
        this.h = false;
        this.i = false;
        this.k = null;
        this.l = null;
        this.m = false;
        this.n = false;
        this.B = VelocityTracker.obtain();
        this.L = true;
        this.U = new Rect();
        ThemeUtils.a(getContext(), this);
        TextPaint textPaint = new TextPaint(1);
        this.M = textPaint;
        textPaint.density = getResources().getDisplayMetrics().density;
        int[] iArr = androidx.appcompat.R.styleable.SwitchCompat;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        TintTypedArray tintTypedArray = new TintTypedArray(context, typedArrayObtainStyledAttributes);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.n.b(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i, 0);
        Drawable drawableE = tintTypedArray.e(androidx.appcompat.R.styleable.SwitchCompat_android_thumb);
        this.e = drawableE;
        if (drawableE != null) {
            drawableE.setCallback(this);
        }
        Drawable drawableE2 = tintTypedArray.e(androidx.appcompat.R.styleable.SwitchCompat_track);
        this.j = drawableE2;
        if (drawableE2 != null) {
            drawableE2.setCallback(this);
        }
        setTextOnInternal(tintTypedArray.k(androidx.appcompat.R.styleable.SwitchCompat_android_textOn));
        setTextOffInternal(tintTypedArray.k(androidx.appcompat.R.styleable.SwitchCompat_android_textOff));
        this.w = tintTypedArray.a(androidx.appcompat.R.styleable.SwitchCompat_showText, true);
        this.o = tintTypedArray.d(androidx.appcompat.R.styleable.SwitchCompat_thumbTextPadding, 0);
        this.p = tintTypedArray.d(androidx.appcompat.R.styleable.SwitchCompat_switchMinWidth, 0);
        this.q = tintTypedArray.d(androidx.appcompat.R.styleable.SwitchCompat_switchPadding, 0);
        this.r = tintTypedArray.a(androidx.appcompat.R.styleable.SwitchCompat_splitTrack, false);
        ColorStateList colorStateListB = tintTypedArray.b(androidx.appcompat.R.styleable.SwitchCompat_thumbTint);
        if (colorStateListB != null) {
            this.f = colorStateListB;
            this.h = true;
        }
        PorterDuff.Mode modeC = DrawableUtils.c(tintTypedArray.h(androidx.appcompat.R.styleable.SwitchCompat_thumbTintMode, -1), null);
        if (this.g != modeC) {
            this.g = modeC;
            this.i = true;
        }
        if (this.h || this.i) {
            a();
        }
        ColorStateList colorStateListB2 = tintTypedArray.b(androidx.appcompat.R.styleable.SwitchCompat_trackTint);
        if (colorStateListB2 != null) {
            this.k = colorStateListB2;
            this.m = true;
        }
        PorterDuff.Mode modeC2 = DrawableUtils.c(tintTypedArray.h(androidx.appcompat.R.styleable.SwitchCompat_trackTintMode, -1), null);
        if (this.l != modeC2) {
            this.l = modeC2;
            this.n = true;
        }
        if (this.m || this.n) {
            b();
        }
        int i2 = tintTypedArray.i(androidx.appcompat.R.styleable.SwitchCompat_switchTextAppearance, 0);
        if (i2 != 0) {
            TintTypedArray tintTypedArray2 = new TintTypedArray(context, context.obtainStyledAttributes(i2, androidx.appcompat.R.styleable.TextAppearance));
            ColorStateList colorStateListB3 = tintTypedArray2.b(androidx.appcompat.R.styleable.TextAppearance_android_textColor);
            if (colorStateListB3 != null) {
                this.N = colorStateListB3;
            } else {
                this.N = getTextColors();
            }
            int iD = tintTypedArray2.d(androidx.appcompat.R.styleable.TextAppearance_android_textSize, 0);
            if (iD != 0) {
                float f = iD;
                if (f != textPaint.getTextSize()) {
                    textPaint.setTextSize(f);
                    requestLayout();
                }
            }
            int iH = tintTypedArray2.h(androidx.appcompat.R.styleable.TextAppearance_android_typeface, -1);
            int iH2 = tintTypedArray2.h(androidx.appcompat.R.styleable.TextAppearance_android_textStyle, -1);
            if (iH == 1) {
                typeface = Typeface.SANS_SERIF;
            } else if (iH != 2) {
                typeface = iH != 3 ? null : Typeface.MONOSPACE;
            } else {
                typeface = Typeface.SERIF;
            }
            if (iH2 > 0) {
                if (typeface == null) {
                    typefaceCreate = Typeface.defaultFromStyle(iH2);
                } else {
                    typefaceCreate = Typeface.create(typeface, iH2);
                }
                setSwitchTypeface(typefaceCreate);
                int i3 = (~(typefaceCreate != null ? typefaceCreate.getStyle() : 0)) & iH2;
                textPaint.setFakeBoldText((i3 & 1) != 0);
                textPaint.setTextSkewX((i3 & 2) != 0 ? -0.25f : 0.0f);
            } else {
                textPaint.setFakeBoldText(false);
                textPaint.setTextSkewX(0.0f);
                setSwitchTypeface(typeface);
            }
            if (tintTypedArray2.a(androidx.appcompat.R.styleable.TextAppearance_textAllCaps, false)) {
                this.Q = new AllCapsTransformationMethod(getContext());
            } else {
                this.Q = null;
            }
            setTextOnInternal(this.s);
            setTextOffInternal(this.u);
            tintTypedArray2.n();
        }
        new AppCompatTextHelper(this).f(attributeSet, i);
        tintTypedArray.n();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.y = viewConfiguration.getScaledTouchSlop();
        this.C = viewConfiguration.getScaledMinimumFlingVelocity();
        getEmojiTextViewHelper().b(attributeSet, i);
        refreshDrawableState();
        setChecked(isChecked());
    }
}
