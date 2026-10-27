package defpackage;

import android.os.Build;
import android.os.ext.SdkExtensions;

/* JADX INFO: compiled from: BuildCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class yj {
    public static final /* synthetic */ int a = 0;

    /* JADX INFO: compiled from: BuildCompat.java */
    public static final class a {
        public static final /* synthetic */ int a = 0;

        static {
            SdkExtensions.getExtensionVersion(30);
            SdkExtensions.getExtensionVersion(31);
            SdkExtensions.getExtensionVersion(33);
            SdkExtensions.getExtensionVersion(1000000);
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            int i2 = a.a;
        }
        if (i >= 30) {
            int i3 = a.a;
        }
        if (i >= 30) {
            int i4 = a.a;
        }
        if (i >= 30) {
            int i5 = a.a;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a() {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 33
            r2 = 1
            if (r0 >= r1) goto L30
            r1 = 32
            r3 = 0
            if (r0 < r1) goto L2f
            java.lang.String r0 = android.os.Build.VERSION.CODENAME
            java.lang.String r1 = "REL"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L17
            goto L2b
        L17:
            java.util.Locale r1 = java.util.Locale.ROOT
            java.lang.String r0 = r0.toUpperCase(r1)
            java.lang.String r4 = "Tiramisu"
            java.lang.String r1 = r4.toUpperCase(r1)
            int r0 = r0.compareTo(r1)
            if (r0 < 0) goto L2b
            r0 = r2
            goto L2c
        L2b:
            r0 = r3
        L2c:
            if (r0 == 0) goto L2f
            goto L30
        L2f:
            r2 = r3
        L30:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yj.a():boolean");
    }
}
