package defpackage;

import J.N;
import android.content.ComponentName;
import android.text.TextUtils;
import java.util.regex.Pattern;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.share.ShareHelper;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ShareDelegateImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public class fk2 implements ck2 {
    public final ji a;
    public final q2 b;
    public final nw2<Tab> c;
    public final nw2<y13> d;
    public final nw2<Profile> e;
    public final a f;
    public final boolean g;
    public long h;

    /* JADX INFO: compiled from: ShareDelegateImpl.java */
    public static class a {
    }

    public fk2(ji jiVar, q2 q2Var, nw2 nw2Var, aq1 aq1Var, nw2 nw2Var2, a aVar, boolean z) {
        this.a = jiVar;
        this.b = q2Var;
        this.c = nw2Var;
        this.d = aq1Var;
        this.e = nw2Var2;
        this.f = aVar;
        this.g = z;
    }

    public static void d(GURL gurl, GURL gurl2) {
        int i;
        if (!"https".equals(gurl.i())) {
            i = 0;
        } else if (gurl2 == null || gurl2.k()) {
            i = 2;
        } else {
            String strI = gurl2.i();
            i = !"https".equals(strI) ? !"http".equals(strI) ? 3 : 6 : gurl.equals(gurl2) ? 5 : 4;
        }
        oj2.U(i, 7, "Mobile.CanonicalURLResult");
    }

    @Override // defpackage.ck2
    public boolean a() {
        if (!this.g) {
            sk skVar = bq.a;
            if (!N.M09VlOh_("ShareSheetMigrationAndroid")) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    @Override // defpackage.ck2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(int r18, org.chromium.chrome.browser.tab.Tab r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fk2.b(int, org.chromium.chrome.browser.tab.Tab, boolean):void");
    }

    @Override // defpackage.ck2
    public final void c(org.chromium.components.browser_ui.share.a aVar, jr jrVar, int i) {
        fk2 fk2Var = this;
        if (fk2Var.h == 0) {
            fk2Var.h = System.currentTimeMillis();
        }
        ji jiVar = fk2Var.a;
        q2 q2Var = fk2Var.b;
        nw2<Tab> nw2Var = fk2Var.c;
        long j = fk2Var.h;
        boolean zA = a();
        fk2Var.f.getClass();
        nw2<Profile> nw2Var2 = fk2Var.e;
        Profile profileD = nw2Var2.get();
        if (profileD == null) {
            profileD = Profile.d();
        }
        Profile profile = profileD;
        if (jrVar.b) {
            ComponentName componentNameD = hk2.d();
            if (componentNameD != null) {
                ShareHelper.c(2);
                hk2.g(aVar, componentNameD, null, false);
            }
        } else {
            nw2<y13> nw2Var3 = fk2Var.d;
            if (!zA || jrVar.f || profile == null) {
                oj2.U(i, 9, "Sharing.DefaultSharesheetAndroid.Opened");
                new la0(jiVar, nw2Var, nw2Var3, nw2Var2).n(aVar, jrVar, true);
            } else {
                if (profile.b == 0) {
                    throw new uv("Native profile pointer not initialized.");
                }
                oj2.U(i, 9, "Sharing.SharingHubAndroid.Opened");
                ShareHelper.c(1);
                boolean z = nw2Var3.f() && nw2Var3.get().r();
                LargeIconBridge largeIconBridge = new LargeIconBridge(profile);
                AppHooks.get().getClass();
                new xk2(jiVar, q2Var, nw2Var, largeIconBridge, z, oj2.t(profile), profile).n(aVar, jrVar, j);
            }
            fk2Var = this;
        }
        fk2Var.h = 0L;
    }

    public final void e(WindowAndroid windowAndroid, WebContents webContents, String str, GURL gurl, GURL gurl2, int i, boolean z) {
        String strJ;
        if (gurl2 == null || gurl2.k() || !"https".equals(gurl.i())) {
            strJ = gurl.j();
        } else {
            Pattern pattern = bk3.a;
            strJ = !bk3.m(gurl2.i()) ? gurl.j() : gurl2.j();
        }
        if (!TextUtils.isEmpty(strJ) && !TextUtils.isEmpty(strJ)) {
            strJ = ((GURL) N.M1WDPiaY(strJ)).j();
        }
        c(new org.chromium.components.browser_ui.share.a(windowAndroid, str, null, null, strJ, null, null, null, null, null, null, null, null, null), new jr(!z, z, true, null, null, false, false, null, 0), i);
        if (webContents == null || webContents.isDestroyed()) {
            return;
        }
        N.MKWAPOgN(webContents);
    }
}
