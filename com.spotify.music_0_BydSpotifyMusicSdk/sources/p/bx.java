package p;

import com.spotify.remoteconfig.commons.logging.EventLogger$FetchErrorReason;
import com.spotify.remoteconfig.fetcher.FetchType;
import com.spotify.remoteconfig.fetcherimpl.network.ResolveRequestFailedException;
import com.spotify.remoteconfig.fetcherimpl.network.UcsRequestFailedException;
import java.net.SocketTimeoutException;
import kotlin.Triple;

/* JADX INFO: loaded from: classes.dex */
public final class bx {
    public final cg2 a;
    public final ki0 b;
    public final Triple c = new Triple(null, null, null);

    public bx(cg2 cg2Var, d51 d51Var) {
        this.a = cg2Var;
        this.b = d51Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0047 A[PHI: r3
  0x0047: PHI (r3v2 java.lang.String) = (r3v1 java.lang.String), (r3v7 java.lang.String) binds: [B:11:0x0025, B:19:0x003c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(long r16, com.spotify.remoteconfig.fetcher.FetchType r18, p.u42 r19) {
        /*
            r15 = this;
            r0 = r19
            r1 = -1
            if (r0 == 0) goto L10
            okhttp3.Response r2 = r0.a
            if (r2 == 0) goto L10
            okhttp3.Response r2 = r2.f96p
            if (r2 == 0) goto L10
            int r2 = r2.l
            goto L11
        L10:
            r2 = r1
        L11:
            com.spotify.remoteconfig.commons.logging.EventLogger$FetchErrorReason r6 = p.l13.p(r2)
            java.lang.String r2 = "<this>"
            p.v03.j(r0, r2)
            okhttp3.Response r2 = r0.a
            okhttp3.Headers r3 = r2.n
            java.lang.String r4 = "grpc-message"
            java.lang.String r3 = r3.a(r4)
            r4 = 1
            if (r3 != 0) goto L47
            r3 = 0
            okhttp3.ResponseBody r0 = r0.c
            if (r0 == 0) goto L3c
            java.lang.String r0 = r0.q()
            if (r0 == 0) goto L3c
            java.lang.String r5 = ""
            boolean r5 = p.v03.b(r0, r5)
            r5 = r5 ^ r4
            if (r5 == 0) goto L3c
            r3 = r0
        L3c:
            if (r3 != 0) goto L47
            java.lang.String r0 = r2.k
            java.lang.String r3 = "message()"
            p.v03.i(r0, r3)
            r7 = r0
            goto L48
        L47:
            r7 = r3
        L48:
            p.hk0 r0 = p.qk2.a()
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            okhttp3.Response r2 = r2.f96p
            if (r2 == 0) goto L56
            int r5 = r2.l
            goto L57
        L56:
            r5 = r1
        L57:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r8 = 0
            r3[r8] = r5
            r3[r4] = r7
            r0.getClass()
            p.hk0.B(r3)
            java.lang.String r10 = r18.name()
            p.ji0 r13 = new p.ji0
            r8 = 232(0xe8, float:3.25E-43)
            r3 = r13
            r4 = r6
            r5 = r7
            r3.<init>(r4, r5, r6, r7, r8)
            if (r2 == 0) goto L78
            int r1 = r2.l
        L78:
            r0 = r15
            r14 = r1
            p.ki0 r1 = r0.b
            r9 = r1
            p.d51 r9 = (p.d51) r9
            r11 = r16
            r9.c(r10, r11, r13, r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p.bx.a(long, com.spotify.remoteconfig.fetcher.FetchType, p.u42):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0046 A[PHI: r3
  0x0046: PHI (r3v2 java.lang.String) = (r3v1 java.lang.String), (r3v6 java.lang.String) binds: [B:11:0x0025, B:19:0x003c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(long r16, com.spotify.remoteconfig.fetcher.FetchType r18, p.u42 r19) {
        /*
            r15 = this;
            r0 = r19
            r1 = -1
            if (r0 == 0) goto L10
            okhttp3.Response r2 = r0.a
            if (r2 == 0) goto L10
            okhttp3.Response r2 = r2.f96p
            if (r2 == 0) goto L10
            int r2 = r2.l
            goto L11
        L10:
            r2 = r1
        L11:
            com.spotify.remoteconfig.commons.logging.EventLogger$FetchErrorReason r4 = p.l13.p(r2)
            java.lang.String r2 = "<this>"
            p.v03.j(r0, r2)
            okhttp3.Response r2 = r0.a
            okhttp3.Headers r3 = r2.n
            java.lang.String r5 = "grpc-message"
            java.lang.String r3 = r3.a(r5)
            r5 = 1
            if (r3 != 0) goto L46
            r3 = 0
            okhttp3.ResponseBody r0 = r0.c
            if (r0 == 0) goto L3c
            java.lang.String r0 = r0.q()
            if (r0 == 0) goto L3c
            java.lang.String r6 = ""
            boolean r6 = p.v03.b(r0, r6)
            r6 = r6 ^ r5
            if (r6 == 0) goto L3c
            r3 = r0
        L3c:
            if (r3 != 0) goto L46
            java.lang.String r0 = r2.k
            java.lang.String r3 = "message()"
            p.v03.i(r0, r3)
            goto L47
        L46:
            r0 = r3
        L47:
            p.hk0 r3 = p.qk2.a()
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]
            okhttp3.Response r2 = r2.f96p
            if (r2 == 0) goto L55
            int r7 = r2.l
            goto L56
        L55:
            r7 = r1
        L56:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r8 = 0
            r6[r8] = r7
            r6[r5] = r0
            r3.getClass()
            p.hk0.B(r6)
            java.lang.String r10 = r18.name()
            p.ji0 r13 = new p.ji0
            r6 = 0
            r7 = 0
            r8 = 252(0xfc, float:3.53E-43)
            r3 = r13
            r5 = r0
            r3.<init>(r4, r5, r6, r7, r8)
            if (r2 == 0) goto L78
            int r1 = r2.l
        L78:
            r0 = r15
            r14 = r1
            p.ki0 r1 = r0.b
            r9 = r1
            p.d51 r9 = (p.d51) r9
            r11 = r16
            r9.c(r10, r11, r13, r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p.bx.b(long, com.spotify.remoteconfig.fetcher.FetchType, p.u42):void");
    }

    public final void c(Throwable th, FetchType fetchType, long j) {
        v03.j(th, "error");
        v03.j(fetchType, "fetchType");
        hk0 hk0VarA = qk2.a();
        Object[] objArr = {Long.valueOf(j)};
        hk0VarA.getClass();
        hk0.C(objArr);
        if (th instanceof UcsRequestFailedException) {
            b(j, fetchType, ((UcsRequestFailedException) th).a());
            return;
        }
        if (th instanceof ResolveRequestFailedException) {
            a(j, fetchType, ((ResolveRequestFailedException) th).a());
            return;
        }
        boolean z = th instanceof SocketTimeoutException;
        ki0 ki0Var = this.b;
        if (z || v03.b(th.getMessage(), "timeout")) {
            ((d51) ki0Var).c(fetchType.name(), j, new ji0(EventLogger$FetchErrorReason.TIMEOUT, th.getMessage(), null, null, 252), -1);
            return;
        }
        if (th.getMessage() != null) {
            ((d51) ki0Var).c(fetchType.name(), j, new ji0(EventLogger$FetchErrorReason.CLIENT_ERROR, th.getMessage(), null, null, 252), -1);
            return;
        }
        String message = th.getMessage();
        if (message == null) {
            message = "unknown error message";
        }
        StringBuilder sbF = hf2.f(message, " - ");
        Throwable cause = th.getCause();
        sbF.append(cause != null ? cause.getMessage() : null);
        ((d51) ki0Var).c(fetchType.name(), j, new ji0(EventLogger$FetchErrorReason.UNKNOWN, sbF.toString(), null, null, 252), -1);
    }
}
