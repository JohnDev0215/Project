package defpackage;

import J.N;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import defpackage.q01;
import defpackage.w01;
import defpackage.x01;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.installedapp.PackageHash;
import org.chromium.content_public.browser.BrowserContextHandle;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.url.GURL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: InstalledAppProviderImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class w01 implements q01 {
    public final BrowserContextHandle e;
    public final RenderFrameHost f;
    public final vt1 g = new vt1();
    public final a h;

    /* JADX INFO: compiled from: InstalledAppProviderImpl.java */
    public interface a {
    }

    /* JADX INFO: compiled from: InstalledAppProviderImpl.java */
    public class b {
        public int a;
        public final q01.a b;
        public final ArrayList<ka2> c;
        public int d;

        public b(int i, x01.e eVar) {
            this.a = i;
            this.b = eVar;
            ArrayList<ka2> arrayList = new ArrayList<>(Collections.nCopies(this.a, null));
            this.c = arrayList;
            if (this.a == 0) {
                w01.t(w01.this, arrayList, this.d, eVar);
            }
        }

        public final void a(ka2 ka2Var, int i, int i2) {
            ArrayList<ka2> arrayList = this.c;
            arrayList.set(i, ka2Var);
            this.d += i2;
            int i3 = this.a - 1;
            this.a = i3;
            if (i3 == 0) {
                arrayList.removeAll(Collections.singleton(null));
                w01.t(w01.this, arrayList, this.d, this.b);
            }
        }
    }

    public w01(Profile profile, RenderFrameHost renderFrameHost, c01 c01Var) {
        this.e = profile;
        this.f = renderFrameHost;
        this.h = c01Var;
    }

    public static boolean H0(ka2 ka2Var) {
        String str;
        if (ka2Var.b.equals("play") && (str = ka2Var.d) != null) {
            return "instantapp".equals(str) || "instantapp:holdback".equals(ka2Var.d);
        }
        return false;
    }

    public static void I0(final b bVar, final ka2 ka2Var, final int i, final int i2) {
        PostTask.c(rh3.a, new Runnable() { // from class: v01
            @Override // java.lang.Runnable
            public final void run() {
                bVar.a(ka2Var, i, i2);
            }
        });
    }

    public static JSONArray R(String str, vt1 vt1Var) throws PackageManager.NameNotFoundException {
        Bundle bundle;
        vt1Var.getClass();
        ApplicationInfo applicationInfo = xz.a.getPackageManager().getApplicationInfo(str, 128);
        if (applicationInfo == null || (bundle = applicationInfo.metaData) == null) {
            return new JSONArray();
        }
        int i = bundle.getInt("asset_statements");
        if (i == 0) {
            return new JSONArray();
        }
        try {
            try {
                return new JSONArray(xz.a.getPackageManager().getResourcesForApplication(applicationInfo).getString(i));
            } catch (JSONException unused) {
                fa1.l("InstalledAppProvider", "Android package %s has JSON syntax error in asset statements resource (0x%s).", str, Integer.toHexString(i));
                return new JSONArray();
            }
        } catch (Resources.NotFoundException unused2) {
            fa1.l("InstalledAppProvider", "Android package %s missing asset statements resource (0x%s).", str, Integer.toHexString(i));
            return new JSONArray();
        }
    }

    public static void t(w01 w01Var, ArrayList arrayList, int i, q01.a aVar) {
        ka2[] ka2VarArr;
        if (w01Var.f.isIncognito()) {
            ka2VarArr = new ka2[0];
        } else {
            ka2VarArr = new ka2[arrayList.size()];
            arrayList.toArray(ka2VarArr);
        }
        PostTask.b(rh3.a, new ed2(aVar, 1, ka2VarArr), i);
    }

    public static boolean v0(String str, GURL gurl, vt1 vt1Var) {
        boolean zEquals;
        if (gurl == null) {
            return false;
        }
        try {
            JSONArray jSONArrayR = R(str, vt1Var);
            for (int i = 0; i < jSONArrayR.length(); i++) {
                try {
                    GURL gurl2 = null;
                    try {
                        JSONObject jSONObject = jSONArrayR.getJSONObject(i).getJSONObject("target");
                        try {
                            zEquals = jSONObject.getString("namespace").equals("web");
                        } catch (JSONException unused) {
                            zEquals = false;
                        }
                        if (zEquals) {
                            gurl2 = new GURL(jSONObject.getString("site"));
                        }
                    } catch (JSONException unused2) {
                    }
                    if (gurl2 == null) {
                        continue;
                    } else {
                        if (gurl2.i() != null && gurl2.e() != null && gurl2.i().equals(gurl.i()) && gurl2.e().equals(gurl.e()) && gurl2.h().equals(gurl.h())) {
                            return true;
                        }
                    }
                } catch (JSONException unused3) {
                }
            }
        } catch (PackageManager.NameNotFoundException unused4) {
        }
        return false;
    }

    public final int C(String str) {
        FileInputStream fileInputStream;
        if (PackageHash.a == null) {
            PackageHash.a = new SparseArray<>();
        }
        SparseArray<byte[]> sparseArray = PackageHash.a;
        BrowserContextHandle browserContextHandle = this.e;
        byte[] bArr = sparseArray.get(browserContextHandle.hashCode());
        try {
            if (bArr == null) {
                try {
                    fileInputStream = new FileInputStream("/dev/urandom");
                    try {
                        byte[] bArr2 = new byte[20];
                        if (20 != fileInputStream.read(bArr2)) {
                            throw new GeneralSecurityException("Not enough random data available");
                        }
                        fileInputStream.close();
                        PackageHash.a.put(browserContextHandle.hashCode(), bArr2);
                        bArr = bArr2;
                    } catch (Throwable th) {
                        th = th;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = null;
                }
            }
            try {
                Mac mac = Mac.getInstance("HmacSHA256");
                byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
                try {
                    mac.init(new SecretKeySpec(bArr, "HmacSHA256"));
                    byte[] bArrDoFinal = mac.doFinal(bytes);
                    return (((short) ((bArrDoFinal[1] & 255) | ((bArrDoFinal[0] & 255) << 8))) & 1023) / 100;
                } catch (InvalidKeyException e) {
                    throw new RuntimeException(e);
                }
            } catch (NoSuchAlgorithmException e2) {
                throw new RuntimeException(e2);
            }
        } catch (IOException | GeneralSecurityException e3) {
            throw new RuntimeException(e3);
        }
    }

    public final void F(b bVar, int i, ka2 ka2Var) {
        String str;
        int iC = C(ka2Var.c);
        String str2 = ka2Var.c;
        List<PackageInfo> installedPackages = xz.a.getPackageManager().getInstalledPackages(192);
        int i2 = 0;
        while (true) {
            if (i2 >= installedPackages.size()) {
                str = null;
                break;
            }
            PackageInfo packageInfo = installedPackages.get(i2);
            if (fq3.f(packageInfo, packageInfo.packageName) && TextUtils.equals(packageInfo.applicationInfo.metaData.getString("org.chromium.webapk.shell_apk.webManifestUrl"), str2)) {
                str = packageInfo.packageName;
                break;
            }
            i2++;
        }
        if (str != null) {
            I0(bVar, ka2Var, i, iC);
        } else {
            I0(bVar, null, i, iC);
        }
    }

    @Override // defpackage.q01
    public final void Z0(ka2[] ka2VarArr, qj3 qj3Var, x01.e eVar) {
        GURL gurlG = this.f.g();
        if (gurlG == null) {
            gurlG = GURL.emptyGURL();
        }
        final GURL gurl = gurlG;
        int iMin = Math.min(ka2VarArr.length, 3);
        final b bVar = new b(iMin, eVar);
        for (int i = 0; i < iMin; i++) {
            final ka2 ka2Var = ka2VarArr[i];
            if (H0(ka2Var)) {
                final int i2 = i;
                PostTask.c(d73.g, new Runnable() { // from class: s01
                    @Override // java.lang.Runnable
                    public final void run() {
                        w01 w01Var = this.e;
                        w01Var.getClass();
                        ka2 ka2Var2 = ka2Var;
                        int iC = w01Var.C(ka2Var2.d);
                        w01.b bVar2 = bVar;
                        int i3 = i2;
                        w01.a aVar = w01Var.h;
                        if (aVar != null) {
                            gurl.j();
                            "instantapp:holdback".equals(ka2Var2.d);
                            ((j11) ((c01) aVar).f).getClass();
                            w01.I0(bVar2, null, i3, iC);
                            return;
                        }
                        try {
                            vt1 vt1Var = w01Var.g;
                            String str = ka2Var2.d;
                            vt1Var.getClass();
                            ka2Var2.e = xz.a.getPackageManager().getPackageInfo(str, 0).versionName;
                        } catch (PackageManager.NameNotFoundException unused) {
                        }
                        w01.I0(bVar2, ka2Var2, i3, iC);
                    }
                });
            } else if ((ka2Var.b.equals("play") && ka2Var.d != null) ? !H0(ka2Var) : false) {
                final int i3 = i;
                PostTask.c(d73.g, new Runnable() { // from class: t01
                    @Override // java.lang.Runnable
                    public final void run() {
                        w01 w01Var = this.e;
                        w01Var.getClass();
                        ka2 ka2Var2 = ka2Var;
                        int iC = w01Var.C(ka2Var2.d);
                        String str = ka2Var2.d;
                        GURL gurl2 = gurl;
                        vt1 vt1Var = w01Var.g;
                        boolean zV0 = w01.v0(str, gurl2, vt1Var);
                        w01.b bVar2 = bVar;
                        int i4 = i3;
                        if (!zV0) {
                            w01.I0(bVar2, null, i4, iC);
                            return;
                        }
                        try {
                            String str2 = ka2Var2.d;
                            vt1Var.getClass();
                            ka2Var2.e = xz.a.getPackageManager().getPackageInfo(str2, 0).versionName;
                        } catch (PackageManager.NameNotFoundException unused) {
                        }
                        w01.I0(bVar2, ka2Var2, i4, iC);
                    }
                });
            } else if ((ka2Var.b.equals("webapp") && ka2Var.c != null) && ka2Var.c.equals(qj3Var.b)) {
                PostTask.c(d73.g, new la(this, bVar, i, ka2Var));
            } else if (ka2Var.b.equals("webapp") && ka2Var.c != null) {
                final int iC = C(ka2Var.c);
                final int i4 = i;
                N.MoHbKNbT(this.e, ka2Var.c, qj3Var.b, new Callback() { // from class: u01
                    @Override // org.chromium.base.Callback
                    public final void onResult(Object obj) {
                        w01.b bVar2 = bVar;
                        int i5 = i4;
                        ka2 ka2Var2 = ka2Var;
                        w01 w01Var = this.e;
                        w01Var.getClass();
                        if (((Boolean) obj).booleanValue()) {
                            PostTask.c(d73.g, new gw1(w01Var, i5, bVar2, ka2Var2, 1));
                        } else {
                            bVar2.a(null, i5, iC);
                        }
                    }
                });
            } else {
                bVar.a(null, i, 0);
            }
        }
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
    }
}
