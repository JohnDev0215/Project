package org.chromium.chrome.browser.feed.webfeed;

import J.N;
import android.util.Log;
import defpackage.bq;
import defpackage.c3;
import defpackage.d73;
import defpackage.mk3;
import defpackage.nh0;
import defpackage.p62;
import defpackage.rh3;
import defpackage.sk;
import defpackage.y70;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.feed.webfeed.e;
import org.chromium.chrome.browser.feed.webfeed.f;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: WebFeedFollowIntroController.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends nh0 {
    public final /* synthetic */ e e;

    public b(e eVar) {
        this.e = eVar;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void Z(TabImpl tabImpl) {
        boolean z;
        f.a aVar;
        GURL url = tabImpl.getUrl();
        if (tabImpl.isIncognito()) {
            Log.i("cr_WFFollowIntroCtrl", "No intro: tab is incognito");
            return;
        }
        if (!url.i().equals("http") && !url.i().equals("https")) {
            StringBuilder sb = new StringBuilder("No intro: URL scheme is not HTTP or HTTPS: ");
            sb.append(url.b ? url.a : "");
            Log.i("cr_WFFollowIntroCtrl", sb.toString());
            return;
        }
        e eVar = this.e;
        f fVar = eVar.j;
        fVar.getClass();
        mk3 mk3VarB = tabImpl.B();
        byte[] bArr = null;
        if (mk3VarB != null && (aVar = (f.a) mk3VarB.b(f.a.class)) != null) {
            bArr = aVar.e;
        }
        int i = 6;
        if (bArr == null) {
            z = false;
        } else {
            N.MHvYsLrH(bArr, new p62(fVar, i, bArr));
            z = true;
        }
        if (z) {
            return;
        }
        c3 c3Var = new c3(22, this);
        e.a.C0085a c0085a = new e.a.C0085a();
        e.a aVar2 = eVar.k;
        aVar2.d = c0085a;
        c0085a.a = tabImpl;
        c0085a.b = url;
        c0085a.c = c3Var;
        System.nanoTime();
        d73 d73Var = rh3.a;
        y70 y70Var = new y70(aVar2, 6, c0085a);
        sk skVar = bq.a;
        PostTask.b(d73Var, y70Var, N.M37SqSAy("WebFeed", "intro-wait-time-millis", 3000));
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void g1(Tab tab, GURL gurl) {
        e eVar = this.e;
        eVar.k.d = null;
        eVar.j.b.b();
        eVar.h.b();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
        f.a aVar;
        f fVar = this.e.j;
        fVar.getClass();
        if (!navigationHandle.a || navigationHandle.c) {
            return;
        }
        fVar.b.b();
        if (navigationHandle.s == null) {
            navigationHandle.s = new mk3();
        }
        mk3 mk3Var = navigationHandle.s;
        byte[] bArr = (mk3Var == null || (aVar = (f.a) mk3Var.b(f.a.class)) == null) ? null : aVar.e;
        if (bArr != null) {
            tabImpl.B.d(f.a.class, new f.a(bArr));
            return;
        }
        mk3 mk3Var2 = tabImpl.B;
        if (mk3Var2 == null || mk3Var2.b(f.a.class) == null) {
            return;
        }
        tabImpl.B.c(f.a.class);
    }
}
