package okhttp3.internal.http;

import java.io.EOFException;
import okhttp3.Response;
import okhttp3.internal.Util;
import okio.ByteString;
import p.cs;
import p.fo;
import p.ng2;
import p.qo;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class HttpHeaders {
    public static final ByteString a;
    public static final ByteString b;

    static {
        ByteString byteString = ByteString.k;
        a = qo.p("\"\\");
        b = qo.p("\t ,=");
    }

    public static final boolean a(Response response) {
        if (v03.b(response.c.b, "HEAD")) {
            return false;
        }
        int i = response.l;
        return (((i >= 100 && i < 200) || i == 204 || i == 304) && Util.k(response) == -1 && !ng2.b0("chunked", Response.c(response, "Transfer-Encoding"), true)) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0043, code lost:
    
        r7 = new java.lang.StringBuilder();
        r7.append(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004d, code lost:
    
        if (r6 < 0) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004f, code lost:
    
        if (r6 == 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0053, code lost:
    
        if (r6 == 1) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0055, code lost:
    
        r8 = 0;
        r5 = "=".charAt(0);
        r9 = new char[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005c, code lost:
    
        if (r8 >= r6) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005e, code lost:
    
        r9[r8] = r5;
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0063, code lost:
    
        r5 = new java.lang.String(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0069, code lost:
    
        r5 = "=".toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x006e, code lost:
    
        r5 = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a4, code lost:
    
        throw new java.lang.IllegalArgumentException(("Count 'n' must be non-negative, but was " + r6 + '.').toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0147, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0147, code lost:
    
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void b(p.fo r18, java.util.ArrayList r19) {
        /*
            Method dump skipped, instruction units count: 338
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.HttpHeaders.b(p.fo, java.util.ArrayList):void");
    }

    public static final String c(fo foVar) {
        long jB0 = foVar.b0(b);
        if (jB0 == -1) {
            jB0 = foVar.e;
        }
        if (jB0 != 0) {
            return foVar.P0(jB0, cs.a);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004e  */
    /* JADX WARN: Type inference failed for: r23v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void d(okhttp3.CookieJar r36, okhttp3.HttpUrl r37, okhttp3.Headers r38) {
        /*
            Method dump skipped, instruction units count: 560
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.HttpHeaders.d(okhttp3.CookieJar, okhttp3.HttpUrl, okhttp3.Headers):void");
    }

    public static final boolean e(fo foVar) throws EOFException {
        boolean z = false;
        while (!foVar.Q()) {
            byte bG0 = foVar.G0(0L);
            if (bG0 == 44) {
                foVar.readByte();
                z = true;
            } else {
                if (bG0 != 32 && bG0 != 9) {
                    break;
                }
                foVar.readByte();
            }
        }
        return z;
    }
}
