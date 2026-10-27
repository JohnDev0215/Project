package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.chromium.chrome.browser.tab.Tab;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: compiled from: OfflinePageOrigin.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ar1 {
    public final String a;
    public final String[] b;

    public ar1(String str) throws Throwable {
        String str2 = "";
        String[] strArr = null;
        try {
            try {
                JSONArray jSONArray = new JSONArray(str);
                if (jSONArray.length() == 2) {
                    String string = jSONArray.getString(0);
                    try {
                        JSONArray jSONArray2 = jSONArray.getJSONArray(1);
                        int length = jSONArray2.length();
                        String[] strArr2 = new String[length];
                        for (int i = 0; i < length; i++) {
                            try {
                                strArr2[i] = jSONArray2.getString(i);
                            } catch (Throwable th) {
                                th = th;
                                str2 = string;
                                strArr = strArr2;
                                this.a = str2;
                                this.b = strArr;
                                throw th;
                            }
                        }
                        str2 = string;
                        strArr = strArr2;
                    } catch (Throwable th2) {
                        th = th2;
                        str2 = string;
                    }
                }
                this.a = str2;
                this.b = strArr;
            } catch (JSONException unused) {
                this.a = "";
                this.b = null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public final String a() {
        String str = this.a;
        boolean zIsEmpty = TextUtils.isEmpty(str);
        String[] strArr = this.b;
        if (zIsEmpty || strArr == null) {
            return "";
        }
        JSONArray jSONArray = new JSONArray();
        for (String str2 : strArr) {
            jSONArray.put(str2);
        }
        return new JSONArray().put(str).put(jSONArray).toString();
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ar1)) {
            return false;
        }
        ar1 ar1Var = (ar1) obj;
        return this.a.equals(ar1Var.a) && Arrays.equals(this.b, ar1Var.b);
    }

    public final int hashCode() {
        return Arrays.deepHashCode(new Object[]{this.a, this.b});
    }

    public final String toString() {
        return a();
    }

    public ar1(Context context, Tab tab) {
        jy2 jy2Var = (jy2) tab.B().b(jy2.class);
        String[] strArr = null;
        String str = jy2Var != null ? jy2Var.f : null;
        if (TextUtils.isEmpty(str)) {
            this.a = "";
            this.b = null;
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                Signature[] signatureArr = context.getPackageManager().getPackageInfo(str, 64).signatures;
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                int length = signatureArr.length;
                String[] strArr2 = new String[length];
                for (int i = 0; i < length; i++) {
                    messageDigest.update(signatureArr[i].toByteArray());
                    byte[] bArrDigest = messageDigest.digest();
                    strArr2[i] = bArrDigest == null ? null : Base64.encodeToString(bArrDigest, 0);
                }
                Arrays.sort(strArr2);
                strArr = strArr2;
            } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException unused) {
            }
        }
        this.b = strArr;
        if (strArr == null) {
            this.a = "";
        } else {
            this.a = str;
        }
    }

    public ar1() {
        this.a = "";
        this.b = null;
    }
}
