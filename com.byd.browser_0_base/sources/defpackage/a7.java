package defpackage;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/* JADX INFO: compiled from: AppMenuButtonHelperImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a7 extends View.AccessibilityDelegate implements z6 {
    public final i7 e;
    public Runnable f;
    public boolean g;

    public a7(i7 i7Var) {
        this.e = i7Var;
    }

    /* JADX WARN: Code restructure failed: missing block: B:228:0x068d, code lost:
    
        if (r8 != 2) goto L233;
     */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0757  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(android.view.View r24, boolean r25) {
        /*
            Method dump skipped, instruction units count: 1902
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.a7.a(android.view.View, boolean):boolean");
    }

    @Override // defpackage.z6
    public final boolean e(ImageButton imageButton) {
        return a(imageButton, false);
    }

    @Override // defpackage.z6
    public final boolean o() {
        return this.g || this.e.b();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:72:? A[RETURN, SYNTHETIC] */
    @Override // android.view.View.OnTouchListener
    @android.annotation.SuppressLint({"ClickableViewAccessibility"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouch(android.view.View r17, android.view.MotionEvent r18) {
        /*
            Method dump skipped, instruction units count: 338
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.a7.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.view.View.AccessibilityDelegate
    public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (i != 16) {
            return super.performAccessibilityAction(view, i, bundle);
        }
        i7 i7Var = this.e;
        if (i7Var.b()) {
            i7Var.a();
        } else {
            a(view, false);
        }
        view.playSoundEffect(0);
        view.sendAccessibilityEvent(1);
        return true;
    }

    @Override // defpackage.z6
    public final a7 E() {
        return this;
    }
}
