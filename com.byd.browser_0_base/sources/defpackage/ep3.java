package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import defpackage.cw0;
import defpackage.fp3;
import defpackage.tp3;

/* JADX INFO: compiled from: WebApkIdentityServiceClient.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ep3 implements tp3.b {
    public final /* synthetic */ Context a;
    public final /* synthetic */ String b;
    public final /* synthetic */ fp3.a c;

    public ep3(Context context, String str, fp3.a aVar) {
        this.a = context;
        this.b = str;
        this.c = aVar;
    }

    @Override // tp3.b
    public final void a(IBinder iBinder) {
        Bundle bundle;
        Context context = this.a;
        String packageName = context.getPackageName();
        fp3.a aVar = this.c;
        String strA = null;
        if (iBinder != null) {
            int i = cw0.a.e;
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.webapk.lib.common.identity_service.IIdentityService");
            try {
                strA = ((iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof cw0)) ? new cw0.a.C0055a(iBinder) : (cw0) iInterfaceQueryLocalInterface).a();
            } catch (RemoteException unused) {
                Log.w("WebApkIdentityService", "Failed to get runtime host from the Identity service.");
            }
            aVar.c(strA, TextUtils.equals(strA, packageName));
            return;
        }
        try {
            bundle = context.getPackageManager().getApplicationInfo(this.b, 128).metaData;
        } catch (PackageManager.NameNotFoundException unused2) {
            bundle = null;
        }
        if (bundle != null && bundle.getInt("org.chromium.webapk.shell_apk.shellApkVersion") < 6) {
            strA = bundle.getString("org.chromium.webapk.shell_apk.runtimeHost");
        }
        aVar.c(strA, TextUtils.equals(strA, packageName));
    }
}
