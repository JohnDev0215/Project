package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: compiled from: ExternalAuthUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zi0 {
    public static final zi0 b = new zi0();
    public final a8 a = new a8(0);

    public final boolean a(int i, Context context, String str) {
        boolean z;
        boolean z2 = (i & 1) != 0;
        boolean z3 = (i & 2) != 0;
        String[] packagesForUid = context.getApplicationContext().getPackageManager().getPackagesForUid(Binder.getCallingUid());
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        boolean z4 = false;
        for (String str2 : packagesForUid) {
            if (TextUtils.isEmpty(str) || str2.equals(str)) {
                if (z2) {
                    b();
                } else {
                    if (z3) {
                        try {
                            if ((packageManager.getApplicationInfo(str2, 1).flags & 1) == 0) {
                                throw new SecurityException();
                            }
                            z = true;
                        } catch (PackageManager.NameNotFoundException unused) {
                            Log.e("cr_ExternalAuthUtils", "Package with name " + str2 + " not found");
                            z = false;
                        } catch (SecurityException unused2) {
                            Log.e("cr_ExternalAuthUtils", "Caller with package name " + str2 + " is not in the system build");
                            z = false;
                        }
                        if (!z) {
                        }
                    }
                    z4 = true;
                }
                return false;
            }
        }
        return z4;
    }

    public final void b() {
        this.a.getClass();
    }
}
