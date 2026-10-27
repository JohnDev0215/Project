package com.qtxa.qtxadefend;

import android.content.Context;
import com.qtxa.report.JniQtxaManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private JSONObject f151a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<String> f152b;
    private JniQtxaManager.RuleCallback c;

    class a implements JniQtxaManager.RuleCallback {
        a() {
        }

        @Override // com.qtxa.report.JniQtxaManager.RuleCallback
        public void callback(int i, byte[] bArr) throws Throwable {
            w.d("get systemerror rules  : " + i);
            com.qtxa.clean.sysmonitor.d.b("QtSysMonitor", "get systemerror  :" + i);
            if (i != 1) {
                if (com.qtxa.qtxadefend.b.c) {
                    y.this.h();
                    return;
                }
                return;
            }
            try {
                y.this.f(bArr);
            } catch (Exception e) {
                w.d("get systemerror rules  : " + e.toString());
            }
        }
    }

    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static y f154a = new y(null);
    }

    /* synthetic */ y(a aVar) {
        this();
    }

    public static y d() {
        return b.f154a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(byte[] bArr) {
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            this.f151a = jSONObject;
            JSONArray jSONArray = jSONObject.getJSONArray("item");
            if (jSONArray != null) {
                this.f152b.clear();
                for (int i = 0; i < jSONArray.length(); i++) {
                    c(jSONArray.getString(i));
                }
            }
            List<String> listK = z.i().k();
            JSONArray jSONArray2 = this.f151a.getJSONArray("exception");
            if (jSONArray2 != null) {
                listK.clear();
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    String string = jSONArray2.getString(i2);
                    if (string != null && !"".equals(string)) {
                        listK.add(string);
                    }
                }
            }
            int i3 = this.f151a.getInt("cycle");
            if (i3 != 0) {
                z.i().m(i3);
            }
        } catch (Exception e) {
            e.printStackTrace();
            w.d("systemerr config  error:" + e.toString());
            JniQtxaManager.java_rollBack("systemerr", 1, 9, 0, 1, this.c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:42:0x007f A[Catch: all -> 0x007b, TRY_LEAVE, TryCatch #7 {all -> 0x007b, blocks: (B:38:0x0077, B:42:0x007f), top: B:49:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0077 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void h() throws java.lang.Throwable {
        /*
            r5 = this;
            java.lang.String r0 = "testLoaderSec111....1-9-0.rule_bak"
            com.qtxa.qtxadefend.w.a(r0)
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L45
            java.lang.String r2 = "/data/qtxa/rule/1-9-0.rule_bak"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L45
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3e
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3e
            r3.<init>()     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3e
        L16:
            int r0 = r1.read(r2)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r4 = -1
            if (r0 == r4) goto L22
            r4 = 0
            r3.write(r2, r4, r0)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            goto L16
        L22:
            byte[] r0 = r3.toByteArray()     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r5.f(r0)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r1.close()     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r3.close()     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r1.close()     // Catch: java.lang.Throwable -> L68
            r3.close()     // Catch: java.lang.Throwable -> L68
            goto L73
        L36:
            r5 = move-exception
            goto L3c
        L38:
            r5 = move-exception
            goto L40
        L3a:
            r5 = move-exception
            r3 = r0
        L3c:
            r0 = r1
            goto L75
        L3e:
            r5 = move-exception
            r3 = r0
        L40:
            r0 = r1
            goto L47
        L42:
            r5 = move-exception
            r3 = r0
            goto L75
        L45:
            r5 = move-exception
            r3 = r0
        L47:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L74
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L74
            r1.<init>()     // Catch: java.lang.Throwable -> L74
            java.lang.String r2 = "testLoaderRules error....1-9-0.rule_bak"
            r1.append(r2)     // Catch: java.lang.Throwable -> L74
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L74
            r1.append(r5)     // Catch: java.lang.Throwable -> L74
            java.lang.String r5 = r1.toString()     // Catch: java.lang.Throwable -> L74
            com.qtxa.qtxadefend.w.a(r5)     // Catch: java.lang.Throwable -> L74
            if (r0 == 0) goto L6a
            r0.close()     // Catch: java.lang.Throwable -> L68
            goto L6a
        L68:
            r5 = move-exception
            goto L70
        L6a:
            if (r3 == 0) goto L73
            r3.close()     // Catch: java.lang.Throwable -> L68
            goto L73
        L70:
            r5.printStackTrace()
        L73:
            return
        L74:
            r5 = move-exception
        L75:
            if (r0 == 0) goto L7d
            r0.close()     // Catch: java.lang.Throwable -> L7b
            goto L7d
        L7b:
            r0 = move-exception
            goto L83
        L7d:
            if (r3 == 0) goto L86
            r3.close()     // Catch: java.lang.Throwable -> L7b
            goto L86
        L83:
            r0.printStackTrace()
        L86:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qtxa.qtxadefend.y.h():void");
    }

    public void c(String str) {
        this.f152b.add(str);
    }

    public void e(Context context) {
    }

    public void g(String str) {
        if (!JniQtxaManager.library) {
            h();
        } else {
            w.d("registSystemErrorConfigUpdate ");
            JniQtxaManager.java_loadRule(str, 1, 9, 0, 1, this.c);
        }
    }

    private y() {
        this.f151a = null;
        this.c = new a();
        this.f152b = new ArrayList();
        new ArrayList();
        this.f151a = new JSONObject();
    }
}
