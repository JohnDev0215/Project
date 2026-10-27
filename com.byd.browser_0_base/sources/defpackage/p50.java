package defpackage;

import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.util.Log;

/* JADX INFO: compiled from: CustomizationProviderDelegateUpstreamImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public class p50 {
    public static Boolean a;

    public static Uri a(String str) {
        return new Uri.Builder().scheme("content").authority("com.android.partnerbrowsercustomizations").appendPath(str).build();
    }

    public static boolean b() {
        if (a == null) {
            boolean z = false;
            ProviderInfo providerInfoResolveContentProvider = xz.a.getPackageManager().resolveContentProvider("com.android.partnerbrowsercustomizations", 0);
            if (providerInfoResolveContentProvider != null) {
                if ((providerInfoResolveContentProvider.applicationInfo.flags & 1) != 0) {
                    z = true;
                } else {
                    Log.w("cr_CustomizationProv", "Browser Customizations content provider package, " + providerInfoResolveContentProvider.packageName + ", is not a system package. This could be a malicious attempt from a third party app, so skip reading the browser content provider.");
                }
            }
            a = Boolean.valueOf(z);
        }
        return a.booleanValue();
    }
}
