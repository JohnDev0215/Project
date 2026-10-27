package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.util.Log;

/* JADX INFO: renamed from: rc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1442rc {
    public static C1442rc a;

    public C1442rc(Context context) {
        context.getApplicationContext();
    }

    public static AbstractBinderC0382Vf a(PackageInfo packageInfo, AbstractBinderC0382Vf... abstractBinderC0382VfArr) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr == null) {
            return null;
        }
        if (signatureArr.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        BinderC0364Uf binderC0364Uf = new BinderC0364Uf(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < abstractBinderC0382VfArr.length; i++) {
            if (abstractBinderC0382VfArr[i].equals(binderC0364Uf)) {
                return abstractBinderC0382VfArr[i];
            }
        }
        return null;
    }

    public static boolean b(PackageInfo packageInfo, boolean z) {
        if (packageInfo != null && packageInfo.signatures != null) {
            if ((z ? a(packageInfo, AbstractC0400Wf.a) : a(packageInfo, AbstractC0400Wf.a[0])) != null) {
                return true;
            }
        }
        return false;
    }
}
