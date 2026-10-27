package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.incognito.IncognitoTabLauncher;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ig3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ ig3(int i, boolean z) {
        this.e = i;
        this.f = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        boolean z = this.f;
        switch (i) {
            case 0:
                oj2.O("TrustedWebActivity.SplashScreenShown", z);
                break;
            default:
                int i2 = IncognitoTabLauncher.e;
                Object obj = ThreadUtils.a;
                Context context = xz.a;
                PackageManager packageManager = context.getPackageManager();
                ComponentName componentName = new ComponentName(context, (Class<?>) IncognitoTabLauncher.class);
                int i3 = z ? 1 : 2;
                if (packageManager.getComponentEnabledSetting(componentName) != i3) {
                    packageManager.setComponentEnabledSetting(componentName, i3, 1);
                }
                break;
        }
    }
}
