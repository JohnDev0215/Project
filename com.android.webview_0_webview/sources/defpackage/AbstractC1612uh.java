package defpackage;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.util.Log;

/* JADX INFO: renamed from: uh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1612uh {
    public static volatile AbstractC0115Gh a = C0061Dh.s;
    public static final Object b = new Object();

    public static boolean a(Context context, Uri uri) {
        ProviderInfo providerInfoResolveContentProvider;
        String authority = uri.getAuthority();
        boolean z = false;
        if (!"com.google.android.gms.phenotype".equals(authority)) {
            StringBuilder sb = new StringBuilder(String.valueOf(authority).length() + 91);
            sb.append(authority);
            sb.append(" is an unsupported authority. Only com.google.android.gms.phenotype authority is supported.");
            Log.e("PhenotypeClientHelper", sb.toString());
            return false;
        }
        if (a.b()) {
            return ((Boolean) a.a()).booleanValue();
        }
        synchronized (b) {
            if (a.b()) {
                return ((Boolean) a.a()).booleanValue();
            }
            if ("com.google.android.gms".equals(context.getPackageName()) || ((providerInfoResolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.phenotype", 0)) != null && "com.google.android.gms".equals(providerInfoResolveContentProvider.packageName))) {
                boolean z2 = (context.getPackageManager().getApplicationInfo("com.google.android.gms", 0).flags & 129) != 0;
                if (z2) {
                    z = true;
                }
            }
            a = AbstractC0115Gh.c(Boolean.valueOf(z));
            return ((Boolean) a.a()).booleanValue();
        }
    }
}
