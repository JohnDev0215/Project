package org.chromium.chrome.browser.customtabs;

import J.N;
import android.content.ComponentName;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.browser.customtabs.CustomTabsSessionToken;
import defpackage.b50;
import defpackage.bj2;
import defpackage.bq;
import defpackage.d73;
import defpackage.dj2;
import defpackage.ej2;
import defpackage.fa1;
import defpackage.g90;
import defpackage.gg3;
import defpackage.iv;
import defpackage.jb2;
import defpackage.l10;
import defpackage.n3;
import defpackage.nw2;
import defpackage.oj2;
import defpackage.pp;
import defpackage.pq3;
import defpackage.q11;
import defpackage.qe3;
import defpackage.qj;
import defpackage.rh3;
import defpackage.s50;
import defpackage.sk;
import defpackage.wz;
import defpackage.xm;
import defpackage.yj1;
import defpackage.zo3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.Callback;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.customtabs.a;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content_public.browser.WebContents;
import org.json.JSONException;
import org.json.JSONObject;
import zo3.a;

/* JADX INFO: loaded from: classes.dex */
public class CustomTabsConnection {
    public static final HashSet o = new HashSet(Arrays.asList("/bg_non_interactive", "/apps/bg_non_interactive", "/background"));
    public static final yj1 p = new yj1("CCTRealTimeEngagementSignals", false);
    public static final String[] q = {"No request", "Success", "Chrome not initialized", "Not authorized", "Invalid URL", "Invalid referrer", "Invalid referrer for session"};
    public static CustomTabsConnection r;
    public Callback<CustomTabsSessionToken> g;
    public nw2<Integer> h;
    public ej2 i;
    public volatile xm j;
    public int k;
    public ArrayList m;
    public ArrayList n;
    public final n3 a = new n3();
    public final AtomicBoolean e = new AtomicBoolean();
    public final AtomicBoolean f = new AtomicBoolean();
    public final boolean l = bq.k.a();
    public final a c = new a();
    public final boolean d = iv.e().g("custom-tabs-log-service-requests");
    public final bj2 b = ((s50) pp.d()).i();

