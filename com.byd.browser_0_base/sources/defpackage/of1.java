package defpackage;

import J.N;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.appcompat.content.res.AppCompatResources;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: MerchantTrustBottomSheetMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class of1 extends rq3 {
    public GURL f;
    public final /* synthetic */ qf1 g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public of1(qf1 qf1Var, WebContents webContents) {
        super(webContents);
        this.g = qf1Var;
    }

    @Override // defpackage.rq3
    public final void didFinishNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
        if (navigationHandle.g) {
            this.g.h.p(wi.a, this.e.get().s());
        }
    }

    @Override // defpackage.rq3
    public final void didStartNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
        GURL gurl;
        qf1 qf1Var = this.g;
        qf1Var.c.getClass();
        n3.X0("MerchantTrust.BottomSheet.NavigateLink");
        if (navigationHandle.c || (gurl = navigationHandle.e) == null || gurl.equals(this.f)) {
            return;
        }
        this.f = gurl;
        Profile profile = qf1Var.g.get();
        boolean z = true;
        if (!(N.MnXKAgH9(gurl.j(), true) || N.Mn0fHwI$(gurl.j())) && profile != null) {
            z70 z70Var = new z70(qf1Var, 8, gurl);
            FaviconHelper faviconHelper = qf1Var.e;
            faviconHelper.getClass();
            faviconHelper.a(profile, gurl.j(), qf1Var.f, z70Var);
            return;
        }
        PropertyModel propertyModel = qf1Var.h;
        PropertyModel.r<Drawable> rVar = wi.j;
        if (!N.MnXKAgH9(gurl.j(), true) && !N.Mn0fHwI$(gurl.j())) {
            z = false;
        }
        Context context = qf1Var.a;
        propertyModel.p(rVar, z ? AppCompatResources.a(context, R$drawable.ic_logo_googleg_24dp) : uh3.e(context, R$drawable.ic_globe_24dp, R$color.default_icon_color_tint_list));
    }

    @Override // defpackage.rq3
    public final void loadProgressChanged(float f) {
        PropertyModel propertyModel = this.g.h;
        if (propertyModel != null) {
            propertyModel.m(wi.c, f);
        }
    }

    @Override // defpackage.rq3
    public final void titleWasSet(String str) {
        boolean zM6bsIDpc = true;
        if (a8.P()) {
            sk skVar = bq.a;
            zM6bsIDpc = N.M6bsIDpc("CommerceMerchantViewer", "trust_signals_sheet_use_page_title", true);
        }
        if (zM6bsIDpc) {
            this.g.h.p(wi.b, str);
        }
    }
}
