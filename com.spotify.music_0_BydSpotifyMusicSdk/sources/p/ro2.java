package p;

import android.content.res.Resources;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public abstract class ro2 {
    public static final hk0 a;
    public static final ae1 b;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            a = new vo2(8);
        } else if (i >= 28) {
            a = new uo2();
        } else {
            a = new to2();
        }
        b = new ae1(16);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Typeface a(android.content.Context r15, p.yv0 r16, android.content.res.Resources r17, int r18, java.lang.String r19, int r20, int r21, p.zu r22, boolean r23) {
        /*
            Method dump skipped, instruction units count: 416
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ro2.a(android.content.Context, p.yv0, android.content.res.Resources, int, java.lang.String, int, int, p.zu, boolean):android.graphics.Typeface");
    }

    public static String b(Resources resources, int i, String str, int i2, int i3) {
        return resources.getResourcePackageName(i) + '-' + str + '-' + i2 + '-' + i + '-' + i3;
    }
}
