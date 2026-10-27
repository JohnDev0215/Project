package defpackage;

import android.graphics.Path;

/* JADX INFO: renamed from: q2, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1360q2 {
    public static boolean a(C1305p2[] c1305p2Arr, C1305p2[] c1305p2Arr2) {
        if (c1305p2Arr == null || c1305p2Arr2 == null || c1305p2Arr.length != c1305p2Arr2.length) {
            return false;
        }
        for (int i = 0; i < c1305p2Arr.length; i++) {
            if (c1305p2Arr[i].a != c1305p2Arr2[i].a || c1305p2Arr[i].b.length != c1305p2Arr2[i].b.length) {
                return false;
            }
        }
        return true;
    }

    public static float[] b(float[] fArr, int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = fArr.length;
        if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = i2 - i;
        int iMin = Math.min(i3, length - i);
        float[] fArr2 = new float[i3];
        System.arraycopy(fArr, i, fArr2, 0, iMin);
        return fArr2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x008b, code lost:
    
        if (r13 == false) goto L42;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0098 A[Catch: NumberFormatException -> 0x00ba, LOOP:3: B:29:0x006d->B:49:0x0098, LOOP_END, TryCatch #0 {NumberFormatException -> 0x00ba, blocks: (B:26:0x005a, B:29:0x006d, B:31:0x0073, B:36:0x0081, B:49:0x0098, B:51:0x009d, B:54:0x00ad, B:56:0x00b2), top: B:71:0x005a }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x009d A[Catch: NumberFormatException -> 0x00ba, TryCatch #0 {NumberFormatException -> 0x00ba, blocks: (B:26:0x005a, B:29:0x006d, B:31:0x0073, B:36:0x0081, B:49:0x0098, B:51:0x009d, B:54:0x00ad, B:56:0x00b2), top: B:71:0x005a }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ad A[Catch: NumberFormatException -> 0x00ba, TryCatch #0 {NumberFormatException -> 0x00ba, blocks: (B:26:0x005a, B:29:0x006d, B:31:0x0073, B:36:0x0081, B:49:0x0098, B:51:0x009d, B:54:0x00ad, B:56:0x00b2), top: B:71:0x005a }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0097 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.C1305p2[] c(java.lang.String r16) {
        /*
            Method dump skipped, instruction units count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC1360q2.c(java.lang.String):p2[]");
    }

    public static Path d(String str) {
        Path path = new Path();
        C1305p2[] c1305p2ArrC = c(str);
        if (c1305p2ArrC == null) {
            return null;
        }
        try {
            C1305p2.b(c1305p2ArrC, path);
            return path;
        } catch (RuntimeException e) {
            throw new RuntimeException(Z7.c("Error in parsing ", str), e);
        }
    }

    public static C1305p2[] e(C1305p2[] c1305p2Arr) {
        if (c1305p2Arr == null) {
            return null;
        }
        C1305p2[] c1305p2Arr2 = new C1305p2[c1305p2Arr.length];
        for (int i = 0; i < c1305p2Arr.length; i++) {
            c1305p2Arr2[i] = new C1305p2(c1305p2Arr[i]);
        }
        return c1305p2Arr2;
    }
}
