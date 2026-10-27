package defpackage;

import J.N;
import android.graphics.Bitmap;
import android.text.format.Formatter;
import defpackage.ce;
import defpackage.jk2;
import defpackage.lr2;
import defpackage.p9;
import defpackage.px0;
import defpackage.yr2;
import java.io.FileOutputStream;
import java.util.Collection;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.top.n;
import org.chromium.chrome.browser.ui.signin.b;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;
import org.chromium.components.browser_ui.site_settings.f;
import org.chromium.components.browser_ui.site_settings.g;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.page_info.PageInfoCookiesPreference;
import org.chromium.components.page_info.R$drawable;
import org.chromium.components.page_info.R$string;
import org.chromium.components.page_info.d;
import org.chromium.components.webauthn.InternalAuthenticator;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class gc3 implements ot1, ce.a, n.a, RadioButtonWithDescription.a, px0.a, yr2.a, lr2.a, jk2.d, g.c, p9.a, SelectionPopupControllerImpl.a {
    public final /* synthetic */ Object e;

    public /* synthetic */ gc3(Object obj) {
        this.e = obj;
    }

    @Override // org.chromium.components.browser_ui.site_settings.g.c
    public final void a(Collection collection) {
        d dVar = (d) this.e;
        f fVarU0 = SingleWebsiteSettings.U0(ts3.e(ft1.c(dVar.i).toString()), collection);
        dVar.q = fVarU0;
        PageInfoCookiesPreference pageInfoCookiesPreference = dVar.l;
        if (pageInfoCookiesPreference != null) {
            long j = fVarU0.j();
            boolean z = false;
            pageInfoCookiesPreference.j0.R(j > 0 ? String.format(pageInfoCookiesPreference.K().getString(R$string.origin_settings_storage_usage_brief), Formatter.formatShortFileSize(pageInfoCookiesPreference.K(), j)) : null);
            pageInfoCookiesPreference.n0 |= j != 0;
            pageInfoCookiesPreference.O0();
            PageInfoCookiesPreference pageInfoCookiesPreference2 = dVar.l;
            f fVar = dVar.q;
            jj0 jj0Var = fVar.j;
            String strH = fVar.e.h();
            if (jj0Var != null) {
                ChromeImageViewPreference chromeImageViewPreference = pageInfoCookiesPreference2.k0;
                if (chromeImageViewPreference != null) {
                    chromeImageViewPreference.U(true);
                    pageInfoCookiesPreference2.k0.S(R$string.cookie_info_fps_title);
                    pageInfoCookiesPreference2.k0.R(String.format(pageInfoCookiesPreference2.K().getString(R$string.cookie_info_fps_summary), jj0Var.e));
                    pageInfoCookiesPreference2.k0.L(qj2.b(pageInfoCookiesPreference2.K(), R$drawable.tenancy));
                    ChromeImageViewPreference chromeImageViewPreference2 = pageInfoCookiesPreference2.k0;
                    lu1 lu1Var = new lu1(pageInfoCookiesPreference2, ((kr) pageInfoCookiesPreference2.h0).a(), strH);
                    chromeImageViewPreference2.S = lu1Var;
                    nb1.c(lu1Var, chromeImageViewPreference2, false, true);
                    z = true;
                }
            } else {
                pageInfoCookiesPreference2.getClass();
            }
            oj2.O("Security.PageInfo.Cookies.HasFPSInfo", z);
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.n.a
    public final void b(boolean z) {
        ((org.chromium.chrome.browser.toolbar.top.d) this.e).a.e.l(cs2.c, z);
    }

    @Override // px0.a
    public final void c() {
        n3.X0(((xx2) this.e).d);
    }

    @Override // defpackage.tl
    public final void d(Integer num, ps0 ps0Var, jq3 jq3Var) {
        Integer num2 = num;
        ps0 ps0Var2 = ps0Var;
        long j = ((InternalAuthenticator) this.e).a;
        if (j != 0) {
            N.MHORk0l8(j, num2.intValue(), ps0Var2 == null ? null : ps0Var2.b());
        }
    }

    public final boolean e() {
        nr2 nr2Var = (nr2) this.e;
        return nr2Var.q.d() || ApplicationStatus.c(nr2Var.a) == 6;
    }

    @Override // ce.a
    public final boolean f(GURL gurl) {
        org.chromium.chrome.browser.toolbar.g gVar = (org.chromium.chrome.browser.toolbar.g) this.e;
        if (!gVar.A.f()) {
            return false;
        }
        long j = gVar.A.get().a;
        return (j > 0L ? 1 : (j == 0L ? 0 : -1)) == 0 ? false : N.MTTi3vAA(j, gurl);
    }

    @Override // defpackage.ot1
    public final boolean h(String str, int i, long j, String str2, byte[] bArr, boolean z) {
        nw2 nw2Var = (nw2) this.e;
        LoadUrlParams loadUrlParams = new LoadUrlParams(str, i | 33554432);
        loadUrlParams.q = j;
        return zb2.c(loadUrlParams, str2, bArr, false, Boolean.valueOf(z), (Tab) nw2Var.get()) != null;
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription.a
    public final void j(RadioButtonWithDescription radioButtonWithDescription) {
        ((b) this.e).c.l(di1.l, false);
    }

    @Override // jk2.d
    public final void s(FileOutputStream fileOutputStream, sl0 sl0Var) {
        Bitmap bitmap = (Bitmap) this.e;
        bitmap.compress(bitmap.hasAlpha() ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        sl0Var.onResult(Boolean.TRUE);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.b
    public final void t(int i, long j) {
        ((or2) this.e).I(i, true);
    }
}
