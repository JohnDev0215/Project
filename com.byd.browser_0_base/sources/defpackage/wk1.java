package defpackage;

import android.content.Context;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: NavigationHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class wk1 implements zd3 {
    public final float a;
    public final ViewGroup b;
    public final Handler c = new Handler();
    public GestureDetector d;
    public final vk1 e;
    public final tb f;
    public Tab g;
    public int h;
    public final PropertyModel i;
    public float j;

    /* JADX INFO: compiled from: NavigationHandler.java */
    public class a extends GestureDetector.SimpleOnGestureListener {
        public a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onDown(MotionEvent motionEvent) {
            wk1.this.h = 1;
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0050  */
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean onScroll(android.view.MotionEvent r8, android.view.MotionEvent r9, float r10, float r11) {
            /*
                r7 = this;
                wk1 r7 = defpackage.wk1.this
                int r0 = r7.h
                r1 = 0
                r2 = 1
                if (r0 != 0) goto La
                r0 = r2
                goto Lb
            La:
                r0 = r1
            Lb:
                if (r0 == 0) goto Le
                return r2
            Le:
                float r8 = r8.getX()
                float r0 = r9.getX()
                float r9 = r9.getY()
                int r3 = r7.h
                if (r3 == 0) goto L85
                boolean r3 = r7.c()
                if (r3 != 0) goto L26
                goto L85
            L26:
                int r3 = r7.h
                r4 = 3
                r5 = 2
                if (r3 != r2) goto L6c
                float r3 = java.lang.Math.abs(r10)
                float r11 = java.lang.Math.abs(r11)
                r6 = 1071476900(0x3fdd70a4, float:1.73)
                float r11 = r11 * r6
                int r11 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
                if (r11 <= 0) goto L50
                float r11 = r7.a
                int r3 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
                if (r3 < 0) goto L4e
                android.view.ViewGroup r3 = r7.b
                int r3 = r3.getWidth()
                float r3 = (float) r3
                float r3 = r3 - r11
                int r8 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
                if (r8 >= 0) goto L50
            L4e:
                r8 = r2
                goto L51
            L50:
                r8 = r1
            L51:
                if (r8 == 0) goto L5e
                r8 = 0
                int r8 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
                if (r8 <= 0) goto L5a
                r8 = r2
                goto L5b
            L5a:
                r8 = r1
            L5b:
                r7.f(r0, r9, r8)
            L5e:
                int r8 = r7.h
                if (r8 == r5) goto L67
                if (r8 != r4) goto L65
                goto L67
            L65:
                r8 = r1
                goto L68
            L67:
                r8 = r2
            L68:
                if (r8 != 0) goto L6c
                r7.h = r1
            L6c:
                float r8 = -r10
                float r9 = r7.j
                float r9 = r9 + r8
                r7.j = r9
                int r8 = r7.h
                org.chromium.ui.modelutil.PropertyModel r7 = r7.i
                if (r8 != r5) goto L7e
                org.chromium.ui.modelutil.PropertyModel$o r8 = defpackage.ns0.d
                r7.m(r8, r9)
                goto L85
            L7e:
                if (r8 != r4) goto L85
                org.chromium.ui.modelutil.PropertyModel$o r8 = defpackage.ns0.e
                r7.m(r8, r9)
            L85:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: wk1.a.onScroll(android.view.MotionEvent, android.view.MotionEvent, float, float):boolean");
        }
    }

    public wk1(PropertyModel propertyModel, org.chromium.chrome.browser.gesturenav.a aVar, tb tbVar) {
        this.i = propertyModel;
        this.b = aVar;
        Context context = aVar.getContext();
        this.f = tbVar;
        this.h = 0;
        this.a = aVar.getResources().getDisplayMetrics().density * 24.0f;
        this.d = new GestureDetector(context, new a());
        vk1 vk1Var = new vk1(this);
        this.e = vk1Var;
        aVar.addOnAttachStateChangeListener(vk1Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002b  */
    @Override // defpackage.zd3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(android.view.MotionEvent r5) {
        /*
            r4 = this;
            org.chromium.chrome.browser.tab.Tab r0 = r4.g
            r1 = 1
            if (r0 == 0) goto Lb
            boolean r0 = r0.isNativePage()
            if (r0 != 0) goto L2b
        Lb:
            tb r0 = r4.f
            g63$a r0 = (g63.a) r0
            g63 r0 = defpackage.g63.this
            qs1<lr2> r0 = r0.U
            java.lang.Object r2 = r0.get()
            r3 = 0
            if (r2 == 0) goto L28
            java.lang.Object r0 = r0.get()
            lr2 r0 = (defpackage.lr2) r0
            boolean r0 = r0.B()
            if (r0 == 0) goto L28
            r0 = r1
            goto L29
        L28:
            r0 = r3
        L29:
            if (r0 == 0) goto L2c
        L2b:
            r3 = r1
        L2c:
            if (r3 != 0) goto L2f
            return
        L2f:
            android.view.GestureDetector r0 = r4.d
            r0.onTouchEvent(r5)
            int r5 = r5.getAction()
            if (r5 != r1) goto L3d
            r4.d(r1)
        L3d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.wk1.a(android.view.MotionEvent):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002b  */
    @Override // defpackage.zd3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b() {
        /*
            r4 = this;
            org.chromium.chrome.browser.tab.Tab r0 = r4.g
            r1 = 1
            r2 = 0
            if (r0 == 0) goto Lc
            boolean r0 = r0.isNativePage()
            if (r0 != 0) goto L2b
        Lc:
            tb r0 = r4.f
            g63$a r0 = (g63.a) r0
            g63 r0 = defpackage.g63.this
            qs1<lr2> r0 = r0.U
            java.lang.Object r3 = r0.get()
            if (r3 == 0) goto L28
            java.lang.Object r0 = r0.get()
            lr2 r0 = (defpackage.lr2) r0
            boolean r0 = r0.B()
            if (r0 == 0) goto L28
            r0 = r1
            goto L29
        L28:
            r0 = r2
        L29:
            if (r0 == 0) goto L2d
        L2b:
            r0 = r1
            goto L2e
        L2d:
            r0 = r2
        L2e:
            if (r0 != 0) goto L31
            return r2
        L31:
            int r4 = r4.h
            r0 = 2
            if (r4 == r0) goto L3b
            r0 = 3
            if (r4 != r0) goto L3a
            goto L3b
        L3a:
            r1 = r2
        L3b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.wk1.b():boolean");
    }

    public final boolean c() {
        Tab tab = this.g;
        if (tab != null && !tab.isDestroyed()) {
            return true;
        }
        qs1<lr2> qs1Var = g63.this.U;
        return qs1Var.get() != null && qs1Var.get().B();
    }

    public final void d(boolean z) {
        PropertyModel.n nVar = ns0.c;
        PropertyModel propertyModel = this.i;
        propertyModel.l(nVar, z);
        int i = this.h;
        if (i == 2) {
            propertyModel.n(ns0.a, 3);
        } else if (i == 3) {
            propertyModel.n(ns0.a, 4);
        }
        this.j = 0.0f;
    }

    public final void e() {
        int i = this.h;
        PropertyModel propertyModel = this.i;
        if (i == 2) {
            propertyModel.n(ns0.a, 5);
        } else if (i == 3) {
            propertyModel.n(ns0.a, 6);
        }
        this.h = 0;
        this.j = 0.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean f(float r8, float r9, boolean r10) {
        /*
            r7 = this;
            boolean r0 = r7.c()
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            org.chromium.ui.modelutil.PropertyModel$n r0 = defpackage.ns0.b
            org.chromium.ui.modelutil.PropertyModel r2 = r7.i
            r2.l(r0, r10)
            r0 = 1
            if (r10 == 0) goto L1f
            org.chromium.chrome.browser.tab.Tab r3 = r7.g
            if (r3 == 0) goto L1d
            boolean r3 = r3.i()
            if (r3 == 0) goto L1d
            goto L1f
        L1d:
            r3 = r1
            goto L20
        L1f:
            r3 = r0
        L20:
            r4 = 2
            if (r3 == 0) goto L9c
            int r8 = r7.h
            if (r8 == r0) goto L2d
            org.chromium.ui.modelutil.PropertyModel$p r8 = defpackage.ns0.a
            r9 = 5
            r2.n(r8, r9)
        L2d:
            org.chromium.ui.modelutil.PropertyModel$p r8 = defpackage.ns0.f
            if (r10 == 0) goto L33
            goto L91
        L33:
            org.chromium.chrome.browser.tab.Tab r9 = r7.g
            tb r10 = r7.f
            g63$a r10 = (g63.a) r10
            g63 r10 = defpackage.g63.this
            qs1<lr2> r5 = r10.U
            java.lang.Object r6 = r5.get()
            if (r6 == 0) goto L51
            java.lang.Object r5 = r5.get()
            lr2 r5 = (defpackage.lr2) r5
            boolean r5 = r5.B()
            if (r5 == 0) goto L51
            r5 = r0
            goto L52
        L51:
            r5 = r1
        L52:
            if (r5 == 0) goto L55
            goto L87
        L55:
            boolean r5 = r9.g()
            if (r5 != 0) goto L89
            ds2 r5 = defpackage.ds2.a(r9)
            if (r5 != 0) goto L63
            r5 = r1
            goto L65
        L63:
            boolean r5 = r5.f
        L65:
            if (r5 != 0) goto L89
            int r5 = r9.getLaunchType()
            r6 = 12
            if (r5 != r6) goto L70
            goto L89
        L70:
            boolean r5 = defpackage.jy2.f(r9)
            if (r5 == 0) goto L77
            goto L87
        L77:
            java.util.function.Function<org.chromium.chrome.browser.tab.Tab, java.lang.Boolean> r10 = r10.c1
            java.lang.Object r9 = r10.apply(r9)
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L87
            r9 = r0
            goto L8a
        L87:
            r9 = r4
            goto L8a
        L89:
            r9 = r1
        L8a:
            if (r9 != r0) goto L8e
            r1 = r0
            goto L91
        L8e:
            if (r9 != r4) goto L91
            r1 = r4
        L91:
            r2.n(r8, r1)
            org.chromium.ui.modelutil.PropertyModel$p r8 = defpackage.ns0.a
            r2.n(r8, r0)
            r7.h = r4
            goto Lba
        L9c:
            int r10 = r7.h
            if (r10 == r0) goto La6
            org.chromium.ui.modelutil.PropertyModel$p r10 = defpackage.ns0.a
            r0 = 6
            r2.n(r10, r0)
        La6:
            org.chromium.ui.modelutil.PropertyModel$r<android.gesture.GesturePoint> r10 = defpackage.ns0.g
            android.gesture.GesturePoint r0 = new android.gesture.GesturePoint
            r5 = 0
            r0.<init>(r8, r9, r5)
            r2.p(r10, r0)
            org.chromium.ui.modelutil.PropertyModel$p r8 = defpackage.ns0.a
            r2.n(r8, r4)
            r8 = 3
            r7.h = r8
        Lba:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.wk1.f(float, float, boolean):boolean");
    }
}
