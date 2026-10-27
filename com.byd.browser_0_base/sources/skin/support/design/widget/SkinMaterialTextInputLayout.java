package skin.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import com.google.android.material.R$color;
import com.google.android.material.R$style;
import com.google.android.material.textfield.TextInputLayout;
import defpackage.go2;
import defpackage.po2;
import defpackage.ro2;
import defpackage.vd1;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import skin.support.R$styleable;
import skin.support.widget.SkinCompatEditText;

/* JADX INFO: loaded from: classes3.dex */
public class SkinMaterialTextInputLayout extends TextInputLayout implements ro2 {
    public final go2 E0;
    public int F0;
    public int G0;
    public int H0;

    public SkinMaterialTextInputLayout(Context context) {
        this(context, null);
    }

    private TextView getCounterView() {
        try {
            Field declaredField = TextInputLayout.class.getDeclaredField("mCounterView");
            declaredField.setAccessible(true);
            return (TextView) declaredField.get(this);
        } catch (Exception e) {
            Log.e("SkinMaterialTextInputLayout", "getCounterView: " + e);
            return null;
        }
    }

    private TextView getErrorView() {
        try {
            Field declaredField = TextInputLayout.class.getDeclaredField("mErrorView");
            declaredField.setAccessible(true);
            return (TextView) declaredField.get(this);
        } catch (Exception e) {
            Log.e("SkinMaterialTextInputLayout", "getErrorView: " + e);
            return null;
        }
    }

    private void setDefaultTextColor(ColorStateList colorStateList) {
        try {
            Field declaredField = TextInputLayout.class.getDeclaredField("mDefaultTextColor");
            declaredField.setAccessible(true);
            declaredField.set(this, colorStateList);
            D();
        } catch (Exception e) {
            Log.e("SkinMaterialTextInputLayout", "setDefaultTextColor: " + e);
        }
    }

    private void setFocusedTextColor(ColorStateList colorStateList) {
        try {
            Field declaredField = TextInputLayout.class.getDeclaredField("mFocusedTextColor");
            declaredField.setAccessible(true);
            declaredField.set(this, colorStateList);
            D();
        } catch (Exception e) {
            Log.e("SkinMaterialTextInputLayout", "setFocusedTextColor: " + e);
        }
    }

    public final void A() {
        int iB = vd1.b(this.H0);
        this.H0 = iB;
        if (iB != 0 && iB != R$color.abc_hint_foreground_material_light) {
            setFocusedTextColor(po2.b(getContext(), this.H0));
        } else if (getEditText() != null) {
            int iB2 = vd1.b(getEditText() instanceof SkinCompatEditText ? ((SkinCompatEditText) getEditText()).getTextColorResId() : getEditText() instanceof SkinMaterialTextInputEditText ? ((SkinMaterialTextInputEditText) getEditText()).getTextColorResId() : 0);
            if (iB2 != 0) {
                setFocusedTextColor(po2.b(getContext(), iB2));
            }
        }
    }

    public final void B(int i) {
        if (i != 0) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(i, R$styleable.SkinTextAppearance);
            int i2 = R$styleable.SkinTextAppearance_android_textColor;
            if (typedArrayObtainStyledAttributes.hasValue(i2)) {
                this.G0 = typedArrayObtainStyledAttributes.getResourceId(i2, 0);
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        z();
    }

    public final void C() {
        try {
            Method declaredMethod = TextInputLayout.class.getDeclaredMethod("q", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this, new Object[0]);
        } catch (Exception e) {
            Log.e("SkinMaterialTextInputLayout", "updateEditTextBackgroundInternal: " + e);
        }
    }

    public final void D() {
        try {
            Method declaredMethod = TextInputLayout.class.getDeclaredMethod("t", Boolean.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this, Boolean.FALSE);
        } catch (Exception e) {
            Log.e("SkinMaterialTextInputLayout", "updateLabelState: " + e);
        }
    }

    @Override // defpackage.ro2
    public final void e() {
        z();
        y();
        A();
        go2 go2Var = this.E0;
        if (go2Var != null) {
            go2Var.J();
        }
    }

    @Override // com.google.android.material.textfield.TextInputLayout
    public void setCounterEnabled(boolean z) {
        super.setCounterEnabled(z);
        if (z) {
            y();
        }
    }

    @Override // com.google.android.material.textfield.TextInputLayout
    public void setErrorEnabled(boolean z) {
        super.setErrorEnabled(z);
        if (z) {
            z();
        }
    }

    @Override // com.google.android.material.textfield.TextInputLayout
    public void setErrorTextAppearance(int i) {
        super.setErrorTextAppearance(i);
        B(i);
    }

    public final void y() {
        TextView counterView;
        int iB = vd1.b(this.F0);
        this.F0 = iB;
        if (iB == 0 || (counterView = getCounterView()) == null) {
            return;
        }
        counterView.setTextColor(po2.a(getContext(), this.F0));
        C();
    }

    public final void z() {
        TextView errorView;
        int iB = vd1.b(this.G0);
        this.G0 = iB;
        if (iB == 0 || iB == R$color.design_error || (errorView = getErrorView()) == null) {
            return;
        }
        errorView.setTextColor(po2.a(getContext(), this.G0));
        C();
    }

    public SkinMaterialTextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkinMaterialTextInputLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.F0 = 0;
        this.G0 = 0;
        this.H0 = 0;
        go2 go2Var = new go2(this);
        this.E0 = go2Var;
        go2Var.K(attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.google.android.material.R$styleable.TextInputLayout, i, R$style.Widget_Design_TextInputLayout);
        int i2 = com.google.android.material.R$styleable.TextInputLayout_android_textColorHint;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            this.H0 = typedArrayObtainStyledAttributes.getResourceId(i2, 0);
            A();
        }
        B(typedArrayObtainStyledAttributes.getResourceId(com.google.android.material.R$styleable.TextInputLayout_errorTextAppearance, 0));
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(com.google.android.material.R$styleable.TextInputLayout_counterTextAppearance, 0);
        if (resourceId != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(resourceId, R$styleable.SkinTextAppearance);
            int i3 = R$styleable.SkinTextAppearance_android_textColor;
            if (typedArrayObtainStyledAttributes2.hasValue(i3)) {
                this.F0 = typedArrayObtainStyledAttributes2.getResourceId(i3, 0);
            }
            typedArrayObtainStyledAttributes2.recycle();
        }
        y();
        typedArrayObtainStyledAttributes.getResourceId(com.google.android.material.R$styleable.TextInputLayout_passwordToggleDrawable, 0);
        typedArrayObtainStyledAttributes.recycle();
    }
}
