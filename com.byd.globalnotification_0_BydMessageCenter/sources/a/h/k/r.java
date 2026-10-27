package a.h.k;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class r implements View.OnApplyWindowInsetsListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public w f832a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f833b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ k f834c;

    public r(View view, k kVar) {
        this.f833b = view;
        this.f834c = kVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001e  */
    @Override // android.view.View.OnApplyWindowInsetsListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.WindowInsets onApplyWindowInsets(android.view.View r4, android.view.WindowInsets r5) {
        /*
            r3 = this;
            a.h.k.w r0 = a.h.k.w.h(r5, r4)
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 30
            if (r1 >= r2) goto L1e
            android.view.View r1 = r3.f833b
            a.b.k.l.i.f(r5, r1)
            a.h.k.w r5 = r3.f832a
            boolean r5 = r0.equals(r5)
            if (r5 == 0) goto L1e
            a.h.k.k r3 = r3.f834c
            a.h.k.w r3 = r3.a(r4, r0)
            goto L32
        L1e:
            r3.f832a = r0
            a.h.k.k r3 = r3.f834c
            a.h.k.w r3 = r3.a(r4, r0)
            int r5 = android.os.Build.VERSION.SDK_INT
            if (r5 < r2) goto L2f
            android.view.WindowInsets r3 = r3.f()
            return r3
        L2f:
            a.h.k.q.S(r4)
        L32:
            android.view.WindowInsets r3 = r3.f()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: a.h.k.r.onApplyWindowInsets(android.view.View, android.view.WindowInsets):android.view.WindowInsets");
    }
}
