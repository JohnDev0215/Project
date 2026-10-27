package okhttp3.internal.cache;

import java.util.Date;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.http.DatesKt;
import p.ng2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class CacheStrategy {
    public static final Companion c = new Companion(0);
    public final Request a;
    public final Response b;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0039  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static boolean a(okhttp3.Request r3, okhttp3.Response r4) {
            /*
                java.lang.String r0 = "response"
                p.v03.j(r4, r0)
                java.lang.String r0 = "request"
                p.v03.j(r3, r0)
                r0 = 200(0xc8, float:2.8E-43)
                r1 = 0
                int r2 = r4.l
                if (r2 == r0) goto L5b
                r0 = 410(0x19a, float:5.75E-43)
                if (r2 == r0) goto L5b
                r0 = 414(0x19e, float:5.8E-43)
                if (r2 == r0) goto L5b
                r0 = 501(0x1f5, float:7.02E-43)
                if (r2 == r0) goto L5b
                r0 = 203(0xcb, float:2.84E-43)
                if (r2 == r0) goto L5b
                r0 = 204(0xcc, float:2.86E-43)
                if (r2 == r0) goto L5b
                r0 = 307(0x133, float:4.3E-43)
                if (r2 == r0) goto L39
                r0 = 308(0x134, float:4.32E-43)
                if (r2 == r0) goto L5b
                r0 = 404(0x194, float:5.66E-43)
                if (r2 == r0) goto L5b
                r0 = 405(0x195, float:5.68E-43)
                if (r2 == r0) goto L5b
                switch(r2) {
                    case 300: goto L5b;
                    case 301: goto L5b;
                    case 302: goto L39;
                    default: goto L38;
                }
            L38:
                return r1
            L39:
                java.lang.String r0 = "Expires"
                java.lang.String r0 = okhttp3.Response.c(r4, r0)
                if (r0 != 0) goto L5b
                okhttp3.CacheControl r0 = r4.b()
                r2 = -1
                int r0 = r0.c
                if (r0 != r2) goto L5b
                okhttp3.CacheControl r0 = r4.b()
                boolean r0 = r0.f
                if (r0 != 0) goto L5b
                okhttp3.CacheControl r0 = r4.b()
                boolean r0 = r0.e
                if (r0 != 0) goto L5b
                return r1
            L5b:
                okhttp3.CacheControl r4 = r4.b()
                boolean r4 = r4.b
                if (r4 != 0) goto L6c
                okhttp3.CacheControl r3 = r3.a()
                boolean r3 = r3.b
                if (r3 != 0) goto L6c
                r1 = 1
            L6c:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.CacheStrategy.Companion.a(okhttp3.Request, okhttp3.Response):boolean");
        }
    }

    public static final class Factory {
        public final long a;
        public final Request b;
        public final Response c;
        public final Date d;
        public final String e;
        public final Date f;
        public final String g;
        public final Date h;
        public final long i;
        public final long j;
        public final String k;
        public final int l;

        public Factory(long j, Request request, Response response) {
            v03.j(request, "request");
            this.a = j;
            this.b = request;
            this.c = response;
            this.l = -1;
            if (response != null) {
                this.i = response.s;
                this.j = response.t;
                Headers headers = response.n;
                int size = headers.size();
                for (int i = 0; i < size; i++) {
                    String strB = headers.b(i);
                    String strD = headers.d(i);
                    if (ng2.b0(strB, "Date", true)) {
                        this.d = DatesKt.a(strD);
                        this.e = strD;
                    } else if (ng2.b0(strB, "Expires", true)) {
                        this.h = DatesKt.a(strD);
                    } else if (ng2.b0(strB, "Last-Modified", true)) {
                        this.f = DatesKt.a(strD);
                        this.g = strD;
                    } else if (ng2.b0(strB, "ETag", true)) {
                        this.k = strD;
                    } else if (ng2.b0(strB, "Age", true)) {
                        this.l = Util.A(-1, strD);
                    }
                }
            }
        }
    }

    public CacheStrategy(Request request, Response response) {
        this.a = request;
        this.b = response;
    }
}
