package com.qtxa.qtxadefend;

import android.app.ActivityThread;
import com.qtxa.report.JniQtxaManager;
import com.qtxa.report.JniReportMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.security.cert.Certificate;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a extends g {
    private static final String[] j = {"none", "white", "black"};
    private int c;
    private HashMap<ByteBuffer, Integer> d;
    private HashMap<ByteBuffer, Integer> e;
    private JSONObject f;
    private u g;
    private JniQtxaManager.RuleCallback h;
    String i;

    /* JADX INFO: renamed from: com.qtxa.qtxadefend.a$a, reason: collision with other inner class name */
    class C0019a implements JniQtxaManager.RuleCallback {
        C0019a() {
        }

        @Override // com.qtxa.report.JniQtxaManager.RuleCallback
        public void callback(int i, byte[] bArr) throws Throwable {
            if (i == 1) {
                try {
                    a.this.l(bArr);
                    return;
                } catch (Exception unused) {
                    return;
                }
            }
            w.d("load AppSecConfig rule error:" + i);
            if (com.qtxa.qtxadefend.b.c) {
                a.this.q();
            }
        }
    }

    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static a f107a = new a(null);
    }

    /* synthetic */ a(C0019a c0019a) {
        this();
    }

    public static a j() {
        return b.f107a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(byte[] bArr) {
        String[] strArr = j;
        try {
            JSONArray jSONArrayH = h();
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            this.f = jSONObject;
            p(jSONObject.getString("mode").equals("ips") ? 1 : 2);
            JSONArray jSONArray = this.f.getJSONObject("certs").getJSONArray("white");
            w.d("getCertSignTag  cert certsArray: " + jSONArray.length());
            if (jSONArray != null) {
                this.d.clear();
                for (int i = 0; i < jSONArray.length(); i++) {
                    w.d("duqu de  cert hashcode: " + jSONArray.getString(i).hashCode());
                    this.d.put(ByteBuffer.wrap(d.b(jSONArray.getString(i))), 1);
                }
                if (jSONArrayH != null) {
                    for (int i2 = 0; i2 < jSONArrayH.length(); i2++) {
                        try {
                            w.d("duqu de  cert hashcode appsotre: " + jSONArrayH.getString(i2).hashCode());
                            this.d.put(ByteBuffer.wrap(com.qtxa.hidps.b.d.c(jSONArrayH.getString(i2))), 1);
                        } catch (Exception e) {
                            w.d("getCerts  error:" + jSONArrayH.get(i2) + ":" + e.toString());
                        }
                    }
                }
            }
            JSONObject jSONObjectOptJSONObject = this.f.optJSONObject("apks");
            if (jSONObjectOptJSONObject != null) {
                this.e.clear();
                for (int i3 = 1; i3 < strArr.length; i3++) {
                    JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray(strArr[i3]);
                    if (jSONArrayOptJSONArray != null) {
                        for (int i4 = 0; !jSONArrayOptJSONArray.isNull(i4); i4++) {
                            this.e.put(ByteBuffer.wrap(d.b(jSONArrayOptJSONArray.getString(i4))), Integer.valueOf(i3));
                        }
                    }
                }
            }
        } catch (Exception e2) {
            w.d("appsec config  error:" + e2.toString());
            e2.printStackTrace();
            JniQtxaManager.java_rollBack("appsec", 1, 7, 0, 1, this.h);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:42:0x006c A[Catch: all -> 0x0068, TRY_LEAVE, TryCatch #5 {all -> 0x0068, blocks: (B:38:0x0064, B:42:0x006c), top: B:49:0x0064 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0064 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void q() throws java.lang.Throwable {
        /*
            r5 = this;
            java.lang.String r0 = "testLoaderSec....1-7-0.rule_bak"
            com.qtxa.qtxadefend.w.a(r0)
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L45
            java.lang.String r2 = "/data/qtxa/rule/1-7-0.rule_bak"
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
            r5.l(r0)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
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
            java.lang.String r5 = "testLoaderRules error....1-7-0.rule_bak"
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
        throw new UnsupportedOperationException("Method not decompiled: com.qtxa.qtxadefend.a.q():void");
    }

    public boolean d() {
        return this.d.size() == 0;
    }

    public int g(byte[] bArr) {
        if (this.d.size() == 0) {
            w.d("getCertSignTag: mCertHM.size() == 0");
            return 1;
        }
        Integer num = this.d.get(ByteBuffer.wrap(bArr));
        w.d("getCertSignTag hashcode: " + d.c(bArr).hashCode() + "   v:" + num);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public JSONArray h() {
        try {
            super.a();
            String strB = super.b(this.g.c().openFileInput(this.i), true);
            w.d("getCerts:" + strB);
            return new JSONObject(new String(strB)).getJSONArray("certs");
        } catch (Exception e) {
            w.d("getCerts:" + e.toString());
            return new JSONArray();
        }
    }

    public int i() {
        return this.c;
    }

    public void k(u uVar) {
        this.g = uVar;
    }

    public void m(String str) {
        if (JniReportMessage.library) {
            JniQtxaManager.java_loadRule(str, 1, 7, 0, 1, this.h);
        } else {
            q();
        }
    }

    public void n(String str, File file) {
        Certificate[] certificateArrA = n.a(str, ActivityThread.currentApplication(), file, false);
        if (certificateArrA == null) {
            w.d("saveCert error:certs==null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArrayH = h();
        if (jSONArrayH == null) {
            jSONArrayH = new JSONArray();
        }
        byte[] encoded = certificateArrA[0].getPublicKey().getEncoded();
        jSONArrayH.put(com.qtxa.hidps.b.d.a(encoded));
        try {
            jSONObject.put("certs", jSONArrayH);
            FileOutputStream fileOutputStreamOpenFileOutput = this.g.c().openFileOutput(this.i, 0);
            w.d("saveCert:" + jSONObject.toString());
            super.c(fileOutputStreamOpenFileOutput, jSONObject.toString(), true);
            j().o(encoded);
        } catch (Exception e) {
            w.d("saveCert error:certs:" + e.toString());
        }
    }

    public void o(byte[] bArr) {
        this.d.put(ByteBuffer.wrap(bArr), 1);
    }

    public void p(int i) {
        this.c = i;
    }

    private a() {
        this.c = 1;
        this.f = null;
        this.h = new C0019a();
        this.i = "appstore";
        this.d = new HashMap<>();
        this.e = new HashMap<>();
        this.f = new JSONObject();
    }
}
