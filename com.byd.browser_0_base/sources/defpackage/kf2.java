package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import defpackage.ef2;

/* JADX INFO: compiled from: ScrimView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class kf2 extends View {
    public final ViewGroup e;
    public final int f;
    public final ef2.b g;

    public kf2(Context context, ViewGroup viewGroup, int i, if2 if2Var) {
        super(context);
        this.e = viewGroup;
        setFocusable(false);
        setImportantForAccessibility(2);
        this.f = i;
        this.g = if2Var;
        setAlpha(0.0f);
        setVisibility(8);
        setBackgroundColor(i);
        setLayoutParams(new ViewGroup.MarginLayoutParams(-1, -1));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            ef2$b r0 = r5.g
            if2 r0 = (defpackage.if2) r0
            boolean r1 = r0.i
            r2 = 0
            if (r1 == 0) goto La
            goto L47
        La:
            org.chromium.ui.modelutil.PropertyModel r1 = r0.f
            java.util.ArrayList r1 = r1.b()
            org.chromium.ui.modelutil.PropertyModel$r<android.view.GestureDetector> r3 = defpackage.jf2.j
            boolean r1 = r1.contains(r3)
            if (r1 != 0) goto L19
            goto L47
        L19:
            boolean r1 = r0.h
            if (r1 == 0) goto L39
            int r1 = r6.getActionMasked()
            if (r1 == 0) goto L39
            android.view.MotionEvent r1 = android.view.MotionEvent.obtain(r6)
            r1.setAction(r2)
            org.chromium.ui.modelutil.PropertyModel r4 = r0.f
            java.lang.Object r4 = r4.j(r3)
            android.view.GestureDetector r4 = (android.view.GestureDetector) r4
            boolean r1 = r4.onTouchEvent(r1)
            if (r1 != 0) goto L39
            goto L47
        L39:
            r0.h = r2
            org.chromium.ui.modelutil.PropertyModel r0 = r0.f
            java.lang.Object r0 = r0.j(r3)
            android.view.GestureDetector r0 = (android.view.GestureDetector) r0
            boolean r2 = r0.onTouchEvent(r6)
        L47:
            if (r2 == 0) goto L4b
            r5 = 1
            return r5
        L4b:
            boolean r5 = super.onTouchEvent(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kf2.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public final void setBackgroundColor(int i) {
        if (i == 0) {
            i = this.f;
        }
        super.setBackgroundColor(i);
    }
}
