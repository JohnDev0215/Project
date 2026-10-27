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

/* JADX INFO: compiled from: AppCompatButton.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends Button implements androidx.core.widget.b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final d f1024e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final v f1025g;

    public e(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, e.a.buttonStyle);
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f1024e;
        if (dVar != null) {
            dVar.b();
        }
        v vVar = this.f1025g;
        if (vVar != null) {
            vVar.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (androidx.core.widget.b.f2018a) {
            return super.getAutoSizeMaxTextSize();
        }
        v vVar = this.f1025g;
        if (vVar != null) {
            return vVar.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (androidx.core.widget.b.f2018a) {
            return super.getAutoSizeMinTextSize();
        }
        v vVar = this.f1025g;
        if (vVar != null) {
            return vVar.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (androidx.core.widget.b.f2018a) {
            return super.getAutoSizeStepGranularity();
        }
        v vVar = this.f1025g;
        if (vVar != null) {
            return vVar.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (androidx.core.widget.b.f2018a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        v vVar = this.f1025g;
        return vVar != null ? vVar.h() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (androidx.core.widget.b.f2018a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        v vVar = this.f1025g;
        if (vVar != null) {
            return vVar.i();
        }
        return 0;
    }

    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f1024e;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f1024e;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1025g.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1025g.k();
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
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        v vVar = this.f1025g;
        if (vVar != null) {
            vVar.o(z6, i7, i8, i9, i10);
        }
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        super.onTextChanged(charSequence, i7, i8, i9);
        v vVar = this.f1025g;
        if (vVar == null || androidx.core.widget.b.f2018a || !vVar.l()) {
            return;
        }
        this.f1025g.c();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i7, int i8, int i9, int i10) {
        if (androidx.core.widget.b.f2018a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i7, i8, i9, i10);
            return;
        }
        v vVar = this.f1025g;
        if (vVar != null) {
            vVar.t(i7, i8, i9, i10);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i7) {
        if (androidx.core.widget.b.f2018a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i7);
            return;
        }
        v vVar = this.f1025g;
        if (vVar != null) {
            vVar.u(iArr, i7);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i7) {
        if (androidx.core.widget.b.f2018a) {
            super.setAutoSizeTextTypeWithDefaults(i7);
            return;
        }
        v vVar = this.f1025g;
        if (vVar != null) {
            vVar.v(i7);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f1024e;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        d dVar = this.f1024e;
        if (dVar != null) {
            dVar.g(i7);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.i.p(this, callback));
    }

    public void setSupportAllCaps(boolean z6) {
        v vVar = this.f1025g;
        if (vVar != null) {
            vVar.s(z6);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f1024e;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f1024e;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.f1025g.w(colorStateList);
        this.f1025g.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.f1025g.x(mode);
        this.f1025g.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        v vVar = this.f1025g;
        if (vVar != null) {
            vVar.q(context, i7);
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i7, float f7) {
        if (androidx.core.widget.b.f2018a) {
            super.setTextSize(i7, f7);
            return;
        }
        v vVar = this.f1025g;
        if (vVar != null) {
            vVar.A(i7, f7);
        }
    }

    public e(Context context, AttributeSet attributeSet, int i7) {
        super(p0.b(context), attributeSet, i7);
        n0.a(this, getContext());
        d dVar = new d(this);
        this.f1024e = dVar;
        dVar.e(attributeSet, i7);
        v vVar = new v(this);
        this.f1025g = vVar;
        vVar.m(attributeSet, i7);
        vVar.b();
    }
}