    public static JSONObject a(Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        if (bundle == null) {
            return jSONObject;
        }
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            try {
                if (obj instanceof Bundle) {
                    jSONObject.put(str, a((Bundle) obj));
                } else if ((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Boolean)) {
                    jSONObject.put(str, obj);
                } else if (obj == null) {
                    jSONObject.put(str, JSONObject.NULL);
                } else {
                    jSONObject.put(str, obj.toString());
                }
            } catch (JSONException unused) {
            }
        }
        return jSONObject;
    }

    public static void b() {
        if (SysUtils.isLowEndDevice()) {
            return;
        }
        zo3 zo3VarA = zo3.a();
        zo3VarA.getClass();
        TraceEvent traceEventH0 = TraceEvent.H0("WarmupManager.createSpareWebContents", null);
        try {
            Object obj = ThreadUtils.a;
            if (org.chromium.base.library_loader.a.l.d() && zo3VarA.e == null) {
                new pq3();
                zo3VarA.e = (WebContents) N.MDMZjIJS(Profile.d(), true, true, new pq3.a());
                zo3.a aVar = zo3VarA.new a();
                zo3VarA.f = aVar;
                zo3VarA.e.C(aVar);
                if (traceEventH0 == null) {
                    return;
                }
            } else if (traceEventH0 == null) {
                return;
            }
            traceEventH0.close();
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    public static CustomTabsConnection e() {
        if (r == null) {
            AppHooks.get().getClass();
            r = new CustomTabsConnection();
        }
        return r;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0072, code lost:
    
        r0 = r0[2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0075, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0078, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean h() {
        /*
            int r0 = android.os.Binder.getCallingUid()
            int r1 = android.os.Process.myUid()
            r2 = 1
            if (r0 != r1) goto Lc
            return r2
        Lc:
            int r0 = android.os.Binder.getCallingPid()
            java.lang.String r1 = "/proc/"
            java.lang.String r3 = defpackage.gq2.e(r1, r0)
            java.io.File r4 = new java.io.File
            r4.<init>(r3)
            boolean r3 = r4.exists()
            if (r3 == 0) goto L2f
            boolean r3 = r4.isDirectory()
            if (r3 == 0) goto L2f
            boolean r3 = r4.canExecute()
            if (r3 == 0) goto L2f
            r3 = r2
            goto L30
        L2f:
            r3 = 0
        L30:
            if (r3 != 0) goto L33
            return r2
        L33:
            java.util.HashSet r3 = org.chromium.chrome.browser.customtabs.CustomTabsConnection.o
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r1)
            r4.append(r0)
            java.lang.String r0 = "/cgroup"
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            java.lang.String r1 = "cpuset"
            ku2 r4 = defpackage.ku2.t()     // Catch: java.io.IOException -> L8d
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L88
            java.io.FileReader r6 = new java.io.FileReader     // Catch: java.lang.Throwable -> L88
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L88
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L88
        L56:
            java.lang.String r0 = r5.readLine()     // Catch: java.lang.Throwable -> L83
            if (r0 == 0) goto L7c
            java.lang.String r0 = r0.trim()     // Catch: java.lang.Throwable -> L83
            java.lang.String r6 = ":"
            java.lang.String[] r0 = r0.split(r6)     // Catch: java.lang.Throwable -> L83
            int r6 = r0.length     // Catch: java.lang.Throwable -> L83
            r7 = 3
            if (r6 != r7) goto L56
            r6 = r0[r2]     // Catch: java.lang.Throwable -> L83
            boolean r6 = r6.equals(r1)     // Catch: java.lang.Throwable -> L83
            if (r6 == 0) goto L56
            r1 = 2
            r0 = r0[r1]     // Catch: java.lang.Throwable -> L83
            r5.close()     // Catch: java.lang.Throwable -> L88
            r4.close()     // Catch: java.io.IOException -> L8d
            goto L8e
        L7c:
            r5.close()     // Catch: java.lang.Throwable -> L88
            r4.close()     // Catch: java.io.IOException -> L8d
            goto L8d
        L83:
            r0 = move-exception
            r5.close()     // Catch: java.lang.Throwable -> L87
        L87:
            throw r0     // Catch: java.lang.Throwable -> L88
        L88:
            r0 = move-exception
            r4.close()     // Catch: java.lang.Throwable -> L8c
        L8c:
            throw r0     // Catch: java.io.IOException -> L8d
        L8d:
            r0 = 0
        L8e:
            boolean r0 = r3.contains(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.customtabs.CustomTabsConnection.h():boolean");
    }

    public static boolean j(Uri uri) {
        if (uri == null) {
            return false;
        }
        String scheme = uri.normalizeScheme().getScheme();
        return scheme == null || scheme.equals("http") || scheme.equals("https");
    }

    @CalledByNative
    public static void notifyClientOfDetachedRequestCompletion(CustomTabsSessionToken customTabsSessionToken, String str, int i) {
        sk skVar = bq.a;
        if (N.M09VlOh_("CCTReportParallelRequestStatus")) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("url", Uri.parse(str));
            bundle.putInt("net_error", i);
            CustomTabsConnection customTabsConnectionE = e();
            customTabsConnectionE.r(customTabsSessionToken, "onDetachedRequestCompleted", bundle);
            if (customTabsConnectionE.d) {
                customTabsConnectionE.l(a(bundle).toString(), "onDetachedRequestCompleted");
            }
        }
    }

    public static boolean q(List list) {
        boolean z = false;
        if (list == null) {
            return false;
        }
        zo3 zo3VarA = zo3.a();
        Profile profileD = Profile.d();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            try {
                Uri uri = (Uri) q11.m((Bundle) it.next(), "android.support.customtabs.otherurls.URL");
                if (j(uri)) {
                    zo3VarA.c(uri.toString(), profileD);
                    z = true;
                }
            } catch (ClassCastException unused) {
            }
        }
        return z;
    }

    public final void c(final boolean z, final CustomTabsSessionToken customTabsSessionToken, final int i, final String str, final Bundle bundle, final List<Bundle> list, boolean z2) {
        Object obj = ThreadUtils.a;
        int[] intArray = null;
        TraceEvent traceEventH0 = TraceEvent.H0("CustomTabsConnection.mayLaunchUrlOnUiThread", null);
        try {
            if (!qj.a().g()) {
                if (z2) {
                    PostTask.c(rh3.a, new Runnable() { // from class: c50
                        @Override // java.lang.Runnable
                        public final void run() {
                            CustomTabsConnection customTabsConnection = this.e;
                            boolean z3 = z;
                            CustomTabsSessionToken customTabsSessionToken2 = customTabsSessionToken;
                            int i2 = i;
                            String str2 = str;
                            Bundle bundle2 = bundle;
                            List<Bundle> list2 = list;
                            HashSet hashSet = CustomTabsConnection.o;
                            customTabsConnection.c(z3, customTabsSessionToken2, i2, str2, bundle2, list2, false);
                        }
                    });
                }
                if (traceEventH0 != null) {
                    traceEventH0.close();
                    return;
                }
                return;
            }
            if (bundle != null) {
                ComponentName componentName = q11.a;
                try {
                    intArray = bundle.getIntArray("org.chromium.chrome.browser.customtabs.AGA_EXPERIMENT_IDS");
                } catch (Throwable unused) {
                    Log.e("cr_IntentUtils", "getIntArray failed on bundle " + bundle);
                }
                if (intArray != null) {
                    N.MwmPuE$v("GsaExperiments", intArray, false);
                }
            }
            if (!z) {
                g(customTabsSessionToken, str, bundle, list);
            } else if (q(list)) {
                b();
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused2) {
                }
            }
            throw th;
        }
    }

    public final String d(CustomTabsSessionToken customTabsSessionToken) {
        return this.c.c(customTabsSessionToken);
    }

    public final dj2 f() {
        if (!bq.q.a()) {
            return null;
        }
        if (this.i == null) {
            this.i = new ej2();
        }
        return this.i;
    }

    public final void g(CustomTabsSessionToken customTabsSessionToken, String str, Bundle bundle, List list) {
        int i;
        Object obj = ThreadUtils.a;
        if (TextUtils.isEmpty(str)) {
            this.a.getClass();
            return;
        }
        if (!g90.c().d) {
            i = 5;
        } else if (oj2.k(Profile.d()).b("profile.cookie_controls_mode") == 1) {
            i = 6;
        } else if (N.MaV3tKHW() == 0) {
            i = 7;
        } else {
            i = 0;
        }
        oj2.U(i, 10, "CustomTabs.SpeculationStatusOnStart");
        if (i == 0) {
            a aVar = this.c;
            synchronized (aVar) {
                if (((a.e) aVar.c.get(customTabsSessionToken)) == null) {
                }
            }
            zo3 zo3VarA = zo3.a();
            Profile profileD = Profile.d();
            this.a.getClass();
            b();
            zo3VarA.c(str, profileD);
        }
        q(list);
    }

    public final boolean i(String str) {
        if (this.l) {
            ArrayList arrayList = this.m;
            if (arrayList != null && arrayList.contains(str)) {
                return true;
            }
            ArrayList arrayList2 = this.n;
            if (arrayList2 != null && arrayList2.contains(str)) {
                return false;
            }
        }
        if (str.equals("CCTBrandTransparency")) {
            return bq.g.a();
        }
        if (str.equals("CCTRealTimeEngagementSignals")) {
            return p.a();
        }
        Log.e("cr_ChromeConnection", "Unsupported Feature!");
        return false;
    }

    public final void k(Object obj, String str) {
        if (this.d) {
            fa1.m("ChromeConnection", "%s = %b, Calling UID = %d", str, obj, Integer.valueOf(Binder.getCallingUid()));
        }
    }

    public final void l(Object obj, String str) {
        if (this.d) {
            fa1.l("ChromeConnection", "%s args = %s", str, obj);
        }
    }

    public final boolean m(final CustomTabsSessionToken customTabsSessionToken, Uri uri, final Bundle bundle, final List<Bundle> list) {
        boolean z;
        if (org.chromium.chrome.browser.b.q(bundle)) {
            return false;
        }
        final boolean z2 = (uri == null || TextUtils.isEmpty(uri.toString())) && list != null;
        final String string = j(uri) ? uri.toString() : null;
        if (uri != null && string == null && !z2) {
            return false;
        }
        final int callingUid = Binder.getCallingUid();
        if (!v(false)) {
            return false;
        }
        a aVar = this.c;
        boolean z3 = list != null;
        synchronized (aVar) {
            a.e eVar = (a.e) aVar.c.get(customTabsSessionToken);
            if (eVar == null || eVar.a != callingUid) {
                z = false;
            } else {
                boolean z4 = TextUtils.isEmpty(string) && z3 && !eVar.h;
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                eVar.m = string;
                eVar.n = jElapsedRealtime;
                eVar.i |= !TextUtils.isEmpty(string);
                eVar.h = z3 | eVar.h;
                if (z4) {
                    z = true;
                } else {
                    jb2 jb2VarA = jb2.a(callingUid);
                    long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                    long j = jElapsedRealtime2 - jb2VarA.a;
                    long j2 = jb2VarA.b;
                    if (j < j2) {
                        z = false;
                    } else {
                        jb2VarA.a = jElapsedRealtime2;
                        long j3 = j2 * 2;
                        if (j < j3) {
                            jb2VarA.b = Math.min(10000L, j3);
                        } else {
                            jb2VarA.b = 100L;
                        }
                        z = true;
                    }
                }
            }
        }
        if (!z) {
            return false;
        }
        PostTask.c(rh3.a, new Runnable() { // from class: a50
            @Override // java.lang.Runnable
            public final void run() {
                CustomTabsConnection customTabsConnection = this.e;
                boolean z5 = z2;
                CustomTabsSessionToken customTabsSessionToken2 = customTabsSessionToken;
                int i = callingUid;
                String str = string;
                Bundle bundle2 = bundle;
                List<Bundle> list2 = list;
                HashSet hashSet = CustomTabsConnection.o;
                customTabsConnection.c(z5, customTabsSessionToken2, i, str, bundle2, list2, true);
            }
        });
        return true;
    }

    public final void n(CustomTabsSessionToken customTabsSessionToken, int i) {
        n3 n3VarB = this.c.b(customTabsSessionToken);
        if (n3VarB == null) {
            return;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putLong("timestampUptimeMillis", SystemClock.uptimeMillis());
            n3VarB.T0(i, bundle);
            l(Integer.valueOf(i), "onNavigationEvent()");
        } catch (Exception unused) {
        }
    }

    public final boolean o(CustomTabsSessionToken customTabsSessionToken, Bundle bundle) {
        a aVar = this.c;
        synchronized (aVar) {
            return ((a.e) aVar.c.get(customTabsSessionToken)) == null ? false : false;
        }
    }

    public final void p(CustomTabsSessionToken customTabsSessionToken, int i, int i2) {
        Bundle bundle = new Bundle();
        if (i != this.k) {
            bundle.putInt("size", i);
            if (r(customTabsSessionToken, "onResized", bundle) && this.d) {
                l(bundle, "extraCallback(onResized)");
            }
            this.k = i;
        }
        n3 n3VarB = this.c.b(customTabsSessionToken);
        if (n3VarB == null) {
            return;
        }
        try {
            n3VarB.R0(i, i2, bundle);
            l("(" + i + "x" + i2 + ")", "onActivityResized()");
        } catch (Exception unused) {
        }
    }

    public final boolean r(CustomTabsSessionToken customTabsSessionToken, String str, Bundle bundle) {
        n3 n3VarB = this.c.b(customTabsSessionToken);
        if (n3VarB == null) {
            return false;
        }
        try {
            TraceEvent traceEventH0 = TraceEvent.H0("CustomTabsConnection::safeExtraCallback", str);
            try {
                n3VarB.L(str, bundle);
                if (traceEventH0 == null) {
                    return true;
                }
                traceEventH0.close();
                return true;
            } catch (Throwable th) {
                if (traceEventH0 != null) {
                    try {
                        traceEventH0.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        } catch (Exception unused2) {
            return false;
        }
    }

    public final Bundle s(CustomTabsSessionToken customTabsSessionToken, String str, Bundle bundle) {
        n3 n3VarB = this.c.b(customTabsSessionToken);
        if (n3VarB == null) {
            return null;
        }
        try {
            TraceEvent traceEventH0 = TraceEvent.H0("CustomTabsConnection::safeExtraCallbackWithResult", str);
            try {
                Bundle bundleM = n3VarB.M(str, bundle);
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return bundleM;
            } catch (Throwable th) {
                if (traceEventH0 != null) {
                    try {
                        traceEventH0.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    public final boolean t(CustomTabsSessionToken customTabsSessionToken) {
        a aVar = this.c;
        Boolean boolValueOf = Boolean.FALSE;
        synchronized (aVar) {
            a.e eVar = (a.e) aVar.c.get(customTabsSessionToken);
            if (eVar != null) {
                boolValueOf = Boolean.valueOf(eVar.k);
            }
        }
        return boolValueOf.booleanValue();
    }

    public final boolean u() {
        TraceEvent traceEventH0 = TraceEvent.H0("CustomTabsConnection.warmup", null);
        try {
            boolean zV = v(true);
            k(Boolean.valueOf(zV), "warmup()");
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return zV;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    public final boolean v(boolean z) {
        int i;
        int i2 = 0;
        if (!h()) {
            return false;
        }
        int callingUid = Binder.getCallingUid();
        a aVar = this.c;
        synchronized (aVar) {
            i = 1;
            aVar.e = true;
            aVar.d.put(callingUid, true);
        }
        boolean z2 = !this.e.compareAndSet(false, true);
        xm xmVar = new xm();
        if (!z2) {
            xmVar.a(rh3.a, new l10(2, this));
        }
        if (z) {
            this.a.getClass();
            xmVar.a(rh3.a, new qe3(6));
        }
        d73 d73Var = rh3.a;
        xmVar.a(d73Var, new wz(7));
        if (!z2) {
            xmVar.a(d73Var, new gg3(i));
        }
        xmVar.a(d73Var, new b50(callingUid, i2, this));
        xmVar.b(false);
        this.j = xmVar;
        return true;
    }
}
