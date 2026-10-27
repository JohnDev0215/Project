package p;

import okhttp3.Headers;

/* JADX INFO: loaded from: classes.dex */
public abstract class dp {
    public static Headers a(Headers headers, Headers headers2) {
        Headers.Builder builder = new Headers.Builder();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String strB = headers.b(i);
            String strD = headers.d(i);
            if ((!ng2.b0("Warning", strB, true) || !ng2.g0(strD, "1", false)) && (ng2.b0("Content-Length", strB, true) || ng2.b0("Content-Encoding", strB, true) || ng2.b0("Content-Type", strB, true) || !b(strB) || headers2.a(strB) == null)) {
                builder.d(strB, strD);
            }
        }
        int size2 = headers2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            String strB2 = headers2.b(i2);
            if (!ng2.b0("Content-Length", strB2, true) && !ng2.b0("Content-Encoding", strB2, true) && !ng2.b0("Content-Type", strB2, true) && b(strB2)) {
                builder.d(strB2, headers2.d(i2));
            }
        }
        return builder.e();
    }

    public static boolean b(String str) {
        return (ng2.b0("Connection", str, true) || ng2.b0("Keep-Alive", str, true) || ng2.b0("Proxy-Authenticate", str, true) || ng2.b0("Proxy-Authorization", str, true) || ng2.b0("TE", str, true) || ng2.b0("Trailers", str, true) || ng2.b0("Transfer-Encoding", str, true) || ng2.b0("Upgrade", str, true)) ? false : true;
    }
}
