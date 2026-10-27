package org.chromium.components.page_info;

import J.N;
import android.view.View;
import defpackage.an2;
import defpackage.aq3;
import defpackage.f00;
import defpackage.f52;
import defpackage.gc3;
import defpackage.im2;
import defpackage.iq2;
import defpackage.j52;
import defpackage.kr;
import defpackage.l51;
import defpackage.qj2;
import defpackage.tu1;
import defpackage.un1;
import defpackage.uu1;
import defpackage.ve3;
import defpackage.vq;
import defpackage.yn2;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;
import org.chromium.components.browser_ui.site_settings.g;
import org.chromium.components.content_settings.CookieControlsBridge;
import org.chromium.components.page_info.PageInfoCookiesPreference;
import org.chromium.components.page_info.PageInfoRowView;

/* JADX INFO: compiled from: PageInfoCookiesController.java */
/* JADX INFO: loaded from: classes3.dex */
public final class d extends uu1 implements f00 {
    public final tu1 g;
    public final PageInfoRowView h;
    public final String i;
    public final String j;
    public CookieControlsBridge k;
    public PageInfoCookiesPreference l;
    public int m;
    public int n;
    public int o;
    public boolean p;
    public org.chromium.components.browser_ui.site_settings.f q;

    public d(tu1 tu1Var, PageInfoRowView pageInfoRowView, vq vqVar) {
        super(vqVar);
        this.g = tu1Var;
        this.h = pageInfoRowView;
        this.i = ((PageInfoController) tu1Var).m.j();
        String string = pageInfoRowView.getContext().getResources().getString(R$string.page_info_cookies_title);
        this.j = string;
        Profile profile = vqVar.j;
        this.k = new CookieControlsBridge(this, vqVar.f, profile.h() ? (Profile) N.MD_ez$kP(profile.b, profile) : null);
        PageInfoRowView.a aVar = new PageInfoRowView.a();
        aVar.a = vqVar.b;
        aVar.e = string;
        aVar.b = R$drawable.permission_cookie;
        aVar.h = true;
        aVar.g = new an2(2, this);
        pageInfoRowView.setParams(aVar);
    }

    @Override // defpackage.xu1
    public final String b() {
        return this.j;
    }

    @Override // defpackage.xu1
    public final View c(PageInfoContainer pageInfoContainer) {
        c cVar = this.e;
        if (!(!cVar.a().L())) {
            return null;
        }
        PageInfoCookiesPreference pageInfoCookiesPreference = new PageInfoCookiesPreference();
        this.l = pageInfoCookiesPreference;
        View viewE = e(pageInfoCookiesPreference);
        PageInfoCookiesPreference.a aVar = new PageInfoCookiesPreference.a();
        aVar.a = cVar.c;
        aVar.b = new aq3(6, this);
        aVar.c = new ve3(19, this);
        aVar.d = new f52(29, cVar);
        tu1 tu1Var = this.g;
        aVar.e = N.M9l6T3Dg(((vq) ((PageInfoController) tu1Var).h).j, this.i);
        PageInfoController pageInfoController = (PageInfoController) tu1Var;
        aVar.f = pageInfoController.m.e();
        PageInfoCookiesPreference pageInfoCookiesPreference2 = this.l;
        pageInfoCookiesPreference2.f("cookie_summary").R(iq2.a(pageInfoCookiesPreference2.O(R$string.page_info_cookies_description), new iq2.a(new un1(pageInfoCookiesPreference2.K(), new l51(16, aVar)), "<link>", "</link>")));
        pageInfoCookiesPreference2.i0.U(aVar.a);
        pageInfoCookiesPreference2.i0.setOnPreferenceChangeListener(new im2(aVar));
        if (true ^ N.MJSt3Ocq(((kr) pageInfoCookiesPreference2.h0).b, 0)) {
            pageInfoCookiesPreference2.i0.S(R$string.page_info_all_cookies_block);
        }
        pageInfoCookiesPreference2.j0.L(qj2.b(pageInfoCookiesPreference2.K(), R$drawable.permission_cookie));
        pageInfoCookiesPreference2.j0.Z(R$drawable.ic_delete_white_24dp, R$string.page_info_cookies_clear, null);
        ChromeImageViewPreference chromeImageViewPreference = pageInfoCookiesPreference2.j0;
        if (chromeImageViewPreference.Y) {
            chromeImageViewPreference.Y = false;
            chromeImageViewPreference.Y();
        }
        pageInfoCookiesPreference2.m0 = aVar.e;
        pageInfoCookiesPreference2.j0.setOnPreferenceClickListener(new j52(pageInfoCookiesPreference2, 3));
        pageInfoCookiesPreference2.O0();
        pageInfoCookiesPreference2.l0 = aVar.c;
        pageInfoCookiesPreference2.o0 = aVar.f;
        this.l.N0(this.m, this.n);
        this.l.M0(this.o, this.p);
        vq vqVar = (vq) cVar;
        new g(((vq) pageInfoController.h).j, false).b(new kr(vqVar.i, vqVar.j), yn2.c(((vq) pageInfoController.h).j, 22), new gc3(this));
        return viewE;
    }

    @Override // defpackage.xu1
    public final void f() {
        this.l = null;
        g();
    }

    @Override // defpackage.xu1
    public final void a() {
    }

    @Override // defpackage.xu1
    public final void d() {
    }
}
