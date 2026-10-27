package com.google.firebase.installations;

import android.net.TrafficStats;
import android.util.Log;
import com.google.firebase.concurrent.b;
import com.google.firebase.installations.FirebaseInstallationsException;
import com.google.firebase.installations.local.PersistedInstallation$RegistrationStatus;
import com.google.firebase.installations.remote.InstallationResponse$ResponseCode;
import com.google.firebase.installations.remote.TokenResult$ResponseCode;
import com.spotify.connectivity.httpwebgate.WebgateAuthorizer;
import com.spotify.cosmos.cosmos.Request;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;
import p.c02;
import p.di;
import p.g31;
import p.g90;
import p.is2;
import p.jm2;
import p.ki;
import p.kk0;
import p.o01;
import p.ru0;
import p.t32;
import p.uu0;
import p.vi;
import p.w81;
import p.wu0;
import p.yy1;
import p.zv;

/* JADX INFO: loaded from: classes.dex */
public final class a implements wu0 {
    public static final Object m = new Object();
    public final ru0 a;
    public final uu0 b;
    public final g90 c;
    public final is2 d;
    public final w81 e;
    public final c02 f;
    public final Object g;
    public final ExecutorService h;
    public final Executor i;
    public String j;
    public final HashSet k;
    public final ArrayList l;

    static {
        new AtomicInteger(1);
    }

    public a(ru0 ru0Var, yy1 yy1Var, ExecutorService executorService, b bVar) {
        ru0Var.a();
        uu0 uu0Var = new uu0(ru0Var.a, yy1Var);
        g90 g90Var = new g90(ru0Var);
        is2 is2VarA = is2.a();
        w81 w81Var = new w81(new zv(2, ru0Var));
        c02 c02Var = new c02();
        this.g = new Object();
        this.k = new HashSet();
        this.l = new ArrayList();
        this.a = ru0Var;
        this.b = uu0Var;
        this.c = g90Var;
        this.d = is2VarA;
        this.e = w81Var;
        this.f = c02Var;
        this.h = executorService;
        this.i = bVar;
    }

