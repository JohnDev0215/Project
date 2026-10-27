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
import l0.s;
import n0.b;
import n0.i;
import n0.k;
import o.b0;
import o.d;
import o.m;
import o.z;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatButton extends Button implements s, b, k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f447b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final m f448c;

    public AppCompatButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f447b;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.f448c;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (b.f4070a) {
            return super.getAutoSizeMaxTextSize();
        }
        m mVar = this.f448c;
        if (mVar != null) {
            return mVar.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (b.f4070a) {
            return super.getAutoSizeMinTextSize();
        }
        m mVar = this.f448c;
        if (mVar != null) {
            return mVar.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (b.f4070a) {
            return super.getAutoSizeStepGranularity();
        }
        m mVar = this.f448c;
        if (mVar != null) {
            return mVar.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (b.f4070a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        m mVar = this.f448c;
        return mVar != null ? mVar.h() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (b.f4070a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        m mVar = this.f448c;
        if (mVar != null) {
            return mVar.i();
        }
        return 0;
    }

    @Override // l0.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f447b;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // l0.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f447b;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f448c.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f448c.k();
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
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        super.onLayout(z3, i3, i4, i5, i6);
        m mVar = this.f448c;
        if (mVar != null) {
            mVar.o(z3, i3, i4, i5, i6);
        }
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
        super.onTextChanged(charSequence, i3, i4, i5);
        m mVar = this.f448c;
        if (mVar == null || b.f4070a || !mVar.l()) {
            return;
        }
        this.f448c.c();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i3, int i4, int i5, int i6) throws IllegalArgumentException {
        if (b.f4070a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i3, i4, i5, i6);
            return;
        }
        m mVar = this.f448c;
        if (mVar != null) {
            mVar.s(i3, i4, i5, i6);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i3) throws IllegalArgumentException {
        if (b.f4070a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i3);
            return;
        }
        m mVar = this.f448c;
        if (mVar != null) {
            mVar.t(iArr, i3);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i3) {
        if (b.f4070a) {
            super.setAutoSizeTextTypeWithDefaults(i3);
            return;
        }
        m mVar = this.f448c;
        if (mVar != null) {
            mVar.u(i3);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f447b;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i3) {
        super.setBackgroundResource(i3);
        d dVar = this.f447b;
        if (dVar != null) {
            dVar.g(i3);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(i.s(this, callback));
    }

    public void setSupportAllCaps(boolean z3) {
        m mVar = this.f448c;
        if (mVar != null) {
            mVar.r(z3);
        }
    }

    @Override // l0.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f447b;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // l0.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f447b;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // n0.k
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.f448c.v(colorStateList);
        this.f448c.b();
    }

    @Override // n0.k
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.f448c.w(mode);
        this.f448c.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i3) {
        super.setTextAppearance(context, i3);
        m mVar = this.f448c;
        if (mVar != null) {
            mVar.q(context, i3);
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i3, float f3) {
        if (b.f4070a) {
            super.setTextSize(i3, f3);
            return;
        }
        m mVar = this.f448c;
        if (mVar != null) {
            mVar.z(i3, f3);
        }
    }

    public AppCompatButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.buttonStyle);
    }

    public AppCompatButton(Context context, AttributeSet attributeSet, int i3) {
        super(b0.b(context), attributeSet, i3);
        z.a(this, getContext());
        d dVar = new d(this);
        this.f447b = dVar;
        dVar.e(attributeSet, i3);
        m mVar = new m(this);
        this.f448c = mVar;
        mVar.m(attributeSet, i3);
        mVar.b();
    }
}
