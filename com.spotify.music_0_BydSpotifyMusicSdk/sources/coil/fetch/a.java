package coil.fetch;

import android.webkit.MimeTypeMap;
import coil.request.CachePolicy;
import java.io.IOException;
import java.util.Map;
import kotlin.text.b;
import okhttp3.CacheControl;
import okhttp3.MediaType;
import okhttp3.Request;
import p.br1;
import p.ca0;
import p.du0;
import p.hu0;
import p.i;
import p.ng2;
import p.os1;
import p.qt0;
import p.v03;
import p.v81;
import p.w02;
import p.y02;
import p.y90;
import p.z02;

/* JADX INFO: loaded from: classes.dex */
public final class a implements qt0 {
    public static final CacheControl f;
    public static final CacheControl g;
    public final String a;
    public final br1 b;
    public final v81 c;
    public final v81 d;
    public final boolean e;

    static {
        CacheControl.Builder builder = new CacheControl.Builder();
        builder.a = true;
        builder.b = true;
        f = builder.a();
        CacheControl.Builder builder2 = new CacheControl.Builder();
        builder2.a = true;
        builder2.f = true;
        g = builder2.a();
    }

    public a(String str, br1 br1Var, v81 v81Var, v81 v81Var2, boolean z) {
        this.a = str;
        this.b = br1Var;
        this.c = v81Var;
        this.d = v81Var2;
        this.e = z;
    }

