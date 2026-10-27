package com.google.android.material.materialswitch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat;
import androidx.appcompat.widget.DrawableUtils;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.TintTypedArray;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.gv;
import defpackage.r83;
import defpackage.uc1;
import defpackage.yn3;
import java.lang.reflect.Field;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class MaterialSwitch extends SwitchCompat {
    public static final int m0 = R$style.Widget_Material3_CompoundButton_MaterialSwitch;
    public static final int[] n0 = {R$attr.state_with_icon};
    public final a a0;
    public final b b0;
    public Drawable c0;
    public Drawable d0;
    public Drawable e0;
    public Drawable f0;
    public ColorStateList g0;
    public ColorStateList h0;
    public PorterDuff.Mode i0;
    public ColorStateList j0;
    public ColorStateList k0;
    public PorterDuff.Mode l0;

    @SuppressLint({"PrivateApi"})
    public static final class a {
        public final MaterialSwitch a;
        public final Field b;

        public a(MaterialSwitch materialSwitch, Field field) {
            this.a = materialSwitch;
            this.b = field;
        }
    }

    @SuppressLint({"PrivateApi"})
    public final class b {
        public final Field a;

        public b() {
            Field declaredField;
            try {
                declaredField = SwitchCompat.class.getDeclaredField("D");
                declaredField.setAccessible(true);
            } catch (Exception unused) {
                declaredField = null;
            }
            this.a = declaredField;
        }
    }

    public MaterialSwitch(Context context) {
        this(context, null);
    }

    public static Drawable f(Drawable drawable, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (drawable == null) {
            return null;
        }
        if (colorStateList != null) {
            drawable = drawable.mutate();
            if (mode != null) {
                drawable.setTintMode(mode);
            }
        }
        return drawable;
    }

    private float getThumbPos() {
        b bVar = this.b0;
        MaterialSwitch materialSwitch = MaterialSwitch.this;
        try {
            Field field = bVar.a;
            if (field != null) {
                return field.getFloat(materialSwitch);
            }
        } catch (IllegalAccessException unused) {
        }
        return materialSwitch.isChecked() ? 1.0f : 0.0f;
    }

    public static void i(Drawable drawable, ColorStateList colorStateList, int[] iArr, int[] iArr2, float f) {
        if (drawable == null || colorStateList == null) {
            return;
        }
        boolean z = drawable instanceof AnimatedStateListDrawableCompat;
        int colorForState = colorStateList.getColorForState(iArr, 0);
        int colorForState2 = colorStateList.getColorForState(iArr2, 0);
        ThreadLocal<double[]> threadLocal = gv.a;
        float f2 = 1.0f - f;
        drawable.setTint(Color.argb((int) ((Color.alpha(colorForState2) * f) + (Color.alpha(colorForState) * f2)), (int) ((Color.red(colorForState2) * f) + (Color.red(colorForState) * f2)), (int) ((Color.green(colorForState2) * f) + (Color.green(colorForState) * f2)), (int) ((Color.blue(colorForState2) * f) + (Color.blue(colorForState) * f2))));
    }

    public final void g() {
        int intrinsicHeight;
        int intrinsicWidth;
        this.c0 = f(this.c0, this.g0, getThumbTintMode());
        this.d0 = f(this.d0, this.h0, this.i0);
        j();
        Drawable drawable = this.c0;
        Drawable drawable2 = this.d0;
        if (drawable == null) {
            drawable = drawable2;
        } else if (drawable2 != null) {
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable, drawable2});
            if (drawable2.getIntrinsicWidth() > drawable.getIntrinsicWidth() || drawable2.getIntrinsicHeight() > drawable.getIntrinsicHeight()) {
                float intrinsicWidth2 = drawable2.getIntrinsicWidth() / drawable2.getIntrinsicHeight();
                if (intrinsicWidth2 >= drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight()) {
                    intrinsicWidth = drawable.getIntrinsicWidth();
                    intrinsicHeight = (int) (intrinsicWidth / intrinsicWidth2);
                } else {
                    intrinsicHeight = drawable.getIntrinsicHeight();
                    intrinsicWidth = (int) (intrinsicWidth2 * intrinsicHeight);
                }
            } else {
                intrinsicWidth = drawable2.getIntrinsicWidth();
                intrinsicHeight = drawable2.getIntrinsicHeight();
            }
            layerDrawable.setLayerSize(1, intrinsicWidth, intrinsicHeight);
            layerDrawable.setLayerGravity(1, 17);
            drawable = layerDrawable;
        }
        super.setThumbDrawable(drawable);
        refreshDrawableState();
    }

    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        Field field;
        if (!yn3.d(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        a aVar = this.a0;
        MaterialSwitch materialSwitch = aVar.a;
        try {
            field = aVar.b;
        } catch (IllegalAccessException unused) {
        }
        int i = field != null ? field.getInt(materialSwitch) : materialSwitch.getSwitchMinWidth();
        return getSwitchMinWidth() + (compoundPaddingLeft - i);
    }

    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingRight() {
        Field field;
        if (yn3.d(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight();
        a aVar = this.a0;
        MaterialSwitch materialSwitch = aVar.a;
        try {
            field = aVar.b;
        } catch (IllegalAccessException unused) {
        }
        int i = field != null ? field.getInt(materialSwitch) : materialSwitch.getSwitchMinWidth();
        return getSwitchMinWidth() + (compoundPaddingRight - i);
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public Drawable getThumbDrawable() {
        return this.c0;
    }

    public Drawable getThumbIconDrawable() {
        return this.d0;
    }

    public ColorStateList getThumbIconTintList() {
        return this.h0;
    }

    public PorterDuff.Mode getThumbIconTintMode() {
        return this.i0;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public ColorStateList getThumbTintList() {
        return this.g0;
    }

    public Drawable getTrackDecorationDrawable() {
        return this.f0;
    }

    public ColorStateList getTrackDecorationTintList() {
        return this.k0;
    }

    public PorterDuff.Mode getTrackDecorationTintMode() {
        return this.l0;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public Drawable getTrackDrawable() {
        return this.e0;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public ColorStateList getTrackTintList() {
        return this.j0;
    }

    public final void h() {
        this.e0 = f(this.e0, this.j0, getTrackTintMode());
        this.f0 = f(this.f0, this.k0, this.l0);
        j();
        Drawable layerDrawable = this.e0;
        if (layerDrawable != null && this.f0 != null) {
            layerDrawable = new LayerDrawable(new Drawable[]{this.e0, this.f0});
        } else if (layerDrawable == null) {
            layerDrawable = this.f0;
        }
        if (layerDrawable != null) {
            setSwitchMinWidth(layerDrawable.getIntrinsicWidth());
        }
        super.setTrackDrawable(layerDrawable);
    }

    @Override // android.view.View
    public final void invalidate() {
        if (this.b0 != null) {
            j();
        }
        super.invalidate();
    }

    public final void j() {
        if (this.g0 == null && this.h0 == null && this.j0 == null && this.k0 == null) {
            return;
        }
        float thumbPos = getThumbPos();
        int[] drawableState = getDrawableState();
        int[] iArr = new int[drawableState.length];
        int i = 0;
        int i2 = 0;
        for (int i3 : drawableState) {
            if (i3 != 16842912) {
                iArr[i2] = i3;
                i2++;
            }
        }
        while (true) {
            if (i >= drawableState.length) {
                int[] iArrCopyOf = Arrays.copyOf(drawableState, drawableState.length + 1);
                iArrCopyOf[drawableState.length] = 16842912;
                drawableState = iArrCopyOf;
                break;
            }
            int i4 = drawableState[i];
            if (i4 == 16842912) {
                break;
            }
            if (i4 == 0) {
                drawableState = (int[]) drawableState.clone();
                drawableState[i] = 16842912;
                break;
            }
            i++;
        }
        ColorStateList colorStateList = this.g0;
        if (colorStateList != null) {
            i(this.c0, colorStateList, iArr, drawableState, thumbPos);
        }
        ColorStateList colorStateList2 = this.h0;
        if (colorStateList2 != null) {
            i(this.d0, colorStateList2, iArr, drawableState, thumbPos);
        }
        ColorStateList colorStateList3 = this.j0;
        if (colorStateList3 != null) {
            i(this.e0, colorStateList3, iArr, drawableState, thumbPos);
        }
        ColorStateList colorStateList4 = this.k0;
        if (colorStateList4 != null) {
            i(this.f0, colorStateList4, iArr, drawableState, thumbPos);
        }
    }

    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.d0 != null) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, n0);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.TextView, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int switchMinWidth = getSwitchMinWidth();
        a aVar = this.a0;
        aVar.getClass();
        try {
            Field field = aVar.b;
            if (field != null) {
                field.setInt(aVar.a, switchMinWidth);
            }
        } catch (IllegalAccessException unused) {
        }
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbDrawable(Drawable drawable) {
        this.c0 = drawable;
        g();
    }

    public void setThumbIconDrawable(Drawable drawable) {
        this.d0 = drawable;
        g();
    }

    public void setThumbIconResource(int i) {
        setThumbIconDrawable(AppCompatResources.a(getContext(), i));
    }

    public void setThumbIconTintList(ColorStateList colorStateList) {
        this.h0 = colorStateList;
        g();
    }

    public void setThumbIconTintMode(PorterDuff.Mode mode) {
        this.i0 = mode;
        g();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbTintList(ColorStateList colorStateList) {
        this.g0 = colorStateList;
        g();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbTintMode(PorterDuff.Mode mode) {
        super.setThumbTintMode(mode);
        g();
    }

    public void setTrackDecorationDrawable(Drawable drawable) {
        this.f0 = drawable;
        h();
    }

    public void setTrackDecorationResource(int i) {
        setTrackDecorationDrawable(AppCompatResources.a(getContext(), i));
    }

    public void setTrackDecorationTintList(ColorStateList colorStateList) {
        this.k0 = colorStateList;
        h();
    }

    public void setTrackDecorationTintMode(PorterDuff.Mode mode) {
        this.l0 = mode;
        h();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackDrawable(Drawable drawable) {
        this.e0 = drawable;
        h();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackTintList(ColorStateList colorStateList) {
        this.j0 = colorStateList;
        h();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackTintMode(PorterDuff.Mode mode) {
        super.setTrackTintMode(mode);
        h();
    }

    public MaterialSwitch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialSwitchStyle);
    }

    public MaterialSwitch(Context context, AttributeSet attributeSet, int i) {
        Field declaredField;
        super(uc1.a(context, attributeSet, i, m0), attributeSet, i);
        try {
            declaredField = SwitchCompat.class.getDeclaredField("E");
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException | SecurityException unused) {
            declaredField = null;
        }
        this.a0 = new a(this, declaredField);
        this.b0 = new b();
        Context context2 = getContext();
        this.c0 = super.getThumbDrawable();
        this.g0 = super.getThumbTintList();
        super.setThumbTintList(null);
        this.e0 = super.getTrackDrawable();
        this.j0 = super.getTrackTintList();
        super.setTrackTintList(null);
        TintTypedArray tintTypedArrayE = r83.e(context2, attributeSet, R$styleable.MaterialSwitch, i, m0, new int[0]);
        this.d0 = tintTypedArrayE.e(R$styleable.MaterialSwitch_thumbIcon);
        this.h0 = tintTypedArrayE.b(R$styleable.MaterialSwitch_thumbIconTint);
        this.i0 = DrawableUtils.c(tintTypedArrayE.h(R$styleable.MaterialSwitch_thumbIconTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.f0 = tintTypedArrayE.e(R$styleable.MaterialSwitch_trackDecoration);
        this.k0 = tintTypedArrayE.b(R$styleable.MaterialSwitch_trackDecorationTint);
        this.l0 = DrawableUtils.c(tintTypedArrayE.h(R$styleable.MaterialSwitch_trackDecorationTintMode, -1), PorterDuff.Mode.SRC_IN);
        tintTypedArrayE.n();
        g();
        h();
    }
}
