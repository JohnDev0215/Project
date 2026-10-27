package org.chromium.chrome.browser.ntp;

import J.N;
import android.app.Activity;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import defpackage.ao3;
import defpackage.be;
import defpackage.lk1;
import defpackage.p21;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ntp.a;
import org.chromium.chrome.browser.site_settings.CookieControlsServiceBridge;

/* JADX INFO: compiled from: IncognitoNewTabPage.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d extends be implements p21 {
    public final Activity k;
    public final String l;
    public final IncognitoNewTabPageView m;
    public final c n;
    public a o;
    public a.InterfaceC0096a p;
    public final int q;

    public d(Activity activity, lk1.b bVar) {
        super(bVar);
        this.k = activity;
        this.q = bVar.a().getColor(R$color.ntp_bg_incognito);
        c cVar = new c(this);
        this.n = cVar;
        this.l = bVar.a().getResources().getString(R$string.new_incognito_tab_title);
        IncognitoNewTabPageView incognitoNewTabPageView = (IncognitoNewTabPageView) LayoutInflater.from(bVar.a()).inflate(R$layout.new_tab_page_incognito, (ViewGroup) null);
        this.m = incognitoNewTabPageView;
        incognitoNewTabPageView.e = cVar;
        a aVar = new a();
        this.o = aVar;
        if (!aVar.g) {
            aVar.e = new CookieControlsServiceBridge(aVar);
            aVar.g = true;
        }
        b bVar2 = new b(cVar);
        this.p = bVar2;
        this.o.f.a(bVar2);
        incognitoNewTabPageView.setIncognitoCookieControlsToggleCheckedListener(this.o);
        incognitoNewTabPageView.setIncognitoCookieControlsIconOnclickListener(this.o);
        a aVar2 = this.o;
        if (aVar2.g) {
            N.Ml$8f4xR(aVar2.e.a);
        }
        incognitoNewTabPageView.setDefaultFocusHighlightEnabled(false);
        c(incognitoNewTabPageView);
    }

    @Override // defpackage.be, defpackage.jk1
    public final void destroy() {
        d dVar = this.n.a;
        a aVar = dVar.o;
        if (aVar != null) {
            aVar.f.c(dVar.p);
        }
        super.destroy();
    }

    @Override // defpackage.jk1
    public final String getTitle() {
        return this.l;
    }

    @Override // defpackage.be, defpackage.jk1
    public final String getUrl() {
        return "chrome-native://newtab/";
    }

    @Override // defpackage.p21
    public final boolean i() {
        IncognitoNewTabPageView incognitoNewTabPageView = this.m;
        if (incognitoNewTabPageView.getWidth() == 0 || incognitoNewTabPageView.getHeight() == 0) {
            return false;
        }
        a aVar = ((c) incognitoNewTabPageView.e).a.o;
        int i = aVar.k;
        int i2 = aVar.i;
        boolean z = (i == i2 && aVar.j == aVar.h) ? false : true;
        aVar.j = aVar.h;
        aVar.k = i2;
        return (!z && incognitoNewTabPageView.getWidth() == incognitoNewTabPageView.i && incognitoNewTabPageView.getHeight() == incognitoNewTabPageView.j && incognitoNewTabPageView.g.getScrollY() == incognitoNewTabPageView.k) ? false : true;
    }

    @Override // defpackage.jk1
    public final String k() {
        return "newtab";
    }

    @Override // defpackage.p21
    public final void m(Canvas canvas) {
        IncognitoNewTabPageView incognitoNewTabPageView = this.m;
        incognitoNewTabPageView.getClass();
        ao3.d(incognitoNewTabPageView);
        incognitoNewTabPageView.draw(canvas);
        incognitoNewTabPageView.i = incognitoNewTabPageView.getWidth();
        incognitoNewTabPageView.j = incognitoNewTabPageView.getHeight();
        incognitoNewTabPageView.k = incognitoNewTabPageView.g.getScrollY();
    }

    @Override // defpackage.be, defpackage.jk1
    public final int r() {
        return this.q;
    }

    @Override // defpackage.be, defpackage.jk1
    public final void e(String str) {
    }
}
