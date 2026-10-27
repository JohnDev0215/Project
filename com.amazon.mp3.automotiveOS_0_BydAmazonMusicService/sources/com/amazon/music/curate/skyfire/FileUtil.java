package com.amazon.music.curate.skyfire;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class FileUtil {
    private static final String TAG = "FileUtil";
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class.getSimpleName());

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:49:0x007f A[Catch: Exception -> 0x007b, TryCatch #2 {Exception -> 0x007b, blocks: (B:45:0x0077, B:49:0x007f, B:51:0x0084), top: B:57:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0084 A[Catch: Exception -> 0x007b, TRY_LEAVE, TryCatch #2 {Exception -> 0x007b, blocks: (B:45:0x0077, B:49:0x007f, B:51:0x0084), top: B:57:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0077 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r7v12, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8, types: [java.io.InputStreamReader, java.io.Reader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String readAssetFile(android.content.Context r7, java.lang.String r8) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "failed to close streams"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 0
            android.content.res.Resources r7 = r7.getResources()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L4a
            android.content.res.AssetManager r7 = r7.getAssets()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L4a
            java.io.InputStream r7 = r7.open(r8)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L4a
            java.io.InputStreamReader r8 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L41
            r8.<init>(r7)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L41
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L39
            r3.<init>(r8)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L39
        L1e:
            java.lang.String r2 = r3.readLine()     // Catch: java.lang.Exception -> L34 java.lang.Throwable -> L73
            if (r2 == 0) goto L28
            r1.append(r2)     // Catch: java.lang.Exception -> L34 java.lang.Throwable -> L73
            goto L1e
        L28:
            r8.close()     // Catch: java.lang.Exception -> L5c
            if (r7 == 0) goto L30
            r7.close()     // Catch: java.lang.Exception -> L5c
        L30:
            r3.close()     // Catch: java.lang.Exception -> L5c
            goto L6e
        L34:
            r2 = move-exception
            goto L4f
        L36:
            r1 = move-exception
            r3 = r2
            goto L74
        L39:
            r3 = move-exception
            r6 = r3
            r3 = r2
            r2 = r6
            goto L4f
        L3e:
            r1 = move-exception
            r3 = r2
            goto L75
        L41:
            r8 = move-exception
            r3 = r2
            r2 = r8
            r8 = r3
            goto L4f
        L46:
            r1 = move-exception
            r7 = r2
            r3 = r7
            goto L75
        L4a:
            r7 = move-exception
            r8 = r2
            r3 = r8
            r2 = r7
            r7 = r3
        L4f:
            org.slf4j.Logger r4 = com.amazon.music.curate.skyfire.FileUtil.logger     // Catch: java.lang.Throwable -> L73
            java.lang.String r5 = "failed to read asset"
            r4.error(r5, r2)     // Catch: java.lang.Throwable -> L73
            if (r8 == 0) goto L5e
            r8.close()     // Catch: java.lang.Exception -> L5c
            goto L5e
        L5c:
            r7 = move-exception
            goto L69
        L5e:
            if (r7 == 0) goto L63
            r7.close()     // Catch: java.lang.Exception -> L5c
        L63:
            if (r3 == 0) goto L6e
            r3.close()     // Catch: java.lang.Exception -> L5c
            goto L6e
        L69:
            org.slf4j.Logger r8 = com.amazon.music.curate.skyfire.FileUtil.logger
            r8.error(r0, r7)
        L6e:
            java.lang.String r7 = r1.toString()
            return r7
        L73:
            r1 = move-exception
        L74:
            r2 = r8
        L75:
            if (r2 == 0) goto L7d
            r2.close()     // Catch: java.lang.Exception -> L7b
            goto L7d
        L7b:
            r7 = move-exception
            goto L88
        L7d:
            if (r7 == 0) goto L82
            r7.close()     // Catch: java.lang.Exception -> L7b
        L82:
            if (r3 == 0) goto L8d
            r3.close()     // Catch: java.lang.Exception -> L7b
            goto L8d
        L88:
            org.slf4j.Logger r8 = com.amazon.music.curate.skyfire.FileUtil.logger
            r8.error(r0, r7)
        L8d:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.curate.skyfire.FileUtil.readAssetFile(android.content.Context, java.lang.String):java.lang.String");
    }
}
