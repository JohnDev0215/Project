package b.b.m.f.e;

import android.webkit.WebView;

/* JADX INFO: compiled from: BridgeUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0056 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r4, java.lang.String r5) throws java.lang.Throwable {
        /*
            r0 = 0
            android.content.res.AssetManager r4 = r4.getAssets()     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            java.io.InputStream r4 = r4.open(r5)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L52
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L52
            r1.<init>(r4)     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L52
            r5.<init>(r1)     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L52
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L52
            r1.<init>()     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L52
        L18:
            java.lang.String r2 = r5.readLine()     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L52
            if (r2 == 0) goto L29
            java.lang.String r3 = "^\\s*\\/\\/.*"
            boolean r3 = r2.matches(r3)     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L52
            if (r3 != 0) goto L29
            r1.append(r2)     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L52
        L29:
            if (r2 != 0) goto L18
            r5.close()     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L52
            r4.close()     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L52
            java.lang.String r5 = r1.toString()     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L52
            r4.close()     // Catch: java.io.IOException -> L39
            goto L3d
        L39:
            r4 = move-exception
            r4.printStackTrace()
        L3d:
            return r5
        L3e:
            r5 = move-exception
            goto L44
        L40:
            r5 = move-exception
            goto L54
        L42:
            r5 = move-exception
            r4 = r0
        L44:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L52
            if (r4 == 0) goto L51
            r4.close()     // Catch: java.io.IOException -> L4d
            goto L51
        L4d:
            r4 = move-exception
            r4.printStackTrace()
        L51:
            return r0
        L52:
            r5 = move-exception
            r0 = r4
        L54:
            if (r0 == 0) goto L5e
            r0.close()     // Catch: java.io.IOException -> L5a
            goto L5e
        L5a:
            r4 = move-exception
            r4.printStackTrace()
        L5e:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.m.f.e.b.a(android.content.Context, java.lang.String):java.lang.String");
    }

    public static String b(String str) {
        if (str.startsWith("yy://return/_fetchQueue/")) {
            return str.replace("yy://return/_fetchQueue/", "");
        }
        String[] strArrSplit = str.replace("yy://return/", "").split("/");
        if (strArrSplit.length < 2) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < strArrSplit.length; i++) {
            sb.append(strArrSplit[i]);
        }
        return sb.toString();
    }

    public static String c(String str) {
        String[] strArrSplit = str.replace("yy://return/", "").split("/");
        if (strArrSplit.length >= 1) {
            return strArrSplit[0];
        }
        return null;
    }

    public static String d(String str) {
        return str.replace("javascript:WebViewJavascriptBridge.", "").replaceAll("\\(.*\\);", "");
    }

    public static void e(WebView webView, String str) throws Throwable {
        webView.loadUrl("javascript:" + a(webView.getContext(), str));
    }
}
