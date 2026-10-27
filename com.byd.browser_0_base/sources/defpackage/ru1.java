package defpackage;

import J.N;
import android.app.Activity;
import android.view.View;
import defpackage.dv0;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.history.BrowsingHistoryBridge;
import org.chromium.chrome.browser.history.a;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.page_info.PageInfoContainer;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.components.page_info.PageInfoRowView;
import org.chromium.components.page_info.c;

/* JADX INFO: compiled from: PageInfoHistoryController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ru1 implements xu1, dv0.a {
    public static final int o = View.generateViewId();
    public static final pd p = new pd(10);
    public final tu1 e;
    public final PageInfoRowView f;
    public final c g;
    public final nw2<Tab> h;
    public final String i;
    public final String j;
    public boolean k;
    public BrowsingHistoryBridge l;
    public dv0 m;
    public long n;

    public ru1(tu1 tu1Var, PageInfoRowView pageInfoRowView, vq vqVar, p30 p30Var) {
        this.e = tu1Var;
        this.f = pageInfoRowView;
        this.g = vqVar;
        this.i = pageInfoRowView.getContext().getResources().getString(R$string.page_info_history_title);
        String strE = ((PageInfoController) tu1Var).m.e();
        this.j = strE;
        this.h = p30Var;
        BrowsingHistoryBridge browsingHistoryBridge = new BrowsingHistoryBridge(Profile.d());
        this.l = browsingHistoryBridge;
        N.MN48Z3Io(browsingHistoryBridge.b, browsingHistoryBridge, strE, new dm0(19, this));
    }

    @Override // defpackage.xu1
    public final String b() {
        return this.i;
    }

    @Override // defpackage.xu1
    public final View c(PageInfoContainer pageInfoContainer) {
        Activity activity = (Activity) ((PageInfoController) this.e).f.k().get();
        String str = this.j;
        dv0 dv0Var = new dv0(activity, this, false, false, true, false, str, null, this.h, new aq1(), new BrowsingHistoryBridge(Profile.d()));
        this.m = dv0Var;
        a aVar = dv0Var.o;
        aVar.y = str;
        aVar.U();
        return this.m.p;
    }

    @Override // defpackage.xu1
    public final void d() {
        if (this.k) {
            BrowsingHistoryBridge browsingHistoryBridge = new BrowsingHistoryBridge(Profile.d());
            this.l = browsingHistoryBridge;
            N.MN48Z3Io(browsingHistoryBridge.b, browsingHistoryBridge, this.j, new dm0(19, this));
        }
        this.k = false;
    }

    @Override // defpackage.xu1
    public final void f() {
        dv0 dv0Var = this.m;
        if (dv0Var != null) {
            dv0Var.d();
            this.m = null;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:6:0x0016. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g() {
        /*
            r11 = this;
            org.chromium.components.page_info.PageInfoRowView$a r0 = new org.chromium.components.page_info.PageInfoRowView$a
            r0.<init>()
            long r1 = r11.n
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r2 = 0
            org.chromium.components.page_info.PageInfoRowView r5 = r11.f
            r6 = 1
            if (r1 != 0) goto L12
            goto L44
        L12:
            pd r1 = defpackage.ru1.p
            int r1 = r1.e
            switch(r1) {
                case 7: goto L19;
                default: goto L19;
            }
        L19:
            long r7 = java.lang.System.currentTimeMillis()
            java.util.Calendar r1 = defpackage.jl.a(r7)
            java.util.Date r1 = r1.getTime()
            long r7 = r1.getTime()
            long r9 = r11.n
            java.util.Calendar r1 = defpackage.jl.a(r9)
            java.util.Date r1 = r1.getTime()
            long r9 = r1.getTime()
            long r7 = r7 - r9
            android.content.Context r1 = r5.getContext()
            android.content.res.Resources r1 = r1.getResources()
            int r3 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r3 >= 0) goto L46
        L44:
            r1 = 0
            goto L8c
        L46:
            if (r3 != 0) goto L4f
            int r3 = org.chromium.chrome.R$string.page_info_history_last_visit_today
            java.lang.String r1 = r1.getString(r3)
            goto L8c
        L4f:
            r3 = 86400000(0x5265c00, double:4.2687272E-316)
            int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r9 != 0) goto L5d
            int r3 = org.chromium.chrome.R$string.page_info_history_last_visit_yesterday
            java.lang.String r1 = r1.getString(r3)
            goto L8c
        L5d:
            if (r9 <= 0) goto L77
            r9 = 604800000(0x240c8400, double:2.988109026E-315)
            int r9 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r9 > 0) goto L77
            int r9 = org.chromium.chrome.R$string.page_info_history_last_visit_days
            java.lang.Object[] r10 = new java.lang.Object[r6]
            long r7 = r7 / r3
            int r3 = (int) r7
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r10[r2] = r3
            java.lang.String r1 = r1.getString(r9, r10)
            goto L8c
        L77:
            int r3 = org.chromium.chrome.R$string.page_info_history_last_visit_date
            java.lang.Object[] r4 = new java.lang.Object[r6]
            java.util.Date r7 = new java.util.Date
            long r8 = r11.n
            r7.<init>(r8)
            java.lang.StringBuilder r7 = defpackage.ou2.a(r7)
            r4[r2] = r7
            java.lang.String r1 = r1.getString(r3, r4)
        L8c:
            r0.e = r1
            if (r1 == 0) goto La1
            org.chromium.components.page_info.c r1 = r11.g
            boolean r3 = r1.b
            if (r3 == 0) goto La1
            vq r1 = (defpackage.vq) r1
            org.chromium.chrome.browser.profiles.Profile r1 = r1.j
            boolean r1 = r1.h()
            if (r1 != 0) goto La1
            r2 = r6
        La1:
            r0.a = r2
            int r1 = org.chromium.chrome.R$drawable.ic_history_googblue_24dp
            r0.b = r1
            r0.h = r6
            eu1 r1 = new eu1
            r1.<init>(r6, r11)
            r0.g = r1
            r5.setParams(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ru1.g():void");
    }

    @Override // dv0.a
    public final void q() {
        ((PageInfoController) this.e).f(21);
    }

    @Override // dv0.a
    public final void t(ev0 ev0Var) {
        PageInfoController pageInfoController = (PageInfoController) this.e;
        pageInfoController.f(20);
        this.k = true;
        if (this.m.o.h == 0) {
            this.n = 0L;
            g();
            pageInfoController.d();
        }
    }

    @Override // defpackage.xu1
    public final void a() {
    }

    @Override // dv0.a
    public final void e() {
    }

    @Override // dv0.a
    public final void l() {
    }

    @Override // dv0.a
    public final void s() {
    }

    @Override // dv0.a
    public final void u() {
    }

    @Override // dv0.a
    public final void h(boolean z) {
    }
}
