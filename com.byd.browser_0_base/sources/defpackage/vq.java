package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import defpackage.wu1;
import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.offlinepages.OfflinePageItem;
import org.chromium.chrome.browser.offlinepages.d;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.page_info.c;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ChromePageInfoControllerDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vq extends c {
    public final WebContents f;
    public final nw2<bi1> g;
    public final nw2<EphemeralTabCoordinator> h;
    public final Context i;
    public final Profile j;
    public final nw2<wu1.a> k;
    public final wq l;
    public final d.b m;
    public String n;

    public vq(Activity activity, WebContents webContents, nw2 nw2Var, d.b bVar, nw2 nw2Var2, nw2 nw2Var3, wq wqVar) {
        qp qpVar = new qp(Profile.b(webContents));
        boolean z = false;
        boolean z2 = d.c(webContents) != null;
        GURL gurlD = webContents != null ? a8.D(webContents.s()) : null;
        if (!z2 && gurlD != null) {
            Pattern pattern = bk3.a;
            if (bk3.m(gurlD.i())) {
                z = true;
            }
        }
        super(qpVar, z, N.Mvm8S6US(Profile.b(webContents)));
        this.i = activity;
        this.f = webContents;
        this.g = nw2Var;
        this.h = nw2Var3;
        this.j = Profile.b(webContents);
        this.k = nw2Var2;
        this.l = wqVar;
        this.d = 1;
        OfflinePageItem offlinePageItemC = d.c(webContents);
        if (offlinePageItemC != null) {
            this.e = offlinePageItemC.a;
            if (d.f(webContents)) {
                this.d = 2;
            } else {
                this.d = 3;
            }
            long j = offlinePageItemC.g;
            if (j != 0) {
                this.n = DateFormat.getDateInstance(2).format(new Date(j));
            }
        }
        this.m = bVar;
        oj2.t(Profile.d()).notifyEvent("page_info_opened");
    }

    @Override // org.chromium.components.page_info.c
    public final lp0 a() {
        FragmentActivity fragmentActivity = (FragmentActivity) this.i;
        if (fragmentActivity.isFinishing()) {
            return null;
        }
        return fragmentActivity.x1();
    }

    @Override // org.chromium.components.page_info.c
    public final String b() {
        int i = this.d;
        Context context = this.i;
        if (i == 2) {
            return String.format(context.getString(R$string.page_info_connection_offline), this.n);
        }
        if (i == 3) {
            return TextUtils.isEmpty(this.n) ? context.getString(R$string.page_info_offline_page_not_trusted_without_date) : String.format(context.getString(R$string.page_info_offline_page_not_trusted_with_date), this.n);
        }
        return null;
    }

    @Override // org.chromium.components.page_info.c
    public final boolean c() {
        TabImpl tabImpl = (TabImpl) N.MMqeq$AW(this.f);
        return tabImpl != null && a63.a(tabImpl).b();
    }
}
