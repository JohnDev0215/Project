package com.qtxa.qtxadefend;

import android.content.pm.PackageManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class l extends g {
    private final u c;
    private final Map<String, k> d = new HashMap();

    l(u uVar) {
        this.c = uVar;
        JSONObject jSONObjectJ = j();
        w.d("onRunPackage  PackageRecordManager:load:" + jSONObjectJ);
        Iterator<String> itKeys = jSONObjectJ.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                this.d.put(next, k.a(jSONObjectJ.getJSONObject(next)));
            } catch (JSONException unused) {
            }
        }
    }

    private synchronized k g(String str) {
        k kVar;
        kVar = this.d.get(str);
        if (kVar == null) {
            kVar = new k(str);
            this.d.put(str, kVar);
        }
        return kVar;
    }

    private JSONObject j() {
        try {
            return new JSONObject(super.b(this.c.c().openFileInput("records"), true));
        } catch (IOException | JSONException unused) {
            return new JSONObject();
        }
    }

    private synchronized k k(String str) {
        return this.d.get(str);
    }

    private synchronized void m() {
        try {
            FileOutputStream fileOutputStreamOpenFileOutput = this.c.c().openFileOutput("records", 0);
            super.c(fileOutputStreamOpenFileOutput, n().toString(), true);
            fileOutputStreamOpenFileOutput.close();
        } catch (IOException | JSONException unused) {
        }
    }

    private JSONObject n() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, k> entry : this.d.entrySet()) {
            if (!entry.getValue().f132b) {
                jSONObject.put(entry.getKey(), entry.getValue().b());
            }
        }
        return jSONObject;
    }

    synchronized k d(String str, boolean z) {
        k kVarK;
        kVarK = k(str);
        if (kVarK == null) {
            kVarK = new k(str);
        }
        kVarK.f132b = false;
        kVarK.c = false;
        kVarK.d = null;
        try {
            kVarK.d = ByteBuffer.wrap(i.b(new File(this.c.c().getPackageManager().getPackageInfo(str, 0).applicationInfo.sourceDir), "SHA-256"));
        } catch (PackageManager.NameNotFoundException | IOException | NoSuchAlgorithmException unused) {
        }
        this.d.put(str, kVarK);
        m();
        return kVarK;
    }

    synchronized boolean e(String str) {
        w.d("checkPackageHash");
        k kVarK = k(str);
        if (kVarK == null || kVarK.d == null) {
            return true;
        }
        try {
            File file = new File(this.c.c().getPackageManager().getPackageInfo(str, 0).applicationInfo.sourceDir);
            if (!file.exists()) {
                return false;
            }
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(i.b(file, "SHA-256"));
            w.d("checkPackageHash2:" + byteBufferWrap.toString());
            w.d("checkPackageHash3:" + kVarK.d.toString());
            return kVarK.d.equals(byteBufferWrap);
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        } catch (IOException unused2) {
            return false;
        } catch (NoSuchAlgorithmException unused3) {
            return false;
        }
    }

    synchronized void f(String str) {
        k kVarK = k(str);
        if (kVarK != null) {
            kVarK.f132b = true;
            kVarK.d = null;
        }
        m();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    synchronized boolean h(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            com.qtxa.qtxadefend.k r2 = r1.k(r2)     // Catch: java.lang.Throwable -> L14
            if (r2 == 0) goto L11
            boolean r0 = r2.f132b     // Catch: java.lang.Throwable -> L14
            if (r0 != 0) goto L11
            java.nio.ByteBuffer r2 = r2.d     // Catch: java.lang.Throwable -> L14
            if (r2 == 0) goto L11
            r2 = 1
            goto L12
        L11:
            r2 = 0
        L12:
            monitor-exit(r1)
            return r2
        L14:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qtxa.qtxadefend.l.h(java.lang.String):boolean");
    }

    boolean i(String str) {
        return g(str).c;
    }

    void l(String str) {
        g(str).f132b = false;
        g(str).c = true;
        m();
    }
}
