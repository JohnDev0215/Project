package org.chromium.components.content_relationship_verification;

import J.N;
import android.os.SystemClock;
import android.util.Log;
import defpackage.ft1;
import defpackage.gl3;
import defpackage.ku2;
import defpackage.la2;
import defpackage.xz;
import defpackage.ys;
import defpackage.zt1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.browserservices.verification.ChromeOriginVerifier;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes2.dex */
public abstract class OriginVerifier {
    public final String a;
    public final ArrayList b;
    public final String c;
    public final HashMap d;
    public long e;
    public long f;
    public final gl3 g;
    public WebContents h;

    public interface a {
        void a(String str, ft1 ft1Var, boolean z, Boolean bool);
    }

    public class b implements Runnable {
        public final ft1 e;
        public final boolean f;
        public final Boolean g;
        public final /* synthetic */ OriginVerifier h;

        public b(ChromeOriginVerifier chromeOriginVerifier, ft1 ft1Var, boolean z, Boolean bool) {
            this.h = chromeOriginVerifier;
            this.e = ft1Var;
            this.f = z;
            this.g = bool;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.h.a(this.e, this.f, this.g);
        }
    }

    public enum c {
        ONLINE_SUCCESS,
        ONLINE_FAILURE,
        OFFLINE_SUCCESS,
        OFFLINE_FAILURE,
        HTTPS_FAILURE,
        REQUEST_FAILURE
    }

    public OriginVerifier(String str, String str2, WebContents webContents) {
        ys ysVar = ys.b;
        this.d = new HashMap();
        this.a = str;
        xz.a.getPackageManager();
        this.b = zt1.b(str);
        this.c = str2;
        this.h = webContents;
        this.g = ysVar;
    }

    public final void a(ft1 ft1Var, boolean z, Boolean bool) {
        String strJoin;
        ArrayList arrayList = this.b;
        String str = this.c;
        gl3 gl3Var = this.g;
        String str2 = this.a;
        if (z) {
            HashSet hashSetA = gl3Var.a();
            if (arrayList != null) {
                Collections.sort(arrayList);
                strJoin = String.join(",", arrayList);
            } else {
                strJoin = "";
            }
            hashSetA.add(str2 + "," + ft1Var + "," + str + "," + strJoin);
            SharedPreferencesManager.getInstance().p("verified_digital_asset_links", hashSetA);
        }
        la2 la2Var = new la2(str2, arrayList, ft1Var, str);
        if (z) {
            HashSet hashSetA2 = gl3Var.a();
            hashSetA2.add(la2Var.toString());
            SharedPreferencesManager.getInstance().p("verified_digital_asset_links", hashSetA2);
        } else {
            HashSet hashSetA3 = gl3Var.a();
            hashSetA3.remove(la2Var.toString());
            SharedPreferencesManager.getInstance().p("verified_digital_asset_links", hashSetA3);
        }
        HashMap map = this.d;
        if (map.containsKey(ft1Var)) {
            Iterator it = ((Set) map.get(ft1Var)).iterator();
            while (it.hasNext()) {
                ((a) it.next()).a(str2, ft1Var, z, bool);
            }
            map.remove(ft1Var);
        }
        if (bool != null) {
            c(SystemClock.uptimeMillis() - this.f, bool.booleanValue());
        }
        if (map.isEmpty()) {
            long j = this.e;
            if (j == 0) {
                return;
            }
            N.MCZNrhuL(j, this);
            this.e = 0L;
        }
    }

    public abstract void b(c cVar);

    public abstract void c(long j, boolean z);

    @CalledByNative
    public void onOriginVerificationResult(String str, int i) {
        String strJoin;
        ft1 ft1VarC = ft1.c(str);
        if (i == 0) {
            b(c.ONLINE_SUCCESS);
            a(ft1VarC, true, Boolean.TRUE);
            return;
        }
        if (i == 1) {
            b(c.ONLINE_FAILURE);
            a(ft1VarC, false, Boolean.TRUE);
            return;
        }
        if (i != 2) {
            return;
        }
        Log.i("cr_OriginVerifier", "Device is offline, checking saved verification result.");
        ku2 ku2VarT = ku2.t();
        try {
            gl3 gl3Var = this.g;
            String str2 = this.a;
            ArrayList arrayList = this.b;
            String str3 = this.c;
            HashSet hashSetA = gl3Var.a();
            if (arrayList != null) {
                Collections.sort(arrayList);
                strJoin = String.join(",", arrayList);
            } else {
                strJoin = "";
            }
            boolean zContains = hashSetA.contains(str2 + "," + ft1VarC + "," + str3 + "," + strJoin);
            ku2VarT.close();
            b(zContains ? c.OFFLINE_SUCCESS : c.OFFLINE_FAILURE);
            a(ft1VarC, zContains, Boolean.FALSE);
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }
}
