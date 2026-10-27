package org.chromium.ui.base;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ResourceBundle {
    public static String[] a;
    public static String[] b;

    /* JADX WARN: Removed duplicated region for block: B:38:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getLocalePakResourcePath(java.lang.String r7, boolean r8) {
        /*
            java.lang.String[] r0 = org.chromium.ui.base.ResourceBundle.b
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            int r0 = java.util.Arrays.binarySearch(r0, r7)
            if (r0 >= 0) goto Ld
            return r1
        Ld:
            r0 = 1
            r2 = 2
            r3 = 0
            if (r8 == 0) goto L89
            java.lang.String r8 = "en-US"
            boolean r8 = r7.equals(r8)
            if (r8 == 0) goto L1e
            java.lang.String r8 = "assets/fallback-locales/"
            goto L8b
        L1e:
            r8 = 45
            int r8 = r7.indexOf(r8)
            if (r8 >= 0) goto L28
            r8 = r7
            goto L2c
        L28:
            java.lang.String r8 = r7.substring(r3, r8)
        L2c:
            int r4 = r8.hashCode()
            r5 = 3325(0xcfd, float:4.66E-42)
            r6 = 3
            if (r4 == r5) goto L61
            r5 = 3355(0xd1b, float:4.701E-42)
            if (r4 == r5) goto L57
            r5 = 3856(0xf10, float:5.403E-42)
            if (r4 == r5) goto L4d
            r5 = 101385(0x18c09, float:1.4207E-40)
            if (r4 == r5) goto L43
            goto L6b
        L43:
            java.lang.String r4 = "fil"
            boolean r4 = r8.equals(r4)
            if (r4 == 0) goto L6b
            r4 = 3
            goto L6c
        L4d:
            java.lang.String r4 = "yi"
            boolean r4 = r8.equals(r4)
            if (r4 == 0) goto L6b
            r4 = 1
            goto L6c
        L57:
            java.lang.String r4 = "id"
            boolean r4 = r8.equals(r4)
            if (r4 == 0) goto L6b
            r4 = 2
            goto L6c
        L61:
            java.lang.String r4 = "he"
            boolean r4 = r8.equals(r4)
            if (r4 == 0) goto L6b
            r4 = 0
            goto L6c
        L6b:
            r4 = -1
        L6c:
            if (r4 == 0) goto L7e
            if (r4 == r0) goto L7b
            if (r4 == r2) goto L78
            if (r4 == r6) goto L75
            goto L80
        L75:
            java.lang.String r8 = "tl"
            goto L80
        L78:
            java.lang.String r8 = "in"
            goto L80
        L7b:
            java.lang.String r8 = "ji"
            goto L80
        L7e:
            java.lang.String r8 = "iw"
        L80:
            java.lang.String r4 = "assets/locales#lang_"
            java.lang.String r5 = "/"
            java.lang.String r8 = defpackage.Z7.d(r4, r8, r5)
            goto L8b
        L89:
            java.lang.String r8 = "assets/stored-locales/"
        L8b:
            java.lang.String r4 = ".pak"
            java.lang.String r7 = defpackage.Z7.d(r8, r7, r4)
            android.content.Context r8 = defpackage.Is.a
            android.content.res.AssetManager r8 = r8.getAssets()
            android.content.res.AssetFileDescriptor r8 = r8.openNonAssetFd(r7)     // Catch: java.io.IOException -> La1
            if (r8 == 0) goto La0
            r8.close()     // Catch: java.io.IOException -> La1
        La0:
            return r7
        La1:
            r8 = move-exception
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r3] = r7
            r2[r0] = r8
            java.lang.String r7 = "ResourceBundle"
            java.lang.String r8 = "Error while loading asset %s: %s"
            defpackage.Ts.a(r7, r8, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.ui.base.ResourceBundle.getLocalePakResourcePath(java.lang.String, boolean):java.lang.String");
    }

    public static void setNoAvailableLocalePaks() {
        a = new String[0];
        b = new String[0];
    }
}
