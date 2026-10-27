package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/* JADX INFO: renamed from: xL, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1763xL implements HL {
    public static HL x() {
        Context context = Is.a;
        if (C1223nc.c.e(context) != 0) {
            Ts.f("BarcodeProviderImpl", "Google Play Services not available", new Object[0]);
            return null;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.google.android.gms", 0);
            if (packageInfo.versionCode >= 19742000) {
                return new C1763xL();
            }
            Ts.f("BarcodeProviderImpl", "Detection disabled (%s < 19.7.42)", packageInfo.versionName);
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            Ts.f("BarcodeProviderImpl", "Google Play Services not available", new Object[0]);
            return null;
        }
    }

    @Override // defpackage.HL
    public void F(GL gl) {
        ((NL) gl).a(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13});
    }

    @Override // defpackage.LI
    public void I(WJ wj) {
    }

    @Override // defpackage.InterfaceC0822gJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // defpackage.HL
    public void f(C0934iJ c0934iJ, C0490aM c0490aM) {
        AbstractC0599cJ abstractC0599cJ = FL.n;
        ZL.a.b(new C1708wL(c0490aM), c0934iJ);
    }
}
