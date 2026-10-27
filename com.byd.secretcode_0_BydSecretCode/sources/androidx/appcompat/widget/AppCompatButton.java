package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import androidx.appcompat.R$attr;
import defpackage.b3;
import defpackage.d3;
import defpackage.f2;
import defpackage.ja;
import defpackage.o2;
import defpackage.qa;
import defpackage.s9;
import defpackage.sa;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatButton extends Button implements s9, ja, sa {
    public final f2 b;
    public final o2 c;

    public AppCompatButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.b();
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (ja.a) {
            return super.getAutoSizeMaxTextSize();
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            return o2Var.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (ja.a) {
            return super.getAutoSizeMinTextSize();
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            return o2Var.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (ja.a) {
            return super.getAutoSizeStepGranularity();
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            return o2Var.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (ja.a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        o2 o2Var = this.c;
        return o2Var != null ? o2Var.h() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (ja.a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            return o2Var.i();
        }
        return 0;
    }

    @Override // defpackage.s9
    public ColorStateList getSupportBackgroundTintList() {
        f2 f2Var = this.b;
        if (f2Var != null) {
            return f2Var.c();
        }
        return null;
    }

    @Override // defpackage.s9
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        f2 f2Var = this.b;
        if (f2Var != null) {
            return f2Var.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.c.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.c.k();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.o(z, i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        o2 o2Var = this.c;
        if (o2Var == null || ja.a || !o2Var.l()) {
            return;
        }
        this.c.c();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        if (ja.a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.s(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) throws IllegalArgumentException {
        if (ja.a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.t(iArr, i);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (ja.a) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.u(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.g(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(qa.s(this, callback));
    }

    public void setSupportAllCaps(boolean z) {
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.r(z);
        }
    }

    @Override // defpackage.s9
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.i(colorStateList);
        }
    }

    @Override // defpackage.s9
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.j(mode);
        }
    }

    @Override // defpackage.sa
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.c.v(colorStateList);
        this.c.b();
    }

    @Override // defpackage.sa
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.c.w(mode);
        this.c.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.q(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f) {
        if (ja.a) {
            super.setTextSize(i, f);
            return;
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.z(i, f);
        }
    }

    public AppCompatButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.buttonStyle);
    }

    public AppCompatButton(Context context, AttributeSet attributeSet, int i) {
        super(d3.b(context), attributeSet, i);
        b3.a(this, getContext());
        f2 f2Var = new f2(this);
        this.b = f2Var;
        f2Var.e(attributeSet, i);
        o2 o2Var = new o2(this);
        this.c = o2Var;
        o2Var.m(attributeSet, i);
        o2Var.b();
    }
}
