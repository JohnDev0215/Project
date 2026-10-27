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
import b.b.a;
import b.b.q.b0;
import b.b.q.d;
import b.b.q.m;
import b.b.q.z;
import b.f.m.r;
import b.f.n.b;
import b.f.n.i;
import b.f.n.k;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatButton extends Button implements r, b, k {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f188c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m f189d;

    public AppCompatButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f188c;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.f189d;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (b.f1833a) {
            return super.getAutoSizeMaxTextSize();
        }
        m mVar = this.f189d;
        if (mVar != null) {
            return mVar.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (b.f1833a) {
            return super.getAutoSizeMinTextSize();
        }
        m mVar = this.f189d;
        if (mVar != null) {
            return mVar.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (b.f1833a) {
            return super.getAutoSizeStepGranularity();
        }
        m mVar = this.f189d;
        if (mVar != null) {
            return mVar.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (b.f1833a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        m mVar = this.f189d;
        return mVar != null ? mVar.h() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (b.f1833a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        m mVar = this.f189d;
        if (mVar != null) {
            return mVar.i();
        }
        return 0;
    }

    @Override // b.f.m.r
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f188c;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // b.f.m.r
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f188c;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f189d.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f189d.k();
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
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        m mVar = this.f189d;
        if (mVar != null) {
            mVar.o(z, i2, i3, i4, i5);
        }
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        super.onTextChanged(charSequence, i2, i3, i4);
        m mVar = this.f189d;
        if (mVar == null || b.f1833a || !mVar.l()) {
            return;
        }
        this.f189d.c();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i2, int i3, int i4, int i5) throws IllegalArgumentException {
        if (b.f1833a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i2, i3, i4, i5);
            return;
        }
        m mVar = this.f189d;
        if (mVar != null) {
            mVar.s(i2, i3, i4, i5);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i2) throws IllegalArgumentException {
        if (b.f1833a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
            return;
        }
        m mVar = this.f189d;
        if (mVar != null) {
            mVar.t(iArr, i2);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i2) {
        if (b.f1833a) {
            super.setAutoSizeTextTypeWithDefaults(i2);
            return;
        }
        m mVar = this.f189d;
        if (mVar != null) {
            mVar.u(i2);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f188c;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        d dVar = this.f188c;
        if (dVar != null) {
            dVar.g(i2);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(i.n(this, callback));
    }

    public void setSupportAllCaps(boolean z) {
        m mVar = this.f189d;
        if (mVar != null) {
            mVar.r(z);
        }
    }

    @Override // b.f.m.r
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f188c;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // b.f.m.r
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f188c;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // b.f.n.k
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.f189d.v(colorStateList);
        this.f189d.b();
    }

    @Override // b.f.n.k
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.f189d.w(mode);
        this.f189d.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        m mVar = this.f189d;
        if (mVar != null) {
            mVar.q(context, i2);
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i2, float f2) {
        if (b.f1833a) {
            super.setTextSize(i2, f2);
            return;
        }
        m mVar = this.f189d;
        if (mVar != null) {
            mVar.z(i2, f2);
        }
    }

    public AppCompatButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.r);
    }

    public AppCompatButton(Context context, AttributeSet attributeSet, int i2) {
        super(b0.b(context), attributeSet, i2);
        z.a(this, getContext());
        d dVar = new d(this);
        this.f188c = dVar;
        dVar.e(attributeSet, i2);
        m mVar = new m(this);
        this.f189d = mVar;
        mVar.m(attributeSet, i2);
        mVar.b();
    }
}
