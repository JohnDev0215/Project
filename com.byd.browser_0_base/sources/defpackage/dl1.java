package defpackage;

import android.view.View;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import defpackage.cl1;

/* JADX INFO: compiled from: NavigationMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public final class dl1 extends C0214v {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ cl1.c f;

    public dl1(cl1.c cVar, int i, boolean z) {
        this.f = cVar;
        this.d = i;
        this.e = z;
    }

    @Override // defpackage.C0214v
    public final void d(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        cl1 cl1Var;
        this.a.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.a);
        int i = this.d;
        int i2 = 0;
        int i3 = i;
        while (true) {
            cl1Var = cl1.this;
            if (i2 >= i) {
                break;
            }
            if (cl1Var.i.m(i2) == 2) {
                i3--;
            }
            i2++;
        }
        if (cl1Var.f.getChildCount() == 0) {
            i3--;
        }
        accessibilityNodeInfoCompat.q(AccessibilityNodeInfoCompat.d.a(i3, 1, 1, 1, this.e, view.isSelected()));
    }
}
