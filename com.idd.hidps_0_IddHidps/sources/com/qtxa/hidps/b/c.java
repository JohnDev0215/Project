package com.qtxa.hidps.b;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00f9  */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.os.IBinder] */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.Closeable] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.lang.Integer> b() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qtxa.hidps.b.c.b():java.util.List");
    }

    private static a c(a aVar, String str, String str2) {
        if (str == null || str.isEmpty()) {
            return aVar;
        }
        if ((!str.contains("packageName=") || !str.contains("processName=")) && !str.contains("state=")) {
            return aVar;
        }
        String strSubstring = null;
        String strSubstring2 = null;
        for (String str3 : str.split(" ")) {
            if (str3.startsWith("packageName=")) {
                strSubstring = str3.substring(12);
            } else if (str3.startsWith("processName=")) {
                strSubstring2 = str3.substring(12);
            } else if (str3.startsWith("state=")) {
                String strSubstring3 = str3.substring(6);
                if (aVar != null) {
                    aVar.g(strSubstring3, str2);
                }
            }
        }
        return (strSubstring == null || strSubstring2 == null) ? aVar : new a(strSubstring, strSubstring2);
    }

    public static List<a> d() {
        return e(false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0105  */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.os.IBinder] */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.Object, java.util.List<com.qtxa.hidps.b.a>] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.qtxa.hidps.b.a> e(boolean r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qtxa.hidps.b.c.e(boolean):java.util.List");
    }
}
