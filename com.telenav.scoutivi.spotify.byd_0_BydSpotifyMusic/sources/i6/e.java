package i6;

import c6.b0;
import c6.m;
import c6.n;
import c6.t;
import c6.u;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.l;
import p6.h;
import y5.p;

/* JADX INFO: compiled from: HttpHeaders.kt */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final p6.h f7049a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final p6.h f7050b;

    static {
        h.a aVar = p6.h.f8870j;
        f7049a = aVar.c("\"\\");
        f7050b = aVar.c("\t ,=");
    }

    public static final List<c6.h> a(t parseChallenges, String headerName) {
        l.e(parseChallenges, "$this$parseChallenges");
        l.e(headerName, "headerName");
        ArrayList arrayList = new ArrayList();
        int size = parseChallenges.size();
        for (int i7 = 0; i7 < size; i7++) {
            if (p.o(headerName, parseChallenges.d(i7), true)) {
                try {
                    c(new p6.e().H(parseChallenges.f(i7)), arrayList);
                } catch (EOFException e7) {
                    l6.h.f8004c.g().j("Unable to parse challenge", 5, e7);
                }
            }
        }
        return arrayList;
    }

    public static final boolean b(b0 promisesBody) {
        l.e(promisesBody, "$this$promisesBody");
        if (l.a(promisesBody.g0().g(), "HEAD")) {
            return false;
        }
        int I = promisesBody.I();
        return (((I >= 100 && I < 200) || I == 204 || I == 304) && d6.b.r(promisesBody) == -1 && !p.o("chunked", b0.U(promisesBody, "Transfer-Encoding", null, 2, null), true)) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x0085, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0085, code lost:
    
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0090  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final void c(p6.e r7, java.util.List<c6.h> r8) throws java.io.EOFException {
        /*
            r0 = 0
        L1:
            r1 = r0
        L2:
            if (r1 != 0) goto Le
            g(r7)
            java.lang.String r1 = e(r7)
            if (r1 != 0) goto Le
            return
        Le:
            boolean r2 = g(r7)
            java.lang.String r3 = e(r7)
            if (r3 != 0) goto L2c
            boolean r7 = r7.J()
            if (r7 != 0) goto L1f
            return
        L1f:
            c6.h r7 = new c6.h
            java.util.Map r0 = h5.e0.f()
            r7.<init>(r1, r0)
            r8.add(r7)
            return
        L2c:
            r4 = 61
            byte r4 = (byte) r4
            int r5 = d6.b.F(r7, r4)
            boolean r6 = g(r7)
            if (r2 != 0) goto L68
            if (r6 != 0) goto L41
            boolean r2 = r7.J()
            if (r2 == 0) goto L68
        L41:
            c6.h r2 = new c6.h
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r3)
            java.lang.String r3 = "="
            java.lang.String r3 = y5.g.s(r3, r5)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            java.util.Map r3 = java.util.Collections.singletonMap(r0, r3)
            java.lang.String r4 = "Collections.singletonMap…ek + \"=\".repeat(eqCount))"
            kotlin.jvm.internal.l.d(r3, r4)
            r2.<init>(r1, r3)
            r8.add(r2)
            goto L1
        L68:
            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
            r2.<init>()
            int r6 = d6.b.F(r7, r4)
            int r5 = r5 + r6
        L72:
            if (r3 != 0) goto L83
            java.lang.String r3 = e(r7)
            boolean r5 = g(r7)
            if (r5 == 0) goto L7f
            goto L85
        L7f:
            int r5 = d6.b.F(r7, r4)
        L83:
            if (r5 != 0) goto L90
        L85:
            c6.h r4 = new c6.h
            r4.<init>(r1, r2)
            r8.add(r4)
            r1 = r3
            goto L2
        L90:
            r6 = 1
            if (r5 <= r6) goto L94
            return
        L94:
            boolean r6 = g(r7)
            if (r6 == 0) goto L9b
            return
        L9b:
            r6 = 34
            byte r6 = (byte) r6
            boolean r6 = h(r7, r6)
            if (r6 == 0) goto La9
            java.lang.String r6 = d(r7)
            goto Lad
        La9:
            java.lang.String r6 = e(r7)
        Lad:
            if (r6 == 0) goto Lc7
            java.lang.Object r3 = r2.put(r3, r6)
            java.lang.String r3 = (java.lang.String) r3
            if (r3 == 0) goto Lb8
            return
        Lb8:
            boolean r3 = g(r7)
            if (r3 != 0) goto Lc5
            boolean r3 = r7.J()
            if (r3 != 0) goto Lc5
            return
        Lc5:
            r3 = r0
            goto L72
        Lc7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: i6.e.c(p6.e, java.util.List):void");
    }

    private static final String d(p6.e eVar) throws EOFException {
        byte b7 = (byte) 34;
        if (!(eVar.Y() == b7)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        p6.e eVar2 = new p6.e();
        while (true) {
            long jU = eVar.U(f7049a);
            if (jU == -1) {
                return null;
            }
            if (eVar.N(jU) == b7) {
                eVar2.l(eVar, jU);
                eVar.Y();
                return eVar2.g0();
            }
            if (eVar.j0() == jU + 1) {
                return null;
            }
            eVar2.l(eVar, jU);
            eVar.Y();
            eVar2.l(eVar, 1L);
        }
    }

    private static final String e(p6.e eVar) {
        long jU = eVar.U(f7050b);
        if (jU == -1) {
            jU = eVar.j0();
        }
        if (jU != 0) {
            return eVar.h0(jU);
        }
        return null;
    }

    public static final void f(n receiveHeaders, u url, t headers) {
        l.e(receiveHeaders, "$this$receiveHeaders");
        l.e(url, "url");
        l.e(headers, "headers");
        if (receiveHeaders == n.f3822a) {
            return;
        }
        List<m> listE = m.f3812n.e(url, headers);
        if (listE.isEmpty()) {
            return;
        }
        receiveHeaders.a(url, listE);
    }

    private static final boolean g(p6.e eVar) throws EOFException {
        boolean z6 = false;
        while (!eVar.J()) {
            byte bN = eVar.N(0L);
            if (bN == 9 || bN == 32) {
                eVar.Y();
            } else {
                if (bN != 44) {
                    break;
                }
                eVar.Y();
                z6 = true;
            }
        }
        return z6;
    }

    private static final boolean h(p6.e eVar, byte b7) {
        return !eVar.J() && eVar.N(0L) == b7;
    }
}