    public static String d(String str, MediaType mediaType) {
        String strB;
        String str2 = mediaType != null ? mediaType.a : null;
        if ((str2 == null || ng2.g0(str2, "text/plain", false)) && (strB = i.b(MimeTypeMap.getSingleton(), str)) != null) {
            return strB;
        }
        if (str2 != null) {
            return b.E0(str2, ';');
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x020f A[Catch: Exception -> 0x0199, TryCatch #2 {Exception -> 0x0199, blocks: (B:93:0x01e1, B:95:0x01e7, B:97:0x0206, B:99:0x020b, B:98:0x0209, B:101:0x020f, B:102:0x0218, B:69:0x0169, B:72:0x0175, B:74:0x0181, B:76:0x018f, B:80:0x019b, B:82:0x01a3, B:84:0x01bd, B:86:0x01c2, B:85:0x01c0, B:88:0x01c6), top: B:114:0x0169 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x009f A[Catch: Exception -> 0x00d8, TRY_ENTER, TryCatch #3 {Exception -> 0x00d8, blocks: (B:103:0x0219, B:104:0x021c, B:67:0x0161, B:105:0x021d, B:106:0x0226, B:36:0x009f, B:38:0x00aa, B:47:0x00dc, B:49:0x00e0, B:53:0x00f9, B:63:0x0149, B:55:0x0111, B:57:0x011d, B:58:0x0126, B:41:0x00c0, B:43:0x00c8, B:60:0x0130, B:61:0x013b, B:62:0x013c), top: B:115:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x013c A[Catch: Exception -> 0x00d8, TryCatch #3 {Exception -> 0x00d8, blocks: (B:103:0x0219, B:104:0x021c, B:67:0x0161, B:105:0x021d, B:106:0x0226, B:36:0x009f, B:38:0x00aa, B:47:0x00dc, B:49:0x00e0, B:53:0x00f9, B:63:0x0149, B:55:0x0111, B:57:0x011d, B:58:0x0126, B:41:0x00c0, B:43:0x00c8, B:60:0x0130, B:61:0x013b, B:62:0x013c), top: B:115:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x015c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01e7 A[Catch: Exception -> 0x0199, TryCatch #2 {Exception -> 0x0199, blocks: (B:93:0x01e1, B:95:0x01e7, B:97:0x0206, B:99:0x020b, B:98:0x0209, B:101:0x020f, B:102:0x0218, B:69:0x0169, B:72:0x0175, B:74:0x0181, B:76:0x018f, B:80:0x019b, B:82:0x01a3, B:84:0x01bd, B:86:0x01c2, B:85:0x01c0, B:88:0x01c6), top: B:114:0x0169 }] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v14, types: [coil.fetch.a, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r14v0, types: [coil.fetch.a, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v11, types: [p.x90] */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v17, types: [p.x90] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4, types: [coil.fetch.a, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v7 */
    @Override // p.qt0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(p.c00 r15) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 557
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.fetch.a.a(p.c00):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(okhttp3.Request r5, p.c00 r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof coil.fetch.HttpUriFetcher$executeNetworkRequest$1
            if (r0 == 0) goto L13
            r0 = r6
            coil.fetch.HttpUriFetcher$executeNetworkRequest$1 r0 = (coil.fetch.HttpUriFetcher$executeNetworkRequest$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            coil.fetch.HttpUriFetcher$executeNetworkRequest$1 r0 = new coil.fetch.HttpUriFetcher$executeNetworkRequest$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.a.d(r6)
            goto L8d
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2f:
            kotlin.a.d(r6)
            android.graphics.Bitmap$Config[] r6 = p.i.a
            android.os.Looper r6 = android.os.Looper.myLooper()
            android.os.Looper r2 = android.os.Looper.getMainLooper()
            boolean r6 = p.v03.b(r6, r2)
            p.v81 r2 = r4.c
            if (r6 == 0) goto L63
            p.br1 r6 = r4.b
            coil.request.CachePolicy r6 = r6.o
            boolean r6 = r6.a()
            if (r6 != 0) goto L5d
            java.lang.Object r6 = r2.getValue()
            okhttp3.Call$Factory r6 = (okhttp3.Call.Factory) r6
            okhttp3.internal.connection.RealCall r5 = r6.d(r5)
            okhttp3.Response r5 = r5.b()
            goto L90
        L5d:
            android.os.NetworkOnMainThreadException r5 = new android.os.NetworkOnMainThreadException
            r5.<init>()
            throw r5
        L63:
            java.lang.Object r6 = r2.getValue()
            okhttp3.Call$Factory r6 = (okhttp3.Call.Factory) r6
            okhttp3.internal.connection.RealCall r5 = r6.d(r5)
            r0.label = r3
            p.zp r6 = new p.zp
            p.c00 r0 = p.vr2.k(r0)
            r6.<init>(r3, r0)
            r6.r()
            p.e00 r0 = new p.e00
            r0.<init>(r5, r6)
            r5.J(r0)
            r6.t(r0)
            java.lang.Object r6 = r6.q()
            if (r6 != r1) goto L8d
            return r1
        L8d:
            r5 = r6
            okhttp3.Response r5 = (okhttp3.Response) r5
        L90:
            boolean r6 = r5.i()
            if (r6 != 0) goto La9
            r6 = 304(0x130, float:4.26E-43)
            int r0 = r5.l
            if (r0 == r6) goto La9
            okhttp3.ResponseBody r6 = r5.o
            if (r6 == 0) goto La3
            p.i.a(r6)
        La3:
            coil.network.HttpException r6 = new coil.network.HttpException
            r6.<init>(r5)
            throw r6
        La9:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.fetch.a.b(okhttp3.Request, p.c00):java.lang.Object");
    }

    public final hu0 c() {
        Object value = this.d.getValue();
        v03.h(value);
        return ((z02) ((y90) value)).a;
    }

    public final Request e() {
        Request.Builder builder = new Request.Builder();
        builder.h(this.a);
        br1 br1Var = this.b;
        builder.e(br1Var.j);
        for (Map.Entry entry : br1Var.k.a.entrySet()) {
            Object key = entry.getKey();
            v03.g(key, "null cannot be cast to non-null type java.lang.Class<kotlin.Any>");
            builder.g((Class) key, entry.getValue());
        }
        CachePolicy cachePolicy = br1Var.n;
        boolean zA = cachePolicy.a();
        boolean zA2 = br1Var.o.a();
        if (!zA2 && zA) {
            builder.c(CacheControl.f91p);
        } else if (!zA2 || zA) {
            if (!zA2 && !zA) {
                builder.c(g);
            }
        } else if (cachePolicy.b()) {
            builder.c(CacheControl.o);
        } else {
            builder.c(f);
        }
        return builder.b();
    }

    public final coil.network.a f(y02 y02Var) throws Throwable {
        Throwable th;
        coil.network.a aVar;
        try {
            hu0 hu0VarC = c();
            ca0 ca0Var = y02Var.c;
            if (!(!ca0Var.e)) {
                throw new IllegalStateException("snapshot is closed".toString());
            }
            w02 w02VarD = v03.d(hu0VarC.l((os1) ca0Var.c.c.get(0)));
            try {
                aVar = new coil.network.a(w02VarD);
                try {
                    w02VarD.close();
                    th = null;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                try {
                    w02VarD.close();
                } catch (Throwable th4) {
                    kotlin.a.a(th3, th4);
                }
                th = th3;
                aVar = null;
            }
            if (th != null) {
                throw th;
            }
            v03.h(aVar);
            return aVar;
        } catch (IOException unused) {
            return null;
        }
    }

    public final du0 g(y02 y02Var) {
        ca0 ca0Var = y02Var.c;
        if (!(!ca0Var.e)) {
            throw new IllegalStateException("snapshot is closed".toString());
        }
        os1 os1Var = (os1) ca0Var.c.c.get(1);
        hu0 hu0VarC = c();
        String str = this.b.i;
        if (str == null) {
            str = this.a;
        }
        return new du0(os1Var, hu0VarC, str, y02Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p.y02 h(p.x90 r6, okhttp3.Request r7, okhttp3.Response r8, coil.network.a r9) {
        /*
            Method dump skipped, instruction units count: 359
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.fetch.a.h(p.x90, okhttp3.Request, okhttp3.Response, coil.network.a):p.y02");
    }
}
