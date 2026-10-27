package F0;

import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public abstract class w1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ViewGroup.LayoutParams f2293a = new ViewGroup.LayoutParams(-2, -2);

    /* JADX WARN: Removed duplicated region for block: B:20:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ad  */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.Object, java.util.Collection] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final F0.u1 a(F0.AbstractC0191a r6, T.AbstractC0764s r7, b0.b r8) {
        /*
            java.util.concurrent.atomic.AtomicBoolean r0 = F0.AbstractC0241z0.f2301a
            r1 = 0
            r2 = 1
            boolean r0 = r0.compareAndSet(r1, r2)
            r3 = 0
            if (r0 == 0) goto L3f
            r0 = 6
            U8.l r0 = x3.AbstractC2673a.a(r2, r0, r3)
            m7.n r2 = F0.C0204g0.f2194x
            java.lang.Object r2 = r2.getValue()
            q7.h r2 = (q7.InterfaceC2194h) r2
            X8.c r2 = S8.AbstractC0727y.a(r2)
            F0.y0 r4 = new F0.y0
            r4.<init>(r0, r3)
            r5 = 3
            S8.AbstractC0727y.w(r2, r3, r3, r4, r5)
            A.F r2 = new A.F
            r4 = 11
            r2.<init>(r0, r4)
            java.lang.Object r0 = e0.AbstractC1174n.f13721b
            monitor-enter(r0)
            java.lang.Object r4 = e0.AbstractC1174n.f13725h     // Catch: java.lang.Throwable -> L3c
            java.util.ArrayList r2 = n7.AbstractC1998m.o0(r4, r2)     // Catch: java.lang.Throwable -> L3c
            e0.AbstractC1174n.f13725h = r2     // Catch: java.lang.Throwable -> L3c
            monitor-exit(r0)
            e0.AbstractC1174n.a()
            goto L3f
        L3c:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        L3f:
            int r0 = r6.getChildCount()
            if (r0 <= 0) goto L52
            android.view.View r0 = r6.getChildAt(r1)
            boolean r1 = r0 instanceof androidx.compose.ui.platform.AndroidComposeView
            if (r1 == 0) goto L50
            androidx.compose.ui.platform.AndroidComposeView r0 = (androidx.compose.ui.platform.AndroidComposeView) r0
            goto L56
        L50:
            r0 = r3
            goto L56
        L52:
            r6.removeAllViews()
            goto L50
        L56:
            if (r0 != 0) goto L6e
            androidx.compose.ui.platform.AndroidComposeView r0 = new androidx.compose.ui.platform.AndroidComposeView
            android.content.Context r1 = r6.getContext()
            q7.h r2 = r7.h()
            r0.<init>(r1, r2)
            android.view.View r1 = r0.getView()
            android.view.ViewGroup$LayoutParams r2 = F0.w1.f2293a
            r6.addView(r1, r2)
        L6e:
            l3.P r6 = new l3.P
            E0.F r1 = r0.getRoot()
            r6.<init>(r1)
            T.u r1 = new T.u
            r1.<init>(r7, r6)
            android.view.View r6 = r0.getView()
            r2 = 2131362367(0x7f0a023f, float:1.8344513E38)
            java.lang.Object r6 = r6.getTag(r2)
            boolean r4 = r6 instanceof F0.u1
            if (r4 == 0) goto L8e
            r3 = r6
            F0.u1 r3 = (F0.u1) r3
        L8e:
            if (r3 != 0) goto L9c
            F0.u1 r3 = new F0.u1
            r3.<init>(r0, r1)
            android.view.View r6 = r0.getView()
            r6.setTag(r2, r3)
        L9c:
            r3.c(r8)
            q7.h r6 = r0.getCoroutineContext()
            q7.h r8 = r7.h()
            boolean r6 = A7.m.a(r6, r8)
            if (r6 != 0) goto Lb4
            q7.h r6 = r7.h()
            r0.setCoroutineContext(r6)
        Lb4:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.w1.a(F0.a, T.s, b0.b):F0.u1");
    }
}
