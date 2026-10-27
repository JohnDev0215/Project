package defpackage;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import java.util.Arrays;
import org.chromium.base.ThreadUtils;
import org.chromium.components.payments.PaymentRequestUpdateEventListener;

/* JADX INFO: compiled from: PaymentDetailsUpdateServiceHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class uy1 {
    public static uy1 e;
    public kw0 a;
    public PaymentRequestUpdateEventListener b;
    public PackageInfo c;
    public ut1 d;

    public static uy1 a() {
        Object obj = ThreadUtils.a;
        if (e == null) {
            e = new uy1();
        }
        return e;
    }

    public static void c(String str, kw0 kw0Var) {
        Object obj = ThreadUtils.a;
        if (kw0Var == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("error", str);
        try {
            kw0Var.s(bundle);
        } catch (RemoteException e2) {
            Log.e("cr_PaymentDetailsUpdate", "Error calling updateWith", e2);
        }
    }

    public final boolean b(int i) {
        Object obj = ThreadUtils.a;
        if (this.d == null) {
            Log.e("cr_PaymentDetailsUpdate", "Caller's signature or package name does not match invoked app's.");
            return false;
        }
        String nameForUid = xz.a.getPackageManager().getNameForUid(i);
        PackageInfo packageInfoC = nameForUid == null ? null : zt1.c(64, nameForUid);
        PackageInfo packageInfo = this.c;
        if (packageInfo == null || packageInfoC == null || !packageInfo.packageName.equals(packageInfoC.packageName)) {
            Log.e("cr_PaymentDetailsUpdate", "Caller's signature or package name does not match invoked app's.");
            return false;
        }
        boolean zEquals = Arrays.equals(packageInfoC.signatures, this.c.signatures);
        if (!zEquals) {
            Log.e("cr_PaymentDetailsUpdate", "Caller's signature or package name does not match invoked app's.");
        }
        return zEquals;
    }
}
