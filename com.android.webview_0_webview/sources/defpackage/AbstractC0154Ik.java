package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;

/* JADX INFO: renamed from: Ik, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0154Ik extends FrameLayout {
    public final AccessibilityManager s;
    public final InterfaceC1415r3 t;

    public AbstractC0154Ik(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0678dn.v0);
        if (typedArrayObtainStyledAttributes.hasValue(0)) {
            AbstractC1251o3.G(this, typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0));
        }
        typedArrayObtainStyledAttributes.recycle();
        this.s = (AccessibilityManager) context.getSystemService("accessibility");
        C0136Hk c0136Hk = new C0136Hk(this);
        this.t = c0136Hk;
        this.s.addTouchExplorationStateChangeListener(new AccessibilityManagerTouchExplorationStateChangeListenerC1470s3(c0136Hk));
        boolean zIsTouchExplorationEnabled = this.s.isTouchExplorationEnabled();
        setClickable(!zIsTouchExplorationEnabled);
        setFocusable(zIsTouchExplorationEnabled);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        AbstractC1251o3.A(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AccessibilityManager accessibilityManager = this.s;
        InterfaceC1415r3 interfaceC1415r3 = this.t;
        if (interfaceC1415r3 == null) {
            return;
        }
        accessibilityManager.removeTouchExplorationStateChangeListener(new AccessibilityManagerTouchExplorationStateChangeListenerC1470s3(interfaceC1415r3));
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }
}
