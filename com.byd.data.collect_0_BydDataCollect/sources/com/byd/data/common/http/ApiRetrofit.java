package com.byd.data.common.http;

import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.os.SystemProperties;
import android.util.SparseArray;
import com.byd.data.common.entiry.cloud.CountryCode;
import com.byd.data.common.entiry.cloud.config.CountryBean;
import com.byd.data.common.http.c.c;
import com.byd.data.common.i.f;
import f.b0;
import f.l0.a;
import h.u;
import h.z.a.h;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class ApiRetrofit {

    /* JADX INFO: renamed from: a */
    private static volatile ApiRetrofit f3403a;

    /* JADX INFO: renamed from: b */
    private final String f3404b;

    /* JADX INFO: renamed from: c */
    private final byte f3405c;

    /* JADX INFO: renamed from: d */
    private byte f3406d;

    /* JADX INFO: renamed from: e */
    public b0 f3407e;

    /* JADX INFO: renamed from: f */
    private SparseArray<com.byd.data.common.http.c.a> f3408f;

    /* JADX INFO: renamed from: g */
    private CountryBean f3409g;

    class a extends com.google.gson.v.a<CountryCode> {
        a() {
        }
    }

    private ApiRetrofit() {
        String str = "ApiRetrofit" + com.byd.data.common.i.b.f3423a;
        this.f3404b = str;
        this.f3405c = (byte) 10;
        this.f3408f = new SparseArray<>(3);
        this.f3406d = (byte) BYDAutoTestDevice.getInstance(com.byd.data.common.g.a.i().d()).getCloudServieEv();
        f.g(str, "construct testEv = " + ((int) this.f3406d));
    }

    private int b(short[] sArr, int i2) {
        int length = sArr.length - 1;
        int i3 = 0;
        while (i3 <= length) {
            int i4 = (i3 + length) >>> 1;
            short s = sArr[i4];
            if (s < i2) {
                i3 = i4 + 1;
            } else {
                if (s <= i2) {
                    return i4;
                }
                length = i4 - 1;
            }
        }
        return -1;
    }

    private String c(byte b2) {
        CountryBean countryBean = this.f3409g;
        if (countryBean == null || l()) {
            return "http://noFound/";
        }
        String strA = countryBean.a();
        StringBuffer stringBuffer = new StringBuffer("https://");
        stringBuffer.append(strA);
        stringBuffer.append("-");
        boolean zK = k();
        if (zK) {
            stringBuffer.append("test");
        }
        stringBuffer.append(b2 == 1 ? "newcan" : "bms");
        stringBuffer.append("-data.byd.auto");
        stringBuffer.append((zK || b2 != 2) ? "/" : ":446/");
        f.b(this.f3404b, "area =" + strA + ", url =" + ((Object) stringBuffer));
        return stringBuffer.toString();
    }

    private String e(byte b2) {
        return (b2 == 1 || b2 == 2) ? c(b2) : b2 != 3 ? "" : "https://www.energy-link.com.cn/";
    }

    public static ApiRetrofit h() {
        if (f3403a == null) {
            synchronized (ApiRetrofit.class) {
                if (f3403a == null) {
                    f3403a = new ApiRetrofit();
                    f3403a.i();
                }
            }
        }
        return f3403a;
    }

    private CountryBean i() {
        if (k()) {
            return g();
        }
        com.byd.data.common.server.b bVarE = com.byd.data.common.server.b.e();
        short s = (short) SystemProperties.getInt("persist.vendor.byd.countrycode", 30);
        int iZ = com.byd.data.common.g.a.i().j().z();
        m(bVarE, s, iZ);
        f.g(this.f3404b, "getReqUrl tBox mccMnc =" + iZ + ",countryCode =" + ((int) s));
        if (this.f3409g == null) {
            this.f3409g = new CountryBean(iZ, "http://noFound/", s);
            f.g(this.f3404b, "no found fill null domain name");
        }
        return this.f3409g;
    }

    private com.byd.data.common.http.c.a j(byte b2) {
        b0.b bVar = new b0.b();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        bVar.d(10L, timeUnit).h(10L, timeUnit).f(10L, timeUnit).g(true);
        f.l0.a aVar = new f.l0.a(new com.byd.data.common.http.a());
        aVar.d((k() || f.f3426a) ? a.EnumC0102a.BODY : a.EnumC0102a.BASIC);
        bVar.b(aVar);
        bVar.b(new com.byd.data.common.http.d.b());
        bVar.a(new com.byd.data.common.http.d.a(b2));
        bVar.e(new com.byd.data.common.b.e.a());
        this.f3407e = bVar.c();
        com.byd.data.common.http.c.a aVar2 = (com.byd.data.common.http.c.a) new u.b().c(e(b2)).b(com.byd.data.common.b.d.a.f(com.byd.data.common.server.b.e().b())).a(h.d()).g(this.f3407e).e().b(b2 == 1 ? com.byd.data.common.http.c.a.class : b2 == 2 ? com.byd.data.common.http.c.b.class : c.class);
        this.f3408f.put(b2, aVar2);
        return aVar2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x007f, code lost:
    
        r2.d(r9);
        r7.f3409g = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m(com.byd.data.common.server.b r8, short r9, int r10) {
        /*
            r7 = this;
            java.io.File r10 = new java.io.File     // Catch: org.json.JSONException -> L98 java.io.IOException -> L9a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: org.json.JSONException -> L98 java.io.IOException -> L9a
            r0.<init>()     // Catch: org.json.JSONException -> L98 java.io.IOException -> L9a
            java.util.Objects.requireNonNull(r8)     // Catch: org.json.JSONException -> L98 java.io.IOException -> L9a
            java.lang.String r8 = "/vendor/etc/byd/config/"
            r0.append(r8)     // Catch: org.json.JSONException -> L98 java.io.IOException -> L9a
            java.lang.String r8 = "cloud/CountryArea.conf"
            r0.append(r8)     // Catch: org.json.JSONException -> L98 java.io.IOException -> L9a
            java.lang.String r8 = r0.toString()     // Catch: org.json.JSONException -> L98 java.io.IOException -> L9a
            r10.<init>(r8)     // Catch: org.json.JSONException -> L98 java.io.IOException -> L9a
            okio.Source r8 = okio.Okio.i(r10)     // Catch: org.json.JSONException -> L98 java.io.IOException -> L9a
            okio.BufferedSource r8 = okio.Okio.c(r8)     // Catch: org.json.JSONException -> L98 java.io.IOException -> L9a
            java.lang.String r10 = r8.O()     // Catch: java.lang.Throwable -> L8c
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L8c
            r0.<init>(r10)     // Catch: java.lang.Throwable -> L8c
            com.google.gson.e r10 = new com.google.gson.e     // Catch: java.lang.Throwable -> L8c
            r10.<init>()     // Catch: java.lang.Throwable -> L8c
            com.google.gson.Gson r10 = r10.b()     // Catch: java.lang.Throwable -> L8c
            java.lang.String r1 = "DataStructure"
            java.lang.String r0 = r0.optString(r1)     // Catch: java.lang.Throwable -> L8c
            com.byd.data.common.http.ApiRetrofit$a r1 = new com.byd.data.common.http.ApiRetrofit$a     // Catch: java.lang.Throwable -> L8c
            r1.<init>()     // Catch: java.lang.Throwable -> L8c
            java.lang.reflect.Type r1 = r1.e()     // Catch: java.lang.Throwable -> L8c
            java.lang.Object r10 = r10.j(r0, r1)     // Catch: java.lang.Throwable -> L8c
            com.byd.data.common.entiry.cloud.CountryCode r10 = (com.byd.data.common.entiry.cloud.CountryCode) r10     // Catch: java.lang.Throwable -> L8c
            r0 = 0
            r1 = r0
        L4c:
            com.byd.data.common.entiry.cloud.config.CountryBean[] r2 = r10.mccMncList     // Catch: java.lang.Throwable -> L8c
            int r3 = r2.length     // Catch: java.lang.Throwable -> L8c
            if (r1 >= r3) goto L88
            r2 = r2[r1]     // Catch: java.lang.Throwable -> L8c
            if (r2 != 0) goto L56
            goto L85
        L56:
            short[] r3 = r2.b()     // Catch: java.lang.Throwable -> L8c
            if (r3 != 0) goto L5e
            r3 = r0
            goto L62
        L5e:
            int r3 = r7.b(r3, r9)     // Catch: java.lang.Throwable -> L8c
        L62:
            java.lang.String r4 = r7.f3404b     // Catch: java.lang.Throwable -> L8c
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8c
            r5.<init>()     // Catch: java.lang.Throwable -> L8c
            r5.append(r3)     // Catch: java.lang.Throwable -> L8c
            java.lang.String r6 = ",readUrlConfig version="
            r5.append(r6)     // Catch: java.lang.Throwable -> L8c
            int r6 = r10.version     // Catch: java.lang.Throwable -> L8c
            r5.append(r6)     // Catch: java.lang.Throwable -> L8c
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L8c
            com.byd.data.common.i.f.g(r4, r5)     // Catch: java.lang.Throwable -> L8c
            if (r3 < 0) goto L85
            r2.d(r9)     // Catch: java.lang.Throwable -> L8c
            r7.f3409g = r2     // Catch: java.lang.Throwable -> L8c
            goto L88
        L85:
            int r1 = r1 + 1
            goto L4c
        L88:
            r8.close()     // Catch: org.json.JSONException -> L98 java.io.IOException -> L9a
            goto L9e
        L8c:
            r9 = move-exception
            if (r8 == 0) goto L97
            r8.close()     // Catch: java.lang.Throwable -> L93
            goto L97
        L93:
            r8 = move-exception
            r9.addSuppressed(r8)     // Catch: org.json.JSONException -> L98 java.io.IOException -> L9a
        L97:
            throw r9     // Catch: org.json.JSONException -> L98 java.io.IOException -> L9a
        L98:
            r8 = move-exception
            goto L9b
        L9a:
            r8 = move-exception
        L9b:
            r8.printStackTrace()
        L9e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.data.common.http.ApiRetrofit.m(com.byd.data.common.server.b, short, int):void");
    }

    public void a() {
        this.f3408f.clear();
        f3403a = null;
    }

    public com.byd.data.common.http.c.a d() {
        com.byd.data.common.http.c.a aVar = this.f3408f.get(1);
        return aVar == null ? j((byte) 1) : aVar;
    }

    public synchronized com.byd.data.common.http.c.b f() {
        com.byd.data.common.http.c.a aVar = this.f3408f.get(2);
        if (aVar == null) {
            return (com.byd.data.common.http.c.b) j((byte) 2);
        }
        return (com.byd.data.common.http.c.b) aVar;
    }

    public CountryBean g() {
        CountryBean countryBean = this.f3409g;
        if (countryBean != null) {
            return countryBean;
        }
        this.f3409g = new CountryBean(90131, k() ? "https://eu-testnewcan-data.byd.auto/" : "https://eu-newcan-data.byd.auto/", (short) 31);
        f.b(this.f3404b, "cloud service url = " + this.f3409g.url);
        return this.f3409g;
    }

    public boolean k() {
        return this.f3406d == 1;
    }

    public boolean l() {
        CountryBean countryBean = this.f3409g;
        if (countryBean == null) {
            return true;
        }
        return countryBean.url.equals("http://noFound/");
    }
}
