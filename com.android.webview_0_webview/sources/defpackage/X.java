package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class X extends Button implements InterfaceC1635v3 {
    public final W s;
    public final C1026k0 t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public X(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        I0.a(context);
        W w = new W(this);
        this.s = w;
        w.b(attributeSet, i);
        C1026k0 c1026k0 = new C1026k0(this);
        this.t = c1026k0;
        c1026k0.e(attributeSet, i);
        this.t.b();
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        W w = this.s;
        if (w != null) {
            w.a();
        }
        C1026k0 c1026k0 = this.t;
        if (c1026k0 != null) {
            c1026k0.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (InterfaceC1635v3.a) {
            return super.getAutoSizeMaxTextSize();
        }
        C1026k0 c1026k0 = this.t;
        if (c1026k0 != null) {
            return Math.round(c1026k0.h.e);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (InterfaceC1635v3.a) {
            return super.getAutoSizeMinTextSize();
        }
        C1026k0 c1026k0 = this.t;
        if (c1026k0 != null) {
            return Math.round(c1026k0.h.d);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (InterfaceC1635v3.a) {
            return super.getAutoSizeStepGranularity();
        }
        C1026k0 c1026k0 = this.t;
        if (c1026k0 != null) {
            return Math.round(c1026k0.h.c);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (InterfaceC1635v3.a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        C1026k0 c1026k0 = this.t;
        return c1026k0 != null ? c1026k0.h.f : new int[0];
    }

    @Override // android.widget.TextView
    public int getAutoSizeTextType() {
        if (InterfaceC1635v3.a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        C1026k0 c1026k0 = this.t;
        if (c1026k0 != null) {
            return c1026k0.h.a;
        }
        return 0;
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
        C1026k0 c1026k0 = this.t;
        if (c1026k0 == null || InterfaceC1635v3.a) {
            return;
        }
        c1026k0.h.a();
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        C1026k0 c1026k0 = this.t;
        if (c1026k0 == null || InterfaceC1635v3.a || !c1026k0.d()) {
            return;
        }
        this.t.h.a();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (InterfaceC1635v3.a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        C1026k0 c1026k0 = this.t;
        if (c1026k0 != null) {
            c1026k0.g(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        if (InterfaceC1635v3.a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        C1026k0 c1026k0 = this.t;
        if (c1026k0 != null) {
            c1026k0.h(iArr, i);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (InterfaceC1635v3.a) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        C1026k0 c1026k0 = this.t;
        if (c1026k0 != null) {
            c1026k0.i(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        W w = this.s;
        if (w != null) {
            w.c();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        W w = this.s;
        if (w != null) {
            w.d(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(AbstractC1855z3.e(this, callback));
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        C1026k0 c1026k0 = this.t;
        if (c1026k0 != null) {
            c1026k0.f(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f) {
        boolean z = InterfaceC1635v3.a;
        if (z) {
            super.setTextSize(i, f);
            return;
        }
        C1026k0 c1026k0 = this.t;
        if (c1026k0 == null || z || c1026k0.d()) {
            return;
        }
        c1026k0.h.f(i, f);
    }
}
