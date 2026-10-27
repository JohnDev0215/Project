package org.chromium.android_webview;

import J.N;
import android.net.Uri;
import android.util.Log;
import android.util.TypedValue;
import defpackage.AbstractC0229Mo;
import defpackage.Is;
import defpackage.Z7;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AndroidProtocolHandler {
    public static Class a(String str, String str2) {
        return Is.a.getClassLoader().loadClass(str + ".R$" + str2);
    }

    public static int b(String str, String str2) throws ClassNotFoundException {
        Class clsA;
        int iLastIndexOf;
        String packageName = Is.a.getPackageName();
        try {
            clsA = a(packageName, str);
        } catch (ClassNotFoundException e) {
            String strSubstring = packageName;
            Class clsA2 = null;
            while (clsA2 == null) {
                strSubstring = (strSubstring == null || (iLastIndexOf = strSubstring.lastIndexOf(46)) == -1) ? null : strSubstring.substring(0, iLastIndexOf);
                if (strSubstring == null) {
                    throw e;
                }
                try {
                    clsA2 = a(strSubstring, str);
                } catch (ClassNotFoundException unused) {
                }
            }
            clsA = clsA2;
        }
        return clsA.getField(str2).getInt(null);
    }

    public static int c(int i) {
        TypedValue typedValue = new TypedValue();
        Is.a.getResources().getValue(i, typedValue, true);
        return typedValue.type;
    }

    public static InputStream d(Uri uri) {
        try {
            return Is.a.getAssets().open(uri.getPath().replaceFirst(N.Myi_XSsy(), ""), 2);
        } catch (IOException unused) {
            Log.e("AndroidProtocolHandler", "Unable to open asset URL: " + uri);
            return null;
        }
    }

    public static InputStream e(Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() != 3) {
            Log.e("AndroidProtocolHandler", "Incorrect resource path: " + uri);
            return null;
        }
        String str = pathSegments.get(0);
        String str2 = pathSegments.get(1);
        String str3 = pathSegments.get(2);
        if (!Z7.d("/", str, "/").equals(N.MZEbjzws())) {
            StringBuilder sbF = Z7.f("Resource path does not start with ");
            sbF.append(N.MZEbjzws());
            sbF.append(": ");
            sbF.append(uri);
            Log.e("AndroidProtocolHandler", sbF.toString());
            return null;
        }
        try {
            int iB = b(str2, str3.split("\\.")[0]);
            if (c(iB) == 3) {
                return Is.a.getResources().openRawResource(iB);
            }
            Log.e("AndroidProtocolHandler", "Asset not of type string: " + uri);
            return null;
        } catch (ClassNotFoundException e) {
            Log.e("AndroidProtocolHandler", "Unable to open resource URL: " + uri, e);
            return null;
        } catch (IllegalAccessException e2) {
            Log.e("AndroidProtocolHandler", "Unable to open resource URL: " + uri, e2);
            return null;
        } catch (NoSuchFieldException e3) {
            Log.e("AndroidProtocolHandler", "Unable to open resource URL: " + uri, e3);
            return null;
        }
    }

    public static Uri f(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        Uri uri = Uri.parse(str);
        String path = uri.getPath();
        if (path != null && !path.isEmpty() && !path.equals("/")) {
            return uri;
        }
        Log.e("AndroidProtocolHandler", "URL does not have a path: " + str);
        return null;
    }

    public static String getMimeType(InputStream inputStream, String str) {
        Uri uriF = f(str);
        if (uriF == null) {
            return null;
        }
        try {
            String path = uriF.getPath();
            if (uriF.getScheme().equals("content")) {
                String type = Is.a.getContentResolver().getType(uriF);
                if (type == null) {
                    AbstractC0229Mo.b(0);
                } else {
                    AbstractC0229Mo.b(1);
                }
                return type;
            }
            if (uriF.getScheme().equals("file") && path.startsWith(N.Myi_XSsy())) {
                String strGuessContentTypeFromName = URLConnection.guessContentTypeFromName(path);
                if (strGuessContentTypeFromName != null) {
                    AbstractC0229Mo.b(3);
                    return strGuessContentTypeFromName;
                }
                AbstractC0229Mo.b(2);
            }
            try {
                String strGuessContentTypeFromStream = URLConnection.guessContentTypeFromStream(inputStream);
                if (strGuessContentTypeFromStream == null) {
                    AbstractC0229Mo.b(5);
                } else {
                    AbstractC0229Mo.b(6);
                }
                return strGuessContentTypeFromStream;
            } catch (IOException unused) {
                AbstractC0229Mo.b(7);
                return null;
            }
        } catch (Exception unused2) {
            Log.e("AndroidProtocolHandler", "Unable to get mime type" + str);
            AbstractC0229Mo.b(4);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.io.InputStream open(java.lang.String r5) {
        /*
            java.lang.String r0 = "AndroidProtocolHandler"
            android.net.Uri r5 = f(r5)
            r1 = 0
            if (r5 != 0) goto La
            return r1
        La:
            java.lang.String r2 = r5.getScheme()     // Catch: java.lang.Exception -> L65
            java.lang.String r3 = "file"
            boolean r2 = r2.equals(r3)     // Catch: java.lang.Exception -> L65
            if (r2 == 0) goto L38
            java.lang.String r2 = r5.getPath()     // Catch: java.lang.Exception -> L65
            java.lang.String r3 = J.N.Myi_XSsy()     // Catch: java.lang.Exception -> L65
            boolean r3 = r2.startsWith(r3)     // Catch: java.lang.Exception -> L65
            if (r3 == 0) goto L29
            java.io.InputStream r2 = d(r5)     // Catch: java.lang.Exception -> L65
            goto L7a
        L29:
            java.lang.String r3 = J.N.MZEbjzws()     // Catch: java.lang.Exception -> L65
            boolean r2 = r2.startsWith(r3)     // Catch: java.lang.Exception -> L65
            if (r2 == 0) goto L63
            java.io.InputStream r2 = e(r5)     // Catch: java.lang.Exception -> L65
            goto L7a
        L38:
            java.lang.String r2 = r5.getScheme()     // Catch: java.lang.Exception -> L65
            java.lang.String r3 = "content"
            boolean r2 = r2.equals(r3)     // Catch: java.lang.Exception -> L65
            if (r2 == 0) goto L63
            android.content.Context r2 = defpackage.Is.a     // Catch: java.lang.Exception -> L4f
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Exception -> L4f
            java.io.InputStream r2 = r2.openInputStream(r5)     // Catch: java.lang.Exception -> L4f
            goto L7a
        L4f:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L65
            r2.<init>()     // Catch: java.lang.Exception -> L65
            java.lang.String r3 = "Unable to open content URL: "
            r2.append(r3)     // Catch: java.lang.Exception -> L65
            r2.append(r5)     // Catch: java.lang.Exception -> L65
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> L65
            android.util.Log.e(r0, r2)     // Catch: java.lang.Exception -> L65
        L63:
            r2 = r1
            goto L7a
        L65:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Error opening inputstream: "
            r2.append(r3)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            android.util.Log.e(r0, r2)
            goto L63
        L7a:
            if (r2 == 0) goto Lb1
            java.lang.String r3 = r5.getLastPathSegment()
            java.lang.String r4 = ".svgz"
            boolean r3 = r3.endsWith(r4)
            if (r3 == 0) goto Lb1
            java.util.zip.GZIPInputStream r3 = new java.util.zip.GZIPInputStream     // Catch: java.io.IOException -> L8f
            r3.<init>(r2)     // Catch: java.io.IOException -> L8f
            r2 = r3
            goto Lb1
        L8f:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Error decompressing "
            r3.append(r4)
            r3.append(r5)
            java.lang.String r5 = " - "
            r3.append(r5)
            java.lang.String r5 = r2.getMessage()
            r3.append(r5)
            java.lang.String r5 = r3.toString()
            android.util.Log.e(r0, r5)
            return r1
        Lb1:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.android_webview.AndroidProtocolHandler.open(java.lang.String):java.io.InputStream");
    }
}
