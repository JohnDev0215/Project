package com.byd.util;

import android.webkit.MimeTypeMap;

/* JADX INFO: loaded from: classes2.dex */
public class m {
    public static String a(String str) {
        int iLastIndexOf;
        return (str == null || (iLastIndexOf = str.lastIndexOf(".")) == -1) ? "" : str.substring(iLastIndexOf + 1).toLowerCase();
    }

    public static boolean b(String str) {
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
        return mimeTypeFromExtension != null && mimeTypeFromExtension.startsWith("image");
    }

    public static boolean c(String str) {
        String strA = a(str.toLowerCase());
        if ("".equals(strA)) {
            return false;
        }
        return b(strA);
    }

    public static boolean d(String str) {
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
        return mimeTypeFromExtension != null && mimeTypeFromExtension.startsWith("video");
    }

    public static boolean e(String str) {
        String strA = a(str.toLowerCase());
        if ("".equals(strA)) {
            return false;
        }
        return d(strA);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0064, code lost:
    
        r2[0] = java.lang.Double.parseDouble(r7.substring(0, r9));
        r2[1] = java.lang.Double.parseDouble(r7.substring(r9));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static double[] f(android.content.Context r7, java.lang.String r8, android.net.Uri r9) throws java.io.IOException {
        /*
            java.lang.String r0 = ""
            android.media.MediaMetadataRetriever r1 = new android.media.MediaMetadataRetriever
            r1.<init>()
            r2 = 0
            if (r9 == 0) goto Le
            r1.setDataSource(r7, r9)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            goto L11
        Le:
            r1.setDataSource(r8)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
        L11:
            r7 = 23
            java.lang.String r7 = r1.extractMetadata(r7)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            r8.<init>()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            java.lang.String r9 = "readVideoLocation -> location info :"
            r8.append(r9)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            java.lang.String r9 = java.lang.String.valueOf(r7)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            r3 = 4
            r4 = 0
            java.lang.String r9 = r9.substring(r4, r3)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            r8.append(r9)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            java.lang.String r9 = "..."
            r8.append(r9)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            com.byd.util.w.c(r8)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            if (r7 == 0) goto L82
            boolean r8 = r0.equals(r7)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            if (r8 != 0) goto L82
            java.lang.String r8 = "/"
            java.lang.String r7 = r7.replaceAll(r8, r0)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            r8 = 2
            double[] r2 = new double[r8]     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            int r8 = r7.length()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            r9 = r4
        L50:
            if (r9 >= r8) goto L82
            char r0 = r7.charAt(r9)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            r3 = 43
            if (r0 == r3) goto L62
            char r0 = r7.charAt(r9)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            r3 = 45
            if (r0 != r3) goto L7a
        L62:
            if (r9 <= 0) goto L7a
            java.lang.String r8 = r7.substring(r4, r9)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            double r5 = java.lang.Double.parseDouble(r8)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            r2[r4] = r5     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            java.lang.String r7 = r7.substring(r9)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            double r7 = java.lang.Double.parseDouble(r7)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            r9 = 1
            r2[r9] = r7     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L82
            goto L82
        L7a:
            int r9 = r9 + 1
            goto L50
        L7d:
            r7 = move-exception
            r1.release()
            throw r7
        L82:
            r1.release()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.util.m.f(android.content.Context, java.lang.String, android.net.Uri):double[]");
    }
}
