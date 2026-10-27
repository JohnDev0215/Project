package defpackage;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.protobuf.d0;
import defpackage.iq2;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.page_info.AboutThisSiteView;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.page_info.PageInfoContainer;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.components.page_info.PageInfoRowView;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: PageInfoAboutThisSiteController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class gu1 implements xu1 {
    public static final int k = View.generateViewId();
    public final tu1 e;
    public final nw2<EphemeralTabCoordinator> f;
    public final PageInfoRowView g;
    public final WebContents h;
    public C0193k i;
    public fu1 j;

    public gu1(tu1 tu1Var, nw2 nw2Var, PageInfoRowView pageInfoRowView, vq vqVar, WebContents webContents) {
        C0193k c0193kT;
        this.e = tu1Var;
        this.f = nw2Var;
        this.g = pageInfoRowView;
        this.h = webContents;
        if (!vqVar.b || vqVar.j.h()) {
            return;
        }
        PageInfoController pageInfoController = (PageInfoController) tu1Var;
        if (pageInfoController.n != 3) {
            return;
        }
        byte[] bArrMysp9BSd = N.Mysp9BSd(((vq) pageInfoController.h).j, pageInfoController.m, webContents);
        if (bArrMysp9BSd == null) {
            c0193kT = null;
        } else {
            try {
                c0193kT = C0193k.T(bArrMysp9BSd);
            } catch (d0 e) {
                Log.e("cr_PageInfo", "Could not parse proto: %s", e);
                c0193kT = null;
            }
        }
        this.i = c0193kT;
        if (c0193kT == null) {
            return;
        }
        PageInfoRowView pageInfoRowView2 = this.g;
        String strP = this.i.Q() ? this.i.O().P() : pageInfoRowView2.getContext().getResources().getString(R$string.page_info_about_this_page_description_placeholder);
        PageInfoRowView.a aVar = new PageInfoRowView.a();
        aVar.e = e();
        aVar.f = strP;
        aVar.i = true;
        aVar.a = true;
        sk skVar = bq.a;
        aVar.b = N.M09VlOh_("PageInfoAboutThisSiteNewIcon") && N.M09VlOh_("PageInfoAboutThisSiteMoreInfo") ? N.M3N2gIjq() : R$drawable.ic_info_outline_grey_24dp;
        aVar.d = !(N.M09VlOh_("PageInfoAboutThisSiteNewIcon") && N.M09VlOh_("PageInfoAboutThisSiteMoreInfo"));
        aVar.h = true;
        aVar.g = new eu1(0, this);
        pageInfoRowView2.setParams(aVar);
    }

    @Override // defpackage.xu1
    public final void a() {
        boolean z = this.i != null;
        PageInfoController pageInfoController = (PageInfoController) this.e;
        long j = pageInfoController.i;
        if (j != 0) {
            N.M_us4Ps5(j, pageInfoController, z);
        } else {
            Log.e("PageInfoController  ", "setAboutThisSiteShown:   mNativePageInfoController == 0");
        }
    }

    @Override // defpackage.xu1
    public final String b() {
        return this.g.getContext().getResources().getString(R$string.page_info_about_this_site_title);
    }

    @Override // defpackage.xu1
    public final View c(PageInfoContainer pageInfoContainer) {
        Context context = pageInfoContainer.getContext();
        int i = R$layout.page_info_about_this_site_view;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        ku2 ku2VarT = ku2.t();
        try {
            View viewInflate = layoutInflaterFrom.inflate(i, (ViewGroup) pageInfoContainer, false);
            ku2VarT.close();
            AboutThisSiteView aboutThisSiteView = (AboutThisSiteView) viewInflate;
            C0193k c0193k = this.i;
            aboutThisSiteView.g = new zc0(26, this);
            C0189i c0189iO = c0193k.O();
            aboutThisSiteView.e.setText(c0189iO.P());
            aboutThisSiteView.f.setText(iq2.a(aboutThisSiteView.getContext().getResources().getString(R$string.page_info_about_this_site_subpage_from_label, "<link>" + c0189iO.Q().P() + "</link>"), new iq2.a(new un1(aboutThisSiteView.getContext(), new lj1(10, aboutThisSiteView)), "<link>", "</link>")));
            return aboutThisSiteView;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final String e() {
        Resources resources = this.g.getContext().getResources();
        sk skVar = bq.a;
        return resources.getString(N.M09VlOh_("PageInfoAboutThisSiteMoreInfo") ? R$string.page_info_about_this_page_title : R$string.page_info_about_this_site_title);
    }

    public final void g(int i, String str) {
        PageInfoController pageInfoController = (PageInfoController) this.e;
        pageInfoController.f(i);
        nw2<EphemeralTabCoordinator> nw2Var = this.f;
        if (nw2Var == null || nw2Var.get() == null) {
            new iz2(false).b(0, (TabImpl) N.MMqeq$AW(this.h), new LoadUrlParams(str, 0));
            return;
        }
        Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
        if (this.i.S() && str.equals(this.i.P().P())) {
            sk skVar = bq.a;
            if (N.M09VlOh_("PageInfoAboutThisSiteImprovedBottomSheet")) {
                builderBuildUpon.appendQueryParameter("ilrm", "minimal,nohead");
            } else {
                builderBuildUpon.appendQueryParameter("ilrm", "minimal");
            }
        }
        GURL gurl = new GURL(builderBuildUpon.toString());
        GURL gurl2 = new GURL(str);
        sk skVar2 = bq.a;
        if (N.M09VlOh_("PageInfoAboutThisSiteImprovedBottomSheet")) {
            this.j = new fu1(this, gurl);
            EphemeralTabCoordinator ephemeralTabCoordinator = nw2Var.get();
            ephemeralTabCoordinator.k.c.a(this.j);
        }
        nw2Var.get().a(gurl, gurl2, e(), false);
        pu1 pu1Var = pageInfoController.l;
        if (pu1Var != null) {
            pu1Var.b.dismiss();
        }
    }

    @Override // defpackage.xu1
    public final void d() {
    }

    @Override // defpackage.xu1
    public final void f() {
    }
}
