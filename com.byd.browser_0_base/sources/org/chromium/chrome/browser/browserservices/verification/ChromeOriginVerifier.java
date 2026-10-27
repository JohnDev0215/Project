package org.chromium.chrome.browser.browserservices.verification;

import J.N;
import android.os.SystemClock;
import android.text.TextUtils;
import defpackage.fa1;
import defpackage.ft1;
import defpackage.gl3;
import defpackage.iv;
import defpackage.oj2;
import defpackage.rh3;
import defpackage.xz;
import defpackage.ys;
import defpackage.z7;
import defpackage.zi0;
import defpackage.zt1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.content_relationship_verification.OriginVerifier;
import org.chromium.content_public.browser.BrowserContextHandle;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes.dex */
public class ChromeOriginVerifier extends OriginVerifier {
    public final zi0 i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChromeOriginVerifier(String str, int i, WebContents webContents, zi0 zi0Var) {
        super(str, i != 1 ? i != 2 ? null : "delegate_permission/common.handle_all_urls" : "delegate_permission/common.use_as_origin", webContents);
        ys ysVar = ys.b;
        this.i = zi0Var;
    }

    @CalledByNative
    public static void clearBrowsingData() {
        ys ysVar = ys.b;
        Object obj = ThreadUtils.a;
        SharedPreferencesManager.getInstance().p("verified_digital_asset_links", Collections.emptySet());
        gl3.a.clear();
    }

    public static boolean h(String str, ft1 ft1Var) {
        String strJoin;
        xz.a.getPackageManager();
        ArrayList arrayListB = zt1.b(str);
        String str2 = arrayListB == null ? null : (String) arrayListB.get(0);
        ys ysVar = ys.b;
        if (!gl3.b(str, "delegate_permission/common.use_as_origin", ft1Var)) {
            List listAsList = Arrays.asList(str2);
            HashSet hashSetA = ysVar.a();
            if (listAsList != null) {
                Collections.sort(listAsList);
                strJoin = String.join(",", listAsList);
            } else {
                strJoin = "";
            }
            if (!hashSetA.contains(str + "," + ft1Var + ",delegate_permission/common.use_as_origin," + strJoin)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.chromium.components.content_relationship_verification.OriginVerifier
    public final void b(OriginVerifier.c cVar) {
        int iOrdinal = cVar.ordinal();
        if (iOrdinal == 0) {
            z7.c0(0);
            return;
        }
        if (iOrdinal == 1) {
            z7.c0(1);
            return;
        }
        if (iOrdinal == 2) {
            z7.c0(2);
            return;
        }
        if (iOrdinal == 3) {
            z7.c0(3);
        } else if (iOrdinal == 4) {
            z7.c0(4);
        } else {
            if (iOrdinal != 5) {
                return;
            }
            z7.c0(5);
        }
    }

    @Override // org.chromium.components.content_relationship_verification.OriginVerifier
    public final void c(long j, boolean z) {
        if (z) {
            oj2.b0(j, "BrowserServices.VerificationTime.Online");
        } else {
            oj2.b0(j, "BrowserServices.VerificationTime.Offline");
        }
    }

    public final void d(BrowserContextHandle browserContextHandle) {
        this.e = N.MH2fr4nH(this, browserContextHandle);
    }

    public final void e(String str) {
        if (this.i == null) {
            return;
        }
        str.equals("delegate_permission/common.handle_all_urls");
    }

    public final void f(OriginVerifier.a aVar, ft1 ft1Var) {
        Object obj = ThreadUtils.a;
        if (!(this.e != 0)) {
            d(Profile.d());
        }
        HashMap map = this.d;
        if (map.containsKey(ft1Var)) {
            ((Set) map.get(ft1Var)).add(aVar);
            return;
        }
        map.put(ft1Var, new HashSet());
        ((Set) map.get(ft1Var)).add(aVar);
        String strF = iv.e().f("disable-digital-asset-link-verification-for-url");
        if (!TextUtils.isEmpty(strF) && ft1Var.equals(ft1.b(strF))) {
            fa1.g("ChromeOriginVerifier", "Verification skipped for %s due to command line flag.", ft1Var);
            PostTask.d(rh3.a, new OriginVerifier.b(this, ft1Var, true, null));
            return;
        }
        String scheme = ft1Var.a.getScheme();
        String host = ft1Var.a.getHost();
        if (!TextUtils.isEmpty(scheme)) {
            Locale locale = Locale.US;
            if (!"http".equals(scheme.toLowerCase(locale)) || "localhost".equals(host.toLowerCase(locale))) {
                this.g.getClass();
                String str = this.a;
                String str2 = this.c;
                if (gl3.b(str, str2, ft1Var)) {
                    fa1.g("OriginVerifier", "Verification succeeded for %s, it was overridden.", ft1Var);
                    PostTask.d(rh3.a, new OriginVerifier.b(this, ft1Var, true, null));
                    return;
                }
                e(str2);
                WebContents webContents = this.h;
                if (webContents != null && webContents.isDestroyed()) {
                    this.h = null;
                }
                this.f = SystemClock.uptimeMillis();
                ArrayList arrayList = this.b;
                if (N.MjORErrK(this.e, this, this.a, arrayList != null ? (String[]) arrayList.toArray(new String[0]) : null, ft1Var.toString(), this.c, this.h)) {
                    return;
                }
                b(OriginVerifier.c.REQUEST_FAILURE);
                PostTask.d(rh3.a, new OriginVerifier.b(this, ft1Var, false, Boolean.FALSE));
                return;
            }
        }
        fa1.g("OriginVerifier", "Verification failed for %s as not https or localhost.", ft1Var);
        b(OriginVerifier.c.HTTPS_FAILURE);
        PostTask.d(rh3.a, new OriginVerifier.b(this, ft1Var, false, null));
    }

    public final boolean g(ft1 ft1Var) {
        String strJoin;
        ArrayList arrayList = this.b;
        ys ysVar = ys.b;
        String str = this.a;
        String str2 = this.c;
        if (!gl3.b(str, str2, ft1Var)) {
            HashSet hashSetA = ysVar.a();
            if (arrayList != null) {
                Collections.sort(arrayList);
                strJoin = String.join(",", arrayList);
            } else {
                strJoin = "";
            }
            if (!hashSetA.contains(str + "," + ft1Var + "," + str2 + "," + strJoin)) {
                return false;
            }
        }
        return true;
    }
}
