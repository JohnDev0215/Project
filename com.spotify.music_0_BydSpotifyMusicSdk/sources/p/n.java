package p;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.provider.Settings;
import com.spotify.base.java.logging.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class n implements ut {
    public final String a;

    public n(Context context) {
        context.getPackageManager().getInstallerPackageName(context.getApplicationContext().getPackageName());
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            if (packageInfo.signatures != null) {
                ArrayList arrayList = new ArrayList();
                for (Signature signature : packageInfo.signatures) {
                    int i = com.google.common.hash.d.a;
                    p11 p11Var = com.google.common.hash.b.a;
                    byte[] byteArray = signature.toByteArray();
                    k1 k1Var = (k1) p11Var;
                    k1Var.getClass();
                    int length = byteArray.length;
                    ci1.m(0, length, byteArray.length);
                    if (length < 0) {
                        throw new IllegalArgumentException(v03.z("expectedInputSize must be >= 0 but was %s", Integer.valueOf(length)));
                    }
                    arrayList.add(k1Var.a().y0(byteArray, length).l0().toString());
                }
                new tz2(String.valueOf(':'), 1).a(Arrays.asList(arrayList.toArray()));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Object[] objArr = new Object[0];
            Iterator it = Logger.a.iterator();
            while (it.hasNext()) {
                ((hc1) it.next()).a().f(Logger.c.b(), Logger.d("Failed to get the application signatures", objArr), e);
            }
        }
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        this.a = (string == null || string.isEmpty()) ? "0" : string;
    }

    public final String a() {
        int i = kk.a;
        return new as().z().s();
    }
}
