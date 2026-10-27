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
import o0.s;
import r.b0;
import r.d;
import r.m;
import r.z;
import r0.b;
import r0.i;
import r0.k;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatButton extends Button implements s, b, k {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f548d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final m f549e;

    public AppCompatButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f548d;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.f549e;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (b.f7723a) {
            return super.getAutoSizeMaxTextSize();
        }
        m mVar = this.f549e;
        if (mVar != null) {
            return mVar.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (b.f7723a) {
            return super.getAutoSizeMinTextSize();
        }
        m mVar = this.f549e;
        if (mVar != null) {
            return mVar.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (b.f7723a) {
            return super.getAutoSizeStepGranularity();
        }
        m mVar = this.f549e;
        if (mVar != null) {
            return mVar.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (b.f7723a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        m mVar = this.f549e;
        return mVar != null ? mVar.h() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (b.f7723a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        m mVar = this.f549e;
        if (mVar != null) {
            return mVar.i();
        }
        return 0;
    }

    @Override // o0.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f548d;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // o0.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f548d;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f549e.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f549e.k();
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
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
        m mVar = this.f549e;
        if (mVar != null) {
            mVar.o(z7, i8, i9, i10, i11);
        }
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
        super.onTextChanged(charSequence, i8, i9, i10);
        m mVar = this.f549e;
        if (mVar == null || b.f7723a || !mVar.l()) {
            return;
        }
        this.f549e.c();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i8, int i9, int i10, int i11) throws IllegalArgumentException {
        if (b.f7723a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i8, i9, i10, i11);
            return;
        }
        m mVar = this.f549e;
        if (mVar != null) {
            mVar.s(i8, i9, i10, i11);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i8) throws IllegalArgumentException {
        if (b.f7723a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i8);
            return;
        }
        m mVar = this.f549e;
        if (mVar != null) {
            mVar.t(iArr, i8);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i8) {
        if (b.f7723a) {
            super.setAutoSizeTextTypeWithDefaults(i8);
            return;
        }
        m mVar = this.f549e;
        if (mVar != null) {
            mVar.u(i8);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f548d;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i8) {
        super.setBackgroundResource(i8);
        d dVar = this.f548d;
        if (dVar != null) {
            dVar.g(i8);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(i.s(this, callback));
    }

    public void setSupportAllCaps(boolean z7) {
        m mVar = this.f549e;
        if (mVar != null) {
            mVar.r(z7);
        }
    }

    @Override // o0.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f548d;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // o0.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f548d;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // r0.k
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.f549e.v(colorStateList);
        this.f549e.b();
    }

    @Override // r0.k
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.f549e.w(mode);
        this.f549e.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i8) {
        super.setTextAppearance(context, i8);
        m mVar = this.f549e;
        if (mVar != null) {
            mVar.q(context, i8);
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i8, float f8) {
        if (b.f7723a) {
            super.setTextSize(i8, f8);
            return;
        }
        m mVar = this.f549e;
        if (mVar != null) {
            mVar.z(i8, f8);
        }
    }

    public AppCompatButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.buttonStyle);
    }

    public AppCompatButton(Context context, AttributeSet attributeSet, int i8) {
        super(b0.b(context), attributeSet, i8);
        z.a(this, getContext());
        d dVar = new d(this);
        this.f548d = dVar;
        dVar.e(attributeSet, i8);
        m mVar = new m(this);
        this.f549e = mVar;
        mVar.m(attributeSet, i8);
        mVar.b();
    }
}
