package defpackage;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.components.embedder_support.view.ContentView;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: MerchantTrustBottomSheetMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qf1 {
    public final Context a;
    public final WindowAndroid b;
    public final wf1 c;
    public final int d;
    public final FaviconHelper e;
    public final int f;
    public final zp1<Profile> g;
    public PropertyModel h;
    public WebContents i;
    public ContentView j;
    public pf1 k;
    public of1 l;

    public qf1(AppCompatActivity appCompatActivity, g3 g3Var, wf1 wf1Var, zp1 zp1Var, FaviconHelper faviconHelper) {
        this.a = appCompatActivity;
        this.b = g3Var;
        this.c = wf1Var;
        this.d = (int) (appCompatActivity.getResources().getDimensionPixelSize(R$dimen.toolbar_height_no_shadow) / g3Var.h.d);
        this.e = faviconHelper;
        this.f = appCompatActivity.getResources().getDimensionPixelSize(R$dimen.preview_tab_favicon_size);
        this.g = zp1Var;
    }

    public final void a(GURL gurl) {
        WebContents webContents = this.i;
        if (webContents != null) {
            webContents.m().b(new LoadUrlParams(gurl.j(), 0));
        }
    }
}
