package okhttp3.internal.cache;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.Response;
import p.ng2;

/* JADX INFO: loaded from: classes.dex */
public final class CacheInterceptor implements Interceptor {
    public static final Companion c = new Companion(0);
    public final Cache b;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }

        public static final Response a(Companion companion, Response response) {
            companion.getClass();
            if ((response != null ? response.o : null) == null) {
                return response;
            }
            Response.Builder builderQ = response.q();
            builderQ.g = null;
            return builderQ.a();
        }

        public static boolean b(String str) {
            return (ng2.b0("Connection", str, true) || ng2.b0("Keep-Alive", str, true) || ng2.b0("Proxy-Authenticate", str, true) || ng2.b0("Proxy-Authorization", str, true) || ng2.b0("TE", str, true) || ng2.b0("Trailers", str, true) || ng2.b0("Transfer-Encoding", str, true) || ng2.b0("Upgrade", str, true)) ? false : true;
        }
    }

    public CacheInterceptor(Cache cache) {
        this.b = cache;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0198  */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v26, types: [okhttp3.Request, okhttp3.Response] */
    /* JADX WARN: Type inference failed for: r0v47 */
    /* JADX WARN: Type inference failed for: r0v8 */
    @Override // okhttp3.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final okhttp3.Response intercept(okhttp3.Interceptor.Chain r27) {
        /*
            Method dump skipped, instruction units count: 1125
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.CacheInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }
}
