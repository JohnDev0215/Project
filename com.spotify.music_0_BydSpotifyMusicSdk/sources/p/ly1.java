package p;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class ly1 {
    public static final w32 a = new w32();
    public static final Object b = new Object();
    public static qo c = null;

    public static long a(Context context) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        return Build.VERSION.SDK_INT >= 33 ? jy1.a(packageManager, context).lastUpdateTime : packageManager.getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
    }

    public static qo b() {
        qo qoVar = new qo();
        c = qoVar;
        a.h(qoVar);
        return c;
    }

    public static void c(Context context, boolean z) {
        ky1 ky1VarA;
        int i;
        if (z || c == null) {
            synchronized (b) {
                if (!z) {
                    try {
                        if (c != null) {
                            return;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                int i2 = Build.VERSION.SDK_INT;
                if (i2 >= 28 && i2 != 30) {
                    File file = new File(new File("/data/misc/profiles/ref/", context.getPackageName()), "primary.prof");
                    long length = file.length();
                    int i3 = 0;
                    boolean z2 = file.exists() && length > 0;
                    File file2 = new File(new File("/data/misc/profiles/cur/0/", context.getPackageName()), "primary.prof");
                    long length2 = file2.length();
                    boolean z3 = file2.exists() && length2 > 0;
                    try {
                        long jA = a(context);
                        File file3 = new File(context.getFilesDir(), "profileInstalled");
                        if (file3.exists()) {
                            try {
                                ky1VarA = ky1.a(file3);
                            } catch (IOException unused) {
                                b();
                                return;
                            }
                        } else {
                            ky1VarA = null;
                        }
                        if (ky1VarA != null && ky1VarA.c == jA && (i = ky1VarA.b) != 2) {
                            i3 = i;
                        } else if (z2) {
                            i3 = 1;
                        } else if (z3) {
                            i3 = 2;
                        }
                        if (z && z3 && i3 != 1) {
                            i3 = 2;
                        }
                        ky1 ky1Var = new ky1(1, (ky1VarA == null || ky1VarA.b != 2 || i3 != 1 || length >= ky1VarA.d) ? i3 : 3, jA, length2);
                        if (ky1VarA == null || !ky1VarA.equals(ky1Var)) {
                            try {
                                ky1Var.b(file3);
                            } catch (IOException unused2) {
                            }
                        }
                        b();
                        return;
                    } catch (PackageManager.NameNotFoundException unused3) {
                        b();
                        return;
                    }
                }
                b();
            }
        }
    }
}
