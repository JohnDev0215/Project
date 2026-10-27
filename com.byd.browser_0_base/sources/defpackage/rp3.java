package defpackage;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import defpackage.pw0;
import defpackage.tp3;
import org.chromium.webapk.lib.runtime_library.IWebApkApi;

/* JADX INFO: compiled from: WebApkServiceClient.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rp3 {
    public static rp3 b;
    public final tp3 a = new tp3(rh3.a, "android.intent.category.WEBAPK_API", null);

    /* JADX INFO: compiled from: WebApkServiceClient.java */
    @FunctionalInterface
    public interface a extends tp3.b {
        @Override // tp3.b
        default void a(IBinder iBinder) {
            if (iBinder == null) {
                oj2.O("WebApk.WebApkService.BindSuccess", false);
                return;
            }
            try {
                int i = pw0.a.e;
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IWebApkApi.DESCRIPTOR);
                b((iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof pw0)) ? new pw0.a.C0163a(iBinder) : (pw0) iInterfaceQueryLocalInterface);
                oj2.O("WebApk.WebApkService.BindSuccess", true);
            } catch (RemoteException e) {
                Log.w("cr_WebApkServiceClient", "WebApkAPI use failed.", e);
            }
        }

        void b(pw0 pw0Var);
    }

    public static rp3 a() {
        if (b == null) {
            b = new rp3();
        }
        return b;
    }
}
