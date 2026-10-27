package defpackage;

import android.content.Context;
import android.net.wifi.WifiManager;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SK {
    public final Context a;
    public final Object b = new Object();
    public boolean c;
    public boolean d;
    public WifiManager e;

    public SK(Context context) {
        this.a = context;
    }

    public final boolean a() {
        if (this.c) {
            return this.d;
        }
        boolean z = this.a.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", this.a.getPackageName()) == 0;
        this.d = z;
        this.e = z ? (WifiManager) this.a.getSystemService("wifi") : null;
        this.c = true;
        return this.d;
    }
}
