package defpackage;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: PackageIdentityUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class tt1 {

    /* JADX INFO: compiled from: PackageIdentityUtils.java */
    public static class a {
        public final boolean a(String str, PackageManager packageManager, lb3 lb3Var) throws PackageManager.NameNotFoundException, IOException {
            lb3Var.b();
            String str2 = lb3Var.b;
            if (str2 == null) {
                throw new IllegalStateException();
            }
            if (!str2.equals(str)) {
                return false;
            }
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 134217728);
            ArrayList arrayList = new ArrayList();
            SigningInfo signingInfo = packageInfo.signingInfo;
            if (signingInfo.hasMultipleSigners()) {
                for (Signature signature : signingInfo.getApkContentsSigners()) {
                    arrayList.add(tt1.a(signature));
                }
            } else {
                arrayList.add(tt1.a(signingInfo.getSigningCertificateHistory()[0]));
            }
            if (arrayList.size() != 1) {
                return lb3Var.equals(lb3.a(str, arrayList));
            }
            lb3Var.b();
            ArrayList arrayList2 = lb3Var.c;
            if (arrayList2 != null) {
                return packageManager.hasSigningCertificate(str, Arrays.copyOf((byte[]) arrayList2.get(0), ((byte[]) lb3Var.c.get(0)).length), 1);
            }
            throw new IllegalStateException();
        }
    }

    public static byte[] a(Signature signature) {
        try {
            return MessageDigest.getInstance("SHA256").digest(signature.toByteArray());
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }
}
