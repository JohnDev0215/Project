package p;

import android.content.Context;
import android.content.pm.PackageManager;

/* JADX INFO: loaded from: classes.dex */
public final class b23 {
    public static final tz2 c = new tz2("PackageStateCache", 0);
    public final Context a;
    public int b = -1;

    public b23(Context context) {
        this.a = context;
    }

    public final synchronized int a() {
        if (this.b == -1) {
            try {
                this.b = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException unused) {
                c.c("The current version of the app could not be retrieved", new Object[0]);
            }
        }
        return this.b;
    }
}
