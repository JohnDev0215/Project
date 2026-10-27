package com.qtxa.qtxadefend;

import com.qtxa.report.JniQtxaManager;
import com.qtxa.report.JniReportMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f139a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private JSONObject f140b;
    private List<String> c;
    private List<String> d;
    private List<String> e;
    private HashMap<String, String> f;
    private JniQtxaManager.RuleCallback g;

    class a implements JniQtxaManager.RuleCallback {
        a() {
        }

        @Override // com.qtxa.report.JniQtxaManager.RuleCallback
        public void callback(int i, byte[] bArr) throws Throwable {
            if (i == 1) {
                q.this.j(bArr);
                return;
            }
            w.d("rootcheck load error.");
            if (com.qtxa.qtxadefend.b.c) {
                q.this.m();
            }
        }
    }

    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static q f142a = new q(null);
    }

    /* synthetic */ q(a aVar) {
        this();
    }

    public static q d() {
        return b.f142a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(byte[] bArr) {
        try {
            String str = new String(bArr, "UTF-8");
            w.d("get rootcheck rules: " + str);
            JSONObject jSONObject = new JSONObject(str);
            this.f140b = jSONObject;
            JSONArray jSONArray = jSONObject.getJSONArray("superapks");
            if (jSONArray != null) {
                w.d("get rootcheck superapks: " + jSONArray.length());
                this.c.clear();
                for (int i = 0; i < jSONArray.length(); i++) {
                    w.d("rootcheck addSuperApks: " + jSONArray.getString(i));
                    c(jSONArray.getString(i));
                }
            }
            JSONObject jSONObject2 = this.f140b.getJSONObject("properties");
            w.d("rootcheck getJSONObject properties" + jSONObject2);
            if (jSONObject2 != null) {
                this.f.clear();
                this.e.clear();
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys != null && itKeys.hasNext()) {
                    String next = itKeys.next();
                    w.d("rootcheck properties key: " + next);
                    String string = Integer.toString(jSONObject2.getInt(next));
                    w.d("rootcheck properties vlues: " + string);
                    this.e.add(next);
                    this.f.put(next, string);
                }
            }
            JSONArray jSONArray2 = this.f140b.getJSONArray("instruct");
            if (jSONArray2 != null) {
                w.d("get rootcheck instruct: " + jSONArray2.length());
                this.d.clear();
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    String string2 = jSONArray2.getString(i2);
                    w.d("rootcheck instruct: " + string2);
                    if (string2 != null && !"".equals(string2)) {
                        this.d.add(string2);
                    }
                }
            }
            int i3 = this.f140b.getInt("cycle");
            w.d("get rootcheck cycle: " + i3);
            if (i3 == 0) {
                a0.a().c();
            }
            if (i3 == 0 || this.f139a == i3) {
                return;
            }
            l(i3 * 1000);
            a0.a().c();
            a0.a().b();
        } catch (Exception e) {
            e.printStackTrace();
            w.d("rootcheck secconfig  error:" + e.toString());
            JniQtxaManager.java_rollBack("rootcheck", 1, 6, 0, 1, this.g);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:42:0x006c A[Catch: all -> 0x0068, TRY_LEAVE, TryCatch #5 {all -> 0x0068, blocks: (B:38:0x0064, B:42:0x006c), top: B:49:0x0064 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0064 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m() throws java.lang.Throwable {
        /*
            r5 = this;
            java.lang.String r0 = "testLoaderRules....1-6-0.rule_bak"
            com.qtxa.qtxadefend.w.a(r0)
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L45
            java.lang.String r2 = "/data/qtxa/rule/1-6-0.rule_bak"
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
            r5.j(r0)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r1.close()     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r3.close()     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r1.close()     // Catch: java.lang.Throwable -> L55
            r3.close()     // Catch: java.lang.Throwable -> L55
            goto L60
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
            goto L62
        L3e:
            r5 = move-exception
            r3 = r0
        L40:
            r0 = r1
            goto L47
        L42:
            r5 = move-exception
            r3 = r0
            goto L62
        L45:
            r5 = move-exception
            r3 = r0
        L47:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L61
            java.lang.String r5 = "testLoaderRules error....1-6-0.rule_bak"
            com.qtxa.qtxadefend.w.a(r5)     // Catch: java.lang.Throwable -> L61
            if (r0 == 0) goto L57
            r0.close()     // Catch: java.lang.Throwable -> L55
            goto L57
        L55:
            r5 = move-exception
            goto L5d
        L57:
            if (r3 == 0) goto L60
            r3.close()     // Catch: java.lang.Throwable -> L55
            goto L60
        L5d:
            r5.printStackTrace()
        L60:
            return
        L61:
            r5 = move-exception
        L62:
            if (r0 == 0) goto L6a
            r0.close()     // Catch: java.lang.Throwable -> L68
            goto L6a
        L68:
            r0 = move-exception
            goto L70
        L6a:
            if (r3 == 0) goto L73
            r3.close()     // Catch: java.lang.Throwable -> L68
            goto L73
        L70:
            r0.printStackTrace()
        L73:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qtxa.qtxadefend.q.m():void");
    }

    public void c(String str) {
        this.c.add(str);
    }

    public int e() {
        return this.f139a;
    }

    public List<String> f() {
        return this.e;
    }

    public boolean g(String str) {
        return this.c.contains(str);
    }

    public List<String> h() {
        return this.d;
    }

    public boolean i(String str, String str2) {
        return this.f.get(str) != null && this.f.get(str).equals(str2);
    }

    public void k(String str) throws Throwable {
        if (JniReportMessage.library) {
            JniQtxaManager.java_loadRule(str, 1, 6, 0, 1, this.g);
        } else {
            m();
        }
    }

    public void l(int i) {
        this.f139a = i;
    }

    private q() {
        this.f139a = 1800000;
        this.f140b = null;
        this.g = new a();
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.f = new HashMap<>();
        this.f140b = new JSONObject();
    }
}
