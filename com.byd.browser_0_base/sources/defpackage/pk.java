package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.browserservices.permissiondelegation.a;

/* JADX INFO: compiled from: Cache.java */
/* JADX INFO: loaded from: classes.dex */
public final class pk {
    public final Object a;
    public final Object b;
    public final Object c;
    public Object d;

    public pk(int i) {
        if (i != 1) {
            this.a = new t22(256, 0);
            this.b = new t22(256, 0);
            this.c = new t22(256, 0);
            this.d = new hq2[32];
            return;
        }
        this.a = new m8();
        this.b = new SparseArray();
        this.c = new sa1();
        this.d = new m8();
    }

    public final void a(String str, String str2, ft1 ft1Var) {
        ArrayList arrayList;
        if (((Set) this.d).contains(ft1Var)) {
            return;
        }
        y01 y01Var = (y01) ((p61) this.c).get();
        PackageManager packageManager = y01Var.a;
        HashSet hashSet = y01Var.c;
        StringBuilder sbK = C0173a.k(str, ":");
        sbK.append(ft1Var.toString());
        int i = 0;
        if (hashSet.add(sbK.toString())) {
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
                String string = packageManager.getApplicationLabel(applicationInfo).toString();
                if (TextUtils.isEmpty(string) || applicationInfo.uid == -1) {
                    fa1.d("DataRecorder", "Invalid details for client package %s: %d, %s", str, Integer.valueOf(applicationInfo.uid), string);
                } else {
                    y01Var.b.f(applicationInfo.uid, string, str, bk3.b(ft1Var.toString(), true), ft1Var);
                }
            } catch (PackageManager.NameNotFoundException unused) {
                fa1.b("DataRecorder", "Couldn't find name for client package %s", str);
            }
        }
        f02 f02Var = (f02) this.b;
        f02Var.getClass();
        Uri uri = Uri.parse(str2);
        Intent intent = new Intent();
        intent.setPackage(str);
        intent.setData(uri);
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.BROWSABLE");
        xa3 xa3VarQ = xa3.q("BrowserServices.BrowsableIntentCheck");
        try {
            boolean z = wt1.d(intent, 0) != null;
            xa3VarQ.close();
            if (z) {
                a aVar = f02Var.a;
                jb3 jb3Var = null;
                try {
                    PackageInfo packageInfo = aVar.b.getPackageInfo(str, 134217728);
                    arrayList = new ArrayList();
                    SigningInfo signingInfo = packageInfo.signingInfo;
                    if (signingInfo.hasMultipleSigners()) {
                        for (Signature signature : signingInfo.getApkContentsSigners()) {
                            arrayList.add(tt1.a(signature));
                        }
                    } else {
                        arrayList.add(tt1.a(signingInfo.getSigningCertificateHistory()[0]));
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e("PackageIdentity", "Could not get fingerprint for package.", e);
                    arrayList = null;
                }
                if (arrayList != null) {
                    try {
                        jb3Var = new jb3(lb3.a(str, arrayList));
                    } catch (IOException e2) {
                        Log.e("Token", "Exception when creating token.", e2);
                    }
                }
                if (jb3Var != null) {
                    a11 a11Var = aVar.a;
                    a11Var.getClass();
                    String strA = a11.a(ft1Var);
                    Set<String> setEmptySet = Collections.emptySet();
                    SharedPreferences sharedPreferences = a11Var.a;
                    HashSet hashSet2 = new HashSet(sharedPreferences.getStringSet(strA, setEmptySet));
                    byte[] bArr = jb3Var.a.a;
                    hashSet2.add(Base64.encodeToString(Arrays.copyOf(bArr, bArr.length), 3));
                    sharedPreferences.edit().putStringSet(strA, hashSet2).apply();
                }
                dp1 dp1Var = f02Var.b;
                dp1Var.getClass();
                zo1 zo1Var = new zo1(dp1Var, i, ft1Var);
                sf3 sf3Var = dp1Var.b;
                sf3Var.getClass();
                sf3Var.a(Uri.parse(str2), new lf3(sf3Var, xz.a.getResources().getString(R$string.notification_category_group_general), zo1Var));
            }
            ((Set) this.d).add(ft1Var);
        } finally {
        }
    }

    public /* synthetic */ pk(Context context, f02 f02Var, p61 p61Var) {
        this.d = new HashSet();
        this.a = context;
        this.b = f02Var;
        this.c = p61Var;
    }
}