    public final ki a(ki kiVar) throws FirebaseInstallationsException {
        int responseCode;
        vi viVarF;
        ru0 ru0Var = this.a;
        ru0Var.a();
        String str = ru0Var.c.a;
        String str2 = kiVar.a;
        ru0 ru0Var2 = this.a;
        ru0Var2.a();
        String str3 = ru0Var2.c.g;
        String str4 = kiVar.d;
        uu0 uu0Var = this.b;
        t32 t32Var = uu0Var.c;
        boolean zA = t32Var.a();
        FirebaseInstallationsException.Status status = FirebaseInstallationsException.Status.e;
        if (!zA) {
            throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", status);
        }
        URL urlA = uu0.a(String.format("projects/%s/installations/%s/authTokens:generate", str3, str2));
        for (int i = 0; i <= 1; i++) {
            TrafficStats.setThreadStatsTag(32771);
            HttpURLConnection httpURLConnectionC = uu0Var.c(urlA, str);
            try {
                try {
                    httpURLConnectionC.setRequestMethod(Request.POST);
                    httpURLConnectionC.addRequestProperty(WebgateAuthorizer.AUTHORIZATION_HEADER, "FIS_v2 " + str4);
                    httpURLConnectionC.setDoOutput(true);
                    uu0.h(httpURLConnectionC);
                    responseCode = httpURLConnectionC.getResponseCode();
                    t32Var.b(responseCode);
                } catch (Throwable th) {
                    httpURLConnectionC.disconnect();
                    TrafficStats.clearThreadStatsTag();
                    throw th;
                }
            } catch (IOException | AssertionError unused) {
            }
            if (responseCode >= 200 && responseCode < 300) {
                viVarF = uu0.f(httpURLConnectionC);
            } else {
                uu0.b(httpURLConnectionC, null, str, str3);
                if (responseCode == 401 || responseCode == 404) {
                    kk0 kk0VarA = vi.a();
                    kk0VarA.l = TokenResult$ResponseCode.k;
                    viVarF = kk0VarA.i();
                } else {
                    if (responseCode == 429) {
                        throw new FirebaseInstallationsException("Firebase servers have received too many requests from this client in a short period of time. Please try again later.", FirebaseInstallationsException.Status.k);
                    }
                    if (responseCode < 500 || responseCode >= 600) {
                        Log.e("Firebase-Installations", "Firebase Installations can not communicate with Firebase server APIs due to invalid configuration. Please update your Firebase initialization process and set valid Firebase options (API key, Project ID, Application ID) when initializing Firebase.");
                        kk0 kk0VarA2 = vi.a();
                        kk0VarA2.l = TokenResult$ResponseCode.e;
                        viVarF = kk0VarA2.i();
                    }
                    httpURLConnectionC.disconnect();
                    TrafficStats.clearThreadStatsTag();
                }
            }
            httpURLConnectionC.disconnect();
            TrafficStats.clearThreadStatsTag();
            int iOrdinal = viVarF.c.ordinal();
            if (iOrdinal == 0) {
                is2 is2Var = this.d;
                is2Var.getClass();
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                is2Var.a.getClass();
                long seconds = timeUnit.toSeconds(System.currentTimeMillis());
                jm2 jm2VarA = kiVar.a();
                jm2VarA.c = viVarF.a;
                jm2VarA.e = Long.valueOf(viVarF.b);
                jm2VarA.f = Long.valueOf(seconds);
                return jm2VarA.h();
            }
            if (iOrdinal == 1) {
                jm2 jm2VarA2 = kiVar.a();
                jm2VarA2.g = "BAD CONFIG";
                jm2VarA2.j(PersistedInstallation$RegistrationStatus.m);
                return jm2VarA2.h();
            }
            if (iOrdinal != 2) {
                throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", status);
            }
            synchronized (this) {
                this.j = null;
            }
            jm2 jm2VarA3 = kiVar.a();
            jm2VarA3.j(PersistedInstallation$RegistrationStatus.e);
            return jm2VarA3.h();
        }
        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", status);
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String b(p.ki r6) {
        /*
            r5 = this;
            p.ru0 r0 = r5.a
            r0.a()
            java.lang.String r0 = r0.b
            java.lang.String r1 = "CHIME_ANDROID_SDK"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L1e
            p.ru0 r0 = r5.a
            r0.a()
            java.lang.String r1 = "[DEFAULT]"
            java.lang.String r0 = r0.b
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L5c
        L1e:
            com.google.firebase.installations.local.PersistedInstallation$RegistrationStatus r0 = com.google.firebase.installations.local.PersistedInstallation$RegistrationStatus.c
            com.google.firebase.installations.local.PersistedInstallation$RegistrationStatus r6 = r6.b
            if (r6 != r0) goto L5c
            p.w81 r6 = r5.e
            java.lang.Object r6 = r6.get()
            p.g31 r6 = (p.g31) r6
            android.content.SharedPreferences r0 = r6.a
            monitor-enter(r0)
            android.content.SharedPreferences r1 = r6.a     // Catch: java.lang.Throwable -> L40
            monitor-enter(r1)     // Catch: java.lang.Throwable -> L40
            android.content.SharedPreferences r2 = r6.a     // Catch: java.lang.Throwable -> L57
            java.lang.String r3 = "|S|id"
            r4 = 0
            java.lang.String r2 = r2.getString(r3, r4)     // Catch: java.lang.Throwable -> L57
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L57
            if (r2 == 0) goto L42
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L40
            goto L47
        L40:
            r6 = move-exception
            goto L5a
        L42:
            java.lang.String r2 = r6.a()     // Catch: java.lang.Throwable -> L40
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L40
        L47:
            boolean r6 = android.text.TextUtils.isEmpty(r2)
            if (r6 == 0) goto L56
            p.c02 r6 = r5.f
            r6.getClass()
            java.lang.String r2 = p.c02.a()
        L56:
            return r2
        L57:
            r6 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L57
            throw r6     // Catch: java.lang.Throwable -> L40
        L5a:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L40
            throw r6
        L5c:
            p.c02 r6 = r5.f
            r6.getClass()
            java.lang.String r6 = p.c02.a()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.installations.a.b(p.ki):java.lang.String");
    }

    public final ki c(ki kiVar) throws FirebaseInstallationsException {
        int responseCode;
        di diVarE;
        String str = kiVar.a;
        String string = null;
        if (str != null && str.length() == 11) {
            g31 g31Var = (g31) this.e.get();
            synchronized (g31Var.a) {
                try {
                    String[] strArr = g31.c;
                    int i = 0;
                    while (true) {
                        if (i < 4) {
                            String str2 = strArr[i];
                            String string2 = g31Var.a.getString("|T|" + g31Var.b + "|" + str2, null);
                            if (string2 == null || string2.isEmpty()) {
                                i++;
                            } else if (string2.startsWith("{")) {
                                try {
                                    string = new JSONObject(string2).getString("token");
                                } catch (JSONException unused) {
                                }
                            } else {
                                string = string2;
                            }
                        }
                    }
                } finally {
                }
            }
        }
        uu0 uu0Var = this.b;
        ru0 ru0Var = this.a;
        ru0Var.a();
        String str3 = ru0Var.c.a;
        String str4 = kiVar.a;
        ru0 ru0Var2 = this.a;
        ru0Var2.a();
        String str5 = ru0Var2.c.g;
        ru0 ru0Var3 = this.a;
        ru0Var3.a();
        String str6 = ru0Var3.c.b;
        t32 t32Var = uu0Var.c;
        boolean zA = t32Var.a();
        FirebaseInstallationsException.Status status = FirebaseInstallationsException.Status.e;
        if (!zA) {
            throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", status);
        }
        URL urlA = uu0.a(String.format("projects/%s/installations", str5));
        for (int i2 = 0; i2 <= 1; i2++) {
            TrafficStats.setThreadStatsTag(32769);
            HttpURLConnection httpURLConnectionC = uu0Var.c(urlA, str3);
            try {
                try {
                    httpURLConnectionC.setRequestMethod(Request.POST);
                    httpURLConnectionC.setDoOutput(true);
                    if (string != null) {
                        httpURLConnectionC.addRequestProperty("x-goog-fis-android-iid-migration-auth", string);
                    }
                    uu0.g(httpURLConnectionC, str4, str6);
                    responseCode = httpURLConnectionC.getResponseCode();
                    t32Var.b(responseCode);
                } catch (IOException | AssertionError unused2) {
                }
                if (responseCode < 200 || responseCode >= 300) {
                    uu0.b(httpURLConnectionC, str6, str3, str5);
                    if (responseCode == 429) {
                        throw new FirebaseInstallationsException("Firebase servers have received too many requests from this client in a short period of time. Please try again later.", FirebaseInstallationsException.Status.k);
                    }
                    if (responseCode < 500 || responseCode >= 600) {
                        Log.e("Firebase-Installations", "Firebase Installations can not communicate with Firebase server APIs due to invalid configuration. Please update your Firebase initialization process and set valid Firebase options (API key, Project ID, Application ID) when initializing Firebase.");
                        di diVar = new di(null, null, null, null, InstallationResponse$ResponseCode.e);
                        httpURLConnectionC.disconnect();
                        TrafficStats.clearThreadStatsTag();
                        diVarE = diVar;
                    }
                    httpURLConnectionC.disconnect();
                    TrafficStats.clearThreadStatsTag();
                } else {
                    diVarE = uu0.e(httpURLConnectionC);
                    httpURLConnectionC.disconnect();
                    TrafficStats.clearThreadStatsTag();
                }
                int iOrdinal = diVarE.e.ordinal();
                if (iOrdinal != 0) {
                    if (iOrdinal != 1) {
                        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.e);
                    }
                    jm2 jm2VarA = kiVar.a();
                    jm2VarA.g = "BAD CONFIG";
                    jm2VarA.j(PersistedInstallation$RegistrationStatus.m);
                    return jm2VarA.h();
                }
                String str7 = diVarE.b;
                String str8 = diVarE.c;
                is2 is2Var = this.d;
                is2Var.getClass();
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                is2Var.a.getClass();
                long seconds = timeUnit.toSeconds(System.currentTimeMillis());
                vi viVar = diVarE.d;
                String str9 = viVar.a;
                long j = viVar.b;
                jm2 jm2VarA2 = kiVar.a();
                jm2VarA2.a = str7;
                jm2VarA2.j(PersistedInstallation$RegistrationStatus.l);
                jm2VarA2.c = str9;
                jm2VarA2.d = str8;
                jm2VarA2.e = Long.valueOf(j);
                jm2VarA2.f = Long.valueOf(seconds);
                return jm2VarA2.h();
            } finally {
                httpURLConnectionC.disconnect();
                TrafficStats.clearThreadStatsTag();
            }
        }
        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", status);
    }

    public final void d() {
        synchronized (this.g) {
            try {
                Iterator it = this.l.iterator();
                while (it.hasNext()) {
                    ((o01) it.next()).getClass();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void e(ki kiVar) {
        synchronized (this.g) {
            try {
                Iterator it = this.l.iterator();
                while (it.hasNext()) {
                    o01 o01Var = (o01) it.next();
                    o01Var.getClass();
                    PersistedInstallation$RegistrationStatus persistedInstallation$RegistrationStatus = PersistedInstallation$RegistrationStatus.k;
                    PersistedInstallation$RegistrationStatus persistedInstallation$RegistrationStatus2 = kiVar.b;
                    if (persistedInstallation$RegistrationStatus2 == persistedInstallation$RegistrationStatus || persistedInstallation$RegistrationStatus2 == PersistedInstallation$RegistrationStatus.l || persistedInstallation$RegistrationStatus2 == PersistedInstallation$RegistrationStatus.m) {
                        o01Var.a.b(kiVar.a);
                        it.remove();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
